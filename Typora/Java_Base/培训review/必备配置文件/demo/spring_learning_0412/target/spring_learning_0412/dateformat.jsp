<%--
  Created by IntelliJ IDEA.
  User: 86152
  Date: 2020/4/22
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dateformat</title>
</head>
<script>
</script>
<body>
    <%--<form method="post" action="dateformat/saveUser">
        用户名：<input type="text" id="username" name="username"/><br/>
        密码：<input type="text" id="password" name="password"/><br/>
        生日：<input type="text" id="birthday" name="birthday"/><br/>
        <button  type="submit">提交</button>
    </form>--%>
    <form method="post" action="dateformat/doGetVariables">
        用户名：<input type="text" name="name"/><br/>
        <button  type="submit">提交</button>
    </form>
</body>
</html>
