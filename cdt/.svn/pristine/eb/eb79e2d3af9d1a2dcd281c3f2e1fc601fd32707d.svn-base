<%-- 
    Document   : create.jsp
    Created on : Jul 14, 2014, 8:37:25 AM
    Author     : TheHoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username != null?viewer.user.username:viewer.user.email}</a></li>
        <li class="active">Gửi SMS</li>
    </ol>
    <h1 class="title-pages">SMS Marketing
        <span class="clearfix small">Chỉ với: <span class="clr-red">100 xèng/SMS</span>. Duyệt tin nhắn chậm nhất sau 2h làm việc</span>
    </h1>
    <div class="tabs-content-user">
        <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-soan-va-gui-sms-714567998304.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn soạn và gửi SMS
            </a></div>
        <ul class="tab-title-content">
            <li  class="active">
                <c:if test="${sms == null}">
                    <a href="${baseUrl}/user/create-sms-marketing.html">Soạn tin nhắn SMS</a>
                </c:if>
                <c:if test="${sms != null}">
                    <a href="${baseUrl}/user/create-sms-marketing.html?id=${sms.id}">Sửa tin nhắn SMS</a>
                </c:if>
            </li>
            <li><a href="${baseUrl}/user/sms-marketing.html">Danh sách tin nhắn SMS</a></li>
        </ul>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <input name="id" value="${sms.id}" style="display: none" />
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label"  for="name">Tiêu đề:</label>
                            <div class="col-sm-10">
                                <input type="text" name="name" class="form-control" placeholder="Tiêu đề chỉ để người bán quản lý" value="${sms.name}">
                                <span class="help-block" for="name"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Danh sách khách hàng:</label>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-default customers" data-toggle="modal" data-target="#ModalNormal" onclick="sellercustomer.listcustomerselect()">Chọn danh sách khách hàng</button>     
                            </div>
                            <div class="help-block customers_error" style="display: none"></div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="sendTime">Thời điểm gửi SMS:</label>
                            <div class="col-sm-8">
                                <div class="checkbox-inline" style="padding-top:0px; margin-right:15px;">
                                    <label class="control-label"><input type="checkbox" name="checkTime"> Gửi ngay</label>
                                </div>
                                <div class="date-picker-block" style="display:inline-block; min-width:255px;">
                                    <input type="hidden" name="sendTime" class="form-control timesend" value="${sms.sendTime}" placeholder="Đặt lịch gửi">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </div>                                      
                                <span class="help-block" for="sendTime"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label" for="content">Nội dung sms</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="content" rows="5" onkeyup="email.countCharacters()" onkeypress="email.countCharacters()" onkeydown="email.countCharacters()">${sms.content}</textarea>
                                <span class="pull-right small mgt-15">Tối đa 130 ký tự - <span class="clr-org"><strong>Còn <span class="countresult">130/130</span></strong></span> ký tự</span>
                                <span class="help-block" for="content"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">&nbsp;</label>
                            <div class="col-sm-10">
                                <c:if test="${sms == null}">
                                    <button class="btn btn-lg btn-danger" onclick="email.createSms();">Lưu lại</button>
                                </c:if>
                                <c:if test="${sms != null}">
                                    <button class="btn btn-lg btn-danger" onclick="email.editSaveSmsMarketing();">Lưu lại</button>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12"><strong class="clr-red">Lưu ý:</strong> Tin nhắn của bạn sẽ được gửi đi sau khi CĐT đã kiểm duyệt" Sau nút lưu lại</div>
                        </div>
                    </div>
                </div>                            
            </div>                     
        </div>   
    </div>
</div>