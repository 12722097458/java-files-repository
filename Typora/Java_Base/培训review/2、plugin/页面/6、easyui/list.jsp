<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<html>
    <head>
        <meta http-equiv="Content-Language" content="zh-cn">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>商品分类列表</title>
        <link rel="stylesheet" href="${contextPath}/css/bootstrap.min.css" type="text/css" />
        <link href="${contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />

        <link href="${contextPath}/themes/default/easyui.css" rel="stylesheet" type="text/css" />
        <link href="${contextPath}/themes/icon.css" rel="stylesheet" type="text/css" />

        <script src="${contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
        <script src="${contextPath}/js/jquery.easyui.min.js" type="text/javascript"></script>
        <script src="${contextPath}/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>


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
        <br>


        <div id="doEdit">
            <form id="addCategoryForm" method="post" style="margin-top: 30px">
                <table>
                    <tr>
                        <td width="34%" align="center" bgColor="#f5fafe" class="ta_01">
                            分类名称：
                        </td>
                        <td class="ta_01" bgColor="#ffffff" colspan="3">
                            <input id="cname" name="cname" type="text" />
                            <input id="cid" name="cid" type="hidden" />

                            <%--对cname框进行easyui的验证--%>
                            <script type="text/javascript">
                                $("#cname").validatebox({
                                    required: true,
                                    validType:{
                                        length:[4,10],
                                        remote:["${contextPath}/admin/category/doCheckCnameAjax","cname"]
                                    }
                                });
                            </script>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <script>
            var url = "${contextPath}/admin/category/doAddAjax";

            $('#doEdit').dialog({
                title: '编辑',
                width: 400,
                height: 200,
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
                        $('#addCategoryForm').form('submit', {
                            url:url,
                            onSubmit: function(){
                                //手动校验
                                var flag = $(this).form('validate');
                                return flag;
                            },
                            success:function(result){
                                //JSON对象转换为javaScript对象
                                var data = JSON.parse(result);
                                if (data.result != 1) {
                                    $.messager.alert("错误","对不起，更新商品分类时出现错误，请重新操作！");
                                    return;
                                }
                                $.messager.alert("成功","商品分类更新成功，即将返回商品分类页面！");
                                $('#category-datagrid').datagrid('reload');
                                leaveEdit();
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



        <table id="category-datagrid" class="easyui-datagrid" style="width:800px;height:300px"
               url="${contextPath}/admin/category/doListAjax"
               toolbar="#toolbar"
               title="分类管理" iconCls="icon-save"
               rownumbers="true"
               pagination="true"
               singleSelect="true"
               pageSize="5"
               pageList="[5,10,15,20,25,30]">
            <thead>
            <tr>
            <th field="cid" width="50%">编号</th>
            <th field="cname" width="50%">名称</th>
            </tr>
            </thead>
        </table>

        <div id="toolbar">
            <div>
                <label for="search-cname">名称：</label>
                <input class="easyui-validatebox" id="search-cname" type="text"  />
                <a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="doSearch()">搜索</a>
                <script type="text/javascript">
                    function doSearch(){
                        $('#category-datagrid').datagrid('load',{
                            cname: $('#search-cname').val()
                        });
                    }
                </script>
            </div>
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCatregory()">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCategory()">编辑</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destoryCategory()">删除</a>
        </div>

        <script>
            function destoryCategory(){
                var row = $('#category-datagrid').datagrid('getSelected');    //得到选中行的数据，json格式{key1:value1,key2:value2}
                if (row){           //json对象
                    $.messager.confirm('确认','是否确定删除本分类信息：'+row.cname+'?',function(r){
                        if (r){    //成立
                            $.post("${contextPath}/admin/category/doDeleteCategoryAjax","cid="+row.cid,function(data){
                                if (data.result == 1){
                                    $.messager.alert("删除成功","商品分类删除成功，即将回到商品分类列表！","info");
                                    $('#category-datagrid').datagrid('reload');	// reload the user data
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
            function editCategory(){
                var row = $('#category-datagrid').datagrid('getSelected');    //得到选中行的数据，json格式{key1:value1,key2:value2}
                if (row){           //json对象
                    $("#addCategoryForm").form('load',row);
                    doEdit();
                    url = "${contextPath}/admin/category/doUpdateCategoryAjax";
                    $('#doEdit').dialog({
                        title:"修改商品名称"
                    })
                } else {
                    $.messager.alert("提示","请至少选择一个分类信息,再完成编辑操作！","error");
                }
            }
            function addCatregory(){
                //添加前，先清理表单中原有的数据
                $("#editForm").form('clear');
                doEdit();
                $('#doEdit').dialog({
                    title:"添加商品种类"
                })
            }
        </script>


    </body>
</html>

