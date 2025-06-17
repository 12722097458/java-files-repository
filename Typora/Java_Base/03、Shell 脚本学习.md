Shell 脚本（shell script），是一花括号是可选的，加不加都行种为 shell 编写的脚本程序。

> 所以脚本其实就是短小的、用来让计算机自动化完成一系列工作的程序，这类程序可以用文本编辑器修改，不需要编译，通常是解释运行的。



## 1、shell定义变量规则：

- ​	英文
- ​	数字（变量开头不能是数字）
- ​	下划线

注意：不能用bash的关键字，your_name====="runoob.com"  变量名和等号之间不能有空格

变量定义时，默认都是字符串。可以不写引号。



## 2、使用变量

1、echo ${SHELL}      -->   /bin/bash    说明linux默认用bash来进行解析shell文本

```bash
编写一个helloworld.sh,最终发现执行方式有三种
1、sh helloworld.sh
2、bash helloworld.sh
3、./helloworld.sh
其中前两种是调用sh的解析器,第三种如果执行权限不够，则无法执行。   -->修改权限 chmod 744 helloworld.sh
```



2、使用一个定义过的变量，只要在变量名前面加美元符号即可，如：

```java
your_name="qinjx"
echo $your_name
echo ${your_name}
```

花括号是可选的，加不加都行。推荐给所有变量加上花括号



```java
//只读变量:设置后无法进行修改
myUrl="https://www.google.com"
readonly myUrl

//删除变量，变量被删除后不能再次使用。unset 命令不能删除只读变量。
unset variable_name
    

```

## 3、shell字符串可以用双引号""，也可以用单引号''

```java
//双引号拼接比较方便
#!/bin/bash
shell_var="Jack";
shell_con="你的名字："${shell_var}"哈！"
echo  "我知道你是"${shell_con};
```

字符串基本用法

```java
//${#str} 查看字符串str的长度
str="abcde"
echo ${#str}      --》5

//截取字符串
echo ${str:1:3}    --> bcd
    
//查找子字符串
查找字符r的位置    
string="runoob is a great site"
echo `expr index "${string}" r`  --> 输出 1
```

## 4、shell数组

```java
#!/bin/bash
//数组定义，值用空格隔开
arr=(str1 str2 str3 str4)
//也可以直接设置值，下标可以不连续
arr[10]="haha"

//获取数组的长度
length=${#arr[*]}       /   length=${#arr[@]}

echo ${arr[2]}
echo ${arr[10]}
```



## 5、Shell传递参数：

文件：changeVar.sh

```java
echo "Shell 传递参数实例！";
echo "执行的文件名：$0";
echo "第一个参数为：$1";
echo "第一个参数为：${1}";
echo "传递参数的个数为：${#}"
echo "传递的参数作为一个字符串显示：$*";
```

使用步骤：在控制台输入：sh changeVar.sh 3 2 1，     即将三个参数传入对应的`$1  `          ;$0为执行的文件名





## 6、shell基本运算符

```java
算术运算符:  
a、val=`expr 2 + 2`   ```英文输入法模式下，按1左边的~  表达式和运算符之间要有空格，例如 2+2 是不对的，必须写成 2 + 2  ; val=xxx这里的=两边不能有空格
b、num=`expr 2 \* 2`;  乘号,除号(*  /)前边必须加反斜杠(\)才能实现乘法运算；还不能是小数
c、可以用$[a+b]   计算简单的运算 sum2=$[num1*num2]    加减乘除都可以，无需转义符，可以有空格
关系运算符：
-eq  /  -ne  /  -gt  /  -lt  /  -ge  /  -le
[ $a == $b ] 返回 false。  不能直接打印出来，可以用于if判断  记着要有空格。
布尔运算符：	！非   /   -o或   /    -a与                  ：[ $a -lt 20 -o $b -gt 100 ] 返回 true。
逻辑运算符： &&     ||  
    
    if [[ ${num1} -eq ${num2} &&  ${a} -eq ${b} ]]
 ==
    if [ ${num1} -eq ${num2} -a  ${a} -eq ${b} ]
 ==
    if test ${num1} -eq ${num2} -a  ${a} -eq ${b}

    
字符串运算符： 
    =          检测两个字符串是否相等
    !=
    -z         检测字符串长度是否为0，为0返回 true。
    -n         检测字符串长度是否不为 0，不为 0 返回 true。
    $          检测字符串是否为空，不为空返回 true。                          	[ $a ] 返回 true。
    
