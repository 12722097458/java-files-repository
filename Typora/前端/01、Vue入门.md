## 一、入门Vue

npm run dev

### 1、Hello World编写

1、导入vue.min.js

<script src="js/vue.min.js"></script>

2、编写相关代码

通过感叹号!引入html的模板

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
</body>
</html>
```

3、写VUE的script以及body中对其引用

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="js/vue.min.js"></script>
    
    
</head>
<body>

    <div id="divId">
        <!-- 插值表达式 -->
        {{msg}}
    </div>
    
    <script>
        new Vue({
            el: '#divId',
            data: {
                msg : 'Hello Vue!'
            }
        })
    </script>

    
</body>
</html>
```

script的内容要写在div的下面，否则无法解析

### 2、v-bind单向绑定

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="js/vue.min.js"></script>
    
    
</head>
<body>

    <div id="divId" v-bind:style="msg">
        {{keyWord}}
    </div>
    
    <script>
        new Vue({
            el: '#divId',
            data: {
                msg : 'color:red;',
                keyWord: '单向绑定!'
            }
        })
    </script>

    
</body>
</html>
```



### 3、v-model双向绑定

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="js/vue.min.js"></script>
    
    
</head>
<body>

    <div id="divId">
        {{keyWord}}
        <br/>
        <input type="text" v-bind:value="keyWord"></input>

        <br/>
        双向绑定：<input type="text" v-model="keyWord"></input>

    </div>
    
    <script>
        new Vue({
            el: '#divId',
            data: {
                keyWord: '绑定'
            }
        })
    </script>

    
</body>
</html>
```

### 4、v-on/@事件绑定

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="js/vue.min.js"></script>
    
    
</head>
<body>

    <div id="divId">
        <button v-on:click="show()">{{keyWord}}</button>
        <br/>
        <button @click="show()">{{keyWord}}</button>

    </div>
    
    <script>
        new Vue({
            el: '#divId',
            data: {
                keyWord: '事件绑定'
            },
            methods: {
                show() {
                    alert('show()...')
                }
            }
        })
    </script>

    
</body>
</html>
```

两种写法：

（1）v-on:click="show()"

（2）@click="show()"

### 5、v-if-else条件渲染指令

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="js/vue.min.js"></script>
    
    
</head>
<body>

    <div id="divId">
        <input type="checkbox" v-model="default2" />
        <br/>
        <div v-if="default2">选中了</div>
        <div v-else>未选中</div>
    </div>
    
    <script>
        new Vue({
            el: '#divId',
            data: {
                default2: false
            }
        })
    </script>

    
</body>
</html>
```

### 6、v-for循环指令

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="js/vue.min.js"></script>
    
    
</head>
<body>

    <div id="divId">
        <div v-for="(user,index) in userList">
            {{index}} -- {{user.name}} -- {{user.age}}
        </div>
    </div>
    
    <script>
        new Vue({
            el: '#divId',
            data: {
                userList: [
                    {name:"Jack",age:21},
                    {name:"Rose",age:18},
                    {name:"Tom",age:22}
                ]
            }
        })
    </script>

    
</body>
</html>
```



## 二、axios

> axios是独立于vue的一个项目，可以用于浏览器和node.js中发送ajax请求.

（1）引入vue.js和axios.js

```html
<script src="js/vue.min.js"></script>
<script src="js/axios.min.js"></script>
```

（2）结合ajax进行数据展示

模拟数据

```json
{
    "code": 200,
    "msg": "success",
    "users": {
        "items": [
            {"name": "Jack","age": 20},
            {"name": "Rose","age": 21},
            {"name": "Tom","age": 22}
        ]
    }
}
```

页面编写

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="js/vue.min.js"></script>
    <script src="js/axios.min.js"></script>

    
</head>
<body>

    <div id="divId">
        <div v-for="user in userList">
            {{user.name}} -- {{user.age}} <br/>
        </div>
    </div>
    
    <script>
        new Vue({
            el: '#divId',
            data: {
                userList: [
                ]
            },
            created() { // 在页面渲染之前先执行
                //调用方法，得到json数据
                this.getList();
            },
            methods:{
                getList() {
                    // 使用axios方式进行ajax请求
                    axios.get("user.json")
                    .then(
                        response => {
                            this.userList = response.data.users.items;
                            console.log(this.userList);
                            console.log(response.data.code);
                        }
                    )    // 请求成功
                    .catch(
                        response => {
                            console.log(response);
                        }
                    )   // 请求失败
                }
            }
        })
    </script>

    
</body>
</html>
```



