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

![image-20250709142412360](C:\Users\yinjun\AppData\Roaming\Typora\typora-user-images\image-20250709142412360.png)

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

    public class Person implements Serializable {
    private  static final long serialVersionUID = 42324234232L;

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



## 4. Servlet生命周期

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



LifeCycleFilter constructor...
LifeCycleFilter init = ApplicationFilterConfig[name=com.ityj.filter.LifeCycleFilter, filterClass=com.ityj.filter.LifeCycleFilter]
ConfigServlet constructor
ConfigServlet init

before LifeCycleFilter.doFilter...
before MyFilter.doFilter...
into HelloServlet.service()...
k2 = v2
end MyFilter.doFilter...
end LifeCycleFilter.doFilter...

ConfigServlet destroy
LifeCycleFilter destroy...





## 16. Listener

![image-20250715163417835](https://gitee.com/yj1109/cloud-image/raw/master/img/20250715163418107.png)

```
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



### （8）自动注入(autowire="byName")

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
