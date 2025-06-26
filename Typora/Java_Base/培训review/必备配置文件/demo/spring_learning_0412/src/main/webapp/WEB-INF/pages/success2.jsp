<%@ page import="java.util.Date" %>
<%@ page import="com.igeek.ssm.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: 86152
  Date: 2020/4/12
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html>
<head>
    <title>success</title>
</head>
<body>
    <h1>Congratulations!</h1>
    <c:forEach var="user" items="${selectedUsers}">
        ${user.username}
        <br/>
    </c:forEach>
    <h1>--------------------------</h1>
    <div>
        \${3>4}
        ${3>4}
        <hr/>
        <h3>算数运算符</h3>
        ${3+4}<br/>
        ${3-4}<br/>
        ${3*4}<br/>
        ${3/4}<br/>
        ${3%4}<br/>
        <h3>比较运算符</h3>
        ${3==4}<br/>
        ${3!=4 && 3<=4 and 3<=2}<br/>
        ${3>=4}<br/>
    </div>
    \${empty selectedUsers} : ${empty selectedUsers}

    <hr/>
    <%
        request.setAttribute("sex","男");
        request.setAttribute("date",new Date());
        session.setAttribute("age",16);
        session.setAttribute("sex","女");
        User user = new User();
        user.setBirthday(new Date());
        request.setAttribute("user",user);
    %>
    <h1>el取值</h1>
    ${requestScope.sex}  ${sessionScope.age}  ${sex}
    <h1>el取对象值</h1>
    ${requestScope.selectedUsers}<br/>
    ${requestScope.user.birth}


    <hr/>
    msg：
    ${requestScope.msg}   <br/>
    \${sessionScope.msg}=${sessionScope.msg}

    <hr/>
    <hr/>
    <hr/>
    \${requestScope.name}=${requestScope.name}
</body>
</html>
