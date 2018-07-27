<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<style>
    .radio input[type="radio"], .checkbox input[type="checkbox"] {
        float: left;
        margin: 4px 4px 0;
        margin-left: 0px;
    }
</style>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="modal-body" data-width="860">
    <div class="portlet box blue">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>用户账户信息</div>
            <div class="tools">
                <%-- <a href="javascript:;" class="collapse"></a>
                 <a href="#portlet-config" data-toggle="modal" class="config"></a>
                 <a href="javascript:;" class="reload hidden-phone"></a>
                 <a href="javascript:;" class="remove hidden-phone"></a>--%>
            </div>
        </div>
        <div class="portlet-body">
            <div class="portlet-body">
                <div class="alert alert-block alert-error fade in">
                    <button type="button" class="close" data-dismiss="alert"></button>
                    <h4 class="alert-heading">用户账户记录</h4>
                    <table class="table table-striped table-bordered table-hover table-full-width dataTable" id="portlet_Tables_1"
                           aria-describedby="sample_2_info">
                        <thead>
                        <tr>
                            <th>类型</th>
                            <th>金额(分)</th>
                            <th>主账户变更前金额(分)</th>
                            <th>主账户变更后金额(分)</th>
                            <th>子账户变更前金额(分)</th>
                            <th>子账户变更后金额(分)</th>
                            <th>积分变更前</th>
                            <th>积分变更后</th>
                            <th>变更说明</th>
                            <th>变更时间</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <input type="hidden" value="${userId}" id="userId" />
        <button type="button" data-dismiss="modal" class="btn">关闭</button>
    </div>
</div>
<script>

    jQuery(document).ready(function() {
        initPage1();
        // initPage2();

    });

    function initPage1(){
        var oTable = $('#portlet_Tables_1').dataTable({
            "bLengthChange" : false, //改变每页显示数据数量 可选的每页展示的数据数量，默认为10条
            "iDisplayLength" : 50, // 默认煤业显示条数
            "bDestroy" : true,
            "bServerSide" : true, // 使用服务器端处理
            "bSort": false, //排序功能 默认为true
            "searching" : false, // 是否增加搜索功能
            "sDom": "<'row-fluid'<f>r>t<'row-fluid'<'span6'i><'span6'p>>", // table布局
            "aaSorting" : [[ 0, "desc" ]],
            "fnServerParams" : function(aoData) {
                aoData.push({ "name" : "userId", "value" : $("#userId").val()});
            },
            "sServerMethod" : "POST",
            "sAjaxSource" : "getUserAccountChangeList",
            "aoColumns" : [ {
                "sClass" : "center",
                "mDataProp" : "type",
                "mRender" : function(obj){
                    if(obj == 1){
                        return "主账号增加";
                    }else if(obj == 2){
                        return "主账号扣减";
                    }else if(obj == 3){
                        return "子账号增加";
                    }
                    else if(obj == 4){
                        return "子账号扣减";
                    }
                    else if(obj == 5){
                        return "积分增加";
                    }
                    else if(obj == 6){
                        return "积分扣减";
                    }
                }
            }, {
                "sClass" : "center",
                "mDataProp" : "money",
                "mRender" : function(obj){
                    if(obj == null){
                        return "";
                    }else{
                        return obj;
                    }
                }
            }, {
                "sClass" : "center",
                "mDataProp" : "paChangeBefore",
                "mRender" : function(obj){
                    if(obj == null){
                        return "";
                    }else{
                        return obj;
                    }
                }
            }, {
                "sClass" : "center",
                "mDataProp" : "paChangeAfter",
                "mRender" : function(obj){
                    if(obj == null){
                        return "";
                    }else{
                        return obj;
                    }
                }
            },  {
                "sClass" : "center",
                "mDataProp" : "saChangeBefore",
                "mRender" : function(obj){
                    if(obj == null){
                        return "";
                    }else{
                        return obj;
                    }
                }
            },  {
                "sClass" : "center",
                "mDataProp" : "saChangeAfter",
                "mRender" : function(obj){
                    if(obj == null){
                        return "";
                    }else{
                        return obj;
                    }
                }
            },  {
                "sClass" : "center",
                "mDataProp" : "creditChangeBefore",
                "mRender" : function(obj){
                    if(obj == null){
                        return "";
                    }else{
                        return obj;
                    }
                }
            },  {
                "sClass" : "center",
                "mDataProp" : "creditChangeAfter",
                "mRender" : function(obj){
                    if(obj == null){
                        return "";
                    }else{
                        return obj;
                    }
                }
            },  {
                "sClass" : "center",
                "mDataProp" : "source",
                "mRender" : function(obj){
                    if(obj == 1){
                        return "正常充值";
                    }else if(obj == 2){
                        return "赠送";
                    }else if(obj == 3){
                        return "日租扣减";
                    }else if(obj == 4){
                        return "购买流量包";
                    }else if(obj == 5){
                        return "人工";
                    }else if(obj == 6){
                        return "月功能费";
                    }else if(obj == 7){
                        return obj;
                    }
                }
            },{
                "sClass" : "center",
                "mDataProp" : "createTime",
                "mRender" : function(obj){
                    if(obj == null){
                        return "";
                    }else{
                        return obj;
                    }
                }
            }],
            "sPaginationType": "bootstrap",
            "oLanguage": {
                "sProcessing" : "处理中...",
                "sZeroRecords" : "没有匹配的记录", // 无记录的情况下显示的表格信息
                "sInfoEmpty" : "显示第 0 至 0 项记录，共 0 项", // 当没有数据时显示的页脚信息
                "sInfo" : "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
                "oPaginate": {
                    "sFirst" : "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast" : "尾页"
                }
            }
        });
    }
</script>