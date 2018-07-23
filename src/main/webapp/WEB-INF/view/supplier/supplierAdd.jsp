<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<style>
    .radio input[type="radio"], .checkbox input[type="checkbox"] {
        float: left;
        margin: 4px 4px 0;
        margin-left: 0px;
    }
</style>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div class="modal-body" data-width="720">
    <div class="portlet-body form row-fluid">
        <form id="form_sample_1" action="#" class="form-horizontal dialog" enctype="multipart/form-data"
              onsubmit="return false;">
            <input type="hidden" id="initID" name="id" value="${supplier.id }"/>
            <div class="control-group">
                <label class="control-label" for="firstName"><span class="required">*</span>公司名称</label>
                <div class="controls">
                    <input type="text" class="m-wrap span10" name="company"
                           value="${supplier.company}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="firstName">签名密钥</label>
                <div class="controls">
                    <input type="text" class="m-wrap span10" name="signKey"
                           value="${supplier.signKey}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="firstName">消息密钥</label>
                <div class="controls">
                    <input type="text" class="m-wrap span10" name="invokeToken"
                           value="${supplier.invokeToken}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="firstName"><span class="required">*</span>企业编码</label>
                <div class="controls">
                    <input type="text" class="m-wrap span10" name="ecCode" ${supplier==null ? "":"readonly"}
                            value="${supplier.ecCode}">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label"><span class="required">*</span>供应商身份类型</label>
                <div class="controls">
                    <c:forEach var="providerType" items="${providerTypeList}">
                        <c:choose>
                            <c:when test="${providerType.id == supplier.identity }">
                                <label class="radio">
                                    <input type="radio" name=identity id="identity" checked
                                           value="${providerType.id }"/>${providerType.name }
                                </label>
                            </c:when>
                            <c:otherwise>
                                <label class="radio">
                                    <input type="radio" id="identity" name="identity"
                                           value="${providerType.id }"/>${providerType.name }
                                </label>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="firstName"><span class="required">*</span>联系人姓名</label>
                <div class="controls">
                    <input type="text" class="m-wrap span10" name="linker"
                           value="${supplier.linker}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="firstName"><span class="required">*</span>手机号</label>
                <div class="controls">
                    <input type="text" class="m-wrap span10" name="phone"
                           value="${supplier.phone}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="firstName">邮箱</label>
                <div class="controls">
                    <input type="text" class="m-wrap span10" name="mail"
                           value="${supplier.mail}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label"><span class="required">*</span>状态</label>
                <div class="controls">
                    <c:forEach var="state" items="${stateList}">
                        <c:choose>
                            <c:when test="${state.id == supplier.state }">
                                <label class="radio"><input type="radio" name=state id="state"
                                                            checked value="${state.id }"/>${state.name }</label>
                            </c:when>
                            <c:otherwise>
                                <label class="radio"><input type="radio" id="state"
                                                            name="state" value="${state.id }"/>${state.name }</label>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="firstName">账号及开票信息</label>
                <div class="controls">
                    <input type="text" class="m-wrap span10" name="bankInfo" maxlength="16"
                           value="${supplier.bankInfo}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="firstName"><span class="required">*</span>所属商务</label>
                <div class="controls">
                    <input type="text" class="m-wrap span10" name="business"
                           value="${supplier.business}">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="firstName">企业扩展信息(技术填写)</label>
                <div class="controls">
                    <%--<input type="text" class="m-wrap span10" name="ecExtensionInfo"--%>
                    <%--value="${supplier.ecExtensionInfo}">--%>
                    <textarea type="text" class="m-wrap span10"
                              name="ecExtensionInfo">${supplier.ecExtensionInfo}</textarea>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">合同上传</label>
                <div class="controls">
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                        <div class="fileupload-new thumbnail" style="width: 100px; height: 100px;">
                            <img id="attachment"
                                 src="${supplier.attachment != null && supplier.attachment != '' ? supplier.attachment : '../media/image/default-img.png'}"
                                 alt=""/>
                        </div>
                        <div class="fileupload-preview fileupload-exists thumbnail"
                             style="max-width: 100px; max-height: 120px; line-height: 20px;"></div>
                        <div>
							<span class="btn btn-file">
								<span class="fileupload-new">本地上传</span>
								<span class="fileupload-exists">更改</span>
								<input type="file" class="default" name="attachmentFile"/>
							</span>
                            <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">删除</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="firstName">企业备注信息</label>
                <div class="controls">
                    <%--<input type="text" class="m-wrap span10" name="info"--%>
                    <%--value="${supplier.ecExtensionInfo}">--%>
                    <textarea type="text" class="m-wrap span10" name="info">${supplier.ecExtensionInfo}</textarea>
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
    $("#submit_btn").click(function () {
        var id = $("#initID").val();
        if (id != "" && id != null) {
            $("#form_sample_1").attr("action", "./addSupplier");
        } else {
            $("#form_sample_1").attr("action", "./addSupplier");
        }
        if ($('#form_sample_1').valid()) {
            App.Ajax.submit('form_sample_1', {
                fn: function (json) {
                    App.Tables.refresh('portlet');
                }
            });
        }
    });
    App.validate('form_sample_1', {
        rules: {
            "company": {
                required: true
            },
            "ecCode": {
                required: true
            },
            "identity": {
                required: true
            },
            "linker": {
                required: true
            },
            "phone": {
                required: true
            },
            "business": {
                required: true
            },
            "state": {
                required: true
            }
        }
    });
</script>