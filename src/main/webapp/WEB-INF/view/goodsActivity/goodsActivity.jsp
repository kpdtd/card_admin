<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%-- <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> --%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<%@ include file="/common/css.jsp" %>
<%@include file="/common/js.jsp" %>
<!-- BEGIN HEAD -->
<head>
    <style type="text/css">
    </style>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-footer-fixed">
<!-- BEGIN HEADER -->
<%@ include file="/common/rightTop.jsp" %>
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <!-- BEGIN SIDEBAR -->
    <%@ include file="/common/menu.jsp" %>
    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <ul class="breadcrumb">
                        <li><i class="icon-list"></i><a href="javascript:;">活动定义</a><i class="icon-angle-right"></i>
                        </li>
                        <li><a href="javascript:;">列表</a></li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN EXAMPLE TABLE PORTLET-->
                    <div class="portlet box blue" id="portlet">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="icon-th"></i>列表
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                <div class="row-fluid">
                                    <div class="input-append">
                                        <%-- <shiro:hasPermission name="/dictionary/add">   --%>
                                        <a id="add" class="btn green"><i class="icon-plus"></i> 添加</a>
                                        <%-- </shiro:hasPermission>  --%>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <table style="table-layout: fixed;"
                                           class="table table-striped table-bordered table-hover table-full-width dataTable"
                                           id="portlet_Tables"
                                           aria-describedby="sample_2_info">
                                        <thead>
                                        <tr>
                                            <th> id</th>
                                            <th> 标题</th>
                                            <th> 执行逻辑</th>
                                            <th> 活动说明</th>
                                            <th> 活动类型</th>
                                            <th> 运营商</th>
                                            <th> 扩展JSON</th>
                                            <th> 状态</th>
                                            <th> 创建时间</th>
                                            <th> 更新时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    jQuery(document).ready(function () {
        App.init(); // initlayout and core plugins
        UIModals.init();
        TableAdvanced.init();
        initPage();
        FormComponents.init();
        $('.btn.green').click(function () {
            App.Modal.load('./add', {}, {'title': '添加活动'});
        });

    });

    function edit(id) {
        App.Modal.load("./add", {'id': id}, {"title": "修改活动"});
    }
    function initPage() {
        var oTable = $('#portlet_Tables').dataTable({
            "bLengthChange": false, //改变每页显示数据数量 可选的每页展示的数据数量，默认为10条
            "iDisplayLength": 50, // 默认煤业显示条数
            "bDestroy": true,
            "bServerSide": true, // 使用服务器端处理
            "bSort": false, //排序功能 默认为true
            "searching": false, // 是否增加搜索功能
            "sDom": "<'row-fluid'<f>r>t<'row-fluid'<'span6'i><'span6'p>>", // table布局
            "aaSorting": [[0, "desc"]],
            "fnServerParams": function (aoData) {
            },
            "sServerMethod": "POST",
            "sAjaxSource": "getList",
            "aoColumns": [{
                "sClass": "center",
                "mDataProp": "id",
                "mRender": function (obj) {
                    if (obj == null) {
                        return "";
                    } else {
                        return obj;
                    }
                }
            }, {
                "sClass": "center",
                "mDataProp": "title",
                "mRender": function (obj) {
                    if (obj == null) {
                        return "";
                    } else {
                        return obj;
                    }
                }
            }, {
                "sClass": "center",
                "mDataProp": "execLogic",
                "mRender": function (obj) {
                    if (obj == null) {
                        return "";
                    } else {
                        return obj;
                    }
                }
            }, {
                "sClass": "center",
                "mDataProp": "info",
                "mRender": function (obj) {
                    if (obj == null) {
                        return "";
                    } else {
                        return obj;
                    }
                }
            }, {
                "sClass": "center",
                "mDataProp": "type",
                "mRender": function (obj) {
                    if (obj == 1) {
                        return "购买活动";
                    } else if (obj == 2) {
                        return "非购买活动";
                    } else {
                        return obj;
                    }
                }
            }, {
                "sClass": "center",
                "mDataProp": "operators",
                "mRender": function (obj) {
                    if (obj == 1) {
                        return "移动";
                    } else if (obj == 2) {
                        return "联通";
                    }else if (obj == 3) {
                        return "电信";
                    }else {
                        return "其他";
                    }
                }
            }, {
                "sClass": "center",
                "mDataProp": "extJson",
                "mRender": function (obj) {
                    if (obj == null) {
                        return "";
                    } else {
                        return obj;
                    }
                }
            }, {
                "sClass": "center",
                "mDataProp": "state",
                "mRender": function (obj) {
                    if (obj == 0) {
                        return "停用";
                    } else if (obj == 1) {
                        return "启用";
                    } else {
                        return obj;
                    }
                }
            }, {
                "sClass": "center",
                "mDataProp": "createTime",
                "mRender": function (obj) {
                    if (obj == null) {
                        return "";
                    } else {
                        return obj;
                    }
                }
            }, {
                "sClass": "center",
                "mDataProp": "updateTime",
                "mRender": function (obj) {
                    if (obj == null) {
                        return "";
                    } else {
                        return obj;
                    }
                }
            }, {
                "sClass": "center",
                "mDataProp": "id",
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    var html = "";
                    <%--  <shiro:hasPermission name="/dictionary/editDictionary">  --%>
                    html += '<a href="javascript:;" onclick="edit(' + oData.id + ')">编辑</a>';
                    <%--  </shiro:hasPermission>  --%>
                    return $(nTd).html(html);
                }
            }],
            "sPaginationType": "bootstrap",
            "oLanguage": {
                "sProcessing": "处理中...",
                "sZeroRecords": "没有匹配的记录", // 无记录的情况下显示的表格信息
                "sInfoEmpty": "显示第 0 至 0 项记录，共 0 项", // 当没有数据时显示的页脚信息
                "sInfo": "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "尾页"
                }
            }
        });
    }
</script>
</body>
<!-- END BODY -->
</html>