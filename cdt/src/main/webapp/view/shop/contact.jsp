<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-lg-9 col-md-9 col-sm-8 col-xs-12 right-content">
    <ol class="breadcrumb">
        <li><a href="#">Trang chủ</a></li>
        <li><a href="#">${alias}</a></li>
        <li>Liên hệ</li>
    </ol>
    <h2>Liên hệ</h2>

    <div class="box-module">
        <p>Vui lòng điền các thông tin và click vào nút gửi Liên hệ. Chúng tôi sẽ trả lời bạn qua Email hoặc số điện thoại bạn để lại</p>

        <div class="form-horizontal" role="form" id="shopContact">
            <div class="form-group">
                <label for="inputPassword" class="col-sm-2 control-label">Họ tên</label>
                <div class="col-sm-6">
                    <input type="hidden" name="alias" class="form-control" value="${shop.alias}">
                    <input type="text" name="name" class="form-control" id="inputname" placeholder="Họ tên">
                    <span class="small">Các liên hệ sử dụng tên đầy đủ sẽ được ưu tiên hồi đáp</span>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword" class="col-sm-2 control-label">Số điện thoại</label>
                <div class="col-sm-6">
                    <input type="text" name="phone" class="form-control" id="inputphone" placeholder="Điện thoại">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-6">
                    <input type="text" name="email" class="form-control" id="inputphone" placeholder="Email">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword" class="col-sm-2 control-label">Nội dung</label>
                <div class="col-sm-6">
                    <textarea rows="5" name="content" class="form-control col-lg-12 col-xs-12"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">&nbsp;</label>
                <div class="col-sm-6">
                    <button class="btn btn-primary" onclick="shopcontact.send();"><span class="fa fa-mail-reply-all"></span> Gửi</button>
                </div>
            </div>
        </div>

    </div>
</div>
