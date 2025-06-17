1、在实体类编写时最好都用包装类。

出现Field error in object 'payment' on field 'id': rejected value []; codes [typeMismatch.payment.id,typeMismatch.id,typeMismatch.long,typeMismatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [payment.id,id]; arguments []; default message [id]]; default message [Failed to convert property value of type 'java.lang.String' to required type 'long' for property 'id'; nested exception is java.lang.NumberFormatException: For input string: ""]

在ssm框架中，前端没有没有传值或者搜索条件为空时，传值时无法转换为int，会导致加载controller之前就报错，故应把属性值得类型改为Integer，即可接收到null。



2、由于缺少@RequestBody注解导致服务端传入订单新增时，数据为null。

```java
public CommonResult<Payment> create(Payment payment) {}
```

![image-20210219180802222](D:\我的文件\Typora\文档\17、bug小结.assets\image-20210219180802222.png)

```java
// 在生产者的controller上添加@RequestBody后恢复正常
// 如果消费者也是post请求，会出错。：：：：客户端发送的都是get请求，所以只需要把生产者服务端的@RequestBody加上就行了
public CommonResult<Payment> create(@RequestBody Payment payment) {}
```

