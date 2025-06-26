<%--
  Created by IntelliJ IDEA.
  User: 86152
  Date: 2020/5/22
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>日期格式化</title>
</head>
<body>
    <form method="post" action="user/doSaveUser">
        <div>
            <div>
                用户编号：
                <input type="text" name="userid">
            </div>
            <div>
                用户名称：
                <input type="text" name="username">
            </div>
            <div>
                用户密码：
                <input type="text" name="password">
            </div>
            <div>
                用户生日：
                <input type="text" name="birthday">
            </div>
            <div>
                <button type="submit">提交</button>
                <%--<input type="submit" name="btn" value="提交">--%>
            </div>
        </div>
    </form>
</body>
</html>
