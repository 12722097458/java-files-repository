<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品分类列表</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
	width: 100%;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
</style>
</head>

<body>


	<!-- 引入header.jsp -->
	<jsp:include page="/WEB-INF/jsp/root/header.jsp"></jsp:include>


	<div class="row" style="width: 1210px; margin: 0 auto;">
		<div class="col-md-12">
			<ol class="breadcrumb">
				<li><a href="${contextPath}/product.do?op=toIndex">首页</a></li>
				<li>${requestScope.category.cname}</li>

			</ol>
		</div>



		<c:forEach var="product" items="${requestScope.pageUtils.data}">
			<div class="col-md-2">
				<a href="${contextPath}/product.do?op=toProduct_info&pid=${product.pid}&cid=${product.cid}">
					<img src="${contextPath}/${product.pimage}" width="170" height="170" style="display: inline-block;">
				</a>
				<p style="height: 40px;">
					<a href="${contextPath}/product.do?op=toProduct_info&pid=${product.pid}&cid=${product.cid}" style='color: green'>${product.pname}</a>
				</p>
				<p>
					<font color="#FF0000">商城价：&yen;${product.market_price}</font>
				</p>
			</div>
		</c:forEach>





	</div>

	<!--分页 -->
	<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">

			<c:choose>
				<c:when test="${requestScope.pageUtils.pageNo>1}">
					<li>
						<a href="${contextPath}/product.do?op=toProduct_list&cid=${requestScope.category.cid}&pageNo=${requestScope.pageUtils.pageNo-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="disabled">
						<span aria-hidden="true">&laquo;</span>
					</li>
				</c:otherwise>
			</c:choose>



			<c:forEach var="i" begin="1" end="${requestScope.pageUtils.totalPages}">
				<li ${requestScope.pageUtils.pageNo==i?"class='active'":""}>
					<a href="${contextPath}/product.do?op=toProduct_list&pageNo=${i}&cid=${requestScope.category.cid}">${i}</a>
				</li>
			</c:forEach>


			<c:choose>
				<c:when test="${requestScope.pageUtils.pageNo < requestScope.pageUtils.totalPages}">
					<li>
						<a href="${contextPath}/product.do?op=toProduct_list&cid=${requestScope.category.cid}&pageNo=${requestScope.pageUtils.pageNo+1}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="disabled">
						<span aria-hidden="true">&raquo;</span>
					</li>
				</c:otherwise>
			</c:choose>

		</ul>
	</div>
	<!-- 分页结束 -->

	<!--商品浏览记录-->
	<div style="width: 1210px; margin: 0 auto; padding: 0 9px; border: 1px solid #ddd; border-top: 2px solid #999; height: 246px;">

		<h4 style="width: 50%; float: left; font: 14px/30px 微软雅黑">浏览记录</h4>
		<div style="width: 50%; float: right; text-align: right;">
			<a href="">more</a>
		</div>
		<div style="clear: both;"></div>

		<div style="overflow: hidden;">

			<ul style="list-style: none;">
				<c:forEach var="product" items="${requestScope.historyProducts}">
					<li style="width: 150px; height: 216; float: left; margin: 0 8px 0 0; padding: 0 18px 15px; text-align: center;">
						<img src="${contextPath}/${product.pimage}" width="130px" height="130px" />
						<p>pid:${product.pid}</p>
					</li>
				</c:forEach>

			</ul>

		</div>
	</div>


	<!-- 引入footer.jsp -->
	<jsp:include page="/WEB-INF/jsp/root/footer.jsp"></jsp:include>

</body>

</html>