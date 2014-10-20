<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<div class="col-lg-9 col-md-9 col-sm-8 col-xs-12 right-content">
    <ol class="breadcrumb">
        <li><a href="${baseUrl}/${shop.alias}">Trang chủ</a></li>
        <li><a href="${baseUrl}/${shop.alias}/news-allcategory.html">Tin tức</a></li>
            <c:if test="${newsCategory!=null}">
                <c:forEach items="${newsCategory.path}" var="path">
                    <c:forEach items="${shopNewsCategories}" var="cate">
                        <c:if test="${cate.id==path}"><li><a href="${baseUrl}/${shop.alias}/news-category/${cate.id}/${text:createAlias(cate.name)}.html">${cate.name}</a></li></c:if>
                    </c:forEach>
                </c:forEach>
            </c:if>
    </ol>     
    <c:if test="${shopNewsData.dataCount > 0}">
        <div class="box-module news-pages-list">
            <div class="container-fluid">
                <div class="row">                    	
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="title-box">
                            <div class="clearfix">
                                <h2 class="pull-left"><a href="${baseUrl}/${shop.alias}/news-category/${newsCategory.id}/${text:createAlias(newsCategory.name)}.html">${newsCategory.name}</a></h2>                        
                            </div>
                            <hr />
                        </div><!--Title box-->
                        <c:forEach items="${shopNewsData.data}" var="news">
                            <div class="media">
                                <a class="pull-left img-news" href="${baseUrl}/${shop.alias}/news/${news.id}/${text:createAlias(news.title)}.html">
                                    <c:if test="${news.image==null || news.image==''}">
                                        <img class="media-object" src="${staticUrl}/shop/img/data/logo.png" alt="${news.title}">
                                    </c:if>
                                    <c:if test="${news.image!=null && news.image!=''}">
                                        <img class="media-object" src="${news.image}" alt="${news.title}">
                                    </c:if>
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading"><a href="${baseUrl}/${shop.alias}/news/${news.id}/${text:createAlias(news.title)}.html">${news.title}</a></h4>
                                        ${news.detail}
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <ul class="pagination pull-right">
                        <c:if test="${shopNewsData.pageIndex > 3}"><li><a href="${baseUrl}/${shop.alias}/news-category/${newsCategory.id}/${text:createAlias(newsCategory.name)}.html">Đầu</a></li></c:if>
                        <c:if test="${shopNewsData.pageIndex > 2}"><li><a href="${baseUrl}/${shop.alias}/news-category/${newsCategory.id}/${text:createAlias(newsCategory.name)}.html?page=${shopNewsData.pageIndex}">&laquo;</a></li></c:if>
                        <c:if test="${shopNewsData.pageIndex >= 2}"><li><a href="${baseUrl}/${shop.alias}/news-category/${newsCategory.id}/${text:createAlias(newsCategory.name)}.html?page=${shopNewsData.pageIndex-1}">${shopNewsData.pageIndex-1}</a></li></c:if>
                        <c:if test="${shopNewsData.pageIndex >= 1}"><li><a href="${baseUrl}/${shop.alias}/news-category/${newsCategory.id}/${text:createAlias(newsCategory.name)}.html?page=${shopNewsData.pageIndex}">${shopNewsData.pageIndex}</a></li></c:if>
                        <li class="active"><a>${shopNewsData.pageIndex + 1}</a></li>
                        <c:if test="${shopNewsData.pageCount - shopNewsData.pageIndex > 2}"><li><a href="${baseUrl}/${shop.alias}/news-category/${newsCategory.id}/${text:createAlias(newsCategory.name)}.html?page=${shopNewsData.pageIndex+2}">${shopNewsData.pageIndex+2}</a></li></c:if>
                        <c:if test="${shopNewsData.pageCount - shopNewsData.pageIndex > 3}"><li><a href="${baseUrl}/${shop.alias}/news-category/${newsCategory.id}/${text:createAlias(newsCategory.name)}.html?page=${shopNewsData.pageIndex+3}">${shopNewsData.pageIndex+3}</a></li></c:if>
                        <c:if test="${shopNewsData.pageCount - shopNewsData.pageIndex > 2}"><li><a href="${baseUrl}/${shop.alias}/news-category/${newsCategory.id}/${text:createAlias(newsCategory.name)}.html?page=${shopNewsData.pageIndex+2}">&raquo;</a></li></c:if>
                        <c:if test="${shopNewsData.pageCount - shopNewsData.pageIndex > 2}"><li><a href="${baseUrl}/${shop.alias}/news-category/${newsCategory.id}/${text:createAlias(newsCategory.name)}.html?page=${shopNewsData.pageCount}">Cuối</a></li></c:if>

                        </ul>                                                                       
                    </div>
                </div>                    
            </div><!--Hiển thị tin tức theo danh mục-->
    </c:if>
</div><!--Right content-->
