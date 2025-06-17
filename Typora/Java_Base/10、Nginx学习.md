### 一、nginx安装

1、在官网下载相应版本的nginx的tar包

2、上传到/app/tools目录下

3、进入目录/app/tools/nginx-1.18.0

```shell
./configure

如果报错requires the PCRE library，执行下行命令
yum -y install zlib zlib-devel openssl openssl--devel pcre pcre-devel

make
make install

whereis nginx -- /usr/local/nginx
```

4、启动nginx

```shell
cd /usr/local/nginx/sbin
./nginx    # 启动
./nginx -s stop  #停止
./nginx -s quit  #安全退出
./nginx -s reload  #重新加载配置文件


/usr/local/nginx/sbin/nginx  -c /app/tools/springcloud/nginx-1.18.0/conf/nginx.conf


service iptables stop  #关闭防火墙
```

访问：http://192.168.118.128/默认端口为80

![image-20210305232200257](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220801081403.png)

### 二、配置

#### 1、新建项目boot_redis01和boot_redis02

两者内容一致，只是端口不同，模拟分布式部署，高并发卖票情况。

遇到并解决的问题主要有：

（1）单机模式下利用jmeter压测，票会出现重复出售的情况：在JVM层加synchronized或lock锁，推荐用lock，因为trylock()可以设置等待时间，避免大量的线程聚集。

（2）通过nginx配置，搭建分布式环境，两个项目轮询调用buy_goods接口。高并发情况下，无法保证安全。synchronized和lock只能保证单机情况下的安全，分布式无法确保：由于redis是单一的，所以可以在redis上手动加一个锁，当boot01访问时，直接用uuid上锁"redis_lock"即插入一个string的键值对，操作完毕后，释放redis_lock。

（4）问题可能是未能正常解锁，导致程序阻塞宕机：首先用try-catch-finally代码块，解锁过程放在finally中。

（5）如果boot01和boot02部署项目的linux服务器宕机了，这是正在运行的程序可能无法对redis的redis_lock解锁了：在设置redis锁时加一个过期时间

（6）添加redis_lock和设置过期时间需要是原子操作，需要选用恰当的API：set k1 v1 EX 10

（7）过期时间的设置需要考虑清楚：如果设置过短，某次业务遇到网络故障等运行缓慢，还未结束，直接自动解锁了，等到其真正结束finally块中又进行了delete操作，此时删除的不是自己设置的key，出现混乱：删除前需要判断当前的value是不是自己设置的。

（8）当然，删除前有一个校验操作需要get和delete，这两个需要保证线程安全：可以用用Lua脚本或者redis的watch-multi-exec事务操作。

（9）上述比较麻烦，可以直接用**Redisson**来获取锁，并实现分布式的线性安全。

```java
package com.ityj.redis.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;


@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    /**
     * 保证不是序列化后的乱码配置
     */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory){
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisHost + ":6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
 
 
```

```java
@RestController
public class GoodController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private Redisson redisson;

    private static final String REDIS_TEST_LOCK = "REDIS_TEST_LOCK";


    @GetMapping("/buy_goods")
    public String buy_Goods(){

        RLock redissonLock = redisson.getLock(REDIS_TEST_LOCK);
        redissonLock.lock();
        try {
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);

            if (goodsNumber > 0){
                int realNumber = goodsNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001",realNumber + "");
                System.out.println("你已经成功秒杀商品，此时还剩余：" + realNumber + "件"+"\t 服务器端口: "+serverPort);
                return "你已经成功秒杀商品，此时还剩余：" + realNumber + "件"+"\t 服务器端口: "+serverPort;
            }else {
                System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临"+"\t 服务器端口: "+serverPort);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            redissonLock.unlock();
        }
        return "商品已经售罄/活动结束/调用超时，欢迎下次光临"+"\t 服务器端口: "+serverPort;
    }
```

```yml
server:
  port: 2222

spring:
  redis:
    database: 0
    host: 192.168.118.128
    port: 6379
    lettuce:
      pool:
        max-active: 8
        max-wait: -1
        max-idle: 8
        min-idle: 0
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.3</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.ityj.redis</groupId>
    <artifactId>boot_redis02</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>boot_redis02</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-redis -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-pool2 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>3.1.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-aop -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.redisson/redisson -->
        <dependency>
            <groupId>org.redisson</groupId>
            <artifactId>redisson</artifactId>
            <version>3.13.4</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>


        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>


    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

others:

启动redis：

```shell
cd /app/tools/redis-3.0.7/bin
./redis-server ./config/redis6379.conf
```



#### 2、配置linux上的nginx

实现对windows下的两个boot项目的负载均衡。

```shell
cd /usr/local/nginx/conf
```

```shell

#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;
	
	# 注意，这里的server名字即org.tonny.balance不能带下划线

	 upstream com.ityj.redis {

	   # 项目的地址 windows
	   server 192.168.1.5:1111 weight=1;

	   server 192.168.1.5:2222 weight=1;

	 }

    server {
        listen       80;
        server_name  localhost;
        # 在linux上访问http://localhost:80会去找http://com.ityj.redis
        # http://com.ityj.redis 又会去找192.168.1.5:1111、192.168.1.5:2222

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            #root   html;
            #index  index.html index.htm;
	    	proxy_pass   http://com.ityj.redis;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }


    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}

```

> 192.168.1.5是windows的IP



学习通是基于微服务架构打造的课程学习，知识传播与管理分享平台。 它利用超星20余年来积累的海量的图书、期刊、报纸、视频、原创等资源，集知识管理、课程学习







others

启动nginx

```shell
cd /usr/local/nginx/sbin
./ nginx

修改完配置文件后重新加载nginx
./nginx -s reload

./nginx -s reopen # 重启 nginx
./nginx -s stop  # 停止 nginx
```





#### 3、测试

启动nginx，redis以及在本地启动boot_redis01和boot_redis02

（1）在linux上访问http://localhost/buy_goods

![image-20210306095358801](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220801081404.png)



（2）在windows本地上访问：http://192.168.118.128/buy_goods

> 192.168.118.128是linux的IP

![image-20210306095515373](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220801081408.png)

（3）至于反向代理，需要在本地的host中配置映射

C:\Windows\System32\drivers\etc\hosts文件

![image-20210306100002755](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220801081411.png)

访问http://com.ityj.redis/buy_goods也可以

![image-20210306100035306](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220801081414.png)

（4）jmeter压测也没问题

![image-20210306100058421](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220801081416.png)

![image-20210306100109931](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220801081418.png)

```php
   这段配置的意思是访问www.aaa.com或者aaa.com的请求，会被nginx映射到http://www.aaa.con:8087/proj1/；而访问www.bbb.com或者bbb.com的请求，会被映射到http://www.aaa.con:8087/proj2/。多配置location /proj1/的原因是避免CSS/JS等在html中设置了项目名路径的资源因为nginx的反向代理造成丢失工程名而无法访问到资源。
```