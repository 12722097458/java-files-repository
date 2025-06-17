### 1、简单的springMVC项目搭建

1.1 新建maven项目，并构建基本的sptingMVC目录结构

![image-20201204221107052](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617164402848.png)

1.2 导入pom坐标

1.3 编写web.xml文件，配置前端控制器

```xml
<!--1、配置前端控制器，读取配置文件-->
  <servlet>
    <servlet-name>dispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:application-springmvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>dispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
```

1.4 编写application_springmvc.properties配置文件（支持spring注解、视图解析器、支持springmvc(@Controller)注解）

```properties
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/mvc
http://www.springframework.org/schema/mvc/spring-mvc.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context.xsd">

    <!--1、开启注解扫描-->
    <context:component-scan base-package="com.ityj.springmvc.controller"/>

    <!--2、配置视图解析器-->
    <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    
    <mvc:annotation-driven/>
</beans>
```

1.5 编写controller

```java
package com.ityj.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping(path = "/hello")
    public String sayHello() {
        System.out.println("hello SpringMVC！");
        return "success";
    }
}
```

1.6 编写超链接，进行调用对应的controller接口

```jsp
<%--
  Created by IntelliJ IDEA.
  User: 86152
  Date: 2020/11/30
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <h2>欢迎您！</h2>
    <a href="hello">点击我</a>
    <br/>
    <a href="${pageContext.request.contextPath}/hello">点击我2</a>
</body>
</html>
```

1.7 在tomcat中配置VN options   =  -Dfile.encoding=UTF-8解决启动项目时中文乱码现象

###  2、参数绑定及注解讲解

1. @RequestMapping可以放在类上，也可以放于方法上。表示请求路径 method = RequestMethod.POST属性可以指定请求的类型。

2. 通过url上的key直接获取到对应的值:

	```java
	<a href="param/getParamByName?sid=加油">controller根据name直接获取请求参数</a>
	
	@RequestMapping(path = "/getParamByName")
	public String getParamByName(String sid) {}   // sid = 加油  没有发生乱码现象
	```

3. 通过form表单提交信息

	```java
	 <form action="param/formInformationGet" method="post">
	        用户名：<input type="text" name="username"/><br/>
	        密码：<input type="text" name="password"/><br/>
	        年龄：<input type="text" name="age"/><br/>
	        <input type="submit" value="表单提交"/>
	  </form>
	     
	  @RequestMapping(path = "/formInformationGet")
	  public String formInformationGet(String username, String password, String age) {
	     System.out.println("进入 formInformationGet。。。");
	     System.out.println("username = " + username);
	     System.out.println("password = " + password);
	     System.out.println("age = " + age);
	     return "success";
	  }   
	  
	总结：
	  当表单的请求方式为get请求是，不会出现中文乱码现象。而post请求时会乱码（需要通过springMVC配置过滤器对字符集进行设置）。
	```

