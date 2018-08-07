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
            <input type="hidden" id="initID" name="id" value="${supplierInterfaceItem.id}"/>
            <label class="control-label" for="firstName"><span
                    class="required">*</span>选择运营商</label>
            <div class="controls">
                <select id="supplierId2" class="m-wrap medium select2"
                        name="supplierId">
                    <option value=""></option>
                    <c:forEach var="supplier" items="${supplierList}">
                        <c:choose>
                            <c:when test="${supplier.id == supplierInterfaceItem.supplierId}">
                                <option value="${supplier.id}" selected="selected">${supplier.company}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${supplier.id}">${supplier.company}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="control-group">
                <label class="control-label" for="firstName"><span class="required">*</span> 企业编码</label>
                <div class="controls">
                    <input type="text" class="m-wrap span10" name="ecCode"
                           value="${supplierInterfaceItem.ecCode}">
                </div>
            </div>

            <label class="control-label" for="firstName"><span
                    class="required">*</span>选择接口</label>
            <div class="controls">
                <select id="interfaceId" class="m-wrap medium select2"
                        name="interfaceId">
                    <option value=""></option>
                    <c:forEach var="interfaceItem" items="${interfaceListList}">
                        <c:choose>
                            <c:when test="${interfaceItem.id == supplierInterfaceItem.interfaceId}">
                                <option value="${interfaceItem.id}" selected="selected">${interfaceItem.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${interfaceItem.id}">${interfaceItem.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>

            <div class="control-group">
                <label class="control-label" for="firstName"><span class="required">*</span> 执行类名</label>
                <div class="controls">
                    <input type="text" class="m-wrap span10" name="className"
                           value="${supplierInterfaceItem.className}">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="firstName"><span class="required">*</span> url</label>
                <div class="controls">
                    <input type="text" class="m-wrap span10" name="url"
                           value="${supplierInterfaceItem.url}">
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
    $('#interfaceId').select2({
        placeholder: "选择接口",
        allowClear: true
    });
    $('#supplierId2').select2({
        placeholder: "选择供应商",
        allowClear: true
    });
    $("#submit_btn").click(function () {
        var id = $("#initID").val();
        if (id != "" && id != null) {
            $("#form_sample_1").attr("action", "./addSupplierInterfaceItem");
        } else {
            $("#form_sample_1").attr("action", "./addSupplierInterfaceItem");
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
            "supplierId": {
                required: true
            }, "ecCode": {
                required: true
            }, "interfaceId": {
                required: true
            }, "className": {
                required: true
            }, "url": {
                required: true
            }
        }
    });

</script>