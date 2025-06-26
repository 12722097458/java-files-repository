<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>订单支付成功</title>
    <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="${contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${contextPath }/js/jquery.validate.min.js" type="text/javascript"></script>



</head>

<body>
<div class="container-fluid">

    <!-- 引入header.jsp -->
    <jsp:include page="/WEB-INF/jsp/root/header.jsp"></jsp:include>

    <h3>订单支付出现错误！</h3>
    <!-- 引入footer.jsp -->
    <jsp:include page="/WEB-INF/jsp/root/footer.jsp"></jsp:include>
</div>
</body>

</html>