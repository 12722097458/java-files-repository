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


setnx : set if not exist
<h2>**各种数据类型操作对比：**<h2>

|                     | 增                                          | 查                                           | 删                         |
| ------------------- |--------------------------------------------| -------------------------------------------- | -------------------------- |
| string：字符串      | set name Jack   // mset k1 v1 k2 v2        | get name                                     | del name                   |
| hash：散列          | hset myhash name Jack / hset myhash age 16 | hget myhash name  /  hget all myhash查询所有 | hdel myhash name           |
| list：列表，可重复  | lput mylist a / rput list m                | lrange mylist 0 -1                           | lpop mylist  / rpop mylist |
| set：集合，不可重复 | sadd myset v                               | smembers myset                               | srem myset v:删除某个元素  |
| sortedset：有序集合 | zadd mysortedset 16 value                  | zrange mysortedset 0 -1                      | zrem mysortedset  value    |







### 一、通用命令 (General)

这些命令适用于所有类型的 Key。

| 命令           | 语法                   | 描述                                           | 示例                      |
| :------------- | :--------------------- | :--------------------------------------------- | :------------------------ |
| **`DEL`**      | `DEL key [key ...]`    | 删除一个或多个 key                             | `DEL user:1000`           |
| **`EXISTS`**   | `EXISTS key [key ...]` | 检查一个或多个 key 是否存在                    | `EXISTS website`          |
| **`EXPIRE`**   | `EXPIRE key seconds`   | 为 key 设置过期时间（秒）                      | `EXPIRE session:abc 3600` |
| **`TTL`**      | `TTL key`              | 查看 key 的剩余生存时间（秒）                  | `TTL session:abc`         |
| **`PERSIST`**  | `PERSIST key`          | 移除 key 的过期时间，使其永久有效              | `PERSIST mykey`           |
| **`KEYS`**     | `KEYS pattern`         | 查找所有符合给定模式 `pattern` 的 key          | `KEYS user:*`             |
| **`TYPE`**     | `TYPE key`             | 返回 key 所存储的值的类型                      | `TYPE user:1000`          |
| **`RENAME`**   | `RENAME key newkey`    | 修改 key 的名称                                | `RENAME oldname newname`  |
| **`FLUSHALL`** | `FLUSHALL [ASYNC]`     | **清空整个 Redis 服务器的数据**（谨慎使用！）  | `FLUSHALL`                |
| **`FLUSHDB`**  | `FLUSHDB [ASYNC]`      | 清空当前数据库的所有数据（谨慎使用！）         | `FLUSHDB`                 |
| **`SELECT`**   | `SELECT index`         | 切换数据库，Redis 默认有 16 个数据库 (0-15)    | `SELECT 1`                |
| info           | info                   | 查看在哪个DB    db0:keys=4,expires=0,avg_ttl=0 |                           |



> **警告:** `KEYS *` 命令在生产环境慎用，因为当 Key 数量很大时会导致 Redis 服务短暂阻塞。建议使用 `SCAN` 命令进行迭代式查询。

------

### 二、字符串 (String)

最简单的键值对类型，值可以是字符串、整数或浮点数。

| 命令         | 语法                                          | 描述                                                         | 示例                                  |
| :----------- | :-------------------------------------------- | :----------------------------------------------------------- | :------------------------------------ |
| **`SET`**    | `SET key value [EX seconds][PX milliseconds]` | 设置指定 key 的值                                            | `SET name "Alice" EX 10`              |
| **`GET`**    | `GET key`                                     | 获取指定 key 的值                                            | `GET name`                            |
| **`MSET`**   | `MSET key value [key value ...]`              | 同时设置一个或多个 key-value 对                              | `MSET a 1 b 2 c 3`                    |
| **`MGET`**   | `MGET key [key ...]`                          | 获取所有(一个或多个)给定 key 的值                            | `MGET a b c`                          |
| **`SETNX`**  | `SETNX key value`                             | **SET if Not eXists**，只有在 key 不存在时设置值（常用于分布式锁） | `SETNX lock:order 1`                  |
| **`INCR`**   | `INCR key`                                    | 将 key 中储存的数字值增一                                    | `SET counter 10` `INCR counter` -> 11 |
| **`DECR`**   | `DECR key`                                    | 将 key 中储存的数字值减一                                    | `DECR counter` -> 10                  |
| **`INCRBY`** | `INCRBY key increment`                        | 将 key 所储存的值加上给定的增量值                            | `INCRBY counter 5` -> 15              |
| **`APPEND`** | `APPEND key value`                            | 如果 key 已经存在并且是一个字符串，将指定的 value 追加到该 key 原值的末尾 | `APPEND name " Smith"`                |

