<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="url" uri="http://chodientu.vn/url" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <div class="container">
<!--        <span class="header-bg1"></span>
        <span class="header-bg2"></span>-->
        <a class="logo" href="${baseUrl}" title="${title}" ><img src="${staticUrl}/market/images/logo.png" alt="logo" /></a>
        <div class="box-search">
            <div class="search-choose">
                <span class="search-menu"></span>
                <span class="icon-arrowdown"></span>
                <div class="popmenu">
                    <span class="popmenu-bullet"></span>
                    <ul class="box-menu navbar-search-cat">
                        <li>
                            <a href="javascript:search.changeCat();" title="Danh mục sản phẩm" >Tất cả danh mục</a>
                        </li>
                        <c:forEach var="item" items="${categories}" >
                            <li for_cate="${item.id}" >
                                <a href="javascript:search.changeCat('${item.id}');" title="${item.name}" >${item.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div><!-- /popmenu -->
            </div>
            <div class="search-inner"><input type="text" name="headsearch" class="text navbar-search" placeholder="Tìm kiếm, Ví dụ: đầm dự tiệc, iphone 6, ipad, samsung galaxy S5" /></div>
            <input onclick="search.go()" type="button" class="btn-search" value="Tìm kiếm" />
        </div><!-- /box-search -->
        <c:if test="${ushop == null}">
            <a class="open-show-free" href="${baseUrl}/user/open-shop-step1.html">Mở Shop miễn phí</a>
        </c:if>
        <c:if test="${ushop != null}">
            <a class="open-show-free" href="${baseUrl}/${ushop.alias}/" target="_blank">Vào shop</a>
        </c:if>
    </div><!-- /container -->
</div><!-- /header -->