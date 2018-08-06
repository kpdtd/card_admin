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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="modal-body" data-width="720">
	<div class="portlet-body form row-fluid">
		<form id="form_sample_1" action="#" class="form-horizontal dialog"  enctype="multipart/form-data"
			onsubmit="return false;">
			<input type="hidden" id="initID" name="id" value="${cashCouponDefinition.id}"/>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	名称</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="title" 
						 value="${cashCouponDefinition.title}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	子标题</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="subtitle" 
						 value="${cashCouponDefinition.subtitle}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	提示文字</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="tips" 
						 value="${cashCouponDefinition.tips}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	详情</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="deatil" 
						 value="${cashCouponDefinition.deatil}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	数量</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="number" 
						 value="${cashCouponDefinition.number}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><span class="required">*</span>类型</label>
				<div class="controls">
					<c:forEach var="couponType" items="${typeList}">
						<c:choose>
							<c:when test="${couponType.id == cashCouponDefinition.couponType }">
								<label class="radio"><input type="radio" name="couponType" id="couponType"
															checked value="${couponType.id }" />${couponType.name }</label>
							</c:when>
							<c:otherwise>
								<label class="radio"><input type="radio" id="couponType"
															name="couponType" value="${couponType.id }" />${couponType.name }</label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>	面值(分)</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="faceValue" 
						 value="${cashCouponDefinition.faceValue}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><span class="required">*</span>日期规则</label>
				<div class="controls">
					<c:forEach var="rule" items="${ruleList}">
						<c:choose>
							<c:when test="${rule.id == cashCouponDefinition.dateRule }">
								<label class="radio"><input type="radio" name="dateRule" id="${rule.id }" checked value="${rule.id }" />${rule.name }</label>
							</c:when>
							<c:otherwise>
								<label class="radio"><input type="radio" id="${rule.id }"
															name="dateRule" value="${rule.id }" />${rule.name }</label>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			
			<div class="control-group" id="validDays">
				<label class="control-label" for="firstName"><span class="required">*</span>	有效天数</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="validDays" 
						 value="${cashCouponDefinition.validDays}">
				</div>
			</div>

			<div class="control-group" id="startDate">
				<label class="control-label" for="firstName"><span class="required">*</span>	开始时间</label>
				<div class="controls">
					<input type="text" class="m-wrap xsmall form_date" name="startDate"
						   value="<fmt:formatDate type="date" value="${cashCouponDefinition.startDate}" dateStyle="default" pattern="yyyy-MM-dd" />"
						   pattern="yyyy-MM-dd" placeholder="开始时间">
				</div>
			</div>

			<div class="control-group" id="endDate">
				<label class="control-label" for="firstName"><span class="required">*</span>	结束时间</label>
				<div class="controls">
					<input type="text" class="m-wrap xsmall form_date"  name="endDate"
						   value="<fmt:formatDate type="date" value="${cashCouponDefinition.endDate}" dateStyle="default" pattern="yyyy-MM-dd" />"
						   pattern="yyyy-MM-dd" placeholder="结束时间">

				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required"></span>	扩展逻辑定义</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="extensionInfo" 
						 value="${cashCouponDefinition.extensionInfo}">
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
		if($('input[type=radio][name=dateRule]:checked').val()==2){
			$("#validDays").addClass('hide');
			$("#startDate").removeClass('hide');
			$("#endDate").removeClass('hide');

		}else {
			$('input[type=radio][name=dateRule]:first').attr('checked', 'checked');
			$("#startDate").addClass('hide');
			$("#endDate").addClass('hide');
			$("#validDays").removeClass('hide');
		}
		if($('input[type=radio][name=couponType]:checked').val()==null){
			$('input[type=radio][name=couponType]:first').attr('checked', 'checked');
		}
	});
	$("#submit_btn").click(function(){
		var id = $("#initID").val();
		if(id != "" && id != null){
			$("#form_sample_1").attr("action","./addCashCouponDefinition");
		}else{
			$("#form_sample_1").attr("action","./addCashCouponDefinition");
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
			}, "deatil" : {
				required : true
			}, "number" : {
				required: true,
				digits:true
			}, "faceValue" : {
				required : true,
				digits:true
			}, "validDays" : {
				required : "#1:checked",
				digits:true
			},"startDate" : {
				required : "#2:checked"
			},"endDate" : {
				required : "#2:checked"
			}
		}
	});
	$('.form_date').datetimepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		weekStart: 1,
		todayBtn: 1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
	$('input[type=radio][name=dateRule]').change(function () {
		if(this.value==1){
			$("#startDate").addClass('hide');
			$("#endDate").addClass('hide');
			$("#validDays").removeClass('hide');
		}else {
			$("#validDays").addClass('hide');
			$("#startDate").removeClass('hide');
			$("#endDate").removeClass('hide');
		}
	});
</script>