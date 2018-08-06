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
			<input type="hidden" id="initID" name="id" value="${goodsActivity.id}"/>
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	标题</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="title" 
						 value="${goodsActivity.title}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	执行逻辑</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="execLogic" 
						 value="${goodsActivity.execLogic}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	说明</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="info" 
						 value="${goodsActivity.info}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><span class="required"></span>类型</label>
				<div class="controls">
					<c:forEach var="type" items="${activityType}">
						<c:choose>
							<c:when test="${type.id == goodsActivity.type }">
								<label class="radio"><input type="radio" name="type" id="type"
															checked value="${type.id }" />${type.name }</label>
							</c:when>
							<c:otherwise>
								<label class="radio"><input type="radio" id="type"
															name="type" value="${type.id }" />${type.name }</label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><span class="required"></span>运营商</label>
				<div class="controls">
					<c:forEach var="operators" items="${providerType}">
						<c:choose>
							<c:when test="${operators.id == goodsActivity.operators }">
								<label class="radio"><input type="radio" name=operators id="operators"
															checked value="${operators.id }" />${operators.name }</label>
							</c:when>
							<c:otherwise>
								<label class="radio"><input type="radio" id="operators"
															name="operators" value="${operators.id }" />${operators.name }</label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	执行JSON</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="extJson" 
						 value="${goodsActivity.extJson}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><span class="required"></span>状态</label>
				<div class="controls">
					<c:forEach var="state" items="${stateList}">
						<c:choose>
							<c:when test="${state.id == goodsActivity.state }">
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
		if($('input[type=radio][name=type]:checked').val()==null){
			$('input[type=radio][name=type]:first').attr('checked', 'checked');
		}
		if($('input[type=radio][name=operators]:checked').val()==null){
			$('input[type=radio][name=operators]:first').attr('checked', 'checked');
		}
		if($('input[type=radio][name=state]:checked').val()==null){
			$('input[type=radio][name=state]:first').attr('checked', 'checked');
		}
	});

	$("#submit_btn").click(function(){
		var id = $("#initID").val();
		if(id != "" && id != null){
			$("#form_sample_1").attr("action","./addGoodsActivity");
		}else{
			$("#form_sample_1").attr("action","./addGoodsActivity");
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
			"title" : {
				required : true
			},"execLogic" : {
				required : true
			}
		}
	});
</script>