4. 解决中文乱码：秩序在web.xml中配置过滤器即可

	```xml
	<!--2. 过滤器处理post请求中文乱码问题-->
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

5. @RequestParam注解测试

	```java
	public String paramTest(@RequestParam(name = "id", required = true) String sid) {}
	意思是：前端必须传过来一个key为id或者表单name=id的映射，并封装在sid中。属性required默认为true，即如果没有传入，就会进行报错。
	```

6. 对象直接获取（日期类型转换器）

	```java
	<form action="param/objectSubmitTest" method="post">
	    用户名：<input type="text" name="uid"/><br/>
	    姓名：<input type="text" name="name"/><br/>
	    生日：<input type="text" name="birthday"/><br/>
	    <input type="submit" value="User对象提交"/>
	</form>
	
	@RequestMapping(path = "/objectSubmitTest")
	    public String objectSubmitTest(User user) {
	    System.out.println("objectSubmitTest。。。");
	    System.out.println("user = " + user);
	    return "success";
	}
	结果：
	    （1）直接输入参数User user即可自动进行属性的封装。
	    （2）如果birthday输入2020-12-12，将会进行报错。spring封装日期格式默认为yyyy/MM/dd的类型。可通过自定义类型转换器进行处理。
	  
	```

7. 自定义日期类型抓换器

	```java
	// 第一步，编写转换器
	package com.ityj.springmvc.converter;
	
	import lombok.SneakyThrows;
	import org.springframework.core.convert.converter.Converter;
	
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	
	public class MyDateConverter implements Converter<String ,Date> {
	    @SneakyThrows
	    @Override
	    public Date convert(String source) {
	        if (source == null) {
	            throw new RuntimeException("参数不能为空！");
	        }
	        String pattern = "yyyy/MM/dd";
	        if (source.contains("-")) {
	            pattern = "yyyy-MM-dd";
	        }
	        DateFormat formatter = new SimpleDateFormat(pattern);
	        return formatter.parse(source);
	    }
	}
	
	// 2. 在配置文件中进行注册
	<!-- 日期类型转换器注册-->
	    <bean id="conversionServiceFactoryBean" class="org.springframework.context.support.ConversionServiceFactoryBean">
	        <property name="converters">
	            <set>
	                <bean id="myDateConverter" class="com.ityj.springmvc.converter.MyDateConverter"/>
	            </set>
	        </property>
	    </bean>
	
	  <!--<mvc:annotation-driven/>默认注册了处理器映射器、处理器适配器以及springMVC的一些注解；转换器的话需要手动添加-->
	    <mvc:annotation-driven conversion-service="conversionServiceFactoryBean"/>
	```

8. @RequestBody可以获取请求体的内容

9. @CookieValue获取cookie值

	```java
	@RequestMapping(path = "/cookieValueTest")
	public String cookieValueTest(@CookieValue(value = "JSESSIONID") String cookieValue) {
	    System.out.println("cookieValueTest。。。");
	    System.out.println("cookieValue = " + cookieValue);
	    return "success";
	}
	
	<a href="param/cookieValueTest?id=加油">@CookieValue测试</a>
	```

10. @SessionAttributes只能用于类上，可将requestScope范围内的值扩充到sessionScope中，用于数据共享

	```java
	@SessionAttributes(value = {"msg"})
	public class ParamsController {}
	可以将msg范围扩充到sessionScope
	    
	@RequestMapping(path = "/sessionAttributesTest")
	public String sessionAttributesTest(Model model) {
	    System.out.println("sessionAttributesTest。。。");
	    model.addAttribute("msg", "加油，打工人！");
	    return "success";
	}
	
	<a href="param/sessionAttributesTest">@SessionAttributes测试</a>
	```

11. ajax提交实现异步交互，并获取整体json字符串

	（1）导入了jQuery.js，编写了对应的方法，发现1.绑定的事件无法起作用。2.<form onsubmit="return false;" method="post"></form>一定要注意覆盖提交事件行为。否则请求会被覆盖。

	```jsp
	<%--
	  Created by IntelliJ IDEA.
	  User: 86152
	  Date: 2020/12/5
	  Time: 20:34
	  To change this template use File | Settings | File Templates.
	--%>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<html>
	<head>
	    <title>ajax提交</title>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.0.js"></script>
	
	    <script>
	        $(function () {
	            $("#buttoon_submit").click(function () {
	
	                /*
	                *  使用Ajax，JS也是默认使用ISO8859编码，所以在进行请求时遇到中文参数需要进行编码
	                * */
	                $.post("${pageContext.request.contextPath}/ajax/dataGet", {"uid":$("#uid").val(), "name":$("#name").val(), "birthday":$("#birthday").val()},
	                function(data){
	                    alert(data.uid);
	                    alert(data.name);
	                }, "json")
	            })
	        })
	
	    </script>
	
	</head>
	<body>
	    <%--
	        一定要取消form的原始表单提交行为，否则会将请求覆盖，无法返回值
        --%>
	    <form onsubmit="return false;" method="post">
        用户名：<input type="text" id="uid" name="uid"/><br/>
	        姓名：<input type="text" id="name" name="name"/><br/>
        生日：<input type="text" id="birthday" name="birthday"/><br/>
	        <input type="submit" id="buttoon_submit" value="ajax提交"/>
	    </form>
	</body>
	</html>
	
	```

	（2） 是前端控制器对所有的请求都进行了拦截，包括了静态资源，所以无法起作用。需要对这些目录下的静态资源进行释放。

	​			通过spring-mvc的配置文件可以实现。
	
	```xml
	    <!--4. 对静态资源不拦截-->
	    <mvc:resources mapping="/js/**" location="/js/"/>
	    <mvc:resources mapping="/css/**" location="/css/"/>
	    <mvc:resources mapping="/images/**" location="/images/"/>
	```
	
	（3）可以访问后，编写controller以及对应的json数据传输。直接交互会出现中文乱码问题，主要是ajax提交默认的编码格式不是UTF-8。用@RequestBody String body接收以及返回json字符串，前端接收处理再次出现中文乱码问题。
	
	```java
	package com.ityj.springmvc.controller;
	
	import com.alibaba.fastjson.JSON;
	import com.ityj.springmvc.entity.User;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.ResponseBody;
	
	import javax.servlet.http.HttpServletRequest;
	
	@RequestMapping(value = "/ajax")
	@Controller
	public class AjaxController {
	
	    @RequestMapping(path = "/dataGet", method = RequestMethod.POST)
	    @ResponseBody
	    public String getAjaxData(@RequestBody String body, HttpServletRequest request) {
	        String uid = request.getParameter("uid");
	        System.out.println("uid = " + uid);        // 就好看好看
	
	        System.out.println("进入getAjaxData。。。");
	        System.out.println("body = " + body);  //body = uid=%E5%B0%B1%E5%A5%BD%E7%9C%8B%E5%A5%BD%E7%9C%8B&name=%E8%83%B8%E5%A4%A7&birthday=2020-2-2
	
	        User user = new User();
	        user.setUid("S001");
	        user.setName("小环");
	        return JSON.toJSONString(user);
	    }
	}
	
	```
	
	
	
	

### 3、相应数据及结果视图

1. 请求转发以及响应重定向的几种方式：

	```java
	    /*
	    *   请求转发：
	    *       一次请求，两次响应。发生在服务器端。请求地址栏不会发生变化。可以直接访问WEB-INF下的文件。用于操作失败，回显错误信息
	    *    响应重定向：
	    *       两次请求，两次响应。请求地址栏发生了变化，不能访问WEB-INF下的文件。一般用于操作（登录）成功，页面跳转。
	    *
	    * */
	    @RequestMapping(path = "/hello", method = RequestMethod.GET)
	    public String sayHello(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        System.out.println("hello SpringMVC！");
	        // 请求转发1.解析配置文件中视图解析器，进行页面的跳转：请求转发。
	        // return "success";        //http://localhost:9090/mvc/hello  显示的还是hello没有显示success.jsp
	        // 请求转发2.不走视图解析器
	        // request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);
	        // 请求转发3.不走视图解析器
	        // return "forward:/WEB-INF/pages/success.jsp";
	
	        // 响应重定向1. 必须写项目名
	        // response.sendRedirect(request.getContextPath() + "/toSuccess");
	        // 响应重定向2. 不能写项目名
	        return "redirect:/toSuccess";     // http://localhost:9090/mvc/toSuccess   地址栏发生了变化，请求的是/hello  最后显示的是/toSuccess
	    }
	
	    @RequestMapping(path = "/sayHello", method = RequestMethod.GET)
	    public void sayHello2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        System.out.println("sayHello2 SpringMVC！");
	        // 请求转发
	        // request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);
	
	        // 响应重定向
	        response.sendRedirect(request.getContextPath() + "/toSuccess");
	    }
	```

2. ModelAndView返回跳转的页面

	```java
	@RequestMapping(path = "/testModelAndView")
	    public ModelAndView testModelAndView() {
	        System.out.println("testModelAndView ...");
	        ModelAndView mv = new ModelAndView();
	        User user = new User();
	        user.setUid("S001");
	        user.setName("Rose");
	        user.setBirthday(new Date());
	        mv.addObject("user", user);
	        // 响应重定向的话，对应的requestScope信息丢失
	        // mv.setViewName("redirect:/toSuccess");
	        // 请求转发
	        mv.setViewName("success");
	        return mv;
	    }
	```

### 4、文件上传

#### 1.  传统方式的文件上传

```java
    <form action="upload/fileUploadForme" method="post" enctype="multipart/form-data">
        传统文件上传：<input type="file" name="fileUploadFormer" /><br/>
        <input type="submit" value="文件上传"/>
    </form>
        
        
         @RequestMapping(path = "/fileUploadForme")
    public String fileUploadFormerWeb(HttpServletRequest request) throws Exception {
        System.out.println("文件上传fileUploadFormerWeb。。。");


        // 使用fileupload组件实现文件的上传。
        String uploadPath = request.getSession().getServletContext().getRealPath("/uploads/");
        // 判断要上传路径是否存在
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        // 解析request对象，获取上传文件项
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
        // 解析request
        List<FileItem> fileItems = fileUpload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            // 判断当前fileItem是否为上传文件项
            if (!fileItem.isFormField()) {
                // 是文件项
                // 获取文件名
                String sourceFileName = fileItem.getName();
                String fileName = Instant.now().toEpochMilli() + "_" + sourceFileName;
                fileItem.write(new File(uploadPath, fileName));
                // 删除临时文件
                fileItem.delete();
            }
        }

        System.out.println("文件上传成功！！！");
        return "success";
    }
