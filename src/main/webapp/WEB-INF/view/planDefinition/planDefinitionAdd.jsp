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
			<input type="hidden" id="initID" name="id" value="${planDefinition.id}"/>
			

			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	套餐编码</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="code" 
						 value="${planDefinition.code}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	套餐名称</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="name" 
						 value="${planDefinition.name}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"> </span>	套餐子名称</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="subName" 
						 value="${planDefinition.subName}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	月租价格(分)</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="monthlyPlanPrice" 
						 value="${planDefinition.monthlyPlanPrice}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	流量计价单位(M)</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="flowUnit" 
						 value="${planDefinition.flowUnit}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	单位计价(元)</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="flowUnitPrice" 
						 value="${planDefinition.flowUnitPrice}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><span class="required">*</span>状态</label>
				<div class="controls">
					<c:forEach var="state" items="${stateList}">
						<c:choose>
							<c:when test="${state.id == planDefinition.state }">
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
				<label class="control-label" for="firstName"><span class="required"></span>	套餐说明</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="info" 
						 value="${planDefinition.info}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><span class="required">*</span>生效时间</label>
				<div class="controls">
					<c:forEach var="effectiveTime" items="${effectiveTimeList}">
						<c:choose>
							<c:when test="${effectiveTime.id == planDefinition.effectiveTime }">
								<label class="radio"><input type="radio" name=effectiveTime id="effectiveTime"
															checked value="${effectiveTime.id }" />${effectiveTime.name }</label>
							</c:when>
							<c:otherwise>
								<label class="radio"><input type="radio" id="effectiveTime"
															name="effectiveTime" value="${effectiveTime.id }" />${effectiveTime.name }</label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	显示顺序</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="displaySort" 
						 value="${planDefinition.displaySort}">
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
			$("#form_sample_1").attr("action","./addPlanDefinition");
		}else{
			$("#form_sample_1").attr("action","./addPlanDefinition");
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
			"code":{
				required : true
			},
			"name" : {
				required : true
			},
			"monthlyPlanPrice":{
				required : true
			},
			"flowUnit":{
				required : true
			},
			"flowUnitPrice":{
				required : true
			},
			"state":{
				required : true
			},
			"effectiveTime":{
				required : true
			}
		}
	});
</script>