## 三、element-ui

> https://element.eleme.cn/#/zh-CN/component/quickstart

Bootstrap是基于jQuery的，而element-ui是基于Vue的前端快速开发工具。



## 四 、Node.js

> 1、是JavaScript的运行环境
>
> 2、可以模拟服务端效果，无需Tomcat等服务器

BFF: Backend For Frontend（服务于前端的后端），即专门用于为前端业务提供数据的一个后端程序。



### 1、下载nodejs

一路下一步即可，最终通过cmd窗口`node -v`查看是否安装成功。

### 2、入门案例

新建一个01-hello.js文件，并写入简单的打印语句

```js
console.log(1 + 1);
```

在当前文件的目录下，打开cmd，输入：

```shell
node 01-hello.js
```

即可执行js文件，打印出2。

> 也可以在vscode中打开：当前文件夹-右键-Open in Integrated Terminal



## 五、npm包管理工具

> NPM全称Node Package Manager，是Node.js包管理工具，是全球最大的模块生态系统，里面所有的模块都是开源免费的；也是Node.js的包管理工具，相当于管理前端的Maven 。



### 1、安装

在安装nodejs后，会自动安装好npm

可以通过命令`npm-v`查看对应的版本号。

### 2、npm使用

（1）项目初始化

新建文件夹npm，输入：

```shell
npm init -y
```

初始化成功后会生成一个package.json的文件，此文件类似于pom.xml，保存了一些项目的基本信息。

（2）修改全局配置

设置镜像地址：

```shell
#经过下面的配置，以后所有的 npm install 都会经过淘宝的镜像地址下载
npm config set registry https://registry.npm.taobao.org 
#查看npm配置信息
npm config list
```

（3）npm install下载指定的依赖包

```shell
#使用 npm install 安装依赖包的最新版，
#模块安装的位置：项目目录\node_modules
#同时package.json 文件中，依赖包会被添加到dependencies节点下，类似maven中的 <dependencies>
#默认参数：--save  简写  -S  将当前依赖保存在dependencies节点下
npm install jquery
```



## 六、ES6模块化开发

> 模块化是指两个模块之间相互调用，提升扩展性

#### 1、创建项目，并进行初始化

es6-module-demo  -》 初始化后生成package.json文件。

#### 2、创建01.js

```js
export default{
    list() {
        console.log("list...")
    },
    save() {
        console.log("save...")
    }
}
```

#### 3、创建02.js

02.js引用01.js并调用01的方法。

```js
import user from './01.js';   // 引入

user.list();        // 执行   node 02.js
user.save();        // npm install -g babel-cli    
```

#### 4、node 02.js运行

发现有问题：

> 因为在node.js中无法直接执行ES6的模块化代码，需要把ES6转换为ES5才可以。

#### 5、安装babel转码器解决es6模块化执行问题。

（1）安装babel工具

```shell
npm install -g babel-cli

babel --version  # 查看版本
```

如果查看版本报错，可以尝试修改VS的打开方式为：Command Prompt(CMD)

（2）编写.babelrc配置文件，放在根目录下。

```shell
{
    "presets": ["es2015"],
    "plugins": ""
}
```

（3）安装转码器

```shell
npm install -D babel-preset-es2015
```

安装后生成node_modules文件夹内容。

（4）通过babel将es6的代码转换成es5

把src/js下的es6代码转换成es5并存放在src/code-es5目录下

```shell
babel src/js -d src/code-es5 
```

![image-20210710231705617](https://gitee.com/yj1109/cloud-image/raw/master/img/image-20210710231705617.png)

#### 6、再次运行02.js

进入新生成的code-es5目录下

```shell
D:\yygh-front-end\es6-module-demo\src\code-es5>node 02.js
```

执行02.js，即可正常打印出最终结果。



## 七、webpack打包工具

#### 1、下载安装webpack

```shell
npm install -g webpack webpack-cli
webpack -v  # show version
```



## 八、VUE的功能

> 1. 实现前后端交互，通过表格展示出医院信息
> 2. 分页
> 3. 条件查询