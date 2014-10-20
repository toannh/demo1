<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld" %>
<div class="container">
    <div class="tree-main">
        <div class="menu-click">
            <jsp:include page="/view/market/widget/alias.jsp" />
        </div><!--menu-click-->
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a><span class="tree-before"></span><a class="last-item" href="${baseUrl}/lich-su-tim-kiem.html">Lịch sử tìm kiếm</a><span class="tree-after"></span>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <div class="bg-white">
            <div class="sidebar">
                <div class="pbox search-winget">
                    <div class="pbox-title"><label class="lb-name">Sản phẩm vừa xem</label></div>
                    <div class="pbox-content">
                        <ul class="search-near">
                            <li class="active">
                                <div class="search-count">
                                    <c:forEach items="${itemRandom}" var="it" >
                                        <div class="grid">
                                            <div class="img">
                                                <a href="${url:item(it.id,it.name)}"><img src="${(it.images != null && fn:length(it.images) >0)?it.images[0]:staticUrl.concat('/market/images/no-image-product.png')}" /></a>
                                            </div>
                                            <div class="g-content">
                                                <div class="g-row"><a class="g-title" href="${url:item(it.id,it.name)}">${it.name}</a></div>
                                                    <c:if test="${(text:sellPrice(it.sellPrice,it.discount,it.discountPrice,it.discountPercent)) != '0'}">
                                                    <div class="g-row"><span class="g-cost">${text:sellPrice(it.sellPrice,it.discount,it.discountPrice,it.discountPercent)} VNĐ</span></div>
                                                </c:if>
                                            </div>
                                        </div><!--grid-->
                                    </c:forEach>
                                </div><!--search-count-->
                            </li>
                        </ul>
                    </div><!--pbox-content-->
                </div><!--pbox-->
            </div><!-- sidebar -->
            <div class="main">
                <div class="mbox">
                    <div class="mbox-title full-tab">
                        <ul>
                            <li class="active"><a href="${baseUrl}/lich-su-tim-kiem.html">Lịch sử tìm kiếm</a></li>
                            <li><a href="${baseUrl}/tim-kiem-pho-bien.html">Từ khóa phổ biến</a></li>
                                <c:if test="${viewer.user != null}">
                                <li><a href="${baseUrl}/lich-su-tim-kiem-cua-ban.html">Lịch sử tìm kiếm của bạn</a></li>
                                </c:if>
                        </ul>
                    </div><!-- mbox-title -->
                    <div class="mbox-content">
                        <div class="search-result" data-rel="counttagSearch"></div>
                        <div class="search-result"> <button type="button" class="btn btn-danger" onclick="searchhistory.del();" >
                                <span class="glyphicon glyphicon-trash"></span> Xóa</button></div>
                        <div class="search-content" data-rel="tagSearch" >
                        </div><!--search-content-->
                    </div><!-- mbox-content -->
                </div><!-- mbox -->
            </div><!-- main -->
            <div class="clearfix"></div>
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
    <div class="internal-text">
        <h1>Lịch sử tìm kiếm sản phẩm tại Chodientu.vn</h1>
        <!--<h2>Lịch sử tìm kiêm sản phẩm tại chodientu.vn</h2>-->
    </div><!-- /internal-text -->
</div><!-- container -->