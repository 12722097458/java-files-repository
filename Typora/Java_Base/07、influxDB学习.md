## 一、时序数据库InfluxDb的介绍及安装

==什么是时序数据库？==

时序数据库是近几年一个新的概念，与传统的Mysql关系型数据库相比，它的最大的特点是：**数据按照时间顺序存储。是一个用于存储和分析时间序列数据的开源数据库**。

举例来说，日志数据，是以时间顺序存储的，所以用时序数据库存储是一种很好的选择。使用Mysql在存储的过程中，不是对这种基于时间的数据进行优化的，所以，在查询、插入上有一些瓶颈。

==InfluxDB的特点==

InfluxDB有很多特点，如下：

- 内置HTTP接口，使用方便
- 数据可以打标记，这样查询可以很灵活
- 类似SQL的查询语句
- 安装管理很简单，并且读写数据很高效
- 能够实时查询，数据在写入时被索引后就能够被立即查出

1、Centos6.5环境下安装

```bash
yum -y install wget

wget https://dl.influxdata.com/influxdb/releases/influxdb-1.5.4.x86_64.rpm

sudo yum localinstall influxdb-1.5.4.x86_64.rpm
```

influxd config      --可以用来查看配置

2、influxd    启动服务 (注意检查防火墙状态！service iptables stop)，启动后对于1.2.4及以下版本的influxdb，可以登录http://192.168.189.128:8083/进行可视化操作，或者下载本地的工具InfluxDBStudio进行本地操作（相当于mysql的可视化工具Navicat）

```bash
service influxdb start
service influxdb restart
service influxdb stop
service influxdb status
```



3、influx      连接服务，默认8086端口。

准备好接收influx的查询语句(简称InfluxQL)，用`exit/quit`可以退出命令行。

## 二、influxdb快速入门指南

1、创建一个数据库

```sql
create database influxDatabase;    -- 创建
show databases;					   -- 查看所有数据库，默认有一个_internal数据库。是用来存储InfluxDB内部的实时监控数据

use influxDatabase;      		   --切换并使用influxDatabase数据库。
show measurements				   --查看此数据库的表  相当于MySQL中的 show tables;

```

2、数据表基本介绍

、、、

3、CR_ud

时间序列数据通常是一次写入，很少更新。

```sql
--插入数据
insert cpu,host=serverA,region=CN,msg="加油world!" value="0.88"
目前我的经验:
	tag只能是字符串，所以插入的时候不要写双引号了""，比如上面的host=serverA，不要写host="serverA"。
		a、写成host=serverA，查询时语句为 ： select * from cpu where host='serverA'       --这里时单引号
		b、写成host='serverA'  查询语句就变成：select * from cpu where host='"serverA"'   --这里是单引套双引


--查看数据
select host,region,msg,value from cpu;
select * from cpu;
```

注意：

- 如果两个insert语句对应的measurement（即表）相同(即time，和所有tag都相同)，则会进行覆盖。

- 大小写敏感

- ![image-20200622111440933](D:\我的文件\Typora\文档\4、influxDB学习.assets\image-20200622111440933.png)

- measurement名称，tag keys，tag value和field key不用单双引号。InfluxDB会假定引号是名称的一部分                                           对应的tag插入数据时不要加引号""![image-20200616093025358](C:\Users\86152\AppData\Roaming\Typora\typora-user-images\image-20200616093025358.png)

- 语句的结尾不要写分号了，其实在之前工作里java代码中的相关sql最后也不需要写分号。![image-20200616093820532](C:\Users\86152\AppData\Roaming\Typora\typora-user-images\image-20200616093820532.png)

- 特殊字符有：

  ```xml
  逗号,
  等号=
  空格 
  双引号"
  都要用反斜杠\来进行转义
  ```

  

## 三、influxdb写入数据

1、使用HTTP接口创建数据库

```bash
#先退出influx的操作界面，再执行以下的命令，即可创建mydb数据库。  query请求地址 以及q=的q都不能省略
curl -i -XPOST http://localhost:8086/query --data-urlencode "q=CREATE DATABASE mydb"
```

2.使用HTTP请求写入数据

```bash
#写入单行数据
curl -i -XPOST 'http://localhost:8086/write?db=mydb' --data-binary 'mytable,host=007,region=CH value=201 1434055562000000000'
#写入多行数据，换行表示。   \+Enter 换行
curl -i -XPOST 'http://localhost:8086/write?db=mydb' --data-binary 'cpu_load_short,host=server02 value=0.67
cpu_load_short,host=server02,region=us-west value=0.55 1422568543702900257
cpu_load_short,direction=in,host=server01,region=us-west value=2.0 1422568543702900257'
```

