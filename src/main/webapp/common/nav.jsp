<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header navbar navbar-inverse navbar-fixed-top">
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="navbar-inner">
		<div class="container-fluid">
			<!-- BEGIN LOGO -->
			<a class="brand" href="javascript:;"> <img src="<%=request.getContextPath() %>/media/image/logo.png" alt="logo" />
			</a>
			<!-- END LOGO -->

			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse"> <img
				src="<%=request.getContextPath() %>/media/image/menu-toggler.png" alt="" />
			</a>
			<!-- END RESPONSIVE MENU TOGGLER -->

			<!-- BEGIN TOP NAVIGATION MENU -->
			<ul class="nav pull-right">
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<li class="dropdown user"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <span
						class="username">${sessionScope.roles }</span> <img alt="" src="<%=request.getContextPath() %>/media/image/avatar1_small.jpg" /> <span
						class="username">${sessionScope.username }</span> <i class="icon-angle-down"></i>
				</a>
					<ul class="dropdown-menu">
						<li><a href="javascript:" onclick="updatePassword();"><i class="icon-signout"></i>修改密码</a></li>
						<li><a href="javascript:" onclick="logout();"><i class="icon-signout"></i>注 销</a></li>
					</ul></li>
				<!-- END USER LOGIN DROPDOWN -->
			</ul>
			<!-- END TOP NAVIGATION MENU -->
		</div>
	</div>
	<script type="text/javascript">
	function logout(){
		 $.ajax({
			type : 'POST',
			url : '../login/logout',
			dataType : "json",
			async : false,
			data : {},
			success : function(data) {
				window.location.href = data.msg;
			}
		})
	}
	
	function updatePassword(){
		App.Modal.load("../sysUser/updatePassword",{},{"title":"修改密码"});
	}
	</script>
	<!-- END TOP NAVIGATION BAR -->
</div>