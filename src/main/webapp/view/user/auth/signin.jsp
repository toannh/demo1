<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a><span class="tree-normal"></span><a href="${baseUrl}">ChợĐiệnTử</a><span class="tree-before"></span><a class="last-item" href="${baseUrl}/user/signin.html">Đăng nhập</a><span class="tree-after"></span>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <div class="form-user"> 
            <div class="fu-title" onclick="facebookClient.init()" >Đăng nhập</div>
            <form method="POST">
                <div class="form-horizontal">
                    <c:if test="${error != null}"> 
                        <div class="form-group has-error">
                            <div class="col-sm-8">
                                <div class="help-block">${error}</div>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Tài khoản:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" value="${username}" id="username" name="username"  placeholder="Sử dụng Tên đăng nhập hoặc Email để đăng nhập" />
                            <!--<div class="help-block">Tài khoản bắt buộc phải nhập.</div>-->
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Mật khẩu:</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control"  id="password" name="password" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8 col-sm-offset-2">
                            <div class="checkbox">
                                <label><input type="checkbox" name="checkCookie"> Tự động đăng nhập</label>
                                &nbsp;&nbsp;|&nbsp;&nbsp;<a href="${baseUrl}/user/forgot.html"><b>Quên mật khẩu?</b></a>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8 col-sm-offset-2">
                            <input name="" type="submit" class="btn btn-danger" value="Đăng nhập" />
                        </div>
                    </div>
                </div><!-- /form-horizontal -->
            </form>
            <div class="login-other">
                Bạn có thể đăng nhập với:
                <a href="${baseUrl}/user/sso.html?provider=facebook"><span class="icon-facebooklogin"></span></a>
                <a href="${baseUrl}/user/sso.html?provider=yahoo"><span class="icon-yahoologin"></span></a>
                <a href="${baseUrl}/user/sso.html?provider=googleplus"><span class="icon-googlelogin"></span></a>
            </div>
        </div><!-- /form-user -->
        <div class="clearfix"></div>
    </div><!-- bground -->
</div><!-- container -->