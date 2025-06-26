<%--
  Created by IntelliJ IDEA.
  User: 86152
  Date: 2020/4/12
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<script type="application/javascript">

    var person = {
        name:"尹君",
        age:19,
        sex:"男"
    };
    //输出这个对象
    console.log(person);
    //将对象转换为JSON对象
    console.log(JSON.stringify(person));

    //将string字符串转换为JSON对象
    var str = "{\"name\":\"尹君\",\"age\":19,\"sex\":\"男\"}";
    console.log(JSON.parse(str));

</script>

<body>
    <h2>欢迎来到首页！</h2>
    <a href="user/sayHello">链接</a>
    <hr/>
    <a href="jsonLearning">Json字符串接收</a>
    <a href="jsonLearningList">JsonList字符串接收</a><hr/>
    <a href="jsplearing.jsp">jsplearing.jsp</a><br/>
    <a href="dateformat.jsp">自定义类型转换器</a><br/>
    <a href="testSessionAttribute.jsp">testsessionAttribute.jsp</a><br/>
    <a href="testModelAndView.jsp">testModelAndView.jsp</a><br/>
    <a href="testJson.jsp">testJson.jsp</a><br/>
    <a href="user/testException">异常测试</a><br/>

    <hr/>

</body>
</html>
