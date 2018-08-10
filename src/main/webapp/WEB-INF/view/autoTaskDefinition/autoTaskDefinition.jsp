<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

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
							<li>
								<i class="icon-list"></i>
								<a href="javascript:;">自动任务配置</a>
								<i class="icon-angle-right"></i>
							</li>
							<li>
								<a href="javascript:;">列表</a>
							</li>
						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box light-grey" id="portlet">
							<!--  是灰色列表外面的表格框，每个jsp应保持一致 -->
							<!-- 这里蓝色背景搜索内容及提示内容区域  -->
<!-- 							<div class="row-fluid">
								<div class="alert alert-block alert-info fade in">
								Cron表达式的格式：秒 分 时 日 月 周 年(可选)。<br>
字段名                允许的值                    允许的特殊字符<br> 
秒                     0-59                           , - * / <br>
分                     0-59                           , - * / <br>
小时                   0-23                           , - * / <br>
日                      1-31                          , - * ? / L W C<br>
月                     1-12 or JAN-DEC                , - * / <br>
周几                  1-7 or SUN-SAT                  , - * ? / L C #<br> 
年(可选字段)     	empty                             1970-2099 , - * /<br>
								</div>
							</div> -->
							<div class="row-fluid">
								<div class="alert alert-block alert-info fade in">
									说明：<br> 
									1. 时间表达式采用quarze的时间语法：<br>
									2. Cron表达式的格式：秒 分 时 日 月 周 年(可选)。<br>
									3. 例：0 * * * * ? 每1分钟触发一次 |  0 0 * * * ? 每天每1小时触发一次  | 0 0 10 * * ? 每天10点触发一次
									<br><br>
									<button class="btn black" id='add' onclick="add()">
										<i class="icon-plus"></i> 新增任务
									</button>
										<!-- <a id="add" class="btn green">
										<i class="icon-plus"></i> 添加
									</a> -->
								</div>
							</div>
							<!-- 							<div class="portlet-title">
								<div class="caption">
									<i class="icon-th"></i>列表
								</div>
							</div> -->
							<div class="portlet-body">
								<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
									<div class="row-fluid">
										<table style="table-layout: fixed;"
											class="table table-striped table-bordered table-hover table-full-width dataTable" id="portlet_Tables"
											aria-describedby="sample_2_info">
											<thead>
												<tr>
													<th>任务名称</th>
													<th>任务类定义</th>
													<th>时间表达式</th>
													<th>通知方式</th>
													<th>通知人</th>
													<th>执行状态</th>
													<th>任务状态</th>
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
			/* $('.btn.green').click(function() {
				App.Modal.load('./add', {}, {
					'title' : '添加上游'
				});
			}); */

		});
		function add() {
			App.Modal.load('./add', {}, {
				'title' : '新建定时任务'
			});
		}
		function edit(id) {
			App.Modal.load("./add", {
				'id' : id
			}, {
				"title" : "修改定时任务"
			});
		}

		function initPage() {
			var oTable = $('#portlet_Tables')
					.dataTable(
							{
								"bLengthChange" : false, //改变每页显示数据数量 可选的每页展示的数据数量，默认为10条
								"iDisplayLength" : 50, // 默认煤业显示条数
								"bDestroy" : true,
								"bServerSide" : true, // 使用服务器端处理
								"bSort" : false, //排序功能 默认为true
								"searching" : false, // 是否增加搜索功能
								"sDom" : "<'row-fluid'<f>r>t<'row-fluid'<'span6'i><'span6'p>>", // table布局
								"aaSorting" : [ [ 0, "desc" ] ],
								"fnServerParams" : function(aoData) {
								},
								"sServerMethod" : "POST",
								"sAjaxSource" : "getList",
								"aoColumns" : [
										{
											"sWidth" : "100px",
											"sClass" : "center",
											"mDataProp" : "taskName",
											"mRender" : function(obj) {
												if (obj == null) {
													return "";
												} else {
													return obj;
												}
											}
										},
										{
											"sWidth" : "100px",
											"sClass" : "center",
											"mDataProp" : "taskExecLogic",
											"mRender" : function(obj) {
												if (obj == null) {
													return "";
												} else {
													return obj;
												}
											}
										},
										{
											"sWidth" : "100px",
											"sClass" : "center",
											"mDataProp" : "taskTimeCron",
											"mRender" : function(obj) {
												if (obj == null) {
													return "";
												} else {
													return obj;
												}
											}
										},
										{
											"sClass" : "center",
											"mDataProp" : "noticeType",
											"mRender" : function(obj) {
												if (obj == 1) {
													return "邮件";
												} else if (obj == 2) {
													return "短信";
												} else {
													return "不通知";
												}
											}
										},
										{
											"sClass" : "center",
											"mDataProp" : "noticePerson",
											"mRender" : function(obj) {
												if (obj == null) {
													return "";
												} else {
													return obj.toString()
															.substring(0, 8);
												}
											}
										},
										{
											"sClass" : "center",
											"mDataProp" : "executeState",
											"mRender" : function(obj) {
												if (obj == 1) {
													return "休眠";
												} else if (obj == 2) {
													return "执行中";
												} else {
													return "任务异常";
												}
											}
										},
										{
											"sClass" : "center",
											"mDataProp" : "state",
											"fnCreatedCell" : function(nTd,
													sData, oData, iRow, iCol) {
												var html = oData.state == 0 ? "冻结"
														: "启用";
	<%-- <shiro:hasPermission name="/autoTaskDefinition/updateState"> --%>
		if (oData.state == 0
														|| oData.state == null) {
													html = " <a href='javascript:;' onclick=updateState('"
															+ oData.id
															+ "',1,'您确认要启用该任务吗?')>冻结</a>";
												} else {
													html = " <a href='javascript:;' onclick=updateState('"
															+ oData.id
															+ "',0,'您确认要冻结该任务吗?')>启用</a>";
												}
	<%-- </shiro:hasPermission> --%>
		return $(nTd).html(html);
											}
										},
										{
											"sClass" : "center",
											"mDataProp" : "createTime",
											"mRender" : function(obj) {
												if (obj == null) {
													return "";
												} else {
													return obj.toString()
															.substring(5, 16);
												}
											}
										},
										{
											"sClass" : "center",
											"mDataProp" : "id",
											"fnCreatedCell" : function(nTd,
													sData, oData, iRow, iCol) {
												var html = "";
	<%-- <shiro:hasPermission name="/autoTaskDefinition/add"> --%>
		html += '<a href="javascript:;" onclick="edit('
														+ oData.id
														+ ')">编辑</a>';
	<%-- </shiro:hasPermission> --%>
		return $(nTd).html(html);
											}
										} ],
								"sPaginationType" : "bootstrap",
								"oLanguage" : {
									"sProcessing" : "处理中...",
									"sZeroRecords" : "没有匹配的记录", // 无记录的情况下显示的表格信息
									"sInfoEmpty" : "显示第 0 至 0 项记录，共 0 项", // 当没有数据时显示的页脚信息
									"sInfo" : "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
									"oPaginate" : {
										"sFirst" : "首页",
										"sPrevious" : "上一页",
										"sNext" : "下一页",
										"sLast" : "尾页"
									}
								}
							});
		}
	</script>
</body>
<!-- END BODY -->
</html>