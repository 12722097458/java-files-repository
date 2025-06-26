## Java的三大版本

* JavaSE：标准版（桌面程序，控制台开发......）
* JavaME：嵌入式开发（手机、家电......）
* JavaEE：企业级开发（Web开发、服务器开发......）

## JDK、JRE、JVM比较

* JDK : Java Development Kit(Java开发者工具)
* JRE：Java Runtime Environment(Java运行时环境)
* JVM：Java Virtual Machine(Java虚拟机)

## Java注释

1. 单行注释  		`//`
2. 多行注释         `/*    */`
3. 文档注释         `/**   */`

## Java标识符

* 标识符以字母、$或者下划线开头
* 首字符后可以加数字、字母、$或者下划线
* 不能使用关键字作为变量名或者类名

## 数据类型

> Java是一种强类型语言，变量必须先定义后使用





---

## 面向对象&面向过程

> 面向过程思想：
>
> 	1. 步骤清晰简单，第一步要做什么，第二步要做什么......
>					
> 	2. 面向过程适合处理一些较为简单的问题

> 面向对象思想：
>
> 	1. 物以类聚。分类的思想模式，对这些分类进行独立的思考。最后再对分类下的细节进行面向过程的思索。
>					
> 	2. 面向对象适合处理较为复杂的问题，适合处理需要多人协作的问题。



面向对象的编程思想（Object-Oriented Programing）OOP

面向对象的本质：以类的方式组织代码，以对象的方式封装数据。

三大特征：

1. 继承  
2. 封装
3. 多态



非静态可以调用静态方法，而静态方法不能调用非静态?

静态static方法是在类加载时直接生成，而非 静态方法必须是new后才生成，可以理解为生成时机不同，调用不存在的是不行的。



对象与类的关系：对象是具体的实物，类是对对象的抽象

![image-20250617113648223](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617113648460.png)

## 多线程

```shell
# 线程通信
*   wait():一旦执行此方法，当前线程进入阻塞状态，并且会释放同步监视器
*   notify(): 一旦执行此方法，就会唤醒被wait的一个线程。如果多个线程被wait，就会释放优先权最高的一个线程。
*   notifyAll():唤醒所有被wait的方法
*
*   1. wait();notify();notifyAll();三个方法必须在被synchronized修饰的同步代码块或同步方法中
*   2. wait();notify();notifyAll();三个方法的调用者必须是同步代码块或同步方法的同一个同步监视器。
*

wait()和sleep()的异同
同：
	1. 一旦执行此方法，都可以使当前的线程进入阻塞状态
异：
	1. 方法声明位置不同：Thread.sleep();    Object.wait()
	2. 调用的要求不同：sleep调用可以在任何需要的场景下调用，而wait()必须在被synchronized修饰的同步代码块或同步方法中
	3. 关于是否释放同步代监视器：sleep()不释放锁；wait（）会释放，且需要用notify()或notifyAll()唤醒

线程池学习
	背景：经常创建和销毁使用量特别大的资源，比如并发情况下的线程，对性能影响很大。
	思路：提前创建好多个线程，放入线程池中。使用时直接获取，使用完放回池中。可以避免重复创建和销毁，实现重复利用。（交通工具）
	好处：	
		1. 提高响应时间（减少了创建新的线程的时间）
		2. 降低资源的消耗（重复利用线程池中的线程，不需要每次都创建）
		3. 便于线程管理
			corePoolSize: 核心池的大小
			maximumPoolSize: 最大线程数
			keepAliiveTime: 线程没有任务时，最多保持多久会终止

```





## 字符集学习

1. ASCII：美国标准信息交换码，用一个字节的7位可以表示完全，只包换英文字母以及一些特殊符号。
2. GB2312：中国的中文编码表。最多两个字节表示所有字符。
3. GBK：中国的中文编码表升级，融合了更多的中文文字字符。最多两个字节编码。（1汉字：2字节）Windows中的ANSI就是GBK
4. Unicode：国际标准码，融合了人类使用的所有字符，每个字符分配唯一的字符码。
5. UTF-8：变长的编码方式，可用1-4个字节表示字符。（1个中文汉字占3字节）



## 逻辑运算

