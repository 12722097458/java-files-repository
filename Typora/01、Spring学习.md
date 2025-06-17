> Spring学习之基本概念、IOC、AOP、事务
>
>  https://www.bilibili.com/video/BV1Vf4y127N5?p=1

### 1、简介

#### 1、spring是什么？

是一个轻量级的开源的JavaEE框架，解决企业应用开发的繁琐性。为简化而生。

轻量级：使用过程中引用的jar包较少、无需依赖其他组件。

Spring两大核心IOC,AOP：

（1）IOC：控制反转，对象的创建过程交给Spring管理。

（2）AOP：面向切面，再不改变源代码的情况下进行功能的增强。

#### 2、Spring的特点

（1）方便解耦，简化开发

（2）Aop编程的支持

（3）方便测试

（4）方便集成其他框架：Mybatis

（5）方便进行事务的操作，降低API的开发难度

### 2、IOC容器

#### 1、IOC的底层原理

##### **什么是IOC？**

（1）控制反转，把对象的创建和对象之间的调用交给Spring管理

（2）使用IOC的目的：降低耦合

##### **IOC的底层原理：**

（1） XML解析、工厂模式、反射

（2）画图解析IOC原理：降低耦合

![image-20210307181415679](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165435569.png)

#### 2、IOC的一个接口（BeanFactory）

（1）IOC工厂基于IOC容器完成，IOC容器底层就是对象工厂。

（2）Spring提供IOC容器实现的两种方法：（两个接口）

1. FactoryBean：IOC容器的基本实现，是Spring框架内部使用的接口，不提供开发人员使用。

   > 加载配置文件时不会创建对象，只有第一次获取对象时才去创建。

2. ApplicationContext：BeanFatory接口的子接口，提供了更多更强大的功能，一般由开发人员进行使用

   > 加载配置文件时就会创建对象：比较好，对象的创建等消耗资源的操作就应该在项目启动的时候完成。

#### 3、IOC操作Bean管理（基于XML）

##### **1、什么是Bean管理？**

Bean管理是指以下两个操作：

* Spring创建对象
* Spring注入属性

##### **2、Bean管理操作有两种：**

###### **（1）set方式注入**

```java
package com.ityj.spring.entity;

public class User {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void sayHello() {
        System.out.println("hello...");
    }

}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--根据无参构造实例化对象，如果没有无参构造会报错-->
    <bean id="user01" class="com.ityj.spring.entity.User">
        <!--通过XML注入属性-->
        <property name="userName" value="Jack大表哥"/>
    </bean>
</beans>
```

```java
@Test
public void testBean() {
    // 1. 加载配置文件
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:bean1.xml");
    // 2. 获取配置创建的对象
    User user01 = applicationContext.getBean("user01", User.class);
    user01.sayHello();
    String userName = user01.getUserName();
    System.out.println("userName = " + userName);
}
```

![image-20210307185705378](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165441616.png)

###### **（2）也可以用有参构造方式注入：**

```java
package com.ityj.spring.entity;

public class User {

    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void sayHello() {
        System.out.println("hello...");
    }

}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--根据有参构造实例化对象-->
    <bean id="user01" class="com.ityj.spring.entity.User">
        <constructor-arg name="userName" value="Jackkkk"/>
    </bean>
</beans>
```

结果也OK。



##### **3、bean的作用域？**

分为：singleton、prototype、request、session

设置方法：可以在spring配置文件的bean标签里scope属性修改。

常用的是singleton单实例和prototype多实例，默认是单实例：

**区别如下：**

* singleton在加载spring配置文件时，就会创建对象
* prototype是在调用getBean()方法时创建此多实例对象



##### **4、bean的生命周期？**

```java
package com.ityj.spring.entity;

public class User {

    private String userName;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
        System.out.println("第二步：调用set方法设置属性值。。。");
    }

    public User() {
        System.out.println("第一步：通过无参构造创建bean实例。。。");
    }

    public void sayHello() {
        System.out.println("hello...");
    }

    public void initMethod() {
        System.out.println("第三步：调用初始化方法initMethod()。。。");
    }

    public void destoryMethod() {
        System.out.println("第五步：调用销毁方法 destoryMethod()。。。");
    }

}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--根据无参构造实例化对象，如果没有无参构造会报错-->
    <bean id="user" class="com.ityj.spring.entity.User" init-method="initMethod" destroy-method="destoryMethod">
        <!--通过XML注入属性-->
        <property name="userName" value="Jack大表哥"/>
    </bean>
</beans>
```

