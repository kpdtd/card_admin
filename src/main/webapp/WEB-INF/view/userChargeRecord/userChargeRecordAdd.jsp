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
			<input type="hidden" id="initID" name="id" value="$/{userChargeRecord.id}"/>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	id</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="id" 
						 value="$/{userChargeRecord.id}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	userId</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="userId" 
						 value="$/{userChargeRecord.userId}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	chargeListId</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="chargeListId" 
						 value="$/{userChargeRecord.chargeListId}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	chargeListName</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="chargeListName" 
						 value="$/{userChargeRecord.chargeListName}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	phone</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="phone" 
						 value="$/{userChargeRecord.phone}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	outTradeNo</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="outTradeNo" 
						 value="$/{userChargeRecord.outTradeNo}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	tradeNo</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="tradeNo" 
						 value="$/{userChargeRecord.tradeNo}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	iccid</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="iccid" 
						 value="$/{userChargeRecord.iccid}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	openId</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="openId" 
						 value="$/{userChargeRecord.openId}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	payType</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="payType" 
						 value="$/{userChargeRecord.payType}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	money</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="money" 
						 value="$/{userChargeRecord.money}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	payer</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="payer" 
						 value="$/{userChargeRecord.payer}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	state</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="state" 
						 value="$/{userChargeRecord.state}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	causes</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="causes" 
						 value="$/{userChargeRecord.causes}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	ip</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="ip" 
						 value="$/{userChargeRecord.ip}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	aid</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="aid" 
						 value="$/{userChargeRecord.aid}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	createTime</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="createTime" 
						 value="$/{userChargeRecord.createTime}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	updateTime</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="updateTime" 
						 value="$/{userChargeRecord.updateTime}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><span class="required">*</span>渠道类型</label>
				<div class="controls">
					<c:forEach var="" items="">
						<c:choose>
							<c:when test="\{channelType.id == agency.channelType }">
								<label class="radio">
									{channelType.name }
									<input type="radio" name=channelType id="channelType" checked value="$\{channelType.id }" />
								</label>
							</c:when>
							<c:otherwise>
								<label class="radio">
									<input type="radio" id="channelType" name="channelType" value="$\{channelType.id }" />$\{channelType.name }
								</label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label"><span class="required">*</span>状态</label>
				<div class="controls">
					<c:forEach var="state" items="$\{stateList}">
						<c:choose>
							<c:when test="$\{state.id == agency.state }">
								<label class="radio"><input type="radio" name=state id="state"
									checked value="$\{state.id }" />$\{state.name }</label>
							</c:when>
							<c:otherwise>
								<label class="radio"><input type="radio" id="state"
									name="state" value="$\{state.id }" />$\{state.name }</label>
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
	$("#submit_btn").click(function(){
		var id = $("#initID").val();
		if(id != "" && id != null){
			$("#form_sample_1").attr("action","./addUserChargeRecord");
		}else{
			$("#form_sample_1").attr("action","./addUserChargeRecord");
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