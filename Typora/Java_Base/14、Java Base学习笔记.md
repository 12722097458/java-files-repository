对应代码：
https://github.com/12722097458/java-base-learning-20250625

视频：

https://www.bilibili.com/video/BV1Kb411W75N?spm_id_from=333.788.videopod.episodes&vd_source=b23569b676ce26126febad3c290b16e8&p=152

# 一、计算机基本介绍

## 1、bit(比特)和byte(字节)

1个1或者1个0存储为一个比特(bit)，bit是计算机的最小存储单位

byte是计算机中最基本的存储单元，每个字节由8个bit构成



## 2、CPU、内存和硬盘

![image-20250626132744552](https://gitee.com/yj1109/cloud-image/raw/master/img/20250626132744660.png)

CPU运行速度很快，而硬盘读取速度太慢。内存一定程度上解决了这个问题。

内存是带电存储的，一旦断电数据就丢失。硬盘可以长时间存储。



## 3、计算机编程语言

![image-20250626164101186](https://gitee.com/yj1109/cloud-image/raw/master/img/20250626164101270.png)

![image-20250626164202305](https://gitee.com/yj1109/cloud-image/raw/master/img/20250626164202354.png)

# 二、Java语言概述



## 1、Java语言的特点

![image-20250626165626053](https://gitee.com/yj1109/cloud-image/raw/master/img/20250626165626103.png)

![image-20250626165758303](https://gitee.com/yj1109/cloud-image/raw/master/img/20250626165758357.png)



封装：

> Java提供了4种权限修饰符来修饰类以及类的内部结构，体现类以及类的内部结构在被调用时的可见性大小

![image-20250701143224020](https://gitee.com/yj1109/cloud-image/raw/master/img/20250701143224081.png)



继承：

A extends B{}

A： 子类，派生类, subclass

B： 父类，基类、超类，superclass

> 1. 一旦子类A继承了父类B， 子类就拥有了父类B中声明的结构：属性，方法
>
> 特别的：
>
> ​	父类中声明的private属性或方法，子类继承之后仍然认为子类获取了父类的私有结构。只是因为封装性的影响使得子类不能直接调用父类的结构而已。
>
> 2. 子类继承父类后，子类可以定义自己特有的属性和方法。



特点： 1. 一个类可以被多个子类继承 2.java类单层继承。  3. 子父类是相对的 4. 直接父类和间接父类 5. 子类拥有父类以及间接父类的所有声明的属性和方法



多态性:

> 一个事物的多种形态。 向上转型
>
> Person p = new Man();  // 子类对象的多态性 --》 父类的引用指向子类的对象

![image-20250701165159999](https://gitee.com/yj1109/cloud-image/raw/master/img/20250701165200131.png)

## 2、Java的基本运算

```java
float f = 123.4F;
float f2 = (float) 123.4; // 浮点型常熟默认类型是double,所以需要加F。或强转(float) 123.4
short s = 123;  // 字面量123的默认类型是int， 对于整数，如果该范围在目标范围内，会自动执行隐式类型转换。所以没有编译报错

char c = 'a';
byte b = 3;
int s2 = s + s;
int s3 = s + b;
int s4 = c + s;  // byte/short/char 之间的运算都要用int接收

s+=3;
s++;   // s+=  s++ 都不会改变本身的数据类型

int a2 = 0B0011;  // 二进制binary   0B或0开头    3
int a10 = 123;  // 十进制 decimal               123
int a8 = 027;  // 八进制octal  0开头              23
int a16 = 0x11; // 十六进制 hex  0x或0X开头       17

int res1 = 12 % 5;     // 2 结果的符号与被模数一致
int res2 = 12 % -5;    // 2
int res3 = -12 % 5;    // -2
int res4 = -12 % -5;   // -2
```



## 3、二进制的负数(补码)

![image-20250627152423551](https://gitee.com/yj1109/cloud-image/raw/master/img/20250627152423803.png)

## 4、Java流程控制

### 1.1 分支结构

if else

switch()

byte/short/int/char/enum/String



### 1.2 循环结构

for

while

do-while

先执行一次do代码块的逻辑，至少会执行一次循环体

```java
do {
    
} while ();
```





break label:

```java
label2: for (int i = 0; i < 100; i++) {
    System.out.println("i = " + i);
    for (int j = 0; j < 20; j++) {
        if (i == 18) {
            break label2;  // 跳出所有循环
        }
    }
}
```



## 5、数组

### 1.1 一维数组

初始化

```java
int[] arr1 = {1, 2, 3};

int[] arr2 = new int[] {1, 2, 3};

int[] arr3 = new int[3];
arr3[0] = 1;
arr3[1] = 2;
arr3[2] = 3;
```

一维数组内存解析

![image-20250629215053304](https://gitee.com/yj1109/cloud-image/raw/master/img/20250629215053368.png)

### 1.2 二维数组

初始化

```java
int[][] ar1 = {{1,2,3},{1,2,3},{1,2,3}};

int[][] ar2 = new int[][]{{1,2,3},{1,2,3},{1,2,3}};

int[][] ar3 = new int[3][2];  // 底层是一个长度为3的一维数组。 每个一维数组又由一个长度为2的一维数组组成
ar3[0][0] = 11;
ar3[0][1] = 12;
ar3[1][0] = 21;
ar3[1][1] = 22;
ar3[2][0] = 31;
ar3[2][1] = 32;

int[][] ar4 = new int[3][];   // 此时二维数组只知道长度为3， 具体值没有初始化。 直接取值会出现NPE
//int i = ar4[1][1]; // NPE
// 初始化后开辟了空间，才能取值。
ar4[1] = new int[4];
int j = ar4[1][1];   // 0
System.out.println("j = " + j);
```



二维数组内存解析

![image-20250629214954251](https://gitee.com/yj1109/cloud-image/raw/master/img/20250629214954420.png)



### 1.3 快速排序

![image-20250701120749167](https://gitee.com/yj1109/cloud-image/raw/master/img/20250701120749299.png)

![image-20250701120727807](https://gitee.com/yj1109/cloud-image/raw/master/img/20250701120727995.png)

## 6、面向对象

### 1.1 内存分析

 ![image-20250630212402195](https://gitee.com/yj1109/cloud-image/raw/master/img/20250630212402287.png)

![image-20250701120626735](https://gitee.com/yj1109/cloud-image/raw/master/img/20250701120626903.png)



### 1.2 权限修饰符

![image-20250701143142887](https://gitee.com/yj1109/cloud-image/raw/master/img/20250701143142958.png)

![image-20250701162145253](https://gitee.com/yj1109/cloud-image/raw/master/img/20250701162955435.png)



### 1.3 基本数据类型的自动装箱与拆箱

```java
// 装箱
Integer in = new Integer(1);
Integer in2 = Integer.valueOf(1);
// 自动装箱
Integer in3 = Integer.parseInt("1");


//自动拆箱
int a = in;
int i = in.intValue();
```



#### 面试题

##### 1.  IntegerCache默认会存储-128  ~ 127

```java
Integer i1 = new Integer(123);
Integer i2 = new Integer(123);
System.out.println(i1 == i2);// false  --  new 的两个不同的对象

Integer i3 = 222;
Integer i4 = 222;
System.out.println(i3 == i4);  // false

Integer i5 = 11;
Integer i6 = 11;
System.out.println(i5 == i6);  // true IntegerCache默认会存储-128 - 127的数。 如果用自动装箱的方式给Integer赋值这个范围内的数。会直接从cache里拿。
```



##### 2. 三元表达式会默认将两边的数值类型一致化

```java
boolean flag = (int) (Math.random()  * 100) % 2 == 1;
Object o1 = flag ? new Integer(1) : new Double(2.2D);
System.out.println("o1 = " + o1);  //返回是一个double类型
```

编译后的代码

![image-20250702121542389](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702121542472.png)

### 1.4 == 和equals()

![ddcddbb0fc83fa3e7a9ff7198f3449f](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702121713129.jpg)



### 1.5 static关键字

#### 1. 静态修饰变量

##### 1.1 静态变量(类变量)

![image-20250702123650311](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702123650402.png)

##### 1.2 static内存解析

![image-20250702123526980](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702123527070.png)



#### 2. static修饰方法

![image-20250702124128479](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702124128560.png)

![image-20250702124412266](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702124412342.png)

 

#### 3. static应用 - 单例设计模式(饿汉式/懒汉式)

#### 4. 静态代码块 vs 非静态代码块

![image-20250702142359648](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702142359845.png)

### 1.6 类的成员之四-代码块 (属性，方法，构造器)

![image-20250702133712600](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702133712704.png)



执行顺序：

有父类，先父类。 由父及子，静态先行

静态代码块，普通代码块， 构造方法

![image-20250702134303923](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702134304136.png)





### 1.7 final 关键字

static final可以修饰属性- 全局常量

也可以修饰方法

![image-20250702140157528](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702140157717.png)

```java

public class A007_FinalTest {
    
    // 成员变量被final修饰， 可直接赋值，可代码块赋值， 可构造方法赋值
    final int A = 1;
    final int B;
    final int C;
    {
        B = 2;
    }

    public A007_FinalTest (int n) {
        C = n;
    }
    
    // 局部变量+final 赋值后不能改变
    public static void main(String[] args) {
        final int num = 3;
        // num = 12; 局部变量一旦赋值不能修改

        final int num2;
        num2 = 3;
        // num2 = 4; 不能修改
        Order order = new Order();
        A007_FinalTest test = new A007_FinalTest(3);
        test.method(order);
        System.out.println("order.id = " + order.id);  // 123
    }
    
    // 形参被final修饰，不能改变
    private void method(final int num) {
        //num = 3;
    }

    // order 不可变，但里面的属性可变
    private void method(final Order order) {
        order.id = 123;
        // order = new Order(); Cannot assign a value to final variable 'order'
    }

}

class Order {
    public int id;
}

```



### 1.8 abstract

![image-20250702144705745](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702144705878.png)



####  模板方法设计模式 - 用到abstract

![image-20250702145155393](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702145155490.png)

![image-20250702145330176](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702145530861.png)

### 1.9 interface

![image-20250702151907516](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702151907688.png)

![image-20250702152341762](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702152341905.png)

类可以实现多个接口------C extends M implements A, B {}



接口可以继承多个接口。

```java
public interface MyInterface extends MyInterface2, MyInterface3 {

    // 常量
    public static final double PI = 3.14D;

    // 抽象方法，abstract可省略
    void invoke();
    abstract int getData();

    // 静态方法
    static void method() {
        System.out.println("MyInterface.static method...");
    }
    // 默认方法
    default void method2() {}

}
```



#### 应用 - 代理模式



静态代理

```java
package com.ityj.base;

// interface 的应用。  代理模式
public class A008_ProxyTest {
    public static void main(String[] args) {
        Server server = new Server();
        ProxyServer proxyServer = new ProxyServer(server);
        proxyServer.browse();
    }

}

// 同一个接口
interface NetWork {
    void browse();
}

// 被代理类
class Server implements NetWork {
    @Override
    public void browse() {
        System.out.println("Server 真实方法执行。。。");
    }
}

// 代理类
class ProxyServer implements NetWork {

    private NetWork netWork;

    public ProxyServer (NetWork netWork) {
        this.netWork = netWork;
    }

    private void check () {
        System.out.println("ProxyServer 做一些检查工作。。。");
    }

    @Override
    public void browse() {
        System.out.println("ProxyServer 代理方法开始执行。。。");
        check();
        netWork.browse();
    }
}
```



#### 应用- 工厂模式



### 1.10 类的成员之五-内部类

#### 1.1 成员内部类（静态和非静态）

![image-20250702162806615](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702162806724.png)

![image-20250702162844421](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702162844529.png)

内部类调用外部类

![image-20250702163055895](https://gitee.com/yj1109/cloud-image/raw/master/img/20250702163056011.png)





## 7. 异常

![aebdcb405b79c0b0d6fd62420ab45dc](https://gitee.com/yj1109/cloud-image/raw/master/img/20250703120200055.jpg)



面试题：

![1d715c96de631fdc295f04b6c34ae45](https://gitee.com/yj1109/cloud-image/raw/master/img/20250703120252649.jpg)





# 三、Java高级部分

## 1. 多线程

### 1.1 基本概念

![b4d125522557b1693dedb4655cadf13](https://gitee.com/yj1109/cloud-image/raw/master/img/20250703120321127.jpg)

![b5cec2cb3a9ab46d64fe0a6daf721ef](https://gitee.com/yj1109/cloud-image/raw/master/img/20250703120333813.jpg)

![a66ea9fe15e491bb707096a41779a0f](https://gitee.com/yj1109/cloud-image/raw/master/img/20250703120339619.jpg)

![5fdff5b83b421b5fd9b01d305df5274](https://gitee.com/yj1109/cloud-image/raw/master/img/20250703120349232.jpg)



![e9473921725a6b0357dffff9a93c416](https://gitee.com/yj1109/cloud-image/raw/master/img/20250703120313581.jpg)

![image-20250703162217463](https://gitee.com/yj1109/cloud-image/raw/master/img/20250703162217595.png)



### 1.2 线程的生命周期

![image-20250704103052360](https://gitee.com/yj1109/cloud-image/raw/master/img/20250704103052668.png)

### 1.3 解决线程安全

#### （1）同步代码块

![image-20250704105954174](https://gitee.com/yj1109/cloud-image/raw/master/img/20250704105954498.png)



#### （2）同步方法

![image-20250704111235744](https://gitee.com/yj1109/cloud-image/raw/master/img/20250704111236019.png)

#### （3）ReentrantLock

![image-20250704155914018](https://gitee.com/yj1109/cloud-image/raw/master/img/20250704155914221.png)

#### （4）应用

可以解决单例懒汉模式的线程安全问题

```java
class Lazy {

    private static volatile Lazy INSTANCE;
    private Lazy() {};

    // 效率差。。 其实只有第一次会出现现成问题
    public synchronized static Lazy getInstance2() {// 锁的是Lazy.class
        if (INSTANCE == null) {
            INSTANCE = new Lazy();
        }
        return INSTANCE;
    }
    public static Lazy getInstance2_2() {
        synchronized (Lazy.class) {
            if (INSTANCE == null) {
                INSTANCE = new Lazy();
            }
            return INSTANCE;
        }
    }


    // 双重校验锁  效率更高
    public static Lazy getInstance() {
        if (INSTANCE == null) {
            synchronized (Lazy.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Lazy();
                }
            }
        }
        return INSTANCE;
    }
}
```



#### (5) 死锁问题

![image-20250704155629887](https://gitee.com/yj1109/cloud-image/raw/master/img/20250704155630139.png)

### 1.4 线程创建的4种方法

```java
new Thread()
new Runnable()
new Callable()
new ThreadPoolExecutor()
```



## 2. Java常用类

### 1.1 String

![image-20250704160717482](https://gitee.com/yj1109/cloud-image/raw/master/img/20250704160717824.png)