```java
@Test
public void testBeanLifeCycle() {
    // 1. 加载配置文件
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:beanLife.xml");
    // 2. 获取配置创建的对象
    User user = applicationContext.getBean("user", User.class);
    System.out.println("第四步：获取创建了bean实例对象。。。");

    // 3. 销毁方法，需要ClassPathXmlApplicationContext接收
    applicationContext.destroy();
}
```

![image-20210307195256436](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165446060.png)



如果配置了BeanPostProcessor，那么bean的声明周期将会新增两步：

```java
package com.ityj.spring.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean初始化之前执行postProcessBeforeInitialization()....");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean初始化之前执行 postProcessAfterInitialization()....");
        return bean;
    }
}
```



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--根据无参构造实例化对象，如果没有无参构造会报错-->
    <bean id="user" class="com.ityj.spring.entity.User" init-method="initMethod" destroy-method="destoryMethod">
        <!--通过XML注入属性-->
        <property name="userName" value="Jack大表哥"/>
    </bean>

    <bean id="myBeanPostProcessor" class="com.ityj.spring.entity.MyBeanPostProcessor"/>
</beans>
```

![image-20210307200455987](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165449661.png)

#### 4、IOC操作Bean管理（基于注解）



### 3、AOP

#### 1、什么是AOP？

（1）面向切面编程，利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑的各个部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

（2）通俗描述：不改变源代码的情况下，在主骨干里添加新的功能。

（3）登录的例子说明：添加新的权限判断模块，不需要修改源代码，也可以实现功能。

![image-20210307210546436](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165457251.png)

#### 2、AOP底层原理

有两种情况的动态代理：

##### 第一种：有接口情况，使用JDK动态代理

* 创建**接口**实现类的代理对象，增强类的方法。

![image-20210307211916070](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165500604.png)

```java
// 一个接口
public interface UserDao {

    int add(int num);
}

// 一个接口的实现类
public class UserDaoImpl implements UserDao {

    @Override
    public int add(int num) {
        System.out.println("UserDaoImpl.add()执行了。。。");
        return num;
    }
}

// 增强的方法
public class MyUserDaoInvocationHandler implements InvocationHandler {

    private Object object;

    public MyUserDaoInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("方法执行前。。。InvocationHandler对方法进行了加强，方法名称为：" + method.getName());

        Object result = method.invoke(object, args);

        System.out.println("方法执行后。。。InvocationHandler对方法进行了加强，方法名称为：" + method.getName());

        return result;
    }
}

// 创建接口实现类的代理对象instance
public class ProxyMain {
    public static void main(String[] args) {

        UserDao userDao = new UserDaoImpl();
        // 代理对象
        UserDao instance = (UserDao) Proxy.newProxyInstance(ProxyMain.class.getClassLoader(), userDao.getClass().getInterfaces(), new MyUserDaoInvocationHandler(userDao));
        int result = instance.add(10);

        System.out.println("result = " + result);
    }
}

