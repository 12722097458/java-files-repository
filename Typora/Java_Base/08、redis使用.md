Redis是一个非关系型的key-value数据库。

它通常被称为数据结构服务器：

阅读本教程前，你需要了解基本的数据结构，例如以下几种：

- String: 字符串
- Hash: 散列
- List: 列表
- Set: 集合
- Sorted Set: 有序集合

使用方法：

==**1、导入依赖**==

```xml
<!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>3.0.1</version>
</dependency>
```

==**2、普通增删改**==

```java
package com.igeek.ssm.utils;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisTest {



    @Test
    public void testRedisConnect(){
        //获取连接
        Jedis jedis = new Jedis("192.168.189.128",6379);
        //1、===========String类型============
        jedis.set("name","Jack");
        String name = jedis.get("name");
        //删除
        jedis.del("name");
        System.out.println("新增的name="+name);
        System.out.println("1、========end==String类型===========");
        System.out.println();


        //2、===========hash类型============
        jedis.hset("hashMap","name","Jack");
        jedis.hset("hashMap","age","17");
        jedis.hset("hashMap","gender","male");
        String gender = jedis.hget("hashMap", "gender");
        //查询
        System.out.println("hashMap.gender = "+gender);
        //删除指定的key
        jedis.hdel("hashMap","gender");
        Map<String, String> hashMap = jedis.hgetAll("hashMap");
        Set<String> keySet = hashMap.keySet();
        for (String key : keySet) {
            System.out.println(key + " = " + hashMap.get(key));
        }
        jedis.hdel("hashMap","name");
        jedis.hdel("hashMap","age");
        System.out.println("2、==== end ==hash类型========");
        System.out.println();

        //3、===========list类型============
        jedis.lpush("lists","a");
        jedis.lpush("lists","a");
        jedis.lpush("lists","b");
        jedis.lpush("lists","c");
        jedis.rpush("lists","a");
        jedis.rpush("lists","b");
        jedis.rpush("lists","c");
        List<String> lists = jedis.lrange("lists", 0, -1);
        System.out.println(lists);
        //[c, b, a, a, a, b, c]   lpop删掉第一个c   rpop 删掉最后一个'c'
        for (int i = 0; i < lists.size(); i++) {
            jedis.lpop("lists");
        }
        System.out.println("3、======end====list类型============");
        System.out.println();

        //4、===========set类型============
        jedis.sadd("sets","c");
        jedis.sadd("sets","c");
        jedis.sadd("sets","b");
        jedis.sadd("sets","a");
        jedis.sadd("sets","aa");
        jedis.sadd("sets","ac");
        Set<String> sets = jedis.smembers("sets");
        for (String set : sets) {
            System.out.println(set);
        }
        for (String set : sets) {
            jedis.srem("sets",set);
        }
        System.out.println("4、==end===set类型============");
        System.out.println();


        //5、===========sortedset类型============
        jedis.zadd("sortedsets",1.1,"a");
        jedis.zadd("sortedsets",1.3,"b");
        jedis.zadd("sortedsets",1.3,"d");
        jedis.zadd("sortedsets",1.4,"c");
        jedis.zadd("sortedsets",1.5,"a");
        Set<String> sortedsets = jedis.zrange("sortedsets", 0, -1);
        for (String sortedset : sortedsets) {
            System.out.println(sortedset);
        }
        for (String sortedset : sortedsets) {
            jedis.zrem("sortedsets",sortedset);
        }
        System.out.println("5、=======end====sortedset类型============");
        System.out.println();

        //***********************************
        //6、redis的应用：
        // 1、可以利用设置带有过期时间的变量，对注册注册后输入手机短信验证码的有效时间进行处理
        // 2、可以对订单30分钟未付款自动进行删除
        jedis.setex("int",30,"哈哈哈");
        System.out.println("key为int的字符串类型关数据将在30秒后自动删除！");
        //***********************************


        jedis.close();
    }

    //
    @Test
    public void testJedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.189.128",6379);
        Jedis jedis = jedisPool.getResource();
        jedis.setex("exxxx",30,"hahahahha");
    }

    @Test
    public void testJedisUtils(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.set("aaa","bbb");
        JedisUtils.closeResources(jedis);
    }


}

```

==**3、工具类编写**==

```java
package com.igeek.ssm.utils;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisUtils {

    private static JedisPool jedisPool = null;
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        Properties properties = new Properties();
        InputStream is = JedisUtils.class.getClassLoader().getResourceAsStream("jedis-config.xml");
        try {
            properties.load(is);
            String host = properties.getProperty("Host");
            String port = properties.getProperty("Port");
            String maxTotal = properties.getProperty("MaxTotal");
            String maxIdle = properties.getProperty("MaxIdle");
            jedisPoolConfig.setMaxTotal(Integer.parseInt(maxTotal));
            jedisPoolConfig.setMaxIdle(Integer.parseInt(maxIdle));
            System.out.println(host);
            jedisPool = new JedisPool(jedisPoolConfig,host, Integer.parseInt(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
    public static void closeResources(Jedis jedis){
        jedis.close();
    }

}

```

==**4、工具类引用的外部资源**==

```xml
Host=192.168.189.128
Port=6379
MaxTotal=50
MaxIdle=10
```



<h2>**各种数据类型操作对比：**<h2>

|                     | 增                                         | 查                                           | 删                         |
| ------------------- | ------------------------------------------ | -------------------------------------------- | -------------------------- |
| string：字符串      | set name Jack                              | get name                                     | del name                   |
| hash：散列          | hset myhash name Jack / hset myhash age 16 | hget myhash name  /  hget all myhash查询所有 | hdel myhash name           |
| list：列表，可重复  | lput mylist a / rput list m                | lrange mylist 0 -1                           | lpop mylist  / rpop mylist |
| set：集合，不可重复 | sadd myset v                               | smembers myset                               | srem myset v:删除某个元素  |
| sortedset：有序集合 | zadd mysortedset 16 value                  | zrange mysortedset 0 -1                      | zrem mysortedset  value    |

