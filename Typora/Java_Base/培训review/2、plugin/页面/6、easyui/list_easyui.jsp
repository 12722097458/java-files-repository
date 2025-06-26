<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<html>
    <head>
        <meta http-equiv="Content-Language" content="zh-cn">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>商品列表</title>
        <link href="${contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
        <script src="${contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
        <script src="${contextPath}/js/jquery.easyui.min.js" type="text/javascript"></script>
        <link href="${contextPath}/themes/default/easyui.css" rel="stylesheet" type="text/css">
        <link href="${contextPath}/themes/icon.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="${contextPath}/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="${contextPath}/js/datagrid-detailview.js"></script>
        <script>
            function doEdit(){
                $('#doEdit').dialog({
                    closed: false
                });
            }
            function leaveEdit(){
                $('#doEdit').dialog({
                    closed: true
                });
            }
            if ($.fn.validatebox){
                $.fn.validatebox.defaults.rules.remote.message = '此商品名称已存在，请重新输入！';
            }

        </script>

    </head>

    <body>

        <%--弹出框--%>
        <div id="doEdit" style="margin-top: 10px">

            <form id="addProductForm"  method="post" enctype="multipart/form-data">
                <table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
                    <input type="hidden" name="pid">
                    <tr>
                        <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                            商品名称：
                        </td>
                        <td class="ta_01" bgColor="#ffffff">
                            <input type="text" name="pname" id="product_pname" class="easyui-textbox bg"/>
                        </td>
                        <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                            是否热门：
                        </td>
                        <td class="ta_01" bgColor="#ffffff">
                            <input type="radio" name="is_hot" value="1">是
                            <input type="radio" name="is_hot" value="0">否
                        </td>
                    </tr>
                    <tr>
                        <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                            市场价格：
                        </td>
                        <td class="ta_01" bgColor="#ffffff">
                            <input type="text" class="easyui-textbox bg" name="market_price" id="market_price"/>
                        </td>
                        <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                            商城价格：
                        </td>
                        <td class="ta_01" bgColor="#ffffff">
                            <input type="text" class="easyui-textbox bg" name="shop_price" id="shop_price"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                            商品图片：
                        </td>
                        <td class="ta_01" bgColor="#ffffff" colspan="3">
                            <input class="easyui-filebox" width="200px" buttonText="选择文件" name="upload" />
                        </td>

                    </tr>
                    <tr>
                        <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                            所属分类：
                        </td>
                        <td class="ta_01" bgColor="#ffffff" colspan="3" >
                            <select id="cid" name="cid" style="width: 150px" class="easyui-combobox">
                            </select>
                        </td>
                        <script>
                            $('#cid').combobox({
                                url:"${contextPath}/admin/doAddCategoriesAjax",
                                valueField:'cid',
                                textField:'cname'
                            });
                        </script>
                    </tr>

                    <tr>
                        <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                            商品描述：
                        </td>
                        <td class="ta_01" bgColor="#ffffff" colspan="3">
                            <textarea name="pdesc" rows="3" cols="20"></textarea>
                        </td>
                    </tr>
                </table>
            </form>

        </div>

        <script>
            var url = "${contextPath}/admin/doAddProductAjax";

            $('#doEdit').dialog({
                title: '编辑',
                width: 500,
                height: 300,
                closed: true,
                cache: false,
                modal: true,

                toolbar: [{
                    iconCls:'icon-help',
                    text:'帮助',
                    handler:function(){
                        $.messager.alert("帮助","进入官网查看详细信息！");
                    }
                }],

                buttons:[{
                    iconCls:'icon-save',
                    text:'提交',
                    handler:function(){
                        //表单提交
                        $('#addProductForm').form('submit', {
                            url:url,
                            onSubmit: function(){
                                //手动校验
                                var flag = $(this).form('validate');
                                return flag;
                            },
                            success:function(result){
                                //JSON对象转换为javaScript对象
                                var data = JSON.parse(result);
                                alert(data.result);
                                if(data.result == -1){
                                    $.messager.alert("上传失败","文件上传失败，请重新操作！","error");
                                }else if (data.result == 0) {
                                    //上传数据出错，页面提示
                                    $.messager.alert("编辑失败",'信息修改出错，请重新操作！','error');
                                } else {
                                    $.messager.alert("编辑成功","商品信息编辑成功，即将到此商品详细信息页面！","info");
                                    leaveEdit();
                                    var temp = "reload";
                                    if (url == "${contextPath}/admin/doAddProductAjax") {
                                        temp = "load";
                                    }
                                    $('#product-datagrid').datagrid(temp)
                                }
                            }
                        });
                    }
                },{
                    iconCls:'icon-no',
                    text:'关闭',
                    handler:function(){
                        leaveEdit();
                    }
                }]
            });
        </script>


        <table id="product-datagrid" class="easyui-datagrid" style="width:800px;height:540px"
               url="${contextPath}/admin/doListAjaxEasyUI"
               toolbar="#toolbar"
               title="商品列表" iconCls="icon-save"
               rownumbers="true"
               singleSelect="true"
               pagination="true"
               pageSize="5"
               pageList="[5,10,15,20,25,30]">
            <thead>
                <tr>
                    <th field="pimage" width="10%" formatter="formatImage">图片</th>
                    <th field="pname" width="25%">商品名称</th>
                    <th field="shop_price" width="10%">商品价格</th>
                    <th field="pdate" width="15%" formatter="formatDate">上架时间</th>
                    <th field="pflag" width="10%" formatter="formatState">状态</th>
                    <th field="category" width="15%" formatter="formatCategory">分类名称</th>
                </tr>
            </thead>
        </table>
        <script>
            function formatDate(value,row,index) {
                if (value) {
                    var date = new Date(value);
                    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                    return date;
                }
                return "未定义";
            }
            function formatCategory(value,row,index) {
                if (value) {
                    return value.cname;
                }
                return "未定义";
            }
            function formatImage(value,row,index) {
                return "<img src='${contextPath}/"+value+"' height='50' />"
            }
            function formatState(value,row,index) {
                var state = (value==1?"已上架":"未上架");
                return state;
            }
        </script>

        <div id="toolbar">
            <div>
                <form id="searchForm" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="pageNo" name="pageNo" value="1">
                    商品名称：<input class="easyui-textbox" type="text" name="conditionBean.pname" id="search-pname" >
                    <br/>
                    商品价格：<input class="easyui-textbox" id="beginPrice" name="beginPrice" type="text" > - <input class="easyui-textbox" id="endPrice"  type="text" name="endPrice" >
                    <br/>
                    上架时间：<input class= "easyui-datebox" id="beginDate" type="text" name="beginDate" > - <input class= "easyui-datebox" id="endDate" type="text" name="endDate" >
                    <br/>
                    商品分类：<input id="categoryId" class="easyui-textbox" name="conditionBean.cid">&nbsp;&nbsp;&nbsp;
                    <a href="#" class="easyui-linkbutton" iconCls='icon-search' onclick="doSearch()">搜索</a>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-clear" onclick="doClear()" >清空</a>
                    <br/>
                </form>
                <hr/>
                <!-- 针对某个字段的值进行后期加工显示 -->
                <script type="text/javascript">

                    $(function(){
                        //加载datagrid扩展功能
                        $("#product-datagrid").datagrid({
                            view: detailview,
                            detailFormatter:function(index,row){
                                //展开后的呈现内容形式
                                return '<div class="ddv" style="padding:5px 0"></div>';
                            },
                            onExpandRow: function(index,row){
                                var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
                                //把服务器返回的内容，作为ddv的内容去呈现
                                ddv.panel({
                                    border:false,
                                    cache:false,
                                    href:'${contextPath}/admin/toInfo?pid='+row.pid,
                                    onLoad:function(){
                                        $('#product-datagrid').datagrid('fixDetailRowHeight',index);
                                    }
                                });
                                $('#product-datagrid').datagrid('fixDetailRowHeight',index);
                            }
                        });

                    });


                    /*填充下拉框*/
                    $('#categoryId').combobox({
                        url:"${contextPath}/admin/doAddCategoriesAjax",
                        valueField:'cid',
                        textField:'cname'
                    });
                    //根据条件查找特定商品
                    function doSearch(){
                        //把表单数据转换成数组格式
                        var formData = $("#searchForm").serializeArray();

                        var conditionJSON = {}; // 存储最终JSON结果对象
                        for (var i = 0; i < formData.length; i++) { // 遍历参数
                            //console.log(formData[i]);
                            var data = formData[i];
                            conditionJSON[data.name]=data.value;
                        }

                        //console.log(res);
                        $('#product-datagrid').datagrid('load',conditionJSON);

                    }
                    function doClear() {
                        //easyui清空表单
                        $('#searchForm').form('clear');
                    }
                </script>
            </div>
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addProduct()">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="editProduct()">编辑</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="destroyProduct()">删除</a>
        </div>

        <script>
            function destroyProduct(){
                var row = $('#product-datagrid').datagrid('getSelected');    //得到选中行的数据，json格式{key1:value1,key2:value2}
                if (row){           //json对象
                    $.messager.confirm('确认','是否确定删除名称为：'+row.pname+'的商品吗?',function(r){
                        if (r){    //成立
                            $.post("${contextPath}/admin/doDeleteProductAjax","pid="+row.pid,function(data){
                                if (data.result == 1){
                                    $.messager.alert("删除成功","商品分类删除成功，即将回到商品分类列表！","info");
                                    $('#product-datagrid').datagrid('reload');	// reload the user data
                                } else {
                                    $.messager.alert("删除失败","商品分类删除失败，请重新操作！","no");
                                }
                            },'json');
                        }
                    });
                } else {
                    $.messager.alert("提示","请至少选择一个分类信息,再完成删除操作！","error");
                }
            }
            function editProduct(){
                var row = $('#product-datagrid').datagrid('getSelected');    //得到选中行的数据，json格式{key1:value1,key2:value2}
                if (row){           //json对象
                    $("#addProductForm").form('load',row);
                    doEdit();
                    url = "${contextPath}/admin/doEditAjax";
                    $('#doEdit').dialog({
                        title:"修改商品信息"
                    })
                } else {
                    $.messager.alert("提示","请至少选择一个分类信息,再完成编辑操作！","error");
                }
            }
            function addProduct(){
                //添加前，先清理表单中原有的数据
                $("#addProductForm").form('clear');
                url = "${contextPath}/admin/doAddProductAjax";
                doEdit();
                $('#doEdit').dialog({
                    title:"添加商品"
                })
            }
        </script>

    </body>
</html>

