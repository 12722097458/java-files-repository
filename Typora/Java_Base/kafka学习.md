一、安装

安装集群模式的kafaka，其依赖于集群模式的zookeeper，所以需要先安装好集群模式的zookeeper

zookeeper的安排：

192.168.118.201、192.168.118.202、192.168.118.203构成一个集群

1、下载kafka_2.12-2.7.0.tgz

2、上传到/app/tools/cloud目录下

3、解压

```shell
tar -zxvf kafka_2.12-2.7.0.tgz
```

4、修改/app/tools/cloud/kafka_2.12-2.7.0/config/server.properties

```properties
############################# Server Basics #############################
# The id of the broker. This must be set to a unique integer for each broker.
broker.id=1           # 分别为1 2 3

############################# Log Basics #############################
# A comma separated list of directories under which to store log files
log.dirs=/app/tools/cloud/kafka_2.12-2.7.0/kafka-logs

############################# Zookeeper #############################
# Zookeeper connection string (see zookeeper docs for details).
# This is a comma separated host:port pairs, each corresponding to a zk
# server. e.g. "127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002".
# You can also append an optional chroot string to the urls to specify the
# root directory for all kafka znodes.
# 用ip:port会报错：kafka.zookeeper.ZooKeeperClientTimeoutException  坑死了
# zookeeper.connect=192.168.118.201:2181,192.168.118.202:2181,192.168.118.203:2181
 
# 改成  域名:port--》使用域名需要修改/etc/hosts文件，进行映射
# 192.168.118.201 bigdata01
# 192.168.118.202 bigdata02
# 192.168.118.203 bigdata03
zookeeper.connect=bigdata01:2181,bigdata02:2181,bigdata03:2181

```

5、启动kafka服务

首先启动zookeeper

```shell
#  启动zookeeper
cd /app/tools/cloud/zookeeper-3.4.9/bin

sh zkServer.sh start
```

启动kafka前确保zookeeper正常启动中

```shell
sh /myshell/cloud/zookeeper-start.sh
cd /app/tools/cloud/kafka_2.12-2.7.0/bin
sh kafka-server-start.sh -daemon ../config/server.properties      #后台启动

sh kafka-server-stop.sh

```



二、基本命令

```shell
cd /app/tools/cloud/kafka_2.12-2.7.0/bin

-- 创建topic指定分区和副本
sh kafka-topics.sh --create --zookeeper 192.168.118.201:2181 --topic firstTopic --partitions 2 --replication-factor 2
-- 删除
sh kafka-topics.sh --delete --zookeeper 192.168.118.201:2181 --topic firstTopic
-- 查看
sh kafka-topics.sh --describe --zookeeper 192.168.118.201:2181 --topic firstTopic

```

sudo lsof -i :2181



1、通过console生产及消费数据

```shell
cd /app/tools/cloud/kafka_2.12-2.7.0/bin
sh kafka-console-producer.sh --topic firstTopic --broker-list 192.168.118.201:9092

#############
在103上开启一个消费者
sh kafka-console-consumer.sh --topic firstTopic --bootstrap-server 192.168.118.201:9092
# 可以获取所有的信息加上--from-beginning
sh kafka-console-consumer.sh --topic firstTopic --bootstrap-server 192.168.118.201:9092 --from-beginning

```



消费者offset学习

offset在kafka老版本中保存在zookeeper中，消费时console要用 --zookeeper

在新版本中保存在kafka中，用--bootstrap-server

（1）创建一个新的topic(bigdataTopic)，并开启一个生产者和两个消费者终端

```shell
#####创建topic
sh kafka-topics.sh --topic bigdataTopic --zookeeper 192.168.118.201:2181 --partitions 2 --replication-factor 2 --create
####启动一个生产者
sh kafka-console-producer.sh --topic bigdataTopic --broker-list 192.168.118.201:9092
####再启动两个消费者，先通过zookeeper连接
sh kafka-console-consumer.sh --topic bigdataTopic --bootstrap-server 192.168.118.201:9092 --from-beginning

```



安装kafka可视化工具eagle

1、修改 kafka 启动命令 

修改 kafka-server-start.sh 命令

