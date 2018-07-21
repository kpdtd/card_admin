<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>IOT管理 | 首页</title>
<%@ include file="/common/css.jsp"%>
</head>
<body class="page-header-fixed page-footer-fixed">
	<%@ include file="/common/rightTop.jsp"%>
	<div class="page-container">
		<%@ include file="/common/menu.jsp"%>
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<ul class="breadcrumb">
							<li><i class="icon-home"></i><a href="javascript:;">首页</a><i class="icon-angle-right"></i></li>
						</ul>
					</div>
				</div>
				<%-- <div class="row-fluid">
					<!-- END DASHBOARD STATS -->
					<div class="clearfix"></div>
					<div class="row-fluid">
						<div class="span6">
							<div class="portlet box purple">
								<div class="portlet-title">
									<div class="caption"><i class="icon-calendar"></i>收藏情况统计</div>
									<div class="actions">
										<!-- <a href="javascript:;" class="btn yellow easy-pie-chart-reload"><i class="icon-repeat"></i> Reload</a> -->
									</div>
								</div>
								<div class="portlet-body">
									<div class="row-fluid" id="container" >
										
									</div>
								</div>
							</div>
							<!-- END PORTLET-->
						</div>
						<div class="span6">
							<div class="portlet box purple">
								<div class="portlet-title">
									<div class="caption"><i class="icon-calendar"></i>月份订单分布情况</div>
									<div class="actions">
										<!-- <a href="javascript:;" class="btn yellow easy-pie-chart-reload"><i class="icon-repeat"></i> Reload</a> -->
									</div>
								</div>
								<div class="portlet-body">
									<div class="control-group">
											<select id="startMounth" class="m-wrap xsmall" name="startMounth">
												<option value=""></option>
												<c:forEach var="mounth" items="${mounthList}">
													<c:if test="${nowMounth==mounth.id}">
														<option value="${mounth.id}" selected >${mounth.name}</option>
													</c:if>
													<c:if test="${nowMounth!=mounth.id}">
														<option value="${mounth.id }">${mounth.name}</option>
													</c:if>
													
												</c:forEach>
											</select>
											<select id="endMounth" class="m-wrap xsmall" name="endMounth">
												<option value=""></option>
												<c:forEach var="mounth" items="${mounthList}">
													<c:if test="${ComparedMounth==mounth.id}">
														<option value="${mounth.id}" selected >${mounth.name}</option>
													</c:if>
													<c:if test="${ComparedMounth!=mounth.id}">
														<option value="${mounth.id}">${mounth.name}</option>
													</c:if>
												</c:forEach>
											</select>
											<select id="state" class="m-wrap xsmall" name="state" >
												<option value=""></option>
												<option value="0">失败</option>
												<option value="1">成功</option>
											</select>
											
										</div>
									<div class="row-fluid" id="container1" >
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div> --%>
			</div>
		</div>
	</div>
	<%@ include file="/common/js.jsp"%>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			App.init();
		});
		$('#startMounth').select2({
		    placeholder: "对比月份1",
		    allowClear: true
		});
	   $('#endMounth').select2({
		    placeholder: "对比月份2",
		    allowClear: true
		});
	   $('#state').select2({
		    placeholder: "订单状态",
		    allowClear: true
		});
	   
		/* var data;
	    $.ajax({
		   	type: "POST",
		   	async: false,
		   	url: "../userCollect/getData",
		   	dataType: "json",
		   	success: function(msg){
		   	  	data = msg;
		   	}
	    })
		 Highcharts.chart('container', {
		    chart: {
		        type: 'pie',
		        options3d: {
		            enabled: true,
		            alpha: 45
		        }
		    },
		    title: {
		        text: '各个活动收藏情况统计图'
		    },
		    plotOptions: {
		        pie: {
		            innerSize: 55,
		            depth: 50
		        }
		    },
		    series: [{
		        name: '收藏统计图',
		        data: data
		    }]
		}); */
	    
		$("#startMounth").change(function(){
			if($("#startMounth").val()!=""&&$("#endMounth").val()!=""){
				getCompared($("#startMounth").val(),$("#endMounth").val());
			}
			
		});
		$("#endMounth").change(function(){
			if($("#startMounth").val()!=""&&$("#endMounth").val()!=""){
				getCompared($("#startMounth").val(),$("#endMounth").val());
			}
			
		});
		/* var ComparedMounth = ${ComparedMounth};
		var nowMounth = ${nowMounth};
		if($("#startMounth").val()!=""&&$("#endMounth").val()!=""){
			getCompared($("#startMounth").val(),$("#endMounth").val());
		} */
		
		function getCompared(startMounth,endMounth){
			$.ajax({
			   	type: "POST",
			   	async: false,
			   	url: "../userCollect/getCompared",
			   	data:{"startMounth":startMounth,"endMounth":endMounth},
			   	dataType: "json",
			   	success: function(msg){
			   		Highcharts.chart('container1', {
					    chart: {
					        type: 'spline'
					    },
					    title: {
					        text: '各个月份订单情况'
					    },
					    xAxis: {
					        categories: ['1-3号', '4-6号', '7-9号', '10-12号', '13-15号', '16-18','19-21','22-24','25-27','28-月底']
					    },
					    yAxis: {
					        title: {
					            text: '单数'
					        },
					        labels: {
					            formatter: function () {
					                return this.value + '万';
					            }
					        }
					    },
					    tooltip: {
					        crosshairs: true,
					        shared: true
					    },
					    plotOptions: {
					        spline: {
					            marker: {
					                radius: 4,
					                lineColor: '#666666',
					                lineWidth: 1
					            }
					        }
					    },
					    series: msg
					});
			   	}
		    })
		};
	</script>
</body>
</html>
