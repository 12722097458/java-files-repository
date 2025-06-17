>本文主要记录一个SpringBoot项目，是多模块化的多数据源项目。基本框架如图：

![image-20210124190520817](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162909079.png)

### 一、创建多模块化的整体框架

#### 1、创建父项目

Create New Project

![image-20210124183958563](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162916879.png)

![image-20210124184029206](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162920316.png)

![image-20210124184127682](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163114356.png)

![image-20210124184206732](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162922583.png)

删除多余的目录，只保留pom.xml

![image-20210124184334444](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162927886.png)

![image-20210124201918118](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162930671.png)

**父项目的pom.xml**

所有的子模块都需要依赖父项目

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- 项目说明：这里作为聚合工程的父工程，修改打包为pom聚合工程 -->
    <groupId>com.ityj.modules</groupId>
    <artifactId>module-parent</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>

    <!-- 继承说明：这里继承SpringBoot提供的父工程 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.2</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <!-- 模块说明：这里声明多个子模块 -->
    <modules>
        <module>common</module>
        <module>dao</module>
        <module>service</module>
        <module>web</module>
        <module>web02</module>
        <module>entrance</module>
    </modules>

    <!-- 版本说明：这里统一管理依赖的版本号,为了各个子module依赖包的一致性，避免版本不一致导致冲突问题 -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
        <mysql.version>5.1.18</mysql.version>
    </properties>

    <!--依赖说明：公共的maven库在这里添加，每个工程独有的maven包，在各个子工程添加，但是版本号都是在父工程统一配置-->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>

    </dependencies>

</project>
```

#### 2、创建公共方法common的模块

利用Spring Initializr创建。

![image-20210124203437669](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162933744.png)

![image-20210124203535649](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162936341.png)

![image-20210124203549640](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162938522.png)

![image-20210124203639569](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162940789.png)

删除多余的目录以及自动生成的Springboot入口类CommonApplication.java

添加common对应的配置文件application-common.yml

![image-20210124203902232](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163324421.png)

修改common模块的pom.xml

common模块相对独立，只是父模块的子模块，没有其他的依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<!-- 继承说明：这里继承SpringBoot提供的父工程 -->
	<parent>
		<groupId>com.ityj.modules</groupId>
		<artifactId>springboot-multi-modules-parent</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</parent>

	<artifactId>common</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>common</name>
	<description>common project for Spring Boot</description>

	<dependencies>

	</dependencies>

</project>

```

#### 3、创建数据访问层dao模块

同样利用Spring Initializr创建。

![image-20210124204553924](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162944538.png)

由于整合的数据源包括mysql和oracle，所以选择相关的starter

![image-20210124204818194](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163122905.png)

![image-20210124204827932](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162947931.png)

同样删除多余的目录以及自动生成的Springboot入口类DaoApplication.java

添加common对应的配置文件application-dao.yml

![image-20210124205006528](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617162951039.png)

修改dao模块的pom.xml

dao模块是父模块的子模块，同时需要依赖common模块

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<!-- 继承说明：这里继承SpringBoot提供的父工程 -->
	<parent>
		<groupId>com.ityj.modules</groupId>
		<artifactId>springboot-multi-modules-parent</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</parent>

	<artifactId>dao</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>dao</name>
	<description>dao project for Spring Boot</description>


	<dependencies>

		<!--引入其他模块的依赖-->
		<dependency>
			<groupId>com.ityj.modules</groupId>
			<artifactId>common</artifactId>
			<version>${project.parent.version}</version>
		</dependency>

		<!--使用mysql、oracle驱动、mybatis、以及druid-->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.1.4</version>
		</dependency>
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid-spring-boot-starter</artifactId>
			<version>1.1.10</version>
		</dependency>
		<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc8</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

</project>

```

**其他：**

由于引入了mybatis以及驱动，所以需要配置相关的数据库连接。application-dao.yml

```yml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/db1?serverTimezone=Asia/Shanghai
    username: root
    password: root

    # 连接池配置
    druid:
