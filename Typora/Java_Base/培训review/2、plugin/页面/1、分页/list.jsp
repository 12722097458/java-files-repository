<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script src="${contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
	function addProduct() {
		window.location.href = "${contextPath}/admin/product/add.jsp";
	}
	
	function doRemove(pid,pname){
		if(window.confirm("是否确定删除："+pname+" - 商品信息？")){
			$.post("${contextPath}/admin/product.do?op=doRemoveAjax","pid="+pid,function(data){
				//{"result":0 or 1}
				
				if(data.result==1){
					alert("删除成功!最新数据重新查找！");
					gotoPage($("#pageNo").val());
					return;
				}
				
				alert("对不起，删除失败！请重新尝试！");
			},"json");
		}
	}
	
	function clearCondition(){
		$(".condition").val("");
	}
	
	function gotoPage(pageNo){
		$("#pageNo").val(pageNo);
		doSearch();
	}
	
	function doSearch(){
		
		var formData = $("#searchForm").serialize();
		//自动查询一遍商品列表
		$.post("${contextPath}/admin/product.do?op=doSearchAjax",formData,function(pageUtils){
			//每次查询时，先把原先的结果给清除
			$("#productDataGrid tr:gt(0)").remove();
			for(var i=0;i<pageUtils.data.length;i++){
				var product=pageUtils.data[i];
				var tr="<tr onmouseover=\"this.style.backgroundColor = 'yellow';\" onmouseout=\"this.style.backgroundColor = 'white';\">";
				tr+="<td style='CURSOR: hand; HEIGHT: 22px' align='center'>"+product.pid+"</td>";
				tr+="<td style='CURSOR: hand; HEIGHT: 22px' align='center'><img width='40' height='45' src='${contextPath}/"+product.pimage+"'></td>";
				tr+="<td style='CURSOR: hand; HEIGHT: 22px' align='center'>"+(product.pname?product.pname:"-")+"</td>";
				tr+="<td style='CURSOR: hand; HEIGHT: 22px' align='center'>"+(product.shop_price?product.shop_price:"-")+"</td>";
				tr+="<td style='CURSOR: hand; HEIGHT: 22px' align='center'>"+(product.is_hot==1?"是":"否")+"</td>";
				tr+="<td style='CURSOR: hand; HEIGHT: 22px' align='center'>"+product.pdate+"</td>";
				tr+="<td style='CURSOR: hand; HEIGHT: 22px' align='center'>"+product.cname+"</td>";
				tr+="<td style='HEIGHT: 22px' align='center'>";
				tr+="	<img src='${contextPath}/images/i_edit.gif' border='0' style='CURSOR: hand'></td>";
				tr+="<td style='HEIGHT: 22px' align='center'>";
				tr+="	<img onclick=\"doRemove('"+product.pid+"','"+product.pname+"');\" src='${contextPath}/images/i_del.gif' width='16' height='16' border='0' style='CURSOR: hand'>";
				tr+="</td>";
				tr+="</tr>";
				$("#productDataGrid").append(tr);
			}
			
			//先清除原先的分页信息
			$("#pageUl li").remove();
			
			var ulHtml="";
			//填充分页信息
			if(pageUtils.pageNo==1){
				ulHtml+="<li class='disabled'><span aria-hidden='true'>&laquo;</span></li>";
			}else{
				ulHtml+="<li class='disabled'><a href='javascript:void(0);' onclick='gotoPage("+(pageUtils.pageNo-1)+");' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>";
			}
			
			for(var i=0;i<pageUtils.bar.length;i++){
				var p = pageUtils.bar[i];
				if(p==pageUtils.pageNo){
					ulHtml+="<li class='active'><a href='javascript:void(0);' onclick='gotoPage("+p+");' >"+p+"</a></li>";
				}else{
					ulHtml+="<li><a href='javascript:void(0);' onclick='gotoPage("+p+");' >"+p+"</a></li>";
				}
			}
			
			if(pageUtils.pageNo==pageUtils.totalPages){
				ulHtml+="<li class='disabled'><span aria-hidden='true'>&raquo;</span></li>";
			}else{
				ulHtml+="<li><a href='javascript:void(0);' onclick='gotoPage("+(pageUtils.pageNo+1)+");' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>";
			}
			
			$("#pageUl").append(ulHtml);
		},"json");
	}
	
	$(function(){
		//填充分类下拉列表框
		$.post("${contextPath}/product.do?op=doSearchCategoriesAjax",function(data){
			
			for(var i=0;i<data.length;i++){
				var category=data[i];
				
				$("#cid").append("<option value='"+category.cid+"'>"+category.cname+"</option>");

			}
			
		},"json");
		
		//自动查询一遍商品列表
		doSearch();
	});
</script>
</HEAD>
<body>
	<br>
	<form id="searchForm" name="searchForm" onsubmit="return false;"
		method="post">
		<input type="hidden" id="pageNo" name="pageNo" value="1">
		商品名：<input class="condition" type="text" name="pname" >
		<br/>
		商品分类：<select class="condition"  id="cid" name="cid">
					<option value="">请选择商品分类</option>
				</select>
		<br/>
		商品价格：<input class="condition"  type="text" name="beginPrice" > - <input class="condition"  type="text" name="endPrice" >
		<br/>
		上架时间：<input class="condition"  type="date" name="beginDate" > - <input class="condition"  type="date" name="endDate" >
		<br/>
		<input type="button" id="btnSearch" value="查询" onclick="gotoPage(1);">
		<input type="button" value="清空" onclick="clearCondition();">
		<button type="button" id="btnToAdd" class="button_add" onclick="addProduct()">添加</button>
	</form>
	
	<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>商品列表</strong>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="productDataGrid"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="20%">编号</td>
								<td align="center" width="10%">商品图片</td>
								<td align="center" width="20%">商品名称</td>
								<td align="center" width="10%">商品价格</td>
								<td align="center" width="5%">是否热门</td>
								<td align="center" width="15%">上架时间</td>
								<td align="center" width="10%">分类</td>
								<td width="5%" align="center">编辑</td>
								<td width="5%" align="center">删除</td>
							</tr>
							
					</td>
				</tr>

			</TBODY>
		</table>
		
		<!--分页 -->
		<div style="width: 500px; margin: 0 auto; margin-top: 50px; text-align: center;">
			<ul id="pageUl" class="pagination" style="text-align: center; margin-top: 10px;">
			
			</ul>
		</div>
		<!-- 分页结束 -->
</body>
</HTML>