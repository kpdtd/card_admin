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
			<input type="hidden" id="initID" name="id" value="${flowPacketDefinition.id}"/>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	编码</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="code" 
						 value="${flowPacketDefinition.code}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	产品名称</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="name" 
						 value="${flowPacketDefinition.name}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	流量值(M)</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="flowNumber" 
						 value="${flowPacketDefinition.flowNumber}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	售卖价格(分)</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="price" 
						 value="${flowPacketDefinition.price}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	显示顺序</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="displaySort" 
						 value="${flowPacketDefinition.displaySort}">
					<label class="control-label" for="firstName"><span class="required">越小越排前</span></label>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	优先级</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="useSort" 
						 value="${flowPacketDefinition.useSort}">
					<label class="control-label" for="firstName"><span class="required">越小越先扣减</span></label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><span class="required"></span>生效规则</label>
				<div class="controls">
					<c:forEach var="startTime" items="${flowStartTime}">
						<c:choose>
							<c:when test="${startTime.id == flowPacketDefinition.startTime }">
								<label class="radio"><input type="radio" name=startTime id="startTime"
															checked value="${startTime.id }" />${startTime.name }</label>
							</c:when>
							<c:otherwise>
								<label class="radio"><input type="radio" id="startTime"
															name="startTime" value="${startTime.id }" />${startTime.name }</label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	产品提示</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="tips" 
						 value="${flowPacketDefinition.tips}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	产品说明</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="deatil" 
						 value="${flowPacketDefinition.deatil}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><span class="required"></span>状态</label>
				<div class="controls">
					<c:forEach var="state" items="${stateList}">
						<c:choose>
							<c:when test="${state.id == flowPacketDefinition.state }">
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
		</form>
	</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn green" id="submit_btn">保存</button>
	<button type="button" data-dismiss="modal" class="btn">关闭</button>
</div>
<script>
	jQuery(document).ready(function() {
		if($('input[type=radio][name=startTime]:checked').val()==null){
			$('input[type=radio][name=startTime]:first').attr('checked', 'checked');
		}
		if($('input[type=radio][name=state]:checked').val()==null){
			$('input[type=radio][name=state]:first').attr('checked', 'checked');
		}
	});

	$("#submit_btn").click(function(){
		var id = $("#initID").val();
		if(id != "" && id != null){
			$("#form_sample_1").attr("action","./addFlowPacketDefinition");
		}else{
			$("#form_sample_1").attr("action","./addFlowPacketDefinition");
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
			"code" : {
				required : true
			},"name" : {
				required : true
			},"flowNumber" : {
				required : true
			},"price" : {
				required : true
			},"startTime" : {
				required : true
			}
		}
	});
</script>