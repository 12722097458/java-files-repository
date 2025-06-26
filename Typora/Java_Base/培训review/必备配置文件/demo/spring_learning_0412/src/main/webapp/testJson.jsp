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
    <title>testJson.jsp</title>
</head>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js" type="application/javascript"></script>
<script>
    $(function () {

        $("#btn").click(function(){
            /*//手动提交
            var isOk = $("#userForm").valid();
            if (isOk == false) {
                return;
            }*/
            //可以注册
            var temp = $("#userForm").serialize();
            $.post("${pageContext.request.contextPath}/jsonTest",temp,function(data){
                alert(data);
                alert(data.userId);
            },"json");

        });


    });

</script>
<body>
    <h2>testJson</h2>
    <form method="post" onsubmit="return false;" id="userForm" >
        用户名：<input type="text" id="username" name="username"/><br/>
        密码：<input type="text" id="password" name="password"/><br/>
        生日：<input type="text" id="birthday" name="birthday"/><br/>
        <button id="btn" type="button">提交</button>
    </form>
</body>
</html>
