ORACLE中存储过程学习

存储过程：可以理解为Java语言中的方法或函数。

工具：PL/SQL

> 注：存储过程中不区分大小写



# 一、程序结构介绍

## 1.1 打印hello word

创建测试窗口

```sql
-- Created on 2020/7/2 by 86152 
declare 
  -- Local variables here
  -- 声明变量，游标，不需要可以省略
  i integer;
begin
  -- Test statements here
  -- 执行语句
  Dbms_Output.put_line('Hello world!');
  -- 相当于java中 sout('Hello world!')
  -- [异常处理]
end;
```

## 1.2

在命令窗口或者SQL plus中执行以上的存储过程无法打印出结果，因为程序默认关闭了输出选项。

可以通过 ==set serveroutput on== 开启。    斜杠( / ) 表示结束输入。



## 1.3  变量

变量分为两大类：

​	1、普通数据类型：（char,varchar2,date,number,boolean,long）

​	2、特殊变量类型（引用型变量、记录型变量）

## 1.4 变量赋值方式：

```sql
-- 两种方式
-- 1、直接赋值  name := '张三'
-- 2、语句赋值  select '张三' into 'name'
```

1、eg：

```sql
-- Created on 2020/7/2 by 86152 
declare 
  -- Local variables here
  i integer;
  
  -- 姓名
  sname varchar2(20) := '张三';
  -- 薪水
  salary number;
  -- 地址
  address varchar2(200);
  
begin
  -- Test statements here
  
  -- 直接赋值
  salary := 2000;
  -- 语句赋值
  select '海南' into address from dual;
  
  -- 打印语句输出
  Dbms_Output.put_line('姓名：'||sname||' 薪水：'||salary||' 地址：'||address);
end;
```

2、eg:

变量的类型和长度取决于表中字段的类型和长度

sname  emp.ename%TYPE

3、记录型变量（引用类型）相当于Java中的一个对象，即数据库中的一行数据

v_emp emp%ROWTYPE;

```sql
-- Created on 2020/7/2 by 86152 
declare 
  -- Local variables here
  i integer;
  
  -- 姓名
  sname varchar2(20) := '张三';
  -- 薪水
  salary number;
  -- 地址
  address varchar2(200);
  
  -- 特殊类型：引用型变量
  t_menu_id sys_menu.menu_id%type;
  t_menu_name sys_menu.menu_name%Type;
  
  -- 记录型变量
  t_menu sys_menu%rowtype;
  
begin
  -- Test statements here
  
  -- 直接赋值
  salary := 2000;
  -- 语句赋值
  select '海南' into address from dual;
  
  -- 打印语句输出
  Dbms_Output.put_line('姓名：'||sname||',薪水：'||salary||',地址：'||address);
  
  
  --  =============
  select  a.menu_id,a.menu_name into t_menu_id,t_menu_name from sys_menu a where a.menu_id='200100100200';
  select a.* into t_menu  from sys_menu a where a.menu_id='200100100200';
  
  
  dbms_output.put_line(t_menu_id||','||t_menu_name);
  dbms_output.put_line(t_menu.menu_id||','||t_menu.menu_name);

  
end;
```





# 二、流程控制

## 2.1 条件分析if

```sql
-- Created on 2020/7/2 by 86152 
declare 
  -- Local variables here
  num integer;
begin
  -- Test statements here
  select count(1) into num from sys_menu a;
  if num>200 then
    dbms_output.put_line('表中数据为：'||num||'，大于200条。');
    elsif num>100 then
    dbms_output.put_line('表中数据为：'||num||'，大于100条且不大于200条。');
    else
    dbms_output.put_line('表中数据为：'||num||'，小于等于100条');
  end if;
  
end;	
```

## 2.2 循环loop

```sql
-- Created on 2020/7/2 by 86152 
declare 
  -- Local variables here
  i integer := 1;
begin
  -- Test statements here
  loop
    exit when i > 10;
    dbms_output.put_line(i);
    i := i+1;
  end loop;
end;
```





## 3、游标Cursor

##### 用来存储一个查询返回的多条数据（Java中的ResultSet）

```sql
-- Created on 2020/7/2 by 86152 
declare 
  -- 声明游标
  cursor c_menu(t_level sys_menu.menu_lvl%TYPE) is  
  select a.menu_id,a.menu_name from sys_menu a where a.menu_lvl=t_level;
  -- 声明变量接收游标中的值
  v_id sys_menu.menu_id%type;
  v_name sys_menu.menu_name%type;
  
begin
  -- 打开游标
  open c_menu(3);
  
  --遍历
  loop
    
    -- 获取游标中的值
    fetch c_menu into v_id,v_name;
    --判断退出循环     
    exit when c_menu%notfound;
    
    dbms_output.put_line(v_id||' - '||v_name);
    
  end loop;
  
  -- 关闭游标
  close c_menu;
  
end;
```

# 三、存储过程（procedure）：

Oracle给的建议：能在数据库中操作的，不要放入程序中。减少数据库的交互，提升效率。

## 3.1 创建一个存储过程

```sql
create or replace procedure p_hello is
-- 声明变量

begin
  dbms_output.put_line('Hello world!');
end p_hello;
```



```sql
-- ||有参
create or replace procedure p_querymenubyid(i_id in sys_menu.menu_id%TYPE) is

t_name sys_menu.menu_name%Type;

begin
       
select a.menu_name into t_name from sys_menu a where a.menu_id = i_id;

dbms_output.put_line(t_name);
  

end p_querymenubyid;
```





## 3.2 调用存储过程方法：

> 1、在测试窗口中
>
> begin
>   -- Test statements here
>   p_hello;
> end;
>
> 2、在命令窗口
>
> exec p_hello





## 3.3 创建带返回值输出的存储过程

```sql
create or replace procedure p_querymenubyid(i_id in sys_menu.menu_id%TYPE) is

t_name sys_menu.menu_name%Type;

begin
       
select a.menu_name into t_name from sys_menu a where a.menu_id = i_id;

dbms_output.put_line(t_name);
  

end p_querymenubyid;
```

## 3.4  在PL/SQL中调用

```sql
declare
-- 声明变量
i_id sys_menu.menu_id%Type := '200200100200';
o_name sys_menu.menu_name%Type;
begin
   p_inid_outname_menu(i_id,o_name);
   dbms_output.put_line(o_name);
end;
```



## 3.5 在java中调用

```java
package com.ityj.ssm.utils;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.junit.jupiter.api.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class ProduceTest {


    @Test
    public void testProduce() throws Exception {

        // 1、注册驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        // 2、获取链接
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:oracle";
        String username = "c##xiao";
        String password = "xiao";
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3、获得语句对象
        String sql = "{call p_inid_outname_menu(?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);

        // 4、设置输入参数
        callableStatement.setString(1,"100100100911");

        // 5、注册输出参数
        callableStatement.registerOutParameter(2 , OracleTypes.VARCHAR);

        // 6、执行存储过程
        boolean execute = callableStatement.execute();

        // 7、获取输出参数
        String out = callableStatement.getString(2);
        System.out.println(out);

        callableStatement.close();
        connection.close();


    }

}

```

