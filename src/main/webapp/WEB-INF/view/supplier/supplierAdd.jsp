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
			<input type="hidden" id="initID" name="id" value="$/{supplier.id}"/>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	id</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="id" 
						 value="$/{supplier.id}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	company</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="company" 
						 value="$/{supplier.company}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	signKey</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="signKey" 
						 value="$/{supplier.signKey}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	invokeToken</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="invokeToken" 
						 value="$/{supplier.invokeToken}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	ecCode</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="ecCode" 
						 value="$/{supplier.ecCode}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	identity</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="identity" 
						 value="$/{supplier.identity}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	linker</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="linker" 
						 value="$/{supplier.linker}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	phone</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="phone" 
						 value="$/{supplier.phone}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	mail</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="mail" 
						 value="$/{supplier.mail}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	state</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="state" 
						 value="$/{supplier.state}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	bankInfo</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="bankInfo" 
						 value="$/{supplier.bankInfo}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	business</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="business" 
						 value="$/{supplier.business}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	ecExtensionInfo</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="ecExtensionInfo" 
						 value="$/{supplier.ecExtensionInfo}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	attachment</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="attachment" 
						 value="$/{supplier.attachment}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	info</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="info" 
						 value="$/{supplier.info}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	creator</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="creator" 
						 value="$/{supplier.creator}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	createTime</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="createTime" 
						 value="$/{supplier.createTime}">
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
			$("#form_sample_1").attr("action","./addSupplier");
		}else{
			$("#form_sample_1").attr("action","./addSupplier");
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