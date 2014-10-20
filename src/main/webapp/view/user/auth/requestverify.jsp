<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a><span class="tree-normal"></span><a href="${baseUrl}">ChợĐiệnTử</a><span class="tree-before"></span><a class="last-item" href="${baseUrl}/user/signup.html">Đăng ký tài khoản</a><span class="tree-after"></span>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <form id="loginForm"  method="POST">
            <div class="form-user">
                <div class="fu-title">Gửi yêu cầu kích hoạt email</div>
                <div class="form-horizontal">
                    <c:if test="${error != null}"> 
                        <div class="form-group has-error">
                            <div class="col-sm-8">
                                <div class="help-block">${error}</div>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Email:<span class="redfont">*</span></label>
                        <div class="col-sm-7">
                            <input name="email" class="form-control" placeholder="mailcuaban@gmail.com" value="${email}" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Mật khẩu:<span class="redfont">*</span></label>
                        <div class="col-sm-7">
                            <input name="password" type="password" class="form-control" placeholder="**********" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-7 col-sm-offset-3">
                            <div class="box-captcha"><img src="" alt="captcha" style="height: 50px;" /></div>
                            <input type="button" value="Hình ảnh khác" name="yt0" onclick="auth.reloadCaptcha()" class="btnnewimg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Mã xác nhận:<span class="redfont">*</span></label>
                        <div class="col-sm-7">
                            <input name="captcha" class="form-control" placeholder="Nhập mã xác nhận"  />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-7 col-sm-offset-3">
                            <input name="" type="submit" class="btn btn-danger" value="Yêu cầu" />
                        </div>
                    </div>
                </div><!-- /form-horizontal -->
            </div><!-- /form-user -->
        </form>
        <div class="clearfix"></div>
    </div><!-- bground -->
</div><!-- container -->