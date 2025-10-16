对应代码：
https://github.com/12722097458/java-base-learning-20250625

视频：

https://www.bilibili.com/video/BV1Kb411W75N?spm_id_from=333.788.videopod.episodes&vd_source=b23569b676ce26126febad3c290b16e8&p=152

# 一、计算机基本介绍

## 1、bit(比特)和byte(字节)

1个1或者1个0存储为一个比特(bit)，bit是计算机的最小存储单位

byte是计算机中最基本的存储单元，每个字节由8个bit构成



## 2、CPU、内存和硬盘

![image-20250917111119313](https://gitee.com/yj1109/cloud-image/raw/master/img/20250917111119789.png)

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

int aa = (int)  -3.3;  // -3   向0靠拢
int bb = (int)  3.3;  // 3



float ff = 1.11111111111111111111111F;
double dd = 1.11111111111111111111111D;
System.out.println("ff = " + ff);   // 1.1111112
System.out.println("dd = " + dd);   // 1.1111111111111112
```



![image-20250917123539851](https://gitee.com/yj1109/cloud-image/raw/master/img/20250917123540291.png)





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

> 其他类型也有对应的缓存 
>
> ```
> Character - int size = 127 + 1;
> ```

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

![image-20250822155701568](https://gitee.com/yj1109/cloud-image/raw/master/img/20250822155743176.png)



![image-20250822161005634](https://gitee.com/yj1109/cloud-image/raw/master/img/20250822161006724.png)



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
//         num = 12; // 编译报错 局部变量一旦赋值不能修改

        final int num2;
        num2 = 3;
//         num2 = 4; // 编译报错  不能修改
        Order order = new Order();
        A007_FinalTest test = new A007_FinalTest(3);
        test.method(order);
        System.out.println("order.id = " + order.id);  // 123
    }
    
    // 形参被final修饰，不能改变
    private void method(final int num) {
//        num = 3; // 编译报错 
    }

    // order 不可变，但里面的属性可变
    private void method(final Order order) {
        order.id = 123;
//         order = new Order(); // 编译报错  Cannot assign a value to final variable 'order'
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

```java
/*
*   成员内部类（Member Inner Class）   OuterClass.InnerClass innerObject = outerObject.new InnerClass();
    静态内部类（Static Nested Class）  OuterClass.StaticNestedClass nestedObject = new OuterClass.StaticNestedClass();
    局部内部类（Local Inner Class）    LocalInner localInner = new LocalInner();
    匿名内部类（Anonymous Inner Class）  函数式接口
*
* */
```

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



### 1.2 内存结构

栈，堆，方法区(字符串常量池在这里)

两个**常量**相加，最终还是指向字符串常量池。

其他情况都是StringBuffer. append 出新的字符串  -- 堆中

```java
// public final class String
String str = "abc";   //  常量池
String str2 = "abc";   //  常量池
String m = "a";   //  常量池
String m2 = "bc";   //  常量池
String s2 = new String("abc");  //堆
String abc = new String("abc").intern();   //  常量池
System.out.println(str == abc);   // true

String m3 = m + m; // 堆
System.out.println("m3==str = " + (m3==str));  //false

String m4 = m + "bc";  // 堆
System.out.println("m4==str = " + (m4==str));  //false

final String mm = "a";  // final是个常量
String m5 = mm + "bc";  // 常量池   编译器会直接优化为  String m5 = "abc";
System.out.println("m5==str = " + (m5==str));  //true
```



### 1.3 字符串和Byte[] 和Char[]转换

```java
String stt = "09AZaz中国";
char[] charArray = stt.toCharArray();
System.out.println("charArray = " + Arrays.toString(charArray));
String s1 = new String(charArray);
System.out.println("s1 = " + s1);

// 编码 ： 把能看懂的转换成看不懂的(二进制)
byte[] bytes = stt.getBytes(StandardCharsets.UTF_8);  // UTF-8   一个中文3个字节
System.out.println("bytes = " + Arrays.toString(bytes));
byte[] gbkBytes = stt.getBytes("gbk");  // GBK  一个中文2字节
System.out.println("gbkBytes = " + Arrays.toString(gbkBytes));

// 解码
// 乱码
String mismatch = new String(gbkBytes, StandardCharsets.UTF_8);
System.out.println("mismatch = " + mismatch);
// 正确
String correct = new String(gbkBytes, "GBK");
System.out.println("correct = " + correct);
```

### 1.4 String, StirngBuilder, StringBuffer对比

![f6c6ee1c990ac7c2df48aae55da0acc](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707152327665.jpg)



![bef2ce386f8c8fed000162c8e27b0af](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707152401694.jpg)



### 1.5 JDK8 日期类

![0547e10bc39a3f2e455eeaf8e7c6fcd](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707155211821.jpg)

![9a138c008f737022249ffb8f42592f6](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707155214760.jpg)

### 1.6 枚举类enum

![732136b9567ade0b82d5bc9b3f4e1db](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707155627355.jpg)

![894e037362e00b6d534e1c30e195444](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707155206228.jpg)

### 1.7 注解

![c923827dfbbc1d01bb89037f8f353f6](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707155352606.jpg)

![79ed33bf0f948f6558a09224d740006](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707155618773.jpg)

![7553891b665dc7b4411c04eef00d9a5](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707155637642.jpg)



## 3. 集合框架

以下是Java中主要Collection和Map实现类的总结，包括初始化大小、数据结构、扩容方式以及JDK7与JDK8的差异：

------

### **List实现类**

| **类名**                 | **初始化大小** | **数据结构** | **扩容方式**                                                 | **JDK7 vs JDK8差异**                                       |
| :----------------------- | :------------- | :----------- | :----------------------------------------------------------- | :--------------------------------------------------------- |
| **ArrayList**            | 10             | 动态数组     | 扩容至1.5倍（`newCapacity = oldCapacity + (oldCapacity >> 1)`） | JDK7：直接创建大小为10的数组 JDK8：首次`add()`时初始化为10 |
| **Vector**               | 10             | 动态数组     | 扩容至2倍（默认），或按构造函数的增量扩容                    | 无显著差异                                                 |
| **LinkedList**           | -              | 双向链表     | 无需扩容，动态添加节点                                       | 无差异                                                     |
| **CopyOnWriteArrayList** | 0              | 动态数组     | 每次修改时复制新数组（大小+1）                               | 无差异                                                     |

------

### **Set实现类**

| **类名**                | **初始化大小** | **底层实现**         | **数据结构**    | **扩容方式**           |
| :---------------------- | :------------- | :------------------- | :-------------- | :--------------------- |
| **HashSet**             | 16             | HashMap              | 哈希表          | 同HashMap              |
| **LinkedHashSet**       | 16             | LinkedHashMap        | 哈希表+双向链表 | 同HashMap              |
| **TreeSet**             | -              | TreeMap              | 红黑树          | 无需扩容               |
| **CopyOnWriteArraySet** | 0              | CopyOnWriteArrayList | 动态数组        | 同CopyOnWriteArrayList |

------

### **Map实现类**

| **类名**              | **初始化大小** | **数据结构**                                        | **扩容方式**                                              | **JDK7 vs JDK8差异**                                         |
| :-------------------- | :------------- | :-------------------------------------------------- | :-------------------------------------------------------- | :----------------------------------------------------------- |
| **HashMap**           | 16             | JDK7：数组+链表 JDK8：数组+链表/红黑树              | 扩容至2倍（当`size > threshold = capacity * loadFactor`） | **JDK7**：链表头插法（多线程可能死链） **JDK8**：链表尾插法；链表≥8且数组≥64时转红黑树 |
| **LinkedHashMap**     | 16             | 同HashMap + 双向链表维护顺序                        | 同HashMap                                                 | 无显著差异                                                   |
| **Hashtable**         | 11             | 数组+链表                                           | 扩容至`2n + 1`                                            | 无差异                                                       |
| **TreeMap**           | -              | 红黑树                                              | 无需扩容                                                  | 无差异                                                       |
| **ConcurrentHashMap** | 16             | JDK7：Segment分段锁+链表 JDK8：数组+链表/红黑树+CAS | JDK7：段内扩容至2倍 JDK8：整体扩容至2倍（多线程协同）     | **JDK7**：分段锁（并发度=Segment数） **JDK8**：CAS+synchronized锁链表头，粒度更细 |

------

### **关键区别总结**

1. **HashMap扩容**
   - **JDK7**：头插法（可能环形链）、纯链表
   - **JDK8**：尾插法、链表转红黑树、扩容优化（高位运算确定新位置）
2. **ConcurrentHashMap并发机制**
   - **JDK7**：分段锁（`Segment`），并发度固定
   - **JDK8**：`CAS`+`synchronized`锁链表头，并发度=数组大小
3. **ArrayList初始化**
   - **JDK7**：立即分配10个空间
   - **JDK8**：首次`add()`时分配（懒加载）
4. **树化条件（JDK8 HashMap）**
   - 链表长度 ≥ `8` **且** 数组长度 ≥ `64`，否则仅扩容。

------

### **扩容公式汇总**

| **类名**  | **扩容公式**                                     |
| :-------- | :----------------------------------------------- |
| ArrayList | `newCapacity = oldCapacity + (oldCapacity >> 1)` |
| Vector    | `newCapacity = oldCapacity * 2`（默认）          |
| HashMap   | `newCapacity = oldCapacity << 1`（2倍）          |
| Hashtable | `newCapacity = (oldCapacity << 1) + 1`           |

> **注**：
>
> - 所有默认负载因子（`loadFactor`）均为 **0.75**（除`TreeMap`无此概念）。
> - 并发容器（如`ConcurrentHashMap`）在JDK8中性能显著优化，推荐替代`Hashtable`。





### 1.1  List

有序可重复

![d0975307a6f4eb4704d82a20cda8e59](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707155535786.jpg)

![5c43d69ee4405480f19903d921d2b28](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707155547624.jpg)

![c33e150193bd1d8cb150589787220c4](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707155552995.jpg)

```java
package com.ityj.advance.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 *
 *  List : 有序、可重复  '动态'数组：长度不固定(jdk1.2)
 *      ArrayList：List的主要实现类(jdk1.2) ,线程不安全，效率高;低层使用数组Object[] elementData 存储
 *      LinkedList：(jdk1.2) 底层使用的是双向链表：对频繁的插入以及删除操作效率较高。
 *      Vector：最早的实现类（JDK1.0）  线性安全、效率低，低层使用数组Object[] elementData 存储
 *
 *      同：三个类都实现了List接口，存储数据的特点相同：有序，可重复
 */

public class ListTest {
    public static void main(String[] args) {

        int num = 3;

        int num2 = num >> 1;

        System.out.println("num2 = " + num2);
    }

    /**
     * ArrayList源码解析：
     * ==底层是数组==
     jdk1.7和1.8是不同的。
     jdk1.7：初始化的时候默认创建的集合长度是 10的Object[]
     1.7的扩容是1.5倍进行的：int newCapacity = oldCapacity + (oldCapacity >> 1);


     jdk1.8: 初始化时默认没有创建数组，节省内存：private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
     第一次调用add()方法时，会进行默认初始化长度为10的数组、
     后续和1.7一致

     总结：jdk7的ArrayList创建类似于单例中的饿汉式，而jdk8中的ArrayList创建类似于懒汉式。
     */
    @Test
    public void arrayListTest(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");   // 第11次调用需要扩容   10 -->  15
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        list.add("16"); // 第16次进行扩容   15 --> 22
        list.add("17");
        list.add("18");
        list.add("19");
        System.out.println(list.size());
    }

    /**
     *      ==底层双向链表==
     *   内部声明了Node的first 和 last属性，默认值都是null
     *   进行新增：list.add(123);   将123封装到Node中，创建了Node对象
     *
     *      可以体现LinkedList的双向链表结构
     *
     *private static class Node<E> {
         E item;
         Node<E> next;
         Node<E> prev;

         Node(Node<E> prev, E element, Node<E> next) {
         this.item = element;
         this.next = next;
         this.prev = prev;
         }
      }
     */
    @Test
    public void linkedListTest() {
        List list = new LinkedList();
        list.add("1");
        list.add("2");
    }


    /**
     *  ==底层是：数组== protected Object[] elementData
     *  初始化长度10，扩容方式也不太一样，2倍扩容
     *  同步，线性安全。很少使用      public synchronized boolean add(E e) {}
     */
    @Test
    public void vectorTest() {
        List list = new Vector();
        list.add(1);
    }
}
```





### 1.2  Set

无序不可重复

![image-20250707161355978](https://gitee.com/yj1109/cloud-image/raw/master/img/20250707161356375.png)



### 1.3 Map

#### (1) HashMap基本概念

![image-20250709110508957](https://gitee.com/yj1109/cloud-image/raw/master/img/20250709110509163.png)



(2) HashMap扩容以及put()

![image-20250709112301307](https://gitee.com/yj1109/cloud-image/raw/master/img/20250709112301510.png)

![image-20250709131522590](https://gitee.com/yj1109/cloud-image/raw/master/img/20250709131522783.png)

![image-20250709131744198](https://gitee.com/yj1109/cloud-image/raw/master/img/20250709131744338.png)



## 4. 泛型

### 1.1 泛型类

![image-20250709142412360](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911163433845.png)

### 1.2 泛型方法

![image-20250709133136368](https://gitee.com/yj1109/cloud-image/raw/master/img/20250709133136517.png)

### 1.3 通配符

![image-20250709133702059](https://gitee.com/yj1109/cloud-image/raw/master/img/20250709133702214.png)

![image-20250709134505751](https://gitee.com/yj1109/cloud-image/raw/master/img/20250709134505899.png)



## 5. IO流

![image-20250709142742218](https://gitee.com/yj1109/cloud-image/raw/master/img/20250709142742385.png)

![image-20250709143920136](https://gitee.com/yj1109/cloud-image/raw/master/img/20250709145817151.png)





### (1) 字符集

![image-20250709151812293](https://gitee.com/yj1109/cloud-image/raw/master/img/20250709151812448.png)

![image-20250709151920379](https://gitee.com/yj1109/cloud-image/raw/master/img/20250709151920542.png)

![image-20250709152026081](https://gitee.com/yj1109/cloud-image/raw/master/img/20250709152913093.png)

| **编码格式**   | **单中文字符占用字节数** | **特点说明**                                                 |
| :------------- | :----------------------- | :----------------------------------------------------------- |
| **UTF-8**      | **3字节**                | 变长编码，中文字符通常占3字节（Unicode基本多语言平面）       |
| **GBK**        | **2字节**                | 固定双字节中文编码                                           |
| **GB2312**     | 2字节                    | 固定双字节（兼容GB2312字符集内的中文）                       |
| **UTF-16BE**   | 2字节                    | 大端序，无BOM（Byte Order Mark）                             |
| **UTF-16LE**   | 2字节                    | 小端序，无BOM                                                |
| **UTF-16**     | 4字节（含BOM）           | 默认添加2字节BOM（`FE FF`），实际字符占2字节（总长=2+2n，n为字符数） |
| **UTF-32**     | 4字节                    | 固定4字节编码                                                |
| **ISO-8859-1** | 1字节                    | 不支持中文，中文字符被替换为`0x3F`（`?`）                    |
| **ASCII**      | 1字节                    | 不支持中文，中文字符被替换为`0x3F`（`?`）                    |





### (2) 对象流 ObjectInputStream

> Serializable和serialVersionUID缺一不可

```java
public class Person implements Serializable {
	private  static final long serialVersionUID = 42324234232L;
}
```

```java
package com.ityj.advance.io;

import com.ityj.advance.io.entity.Person;
import org.junit.jupiter.api.Test;

import java.io.*;

public class ObjectStream {

    @Test
    public void writeObject() {
        Person person = new Person();
        person.setAge(33);
        person.setName("颗颗");
        try (ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream("person.data"))) {
            oos.writeObject(person);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("complete....");
    }

    @Test
    public void readObject() {
        try (ObjectInputStream ois =  new ObjectInputStream(new FileInputStream("person.data"))) {
            Object o = ois.readObject();
            if (o instanceof Person) {
                Person p = (Person) o;
                System.out.println("p = " + p);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("complete....");
    }

}
```

```java
package com.ityj.advance.io.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
// 1. 必须实现implements Serializable， 否则写出时报错NotSerializableException
// 2. 必须指定唯一序列号serialVersionUID， 否则修改Person.java文件后，再次读取数据是会报错   java.io.InvalidClassException:
// 3. 当前类的所有属性必须都是可序列化的
// static和transient是不会被持久化的

public class Person implements Serializable {

    private  static final long serialVersionUID = 42324234232L;

    private String name;
    private int age;
    private int sss;
    
    // private Account account;// Account必须也是Serializable
}
```



## 6. 网络编程

**一句话总结关键区别：**

> **TCP 可靠但慢，像打电话；UDP 快但不可靠，像发短信。**

**面试回答建议：**
“TCP 和 UDP 是传输层的两大协议。核心区别在于：

1. **TCP 是面向连接的、可靠的协议**，通过确认、重传等机制保证数据完整有序，但速度较慢，适合文件传输、网页浏览等场景。
2. **UDP 是无连接的、不可靠的协议**，不保证数据到达或顺序，但开销小、速度快，适合视频直播、实时游戏等对延迟敏感的应用。”

### (1) TCP

```java
package com.ityj.advance.inet;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest {


    @Test
    public void client() throws IOException {
        Socket socket = new Socket("192.168.110.236", 8088);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("TCPTest.client.......".getBytes());
        outputStream.close();
        socket.close();
        System.out.println("client end...");
    }

    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] buff = new byte[8];
        int len;
        System.out.println("收到数据：" + socket.getInetAddress());
        while ((len = inputStream.read(buff)) != -1) {
            System.out.print(new String(buff, 0, len));
        }
        System.out.println("server end...");
        socket.close();
        serverSocket.close();
    }

}
```



### (2) UDP

```java
package com.ityj.advance.inet;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class UDPTest {


    @Test
    public void sender() throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        String str = "UDP报文。。。";
        DatagramPacket datagramPacket = new DatagramPacket(str.getBytes(), 0, str.getBytes().length,
                InetAddress.getByName("127.0.0.1"), 8099);

        datagramSocket.send(datagramPacket);
        datagramSocket.close();
        System.out.println("sender end...");
    }

    @Test
    public void receiver() throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8099);

        byte[] buffer = new byte[55];  // 可能读不完，或者读的过多
        DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
        datagramSocket.receive(datagramPacket);

        System.out.println("buffer = " + new String(buffer));

        datagramSocket.close();
        System.out.println("receiver end...");
    }

}
```



### (3) URLConnection

```java
@Test
    public void http() throws IOException, URISyntaxException {
        URL url = new URL("http://localhost:8888/ping");
        URI uri = url.toURI();
        Object content = url.getContent();
        int port = url.getPort();
        System.out.println("port = " + port);
    }

    @Test
    public void httpConncet() throws IOException, URISyntaxException {
        URL url = new URL("http://localhost:8888/ping");
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();

        InputStream inputStream = urlConnection.getInputStream();
        byte[] buff = new byte[8];
        int len;
        while ((len = inputStream.read(buff)) != -1) {
            System.out.println(new String(buff, 0, len));
        }
        inputStream.close();
        System.out.println("complete...........");
    }
```



### (4) HttpClient JDK11

```java
@Test
// since JDK11
public void httpClient() throws IOException, URISyntaxException, InterruptedException {
    URL url = new URL("http://localhost:8888/ping");

    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest. newBuilder()
            .uri(URI.create("http://localhost:8888/ping"))
            .header("Content-Type", "text/plain; charset=UTF-8")
            .build();
    HttpResponse.BodyHandler<String> stringBodyHandler = HttpResponse.BodyHandlers.ofString();
    HttpResponse<String> res = httpClient.send(request, stringBodyHandler);
    String body = res.body();
    System.out.println("body = " + body);

    System.out.println("complete...........");
}
```



## 7. 反射

### (1) 基本概念

![image-20250710142350940](https://gitee.com/yj1109/cloud-image/raw/master/img/20250710142351691.png)

![image-20250710142441026](https://gitee.com/yj1109/cloud-image/raw/master/img/20250710142441591.png)

![image-20250710144755938](https://gitee.com/yj1109/cloud-image/raw/master/img/20250710144756454.png)





### (2) 基本信息获取

```java
package com.ityj.advance.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodTest {

    @Test
    public void testReflect () throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // Class的实例对应着加载到内存中的一个运行时类
        Class clazz = Person.class;
        Class clazz2 = new Person().getClass();
        Class clazz3 = Class.forName("com.ityj.advance.reflect.entity.Person");
        Class clazz4 = MethodTest.class.getClassLoader().loadClass("com.ityj.advance.reflect.entity.Person");
        System.out.println(clazz == clazz2);  // true
        System.out.println(clazz3 == clazz4); // true
        System.out.println(clazz == clazz3);  // true


        Object object = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("eat", String.class);
        method.setAccessible(true);
        Object retValue = method.invoke(object, "香蕉");
        System.out.println("retValue = " + retValue);

        Method method1 = clazz.getDeclaredMethod("setName", String.class);
        method1.setAccessible(true);
        method1.invoke(object, "Jack");
        System.out.println("object.getClass() = " + object.getClass());
        System.out.println("object = " + object);

        Method method2 = clazz.getDeclaredMethod("getName");
        Object name = method2.invoke(object);
        System.out.println("name = " + name);

        Method[] methods = clazz.getMethods();  // 返回Person以及其父类 Object的所有public方法
        System.out.println("Arrays.toString(methods) = " + Arrays.toString(methods));

        Method[] declaredMethods = clazz.getDeclaredMethods();  // 只返回java.lang.String com.ityj.advance.reflect.entity.Person里的所有方法（包括private）
        System.out.println("Arrays.toString(declaredMethods) = " + Arrays.toString(declaredMethods));

    }
}

class Person {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void eat(String food) {
        System.out.println("吃的是：" + food);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
```



### (3) ClassLoader

```java
@Test
public void classLoaderTest() {
    ClassLoader classLoader = HelloController.class.getClassLoader();
    System.out.println("classLoader = " + classLoader);  // jdk.internal.loader.ClassLoaders$AppClassLoader@1f89ab83

    ClassLoader classLoader2 = classLoader.getParent();
    System.out.println("classLoader2 = " + classLoader2);  // jdk.internal.loader.ClassLoaders$PlatformClassLoader@1810399e


    // 表示方式：JVM 规范规定，由启动类加载器加载的类，其 Class.getClassLoader() 方法必须返回 null。
    // 这是一种约定，用于标识这些类的“根”来源。
    ClassLoader classLoader3 = String.class.getClassLoader();
    System.out.println("classLoader3 = " + classLoader3); // null
}
```



### (4) 反射应用 - 动态代理

```java
package com.ityj.advance.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    public static void main(String[] args) {
        Man man = new Man();
        Human proxy = (Human) ProxyFactory.newInstance(man);
        String retValue = proxy.eat("土豆");
        System.out.println("retValue = " + retValue);

    }
}


interface Human {
    String eat(String food);
}

class Man implements Human {
    @Override
    public String eat(String food) {
        System.out.println("Man 正在吃" + food);
        return food;
    }
}

class ProxyFactory {
    public static Object newInstance(Object obj) {
        //     public static Object newProxyInstance(ClassLoader loader,
        //                                          Class<?>[] interfaces,
        //                                          InvocationHandler h) {
        Object object = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(System.currentTimeMillis());
                Object retValue = method.invoke(obj, args);
                System.out.println(System.currentTimeMillis());
                return retValue;
            }
        });
        return object;
    }
}
```



# 四、JDBC

> https://www.bilibili.com/video/BV1eJ411c7rf/?spm_id_from=333.1391.0.0&p=12

## 1. JDBC 获取连接

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.22</version>
</dependency>
```

```java
@Test
public void testConnection() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.137.110/sys?serverTimezone=EST", "root", "root");
    System.out.println("connection = " + connection);
}
```



## 2. PreparedStatement

PreparedStatement 通过**分离 SQL 结构与参数数据**、**自动转义特殊字符**、**预编译锁定执行计划**三重机制，确保用户输入始终被当作数据处理而非可执行代码，从而从根本上杜绝 SQL 注入漏洞。



```java
package com.ityj.jdbc;

import com.ityj.jdbc.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Mysql数据库PreparedStatement  进行增删改查操作
@Slf4j
public class PrepareStatementTest {

    @Test
    public void testInsert() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.137.110/mydb?serverTimezone=EST", "root", "root");
        System.out.println("connection = " + connection);

        String sql = "insert into student (name, age, gender, birthday, comment) values (?, ?, ?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "Michael");
        ps.setInt(2, 26);
        ps.setString(3, "男");
        ps.setDate(4, new Date(System.currentTimeMillis()));
        FileInputStream is = new FileInputStream(new File("src/main/resources/static/tt微信图片_20241230163708.jpg"));
        ps.setBlob(5, is);
        int count = ps.executeUpdate();
        System.out.println("count = " + count);
        is.close();
        ps.close();
        connection.close();
    }

    @Test
    public void testInsertTransaction() {
        Connection connection = null;
        PreparedStatement ps = null;
        FileInputStream is = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.137.110/mydb?serverTimezone=EST", "root", "root");
            System.out.println("connection = " + connection);

            System.out.println("当前的autoCommit值：" + connection.getAutoCommit());
            // mysql 默认是TRANSACTION_REPEATABLE_READ。 oracle是TRANSACTION_READ_COMMITTED
            // mysql要求更严格， 效率会低一点
            System.out.println("getTransactionIsolation = " + connection.getTransactionIsolation());
            connection.setAutoCommit(false);   // 1.  用于异常回滚，解决事务问题

            String sql = "insert into student (name, age, gender, birthday, comment) values (?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "Michael");
            ps.setInt(2, 26);
            ps.setString(3, "男");
            ps.setDate(4, new Date(System.currentTimeMillis()));
            is = new FileInputStream(new File("src/main/resources/static/tt微信图片_20241230163708.jpg"));
            ps.setBlob(5, is);
            int count = ps.executeUpdate();

            int a = 1 / 0;

            connection.commit(); // 2.1 提交

            System.out.println("count = " + count);
        } catch (Exception e) {
            log.error("Error when handle code: ", e);
            try {
                connection.rollback();  // 2.2 异常回滚
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Test
    public void testDelete() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.137.110/mydb?serverTimezone=EST", "root", "root");

        String sql = "delete from  student where name = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "Jack");
        int count = ps.executeUpdate();
        System.out.println("count = " + count);
        ps.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.137.110/mydb?serverTimezone=EST", "root", "root");

        String sql = "update student set age = ? where id = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, 28);
        ps.setInt(2, 1);
        int count = ps.executeUpdate();
        System.out.println("count = " + count);
        ps.close();
        connection.close();
    }

    @Test
    public void testQuery() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.137.110/mydb?serverTimezone=EST", "root", "root");

        String sql = "select count(*) from student";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            System.out.println("resultSet count = " + resultSet.getInt(1));
        }


        String sql2 = "select name, age, gender, birthday, height  from student where id > ?";
        PreparedStatement ps2 = connection.prepareStatement(sql2);
        ps2.setInt(1, 1);
        ResultSet resultSet2 = ps2.executeQuery();
        List<Student> list = new ArrayList<>();
        while (resultSet2.next()) {
            Student student = new Student();
            student.setName(resultSet2.getString("name"));
            student.setAge(resultSet2.getInt("age"));
            student.setGender(resultSet2.getString("gender"));
            student.setBirthday(resultSet2.getDate("birthday"));
            student.setHeight(resultSet2.getDouble("height"));
            list.add(student);
        }
        System.out.println("list = " + list);

        ps.close();
        connection.close();
    }

}
```



