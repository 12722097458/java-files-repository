<!-- 以下代码都是原来Servlet中doGet或doPost方法中的部分 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set> 

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ajax文件上传</title>
		<script src="${contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		
		<script>
			$(document).ready(function(){
				
				
				$("#btnSubmitOne").click(function(){
					//有实际表单的情况，直接把表单数据转成表单数据对象
					var formData = new FormData($("#oneForm")[0]);
					
		            $.ajax({
						async : false,//同步
						cache : false,
		                type : "post",
		                data : formData,
		                url : '${contextPath}/fileuploadajax.do?op=doFileUploadOneAjax',   //ajax上传的路径
		                dataType : 'json',
		                contentType: false, //必须，告诉jquery不要设置Content-Type请求头
		                processData: false, //必须，告诉jquery不要处理发送的数据
		                success : function(data) {
		                    if(data.result==1){
		                    	alert('文件上传成功');
		                    }else {
		                    	//上传数据出错，页面提示
		                        alert('表格数据错误，请修改后重新上传');
		                    }
		                },
		                error : function(result) {
		                }
		            });
				});
				
				$("#btnSubmitMany").click(function(){
					//没有实际表单的情况，
					var formData = new FormData();
					
					//动态组装表单数据
					formData.append("name",$("#name").val());
					formData.append("password",$("#password").val());
					formData.append("fileOne",$('#file1')[0].files[0]);
					formData.append("fileTwo",$('#file2')[0].files[0]);
					
		            $.ajax({
						async : false,//同步
						cache : false,
		                type : "post",
		                data : formData,
		                url : '${contextPath}/fileuploadajax.do?op=doFileUploadManyAjax',   //ajax上传的路径
		                dataType : 'json',
		                contentType: false, //必须，告诉jquery不要设置Content-Type请求头
		                processData: false, //必须，告诉jquery不要处理发送的数据
		                success : function(data) {
		                    if(data.result==1){
		                    	alert('文件上传成功');
		                    }else {
		                    	//上传数据出错，页面提示
		                        alert('表格数据错误，请修改后重新上传');
		                    }
		                },
		                error : function(result) {
		                }
		            });
				});
			});
			
		</script>
		
	</head>
	<body>
	
		<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>

		<h3>
			Ajax文件上传
		</h3>
		
		<form id="oneForm" onsubmit="return false;" method="post" enctype="multipart/form-data">
			用户名：<input type="text" name="name">
			<br/>
			密码：<input type="password" name="password">
			<br/>
			文件1：<input type="file" name="file1">
			<br/>
			
			<input type="button" id="btnSubmitOne" value="单文件提交">
		</form>
		
		<hr/>
		
		<div>
			用户名：<input id="name" type="text" name="name">
			<br/>
			密码：<input id="password" type="password" name="password">
			<br/>
			文件1：<input id="file1" type="file" name="file1">
			<br/>
			文件2：<input id="file2" type="file" name="file2">
			<br/>
			
			<input type="button" id="btnSubmitMany" value="无表单文件提交">
		</div>
		
	</body>
</html>