3.使用HTTP请求将外部文件中的SQL写入到数据库

首先创建一个文件：influxfile.txt

```bash
cpu_load,host=server02 value=1.67
cpu_load,host=server02,region=us-west value=1.55 1422568543702900257
cpu_load,direction=in,host=server01,region=us-west value=12.0 1422568543702900257
```

然后执行，即可将外部的sql写入到程序中。

```bash
#文件的路径好像没什么要求，不需要在当前文件夹下
curl -i -XPOST 'http://localhost:8086/write?db=mydb' --data-binary @influxfile.txt
```

4.导入数据到InfluxDb中

```bash
#下载一份样例数据：
curl https://s3.amazonaws.com/noaa.water-database/NOAA_data.txt -o NOAA_data.txt
#将数据导入到influxdb中，执行influx -import命令
influx -import -path=NOAA_data.txt -precision=s -database=NOAA_water_database
```



## 四、influxdb查询数据

1、使用HTTP Rest接口查询数据

- 可以查询出数据，并以Json字符串的格式展示出来。

```bash
curl -G 'http://localhost:8086/query?pretty=true' --data-urlencode "db=mydb" --data-urlencode "q=SELECT * FROM cpu_load_short"
```

注意：

​	**pretty=true**是为了让结果展示格式化，看起来更加清晰，一般可以不需要。格式化的过程会消耗不必要的网络带宽。

2、查询多个语句，就是在两个sql语句前用分号；隔开。

```bash
curl -G 'http://localhost:8086/query?pretty=true' --data-urlencode "db=mydb" --data-urlencode "q=SELECT * FROM cpu_load_short;select \"id\",\"name\",\"value\" from cpu"
```

注意：

- cpu表中的name字段应该是一个关键字，我们查询的时候如果涉及到的话，就需要加上引号。
- 查询语句中要加上**value**这个字段，否则语句的返回值将为空。
- 注意大小写以及中英文标点符号。

3、时间戳格式:

在InfluxDB中的所有数据都是存的UTC时间，时间戳默认返回RFC3339格式的纳米级的UTC时间，例如`2015-08-04T19:05:14.318570484Z`，如果你想要返回Unix格式的时间，可以在请求参数里设置`epoch`参数，其中epoch可以是`[h,m,s,ms,u,ns]`之一。例如返回一个秒级的epoch：

```bash
curl -G 'http://localhost:8086/query' --data-urlencode "db=mydb" --data-urlencode "epoch=s" --data-urlencode "q=select \"id\",\"name\",\"value\" from cpu"
```



## 五、influxdb采样和数据保留（CQ和RP）

**https://www.hellodemos.com/hello-influxdb/influxdb-downsampling-and-retention.html**

1、连续查询 **Continuous Query (CQ)**是在数据库内部自动周期性跑着的一个InfluxQL的查询，CQs需要在`SELECT`语句中使用一个函数，并且一定包括一个`GROUP BY time()`语句。

2、保留策略 **Retention Policy (RP)**是InfluxDB数据架构的一部分，它描述了InfluxDB保存数据的时间。InfluxDB会比较服务器本地的时间戳和请求数据里的时间戳，并删除比你在RPs里面用`DURATION`设置的更老的数据。一个数据库中可以有多个RPs但是每个数据库的RPs是唯一的。

3、数据实现

4、创建数据库

```bash
create database food_data;
```

5、创建一个两个小时的默认RP

如果我们写数据的时候没有指定RP的话，InfluxDB会使用默认的RP，我们设置默认的RP是两个小时。使用`CREATE RETENTION POLICY`语句来创建一个默认RP:

```bash
create retention policy "two hours" on "food_data" duration 2h replication 1 default;
```

这个RP的名字叫**two_hours**作用于**food_data**数据库上，`two_hours`保存数据的周期是两个小时，并作为`food_data`的默认RP。

说明：

	>在创建数据库时，InfluxDB会自动生成一个叫做`autogen`的RP，并作为数据库的默认RP，`autogen`这个RP会永远保留数据。在输入上面的命令之后，`two_hours`会取代`autogen`作为`food_data`的默认RP。

6、创建一个保留52周数据的RP

```bash
create retention policy "a_year" on "food_data" duration 52w replication 1;
```

