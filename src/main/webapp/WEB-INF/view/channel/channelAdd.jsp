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
			<input type="hidden" id="initID" name="id" value="${channel.id}"/>

			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	渠道名称</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="name" 
						 value="${channel.name}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	渠道编码</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="code" 
						 value="${channel.code}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName">	推广地址</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="adUrl" 
						 value="${channel.adUrl}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><span class="required">*</span>渠道类型</label>
				<div class="controls">
					<c:forEach var="category" items="${categoryList}">
						<c:choose>
							<c:when test="${category.id == channel.category }">
								<label class="radio">
									${channelType.name }
									<input type="radio" name=category id="category" checked value="${category.id }" />
								</label>
							</c:when>
							<c:otherwise>
								<label class="radio">
									<input type="radio" id="category" name="category" value="${category.id }" />${category.name }
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
							<c:when test="${state.id == channel.state }">
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
				<label class="control-label" for="firstName"><span class="required">*</span>	联系人</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="linker" 
						 value="${channel.linker}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	渠道信息</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="info" 
						 value="${channel.info}">
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
			$("#form_sample_1").attr("action","./addChannel");
		}else{
			$("#form_sample_1").attr("action","./addChannel");
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
			"code" : {
				required : true
			},
			"category" : {
				required : true
			},
			"state" : {
				required : true
			},
			"linker" : {
				required : true
			}
		}
	});
</script>