## 3. ACID

锁、redolog、undolog、mvcc

![image-20250711162748942](https://gitee.com/yj1109/cloud-image/raw/master/img/20250711162749576.png)



## 4. 四种事务隔离级别

![image-20250711162913862](https://gitee.com/yj1109/cloud-image/raw/master/img/20250711162914398.png)

## 5. C3P0连接池

```xml
<dependency>
    <groupId>com.mchange</groupId>
    <artifactId>c3p0</artifactId>
    <version>0.9.5.2</version>
</dependency>
```

```java
@Test
    public void testC3p0Pool() throws SQLException, PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://192.168.137.110/sys?serverTimezone=EST");
        dataSource.setUser("root");
        dataSource.setPassword("root");
//        dataSource.setMaxPoolSize();   
//        dataSource.setInitialPoolSize();
        System.out.println("dataSource = " + dataSource);
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
    }


    /*
    *   public ComboPooledDataSource(String configName) {
        super(configName);
    }
    * 可以通过xml配置资源， 直接加载dataSource
    *
    * */
```



## 6. Druid连接池

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.8</version>
</dependency>
```

```java
public void testDruid() throws Exception {
    Map<String, String> map = Map.of("driverClassName", "com.mysql.cj.jdbc.Driver",
            "url", "jdbc:mysql://192.168.137.110/sys?serverTimezone=EST",
            "username", "root",
            "password", "root",
            "initialSize", "5");  // com.alibaba.druid.pool.DruidDataSourceFactory.config
    DataSource dataSource = DruidDataSourceFactory.createDataSource(map);

    Connection connection = dataSource.getConnection();
    System.out.println("connection = " + connection);
}
```



# 五、Servlet

> https://www.bilibili.com/video/BV1UN411x7xe/?spm_id_from=333.1391.0.0&p=1&vd_source=b23569b676ce26126febad3c290b16e8

## 1. http介绍

![image-20250714144418922](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714144419212.png)

![77825017097d6ce4821305a91b0c6d8](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714151137048.jpg)

![eae0f1512af5308ca39591947f282d0](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714151155294.jpg)





## 2. xml实现servlet配置

![c834dd5c53fe6ff0bd99bea9f1febf4](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714151550254.jpg)

### (1) 基本配置

#### 1.1 xml

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>

  <context-param>
    <param-name>unicode</param-name>
    <param-value>UTF-8</param-value>
  </context-param>

  <servlet>
    <servlet-name>configServlet</servlet-name>
    <servlet-class>com.ityj.servlet.ConfigServlet</servlet-class>
    <init-param>
      <param-name>key1</param-name>
      <param-value>v1</param-value>
    </init-param>
    <load-on-startup>-1</load-on-startup> <!--  -1表示懒加载。可以配置任意正整数，数字越小优先级越高。推荐6以上(tomcat默认配置了一些)，重复也可以  -->
  </servlet>
  <servlet-mapping>
    <servlet-name>configServlet</servlet-name>
    <url-pattern>/configServlet</url-pattern>
  </servlet-mapping>
</web-app>
```

#### 1.2 Servlet

```java
public class ConfigServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("into service()...");
        ServletConfig servletConfig = getServletConfig();
        String value = servletConfig.getInitParameter("key1");
        System.out.println("key1:" + value);

        ServletContext servletContext = getServletContext();
        String unicode = servletContext.getInitParameter("unicode");
        System.out.println("ServletContext - unicode:" + unicode);


        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("success!");
    }
}
```

![87951b626475e4539442ce3bd33c33f](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714151420755.jpg)







### (2) 配置说明

#### 1.1 load-on-startup

> 配置当前servlet是否懒加载。 默认-1表示懒加载  正整数表示加载顺序。越小越早加载。可以重复（内部会自己调整）

```xml
<servlet>
  <load-on-startup>-1</load-on-startup>
</servlet>
```

#### 1.2 init-param

> ServletConfig 作用于当前Servlet... 

```xml
<servlet>
    <init-param>
      <param-name>key1</param-name>
      <param-value>v1</param-value>
    </init-param>
  </servlet>
```

获取方式：

```java
ServletConfig servletConfig = getServletConfig();
String value = servletConfig.getInitParameter("key1");
System.out.println("key1:" + value);
```



#### 1.3 context-param

> ServletContext 作用域为整个应用，即application.  所有Servlet共享

```xml
<web-app>
  <context-param>
    <param-name>unicode</param-name>
    <param-value>UTF-8</param-value>
  </context-param>
</web-app>
```

```java
ServletContext servletContext = getServletContext();
String unicode = servletContext.getInitParameter("unicode");
System.out.println("ServletContext - unicode:" + unicode);
```



## 3. 注解实现servlet

http://localhost:8080/web_mvc/helloServlet

**/helloServlet**   斜线/不能省略

```java
@WebServlet(urlPatterns = "/helloServlet", loadOnStartup = -1,
initParams = {@WebInitParam(name = "k", value = "v"), @WebInitParam(name = "k2", value = "v2")})
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("into HelloServlet.service()...");

        ServletConfig servletConfig = this.getServletConfig();
        String k2 = servletConfig.getInitParameter("k2");
        System.out.println("k2 = " + k2);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("success!");
    }
}
```



## 4. Servlet生命周期（默认懒加载  ）

![image-20250714150945601](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714150945890.png)



### (1) loadOnStartup = -1

```shell
ConfigServlet constructor
ConfigServlet init
into LifeCycleServlet.service()...
into LifeCycleServlet.service()...
into LifeCycleServlet.service()...
into LifeCycleServlet.service()...
D:\2025\java\software\apache-tomcat-10.1.43\bin\catalina.bat stop
Using CATALINA_BASE:   "C:\Users\yinjun\AppData\Local\JetBrains\IntelliJIdea2025.1\tomcat\2b531dc6-e63e-414a-afbe-847e746b9862"
Using CATALINA_HOME:   "D:\2025\java\software\apache-tomcat-10.1.43"
Using CATALINA_TMPDIR: "D:\2025\java\software\apache-tomcat-10.1.43\temp"
Using JRE_HOME:        "C:\Program Files\Java\jdk-11"
Using CLASSPATH:       "D:\2025\java\software\apache-tomcat-10.1.43\bin\bootstrap.jar;D:\2025\java\software\apache-tomcat-10.1.43\bin\tomcat-juli.jar"
Using CATALINA_OPTS:   ""
14-Jul-2025 15:05:57.969 信息 [main] org.apache.catalina.core.StandardServer.await 通过关闭端口接收到有效的关闭命令。正在停止服务器实例。
14-Jul-2025 15:05:57.969 信息 [main] org.apache.coyote.AbstractProtocol.pause 暂停ProtocolHandler["http-nio-8080"]
14-Jul-2025 15:05:58.309 信息 [main] org.apache.catalina.core.StandardService.stopInternal 正在停止服务[Catalina]
ConfigServlet destroy
```

### (2) loadOnStartup = 10

```shell
Connected to server
[2025-07-14 03:07:28,396] Artifact web-mvc:war exploded: Artifact is being deployed, please wait…
14-Jul-2025 15:07:29.000 警告 [RMI TCP Connection(2)-127.0.0.1] org.apache.catalina.util.SessionIdGeneratorBase.createSecureRandom 使用[SHA1PRNG]创建会话ID生成的SecureRandom实例花费了[215]毫秒。
ConfigServlet constructor
ConfigServlet init
[2025-07-14 03:07:29,026] Artifact web-mvc:war exploded: Artifact is deployed successfully
[2025-07-14 03:07:29,026] Artifact web-mvc:war exploded: Deploy took 631 milliseconds


into LifeCycleServlet.service()...
into LifeCycleServlet.service()...
into LifeCycleServlet.service()...
D:\2025\java\software\apache-tomcat-10.1.43\bin\catalina.bat stop
Using CATALINA_BASE:   "C:\Users\yinjun\AppData\Local\JetBrains\IntelliJIdea2025.1\tomcat\2b531dc6-e63e-414a-afbe-847e746b9862"
Using CATALINA_HOME:   "D:\2025\java\software\apache-tomcat-10.1.43"
Using CATALINA_TMPDIR: "D:\2025\java\software\apache-tomcat-10.1.43\temp"
Using JRE_HOME:        "C:\Program Files\Java\jdk-11"
Using CLASSPATH:       "D:\2025\java\software\apache-tomcat-10.1.43\bin\bootstrap.jar;D:\2025\java\software\apache-tomcat-10.1.43\bin\tomcat-juli.jar"
Using CATALINA_OPTS:   ""
14-Jul-2025 15:08:23.431 信息 [main] org.apache.catalina.core.StandardServer.await 通过关闭端口接收到有效的关闭命令。正在停止服务器实例。
14-Jul-2025 15:08:23.431 信息 [main] org.apache.coyote.AbstractProtocol.pause 暂停ProtocolHandler["http-nio-8080"]
14-Jul-2025 15:08:23.770 信息 [main] org.apache.catalina.core.StandardService.stopInternal 正在停止服务[Catalina]
ConfigServlet destroy
```





## 5. ServletConfig和ServletContext

![3262bba062649ca8f40c473e48ad91e](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714151438725.jpg)



## 6. HttpServletRequest

```java
private void testHttpServletRequest(HttpServletRequest req) {
    // 请求行  GET /web_mvc/lifeCycleServlet HTTP/1.1
    System.out.println(req.getMethod());
    System.out.println(req.getContextPath());
    System.out.println(req.getProtocol());
    // 请求头  k:v
    System.out.println(req.getHeader("Connection"));
    // 请求体
    String k = req.getParameter("k");
    System.out.println("k = " + k);
}
```

## 7. HttpServletResponse

```java
private void testHttpServletResponse(HttpServletResponse resp) throws IOException {
    // 响应行  HTTP/1.1 200
    resp.setStatus(200);
    // 响应头
    resp.setContentType("text/html");
    resp.setCharacterEncoding("UTF-8");
    resp.setHeader("Connection", "keep-alive");  // Connection: keep-alive, keep-alive
    // 响应体
    PrintWriter writer = resp.getWriter();
    writer.write("<b>complete</b>");
}
```

![image-20250714140646342](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714140646632.png)

## 8. 请求转发

```
*   1. 请求转发是通过HttpServletRequest实现的
*   2. 是服务器内部行为，对客户端是屏蔽的
*   3. 客户端只产生了一个请求， 服务端只产生了一对request和response.
*   4. 客户端请求栏地址是不变的
*   5. 参数可以传递
*   6. 目标资源可以是servlet动态资源，也可以是html等静态资源
*   7. 目标资源可以是WEB-INF下的受保护的资源，该方式也是获取WEB-INF资源的唯一途径
*   8. 目标资源不可以是外部资源

// response  HTTP/1.1 200
```

![image-20250714154952867](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714154953164.png)



```java
@WebServlet(urlPatterns = "/servletA")
public class ServletA extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("into ServletA.service()...");

        req.getRequestDispatcher("servletB").forward(req, resp);
        //req.getRequestDispatcher("index.jsp").forward(req, resp);
        //req.getRequestDispatcher("WEB-INF/css/a.css").forward(req, resp); 可以访问
        // req.getRequestDispatcher("www.baidu.com").forward(req, resp); // 消息 请求的资源[/web_mvc/www.baidu.com]不可用



    }
}
```

```java
@WebServlet(urlPatterns = "/servletB")
public class ServletB extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("into ServletB.service()...");

        String k = req.getParameter("k");
        System.out.println("k from input parameter: " + k);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("ServletB success!");
    }
}
```

## 9.响应重定向

![image-20250714160635777](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714160636077.png)



```
*   1. 请求转发是通过HttpServletResponse实现的
*   2. 是在服务器提示下，客户端行为
*   3. 客户端产生了多个请求  >=2，同时也会有多对req, resp
*   4. 客户端请求栏地址是变化的
*   5. 参数不可以传递
*   6. 目标资源可以是servlet动态资源，也可以是html等静态资源(视图资源)
*   7. 不可以是WEB-INF下的受保护的资源
*   8. 目标资源可以是外部资源
```

```java
@WebServlet(urlPatterns = "/servlet1")
public class Servlet1 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("into Servlet1.service()...");

        // sendRedirect 两个作用 1.status code 302  2.  Location: servlet2
        resp.sendRedirect("servlet2");
        //resp.sendRedirect("index.jsp");
        //resp.sendRedirect("WEB-INF/css/a.css"); // 不能访问。 相当于想通过浏览器直接访问WEB-INF  拒绝
        //resp.sendRedirect("https://www.baidu.com"); // 可用

    }
}
```

```java
@WebServlet(urlPatterns = "/servlet2")
public class Servlet2 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("into Servlet2.service()...");

        String k = req.getParameter("k");
        System.out.println("k from input parameter: " + k);

    }
}
```

![image-20250714162016373](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714162016656.png)

![image-20250714162029637](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714162029921.png)





## 10. 乱码(todo)

![image-20250714164817025](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714164817344.png)

### （1）GET

![image-20250714164303703](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714164304029.png)



### （2）POST

![image-20250714164526147](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714164526460.png)





### （3）响应乱码

![image-20250714165451482](https://gitee.com/yj1109/cloud-image/raw/master/img/20250714165451783.png)

```java
package com.ityj.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// todo
@WebServlet("/garbled")
public class GarbledTextServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
/*
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");*/

        System.out.println(req.getParameter("name"));
        resp.getWriter().write(req.getParameter("name"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");

        System.out.println(req.getParameter("name"));
        resp.getWriter().write(req.getParameter("name"));
    }
}
```



## 11. 路径问题

### （1）前端路径问题

#### 1.1 相对路径

![image-20250715150635464](https://gitee.com/yj1109/cloud-image/raw/master/img/20250715150636145.png)



#### 1.2 绝对路径

斜杠/开头，需要加上contextName

![image-20250715150845818](https://gitee.com/yj1109/cloud-image/raw/master/img/20250715150846395.png)



![image-20250715151036095](https://gitee.com/yj1109/cloud-image/raw/master/img/20250715151108758.png)



### （2）后端路径

![1752563607457](https://gitee.com/yj1109/cloud-image/raw/master/img/20250715151340289.png)





## 12. 会话管理-Cookie

![image-20250715154147858](https://gitee.com/yj1109/cloud-image/raw/master/img/20250715154148321.png)

```java
System.out.println("into CookieServlet1.service()...");
Cookie cookie =  new Cookie("k1", "v1");
cookie.setMaxAge(3 * 60); // second
Cookie cookie2 =  new Cookie("k2", "v2");
resp.addCookie(cookie);
resp.addCookie(cookie2);
```



## 13.  会话管理-Session

![image-20250715155741648](https://gitee.com/yj1109/cloud-image/raw/master/img/20250715160522729.png)



![image-20250715155816116](https://gitee.com/yj1109/cloud-image/raw/master/img/20250715155816733.png)

```java
@WebServlet(urlPatterns = "/session1")
public class SessionServlet1 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("into SessionServlet1.service()...");

        // 会判断JSESSIONID作为key的Cookie是否存在， 不存在则创建
            // 存在会判断当前会话的JSESSIONID是否存在， 不存在则创建
        HttpSession session = req.getSession();
        System.out.println(session.getId() + "-------------" + session.isNew());
        Object user = session.getAttribute("user");
        if (user == null) {
            System.out.println("user is null");
            session.setAttribute("user", "Jack");
        } else {
            System.out.println("user is " + user);
        }
    }
}
```

## 14. 三大域

请求域（HttpServletRequest）： 一次请求。请求转发也是一次

会话域（HttpSession）： 一个会话，可以是多个请求

应用域（ServletContext）: 本应用内，可以是多个会话

![image-20250715161009209](https://gitee.com/yj1109/cloud-image/raw/master/img/20250715161225986.png)



![image-20250715160920180](https://gitee.com/yj1109/cloud-image/raw/master/img/20250715161420541.png)





## 15. Filter

![image-20250715162641914](https://gitee.com/yj1109/cloud-image/raw/master/img/20250715162642211.png)

```java
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(urlPatterns = {"/helloServlet", "*.html"})
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("before MyFilter.doFilter...");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("end MyFilter.doFilter...");
    }
}
```





### Filter生命周期

> 项目启动即初始化，项目关闭销毁。

> 初始化在Servlet之前



**LifeCycleFilter constructor...**
**LifeCycleFilter init = ApplicationFilterConfig[name=com.ityj.filter.LifeCycleFilter, filterClass=com.ityj.filter.LifeCycleFilter]**
ConfigServlet constructor
ConfigServlet init

**before LifeCycleFilter.doFilter...**
before MyFilter.doFilter...
into HelloServlet.service()...
k2 = v2
end MyFilter.doFilter...
**end LifeCycleFilter.doFilter...**

ConfigServlet destroy
**LifeCycleFilter destroy...**





## 16. Listener

![image-20250715163417835](https://gitee.com/yj1109/cloud-image/raw/master/img/20250715163418107.png)

```java
package com.ityj.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener("/helloServlet")
public class MyContextListener implements ServletContextListener, ServletContextAttributeListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("MyContextListener contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("MyContextListener contextDestroyed");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("MyContextListener attributeAdded  " + scae.getName() + "  " + scae.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        ServletContextAttributeListener.super.attributeRemoved(scae);
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        ServletContextAttributeListener.super.attributeReplaced(scae);
    }


}
```





# 六、Spring

> 基础性综合性开发框架

> https://www.bilibili.com/video/BV1kR4y1b7Qc?spm_id_from=333.788.videopod.episodes&vd_source=b23569b676ce26126febad3c290b16e8&p=2

## 1. Spring简述

![image-20250716110803587](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716110804092.png)

![image-20250716110938235](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716110938601.png)





## 2. 整合log4j2

pom

```xml
<!-- 使用slf4j 作为日志门面 -->
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-api</artifactId>
  <version>1.7.26</version>
</dependency>
<!-- 使用 log4j2 的适配器进行绑定 -->
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-slf4j-impl</artifactId>
  <version>2.9.1</version>
</dependency>

<!-- log4j2 日志门面 -->
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-api</artifactId>
  <version>2.11.1</version>
</dependency>
<!-- log4j2 日志实面 -->
<dependency>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-core</artifactId>
  <version>2.11.1</version>
</dependency>
```



log4j2.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration status="warn" monitorInterval="5">
    <properties>
        <property name="LOG_HOME">logs</property>
    </properties>

    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] [%-5level] %c{36}:%L --- %m%n" />
        </Console>

        <File name="file" fileName="${LOG_HOME}/myfile.log">
            <PatternLayout pattern="[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5level] %l %c{36} - %m%n" />
        </File>

        <RandomAccessFile name="accessFile" fileName="${LOG_HOME}/myAcclog.log">
            <PatternLayout pattern="[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5level] %l %c{36} - %m%n" />
        </RandomAccessFile>

        <RollingFile name="rollingFile" fileName="${LOG_HOME}/myrollog.log"
                     filePattern="E:/logs/$${date:yyyy-MM-dd}/myrollog-%d{yyyy-MM-dd-HH-mm}-%i.log">
            <ThresholdFilter level="debug" onMatch="ACCEPT" onMismatch="DENY" />
            <PatternLayout pattern="[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5level] %l %c{36} - %msg%n" />
            <Policies>
                <OnStartupTriggeringPolicy />
                <SizeBasedTriggeringPolicy size="10 MB" />
                <TimeBasedTriggeringPolicy />
            </Policies>
            <DefaultRolloverStrategy max="30" />
        </RollingFile>

    </Appenders>

    <Loggers>
        <Root level="debug">
            <AppenderRef ref="Console" />
            <AppenderRef ref="file" />
        </Root>
    </Loggers>
</configuration>
```

## 3. IoC

### （1）概念介绍

![image-20250716112508256](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716112508922.png)

![image-20250716112750931](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716112751447.png)



### （2）DI

![image-20250716112922867](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716112923244.png)



### （3）简单实现Ioc

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="user" class="com.ityj.spring.entity.User"></bean>

</beans>
```

```java
public class User {
}
```

```java
@Test
public void testBean() {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    User user = context.getBean("user", User.class);
    System.out.println("user = " + user);
}
```

## 4. 基于xml管理的bean

### （1）bean创建方式

```java
@Test
    public void testBean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // 1 根据名字
        Object user1 = context.getBean("user");
        // 2 根据类型
        // org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.ityj.spring.entity.User' available: expected single matching bean but found 2: user,user2
        //  <bean id="user" class="com.ityj.spring.entity.User"></bean>
        //    <bean id="user2" class="com.ityj.spring.entity.User"></bean>
//        User user2 = context.getBean(User.class);
        // 3 名字 + 类型
        User user3 = context.getBean("user", User.class);
        log.info("user1 = {}", user1);
        log.info("user3 = {}", user3);
    }
```



### （2）依赖注入 - setter

```java
public class User {

    private String name;
    private Integer age;
}
```

```xml
<bean id="user" class="com.ityj.spring.entity.User">
    <property name="name" value="Jack"/>
    <property name="age" value="23"/>
</bean>
```



### （3）依赖注入 - constructor

```java
public class User {

    private String name;
    private Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
```

```xml
<!--构造器注入-->
<bean id="user2" class="com.ityj.spring.entity.User" >
    <constructor-arg name="name" value="Merry"/>
    <constructor-arg name="age" value="34"/>
</bean>
```



### （4）特殊值处理 - String

```xml
<!--属性特殊值处理-->
<bean id="user3" class="com.ityj.spring.entity.User" >
    <constructor-arg name="name" value="&lt;&gt;"/>
    <constructor-arg name="age" value="34"/>
</bean>

<!--属性特殊值处理2-->
<bean id="user4" class="com.ityj.spring.entity.User">
    <property name="name">
        <value><![CDATA[ a <> b ]]></value>
    </property>
    <property name="age" value="23"/>
</bean>
```

```java
@Test
public void testDi_special_character() {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    User user = context.getBean("user3", User.class);
    log.info("user = {}", user);  //  user = User{name='<>', age=34}

    User user4 = context.getBean("user4", User.class);
    log.info("user4 = {}", user4);  //  user4 = User{name=' a <> b ', age=23}
}
```



### （5）为对象类型赋值

```java
public class Account {
}
```

```java
public class User {

    private String name;
    private Integer age;
    private Account account;
```

```xml
<!--注入对象-->
<bean id="account" class="com.ityj.spring.entity.Account"/>
<bean id="user5" class="com.ityj.spring.entity.User">
    <property name="name" value="Lucy"/>
    <property name="age" value="23"/>
    <property name="account" ref="account"/>
</bean>
```

### （6）赋值arr/list/map

```java
public class Account {
    private String[] arr;
    private List<Integer> list;
    private Map<String, Integer> map;
```

```xml
 <bean id="account2" class="com.ityj.spring.entity.Account">
        <property name="arr">
            <array>
                <value>a</value>
                <value>b</value>
                <value>c</value>
            </array>
        </property>

        <property name="list">
            <list>
                <value>1</value>
                <value>2</value>
                <value>3</value>
            </list>
        </property>

        <property name="map">
            <map>
                <entry key="k1" value="1"/>
                <entry value="2" key="k2"/>
            </map>
        </property>
    </bean>
```



### （7）引入外部属性文件

#### 1.1 为当前配置文件引入外部的配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

    <context:property-placeholder location="classpath:jdbc.properties"/>
```



#### 1.2 用${}引入对应配置

```xml
<bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${jdbc.driverClassName}"/>
    <property name="url" value="${jdbc.url}"/>
    <property name="username" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
    <property name="initialSize" value="${jdbc.initialSize}"/>
</bean>
```



### （8）自动注入(autowire 设置 byName")

```xml
<bean id="account" class="com.ityj.spring.entity.Account">
    <property name="arr">
        <array>
            <value>a</value>
            <value>b</value>
            <value>c</value>
        </array>
    </property>
</bean>
```

```xml
<!--自动装配注入对象-->
<bean id="user_auto" class="com.ityj.spring.entity.User" autowire="byName">
    <property name="name" value="Lucy"/>
    <property name="age" value="23"/>
</bean>
```

```java
@Test
public void testDi_auto() {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    User user = context.getBean("user_auto", User.class);
    System.out.println("user = " + user);  // user = User{name='Lucy', age=23, account=Account{arr=[a, b, c], list=null, map=null}}
}
```

## 5. bean的生命周期

> ### 1. **实例化（Instantiation）**
>
> Spring 容器通过构造函数或工厂方法创建 Bean 的实例
>
> ### 2. **属性赋值（Population）**
>
> **依赖注入**：容器为 Bean 的属性赋值（通过 Setter、构造函数或字段注入）
>
> Autowired: AutowiredAnnotationBeanPostProcessor
>
> ### 3. **初始化（Initialization）**
>
> servlet的init()方法
>
> 1. BeanPostProcessor.postProcessBeforeInitialization()  前后两个方法
> 2. before-method 
>
> ### 4. **销毁（Destruction）**
>
> destroy

![1752640751223](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716123916743.png)

```java
package com.ityj.spring.entity;

public class LifeCycleBean {

    private String name;

    public LifeCycleBean() {
        System.out.println("1    LifeCycleBean 无参构造");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("2    LifeCycleBean.setName " + name);
        this.name = name;
    }

    private void init() {
        System.out.println("4   LifeCycleBean.init() method");
    }

    private void destroy() {
        System.out.println("7   LifeCycleBean.destroy() method");
    }

}
```

```java
package com.ityj.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("3   before  BeanPostProcessor.postProcessBeforeInitialization() method");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("5   after  BeanPostProcessor.postProcessAfterInitialization() method");
        return bean;
    }


}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="lifeCycleBean" class="com.ityj.spring.entity.LifeCycleBean" init-method="init" destroy-method="destroy" lazy-init="default">
        <property name="name" value="LifeCycle"/>
    </bean>

    <bean id="myBeanPostProcessor" class="com.ityj.spring.processor.MyBeanPostProcessor"/>
</beans>
```

```java
public void beanLifeCycle() throws SQLException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-lifecycle.xml");
    LifeCycleBean entity = context.getBean("lifeCycleBean", LifeCycleBean.class);
    System.out.println("6 bean实例创建完成。。entity = " + entity);
    // 测试bean生命周期
    context.close();
}


12:40:35.177 [main] [DEBUG] org.springframework.beans.factory.support.DefaultListableBeanFactory:225 --- Creating shared instance of singleton bean 'lifeCycleBean'
1    LifeCycleBean 无参构造
2    LifeCycleBean.setName LifeCycle
3   before  BeanPostProcessor.postProcessBeforeInitialization() method
4   LifeCycleBean.init() method
5   after  BeanPostProcessor.postProcessAfterInitialization() method
6 bean实例创建完成。。entity = com.ityj.spring.entity.LifeCycleBean@fff25f1
12:40:35.203 [main] [DEBUG] org.springframework.context.support.ClassPathXmlApplicationContext:1049 --- Closing org.springframework.context.support.ClassPathXmlApplicationContext@5e8ac0e1, started on Wed Jul 16 12:40:34 CST 2025
7   LifeCycleBean.destroy() method
```



## 6. 基于注解管理的bean

### （1）基本介绍

![image-20250716132143597](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716132143842.png)



### （2）注解使用

#### 1.1 开启注解扫描

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">

    <!--开启注解扫描，扫描com.ityj.spring以及其子包下的注解-->
    <context:component-scan base-package="com.ityj.spring"/>

</beans>
```



#### 1.2 添加注解

```java
package com.ityj.spring.annotation.bean;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component //默认id是类名首字母小写  <bean id="student" class="com.ityj.spring.annotation.bean.Student"/>
/*@Repository
@Service
@Controlle*/
public class Student {
}
```

![image-20250716133452864](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716133453140.png)

#### 1.3 测试

```java
@Test
public void beanCreate() {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-annotation.xml");
    Student student = context.getBean("student", Student.class);
    System.out.println("student = " + student);
}
```

### （3）@Autowire注入属性

```java
package com.ityj.spring.annotation.controller;

import com.ityj.spring.annotation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    // 1.成员变量
    /*@Autowired  byType
    private UserService userService;*/

    /*// 2. set方法
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/

    // 3. 构造方法上
    /*private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }*/

    // 4. 构造方法的形参上
    /*private UserService userService;
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }*/

    // 5. 当前方法只有一个只含一个变量的构造方法， 可以省略@Autowired
    /*private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }*/

    // 6 Autowired + Qualifier
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    public void add() {
        System.out.println("UserController.add()...");
        userService.add();
    }


}
```



默认是按照类型注入  - byType。 如果有多个实现，那么需要制定具体name. 否则会报错。

```java
// Could not autowire. There is more than one bean of 'UserDao' type.
    *   Beans:
userMongoDbDaoImpl   (UserMongoDbDaoImpl.java)
userMysqlDaoImpl   (UserMysqlDaoImpl.java)
    * */
    @Autowired
    @Qualifier("userMongoDbDaoImpl")
    private UserDao userDao;
```

Autowired 为什么在属性赋值阶段？

1. **逻辑顺序合理**
   - 先创建对象实例 → 再填充依赖 → 最后初始化。
2. **解决循环依赖**
   - 属性赋值阶段允许提前暴露半成品 Bean（三级缓存），从而支持 Setter/字段注入的循环依赖。
3. **扩展性**
   - 通过 `BeanPostProcessor`（如 `AutowiredAnnotationBeanPostProcessor`）在属性赋值前后插入自定义逻辑。



### （4）@Resource注入属性

```
@Resource(name = "resourceUserMysqlDaoImpl")
private UserDao userDao;

1. 先根据name "resourceUserMysqlDaoImpl"找bean
2. 再根据name "userDao"找bean
3. 最后根据type UserDao.class找bean
```

```java
package com.ityj.spring.resource.service.impl;

import com.ityj.spring.resource.dao.UserDao;
import com.ityj.spring.resource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("resourceUserServiceImpl")
public class UserServiceImpl implements UserService {

    // y.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.ityj.spring.resource.dao.UserDao' available: expected single matching bean but found 2: resourceUserMongoDbDaoImpl,resourceUserMysqlDaoImpl
    /*@Resource   // @Resource的name没有配置， 所以1. 先根据"userDao"去找bean， 没有找到  2. 再根据UserDao类型找  --》 找到两个。。所以报错
    private UserDao userDao;*/

    // 正确方式1
    @Resource
    private UserDao resourceUserMysqlDaoImpl;

    // 正确方式 2
    @Resource(name = "resourceUserMysqlDaoImpl")
    private UserDao userDao;

    @Override
    public void add() {
        System.out.println("resourceUserServiceImpl.add()...");
        System.out.println(userDao == resourceUserMysqlDaoImpl);
        userDao.add();
    }
}
```

### （5）@Autowire 和 @Resource区别

> “`@Autowired` 是 Spring 的，默认按类型找，找不到再按名字；`@Resource` 是 Java 标准的，默认按名字找，找不到再按类型。Autowired 有必选控制，Resource 没有直接的必选属性。”

**加分点（如果时间允许）：**

- 提到 `@Autowired` 需要 `@Qualifier` 配合处理多个同类型 Bean，而 `@Resource` 可以直接用 `name` 属性指定。
- 说明 `@Resource` 在非 Spring 环境（如 Java EE）中也能用，可移植性稍好（虽然面试主要问 Spring）。





## 7. 全注解开发

### （1）添加配置类

> @Configuration
>
> @ComponentScan

```java
package com.ityj.spring.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.ityj.spring")  // 组件扫描 <context:component-scan base-package="com.ityj.spring"/>
public class MyConfiguration {

}
```

### （2）测试

```java
@Test
public void testFullAnnotation() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
    UserController userController = context.getBean("userController", UserController.class);
    userController.add();
}
```





## 8. AOP

通过切入点(pointcut)表达式可以找到连接点(join point就是需要增强的方法add()...).

通知(Advice)里的代码就是对连接点进行增强。

Aspect就是自己写的代码。比如 MyLogAspect @Aspect

### （1）AOP介绍

![image-20250716155535329](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716155535603.png)

![image-20250716165315653](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716165315832.png)





#### 1.0 横切关注点

![image-20250716164943186](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716164943367.png)



#### 1.1 连接点（JoinPoint）

> 具体的方法。。。UserService.add()

![image-20250716165216696](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716165216897.png)



#### 1.2 切入点（PointCut）

> 切入点表达式来表示被增强的方法

![image-20250716165240967](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716165241128.png)



#### 1.3 通知 （Advice）

> Before advice. after advice ... 

![image-20250716165003590](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716165003793.png)



#### 1.4 切面 (Aspect)

> LogAspect 类就是一个切面

![image-20250716165052026](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716165052195.png)



#### 1.5 目标

被代理的目标对象

#### 1.6 代理

向目标对象应用通知之后创建的代理对象

### （2）jdk动态代理和cglib动态代理

![image-20250716160403351](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716160403641.png)

CGLIB通过ASM生成目标类的子类，并重写非final方法并委托给MethodIntercepor，结合FastClass优化调用性能。

适用于代理无接口的类，如Spring非接口Bean，且对执行性能要求较高的场景

局限：无法代理final类或方法，且代理类过程占用较多内存

```java
package com.ityj.spring.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLIBProxy {
    public static void main(String[] args) {
        // 导出生成的代理字节码文件
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "outclass/");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(AccountService.class);  // 设置目标类为父类
        enhancer.setCallback(new CglibInterceptor()); // 设置拦截器
        AccountService proxy = (AccountService) enhancer.create();  // 创建代理实例
        proxy.add();
    }
}


// 普通方法(非接口)
class AccountService {
    public void add() {
        System.out.println("AccountService.add");
    }
}

class CglibInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("CglibInterceptor.intercept  前置增强");
        Object res = methodProxy.invokeSuper(o, args);
        System.out.println("CglibInterceptor.intercept  后置增强");
        return res;
    }
}
```



### （3）切入点配置规则

![image-20250716164518768](https://gitee.com/yj1109/cloud-image/raw/master/img/20250716164518929.png)



### （4）注解实现AOP

#### 1.1  目标类

```java
package com.ityj.spring.aop.service.impl;

import com.ityj.spring.aop.service.Calculator;
import org.springframework.stereotype.Service;

@Service
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        System.out.println("CalculatorImpl.add  -- 进入目标方法");
        //int aa = 1 / 0;
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        System.out.println("CalculatorImpl.minus");
        return a - b;
    }
}
```



#### 1.2 Aspect切面

@After不管有没有异常都会执行

```java
package com.ityj.spring.aop.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {



    //@Around(value = "execution (public int com.ityj.spring.aop.service.impl.CalculatorImpl.*(int, int))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object proceed = null;
        try {
            System.out.println("@Around环绕通知 前...");
            proceed = joinPoint.proceed();
            System.out.println("@Around环绕通知 afterReturning...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("@Around环绕通知 catch 异常...");
        } finally {
            System.out.println("@Around环绕通知 后...");
        }
        return proceed;
    }


    //@Before(value = "execution (public int com.ityj.spring.aop.service.impl.CalculatorImpl.*(int, int))")
    @Before("pt()")   // testcase中不生效的话，自己web测试就行
    public void before(JoinPoint joinPoint) {
        System.out.println("@Before前置通知...");
    }

    @After(value = "pt()")
    //@After(value = "execution (public int com.ityj.spring.aop.service.impl.CalculatorImpl.*(int, int))")
    public void after(JoinPoint joinPoint) {
        System.out.println("@After后置通知...");
    }


    @AfterReturning(value = "execution (public int com.ityj.spring.aop.service.impl.CalculatorImpl.*(int, int))", returning = "res")
    public void afterReturning(JoinPoint joinPoint, Object res) {
        System.out.println("@AfterReturning 后置通知... " + res);
    }

    @AfterThrowing(value = "execution (public int com.ityj.spring.aop.service.impl.CalculatorImpl.*(int, int))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("@AfterThrowing 异常通知..." + ex);
    }

    @Pointcut("execution (public int com.ityj.spring.aop.service.impl.CalculatorImpl.*(int, int))")
    public void pt(){}

}

```



#### 1.3 xml

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--开启注解扫描，扫描com.ityj.spring以及其子包下的注解-->
    <context:component-scan base-package="com.ityj.spring.aop"/>
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>

</beans>
```



#### 1.4 test  （xml的执行顺序不太一致，和顺序有关）

```java
@Test
public void testBefore() {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-aop.xml");
    Calculator calculator = context.getBean("calculatorImpl", Calculator.class);
    int add = calculator.add(1, 2);
    System.out.println("add = " + add);
}
```



##### 1.4.1 正常

@Around环绕通知 前...
@Before前置通知...
CalculatorImpl.add  -- 进入目标方法
@AfterReturning 后置通知... 3
@After后置通知...
@Around环绕通知 afterReturning...
@Around环绕通知 后...



##### 1.4.2 异常

@Around环绕通知 前...
@Before前置通知...
CalculatorImpl.add   -- 进入目标方法
@AfterThrowing 异常通知...java.lang.ArithmeticException: / by zero
@After后置通知...
java.lang.ArithmeticException: / by zero

@Around环绕通知 catch 异常...
@Around环绕通知 后...

##### 1.4.3 @After相当于finally里的代码，一定会执行



### （5）xml实现AOP

```java
package com.ityj.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class LogXmlAspect {

    public void before(JoinPoint joinPoint) {
        System.out.println("@Before前置通知...");
    }

    public void afterReturning(JoinPoint joinPoint, Object res) {
        System.out.println("@AfterReturning 后置通知... " + res);
    }

    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("@AfterThrowing 异常通知..." + ex);
    }

    public void after(JoinPoint joinPoint) {
        System.out.println("@After后置通知...");
    }

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object proceed = null;
        try {
            System.out.println("@Around环绕通知 前...");
            proceed = joinPoint.proceed();
            System.out.println("@Around环绕通知 afterReturning...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("@Around环绕通知 catch 异常...");
        } finally {
            System.out.println("@Around环绕通知 后...");
        }
        return proceed;
    }

    public void pointcut(){}

}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--开启注解扫描，扫描com.ityj.spring以及其子包下的注解-->
    <context:component-scan base-package="com.ityj.spring.aop"/>

    <aop:config >
        <aop:pointcut id="pt" expression="execution(public int com.ityj.spring.aop.service.impl.CalculatorImpl.*(..))"/>
        <aop:aspect ref="logXmlAspect">
            <aop:before method="before" pointcut-ref="pt"></aop:before>
            <aop:after method="after" pointcut-ref="pt"></aop:after>
            <aop:after-returning method="afterReturning" pointcut-ref="pt" returning="res"></aop:after-returning>
            <aop:after-throwing method="afterThrowing" pointcut-ref="pt" throwing="ex"></aop:after-throwing>
            <aop:around method="around" pointcut-ref="pt"></aop:around>
        </aop:aspect>


    </aop:config>

</beans>
```

@Before前置通知...
@Around环绕通知 前...
CalculatorImpl.add  -- 进入目标方法
@Around环绕通知 afterReturning...
@Around环绕通知 后...
@AfterReturning 后置通知... 3
@After后置通知...



### （6）项目中AOP应用

#### 1.1 日志记录

#### 1.2 事务

#### 1.3 权限校验

soeid

​	role1：read

​	role2: write



用户表

角色表

权限表

用户角色关联表--- jy17241	有哪些role

角色权限关联表 -- role1 有哪些权限



#### 1.4 统一异常处理

@ControllerAdvice

@ExceptionHandler



## 9. tx事务

### （1）Spring JdbcTemplate

#### 1.1 pom

```xml
<dependency>
  <groupId>org.springframework.data</groupId>
  <artifactId>spring-data-jdbc</artifactId>
  <version>2.1.3</version>
</dependency>
```



#### 1.2 配置数据源

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--开启注解扫描，扫描com.ityj.spring以及其子包下的注解-->
    <context:component-scan base-package="com.ityj.spring.jdbc"/>
    <context:property-placeholder location="classpath:jdbc.properties"/>


    <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driverClassName}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <property name="initialSize" value="${jdbc.initialSize}"/>
    </bean>

    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="druidDataSource"/>
    </bean>


</beans>
```

#### 1.3  测试

```java
import com.ityj.spring.jdbc.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;


@SpringJUnitConfig(locations = "classpath:bean-jdbc.xml")
public class TestJdbc {

    private static final Logger log = LoggerFactory.getLogger(TestJdbc.class);

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbcTemplate() {
        log.info(jdbcTemplate.toString());
    }

    @Test
    public void insert() {
        String sql = "insert into student (name, age, gender) values (?, ?, ?)";
        int insert = jdbcTemplate.update(sql, "SpringTemplate", "33", "男");
        System.out.println("insert = " + insert);
    }

    @Test
    public void update() {
        String sql = "update student set name = ? where id = ?";
        int update = jdbcTemplate.update(sql, "麦克", "5");
        System.out.println("update = " + update);
    }

    @Test
    public void delete() {
        String sql = "delete from student  where id = ?";
        int delete = jdbcTemplate.update(sql, "5");
        System.out.println("delete = " + delete);
    }

    @Test
    public void query() {
        String sql = "select name, age, gender from student where id < ?";

        List<Student> students = jdbcTemplate.query(sql,  new BeanPropertyRowMapper<>(Student.class), 12);
        System.out.println("students = " + students);

    }


}
```



### （2） tx实现

#### 1.1 xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/tx
       https://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--开启注解扫描，扫描com.ityj.spring以及其子包下的注解-->
    <context:component-scan base-package="com.ityj.spring.tx"/>
    <context:property-placeholder location="classpath:jdbc.properties"/>

    <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driverClassName}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <property name="initialSize" value="${jdbc.initialSize}"/>
    </bean>

    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="druidDataSource"/>
    </bean>

    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="druidDataSource"></property>
    </bean>
    <tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>

</beans>
```



#### 1.2 测试tx和传播行为

![image-20250717155023581](https://gitee.com/yj1109/cloud-image/raw/master/img/20250717155023849.png)

```java
@Service
public class Service2 {

    @Autowired
    private StudentService studentService;

    @Transactional
    public int batchAdd(List<Student> list) {
        for (int i = 0; i < list.size(); i++) {
            studentService.addAndUpdate(list.get(i));
        }
        return 100;
    }
}

```

```java
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int addAndUpdate(Student student) {
        studentDao.add(student);

        if ("second".equals(student.getName())) {
            int a = 1 / 0;
        }

        int update = studentDao.update(student);
        return update;
    }
}
```



#### 1.3 测试

```java
@SpringJUnitConfig(locations = "classpath:bean-tx.xml")
public class StudentControllerTest {

    @Autowired
    private StudentController studentController;


    @Test
    public void testTx() {
        studentController.addAndUpdate();
    }


    // Propagation.REQUIRES_NEW 会新建一个事务， 单词成功就成功
    //
    @Test
    public void testPro() {
        studentController.batchAdd();
    }


}
```





### （3）tx全注解（配置类）

```java
@Configuration
@ComponentScan(basePackages = "com.ityj.spring.tx")
@EnableTransactionManagement
public class SpringConfig {

    @Bean
    public DataSource getDataSource() throws Exception {  // Creating shared instance of singleton bean 'getDataSource'
        Map<String, String> map = Map.of("driverClassName", "com.mysql.cj.jdbc.Driver",
                "url", "jdbc:mysql://192.168.137.110/mydb?serverTimezone=EST",
                "username", "root",
                "password", "root",
                "initialSize", "5");
        return DruidDataSourceFactory.createDataSource(map);
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {   // 会从容器中通过类型找DataSource  Autowiring by type from bean name 'getJdbcTemplate' via factory method to bean named 'getDataSource'
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }


}

```

```java
@Test
public void testFullAnnotation() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
    StudentController studentController  = context.getBean(StudentController.class);
    studentController.batchAdd();
}
```



### （4）tx实现原理

![image-20250725101926463](https://gitee.com/yj1109/cloud-image/raw/master/img/20250725101927480.png)

![image-20250725102839205](https://gitee.com/yj1109/cloud-image/raw/master/img/20250725102840027.png)

![image-20250725103109576](https://gitee.com/yj1109/cloud-image/raw/master/img/20250725103110754.png)

> **AOP动态代理，通过TransactionInterceptor实现事务增强**

![image-20250725104919704](https://gitee.com/yj1109/cloud-image/raw/master/img/20250725104920394.png)



### （5）事务失效的情景

1. **方法非 `public`**：
   Spring AOP 代理无法为 `private`、`protected` 或包级方法创建事务代理。
2. **自调用（同一个类内调用）**：
   类内部方法A直接调用带 `@Transactional` 的方法B，会绕过代理，导致事务失效。（需通过代理对象调用）
3. **异常类型不正确**：
   默认只对 `RuntimeException` 和 `Error` 回滚。若抛出**检查型异常（如 `Exception`）** 或**吞掉异常**（`catch` 后不抛出/不手动回滚），事务不会回滚。
4. **未配置事务管理器**：
   缺少 `PlatformTransactionManager` Bean（如 `DataSourceTransactionManager`），事务无法启动。
5. **数据库引擎不支持**：
   使用的存储引擎（如 MySQL 的 **MyISAM**）不支持事务。
6. **传播行为设置不当**：
   例如：`REQUIRES_NEW` 的方法在外部无事务时调用有效，但在外部有事务时挂起外部事务；`NOT_SUPPORTED` 会挂起当前事务。
7. **`rollbackFor` 未指定检查异常**：
   如需对自定义检查异常回滚，必须显式配置 `@Transactional(rollbackFor = MyCheckedException.class)`。

> 1）方法不是 `public`；2）类内部自调用；3）抛出或捕获了非 `RuntimeException` 的异常；4）没配事务管理器；5）数据库引擎不支持（如 MyISAM）；6）传播行为配置问题；7）检查异常未指定 `rollbackFor`。”



#  七、SpringMVC

>  构建 **Web 应用程序**的 **MVC (模型-视图-控制器) 框架**，Spring的一个重要模块

> https://www.bilibili.com/video/BV1Ry4y1574R/?spm_id_from=333.788.comment.all.click&vd_source=b23569b676ce26126febad3c290b16e8



## 1. 初始化简单Spring MVC项目

> Tomcat10  + JDK17 + Spring6 + Thymeleaf

https://blog.csdn.net/weixin_44588243/article/details/149440724?sharetype=blogdetail&sharerId=149440724&sharerefer=PC&sharesource=weixin_44588243&spm=1011.2480.3001.8118



## 2. @RequestMapping的使用

```java
package com.ityj.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    // method is GET, and must contains parameter name=Jack
    @RequestMapping(value = "/hello", method = RequestMethod.GET, params = {"name=Jack"})
    public String hello() {
        System.out.println("hello");
        return "hello";
    }

    // ? 代表任意一个字符  特殊字符不行 ? / :都不行
//    @GetMapping(value = "/a?a")
    // * 代表0个或多个
//    @GetMapping(value = "/a*a")
    // **  表示任意的一层或多层目录  只能放在最后
    @GetMapping(value = "/asd/**")
    public String ant() {
        System.out.println("ant");
        return "hello";
    }
}
```



## 3. 支持路径占位符

```java
@GetMapping(value = "/hello/{id}/{name}")
public String pathVariable(@PathVariable(value = "id") String id, @PathVariable("name") String name) {
    log.info("你好：id={}, name={}", id, name);
    return "hello";
}
```





## 4. SpringMVC请求参数获取

### （1）原生的Servlet

```java
// 原始Servlet获取参数
    @GetMapping(value = "/testServlet")
    public String testServlet(HttpServletRequest  request) {
        String username = request.getParameter("username");
        System.out.println("username:"+username);
        return "hello";
    }
```

```html
<a th:href="@{/param/testServlet?username='Jack'}">测试ServletApi获取参数</a>
```

### (2) 通过控制器的形参获取参数 @RequestParam

![image-20250718133603731](https://gitee.com/yj1109/cloud-image/raw/master/img/20250718133603971.png)

```java
@GetMapping(value = "/testParam")
public String testParam(@RequestParam(value = "username", defaultValue = "default", required = true) String username, @RequestParam("hobby") List<String> hobby) {
    System.out.println("username = " + username);
    System.out.println("hobby = " + hobby);
    return "hello";
}
```

```html
<br/>
<form th:action="@{/param/testParam}" th:method="get">
    name: <input type="text" name="username">
    hobby:<input type="checkbox" name = "hobby" value="1">唱
    <input type="checkbox" name = "hobby" value="2">跳
    <input type="submit" value="提交">
</form>
```

### （3）@RequestHeader("Host") String host

### （4）@CookieValue(value = "JSESSIONID") String jsessionId

### （5）通过POJO自动映射

```java
@GetMapping(value = "/testParam")
public String testParam(@RequestParam(value = "username", defaultValue = "default", required = true) String username,
                        @RequestParam("hobby") List<String> hobby,
                        @RequestHeader("Host") String host,
                        @CookieValue(value = "JSESSIONID") String jsessionId,
                        User user) {
    System.out.println("username = " + username);
    System.out.println("hobby = " + hobby);
    System.out.println("host = " + host);
    System.out.println("jsessionId = " + jsessionId);
    System.out.println("user = " + user);
    return "hello";
}
```

## 5. 字符过滤器解决中文乱码

```xml
<filter>
  <filter-name>characterEncodingFilter</filter-name>
  <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
  <init-param>
    <param-name>encoding</param-name>
    <param-value>UTF-8</param-value>
  </init-param>
</filter>
<filter-mapping>
  <filter-name>characterEncodingFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```





## 6. SpringMVC 处理作用域

### （1）request 请求域

#### 1.1 原生Servlet API  向域对象共享数据

```java
package com.ityj.springmvc.controller;


import com.ityj.springmvc.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/scope")
public class ScopeController {

    // 原始Servlet获取参数
    @GetMapping(value = "/requestApi")
    public String testServlet(HttpServletRequest  request) {
        request.setAttribute("money", "333");
        return "hello";
    }

}
```

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>
Hello

money...<b th:text="${money}"></b>
</body>
</html>
```



#### 1.2 ModelAndView向域对象共享数据

```java
@GetMapping(value = "/testModelAndView")
public ModelAndView testModelAndView() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("hello");
    mv.addObject("money_mv", "888");

    return mv;
}
```



#### 1.3 Model向域对象共享数据

```java
@GetMapping(value = "/testModel")
public String testModel(Model model) {
   model.addAttribute("money_model", "009");

    return "hello";
}
```



#### 1.4 Map向域对象共享数据

```java
  @GetMapping(value = "/testModel")
    public String testModel(Model model) {
        System.out.println(model.getClass().getName());  // org.springframework.validation.support.BindingAwareModelMap
       model.addAttribute("money_model", "009");
        return "hello";
    }

```

#### 1.5 ModelMap向域对象共享数据

```java
@GetMapping(value = "/testModelMap")
    public String testModelMap(ModelMap modelMap) {
        System.out.println(modelMap.getClass().getName());  // org.springframework.validation.support.BindingAwareModelMap
        modelMap.addAttribute("money_modelMap", "1234");
        return "hello";
    }
```



### （2）Session域

```java
@GetMapping(value = "/testSessionScope")
public String testSessionScope(HttpSession  session) {
    session.setAttribute("sessionScope", "sessionScope");
    return "hello";
}
```

```html
msg from sessionScope...<b th:text="${session.sessionScope}"></b>  <br/>
```



### （3）ServletContext上下对象（域对象）

```java
@GetMapping(value = "/testServletContextScope")
public String testServletContextScope(HttpSession  session) {
    ServletContext app = session.getServletContext();
    app.setAttribute("testServletContextScope", "testServletContextScope");
    return "hello";
}
```

```html
msg from ServletContextScope...<b th:text="${application.testServletContextScope}"></b>  <br/>
```

> Thymeleaf：自动解析 ${application.key} 为 ServletContext 中的属性。 



## 7. 视图View

### （1）ThymeleafViewResolver

```java
@RequestMapping(value = "/thymeleaf")
public String thymeleaf() {
    System.out.println("thymeleaf");
    return "hello";   // ThymeleafViewResolver
}
```



### （2）InternalResourceView

```java
// 请求转发
@RequestMapping(value = "/testForward")
public String testForward() {
    System.out.println("testForward");
    return "forward:/view/thymeleaf";   // InternalResourceView
}
```



### （3）RedirectView

```java
@RequestMapping(value = "/testRedirect")
public String testRedirect() {
    System.out.println("testRedirect");
    return "redirect:/view/testForward";   // RedirectView
}
```

![image-20250718164605603](https://gitee.com/yj1109/cloud-image/raw/master/img/20250718164605895.png)





## 8. RestFul

### （1）基本代码

```java
package com.ityj.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestFulController {

    @GetMapping("/user/{id}")
    public String query(@PathVariable("id") String id) {
        System.out.println("query id is: " + id);
        return "success";
    }

    @PostMapping("/user")
    public String add(@RequestParam("id") String id, @RequestParam("username") String username) {
        System.out.println("add id is: " + id + ". name is : " + username);
        return "success";
    }

    @PutMapping("/user")
    public String update(@RequestParam("id") String id, @RequestParam("newName") String newName) {
        System.out.println("update id is: " + id + ". newName is : " + newName);
        return "success";
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") String id) {
        System.out.println("delete id is: " + id);
        return "success";
    }



}

```

![image-20250722093135477](https://gitee.com/yj1109/cloud-image/raw/master/img/20250722093135990.png)



### （2）HiddenHttpMethodFilter允许前端发送PUT/DELETE请求

#### 1.1 配置filter

```xml
<filter>
  <filter-name>hiddenHttpMethodFilter</filter-name>
  <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>


<filter-mapping>
  <filter-name>hiddenHttpMethodFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```

#### 1.2 前端配置

* 以post方式提交.  method = post

* 添加一个隐藏的parameter :   _method=PUT/DELETE

```html
提交PUT请求：
<form th:action="@{/user}" th:method="post">
    <input type="hidden" name="_method" value="PUT">
    id: <input type="text" name="id"/> <br/>
    newName: <input type="text" name="newName"/> <br/>
    <input type="submit">
</form>
```

![image-20250722100206309](https://gitee.com/yj1109/cloud-image/raw/master/img/20250722100206762.png)

### （3）开启静态资源访问

DispatcherServlet都会去找对应的Handler,默认不支持静态资源访问。

访问`http://localhost:8080/spring_mvc/pages/hello.js`会404

通过配置可以解决

```xml
<!--开启静态资源的访问  - defaultServlet-->
<mvc:default-servlet-handler/>
```

```log
10:08:14.281 [http-nio-8080-exec-3] [DEBUG] org.springframework.web.servlet.DispatcherServlet:119 --- GET "/spring_mvc/pages/hello.js", parameters={}
10:08:14.298 [http-nio-8080-exec-3] [DEBUG] org.springframework.web.servlet.handler.SimpleUrlHandlerMapping:527 --- Mapped to org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler@6614f201
10:08:14.361 [http-nio-8080-exec-3] [DEBUG] org.springframework.web.servlet.DispatcherServlet:1128 --- Completed 200 OK

```



## 9. HttpMessageConverter

### （1）@RequestBody

```java
@PostMapping("/testRequestBody")
public String testRequestBody(@RequestBody String requestBody) {
    System.out.println("requestBody = " + requestBody);  // requestBody = id=123&newName=ssdf
    return "success";
}
```

![2742a5c1ba450a19f8ba5b6a746fbdc](https://gitee.com/yj1109/cloud-image/raw/master/img/20250724150135066.jpg)



### （2）RequestEntity<T>

```java
@GetMapping("/testRequestEntity")
public String testRequestBody(RequestEntity<String> requestEntity) {
    System.out.println("requestEntity = " + requestEntity);  // requestEntity = <GET http://localhost:8080/spring_mvc/testRequestEntity,[host:"localhost:8080", connection:"keep-alive", sec-ch-ua:""Not)A;Brand";v="8", "Chromium";v="138", "Google Chrome";v="138"", sec-ch-ua-mobile:"?0", sec-ch-ua-platform:""Windows"", upgrade-insecure-requests:"1", user-agent:"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36", accept:"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7", sec-fetch-site:"none", sec-fetch-mode:"navigate", sec-fetch-user:"?1", sec-fetch-dest:"document", accept-encoding:"gzip, deflate, br, zstd", accept-language:"zh-CN,zh;q=0.9"]>
    return "success";
}
```

### （3）@ResponseBody 返回json数据

```java
// HTTP状态 406 - 不可接收  需要引入jackson包来进行对象json转换
@GetMapping("/testResponseBody")
@ResponseBody
public User testResponseBody() {
    System.out.println("testResponseBody...");
    User user = new User();
    user.setUsername("Jack");
    user.setHobby(List.of("A", "B"));
    return user;
}
```

```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.19.0</version>
</dependency>
```

#### @RestController

```java
@Controller
@ResponseBody
public @interface RestController {}
```



### （4）ResponseEntity<T> 文件上传下载

#### 1.1 文件下载

> new ResponseEntity<>(fileInputStream.readAllBytes(), httpHeaders, HttpStatus.OK);
>
> ​    httpHeaders.add("Content-Disposition", "attachment;filename=http.png");  // 固定格式

```java
@GetMapping("/testDownload")
public ResponseEntity<byte[]> testDownload(HttpServletRequest request) throws IOException {
    System.out.println("testDownload...");
    ServletContext servletContext = request.getServletContext();
    String path = servletContext.getRealPath("/imgs/http.png");
    System.out.println("path = " + path);
    FileInputStream fileInputStream = new FileInputStream(path);

    MultiValueMap<String, String> httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Disposition", "attachment;filename=http.png");
    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(fileInputStream.readAllBytes(), httpHeaders, HttpStatus.OK);
    fileInputStream.close();
    return responseEntity;
}
```



#### 1.2 文件上传 (TODO)

```java
// 需要配置一个文件解析器StandardServletMultipartResolver(spring6)，否则空指针  
@PostMapping("/testUpload")
public String testUpload(@RequestPart("fileUpload") MultipartFile fileUpload) throws IOException {
    String originalFilename = fileUpload.getOriginalFilename();
    fileUpload.transferTo(new File(originalFilename));
    return "success";
}
```

```xml
<bean id="multipartResolver"  class="org.springframework.web.multipart.support.StandardServletMultipartResolver"/>
```

```html
测试 文件上传：
<form th:action="@{/testUpload}" method="post" enctype="multipart/form-data">
    请选择文件: <input type="file" name="fileUpload"/> <br/>
    <input type="submit" value="上传">
</form>
```



## 10. 拦截器 implements HandlerInterceptor

```java
package com.ityj.springmvc.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class FirstInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("FirstInterceptor.preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("FirstInterceptor.postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("FirstInterceptor.afterCompletion");
        }
}
```

```xml
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/**"/>
        <ref bean="firstInterceptor"/>
    </mvc:interceptor>
    <mvc:interceptor>
        <mvc:mapping path="/**"/>
        <ref bean="secondInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
```



#### 执行顺序：

源码：

```java
if (!mappedHandler.applyPreHandle(processedRequest, response)) {  
    return;
}

	// mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

mappedHandler.applyPostHandle(processedRequest, response, mv);

mappedHandler.triggerAfterCompletion(request, response, (Exception)null);
```

> 正常： 
>
> a b c三个拦截器
>
> a-pre
>
> b-pre
>
> c-pre
>
> c-post
>
> b-post
>
> a-post
>
> c-after
>
> b-after
>
> a-after



> a b c 三个拦截器，假如c的preHandle返回回了false
>
> a-pre
>
> b-pre
>
> c-pre
>
> b-after
>
> a-after



## 11. 异常处理ExceptionHandler

```java
package com.ityj.springmvc.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler({NullPointerException.class})
    public String fileNotFound(Model model, Exception e) {
        System.out.println("fileNotFound");
        model.addAttribute("errMsg", e);
        return "error";
    }


}
```

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Error page</title>
</head>
<body>

Error : <b th:text="${errMsg}"></b>  <br/>

</body>
</html>
```

```java
@GetMapping("/err")
public String errorTest() {
    String string = null;
    int res = string.length();
    return "index";
}
```



## 12. CORS跨域问题

![5104c459217bab7c6870cb81bfd3d9e](https://gitee.com/yj1109/cloud-image/raw/master/img/20250724150251154.jpg)



## 13. DispatcherServlet处理流程

> https://www.bilibili.com/video/BV14WtLeDEit?spm_id_from=333.788.videopod.episodes&vd_source=b23569b676ce26126febad3c290b16e8&p=133

![image-20250724153457429](https://gitee.com/yj1109/cloud-image/raw/master/img/20250724153458173.png)

## ![](https://gitee.com/yj1109/cloud-image/raw/master/img/20250724153945571.png)14. MVC 完全配置化

```java
AbstractAnnotationConfigDispatcherServletInitializer
```



# 八、MyBatis

> https://www.bilibili.com/video/BV1VP4y1c7j7/?spm_id_from=333.788.comment.all.click&vd_source=b23569b676ce26126febad3c290b16e8

## 1. 导入配置，初始化项目



> org.apache.ibatis.executor.SimpleExecutor#doQuery
>
> org.apache.ibatis.executor.statement.PreparedStatementHandler#query

#### （1）pom

```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>1.18.38</version>
</dependency>

<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <version>8.0.22</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>druid</artifactId>
  <version>1.2.8</version>
</dependency>

<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>3.5.2</version>
</dependency>

<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-webmvc</artifactId>
  <version>6.0.2</version>
</dependency>
```



#### （2） [mybatis-config.xml](..\..\..\..\..\workspace-latest\java-base-learning-20250625\mybatis\src\main\resources\mybatis-config.xml) 

```peoperties
jdbc.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://192.168.137.110/mydb?serverTimezone=EST
jdbc.username=root
jdbc.password=root
jdbc.initialSize=5
```

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--1. 引入外部的配置-->
    <properties resource="jdbc.properties">
        <!--自定义属性，优先级低于db.properties-->
    </properties>


    <!--5、映射实体类对象-->
    <typeAliases>
        <!--默认映射com.ityj.entity目录下的所有实体类。-->
        <!--mybatis的xml中对应的配置文件大小写都可以。没有限制-->
        <package name="com.ityj.mybatis.entity"/>
    </typeAliases>

    <!--2. 配置数据库资源-->
    <environments default="dev">
        <environment id="dev">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driverClassName}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>

    <!--3. 映射mapper-->
    <mappers>
        <!--使用mapper接口类路径-->
        <!--注意：
                1. 此种方法要求mapper接口名称和mapper映射文件名称相同，且放在同一个目录中。
                2. 而如果放入mapper.xml 如果放入java接口同目录下，编译时又不会进行编译，需要手动在pom.xml文件中配置相关的路径指定编译。否则会报错
                3. xml和接口名称要保持一致，否则会报错
                org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.ityj.mybatis.mapper.AccountMapper.getAccountById
        -->
        <package name="com.ityj.mybatis.mapper"/>
    </mappers>

</configuration>
```

####  (3) entity/mapper/mapper.xml

```java
package com.ityj.mybatis.entity;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Data
@ToString
public class Student {
    private String name;
    private int age;
    private String gender;
    private Date birthday;
    private double height;
}
```

```java
package com.ityj.mybatis.mapper;

import com.ityj.mybatis.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentMapper {

    int insert(Student student);
    List<Student> queryAllStudent();
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.ityj.mybatis.mapper.StudentMapper">

    <insert id="insert">
        insert into student (name, age, gender, birthday) values (#{name}, #{age}, #{gender}, #{birthday})
    </insert>

    <select id="queryAllStudent" resultType="com.ityj.mybatis.entity.Student">
        select * from student
    </select>

</mapper>
```

#### （4）test

```java
package com.ityj.mybatis.mapper;


import com.ityj.mybatis.entity.Student;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

public class StudentMapperTest {

    @Test
    public void insert() throws Exception {
        Student student = new Student();
        student.setAge(33);
        student.setHeight(182);
        student.setName("Jack2");
        student.setGender("男");
        student.setBirthday(new Date(System.currentTimeMillis()));

        File file = ResourceUtils.getFile("classpath:mybatis-config.xml");
        FileInputStream fileInputStream = new FileInputStream(file);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(fileInputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true); // true表示自动提交
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        int insert = studentMapper.insert(student);
        System.out.println("insert = " + insert);
    }

    @Test
    public void queryAllStudent() throws Exception {
        File file = ResourceUtils.getFile("classpath:mybatis-config.xml");
        FileInputStream fileInputStream = new FileInputStream(file);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(fileInputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.queryAllStudent();
        System.out.println("students = " + students);

        List<Student> students2 = studentMapper.queryAllStudent();
        System.out.println("students2 = " + students2);
    }


}
```



## 2. 基本功能

### （1）typeAliases

> 映射对应的实体类，可以在对应的mapper.xml中省略全类名

```xml
<typeAliases>
    <!--默认映射com.ityj.entity目录下的所有实体类。-->
    <!--mybatis的xml中对应的配置文件大小写都可以。没有限制-->
    <package name="com.ityj.mybatis.entity"/>
</typeAliases>
```

```xml

    <select id="queryAllStudent" resultType="STudent">  /*忽略大小写*/
        select * from student
    </select>
```



mybatis默认有很多映射

> https://mybatis.org/mybatis-3/configuration.html#typeHandlers

![image-20250723095333084](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723095333555.png)





### （2）xml获取参数

#### 1.1 ${}  字符串拼接

字符串拼接， 需要手动加''  不推荐，会有sql注入问题。 可以传入table name

#### 1.2 #{}  占位符

相当于处理preparestatement的?，能解决sql注入问题

#### 1.3 #{name}/${name}占位符里的值是怎么取的

```java
List<Student> queryByName(String name, int age);
```

```xml
<select id="queryByName" resultType="student">
	select * from student where name = #{name} and age = #{age}
</select>
```

error:

**Cause: org.apache.ibatis.binding.BindingException: Parameter 'name' not found. Available parameters are [arg1, arg0, param1, param2]**

默认参数是两套： arg[index]   param[i + 1] 存在map中。

源码：org.apache.ibatis.binding.MapperMethod#execute

可以在interface中，指定map的名字， **@Param**解决

```java
List<Student> queryByName(@Param("name") String name, @Param("age") int age);
```

![image-20250723101328075](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723101328546.png)



## 3. sql查询

### （1） 模糊查询

```xml
<select id="queryLikeName" resultType="student">
    select * from student where name like "%"#{name}"%"
</select>

10:16:45.792 [main] [DEBUG] com.ityj.mybatis.mapper.StudentMapper.queryLikeName:143 --- ==>  Preparing: select * from student where name like "%"?"%" 
10:16:45.849 [main] [DEBUG] com.ityj.mybatis.mapper.StudentMapper.queryLikeName:143 --- ==> Parameters: Jack(String)
```

### （2）表名要用${}传递

### （3） 解决字段名和属性名不一致

#### 1.1 别名

#### 1.2 下划线映射为驼峰规则

```xml
<settings>
    <setting name="mapUnderscoreToCamelCase" value="true"/>
</settings>
```

#### 1.3 resultMap

```xml
<select id="queryLikeName" resultMap="studentNameMap">
    select * from student where name like "%"#{name}"%"
</select>


<resultMap id="studentNameMap" type="student">
    <!--id 主键映射-->
    <id property="id" column="id"></id>
    <!--result 普通字段-->
    <result property="name" column="name"></result>
</resultMap>
```



## 4. 动态sql

### （1）if

### （2） foreach

### （3）choose, when, otherwise

> https://mybatis.org/mybatis-3/dynamic-sql.html

```xml
<select id="queryByIDList" resultType="student">
    select * from student
    <if test="ids != null">
        <where>
            <foreach item="id" collection="ids"
                     open="ID in (" separator="," close=")">
                #{id}
            </foreach>
        </where>
    </if>
</select>
```



## 5. mybatis缓存

![image-20250724163002772](https://gitee.com/yj1109/cloud-image/raw/master/img/20250724163003449.png)![image-20250724163055986](https://gitee.com/yj1109/cloud-image/raw/master/img/20250724163056615.png)

![image-20250724163150730](https://gitee.com/yj1109/cloud-image/raw/master/img/20250724163151304.png)

### （1）一级缓存

#### 1.1 概念

> 默认开启， SqlSession级别。（事务级别）



#### 1.2 失效

* 不同SqlSession

* 同一个sqlSession中出现增删改

* 查询不同条件

* clearCache()

* ```
  flushCache="true"
  ```



#### 1.3 问题

（1）如果外部删除了一条数据，一级缓存不失效。导致查到的还是之前的数据。





### （2）二级缓存

> 

![image-20250723105456191](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723105456916.png)

![image-20250724163445512](https://gitee.com/yj1109/cloud-image/raw/master/img/20250724163446568.png)

# 九、SSM

> https://www.bilibili.com/video/BV14WtLeDEit/?spm_id_from=333.1391.0.0&p=34

## 1. 容器

### （1）IOC 和DI

![image-20250723125629833](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723125630129.png)

### （2）组件的注册

![image-20250723124527559](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723124527820.png)

### （3）bean生命周期

> Student无参构造。。。
> set方法执行了。。。
> postProcessBeforeInitialization...student
> @PostConstruct
> InitializingBean.afterPropertiesSet...
> Student @Bean initMethod。。。
> postProcessAfterInitialization...student
>
> @PreDestroy
> DisposableBean...destroy
> Student @Bean destroyMethod。。。



![image-20250723124212966](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723124213280.png)

@AutoWirede的实现原理是

![image-20250723124239867](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723124240120.png)

### （4）getBean()



![image-20250723143036789](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723143037117.png)

![image-20250723143011595](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723143011929.png)



### （5）IOC容器初始化过程

> https://www.bilibili.com/video/BV14WtLeDEit?spm_id_from=333.788.videopod.episodes&vd_source=b23569b676ce26126febad3c290b16e8&p=83

```java
入口：
org.springframework.context.support.AbstractApplicationContext#refresh
    
初始化自己定义的bean
org.springframework.context.support.AbstractApplicationContext#finishBeanFactoryInitialization
    1. org.springframework.beans.factory.support.DefaultListableBeanFactory#preInstantiateSingletons
    	2. org.springframework.beans.factory.support.DefaultListableBeanFactory#preInstantiateSingleton
    		3. org.springframework.beans.factory.support.AbstractBeanFactory#doGetBean
    			4. org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#getSingleton(java.lang.String, org.springframework.beans.factory.ObjectFactory<?>)
    			org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])
    
    
    
    org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#initializeBean(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)
    
    
    -- 			wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
			invokeInitMethods(beanName, wrappedBean, mbd);
			wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);

```





![](https://gitee.com/yj1109/cloud-image/raw/master/img/20250724114504959.png)



## 2. AOP

>  Aspect Oriented Programing

![image-20250723134436076](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723134436378.png)



![image-20250723142106954](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723142107256.png)

### （1）jdk动态代理

```java
public static Object getProxyInstance(Object obj) {
    return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("DynamicProxy.before..." + method.getName());
            Object invoke = method.invoke(obj, args);
            System.out.println("DynamicProxy.after..." + method.getName());
            return invoke;
        }
    });
}
```

### （2）AOP

```java
package com.ityj.ssm.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
public class MyLogAspect {

    @Pointcut("execution(public * com.ityj.ssm.controller.HelloController.*(..))")
    private void pt() {
    }

    @Before(value = "pt()")
    public void before(JoinPoint joinPoint) {
        System.out.println("MyLogAspect.before...");
    }

    @After(value = "pt()")
    public void after(JoinPoint joinPoint) {
        System.out.println("MyLogAspect.after...");
    }

    @AfterReturning(value = "pt()", returning = "res")
    public Object afterReturning(JoinPoint joinPoint, Object res) {
        System.out.println("MyLogAspect.afterReturning...");
        return res;
    }

    @AfterThrowing(value = "pt()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("MyLogAspect.afterThrowing..." + ex);
    }

    @Around(value = "pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object proceed = null;
        try {
            System.out.println("@Around环绕通知 前...");
            proceed = joinPoint.proceed();
            System.out.println("@Around环绕通知 afterReturning...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("@Around环绕通知 catch 异常...");
        } finally {
            System.out.println("@Around环绕通知 后...");
        }
        return proceed;
    }
}
```

### （3）多切面的执行顺序

![image-20250723135221522](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723135221843.png)



环绕通知一定要throw exception，不要吃掉。

![image-20250723142440686](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723142441000.png)



## 3. 事务

```
@EnableTransactionManagement // 开启事务管理
@Transactional
```

### （1）@Transactional

#### 1.1  transactionManager

**控制提交和回滚**

> 默认org.springframework.jdbc.support.JdbcTransactionManager@5b3518e1



#### 1.2 TransactionInterceptor

**控制何时提交回滚**

> org.springframework.transaction.interceptor.TransactionAspectSupport#invokeWithinTransaction



#### 1.3 隔离级别



![image-20250723160031702](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723160032104.png)



#### 1.4 传播行为

![image-20250723161152796](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723161153168.png)

![image-20250723162325739](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723162326068.png)

![image-20250723162447079](https://gitee.com/yj1109/cloud-image/raw/master/img/20250723162501697.png)



**总结关键点：**

| 场景                                        | 方法 B 的 `@Transactional` 是否生效 | 方法 A 和 B 是否在同一物理事务 | 默认传播行为 (`REQUIRED`) 下是否一起回滚 | `REQUIRES_NEW` 下是否一起回滚                   |
| :------------------------------------------ | :---------------------------------- | :----------------------------- | :--------------------------------------- | :---------------------------------------------- |
| **A 内部 `this.B()` (自调用)**              | **❌ 失效**                          | **✅ 是** (都在 A 的事务中)     | **✅ 是** (同一事务必然同回滚)            | **不适用** (B 的注解失效，传播行为设置无效)     |
| **A 调用另一个 Bean 的 B (`serviceB.B()`)** | **✅ 生效**                          | **✅ 是** (B 加入 A 的事务)     | **✅ 是** (同一事务必然同回滚)            | **❌ 不一定** (B 在独立事务，A/B 可单独回滚提交) |

**核心结论：**

- **自调用导致内部 `@Transactional` 失效：** 同一个类内部方法调用事务方法，事务注解失效，被调方法运行在调用方的事务（如果存在）或非事务环境中。**这种情况下两个方法都在一个物理事务里，必然一起成功或一起回滚，但这不是内部方法注解生效的结果，而是自调用的副作用。**
- **跨 Bean 调用传播行为生效：** 通过代理正确调用另一个 Bean 的事务方法时，传播行为生效。默认的 `REQUIRED` 会让它们加入同一个事务，一起回滚。使用 `REQUIRES_NEW` 则创建独立事务，回滚互不影响（除非异常传播）。

**强烈建议：**

- **避免在同一个类内部进行事务方法的自调用。** 将需要事务的方法拆分到不同的 Service 层 Bean 中是清晰且符合 Spring 代理机制的最佳实践。
- 理解不同传播行为的语义至关重要，尤其是在跨 Bean 调用时。



## 4. Knife4j

> https://doc.xiaominfo.com/docs/quick-start

```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-openapi3-jakarta-spring-boot-starter</artifactId>
    <version>4.4.0</version>
</dependency>

```

```yml
# springdoc-openapi项目配置
springdoc:
  swagger-ui:
    path: /swagger-ui.html
    tags-sorter: alpha
    operations-sorter: alpha
  api-docs:
    path: /v3/api-docs
  group-configs:
    - group: 'default'
      paths-to-match: '/**'
      packages-to-scan: com.ityj.ssm.controller
# knife4j的增强配置，不需要增强可以不配
knife4j:
  enable: true
  setting:
    language: zh_cn
```





# 十、SpringBoot

> 用于**快速创建**、**配置**和**运行**独立的、生产级的基于 Spring 的应用程序的**框架和工具集**

> https://www.bilibili.com/video/BV1Es4y1q7Bf?spm_id_from=333.788.player.switch&vd_source=b23569b676ce26126febad3c290b16e8&p=25



## 1. 自动配置

> https://www.bilibili.com/video/BV14WtLeDEit?spm_id_from=333.788.videopod.episodes&vd_source=b23569b676ce26126febad3c290b16e8&p=177

### （1）概念理解

![image-20250725153248008](https://gitee.com/yj1109/cloud-image/raw/master/img/20250725153248479.png)

![image-20250725153304352](https://gitee.com/yj1109/cloud-image/raw/master/img/20250725153304720.png)



### （2）最佳实践 - redis

#### 1. 引入starter

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

#### 2. 启动了自动配置RedisAutoConfiguration

```java
@EnableConfigurationProperties(RedisProperties.class)
@Import({ LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class })
public class RedisAutoConfiguration {
    ...
        
    @Bean
	@ConditionalOnMissingBean
	@ConditionalOnSingleCandidate(RedisConnectionFactory.class)
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		return new StringRedisTemplate(redisConnectionFactory);
	}
    ...
}


@ConfigurationProperties("spring.data.redis")
public class RedisProperties {}
```

* @EnableConfigurationProperties(RedisProperties.class)  -- 绑定了配置类RedisProperties并将RedisProperties放入容器
* @Import 导入了另外两个配置类
* 自动加载了stringRedisTemplate

#### 3. 用stringRedisTemplate进行测试

##### 1.1 配置redis host

```yml
spring:
  data:
    redis:
      host: 192.168.137.110
      port: 6379
```

##### 1.2 测试

```java
@Autowired
private StringRedisTemplate stringRedisTemplate;

@Test
void testRedis() {
    ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
    stringStringValueOperations.set("testKey", "Jack");


    String res = stringStringValueOperations.get("testKey");

    log.info("complete: " + res);
}
```

![image-20250726135915177](https://gitee.com/yj1109/cloud-image/raw/master/img/20250726135915451.png)





## 2. Web开发

### （1）引入starter

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### （2）自动配置了WebMvcAutoConfiguration

![image-20250726141149278](https://gitee.com/yj1109/cloud-image/raw/master/img/20250726141149535.png)

### （3）静态资源

> org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#addResourceHandlers

```java
private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
       "classpath:/resources/", "classpath:/static/", "classpath:/public/" };
```

![image-20250726152627243](https://gitee.com/yj1109/cloud-image/raw/master/img/20250726152627495.png)

### （4）欢迎页

> org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.EnableWebMvcConfiguration#welcomePageHandlerMapping

访问首页，默认去静态资源目录下找index.html

> curl http://localhost:8080/



### （5）favicon 图标

是浏览器的行为， 每次浏览器发请求都会带一个/favicon.ico请求， 所以在静态目录下放一个对应的静态文件即可。

![image-20250726154302155](https://gitee.com/yj1109/cloud-image/raw/master/img/20250726154302466.png)



### （6）自定义静态资源规则

#### 1.配置文件

```
通过  http://localhost:8080/pages/hello.html   找到静态文件 static/index.html 
```

```yml
spring:
  mvc:
    static-path-pattern: /pages/**
```

#### 2. 代码配置类

实现WebMvcConfigurer，添加功能

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry); // 保留原有配置
        registry.addResourceHandler("/pages/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(12);
    }
}
```

* 添加了一个新的静态映射， 访问/pages/**会从classpath:/static/找文件
* 保留原有的静态映射
* 不能添加注解@EnableWebMvc， 会导致原有的所有springboot对MVC失效

```java
package com.ityj.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableWebMvc  会关闭mvc的所有自动配置。
// @EnableWebMvc  -> DelegatingWebMvcConfiguration -> 实例化了WebMvcConfigurationSupport。
// @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
//public class WebMvcAutoConfiguration {}  需要没有WebMvcConfigurationSupport才开始实例化。。
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry); // 保留原有配置
        registry.addResourceHandler("/pages/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(12);
    }
}
```



#### 3. 添加一个bean: WebMvcConfigurer

```java
@Configuration
public class MyWebMvcConfigurer {

    // org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration.setConfigurers
    // public void setConfigurers(List<WebMvcConfigurer> configurers) {} 会把所有的WebMvcConfigurer bean都注入
    @Bean
    public WebMvcConfigurer webMvcConfigurer () {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/pages/**")
                        .addResourceLocations("classpath:/static/")
                        .setCachePeriod(12);
            }
        };
        return webMvcConfigurer;
    };

}
```

![image-20250726163432562](https://gitee.com/yj1109/cloud-image/raw/master/img/20250726163432824.png)

### （7）内容协商

> 服务器可以根据不同的请求参数（header or parameter）返回不同类型的数据(xml/json)

#### 1.  根据请求头的Accept值

code:

```java
@GetMapping("/student")
public Student testContentNegotiation(@RequestHeader("Accept") String accept) {
    System.out.println("testContentNegotiation .... accept : " + accept);
    Student student = new Student();
    student.setAge(33);
    student.setHeight(182);
    student.setName("Jack2");
    student.setGender("男");
    student.setBirthday(new Date(System.currentTimeMillis()));
    return student;
}
```



springboot默认导入了jackson-json包，想要返回xml类型数据，需要导入下面的pom以及在实体类加一个注解

```xml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

```java
@JacksonXmlRootElement
```



测试：

![image-20250726171811842](https://gitee.com/yj1109/cloud-image/raw/master/img/20250726171812132.png)

![image-20250726171832637](https://gitee.com/yj1109/cloud-image/raw/master/img/20250726171832944.png)



#### 2. 根据请求uri加一个参数

需要做以下配置

```yml
spring:
  mvc:
    contentnegotiation:
      favor-parameter: true
```



测试：

![image-20250726172110521](https://gitee.com/yj1109/cloud-image/raw/master/img/20250726172110816.png)

![image-20250726172127488](https://gitee.com/yj1109/cloud-image/raw/master/img/20250726172127770.png)



### （8）内容协商原理 - HttpMessageConverter

##### 1.1 @ResponseBody

> ```java
> ha.handle()
>     org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter#invokeHandlerMethod
>     	org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite#handleReturnValue
>     		org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#handleReturnValue
>     			MappingJackson2HttpMessageConverter
> ```

![ffdd16a864743b204cf70bc4316136a](https://gitee.com/yj1109/cloud-image/raw/master/img/20250727174829859.jpg)

![image-20250727174849297](https://gitee.com/yj1109/cloud-image/raw/master/img/20250727174849770.png)

![image-20250727175108895](https://gitee.com/yj1109/cloud-image/raw/master/img/20250727175109208.png)

![image-20250727175132333](https://gitee.com/yj1109/cloud-image/raw/master/img/20250727175132605.png)

### （9）自定义内容协商返回

#### 1.1 添加依赖支持yaml格式

```yaml
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-yaml</artifactId>
</dependency>
```

![image-20250727181716783](https://gitee.com/yj1109/cloud-image/raw/master/img/20250727181717126.png)

![image-20250727181918253](https://gitee.com/yj1109/cloud-image/raw/master/img/20250727181918585.png)



#### 1.2 自定义yml格式

##### 1.1.1 添加配置

```yml
spring:
  mvc:
    contentnegotiation:
      media-types:
        yml: application/yml
```



##### 1.1.2 添加 MyYmlHttpMessageConverter

```java
public class MyYmlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    private static final MediaType APPLICATION_YML = new MediaType("application", "yml");

    private ObjectMapper objectMapper;

    public MyYmlHttpMessageConverter() {
       // 告诉springboot 当前的MessageConverter支持哪个MediaType
       super(APPLICATION_YML);
       YAMLFactory yamlFactory = new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
       this.objectMapper = new ObjectMapper(yamlFactory);
    }

    @Override
    public boolean supports(Class<?> clazz) {
       return true;
    }

    @Override  // 处理@RequestBody
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
       return null;
    }

    @Override// 处理@ResponseBody
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
       this.objectMapper.writeValue(outputMessage.getBody(), o);
    }


}
```



##### 1.2.3 加入spring配置

```java
@Bean
public WebMvcConfigurer webMvcConfigurer () {
    WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {

        
         @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

           converters.add(new MyYmlHttpMessageConverter());


        }

    };
    return webMvcConfigurer;
};
```

![image-20250727181000053](https://gitee.com/yj1109/cloud-image/raw/master/img/20250727181000406.png)



##### 1.2.4 test

![image-20250727182408323](https://gitee.com/yj1109/cloud-image/raw/master/img/20250727182408636.png)





### （10）异常处理



org.apache.catalina.core.StandardWrapperValve#exception(org.apache.catalina.connector.Request, org.apache.catalina.connector.Response, java.lang.Throwable)



![image-20250730144001580](https://gitee.com/yj1109/cloud-image/raw/master/img/20250730144001992.png)





跳转到默认html白页对应代码

![image-20250727150309042](https://gitee.com/yj1109/cloud-image/raw/master/img/20250727150309362.png)



![image-20250727150101763](https://gitee.com/yj1109/cloud-image/raw/master/img/20250727150102071.png)

![image-20250727205959743](https://gitee.com/yj1109/cloud-image/raw/master/img/20250730144149528.png)



![image-20250727165111583](https://gitee.com/yj1109/cloud-image/raw/master/img/20250727165111873.png)



![image-20250727210744349](https://gitee.com/yj1109/cloud-image/raw/master/img/20250727210923725.png)





## 3. 日志

![578a4d73e65784c59879757a1e7f001](https://gitee.com/yj1109/cloud-image/raw/master/img/20250725153803835.jpg)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="false">

    <!--定义日志文件的存储地址 勿在 LogBack 的配置中使用相对路径-->
    <property name="LOG_HOME" value="/home" />

    <!--控制台日志， 控制台输出 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度,%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>

    <!--文件日志， 按照每天生成日志文件 -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件输出的文件名-->
            <FileNamePattern>${LOG_HOME}/TestWeb.log.%d{yyyy-MM-dd}.log</FileNamePattern>
            <!--日志文件保留天数-->
            <MaxHistory>30</MaxHistory>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
        <!--日志文件最大的大小-->
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>10MB</MaxFileSize>
        </triggeringPolicy>
    </appender>

    <!-- show parameters for hibernate sql 专为 Hibernate 定制 -->
    <logger name="org.hibernate.type.descriptor.sql.BasicBinder" level="TRACE" />
    <logger name="org.hibernate.type.descriptor.sql.BasicExtractor" level="DEBUG" />
    <logger name="org.hibernate.SQL" level="DEBUG" />
    <logger name="org.hibernate.engine.QueryParameters" level="DEBUG" />
    <logger name="org.hibernate.engine.query.HQLQueryPlan" level="DEBUG" />

    <!--myibatis log configure-->
    <logger name="com.apache.ibatis" level="TRACE"/>
    <logger name="java.sql.Connection" level="DEBUG"/>
    <logger name="java.sql.Statement" level="DEBUG"/>
    <logger name="java.sql.PreparedStatement" level="DEBUG"/>

    <!-- 日志输出级别 -->
    <root level="DEBUG">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="FILE"/>
    </root>
</configuration>

```







## 4. SpringBoot启动流程

![1378575362e2b7a155ec3da512fa677](https://gitee.com/yj1109/cloud-image/raw/master/img/20250725154228690.jpeg)



## 5. 自定义starter

> https://github.com/12722097458/java-base-learning-20250625/commit/30629627db2304a45f3d9b6f19a50c14320b6655

![e2de01d26944557da79640a5c87b0c2](https://gitee.com/yj1109/cloud-image/raw/master/img/20250725154205377.jpg)

​	![image-20250725162354780](https://gitee.com/yj1109/cloud-image/raw/master/img/20250725162355405.png)



## 6. SpringBoot 整合Mybatis

### （1） 最佳实战

> MybatisAutoConfiguration

#### 1.1 引入pom

注意版本兼容问题

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>3.0.5</version>
</dependency>
```



#### 1.2 改pom

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.137.110/mydb?serverTimezone=EST
    username: root
    password: root
    type: com.alibaba.druid.pool.DruidDataSource
mybatis:
  mapper-locations: classpath:/mapper/*.xml   # 配置mapper.xml的位置
  configuration:
    map-underscore-to-camel-case: true
```



#### 1.3 配置mapper接口的扫描

这样项目启动会自动扫描这个包，并创建bean. 不需要在每一个mapper标注@Mapper注解了

```java
@MapperScan(basePackages = "com.ityj.springboot.mapper") // 导入包下的所有mapper
```



#### 1.4 code

```java
package com.ityj.springboot.mapper;

import com.ityj.springboot.entity.Student;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface StudentMapper {

    List<Student> queryByName(@Param("name") String name);

}
```



#### 1.5 xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.ityj.springboot.mapper.StudentMapper">

    <select id="queryByName" resultType="com.ityj.springboot.entity.Student">
        select * from student where name = #{name}
    </select>

</mapper>
```



#### 1.6 test

```java
package com.ityj.springboot.mapper;

import com.ityj.springboot.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testQuery() {
        List<Student> students = studentMapper.queryByName("Jack");
        System.out.println("students = " + students);
    }

}
```





## 7. 远程调用(RPC)

![image-20250730150434593](https://gitee.com/yj1109/cloud-image/raw/master/img/20250730150434973.png)



![78b99c89fb107d560b683957c1891fe](https://gitee.com/yj1109/cloud-image/raw/master/img/20250730154213669.png)





### （1）WebClient

```pom
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

```java
public Mono<String> recentWeather(String city) {

    WebClient webClient = WebClient.create();
    Map<String, String> map = new HashMap<>();
    map.put("area", city);
    Mono<String> mono = webClient.get()
            .uri("https://ali-weather.showapi.com/hour24?area={area}", map)
            .header("Authorization", "APPCODE c402f4f54f874e25bf75094c2e332c56")
            .acceptCharset(Charset.defaultCharset())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(String.class);
    return mono;
}
```



### （2）Http Interface

```java
public interface WeatherInterface {

    @GetExchange(url = "/hour24")
    Mono<String> getWeather(@RequestParam("area") String city,
                            @RequestHeader("Authorization") String auth);

}
```

```java
public Mono<String> getWeather(String city) {

    WebClient webClient = WebClient.builder().baseUrl("https://ali-weather.showapi.com")
            .codecs(new Consumer<ClientCodecConfigurer>() {
                @Override
                public void accept(ClientCodecConfigurer clientCodecConfigurer) {
                    clientCodecConfigurer.defaultCodecs().maxInMemorySize(256 * 1024 * 1024);
                }
            }).build();
    HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();
    WeatherInterface weatherInterface = httpServiceProxyFactory.createClient(WeatherInterface.class);
    return weatherInterface.getWeather(city, "APPCODE c402f4f54f874e25bf75094c2e332c56");
}
```



## 8. Kafka

![image-20250730154818811](https://gitee.com/yj1109/cloud-image/raw/master/img/20250730154819300.png)

（1）Linux安装Zookeeper和kafka

> https://github.com/12722097458/java-files-repository/commit/9c3ae129fc440d17fbba1e73765263f8ec41a344



（2）SpringBoot整合Kafka

> https://github.com/12722097458/java-base-learning-20250625/commit/30ad1964cdf8aecacbab1bd8064ad1036fa8712c

#### 1. pom

```xml
 <dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```

#### 2. 配置

```yml
  kafka:
    bootstrap-servers: 192.168.137.110:9092
```



#### 3. @EnableKafka

#### 4. producer test

```java
@SpringBootTest
public class KafkaTest {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    public void testOneRecord() {
        CompletableFuture future = kafkaTemplate.send("test-topic", 1,"key", "vvv"  + System.currentTimeMillis());

        future.join();
        System.out.println("send complete!");
    }

    @Test
    public void testProducer() {
        CompletableFuture[] futures = new CompletableFuture[10000];
        for (int i = 0; i < 10000; i++) {
            futures[i] = kafkaTemplate.send("test-topic", "kkk" + i, "vvv"  + i);
        }
        CompletableFuture.allOf(futures).join();
        System.out.println("send complete!");
    }

}
```



#### 5. Consumer 监听@KafkaListener

**同一个group的消费者是竞争关系， 一条数据只能被一个人消费**

```java

@Slf4j
@Component
public class KafkaComponent {

    @KafkaListener(id = "receive1", groupId = "haha", topics = {"test-topic"})
    public void receive(ConsumerRecord consumerRecord) {
        Object key = consumerRecord.key();
        Object value = consumerRecord.value();
        log.info("receive  key :{}, value: {}", key, value);
    }

    @KafkaListener(id = "receive2", groupId = "haha", topics = {"test-topic"})
    public void receive2(ConsumerRecord consumerRecord) {
        Object key = consumerRecord.key();
        Object value = consumerRecord.value();
        log.info("receive2 key :{}, value: {}", key, value);
    }

}
```



## 11. 重点

![245b74c98535a9c3de5e3f50f607e8a](https://gitee.com/yj1109/cloud-image/raw/master/img/20250725154215976.jpg)





## 12. 特殊功能

### （1）项目启动完毕执行某个方法

#### 1.1 实现CommandLineRunner

```java
@Component
public class Start implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("项目启动完毕了 。。。。Start........");
    }
}
```



#### 1.2 实现ApplicationRunner

```java
@Component
public class StartupApplicationRunners implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("项目启动完毕了 。。。。StartupApplicationRunners.run()........");
    }
}
```



#### 1.3 实现SpringApplicationRunListener

```java
@Component
public class StartupListener implements SpringApplicationRunListener {

    // CommandLineRunners and ApplicationRunners have not been called.
    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("项目启动完毕了 。。。。SpringApplicationRunListener.started()........");
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        System.out.println("项目启动完毕了 。。。。SpringApplicationRunListener.ready()........");
    }
}
```

```spring.factories
org.springframework.boot.SpringApplicationRunListener=com.ityj.springboot.component.StartupListener
```

> spring.factories指定了listener，项目启动才会加载



#### 1.4 直接在启动方法里

```java
ConfigurableApplicationContext ioc = SpringApplication.run(Application.class, args);
System.out.println("项目启动完毕了 。。。.Application.main().... IOC准备完毕");
```



#### 1.5 BeanPostProcessor的postProcessAfterInitialization

```java
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (HelloController.class.equals(bean.getClass())) {
            // 执行方法
            System.out.println("MyBeanPostProcessor.postProcessAfterInitialization ..." + beanName);
        }

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
```

#### 1.6 测试

> BeanPostProcessor最先，因为他之后IOC容器不一定初始化好了。

MyBeanPostProcessor.postProcessAfterInitialization ...helloController



![image-20250909155044824](https://gitee.com/yj1109/cloud-image/raw/master/img/20250909155045406.png)



# 十一、Spring Cloud

> https://www.bilibili.com/video/BV1gW421P7RD/?spm_id_from=333.1391.0.0

## 1. Consul注册中心

> https://github.com/12722097458/java-base-learning-20250625/commit/3014235ca370944c10fdee86ae412af19beaf54a

### （1）Consul安装

#### 1.1 下载

> https://developer.hashicorp.com/consul/install

![image-20250829100045683](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829100046021.png)

#### 1.2 启动

```shell
consul.exe agent -server -ui -bind=127.0.0.1 -client=0.0.0.0 -bootstrap-expect  1  -data-dir ./mydata
```

#### 1.3 访问

> http://localhost:8500

![image-20250829100411480](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829100411796.png)



### （2）服务注册进Consul

#### 1.1 引入pom

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```

#### 1.2 添加yml配置

```yml
spring:
  application:
    name: cloud-consumer-order
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        service-name: ${spring.application.name}
```

#### 1.3 成功注册

![image-20250829102506950](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829102507277.png)

### （3）注册中心优势

#### 1.1 RestTemplate

> 可以通过服务名称直接调用，方便进行负载均衡

```java
private static final String PAYMENT_BASE_URL = "http://localhost:8001";
private static final String PAYMENT_BASE_URL = "http://cloud-payment-service"; //服务注册中心上的微服务名称
```



#### 1.2 便于管理服务

统一的UI可以查看对应服务的状态



## 2. Consul服务配置中心

#### 1.1 引入pom

```xml
<!--SpringCloud consul config-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-config</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```



#### 1.2 写yml

```yml
spring:
  application:
    name: cloud-payment-service
  cloud:
    consul:
      config:
        profile-separator: '-' # default value is ","，we update '-'
        format: YAML
        watch:
          wait-time: 1  # 1秒刷新1次 结合@RefreshScope使用
# config/cloud-payment-service/data   data是文件，其他的是文件夹。  UI上yml格式不要用Tab进行对其，直接空格 
# config/cloud-payment-service-dev/data   // 如果spring.profile.active=dev 会读取这个文件
# config/cloud-payment-service-prod/data
```



#### 1.3 consul编辑配置

![image-20250829103248562](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829110209757.png)



#### 1.4 controller取值

两种写法

##### 1.4.1 @Value("${consulConfig.value}" 作为局部变量

```java
@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private PayService payService;
    @Value("${server.port}")
    private String serverPort;
    @GetMapping(value = "/pay/consul/config2")
    // 作为变量，不需要spring.cloud.consul.config.watch.wait-time， 也不需要@RefreshScope
    public ResultData<String> listConfig2(@Value("${consulConfig.value}") String consulConfigName2){
        return ResultData.success("configName from cunsul: " + consulConfigName2 + ", port:" + serverPort);
    }
}
```



##### 1.4.2 @RefreshScope 写在具体bean上

```java
@Slf4j
@RestController
@RefreshScope  // 刷新consul 配置，写在具体的bean上。  spring.cloud.consul.config.watch.wait-time好像没啥用
public class PaymentController {

    @Autowired
    private PayService payService;


    @Value("${server.port}")
    private String serverPort;


    @Value("${consulConfig.value}")
    String consulConfigName;
    
    @GetMapping(value = "/pay/consul/config")    // 需要在consul配置中心添加好配置才能启动成功，否则报错
    public ResultData<String> listConfig(){
        return ResultData.success("configName from cunsul: " + consulConfigName + ", port:" + serverPort);
    }

}
```

#### 1.5 启动

> http://localhost:8001/pay/consul/config  可以实时获取consul配置中心的值



## 3. Load Balancer负载均衡

算法有 轮询，随机，地址散列，加权

### （1）使用方式

```java
@Configuration
public class MyConfig {

    @Bean
    @LoadBalanced // 默认轮询RoundRobinLoadBalancer 开启默认的负载均衡  - 本地端    nginx是服务器端
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
```

### （2）切换方式

```java
//切换负载均衡模式  -  随机
@Configuration
@LoadBalancerClient(
        //下面的value值大小写一定要和consul里面的名字一样，必须一样
        value = "cloud-payment-service",configuration = RestTemplateConfig.class)
public class RestTemplateConfig {
    @Bean
    @LoadBalanced //使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
                                                            LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);

        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }
}
```



### （3）IDEA直接复制项目，修改端口

#### 1.1 复制配置

![image-20250829101414750](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829101415065.png)



#### 1.2 添加启动参数

![image-20250829101551993](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829101552312.png)

#### 1.3 启动

![image-20250829101634880](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829101635195.png)



## 4. Open Feign 远程调用

> 替代RestTemplate，通过接口的方式可以像本地代码一样调用

> 是一个声明式的web服务客户端，只需要创建一个rest接口，并添加注解@FeignClient(value = "cloud-payment-service")

### （1）pom

```java
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```



### （2）接口

```java
@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {

    @GetMapping("/pay/getAll")
    ResultData<List<PayDTO>> queryAll();

    @GetMapping("/pay/get/{id}")
    ResultData<PayDTO> queryById(@PathVariable("id") Integer id);

    @PostMapping(value = "/pay/add")
    ResultData<String> addPay(@RequestBody Pay pay);

    @GetMapping(value = "/pay/info")
    ResultData<String> info();
}
```

### （3）特性

#### 1.1  支持负载均衡

天生自带负载均衡 - 默认集成了LoadBanlancer

![image-20250829114606740](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829114607095.png)



#### 1.2  支持sentinel和fallback

```java
@FeignClient(value = "nacos-payment-provider", fallback = PayFeignSentinelApiFallBack.class)
public interface PayFeignSentinelApi {
    @GetMapping("/pay/nacos/get/{orderNo}")
    ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}
```

```java
@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi {

    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(),"对方服务宕机或不可用，Openfeign FallBack服务降级o(╥﹏╥)o");
    }

}
```



#### 1.3 支持http请求和相应的压缩

![image-20250829114725640](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829114726001.png)

#### 1.4 超时机制

> https://docs.spring.io/spring-cloud-openfeign/reference/4.1/configprops.html

![image-20250829115646191](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829115646539.png)

```yml
spring:
    openfeign:
      client:
        config:
          default:
            #cloud-payment-service:
            #连接超时时间，为避免演示出错，讲解完本次内容后设置为20秒
            connectTimeout: 20000
            #读取超时时间，为避免演示出错，讲解完本次内容后设置为20秒
            readTimeout: 20000
```



其实payment模块接口在62秒后还是执行成功的。

![image-20250829115054612](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829115054993.png)

#### 1.5 重试机制

默认是关闭的

```java
@Configuration
public class FeignConfig {

    @Bean
    public Retryer getRetryer() {
//        return Retryer.NEVER_RETRY; //Feign默认配置是不走重试策略的

        //最大请求次数为3(1+2)，初始间隔时间为100ms，重试间最大间隔时间为1s
        return new Retryer.Default(100,1,3);
    }

    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }

}
```

![image-20250829120311210](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829120311568.png)

#### 1.6 修改默认的Http客户端

默认HttpClient

![image-20250829120201715](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829120202051.png)

修改为Apache HttpClient 5

```yml
spring:
  cloud:
    openfeign:
      httpclient:
        hc5:
          enabled: true
```



修改后日志

![image-20250829120648765](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829120649106.png)



## 5. Circuit Breaker - Resilient4j 熔断降级限流

> 出故障了“保险丝”跳闸，别把整个家给烧了

简单直接的回答是：**熔断器应该由调用者设置，用来保护自己不被慢的或被压垮的下游服务所拖垮。**

因此，在您的场景中（A -> B -> C）：

- **B 应该针对 C 设置熔断器。**
- **C 出问题返回很慢时，B 的熔断器会“断路” C。**

### （1）熔断器

#### 1.1 COUNT_BASED

最近的6次请求，异常数量大于50%即3次会打开断路器，进入熔断状态OPEN。5秒后尝试半开断路器HALF_OPEN，如果2个请求都成功就关闭断路器CLOSE，恢复正常。

```yml
resilience4j:
  circuitbreaker:
    configs:
      default:
        failureRateThreshold: 50 #设置50%的调用失败时打开断路器，超过失败请求百分⽐CircuitBreaker变为OPEN状态。
        slidingWindowType: COUNT_BASED # 滑动窗口的类型
        slidingWindowSize: 6 #滑动窗⼝的⼤⼩配置COUNT_BASED表示6个请求，配置TIME_BASED表示6秒
        minimumNumberOfCalls: 6 #断路器计算失败率或慢调用率之前所需的最小样本(每个滑动窗口周期)。如果minimumNumberOfCalls为10，则必须最少记录10个样本，然后才能计算失败率。如果只记录了9次调用，即使所有9次调用都失败，断路器也不会开启。
        automaticTransitionFromOpenToHalfOpenEnabled: true # 是否启用自动从开启状态过渡到半开状态，默认值为true。如果启用，CircuitBreaker将自动从开启状态过渡到半开状态，并允许一些请求通过以测试服务是否恢复正常
        waitDurationInOpenState: 5s #从OPEN到HALF_OPEN状态需要等待的时间
        permittedNumberOfCallsInHalfOpenState: 2 #半开状态允许的最大请求数，默认值为10。在半开状态下，CircuitBreaker将允许最多permittedNumberOfCallsInHalfOpenState个请求通过，如果其中有任何一个请求失败，CircuitBreaker将重新进入开启状态。
        recordExceptions:
          - java.lang.Exception
    instances:
      cloud-payment-service:
        baseConfig: default
```

```java
@GetMapping(value = "/feign/pay/circuit/{id}")
@CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
public String myCircuitBreaker(@PathVariable("id") Integer id) {
    return payFeignApi.myCircuit(id);
}

//myCircuitFallback就是服务降级后的兜底处理方法
public String myCircuitFallback(Integer id,Throwable t) {
    // 这里是容错处理逻辑，返回备用结果
    return "myCircuitFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~" + t.getMessage();
}
```

http://localhost/feign/pay/circuit/-4，多次失败后会进入熔断状态，在访问正确接口也一样熔断。熔断解除后恢复正常。

#### 1.2 TIME_BASED

2秒内慢查询(超过2s)超过30%进入熔断状态

```yml
# Resilience4j CircuitBreaker 按照时间：TIME_BASED 的例子
resilience4j:
  timelimiter:
    configs:
      default:
        timeout-duration: 10s #神坑的位置，timelimiter 默认限制远程1s，超于1s就超时异常，配置了降级，就走降级逻辑
  circuitbreaker:
    configs:
      default:
        failureRateThreshold: 50 #设置50%的调用失败时打开断路器，超过失败请求百分⽐CircuitBreaker变为OPEN状态。
        slowCallDurationThreshold: 2s #慢调用时间阈值，高于这个阈值的视为慢调用并增加慢调用比例。
        slowCallRateThreshold: 30 #慢调用百分比峰值，断路器把调用时间⼤于slowCallDurationThreshold，视为慢调用，当慢调用比例高于阈值，断路器打开，并开启服务降级
        slidingWindowType: TIME_BASED # 滑动窗口的类型
        slidingWindowSize: 2 #滑动窗口的大小配置，配置TIME_BASED表示2秒
        minimumNumberOfCalls: 2 #断路器计算失败率或慢调用率之前所需的最小样本(每个滑动窗口周期)。
        permittedNumberOfCallsInHalfOpenState: 2 #半开状态允许的最大请求数，默认值为10。
        waitDurationInOpenState: 5s #从OPEN到HALF_OPEN状态需要等待的时间
        recordExceptions:
          - java.lang.Exception
    instances:
      cloud-payment-service:
        baseConfig: default
```



## 6. Circuit Breaker - Bulkhead并发限制

舱壁隔离，控制并发数。  Semaphore 或 fixed thread pool

### （1）SEMAPHORE

```java
resilience4j:
  bulkhead:
    configs:
      default:
        maxConcurrentCalls: 2 # 隔离允许并发线程执行的最大数量
        maxWaitDuration: 1s # 当达到并发调用数量时，新的线程的阻塞时间，我只愿意等待1秒，过时不候进舱壁兜底fallback
    instances:
      cloud-payment-service:
        baseConfig: default
    timelimiter:
      configs:
        default:
          timeout-duration: 20s
```

```java
@GetMapping(value = "/feign/pay/bulkhead/{id}")
@Bulkhead(name = "cloud-payment-service",
        fallbackMethod = "myBulkheadFallback",
        type = Bulkhead.Type.SEMAPHORE)
public String myBulkhead(@PathVariable("id") Integer id) {
    return payFeignApi.myBulkhead(id);
}

public String myBulkheadFallback(Throwable t) {
    return "myBulkheadFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
}
```



用jmeter并发请求http://localhost/feign/pay/bulkhead/9999

此时http://localhost/feign/pay/bulkhead/1 等待1秒后也被拒绝

![image-20250829133756257](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829133756718.png)

### （2）THREADPOOL

```yml
resilience4j:
  timelimiter:
    configs:
      default:
        timeout-duration: 10s #timelimiter默认限制远程1s，超过报错不好演示效果所以加上10秒
  thread-pool-bulkhead:
    configs:
      default:
        core-thread-pool-size: 1
        max-thread-pool-size: 1
        queue-capacity: 1
    instances:
      cloud-payment-service:
        baseConfig: default
```

```java
@GetMapping(value = "/feign/pay/bulkhead_pool/{id}")
@Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadPoolFallback",type = Bulkhead.Type.THREADPOOL)
public CompletableFuture<String> myBulkheadTHREADPOOL(@PathVariable("id") Integer id)
{
    System.out.println(Thread.currentThread().getName()+"\t"+"enter the method!!!");
    try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
    System.out.println(Thread.currentThread().getName()+"\t"+"exist the method!!!");

    return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + " Bulkhead.Type.THREADPOOL");
}
public CompletableFuture<String> myBulkheadPoolFallback(Integer id,Throwable t)
{
    return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
}
```





## 7. Circuit Breaker - rateLimit 限流

地铁站排队安检就是限流

```yml
ratelimiter:
  configs:
    default:
      limitForPeriod: 2 #在一次刷新周期内，允许执行的最大请求数
      limitRefreshPeriod: 1s # 限流器每隔limitRefreshPeriod刷新一次，将允许处理的最大请求数量重置为limitForPeriod
      timeout-duration: 1 # 线程等待权限的默认等待时间
  instances:
    cloud-payment-service:
      baseConfig: default
```

```java
@GetMapping(value = "/feign/pay/ratelimit/{id}")
@RateLimiter(name = "cloud-payment-service",fallbackMethod = "myRatelimitFallback")
public String ratelimit(@PathVariable("id") Integer id) {
    return payFeignApi.myRatelimit(id);
}

public String myRatelimitFallback(Integer id,Throwable t) {
    return "你被限流了，禁止访问/(ㄒoㄒ)/~~";
}
```

1秒限流2个，多的走fallback  http://localhost/feign/pay/ratelimit/32



## 8. Micrometer + Zipkin服务链路追踪

### （1）准备Zipkin

#### 1.1 下载Zipkin

https://zipkin.io/

#### 1.2 启动Zipkin

java -jar zipkin-server-3.0.0-rc0-exec.jar

#### 1.3 访问UI

http://localhost:9411/zipkin/

### （2）引入Micrometer 

#### 1.1 pom

```xml
<!--micrometer-tracing指标追踪  1-->
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing</artifactId>
</dependency>
<!--micrometer-tracing-bridge-brave适配zipkin的桥接包 2-->
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>
<!--micrometer-observation 3-->
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-observation</artifactId>
</dependency>
<!--feign-micrometer 4-->
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-micrometer</artifactId>
</dependency>
<!--zipkin-reporter-brave 5-->
<dependency>
    <groupId>io.zipkin.reporter2</groupId>
    <artifactId>zipkin-reporter-brave</artifactId>
</dependency>
```

#### 1.2 yml

```yml
# zipkin图形展现地址和采样率设置
management:
  zipkin:
    tracing:
      endpoint: http://localhost:9411/api/v2/spans
  tracing:
    sampling:
      probability: 1.0 #采样率默认为0.1(0.1就是10次只能有一次被记录下来)，值越大收集越及时。
```

#### 1.3 查看请求完整链路

![image-20250829143001898](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829143003122.png)



## 9. gateway 网关 router/predict/filter

### （1）Gateway基本概念

![image-20250829143552315](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829143553180.png)

三大核心：

* route: 隐藏端口，隐藏请求路径。  80/pay/list  --> 9752/xxx/pay/list  用9752。。来访问80。。
* predicate： 请求头、请求参数、请求体是否包含某些值
* filter：请求，响应过滤处理拦截

**路由转发+断言判断+执行过滤器链**

### （2）创建项目cloud-gateway9527

#### 1.1 pom

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

#### 1.2 yml

```yml
server:
  port: 9527

spring:
  application:
    name: cloud-gateway #以微服务注册进consul或nacos服务列表内
  cloud:
    consul: #配置consul地址
      host: localhost
      port: 8500
      discovery:
        prefer-ip-address: true
        service-name: ${spring.application.name}
    gateway:
      routes:
        - id: pay_routh1 #pay_routh1                #路由的ID(类似mysql主键ID)，没有固定规则但要求唯一，建议配合服务名
#          uri: http://localhost:8001                     #匹配后提供服务的路由地址
          uri: lb://cloud-payment-service               #application  name
          predicates:
            - Path=/pay/gateway/get/**              # 断言，路径相匹配的进行路由
```

http://localhost:9527/pay/gateway/get/1 访问的是http://cloud-payment-service/pay/gateway/get/1 

### （3）Route

#### 1.1 基本路由

```yml
gateway:
      routes:
        - id: pay_routh1
          uri: lb://cloud-payment-service               #application  name
          predicates:
            - Path=/pay/gateway/get/**              # 断言，路径相匹配的进行路由
```

9527 的 /pay/gateway/get/** 可以直接转发到cloud-payment-service/pay/gateway/get/**



#### 1.2 paymentservice暴露的请求都通过gateway访问

> 仅限于PayFeignApi类中的方法

```java
//@FeignClient(value = "cloud-payment-service")
@FeignClient(value = "cloud-gateway")  // 通过网关来调用payment API
public interface PayFeignApi {}
```

```yml
- id: pay_routh3
  uri: lb://cloud-payment-service
  predicates:
    - Path=/*/**
```

http://localhost:9527/pay/info 、 http://localhost:9527/pay/ratelimit/3 都能正常访问



### （4）Predicate

```yml
predicates:
  - Path=/pay/gateway/get/**              # 断言，路径相匹配的进行路由
  - After=2025-08-03T07:28:21.328677+08:00[Asia/Shanghai]   # 在这个日期之后生效  Before/Between 同理
  - Cookie=myCookie,123   # 需要带着cookie   -- myCookie=123才生效
```

http://localhost:9527/pay/gateway/get/1  必须需要带着cookie   -- myCookie=123才生效

### （5）filter

```yml
predicates:
  - Path=/pay/gateway/get/**              # 断言，路径相匹配的进行路由
  - After=2025-08-03T07:28:21.328677+08:00[Asia/Shanghai]   # 在这个日期之后生效  Before/Between 同理
  - Cookie=myCookie,123   # 需要带着cookie   -- myCookie=123才生效
filters:

    - AddRequestParameter=gender, male

```

请求能够获取到请求参数gender, male

![image-20250829153846938](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829153847528.png)

## 10. Alibaba - Nacos

### （1）Nacos启动

#### 1.1 下载

https://nacos.io/download/nacos-server/

#### 1.2 启动

```shell
startup.cmd -m standalone
```

#### 1.3 访问

http://localhost:8848/nacos/

![image-20250829162102979](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829162103412.png)

### （2）服务注册进Nacos



```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

```yml
spring:
  application:
    name: nacos-payment-provider
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #配置Nacos地址
```



![image-20250829162737177](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829162737701.png)

http://localhost:83/consumer/pay/nacos/3 可正常访问，并实现了负载均衡



### （3）Nacos配置中心

namespace + group + dataId 

```yml
server:
  port: 3377

spring:
  profiles:
    active: prod


# nacos配置
spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #Nacos服务注册中心地址
      config:
        server-addr: localhost:8848 #Nacos作为配置中心地址
        file-extension: yml #指定yaml格式的配置
        namespace: 155f8357-d472-428b-9bb4-0e9bf2039057
        group: PROD_GROUP

# nacos端配置文件DataId的命名规则是：
# ${spring.application.name}-${spring.profile.active}.${spring.cloud.nacos.config.file-extension}
# 本案例的DataID是:nacos-config-client-dev.yaml
```

```java
@RestController
@RefreshScope //在控制器类加入@RefreshScope注解使当前类下的配置支持Nacos的动态刷新功能。
public class NacosConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
```

![](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829163556475.png)

http://localhost:3377/config/info  配置可实时更新



## 11. Alibaba - Sentinel

> 轻量级的流量控制，熔断降级java库

### （1）Sentinel启动

#### 1.1 下载

https://sentinelguard.io/zh-cn/index.html

#### 1.2 启动

```
java -jar sentinel-dashboard-1.8.6.jar
```

#### 1.3 访问

> http://localhost:8080/#/dashboard/home , 后台默认端口8719

![image-20250829164742200](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829164742701.png)



### （2）服务引入Sentinel

#### 1.1 pom

```xml
<!--SpringCloud ailibaba sentinel-datasource-nacos -->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-datasource-nacos</artifactId>
</dependency>
<!--SpringCloud alibaba sentinel -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>
```



#### 1.2 yml

```yml
server:
  port: 8401

spring:
  application:
    name: cloudalibaba-sentinel-service
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848         #Nacos服务注册中心地址
    sentinel:
      transport:
        dashboard: localhost:8080 #配置Sentinel dashboard控制台服务地址
        port: 8719 #默认8719端口，假如被占用会自动从8719开始依次+1扫描,直至找到未被占用的端口
      web-context-unify: false # controller层的方法对service层调用不认为是同一个根链路
      datasource:
        ds1:
          nacos:
            server-addr: localhost:8848
            dataId: ${spring.application.name}
            groupId: DEFAULT_GROUP
            data-type: json
            rule-type: flow # flow流量控制  degrade熔断降级 param-flow热点数据  authority授权规则
```



### （3）流控规则

![image-20250901170554765](https://gitee.com/yj1109/cloud-image/raw/master/img/20250901170555241.png)

#### 1.1 阈值类型

##### 1.1.1 QPS （query per second）

每秒访问次数

##### 1.1.2 并发线程数

每秒并发次数，手动在浏览器刷新不出效果。jmeter压测模拟多用户并发

#### 1.2 流控模式

##### 1.2.1 直接

直接当前资源失败报错。



##### 1.2.2 关联

大量对B请求会导致testA的限流

![image-20250901172016567](https://gitee.com/yj1109/cloud-image/raw/master/img/20250901172016990.png)



##### 1.2.3 链路

只针对/testC限流， 而不对/testD限流

```java
@Service
public class FlowLimitService {
    @SentinelResource(value = "common")
    public void common() {
        System.out.println("------FlowLimitService come in");
    }
}

 /**
 * 流控-链路演示demo
 * C和D两个请求都访问flowLimitService.common()方法，阈值到达后对C限流，对D不管
 */
@Autowired
private FlowLimitService flowLimitService;

@GetMapping("/testC")
public String testC() {
    flowLimitService.common();
    return "------testC";
}

@GetMapping("/testD")
public String testD() {
    flowLimitService.common();
    return "------testD";
}
```

![image-20250901172547985](https://gitee.com/yj1109/cloud-image/raw/master/img/20250901172548375.png)



#### 1.3  流控效果

#####  1.3.1 快速失败

异常报错

##### 1.3.2 Warm Up

```java
com.alibaba.csp.sentinel.slots.block.flow.controller.WarmUpController
public WarmUpController(double count, int warmUpPeriodInSec) {
    construct(count, warmUpPeriodInSec, 3);
}
```

冷启动，前5秒只允许10 / 3 个请求，后续恢复到10个请求

![image-20250901173023343](https://gitee.com/yj1109/cloud-image/raw/master/img/20250901173023727.png)

<img src="https://gitee.com/yj1109/cloud-image/raw/master/img/20250901173956943.png" alt="deepseek_mermaid_20250901_9c9a95" style="zoom:25%;" />

##### 1.3.3 排队等待

只允许1秒进来10个请求，其他请求进队列等待，最多等1秒。超时直接拒绝。

![image-20250901175623533](https://gitee.com/yj1109/cloud-image/raw/master/img/20250901175624251.png)



### （4）熔断规则

#### 1.1 熔断策略

##### 1.1.1 慢调用比例

响应时间比较慢的情况。

```java
@GetMapping("/testF")
public String testF() {
    //暂停几秒钟线程
    try {
        TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("----测试:新增熔断规则-慢调用比例 ");
    return "------testF 新增熔断规则-慢调用比例";
}
```

![image-20250901180128322](https://gitee.com/yj1109/cloud-image/raw/master/img/20250901180128684.png)

##### 1.1.2 异常比例

```java
@GetMapping("/testG")
public String testG() {
    int a = 1 / 0;
    return "------testF 新增熔断规则-异常比例";
}
```

![image-20250901180255657](https://gitee.com/yj1109/cloud-image/raw/master/img/20250901180256037.png)

##### 1.1.3 异常数

![image-20250901180409341](https://gitee.com/yj1109/cloud-image/raw/master/img/20250901180409733.png)

### （5）热点规则



![image-20250901180849572](https://gitee.com/yj1109/cloud-image/raw/master/img/20250901180849999.png)



对于参数p1=1，只允许1秒1个请求。

对于p1不为空的其他值，只允许1秒3个请求。

不对其他情况的场景限流。

![image-20250901180916576](https://gitee.com/yj1109/cloud-image/raw/master/img/20250901180916953.png)

![image-20250901180640037](https://gitee.com/yj1109/cloud-image/raw/master/img/20250901180640423.png)

### （6）授权规则

加黑/白名单 

```java
@Component
public class MyRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("serverName");
    }
}

@RestController
@Slf4j
//Empower授权规则，用来处理请求的来源
public class EmpowerController {
    @GetMapping(value = "/empower")
    public String requestSentinel4() {
        log.info("测试Sentinel授权规则empower");
        return "Sentinel授权规则";
    }
}

 
```

对参数serverName=test拉入黑名单，拒绝访问

![image-20250901181749032](https://gitee.com/yj1109/cloud-image/raw/master/img/20250901181749427.png)



#### （8）持久化到nacos

![image-20250829165115062](https://gitee.com/yj1109/cloud-image/raw/master/img/20250829165115510.png)



### （7）@SentinelResource

fallback是报错或流控走的方法。

blockHandler是熔断走的方法

```java
@GetMapping("/rateLimit/byResourceSentinel")
@SentinelResource(value = "byResourceSentinelResource",
        blockHandler = "blockHandlerMethod",
        fallback = "fallbackMethod")
public String byResource(@RequestParam(value = "p1", required = false) Integer p1) {
    if (p1 != null) {
        int a = 3 / p1;
    }
    return "按rest地址限流测试OK - " + p1;
}

public String blockHandlerMethod(@RequestParam(value = "p1", required = false) Integer p1, BlockException e) {
    return "blockHandlerMethod()..." + e.getMessage();
}

public String fallbackMethod(@RequestParam(value = "p1", required = false) Integer p1, Throwable t) {
    return "fallbackMethod()... " + t.getMessage();
}
```



## 12. Alibaba - Seata 分布式事务管理

### （1）Seata定义

![image-20250903094755891](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903094756518.png)

Apache Seata™ (incubating) 是一款开源的分布式事务解决方案，致力于在微服务架构下提供高性能和简单易用的分布式事务服务。

![image-20250903095337788](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903095338327.png)

#### 1.1 Seata原理

Seata对分布式事务的协调和控制就是1 + 3

1是一个全局事务ID

3是TC/TM/RM

![image-20250903095721943](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903095722485.png)



### （2）Seata软件安装

#### 1.1 下载

https://seata.apache.org/zh-cn/unversioned/download/seata-server/

#### 1.2 启动

```shell
bin\seata-server.bat
```

#### 1.3 访问ui

```shell
http://localhost:7091/#/transaction/list  seata/seata
```

![image-20250903100408707](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903100409278.png)



### （3）环境准备

准备对应的微服务模块：下订单-》 减库存 -》 扣余额 -》 改订单状态

![image-20250903100910587](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903100911085.png)

对应表创建undo_log回滚日志表

![image-20250903100725675](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903100726193.png)





### （4）测试

#### 1.1 超时报错未回滚

出现了脏数据

![image-20250903104040636](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903104041405.png)

![image-20250903104050429](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903104051046.png)

![image-20250903104058447](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903104058958.png)



#### 1.2 开启全局事务 - 报错后回滚

在入口Order添加注解@GlobalTransactional

```java
@GlobalTransactional(name = "zzyy-create-order", rollbackFor = {Exception.class})  //默认AT 自动事务
@Override
public void create(Order order) {}
```



Seata 会保存当前事务的信息

![image-20250903102839435](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903102840024.png)

![image-20250903102816619](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903102817255.png)



rm一阶段直接提交，并将信息存入到undo_log表

![image-20250903104409639](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903104410224.png)

![image-20250903104422426](https://gitee.com/yj1109/cloud-image/raw/master/img/20250903104423095.png)

```json
{
  "@class": "io.seata.rm.datasource.undo.BranchUndoLog",
  "xid": "192.168.137.1:8091:3531511990017679384",
  "branchId": 3531511990017679387,
  "sqlUndoLogs": [
    "java.util.ArrayList",
    [
      {
        "@class": "io.seata.rm.datasource.undo.SQLUndoLog",
        "sqlType": "UPDATE",
        "tableName": "t_account",
        "beforeImage": {
          "@class": "io.seata.rm.datasource.sql.struct.TableRecords",
          "tableName": "t_account",
          "rows": [
            "java.util.ArrayList",
            [
              {
                "@class": "io.seata.rm.datasource.sql.struct.Row",
                "fields": [
                  "java.util.ArrayList",
                  [
                    {
                      "@class": "io.seata.rm.datasource.sql.struct.Field",
                      "name": "id",
                      "keyType": "PRIMARY_KEY",
                      "type": -5,
                      "value": [
                        "java.lang.Long",
                        1
                      ]
                    },
                    {
                      "@class": "io.seata.rm.datasource.sql.struct.Field",
                      "name": "residue",
                      "keyType": "NULL",
                      "type": 3,
                      "value": [
                        "java.math.BigDecimal",
                        1000
                      ]
                    },
                    {
                      "@class": "io.seata.rm.datasource.sql.struct.Field",
                      "name": "used",
                      "keyType": "NULL",
                      "type": 3,
                      "value": [
                        "java.math.BigDecimal",
                        0
                      ]
                    }
                  ]
                ]
              }
            ]
          ]
        },
        "afterImage": {
          "@class": "io.seata.rm.datasource.sql.struct.TableRecords",
          "tableName": "t_account",
          "rows": [
            "java.util.ArrayList",
            [
              {
                "@class": "io.seata.rm.datasource.sql.struct.Row",
                "fields": [
                  "java.util.ArrayList",
                  [
                    {
                      "@class": "io.seata.rm.datasource.sql.struct.Field",
                      "name": "id",
                      "keyType": "PRIMARY_KEY",
                      "type": -5,
                      "value": [
                        "java.lang.Long",
                        1
                      ]
                    },
                    {
                      "@class": "io.seata.rm.datasource.sql.struct.Field",
                      "name": "residue",
                      "keyType": "NULL",
                      "type": 3,
                      "value": [
                        "java.math.BigDecimal",
                        970
                      ]
                    },
                    {
                      "@class": "io.seata.rm.datasource.sql.struct.Field",
                      "name": "used",
                      "keyType": "NULL",
                      "type": 3,
                      "value": [
                        "java.math.BigDecimal",
                        30
                      ]
                    }
                  ]
                ]
              }
            ]
          ]
        }
      }
    ]
  ]
}
```



报错后TM会根据undo_log的信息对对应数据回滚，并删除undo_log



# 十二、Java并发编程

## 1. 并发编程三大特征

### （1）原子性

#### 1.1 描述

原子性是指一个操作（多个指令）是一个整体不可分割。一个线程正在执行这个操作，其他线程想要执行的话，必须等他执行完。

```java
package com.ityj.interview.advance.concurrent.thread;

// 原子性可以解决并发编程问题
// count = 168  可以用 cas/ synchronized /  lock 解决
public class Thread1 {

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            incr();
        });
        Thread t2 = new Thread(() -> {
            incr();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("count = " + count);

    }

    private static synchronized void incr() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
        }
    }

}
```

#### 1.2 解决

原子性可以解决并发问题。 java中有以下方法

CAS / synchronized / lock



### （2）可见性

#### 1.1 描述

A线程不可见B线程修改后的数据。（CPU和JVM的数据不是实时更新的）

```java
package com.ityj.interview.advance.concurrent.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

// 可见性
public class Thread2 {

    //static volatile boolean flag = false;  //1. 直接volatile
    static boolean flag = false;
    static volatile int count = 1;



    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            check();
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        flag = true;
        System.out.println("main flag = " + flag);

    }

    private static void check_2() {  // 2. synchronized
        while (true) {
            if (flag) {
                System.out.println("flag is true and exit!" + Thread.currentThread().getName());
                break;
            } else {
                System.out.println(111);  // println 带有锁 synchronized (this)
            }
        }
    }

    static ReentrantLock lock  = new ReentrantLock();
    private static void check_3() {  // 3. lock 底层也是cas, 改了volatile state; cpu会把当前所有数据刷到JVM， 包括之前的flag
        while (true) {
            lock.lock();
            if (flag) {
                System.out.println("flag is true and exit!" + Thread.currentThread().getName());
                break;
            } else {
                count++;
            }
            lock.unlock();
        }
    }

    private static void check() {  // 4. 随便找一个volatile修饰的变量，会同时刷新flag
        while (true) {
            if (flag) {
                System.out.println("flag is true and exit!" + Thread.currentThread().getName());
                break;
            } else {
                count++;
            }
        }
    }

}

```

#### 1.2 解决

JMM用于屏蔽掉硬件和各个操作系统之间内存的差异。

volatile /  synchronized / lock(本质也是cas 改volatile state; )

![image-20250815150634124](https://gitee.com/yj1109/cloud-image/raw/master/img/20250815150635002.png)

### （3）有序性 

#### 1.1 描述

在Java中，.java文件在被编译后会生成多条指令，这些指令需要CPU去执行，CPU执行这些指令时，会在不影响最终结果的前提下对这些指令做一定程度的重排序。

Java做编译时，JVM内部也会有一定的JIT优化。也会做一些指令重排序



```java
package com.ityj.interview.advance.concurrent.thread;

// 可见性
// 正常情况下 x y打印的可能性为 11/01/10 如果出现了00 说明 t1 t2 的两个操作出现了指令重排序
public class Thread3 {

    static int a,b,x,y;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;


            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            });

            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();

            if (x == 0 && y == 0) {
                System.out.println("i == " + i + " 时，x == " + x + ", y == " + y);
            }

        }

        System.out.println("main complete... ");

    }

}
```

#### 1.2 出现的问题

懒汉式DLC基本模式可能出现指令重排导致第二个线程拿到未赋值的对象。

```java
class Lazy {

    private Lazy() {}

    private static Lazy INSTANCE = null;

    public static Lazy getInstance() {
        if (INSTANCE == null) {
            synchronized (Lazy.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Lazy();  // 可能出现指令重排。 正常 1开辟空间  2初始化属性  3引用赋值。   如果 132 就会导致对象未初始化 t2判断非空，返回的对象是不完整的
                }
            }
        }
        return INSTANCE;
    }


}
```



#### 1.3 解决

在涉及到的变量上加上volatile

```JAVA
    private static volatile Lazy INSTANCE = null;
```





## 2. 锁的分类

### （1）悲观锁 & 乐观锁

**悲观锁：** synchronized， ReentrantLock， ReentrantReadWriteLock

悲观锁在拿不到资源后，会将当前线程挂起（BLOCKED/WAITING/TIME_WAITING），挂起的操作是由CPU解决的。涉及到用户态与内核态之间切换，会影响一定的效率。

**乐观锁：** CAS

不涉及线程挂起，但如果长时间未获取到锁。空转会浪费CPU性能。

> 资源竞争不激烈，几次尝试就能成功拿到锁的场景推荐乐观锁。否则悲观锁

### （2）可重入锁 & 不可重入锁



可重入锁：  synchronized， ReentrantLock， ReentrantReadWriteLock

A() B() 方法都被synchronized修饰（同一把锁）。A内部调用B，A已经拥有锁，这时候再调用B，此时不需要再获取锁。 就是可重入锁。会记录重入次数。

```java
public static void main(String[] args) {
    methodA();
}

public static synchronized void methodA() {
    System.out.println("AAA");
    methodB();
}


public static synchronized void methodB() {
    System.out.println("BBB");
}
```

不可重入锁：

线程池的Worker对象是不可重入，不过他不是来实现原子性的。

### （3）公平锁 & 非公平锁

非公平锁：synchronized, ReentrantLock(false)



公平锁：ReentrantLock(true) 

排队，FIFO



### （4）互斥锁 & 共享锁

互斥锁：写锁，锁只能被一个线程拥有

synchronized, ReentrantLock



共享锁：读锁，同一时间点，可以多个线程同时持有当前锁资源。

ReentrantReadWriteLock



## 3. CAS

### （1）什么是CAS

Compare and Swap， 比较和交换，是一条CPU并发原语。

他在替换内存中某个位置的值时可以保证原子性。先比较oldValue和内存值，如果一致就将内存值改为newValue

Java提供了一个原子类Unsafe,里面提供了CAS的操作方法，这些方法是native修饰的，底层调用C++. 我们看到Unsafe.compareAndSwapInt就到头了。



### （2）AtomicInteger.compareAndSet()

针对i++这种操作，基于CAS是如何实现原子操作：Atomic类

* AtomicInteger的compareAndSet()用到了Unsafe原子类
* 他获取到了对应属性在内存中的偏移量（就是对应的value），同时也就获取到了数据的oldValue
* 然后基于Unsafe魔法类的compareAndSetInt()实现数据原子性的自增或自减



C++源码

https://hg.openjdk.org/jdk8u/jdk8u/hotspot/file/69087d08d473/src/share/vm/prims/unsafe.cpp

![image-20250818110255086](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818110255529.png)



https://hg.openjdk.org/jdk8u/jdk8u/hotspot/file/69087d08d473/src/os_cpu/linux_x86/vm/atomic_linux_x86.inline.hpp

![image-20250818110541010](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818110541363.png)

### （3）CAS问题

1. ABA
2. 空转影响CPU
3. 只能针对一个属性保证原子性

## 4. synchronized

可以对方法或代码块进行加锁。一般使用小粒度的同步代码快

synchronized的锁是基于对象实现的，在java中，Object类提供了wait, notify等针对锁的操作。java所有的对象都可以作为一把锁。

### （1）类锁和对象锁

同步方法：

* this  -  锁的是当前类对象
* 类.class

同步代码块：

* 静态方法 - 类.class
* 非静态方法 - 锁的是当前类对象

![image-20250818113035414](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818113035783.png)



### （2）锁的优化

![image-20250818120342879](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818120343476.png)



### （3）锁消除

JVM会自动将没必要上锁的锁去掉

**原理**：当JVM（通过逃逸分析）确定某个锁对象**不会逃逸出当前线程**时，会直接移除同步操作。

### （4）锁膨胀

如果在一个for循环里上锁， JVM会自动把锁的范围扩大。

相邻的多个同步块使用同一把锁

### （5）锁升级

无锁： 当前对象没有被作为锁资源

偏向锁：只有一个线程t1，同一个线程重复获取这把锁，就是偏向锁。 直接拿锁走人

​	两个线程进行竞争，t2也过来了

​	1.1 如果t1还拥有锁资源，导致t2拿锁失败。会进行锁升级，偏向锁撤销。 **偏向  --》 轻量级**

​        1.2 如果t1已经结束，偏向锁没有被持有。 那么t2拿到锁，锁会直接将偏向的线程更改为t2， 拿锁走人。

轻量级锁： 会采用自旋锁的方式尝试获取这个锁资源（自适应锁资源）。

​	1.1 成功，拿锁走人

​	1.2 多次失败，达到自旋的阈值。会再次进行升级   **轻量级  --》 重量级**

重量级锁： 传统的synchronized，拿锁成功走人，失败挂起。



可以从偏向锁降级为无锁。 其他状态不可逆。



## 5. synchronized的实现原理

### （1）MarkWord

主要是看对象头里的markword存储的信息，markword占用了8字节

- 锁状态管理（偏向/轻量级/重量级锁）
- 对象哈希码存储
- 垃圾回收分代年龄记录
- GC标记

![image-20250818122913232](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818122913589.png)

![image-20250818123050859](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818123051248.png)

无锁：内部正常存储对象的信息，hashcode，分代年龄等。 锁标记位 001

偏向锁：内部没有地方存储hashcode了，大部分空间都存储了偏向哪个线程，存储了线程的标识。锁标记位：101

轻量级锁： 内部直接鵆了Lock Record地址。Lock Record存储了对象的基本信息。 锁标记位： 00

重量级锁： 内部直接存储了ObjectMonitor的地址，ObjectMonitor存储的是对象的信息。 锁标记位 10



### （2）查看锁MarkWord的变化

#### 1.1 无锁001

```java
public static void main(String[] args) {
    Object obj = new Object();
    System.out.println(ClassLayout.parseInstance(obj).toPrintable());  // 01 00 00 00 (00000001 00000000 00000000 00000000) (1)
}
```

![image-20250818124704420](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818124704830.png)



#### 1.2 匿名偏向锁 101

```shell
在 JDK 17 中，对象头打印显示无锁状态（001）而非偏向锁（101），原因如下：

1. 偏向锁在 JDK 15+ 中默认禁用
JDK 15 开始：偏向锁被标记为废弃（deprecated） 并默认关闭（JEP 374）。

JDK 17：延续此策略，偏向锁机制默认不启用。新创建的对象直接进入无锁状态（标记位 001）。

```

> ```
> 通过参数-XX:+UnlockDiagnosticVMOptions  -XX:+UseBiasedLocking 开启偏向锁
> ```

![image-20250818125854187](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818125854571.png)

101 表示偏向锁

后面的0表示是一个匿名偏向锁， 没有指向任何线程



![image-20250818130603958](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818130604611.png)

![image-20250818130747970](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818131228499.png)



![image-20250818130730518](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818131234153.png)



#### 1.3 轻量级锁

JDK17默认禁用偏向锁，所以直接是无锁转为轻量级锁

```java
public static void main(String[] args) throws InterruptedException {
    Object obj = new Object();
    System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    synchronized (obj) {
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
```

![image-20250818132041748](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818132042185.png)

#### 1.4 重量级锁

![image-20250818135753970](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818135754385.png)





```java
public static void main(String[] args) throws InterruptedException {
    Object obj = new Object();
    Thread t1 = new Thread(() -> {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + " : " + ClassLayout.parseInstance(obj).toPrintable());
        }
    });

    synchronized (obj) {
        System.out.println(Thread.currentThread().getName() + " : " + ClassLayout.parseInstance(obj).toPrintable());
        t1.start();
        Thread.sleep(3000);
    }
}
```



### （3）ObjectMonitor

> https://hg.openjdk.org/jdk8u/jdk8u/hotspot/file/69087d08d473/src/share/vm/runtime/objectMonitor.hpp

```c++
  // initialize the monitor, exception the semaphore, all other fields
  // are simple integers or pointers
  ObjectMonitor() {
    _header       = NULL; 
    _count        = 0;     // 竞争锁的线程个数
    _waiters      = 0,     // _WaitSet中有多少个线程处于wait()状态
    _recursions   = 0;     // 锁重入的次数
    _object       = NULL;
    _owner        = NULL;   // 持有锁的线程
    _WaitSet      = NULL;    // 持有锁的线程执行wait方法后，会扔到这个WaitSet里
    _WaitSetLock  = 0 ;
    _Responsible  = NULL ;
    _succ         = NULL ;
    _cxq          = NULL ;   // 获取锁资源失败后， 线程需要放在这个单向链表中
    FreeNext      = NULL ;
    _EntryList    = NULL ;   // cxq中的等待线程会基于一定机制扔到_EntryList，同时拿锁失败也可能进来这里
    _SpinFreq     = 0 ;
    _SpinClock    = 0 ;
    OwnerIsThread = 0 ;
    _previous_owner_tid = 0;
  }
```

https://hg.openjdk.org/jdk8u/jdk8u/hotspot/file/69087d08d473/src/share/vm/runtime/objectMonitor.cpp

![image-20250818141155613](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818141156010.png)



## 6. ReentrantLock

### （1）ReentrantLock和synchronized的区别

1.1 synchronized是一个关键字，ReentrantLock是一个对象，需要基于对象操作

1.2 synchronized可以用于方法或代码块。 ReentrantLock需要手动处理开启锁以及关闭锁

1.3 synchronized 拿不到锁只能挂起， lock可以设置超时时间等方式处理

1.4 lock可以支持公平锁

1.5 synchronized是基于对象实现的， lock是基于AQS实现

### （2）AQS

AQS就是java.util.concurrenct包下的一个抽象类AbstractQueuedSynchronizer

主要有三个属性

1. state  -- _recursions ； state为0时，表示当前锁没有被持有。

2. 双向链表(阻塞队列) - _cxq/_EntryList； 锁获取失败时，会将当前线程封装成Node对象，并加入到AQS双向队列的队尾。等待持有锁的线程释放锁资源，当前的线程才有机会拿。（当然需要当前线程移动到最前端）

![image-20250818145831368](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818150340304.png)

3. 一个单向链表 - _WaitSet：定向链表是持有锁的线程执行了await()方法，线程会释放掉锁资源并添加到当前的单向链表中并挂起线程。当被signal()唤醒后，会把线程从单向链表中扔到双向链表中等待获取锁。

![image-20250818150051450](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818150051823.png)



### （3）ReentrantLock非公平锁加锁流程

```java
final void lock() {
    if (!initialTryLock())
        acquire(1);
}


final boolean initialTryLock() {
    Thread current = Thread.currentThread();
    if (compareAndSetState(0, 1)) { // first attempt is unguarded
        setExclusiveOwnerThread(current);
        return true;
    } else if (getExclusiveOwnerThread() == current) {
        int c = getState() + 1;
        if (c < 0) // overflow
            throw new Error("Maximum lock count exceeded");
        setState(c);
        return true;
    } else
        return false;
}

protected final boolean tryAcquire(int acquires) {
    if (getState() == 0 && compareAndSetState(0, acquires)) {
        setExclusiveOwnerThread(Thread.currentThread());
        return true;
    }
    return false;
}

java.util.concurrent.locks.AbstractQueuedSynchronizer#acquire(java.util.concurrent.locks.AbstractQueuedSynchronizer.Node, int, boolean, boolean, boolean, long)
```



![image-20250818151643914](https://gitee.com/yj1109/cloud-image/raw/master/img/20250818151644342.png)





### （4）ReentrantLock公平锁

```java
static final class FairSync extends Sync {
    private static final long serialVersionUID = -3000897897090466540L;

    /**
     * Acquires only if reentrant or queue is empty.
     */
    final boolean initialTryLock() {
        Thread current = Thread.currentThread();
        int c = getState();
        if (c == 0) {
            if (!hasQueuedThreads() && compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        } else if (getExclusiveOwnerThread() == current) {
            if (++c < 0) // overflow
                throw new Error("Maximum lock count exceeded");
            setState(c);
            return true;
        }
        return false;
    }

    /**
     * Acquires only if thread is first waiter or empty
     */
    protected final boolean tryAcquire(int acquires) {
        if (getState() == 0 && !hasQueuedPredecessors() &&
            compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }
}
```

公平和非公平的不同：

lock方法实现方式不同

* 非公平锁会直接执行CAS尝试获取锁。尝试改state的值(0 -> 1)。失败才走acquire方法
* 公平锁会判断state的值，如果不是0并且当前持有锁的线程不是自己，直接走acquire()方法

tryAcquire()实现不同：

* 非公平锁，在state为0时直接CAS抢锁
* 公平锁，state为0是还会判断队列是否有任务



# 十三、Dubbo

## 1. zookeeper + dubbo实现远程调用

> JDK17 + maven3.9.9 + node16.20.2 + zk3.8.4 + springboot3.5.0 + dubbo3.2.10

### （1）初始化provider-ticket

#### 1.1 pom

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.5.0</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>

<dependencies>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- dubbo -->
    <dependency>
        <groupId>org.apache.dubbo</groupId>
        <artifactId>dubbo-spring-boot-starter</artifactId>
        <version>3.2.10</version>
        <exclusions>
            <exclusion>
                <groupId>org.apache.zookeeper</groupId>
                <artifactId>zookeeper</artifactId>
            </exclusion>
        </exclusions>
    </dependency>

    <dependency>
        <groupId>org.apache.zookeeper</groupId>
        <artifactId>zookeeper</artifactId>
        <version>3.9.1</version>
    </dependency>

    <!-- 包含 Zookeeper 客户端等必要依赖 -->
    <dependency>
        <groupId>org.apache.dubbo</groupId>
        <artifactId>dubbo-dependencies-zookeeper</artifactId>
        <version>3.2.10</version>
        <type>pom</type>
    </dependency>

</dependencies>
```

#### 1.2 yml

```yml
dubbo:
  application:
    name: provider-ticket
  registry:
    address: zookeeper://192.168.137.110:2181
  scan:
    base-packages: com.ityj.dubbo.service.impl # 可以用 @DubboComponentScan

server:
  port: 8181
```

#### 1.3 启动类

```java
@SpringBootApplication
//@DubboComponentScan("com.ityj.dubbo.service")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(Application.class, args);
    }

}

```

#### 1.4 业务

```java
public interface TicketService {
    String getTicket(String ticketNo);
}


@DubboService  //服务发送出去
@Component
public class TicketServiceImpl implements TicketService {

    @Override
    public String getTicket(String ticketNo) {
        return "Dubbo provider TicketServiceImpl......" + ticketNo;
    }
}
```

### （2）初始化consumer-user

#### 1.1 pom

同provider-ticket

#### 1.2 yml

```yml
dubbo:
  application:
    name: consumer-user
  registry:
    address: zookeeper://192.168.137.110:2181
server:
  port: 8888
```

#### 1.3 启动类

```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(Application.class, args);
    }

}
```

#### 1.4 业务类

* 复制和provider一样的接口

```java
package com.ityj.dubbo.service;

public interface TicketService {
    String getTicket(String ticketNo);
}
```

* 用@DubboReference注入

```java
@RestController
public class UserController {

    @DubboReference
    private TicketService ticketService;

    // http://localhost:8888/ticket/7
    @GetMapping("/ticket/{ticketNo}")
    public String buyTicket(@PathVariable("ticketNo") String ticketNo) {
        return ticketService.getTicket(ticketNo);
    }

}
```



### （3）测试

> http://localhost:8888/ticket/74

![image-20250909125153533](https://gitee.com/yj1109/cloud-image/raw/master/img/20250909125154024.png)

可以用ZooInspector查看注册进zookeeper的服务

![image-20250909125719475](https://gitee.com/yj1109/cloud-image/raw/master/img/20250909125719871.png)



## 2. Dubbo Admin

### （1）下载源码

> https://github.com/apache/dubbo-admin/tree/0.6.0-release



### （2）修改配置

修改zookeeper版本，修复Jdk17连接zookeeper报错：127.0.0.1/＜unresolved＞:2181

```xml
<dependency>
    <groupId>org.apache.zookeeper</groupId>
    <artifactId>zookeeper</artifactId>
    <version>3.9.1</version>
</dependency>
```

更新对应的zookeeper链接

```properties
admin.registry.address=zookeeper://192.168.137.110:2181
admin.config-center=zookeeper://192.168.137.110:2181
admin.metadata-report.address=zookeeper://192.168.137.110:2181
```

### （3）编译运行

```txt
1. 编译打包
在根目录下执行以下命令
java -jar dubbo-admin-0.1.0.jar

2. 分别启动后端和前端
java -jar dubbo-admin-distribution/target/dubbo-admin-0.6.0.jar

dubbo-admin-ui目录下npm run dev
```

![image-20250909130439484](https://gitee.com/yj1109/cloud-image/raw/master/img/20250909130439888.png)

### （4）测试

> http://localhost:38080/ 或 http://localhost:38082

![image-20250909130815868](https://gitee.com/yj1109/cloud-image/raw/master/img/20250909130816311.png)

## 3. Dubbo应用

### （1）服务注册远程RPC调用

服务注册用zookeeper

RPC调用主要是两个注解: @DubboService 和 @DubboReference

### （2）负载均衡

```java
@DubboReference(loadbalance = LoadbalanceRules.RANDOM)
private TicketService ticketService;
```

### （3）集群容错

failover  - 失败后尝试访问其他服务器

failfast - 快速失败 不重试

failsafe 失败不报错，仅记录日志

failback: 失败后自动恢复，后台记录失败，定时重发

forking: 并行调用，有成功就返回

超时时间是2秒，可重试2次

```java
@DubboReference(loadbalance = LoadbalanceRules.RANDOM,
        cluster = ClusterRules.FAIL_OVER, retries = 2, timeout = 2000)
private TicketService ticketService;
```

### （4）服务降级

```java
mock = "com.ityj.dubbo.service.fallback.TicketServiceFallback"
```

```java
package com.ityj.dubbo.service.fallback;

import com.ityj.dubbo.service.TicketService;

public class TicketServiceFallback implements TicketService {
    @Override
    public String getTicket(String ticketNo) {
        return "失败了。。降级处理！";
    }
}
```

![image-20250909144739038](https://gitee.com/yj1109/cloud-image/raw/master/img/20250909144739666.png)

### （5）熔断限流

引入Resilience 或 Sentinel





# 十四、Kubernetes



容器化管理工具，

[Kubernetes](https://kubernetes.io/zh-cn/docs/concepts/overview/) 也称为 K8s，是用于自动部署、扩缩和管理容器化应用程序的开源系统。Kubernetes 源自 [Google 15 年生产环境的运维经验](http://queue.acm.org/detail.cfm?id=2898444)

![image-20250909161829668](https://gitee.com/yj1109/cloud-image/raw/master/img/20250909161830196.png)



![image.png](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910130410456.png)



k8s部署一个springboot项目

https://www.bilibili.com/video/BV1dm421u7My/?spm_id_from=333.337.search-card.all.click&vd_source=b23569b676ce26126febad3c290b16e8

Docker

1.编写Dockerfile文件

2.根据Dockerfile创建镜像image

3.可以将镜像推送到docker远程镜像仓库

4.使用docker run创建container



K8s

1.编写k8s配置文件

vi springboot-demo.yml

2. kubectl apply -f springboot-demo.yml



deployment/service/ingress

/向容器外暴露一个端口/用一个host映射对应的服务



1.deployment类型

![image-20250910125504924](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910125505365.png)

![image-20250910125526058](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910125526479.png)



2.service类型

![image-20250910125651844](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910125652292.png)



3.ingress

/etc/hosts做了映射

![image-20250910130027561](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910130028041.png)

![image-20250910130055727](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911163403190.png)

![image-20250910130211718](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910130212195.png)







svc：相当于大门。请求通过svc找到具体的容器进行操作

controller控制器保证动态扩容





# 十五、Prometheus



# 十六、ElasticSearch

https://gitee.com/zhengqingya/java-workspace/blob/master/SpringBoot%E7%B3%BB%E5%88%97/04-%E6%95%B4%E5%90%88Elasticsearch/02-spring-data-es/Spring%20Boot%20(4)%20%E6%95%B4%E5%90%88%20Elasticsearch.md



> https://www.bilibili.com/video/BV1V4411M7dK/?spm_id_from=333.337.search-card.all.click&vd_source=b23569b676ce26126febad3c290b16e8

![image-20250912122820164](https://gitee.com/yj1109/cloud-image/raw/master/img/20250912122820628.png)

全文检索：用的是倒排索引

![image-20250912122620898](https://gitee.com/yj1109/cloud-image/raw/master/img/20250912122621329.png)



什么是Elasticsearch?

分布式，高性能，高可用，可伸缩的搜索和分析系统。

![image-20250912124105606](https://gitee.com/yj1109/cloud-image/raw/master/img/20250912124106067.png)

![image-20250912124504509](https://gitee.com/yj1109/cloud-image/raw/master/img/20250912124504948.png)

![image-20250912124732349](https://gitee.com/yj1109/cloud-image/raw/master/img/20250912124732827.png)





下载elasticsearch-9.1.3和kibana-9.1.3，分别启动

es + kibana

添加配置

```shell
# Disabled security features
xpack.security.enabled: false
```

```shell
es启动成功：
http://localhost:9200/

kibana启动成功:
http://localhost:5601/app/dev_tools#/console/shell

```

下载分词器插件放到es的插件目录里

> https://release.infinilabs.com/analysis-ik/stable/





kibana

```shell
创建索引库：PUT /索引库名
查询索引库：GET /索引库名
删除索引库：DELETE /索引库名
修改索引库（添加字段）：PUT /索引库名/_mapping


```

索引的增删改查

1.创建

```shell
PUT /index_test
{
  "mappings": {
    "properties": {
      "column1":{
        "type": "text",
        "analyzer": "ik_smart"
      },
      "column2":{
        "type": "keyword",
        "index": "false"
      },
      "column3":{
        "properties": {
          "子字段1": {
            "type": "keyword"
          },
          "子字段2": {
            "type": "keyword"
          }
        }
      }
    }
  }
}

```



2. 查看index

   ```shell
   
   GET /index_test
   ```

   

3. index新增字段

   ```shell
   PUT /index_test/_mapping
   {
     "properties": {
       "new_column":{
         "type": "integer"
       }
     }
   }
   
   ```

4. index 删除字段

   ```shell
   DELETE /index_test
   ```

   



文档的增删改查

1. 增

```shell
POST /index_test/_doc/1
{
    "info": "真相只有一个！",
    "email": "zy@itcast.cn",
    "name": {
        "firstName": "柯",
        "lastName": "南"
    }
}

```

2. 删

```shell
DELETE /index_test/_doc/1
```

3. 改

```shell
POST /index_test/_update/1
 {
    "doc":{"info":"真相只有一111111111个"
  }
}
```



4. 查

```shell

GET /index_test/_doc/1
//批量查询：查询该索引库下的全部文档
GET /index_test/_search


GET /index_test/_search
{
  "query": {
    "match": {
      "info": "真相"
    }
  }
}



```







Java API

> https://www.cnblogs.com/buchizicai/p/17093719.html

新建索引

```java
@Configuration
public class ESConfig {
    @Bean
    public RestHighLevelClient client(){
        return new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://localhost:9200")
        ));
    }
}
```

```java
package com.ityj.springboot.util;

public class HotelConstants {
    public static final String MAPPING_TEMPLATE = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"name\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"address\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"price\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"score\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"brand\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"city\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"starName\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"business\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"location\":{\n" +
            "        \"type\": \"geo_point\"\n" +
            "      },\n" +
            "      \"pic\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
```

```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>7.17.29</version>
</dependency>
```





```java
package com.ityj.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.ityj.springboot.util.HotelConstants.MAPPING_TEMPLATE;

@Slf4j
@SpringBootTest
public class ESTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void createHotelIndex() throws IOException {
        // 1.创建Request对象
        CreateIndexRequest request = new CreateIndexRequest("hotel");
        // 2.准备请求的参数：DSL语句
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        // 3.发送请求
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }

}

```



测试

![image-20250912155057176](https://gitee.com/yj1109/cloud-image/raw/master/img/20250912155057850.png)



删除索引：

```java
@Test
void testDeleteHotelIndex() throws IOException {
    // 1.创建Request对象
    DeleteIndexRequest request = new DeleteIndexRequest("hotel");
    // 2.发送请求
    restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
}
```



索引查询

```java
@Test
void testExistsHotelIndex() throws IOException {
    // 1.创建Request对象
    GetIndexRequest request = new GetIndexRequest("hotel");
    // 2.发送请求
    boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
    // 3.输出
    System.err.println(exists ? "索引库已经存在！" : "索引库不存在！");
}
```











# 十七、Mysql

## 1. 面试题

### （1）B树 VS B+树

![image-20250910131600071](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911093117537.png)



### （2）innodb是如何用索引实现范围查找

![image-20250910131639207](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911093112304.png)

innodb会按主键建立一个B+树，根节点只存索引。叶子节点才存具体值。

#### 1.1 如果按主键索引a查找

select * from t_user where a = 3;

从上往下找，走了索引

![image-20250910132902280](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910132902704.png)

#### 1.2按普通字段b查找

select * from t_user where b = 3;

全表扫描

![image-20250910132835536](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911093106112.png)

#### 1.3范围查找

select * from t_user where a > 3

能走索引，先执行a=3，再取后面的数据

![image-20250910132950879](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910132952302.png)



select * from t_user where a <> 3

也走到了索引

![image-20250910133019922](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911093100359.png)



### （3）联合索引最左前缀法则

CREATE INDEX idx_name234 ON t_user(name2, name3, name4)

![image-20250910140311688](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910140312083.png)

![image-20250910135245085](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910135245497.png)

查询会根据联合索引拿到对应的主键索引值，然后回表查询拿到结果。

#### 1.1 所有字段都在，不管顺序 -> 走索引

![image-20250910140406761](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910140407149.png)

#### 1.2 只要联合索引第一个字段在  ->  走索引

![image-20250910140643391](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910140643806.png)

![image-20250910140659497](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910140659888.png)

#### 1.3 只要联合索引第一个字段不在  ->  不走索引

![image-20250910140850868](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910140851264.png)



### （4）覆盖索引

#### 1.1查询的字段在索引字段

where条件对应的联合索引 或者 主键索引。

![image-20250910144659489](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910144659905.png)

#### 1.2 查询的字段不在联合索引范围内

index t_user(b,c)

index t_user(name)

![image-20250910144851374](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910144851785.png)



### （5）索引扫描底层原理

EXPLAIN SELECT NAME FROM t_user

* 底层可以直接从主键对应的B+树扫描（全表扫描），叶子节点存储的是完整数据
* 也可以从index t_user(name)对应的B+树扫描 --> 因为只返回name字段，所以这个效率更高

![image-20250910145216592](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910145216999.png)



### （6）Order by 索引的选择

#### 1.1 SELECT * FROM t_user ORDER BY b, c;   --》 全表扫描

![image-20250910150422452](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910150422861.png)

#### 1.2 SELECT b,c,a FROM t_user ORDER BY b, c;  --》 bc联合索引

![image-20250910150505314](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910150505718.png)

### （7）数据类型转换 varchar - int 索引失效

1.1 int数字类型用引号  --》 自动转换成数字 用上索引

![image-20250910151812195](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911093041536.png)

![image-20250910151831743](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910151832749.png)

#### 1.2 varchar类型没用引号  --》 未用索引

底层是将数据表里的所有name字段转换成数字，'asd' --> 0  所以破坏了B+树，没有走索引

![image-20250910154811042](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910154811490.png)





![image-20250910152035780](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910152036796.png)

![image-20250910152050792](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910152051618.png)



#### 1.3 字段做算术运算不走索引

![image-20250910155037760](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910155038224.png)

![image-20250910155051988](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910155052391.png)

### （8）索引类型

![image-20250910160239114](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910160239505.png)



### （9）索引的优缺点

#### 1.1 优点

* 提高数据检索效率，降低数据库IO成本
* 唯一索引可保证数据唯一性
* 减少查询中分组和排序的耗时
* 加速两个表之间的连接，一般是外键创建索引

#### 1.2 缺点

* 空间换时间，占用物理空间
* 创建索引和维护索引需要时间。维护B+树成本较高
* 降低增删改的效率(B+树动态维护)

### （10）聚簇索引和非聚簇索引

![image-20250910163354341](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910163355157.png)

#### 1.1 聚簇索引

* 就是主键索引，叶子节点存的是一行的所有值。

* 一次查询即可拿到数据

* 增删改需要更新索引树，增加系统开销



#### 1.2 非聚簇索引

* 辅助索引，二级索引。叶子节点存储了对应的主键。需要用主键回表查询具体值。
* 如果索引覆盖的话，不需要回表查询了
* 修改或删除时不需要更新索引树，减少了系统开销



![image-20250910163709836](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910163710280.png)





### （11）InnoDb的索引和MyISAM索引区别

* InnoDb的索引包括聚簇索引和非聚簇索引
* MyISAM只有非聚簇索引

![image-20250910165316398](https://gitee.com/yj1109/cloud-image/raw/master/img/20250910165316837.png)







### （12）索引下推

EXPLAIN SELECT * FROM t_user WHERE b > 2 AND c = 3;

索引下推是指在引擎层对b>2扫描后，同时对c=3进行过滤。直接将结果进行回表返回给服务层。



![image-20250911094408446](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911094409235.png)

![image-20250911093958184](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911163445953.png)



![image-20250911094028516](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911163424302.png)





### （13）索引失效场景

1.1 like左边带%    

* 最左匹配原则
* 如果select的字段是覆盖索引，那么还是会走索引的

![image-20250911095600076](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911163539259.png)

1.2 类型隐式转换

1.3 where进行运算

1.4 使用or且存在非索引列

1.5 in   

1.6 order by  可能，看具体执行选择

1.7 is null / is not null 可能   -- 回表成本。小的话走索引，大的话不走索引。。



### (14) Innodb如何实现事务

通过buffer  pool, logBuffer, redo log, undo log来实现事务的。

update:

1. 根据条件找到所在页，并将数据缓存在buffer pool里
2. 修改buffer pool内存里的数据
3. 同时生成redolog, undolog用于磁盘持久化或回滚



### （15）MySql的锁有哪些

#### 1.1 行锁：innodb支持

* 共享锁： 读锁，多个线程能获取同一把锁。select xxx in share mode
* 排他锁：写锁。只有一个线程能获取这把锁。默认insert/update/delete会上锁。或者手动上锁：select xxx for update.
* 自增锁： 针对自增字段，如果数据回滚，自增序列不会回滚。

#### 1.2 表锁：innodb和myisam支持

* 共享读锁
* 排他写锁
* 意向锁：innodb自带的，不需用户干预

#### 1.3 全局锁：加锁后只能读，写操作挂起。一般用户数据库备份



常见的锁算法：

比如id有3个(1, 4, 9)

* 记录锁  update t_u xxx where id = 4， 会把这个一条记录上锁。只有一条，行锁可能锁多条
* 间隙锁： update t_u xxx where id = 5，RR隔离级别下会锁范围(4, 9)，防止幻读
* 临间锁next-key: 间隙锁+右记录锁  update t_u xxx where id = 5锁(4, 9]



![image-20250911122125445](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911122125873.png)



### （16）存储引擎有哪些

![image-20250911105620024](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911105620867.png)

对比InnoDB和MyISAM

* MyISAM会存储两个文件MYD和MYISAM. MYD是数据，MYISAM是索引. InnoDB只有一个ibd文件

![image-20250911105913409](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911105914729.png)

* InnoDB支持事务，行锁和外键



### （17）事务的基本特性和隔离级别

ACID

Read uncommitted

read committed :  oracle默认，解决脏读问题

repeatable read：mysql默认，解决不可重复读问题

serializable



脏读：加锁可解决 select ... for update

不可重复读: 读的时候共享锁，写的时候排他锁

幻读











### （18）分库分表

**水平**

分库：存放在多个数据库里。库多了可以缓解IO和CPU压力

分表：存放在多个表里。比如按照星期存储。 om_credit_transactions_fact_monday， om_credit_transactions_fact_tuesday

表多了可以提高sql执行效率，减轻CPU压力



**垂直**

分库：结构数据都不一样。 user_db:users,address     prodduct_db: product, comment

分表：按字段拆分成多个。

![image-20250911120927634](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911120928068.png)



### （19）主从复制，读写分离

主库的所有东西都会复制到从库里。

主库记录binlog，从库有一个线程定期从binlog拉数据

![image-20250911123205182](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911123205632.png)



![image-20250911122612185](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911122612756.png)






以下三种技术可以实现分库分表的快速操作：
mycat

shardingjdbc

mybatis-plus 分表插件，基于拦截器可以定制化，实现表名的动态化





### （20）慢sql优化

通过日志定位问题sql

业务上的优化，减少返回的行数。减少返回的字段数。

explain 索引

缓存

异步

分库分表





### （21）行锁升级成表锁的场景

1.where条件里没有用到索引，或者索引失效

![image-20250911163114999](https://gitee.com/yj1109/cloud-image/raw/master/img/20250911163115563.png)

## 2. 基本语法

### （1）inner/left/right/cross join ... on

- 内连接（INNER JOIN）只返回两个表中都有匹配的行。

- 左连接（LEFT JOIN）返回左表（这里是 Employees 表）的所有行，如果右表（这里是 EmployeeUNI 表）中没有匹配的行，则结果集中的对应列将为 NULL。

  https://leetcode.cn/problems/replace-employee-id-with-the-unique-identifier/description/?envType=study-plan-v2&envId=sql-free-50

- cross join 就是一张表计算2次  contract c1, contract c2

  https://leetcode.cn/problems/rising-temperature/description/?envType=study-plan-v2&envId=sql-free-50



### （2）datediff(date1, date2)

```sql
SELECT DATEDIFF('2025-09-26', '2025-09-25')  -- 1
SELECT DATEDIFF('2025-09-25', '2025-09-26')  -- -1

DATEDIFF('2025-09-26', date) between 0 and 29 -- 统计2025-09-26前30天的数据，包括9.26
```



### （3）having

先group by , 后having

```sql
select * from Employee e
group by e.managerId
having count(e.managerId) >= 5
```

> https://leetcode.cn/problems/managers-with-at-least-5-direct-reports/?envType=study-plan-v2&envId=sql-free-50



### （4）ifnull(x, 0)  / avg()  /  round(xx, 2)

> https://leetcode.cn/problems/confirmation-rate/?envType=study-plan-v2&envId=sql-free-50

```sql
select round(123.4567, 3)   -- 123.457            
select ifnull(null, 'input is null!')  -- input is null!
-- 3 7 2 6
SELECT AVG(user_id) avg_id FROM Signups ;  -- 结果是18 / 4 = 4.5
-- 3 7 2 6
-- SELECT AVG(user_id) avg_id FROM Signups ;  -- 结果是18 / 4 = 4.5
SELECT AVG(user_id=3) avg_id FROM Signups ;  -- 结果是user_id=3 的行数所占的比例， 1 / 4 = 0.25

```

### （5）mod(x, y)  相当于 x % y

```sql
mod(11, 2)  -- 1
```

### （6）DATE_FORMAT

```sql
select DATE_FORMAT(NOW(), '%Y-%m-%d %T')   -- 2025-09-26 05:04:04  
```

### （7）IF

```sql
select IF(3 = 122, 1, 0) from dual   -- 0.......如果3==122 ? 1 : 0
可以结合count/sum等使用:
	count(if(state='approved', 1, null))
	sum(if(state='approved', amount, 0))
```

### （8） DATE_ADD(now(), INTERVAL 1 DAY)

```sql
select DATE_ADD(now(), INTERVAL 1 DAY) -- 2025-09-27 05:24:14  当前日期是2025-09-26 05:24:14

```



### （9）SUBSTRING/UPPER/LOWER/CONCAT

* SUBSTRING(column_name, start, length)：这将从列的值中提取一个子字符串，从指定的起始位置开始，直到指定的长度。start从1开始，不像java的索引0
* SUBSTRING(column_name, start)

* UPPER(expression)：这会将字符串表达式转换为大写。

* LOWER(expression)：这会将字符串表达式转换为小写。

* CONCAT(string1, string2, ...)：这会将两个或多个字符串连接成一个字符串。
* LEFT(string, length)
* RIGHT(string, length)
* LPAD(string, length, value) 在左边填充
* RPAD  右边填充

```sql
-- select substring("abcd", 1, 2) -- ab                      
-- select substring("abcd", 2) -- bcd
-- select upper('jUN')  -- JUN
-- select CONCAT('A', 'B', 'C')  -- ABC
-- select left('abcd', 2) -- ab
-- select lpad('abcd', 10, '*') -- ******abcd
-- select lpad('abcd', 3, '*')   -- abc
```



### （10） CHAR_LENGTH

```sql
char_length('abc')   -- 3
```





# 十八、Spring Security



# 十九、Spring AI Alibaba

> bilibili: https://www.bilibili.com/video/BV1pvWGznEqh/?spm_id_from=333.1387.favlist.content.click&vd_source=b23569b676ce26126febad3c290b16e8
>
> alibaba: https://java2ai.com/?spm=5176.29160081.0.0.2856aa5cHiZsZE
>
> 版本选择： https://java2ai.com/docs/1.0.0.2/faq/?spm=5176.29160081.0.0.2856aa5cHiZsZE
>
> 获取阿里云百炼access key: https://bailian.console.aliyun.com/?tab=model#/api-key配置到电脑环境变量 ALIYUN-BAILIAN-ACCESS-KEY
>
> 模型名称：https://bailian.console.aliyun.com/?tab=model#/model-market/detail/qwen3-max
>
> qwen3-max / deepseek-v3.2-exp
>
> API url前缀示例： https://bailian.console.aliyun.com/?tab=api#/api/?type=model&url=2712576
>
> 使用SDK调用时需配置的base_url：`https://dashscope.aliyuncs.com/compatible-mode/v1`





## 1、ChatModel

> 对话模型(ChatModel)是底层接口，直接与具体大语言模型交互，
> 提供call()和stream()方法，适合简单大模型交互场景

| Spring AI Alibaba | Spring AI | Spring Boot | jdk  | meven |      |
| ----------------- | --------- | ----------- | ---- | ----- | ---- |
| 1.0.0.2           | 1.0.0     | 3.5.0       | 17   | 3.9.9 |      |







通过DashScope协议调用deepseek/qwen3-max





## 2、ollama

> Ollama本地大模型部署，大模型下载到本地用ollama启动并运行

1. 下载

https://ollama.com/download

2. 安装

指定安装目录：

```shell
OllamaSetup.exe /DIR=D:\2025\java\software\ai\Ollama
```

3. 指定大模型下载目录

   ```shell
   新建一个环境变量
   OLLAMA_MODELS
   D:\2025\java\software\ai\Ollama\models
   ```



对于 0.12.3版本，不用改环境变量，直接通过UI修改

![image-20251010153616862](https://gitee.com/yj1109/cloud-image/raw/master/img/20251010153617439.png)

3. 从远程仓库下载大模型到本地

```shell
ollama run qwen3:0.6b   -- 下载一个最小的模型
ollama ps
```

5. 运行大模型

```shell
ollama run qwen3:0.6b
```

6. 基本指令

```shell
ollama --version
ollama ps   -- 查看运行的模型
ollama list -- 查看本地仓库的模型
netstat -ano | findstr 11434   --默认端口是11434

```







## 3、ChatClient

> ChatClient是高级封装，基于ChatModel构建，适合快速构建标准化复杂AI服务，支持同步和流式交互，集成多种高级功能。



## 4、SSE(Server Sent Event)

![image-20251010163613597](https://gitee.com/yj1109/cloud-image/raw/master/img/20251010163614203.png)







## 5、prompt提示词

四大角色：

system

user

assistant：AI的返回

tool：相当于工具类utils, 比如天气查询服务



## 6、PromptTemplate

字符串占位符

```java
@GetMapping("/promptTemplate")
public Flux<String> promptTemplate(@RequestParam(value = "topic") String topic, @RequestParam(value = "count") int count) {
    PromptTemplate promptTemplate = new PromptTemplate("请给我讲一个关于{topic}的故事，字数控制在{count}内!");
    Prompt prompt = promptTemplate.create(Map.of("topic", topic, "count", count));
    return qwenChatClient.prompt(prompt)
            .stream().content();
}
```



## 7、Structured Output格式化输出

输出成一个对象

```java
@GetMapping("/output")
public Student systemAndUser(@RequestParam(value = "age") int age,
                                  @RequestParam(value = "name") String name) {
    return qwenChatClient.prompt()
            .user(new Consumer<ChatClient.PromptUserSpec>() {
                @Override
                public void accept(ChatClient.PromptUserSpec promptUserSpec) {
                    promptUserSpec.text("我的学号是10002,今年{age}岁了，名字是{name}").params(Map.of("age", age, "name", name));
                }
            }).call().entity(Student.class);
}
```





## 8、Persistent对话持久化 ChatMemory

对话持久化到redis里，下次对话可以读取历史记录

```
RedisChatMemoryRepository
MessageWindowChatMemory
```



```yml
spring:
  data:
    redis:
      host: 192.168.137.110
      port: 6379
```

```java
@Configuration
public class RedisChatMemoryRepositoryConfig {

    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public RedisChatMemoryRepository redisChatMemoryRepository() {
        return RedisChatMemoryRepository.builder().port(port).host(host).build();
    }

}
```

```java
@Bean("qwenChatClient")
public ChatClient getQwenChatClient(@Autowired @Qualifier(value = "qwenChatModel")ChatModel qwenChatModel,
                                    RedisChatMemoryRepository redisChatMemoryRepository) {

    MessageWindowChatMemory messageWindowChatMemory = MessageWindowChatMemory.builder()
            .maxMessages(10).chatMemoryRepository(redisChatMemoryRepository).build();
    return ChatClient.builder(qwenChatModel)
            .defaultAdvisors(MessageChatMemoryAdvisor.builder(messageWindowChatMemory).build())
            .build();
}
```

```java
@GetMapping("/chat/persistent")
public Flux<String> chatStream(@RequestParam(value = "content", defaultValue = "你是谁？") String content,
                               @RequestParam(value = "userId") String userId) {
    return qwenChatClient.prompt()
            .user(content)
            .advisors(new Consumer<ChatClient.AdvisorSpec>() {
                @Override
                public void accept(ChatClient.AdvisorSpec advisorSpec) {
                    advisorSpec.param(CONVERSATION_ID, userId);   // redis结构是list,  key是 spring_ai_alibaba_chat_memory:1001
                }
            })
            .stream().content();
}
```

## 9、文生图

> https://bailian.console.aliyun.com/?tab=model#/model-market/detail/wanx2.0-t2i-turbo

wanx2.0-t2i-turbo  -- 0.04元/张

```java
@GetMapping("/imageModel")
public String chatStream(@RequestParam(value = "content", defaultValue = "生成一只萨摩耶图片") String content) {
    return imageModel.call(new ImagePrompt(content))
            .getResult().getOutput().getUrl();
}
```

## 10、文生音

```java
@Resource
private SpeechSynthesisModel speechSynthesisModel;
```

## 11、向量化和向量数据库（embedding model）

文本向量化

嵌入模型



> docker run -d --name redis-stack-server -p 6379:6379 redis/redis-stack-server

RDBMS： 关系型数据库   - 精确查找

vectorDB:  向量数据库  - 相似性搜索

​	苹果 - [1,3,5,2,34,4]

淘宝搜索苹果   -- 猜你喜欢就可以用向量数据库来处理



redisStack



```yml
spring:
  ai:
    vectorstore:
      redis:
        initialize-schema: true
        index-name: my-default-index
        prefix: my-custom-prefix

  data:
    redis:
      host: 192.168.137.110
      port: 6379
```

```java
@RestController
@Slf4j
public class Embed2VectorController {
    @Resource
    private EmbeddingModel embeddingModel;

    @Resource
    private VectorStore vectorStore;

    /**
     * 文本向量化
     * http://localhost:8010/text2embed?msg=射雕英雄传
     *
     * @param msg
     * @return
     */
    @GetMapping("/text2embed")
    public EmbeddingResponse text2Embed(@RequestParam(name = "msg") String msg) {
        EmbeddingResponse embeddingResponse = embeddingModel.call(new EmbeddingRequest(List.of(msg), null));

        System.out.println(Arrays.toString(embeddingResponse.getResult().getOutput()));

        return embeddingResponse;
    }

    @GetMapping("/embed2vector/add")
    public void add() {
        List<Document> documents = List.of(
                new Document("i study LLM"),
                new Document("i love java")
        );

        vectorStore.add(documents);
    }

    @GetMapping("/embed2vector/get")
    public List getAll(@RequestParam(name = "msg") String msg) {
        SearchRequest searchRequest = SearchRequest.builder()
                .query(msg)
                .topK(2)
                .build();

        List<Document> list = vectorStore.similaritySearch(searchRequest);

        System.out.println(list);

        return list;
    }
}
```

## 12、RAG (Retrieval-Augmented Generation)检索智能增强





![image-20251016095653102](https://gitee.com/yj1109/cloud-image/raw/master/img/20251016095653562.png)



![image-20251016095707304](https://gitee.com/yj1109/cloud-image/raw/master/img/20251016095707783.png)



RAG技术就像给AI大模型装上了「实时百科大脑」，为了让大模型获取足够的上下文，以便获得更加广泛的信息源，通过先查资料后回答的机制，让AI摆脱传统模型的”知识遗忘和幻觉回复”困境

（考试准备了一个小抄，不会了先看小抄）



### （1）本地存入redisStack向量数据库，大模型学习

```txt
00000 系统OK正确执行后的返回
A0001 用户端错误一级宏观错误码
A0100 用户注册错误二级宏观错误码
B1111 支付接口超时
C2222 Kafka消息解压严重
```



### （2）通过远程知识库进行学习

![image-20251016162104433](https://gitee.com/yj1109/cloud-image/raw/master/img/20251016162104992.png)

```java
@GetMapping("/rag")
public Flux<String> rag(@RequestParam(name = "msg") String msg) {
    String systemInfo = """
            你是一个运维工程师,按照给出的编码给出对应故障解释,否则回复找不到信息。
            """;
    //1 RetrieverOptions参数配置
    DashScopeDocumentRetrieverOptions documentRetrieverOptions = DashScopeDocumentRetrieverOptions.builder()
            .withIndexName("ops-errorcode")// 百炼平台云知识库名称
            .build();

    //2 百炼平台RAG知识库构建器
    DocumentRetriever retriever = new DashScopeDocumentRetriever(dashScopeApi, documentRetrieverOptions);

    return chatClient
            .prompt()
            .system(systemInfo)
            .user(msg)
            .advisors(new DocumentRetrievalAdvisor(retriever))
            .stream()
            .content();
}
```



## 13、ToolCalling 工具调用

大模型是预训练数据，只收集知识库里截止某个时间点之前的数据 

可以指定第三方工具，让大模型进行调用，并把结果进行返回。



```java
public class DateTimeTools
{
    /**
     * 1.定义 function call（tool call）
     * 2. returnDirect
     *    true = tool直接返回不走大模型，直接给客户
     *    false = 拿到tool返回的结果，给大模型，最后由大模型回复
     */
    @Tool(description = "获取当前时间", returnDirect = false)
    public String getCurrentTime()
    {
        return LocalDateTime.now().toString();
    }
}
```

```java
@RequestMapping("callWithTool")
public Flux<String> callWithTool(@RequestParam(name = "msg", defaultValue = "现在几点了") String msg) {
    return chatClient.prompt()
            .tools(new DateTimeTools())
            .user(msg).stream().content();
}
```

## 14、MCP （Model Context Protocol）模型上下文协议

https://mcp.so/zh/servers

之前每个大模型(如DeepSeek、ChatGPT)需要为每个工具单独开发接口(Tool/FunctionCalling)，导致重复劳动

MCP 厉害的地方在于，不用重复造轮子。

过去每个软件（比如微信、Excel）都要单独给 AI 做接口，

现在 MCP 统一了标准，就像所有电器都用 USB-C 充电口，AI 一个接口就能连接所有工具



Java界的SpringCloud Openfeign，只不过Openfeign是用于微服务通讯的，
而MCP用于大模型通讯的，但它们都是为了通讯获取某项数据的一种机制

![image-20251016162545927](https://gitee.com/yj1109/cloud-image/raw/master/img/20251016162546415.png)

### （1）本地构建一个mcp server， 供自己调用

#### 1.1 server 暴露功能

```java
@Service
public class WeatherService
{
    @Tool(description = "根据城市名称获取天气预报")
    public String getWeatherByCity(String city)
    {
        Map<String, String> map = Map.of(
                "北京", "11111降雨频繁，其中今天和后天雨势较强，部分地区有暴雨并伴强对流天气，需注意",
                "上海", "22222多云,15℃~27℃,南风3级，当前温度27℃。",
                "深圳", "333333多云40天，阴16天，雨30天，晴3天"
        );
        return map.getOrDefault(city, "抱歉：未查询到对应城市！");
    }
}
```

```java
@Configuration
public class McpServerConfig
{
    /**
     * 将工具方法暴露给外部 mcp client 调用
     */
    @Bean
    public ToolCallbackProvider weatherTools(WeatherService weatherService)
    {
        return MethodToolCallbackProvider.builder()
                .toolObjects(weatherService)
                .build();
    }
}
```

```yml
spring:yml
  ai:
    mcp:
      server:
        type: async
        name: my-weather-mcp-server
        version: 1.0.0
```

#### 1.2 client引入mcp server的功能

```yml
spring:
    mcp:
      client:
        enabled: true
        type: async
        request-timeout: 60s
        toolcallback:
          enabled: true
        sse:
          connections:
            my-weather-mcp-server:
              url: http://localhost:8013
```

```java
@Bean("qwenChatClient")
public ChatClient getQwenChatClient(@Autowired @Qualifier(value = "qwenChatModel")ChatModel qwenChatModel, ToolCallbackProvider toolCallbackProvider) {
    return ChatClient.builder(qwenChatModel)
            .defaultToolCallbacks(toolCallbackProvider.getToolCallbacks()) //mcp协议，配置见yml文件
            .build();
}
```

```java
@RequestMapping("mcp")
public Flux<String> mcp(@RequestParam(name = "msg", defaultValue = "北京天气怎么样") String msg) {
    return chatClient.prompt().user(msg).stream().content();
}
```

### （2）调用远程rag server baiduMap

https://mcp.so/zh/server/baidu-map/baidu-maps

```json
{
  "mcpServers": {
    "baidu-map": {
      "command": "C:\\\\Program Files\\\\nodejs\\\\npx.cmd",
      "args": [
        "-y",
        "@baidumap/mcp-server-baidu-map"
      ],
      "env": {
        "BAIDU_MAP_API_KEY": "DpfAn2Hkvlyadx0YtYVmI4TXDgWt8Ode"
      }
    }
  }
}
```

```yml
spring:
  ai:
    mcp:
      client:
        enabled: true
        stdio:
          servers-configuration: classpath:/baidumap.json
```

```java
@Bean("qwenChatClient")
public ChatClient getQwenChatClient(@Autowired @Qualifier(value = "qwenChatModel")ChatModel qwenChatModel, ToolCallbackProvider toolCallbackProvider) {
    return ChatClient.builder(qwenChatModel)
            .defaultToolCallbacks(toolCallbackProvider.getToolCallbacks()) //mcp协议，配置见yml文件
            .build();
}
```

```java
@RequestMapping("mcp")
public Flux<String> mcp(@RequestParam(name = "msg", defaultValue = "北京天气怎么样") String msg) {
    return chatClient.prompt().user(msg).stream().content();
}
```



## 15、agent智能体  工作流

阿里云百炼平台构建一个工作流，java agent智能体调用这个工作流

![image-20251016163143217](https://gitee.com/yj1109/cloud-image/raw/master/img/20251016163143858.png)

```java
@RestController
public class MenuCallAgentController
{
    // 百炼平台的appid
    @Value("${api.healtheat.appid}")
    private String APPID;
    // 百炼云端智能体调用对象
       private DashScopeAgent agent;
    //构造方法注入，创建百炼云端智能体对象
       public MenuCallAgentController(DashScopeAgentApi agentApi)
    {
        this.agent = new DashScopeAgent(agentApi);
    }

    /**
     * http://localhost:8016/eatAgent
     * @param topic
     * @return
     */
    @GetMapping("/eatAgent")
    public String eatAgent(@RequestParam(name = "topic",defaultValue = "今天中午吃什么") String topic)
    {
        DashScopeAgentOptions options = DashScopeAgentOptions.builder().withAppId(APPID).build();

        Prompt prompt = new Prompt(topic, options);

        return agent.call(prompt).getResult().getOutput().getText();
    }
}
```
