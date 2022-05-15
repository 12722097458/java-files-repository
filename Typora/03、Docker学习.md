

# 一、Docker介绍

> https://www.bilibili.com/video/BV1og4y1q7M4?spm_id_from=333.999.0.0

## 1、Docker为什么出现？

传统：开发提供jar包，运维部署（运维需要安装一系列对应版本的环境(jdk/redis/hadoop...)，很麻烦）

现在：开发打包部署上线，一套完成。jar+环境 打成一个大的包，直接部署即可。



java -- apk --发布（应用商店） -- 张三下载apk -- 直接安装即可使用

java -- jar(+环境) -- 打包项目带上环境（称为镜像：如windows重装系统就通过镜像） -- （Docker仓库：商店） -- 下载我们发布的镜像 --直接运行即可。



Docker的思想来源于集装箱，隔离，每个箱子是互相隔离的。

Docker通过隔离的机制，可以将服务器利用到极致，充分使用服务器的资源。



## 2、Docker的历史

2013年Docker开源，使用的人越来越多，在2014年4月Docker 1.0发布。

Docker为什么火？十分轻巧

在容器技术出来之前，我们都是使用虚拟技术！

虚拟机：在windows上安装一个centos，通过这个软件，可以安装多台Linux环境。占用内存较大，笨重。

Docker容器技术，也是一种虚拟化技术.

Docker文档：https://docs.docker.com/



## 3、Docker能干吗？

> 传统的虚拟机项目部署

