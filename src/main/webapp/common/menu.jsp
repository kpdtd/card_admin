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
				<i class=" icon-list-ul"></i> <span class="title">运营商管理</span> <span class="arrow"></span>
			</a>
			<ul class="sub-menu" style="display: none;">
				<li id="agency">
					<a href="<%=request.getContextPath()%>/supplier/getPage">运营商基本信息</a>
				</li>
				<li id="goodsLibrary">
					<a href="">运营商套餐/流量池</a>
				</li>
			</ul>
		</li>
		<li class="">
			<a href="javascript:;">
				<i class=" icon-list-ul"></i> <span class="title">卡归属方管理</span> <span class="arrow"></span>
			</a>
			<ul class="sub-menu">
				<li>
					<a class="" href="#"> 归属方信息 </a>
				</li>
			</ul>
		</li>
		<li class="">
			<a href="javascript:;">
				<i class=" icon-list-ul"></i> <span class="title">卡信息管理</span> <span class="arrow"></span>
			</a>
			<ul class="sub-menu">
				<li>
					<a class="" href="#"> 库存管理</a>
				</li>
				<li>
					<a class="" href="#"> 注销卡管理</a>
				</li>
				<li>
					<a class="" href="<%=request.getContextPath()%>/supplier/getPage"> 卡批量管理</a>
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
					<a href="<%=request.getContextPath()%>/dictionary/getPage""> 数据字典管理</a>
				</li>
			</ul>
		</li>
	</ul>
	<!-- END SIDEBAR MENU -->
</div>