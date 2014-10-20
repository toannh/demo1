<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a><span class="tree-normal"></span><a href="${baseUrl}/user/open-shop-step1">Mở shop</a>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <div class="bg-white">
            <div class="page-openshop">
                <div class="openshop-title">Mở shop thành công</div>
                <div class="openshop-finish">
                    <div class="row">
                        <div class="grid">
                            <div class="img"><img src="${baseUrl}/static/user/images/icons/icon-bigshop.png" alt="shop"></div>
                            <div class="g-content">
                                <div class="g-row">Chúc mừng bạn đã <strong>hoàn thành</strong> xong các bước <b class="text-danger">mở shop!</b></div>
                                <div class="g-shop">
                                    <div class="g-row">
                                        <b>Tên shop:&nbsp;</b>
                                        ${shop.title}
                                    </div>
                                    <div class="g-row">
                                        <b>Link shop:&nbsp;</b>
                                        ${baseUrl}/${shop.alias}
                                    </div>
                                </div>
                            </div>
                        </div><!-- /grid-->
                    </div><!-- /row-->
                    <div class="sf-row text-center">
                        <a href="${baseUrl}/${shop.alias}" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>Vào shop</a>
                        <a href="${baseUrl}/user/cau-hinh-shop-step1.html" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-cog"></span>Quản trị shop</a>
                    </div>
                </div>
            </div><!-- page-openshop -->
            <div class="clearfix"></div>
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
</div><!-- container -->