```

#### 2. springMVC提供的文件上传

（1） 原理解析：配置一个文件上传的控制器，直接获取到文件，上传即可。

![image-20201206104916209](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617164410709.png)

（2）代码编写

```java
// 1. jsp编写
<form action="upload/fileUploadSpringMVC" method="post" enctype="multipart/form-data">
    springMVC文件上传：<input type="file" name="fileUpload" /><br/>
    <input type="submit" value="文件上传"/>
</form>
    
// 2. 文件上传控制器配置
<!--配置文件上传的控制器   id -> multipartResolver固定，不能修改-->
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
    
// 3. 代码编写
@RequestMapping(path = "/fileUploadSpringMVC")
    public String fileUploadSpringMVC(HttpServletRequest request, MultipartFile fileUpload) throws Exception {
        //MultipartFile fileUpload表示springMVC已经将需要上传的文件封装好了，只需要配置上传路径上传即可
        System.out.println("文件上传 fileUploadSpringMVC。。。");
        // 1. 设置上传的路径
        String uploadPath = request.getSession().getServletContext().getRealPath("/uploads/");
        // 判断要上传路径是否存在
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 2. 获取上传的名字
        String originalFilename = fileUpload.getOriginalFilename();
        String fileName = Instant.now().toEpochMilli() + "_" + originalFilename;

        // 3. 上传
        fileUpload.transferTo(new File(uploadPath, fileName));

        System.out.println("文件上传成功！");
        return "success";
    }
