
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%-- <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> --%>

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
/* 
	https://www.cnblogs.com/hdwang/p/7146434.html
	datatabe横向滚动条，不好用
	#tableArea .dataTables_wrapper {
    position: relative;
    clear: both;
    zoom: 1;
    overflow-x: auto;
}

#tableArea table{
    width: 800px;
} */

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
								<a href="javascript:;">卡批量管理</a>
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
							<div class="row-fluid">
								<div class="alert alert-block alert-info fade in">
									<%-- <shiro:hasPermission name="/dictionary/add">   --%>
									<button class="btn black" id='batchStockIn'  onclick="batchStockIn()">
										<i class="icon-plus"></i> 批量入库
									</button>
									&nbsp&nbsp&nbsp&nbsp
									<button class="btn black"  id='batchCardOwner'  onclick="batchCardOwner()">
										<i class="icon-plus"></i> 批量变更归属
									</button>
									&nbsp&nbsp&nbsp&nbsp
									<button class="btn black">
										<i class="icon-plus"></i> 批量变更套餐
									</button>
									&nbsp&nbsp&nbsp&nbsp
									<button class="btn black">
										<i class="icon-plus"></i> 批量销号
									</button>
									<!-- <a href="#" class="btn blue"><i class="icon-plus"></i> 批量状态变更</a> -->
									<%-- </shiro:hasPermission>  --%>
								</div>
							</div>

							<div class="portlet-title">
								<div class="caption">
									<i class="icon-th"></i>列表
								</div>
							</div>
							<div class="portlet-body">
								<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
									<div class="row-fluid">
										<table style="table-layout: fixed;white-space: nowrap;"
											class="table table-striped table-bordered table-hover table-full-width dataTable" id="portlet_Tables"
											aria-describedby="sample_2_info">
											<thead>
												<tr>
													<th nowrap="nowrap">批次号</th>
													<th nowrap="nowrap">动作</th>
													<th nowrap="nowrap">数量</th>
													<th nowrap="nowrap">处理状态</th>
													<th nowrap="nowrap">操作人</th>
													<!-- <th nowrap="nowrap">备注</th> -->
													<th nowrap="nowrap">开始时间</th>
													<th nowrap="nowrap">结束时间</th>
													<th nowrap="nowrap">原始文件</th>
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
/* 			$('#batchStockIn').click(function() {
				App.Modal.load('./getCardStockInPage', {}, {
					'title' : '批量登记'
				});
			}); */
		});

		function batchStockIn() {
			App.Modal.load("./getCardStockInPage", {
			}, {
				"title" : "批量登记"
			});
		}

		function batchCardOwner() {
			App.Modal.load("./getBatchChangeCardOwnerPage", {
			}, {
				"title" : "批量变更卡归属人"
			});
		}

		function edit(id) {
			App.Modal.load("./getCardStockInPage", {
				'id' : id
			}, {
				"title" : "批量登记"
			});
		}

		function downloads(id) {
			var url = "./download?id=" + id;
			location.href = url;
		}

		function initPage() {
			var oTable = $('#portlet_Tables')
					.dataTable(
							{
								"bLengthChange" : false, //改变每页显示数据数量 可选的每页展示的数据数量，默认为10条
								"scrollX": true,
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
											"sWidth": "100px",
											"sClass" : "center",
											"mDataProp" : "code",
											"mRender" : function(obj) {
												if (obj == null) {
													return "";
												} else {
													return obj;
												}
											}
										},
										{
											"sWidth": "60px",
											"sClass" : "center",
											"mDataProp" : "action",
											"mRender" : function(obj) {
												if (obj == null) {
													return "";
												} else {
													return obj;
												}
											}
										},
										{
											"sWidth": "30px",
											"sClass" : "center",
											"mDataProp" : "number",
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
											"mDataProp" : "state",
											"mRender" : function(obj) {
												if (obj == null) {
													return "";
												} else {
													if (obj == 0) {
														return '正处理';
													} else if (obj == 1) {
														return '成功';
													}
												}
											}
										},
										{
											"sClass" : "center",
											"mDataProp" : "operator",
											"mRender" : function(obj) {
												if (obj == null) {
													return "";
												} else {
													return obj;
												}
											}
										},
/* 										{
											"sClass" : "center",
											"mDataProp" : "comment",
											"mRender" : function(obj) {
												if (obj == null) {
													return "";
												} else {
													return obj;
												}
											}
										}, */
										{
											"sWidth": "110px",
											"sClass" : "center",
											"mDataProp" : "startTime",
											"mRender" : function(obj) {
												if (obj == null) {
													return "";
												} else {
													return obj;
												}
											}
										},
										{
											"sWidth": "110px",
											"sClass" : "center",
											"mDataProp" : "endTime",
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
											"mDataProp" : "id",
											"fnCreatedCell" : function(nTd,
													sData, oData, iRow, iCol) {
												var html = "";
	<%--  <shiro:hasPermission name="/dictionary/editDictionary">  --%>
		html += '<a href="./download?id=' + oData.id +'">下载</a>';
	<%--  </shiro:hasPermission>  --%>
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