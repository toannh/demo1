<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://chodientu.vn/url" prefix="url" %>


<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a><span class="tree-normal"></span><a href="${baseUrl}">ChợĐiệnTử</a><span class="tree-before"></span><a class="last-item" href="${baseUrl}/user/signup.html">Đăng ký tài khoản</a><span class="tree-after"></span>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <form:form method="post" enctype="multipart/form-data" action="${baseUrl}/user/signup.html" modelAttribute="signupForm" >
            <div class="form-user">
                <div class="fu-title">Đăng ký tài khoản</div>
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Tên đăng nhập: </label>
                        <div class="col-sm-7">
                            <form:input path="username" cssClass="form-control" placeholder="Tên đăng nhập" />
                            <div class="has-error" >
                                <form:errors element="div" path="username" cssClass="help-block"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Email:<span class="redfont">*</span></label>
                        <div class="col-sm-7">
                            <form:input path="email" cssClass="form-control" placeholder="mailcuaban@gmail.com" />
                            <div class="has-error" >
                                <form:errors element="div" path="email" cssClass="help-block"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Mật khẩu:<span class="redfont">*</span></label>
                        <div class="col-sm-7">
                            <form:password path="password" cssClass="form-control" placeholder="**********" />
                            <div class="has-error" >
                                <form:errors element="div" path="password" cssClass="help-block"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Nhập lại mật khẩu:<span class="redfont">*</span></label>
                        <div class="col-sm-7">
                            <form:password path="confirmPassword" cssClass="form-control" placeholder="**********" />
                            <div class="has-error" >
                                <form:errors element="div" path="confirmPassword" cssClass="help-block"/>
                            </div>
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
                            <form:input path="captcha" cssClass="form-control" placeholder="Nhập mã xác nhận"  />
                            <div class="has-error" >
                                <form:errors element="div" path="captcha" cssClass="help-block"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <div class="checkbox">
                                <form:checkbox path="confirm"/> Tôi đã đọc và đồng ý với <a href="${url:newsDetailUrl(578158007467,'Thỏa thuận người dùng')}" target=_blank>Thỏa ước thành viên</a> của ChợĐiệnTử. 
                            </div>
                            <div class="has-error" >
                                <form:errors element="div" path="confirm" cssClass="help-block"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-7 col-sm-offset-3">
                            <input name="" type="submit" class="btn btn-danger" value="Đăng ký" />
                        </div>
                    </div>
                </div><!-- /form-horizontal -->
            </div><!-- /form-user -->
        </form:form>
        <div class="form-loginlink">
            Nếu bạn đã có tài khoản trên ChợĐiệnTử thì có thể <a href="${baseUrl}/user/signin.html">đăng nhập</a> tại đây!
        </div><!-- /form-loginlink -->
        <div class="clearfix"></div>
    </div><!-- bground -->
</div><!-- container -->