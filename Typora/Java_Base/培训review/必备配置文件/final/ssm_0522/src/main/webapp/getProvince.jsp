<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/jquery-1.8.3.js" type="application/javascript"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

    $(function () {
        //省份
        $.post("hello/getProvince", {}, function (data) {
            var prov = $("#province");
            $(data).each(function () {
                prov.append('<option value="'+this.provinceid+'">'+this.provincename+'</option>')
            })
        }, "json");


        //选择一个省份后的操作
        $("#province").change(function () {

            //删除城市除了第一行的所有
            $("#city option:gt(0)").remove();

            var provinceid2 = $("#province").val();
            //城市
            $.post("hello/getCities", {"provinceid":provinceid2}, function (data) {
                var city = $("#city");
                $(data).each(function () {
                    city.append('<option value="'+this.cityid+'">'+this.cityname+'</option>')
                })
            }, "json")
        });

    })
</script>
<html>
<head>
    <title>三级联动第一步</title>
</head>
<body>
    <form method="post" action="hello/getProvince">
        <div>
            <div>
                省份：
                <select id="province">
                    <option value="-1">--请选择省份--</option>
                </select>
                市区：
                <select id="city">
                    <option value="-1">--请选择城市--</option>
                </select>
            </div>
            <div>
                <button id="btn" type="button">提交</button>
            </div>
        </div>
    </form>
</body>
</html>
