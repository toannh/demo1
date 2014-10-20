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
        </div><!-- /tree-view --><div class="bground">
            <div class="bg-white">
            </div><!-- /tree-main -->
            <div class="bground">
                <div class="bg-white">
                    <div class="page-openshop">
                        <div class="openshop-title">Mở shop miễn phí</div>
                        <input style="display: none" name="userId" value="${viewer.user.id}"/>
                        <div class="openshop-step row">
                            <div class="col-sm-4 active">
                                <div class="os-circle">Bước<span>1</span></div>
                                <div class="os-title"><div class="os-cell">Tạo tài khoản trên <span class="text-danger">ChợĐiệnTử</span></div></div>
                            </div><!-- col-sm-4 -->
                            <div class="col-sm-4 active">
                                <div class="os-circle">Bước<span>2</span></div>
                                <div class="os-title"><div class="os-cell">Chứng thực tài khoản</div></div>
                            </div><!-- col-sm-4 -->
                            <div class="col-sm-4">
                                <div class="os-circle">Bước<span>3</span></div>
                                <div class="os-title"><div class="os-cell">Liên kết với<br />Ngân Lượng và Shipchung</div></div>
                            </div><!-- col-sm-4 -->
                        </div><!-- openshop-step -->
                        <c:if test="${viewer.user.phone != null && viewer.user.phone !='' && viewer.user.phoneVerified}">
                            <div class="openshop-content step2-active">
                                <span class="oc-bullet"></span>
                                <div class="step-content">
                                    <div class="sf-row">Tài khoản của bạn đã được chứng thực số điện thoại</div>
                                    <span class="r-time" ></span>
                                    <div class="sf-row">
                                        <a class="btn-remove" href="${baseUrl}"><span class="glyphicon glyphicon glyphicon-remove"></span> Huỷ</a>
                                        <a href="${baseUrl}/user/open-shop-step3.html" class="btn btn-danger btn-lg">Tiếp tục<span class="glyphicon glyphicon-circle-arrow-right"></span></a>
                                    </div>
                                </div>
                            </div><!-- openshop-content -->
                        </c:if>
                        <c:if test="${viewer.user.phone != null && viewer.user.phone !='' && !viewer.user.phoneVerified}">
                            <div class="openshop-content step2-active">
                                <span class="oc-bullet"></span>
                                <div class="step-content">
                                    <div class="sf-row">
                                        Tài khoản của bạn chưa được kích hoạt số điện thoại. Bạn phải kích hoạt số điện thoại để bắt đầu bán hàng trên ChợĐiệnTử
                                        <br>
                                        (Soạn tin <b class="text-danger">CDT XM ${viewer.user.id}</b> gửi tới <b class="text-danger">8255</b> để xác minh. Phí <b>2.000đ/tin nhắn</b>) 
                                    </div>
                                    <div class="sf-row">
                                        <a class="btn-remove" href="${baseUrl}"><span class="glyphicon glyphicon glyphicon-remove"></span> Huỷ</a>
                                        <a href="#" class="btn btn-danger btn-lg disabled">Tiếp tục<span class="glyphicon glyphicon-circle-arrow-right"></span></a>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${viewer.user.phone == null || viewer.user.phone == ''}">
                            <div class="openshop-content step2-active">
                                <span class="oc-bullet"></span>
                                <div class="step-content">
                                    <div class="sf-row">Tài khoản của bạn chưa cập nhật số điện thoại, Hãy cập nhật số điện thoại để hoàn thành quy trình mở shop!</div>
                                    <div class="form form-inline">
                                        <div class="form-group">
                                            <label><b>Số điện thoại của bạn:</b></label>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="phone">
                                        </div>
                                        <div class="form-group">
                                            <button type="button" class="btn btn-default" onclick="shop.updatePhone();">Cập nhật</button>
                                        </div>
                                        <div class="help-block" id="err_phone" style="color: red"></div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <div class="sf-row"><input name="stepcheck" type="checkbox" disabled="true" checked="" value="0" class="step-chk" >Tôi đã đọc, hiểu và chấp nhận các <a href="#">điều kiện mở Shop bán hàng tại ChợĐiệnTử</a></div>
                    </div><!-- page-openshop -->
                    <div class="clearfix"></div>
                </div><!-- bg-white -->
                <div class="clearfix"></div>
            </div><!-- bground -->
        </div>
    </div>
</div>