------

### 三、哈希 (Hash)

类似于 Map 结构，适合存储对象。

| 命令          | 语法                              | 描述                                           | 示例（存储一个用户对象）                           |
| :------------ | :-------------------------------- | :--------------------------------------------- | :------------------------------------------------- |
| **`HSET`**    | `HSET key field value`            | 将哈希表 key 中的字段 `field` 的值设为 `value` | `HSET user:1000 name "Bob"/ HSET user:1000 age 30` |
| hmset         | `HSET key field value [field...]` | 同时放入多个key-value                          | hmset user:1002 name Rose age 22                   |
| **`HGET`**    | `HGET key field`                  | 获取存储在哈希表中指定字段的值                 | `HGET user:1000 name` -> "Bob"                     |
| **`HGETALL`** | `HGETALL key`                     | 获取在哈希表中指定 key 的所有字段和值          | `HGETALL user:1000`                                |
| **`HDEL`**    | `HDEL key field [field ...]`      | 删除一个或多个哈希表字段                       | `HDEL user:1000 age`                               |
| **`HKEYS`**   | `HKEYS key`                       | 获取哈希表中的所有字段名                       | `HKEYS user:1000`                                  |
| **`HVALS`**   | `HVALS key`                       | 获取哈希表中的所有值                           | `HVALS user:1000`                                  |
| **`HINCRBY`** | `HINCRBY key field increment`     | 为哈希表 key 中的指定字段的整数值加上增量      | `HINCRBY user:1000 age 1`                          |

------

### 四、列表 (List)

简单的字符串列表，按插入顺序排序，可从两端插入/弹出。

| 命令         | 语法                              | 描述                                     | 示例（像一个队列）             |
| :----------- | :-------------------------------- | :--------------------------------------- | :----------------------------- |
| **`LPUSH`**  | `LPUSH key element [element ...]` | 将一个或多个值插入到列表**头部**（左边） | `LPUSH tasks "task1"`          |
| **`RPUSH`**  | `RPUSH key element [element ...]` | 将一个或多个值插入到列表**尾部**（右边） | `RPUSH tasks "task2"`          |
| **`LPOP`**   | `LPOP key [count]`                | 移除并获取列表的**第一个**元素（左边）   | `LPOP tasks` -> "task1"        |
| **`RPOP`**   | `RPOP key [count]`                | 移除并获取列表的**最后一个**元素（右边） | `RPOP tasks` -> "task2"        |
| **`LRANGE`** | `LRANGE key start stop`           | 获取列表指定范围内的元素                 | `LRANGE tasks 0 -1` (获取所有) |
| **`LLEN`**   | `LLEN key`                        | 获取列表长度                             | `LLEN tasks`                   |

------

### 五、集合 (Set)

String 类型的**无序集合**，通过哈希表实现，**元素不重复**。

| 命令            | 语法                           | 描述                                  | 示例（存储标签）            |
| :-------------- | :----------------------------- | :------------------------------------ | :-------------------------- |
| **`SADD`**      | `SADD key member [member ...]` | 向集合添加一个或多个成员              | `SADD tags redis db mysql`  |
| **`SMEMBERS`**  | `SMEMBERS key`                 | 返回集合中的所有成员                  | `SMEMBERS tags`             |
| **`SISMEMBER`** | `SISMEMBER key member`         | 判断 member 元素是否是集合 key 的成员 | `SISMEMBER tags redis` -> 1 |
| **`SREM`**      | `SREM key member [member ...]` | 移除集合中一个或多个成员              | `SREM tags db`              |
| **`SINTER`**    | `SINTER key [key ...]`         | 返回多个集合的**交集**                | `SINTER set1 set2`          |
| **`SUNION`**    | `SUNION key [key ...]`         | 返回多个集合的**并集**                | `SUNION set1 set2`          |

