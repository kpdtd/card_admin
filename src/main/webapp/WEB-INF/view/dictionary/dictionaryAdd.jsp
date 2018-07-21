<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div class="modal-body" data-width="720">
	<div class="portlet-body form row-fluid">
		<form id="form_sample_1" action="#" class="form-horizontal dialog" onsubmit="return false;">
			<input type="hidden" id="initID" name="id" value="${dic.id }"/>
			<div class="control-group">
				<label class="control-label" for="firstName">键<span class="required">*</span></label>
				<div class="controls">
					<input type="text" name="name" class="m-wrap span10" placeholder="键" value="${dic.name}" ${dic.id !=null ? 'readonly' : '' } />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="firstName">值<span class="required">*</span></label>
				<div class="controls">
					<textarea name="value" class="m-wrap span10">${dic.value}</textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="firstName">描述</label>
				<div class="controls">
					<textarea name="description" class="m-wrap span10">${dic.description}</textarea>
				</div>
			</div>
		</form>
	</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn green" id="submit_btn" >保存</button>
	<button type="button" data-dismiss="modal" class="btn">关闭</button>
</div>
<script>
$("#submit_btn").click(function(){
	var id = $("#initID").val();
	if(id != "" && id != null){
		$("#form_sample_1").attr("action","./editDictionary");
	}else{
		$("#form_sample_1").attr("action","./addDictionary");
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
		},
		"value" : {
			required : true
		}
	}
});
</script>