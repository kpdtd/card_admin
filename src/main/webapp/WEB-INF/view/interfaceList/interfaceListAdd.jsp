<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.radio input[type="radio"], .checkbox input[type="checkbox"] {
	float: left;
	margin: 4px 4px 0;
	margin-left: 0px;
}
</style>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<div class="modal-body" data-width="720">
	<div class="portlet-body form row-fluid">
		<form id="form_sample_1" action="#" class="form-horizontal dialog"  enctype="multipart/form-data"
			onsubmit="return false;">
			<input type="hidden" id="initID" name="id" value="${interfaceList.id}"/>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	接口名称</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="name" 
						 value="${interfaceList.name}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	接口标识符</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="tag" 
						 value="${interfaceList.tag}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	说明</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="info" 
						 value="${interfaceList.info}">
				</div>
			</div>
		</form>
	</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn green" id="submit_btn">保存</button>
	<button type="button" data-dismiss="modal" class="btn">关闭</button>
</div>
<script>
	$("#submit_btn").click(function(){
		var id = $("#initID").val();
		if(id != "" && id != null){
			$("#form_sample_1").attr("action","./addInterfaceList");
		}else{
			$("#form_sample_1").attr("action","./addInterfaceList");
		}
		if($('#form_sample_1').valid()){
			App.Ajax.submit('form_sample_1',{
				fn : function(json){
					App.Tables.refresh('portlet');
				}
			});
		}
	});
	App.validate('form_sample_1',{
		rules : {
			"name" : {
				required : true
			},"tag" : {
				required : true
			},"info" : {
				required : true
			}
		}
	});
</script>