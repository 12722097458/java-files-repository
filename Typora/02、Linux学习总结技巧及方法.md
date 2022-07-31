> 前言：Linux中一切皆文件。很多大项目都是部署到Linux上的。root为Linux权限最大的用户。
>
> 开机后会启动很多程序，在windows中称作服务service，在linux中就叫做守护进程(daemon)。
>
> 本文基于**CentOS7**

# 一、虚拟机安装及配置

```shell
网盘地址:
链接：https://pan.baidu.com/s/1euPVoht5DbYt-mIEx0dm_g 
提取码：kb3a
```

![image-20220514095113462](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203358.png)

## 1、安装VMware-workstation客户端

直接双击VMware-Workstation-Lite-16.0.0-1689429.exe，进行安装，选择好安装路径，一直下一步即可。

![image-20220514094618873](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203401.png)



安装成功后多了VMnet8的网卡界面：

![image-20220514094912107](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203633.png)

![image-20220514095002673](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203406.png)



## 2、创建新的虚拟机

（1）点击新建虚拟机，选择自定义

![image-20220514101506355](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203408.png)

（2）选择Vorkstation16

![image-20220514101601111](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203411.png)

（3）稍后安装操作系统

![image-20220514101628962](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203412.png)

（4）选择Linux以及CentOS 7

![image-20220514101708811](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203414.png)

（5）给虚拟机起名以及设置当前配置虚拟机的位置

> 最好进行文件夹分组(CentOS7-110)，当新建多个虚拟机时，最终存在的形式就是文件

![image-20220514102027247](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203418.png)

（6）虚拟机内存设置2048

![image-20220514102144718](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203421.png)

（7）NAT网络模式

![image-20220514102226590](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203423.png)



（8）一直下一步，配置自定义硬件

* 1.1 镜像路径

  ![image-20220514102509562](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203425.png)

* 1.2 网络适配器-NAT

  ![image-20220514102541617](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203428.png)

（9）点击完成即可

![image-20220514102651384](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203430.png)





## 3、初始化虚拟机

1.1 选择Install(Ctrl + Alt鼠标退出虚拟机)

![image-20220514102809198](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203432.png)

1.2 语言选择英文即可

1.3 依次点击，不需要做修改。选择最小化安装

![image-20220514103112215](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203433.png)

1.4 设置密码，等待安装完成。

![image-20220514103148524](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203435.png)

![image-20220514103210802](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203437.png)

1.5 结束后重启虚拟机

![image-20220514103536068](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203438.png)

1.6 登录虚拟机(root/root)

![image-20220514103635383](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203440.png)



## 4、通过NAT模式配置静态IP地址

分配的IP信息为

```shell
IPADDR=192.168.137.110
GATEWAY=192.168.137.2
NETMASK=255.255.255.0
```

（1）取消DHCP动态设置：

![image-20220514105238888](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203443.png)

修改后VMnet8的网卡IP会自动同步:

![image-20220514105409194](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203453.png)



（2）配置虚拟机里的相关配置内容：

登录虚拟机

