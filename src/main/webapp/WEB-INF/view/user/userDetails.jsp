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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="modal-body" data-width="835">
    <div class="portlet box blue">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>用户详情信息</div>
            <div class="tools">
                <%-- <a href="javascript:;" class="collapse"></a>
                 <a href="#portlet-config" data-toggle="modal" class="config"></a>
                 <a href="javascript:;" class="reload hidden-phone"></a>
                 <a href="javascript:;" class="remove hidden-phone"></a>--%>
            </div>
        </div>
        <div class="portlet-body">
            <div class="portlet-body">
                <div class="alert alert-block alert-error fade in">
                    <button type="button" class="close" data-dismiss="alert"></button>
                    <h4 class="alert-heading">用户基本信息</h4>
                    <label class="control-label">id：${user.id}</label>
                    <label class="control-label">唯一标识：${user.indentity}</label>
                    <label class="control-label">卡ID：${user.cardId}</label>
                    <label class="control-label">用户名：${user.username}</label>
                    <label class="control-label">手机号：${user.phone}</label>
                    <label class="control-label">微信OPENID：${user.wxOpenid}</label>
                    <label class="control-label">渠道：${user.channelName}</label>
                    <label class="control-label">创建时间：<fmt:formatDate type="date" value="${user.createTime}"
                                                                      dateStyle="default"
                                                                      pattern="yyyy-MM-dd HH:mm:ss"/></label>
                </div>
                <div class="alert alert-block alert-warning fade in">
                    <button type="button" class="close" data-dismiss="alert"></button>
                    <h4 class="alert-heading">用户流量卡信息</h4>
                    <label class="control-label">ICCID：${card.iccid}</label>
                    <label class="control-label">MSISDN：${card.msisdn}</label>
                    <label class="control-label">IMSI：${card.imsi}</label>
                    <label class="control-label">运营商：${supplier.company}</label>
                    <label class="control-label">归属方：${cardOwner.company}</label>
                    <c:if test="${card.cardState==0}">
                        <label class="control-label">状态：白卡</label>
                    </c:if>
                    <c:if test="${card.cardState==1}">
                        <label class="control-label">状态：测试期</label>
                    </c:if>
                    <c:if test="${card.cardState==2}">
                        <label class="control-label">状态：沉默期</label>
                    </c:if>
                    <c:if test="${card.cardState==3}">
                        <label class="control-label">状态：正使用</label>
                    </c:if>
                    <c:if test="${card.cardState==4}">
                        <label class="control-label">状态：停机</label>
                    </c:if>
                    <c:if test="${card.cardState==5}">
                        <label class="control-label">状态：销户</label>
                    </c:if>
                    <c:if test="${card.cardState==6}">
                        <label class="control-label">状态：预约销户</label>
                    </c:if>
                    <label class="control-label">激活时间：<fmt:formatDate type="date" value="${card.activationTime}"
                                                                      dateStyle="default"
                                                                      pattern="yyyy-MM-dd HH:mm:ss"/></label>
                    <label class="control-label">入库时间：<fmt:formatDate type="date" value="${card.createTime}"
                                                                      dateStyle="default"
                                                                      pattern="yyyy-MM-dd HH:mm:ss"/></label>

                </div>
                <div class="alert alert-block alert-info fade in">
                    <button type="button" class="close" data-dismiss="alert"></button>
                    <h4 class="alert-heading">用户套餐账户信息</h4>
                    <label class="control-label">当前套餐：${using.name}</label>
                    <label class="control-label">下月生效套餐：${next.name}</label>
                    <label class="control-label">用户账户(分)：${userAccount.primaryAccount}</label>
                    <label class="control-label">用户子账户(分)：${userAccount.subAccount}</label>
                    <label class="control-label">用户会员等级：${userAccount.membership}</label>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn">关闭</button>
    </div>
</div>