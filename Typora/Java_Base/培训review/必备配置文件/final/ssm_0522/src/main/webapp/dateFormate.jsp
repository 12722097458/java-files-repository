<%--
  Created by IntelliJ IDEA.
  User: 86152
  Date: 2020/5/22
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/jquery-1.8.3.js" type="application/javascript"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String val = "";
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        if ("uid_cookie".equals(cookie.getName())) {
            val = cookie.getValue();
            System.out.println(val);
            break;
        }
    }
%>

<script>

    $(function () {
        //进入或刷新页面，直接查看用户编号是否在cookie中，如果有存储，直接回显
        $.post("user/showUserId",{},function (data) {
            $("input[name='userid']").val(data.isId);
        },"json");

        //针对userid，如果鼠标移走进行判断，如果重复，进行提示。
        $("input[name='userid']").blur(function () {
            $.post("user/checkUserId",{"userid":$(this).val()},function (data) {
                if (data.ifExists == "0") {
                    //当前用户可用
                    $("#e_userid").css("color","green");
                } else {
                    $("#e_userid").css("color","red");
                }
                $("#e_userid").text(data.msg);
            },"json");

        });

        $("#btn").click(function () {
            var userid = $("input[name='userid']").val();
            var username = $("input[name='username']").val();
            var password = $("input[name='password']").val();
            var birthday = $("input[name='birthday']").val();
            $("#btn").submit();
            /*
            $.ajax({
                type: "POST",
                url: "user/doSaveUser",
                dataType:"json",
                data:{"userid":userid,"username":username,"password":password,"birthday":birthday},
                success: function(data){
                    if (data == 1) {
                        alert("注册成功！");
                    } else {
                        alert("注册出错！");
                    }
                }
            });
            */
            //更简单的方式：
            $.post("user/doSaveUser",{"userid":userid,"username":username,"password":password,"birthday":birthday},function (data) {
                if (data == 1) {
                    alert("注册成功！");
                } else {
                    alert("注册出错！");
                }
            },"json");
        })
    })

</script>
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
                <span id="e_userid"></span>
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
                <input type="date" name="birthday">
            </div>
            <div>
                <button id="btn" type="button">提交</button>
                <%--<input type="submit" name="btn" value="提交">--%>
            </div>
        </div>
    </form>
</body>
</html>
