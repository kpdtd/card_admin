<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<!--<![endif]-->
<%-- <%
	Cookie cookie = null;
	Cookie[] cookies = null;
	// 获取当前域名下的cookies，是一个数组
	cookies = request.getCookies();
	if( cookies != null ){
	   for (int i = 0; i < cookies.length; i++){
	      cookie = cookies[i];
	      if((cookie.getName( )).compareTo("JSESSIONID") == 0 ){
	         cookie.setMaxAge(0);
	         response.addCookie(cookie);
	      }
	   }
	}
%>  --%>
<head>
<meta charset="utf-8" />
<title>流量3.0 | 登录页面</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<link href="media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="media/css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css" />
<link href="media/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />
<link href="media/css/style-metro.css" rel="stylesheet" type="text/css" />
<link href="media/css/style.css" rel="stylesheet" type="text/css" />
<link href="media/css/style-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="media/css/default.css" rel="stylesheet" type="text/css"
	id="style_color" />
<link href="media/css/uniform.default.css" rel="stylesheet"
	type="text/css" />
<link href="media/css/login.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="media/image/favicon.ico" />
</head>
<body class="login" style="">
	<div class="logo">
		<img src="media/image/logo-big.png" alt="">
	</div>
	<div class="content">
		<form action="./login/userLogin" class="form-vertical login-form" id="form_sample_1"
			method="POST" onsubmit="return false;">
			<h3 class="form-title">登录您的账户11111</h3>
			<div class="control-group">
				<label class="control-label"></label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-user"></i> 
						<input class="m-wrap placeholder-no-fix" type="text" placeholder="用户名" name="userAccount">
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"></label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i> 
						<input class="m-wrap placeholder-no-fix" type="password" placeholder="密码" name="userPassword">
					</div>
				</div>
			</div>
			<div class="form-actions">
				<button type="button" class="btn green pull-right" id="submit_btn">
					登录 <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
		</form>
	</div>
	<div class="copyright">2017 © 流量3.0管理平台.</div>
	<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="media/js/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="media/js/jquery-ui-1.10.1.custom.min.js"
		type="text/javascript"></script>
	<script src="media/js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="media/js/excanvas.min.js"></script>
	<script src="media/js/respond.min.js"></script>  
	<![endif]-->
	<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.uniform.min.js" type="text/javascript"></script>
	<script src="media/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="media/js/app.js" type="text/javascript"></script>
	<script src="media/js/bootstrap-modal.js" type="text/javascript"></script>
	<script src="media/js/bootstrap-modalmanager.js" type="text/javascript"></script>
	<script src="media/js/ui-modals.js" type="text/javascript"></script>
	<script src="media/js/login.js" type="text/javascript"></script>
	<script>
		jQuery(document).ready(function() {
			App.init();
			Login.init();
		});
		
		$("#submit_btn").click(function() {
			App.Ajax.submit('form_sample_1');
		});
	</script>
</body>
</html>