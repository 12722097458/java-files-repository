

## 1、过滤器处理中文乱码

在前端页面中输入中文字符，POST方式提交给服务器，服务器端渠道的值会乱码，解决这个问题，可以在web.xml中配置过滤器来对所有请求添加编码集的设置。

```xml
<!--2、过滤器，控制编码格式-->
  <filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```

由于此过滤器对所有请求过滤掉，所以需要在 applicationContext-mvc.xml中排除掉静态资源的过滤。

```xml
<!--3、对于过滤器，排除静态资源-->
    <mvc:resources mapping="/images/**" location="/images/"/>
    <mvc:resources mapping="/js/**" location="/js/"/>
    <mvc:resources mapping="/jsp/**" location="/jsp/"/>
```

## 2、自定义类型转换器

三步走：

1、自定义转换类，实现Converter接口，对接收到的字符串类型日期进行转换

```java
package com.ityj.ssm.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateFormatConverter implements Converter<String,Date> {
    @Override
    public Date convert(String str) {
        String type = "yyyy-MM-dd";
        if (str.contains("/")){
            type = "yyyy/MM/dd";
        }
        Date parse = null;
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        try {
            parse = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (parse==null) {
            throw new RuntimeException();
        }
        return parse;
    }
}

```

2、在applicationContext-mvc.xml中配置容器，并把自定义的转换器加入

```xml
<!--5、配置日期类型转换器-->
    <bean id="conversionServiceFactoryBean" class="org.springframework.context.support.ConversionServiceFactoryBean">
        <property name="converters">
            <set>
                <bean class="com.ityj.ssm.utils.MyDateFormatConverter"/>
            </set>
        </property>
    </bean>
```

3、开启对转换器的应用

```xml
<mvc:annotation-driven conversion-service="conversionServiceFactoryBean">
    
</mvc:annotation-driven>
```

## 3、SpringMVC文件上传步骤

1、在applicationContext-mvc.xml配置文件解析器对象

```xml
<!--6、配置文件解析器对象,id必须为multipartResolver，不能自定义  maxUploadSize单位是字节-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!--上传文件的最大大小，单位为字节 -->
        <property name="maxUploadSize" value="17367648787"></property>
    </bean>
```

2、编写前端页面

- input的type设置为file
- form表单的method设为post,
- form表单的enctype设置为multipart/form-data，以二进制的形式传输数据。

```jsp
<form action="${pageContext.request.contextPath}/hello/fileupLoadTest" enctype="multipart/form-data" method="post">
    <input type="file" name="file"><br><br>
    <input type="submit" value="上传">
</form>
```

3、编写控制器controller

使用MultipartFile对象作为参数，接收前端发送过来的文件，将文件写入本地文件中，就完成了上传操作

```java
 @RequestMapping("/fileupLoadTest")
    public String fileupLoadTest(@RequestParam("file") MultipartFile file, HttpServletRequest req)
            throws IllegalStateException, IOException {

        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return "failed";
        }
        // 获取文件存储路径（绝对路径）
        String path = req.getServletContext().getRealPath("/WEB-INF/file");
        // 获取原文件名
        String fileName = file.getOriginalFilename();
        // 创建文件实例
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        // 写入文件
        file.transferTo(filePath);
        return "success";
    }
```

## 4、异常处理

1、编写自定义实体类MyException

```java
package com.ityj.ssm.domain;

public class MyException extends Exception {
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public MyException(String errMsg) {
        this.errMsg = errMsg;
    }

    public MyException(String message, String errMsg) {
        super(message);
        this.errMsg = errMsg;
    }

    public MyException() {
    }

    @Override
    public String toString() {
        return "MyException{" +
                "errMsg='" + errMsg + '\'' +
                '}';
    }
}

```

2、前端代码调用

```jsp
    <a href="${contextPath}/hello/exceptiontest">异常处理练习</a><br/>

```

3、控制器controller编写，制造异常

```java
@RequestMapping(path = "/exceptiontest")
    public ModelAndView exceptionTest() throws MyException {
        List<User> users = userService.selectAllUsers();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("success");
        try{
            int a = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("数字转换出现异常了。。。");
        }
        mv.addObject("users",users);
        return mv;
    }
```

4、在applicationContext-mvc.xml配置文件解析器对象

```xml
<!--7、配置自定义的异常处理器-->
<bean class="com.ityj.ssm.utils.MyExceptionResolver"/>
```

