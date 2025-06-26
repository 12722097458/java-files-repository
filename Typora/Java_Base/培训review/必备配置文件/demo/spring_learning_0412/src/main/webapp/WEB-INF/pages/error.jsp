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
    <title>error</title>
</head>
<body>
    <h1>${requestScope.errorMsg}</h1>
</body>
</html>
