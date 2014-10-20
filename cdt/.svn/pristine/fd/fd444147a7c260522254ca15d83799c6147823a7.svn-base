<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="url" uri="/WEB-INF/taglib/url.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-lg-3 col-md-3 col-sm-4 col-xs-12 left-slidebar">

    <c:if test="${shopCategories==null||fn:length(shopCategories)==0}">
        <div class="widget-box category-box">
            <div class="navbar-header">
                <a id="simple-menu" href="#menu-category-mobile">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="">
                        <div class="widget-title"><span class="glyphicon glyphicon-align-justify"></span> Danh mục</div>
                    </button>
                </a>
            </div>
            <div class="widget-title"><span class="glyphicon glyphicon-align-justify"></span> Danh mục</div>
            <div class="widget-content" id="more-btn-slider">
                <div class="collapse navbar-collapse ddsmoothmenu-v" id="menu-category-product">
                    <ul class="category-menu"></ul>                                                  
                </div>
            </div>
        </div><!--Danh mục sản phẩm-->
    </c:if>
    <c:if test="${shopCategories!=null&&fn:length(shopCategories)>0}">
        <div class="widget-box category-box">
            <div class="navbar-header">
                <a id="simple-menu" href="#menu-category-mobile">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="">
                        <div class="widget-title"><span class="glyphicon glyphicon-align-justify"></span> Danh mục</div>
                    </button>
                </a>
            </div>
            <div class="widget-title"><span class="glyphicon glyphicon-align-justify"></span> Danh mục</div>
            <div class="widget-content" id="more-btn-slider">
                <div class="collapse navbar-collapse ddsmoothmenu-v" id="menu-category-product">
                    <ul class="category-menu"></ul>                                                  
                </div>
            </div>
        </div><!--Danh mục sản phẩm-->
    </c:if>
    <c:if test="${shopNewsCategories!=null&&fn:length(shopNewsCategories)>0}">
        <div class="widget-box category-box">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a id="simple-menu" href="#menu-category-news-mobile">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="">
                        <div class="widget-title"><span class="glyphicon glyphicon-align-justify"></span> Tin tức</div>
                    </button>
                </a>
            </div>
            <div class="widget-title"><span class="glyphicon glyphicon-certificate"></span> Tin tức</div>
            <div class="widget-content" id="more-btn-slider">
                <div class="collapse navbar-collapse ddsmoothmenu-v" id="menu-catnews">
                    <ul class="news-category">                      
                    </ul>                                                  
                </div>
            </div>
        </div><!--Danh mục tin tức-->
    </c:if>
    <c:if test="${listPromotion!= null && fn:length(listPromotion)>0}">
        <div class="widget-box news-box">
            <div class="widget-title"><span class="fa fa-gift"></span> Chương trình khuyến mại</div>
            <div class="widget-content">                        
                <ul class="list-news-slidebar">                        
                    <c:forEach items="${listPromotion}" var="prom">
                        <li>
                            <a href="${baseUrl}${url:shopBrowseUrl(shopItemSearch, shop.alias, '[{key:"promotionId",op:"mk",val:"'.concat(prom.id).concat('"},{key:"page",val:0}]'))}">${prom.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </c:if>
    <c:if test="${fn:length(shopContact) > 0}"> 
    <div class="widget-box support-online-box">
        <div class="widget-title"><span class="fa fa-bullhorn"></span> Hỗ trợ trực tuyến</div>
        <div class="widget-content">
            <c:forEach items="${shopContact}" var="sContact" varStatus="stt">
                <h4>${sContact.title}</h4>
                <c:if test="${sContact.phone != '' && sContact.phone != ''}" >
                    <p><span class="fa fa-phone"></span> ${sContact.phone}</p>
                </c:if>
                <c:if test="${sContact.email != '' && sContact.email != ''}" >
                    <p><span class="fa fa-envelope-o"></span> <a href="mailto:${sContact.email}" style="font-size:12px">${sContact.email}</a></p>
                    </c:if>
                    <c:if test="${sContact.yahoo != '' && sContact.yahoo != ''}" >
                    <p><img src="http://opi.yahoo.com/online?u=${sContact.yahoo}&m=g&t=5"/> <a href="ymsgr:sendim?${sContact.yahoo}">${sContact.yahoo}</a></p>
                    </c:if>
                    <c:if test="${sContact.skype != '' && sContact.skype != ''}" >
                    <p><span class="fa fa-skype"></span> <a href="skype:${sContact.skype}?chat">${sContact.skype}</a></p>
                    </c:if>
                    <c:if test="${stt.index!=(fn:length(shopContact)-1)}"><hr/></c:if>
            </c:forEach>
        </div>
    </div><!--Support Online-->
    </c:if>
    <div class="widget-box statics-box">  
        <jsp:useBean id="date" class="java.util.Date" />
        <div class="widget-title"><span class="fa fa-cogs"></span> Thống kê shop</div>
        <div class="widget-content">
            <!--p><span class="fa  fa-calendar"></span> Ngày tham gia:<strong>
            <jsp:setProperty name="date" property="time" value="${shop.createTime}" /> 
            <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate>
            </strong> </p-->
                <p><span class="fa fa-globe"></span> <strong>${shop.viewCount}</strong> lượt truy cập</p>

        </div>
    </div><!--Statics box-->
    <c:forEach items="${shopBannerLefts}" var="shopbanner" varStatus="stt">
        <c:if test="${shopbanner.type=='ADV_LEFT'}">
            <dic class="adv-block">
                <a href="${shopbanner.url}" target="_blank"><img src="${shopbanner.image}"></a>
            </dic>
        </c:if>
    </c:forEach>

</div>

<nav id="menu-category-mobile">                         
    <ul></ul>
</nav><!--Danh mục menu chính-->
<nav id="menu-category-news-mobile">                         
    <ul></ul>
</nav><!--Danh mục tin tức-->
