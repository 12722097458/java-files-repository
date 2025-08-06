### 1、在linux上安装zookeeper

（1）下载

`http://archive.apache.org/dist/zookeeper/`下载zookeeper-3.4.9.tar.gz

（2）解压安装

下载完成后进入目录`/app/tools/cloud`通过rz命令，将下载好的zookeeper-3.4.9.tar.gz进行解压

命令为：`tar -zxvf zookeeper-3.4.9.tar.gz `

（3）安装后`cd zookeeper-3.4.9`进入目录，通过`mkdir zkData`创建文件夹，通过配置用于存储持久化的数据。

（4）简单修改配置文件

```shell
cd /app/tools/cloud/zookeeper-3.4.9/conf
# 复制文件
cp zoo_sample.cfg zoo.cfg
vi zoo.cfg
#将 dataDir修改为  --》 dataDir=/app/tools/cloud/zookeeper-3.4.9/zkData

cd /app/tools/cloud/zookeeper-3.4.9/bin
# 可以启动或关闭zk服务器，以及启动zk客户端。
sh zkServer.sh stop      # 关闭zk服务器
sh zkServer.sh start     # 启动zk服务器
sh zkServer.sh status    # 查看状态
sh zkCli.sh              # 启动zk客户端

#zk客户端基本命令
ls /
ls /service
get /service/zookeeper
```

对于集群模式：

```shell
# 201、202、203三台服务器都安装zookeeper
# 1、在zkData目录下新建一个myid的文件，并指定唯一ID--》1 2 3
cd /app/tools/cloud/zookeeper-3.4.9/zkData
touch myid
vi myid   --> 分别指定 1 2 3 

# 2、再次配置zoo.cfg文件
vi /app/tools/cloud/zookeeper-3.4.9/conf/zoo.cfg
集群模式：在最后一行添加以下配置
#######################cluster##########################
server.1=192.168.118.201:2888:3888
server.2=192.168.118.202:2888:3888
server.3=192.168.118.203:2888:3888

server.1/2/3表示myid设置的值的大小

# 3. 分别启动201、202、203的三台zookeeper
cd /app/tools/cloud/zookeeper-3.4.9/bin
sh zkServer.sh start



成功启动后sh zkServer.sh status发现202是leader，201和203都是follower（记着关闭防火墙）
```

（5）zookeeper基本命令操作

```shell
help
ls /
ls2 /    # 'ls2' has been deprecated. Please use 'ls [-s] path' instead.
ls -s /
# 创建两个节点，创建节点时必须同时写入数据
create /sanguo "songjiang"             # 持久存在
get /sanguo   # 取数

create -e /sanguo/wuguo "zhouyu"       # 临时存在，客户端退出重新进入后就消失了 

# 监听
get /sanguo watch  # 如果此时其他客户端修改了/sanguo节点的数值，就可以被发现（只可以监听一次）
另一服务器set /sanguo "shizitou"，
此时可以监控到WatchedEvent state:SyncConnected type:NodeDataChanged path:/sanguo

stat /   # 查看节点的详细信息
```

（6）zookeeper面试题：

**请简述 ZooKeeper 的选举机制？**

1）半数机制：集群中半数以上机器存活，集群可用。所以 Zookeeper 适合安装奇数台 服务器。 

2）Zookeeper 虽然在配置文件中并没有指定 Master 和 Slave。但是，Zookeeper 工作时， 是有一个节点为 Leader，其他则为 Follower，Leader 是通过内部的选举机制临时产生的。

**ZooKeeper 的监听原理是什么？**

![image-20210317162016666](https://gitee.com/yj1109/cloud-image/raw/master/img/20250806122410891.png)