2.1首先是对network进行编辑：**cd /etc/sysconfig/**      -->        **vi network **   修改成图二即可

![image-20220514111232504](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203455.png)

```shell
NETWORKING=yes
GATEWAY=192.168.137.2
HOSTNAME=centos7-110
```

2.2修改系统的网络配置文件，路径为：cd /etc/sysconfig/network-scripts/    --》  vi  ifcfg-eth33

![image-20220514110120025](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203458.png)

```shell
TYPE=Ethernet
PROXY_METHOD=none
BROWSER_ONLY=no
BOOTPROTO=static       		#*** √
DEFROUTE=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_FAILURE_FATAL=no
IPV6_ADDR_GEN_MODE=stable-privacy
NAME=ens33
UUID=ba4448d3-ae69-4569-9405-79c7418538f1
DEVICE=ens33
ONBOOT=yes					 #*** √
IPADDR=192.168.137.110 		 #*** √
NETMASK=255.255.255.0		 #*** √
GATEWAY=192.168.137.2		 #*** √
DNS1=114.114.114.114         #*** √
DNS2=8.8.8.8                 #*** √
```

2.3 保存成功后，直接重启网路即可 **service network restart**

进行测试联通性：linux(192.168.137.110)——》ping www.baidu.com       ping  windows本机的ip(192.168.1.5)

​								windows:   ping linux的ip

![image-20220514110518080](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203500.png)

2.4 关闭Linux防火墙

```shell
systemctl stop firewalld.service      #停止firewall
systemctl disable firewalld.service    #禁止firewall开机启动
firewall-cmd --state             ##查看防火墙状态，是否是running
```

![image-20220514110808415](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203502.png)

2.5 使用FinalShell终端连接虚拟机

![image-20220514111007894](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203503.png)





## 5、问题排查

1、如果linux无法联通本机，可以尝试修改本机共享设置

![](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203511.png)2、如果本机无法ping通linux, 可以尝试在设置静态ip地址时，和本机VM8的IPV4网段地址保持一致。

我设置的GATEWAY=192.168.137.2

![image-20210811081845133](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203513.png)

3、突然间外部无法联通linux内部，linuxping本机，百度都正常。

![image-20220509231131645](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203514.png)

![image-20220509231245124](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203517.png)

![image-20220509231400641](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203518.png)

4、虚拟机启动突然无法联网，ip地址也不显示

```txt
 CentOS 7: Restarting network (via systemctl): Job for network.service failed
```

- 1 停止并禁用虚拟机 NetworkManager 服务
  - `systemctl stop NetworkManager`
  - `systemctl disable NetworkManager`
- 2 重启虚拟机网络服务
  - `systemctl restart network`或 `service network restart`

5、如果没有**VMnet8**的网卡

（1）检查虚拟机的网络编辑器是否配置正确

![image-20220512230238494](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203521.png)

（2）如果**将主机虚拟适配器连接到此网络**无法选中

查看Device Install Service和Device Setup Manager是否是自动启动

![image-20220512230518836](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203524.png)

还不行的话下载CCleaner，清空注册表。

![image-20220512230723409](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203639.png)

6、无法访问linux

（1）首先查看防火墙是否关闭了

（2）查看windows的vm8是否配置了静态IP

![image-20220513001126002](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203526.png)

7、启动出现蓝屏情况，请更新VMware-workstation

windows10使用VMware15每次启动虚拟机直接蓝屏。后来使用VMware16，没有出现这个问题。

8、如果进入虚拟机黑屏，管理员模式进入cmd-->执行 netsh winsock reset  重启计算机



# 二、Linux入门基本指令

## 1、Centos7更换阿里源

```shell
cd /etc/yum.repos.d/
yum install wget -y
mv CentOS-Base.repo CentOS-Base.repo.back
wget -O CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo 
yum makecache #生成缓存
```

## 2、重启防火墙

```shell
ip addr 查看IP信息
关机
shutdown -h now     立马关机
shutdown -h 30      30分钟后关机
shutdown -h 20:30   晚上八点半关机
一般关机之前需要执行sync进行同步数据
reboot  重启虚拟机

systemctl stop firewalld.service      #停止firewall
systemctl disable firewalld.service    #禁止firewall开机启动
firewall-cmd --state             ##查看防火墙状态，是否是running
```

## 3、工作常用命令

1、ls:查看文件夹下的文件；  **ls -a**查看所有包括隐藏文件  ll -h
2、cd       cd ../ 回到上一级      cd /    cd ~
3、pwd查看当前路径   Tab键补全
4、mkdir 创建目录         mkdir --help查看帮助  **mkdir  -p  aaa/bb/ccc** 创建多级目录  rmdir删除目录（目录不能有内容）

5、cat  myfile.txt 查看文件内容 ;      more  myfile.txt;      less  myfile.txt    输入/java可以向下查找包含java字符串的;       tail -10  myfile.txt;  查看文件最后10行  ；

tail -fn 20 myfile.txt动态查看内容

nl a.txt;输出内容包括行号。

mv  a.txt  newfilename.txt     可以进行改名操作

6、cp myfile.txt aaa/newname.txt  复制；  mv myfile.txt ./bbb/newname.txt  剪切myfile.txt到bbb文件夹并重命名为 newname.txt 
      rm a.txt;删除文件需要询问；  rm -rf aaa.txt直接删除，不进行确认。危险。
7、tar -cvf a.tar ./   打包当前目录下的文件，解压后的名字为a.tar ，存放在当前目录下
     tar -zcvf b.tar.gz ./   打包后压缩

tar -zxvf b.tar.gz ./           解压到当前目录下
tar -zxvf b.tar.gz -C ./cc   解压到 cc目录下(带.gz的压缩包需要-zxvf;  MySQL-5.6.22-1.el6.i686.rpm-bundle.tar直接  -xvf即可)

8、find / -name file*   从根目录下查找file开头的文件
      grep Hello /usr/igeek/file.txt --color     查找file.txt文件夹下包含Hello的文字，按行展示并颜色加深

9、vi a.txt  -->i-->xxxxxx--->:q!不保存退出   ：wq!  保存退出   ：/8080  搜索

10、ps -ef | grep java 搜索包含java的进程

```shell
ps -ef | grep java
netstat -anop | grep 29949   # 查看对应端口信息
netstat -tupln    # 查看Linux 的所有端口占用
```

11、kill -9 端口号pid   强制杀死进程

12、  |   管道     A|B    A的输出作为B的输入 
13、权限修改：chmod u=rwx,g=rx,o=rx a.txt    -->修改a.txt权限：当前用户可读可写可执行，组内其他用户可读可执行其他组用户可读可执行。
 -rw-r--r--. 1 root root    40 May 25 18:32 file.txt   -》第一个：-表文件，d表文件夹；接下来三个一组：当前用户，同组其他用户，不同组用户

r:read 读         4
w:write 写       2
x:excute执行   1
*chmod u=rwx,g=rx,o=rx a.txt     ====     chmod 755 a.txt*

14、上传以及压缩文件

* yum install lrzsz  下载LINUX的lrzsz工具用于，本地机与linux交互传输文件。
  rz  上传
  sz  a.txt 下载

* alt+p打开sftp窗口，put上传 ，get下载

15、安装网络工具

```shell
# 安装网络工具
yum install -y curl                        # curl http://localhost:8888
yum update -y && yum install -y iproute     # ip addr
yum install -y vim
yum install -y net-tools              # 安装后可以用ifconfig查看ip信息
```

16、删除ora_data目录下的所有文件：

```shell
rm -r ora_data/ -f  

-f　　　　-force　　　　　　忽略不存在的文件，强制删除，无任何提示
-i　　　　--interactive　　进行交互式地删除
-r | -R　--recursive　　　递归式地删除列出的目录下的所有目录和文件
-v　　　  --verbose　　　　详细显示进行的步骤
```



# 三、软件安装

```shell
rz命令安装
yum -y install lrzsz
```

### 1、JDK安装

1.1 首先java -version查看是否已经安装了jdk，如果已安装，版本不对需要卸载，重新安装。
1.2 getconf LONG_BIT 查看linux操作系统的版本。uname -r

1.3 上传安装包jdk-11_linux-x64_bin.tar.gz到/app/tools

![image-20220514131945820](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203532.png)

1.3 然后解压

```shell
tar -zxvf jdk-11_linux-x64_bin.tar.gz
```

![image-20220514132119593](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203536.png)

1.4 配置环境变量

编辑profile文件

```shell
vi /etc/profile
```

添加以下内容，JAVA_HOME路径自行调整

```shell
JAVA_HOME=/app/tools/jdk-11
CLASSPATH=.:$JAVA_HOME/lib.tools.jar
PATH=$JAVA_HOME/bin:$PATH
export JAVA_HOME CLASSPATH PATH
```

![image-20220514132741378](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203539.png)

1.5 配置后刷新配置文件

```shell
source /etc/profile
```

1.6 执行java -version 查看是否成功

![image-20220514132925414](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203541.png)

> 最后如果  java -version 出问题，（-bash: /usr/local/src/jdk1.8.0_191/bin/java: /lib/ld-linux.so.2: bad ELF interprete）
>
> 执行：sudo yum install glibc.i686 就可以解决了

### 2、Mysql安装

1、下载mysql-8.0.22-linux-glibc2.12-x86_64.tar安装包

```shell
https://downloads.mysql.com/archives/community/
```

![image-20210327132529716](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203543.png)



2、上传到服务器上，并解压

```shell
cd /app/tools
rz
tar -xvf mysql-8.0.22-linux-glibc2.12-x86_64.tar.xz    #-xvf  没有z
```

3、将解压的文件mysql-8.0.22-linux-glibc2.12-x86_64重命名mysql，并移动到/usr/local目录下

```shell
cd /app/tools
mv mysql-8.0.22-linux-glibc2.12-x86_64 mysql
mv mysql /usr/local/
```

4、创建mysql用户组及用户

```shell
cd /usr/local/
groupadd mysql
useradd -r -g mysql mysql
cd mysql/ #注意：进入mysql文件下授权所有的文件
chown -R mysql:mysql ./ 
```

5、在/usr/local/mysql目录下，创建data文件夹

```shell
mkdir data
```

6、初始化数据库，并会自动生成随机密码，记下等下登录要用 

```shell
bin/mysqld --initialize --console --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data

2022-05-14T06:03:58.820301Z 6 [Note] [MY-010454] [Server] A temporary password is generated for root@localhost: -vVzkF55?evr

l4iruJ?i8YI?
可以cat /usr/local/mysql/data/err.log查看
```

7、修改/usr/local/mysql当前目录的用户

```shell
chown -R root:root ./
chown -R mysql:mysql data
```

 8 、`cp support-files/my-default.cnf /etc/my.cnf `
复制过去，其实也就是空白页，一开始没有my-default.cnf这个文件，可以用# touch my-default.cnf命令创建一个，并配置权限 

chmod 777 ./my-default.cnf 

```shell
cd support-files/
touch my-default.cnf
chmod 777 ./my-default.cnf 
cd ../
cp support-files/my-default.cnf /etc/my.cnf 
```

9、配置my.cnf 

vi /etc/my.cnf

```shell
[mysqld]
 
# Remove leading # and set to the amount of RAM for the most important data
# cache in MySQL. Start at 70% of total RAM for dedicated server, else 10%.
# innodb_buffer_pool_size = 128M
 
# Remove leading # to turn on a very important data integrity option: logging
# changes to the binary log between backups.
# log_bin
 
# These are commonly set, remove the # and set as required.
basedir = /usr/local/mysql
datadir = /usr/local/mysql/data
socket = /tmp/mysql.sock
log-error = /usr/local/mysql/data/error.log
pid-file = /usr/local/mysql/data/mysql.pid
tmpdir = /tmp
port = 3306
#lower_case_table_names = 1
# server_id = .....
# socket = .....
#lower_case_table_names = 1
max_allowed_packet=32M
default-authentication-plugin = mysql_native_password
#lower_case_file_system = on
#lower_case_table_names = 1
log_bin_trust_function_creators = ON
# Remove leading # to set options mainly useful for reporting servers.
# The server defaults are faster for transactions and fast SELECTs.
# Adjust sizes as needed, experiment to find the optimal values.
# join_buffer_size = 128M
# sort_buffer_size = 2M
# read_rnd_buffer_size = 2M 
 
sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES
```

10、开机自启，进入/usr/local/mysql/support-files进行设置

```bash
cd support-files/
cp mysql.server /etc/init.d/mysql 
chmod +x /etc/init.d/mysql
```

11、注册服务 

```shell
chkconfig --add mysql
```

检查是否成功

```shell
chkconfig --list mysql
[root@bigdata01 support-files]# chkconfig --list mysql
mysql           0:关闭  1:关闭  2:启用  3:启用  4:启用  5:启用  6:关闭

```

13、etc/ld.so.conf要配置路径，不然报错 

```shell
vi /etc/ld.so.conf
# 添加如下内容:
/usr/local/mysql/lib
```

14、配置环境变量

```shell
vi /etc/profile
添加如下内容：
#MYSQL ENVIRONMENT
export PATH=$PATH:/usr/local/mysql/bin:/usr/local/mysql/lib
```

然后刷新配置文件 source /etc/profile

15、登录，这里输入上面第6步随机生成得密码，细心点输入，没有显示的。登录成功如图所示 

==启动操作如下：（已开机自启）==

```shell
mysql -uroot -p
密码可以直接粘贴  ur/zn.e6p)eR
如果出现socket错误，可以尝试启动mysql 的server
ERROR 2002 (HY000): Can‘t connect to local MySQL server through socket ‘/tmp/mysql.sock‘
 cd support-files/
./mysql.server start
```

![image-20210327141151718](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203548.png)

16、修改密码

```shell
# mysql -uroot -p #进入数据库

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
flush privileges;

use mysql;#进入数据库
select host, user, authentication_string, plugin from user;#查看用户信息
#授权root用户可以远程登录
update user set host='%' where user='root' and host='localhost'; 

flush privileges;

```

启动：

```shell
[root@192 support-files]# ls
my-default.cnf  mysqld_multi.server  mysql-log-rotate  mysql.server
[root@192 support-files]# ./mysql.server restart
Shutting down MySQL.. SUCCESS! 
Starting MySQL.. SUCCESS! 
[root@192 support-files]# 
```

17、此时Navicat可以正常连接了

![image-20220514141025823](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203550.png)



### 3、Tomcat安装

1、上传后直接解压

```shell
tar -zxvf apache-tomcat-8.5.73.tar.gz
```

2、到bin目录下，==启动服务==

```shell
sh startup.sh
```

3、测试

```shell
 http://192.168.137.110:8080   # 看是否启动成功
 tail -fn 20 catalina.out    # 查看实时日志
```

![image-20220514141444928](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203552.png)

### 4、Redis安装

1、安装c语言环境：yum  install gcc-c++
2、压缩  tar -zxvf redis-3.0.7.tar.gz
3、编译  cd redis-3.0.7 然后用 make编译  ；  下载 make PREFIX=/usr/local/tools/redis/ install

```shell
cd redis-3.0.7
yum -y install gcc automake autoconf libtool make
make MALLOC=libc
make distclean

make PREFIX=/usr/local/tools/redis/ install
```

4、把redis.conf 文件复制到bin目录下

```shell
cp /app/tools/redis-3.0.7/redis.conf /usr/local/tools/redis/bin/
```

5、==./redis-server redis.conf  启动redis服务器==

```shell
./bin/redis-server& ./redis.conf    #后台启动(如在配置文件设置了daemonize属性为yes则跟后台进程方式启动其实一样)
```

6、再新开一个会话 到bin目录下，启动客户端

![image-20220514143418741](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203554.png)

windows也可正常连接访问。

![image-20220514143434345](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203556.png)

### 5、MongoDb安装

1、下载

官网下载地址：https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-4.0.13.tgz

2、上传到Linux的 /app/tools 目录下

```shell
rz 上传
```

3、解压，并修改名称便于后续操作

```shell
cd /app/tools
tar -zxvf mongodb-linux-x86_64-4.0.13.tgz
mv mongodb-linux-x86_64-4.0.13 mongodb
```

4、新建data目录以及日志logs目录

```shell
cd /app/tools/mongodb
mkdir -p data/db
mkdir -p logs
```

![image-20210810234609301](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203559.png)

5、创建MongoDB运行时使用的配置文件：mongodb.conf

```shell
vi /app/tools/mongodb/bin/mongodb.conf

直接粘贴以下内容即可：
dbpath = /app/tools/mongodb/data/db #数据文件存放目录
logpath = /app/tools/mongodb/logs/mongodb.log #日志文件存放目录
port = 27017 #端口
fork = true #以守护程序的方式启用，即在后台运行
#auth=true #需要认证。如果放开注释，就必须创建MongoDB的账号，使用账号与密码才可远程访问，第一次安装建议注释
bind_ip=0.0.0.0 #允许远程访问:，或者直接注释，127.0.0.1是只允许本地访问
```

6、配置环境变量

```shell
vi /etc/profile

添加以下内容：
#mongodb
export PATH=$PATH:/app/tools/mongodb/bin
```

7、**启动**

```shell
cd /app/tools/mongodb/bin
./mongod  -f  mongodb.conf   # 启动
```



### 6、RabbitMq安装

由于RabbitMQ是由erlang开发的，所以需要erlang环境.(注意版本兼容问题)

RabbitMQ的rpm包：

```shell
https://packagecloud.io/rabbitmq/rabbitmq-server/packages/el/7/rabbitmq-server-3.9.10-1.el7.noarch.rpm
```

1、安装erlang环境

```shell
wget https://packages.erlang-solutions.com/erlang-solutions-1.0-1.noarch.rpm
yum install epel-release
rpm -Uvh erlang-solutions-1.0-1.noarch.rpm
## 安装完成后输入erl看是否能正确显示版本号
```

2、安装rabbitmq

```shell
上传已经准备好的RabbitMQ的rpm包
rpm -Uvh rabbitmq-server-3.9.10-1.el7.noarch.rpm
安装的默认目录是/usr/sbin/rabbitmq-server
##此时可正常启动，但无法通过UI访问
systemctl start rabbitmq-server
systemctl status rabbitmq-server
systemctl restart rabbitmq-server
```

3、启动WEB管理插件

```shell
rabbitmq-plugins enable rabbitmq_management

##访问管理页面
http://IP:15672
```

4、创建管理用户admin

```shell
#添加用户(用户admin，密码admin)
[root@root ~]# rabbitmqctl add_user admin admin
Adding user "admin" ...

#设置用户角色（admin为管理员）
[root@root ~]# rabbitmqctl set_user_tags admin administrator
Setting tags for user "admin" to [administrator] ...

#设置用户权限(接受来自所有Host的所有操作)
[root@root ~]# rabbitmqctl  set_permissions -p "/" admin '.*' '.*' '.*'
Setting permissions for user "admin" in vhost "/" ...

#查看用户权限
[root@root ~]# rabbitmqctl list_user_permissions admin
Listing permissions for user "admin" ...
vhost    configure    write    read
/    .*    .*    .*

```



# 四、软件启动

## 1. 登录Linux

192.168.137.110  root/root

需要关闭防火墙

## 2. 登录mysql

```shell
mysql -uroot -p
root
```

## 3. 登录Redis

```shell
#启动服务端
cd /usr/local/tools/redis/bin
./redis-server

后台启动方式
首先把配置文件中的redis.config 中对应属性设置为： daemonize yes
./redis-server /app/tools/redis-3.0.7/redis.conf
#启动客户端
./redis-cli
```

## 4.登录Mongo

```shell
cd /app/tools/mongodb/bin
./mongod  -f  mongodb.conf   # 启动
```

## 5.Windows下的nginx

```shell
cd D:\Java\tools-windows\nginx-1.12.0
./nginx.exe
或者双击启动即可
```

## 6.登录Nacos

```shell
解压nacos-server-1.1.4.zip
Windows 通过startup.cmd进行启动。默认端口号8848
Linux通过startup.sh 启动。

windows:
D:\Java\tools-windows\nacos-server-1.1.4\nacos\bin

http://localhost:8848/nacos
nacos/nacos

```

## 7.登录RabbitMq

```shell
systemctl start rabbitmq-server
systemctl status rabbitmq-server
systemctl restart rabbitmq-server
systemctl stop rabbitmq-server

http://192.168.137.110:15672/#/
```



# 四、Java项目部署

## 1、实战部署web项目到Linux

1、要把项目中的绝对路径都改成相对的。

2、确保linux中的mysql数据库内容和本地相同。

3、控制jdk版本：pom文件，maven的setting.xml文件

4、启动后看Catalina.out打印的日志，看是否有报错，有报错一点一点处理。：lombok报错，jupiter报错，jdk版本报错。



## 2、配置一个简单SpringBoot项目

1、准备好简单的SpringBoot项目boot-admin-server

> 没有数据库等任何依赖，可以直接启动

2、通过maven打包后把jar包上传到Linux

![image-20220514133717066](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203603.png)

3、直接启动即可(确保Linux的JDK已经安装成功)

![image-20220514133924888](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203605.png)

4、测试

```shell
http://192.168.137.110:8181
```

![image-20220514133945337](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203607.png)



## 3、以debug方式启动远程服务器上的jar包，并在本地进行debug调试

1、准备一个spring boot项目demo，写一个简单方法返回sayHello

```java
package com.ityj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping(path = "/sayHello")
    @ResponseBody
    public String sayHello(){
        System.out.println("sayHello...");
        return "hello";
    }

}
```

2、配置好和linux匹配的jdk版本后进行打jar包并上传到Linux服务器，确保环境正确并能正常打开项目。

3、在idea中配置remote配置调试端口

![image-20200809131245368](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203610.png)

4、执行以下命令，启动项目

```shell
java -Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9999 -jar  /project/springboot01-helloword_auto-0.0.1-SNAPSHOT.jar 
```

启动成功如下：

![image-20200809131623809](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203611.png)

5、在idea中启动debug

![image-20200809131736744](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203613.png)

此时在项目中打断点即可进行调试。



# 五、后续补充内容

> **==Linux的硬链接和软链接：==**

1、硬链接B相当于原文件A 的一个备份，这样一个文件就拥有了两个路径，如果一个被删除，另一个还可以使用。

2、软链接就类似于windows下的快捷方式，如果把原文件A删除了，这个软链接也会失效。

先创建一个普通文件f1.txt

ln f1.txt f2.txt默认创建的f2.txt就是硬链接

ln -s f1.txt f3.txt创建的f3.txt就是软链接

**把f1.txt删除后，硬链接文件f2.txt正常显示，f3.txt失效**



>##### ==Linux用户的管理==

切换到root用户下：

> **添加账号：**useradd -m user001   ----》    在home目录下就可以看到一个user001的文件夹        
>
> -G  xxx分配用户属组

**本质：**linux下一切皆文件，这里的添加用户说白了就是往某个文件中写入了用户的信息！  cat /etc/passwd目录下可以看到有关信息

> **删除账号：**userdel -r user001
>
> 修改用户（权限等）: usermod  -d  /home/2333  user001      修改后在配置文件passwd文件中可以看到

> 切换用户：su user001

![image-20210811082408121](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203616.png)

exit：推出当前权限用户，回到root用户 

hostname   查看主机名；     hostname  yinj  修改主机名为yinj,临时的。

passwd user001    可以在root用户下修改指定用户的密码

> 锁定账户

比如某员工辞职了，需要冻结这个账号，一旦冻结无法恢复。

passwd -l user001       #锁定之后就无法登录了

passwd -d user001      #清空密码了，也就无法登录

**==用户组管理：==**

属主、属组

每一个用户都有一个用户组，系统可以对一个用户组的所有用户进行集中管理（开发、测试、运维。。。）

>添加用户组：groupadd dev                             cat /etc/group进行配置查看

可以用groupadd  -g  520  dat 指定对应的id,如果不指定就是自增。gid不能重复，重复的话就会提示，无法创建.

不写的话id递增。

> 删除用户组：groupdel  dev

> 修改用户组：groupmod -g 666 -n newdev dev                ;将原来的dev组id改为666，名字改为newdev





**==磁盘管理==**

> df:(列出文件系统的整体磁盘使用量，相当于windows各个盘符使用情况)    -h  
>
> du:(检查当前目录磁盘空间使用量)       

![](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203619.png)



**==进程管理==**

1、在linux中，每一个程序都是自己的一个进程，每个进程都有自己的id号

2、每一个进程都有一个父进程

3、进程的存在有两种方式：前台，后台。

4、一般服务都是后台运行的，基本程序都是前台运行的。



> 命令 ps查看当前系统正在运行的程序

ps  [-xxxxx]

​	-a:所有信息

​	-u:以用户的信息展示进程

​	-x:显示后台运行进程的参数



```bash
ps: 查看进程
| : 管道符用于过滤；  A|B   A的结果作为条件再次进行过滤
grep: 查找文件中附合条件的字符串，

eg:
ps -ef | grep java   #查看java相关的进行(可以查看到父进程的目录)
```

pstree

​	-p:显示父id

​	-u：显示用户组

:1st_place_medal:pstree -pu

![image-20210811082528782](https://alinyun-images-repository.oss-cn-shanghai.aliyuncs.com/images/20220731203623.png)



> 结束进程 ： kill -9  pid
>
> kill -15 pid

`后台执行:nohup`

netstat -tnlp  查看使用的端口



# 六、Docker

# 七、Nginx



