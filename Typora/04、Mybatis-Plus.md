Mybatis-Plus：是一种对mybatis增强的一种工具，在mybatis的基础上只做增强不做改变，为简化提高效率而生。

视频地址：https://www.bilibili.com/video/BV1V5411K7rT?p=10

### 一、Mybatis-Plus入门

#### 1、创建数据库mybatis_plus

```shell
create database mybatis_plus;

CREATE TABLE user(
  id BIGINT (20) NOT NULL COMMENT '主键ID',
  NAME VARCHAR (30) NULL DEFAULT NULL COMMENT '姓名',
  age INT(11) NULL DEFAULT NULL COMMENT '年龄',
  email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (id)
)
INSERT INTO USER(id,NAME,age,email) VALUES
(1,'Jone',18,'test1@baomidou.com'),
(2,'Jack',20,'test2@baomidou.com'),
(3,'Tom',28,'test3@baomidou.com'),
(4,'Sandy',21,'test4@baomidou.com'),
(5,'Billie',24,'test5@baomidou.com')

```

#### 2、导入依赖

```xml
<!--mybatis-plus 数据访问-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.0</version>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.17</version>
</dependency>
```

#### 3、实体类编写

```java
package com.ityj.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;

}
```



#### 4、UserMapper编写

```java
package com.ityj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ityj.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
```

#### 5、mysql连接配置

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis_plus?useSSL=false&serverTimezone=UTC
    username: root
    password: root

mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

#### 6、测试类编写

```java
package com.ityj.mapper;

import com.ityj.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectTest() {
        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);
    }

}
```

### 二、深入学习

#### 1、主键策略

```
// 没有指定主键ID的值，mybatis-plus主动生成了一个值1384519318909337601（雪花算法：分布式 ID生成器）
// 雪花算法是由Twitter公布的分布式主键生成算法，它能保证不同表的主键之间不重复，以及相同表的主键之间有序性.

/* 以下3种类型、只有当插入对象ID 为空，才自动填充。 */
/**
 * 分配ID (主键类型为number或string）,
 * 默认实现类 {@link com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator}(雪花算法)
 *
 * @since 3.3.0
 */
```

```java
@Test
public void insertTest() {
    User user = new User();
    // 这里不指定主键id值
    user.setName("Pop");
    user.setAge(21);
    user.setEmail("sasdasd?@1e12.com");
    int insert = userMapper.insert(user);
    System.out.println("insert = " + insert);
}
```

#### 2、自动填充

表中有两个字段：create_time和update_time；现在想要实现不在业务类中设置这两个字段，在插入和修改时对其值自动填充。

（1）修改表结构以及实体类对象

```java
@Data
@ToString
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;

    // 通过注解指明自动填充的范围
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

}
```

（2）编写一个配置类组件，实现对两个字段的赋值操作

```java
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    // 插入数据时调用
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime", new Date(), metaObject);
    }

    // 修改数据时调用
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", new Date(), metaObject);
    }
}
```

（3）测试

分别调用插入和修改方法，可以看到能够按预想进行数据填充。

#### 3、MP中修改数据的乐观锁

通过加入有一个版本号实现。在数据库中加入一个字段用于控制版本号

（1）修改表结构，加入一个version字段，并修改实体类User：此字段上的注解为@Version

```java
@Data
@ToString
public class User {

    @TableId
    private Long id;
    private String name;
    private Integer age;
    private String email;

    // 通过注解指明自动填充的范围
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    // 控制版本号，防止高并发情况下数据丢失
    @Version
    @TableField(fill = FieldFill.INSERT_UPDATE)    // 修改及新增时都会走赋值逻辑
    private Long version;

}
```

（2）结合MyMetaObjectHandler对version进行初始化设置

```java
// 插入数据时调用
@Override
public void insertFill(MetaObject metaObject) {
    setFieldValByName("createTime", new Date(), metaObject);
    setFieldValByName("version", 1L, metaObject);
}
```

（3）编写一个配置类，添加乐观锁实现的一个插件

```java
@Configuration
public class MybatisPlusConfig {

    // 注册乐观锁插件(新版：3.4.0)
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor()); // 乐观锁插件
        // DbType：数据库类型(根据类型获取应使用的分页方言)
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 分页插件
        return interceptor;
    }

}
```

（4）测试

