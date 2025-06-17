### 一、 MyBatis简介

MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

### 二、简单的Mybatis项目

#### 1. 新建项目

![image-20201211224105755](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617164219556.png)

#### 2.导入依赖

```xml
主要是：mysql驱动、mybatis、junit、lombok、log4j以及druid连接池
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.ityj</groupId>
  <artifactId>spring_mybatis1208</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>spring_mybatis1208 Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>

    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.4.6</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.18</version>
    </dependency>

    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.12</version>
    </dependency>

    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.12</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/junit/junit -->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>RELEASE</version>
    </dependency>
    <!-- log4j -->
    <!-- https://mvnrepository.com/artifact/log4j/log4j -->
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
    </dependency>





  </dependencies>

  <build>
    <finalName>spring_mybatis1208</finalName>

    <!-- 加载对应位置的配置文件,因为默认情况下，maven项目只会加载resources目录下的资源 -->
    <resources>
      <resource>
        <directory>src/main/java</directory>
        <includes>
          <include>**/*.xml</include>
          <include>**/*.properties</include>
        </includes>
      </resource>
      <resource>
        <directory>src/main/resources</directory>
        <includes>
          <include>**/*.xml</include>
          <include>**/*.properties</include>
          <include>**/*.ini</include>
        </includes>
      </resource>
    </resources>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.2.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>

```

#### 3.编写mybatis主配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--1. 引入外部的配置-->
    <properties resource="common/db.properties">
        <!--自定义属性，优先级低于db.properties-->
    </properties>

    <settings>
        <!--
			
            SLF4J | LOG4J | LOG4J2 | JDK_LOGGING | COMMONS_LOGGING | STDOUT_LOGGING | NO_LOGGING
        -->
        <setting name="logImpl" value="LOG4J"/>
    </settings>
    
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
                <property name="driver" value="${druid.driver}"/>
                <property name="url" value="${druid.url}"/>
                <property name="username" value="${druid.username}"/>
                <property name="password" value="${druid.password}"/>
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

#### 4.编写实体类对象

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias(value = "account2")
public class Account {
    private int id;
    private String userName;
    private double money;
}
```

#### 5.编写mapper接口

```java
public interface AccountMapper {

    Account getAccountById(int id);

    Account getAccountByMap(Map<String, Integer> map);

    int insertAccount(Account account);

}
```

#### 6.编写对应的xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.ityj.mybatis.mapper.AccountMapper">

    <resultMap id="accountMapper" type="account2">
        <!--column是数据库中的字段名称，userName是实体类的名称。
            这里只需要配置字段不匹配的即可
        -->
        <result column="name" property="userName"/>
    </resultMap>

    <!--resultType="Account2"  配置文件＋注解的结果-->
    <select id="getAccountById" parameterType="int" resultType="Account2">
        select * from account where id = #{id}
    </select>

    <select id="getAccountByMap" parameterType="map" resultType="account2">
        /*
          这里的accountId是map中的key
        */
        select * from account where id = #{accountId}
    </select>

    <insert id="insertAccount" parameterType="account2">
        insert into account (name, money) values (#{name}, #{money});
    </insert>
</mapper>
```

#### 7.测试类编写

```java
public class MybatisUtil {

    public static SqlSession getSqlSession() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
         return sqlSession;
    }
}


package com.ityj.mybatis.mapper;

import com.ityj.mybatis.entity.Account;
import com.ityj.mybatis.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AccountMapperTest {

    @Test
    public void getAccountByIdTest() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.getAccountById(1);
        sqlSession.close();
        System.out.println("account = " + account);
    }

    @Test
    public void getAccountByMapTest() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Map<String, Integer> map = new HashMap<>();
        map.put("accountId", 2);
        Account account = accountMapper.getAccountByMap(map);
        sqlSession.close();
        System.out.println("account = " + account);
    }

    @Test
    public void insertAccountTest() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        for (int i = 0; i < 50000; i++) {
            Account account = new Account();
            //account.setName(("Jack" + (i + 1)));
            account.setMoney(Math.random() * 1000);
            accountMapper.insertAccount(account);
        }
        sqlSession.commit();
        sqlSession.close();
        System.out.println("执行成功");
    }
}
```

