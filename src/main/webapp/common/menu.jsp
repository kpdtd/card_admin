<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" import="com.anl.card.constant.Constant"%>
<%-- <%@ page language="java" import="java.util.Set,com.tiger.flow.persistence.po.SysResources"%> --%>
<%
	String menu = "";
	if(request.getRequestURI().indexOf("index.jsp") > 0) {
		menu = Constant.MENU_INDEX;
	}
	else {

		menu = request.getAttribute("menu") != null ? request.getAttribute("menu").toString() : null;
	}
%>
<div class="page-sidebar nav-collapse collapse">
	<!-- BEGIN SIDEBAR MENU -->
	<input id="path" type="hidden" value="<%=menu%>" />
	<%-- <ul class="page-sidebar-menu">
		<li>
			<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
			<div class="sidebar-toggler hidden-phone"></div> <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
		</li>
		
		<c:forEach var="resource" items="${resourceTop}" varStatus="i">
			<c:choose>
				<c:when test="${resource.resourceType == 'url' && resource.parentId == 0 && resource.priority == 0}">
					<li id="${resource.resourceDesc }" class="start">
						<a href="<%=request.getContextPath()%>${resource.resourceString}"> 
							<i class="${resource.module }"></i> 
							<span class="title">${resource.resourceName }</span> 
							<span class="selected"></span>
						</a>
					</li>
				</c:when>
				<c:when test="${resource.resourceType == 'tag' }">
					<li class="last">
						<a href="javascript:;">
							<i class="${resource.module}"></i>
							<span class="title">${resource.resourceName }</span>
							<span class="arrow"></span>
						</a>
						<ul class="sub-menu">
							<c:forEach var="res" items="${resourceSub}">
								<c:if test="${res.parentId == resource.resourceId }">
									<li id="${res.resourceDesc }">
										<a href="<%=request.getContextPath()%>${res.resourceString}">${res.resourceName }</a>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</li>
				</c:when>
				<c:when test="${resource.resourceType == 'url' && resource.parentId == 0 && resource.priority != 0}">
					<li id="${resource.resourceDesc}">
						<a href="<%=request.getContextPath()%>${resource.resourceString}">
							<i class="${resource.module}"></i>
							<span class="title">${resource.resourceName }</span>
						</a>
					</li>
				</c:when>
			</c:choose>
		</c:forEach>
	</ul> --%>
	<ul class="page-sidebar-menu">
		<li>
			<div class="sidebar-toggler hidden-phone"></div>
		</li>
		<li id="index" class="start">
			<a class=" start" href="#">
				<i class="icon-home"></i> <span class="title">首页</span>
			</a>
		</li>
		<li class="">
			<a href="javascript:;">
				<i class=" icon-list-ul"></i> <span class="title">合作方管理</span> <span class="arrow"></span>
			</a>
			<ul class="sub-menu" style="display: none;">
				<li id="supplier">
					<a href="<%=request.getContextPath()%>/supplier/getPage">运营商基本信息</a>
				</li>
				<li id="supplierPool">
					<a href="<%=request.getContextPath()%>/supplierPool/getPage">运营商套餐/流量池</a>
				</li>
				<li id="cardOwner">
					<a class="" href="<%=request.getContextPath()%>/cardOwner/getPage"> 归属方管理 </a>
				</li>
				<li id="channel">
					<a class="" href="<%=request.getContextPath()%>/channel/getPage">推广渠道管理</a>
				</li>
				<li id="interfaceList">
					<a class="" href="<%=request.getContextPath()%>/interfaceList/getPage">接口定义管理</a>
				</li>
				<li id="supplierInterfaceItem">
					<a class="" href="<%=request.getContextPath()%>/supplierInterfaceItem/getPage">运营商接口管理</a>
				</li>
			</ul>
		</li>
		<li class="">
			<a href="javascript:;">
				<i class=" icon-list-ul"></i> <span class="title">用户管理</span> <span class="arrow"></span>
			</a>
			<ul class="sub-menu" style="display: none;">
				<li id="user">
					<a href="<%=request.getContextPath()%>/user/getPage">用户基本信息</a>
				</li>
				<li id="userAccount">
					<a href="<%=request.getContextPath()%>/userAccount/getPage">用户账户信息</a>
				</li>
				<li id="userFlowUsedDay">
					<a href="<%=request.getContextPath()%>/userFlowUsedDay/getPage">用户流量日使用量</a>
				</li>
				<li id="userChargeRecord">
					<a href="<%=request.getContextPath()%>/userChargeRecord/getPage">用户支付订单信息</a>
				</li>
				<li id="activityCardInfo">
					<a href="<%=request.getContextPath()%>/activityCardInfo/getPage">用户卡订单信息</a>
				</li>
				<li id="chargeList">
					<a href="<%=request.getContextPath()%>/chargeList/getPage">充值列表信息</a>
				</li>
				<li id="planDefinition">
					<a href="<%=request.getContextPath()%>/planDefinition/getPage">套餐列表信息</a>
				</li>
				<li id="flowPacketDefinition">
					<a href="<%=request.getContextPath()%>/flowPacketDefinition/getPage">流量包信息</a>
				</li>
			</ul>
		</li>

		<li class="">
			<a href="javascript:;">
				<i class=" icon-list-ul"></i> <span class="title">卡信息管理</span> <span class="arrow"></span>
			</a>
			<ul class="sub-menu">
				<li id="card">
					<a class="" href="<%=request.getContextPath()%>/card/getPage"> 卡库存管理</a>
				</li>
				<li id="cardWrittenOff">
					<a class="" href="<%=request.getContextPath()%>/cardWrittenOff/getPage"> 卡注销管理</a>
				</li>
				<li id="cardBatchProcess">
					<a class="" href="<%=request.getContextPath()%>/cardBatchProcess/getPage"> 卡批量操作</a>
				</li>
			</ul>
		</li>
		<li class="">
			<a href="javascript:;">
				<i class=" icon-list-ul"></i> <span class="title">营销活动管理</span> <span class="arrow"></span>
			</a>
			<ul class="sub-menu">
				<li id="cashCouponDefinition">
					<a class="" href="<%=request.getContextPath()%>/cashCouponDefinition/getPage">优惠券管理</a>
				</li>
				<li id="goodsActivity">
					<a href="<%=request.getContextPath()%>/goodsActivity/getPage">活动列表信息</a>
				</li>
			</ul>
		</li>
		<li class="">
			<a href="javascript:;">
				<i class=" icon-list-ul"></i> <span class="title">客服管理</span> <span class="arrow"></span>
			</a>
			<ul class="sub-menu">
				<li>
					<a class="" href="<%=request.getContextPath()%>/customerService/getPage">用户问题工单</a>
				</li>
				<li >
					<a href="<%=request.getContextPath()%>/customerService/getPage">用户综合信息</a>
				</li>
			</ul>
		</li>
		<li class="">
			<a href="javascript:;">
				<i class=" icon-list-ul"></i> <span class="title">定时任务管理</span> <span class="arrow"></span>
			</a>
			<ul class="sub-menu">
				<li id="autoTaskDefinition">
					<a class="" href="<%=request.getContextPath()%>/autoTaskDefinition/getPage">定时任务配置</a>
				</li>
				<li id="autoTaskExecHistory">
					<a href="<%=request.getContextPath()%>/autoTaskExecHistory/getPage">执行结果监控</a>
				</li>
				<li id="">
					失败任务处理
				</li>
			</ul>
		</li>
		<li class="">
			<a href="javascript:;">
				<i class=" icon-list-ul"></i> <span class="title">系统管理</span> <span class="arrow"></span>
			</a>
			<ul class="sub-menu">
				<li id="column">
					<a class="" href="<%=request.getContextPath()%>/column/page"> 频道管理</a>
				</li>
				<li id="dictionary">
					<a href="<%=request.getContextPath()%>/dictionary/getPage"> 数据字典管理</a>
				</li>
			</ul>
		</li>
	</ul>
	<!-- END SIDEBAR MENU -->
</div>