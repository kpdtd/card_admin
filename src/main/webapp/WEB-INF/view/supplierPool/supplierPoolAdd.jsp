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
			<input type="hidden" id="initID" name="id" value="${supplierPool.id}"/>

			<div class="control-group">
				<label class="control-label" for="firstName"><span
						class="required">*</span>选择运营商</label>
				<div class="controls">
					<select id="supplierId" class="m-wrap medium select2"
							name="supplierId"   ${supplierPool==null ? "":"disabled"}>
						<option value=""></option>
						<c:forEach var="supplier" items="${supplierList}">
							<c:choose>
								<c:when test="${supplier.id == supplierPool.supplierId}">
									<option value="${supplier.id}" selected="selected">${supplier.company}</option>
								</c:when>
								<c:otherwise>
									<option value="${supplier.id}">${supplier.company}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>套餐名称</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="poolName" 
						 value="${supplierPool.poolName}">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>流量池大小</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="poolValue" 
						 value="${supplierPool.poolValue}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName"><span class="required">*</span>预警阀值</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="threshold" 
						 value="${supplierPool.threshold}">
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
	$('#supplierId').select2({
		placeholder: "运营商名称",
		allowClear: true
	});
	$("#submit_btn").click(function(){
		var id = $("#initID").val();
		if(id != "" && id != null){
			$("#form_sample_1").attr("action","./addSupplierPool");
		}else{
			$("#form_sample_1").attr("action","./addSupplierPool");
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
			"supplierId":{
				required:true
			},
			"poolName" : {
				required : true
			},
			"poolValue" : {
				required : true
			},
			"threshold" : {
				required : true
			}
		}
	});
</script>