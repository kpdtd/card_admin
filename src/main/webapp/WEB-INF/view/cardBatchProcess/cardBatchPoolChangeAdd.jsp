<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<form id="form_sample_1" action="#" class="form-horizontal dialog" enctype="multipart/form-data"
			onsubmit="return false;">

			<div class="control-group">
				<label class="control-label" for="firstName">
					<span class="required">*</span> 流量池/套餐
				</label>
				<div class="controls">
					<select id="supplierPoolId" class="m-wrap medium select2" name="supplierPoolId">
						<c:forEach var="supplierPool" items="${supplierPoolList}">
							<option value="${supplierPool.id}">${supplierPool.poolName}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<!-- 这里上传excel文件  -->
			<div class="control-group">
				<label class="control-label">上传XLSX</label>
				<div class="controls">
					<div class="fileupload fileupload-new" data-provides="fileupload">
						<div class="fileupload-preview fileupload-exists thumbnail"
							style="max-width: 100px; max-height: 120px; line-height: 20px;"></div>
						<div>
							<span class="btn btn-file"> <span class="fileupload-new">本地上传</span> <span class="fileupload-exists">更改</span>
								<input type="file" class="default" name="file" required="required" />
							</span>
							<a href="#" class="btn fileupload-exists" data-dismiss="fileupload">删除</a>
						</div>
					</div>
					<span class="help-block">注意上传文件格式必须正确</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="firstName">
					<span class="required">*</span> 备注
				</label>
				<div class="controls">
					<textarea class="medium m-wrap" rows="3" name='comment' maxlength='80'></textarea>
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
	$("#submit_btn").click(function() {
		$("#form_sample_1").attr("action", "./batchPoolChange");
		if ($('#form_sample_1').valid()) {
			App.Ajax.submit('form_sample_1', {
				fn : function(json) {
					App.Tables.refresh('portlet');
				}
			});
		}
	});
	App.validate('form_sample_1', {
		rules : {
			"company" : {
				required : true
			}
		}
	});
</script>