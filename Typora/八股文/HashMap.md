# HashMap介绍及扩容机制

## HashMap基本介绍

HashMap是Java集合框架中最重要的数据结构之一，它基于哈希表实现，提供了高效的键值对存储和检索功能。

### 核心特点：

1. **键值对存储**：存储的是Key-Value对
2. **允许null键和null值**：HashMap允许一个null键和多个null值
3. **非线程安全**：不是同步的，多线程环境下需要额外同步
4. **无序**：不保证元素的顺序
5. **快速访问**：理想情况下，get和put操作的时间复杂度为O(1)

## HashMap内部结构

### JDK 1.7及之前

- 数组 + 链表实现
- 使用Entry类存储键值对

### JDK 1.8及之后

- 数组 + 链表 + 红黑树实现
- 使用Node类存储键值对（链表节点）和TreeNode（红黑树节点）
- 当链表长度超过阈值(默认为8)且数组长度大于等于64时，链表转换为红黑树
- 当红黑树节点数小于6时，红黑树退化为链表

## HashMap扩容机制

### 扩容触发条件

1. 当HashMap中的元素数量超过`容量(capacity) * 负载因子(load factor)`时触发扩容
   - 默认容量：16
   - 默认负载因子：0.75
   - 例如：默认情况下，当元素数量超过12(16*0.75)时触发扩容
2. 当链表长度超过8但数组长度小于64时，会优先扩容而不是树化

### 扩容过程

1. 创建一个新的Entry数组，容量是原数组的2倍
2. 重新计算所有元素在新数组中的位置（rehash）
3. 将原有元素转移到新数组中

### JDK 1.8优化

JDK 1.8对扩容进行了优化：

- 通过高位运算`(e.hash & oldCap) == 0`判断元素位置是否需要改变
- 不需要重新计算hash值，元素要么在原位置，要么在原位置+旧容量的位置
- 减少了重新计算hash带来的性能消耗

## 常见面试问题

1. **HashMap的工作原理是什么？**
   - 基于哈希表实现，通过hash算法确定键值对的存储位置
   - 使用链表或红黑树解决哈希冲突
2. **HashMap的put方法流程？**
   - 计算key的hash值
   - 如果数组为空则初始化
   - 计算数组下标，如果该位置为空，直接插入
   - 如果该位置不为空，遍历链表/红黑树
   - 如果找到相同key则替换value
   - 如果没有找到则在链表/红黑树中插入新节点
   - 判断是否需要扩容
3. **为什么HashMap的容量是2的幂次方？**
   - 方便通过位运算计算下标：index = (n - 1) & hash
   - 使元素分布更均匀，减少哈希冲突









# 当红黑树节点数小于6时，红黑树退化为链表   对应的源码







在JDK 1.8的HashMap实现中，当红黑树节点数小于6时，红黑树会退化为链表。这个逻辑主要在`resize()`方法和`TreeNode.split()`方法中实现。以下是相关源码分析：

## 1. 在`resize()`方法中的退化逻辑



```
final Node<K,V>[] resize() {
    // ... 其他代码
    
    else if (e instanceof TreeNode)
        // 如果是树节点，调用split方法处理
        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
    
    // ... 其他代码
}
```

## 2. `TreeNode.split()`方法中的退化逻辑



```
final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
    TreeNode<K,V> b = this;
    // 重新建立链表
    TreeNode<K,V> loHead = null, loTail = null;
    TreeNode<K,V> hiHead = null, hiTail = null;
    int lc = 0, hc = 0;
    
    // 遍历树节点，重新计算位置
    for (TreeNode<K,V> e = b, next; e != null; e = next) {
        next = (TreeNode<K,V>)e.next;
        e.next = null;
        if ((e.hash & bit) == 0) {
            if ((e.prev = loTail) == null)
                loHead = e;
            else
                loTail.next = e;
            loTail = e;
            ++lc;
        }
        else {
            if ((e.prev = hiTail) == null)
                hiHead = e;
            else
                hiTail.next = e;
            hiTail = e;
            ++hc;
        }
    }

    // 检查是否需要退化为链表
    if (loHead != null) {
        // 如果节点数小于等于6，退化为链表
        if (lc <= UNTREEIFY_THRESHOLD)
            tab[index] = loHead.untreeify(map);
        else {
            tab[index] = loHead;
            // 检查hiHead是否为空，如果不为空则需要重新树化
            if (hiHead != null)
                loHead.treeify(tab);
        }
    }
    if (hiHead != null) {
        // 同样处理hiHead
        if (hc <= UNTREEIFY_THRESHOLD)
            tab[index + bit] = hiHead.untreeify(map);
        else {
            tab[index + bit] = hiHead;
            if (loHead != null)
                hiHead.treeify(tab);
        }
    }
}
```

## 3. `UNTREEIFY_THRESHOLD`常量定义



```
static final int UNTREEIFY_THRESHOLD = 6;
```

## 4. `untreeify()`方法实现



```
final Node<K,V> untreeify(HashMap<K,V> map) {
    Node<K,V> hd = null, tl = null;
    // 遍历树节点，转换为普通链表节点
    for (Node<K,V> q = this; q != null; q = q.next) {
        Node<K,V> p = map.replacementNode(q, null);
        if (tl == null)
            hd = p;
        else
            tl.next = p;
        tl = p;
    }
    return hd;
}
```

## 关键点总结：

1. 退化阈值定义为`UNTREEIFY_THRESHOLD = 6`
2. 主要在扩容时(`resize()`)的`split()`方法中检查是否需要退化
3. 当树节点数≤6时，调用`untreeify()`方法将树节点转换为普通链表节点
4. 转换过程是遍历树节点，创建新的普通链表节点并连接起来

这个设计是为了平衡查询效率和空间开销，避免在小数据量下维护红黑树带来的额外开销。