这个语句对数据库`food_data`创建了一个叫做`a_year`的RP，`a_year`保存数据的周期是52周。**去掉`DEFAULT`参数可以保证`a_year`不是数据库`food_data`的默认RP**。这样在读写的时候如果没有指定，仍然是使用`two_hours`这个默认RP。

7、创建CQ

现在我们已经创建了RPs，现在我们要创建一个CQ，去将10秒间隔的数据采样到30分钟的间隔，并把它们安装不同存储策略把它们存在不同的measurement里。

```bash
CREATE CONTINUOUS QUERY "cq_30m" ON "food_data" BEGIN
  SELECT mean("website") AS "mean_website",mean("phone") AS "mean_phone"
  INTO "a_year"."downsampled_orders"
  FROM "orders"
  GROUP BY time(30m)
END
CREATE CONTINUOUS QUERY "cq_30m" ON "food_data" BEGIN  SELECT mean("website") AS"mean_website",mean("phone") AS "mean_phone"  INTO "a_year"."downsampled_orders"  FROM "orders" GROUP BY time(30m) END
上面创建了一个叫做cq_30m的CQ作用于food_data数据库上。cq_30m告诉InfluxDB每30分钟计算一次measurement为orders并使用默认RPtwo_hours的字段website和phone的平均值，然后把结果写入到RP为a_year，两个字段分别是mean_website和mean_phone的measurement名为downsampled_orders的数据中。InfluxDB会每隔30分钟跑对之前30分钟的数据跑一次这个查询。
```



## 六、Influxdb Web管理界面安装

1、InfluxDb如果能够提供一个Web管理界面，那该多方便。实际上InfluxDB 1.2 前，是提供了一个web管理界面的。但是1.3版开始，InfluxDB官方就把web界面给取消了。等于说就是一个可视化界面，其实可以下载一个InfluxDBStudio，进行开始连接。

接下来就是卸载之前装好的1.4版本influxdb，再安装1.2版本的influxdb

```bash
#卸载
rpm -q influxdb;
rpm -e influxdb;
rpm -q influxdb;
rm -rf influxdb;
#安装
wget https://dl.influxdata.com/influxdb/releases/influxdb-1.2.4.x86_64.rpm
yum install -y rpm
# 安装 example.rpm 包并在安装过程中显示正在安装的文件信息及安装进度
rpm -ivh influxdb-1.2.4.x86_64.rpm
```

2、更改配置文件开发Influxdb的8083端口

