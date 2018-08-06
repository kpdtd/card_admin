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
			<input type="hidden" id="initID" name="id" value="${activityCardInfo.id}"/>

			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	iccid</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="iccid" 
						 value="${activityCardInfo.iccid}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	msisdn</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="msisdn" 
						 value="${activityCardInfo.msisdn}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	imsi</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="imsi" 
						 value="${activityCardInfo.imsi}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	手机号</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="mobile" 
						 value="${activityCardInfo.mobile}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><span class="required">*</span>卡状态</label>
				<div class="controls">
					<c:forEach var="cardState" items="${cardStateList}">
						<c:choose>
							<c:when test="${cardState.id == activityCardInfo.cardState }">
								<label class="radio"><input type="radio" name=cardState id="cardState_${cardState.id }"
															checked value="${cardState.id }" />${cardState.name }</label>
							</c:when>
							<c:otherwise>
								<label class="radio"><input type="radio" id="cardState_${cardState.id }"
															name="cardState" value="${cardState.id }" />${cardState.name }</label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	购买价格(分)</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="price" 
						 value="${activityCardInfo.price}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	分类</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="sort" 
						 value="${activityCardInfo.sort}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	收货地址</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="address" 
						 value="${activityCardInfo.address}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	收货人姓名</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="name" 
						 value="${activityCardInfo.name}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	备注</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="info" 
						 value="${activityCardInfo.info}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label"><span class="required">*</span>订单状态</label>
				<div class="controls">
					<c:forEach var="state" items="${cardOrderStateList}">
						<c:choose>
							<c:when test="${state.id == activityCardInfo.state }">
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
		if($('input[type=radio][name=cardState]:checked').val()==null){
			$('input[type=radio][id=cardState_3]').attr('checked', 'checked');
		}
		if($('input[type=radio][name=state]:checked').val()==null){
			$('input[type=radio][name=state]:first').attr('checked', 'checked');
		}
	});
	$("#submit_btn").click(function(){
		var id = $("#initID").val();
		if(id != "" && id != null){
			$("#form_sample_1").attr("action","./addActivityCardInfo");
		}else{
			$("#form_sample_1").attr("action","./addActivityCardInfo");
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
			"mobile" : {
				required : true
			},"cardState":{
				required : true
			},"price":{
				required : true
			},"address":{
				required : true
			},"name":{
				required : true
			},"state":{
				required : true
			}
		}
	});
</script>