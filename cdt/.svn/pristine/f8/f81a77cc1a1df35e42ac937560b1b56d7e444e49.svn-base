<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a>
            <span class="tree-normal"></span>
            <a href="${baseUrl}/user/open-shop-step1.html">Mở shop</a>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <div class="bg-white">
            <div class="page-openshop">
                <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-mo-shop-926616779386.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn mở shop</a></div>
                <div class="openshop-title">Mở shop miễn phí</div>
                <div class="openshop-step row">
                    <div class="col-sm-4 active">
                        <div class="os-circle">Bước<span>1</span></div>
                        <div class="os-title"><div class="os-cell">Tạo tài khoản trên <span class="text-danger">ChợĐiệnTử</span></div></div>
                    </div><!-- col-sm-4 -->
                    <div class="col-sm-4">
                        <div class="os-circle">Bước<span>2</span></div>
                        <div class="os-title"><div class="os-cell">Chứng thực tài khoản</div></div>
                    </div><!-- col-sm-4 -->
                    <div class="col-sm-4">
                        <div class="os-circle">Bước<span>3</span></div>
                        <div class="os-title"><div class="os-cell">Liên kết với<br>Ngân Lượng và Shipchung</div></div>
                    </div><!-- col-sm-4 -->
                </div><!-- openshop-step -->
                <div class="openshop-content step1-active">
                    <span class="oc-bullet"></span>
                    <c:if test="${viewer.user == null}">
                        <div class="step-content">
                            <div class="sf-row">Bạn đã có tài khoản trên ChợĐiệnTử, hãy <a href="${baseUrl}/user/signin.html?ref=${baseUrl}/user/open-shop-step1.html">đăng nhập</a> hoặc <a href="${baseUrl}/user/signup.html">đăng ký</a> nếu chưa có!</div>
                        </div>
                    </c:if>
                    <c:if test="${viewer.user != null}">
                        <div class="step-content">
                            <div class="sf-row">
                                Tài khoản trên Chợ Điện Tử của bạn là:  
                                <a href="${baseUrl}/user/profile.html">
                                    ${viewer.user.username==null?viewer.user.email:viewer.user.username}
                                </a>
                            </div>
                            <div class="sf-row"><input name="stepcheck" type="checkbox" value="0" class="step-chk" >Tôi đã đọc, hiểu và chấp nhận các <a href="http://chodientu.vn/tin-tuc/dieu-khoan-tham-gia-ban-hang--265354186533.html">điều kiện mở Shop bán hàng tại ChợĐiệnTử</a></div>
                            <div class="help-block" id="err_check" style="color: red"></div>
                            <div class="sf-row"><a class="btn btn-danger btn-lg" onclick="shop.nextStepOpenShop();">Tiếp tục<span class="glyphicon glyphicon-circle-arrow-right"></span></a></div>
                        </div>
                    </c:if>
                </div><!-- openshop-content -->
            </div><!-- page-openshop -->
            <div class="clearfix"></div>
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
</div>