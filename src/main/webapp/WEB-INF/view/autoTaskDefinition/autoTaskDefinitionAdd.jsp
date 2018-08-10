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
		<form id="form_sample_1" action="#" class="form-horizontal dialog" onsubmit="return false;">
			<input type="hidden" id="initID" name="id" value="${autoTaskDefinition.id}" />

			<div class="control-group">
				<label class="control-label" for="firstName">
					<span class="required">*</span>任务名称
				</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="taskName" value="${	autoTaskDefinition.taskName}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">
					任务描述
				</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="taskDesc" value="${autoTaskDefinition.taskDesc}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">
					<span class="required">*</span>任务类定义
				</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="taskExecLogic" value="${autoTaskDefinition.taskExecLogic}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">
					<span class="required">*</span>时间表达式
				</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="taskTimeCron" value="${autoTaskDefinition.taskTimeCron}">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="firstName">
					通知方式
				</label>
				<div class="controls">
					<label class="radio">
						<input type="radio" name=noticeType id="noticeType" <c:if test="${autoTaskDefinition.noticeType == 0 }"> checked</c:if>  value="0" />
						不通知
					</label>
					<label class="radio">
						<input type="radio" name=noticeType id="noticeType" <c:if test="${autoTaskDefinition.noticeType == 1 }"> checked</c:if>  value="1" />
						邮件通知
					</label>
					<label class="radio">
						<input type="radio" name=noticeType id="noticeType" <c:if test="${autoTaskDefinition.noticeType == 2 }"> checked</c:if> value="2" />
						短信通知
					</label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="firstName">
					通知人
				</label>
				<div class="controls">
					<input type="text" class="m-wrap span10" name="noticePerson" value="${	autoTaskDefinition.noticePerson}" placeholder="填入正确的邮箱地址或手机号，多人用半角逗号分隔">
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
		var id = $("#initID").val();
		if (id != "" && id != null) {
			$("#form_sample_1").attr("action", "./addAutoTaskDefinition");
		} else {
			$("#form_sample_1").attr("action", "./addAutoTaskDefinition");
		}
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
			"taskName" : {
				required : true
			},
			"taskExecLogic" : {
				required : true
			},
			"taskTimeCron" : {
				required : true
			}
		}
	});
</script>