文件测试运算符:
	if [ -e ${filePath} ]    判断文件是否存在
    if [ -r ${filePath} ]    判断文件是否可读r   写w   执行x    
    if [ -f ${filePath} ]    判断是否是文件
    if [ -d ${filePath} ]    判断是否是文件夹

        
```

## 7、echo输出

```java
echo -e "OK! \c" # -e 开启转义 \c 不换行
echo "It is a test"
    
echo "\"It is a test\""    --》  "It is a test"
```

```java
//显示结果定向至文件，文件不存在会进行创建
echo "It is a test" > myfile
```

```java
//显示时间
echo `date`;      -->   Wed Jun 10 17:06:21 2020
```



## 8、printf打印命令

```java
printf "%-10s %-8s %-4s\n" 姓名 性别 体重kg  
printf "%-10s %-8s %-4.2f\n" 郭靖 男 66.1234 
printf "%-10s %-8s %-4.2f\n" 杨过 男 48.6543 
printf "%-10s %-8s %-4.2f\n" 郭芙 女 47.9876 
```

![image-20200610171039177](C:\Users\86152\AppData\Roaming\Typora\typora-user-images\image-20200610171039177.png)

```xml
%s %c %d %f都是格式替代符

%-10s 指一个宽度为10个字符（-表示左对齐，没有则表示右对齐），任何字符都会被显示在10个字符宽的字符内，如果不足则自动以空格填充，超过也会将内容全部显示出来。

%-4.2f 指格式化为小数，其中.2指保留2位小数。
```



## 9、shell的test命令

Shell中的 test 命令用于检查某个条件是否成立，它可以进行数值、字符和文件三个方面的测试。

数值测试：

```java
-eq  /  -ne  /  -gt  /  -ge  /  -lt  /  -le
    
num1=100
num2=100
if test $[num1] -eq $[num2]           ===========     if [ $[num1] -eq $[num2] ]
then
    echo '两个数相等！'
else
    echo '两个数不相等！'
fi
```



字符串测试

```java
=  /  !=  /  -z 字符串:字符串的长度为零则为真   / -n 字符串的长度不为零则为真
    
    
num1="ru1noob"
num2="runoob"
if test $num1 = $num2
then
    echo '两个字符串相等!'
else
    echo '两个字符串不相等!'
fi
```



文件测试

| 参数      | 说明                                 |
| :-------- | :----------------------------------- |
| -e 文件名 | 如果文件存在则为真                   |
| -r 文件名 | 如果文件存在且可读则为真             |
| -w 文件名 | 如果文件存在且可写则为真             |
| -x 文件名 | 如果文件存在且可执行则为真           |
| -s 文件名 | 如果文件存在且至少有一个字符则为真   |
| -d 文件名 | 如果文件存在且为目录则为真           |
| -f 文件名 | 如果文件存在且为普通文件则为真       |
| -c 文件名 | 如果文件存在且为字符型特殊文件则为真 |
| -b 文件名 | 如果文件存在且为块特殊文件则为真     |

```java
cd /bin
if test -e ./bash
then
    echo '文件已存在!'
else
    echo '文件不存在!'
fi
```



## 10、shell的流程控制

```java
//if循环
a=10
b=20
if [ $a == $b ]
then
   echo "a 等于 b"
elif [ $a -gt $b ]
then
   echo "a 大于 b"
elif [ $a -lt $b ]
then
   echo "a 小于 b"
else
   echo "没有符合的条件"
fi
```

```java
//for循环
for value in 1 2 3 4 5                                     ${@}多用于此场景
do
    echo "The value is: ${value}"
done

    
    
for i in ${@}
do
  echo "喜欢${i}"
done

echo "======================="
for i in "${*}"
do 
 echo 喜欢${i}
done
---------------->>>>>喜欢2 3 4 2 21 f
    
    
    
==============================================================    
sum=0
for ((i=1;i<=100;i++))
do
    sum=$[${sum}+${i}]
done
echo "sum = ${sum}"

```

```java
//while循环
#!/bin/bash
value=1
while(test $value -le 5 )
do
    echo "${value}"
    let value++          //let命令
done
    
    
    
let a=5+4
let b=9-3 
echo $a $b
```

```java
//case 
echo "请输入一个数字（1-7）："
read week
case ${week} in
    "1")
        echo "星期一"
        ;;
    "2")
        echo "星期二";;
     *)
        echo "其他！！！";;
