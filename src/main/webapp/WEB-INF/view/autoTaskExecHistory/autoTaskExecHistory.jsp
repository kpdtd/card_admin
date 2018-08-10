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
								<a href="javascript:;">任务执行结果监控</a>
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
						<!--  灰色列表外面的表格框，每个jsp应保持一致 -->
						<div class="portlet box light-grey" id="portlet">
						    <!-- 重要：用于对齐：下面这个dataTables_wrapper form-inline可以用于对齐按钮和输入框  -->
							<div class="dataTables_wrapper form-inline">
								<!--  蓝色说明区域及 搜索框区域 -->
								<div class="alert alert-block alert-info fade in" >
									<select id="autoTaskId" class="m-wrap medium">
										<option value="">任务名称</option>
										<c:forEach var="autoTask" items="${autoTaskList}">
											<option value="${autoTask.id}">${autoTask.taskName}</option>
										</c:forEach>
									</select>
									<select id="returnResult" class="small m-wrap">
										<option value="">执行结果</option>
										<option value="1">成功</option>
										<option value="2">失败</option>
									</select>
									<button onclick="initPage();" class="btn black">
										<i class="icon-search"></i> 查询
									</button>
								</div>
							</div>
							<div class="portlet-body">
								<div id="sample_2_wrapper" class="dataTables_wrapper form-inline" role="grid">
									<div class="row-fluid">
										<table style="table-layout: fixed;"
											class="table table-striped table-bordered table-hover table-full-width dataTable" id="portlet_Tables"
											aria-describedby="sample_2_info">
											<thead>
												<tr>
													<th>任务名称</th>
													<th>任务项总数</th>
													<th>成功条目数</th>
													<th>执行结果</th>
													<th>开始时间</th>
													<th>耗时</th>
													<!-- <th>创建时间</th> -->
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

		});
		/*  加入这个select2，可以在选项中出现搜索框 */
		/* $('#autoTaskId').select2({
			placeholder : "选择任务名称",
			allowClear : true
		}); */
		function initPage() {
			var oTable = $('#portlet_Tables').dataTable({
				"bLengthChange" : false, //改变每页显示数据数量 可选的每页展示的数据数量，默认为10条
				"iDisplayLength" : 50, // 默认煤业显示条数
				"bDestroy" : true,
				"bServerSide" : true, // 使用服务器端处理
				"bSort" : false, //排序功能 默认为true
				"searching" : false, // 是否增加搜索功能
				"sDom" : "<'row-fluid'<f>r>t<'row-fluid'<'span6'i><'span6'p>>", // table布局
				"aaSorting" : [ [ 0, "desc" ] ],
				"fnServerParams" : function(aoData) {
					aoData.push({
						"name" : "autoTaskId",
						"value" : $("#autoTaskId").val()
					});
					aoData.push({
						"name" : "returnResult",
						"value" : $("#returnResult").val()
					});
				},
				"sServerMethod" : "POST",
				/* "sAjaxSource": "getListByCondition", */
				"sAjaxSource" : "getList",
				"aoColumns" : [ {
					"sClass" : "center",
					"mDataProp" : "taskName",
					"mRender" : function(obj) {
						if (obj == null) {
							return "";
						} else {
							return obj;
						}
					}
				}, {
					"sClass" : "center",
					"mDataProp" : "itemTotal",
					"mRender" : function(obj) {
						if (obj == null) {
							return "";
						} else {
							return obj;
						}
					}
				}, {
					"sClass" : "center",
					"mDataProp" : "itemSuccess",
					"mRender" : function(obj) {
						if (obj == null) {
							return "";
						} else {
							return obj;
						}
					}
				}, {
					"sClass" : "center",
					"mDataProp" : "returnResult",
					"mRender" : function(obj) {
						if (obj == 1) {
							return "成功";
						} else if (obj == 2) {
							return "失败";
						} else {
							return "";
						}
					}
				}, {
					"sClass" : "center",
					"mDataProp" : "startTime",
					"mRender" : function(obj) {
						if (obj == null) {
							return "";
						} else {
							return obj;
						}
					}
				}, {
					"sClass" : "center",
					"mDataProp" : "timeConsuming",
					"mRender" : function(obj) {
						if (obj == null) {
							return "";
						} else {
							return formatSeconds(obj);
						}
					}
				} /* , {
																		"sClass" : "center",
																		"mDataProp" : "createTime",
																		"mRender" : function(obj) {
																			if (obj == null) {
																				return "";
																			} else {
																				return obj ;
																			}
																		}
																	}  */],
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
		/*  秒换算成时分秒 */
		function formatSeconds(value) {
			var theTime = parseInt(value);// 秒 
			var theTime1 = 0;// 分 
			var theTime2 = 0;// 小时 
			// alert(theTime); 
			if (theTime > 60) {
				theTime1 = parseInt(theTime / 60);
				theTime = parseInt(theTime % 60);
				// alert(theTime1+"-"+theTime); 
				if (theTime1 > 60) {
					theTime2 = parseInt(theTime1 / 60);
					theTime1 = parseInt(theTime1 % 60);
				}
			}
			var result = "" + parseInt(theTime) + "秒";
			if (theTime1 > 0) {
				result = "" + parseInt(theTime1) + "分" + result;
			}
			if (theTime2 > 0) {
				result = "" + parseInt(theTime2) + "小时" + result;
			}
			return result;
		}
	</script>
</body>
<!-- END BODY -->
</html>