------

### 六、有序集合 (Sorted Set / ZSet) 

与 Set 类似，但每个成员都会关联一个 `score`（分数），用于排序。元素不重复，但 `score` 可以重复。

| 命令            | 语法                                                         | 描述                                                  | 示例（排行榜）                          |
| :-------------- | :----------------------------------------------------------- | :---------------------------------------------------- | :-------------------------------------- |
| **`ZADD`**      | `ZADD key [NX XX] [GT LT] [CH] [INCR] score member [score member ...]` | 向有序集合添加一个或多个成员，或更新已存在成员的分数  | `ZADD leaderboard 100 "Alice" 90 "Bob"` |
| **`ZRANGE`**    | `ZRANGE key start stop [WITHSCORES]`                         | 通过索引区间返回有序集合指定区间内的成员（低到高）    | `ZRANGE leaderboard 0 -1 WITHSCORES`    |
| **`ZREVRANGE`** | `ZREVRANGE key start stop [WITHSCORES]`                      | 返回有序集中指定区间内的成员，通过分数从高到低排序    | `ZREVRANGE leaderboard 0 2` (Top 3)     |
| **`ZRANK`**     | `ZRANK key member`                                           | 返回有序集合中指定成员的**排名**（低到高，从 0 开始） | `ZRANK leaderboard "Bob"` -> 1          |
| **`ZREVRANK`**  | `ZREVRANK key member`                                        | 返回有序集合中指定成员的排名（高到低）                | `ZREVRANK leaderboard "Alice"` -> 0     |
| **`ZSCORE`**    | `ZSCORE key member`                                          | 返回有序集中，成员的分数值                            | `ZSCORE leaderboard "Alice"` -> "100"   |

------

### 七、流 (Stream) (Redis 5.0+)

主要用于消息队列（Message Queue），提供了更强大的持久化消息功能。

| 命令             | 语法                                                         | 描述                                     | 示例                                                    |                                            |
| :--------------- | :----------------------------------------------------------- | :--------------------------------------- | :------------------------------------------------------ | ------------------------------------------ |
| **`XADD`**       | `XADD stream [NOMKSTREAM] [MAXLEN                            | MINID] id field value [field value ...]` | 添加消息到流末尾                                        | `XADD mystream * sensor-id 1234 temp 19.8` |
| **`XREAD`**      | `XREAD [COUNT count] [BLOCK milliseconds] STREAMS key [key ...] id [id ...]` | 从一个或多个流中读取消息                 | `XREAD COUNT 2 STREAMS mystream 0`                      |                                            |
| **`XGROUP`**     | `XGROUP CREATE stream groupname id`                          | 创建消费者组                             | `XGROUP CREATE mystream mygroup $`                      |                                            |
| **`XREADGROUP`** | `XREADGROUP GROUP group consumer [COUNT count] [BLOCK ms] STREAMS stream [stream ...] id [id ...]` | 通过消费者组读取消息                     | `XREADGROUP GROUP mygroup consumer1 STREAMS mystream >` |                                            |

------

### 八、发布订阅 (Pub/Sub)

用于消息的发布和订阅。

| 命令              | 语法                                  | 描述                     | 示例                         |
| :---------------- | :------------------------------------ | :----------------------- | :--------------------------- |
| **`SUBSCRIBE`**   | `SUBSCRIBE channel [channel ...]`     | 订阅一个或多个频道的信息 | `SUBSCRIBE news`             |
| **`PUBLISH`**     | `PUBLISH channel message`             | 将信息发送到指定的频道   | `PUBLISH news "Hello World"` |
| **`UNSUBSCRIBE`** | `UNSUBSCRIBE [channel [channel ...]]` | 退订给定的频道           | `UNSUBSCRIBE news`           |





