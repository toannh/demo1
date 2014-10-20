<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld" %>
<div class="container">
    <div class="bground">
        <div class="bg-white">
            <div class="page-landing">
                <div class="landing-header" style="border-bottom:3px solid ${landing.color};">
                    <a class="landing-logo" href="${baseUrl}/landing/${landing.id}/${text:createAlias(landing.name)}.html"><img src="${landing.logo}" alt="landing" /></a>
                    <div class="landing-menu">
                        <ul>
                            <c:forEach items="${categories}" var="cate">
                                <li style="background-color:${landing.color};" <c:if test="${landingCategory.id == cate.id}">class="active"</c:if>>
                                        <span typeof="v:Breadcrumb" >
                                            <a rel="v:url" property="v:title" href="${baseUrl}/landing/category/${cate.id}/${text:createAlias(cate.name)}.html">
                                            ${cate.name}
                                        </a>
                                    </span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div><!-- /landing-header -->
                <div class="landing-main" style="background-image:url(${landing.background});">
                    <div class="landing-title" style="border-left:10px solid ${landing.color};">
                        <div class="lt-left">
                            <label class="lt-text">${landingCategory.name}</label>
                        </div>
                        <div class="lt-right"></div>
                    </div><!-- /landing-title -->
                    <div class="landing-listitem">
                        <c:forEach items="${landingItems.data}" var="item">
                            <div class="landing-item">
                                <c:if test="${!item.item.discount && item.item.listingType != 'AUCTION' && item.item.startPrice > item.item.sellPrice}">
                                    <div class="li-sale" style="background-color:${landing.color};">Sale<span>-${text:percentFormat((item.item.startPrice-item.item.sellPrice)/item.item.startPrice)}%</span></div>
                                </c:if>
                                <c:if test="${item.item.discount && item.item.listingType != 'AUCTION'}">
                                    <div class="li-sale" style="background-color:${landing.color};">Sale<span>-${text:percentFormat(item.item.discountPrice>0?item.item.discountPrice/item.item.sellPrice:item.item.discountPercent/100)}%</span></div>
                                </c:if>

                                <div class="li-thumb">
                                    <a href="${baseUrl}/san-pham/${item.item.id}/${text:createAlias(item.item.name)}.html">
                                        <img src="${(item.image != null && item.image != '')?item.image:staticUrl.concat('/market/images/no-image-product.png')}" alt="product" />
                                    </a>
                                </div>

                                <c:if test="${item.item.listingType=='BUYNOW'}">
                                    <c:if test="${!item.item.discount}">
                                        <div class="li-row"><span class="li-price" style="color:${landing.color};">${text:numberFormat(item.item.sellPrice)} <sup class="u-price">đ</sup></span></div>
                                        <c:if test="${item.item.startPrice > item.item.sellPrice}">
                                            <div class="li-row"><span class="li-oldprice">${text:numberFormat(item.item.startPrice)} <sup class="u-price">đ</sup></span></div>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${item.item.discount}">
                                        <div class="li-row"><span class="li-price" style="color:${landing.color};">${text:numberFormat(item.item.discountPrice>0?item.item.sellPrice-item.item.discountPrice:item.item.sellPrice*(100-item.item.discountPercent)/100)} <sup class="u-price">đ</sup></span></div>
                                        <div class="li-row"><span class="li-oldprice">${text:numberFormat(item.item.sellPrice)}<sup class="u-price">đ</sup></span></div>
                                    </c:if>
                                </c:if>
                                <c:if test="${item.item.listingType=='AUCTION'}">
                                    <span class="icon20-bidgray"></span>
                                    <div class="li-row"><span class="li-price" style="color:${landing.color};">${text:numberFormat(item.item.highestBid)} <sup class="u-price">đ</sup></span></div>
                                    <span class="bid-count">(${item.item.bidCount} lượt)</span>
                                </c:if>
                                <div class="li-row"><a class="li-title" href="${baseUrl}/san-pham/${item.item.id}/${text:createAlias(item.item.name)}.html">${item.item.name}</a></div>
                            </div><!-- /landing-item -->
                        </c:forEach>
                        <div class="clearfix"></div>
                    </div><!-- /landing-list-item -->
                    <c:if test="${landingItems.pageCount > 1}">
                        <div class="box-bottom">
                            <ul class="pagination">
                                <c:if test="${landingItems.pageIndex > 3}"><li><a href="${baseUrl}/landing/category/${landingCategory.id}/${text:createAlias(landingCategory.name)}.html" >&laquo;</a></li></c:if>
                                <c:if test="${landingItems.pageIndex > 2}"><li><a href="${baseUrl}/landing/category/${landingCategory.id}/${text:createAlias(landingCategory.name)}.html?page=${landingItems.pageIndex}" >&lt;</a></li></c:if>
                                <c:if test="${landingItems.pageIndex >= 2}"><li><a href="${baseUrl}/landing/category/${landingCategory.id}/${text:createAlias(landingCategory.name)}.html?page=${landingItems.pageIndex-1}" >${landingItems.pageIndex-1}</a></li></c:if>
                                <c:if test="${landingItems.pageIndex >= 1}"><li><a href="${baseUrl}/landing/category/${landingCategory.id}/${text:createAlias(landingCategory.name)}.html?page=${landingItems.pageIndex}" >${landingItems.pageIndex}</a></li></c:if>
                                <li class="active"><a>${landingItems.pageIndex + 1}</a></li>
                                <c:if test="${landingItems.pageCount - landingItems.pageIndex >= 2}"><li><a href="${baseUrl}/landing/category/${landingCategory.id}/${text:createAlias(landingCategory.name)}.html?page=${landingItems.pageIndex+2}">${landingItems.pageIndex+2}</a></li></c:if>
                                <c:if test="${landingItems.pageCount - landingItems.pageIndex > 3}"><li><a href="${baseUrl}/landing/category/${landingCategory.id}/${text:createAlias(landingCategory.name)}.html?page=${landingItems.pageIndex+3}">${landingItems.pageIndex+3}</a></li></c:if>
                                <c:if test="${landingItems.pageCount - landingItems.pageIndex > 2}"><li><a href="${baseUrl}/landing/category/${landingCategory.id}/${text:createAlias(landingCategory.name)}.html?page=${landingItems.pageIndex+2}">&gt;</a></li></c:if>
                                <c:if test="${landingItems.pageCount - landingItems.pageIndex > 2}"><li><a href="${baseUrl}/landing/category/${landingCategory.id}/${text:createAlias(landingCategory.name)}.html?page=${landingItems.pageCount}">&raquo;</a></li></c:if>
                                </ul>
                            </div><!--box-bottom-->
                    </c:if>
                </div><!-- /landing-main -->
            </div><!-- /page-landing -->
        </div><!-- /bg-white -->
    </div><!-- /bground -->
    <div class="internal-text">
        <h1>${landingCategory.name}</h1>
        <h2>${landing.name} ưu đãi đặc biệt tại chodientu.vn</h2>
    </div>
</div><!-- container -->