```

#### 3.跨服务器文件上传

（1） 上传主要用到了com.sun.jersey中的API。

（2）编码过程中主要遇到了两个问题：

* 由于文件名为中文，导致400错误：URLEncoder.encode()解决
* 由于文件服务器没有创建对应的目录，导致409错误：手动创建目录解决

（3）编码

```java
// 1. 前端
<form action="upload/fileUplodAccrossServer" method="post" enctype="multipart/form-data">
    springMVC跨服务器文件上传：<input type="file" name="fileUpload2" /><br/>
    <input type="submit" value="跨服务器文件上传"/>
</form>

// 2. 导入jersey的jar包
// 3. 新建一个springmvc_fileServer服务器，设置单一个端口，并在target/springmvc_fileServer/uploads创建uploads目录
// 4. 文件上传controller编写
    @RequestMapping(path = "/fileUplodAccrossServer")
    public String fileUplodAccrossServerSpringMVC(MultipartFile fileUpload2) throws IOException {
        //MultipartFile fileUpload表示springMVC已经将需要上传的文件封装好了，只需要配置上传路径上传即可
        System.out.println("文件上传 fileUploadSpringMVC。。。");
        // 1. 设置上传的路径
        String uploadPath = "http://localhost:8888/fileServer/uploads/";

        // 2. 获取上传的名字
        String originalFilename = fileUpload2.getOriginalFilename();
        // 解决中文名称报错问题String encodeFileName = URLEncoder.encode(filename,"UTF-8");
        String encodeName = URLEncoder.encode(originalFilename, "UTF-8");
        String fileName = Instant.now().toEpochMilli() + "_" + encodeName;

        // 3. 上传
        // 3.1 利用com.sun.jersey.api.client进行跨服务器文件上传，创建客户端
         Client client = Client.create();
        // 3.2 建立连接
        WebResource resource = client.resource(uploadPath + fileName);
        // 3.3 开始上传
        resource.put(fileUpload2.getBytes());

        System.out.println("文件上传成功！");
        return "success";
    }
```

### 5、异常处理器

（1）总述：主要是通过自定义异常处理器，注册后当异常传递到前端控制器，进行解析，返回到一个特定的友好页面

（2）编写前端代码

```jsp
<a href="exception/toError">点击产生异常</a>
```

（3）编写controller代码

```java
@RequestMapping(path = "/toError")
    public String exceptionTest() throws SysException {
        System.out.println("进入exceptionTest()。。。");
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException("exceptionTest业务出现问题。。。");
        } finally {
            // xxx
        }
        return "success";
    }
