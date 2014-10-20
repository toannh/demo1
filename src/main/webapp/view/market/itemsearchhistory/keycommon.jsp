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
            <a class="home-button" href="#"></a><span class="tree-before"></span><a class="last-item" href="${baseUrl}/tim-kiem-pho-bien.html">Từ khóa phổ biến</a><span class="tree-after"></span>
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
                            <li><a href="${baseUrl}/lich-su-tim-kiem.html">Lịch sử tìm kiếm</a></li>
                            <li class="active"><a href="${baseUrl}/tim-kiem-pho-bien.html">Từ khóa phổ biến</a></li>
                                <c:if test="${viewer.user != null}">
                                <li><a href="${baseUrl}/lich-su-tim-kiem-cua-ban.html">Lịch sử tìm kiếm của bạn</a></li>
                                </c:if>
                        </ul>
                    </div><!-- mbox-title -->
                    <div class="mbox-content">
                        <c:if test="${dataPage.dataCount > 0}">
                            <div class="box-control">
                                <label>Hiện <b>1-${pageSize > 0?pageSize:dataPage.dataCount}</b> trong <b>${dataPage.dataCount}</b> từ khoá theo:</label>
                                <ul>
                                    <li <c:if test="${pageSize == 100}">class="active"</c:if>><a href="${baseUrl}/tim-kiem-pho-bien.html?pageSize=100">100</a></li>
                                    <li <c:if test="${pageSize == 200}">class="active"</c:if>><a href="${baseUrl}/tim-kiem-pho-bien.html?pageSize=200">200</a></li>
                                    <li <c:if test="${pageSize == 500}">class="active"</c:if>><a href="${baseUrl}/tim-kiem-pho-bien.html?pageSize=500">500</a></li>
                                    <li <c:if test="${pageSize == 0}">class="active"</c:if>><a href="${baseUrl}/tim-kiem-pho-bien.html?pageSize=0">Tất cả</a></li>
                                    </ul>
                                </div><!--box-control-->
                        </c:if>
                        <div class="keyword-common">
                            <ul>
                                <c:if test="${dataPage.dataCount <= 0}">
                                    <span class="text-danger"> Không có từ khóa nào được tìm kiếm phổ biến!</span>
                                </c:if>
                                <c:if test="${dataPage.dataCount > 0}">
                                    <c:forEach var="keyword" items="${dataPage.data}" varStatus="stt">
                                        <li><a href="${keyword.url}" target="_blank">${stt.index +1}<span>${keyword.keyword}</span></a></li>
                                                </c:forEach>
                                            </c:if>
                            </ul>
                        </div><!--keyword-common-->
                        <div class="box-bottom">
                            <ul class="pagination">
                                <c:if test="${dataPage.pageIndex > 3}"><li><a href="?page=1"><<</a></li></c:if>
                                <c:if test="${dataPage.pageIndex > 2}"><li><a href="?page=${dataPage.pageIndex}" ><</a></li></c:if>
                                <c:if test="${dataPage.pageIndex > 3}"><li><a>...</a></li></c:if>
                                <c:if test="${dataPage.pageIndex >= 3}"><li><a href="?page=${dataPage.pageIndex-2}">${dataPage.pageIndex-2}</a></li></c:if>
                                <c:if test="${dataPage.pageIndex >= 2}"><li><a href="?page=${dataPage.pageIndex-1}" >${dataPage.pageIndex-1}</a></li></c:if>
                                <c:if test="${dataPage.pageIndex >= 1}"><li><a href="?page=${dataPage.pageIndex}">${dataPage.pageIndex}</a></li></c:if>
                                <li class="active"><a>${dataPage.pageIndex + 1}</a></li>
                                <c:if test="${dataPage.pageCount - dataPage.pageIndex >= 2}"><li><a href="?page=${dataPage.pageIndex+2}">${dataPage.pageIndex+2}</a></li></c:if>
                                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 3}"><li><a href="?page=${dataPage.pageIndex+3}" >${dataPage.pageIndex+3}</a></li></c:if>
                                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 4}"><li><a >...</a></li></c:if>
                                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="?page=${dataPage.pageIndex+2}" >></a></li></c:if>
                                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="?page=${dataPage.pageCount}" >>></a></li></c:if>
                            </ul>
                        </div><!--box-bottom-->
                    </div><!-- mbox-content -->
                </div><!-- mbox -->
            </div><!-- main -->
            <div class="clearfix"></div>
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
    <div class="internal-text">
        <h1>Tốp từ khóa tìm kiếm nhiều nhất tại Chợ Điện Tử - eBay Việt Nam.</h1>
        <!--<h2>Lịch sử tìm kiêm sản phẩm tại chodientu.vn</h2>-->
    </div><!-- /internal-text -->
</div>