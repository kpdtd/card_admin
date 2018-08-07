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
			<input type="hidden" id="initID" name="id" value="${realnameAuthentication.id}"/>

			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	手机号</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="phone" 
						 value="${realnameAuthentication.phone}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	姓名</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="name" 
						 value="${realnameAuthentication.name}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	证件号码</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="identificationNumber" 
						 value="${realnameAuthentication.identificationNumber}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	msisdn</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="msisdn"  readonly
						 value="${realnameAuthentication.msisdn}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	imsi</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="imsi" readonly
						 value="${realnameAuthentication.imsi}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	iccid</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="iccid" readonly
						 value="${realnameAuthentication.iccid}">
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
			$("#form_sample_1").attr("action","./addRealnameAuthentication");
		}else{
			$("#form_sample_1").attr("action","./addRealnameAuthentication");
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
			"phone" : {
				required : true
			},
			"name" : {
				required : true
			},
			"identificationNumber" : {
				required : true
			}
		}
	});
</script>