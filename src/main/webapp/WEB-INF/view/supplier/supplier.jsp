<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<%@ include file="/common/css.jsp"%>
<%@include file="/common/js.jsp"%>
<!-- BEGIN HEAD -->
<head>
<style type="text/css">
</style>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-footer-fixed">
	<!-- BEGIN HEADER -->
	<%@ include file="/common/rightTop.jsp"%>
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<%@ include file="/common/menu.jsp"%>
		<!-- END SIDEBAR -->
		<!-- BEGIN PAGE -->
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<ul class="breadcrumb">
							<li><i class="icon-list"></i><a href="javascript:;">上游运营商</a><i class="icon-angle-right"></i></li>
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
										<%--<div class="input-append">--%>
											<div class="control-group">
												<a id="add" class="btn green"><i class="icon-plus"></i> 添加</a>
												<select id="state" class="m-wrap xsmall">
													<option value=""></option>
													<c:forEach var="state" items="${stateList}">
														<option value="${state.id }">${state.name}</option>
													</c:forEach>
												</select>
												<input class="m-wrap medium" size="10" id="company" type="text" placeholder="公司名称">
												<button onclick="initPage();" class="btn red">
													<i class="icon-search"></i> 查询
												</button>
											</div>
										<%--</div>--%>
									</div>
									<div class="row-fluid">
										<table style="table-layout: fixed;" class="table table-striped table-bordered table-hover table-full-width dataTable" id="portlet_Tables"
											aria-describedby="sample_2_info">
											<thead>
												<tr>
													<th>ID</th>
													<th>公司名称</th>
													<th>企业编码</th>
													<th>状态</th>
													<th>创建时间</th>
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
		jQuery(document).ready(function() {
			App.init(); // initlayout and core plugins
			UIModals.init();
			TableAdvanced.init();
			initPage();
			FormComponents.init();
			$('.btn.green').click(function(){
				App.Modal.load('./add',{},{'title':'添加上游'});
			});

		});
		
		function edit(id){
			App.Modal.load("./add",{'id' : id},{"title":"修改上游"});
		}

        $('#state').select2({
            placeholder: "状态",
            allowClear: true
        });

        function initPage() {
			var oTable = $('#portlet_Tables').dataTable({
				"bLengthChange" : false, //改变每页显示数据数量 可选的每页展示的数据数量，默认为10条
				"iDisplayLength" : 50, // 默认煤业显示条数
				"bDestroy" : true,
				"bServerSide" : true, // 使用服务器端处理
				"bSort": false, //排序功能 默认为true
				"searching" : false, // 是否增加搜索功能
				"sDom": "<'row-fluid'<f>r>t<'row-fluid'<'span6'i><'span6'p>>", // table布局
		        "aaSorting" : [[ 0, "desc" ]], 
				"fnServerParams" : function(aoData) {
                    aoData.push({ "name" : "state", "value" : $("#state").val()});
                    aoData.push({ "name" : "company", "value" : $("#company").val()});
				},
				"sServerMethod" : "POST",
				"sAjaxSource" : "getList",
				"aoColumns" : [{
                    "sClass" : "center",
                    "mDataProp" : "id",
                    "mRender" : function(obj){
                        if(obj == null){
                            return "";
                        }else{
                            //我这里做个简单的示范
                            return obj;
                        }
                    }
                }, {
					"sClass" : "center",
					"mDataProp" : "company",
					"mRender" : function(obj){
						if(obj == null){
							return "";
						}else{
							//我这里做个简单的示范
		                     return obj;
						}
	               }
				}, {
					"sClass" : "center",
					"mDataProp" : "ecCode",
					"mRender" : function(obj){
						if(obj == null){
							return "";
						}else{
							return obj;
						}
					}
				},{
					"sClass" : "center",
					"mDataProp" : "state",
					"mRender" : function(obj){
						if(obj == 1){
							return "启用";
						}else {
		                     return "冻结";
						}
	               }
				} ,{
					"sClass" : "center",
					"mDataProp" : "createTime",
					"mRender" : function(obj){
						if(obj == null){
							return "";
						}else{
		                     return obj;
						}
	               }
				} , {
					"sClass" : "center",
					"mDataProp" : "id",
					"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
						var html = "";
							html += '<a href="javascript:;" onclick="edit('+oData.id+')">编辑</a>';
						return $(nTd).html(html);	
					}
				} ],
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
</body>
<!-- END BODY -->
</html>