计算机中的[逻辑运算](https://so.csdn.net/so/search?q=逻辑运算&spm=1001.2101.3001.7020)又被称作为“布尔运算”，分别为：**与**、**或**，**非**，**异或**。此外在门电路中还有：**同或**、**与非**、**或非**共七种

逻辑运算只有两个布尔值：

- 0 ，表示假值（False）。
- 1 ，表示真值（True）。

### 1. 与（AND）&

| 操作数1 | 操作数2 | 结果值 |
| ------- | ------- | ------ |
| 1       | 1       | 1      |
| 1       | 0       | 0      |
| 0       | 1       | 0      |
| 0       | 0       | 0      |

### 2. 或（OR）|

| 操作数1 | 操作数2 | 结果值 |
| ------- | ------- | ------ |
| 1       | 1       | 1      |
| 1       | 0       | 1      |
| 0       | 1       | 1      |
| 0       | 0       | 0      |

### 3. 非（NOT）~

| 操作数 | 结果值 |
| ------ | ------ |
| 1      | 0      |
| 0      | 1      |

### 4. 异或（XOR）^

**相异为1，相同为0**

逻辑表达式:
![在这里插入图片描述](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617115438796.png)

| 操作数1 | 操作数2 | 结果值 |
| ------- | ------- | ------ |
| 1       | 1       | 0      |
| 1       | 0       | 1      |
| 0       | 1       | 1      |
| 0       | 0       | 0      |

```java
int a = 1 ^ 1;   // 0
int b = -1 ^ 1;  // -2
int c = 0 ^ 100;   // 100
int d = 1 ^ 0;   // 1
int e = 10 ^ 10;  // 0
```



### 5. 同或（XNOR）

逻辑表达式:
![在这里插入图片描述](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617115518515.png)

与异或运算规则相反。运算规则：**相同为1，相异为0**。

| 操作数1 | 操作数2 | 结果值 |
| ------- | ------- | ------ |
| 1       | 1       | 1      |
| 1       | 0       | 0      |
| 0       | 1       | 0      |
| 0       | 0       | 1      |

### 5.与非（NAND）

逻辑表达式:

逻辑与非运算，运算规则：先与后非（全1为0，有0为1）。也就是将两个操作数先进行“逻辑与运算”，对与“运算结果值”再进行“逻辑非运算”，产生最终的结果。

| 操作数1 | 操作数2 | 与运算结果值 | 最终结果值 |
| ------- | ------- | ------------ | ---------- |
| 1       | 1       | 1            | 0          |
| 1       | 0       | 0            | 1          |
| 0       | 1       | 0            | 1          |
| 0       | 0       | 0            | 1          |

### 6.或非（NOR）

逻辑表达式:

逻辑或非运算，运算规则：先或后非（全0为1，有1为0）。也就是将两个操作数先进行“逻辑或运算”，对“或运算结果值”再进行“逻辑非运算”，产生最终的结果。

| 操作数1 | 操作数2 | 或运算结果值 | 最终结果值 |
| ------- | ------- | ------------ | ---------- |
| 1       | 1       | 1            | 0          |
| 1       | 0       | 1            | 0          |
| 0       | 1       | 1            | 0          |
| 0       | 0       | 0            | 1          |



## String字符串学习

### 1. split()

```java
String str = "a,b,c,,,";
String[] split = str.split(",");  // length=3 末尾的空字符串默认被删除, 中间和开头的空字符串默认被保留
System.out.println(split.length);

String[] split2 = str.split(",", -1); // length = 6 使用 split(regex, -1) 可以保留所有空字符串
System.out.println("split2 = " + split2.length);
```



### 2. 字符串比较

```java
@Test
public void testString(){
    String s1 = "good";
    String s2 = "morning";
    String s3 = "goodmorning";

    String s4 = "good" + "morning";
    String s5 = s1 + "morning";
    String s6 = s1 + s2;
    /**
     * 1. 常量与常量的拼接结果放在常量池中，并且常量池中不会存在相同的内容
     * 2. 只要其中一值是变量，就会存在堆中，再指向常量池。  == 判断的是地址，所以下面案例中的后三个值为false
     * 3. 当调用s5.intern()方法时，地址指向常量池
     *
     */
    System.out.println(s4 == s3);   // true
    System.out.println(s5 == s3);   // false
    System.out.println(s6 == s3);   // false
    System.out.println(s6 == s5);   // false

    System.out.println("测试intern()方法："+(s5.intern() == s3));
}
```



## 数组

### 1. 冒泡排序

```java
@Test
public void testSort() {
    Integer[] arr = {5,3,2,1,7,9,0};
    for (int i = 0; i < arr.length - 1; i++) {
        for (int j = 0; j < arr.length - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int tmp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = tmp;
            }
        }
    }
    System.out.println(Arrays.toString(arr));
}
```

## List

### 1. ArrayList默认大小以及扩容

```java
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
```



### 2. LinkedList

```java
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
```



### 3. Vector扩容

```java
/**
 *  ==底层是：数组== protected Object[] elementData
 *  初始化长度10，扩容方式也不太一样，2倍扩容
 *  同步，线性安全。很少使用
 */
@Test
public void vectorTest() {
    List list = new Vector();
    list.add(1);
}
```

## Set

### 1. HashSet

底层是HashMap

```java
public HashSet() {
    map = new HashMap<>();
}
```



### 2. LinkedHashSet

public class LinkedHashSet<E> extends HashSet<E>

```java
public LinkedHashSet() {
    super(16, .75f, true);
}

HashSet(int initialCapacity, float loadFactor, boolean dummy) {
    map = new LinkedHashMap<>(initialCapacity, loadFactor);
}

```



### 3. TreeSet

它实现了 NavigableSet -> SortedSet 接口，基于 TreeMap 实现。默认是自然排序

```java
public TreeSet() {
    this(new TreeMap<>());
}
```

- 元素按照自然顺序或指定的比较器进行排序
- 不允许重复元素
- 不允许 null 元素（取决于比较器实现）
- 基本操作（add、remove、contains）的时间复杂度为 O(log n)

```java
// 1. 使用自然顺序（元素必须实现 Comparable 接口）
TreeSet<String> treeSet1 = new TreeSet<>();

// 2. 使用自定义比较器
TreeSet<String> treeSet2 = new TreeSet<>(Comparator.reverseOrder());

// 3. 从其他集合初始化
Set<String> otherSet = new HashSet<>(Arrays.asList("b", "a", "c"));
TreeSet<String> treeSet3 = new TreeSet<>(otherSet);
```



## Map

### 1. HashMap

第一次add初始话大小为16  -- newCap=16 newThr = 16 * 0.75 = 12

```
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
```



map添加第13个元素后开始第二次扩容 newCap = oldCap << 1 = 32， newThr = oldThr << 1 = 24   扩容完需要把old node[] 复制到 new node[]

```java
public void testHashMap(){
        Map<String,String> map = new HashMap<>();
        map.put("A","1"); // newCap=16 newThr = 16 * 0.75 = 12
        map.put("B","2");
        map.put("C","3");
        map.put("D","4");
        map.put("E","5");
        map.put("F","6");
        map.put("G","7");
        map.put("H","8");
        map.put("I","9");
        map.put("J","10");
        map.put("K","11");
        map.put("L","12");
        map.put("M","13"); // 第13次add时会扩容，newCap = oldCap << 1 = 32， newThr = oldThr << 1 = 24   扩容完需要把old node[] 复制到 new node[]
        map.put("N","14");
        map.put("O","15");
        map.put("P","16");
        map.put("P","hhhhh");
        map.put(null,"hhhhh");
        map.put("Q","17");
    }
```



1. 当同一个node长度超过8并且， 链表长度超过64时转化为红黑树TreeNode
2. 在扩容resize()后会对红黑树进行校验，如果红黑树节点数小于6时，红黑树退化为链表

```java
final Node<K,V>[] resize() {
    // ... 其他代码
    
    else if (e instanceof TreeNode)
        // 如果是树节点，调用split方法处理
        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
    
    // ... 其他代码
}
```



### 2. LinkedHashMap

有序，不重复

```java

public LinkedHashMap() {
    super();
    accessOrder = false;
}

public class LinkedHashMap<K,V> extends HashMap<K,V>
```



LinkedHashMap重写了newNode方法，用的是双向链表。所以能够实现有序

```java
Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
    LinkedHashMap.Entry<K,V> p =
        new LinkedHashMap.Entry<>(hash, key, value, e);
    linkNodeLast(p);
    return p;
}
```



### 3. TreeMap

- TreeMap是Java集合框架中的一个重要实现类，具有以下主要特性：
  1. **基于红黑树实现**：TreeMap内部使用红黑树(一种自平衡的二叉查找树)数据结构来存储键值对。
  2. **有序映射**：
     - 默认按键的自然顺序排序(键必须实现Comparable接口)
     - 也可以通过Comparator在构造时指定自定义排序规则
     - 可以方便地获取第一个(first)、最后一个(last)元素，或某一范围的子映射
  3. **时间复杂度**：
     - 基本操作(put, get, remove)的时间复杂度为O(log n)
     - 比HashMap的O(1)稍慢，但比线性结构快
  4. **非同步**：不是线程安全的，多线程环境下需要外部同步
  5. **不允许null键：但允许null值**
  6. **视图支持**：提供keySet(), values(), entrySet()等视图方法



### 4. HashTable

```java
public Hashtable() {
    this(11, 0.75f);
}
```

- **与HashMap比较**：
  - Hashtable是线程安全的，HashMap不是
  - Hashtable**不允许null键或值**，HashMap允许
  - Hashtable是早期Java版本的一部分，HashMap是Java集合框架的一部分
- **与数组比较**：
  - 数组通过整数索引访问，哈希表可以通过任意类型的键访问
  - 数组查找时间复杂度为O(1)，但插入删除可能为O(n)









​          