```

（4）自定义异常类

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysException extends Exception {

    private String message;
}

```

（5）编写异常处理控制器

```java
/*
*   异常解析类处理器
* */
public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        SysException sysException = null;
        if (e instanceof SysException) {
            sysException = (SysException) e;
        } else {
            sysException = new SysException("系统开小差了，请稍后访问！");
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("errMsg", sysException.getMessage());
        mv.setViewName("error");
        return mv;
    }
}

```

（6）配置异常处理的控制器

```properties
    <!--异常处理器组件-->
    <bean id="sysExceptionResolver" class="com.ityj.springmvc.exception.SysExceptionResolver"/>
```

**![image-20201206214601908](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617164418310.png)**

### 6、拦截器

![image-20201206232340672](https://gitee.com/yj1109/cloud-image/raw/master/img/20250617164425141.png)

> 拦截器是springMVC特有的组件，可以对请求进行过滤拦截处理。

（1）编写前端jsp

```jsp
<a href="interceptor/testInterceptor">springMVC拦截器</a>
```

（2）编写controller

```java
@RequestMapping(path = "/testInterceptor")
    public String testInterceptor() {
        System.out.println("testInterceptor()执行了。。。");
        return "success";
}
```

（3）编写自定义拦截器：两个用于观察执行顺序，内部一样，只是类名不同

```java
package com.ityj.springmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor1 implements HandlerInterceptor {
    // 预处理，在进入controller前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("拦截器MyInterceptor1.preHandle()执行了。。。预处理");
        //true表示放行
        return true;
        // 如果是false，表示不满足要求（未登录。。。）可以手动给页面跳转
        // request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        // return false;
    }

    // 后处理，在controller业务执行完，并且return "success"; 页面跳转之前执行
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器MyInterceptor1.postHandle()执行了。。。后处理，在controller页面跳转之前。");

    }

    // 最终处理，controller包括跳转页面全部执行结束后才开始执行
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("拦截器MyInterceptor1.afterCompletion()执行了。。。最终处理，页面已经响应完才执行。");
    }
}

```

（4）对拦截器进行配置注册

```properties
<!--7. 配置springMVC的拦截器-->
    <mvc:interceptors>
        <mvc:interceptor>
            <!--配置哪些需要拦截-->
            <mvc:mapping path="/interceptor/*"/>
            <bean class="com.ityj.springmvc.interceptor.MyInterceptor1"/>
        </mvc:interceptor>

        <mvc:interceptor>
            <!--配置哪些需要拦截-->
            <mvc:mapping path="/interceptor/*"/>
            <bean class="com.ityj.springmvc.interceptor.MyInterceptor2"/>
        </mvc:interceptor>
    </mvc:interceptors>
```

（5）结果

```java
拦截器MyInterceptor1.preHandle()执行了。。。预处理
拦截器 MyInterceptor2.preHandle()执行了。。。预处理
testInterceptor()执行了。。。
拦截器 MyInterceptor2.postHandle()执行了。。。后处理，在controller页面跳转之前。
拦截器MyInterceptor1.postHandle()执行了。。。后处理，在controller页面跳转之前。
success.jsp页面也执行了。。。
拦截器 MyInterceptor2.afterCompletion()执行了。。。最终处理，页面已经响应完才执行。
拦截器MyInterceptor1.afterCompletion()执行了。。。最终处理，页面已经响应完才执行。
```

### 7、Spring整合SpringMVC和MyBatis

> SSM整合前提是需要单个部分可以独立运行。这里是在SpringMVC项目下进行整合的

#### 1. 整合SpringMVC

1.1 对于dao层、service层的@Service等注解，需要spring能够识别。因此需要对Spring进行配置文件编写

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/mvc
http://www.springframework.org/schema/mvc/spring-mvc.xsd
http://www.springframework.org/schema/tx
http://www.springframework.org/schema/tx/spring-tx.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context.xsd">

    <!--1. spring 开启spring的注解-->
    <context:component-scan base-package="com.ityj.springmvc">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>
</beans>
```

1.2 配置好注解扫描后需要在程序启动后能够扫描到此配置文件，这里涉及到了监听器

```xml
<!--spring整合springmvc添加监听器实现spring配置文件的加载-->
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:application-spring.xml</param-value>
  </context-param>
```

1.3 这样写最简单的controller层就可以调用service层了。

#### 2. 整合Mybatis

2.1 配置数据源

```xml
<!--2. 整合mybatis-->
    <!--2.1 配置druid连接池-->
    <!--引入外部属性文件-->
    <context:property-placeholder location="classpath:jdbc.properties"/>
    <!--德鲁伊数据源连接池-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${durid.driverClassName}"/>
        <property name="url" value="${durid.url}"/>
        <property name="username" value="${durid.username}"/>
        <property name="password" value="${durid.password}"/>
    </bean>
    <!--2.2 配置SqlSessionFactory工厂-->
    <bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!-- 2.5实体类别名设置 -->
        <property name="typeAliasesPackage" value="com.ityj.springmvc.entity"/>
    </bean>

    <!--2.3 配置需要扫描mapper接口的包-->
    <bean id="mapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--指定sql映射文件/接口所在的包（自动扫描） *******-->
        <property name="basePackage" value="com.ityj.springmvc.mapper"/>
    </bean>
    <!--2.4 通过xml以及注解共同对事务进行控制-->
    <!--声明事务管理器transactionManager-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <!--开启事务注解-->
    <tx:annotation-driven transaction-manager="transactionManager"/>
    <!--
        1.可以在此文件中配置相关的切面 。。 切入点等来处理事务
        2.也可以在想要进行事务管理的service层类名或者方法名上编写@Transactional来开启事务管理。（常用）
    -->
    
```

2.2 编写相关的mapper

```java
package com.ityj.springmvc.mapper;

import com.ityj.springmvc.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {

    @Insert(value = "insert into account(name, money) values(#{name}, #{money})")
    int insertAccount(Account account);

    @Update(value = "update account set money=#{money} where id=#{id}")
    int update(Account account);

    @Select(value = "select * from account where id=#{id}")
    Account getAccountById(int id);
}

```

2.3 对于service层的转账案例。对于事务需要进行配置与声明

```xml
	<!--2.4 通过xml以及注解共同对事务进行控制-->
    <!--声明事务管理器transactionManager-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <!--开启事务注解-->
    <tx:annotation-driven transaction-manager="transactionManager"/>
```

```java
package com.ityj.springmvc.service.impl;

import com.ityj.springmvc.entity.Account;
import com.ityj.springmvc.mapper.AccountMapper;
import com.ityj.springmvc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional   // 声明事务
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public int insertAccount(Account account) {
        System.out.println("AccountServiceImpl.insertAccount()...");
        return accountMapper.insertAccount(account);
    }

    @Override
    public void transferMoney(int source, int target, double money) {
        Account sourceAccount = accountMapper.getAccountById(source);
        sourceAccount.setMoney(sourceAccount.getMoney() - money);
        Account targetAccount = accountMapper.getAccountById(target);
        targetAccount.setMoney(targetAccount.getMoney() + money);

        accountMapper.update(sourceAccount);
        int a = 1 / 0;
        accountMapper.update(targetAccount);

        System.out.println("转账成功！");
    }

}

```



### 8.项目上传服务器

> 这是个springMVC项目，需要依赖tomcat,mysql，以及jdk。所以虚拟机上需要准备好以上的条件。
>
> 注意mysql数据库创建以及对应的表创建。	

##### **（1）问题1：**

Tomcat启动报错：严重: Unable to process Jar entry [module-info.class]：

```xml
严重: Unable to process Jar entry [module-info.class] from Jar [jar:file:/D:/Maven/Repository/com/fasterxml/jackson/core/jackson-core/2.11.0/jackson-core-2.11.0.jar!/] for annotations
org.apache.tomcat.util.bcel.classfile.ClassFormatException: Invalid byte tag in constant pool: 19
```

**原因：**

原因就是spring版本高，tomcat版本过低

**解决方法：**

1.tomcat版本低，提升tomcat到对应的版本
2.删除对应的目录下的module-info.class，再次重新启动即可。



##### **（2）问题2：**

8009端口被占用

**解决方法：**

8005、8009、8080端口都是tomcat连接器监听端口，可能是服务器上次启动失败，这个端口没有释放。

执行shutdown.sh在启动服务器即可。



##### **（3）问题3：**

启动成功后在windows机上访问时记着关闭防火墙。

`http://192.168.189.128:8080/springmvc/`