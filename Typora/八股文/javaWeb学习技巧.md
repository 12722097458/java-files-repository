## 1、servlet的生命周期：

**1.初始化：init()**

~~~java
//对于servlet生命周期，如果配置了loadOnStartup = 1属性，就是在容器启动时，直接会调用init()方法
//loadOnStartup默认-1，此servlet不会自动创建，首次调用时进行创建。
//只是针对当前的servlet来说的:另一个servlet没有配置的话，就不会自动创建servlet对象。
@WebServlet(value = "/MyServlet",loadOnStartup=1)
public class MyServlet extends HttpServlet {}
~~~



**2.请求处理:service()**

**3.服务终止，消亡:destory()**

## 2、servlet的全局作用域servletContext

~~~java
@Override
    public void init() throws ServletException {
        String my_email = (String) this.getServletContext().getInitParameter("my-email");
        System.out.println("servletContext全局变量 email = "+my_email);
        System.out.println("===============init   MyServlet。。。");
    }

~~~

~~~xml
<!-- 全局配置参数，因为不属于任何一个servlet，但是所有的servlet都可以通过servletContext读取这个数据 -->
    <context-param>
      <param-name>my-email</param-name>
      <param-value>1272097458@qq.com</param-value>
    </context-param>
~~~

![image-20200609113133016](C:\Users\86152\AppData\Roaming\Typora\typora-user-images\image-20200609113133016.png)



## 3、POST提交解决中文乱码问题

```java
req.setCharacterEncoding("utf-8");
从页面上获取中文不会乱码了。
resp.setContentType("text/html;charset=UTF-8");

idea中tomcat启动乱码：VM	options:   -Dfile.encoding=UTF-8  
```

