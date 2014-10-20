<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="top-navigator navigator-shop">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-3 col-sm-3 col-xs-3">
                <div class="navbar-header">
                    <a href="#menu">
                        <button type="button" class="navbar-toggle">
                            <span class="sr-only"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>                   
                        </button>
                    </a>
                </div>
                <a class="navigator-logo" href="${baseUrl}"><img src="${staticUrl}/shop/img/frame/navigator-logo.png" alt="logo" /></a>

                <div class="menu-top">
                    <ul>
                        <li><a href="${baseUrl}/hotdeal.html">Hotdeal</a></li>
                        <li><a href="${baseUrl}/tin-tuc/huong-dan-nguoi-mua/824548042719.html">Hướng dẫn</a></li>
                        <!--  <li><a href="${baseUrl}/help.html">Trợ giúp</a></li>
                         <li><a href="${baseUrl}/contact.html">Liên hệ</a></li>-->
                    </ul>
                </div><!-- /menu-top -->
            </div>
            <div class="col-lg-4 col-md-4 col-sm-7 col-xs-6">  
                <a class="navigator-logo" href="${baseUrl}"><img src="${staticUrl}/shop/img/frame/navigator-logo.png" alt="logo" /></a>              
                <div class="box-search col-lg-12 col-xs-12">
                    <div class="search-choose">
                        <span class="search-menu"></span>
                        <span class="icon-arrowdown"></span>
                        <div class="popmenu">
                            <span class="popmenu-bullet"></span>
                            <ul class="box-menu" id="navbar-search-cat">
                                <li><a href="javascript:search.changeCat();">Tất cả danh mục</a></li>
                                    <c:forEach var="cat" items="${rootCategories}">
                                    <li for="${cat.id}"><a href="javascript:search.changeCat('${cat.id}');">${cat.name}</a></li>
                                    </c:forEach>
                            </ul>
                        </div><!-- /popmenu -->
                    </div>
                    <div class="search-inner"><input id="navbar-search" type="text" name="headsearch" class="text" placeholder="Tìm kiếm, Ví dụ: đầm dự tiệc, iphone 6, ipad, samsung galaxy S5" /></div>
                    <input onclick="search.go()" class="btn-search" value="Tìm kiếm" />
                </div><!-- /box-search -->
            </div>
            <div class="col-lg-4 col-md-5 col-sm-2 col-xs-3">
                <div class="block-user">
                    <a href="#user">                    
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle">
                                <span class="sr-only"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>                   
                            </button>                            
                        </div>
                        <span class="glyphicon glyphicon-user"></span>
                    </a>
                </div>
                <div class="boxadmin">
                    <ul id="navbar-user">
                        <li class="login-li">Xin chào! <a href="${baseUrl}/user/signin.html">Đăng nhập</a> hoặc <a href="${baseUrl}/user/signup.html">Đăng ký</a></li>
                        <li class="sell-li"><a href="${baseUrl}/user/dang-ban.html">Đăng bán</a></li>
                        <li class="cart-li">
                            <a href="${baseUrl}/gio-hang.html">
                                <c:if test="${fn:length(viewer.cart) > 0}">
                                    <span class="msg-red">${itemCart}</span>
                                </c:if>
                                <span class="icon-cart"></span>
                            </a>
                            <div class="popmenu popmenu-right">
                                <span class="popmenu-bullet"></span>
                                <div class="popmenu-medium">
                                    <div class="usercart-list">
                                        <div class="grid">
                                            <div class="g-row">Bạn chưa chọn mua sản phẩm nào vào giỏ hàng!</div>
                                        </div><!-- /grid -->
                                    </div><!-- /usercart-list -->
                                </div><!-- /popmenu-medium -->
                            </div><!-- /popmenu -->
                        </li>
                    </ul>
                </div><!-- /boxadmin -->
            </div>
        </div>
    </div><!-- /container -->
</div><!-- /navigator -->