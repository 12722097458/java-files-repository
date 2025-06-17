git学习及使用 https://www.bilibili.com/video/BV1FE411P7B3?p=6&spm_id_from=pageDriver

### 一、git安装

官网https://git-scm.com/进行版本选择与安装

下载windows64位系统，选择好安装路径，一路下一步即可。

### 二、git配置

```shell
git config -l     ## 查看配置

git config --system --list   #查看系统配置
# git系统配置：D:\software\tools\Git\etc\gitconfig

git config --global  --list   #查看用户配置
# 用户配置:‪C:\Users\ayinj\.gitconfig
```

配置用户信息：

```shell
git config --global user.name Jack

git config --global user.email ayinjun1109@163.com

```

配置后

![image-20210227142243797](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20210227142243797.png)

### 三、实际操作

#### 1、clone一个项目到本地

第一种：

```shell
git clone https://github.com/12722097458/images-cloud-repo.git
git status

手动创建一个文件， 模拟push操作
git status
git add .
git commit images/a.xls -m "test push"
git push origin master // 命令行会报错 remote: Support for password authentication was removed on August 13, 2021.
可以用idea GUI直接通过配置好的token push.
```

第二种：

```shell
clone的时候直接配置好token, 后续push的时候就不会校验用户了

git clone https://github.com/12722097458/images-cloud-repo.git
git clone https://ghp_afhmNy4JAa9EDGdajJrLHwIXXXXXX@github.com/12722097458/images-cloud-repo.git  // 亲测可用
```



### 四、git问题处理

####  1、如果push443 timeout

取消全局代理即可

```shell
取消全局代理：
git config --global --unset http.proxy
 
git config --global --unset https.proxy
```

#### 2、本地代码绑定远程仓库

先设置remote的url

再pull *# 如果远程仓库已有文件（如 README.md），需要先拉取* git pull origin master --allow-unrelated-histories

```shell
git remote add origin https://gitee.com/yj1109/cloud-image.git
git remote -v
git pull -r origin master --allow-unrelated-histories

```



Git分支

1、git branch       -------查看当前项目的分支

​							-r   查看当前项目远程的分支

2、git branch dev       --新建dev分支

3、git checkout  -b dat             --新建dat分支并切换到dat分支

4、git merge [branch]           --合并指定分支到当前分支

5、git branch -d [branch]    --删除分支

 



### 五、基于PicGo的gitee图床实现Typro文件自定义上传

![image-20211213225155836](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211213225155836.png)

![image-20211213225220401](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20211213225220401.png)



