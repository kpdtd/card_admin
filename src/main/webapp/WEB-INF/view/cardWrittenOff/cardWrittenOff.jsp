
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
							<li><i class="icon-list"></i><a href="javascript:;">注销卡管理</a><i class="icon-angle-right"></i></li>
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
										<div class="control-group">
										<select id="supplierId" class="m-wrap medium">
											<option value=""></option>
											<c:forEach var="supplier" items="${supplierList}">
												<option value="${supplier.id }">${supplier.company}</option>
											</c:forEach>
										</select>
										<input class="m-wrap medium" size="10" id="iccid" type="text" placeholder="iccid">
										<select id="cardState" class="m-wrap xsmall">
											<option value=""></option>
											<c:forEach var="cardState" items="${cardStateList}">
												<option value="${cardState.id }">${cardState.name}</option>
											</c:forEach>
										</select>
										<input type="text" class="m-wrap xsmall form_date" id="startTime" value=""
											   pattern="yyyy-MM-dd" placeholder="注销开始时间">
										<input type="text" class="m-wrap xsmall form_date" id="endTime" value=""
											   pattern="yyyy-MM-dd" placeholder="注销结束时间">
											<button onclick="initPage();" class="btn red">
												<i class="icon-search"></i> 查询
											</button>
											<button onclick="downloadAll();" class="btn red"> 导出当前查询项</button>
										</div>
									</div>
									<div class="row-fluid">
										<table style="table-layout: fixed;" class="table table-striped table-bordered table-hover table-full-width dataTable" id="portlet_Tables"
											aria-describedby="sample_2_info">
											<thead>
												<tr>
													<th>	id</th>
													<th>	运营商</th>
													<th>	归属方</th>
													<th>	iccid</th>
													<th>	msisdn</th>
													<th>	imsi</th>
													<th>	激活时间</th>
													<th>	生命周期</th>
													<th>	运营状态</th>
													<th>	注销时间</th>
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

		$('.form_date').datetimepicker({
			language: 'zh-CN',
			format: 'yyyy-mm-dd',
			weekStart: 1,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
		});
		
		function edit(id){
			App.Modal.load("./add",{'id' : id},{"title":"修改上游"});
		}
		function detailPage(id){
			App.Modal.load("./detailPage",{'id' : id},{"title":"注销卡详情"});
		}
		function downloadAll() {
			var supplierId = $("#supplierId").val();
			var cardState = $("#cardState").val();
			var iccid = $("#iccid").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var url= "./downloadAll?supplierId="+supplierId+"&&cardState="+cardState+"&&iccid="+iccid+"&&startTime="+startTime+"&endTime="+endTime;
			location.href = url;
		}

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
					aoData.push({"name": "startTime", "value": $("#startTime").val()});
					aoData.push({"name": "endTime", "value": $("#endTime").val()});
					aoData.push({"name": "supplierId", "value": $("#supplierId").val()});
					aoData.push({"name": "cardState", "value": $("#cardState").val()});
					aoData.push({"name": "iccid", "value": $("#iccid").val()});
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
		                            return obj;
		                        }
		                    }
		                },{
		                    "sClass" : "center",
		                    "mDataProp" : "supplierId",
		                    "mRender" : function(obj){
		                        if(obj == null){
		                            return "";
		                        }else{
		                            return obj;
		                        }
		                    }
		                },{
		                    "sClass" : "center",
		                    "mDataProp" : "cardOwnerId",
		                    "mRender" : function(obj){
		                        if(obj == null){
		                            return "";
		                        }else{
		                            return obj;
		                        }
		                    }
		                },{
		                    "sClass" : "center",
		                    "mDataProp" : "iccid",
		                    "mRender" : function(obj){
		                        if(obj == null){
		                            return "";
		                        }else{
		                            return obj;
		                        }
		                    }
		                },{
		                    "sClass" : "center",
		                    "mDataProp" : "msisdn",
		                    "mRender" : function(obj){
		                        if(obj == null){
		                            return "";
		                        }else{
		                            return obj;
		                        }
		                    }
		                },{
		                    "sClass" : "center",
		                    "mDataProp" : "imsi",
		                    "mRender" : function(obj){
		                        if(obj == null){
		                            return "";
		                        }else{
		                            return obj;
		                        }
		                    }
		                },{
		                    "sClass" : "center",
		                    "mDataProp" : "activationTime",
		                    "mRender" : function(obj){
		                        if(obj == null){
		                            return "";
		                        }else{
		                            return obj;
		                        }
		                    }
		                },{
					"sClass" : "center",
					"mDataProp" : "cardState",
					"mRender" : function(obj){
						if(obj == 1){
							return '测试期';
						}else if(obj == 2){
							return '沉默期';
						}else if(obj == 3){
							return '正使用';
						}else if(obj == 4){
							return '停机';
						}else if(obj == 5){
							return '销户';
						}else if(obj == 6){
							return '预约销户';
						}else if(obj == 0){
							return '白卡';
						}else{
							return '错误';
						}
					}
				},{
					"sClass" : "center",
					"mDataProp" : "opState",
					"mRender" : function(obj){
						if(obj == null){
							return "";
						}else{
							return obj;
						}
					}
				},{
		                    "sClass" : "center",
		                    "mDataProp" : "writtenOffTime",
		                    "mRender" : function(obj){
		                        if(obj == null){
		                            return "";
		                        }else{
		                            return obj;
		                        }
		                    }
		                },{
					"sClass" : "center",
					"mDataProp" : "id",
					"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
						var html = "";
						<%--  <shiro:hasPermission name="/dictionary/editDictionary">  --%> 
							html += '<a href="javascript:;" onclick="detailPage('+oData.id+')">详细</a>';
							<%--  </shiro:hasPermission>  --%> 
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
		$('#supplierId').select2({
			placeholder: "运营商",
			allowClear: true
		});
		$('#cardState').select2({
			placeholder: "卡状态",
			allowClear: true
		});
	</script>
</body>
<!-- END BODY -->
</html>