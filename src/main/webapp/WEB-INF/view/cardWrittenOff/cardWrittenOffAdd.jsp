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
			<input type="hidden" id="initID" name="id" value="${cardWrittenOff.id}"/>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	id</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="id" 
						 value="${cardWrittenOff.id}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	supplierId</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="supplierId" 
						 value="${cardWrittenOff.supplierId}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	cardOwnerId</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="cardOwnerId" 
						 value="${cardWrittenOff.cardOwnerId}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	iccid</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="iccid" 
						 value="${cardWrittenOff.iccid}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	msisdn</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="msisdn" 
						 value="${cardWrittenOff.msisdn}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	imsi</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="imsi" 
						 value="${cardWrittenOff.imsi}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	bindDevice</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="bindDevice" 
						 value="${cardWrittenOff.bindDevice}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	cardState</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="cardState" 
						 value="${cardWrittenOff.cardState}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	gprsState</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="gprsState" 
						 value="${cardWrittenOff.gprsState}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	opState</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="opState" 
						 value="${cardWrittenOff.opState}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	poolId</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="poolId" 
						 value="${cardWrittenOff.poolId}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	operator</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="operator" 
						 value="${cardWrittenOff.operator}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	apn</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="apn" 
						 value="${cardWrittenOff.apn}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	activationTime</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="activationTime" 
						 value="${cardWrittenOff.activationTime}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	createTime</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="createTime" 
						 value="${cardWrittenOff.createTime}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	updateTime</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="updateTime" 
						 value="${cardWrittenOff.updateTime}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	writtenOffTime</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="writtenOffTime" 
						 value="${cardWrittenOff.writtenOffTime}">
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
			$("#form_sample_1").attr("action","./addCardWrittenOff");
		}else{
			$("#form_sample_1").attr("action","./addCardWrittenOff");
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