esac
```



## 11、read读取控制台输入的值

```java
echo '按下 <CTRL-D> 退出'
echo -n '输入第一个数字: '
read num1
echo -n '输入第二个数字: '
read num2
sum=`expr ${num1} + ${num2}`
echo "sum = ${sum}"
 
==========================================================================
//  -t  限制时间    -p  提示信息    name 接收的字段名称    
#!/bin/bash
read -t 7 -p "请在7秒内输入一个字符串作为姓名：" name
echo "输入的姓名为：${name}"    
```



## 12、shell中的函数

```java
echo "======shell函数编写======"
function printDemo(){
	echo "printDemo被调用了。。。";
}

function sumFunc(){
	#echo ${num1}           函数内部的变量不要和外部冲突了
	echo "请输入第一个数字："
	read number1;
	echo "请输入第二个数字："
	read number2;
	
	echo "number1=${number1}"
	echo "number2=${number2}"
	res=$[ number1 + number2 ];
	echo "函数内部输出结果："${res};
	printDemo
	return ${res};
}
echo "函数调用前。。";
sumFunc;
echo "输入的两个数字之和为 ${?} "
echo "函数调用后。。";
```

要先定义，后调用。即定义写在上面。

函数的返回值需要用    **$?**   获取。

## 13、shell输入到外部

```java
//把输出的这句话打印到introduce.txt文档中
$ echo “我是Jack” > introduce.txt
    
//将结果追加到文件中    
$ echo “我是Jack” > introduce.txt
```



输入到内部

```java
将查到的结果保存到b.txt文件中
wc -l < introduce.txt > b.txt  替换
wc -l < introduce.txt >> b.txt 追加
```







==**再学习**==

> export引出全局变量    不要在shell文件里写，直接在控制台上写   test=2173271   export test
>
> ${n}    输入的参数
>
> ${*}    获取所有的参数，把参数看成了一个整体
>
> ${@}    获取的所有参数，一个一个进行展示
>
> ${?}    判断上一个脚本是否正常执行    0表示正常； 其他不正常



```bash
[ condition ]
-eq   ==
-ne   !=
-gt   >
-lt   <
-ge   >=
-le   <=

======文件======
-r    是否可读
-w	  是否可写
-x    是否可执行

-e    是否存在
-f    是否是文件
-d    是否是目录

```



函数：

```bash
#  获取一个全路径文件的文件名称
basename /temp/shelllearning/mult.sh                ---》》》  mult.sh
basename /temp/shelllearning/mult.sh  .sh           ---》》》  mult
#  获取前面的文件路径
dirname /temp/shelllearning/mult.sh                 ---》》》/temp/shelllearning

```

自定义函数：

```bash
#!/bin/bash
function doSum(){
 sum1=$[${1}+${2}]
 echo ${sum1}
}

read -p "请输入第一个数字：" num1
read -p "请输入第二个数字：" num2

doSum ${num1} ${num2}
```



Shell工具 

1、cut

```bash
# cut是切文件的  ， -f指定第几列 ， -d指定按什么格式切，默认是制表符Tab
cut cutdemo.txt -f 1 -d " "
cut cutdemo.txt -f 1 -d " " | grep hena
eg:  要想切出${PATH}的第一个冒号:后的所有内容
/usr/local/tools/jdk/jdk1.7.0_71/bin:/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/root/bin
echo ${PATH} | cut -d ":" -f 2- 即可

从ifconfg中切割出我们想要的ip地址：多次切割
ifconfig eth0 | grep "inet addr" | cut -d : -f 2 | cut -d " " -f 1 
```

2、sed流编辑器

```
可以将文件中某一行删除或者插入，放入缓冲区中，文件未改变
```

3、awk编辑器，也是切割文件，相对较方便

4、sort排序

```
sort -t : -nrk 2 sort.sh     #  把文件以：分割，按第二列以数字倒序排列
```





常用命令：

| whatis ls(xxxxxx)            |                          |
| ---------------------------- | ------------------------ |
| xxxxx  \| less               | 一点一点显示             |
| head   tail                  |                          |
| find / name  *.log           |                          |
| date                         | 当前时间                 |
| cat a.txt b.txt >  merge.txt | 将a和b合并到merge.txt中  |
| cal                          | 日历                     |
| top                          | 相当于任务管理器         |
| rm -r                        | 删除文件夹               |
| cd -                         | 回到上一次的目录（返回） |