#### 8.总结

> 配置文件比较多，但是比较固定，要严格按照规范处理。

### 三、面向编程学Mybatis

#### 1.mybatis中mapper映射

> mapper映射可以用<package name="com.ityj.mybatis.mapper"/>直接映射这个包下所有的接口，无需单个映射。
>
> （1）但此时对应的AccountMappper.xml和AccountMpper.java必须在同一个目录下；
>
> （2）而配置文件放在java目录下，在项目启动编译时，默认不进行加载。需要在pom.xml进行配置加载java目录下的xml文件。

```xml
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


<!-- 加载对应位置的配置文件,因为默认情况下，maven项目只会加载resources目录下的资源 -->
    <resources>
      <resource>
        <directory>src/main/java</directory>
        <includes>
          <include>**/*.xml</include>
          <include>**/*.properties</include>
        </includes>
      </resource>
      <resource>
        <directory>src/main/resources</directory>
        <includes>
          <include>**/*.xml</include>
          <include>**/*.properties</include>
          <include>**/*.ini</include>
        </includes>
      </resource>
    </resources>
```



> 当然还有一种方法，可以将xml和接口完全分开，xml放入resource目录下。无需在pom.xml中导入扫描资源的配置。

**结构如图：**

![image-20201212162555818](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617164156042.png)



#### 2.xml入参以及出参类型

> 1. **入参**

+ 对于自定义类型，如果配置了实体类映射，直接写映射对应的名称即可。未配置要写类的全称：`com.ityj.mybatis.entity.User`。sql中用  #{name}-->实体类字段名称代替。

+ 对于基本类型int直接写`parameterType="int"`就行。这里的int是指Integer。`parameterType="_int"`是指int类型。单个字段的话，sql中可以用任何字符表示  #{iddddddddddddd}

+ 对于map写`parameterType="map"` sql中要用#{key（map中的key）}接收

+ 对于多个入参，而又没有封装成对象的。不写parameterType。sql中的字段必须和自定义的保持一致。

	`Account getAccountByIdAndName(@Param(value = "accountId")int id, @Param(value = "accountName")String name);`

	`SELECT * FROM account where id=#{accountId} and name=#{accountName}`

注意：

```shell
关于@param注解
1、基本类型或者String上，建议加上。
2、引用类型不需要加，也没必要加。应该不能混合使用（int + map）

关于#{} 和 ${}接收参数的区别
1、#{}与JDBC一样创建prearedStatement参数占位符并安全设置参数（就像使用 ？ 一样），安全迅速，转义字符
2、${} 采用的是字符串拼接参数的形式，不太安全，当传入参数为字段名，表名，排序方式，固定常量则可以使用。不转义字符串，有风险，同时存在sql注入，一般设置固定变量，例如字段名，

```

> 2. **出参**

+ 对于int、update、delete的返回值都是一个数字，也就不存在出参类型了。这里的出参是指select语句。
+ 如果返回值只是一个已有的实体类对象，并且实体类的字段名称和数据库字段名称一致，则可以直接用resultType="User"（已经配置了实体类映射）表示。
+ 如果字段名称不一致，则需要通过resultMap来自定义映射，并且返回时用resultMap="userMapper"
+ 如果返回的是List<Map<String,  Object>> 类型，则需要resultType=“Map”表示；同理List<USer> 需要用resultType=“User”表示

#### 3.多对一以及一对多配置文件映射

（1）准备工作

