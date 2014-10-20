<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<div class="col-lg-9 col-md-9 col-sm-8 col-xs-12 right-content">
    <ol class="breadcrumb">
        <li><a href="${baseUrl}/${shop.alias}">Trang chủ</a></li>
        <li><a href="${baseUrl}/${shop.alias}/news-allcategory.html">Tin tức</a></li>
    </ol>
    <div class="box-module news-pages">

        <div class="container-fluid">
            <div class="row">      
                <c:forEach items="${shopNewsCategories}" var="category" >
                    <c:set var="ischeck" value="false" /> 
                    <c:forEach items="${shopNews}" var="entry">
                        <c:if test="${entry.key == category.id && fn:length(entry.value)>0}">
                            <c:set var="ischeck" value="true" />
                            <c:set var="key" value="${entry.key}" /> 
                            <c:set var="listNews" value="${entry.value}" /> 
                        </c:if>
                    </c:forEach>
                    <c:if test="${ischeck}">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="title-box">
                                <div class="clearfix">
                                    <h2 class="pull-left"><a href="${baseUrl}/${shop.alias}/news-category/${category.id}/${text:createAlias(category.name)}.html">${category.name}</a></h2>                        
                                    <a class="pull-right small" href="${baseUrl}/${shop.alias}/news-category/${category.id}/${text:createAlias(category.name)}.html">Xem tất cả</a>
                                </div>
                                <hr />
                            </div><!--Title box-->
                            <div class="media">
                                <a class="pull-left img-news" href="${baseUrl}/${shop.alias}/news/${listNews[0].id}/${text:createAlias(listNews[0].title)}.html">
                                    <c:if test="${listNews[0].image==null || listNews[0].image==''}">
                                        <img class="media-object" src="${staticUrl}/shop/img/data/logo.png" alt="${listNews[0].title}">
                                    </c:if>
                                    <c:if test="${listNews[0].image!=null && listNews[0].image!=''}">
                                        <img class="media-object" src="${listNews[0].image}" alt="${listNews[0].title}">
                                    </c:if>
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="${baseUrl}/${shop.alias}/news/${listNews[0].id}/${text:createAlias(listNews[0].title)}.html">${listNews[0].title}</a></h4>
                                    <p>${listNews[0].detail}</p>
                                    <ul class="news-list-more">
                                        <c:forEach items="${listNews}" var="news" begin="1">
                                            <li><a href="${baseUrl}/${shop.alias}/news/${news.id}/${text:createAlias(news.title)}.html">${news.title}</a></li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>                                                                  
            </div>
        </div>                    
    </div><!--Hiển thị tin tức theo danh mục-->


</div><!--Right content-->
