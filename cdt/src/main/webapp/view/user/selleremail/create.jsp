<%-- 
    Document   : create.jsp
    Created on : Jul 14, 2014, 8:37:25 AM
    Author     : PhucTd
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username==null?viewer.user.email:viewer.user.username}</a></li>
        <li class="active">${param.edit==null?'Gửi':'Sửa'} E-Mail</li>
    </ol>
    <h1 class="title-pages">E-Mail Marketing
        <span class="clearfix small">Chỉ với: <span class="clr-red">20 xèng/Email</span>. Duyệt email chậm nhất sau 2h làm việc</span>
    </h1>
    <div class="tabs-content-user">
        <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-soan-va-gui-email-marketing-55452363106.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn soạn và gửi email marketing
            </a></div>
        <ul class="tab-title-content">
            <li class="active"><a href="${baseUrl}/user/create-email-marketing.html">${param.edit==null?'Soạn':'Sửa'} email marketing</a></li>
            <li><a href="${baseUrl}/user/email-marketing.html">Danh sách email marketing</a></li>
        </ul>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Tiêu đề:</label>
                            <div class="col-sm-10">
                                <input type="text" name="name" value="${email.name}" placeholder="Tiêu đề gửi email" class="form-control" >
                                <div class="help-block"></div>
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
                            <label class="col-sm-2 control-label">Thời điểm gửi Email:</label>
                            <div class="col-sm-5">
                                <div class="date-picker-block">
                                    <input type="hidden" readonly="readonly" value="${email.sendTime}" name="sendDate" class="form-control hasDatepicker" placeholder="Thời điểm gửi email" />
                                    <span class="glyphicon glyphicon-calendar"></span>   
                                    <div class="help-block"></div>
                                </div>                                      
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Mẫu Email:</label>
                            <div class="col-sm-5">
                                <select class="form-control" name="template">
                                    <option value="TEMPLATE_ONE" ${email.template=='TEMPLATE_ONE'?'selected':''}>Giao diện 1</option>
<!--                                    <option value="TEMPLATE_TWO" ${email.template=='TEMPLATE_TWO'?'selected':''}>Giao diện 2</option>
                                    <option value="TEMPLATE_THREE" ${email.template=='TEMPLATE_THREE'?'selected':''}>Giao diện 3</option>-->
                                </select>
                                <div class="help-block"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Nội dung Email:</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" placeholder="Nội dung email" id="txt_content" name="content" rows="5">${email.content}</textarea>                                    
                                <div class="help-block"></div>
                                <a href="javascript:;" onclick="email.preview()" class="pull-right">Xem trước nội dung gửi đi</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Nhập Email gửi thử</label>
                            <div class="col-sm-8">
                                <input type="text" placeholder="Nhập email gửi thử" name="emailTry" class="form-control">
                                <div class="help-block"></div>
                            </div>
                            <div class="col-sm-2 reset-padding"><button type="button" onclick="email.trySendMail()" class="btn btn-primary btn-block">Gửi thử</button></div>
                        </div>  
                        <div class="form-group">
                            <label class="col-sm-2 control-label">&nbsp;</label>
                            <div class="col-sm-10">
                                <c:if test="${param.edit!=null && email!=null}">
                                    <button class="btn btn-lg btn-danger" onclick="email.updateEmail('${email.id}')">Cập nhật</button>
                                </c:if>
                                <c:if test="${param.edit==null && email==null}">
                                    <button class="btn btn-lg btn-danger" onclick="email.createEmail()">Lưu lại</button> 
                                </c:if>
                                <a href="${baseUrl}/user/email-marketing.html" class="btn-remove"><span class="glyphicon glyphicon glyphicon-remove"></span> Huỷ</a> 
                            </div>
                        </div>  
                    </div>
                </div>                            
            </div>                     
        </div>   
    </div>
</div>