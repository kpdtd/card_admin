
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
.table th, .table td {
	text-align: center;
	/* 	vertical-align: middle !important; */
}
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
								<a href="javascript:;">卡库存管理</a>
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
							<!--  这句不是搜索框蓝色背景 -->
							<div class="portlet-body">
								<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
									<!-- 加入这句才是批量页面中蓝色背景的搜索框 <div class="alert alert-block alert-info fade in"> -->
									<div class="control-group">
										<input class="m-wrap medium" size="20" id="iccid" type="text" placeholder="输入ICCID/MSISDN/IMSI查询">
										<!-- <input class="m-wrap medium" size="20" id="batchNumber" type="text" placeholder="批次号"> -->
										<select id="supplierId" class="small m-wrap">
											<option value="">供货商</option>
											<c:forEach var="supplier" items="${supplierList}">
												<option value="${supplier.id }">${supplier.company}</option>
											</c:forEach>
										</select>
										<select id="cardOwnerId" class="small m-wrap">
											<option value="">归属方</option>
											<c:forEach var="cardOwner" items="${cardOwnerList}">
												<option value="${cardOwner.id }">${cardOwner.company}</option>
											</c:forEach>
										</select>
										<select id="cardState" class="small m-wrap">
											<option value="">卡状态</option>
											<c:forEach var="cardState" items="${cardStateList}">
												<option value="${cardState.id }">${cardState.name}</option>
											</c:forEach>
										</select>
										<select id="supplierPoolId" class="small m-wrap" name="supplierPoolId">
											<option value="">流量池/套餐</option>
											<c:forEach var="supplierPool" items="${supplierPoolList}">
												<option value="${supplierPool.id}">${supplierPool.poolName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="control-group">
										<input type="text" class="m-wrap xsmall form_date" id="startTime" value="" pattern="yyyy-MM-dd"
											placeholder="检索开始时间">
										<input type="text" class="m-wrap xsmall form_date" id="endTime" value="" pattern="yyyy-MM-dd"
											placeholder="检索截止时间">
										<button onclick="initPage();" class="btn black">
											<i class="icon-search"></i> 查询
										</button>
										<shiro:hasPermission name="/card/exportResult">
											<button onclick="exportResult();" class="btn red">导出当前查询项</button>（超过5千条请勿导出）
										</shiro:hasPermission>
										<!-- <button onclick="exportSelect();" class="btn green">导出多选择项== 暂时不开放</button> -->
									</div>
									<div class="control-group">
										<!-- <strong style="color: red"> 重要说明1<br /> 重要说明2<br /></strong> -->
									</div>
									<!-- </div> -->
									<div class="row-fluid" style="overflow-x: auto; width: 950">
										<!-- 指定宽度可以出现横向滚动条  -->
										<table style="table-layout: fixed"
											class="table table-striped table-bordered table-hover table-full-width dataTable" id="portlet_Tables"
											aria-describedby="sample_2_info">
											<thead>
												<tr>
													<!-- <th nowrap="nowrap"><input type="checkbox" class="checkboxes" value="1" id="checkboxes" /></th> -->
													<th nowrap="nowrap">id</th>
													<th nowrap="nowrap">运营商</th>
													<th nowrap="nowrap">归属方</th>
													<th nowrap="nowrap">ICCID</th>
													<th nowrap="nowrap">MSISDN</th>
													<th nowrap="nowrap">IMSI</th>
													<th nowrap="nowrap">卡状态</th>
													<th nowrap="nowrap">移动套餐</th>
													<th nowrap="nowrap">激活时间</th>
													<th nowrap="nowrap">操作</th>
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
		<script>
			jQuery(document).ready(function() {
				App.init(); // initlayout and core plugins
				UIModals.init();
				TableAdvanced.init();
				initPage();
				FormComponents.init();
				$('.btn.green').click(function() {
					App.Modal.load('./add', {}, {
						'title' : '增加卡信息'
					});
				});
				$('.form_date').datetimepicker({
					language : 'zh-CN',
					format : 'yyyy-mm-dd',
					weekStart : 1,
					todayBtn : 1,
					autoclose : 1,
					todayHighlight : 1,
					startView : 2,
					minView : 2,
					forceParse : 0
				});

			});

			function edit(id) {
				App.Modal.load("./add", {
					'id' : id
				}, {
					"title" : "修改卡信息"
				});
			}

			/* 导出查询结果集  */
			function exportResult() {
				var supplierId = $("#supplierId").val();
				var cardOwnerId = $("#cardOwnerId").val();
				var cardState = $("#cardState").val();
				var supplierPoolId = $("#supplierPoolId").val();
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				var url = "./exportResult?supplierId=" + supplierId
						+ "&&cardOwnerId=" + cardOwnerId + "&&cardState="
						+ cardState + "&&supplierPoolId=" + supplierPoolId
						+ "&&startTime=" + startTime + "&&endTime=" + endTime;
				location.href = url;
			}
			/* 导出select项---（没需求，暂时不开放）  */
			function exportSelect() {
				var ids = $("input[name='checkboxes_choose']:checked")
						.serialize()
				var url = "./exportSelect?" + ids;
				location.href = url;
			}

			/*  行头的全选 */
			$("#checkboxes").change(
					function() {
						if ($("#checkboxes").is(':checked')) {
							$("input[name='checkboxes_choose']").attr(
									"checked", true);
						} else {
							$("input[name='checkboxes_choose']").attr(
									"checked", false);
						}
					})

			function initPage() {
				var oTable = $('#portlet_Tables')
						.dataTable(
								{
									"bLengthChange" : false, //改变每页显示数据数量 可选的每页展示的数据数量，默认为10条
									"scrollX" : true,
									"iDisplayLength" : 50, // 默认煤业显示条数
									"bDestroy" : true,
									"bServerSide" : true, // 使用服务器端处理
									"bSort" : false, //排序功能 默认为true
									"searching" : false, // 是否增加搜索功能
									"sDom" : "<'row-fluid'<f>r>t<'row-fluid'<'span6'i><'span6'p>>", // table布局
									"aaSorting" : [ [ 0, "desc" ] ],
									"fnServerParams" : function(aoData) {
										aoData.push({
											"name" : "iccid",
											"value" : $("#iccid").val()
										});
										aoData.push({
											"name" : "supplierId",
											"value" : $("#supplierId").val()
										});
										aoData.push({
											"name" : "cardOwnerId",
											"value" : $("#cardOwnerId").val()
										});
										aoData.push({
											"name" : "cardState",
											"value" : $("#cardState").val()
										});
										aoData.push({
											"name" : "startTime",
											"value" : $("#startTime").val()
										});
										aoData.push({
											"name" : "endTime",
											"value" : $("#endTime").val()
										});
									},
									"sServerMethod" : "POST",
									"sAjaxSource" : "getListByCondition",
									"aoColumns" : [
											/* 		暂时屏蔽，不需要选择导出									{
											 "sWidth" : "10px",
											 "sClass" : "center",
											 "mDataProp" : "id",
											 "mRender" : function(obj) {
											 return '<input type="checkbox" class="checkboxes_choose" name="checkboxes_choose" value="'+obj+'" />';
											 }
											 }, */
											{
												"sWidth" : "30px",
												"sClass" : "center",
												"mDataProp" : "id",
												"mRender" : function(obj) {
													if (obj == null) {
														return "";
													} else {
														return obj;
													}
												}
											},
											{
												"sWidth" : "85px",
												"sClass" : "center",
												"mDataProp" : "supplierName",
												"mRender" : function(obj) {
													if (obj == null) {
														return "";
													} else {
														return obj
																.toString()
																.substring(0, 6);
													}
												}
											},
											{
												"sWidth" : "60px",
												"sClass" : "center",
												"mDataProp" : "cardOwnerName",
												"mRender" : function(obj) {
													if (obj == null) {
														return "";
													} else {
														return obj;
													}
												}
											},
											{
												"sWidth" : "150px",
												"sClass" : "center",
												"mDataProp" : "iccid",
												"mRender" : function(obj) {
													if (obj == null) {
														return "";
													} else {
														return obj;
													}
												}
											},
											{
												"sWidth" : "120px",
												"sClass" : "center",
												"mDataProp" : "msisdn",
												"mRender" : function(obj) {
													if (obj == null) {
														return "";
													} else {
														return obj;
													}
												}
											},
											{
												"sWidth" : "120px",
												"sClass" : "center",
												"mDataProp" : "imsi",
												"mRender" : function(obj) {
													if (obj == null) {
														return "";
													} else {
														return obj;
													}
												}
											},
											{
												"sWidth" : "50px",
												"sClass" : "center",
												"mDataProp" : "cardState",
												"mRender" : function(obj) {
													if (obj == 1) {
														return '测试期';
													} else if (obj == 2) {
														return '沉默期';
													} else if (obj == 3) {
														return '正使用';
													} else if (obj == 4) {
														return '停机';
													} else if (obj == 5) {
														return '销户';
													} else if (obj == 6) {
														return '预约销户';
													} else if (obj == 0) {
														return '白卡';
													} else {
														return '未定义';
													}
												}
											},
											{
												"sWidth" : "100px",
												"sClass" : "center",
												"mDataProp" : "poolName",
												"mRender" : function(obj) {
													if (obj == null) {
														return "";
													} else {
														return obj;
													}
												}
											},
											{
												"sWidth" : "120px",
												"sClass" : "center",
												"mDataProp" : "activationTime",
												"mRender" : function(obj) {
													if (obj == null) {
														return "";
													} else {
														return obj.toString()
																.substring(5);
													}
												}
											},
											{
												"sWidth" : "40px",
												"sClass" : "center",
												"mDataProp" : "id",
												"fnCreatedCell" : function(nTd,
														sData, oData, iRow,
														iCol) {
													var html = "";
/* 													html += '<a href="javascript:;" onclick="edit('
															+ oData.id
															+ ')">编辑</a>'; */
													html += '<a href="javascript:;" onclick="detailPage('
															+ oData.id
															+ ')">详情</a>';
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