*cd /etc/influxdb/*

*vi influxdb.conf*

主要开放8086和8083端口，记得相应的enabled也做修改，具体如下图

```xml
[http]
  # Determines whether HTTP endpoint is enabled.
  # enabled = true
	enabled = true
  # The bind address used by the HTTP service.
  # bind-address = ":8086"
	bind-address = ":8086"

====================================================

[admin]
  # Determines whether the admin service is enabled.
  # enabled = false
	enabled = true
  # The default bind address used by the admin service.
  # bind-address = ":8083"
	bind-address = ":8083"
```

3、启动influxdb-->service influxdb start

```bash
service influxdb start
service influxdb restart
service influxdb stop
service influxdb status
```

4、http://192.168.189.128:8083之后，如果web页面能够正常显示就OK了。防火墙记着关闭

## 七、关键概念

|          | tag                                                | field                                                        |
| -------- | -------------------------------------------------- | ------------------------------------------------------------ |
| 索引     | 有索引，一个measurement（表）应该尽量使用tag       | 没有索引，**不要使用field value作为查询条件**                |
| 必要性   | 可以不存在，建议要有，加快查询，但是写入效率会降低 | **在InfluxDB中不能没有field**，查询语句条件中也必须要有field。`SELECT`在包括一个tag时，必须至少指定一个field |
| 数据类型 | 只能是字符串                                       | 对应的value可以是可以是字符串、浮点数（默认是浮点数）、整数(若需要存整数，需要加个i--->value=32i)、布尔值 |
|          |                                                    | 其中measurement，tag keys，tag values，field keys始终是字符串。 |
|          |                                                    |                                                              |

一行数据，就一个field set集合就可以了。eg :  2  3     -->  **a=2   b=3**这个就是field set

|                          | 描述                                                 | 例子                                                         |
| ------------------------ | ---------------------------------------------------- | ------------------------------------------------------------ |
| field set                | 就是所有field的key value组成的数据集合               | name="张三"  score=101                                       |
| 保留策略retention policy | 单个measurement可以有不同的保留策略                  |                                                              |
| 序列series               | series是retention policy，measurement和tag set的集合 |                                                              |
| point点                  | point就是一行数据                                    | <img src="C:\Users\86152\AppData\Roaming\Typora\typora-user-images\image-20200616100905365.png" alt="image-20200616100905365" style="zoom:25%;" /> |

## 八、InfluxDB与关系型数据库的比较

|      | 关系型数据库（MySql、Oracle）                   | 时间序列数据库InfluxDB                        |
| ---- | ----------------------------------------------- | --------------------------------------------- |
| 特点 |                                                 | 时间戳(timestamp)是必须要有的，可以认为是主键 |
|      | 表                                              | measurement                                   |
|      | 有索引的列                     ------》         | tag                                           |
|      | 没有索引的列                  ------》          | field                                         |
|      | 行                                     ------》 | point                                         |
|      | 存储过程                          ------》      | continuous query和retention policy            |
|      |                                                 | InfluxDB中的数据一次写入，很少更新            |

## 九、InfluxDb中的数据查询语法

1、SELECT语法说明

![image-20200616134848498](C:\Users\86152\AppData\Roaming\Typora\typora-user-images\image-20200616134848498.png)

2、from的使用

```bash
首先执行了三、4的数据导入，use NOAA_water_database
# 1、查询指定的字段，必须有field的
select "level description",location,water_level from h2o_feet limit 10
# 2、从单个measurement中选择特定的tag和field，并提供其标识符类型
select "level description"::field,location::tag,water_level::field from h2o_feet limit 10
# 3、从单个measurement查询所有field
select *::field from h2o_feet limit 10
# 4、从measurement中选择一个特定的field并执行基本计算；water_level是float类型，所以可以进行基本运算
#  这里的 +1 必须要前后有空格
select (water_level * 100 ) + 1 from h2o_feet limit 10
select (water_level * 100 / 10 + 11 - 222) from h2o_feet limit 10
# 5、查询多个measurement
SELECT * FROM "h2o_feet","h2o_pH"
# 6、从完全限定的measurement中选择所有数据
SELECT * FROM "NOAA_water_database"."autogen"."h2o_feet"
该查询选择数据库NOAA_water_database中的数据，autogen为存储策略，h2o_feet为measurement

```

3、where的用法

在`WHERE`子句中单引号来表示字符串字段值。不加引号或者用双引号，不会返回结果，也不会返回错误。

包括： 

```xml
>
<
=
!=
<=
>=
```

```sql
SELECT * FROM "h2o_feet" WHERE "water_level" + 2 > 11.9
SELECT "water_level" FROM "h2o_feet" WHERE "location" <> 'santa_monica' AND (water_level < -0.59 OR water_level > 9.95)
SELECT * FROM "h2o_feet" WHERE time > now() - 7d
```

4、group by

```sql
SELECT MEAN("water_level") FROM "h2o_feet" GROUP BY "location"

-- 默认是对h2o_quality表的所有tag进行分组计算
SELECT MEAN("index") FROM "h2o_quality" GROUP BY *

select "water_level","location" FROM "h2o_feet" WHERE time >= '2015-08-18T00:00:00Z' AND time <= '2020-08-18T00:30:00Z'

```

5、

```sql
SELECT MEAN("difference") FROM (SELECT "cats" - "dogs" AS "difference" FROM "pet_daycare")

SELECT "all_the_means" FROM (SELECT MEAN("water_level") AS "all_the_means" FROM "h2o_feet" WHERE time >= '2015-08-18T00:00:00Z' AND time <= '2015-08-18T00:30:00Z' GROUP BY time(12m) ) WHERE "all_the_means" > 5

SELECT SUM("water_level_derivative") AS "sum_derivative" FROM (SELECT DERIVATIVE(MEAN("water_level")) AS "water_level_derivative" FROM "h2o_feet" WHERE time >= '2015-08-18T00:00:00Z' AND time <= '2015-08-18T00:30:00Z' GROUP BY time(12m),"location") GROUP BY "location"

```

## 十、常见schema查询用法

```sql
show databases
show measurements
#返回指定数据库的保留策略的列表
show retention POLICIES
show tag keys
show field keys
SHOW TAG VALUES ON "NOAA_water_database" WITH KEY = "randtag"
```



```sql
drop database mydb
drop measurement student
```





原文：https://www.hellodemos.com/hello-influxdb/influxdb-demos.html