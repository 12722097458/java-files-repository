<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="${contextPath }/css/bootstrap.min.css" type="text/css" />
<script src="${contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="${contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script src="${contextPath }/js/jquery.validate.min.js" type="text/javascript"></script>

<!--国际化-->
<script type="text/javascript" src="${contextPath }/js/messages_zh.js" ></script>

<script type="text/javascript">
	
	$(function(){
		$("#img").click(function(){
			$(this).attr("src","${contextPath}/user.do?op=doCheckcode&temp="+Math.random());
		})
		
		$("#btnRegister").click(function(){
			
			//手动提交
			var isOk = $("#registForm").valid();
			if (isOk == false) {
				return;
			}
			//可以注册
			var temp = $("#registForm").serialize();
			$.post("${contextPath}/user.do?op=doRegisterAjax",temp,function(data){
				//-2验证码  -1密码   1成功
				var error = "";
				if (data.result == -2) {
					error = "对不起，输入的验证码错误，请重新输入！";
				} else if (data.result == -1) {
					error = "对不起，注册发生错误，请重新注册！";
				} else if (data.result == 1) {
					alert("注册成功，即将进入登录页面！");
					location.href = "${contextPath}/user.do?op=toLogin";
				}
				$("#error").html(error);
				$("#img").attr("src","${contextPath}/user.do?op=doCheckcode&temp="+Math.random());
				
			},"json");
			
		});
	})


	$.validator.addMethod("checkUsername",//自定义校验规则的名称
	function(value, element, params) {//自定义校验规则的实现
		//value)表单元素值
		//element)校验的元素对象
		//params)校验规则输入的参数
		var flag = true;
		//发送一个Ajax，到服务器进行验证用户存在
		$.ajax({
			"async" : false,//同步操作
			"url" : "${contextPath}/user.do?op=checkUsernameAjax",
			"type" : "POST",
			"data" : {
				"username" : value   // value为当前控件的值
			},//提交的请求数据
			"dataType" : "json",//返回的响应数据格式
			"success" : function(data) {
				flag = data.result;
			}
		});
		//需要返回值 false----该校验器校验失败    true---校验通过
		return !flag;
	})

	$(function(){
		$("#registForm").validate({
			rules:{
				username:{
					required:true,
					checkUsername:true    //必须填true
				},
				password:{
					required:true,
					minlength:6,
					maxlength:12
				},
				repassword:{
					required:true,
					equalTo:"#password"
				},
				email:{
					required:true,
					email:true
				},
				name:{
					required:true
				},
				sex:{
					required:true
				},
				telephone:{
					required:true,
					minlength:6,
					maxlength:11
				},
				birthday:{
					required:true
				},
				vcode:{
					required:true
				}
			},
			messages:{
				username:{
					required:"用户名不能为空",
					checkUsername:"用户名已存在，不能再次注册！"
				},
				password:{
					required:"密码不能为空",
					minlength:"密码应不短于6位",
					maxlength:"密码应不长于12位"
				},
				repassword:{
					required:"确认密码不能为空",
					equalTo:"两次密码输入不一致"
				},
				email:{
					required:"邮箱不能为空",
					email:"请输入有效的邮箱地址"
				},
				name:{
					required:"姓名不能为空",
				},
				sex:{
					required:"必须选择性别"
				},
				telephone:{
					required:"电话不能为空",
					minlength:"电话应不短于6位",
					maxlength:"电话应不长于11位"
				},
				birthday:{
					required:"生日不能为空"
				},
				vcode:{
					required:"验证码不能为空"
				}
			},
			errorElement: "div",//生成错误消息所放置的元素类型
			errorPlacement : function(error, element) { //指定错误信息位置
				if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox

					error.appendTo(element.parent().parent()); //将错误信息添加当前元素的父结点后面
				} else {
					//error.insertAfter(element);
					error.appendTo(element.parent());
				}
			}

		});
	});
	
</script>



<style>
div.error{
	color:red;
}

body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}
</style>


</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/WEB-INF/jsp/root/header.jsp"></jsp:include>

	<div class="container"
		style="width: 100%; background: url('image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER
				<h5 id="error" style="color:red"></h5>
				<form class="form-horizontal" style="margin-top: 5px;" method="post" id="registForm" onsubmit="return false;" >
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username" name="username"
								placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password" name="password" 
								placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<label for="repassword" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="repassword" name="repassword"
								placeholder="请输入确认密码">
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="email" name="email"
								placeholder="Email">
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="name" name="name" 
								placeholder="请输入姓名">
						</div>
					</div>
					<div class="form-group opt">
						<label for="sex" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio" name="sex" id="male" value="男" checked>男</label> 
							<label class="radio-inline"> <input type="radio" name="sex" id="female" value="女">女</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="telephone" class="col-sm-2 control-label">联系方式</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="telephone" name="telephone"
							placeholder="请输入联系方式">
						</div>
					</div>
					
					<div class="form-group">
						<label for="birthday" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" id="birthday" name="birthday" > 
						</div>
					</div>

					<div class="form-group">
						<label for="vcode" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="vcode" name="vcode" 
							placeholder="请输入验证码">

						</div>
						<div class="col-sm-2">
							<img src="${contextPath }/user.do?op=doCheckcode" id="img" style="cursor: pointer" title="看不清，换一张！" />
						</div>

					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="button" width="100" value="注册" id="btnRegister"
								style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/WEB-INF/jsp/root/footer.jsp"></jsp:include>

</body>
</html>




