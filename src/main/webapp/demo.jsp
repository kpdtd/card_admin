<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="kindeditor/themes/default/default.css" >
<script type="text/javascript" src="kindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="kindeditor/lang/zh-CN.js"></script>
<script type="text/javascript" src="media/kindeditor_common.js"></script>
<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="desc"]', {
				/* cssPath : '../plugins/code/prettify.css', */
				uploadJson : '../jsp/upload',
				fileManagerJson : '../jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			/* prettyPrint(); */
		});
	</script>
</head>
<body>
	<form name="example" method="post" action="getCongmit">
		<textarea style="width:800px;height:300px;visibility:hidden;" name="desc"></textarea>
		<input type="submit"  />
	</form>
	123123
</body>
</html>