<!-- 以下代码都是原来Servlet中doGet或doPost方法中的部分 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set> 

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>文件上传</title>
	</head>
	<body>
	
		<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>

		<h3>
			文件上传
		</h3>
		
		<form action="${contextPath}/fileupload3.do?op=doFileUploadOne" method="post" enctype="multipart/form-data">
			用户名：<input type="text" name="name">
			<br/>
			密码：<input type="password" name="password">
			<br/>
			文件1：<input type="file" name="file1">
			<br/>
			
			<input type="submit" value="单文件提交">
		</form>
		
		<hr/>
		
		<form action="${contextPath}/fileupload3.do?op=doFileUploadMany" method="post" enctype="multipart/form-data">
			用户名：<input type="text" name="name">
			<br/>
			密码：<input type="password" name="password">
			<br/>
			文件1：<input type="file" name="file1">
			<br/>
			文件2：<input type="file" name="file2">
			<br/>
			
			<input type="submit" value="多文件提交">
		</form>
		
	</body>
</html>