#      # 初始化大小，最小，最大
#      initial-size: 5
#      min-idle: 5
#      max-active: 20
#      # 配置获取连接等待超时的时间
#      max-wait: 60000
#      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位毫秒
#      time-between-eviction-runs-millis: 60000
#      # 配置一个连接在池中最小生存时间
#      min-evictable-idle-time-millis: 300000
#      validation-query: SELECT 1 FROM sys_user
#      test-while-idle: true
#      test-on-borrow: false
#      test-on-return: false
#      # 打开 PSCache，并且指定每个连接上 PSCache 的大小
#      pool-prepared-statements: true
#      max-pool-prepared-statement-per-connection-size: 20
      # 配置监控统计拦截的 Filter，去掉后监控界面 SQL 无法统计，wall 用于防火墙
      filters: stat,wall
#      # 通过 connection-properties 属性打开 mergeSql 功能；慢 SQL 记录
#      connection-properties: druid.stat.mergeSql\=true;druid.stat.slowSqlMillis\=5000
#      # 配置 DruidStatFilter
#      web-stat-filter:
#        enabled: true
#        url-pattern: /*
#        exclusions: .js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*
#      # 配置 DruidStatViewServlet
#      stat-view-servlet:
#        url-pattern: /druid/*
#        # IP 白名单，没有配置或者为空，则允许所有访问
#        allow: 127.0.0.1
#        # IP 黑名单，若白名单也存在，则优先使用
#        deny: 192.168.31.253
#        # 禁用 HTML 中 Reset All 按钮
#        reset-enable: false
#        # 登录用户名/密码
#        login-username: root
#        login-password: 123

```



#### 4、创建业务层service模块

同样利用Spring Initializr创建。

![image-20210124210558425](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163332606.png)

![image-20210124210617657](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163000495.png)

同样删除多余的目录以及自动生成的Springboot入口类ServiceApplication.java

![image-20210124210744321](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163342610.png)

修改service模块的pom.xml

* 引入父模块
* service层依赖dao层，同时也需要common模块，而dao模块已经引入了common，所有引入dao模块即可
* 删除自动生成的maven自动打包的插件。
* 引入需要的坐标

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<!-- 继承说明：这里继承SpringBoot提供的父工程 -->
	<parent>
		<groupId>com.ityj.modules</groupId>
		<artifactId>springboot-multi-modules-parent</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</parent>

	<artifactId>service</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>service</name>
	<description>service project for Spring Boot</description>

	<dependencies>
		<!--引入其他模块的依赖-->
		<dependency>
			<groupId>com.ityj.modules</groupId>
			<artifactId>dao</artifactId>
			<version>${project.parent.version}</version>
		</dependency>
		
	</dependencies>

</project>

```

#### 5、创建项目web01模块

同样利用Spring Initializr创建。这个模块就是普通的springboot项目。

![image-20210124211722360](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163140485.png)

![image-20210124211753219](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163350627.png)

![image-20210124211805475](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163154045.png)

删除多余的文件

![image-20210124211931278](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163206501.png)

其依赖的是service层：打包方式为jar

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<!-- 继承说明：这里继承SpringBoot提供的父工程 -->
	<parent>
		<groupId>com.ityj.modules</groupId>
		<artifactId>springboot-multi-modules-parent</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</parent>

	<artifactId>web01</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>web01</name>
	<description>Demo project for Spring Boot</description>
	<packaging>jar</packaging>

	<dependencies>
		<!--引入其他模块的依赖-->
		<dependency>
			<groupId>com.ityj.modules</groupId>
			<artifactId>service</artifactId>
			<version>${project.parent.version}</version>
		</dependency>
	</dependencies>

</project>

```

==其他：==写一个简单的controller

```java
package com.ityj.modules.web01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Web01HelloController {
    
    @RequestMapping(path = "/helloWeb01")
    public String helloWeb01() {
        log.info("helloWeb01............");
        return "helloWeb01...";
    }
}

```





#### 6、创建项目web02模块

和web01模块一模一样

![image-20210124220842408](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163214988.png)

==其他：==写一个简单的controller

```java
package com.ityj.modules.web01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Web01HelloController {

    // http://localhost:9191/modules/helloWeb01
    @RequestMapping(path = "/helloWeb01")
    public String helloWeb01() {
        log.info("helloWeb01............");
        return "helloWeb01...";
    }
}