![image-20211207232220317](https://gitee.com/yj1109/cloud-image/raw/master/img/GOxQ2skYVRpyEMJ.png)

缺点分析：

1、资源占用非常多，依赖同一个lib（同一套环境），不易扩展

2、冗余步骤多

3、启动慢



## 4、Docker的基本组成

![image-20211213230717545](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211213230717545.png)

### （1）**镜像（image）**

docker镜像就好比一个模板，可以通过这个模板来创建容器服务：tomcat镜像 ===》 run  ===》 tomcat容器01（提供服务）

通过这个镜像可以创建多个容器（最终的服务或者项目就是运行在容器中的）。

### **（2）容器（container）**

Docker利用容器技术，独立运行一个或一组应用，是通过镜像来创建的。

启动，停止，删除等基本命令。

目前可以把容器理解为一个简易的Linux系统。

### **（3）仓库（repository）**

用来存放镜像的地方。

共有仓库和私有仓库，Docker Hub, 阿里云等



## 5、容器化技术

**容器化技术不是模拟的一个完整的操作系统**

![image-20211207232731813](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211207232731813.png)

比较Docker和虚拟机技术的不同：

* 传统虚拟机，虚拟出整套硬件，运行一个完整的操作系统，然后在这个操作系统上运行相关的程序。笨重
* 容器内的应用直接运行在 宿主机的内核，容器是没有内核的，也没有虚拟我们的硬件，所以比较轻便。
* 每个容器间相互隔离，每个容器内部有自己的文件系统，互不影响。



## 6、Devops （开发 运维）

**应用更快速地交付和部署**

传统：一堆帮助文档，安装程序

Docker：打包镜像发布测试，一键运行

**更快捷地升级和扩容缩容**

使用Docker后，我们的部署就像搭积木一样，非常方便。

项目（包括程序和环境）打包成一个镜像，在任意服务器直接运行进行即可，便于扩展。

**更简单的系统运维**

在容器化后，我们的开发、测试环境高度一致。减少出现环境不一致导致的问题。

**更高效的资源利用**

Docker是内核级别的虚拟化，可以在一个物理机上运行多个容器实例，可以重复利用服务器的资源。



# 二、Docker安装

## 1、环境准备

目前是基于CentOS7进行学习，其中7是官方要求的最低版本

```shell
# 查看系统内核，内核为3.10
[root@localhost ~]# uname -r
3.10.0-1160.el7.x86_64

# 查看系统版本
[root@localhost ~]# cat /etc/os-release 
NAME="CentOS Linux"
VERSION="7 (Core)"
ID="centos"
ID_LIKE="rhel fedora"
VERSION_ID="7"
PRETTY_NAME="CentOS Linux 7 (Core)"
ANSI_COLOR="0;31"
CPE_NAME="cpe:/o:centos:centos:7"
HOME_URL="https://www.centos.org/"
BUG_REPORT_URL="https://bugs.centos.org/"

CENTOS_MANTISBT_PROJECT="CentOS-7"
CENTOS_MANTISBT_PROJECT_VERSION="7"
REDHAT_SUPPORT_PRODUCT="centos"
REDHAT_SUPPORT_PRODUCT_VERSION="7"
```

## 2、开始安装

`https://docs.docker.com/engine/install/centos/`

```shell
#1 卸载旧版本
yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
#2 下载相关依赖包
yum install -y yum-utils

#3 添加仓库地址
yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo   # 默认为外网的，我们改为阿里云的
    
yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

#4 更新yum包索引（optional）
yum makecache fast
    
#5 引擎下载Install Docker Engine
yum install docker-ce docker-ce-cli containerd.io   # 默认为最新版本

#6 Start Docker
systemctl start docker

#7 查看安装的版本（是否成功安装） 
docker version

#8 测试（首次执行会先从远程仓库中下载对应的hello-world镜像）
docker run hello-world

docker images  # 查看下载的镜像
```



## 3、卸载步骤

```shell
#1 卸载引擎
yum remove docker-ce docker-ce-cli containerd.io
#2 删除多余文件
rm -rf /var/lib/docker
rm -rf /var/lib/containerd
```



## 4、阿里云镜像加速

1、登录阿里云，找到容器镜像服务==> 镜像工具==> 镜像加速器

```url
https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors
```

2、按照示例配置即可

```shell
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://od44lrrt.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```



## 5、Docker run运行流程

首次运行docker run helllo-world的过程：

![image-20211208084927914](https://gitee.com/yj1109/cloud-image/raw/master/img/h4Rko2mpOBUXgeH.png)

![image-20211208085638241](https://gitee.com/yj1109/cloud-image/raw/master/img/BFb1dt4xkWSAEJu.png)



## 6、底层原理

Docker是怎么工作的？

docker是一个client-server结构的系统，docker是以守护进程方式在（主机/宿主机）后台运行的 ，通过socket从客户端访问。

宿主机（docker- server） 接收到docker-client的指令，进行执行。

![image-20211208092510035](https://gitee.com/yj1109/cloud-image/raw/master/img/wmTug9kG8lVDORY.png)



# 三、Docker的常用命令

## 1、帮助命令

```shell
systemctl start docker
docker version
docker info
docker --help : docker search --help
docker search mysql --limit 10 
docker pull mysql
docker images [-aq]
docker history imageid   # 查看镜像构建过程

docker rmi -f feb5d9fea6a5
docker run -d centos
docker start feb5d9fea6a5
docker ps -a
docker stats 777d794e3db6 # 查看CPU状态  不是status
```

docker帮助文档

```shell
# 命令命令行查看
docker search mysql

https://docs.docker.com/engine/reference/commandline/docker/
#docker hub
https://registry.hub.docker.com/_/tomcat?tab=tags
```



## 2、镜像的命令

```shell
docker search mysql
docker pull mysql  == docker pull mysql:latest  # ：默认最新版本  下载镜像 
docker pull时是分层下载，docker image的核心，联合文件系统
docker rmi -f feb5d9fea6a5  # 根据IMAGE ID进行删除，多个用空格分隔
docker rmi -f $(docker images -aq)
```



## 3、容器的命令

### （1）**新建容器并启动**

```shell
docker pull centos
docker run -it centos /bin/bash # 进入容器中，exit直接结束容器并退出；ctrl + p + q 返回主机，不退出容器

run创建一个容器实例
docker run [可选参数] image
--name="tomcat01"   # 容器名称，用来区分容器
-d #后台进行 类似  jar nohup
-it # 以交互方式启动，进入容器中查看内容
-P  #指定容器的端口 8080:8080
	-P 主机端口:容器端口
	-P 容器端口
	直接写容器端口也可
-p  # 随机指定端口
```

### **（2）列出所有运行的容器**

```shell
docker ps  # 查看正在运行的容器
docker ps -a #查看近期执行的容器  -q 显示id
```

### **（3）删除容器**

```shell
docker rm feb5d9fea6a5
docker rm -f $(docker ps -aq)
```

### **（4）启动和停止容器**

```shell
docker start feb5d9fea6a5        # 需要先有这个实例  docker ps -a
docker restart feb5d9fea6a5
docker stop feb5d9fea6a5
docker kill feb5d9fea6a5
```



## 4、其他常用命令

后台启动容器

```shell
docker run -d feb5d9fea6a5
# 发现问题，通过docker ps 命令并没有发现容器在执行，说明此容器停止了。
# 原因：docker容器使用的是后台运行，必须有一个前台进程，docker发现没有应用（没有使用），就自动停止了（自杀）。
```

查看日志

```shell
docker logs -tf --tail 10 feb5d9fea6a5
```

查看容器中的进程信息

```shell
docker top d1265c697925    # 容器id
```

查看镜像的元数据

```shell
docker inspect d1265c697925
```

进入当前正在运行的容器

```shell
#1 进入容器后开启一个新的终端，相当于打开一个新的窗口
docker exec -it d1265c697925 /bin/bash
#2 进入容器正在执行的终端，不会启动新的进程
docker attach d1265c697925
```

容器与主机文件传输

```shell
内-->外, 把容器内部的文件拷贝到主机上。
docker ps -a
docker start d1265c697925 
docker ps # 看是否运行了
docker attach d1265c697925 # 进入容器
#进入容器中
cd tmp/
touch aaa.txt
exit      # 直接关闭容器也行，不论容器是否启动，文件都是存在的
#回到主机上
docker cp  d1265c697925:/tmp/aaa.txt /tmp     # 发现文件已经成功从容器复制到主机上

# 当前拷贝是一个手动的过程，后续可以通过卷的技术实现自动同步 
```



# 四、软件安装

## 1、安装nginx

### （1）docker images

查看是否已经安装了nginx镜像。（当然需要Linux安装好docker并启动）

### （2）search

```shell
docker search nginx --limit 10
```

> 可以通过浏览器访问`https://registry.hub.docker.com/_/nginx`查看官网介绍以及所有支持的tag

### （3）pull 下载镜像

```shell
docker pull nginx:1.21.6     # https://registry.hub.docker.com/_/nginx?tab=tags
docker images   #查看是否下载成功
docker run --name="nginx01" -d -p 8888:80 nginx:1.21.6 #  -d后台启动 -p 宿主机端口:容器内端口
# 容器将以80端口启动nginx，对外主机暴露的8888端口，启动后可以http://192.168.137.110:8888/进行启动。
docker run --name="nginx8889" -d -p 8889:80 nginx:1.21.6  # 开启两个，对外暴露8889端口

docker exec -it nginx01 /bin/bash        # -it以交互方式启动，可以进入到虚拟机中
exit可以退出
nginx的配置文件在：/etc/nginx/nginx.conf
```

![image-20220514195348597](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220514195348597.png)

![image-20211209091234273](https://gitee.com/yj1109/cloud-image/raw/master/img/v3VHxFlJMmGZtI4.png)



### (4) 删除容器

```shell
docker ps      # 查看启动的容器信息
docker stop 8dc5dd5c7e0b
docker rm 8dc5dd5c7e0b
docker rm -f 8dc5dd5c7e0b  # 强制退出
```

![image-20220514195749112](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220514195749112.png)

==思考：==

每次改动nginx配置都需要进入容器内部，比较麻烦，是否可以在容器外部提供一个映射路径，达到在宿主机修改文件就能同步到内部？数据卷



## 2、安装tomcat

```shell
docker pull tomcat
docker run -d -p 8888:8080 --name="tomcat01" tomcat
# docker run -it --rm tomcat # 一般用于测试，当退出时，就会自动删除容器  docker ps -a 找不到对应的数据了
http://192.168.137.110:8888/ #访问 404 ，没有默认项目
 
#发现问题
1、linux命令少了。
2、tomcat目录下没有webapps目录
原因是：阿里云镜像默认为最小的，删除了所有的没有必要的内容，保证最小可运行环境。

tomcat目录下有一个webapps.disk目录，可以把里面的项目复制到webapps中即可
cd /usr/local/tomcat/webapps.dist
cp ./* ../webapps -r
此时就有文件了
页面访问url即可进入：
http://192.168.137.110:8888/
```

思考：我们每次部署项目需要进入容器内部，十分麻烦，能否在容器外部提供一个映射路径，在外面部署后即可同步到内部容器？

数据卷



## 3、Docker UI

**portainer**

```shell
docker run -d -p 9000:9000 --name=portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock --privileged=true portainer/portainer

启动后登录http://192.168.137.110:9000 即可查看docker启动的容器
```



​	

# 五、Docker镜像讲解

## 1、镜像介绍

### （1）什么是镜像？

镜像是一种轻量级的可独立运行的软件包，用来打包运行环境和基于运行环境开发的软件，它包含运行某个软件所需要的所有内容，包括代码、运行时库以及环境变量和配置文件。

所有的应用可以直接打包成镜像，直接跑起来



### （2）Docker镜像加载

#### 1.1 UnionFS（联合文件系统）

分层下载，共用底层内核等文件。

#### 1.2 加载原理

docker的镜像是由一层一层的文件系统组成，这种层级文件系统称为UnionFS

bootfs(boot file system) 主要包括bootloader和kernel, bootloader主要是引导加载kernel，Linux刚启动的时候就会加载bootfs文件系统，docker的镜像最底层是bootfs。这一层和我们的Linux系统是一样的，包括boot的就加载器和内核。当boot加载完成后整合内核就在内存中了，此时内存的使用权已经由bootfs转交给了内核，此时系统就会卸载bootfs.



rootfs，在bootfs之上。包括的就是典型的linux系统中的/etc, /bin等标准目录和文件。rootfs就是各种不同的操作系统发行版。如centos、ubuntu等

![image-20211213230811330](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211213230811330.png)

![img](https://s2.loli.net/2021/12/10/wfsz7QVvoxG9qUY.gif)

![image-20211213230837372](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211213230837372.png)





### （3）我们docker下载的centos，为什么那么小呢？

![image-20211210082849879](https://gitee.com/yj1109/cloud-image/raw/master/img/iUQxodzjgX5qNpb.png)

对于精简的OS, rootfs可以很小，只需要包含最基础的命令、工具和程序库。因为底层用的是主机的内核，自己只用提供rootfs即可。由此可见，不同的linux发行版，bootfs基本一致，rootfs可能有差异，因此 不同的发行版可以共用bootfs.



Docker的镜像都是只读的，当容器启动时，一个新的可写层被加载到镜像的顶部。

这就是我们通常说的容器层，容器层之下叫镜像层。

![img](https://gitee.com/yj1109/cloud-image/raw/master/img/mrCWlbt2k4p5Ras.jpg)

![image-20211210085345349](https://gitee.com/yj1109/cloud-image/raw/master/img/GiVo4HNjSUTsEyc.png)



## 2、Commit镜像

```shell
docker commit提交容器成为一个新的副本

docker commit -m "提交的描述信息" -a="作者" 容器id(name也行) 目标镜像名:[tag]
```

练习：

我们从仓库直接拉下来的tomcat中webapps目录没有项目，无法直接测试。

我们可以把webapps.dist里的项目复制到webapps中，并把当前状态的tomcat提交成一个新的镜像，后续使用。

```shell
#启动tomcat
docker run -d --name=tomcat01 -p 8888:8080 tomcat

#交互模式进入tomcat容器
docker exec -it tomcat01 /bin/bash

# 复制文件内容
rmdir webapps                 # 删目录
mv webapps.dist/ webapps	  # 重命名

#回到宿主机，进行提交
exit
docker commit -m "Add projects to webapps directory" -a="jun" 6cab865d3cbf mytomcat:1.0

# 查看验证
docker images 
#启动自己的镜像，发现项目已经在了
docker run -d --name="mytomcat01" -p 8181:8080 mytomcat:1.0
```



## 3、容器数据卷

容器之间可以有一个数据共享的技术，将docker容器中产生生的数据同步到本地。

这就是卷技术：目录的挂载，将我们容器内的目录，挂载到linux上。

可以实现容器的持久化和同步操作，容器之间也是可以数据共享的。



### （1）使用数据卷 1：命令 -v

> 方式一：直接使用命令 -v

```shell
docker run --name="centos01" -d -it -v 宿主机路径:容器路径 centos
docker run --name="centos01" -d -it -v /home/share:/home centos   # 把容器中的/home目录内容映射到主机的/home/share。双向绑定的 。启动后发现主机在home目录下自动创建了share文件夹。

# 启动后可以查看具体的挂载方式
docker inspect centos01
```

![image-20211211131951607](https://gitee.com/yj1109/cloud-image/raw/master/img/vW6yXuHRIAYjFQg.png)



#### 1.1 Mysql启动以及持久化

```shell
docker run --name="mysql01" -d -it -p 3333:3306 -e MYSQL_ROOT_PASSWORD=123456 -v /home/share/mysql/data:/var/lib/mysql -v /home/share/mysql/config:/etc/config/config.d mysql:5.7

docker exec -it mysql01 /bin/bash

mysql --version
```

发现，我们挂载到本地的数据卷在容器删除后再次启动一个新的，并配置同样的挂在路径后，数据没有丢失，说明实现了持久化。



#### 1.2 具名挂载和匿名挂载

挂载可以通过-v 命令来实现，分为三种：

##### 1、指定路径挂载

```shell
# -v 主机路径:容器路径
-v /home/share/redis/data:/data
docker run -d -it --name=redis01 -p 8899:6379 -v /home/share/redis/data:/data redis

docker volume ls
```

##### 2、匿名挂载

```shell
-v 容器路径
-v /data
docker run -d -it --name=redis02 -p 9000:6379 -v /data redis
docker volume ls
docker volume inspect e4954e7e6f2a407ba21b268683bcbd37c287aa48a4f992b71b3c9c5974635048


[root@localhost data]# docker volume inspect e4954e7e6f2a407ba21b268683bcbd37c287aa48a4f992b71b3c9c5974635048
[
    {
        "CreatedAt": "2021-12-12T10:40:38+08:00",
        "Driver": "local",
        "Labels": null,
        "Mountpoint": "/var/lib/docker/volumes/e4954e7e6f2a407ba21b268683bcbd37c287aa48a4f992b71b3c9c5974635048/_data",
        "Name": "e4954e7e6f2a407ba21b268683bcbd37c287aa48a4f992b71b3c9c5974635048",
        "Options": null,
        "Scope": "local"
    }
]

可以看到卷数据默认存在宿主机的/var/lib/docker/volumes目录下
```

##### 3、具名挂载

```shell
-v juming-volume:容器路径
-v /data
docker run -d -it --name=redis03 -p 9001:6379 -v juming-volume:/data redis
# /data 映射到 /var/lib/docker/volumes/juming-volume/_data
docker volume ls
docker volume inspect juming-volume
```

##### 4、扩展

通过-v中 添加ro rw参数来指定容器内读写权限: 默认是rw

```shell
docker run -d -it --name=redis04 -p 9002:6379 -v juming2-volume:/data:ro redis  # ro表示只能通过宿主机修改
docker exec -it redis04 /bin/bash

root@2806df7cfcc6:/data# touch a.txt
touch: cannot touch 'a.txt': Read-only file system

```



### （2）数据卷2：通过dockerfile挂载

> 方式二：通过dockerfile挂载 

Docker就是用来创建docker镜像的构建文件。命令脚本：手工打造镜像。

通过脚本可以生成镜像，镜像是一层一层的，脚本一个个的命令就是对应的一个一个layer.

```shell
cd /home/docker-test-volume
touch dockerfile01
vi dockerfile01
FROM centos
VOLUME ["volume01","volume02","volume03-name:/data"]
CMD echo "----end----"
CMD /bin/bash

# 构建镜像
docker build -f /home/docker-test-volume/dockerfile01 -t jun/centos-test:1.0 .   # 记着有一个点

[root@localhost docker-test-volume]# docker build -f /home/docker-test-volume/dockerfile01 -t jun/centos-test:1.0 .
Sending build context to Docker daemon  2.048kB
Step 1/4 : FROM centos
 ---> 5d0da3dc9764
Step 2/4 : VOLUME ["volume01","volume02","volume03-name:/data"]
 ---> Running in 01eb4c561ce0
Removing intermediate container 01eb4c561ce0
 ---> a65326a40035
Step 3/4 : CMD echo "----end----"
 ---> Running in 5ba9f49aafd7
Removing intermediate container 5ba9f49aafd7
 ---> 47e62e17bd4b
Step 4/4 : CMD /bin/bash
 ---> Running in 2d1e90525f19
Removing intermediate container 2d1e90525f19
 ---> b201b4264ee5
Successfully built b201b4264ee5
Successfully tagged jun/centos-test:1.0

```

验证：

```shell
docker images
docker run -it -d --name="mycentos" b201b4264ee5
docker exec -it mycentos /bin/bash
docker inspect mycentos
```

![image-20211212130515422](https://gitee.com/yj1109/cloud-image/raw/master/img/XCipfzbjPKUa254.png)

加入构建镜像时没有挂载卷，要在启动时手动挂载 -v  卷名:容器内路径



### （3）数据卷容器

> 容器间实现数据同步

![image-20211212172912201](https://gitee.com/yj1109/cloud-image/raw/master/img/7Ot1ZokJnMjHvpu.png)

> 启动三个上述自制的centos, 一个父centos00, 两个子centos01,centos02测试数据共享

```shell
docker run --name="centos00" -it -d jun/centos-test:1.0
docker run --name="centos01" -it -d --volumes-from centos00 jun/centos-test:1.0
docker run --name="centos02" -it -d --volumes-from centos00 jun/centos-test:1.0

docker exec -it centos00 bash
# 发现012 内容同步，互相备份同步。
docker stop centos00
docker rm centos00
# 发现12还可以数据同步
docker run --name="centos03" -it -d --volumes-from centos01 jun/centos-test:1.0
# 启动一个新的容器，并以1作为父容器，发现可以进行数据完全备份，并可以实现新数据同步。
```

结论：

容器之间配置信息的传递，数据卷容器的生命周期一直持续到没有容器使用为止。



# 六、DockerFile

## 1、Dockerfile介绍

Dockerfile是用来构建docker镜像的文件，是一系列参数脚本。通过Dockerfile可以构建自己的镜像。

构建步骤：

1、编写一个dockerfile文件

2、docker build构建一个镜像

3、docker run 运行镜像

4、docker push 发布镜像（dockerhub、阿里云镜像）  

![image-20211214072229760](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211214072229760.png)

很多官方镜像都是纯净版基础包，很多功能都没有，通常我们自己搭建自己需要的镜像内容。



## 2、DockerFile 构建过程

### 1.1 基础知识：

1、每个保留字都是大写

2、执行从上到下

3、#表示注释

4、每个指令都会创建提交一个新的镜像层，并进行提交。

dockerfile是面向开发的，我们以后发布项目，做镜像，就需要编写dockerfile文件。

dockerfile逐渐成为企业交付的标准。

**dockerFile ----==bulid==----> dockerImage  ----==run==----> dockerContainer**

dockerFile :构建文件，定义了一些步骤，是源代码

dockerImage :通过dockerfile构建生成镜像，最终发布和运行的产品。

dockerContainer: 就是镜像运行起来，提供服务。



### 1.2 Docker的指令

```shell
FROM  			#基础镜像，一切从这里开始
MAINTAINER		#作者信息，一般是姓名+邮箱
RUN				#镜像构建的时候需要运行的命令
ADD				#添加，如果要构建有tomcat的镜像，就需要添加tomcat压缩包
WORDIR			#镜像的工作目录 ：eg：/bin/bash
VOLUME			#挂载的目录
EXPOSE			#暴露端口配置  EXPOSE命令只是声明了容器应该打开的端口并没有实际上将它打开
CMD				#指定这个容器启动时要运行的指令，只有最后一个会执行
ENTRYPOINT		#指定这个容器启动时要运行的指令，可以追加执行
ONBUILD			#当构建一个被继承dockerfile，这个时候就会运行ONBUILD指令
COPY			#类似ADD，将文件拷贝到镜像中
ENV				#构建的时候设置环境变量
```

![image-20211213224925196](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211213224925196.png)



官方的centos是基础版本，很多功能都没有，我们可以通过在官方centos的基础上构建自己的centos镜像

### 1.3 构建自己的centos镜像

#### 1、编写Dockerfile

```shell
FROM centos:centos7
MAINTAINER jun<1272097458@qq.com>

ENV MYPATH "/usr/local"
WORKDIR $MYPATH

RUN yum install -y vim
RUN yum install -y net-tools

EXPOSE 80

CMD echo $MYPATH
CMD echo "------Build successfully------"

CMD /bin/bash
```

#### 2、执行脚本进行build

```shell
docker build -f /home/dockerfile/my-centos/Dockerfile -t jun/centos-test:1.0 .
```

![image-20220515101024338](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220515101024338.png)



### 1.4 CMD和ENTRYPOINT的区别

```shell
https://www.cnblogs.com/sparkdev/p/8461576.html
https://docs.docker.com/engine/reference/builder/#cmd
```

#### （1）CMD:

> There can only be one `CMD` instruction in a `Dockerfile`. If you list more than one `CMD` then only the last `CMD` will take effect.
>
> **The main purpose of a `CMD` is to provide defaults for an executing container.** These defaults can include an executable, or they can omit the executable, in which case you must specify an `ENTRYPOINT` instruction as well.

```shell
FROM centos:latest
CMD ["ls", "-a"]

# 构建镜像
docker build -f dockerfile-cmd-test -t cmd-test .

# 执行
docker run 5c6437d88c82   # 正常运行，执行了 ls -a 列出了所有的文件

docker run 5c6437d88c82 -l # 想要执行 ls -a -l 列出详细信息, 但是报错，因为替换了CMD ["ls", "-a"] 中的ls -a 
docker: Error response from daemon: OCI runtime create failed: container_linux.go:380: starting container process caused: exec: "-l": executable file not found in $PATH: unknown.

```

#### （2）ENTRYPOINT

```shell
FROM centos:latest
ENTRYPOINT ["ls", "-a"]

# 构建镜像
docker build -f dockerfile-entrypoint-test -t entrypoint-test .

# 执行
docker run d96915fb1342   # 正常运行，执行了 ls -a 列出了所有的文件

docker run d96915fb1342 -l # 想要执行 ls -a -l 列出详细信息, 正常执行，ENTRYPOINT ["ls", "-a"] 中进行追加
```

> 对于 Dockerfile 来说，CMD 和 ENTRYPOINT 是非常重要的指令。它们不是在构建镜像的过程中执行，而是在启动容器时执行，所以主要用来指定容器默认执行的命令。

#### （3）同时使用 CMD 和 ENTRYPOINT 的情况

CMD 和 ENTRYPOINT 指令的用法，会区分 exec 模式和 shell 模式

**exec 模式**

```shell
FROM ubuntu
CMD [ "top" ]
```

**shell模式**

```shell
FROM ubuntu
CMD top
```

对于 CMD 和 ENTRYPOINT 的设计而言，多数情况下它们应该是单独使用的。当然，有一个例外是 CMD 为 ENTRYPOINT 提供默认的可选参数。
可以总结出下面几条规律：
   • 如果 ENTRYPOINT 使用了 shell 模式，CMD 指令会被忽略。
   • 如果 ENTRYPOINT 使用了 exec 模式，CMD 指定的内容被追加为 ENTRYPOINT 指定命令的参数。
   • 如果 ENTRYPOINT 使用了 exec 模式，CMD 也应该使用 exec 模式。
真实的情况要远比这三条规律复杂，好在 docker 给出了官方的解释，如下图所示：

![image-20220515105259217](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220515105259217.png)



## 3、Docker镜像发布

### （1）制作tomcat镜像

```shell
FROM centos:latest
MAINTAINER jun<1272097458@qq.com>
ENV MYPATH "/usr/local"

COPY readme.txt $MYPATH/readme.txt
ADD	apache-tomcat-8.5.73.tar.gz $MYPATH
ADD	jdk-8u161-linux-x64.tar.gz $MYPATH

ENV JAVA_HOME $MYPATH/jdk1.8.0_161
ENV CATALINA_HOME $MYPATH/apache-tomcat-8.5.73
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/bin:$CATALINA_HOME/lib

WORKDIR $MYPATH
EXPOSE 8080

# ENTRYPOINT /bin/bash  如果再加上这个 下面的启动命令就不会自动执行
CMD /usr/local/apache-tomcat-8.5.73/bin/startup.sh && tail -F /usr/local/apache-tomcat-8.5.73/logs/catalina.out
```

```shell
docker build -t my-centos-tomcat:1.0 .   # 默认扫描Dockerfile文件

# 启动
docker run -it -d \
--name="mytomcat01" \
-p 9090:8080 \
-v /home/dockerfile/tomcat-dockerfile/share/webapps:/usr/local/apache-tomcat-8.5.73/webapps \
-v /home/dockerfile/tomcat-dockerfile/share/logs:/usr/local/apache-tomcat-8.5.73/logs \
my-centos-tomcat:1.0

docker exec -it mytomcat01 /bin/bash
```



### （2）发布到dockerHub

```shell
https://hub.docker.com
# 登录
docker login -u1272097458
docker push my-centos-tomcat:1.0  # denied: requested access to the resource is denied

可以给镜像重命名（必须加上和dockerhub相同的用户名才能push，否则失败）
docker tag my-centos-tomcat:1.0 1272097458/my-centos-tomcat:1.0
docker push 1272097458/my-centos-tomcat:1.0  # OK
```



阿里云镜像仓库

```shell
https://cr.console.aliyun.com/cn-beijing/instance/dashboard
# 创建命名空间
# 创建本地仓库
docker push 相关文档：
https://cr.console.aliyun.com/repository/cn-beijing/docker-repository-test/jun-repo/details
docker login --username=阿山1109 registry.cn-beijing.aliyuncs.com
docker tag e0cda0a7f0eb registry.cn-beijing.aliyuncs.com/docker-repository-test/jun-repo:1.0
docker push registry.cn-beijing.aliyuncs.com/docker-repository-test/jun-repo:1.0
```



# 七、Docker网络

## 1、Docker0

![image-20211218171638921](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211218171638921.png)

> 如果再启动两个tomcat容器，那么主机和tomcat，以及两个tomcat之间是如何互相访问的？

每当启动一个docker容器时，docker会自动分配一个地址172.17.0.X

桥接模式，使用的技术是evth-pair

1、启动一个tomcat01,此时主机的网络以及容器的网络如下：

![image-20211218172121096](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211218172121096.png)

![image-20211218172228982](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211218172228982.png)

![image-20211218172259752](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211218172259752.png)

![image-20211218172307782](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211218172307782.png)

此时主机和容器tomcat01能够互相ping通。

2、启动一个tomcat02,发现给其分配的ip是172.17.0.3，此时发现主机192.168.137.110，两个容器都能互相联通。

我们发现这个容器带来的网卡都是一对一对的

evth-pair就是一对的虚拟设备接口，他们都是成对出现的，一端连着协议，一端彼此相连。

正是由于这个特性，evth-pair充当一个桥梁，连接各种虚拟网络设备。

openstac, docker容器之间的连接，ovs的连接都是通过evth-pair技术实现的。

![image-20211218181307807](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211218181307807.png)



结论：

tomcat01和tomcat02共用一个’路由器‘ Docker0

所有容器不指定路由的情况下都是Docker0路由，Docker会给我们分配一个默认的可用IP

Docker中所有的网络接口都是虚拟的，虚拟的转发效率极高（内网传递效率高）

只要容器关闭，对应的一对网桥就会消失。



## 2、--link

> 当我们启动两个容器，想要其通过容器名称进行互通，避免了ip的随机性，如何做到？
>
> 1. 添加启动参数 --link
> 2. 使用自定义网络，而非默认的docker0

```shell
# 启动两个tomcat
docker run --name=tomcat01 -it -d my-centos-tomcat:1.0
docker run --name=tomcat02 -it -d my-centos-tomcat:1.0

# 进入容器tomcat01, 发现和tomcat02可以通过ip联通，无法通过容器名称联通
docker exec -it tomcat01 bash
[root@88e680022610 local]# ping 172.17.0.3
PING 172.17.0.3 (172.17.0.3) 56(84) bytes of data.
64 bytes from 172.17.0.3: icmp_seq=1 ttl=64 time=0.066 ms
64 bytes from 172.17.0.3: icmp_seq=2 ttl=64 time=0.078 ms
[root@88e680022610 local]# ping tomcat03
ping: tomcat03: Name or service not known

# 启动第三个tomcat,并用--link和tomcat01进行连接
docker run --name=tomcat03 -it -d --link tomcat01 my-centos-tomcat:1.0
# 进入容器tomcat03,发现可以通过tomcat01的容器名进行连接。
# 相反,在tomcat01中还是没法通过tomcat03联通
docker exec -it tomcat03 bash
[root@6a73e4d0ea4b local]# ping tomcat01
PING tomcat01 (172.17.0.2) 56(84) bytes of data.
64 bytes from tomcat01 (172.17.0.2): icmp_seq=1 ttl=64 time=0.071 ms
64 bytes from tomcat01 (172.17.0.2): icmp_seq=2 ttl=64 time=0.050 ms

# 探究：在tomcat03中，发现将tomcat01主机名和id配置到了hosts中了。所以可以和tomcat01联通
[root@6a73e4d0ea4b local]# cat /etc/hosts 
127.0.0.1       localhost
::1     localhost ip6-localhost ip6-loopback
fe00::0 ip6-localnet
ff00::0 ip6-mcastprefix
ff02::1 ip6-allnodes
ff02::2 ip6-allrouters
172.17.0.2      tomcat01 88e680022610
172.17.0.4      6a73e4d0ea4b
```

```shell
docker network --help # 查看帮助
docker network ls   # 查看所以的网络
docker network inspect dac97e83d5a9 #可以查看相关的网络配置。
```



## 3、自定义网络

```shell
我们之前启动的容器没有指定网络，默认有--net bridge(docker0)
docker run --name=tomcat01 -it -d my-centos-tomcat:1.0
==》 
docker run --name=tomcat01 -it -d --net bridge my-centos-tomcat:1.0
docker0特点：默认，域名不能访问。--link可以打通
```

```shell
查看所有的docker网络
[root@localhost ~]# docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
dac97e83d5a9   bridge    bridge    local
0f7484af7fff   host      host      local
b0abb1587a7e   none      null      local
```

网络模式：

1. bridge：桥接（默认；自定义也是用这种模式）
2. host：主机模式，和宿主机共享网络
3. none：不配置网络
4. container：容器网络联通（用的少）

```shell
# 创建一个网络：
docker network create --driver bridge --subnet 192.192.0.0/16 --gateway 192.192.0.1 mynet
# 创建成功后，可以在容器启动时指定为自定义的网络mynet, docker network inspect mynet可以查看具体配置
[root@localhost ~]# docker network ls
NETWORK ID     NAME      DRIVER    SCOPE
dac97e83d5a9   bridge    bridge    local
0f7484af7fff   host      host      local
9e67403522db   mynet     bridge    local
b0abb1587a7e   none      null      local

测试：
启动两个tomcat, 使用自定义的网络。
docker run --name=tomcat11 -it -d --net mynet my-centos-tomcat:1.0
docker run --name=tomcat12 -it -d --net mynet my-centos-tomcat:1.0

# 进入两个容器，发现可以通过容器名进行互联。不适用--link，也可以通过name互联
docker exec -it tomcat11 bash
```

不同的集群使用不同的网络，保证集群是健康的。



## 4、网络联通

![image-20211219115643858](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211219115643858.png)

网卡和网卡之间不能互联，而容器和网卡可以。

通过查看API，发现connect命令可以实现网络互联

![image-20211219115835773](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211219115835773.png)

![image-20211219120253831](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211219120253831.png)

```shell
# 将tomcat01加入到mynet网络中
docker network connect mynet tomcat01

# 添加成功后，发现在mynet网络中直接多了tomcat01的容器信息
docker network inspect mynet
```

![image-20211219120905275](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211219120905275.png)



此时tomcat01和tomcat11与tomcat12都在mynet网络下，可以进行互通。

在tomcat01中通过ip addr查看，发现是有两个IP地址

```shell
[root@88e680022610 local]# ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
8: eth0@if9: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:ac:11:00:02 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 172.17.0.2/16 brd 172.17.255.255 scope global eth0
       valid_lft forever preferred_lft forever
19: eth1@if20: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default 
    link/ether 02:42:c0:c0:00:04 brd ff:ff:ff:ff:ff:ff link-netnsid 0
    inet 192.192.0.4/16 brd 192.192.255.255 scope global eth1
       valid_lft forever preferred_lft forever

```

结论：

如要想要跨网络操作别人，就需要docker network connect 网络容器；



# 八、SpringBoot项目通过Dockerfile生成镜像

1、获取springboot的jar包

2、编写Dockerfile文件

```shell
FROM openjdk:11.0.15-oraclelinux7
COPY *.jar /app.jar

# EXPOSE 8888
CMD ["--server.port=9999"]
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

3、上传文件到 /home/dockerfile/springboot-demo-docker

![image-20220515090112680](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220515090112680.png)

4、构建镜像

```shell
docker build -f Dockerfile -t boot-server:1.0 .
```

镜像构建是一步一步来的，openjdk:11.0.15-oraclelinux7在本机镜像库里没有，会先进行下载，再build

![image-20220514211745095](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220514211745095.png)



5、启动镜像boot-server:1.0

```shell
# -p 宿主机:容器内暴露端口，9999是容器内项目实际使用的端口
docker run -it -d -p 8888:9999 --name=boot-server8888 boot-server:1.0   
```

* 一个项目启动会指定一个端口如9999，容器内可以用9999访问
* -p可以对这个端口向宿主机进行映射  -p 8888:9999 表示将9999映射到宿主机的8888端口。可在宿主机内用8888访问
* dockerfile中的EXPOSURE可以不进行配置，启动容器时指定-p即可

![image-20220514234340882](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220514234340882.png)

```shell
docker inspect 2b57aed8a553
```

![image-20220514234405228](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220514234405228.png)

![image-20220514234434852](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220514234434852.png)

6、测试

```shell
进入容器
docker exec -it boot-server8888 /bin/bash        # -it以交互方式启动，可以进入到虚拟机中
# 安装网络工具
yum install -y curl                        # curl http://localhost:9999
yum -y update && yum install -y iproute     # ip addr
yum install -y vim
yum install -y net-tools              # 安装后可以用ifconfig查看ip信息
# 在容器内部应该用9999端口来访问
```

虚拟机内：8888

![image-20220514213403999](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220514213403999.png)

容器内：9999

![image-20220515091650039](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220515091650039.png)

![image-20220515090313074](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20220515090313074.png)

