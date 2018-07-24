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
			<input type="hidden" id="initID" name="id" value="${cardOwner.id}"/>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>公司名称</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="company" 
						 value="${cardOwner.company}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>用户名</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="username" 
						 value="${cardOwner.username}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>密码</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="password" 
						 value="${cardOwner.password}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><span class="required">*</span>类型</label>
				<div class="controls">
					<c:forEach var="cardOwnerType" items="${cardOwnerTypeList}">
						<c:choose>
							<c:when test="${cardOwnerType.id == cardOwner.ownerType }">
								<label class="radio">
									<input type="radio" name="ownerType" id="ownerType" checked value="${cardOwnerType.id }" />${cardOwnerType.name }
								</label>
							</c:when>
							<c:otherwise>
								<label class="radio">
									<input type="radio" id="ownerType" name="ownerType" value="${cardOwnerType.id }" />${cardOwnerType.name }
								</label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><span class="required">*</span>状态</label>
				<div class="controls">
					<c:forEach var="state" items="${stateList}">
						<c:choose>
							<c:when test="${state.id == cardOwner.state }">
								<label class="radio"><input type="radio" name=state id="state"
															checked value="${state.id }" />${state.name }</label>
							</c:when>
							<c:otherwise>
								<label class="radio"><input type="radio" id="state"
															name="state" value="${state.id }" />${state.name }</label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>扩展信息</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="ecExtensionInfo" 
						 value="${cardOwner.ecExtensionInfo}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>备注</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="info" 
						 value="${cardOwner.info}">
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
			$("#form_sample_1").attr("action","./addCardOwner");
		}else{
			$("#form_sample_1").attr("action","./addCardOwner");
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
			"company" : {
				required : true
			}
		}
	});
</script>