```



#### 7、创建项目打包启动的入口模块entrance

同样利用Spring Initializr创建。

![image-20210124212658282](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163012229.png)

![image-20210124212728286](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163225167.png)

同样删除多余的文件

* 但是要保留EntranceApplication.java作为程序的入口。

* application.yml作为配置文件的入口，引入其他模块的配置文件。

![image-20210124212940307](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163017773.png)

>  针对入口EntranceApplication，如果不指明扫描包的路径，默认是EntranceApplication.java当前文件夹以及其子目录。此时子项目的很多目录无法被扫描，所以手动指明扫描的目录`com.ityj.modules`

```java
@SpringBootApplication
@ComponentScan(value = {"com.ityj.modules"})

public class EntranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntranceApplication.class, args);
	}

}
```

> 针对配置文件application.yml，也需要引入其他模块对应的配置文件

```yml
server:
  port: 9191
  servlet:
    context-path: /modules
spring:
  profiles:
    active: common,dao,web01,web02

```

> 针对pom.xml，入口的模块需要引入web01和web02两个主要的项目模块，同时也需要引入maven的打包插件。（后续的jar包分离需要特殊配置）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<!-- 继承说明：这里继承SpringBoot提供的父工程 -->
	<parent>
		<groupId>com.ityj.modules</groupId>
		<artifactId>springboot-multi-modules-parent</artifactId>
		<version>0.0.1-SNAPSHOT</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<artifactId>entrance</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>entrance</name>
	<description>entrance project for Spring Boot</description>
	<packaging>jar</packaging>

	<dependencies>
		<!--引入其他模块的依赖-->
		<dependency>
			<groupId>com.ityj.modules</groupId>
			<artifactId>web01</artifactId>
			<version>${project.parent.version}</version>
		</dependency>
		<dependency>
			<groupId>com.ityj.modules</groupId>
			<artifactId>web02</artifactId>
			<version>${project.parent.version}</version>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```

#### 8、启动项目

##### 1.在idea中直接启动

![image-20210124215850383](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163231827.png)

分别执行web01和web02两个模块的测试请求：

```shell
http://localhost:9191/modules/helloWeb01
http://localhost:9191/modules/helloWeb02
```

显示执行成功！

![image-20210124220006736](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163027485.png)

##### 2.利用maven打包，并通过简单的java -jar启动

在父项目中执行clean和package命令

![image-20210124220116459](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163032160.png)

成功打包后进入entrance包的目录

`D:\workspace_2021\springboot-multi-modules-demo\entrance\target`

![image-20210124221040208](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163249731.png)

进入命令窗口，执行`java -jar entrance-0.0.1-SNAPSHOT.jar`看是否成功启动：

启动成功后分别调用web01模块和web02模块的两个链接，看是否成功调用：

![image-20210124221306393](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163304609.png)

**到这来，多模块的框架已经搭建成功，具体开发可以在此基础上进行。**

### 二、多数据源的配置

> 在实际开发过程中，一个项目可能需要同时查到多个不同数据库的数据，这时就用到了多数据源。

这里主要是对dao模块进行改造，通过增加配置项的方式，实现多数据源。

最终的dao层结构如下图：

![image-20210124232726776](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163042785.png)

对应的主要文件如下：

```java
package com.ityj.modules.dao.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.ityj.modules.dao.mysqldb"}, sqlSessionFactoryRef = "mysqldbSqlSessionFactory")
public class DataSourceConfig1 {

    @Primary // 表示这个数据源是默认数据源, 这个注解必须要加，因为不加的话spring将分不清楚那个为主数据源（默认数据源）
    @Bean("mysqldbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysqldb") //读取application.yml中的配置参数映射成为一个对象
    public DataSource getmysqldbDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean("mysqldbSqlSessionFactory")
    public SqlSessionFactory mysqldbSqlSessionFactory(@Qualifier("mysqldbDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // mapper的xml形式文件位置必须要配置，不然将报错：no statement （这种错误也可能是mapper的xml中，namespace与项目的路径不一致导致）
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/mysqldb/*.xml"));
        return bean.getObject();
    }

    // 指定事务，当service层需要使用时，对于多数据源，要指定其事务管理的name
    @Primary
    @Bean(name = "mysqldbTransactionManager")
    public DataSourceTransactionManager getmysqldbTransactionManager(@Qualifier(value = "mysqldbDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean("mysqldbSqlSessionTemplate")
    public SqlSessionTemplate mysqldbSqlSessionTemplate(@Qualifier("mysqldbSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
```