```shell
#if [ "x$KAFKA_HEAP_OPTS" = "x" ]; then
    #export KAFKA_HEAP_OPTS="-Xmx1G -Xms1G"
#fi

↓↓↓↓↓↓

if [ "x$KAFKA_HEAP_OPTS" = "x" ]; then
 export KAFKA_HEAP_OPTS="-server -Xms2G -Xmx2G -XX:PermSize=128m 
-XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:ParallelGCThreads=8 -
XX:ConcGCThreads=5 -XX:InitiatingHeapOccupancyPercent=70"
 export JMX_PORT="9999"
 #export KAFKA_HEAP_OPTS="-Xmx1G -Xms1G"
fi

```

2、下载kafka-eagle-bin-1.3.7.tar.gz

3、上传到/app/tools/cloud，解压

```shell
tar -zxvf kafka-eagle-bin-1.3.7.tar.gz 
```

4、进入解压后的目录，再次解压里面的kafka-eagle-web-1.3.7-bin.tar.gz文件

```
tar -zxvf kafka-eagle-web-1.3.7-bin.tar.gz
```

5、修改配置文件

```shell
cd /app/tools/cloud/kafka-eagle-bin-1.3.7/kafka-eagle-web-1.3.7/conf


######################################
# multi zookeeper&kafka cluster list
######################################
kafka.eagle.zk.cluster.alias=cluster1
cluster1.zk.list=bigdata01:2181,bigdata02:2181,bigdata03:2181

######################################
# zk client thread limit
######################################
kafka.zk.limit.size=25

######################################
# kafka eagle webui port
######################################
kafka.eagle.webui.port=8048

######################################
# kafka offset storage
######################################
cluster1.kafka.eagle.offset.storage=kafka

######################################
# enable kafka metrics
######################################
kafka.eagle.metrics.charts=true
kafka.eagle.sql.fix.error=false

######################################
# kafka sql topic records max
######################################
kafka.eagle.sql.topic.records.max=5000

######################################
# alarm email configure
######################################
kafka.eagle.mail.enable=false
kafka.eagle.mail.sa=alert_sa@163.com
kafka.eagle.mail.username=alert_sa@163.com
kafka.eagle.mail.password=mqslimczkdqabbbh
kafka.eagle.mail.server.host=smtp.163.com
kafka.eagle.mail.server.port=25

######################################
# alarm im configure
######################################
#kafka.eagle.im.dingding.enable=true
#kafka.eagle.im.dingding.url=https://oapi.dingtalk.com/robot/send?access_token=

#kafka.eagle.im.wechat.enable=true
#kafka.eagle.im.wechat.token=https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=xxx&corpsecret=xxx
#kafka.eagle.im.wechat.url=https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=
#kafka.eagle.im.wechat.touser=
#kafka.eagle.im.wechat.toparty=
#kafka.eagle.im.wechat.totag=
#kafka.eagle.im.wechat.agentid=

######################################
# delete kafka topic token
######################################
kafka.eagle.topic.token=keadmin

######################################
# kafka sasl authenticate
######################################
cluster1.kafka.eagle.sasl.enable=false
cluster1.kafka.eagle.sasl.protocol=SASL_PLAINTEXT
cluster1.kafka.eagle.sasl.mechanism=PLAIN
cluster1.kafka.eagle.sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username="admin" password="kafka-eagle";

######################################
# kafka jdbc driver address
######################################
kafka.eagle.driver=com.mysql.cj.jdbc.Driver
kafka.eagle.url=jdbc:mysql://localhost:3306/db_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8
kafka.eagle.username=root
kafka.eagle.password=root


```

6、修改环境变量

```shell
vim /etc/profile
添加
export KE_HOME=/app/tools/cloud/kafka-eagle-bin-1.3.7/kafka-eagle-web-1.3.7 
export PATH=$PATH:$KE_HOME/bin

```

刷新配置文件  `source /etc/profile`



7、启动eagle(确保zk和kafka正常启动了)

```shell
sh /app/tools/cloud/kafka-eagle-bin-1.3.7/kafka-eagle-web-1.3.7/bin/ke.sh start
```



8、登录前台页面

http://192.168.118.203:8048/ke

![image-20210327144436141](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20210327144436141.png)