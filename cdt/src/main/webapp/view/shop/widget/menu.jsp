<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="text" uri="http://chodientu.vn/text" %>

<nav class="navbar navbar-shop" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nav-main-menu">
                <span class="sr-only"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="nav-main-menu">
            <ul class="nav navbar-nav">
                <li><a href="${baseUrl}/${shop.alias}/"><span class="glyphicon glyphicon-home"> </span></a></li>
                <li class="active"><a href="${baseUrl}/${shop.alias}/browse.html">Sản phẩm</a></li>
                <li class="dropdown">
                    <a href="${baseUrl}/${shop.alias}/news-allcategory.html" <c:if test="${shopNewsCategories != null && fn:length(shopNewsCategories) > 0}">class="dropdown-toggle" data-toggle="dropdown"</c:if>>Tin tức 
                        <c:if test="${shopNewsCategories != null && fn:length(shopNewsCategories) > 0}"><b class="caret"></b>
                        </c:if>
                    </a>
                    <c:if test="${shopNewsCategories != null && fn:length(shopNewsCategories) > 0}">
                        <ul class="dropdown-menu">
                            <c:forEach var="newsCat" items="${shopNewsCategories}">
                                <li><a href="${baseUrl}/${shop.alias}/news-category/${newsCat.id}/${text:createAlias(newsCat.name)}.html">${newsCat.name}</a></li>
                                <li class="divider"></li>
                                </c:forEach>
                        </ul>
                    </c:if>
                </li>
                <li><a href="${baseUrl}/${shop.alias}/about.html">Giới thiệu</a></li>
                <li><a href="${baseUrl}/${shop.alias}/map.html">Bản đồ</a></li>
                <li><a href="${baseUrl}/${shop.alias}/guide.html">Hướng dẫn</a></li>
                <li><a href="${baseUrl}/${shop.alias}/contact.html">Liên hệ</a></li>
            </ul>
            <c:if test="${viewer.user!=null && viewer.user.id == shop.userId}">
                <ul class="nav navbar-nav pull-right">
                    <li class="dropdown">
                        <a href="${baseUrl}/${shop.alias}/browse.html" class="dropdown-toggle" data-toggle="dropdown"><span class="fa fa-edit"></span> Quản trị <b class="caret"></b></a>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li><a href="${baseUrl}/user/cau-hinh-shop-step1.html">Thông tin cơ bản</a></li>
                            <li class="divider"></li>
                            <li><a href="${baseUrl}/user/cau-hinh-shop-step5.html">Danh mục sản phẩm</a></li>
                            <li class="divider"></li>
                            <li><a href="${baseUrl}/user/cau-hinh-shop-step6.html">Danh mục tin tức</a></li>
                            <li class="divider"></li>
                            <li><a href="${baseUrl}/user/shop-news.html">Tin tức</a></li>
                        </ul>
                    </li>
                </ul>
            </c:if>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>