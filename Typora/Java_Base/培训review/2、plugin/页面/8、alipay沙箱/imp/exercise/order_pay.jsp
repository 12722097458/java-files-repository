<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>订单支付</title>
<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script src="${contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>


</head>

<body>
	<div class="container-fluid">

		<!-- 引入header.jsp -->
		<jsp:include page="/WEB-INF/jsp/root/header.jsp"></jsp:include>

		<div class="container">
			<div class="row">
				<div style="margin: 0 auto; margin-top: 10px; width: 950px;">
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th colspan="5">订单编号：${requestScope.orders.oid }</th>
							</tr>
							<tr>
								<th>
									订单金额: <strong style="color: #ff6600;">￥${requestScope.orders.total}元</strong>
								</th>
							</tr>
                            <tr>
                                <th>
                                    <a href="${contextPath}/order/doOrderPay?oid=${requestScope.orders.oid}">支付宝支付</a>
                                </th>
                            </tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>


		<!-- 引入footer.jsp -->
		<jsp:include page="/WEB-INF/jsp/root/footer.jsp"></jsp:include>
	</div>
</body>

</html>