```java
package com.ityj.modules.dao.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.ityj.modules.dao.oracledb", sqlSessionFactoryRef = "oracledbSqlSessionFactory")
public class DataSourceConfig2 {

    @Bean("oracledbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracledb")
    public DataSource getoracledbDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("oracledbSqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("oracledbDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/oracledb/*.xml"));
        return bean.getObject();
    }

    // 指定事务，当service层需要使用时，对于多数据源，要指定其事务管理的name
    @Bean("oracledbTransactionManager")
    public DataSourceTransactionManager getoracledbTransactionManager(@Qualifier(value = "oracledbDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("oracledbSqlSessionTemplate")
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("oracledbSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
```

```yml
spring:
  datasource:
    mysqldb:
      driver-class-name: com.mysql.jdbc.Driver
      url: jdbc:mysql://localhost:3306/ssm?serverTimezone=Asia/Shanghai
      username: root
      password: root
    oracledb:
      driver-class-name: oracle.jdbc.OracleDriver  #可配可不配，阿里的数据库连接池会通过url自动搜寻
      url: jdbc:oracle:thin:@localhost:1521:oracle
      username: c##xiao
      password: xiao
    # 连接池配置
    druid:
#      # 初始化大小，最小，最大
#      initial-size: 5
#      min-idle: 5
#      max-active: 20
#      # 配置获取连接等待超时的时间
#      max-wait: 60000
#      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位毫秒
#      time-between-eviction-runs-millis: 60000
#      # 配置一个连接在池中最小生存时间
#      min-evictable-idle-time-millis: 300000
#      validation-query: SELECT 1 FROM sys_user
#      test-while-idle: true
#      test-on-borrow: false
#      test-on-return: false
#      # 打开 PSCache，并且指定每个连接上 PSCache 的大小
#      pool-prepared-statements: true
#      max-pool-prepared-statement-per-connection-size: 20
      # 配置监控统计拦截的 Filter，去掉后监控界面 SQL 无法统计，wall 用于防火墙
      filters: stat,wall
#      # 通过 connection-properties 属性打开 mergeSql 功能；慢 SQL 记录
#      connection-properties: druid.stat.mergeSql\=true;druid.stat.slowSqlMillis\=5000
#      # 配置 DruidStatFilter
#      web-stat-filter:
#        enabled: true
#        url-pattern: /*
#        exclusions: .js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*
#      # 配置 DruidStatViewServlet
#      stat-view-servlet:
#        url-pattern: /druid/*
#        # IP 白名单，没有配置或者为空，则允许所有访问
#        allow: 127.0.0.1
#        # IP 黑名单，若白名单也存在，则优先使用
#        deny: 192.168.31.253
#        # 禁用 HTML 中 Reset All 按钮
#        reset-enable: false
#        # 登录用户名/密码
#        login-username: root
#        login-password: 123

```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.ityj.modules.dao.mysqldb.mapper.AccountMapper">

    <select id="getAccountById" parameterType="int" resultType="com.ityj.modules.dao.mysqldb.entity.Account">
        select * from account where id = #{accountId}
    </select>

</mapper>
```



其中主要是配置类的使用，是通过分包的方式进行多数据源的切换，如mysqldb包下的所有mapper访问的数据库都是mysqldb配置的数据源。这样可以实现自动切换。

三、jar包分离

可以先查看以下的笔记，后续补充。

```url
https://editor.csdn.net/md/?articleId=112132855
```

最终实现：

![image-20210124233335516](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617163314923.png)

启动脚本为：

```shell
#!/bin/bash
java -Dloader.path=./lib,./jar -jar entrance-0.0.1-SNAPSHOT.jar  --spring.config.location=./conf/application.yml,./conf/application-dao.yml,./conf/application-web01.yml,./conf/application-web02.yml 
```

可以根据实际情况，自行修改；

:alarm_clock::coffee::sleepy:



