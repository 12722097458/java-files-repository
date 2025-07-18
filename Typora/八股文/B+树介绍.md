# **B+树索引：数据库高效查询的核心**

## **1. B+树是什么？**

B+树是一种**多路平衡搜索树**，专为磁盘存储优化，是数据库索引（如MySQL InnoDB）的标准实现结构。

## **2. 为什么数据库用B+树而不用二叉树？**

| 对比维度       | **二叉树**             | **B+树**                         |
| :------------- | :--------------------- | :------------------------------- |
| **高度**       | 高（O(log₂n)）         | 极低（O(logₘn)，m≥100）          |
| **磁盘I/O**    | 每次查询需多次磁盘访问 | 减少I/O次数（3-4层可存百万数据） |
| **范围查询**   | 需中序遍历             | 叶子节点链表直接扫描             |
| **存储利用率** | 节点只存1个key         | 节点存大量key（减少树高度）      |

## **3. B+树核心结构**

text



复制



下载

```
          [内部节点]（只存key，不存数据）
         /    |     \
[叶子节点] -> [叶子节点] -> [叶子节点]（存储所有数据，并用指针串联）
```

- **内部节点**：仅存储键值（索引），用于路由
- **叶子节点**：存储完整数据（或数据指针），并按顺序链接

## **4. B+树查询过程（举例）**

假设查询`id=25`的记录：

1. 从根节点开始（如存储`[10, 20, 30]`）
2. 确定`25∈(20,30]`，进入第2个子节点
3. 在叶子节点找到`25`对应的数据（或数据地址）
4. **若范围查询（如`id BETWEEN 20 AND 30`）**：直接沿叶子节点链表扫描

## **5. B+树的优势**

- **低I/O次数**：3层B+树可支持百万级数据（假设每个节点存100个key）

  text

  

  复制

  

  下载

  ```
  100（根） × 100（中层） × 100（叶子） = 1,000,000条数据
  ```

- **适合磁盘存储**：节点大小=磁盘页大小（如16KB），减少随机I/O

- **稳定的查询效率**：任何查询最多只需`O(logₘn)`次访问（m为分叉数）

## **6. 对比其他数据结构**

| 结构       | 查询复杂度 | 范围查询             | 磁盘友好度 |
| :--------- | :--------- | :------------------- | :--------- |
| **哈希表** | O(1)       | 不支持               | 差         |
| **二叉树** | O(log₂n)   | 支持但效率低         | 差         |
| **B树**    | O(logₘn)   | 支持                 | 较好       |
| **B+树**   | O(logₘn)   | **最优**（叶子链表） | **最优**   |

## **7. 实际应用（MySQL InnoDB）**

- **主键索引**：聚簇索引（叶子节点存完整行数据）
- **二级索引**：叶子节点存主键值（需回表查询）
- **插入优化**：通过节点分裂/合并自动保持平衡

**总结**：B+树通过**多路平衡+叶子链表**的设计，在磁盘存储环境下实现了高效的**点查询和范围查询**，成为数据库索引的事实标准。