```sql
-- student和teacher表的创建
CREATE TABLE `teacher` (
  `id` INT(10) NOT NULL,
  `name` VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO teacher(`id`, `name`) VALUES (1, '秦老师'); 

CREATE TABLE `student` (
  `id` INT(10) NOT NULL,
  `name` VARCHAR(30) DEFAULT NULL,
  `tid` INT(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fktid` (`tid`),
  CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('1', '小明', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('2', '小红', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('3', '小张', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('4', '小李', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('5', '小王', '1');
```

（2）两个实体类的编写

```java
// 多对一的Student
package com.ityj.mybatis.entity;
import lombok.Data;
@Data
public class Student {
    private int id;
    private String name;
    // 多对一，多个学生对应一个老师
    private Teacher teacher;
}

// 一对多的Teacher
package com.ityj.mybatis.entity;
import lombok.Data;
import java.util.List;

@Data
public class Teacher {
    private int id;
    private String name;
    // 一对多--> 一个老师对应多个学生
    private List<Student> students;
}

```

4. **多对一配置文件编写(association和javaType)**

```java
 List<Student> getAllStudents();

    <!--用于映射不同的对象-->
    <resultMap id="studentMap" type="Student">
        <!--一般主键用id，也可以用result，结果一样的。column是数据库中字段名或者别名。property是实体类中的名称-->
        <result column="sid" property="id"/>
        <result column="sname" property="name"/>
        <!--association也可以看成一行private Teacher teacher;  多对一的   多个学生对应一个老师-->
        <association property="teacher" javaType="Teacher">
            <result column="tname" property="name"/>
        </association>
    </resultMap>
    <select id="getAllStudents" resultMap="studentMap">
        select s.id sid,s.name sname,t.name tname from student s, teacher t
        WHERE s.tid = t.id
    </select>
```

5.**一对多的配置文件编写（collection和ofType）**

```java
List<Student> getStudentsByTid(@Param(value = "teacherId") int tid);

    <resultMap id="teacherMap" type="Teacher">
        <id column="tid" property="id"/>
        <result column="tname" property="name"/>
        <!--collection表示一对多，ofType表示集合中的类型-->
        <collection property="students" ofType="Student">
            <result column="sname" property="name"/>
        </collection>
    </resultMap>

    <select id="getStudentsByTid" resultMap="teacherMap">
        select t.id tid, t.name tname, s.name sname from student s, teacher t
        WHERE s.tid = t.id and t.id = #{teacherId}
    </select>
```

#### 4.动态sql

（1）IF选择判断

```xml
<!--动态sql -》if-->
<select id="getStudentIF" resultType="student">
    SELECT * from student where 1=1
    <if test="id != null">
        and id = #{id}
    </if>
    <if test="name != null">
        and name = #{name}
    </if>
</select>
```

（2）where标签的使用避免了  1=1的编写

```xml
<!--动态sql -》if-->
<select id="getStudentIF" resultType="student">
    SELECT * from student where 1=1
    <where>
        <if test="id != null">
            <!--第一个不用加and-->
            id = #{id}
        </if>
        <if test="name != null">
            and name = #{name}
        </if>
    </where>
</select>
```

（3）choose标签，相当于java中的switch，多层判断

```xml
<select id="getStudentChoose" resultType="Student">
    SELECT * from student
    <where>
        <choose>
            <when test="id != null and id = 1">
                name = '小明'
            </when>
            <when test="id != null and id = 2">
                and name = '小红'
            </when>
            <otherwise>
                and name = '小张'
            </otherwise>
        </choose>
    </where>
</select>
```

​	（4）foreach循环遍历

```xml
<select id="getAccountsByCondtion" resultType="map" resultMap="accountMapper">
    select * from account where id > #{id} and name
    <foreach collection="names" open="in ( " item="name" separator="," close=")">
        #{name}
    </foreach>
</select>
```

<select id="getAccountsByCondtion" resultType="map" resultMap="accountMapper">
    select * from account where id > #{id} and name
    <foreach collection="names" open="in ( " item="name" separator="," close=")">
        #{name}
    </foreach>
</select>

#### 5.SQL片段

有些时候，我们可以将一些重复的sql抽取出来，方便后续使用。mybatis可以用<sql>标签来说明

1. 抽取公共部分

	```xml
	<!--固定sql片段-->
	<sql id="sql-id-choose">
	    <choose>
	        <when test="id != null and id = 1">
	            name = '小明'
	        </when>
	        <when test="id != null and id = 2">
	            and name = '小红'
	        </when>
	        <otherwise>
	            and name = '小张'
	        </otherwise>
	    </choose>
	</sql>
	```

	

2. 引用sql片段

	```shell
	<!--choose的学习，相当于jiava中的switch case  case  case default-->
	<select id="getStudentChoose" resultType="Student">
	    SELECT * from student
	    <where>
	        <!--用include标签接收动态sql-->
	        <include refid="sql-id-choose"/>
	    </where>
	</select>
	```

3. 注意事项
	* 最好抽取出来多次使用的重复的sql片段，不要太复杂。
	* 最好不好抽取出来where标签的代码，此代码需要根据不同请求做不同的判断，一般不可以复用。

### 四、mybatis缓存

#### 1.一级缓存

1. 默认是一级缓存（本地缓存）

> 如果同样的条件，调用一个dao层查询接口（连续两次select），最终只查询数据库一次。

2. 缓存失效的情况

	（1）执行了增删改操作。可能会改变原有缓存中的结果，所以必定刷新缓存。

	（2）手动清理缓存`sqlSession.clearSession();`

> 总结：一级缓存默认是开启的，只在一次sqlSession中生效，也就是拿到连接到关闭连接这个区间段。

3. 测试

```java
/**
     * 一级缓存测试。
     * mybatis默认是一级缓存，所以不需要其他操作就可以测试了。
     */
@Test
public void cacheOneClassTest() {
    SqlSession sqlSession = MybatisUtil.getSqlSession();

    AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);

    Account account = accountMapper.getAccountById(1);
    Account account2 = accountMapper.getAccountById(1);
    System.out.println("account2 == account2  : " + (account == account2));   //true

    sqlSession.close();
}
```

![image-20201213143805228](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617164232485.png)

**可以看到，对于同一个sqlSession，同样的查询条件，在第二次查询时没有再次访问数据库，而只是从缓存中取出了数据。减少了交互，提升了效率。**

#### 2.二级缓存

* 二级缓存也叫全局缓存，一级缓存针对的是一个sqlSession的会话，一级缓存作用域太小了。所以出现了二级缓存。
* 二级缓存是基于单个namespace的，一个名称空间对应一个二级缓存。需要单个配置
* 工作机制
	* 一个会话查询一条数据，这条数据就会放入一级缓存中。
	* 如果当前会话结束，对应的一级缓存随之消失。我们想要的是在会话结束时，将此缓存保存到二级缓存中。
	* 如果再有查询已经执行过的语句，就可以从二级缓存中加载。
	* 不同的mapper查询出的数据会放在对应的缓存（cache）中。

开启二级缓存的步骤：

​	（1）在mybatis-config.xml中设置显式开启全局缓存配置。（默认已经开启，可以不配置）

```xml
    <settings>
        <!--
            SLF4J | LOG4J | LOG4J2 | JDK_LOGGING | COMMONS_LOGGING | STDOUT_LOGGING | NO_LOGGING
        -->
        <setting name="logImpl" value="LOG4J"/>
        <!--显式配置全局缓存，默认就是true-->
        <setting name="cacheEnabled" value="true"/>

    </settings>
```

​	（2）在对应的XxxMapper.xml中配置开启二级缓存（基于单个namespace的）

```xml
    <!--在当前mapper.xml中使用二级缓存-->
    <cache readOnly="true"/>
```

​	（3）测试

```java
	/**
     * 二级缓存测试。
     * 二级缓存的开启需要在AccountMapper.xml之类的配置文件中开启，开启方式比较简单：添加标签即可<cache />
     *
     */
    @Test
    public void cacheTwoClassTest() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();

        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.getAccountById(1);
        sqlSession.close();

        SqlSession sqlSession2 = MybatisUtil.getSqlSession();
        AccountMapper accountMapper2 = sqlSession2.getMapper(AccountMapper.class);
        Account account2 = accountMapper2.getAccountById(1);
        System.out.println(account.hashCode());
        System.out.println(account2.hashCode());
        System.out.println(account.equals(account2));
        System.out.println("account == account2  : " + (account == account2));

        sqlSession2.close();
    }
```

![image-20201213154912008](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617164204137.png)

**结论：**发现两个sqlSession查询同一个条件，最终只有一次调用数据库，说明二级缓存起了作用。

二级缓存是在一个sqlSession销毁时开始起作用，销毁时会将一级缓存的内容复制到二级缓存中，再次查询时，还是走的缓存。

注意：实体类对象要序列化。

> 1.先查二级缓存，有的话直接取。2.没有查一级缓存，有的话直接取。3.没有的话去查数据库。