```

![image-20210307214737812](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165508375.png)







##### 第二种：没有接口的情况，使用CGLIB的动态代理

* 创建**子类**的代理对象，增强类的方法。

![image-20210307211933473](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165525119.png)



#### 3、AOP的术语

* 连接点：可以被增强的方法
* 切入点：实际被增强的方法
* 通知（增强）：实际增强的逻辑部分（比如新增的权限判断）
* 切面：是一个动作，把通知应用到切入点的过程

![image-20210307215746488](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165312701.png)

切入点的表达式：

>（1）切入点表达式作用：知道对哪个类里面的哪个方法进行增强 
>（2）语法结构： execution([权限修饰符] [返回类型] [类全路径] [方法名称]([参数列表]) )
>
>​	返回类型可以省略
>
>（3）例子如下：
>    例 1：对 com.atguigu.dao.BookDao 类里面的 add 进行增强
>		execution(* com.atguigu.dao.BookDao.add(..))
> 	例 2：对 com.atguigu.dao.BookDao 类里面的所有的方法进行增强
>		execution(* com.atguigu.dao.BookDao.* (..))
>    例 3：对 com.atguigu.dao 包里面所有类，类里面所有方法进行增强
>		execution(* com.atguigu.dao.*.* (..))

#### 4、AOP操作（准备）

1、Spring的框架一般都是基于AspectJ实现AOP操作的

（1）什么是AspectJ

* AspectJ不是Spring的组成部分，独立AOP框架，一般把AspectJ和Spring框架一起使用，进行AOP操作

2、基于AOP实现AOP操作

（1）基于xml配置文件实现

**（2）基于注解方式实现（常用）**



###### 编码实现：

（1）被代理类User

```java
@Component
public class User {
    public void add() {
        System.out.println("User.add()...");
    }
}
```

（2）代理类UserProxy：编写对应的通知（增强）方法。

```java
package com.ityj.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserProxy {

    @Before(value = "execution(* com.ityj.spring.aop.User.add(..))")
    public void before() {
        System.out.println("before...前置通知");
    }

    @After(value = "execution(* com.ityj.spring.aop.User.add(..))")
    public void after() {
        System.out.println("after...后置通知");
    }

    @AfterReturning(value = "execution(* com.ityj.spring.aop.User.add(..))")
    public void afterReturning() {
        System.out.println("afterReturning...最终通知");
    }
    @AfterThrowing(value = "execution(* com.ityj.spring.aop.User.add(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing...异常通知");
    }

    @Around(value = "execution(* com.ityj.spring.aop.User.add(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around...环绕通知start");
        joinPoint.proceed();
        System.out.println("around...环绕通知end");
    }

}
```

（3）xml配置文件开启扫描以及支持aop

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.ityj.spring.aop"/>

    <aop:aspectj-autoproxy/>

</beans>
```

（4）测试：

```java
@Test
public void testAop() {
    // 1. 加载配置文件
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:aop.xml");
    // 2. 获取配置创建的对象
    User user = applicationContext.getBean("user", User.class);
    user.add();
}
```

结论：

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-aspects</artifactId>
  <version>5.2.6.RELEASE</version>
</dependency>
```



![image-20210307230235925](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165320483.png)



5.2.8版本已经对最终通知和环绕通知最后执行的顺序进行了调整。

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-aspects</artifactId>
  <version>5.2.8.RELEASE</version>
</dependency>
```

![image-20210307230342085](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165333276.png)

![image-20210307231452767](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165342788.png)



### 4、事务

#### 1、什么是事务？

事务是数据库操作的最基本单元，逻辑上一组操作，要么都成功，如果有一个失败所有操作都失败。

#### 2、事务的特性(ACID)：

原子性：不可分割，要么全成功，要么全失败

一致性：操作前后数据总量不会发生变化

隔离性：多个事务之间不会相互影响

持久性：数据会永久保留

#### 3、在Spring中进行事务管理的操作

分两种方式：

（1）编程式事务：

（2）声明式事务：

		* 基于注解的方式（常用）
		* 基于xml配置文件的方式

#### 4、在Spring中的事务管理，底层用的是AOP



#### 5、@Transactional注解里的属性

##### 1、propagation：事务传播行为

默认REQUIRED

多**事务方法**之间直接进行调用，这个过程中事务是怎样进行调用的，就称为传播行为。

事务方法：对数据库表进行数据修改的操作。

![image-20210308083044653](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165349495.png)

![image-20210308082802756](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165356913.png)



##### 2、ioslation：事务隔离级别

（1）多个事务间的规则称为隔离性，多事务操作之间不会产生影响，如果不考虑隔离性将会产生很多问题。

（2）三个读的问题：脏读、不可重复读、虚读

​	1. 脏读：一个未提交事务的操作读取到了另一个未提交事务的操作

![image-20210308084119897](C:/Users/ayinj/AppData/Roaming/Typora/typora-user-images/image-20210308084119897.png)

首先岳不群把500修改成60000，但是还没有提交事务；

此时东方不败读取的是60000数据并开始进行操作；

此时岳不群把数据回滚到了5000，这就出现了很大的问题。脏读



2. 不可重复读：一个未提交的事务读取到另一个事务中**修改**的数据。

![image-20210308085202634](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165405592.png)

AB两个事务，第一次读取到的数据都是5000；

B事务将数据改成100并立即提交；

此时A事务再次获取到的数据是修改后的100，即读取到另一个事务中的数据。不可重复读



3. 虚读：一个未提交的事务读取到另一个事务中**添加**的数据。



##### 3、设置事务的隔离性，就可以解决读的问题

![image-20210308085508806](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165412993.png)

mysql数据库默认的隔离级别是**Repeatable Read**

oracle数据库默认的隔离界别是**Read Commited**

![image-20210308090711558](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617165423246.png)