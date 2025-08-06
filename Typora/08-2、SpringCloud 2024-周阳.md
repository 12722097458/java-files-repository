>  **SpringCloud + SpringCloud alibaba**
>
> https://www.bilibili.com/video/BV1gW421P7RD?spm_id_from=333.788.videopod.episodes&vd_source=b23569b676ce26126febad3c290b16e8&p=2

SpringCloud ：分布式的微服务架构的

一站式解决方案，是多种为服务器架构落地技术的集合体，俗称微服务全家桶

![image-20250731123246725](https://gitee.com/yj1109/cloud-image/raw/master/img/20250731123247082.png)

![image-20250731124043720](https://gitee.com/yj1109/cloud-image/raw/master/img/20250731124044064.png)

# A、SpringCloud

## 一、SpringCloud入门Eureka

#### 1、SpringCloud和SpringBoot版本选择

![image-20250731111157301](https://gitee.com/yj1109/cloud-image/raw/master/img/20250731111157830.png)

#### 3、基本环境搭建

![image-20250731124226457](https://gitee.com/yj1109/cloud-image/raw/master/img/20250731124226777.png)



##### 3、创建order消费者模块

这里是消费者，需要调用生产者provider，因此用到RestTemplate技术，类似于httpClient连接客户端的技术。

> RestTemplate 是从 Spring3.0 开始支持的一个 HTTP 请求工具，它提供了常见的REST请求方案的模版，例如 GET 请求、POST 请求、PUT 请求、DELETE 请求以及一些通用的请求执行方法 exchange 以及 execute。



#### 4、Eureka单机服务注册中心

> Eureka是spring cloud中的一个负责服务注册与发现的组件；
>
> 一个Eureka中分为eureka server和eureka client。其中eureka server是作为服务的注册与发现中心。eureka client既可以作为服务的生产者，又可以作为服务的消费者。

<img src="https://i.loli.net/2021/02/23/cJQSgPer5Hz2hvG.png" alt="image-20210220071348813" style="float:left;" />

#### 5、Eureka集群服务注册中心

如果是单机版的Eureka服务，容错性差。违背了微服务RPC远程服务调用的核心：高可用。

需要搭建Eureka注册中心集群，实现负载均衡+故障容错

![image-20220609233637906](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220609233645.png)

##### 6、Eureka的自我保护机制

Eureka默认的配置eureka.server.enable-self-preservation=true，开启了自我保护机制。

![image-20220611172429514](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731192932.png)

> Eureka的保护模式主要是用于一组客户端和Eureka Server之间存在网络分区场景下的保护。一旦进入保护模式，Eureka Server将会尝试保护其服务注册表中的信息，不再删除服务注册表中的数据，也就是不会销毁任何微服务。（payment8001即使宕机，也会在Eureka界面显示。）

==一句话：某时刻某一个微服务不可用了，Eureka不会立刻清理，依旧会对该微服务的信息进行保存==

==属于CAP里面的AP分支==



## 二、服务注册进zookeeper（单机版）



## 三、服务注册与发现进consul

#### 1.7 Consul总结

==CP，强一致性和分区容错性。==

当某个服务宕机，一定时间未收到心跳响应，直接剔除。

## 四、eureka、zookeeper以及consul异同

CAP：分布式系统有三个指标。CAP理论关注粒度是数据，而不是整体系统设计的策略

* C：Consistency(强一致性)
* A：Availability(可用性)
* P：Partition tolerance(分区容错)：基本都需要满足

![image-20220612115001174](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731192758.png)

1. eureka强调AP，保证服务能正常运行。当某一个服务宕机时，并不会立即将其注册信息删除。好死不如赖活
2. zookeeper只有一个客户端，没有UI页面。
3. zookeeper和consul针对CP。



## 五、服务调用Ribbon & Feign

#### 1、Spring Cloud Ribbon

> Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡工具。
>
> 主要提供客户端的软件负载均衡算法和服务调用

**1、负载均衡（Load Balancer）介绍**

简单地说：负载均衡就是将用户请求平摊分配到多个服务上，从而达到系统的HA（高可用）。

常见的负载均衡软件有：Nginx，LVS，硬件F5等

**2、Ribbon本地负载均衡和Nginx服务端负载均衡区别**

（1）Nginx是服务端负载均衡，客户端所有的请求都会交给nginx，然后由nginx实现转发请求。即负载均衡是由服务端实现的。

（2）Ribbon是本地负载均衡，在调用微服务接口时，会在注册中心获取服务注册信息服务列表后 缓存到JVM的本地，从而在本地实现远程RPC远程服务调用技术。



##### （1）Ribbon的负载规则替换

Ribbon核心组件IRule：根据特定算法从服务列表中选取一个要访问的服务

![image-20210221174753781](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731192713.png)



![image-20210221181929665](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183448.png)

```java
IRule --> AbstractLoadBalancerRule --> RoundRobinRule(默认的轮询机制)  -->choose()方法实现服务器的选择。
```



#### 2、Feign和Open Feign

什么是Feign？

> Feign是一个声明式的WebService客户端，使用Feign能让编写Web Service更加简单。它的使用方法是定义一个服务接口，然后在上面添加注解。Feign可以与Eureka和Ribbon组合使用达到负载均衡。

![image-20210221183416461](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731192637.png)

​	

###### （7）总结

* 启动类上要标明开启OpenFeign注解`@EnableFeignClients`
* 自定义的service接口，需要标明`@FeignClient(value = "CLOUD-PAYMENT-SERVICE") `
* 默认的消费者等待返回结果的超时时间是1S



## 六、服务降级Hystrix

分布式系统面临的问题：

复杂分布式结构中的应用程序有多个依赖关系，每个依赖关系在调用的时候不可避免会出现失败的情况，如果不妥善处理，就可能出现''雪崩''严重后果。

### 1、Hystrix介绍

![image-20210222083030356](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731192545.png)

![image-20210222083203460](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183502.png)



### 4、问题解决（Hystrix）

#### 1. 服务降级（fallback）：设置一个兜底策略

主要用到三个注解

```java
@HystrixCommand、@EnableCircuitBreaker和@DefaultProperties
```

这些可以用在生产者上也可以用在消费者上。一**般情况是会用于消费者端。**



##### （3）问题总结与优化

###### 优化1:defaultFallback

> ​	现在每个方法需要写一个兜底策略，代码冗余严重。可以用@DefaultProperties(defaultFallback = "")注解，对一系列方法配置一个默认的策略。
>
> ```java
>// 全局的兜底策略 ： fallback方法
> public CommonResult<String> globalFallBackHandler() {
>     return CommonResult.fail(Thread.currentThread().getName() + "全局兜底策略执行了。。。");
> }
>    ```
> 
> ```java
>@DefaultProperties(defaultFallback = "globalFallBackHandler", commandProperties = {
>         @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
> })
> ```
>
> ```java
> @GetMapping("/consumer/payment/success/{id}")
> @HystrixCommand
> public CommonResult<String> success(@PathVariable("id") Long id) {
>        //Double.valueOf("sdf");
>     return paymentFeignService.success(id);
>    }
> ```
>    



###### 优化2:FeignClient--> fallback属性

> ```java
>@Component
> @FeignClient(value = "cloud-provider-hystrix-payment")
> public interface PaymentFeignService {
> ```
> 
> 一个自定义的FeignClient接口，可以指向一个服务提供者，因此可以根据这个特点，对这个接口进行实现，完成定制化。避免和业务逻辑混一起。
>
> （1）新建一个类HystrixOrderServiceImpl.java实现接口OrderService
>
> ```java
>@Service
> public class PaymentFallbackService implements PaymentFeignService {
>     @Override
>     public CommonResult<String> success(Long id) {
>         return CommonResult.fail("PaymentFallbackService.success()---> 进入了openfeign默认的兜底策略");
>        }
>    
>        @Override
>        public CommonResult<String> timeout(Long id) {
>            return CommonResult.fail("PaymentFallbackService.timeout()---> 进入了openfeign默认的兜底策略");
>     }
>    }
>    ```
>    
>    （2）修改openfeign访问生产者的统一接口OrderService
>    
> 添加fallback的属性。
> 
>```java
> @Component
>@FeignClient(value = "cloud-provider-hystrix-payment", fallback = PaymentFallbackService.class)
> public interface PaymentFeignService {}
>```
> 
> （3）确保配置文件yml开启了hystrix
> 
> ```yml
> feign:
>    hystrix:
>      	enabled: true #如果处理自身的容错就开启。开启方式与生产端不一样。
>```
> 
> （4）启动相关服务器。进行测试。关闭8001或者在服务提供方制造错误，
>   
>    再次调用链接[localhost/hystrix/consumer/payment/success/89465](http://localhost/hystrix/consumer/payment/success/89465)，发现进入服务降级的方法。
> 
>![image-20220619215826365](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220619215826.png)



#### 2. 服务熔断（break）

![image-20210222221754446](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183508.png)



![image-20210222222923089](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183513.png)

![image-20210222222957912](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183518.png)



#### 3.  服务限流（flowlimit）



## 六一、服务熔断降级 Circuit breaker



> Resilient4j + bulkhead + flowlimit



## 七、Gateway网关

> 原本网关主要是使用zuul1.X但是，1.X存在一些问题，在升级到2.X过程中公司内部出现一些问题，导致效率低下。Spring选择自己研发GateWay

![image-20210224070032972](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183525.png)

![image-20210224070059661](https://i.loli.net/2021/02/24/zBaVjUgdoXkfQMO.png)

![](https://i.loli.net/2021/02/24/zBaVjUgdoXkfQMO.png)



Spring Cloud Gateway 使用的Webflux中的reactor-netty响应式编程组件，底层使用了Netty通讯框架

能干嘛：

* 反向代理
* 鉴权
* 流量控制
* 熔断
* 日志监控

![image-20210224073052192](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731185034.png)

![image-20210224222548368](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183530.png)

![image-20210224222615115](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183534.png)

![image-20210224222702294](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183539.png)



#### 1、三大核心概念

##### 1.1 Route(路由)

路由是构建网关的基本模块，它由ID，目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由

##### 1.2 Predicate（断言）

参考的是java8的java.util.function.Predicate开发人员可以匹配HTTP请求中的所有内容（例如请求头或请求参数），如果请求与断言相匹配则进行路由

##### 1.3 Filter(过滤)

指的是Spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前或者之后对请求进行修改。

![image-20210224222858532](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183543.png)

#### 6、Predicate的使用

启动9527项目，发现启动项里有很多Route Predicate Factories

![image-20210225213937118](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183547.png)

![image-20210225214038310](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183552.png)

总结：说白了，Predicate就是为了实现一组匹配规则，让请求过来找到对应的Route进行处理

添加一系列的and条件



#### 7、Filter的使用

![image-20210225221404766](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183556.png)

##### 1、自定义全局GlobalFilter过滤器

（1）在9725gateway服务中添加GatewayFilter模拟对未登录的用户过滤

即：当请求中不含uname=xxx的键值对时，进行拒绝。

```java
@Component
@Slf4j
public class GatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // http://localhost:9527/payment/discovery?uname=222
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        log.info("Into GatewayFilter..., uname = {}", uname);
        if (!StringUtils.hasText(uname)) {
            log.info("Please input username!");
            exchange.getResponse().setStatusCode(HttpStatus.BAD_GATEWAY);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

```



## 八、Config+Bus+RabbitMQ分布式配置中心 由consul和Nacos替代了

![image-20210228221042471](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183819.png)





## 十、SpringCloud Stream消息驱动

#### 1、消息驱动概述

![image-20220627213315080](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220627213322.png)

==屏蔽底层消息中间件的差异，降低切换版本，统一消息的编程模型==

![image-20210301163054066](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183825.png)

为什么用Cloud Stream？

![image-20210301163212648](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183832.png)

![image-20210301163219732](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183830.png)

![image-20210301163226514](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183838.png)

> Stream中的消息通信方式遵循了发布-订阅模式，Topic主题进行广播：在RabbitMQ就是Exchange，在kafka中就是Topic。



![image-20210301163440328](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183840.png)

![image-20210301163451470](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183842.png)

![image-20210301163701364](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183845.png)



## 十一、SpringCloud Sleuth分布式请求链路追踪 由 Micrometer +Zipkin 替代了

为什么会出现这个技术？需要解决哪些问题？

![image-20210301212342572](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183920.png)

是什么？

> Spring Cloud Sleuth提供了一套完整的服务跟踪的解决方案，在分布式系统中提供追踪解决方案并且兼容支持了zipkin。

![image-20210301212424848](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183923.png)



![image-20210301214019704](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183927.png)

![image-20210301213934749](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183933.png)

> Trace:类似于树结构的Span集合，表示一条调用链路，存在唯一标识；span:表示调用链路来源，通俗的理解span就是一次请求信息







# B、SpringCloud Alibaba

### 一、SpringCloud Alibaba Nacos服务注册和配置中心

#### 1、Nacos简介

Nacos：前四个字母分别为Naming和Configuration的前两个字母，最后的s为Service 

Nacos就是注册中心+配置中心的组合，一个更易于构建云原生应用的动态服务发现，配置管理和服务管理中心

> Nacos = Eureka+Config+Bus

能干嘛？

替代Eureka做服务注册中心，替代Config做服务配置中心。

![image-20210302083906157](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184031.png)



##### 4、各种服务注册对比

![image-20210302092813962](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731183957.png)





#### 4、Nacos作为配置中心替代Config+Bus

> config+bus的配置中心，需要一个server来获取远程仓库的配置，再将此配置分发到各个微服务，链路较长，而同步比较麻烦。
>
> Nacos的前端页面已经集成了配置中心，可以直接在上面修改，而修改后可以直接获取到。降低耦合



###### （3）写yml（2个）

application.yml是获取Nacos配置中心的配置文件内容，而bootstrap.yml是自己个性化的内容。

yml的两个配置，为什么要配置两个？

![](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184008.png)



### 二、Sentinel

>  一句话解释，之前我们讲解过的Hystrix

#### 3、流控模式

##### （1）QPS直接失败

QPS：每秒的访问量

![image-20210302143549378](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184156.png)

表示每秒仅支持访问一次，超过一次将会返回错误信息！



##### （2）线程数直接失败

修改/testB的程序，休眠1分钟

```java
@GetMapping("/testB")
public String testB() {
    
    try {
        TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return "------testB";
}
```

线程数大于1将会直接报错。

此时调用多个线程访问，即多次F5刷新，此时会返回错误信息：



##### （3）流控效果-Warm Up

![image-20210302150430692](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184214.png)

静置一段时间后初次访问开始计算

前5秒为预热时间，这段时间的请求阈值是    10  /   3   =  3；其中10是设置的，3是系统默认的coldFactor

超过5秒，回归正常的阈值设置10；查过10的QPS将会限流



**应用场景：**

![image-20210303082650279](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184227.png)

##### （4）流控效果-排队等待

> testB接口每秒只支持执行一次，多余的接口会排队等待，超时时间为500ms







##### （5）关联模式

A关联B，如果B的QPS查过阈值，A将会限流报错。

订单模块关联支付模块，如果支付模块访问量过大，订单模块需要限流。



#### 4、Sentinel熔断降级

> 1.8+有了新的改版：https://github.com/alibaba/Sentinel/wiki/%E7%86%94%E6%96%AD%E9%99%8D%E7%BA%A7

![image-20210303090210435](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184312.png)

##### （1）RT

![image-20210303090307739](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184248.png)

![image-20210303090314496](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184517.png)

添加测试方法：

```java
@GetMapping("/testD")
public String testD() {
    try { 
        TimeUnit.SECONDS.sleep(1); 
    } catch (InterruptedException e) {
        e.printStackTrace(); 
    }
    log.info("testD 测试RT");

    return "------testD";
}
```

> RT是指平均响应时间，如果1秒内持续进入了5个及以上的线程请求，并且平均响应时间大于0.2秒，那么接下来的5秒钟，服务将会熔断，不可用。5秒后恢复正常。

##### （2）异常比例

> 不要加异常处理器ExceptionHandler，否则不会被认为是异常

![image-20210303094054649](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731185042.png)

![image-20210303094105415](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184319.png)

![image-20210303094354662](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184322.png)

##### （3）异常数

![image-20210303094319222](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184522.png)

![image-20210303094323432](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184325.png)

![image-20210303094341479](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184329.png)

#### 5、热点key降级

> 热点限流就是通过一个自定义的兜底方法，实现对于异常的处理。

之前配置的sentinel降级：如果程序出错，将会返回系统默认的规则。

这里的兜底就是Hystrix中的兜底方法（类似）。

注解这里是@SentinelResource，替代了豪猪哥的@HystrixCommand

##### 1、程序编写实现热点限流

（1）写业务

```java
@SentinelResource(value = "testHotKey", fallback = "hotKey_fallBack")
@GetMapping("/testHotKey")
public String testHotKey(@RequestParam(value = "id", required = false) String id,
                         @RequestParam(value = "name", required = false)  String name) {
    return "------testHotKey:" + id + name;
}

public String hotKey_fallBack(String id, String name) {
    return "------hotKey_fallBack";
}
```

（2）在sentinel配置页面配置

在访问带有p1参数的请求：http://localhost:8401/testHotKey?id=22，如果一分钟请求数大于1，在接下来的统计窗口1秒中，会走兜底方法，而不是Whitelabel Error Page

![image-20220716180510867](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220716180518.png)



##### 2、热点限流设置白名单

如果说http://localhost:8401/testHotKey?id=666的请求p1=666为白名单，就是说访问http://localhost:8401/testHotKey?id=666时，会有一个新的自定义的阈值条件，达到此条件会走兜底方法。

配置即可：

![image-20210303101113651](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184358.png)

测试http://localhost:8401/testHotKey?id=666和http://localhost:8401/testHotKey?id=123

发现666不会走兜底策略（访问没到200阈值）

而p1=123直接很快进入兜底方法。

![image-20220716180704206](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220716180704.png)



#### 6、系统规则

作为系统的总把关，例如网红小吃店，如果只能容纳50人，到50后就拒绝进入了。

![image-20210303103254176](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184403.png)

http://localhost:8401/testA

![image-20220716183000457](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220716183000.png)

最好不要使用，范围太大。



#### 7、@SentinelResource

##### 1、按资源名称限流+后续处理

blockHandler 只针对sentinel控制台的配置的规则，比如限流这些条件达到了，会走handleException方法。

如果java报错，还是把错误信息给显示在了页面上，不和谐

```java
@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流测试OK", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }
}
```

http://localhost:8401/byResource



![image-20210303105237144](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184410.png)

![image-20210303105347348](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184414.png)

多次访问

![image-20210303105254463](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184418.png)



**问题：**

关闭8401服务，sentinel工作台上没有了相关的服务，请求也变为访问错误

![image-20210303105513412](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184422.png)

![image-20210303105546371](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184429.png)

说明注册没有持久化。

##### 2、按照Url地址限流+后续处理

```java
@GetMapping("/rateLimit/byUrl")
@SentinelResource(value = "byUrl")
public CommonResult byUrl()
{
    return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
}
```



![image-20210303105829319](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184434.png)

这个没有编写对应的兜底策略，返回的是系统默认提示。

http://localhost:8401/rateLimit/byUrl

![](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184438.png)

**问题：**

![image-20210303105915372](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184441.png)



##### 3、客户自定义限流处理逻辑

顶一一个全局的兜底策略，出问题走此逻辑。

（1）自定义的全局兜底策略

```java
    /*
    *  1. 必须是 static
    *   com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect.invokeResourceWithSentinel
    *  2. 最后一个参数必须是BlockException，并且前面的参数要和原方法保持一致
    * */
public class CustomerBlockHandler {

    public static CommonResult handleException(BlockException exception) {
        return new CommonResult(2021, "自定义限流处理信息....CustomerBlockHandler");
    }
}
```

（2）普通的业务类

配置了@SentinelResource，以及其指向的兜底策略blockHandlerClass + blockHandler

```java
@GetMapping("/rateLimit/customerBlockHandler")
@SentinelResource(value = "customerBlockHandler",
        blockHandlerClass = CustomerBlockHandler.class,
        blockHandler = "handleException")
public CommonResult customerBlockHandler() {
    return new CommonResult(200, "按客戶自定义", new Payment(2020L, "serial003"));
}
```

（3）测试

![image-20210303111316920](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184444.png)

http://localhost:8401/rateLimit/customerBlockHandler

![image-20210303111854207](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184447.png)

多次访问，可见：走了自定义的全局处理策略。



#### 8、服务熔断功能

> 整合openfeign，结合blockHandler和fallback实现高可用

[代码提交链接](https://github.com/12722097458/spring-cloud-2022/commit/50b95bf18cc5c543fd2a815fda5661c26dd2223c)



#### 9、规则持久化

> Sentinel目前配置的规则并未进行持久化，一旦项目重启，规则将消失。

解决方法：可以存到某些持久化容器里，或者数据库。官方推荐通过nacos进行存储（就是持久化到Mysql中了）



### 三、Seata

**一次业务操作需要跨多个数据源或需要跨多个系统进行远程调用，就会产生分布式事务问题。**

**Seata是一款开源的分布式事务解决方案，致力于在微服务架构下提供高性能和简单易用的分布式事务服务。**

一个典型的分布式事务过程是由**ID+三组件模型**构成；

* Transaction ID（全局唯一的事务ID）
* Transaction Coordinator(TC)：事务协调器，维护全局事务的运行状态，负责协调并驱动全局事务的提交或回滚;
* Transaction  Manager(TM) ：控制全局事务的边界，负责开启一个全局事务，并最终发起全局提交或全局回滚的决议;
* Resource Manager(RM) ：控制分支事务，负责分支注册，状态汇报，并接收事务协调器的指令，驱动分支（本地）事务的提交和回滚；

处理过程：

![image-20210303152643881](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184526.png)

![image-20210303152647818](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731184457.png)







# C、开发中遇到的BUG

## 1、application.yml的样式未改变。

(1) 父项目是一个简单的maven项目，不需要引入springboot-parent. 之前直接引入了parent，导致子模块的jar包不能正常引入。==子模块pom文件没有正常引入==

(2) 配置文件名字错误：写成了**appliaction.yml**

## 2、jar包冲突

> 根目录下莫名多了一个lib包，里面有一些jar,导致和maven仓库里的jar包冲突掉，项目启动失败。删除Lib文件夹。重启解决。

## 3、无空参构造导致报错

> com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.ityj.springcloud.entity.model.CommonResult` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)

## 4、微服务Devtool热部署无法生效

> 原因是把spring-boot-devtools依赖放到commons模块。但是加了一个 <optional>true</optional>去掉就可以了。

==<optional>true</optional>表示两个项目之间依赖不传递；不设置optional或者optional是false，表示传递依赖。==

## 5、在zookeeper作为服务注册中心时，servicename大小写必须和配置文件中保持一致

```shell
{"code":-1,"msg":"java.lang.IllegalStateException: No instances available for CLOUD-PAYMENT-SERVICE","data":null}
```

> org.springframework.cloud.client.discovery.DiscoveryClient#getInstances

对于zookeeper，大小写敏感

```java
public ZookeeperDependency getDependencyForAlias(final String alias) {
    for (Map.Entry<String, ZookeeperDependency> zookeeperDependencyEntry : this.dependencies
         .entrySet()) {
        if (zookeeperDependencyEntry.getKey().equals(alias)) {
            return zookeeperDependencyEntry.getValue();
        }
    }
    return null;
}
```

对于eureka，大小写不敏感

```java
public List<InstanceInfo> getInstancesByVirtualHostName(String virtualHostName) {
    return Optional.ofNullable(this.virtualHostNameAppMap.get(virtualHostName.toUpperCase(Locale.ROOT)))
        .map(VipIndexSupport::getVipList)
        .map(AtomicReference::get)
        .orElseGet(Collections::emptyList); 
}
```



## 6、commons模块里引入了actuator的依赖，但是子项目无法访问/actuator/health。 一直404

原因是父项目的依赖添加错误。没有添加成starter...

原来：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-actuator</artifactId>
</dependency>
```



==正确的是：==

```xml
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```



## 7、hystrixDashboard无法正常监控其他应用

![image-20220622222254036](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220622222254.png)

接口是正常的

![image-20220622222323755](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220622222323.png)



解决方法：

在90001dashboard中添加配置：

```yml
hystrix:
  dashboard:
    proxy-stream-allow-list: "*"
```

重启即可。





## 8、java.lang.IllegalArgumentException: Source must not be null

原来的代码是：

```java
@Override
public PaymentDTO getPaymentById(Long id) {
    PaymentPO paymentPO = baseMapper.selectById(id);
    Assert.notNull(paymentPO, "Could not find related info.");
    BeanUtils.copyProperties(paymentPO, paymentDTO);
    return paymentDTO;
}
```

报错原因是paymentPO为null。导致BeanUtils.copyProperties出错。添加判断即可

```java
Assert.notNull(paymentPO, "Could not find related info.");
```



## 9、Nacos集群启动三个节点，过一会发现只剩两个了。

```shell
ps -ef|grep nacos|grep -v grep|wc -l
```

 因为nacos比较占用内存，我启动一个差不多1.5G占用，我的CentOS给了2G内存，所以不够用。

解决：

（1）分配大内存

![image-20220707221630564](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220707221637.png)

(2) 调小nacos启动内存参数

![image-20220708093528433](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220708093535.png)

（3）还不行就复制三份，修改application.properties的server.port, 分别设置为3333，4444和5555



## 10、nacos集群结合nginx启动后无法成功代理

报错信息：

![image-20220707222104505](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220707222104.png)

原因：nginx配置错误: 名字加了下划线: nacos_cluster

处理：

![image-20220708093812831](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220708093812.png)

修改后重启即可。

## 11、Cloud项目中Nacos2.0.3的nacosClient通过nginx暴露的端口注册nacos集群启动报错。

```shell
 .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::       (v2.3.12.RELEASE)

2022-07-08 09:31:18.555  INFO 2880 --- [  restartedMain] c.i.s.NacosProvider9002Starter           : No active profile set, falling back to default profiles: default
2022-07-08 09:31:19.223  WARN 2880 --- [  restartedMain] o.s.boot.actuate.endpoint.EndpointId     : Endpoint ID 'service-registry' contains invalid characters, please migrate to a valid format.
2022-07-08 09:31:19.293  INFO 2880 --- [  restartedMain] o.s.cloud.context.scope.GenericScope     : BeanFactory id=d9a0d0be-2f15-378c-a47d-5762f76622d9
2022-07-08 09:31:19.612  INFO 2880 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 9002 (http)
2022-07-08 09:31:19.619  INFO 2880 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-07-08 09:31:19.619  INFO 2880 --- [  restartedMain] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.46]
2022-07-08 09:31:19.698  INFO 2880 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-07-08 09:31:19.698  INFO 2880 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1129 ms
2022-07-08 09:31:20.509  INFO 2880 --- [  restartedMain] c.a.n.p.a.s.c.ClientAuthPluginManager    : [ClientAuthPluginManager] Load ClientAuthService com.alibaba.nacos.client.auth.impl.NacosClientAuthServiceImpl success.
2022-07-08 09:31:20.510  INFO 2880 --- [  restartedMain] c.a.n.p.a.s.c.ClientAuthPluginManager    : [ClientAuthPluginManager] Load ClientAuthService com.alibaba.nacos.client.auth.ram.RamClientAuthServiceImpl success.
2022-07-08 09:31:28.185  INFO 2880 --- [  restartedMain] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 18 endpoint(s) beneath base path '/actuator'
2022-07-08 09:31:28.222  INFO 2880 --- [  restartedMain] pertySourcedRequestMappingHandlerMapping : Mapped URL path [/v2/api-docs] onto method [springfox.documentation.swagger2.web.Swagger2ControllerWebMvc#getDocumentation(String, HttpServletRequest)]
2022-07-08 09:31:28.232  WARN 2880 --- [  restartedMain] c.n.c.sources.URLConfigurationSource     : No URLs will be polled as dynamic configuration sources.
2022-07-08 09:31:28.232  INFO 2880 --- [  restartedMain] c.n.c.sources.URLConfigurationSource     : To enable URLs as dynamic configuration sources, define System property archaius.configurationSource.additionalUrls or make config.properties available on classpath.
2022-07-08 09:31:28.234  WARN 2880 --- [  restartedMain] c.n.c.sources.URLConfigurationSource     : No URLs will be polled as dynamic configuration sources.
2022-07-08 09:31:28.234  INFO 2880 --- [  restartedMain] c.n.c.sources.URLConfigurationSource     : To enable URLs as dynamic configuration sources, define System property archaius.configurationSource.additionalUrls or make config.properties available on classpath.
2022-07-08 09:31:28.285  INFO 2880 --- [  restartedMain] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2022-07-08 09:31:28.305  INFO 2880 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2022-07-08 09:31:28.347  INFO 2880 --- [  restartedMain] o.s.s.c.ThreadPoolTaskScheduler          : Initializing ExecutorService 'Nacos-Watch-Task-Scheduler'
2022-07-08 09:31:29.265 ERROR 2880 --- [  restartedMain] c.a.cloud.nacos.discovery.NacosWatch     : namingService subscribe failed, properties:NacosDiscoveryProperties{serverAddr='192.168.137.110:1111', endpoint='', namespace='', watchDelay=30000, logName='', service='nacos-payment-provider', weight=1.0, clusterName='DEFAULT', group='DEFAULT_GROUP', namingLoadCacheAtStart='false', metadata={preserved.register.source=SPRING_CLOUD}, registerEnabled=true, ip='192.168.1.3', networkInterface='', port=-1, secure=false, accessKey='', secretKey='', heartBeatInterval=null, heartBeatTimeout=null, ipDeleteTimeout=null, failFast=true}

com.alibaba.nacos.api.exception.NacosException: Request nacos server failed: 
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.requestToServer(NamingGrpcClientProxy.java:288) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.doSubscribe(NamingGrpcClientProxy.java:229) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.subscribe(NamingGrpcClientProxy.java:214) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.NamingClientProxyDelegate.subscribe(NamingClientProxyDelegate.java:147) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.NacosNamingService.subscribe(NacosNamingService.java:393) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.cloud.nacos.discovery.NacosWatch.start(NacosWatch.java:134) ~[spring-cloud-starter-alibaba-nacos-discovery-2.2.7.RELEASE.jar:2.2.7.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor.doStart(DefaultLifecycleProcessor.java:182) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor.access$200(DefaultLifecycleProcessor.java:53) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor$LifecycleGroup.start(DefaultLifecycleProcessor.java:360) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor.startBeans(DefaultLifecycleProcessor.java:158) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor.onRefresh(DefaultLifecycleProcessor.java:122) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:895) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:554) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:143) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:755) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:747) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:402) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:312) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1247) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1236) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at com.ityj.springcloud.NacosProvider9002Starter.main(NacosProvider9002Starter.java:11) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49) ~[spring-boot-devtools-2.3.12.RELEASE.jar:2.3.12.RELEASE]
Caused by: com.alibaba.nacos.api.exception.NacosException: Client not connected, current status:STARTING
	at com.alibaba.nacos.common.remote.client.RpcClient.request(RpcClient.java:651) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.common.remote.client.RpcClient.request(RpcClient.java:631) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.requestToServer(NamingGrpcClientProxy.java:278) ~[nacos-client-2.1.0.jar:na]
	... 25 common frames omitted

2022-07-08 09:31:29.282  INFO 2880 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9002 (http) with context path ''
2022-07-08 09:31:29.589 ERROR 2880 --- [  restartedMain] c.a.c.n.registry.NacosServiceRegistry    : nacos registry, nacos-payment-provider register failed...NacosRegistration{nacosDiscoveryProperties=NacosDiscoveryProperties{serverAddr='192.168.137.110:1111', endpoint='', namespace='', watchDelay=30000, logName='', service='nacos-payment-provider', weight=1.0, clusterName='DEFAULT', group='DEFAULT_GROUP', namingLoadCacheAtStart='false', metadata={preserved.register.source=SPRING_CLOUD}, registerEnabled=true, ip='192.168.1.3', networkInterface='', port=9002, secure=false, accessKey='', secretKey='', heartBeatInterval=null, heartBeatTimeout=null, ipDeleteTimeout=null, failFast=true}},

com.alibaba.nacos.api.exception.NacosException: Request nacos server failed: 
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.requestToServer(NamingGrpcClientProxy.java:288) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.doRegisterService(NamingGrpcClientProxy.java:128) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.registerService(NamingGrpcClientProxy.java:114) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.NamingClientProxyDelegate.registerService(NamingClientProxyDelegate.java:94) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.NacosNamingService.registerInstance(NacosNamingService.java:145) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.cloud.nacos.registry.NacosServiceRegistry.register(NacosServiceRegistry.java:74) ~[spring-cloud-starter-alibaba-nacos-discovery-2.2.7.RELEASE.jar:2.2.7.RELEASE]
	at org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration.register(AbstractAutoServiceRegistration.java:239) ~[spring-cloud-commons-2.2.9.RELEASE.jar:2.2.9.RELEASE]
	at com.alibaba.cloud.nacos.registry.NacosAutoServiceRegistration.register(NacosAutoServiceRegistration.java:78) ~[spring-cloud-starter-alibaba-nacos-discovery-2.2.7.RELEASE.jar:2.2.7.RELEASE]
	at org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration.start(AbstractAutoServiceRegistration.java:138) ~[spring-cloud-commons-2.2.9.RELEASE.jar:2.2.9.RELEASE]
	at org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration.bind(AbstractAutoServiceRegistration.java:101) ~[spring-cloud-commons-2.2.9.RELEASE.jar:2.2.9.RELEASE]
	at org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration.onApplicationEvent(AbstractAutoServiceRegistration.java:88) ~[spring-cloud-commons-2.2.9.RELEASE.jar:2.2.9.RELEASE]
	at org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration.onApplicationEvent(AbstractAutoServiceRegistration.java:47) ~[spring-cloud-commons-2.2.9.RELEASE.jar:2.2.9.RELEASE]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:172) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:165) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:139) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:404) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:361) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.boot.web.servlet.context.WebServerStartStopLifecycle.start(WebServerStartStopLifecycle.java:46) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor.doStart(DefaultLifecycleProcessor.java:182) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor.access$200(DefaultLifecycleProcessor.java:53) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor$LifecycleGroup.start(DefaultLifecycleProcessor.java:360) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor.startBeans(DefaultLifecycleProcessor.java:158) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor.onRefresh(DefaultLifecycleProcessor.java:122) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:895) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:554) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:143) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:755) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:747) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:402) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:312) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1247) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1236) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at com.ityj.springcloud.NacosProvider9002Starter.main(NacosProvider9002Starter.java:11) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49) ~[spring-boot-devtools-2.3.12.RELEASE.jar:2.3.12.RELEASE]
Caused by: com.alibaba.nacos.api.exception.NacosException: Client not connected, current status:STARTING
	at com.alibaba.nacos.common.remote.client.RpcClient.request(RpcClient.java:651) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.common.remote.client.RpcClient.request(RpcClient.java:631) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.requestToServer(NamingGrpcClientProxy.java:278) ~[nacos-client-2.1.0.jar:na]
	... 37 common frames omitted

2022-07-08 09:31:29.589  WARN 2880 --- [  restartedMain] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.context.ApplicationContextException: Failed to start bean 'webServerStartStop'; nested exception is java.lang.reflect.UndeclaredThrowableException
2022-07-08 09:31:29.590  INFO 2880 --- [  restartedMain] o.s.s.c.ThreadPoolTaskScheduler          : Shutting down ExecutorService 'Nacos-Watch-Task-Scheduler'
2022-07-08 09:31:29.893 ERROR 2880 --- [  restartedMain] c.a.cloud.nacos.discovery.NacosWatch     : namingService unsubscribe failed, properties:NacosDiscoveryProperties{serverAddr='192.168.137.110:1111', endpoint='', namespace='', watchDelay=30000, logName='', service='nacos-payment-provider', weight=1.0, clusterName='DEFAULT', group='DEFAULT_GROUP', namingLoadCacheAtStart='false', metadata={preserved.register.source=SPRING_CLOUD}, registerEnabled=true, ip='192.168.1.3', networkInterface='', port=9002, secure=false, accessKey='', secretKey='', heartBeatInterval=null, heartBeatTimeout=null, ipDeleteTimeout=null, failFast=true}

com.alibaba.nacos.api.exception.NacosException: Request nacos server failed: 
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.requestToServer(NamingGrpcClientProxy.java:288) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.doUnsubscribe(NamingGrpcClientProxy.java:259) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.unsubscribe(NamingGrpcClientProxy.java:240) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.NamingClientProxyDelegate.unsubscribe(NamingClientProxyDelegate.java:157) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.NacosNamingService.unsubscribe(NacosNamingService.java:417) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.cloud.nacos.discovery.NacosWatch.stop(NacosWatch.java:177) ~[spring-cloud-starter-alibaba-nacos-discovery-2.2.7.RELEASE.jar:2.2.7.RELEASE]
	at com.alibaba.cloud.nacos.discovery.NacosWatch.destroy(NacosWatch.java:207) ~[spring-cloud-starter-alibaba-nacos-discovery-2.2.7.RELEASE.jar:2.2.7.RELEASE]
	at org.springframework.beans.factory.support.DisposableBeanAdapter.destroy(DisposableBeanAdapter.java:199) ~[spring-beans-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.destroyBean(DefaultSingletonBeanRegistry.java:587) ~[spring-beans-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.destroySingleton(DefaultSingletonBeanRegistry.java:559) ~[spring-beans-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.destroySingleton(DefaultListableBeanFactory.java:1092) ~[spring-beans-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.destroySingletons(DefaultSingletonBeanRegistry.java:520) ~[spring-beans-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.destroySingletons(DefaultListableBeanFactory.java:1085) ~[spring-beans-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.destroyBeans(AbstractApplicationContext.java:1061) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:564) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:143) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:755) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:747) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:402) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:312) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1247) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1236) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at com.ityj.springcloud.NacosProvider9002Starter.main(NacosProvider9002Starter.java:11) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49) ~[spring-boot-devtools-2.3.12.RELEASE.jar:2.3.12.RELEASE]
Caused by: com.alibaba.nacos.api.exception.NacosException: Client not connected, current status:STARTING
	at com.alibaba.nacos.common.remote.client.RpcClient.request(RpcClient.java:651) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.common.remote.client.RpcClient.request(RpcClient.java:631) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.requestToServer(NamingGrpcClientProxy.java:278) ~[nacos-client-2.1.0.jar:na]
	... 27 common frames omitted

2022-07-08 09:31:29.894  INFO 2880 --- [  restartedMain] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
2022-07-08 09:31:30.639  INFO 2880 --- [  restartedMain] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2022-07-08 09:31:30.655  INFO 2880 --- [  restartedMain] ConditionEvaluationReportLoggingListener : 

Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2022-07-08 09:31:30.668 ERROR 2880 --- [  restartedMain] o.s.boot.SpringApplication               : Application run failed

org.springframework.context.ApplicationContextException: Failed to start bean 'webServerStartStop'; nested exception is java.lang.reflect.UndeclaredThrowableException
	at org.springframework.context.support.DefaultLifecycleProcessor.doStart(DefaultLifecycleProcessor.java:185) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor.access$200(DefaultLifecycleProcessor.java:53) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor$LifecycleGroup.start(DefaultLifecycleProcessor.java:360) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor.startBeans(DefaultLifecycleProcessor.java:158) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor.onRefresh(DefaultLifecycleProcessor.java:122) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.finishRefresh(AbstractApplicationContext.java:895) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:554) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:143) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:755) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:747) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:402) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:312) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1247) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1236) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at com.ityj.springcloud.NacosProvider9002Starter.main(NacosProvider9002Starter.java:11) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49) ~[spring-boot-devtools-2.3.12.RELEASE.jar:2.3.12.RELEASE]
Caused by: java.lang.reflect.UndeclaredThrowableException: null
	at org.springframework.util.ReflectionUtils.rethrowRuntimeException(ReflectionUtils.java:147) ~[spring-core-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at com.alibaba.cloud.nacos.registry.NacosServiceRegistry.register(NacosServiceRegistry.java:82) ~[spring-cloud-starter-alibaba-nacos-discovery-2.2.7.RELEASE.jar:2.2.7.RELEASE]
	at org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration.register(AbstractAutoServiceRegistration.java:239) ~[spring-cloud-commons-2.2.9.RELEASE.jar:2.2.9.RELEASE]
	at com.alibaba.cloud.nacos.registry.NacosAutoServiceRegistration.register(NacosAutoServiceRegistration.java:78) ~[spring-cloud-starter-alibaba-nacos-discovery-2.2.7.RELEASE.jar:2.2.7.RELEASE]
	at org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration.start(AbstractAutoServiceRegistration.java:138) ~[spring-cloud-commons-2.2.9.RELEASE.jar:2.2.9.RELEASE]
	at org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration.bind(AbstractAutoServiceRegistration.java:101) ~[spring-cloud-commons-2.2.9.RELEASE.jar:2.2.9.RELEASE]
	at org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration.onApplicationEvent(AbstractAutoServiceRegistration.java:88) ~[spring-cloud-commons-2.2.9.RELEASE.jar:2.2.9.RELEASE]
	at org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration.onApplicationEvent(AbstractAutoServiceRegistration.java:47) ~[spring-cloud-commons-2.2.9.RELEASE.jar:2.2.9.RELEASE]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:172) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:165) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:139) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:404) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.publishEvent(AbstractApplicationContext.java:361) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	at org.springframework.boot.web.servlet.context.WebServerStartStopLifecycle.start(WebServerStartStopLifecycle.java:46) ~[spring-boot-2.3.12.RELEASE.jar:2.3.12.RELEASE]
	at org.springframework.context.support.DefaultLifecycleProcessor.doStart(DefaultLifecycleProcessor.java:182) ~[spring-context-5.2.15.RELEASE.jar:5.2.15.RELEASE]
	... 19 common frames omitted
Caused by: com.alibaba.nacos.api.exception.NacosException: Request nacos server failed: 
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.requestToServer(NamingGrpcClientProxy.java:288) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.doRegisterService(NamingGrpcClientProxy.java:128) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.registerService(NamingGrpcClientProxy.java:114) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.NamingClientProxyDelegate.registerService(NamingClientProxyDelegate.java:94) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.NacosNamingService.registerInstance(NacosNamingService.java:145) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.cloud.nacos.registry.NacosServiceRegistry.register(NacosServiceRegistry.java:74) ~[spring-cloud-starter-alibaba-nacos-discovery-2.2.7.RELEASE.jar:2.2.7.RELEASE]
	... 32 common frames omitted
Caused by: com.alibaba.nacos.api.exception.NacosException: Client not connected, current status:STARTING
	at com.alibaba.nacos.common.remote.client.RpcClient.request(RpcClient.java:651) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.common.remote.client.RpcClient.request(RpcClient.java:631) ~[nacos-client-2.1.0.jar:na]
	at com.alibaba.nacos.client.naming.remote.gprc.NamingGrpcClientProxy.requestToServer(NamingGrpcClientProxy.java:278) ~[nacos-client-2.1.0.jar:na]
	... 37 common frames omitted

2022-07-08 09:31:30.671  WARN 2880 --- [      Thread-11] c.a.n.common.http.HttpClientBeanHolder   : [HttpClientBeanHolder] Start destroying common HttpClient
2022-07-08 09:31:30.671  WARN 2880 --- [       Thread-8] c.a.nacos.common.notify.NotifyCenter     : [NotifyCenter] Start destroying Publisher
2022-07-08 09:31:30.671  WARN 2880 --- [       Thread-8] c.a.nacos.common.notify.NotifyCenter     : [NotifyCenter] Destruction of the end
2022-07-08 09:31:30.672  WARN 2880 --- [      Thread-11] c.a.n.common.http.HttpClientBeanHolder   : [HttpClientBeanHolder] Destruction of the end
```

![image-20220708094225992](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220708094226.png)

根据官网的意思2.x后需要暴露两个端口，但是我Linux防火墙已经关掉了，并且页面可以正常访问，很奇怪。还没有完美的解决方案。

`https://nacos.io/zh-cn/docs/2.0.0-compatibility.html`

处理方法：

### 1. 降低nacos.client版本

![image-20220708094528862](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731193228.png)



### 2.client还是用2xx版本，不适用nginx代理，直接把nacos集群的IP用逗号隔开

> 暂时用这种吧

![image-20220708094759415](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731193108.png)



### 12、Sentinel Dashboard 无法监控配置好的服务

最终发现是pom文件有问题，坐标写错了。IDEA也没有标红

错误：

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>sentinel-datasource-nacos</artifactId>
</dependency>
```

正确：

```xml
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-datasource-nacos</artifactId>
</dependency>
```

