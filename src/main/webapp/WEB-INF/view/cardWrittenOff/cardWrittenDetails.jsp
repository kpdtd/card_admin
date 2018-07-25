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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="modal-body" data-width="720">
	<div class="portlet-body form row-fluid">
		<form id="form_sample_1" action="#" class="form-horizontal dialog"  enctype="multipart/form-data"
			onsubmit="return false;">
			<input type="hidden" id="initID" name="id"  readonly value="${cardWrittenOff.id}"/>

			<div class="control-group">
				<label class="control-label" for="firstName">	id</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="id" readonly
						 value="${cardWrittenOff.id}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	运营商</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="supplierId" readonly
						 value="${cardWrittenOff.supplierName}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	归属方</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="cardOwnerId" readonly
						 value="${cardWrittenOff.ownerCompany}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	iccid</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="iccid" readonly
						 value="${cardWrittenOff.iccid}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	msisdn</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="msisdn" readonly
						 value="${cardWrittenOff.msisdn}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	imsi</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="imsi" readonly
						 value="${cardWrittenOff.imsi}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	设备号</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="bindDevice" readonly
						 value="${cardWrittenOff.bindDevice}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	生命周期</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="cardState" readonly
						 value="<c:if test="${cardWrittenOff.cardState==0}">白卡</c:if><c:if test="${cardWrittenOff.cardState==1}">测试期</c:if><c:if test="${cardWrittenOff.cardState==2}">沉默期</c:if><c:if test="${cardWrittenOff.cardState==3}">正使用</c:if><c:if test="${cardWrittenOff.cardState==4}">停机</c:if><c:if test="${cardWrittenOff.cardState==5}">销户</c:if><c:if test="${cardWrittenOff.cardState==6}">预约销户</c:if>">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	gprs状态</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="gprsState" readonly
						 value="<c:if test="${cardWrittenOff.gprsState==1}">开通</c:if><c:if test="${cardWrittenOff.gprsState==2}">关停</c:if>">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	运营状态</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="opState" readonly
						 value="${cardWrittenOff.opState}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	流量池ID</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="poolId" readonly
						 value="${cardWrittenOff.poolId}">
				</div>
			</div>
			

			<div class="control-group">
				<label class="control-label" for="firstName">	apn名称</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="apn" readonly
						 value="${cardWrittenOff.apn}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	激活时间</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="activationTime" readonly
						 value="<fmt:formatDate type="date" value="${cardWrittenOff.activationTime}" dateStyle="default" pattern="yyyy-MM-dd HH:mm:ss" />">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	创建时间</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="createTime" readonly
						 value="<fmt:formatDate type="date" value="${cardWrittenOff.createTime}" dateStyle="default" pattern="yyyy-MM-dd HH:mm:ss" />">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	更新时间</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="updateTime" readonly
						 value="<fmt:formatDate type="date" value="${cardWrittenOff.updateTime}" dateStyle="default" pattern="yyyy-MM-dd HH:mm:ss" />">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">	注销时间</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="writtenOffTime" readonly
						 value="<fmt:formatDate type="date" value="${cardWrittenOff.writtenOffTime}" dateStyle="default" pattern="yyyy-MM-dd HH:mm:ss" />">
				</div>
			</div>
			
		</form>
	</div>
</div>
<div class="modal-footer">
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