新增一个user，并进行修改，发现version自增，可实现乐观锁。

### 三、基本查询

#### 1、多个ID批量查询

```java
@Test
public void queryList() {
    List<Long> idList = new ArrayList<>();
    idList.add(1384519318909337601L);
    idList.add(1L);
    idList.add(2L);
    idList.add(3L);
    List<User> users = userMapper.selectBatchIds(idList);
    System.out.println("users = " + users);
}
```

#### 2、简单条件查询

```java
@Test
public void queryByConditionTest() {
    Map<String, Object> condition = new HashMap<>();
    condition.put("name", "Update");
    condition.put("age", 21);
    List<User> users = userMapper.selectByMap(condition);
    System.out.println("users = " + users);
}
```

#### 3、分页查询

（1）配置分页插件（类似于乐观锁的配置）

```java
// 注册乐观锁插件(新版：3.4.0)
@Bean
public MybatisPlusInterceptor mybatisPlusInterceptor(){
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor()); // 乐观锁插件
    // DbType：数据库类型(根据类型获取应使用的分页方言)
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 分页插件
    return interceptor;
}
```

（2）调用MP的接口实现分页

```java
@Test
    public void queryByPageTest() {
        // 查询第1页，每页3条数据
        Page<User> page = new Page<>(1, 3);
        Page<User> userPage = userMapper.selectPage(page, null);
        List<User> records = userPage.getRecords();
        System.out.println("records = " + records);

        long total = userPage.getTotal();
        long pages = userPage.getPages();
        long current = userPage.getCurrent();     // 当前第几页
        long size = userPage.getSize();           // 每页多少
        Long maxLimit = userPage.getMaxLimit();
        boolean b1 = userPage.hasPrevious();
        boolean b = userPage.hasNext();
        System.out.println();
    }
```

### 四、删除操作

#### 1、根据ID进行删除

```java
// 根据ID进行删除
@Test
public void deleteUserByIdTest() {
    int i = userMapper.deleteById(1385223394106130434L);
    System.out.println("i = " + i);
}
```

#### 2、批量删除

```java
// 根据ID集合，批量删除
@Test
public void deleteUserByIdListTest() {
    int count = userMapper.deleteBatchIds(Arrays.asList(1L, 2L, 3L));
    System.out.println("count = " + count);
}
```

#### 3、简单条件删除

```java
// 简单条件删除
@Test
public void deleteByConditionTest() {
    Map<String, Object> condition = new HashMap<>();
    condition.put("name", "Pop");
    condition.put("age", 21);
    int count = userMapper.deleteByMap(condition);
    System.out.println("count = " + count);
}
```

#### 4、逻辑删除

逻辑删除时针对物理删除而言，物理删除是直接从数据库中delete掉数据，而逻辑删除只是对标志位进行修改，数据没有删除。

（1）user表新增一个deleted字段：0表示未删除，1表示删除。同时修改User实体类，指明实体类的逻辑字段。

```java
@Data
@ToString
public class User {

    @TableId
    private Long id;
    private String name;
    private Integer age;
    private String email;

    // 通过注解指明自动填充的范围
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    // 控制版本号，防止高并发情况下数据丢失
    @Version
    @TableField(fill = FieldFill.INSERT_UPDATE)    // 修改及新增时都会走赋值逻辑
    private Long version;

    // 是否删除标记为 0 未删除   1 删除了
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;

}
```

添加了一个deleted字段，同时需要自动填充值0。@TableLogic注解不要忘记

```java
setFieldValByName("deleted", 0, metaObject);
```

（2）编写删除方法

```java
// 逻辑删除
@Test
public void deleteLogicTest() {
    int i = userMapper.deleteById(1385243282270961665L);
    System.out.println("i = " + i);
}
```

（3）测试

此时查询方法都会默认查询deleted=0的所有数据，而删除其实就是update,把标志位deleted设置为了1

五、复杂查询

使用到了Wrapper(QueryWrapper)

```java
// 复杂查询
    @Test
    public void queryWrapperTest() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        // 1. gt lt ge le
        userQueryWrapper.le("age", 21)
//                .likeRight("name", "San")
                .orderByDesc("age");
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println("users = " + users);

        // 2. eq ne

        // 3. like(%A%)  likeLeft(like %A)  likeRight(like A%)
        // 4. orderby  orderByDesc
    }
```

