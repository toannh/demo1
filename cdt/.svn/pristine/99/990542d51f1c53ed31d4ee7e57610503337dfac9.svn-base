<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld" %>
<div class="container">
    <div class="bground">
        <div class="bg-white">
            <div class="page-hotdeal">
                <div class="hotdeal-header">
                    <a class="hotdeal-logo" href="${baseUrl}/hotdeal.html"><img src="${staticUrl}/market/images/hotdeal/hotdeal-logo.png" alt="hotDEAL" /></a>
                    <div class="hotdeal-menu">
                        <ul>
                            <c:forEach items="${categories}" var="cate">
                                <c:if test="${(cate.parentId==null || cate.parentId=='') && !cate.special}">
                                    <li <c:if test="${currentCate.parentId == cate.id || currentCate.id == cate.id}">class="active"</c:if>>
                                        <a href="${baseUrl}/hotdeal/${cate.id}/${text:createAlias(cate.name)}.html">${cate.name}</a>
                                        <ul>
                                            <c:forEach items="${categoriesChilds}" var="cateChilds">
                                                <c:if test="${cateChilds.parentId == cate.id}">
                                                    <li><a href="${baseUrl}/hotdeal/${cateChilds.id}/${text:createAlias(cateChilds.name)}.html">${cateChilds.name}</a></li>
                                                    </c:if>
                                                </c:forEach>
                                        </ul>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div><!-- /hotdeal-menu -->
                </div><!-- /hotdeal-header -->
                <ol class="breadcrumb">
                    <li>
                        <a href="${baseUrl}/hotdeal.html">
                            <span class="hotdeal-icon-breadcrumb"></span>
                            <b class="text-danger">HotDEAL</b>
                        </a>
                    </li>
                    <c:set var="exits" value="false"/>
                    <c:forEach items="${categories}" var="cate">
                        <c:if test="${currentCate.id == cate.id || (currentCate.parentId!=null && currentCate.parentId == cate.id)}">
                            <c:if test="${currentCate.id == cate.id}">
                                <c:set var="exits" value="true"/>
                            </c:if>
                            <li>
                                <a href="${baseUrl}/hotdeal/${cate.id}/${text:createAlias(cate.name)}.html">${cate.name} <c:if test="${!cate.leaf}"><span class="icon-arrowdown"></span></c:if></a>
                                <c:if test="${!cate.leaf}">
                                <ul>
                                    <c:forEach items="${categoriesChilds}" var="cateChilds">
                                        <c:if test="${cateChilds.parentId == cate.id}">
                                            <li><a href="${baseUrl}/hotdeal/${cateChilds.id}/${text:createAlias(cateChilds.name)}.html">${cateChilds.name}</a></li>
                                            </c:if>
                                        </c:forEach>
                                </ul>
                                </c:if>
                            </li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${!exits}">
                        <li class="active"><a href="${baseUrl}/hotdeal/${currentCate.id}/${text:createAlias(currentCate.name)}.html">${currentCate.name}</a></li>
                        </c:if>
                </ol>
                <div class="hdbox">
                    <div class="hdbox-title">
                        <div class="hdbox-lb">
                            <label class="lb-name">${currentCate.name}</label>
                            <c:if test="${!currentCate.leaf}">
                                <div class="hotdeal-submenu">
                                    <span class="hotdeal-iconmenu"></span>
                                    <div class="hs-popout">
                                        <ul>
                                            <c:forEach items="${categoriesChilds}" var="cateChilds">
                                                <c:if test="${cateChilds.parentId == currentCate.id}">
                                                    <li><a href="${baseUrl}/hotdeal/${cateChilds.id}/${text:createAlias(cateChilds.name)}.html">${cateChilds.name}</a></li>
                                                    </c:if>
                                                </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </c:if>

                        </div><!-- /hdbox-lb -->
                    </div><!-- /hdbox-title -->
                    <div class="hdbox-content">
                        <div class="hd-list">
                            <c:if test="${fn:length(hotdealItemsPage.data)<=0}">
                                <p class="clr-red text-center fs-18">Hiện tại deal này chưa có sản phẩm, vui lòng quay lại sau !</p>
                            </c:if>
                            <c:if test="${fn:length(hotdealItemsPage.data)>0}">
                                <c:forEach items="${hotdealItemsPage.data}" var="hitem">
                                    <div class="hd-item">
                                        <div class="big-shadow">
                                            <c:if test="${!hitem.item.discount && hitem.item.listingType != 'AUCTION' && hitem.item.startPrice > hitem.item.sellPrice}">
                                                <span class="item-sale">-${text:percentFormat((hitem.item.startPrice-hitem.item.sellPrice)/hitem.item.startPrice)}%</span>
                                            </c:if>
                                            <c:if test="${hitem.item.discount && hitem.item.listingType != 'AUCTION'}">
                                                <span class="item-sale">-${text:percentFormat(hitem.item.discountPrice>0?hitem.item.discountPrice/hitem.item.sellPrice:hitem.item.discountPercent/100)}%</span>
                                            </c:if>  
                                            <a class="view-now" href="${baseUrl}/san-pham/${hitem.item.id}/${text:createAlias(hitem.item.id)}.html">Xem ngay</a>
                                            <div class="item-img"><a href="${baseUrl}/san-pham/${hitem.item.id}/${text:createAlias(hitem.item.id)}.html">
                                                    <img src="${(hitem.item.images != null && fn:length(hitem.item.images) >0)?hitem.item.images[0]:staticUrl.concat('/market/images/no-image-product.png')}"  alt="${hitem.item.name}" title="${hitem.item.name}"/>
                                                </a></div>
                                            <div class="item-text">
                                                <a class="item-link" href="${baseUrl}/san-pham/${hitem.item.id}/${text:createAlias(hitem.item.id)}.html">${hitem.item.name}</a>
                                                <div class="item-row">

                                                    <c:if test="${!hitem.item.discount}">
                                                        <span class="item-price">${text:numberFormat(hitem.item.sellPrice)} <sup class="u-price">đ</sup></span>
                                                        <c:if test="${hitem.item.startPrice > hitem.item.sellPrice}">
                                                            <span class="old-price">${text:numberFormat(hitem.item.startPrice)} <sup class="u-price">đ</sup></span>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${hitem.item.discount}">
                                                        <span class="item-price">${text:numberFormat(hitem.item.discountPrice>0?hitem.item.sellPrice-hitem.item.discountPrice:hitem.item.sellPrice*(100-hitem.item.discountPercent)/100)} <sup class="u-price">đ</sup></span>
                                                        <span class="old-price">${text:numberFormat(hitem.item.sellPrice)} <sup class="u-price">đ</sup></span>
                                                    </c:if>
                                                    <c:if test="${hitem.item.listingType=='AUCTION'}">
                                                        <span class="icon20-bidgray"></span>
                                                        <span class="item-price">${text:numberFormat(hitem.item.highestBid)} <sup class="u-price">đ</sup></span>
                                                        <span class="bid-count">(${hitem.item.bidCount} lượt)</span>
                                                    </c:if>
                                                </div>
                                                <div class="item-row bg-item">
                                                    <div class="item-shop">
                                                        <c:if test="${hitem.item.shopName!=null && hitem.item.shopName!=''}">
                                                            <span class="icon16-shop"></span>
                                                            <a href="${baseUrl}/${hitem.item.shopName}/">${hitem.item.shopName}</a>
                                                        </c:if>
                                                        <c:if test="${hitem.item.shopName==null || hitem.item.shopName==''}">
                                                            <span class="icon16-usernormal"></span>
                                                            <a href="#">${hitem.item.shopName}</a>
                                                        </c:if>
                                                        <c:if test="${hitem.item.condition=='NEW'}">
                                                            <span class="item-status">(Hàng mới)</span>
                                                        </c:if>
                                                        <c:if test="${hitem.item.condition=='OLD'}">
                                                            <span class="item-status">(Hàng cũ)</span>
                                                        </c:if>
                                                    </div>
                                                    <div class="item-icon">
                                                        <c:if test="${hitem.item.onlinePayment}">
                                                            <p>
                                                                <span class="icon16-nlgray"></span>
                                                                <span class="icon-desc">Thanh toán qua NgânLượng</span>
                                                            </p>
                                                        </c:if>
                                                        <c:if test="${hitem.item.cod}">
                                                            <p>
                                                                <span class="icon16-codgray"></span>
                                                                <span class="icon-desc">Giao hàng và thu tiền</span>
                                                            </p>
                                                        </c:if>
                                                        <!--                                                    <p><span class="icon16-transgray"></span><span class="icon-desc">Miễn phí vận chuyển cho đơn hàng từ <b>200.000 đ</b></span></p>-->
                                                    </div>
                                                </div>
                                            </div>
                                            <ul class="item-hover">
                                                <c:if test="${hitem.item.images != null && fn:length(hitem.item.images) > 1}">
                                                    <c:forEach var="image" items="${hitem.item.images}" end="4">
                                                        <li>
                                                            <span><img src="${image}" alt="item" /></span>
                                                        </li>
                                                    </c:forEach>
                                                </c:if>
                                            </ul>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <div class="clearfix"></div>
                        </div><!-- /hd-list -->
                        <c:if test="${hotdealItemsPage.pageCount > 1}">
                            <div class="box-bottom">
                                
                                <ul class="pagination">
                                    <c:if test="${hotdealItemsPage.pageIndex > 3}"><li><a href="${baseUrl}/hotdeal/${currentCate.id}/${text:createAlias(currentCate.name)}.html" >&laquo;</a></li></c:if>
                                    <c:if test="${hotdealItemsPage.pageIndex > 2}"><li><a href="${baseUrl}/hotdeal/${currentCate.id}/${text:createAlias(currentCate.name)}.html?page=${hotdealItemsPage.pageIndex}" >&lt;</a></li></c:if>
                                    <c:if test="${hotdealItemsPage.pageIndex >= 2}"><li><a href="${baseUrl}/hotdeal/${currentCate.id}/${text:createAlias(currentCate.name)}.html?page=${hotdealItemsPage.pageIndex-1}" >${hotdealItemsPage.pageIndex-1}</a></li></c:if>
                                    <c:if test="${hotdealItemsPage.pageIndex >= 1}"><li><a href="${baseUrl}/hotdeal/${currentCate.id}/${text:createAlias(currentCate.name)}.html?page=${hotdealItemsPage.pageIndex}" >${hotdealItemsPage.pageIndex}</a></li></c:if>
                                    <li class="active"><a>${hotdealItemsPage.pageIndex + 1}</a></li>
                                    <c:if test="${hotdealItemsPage.pageCount - hotdealItemsPage.pageIndex >= 2}"><li><a href="${baseUrl}/hotdeal/${currentCate.id}/${text:createAlias(currentCate.name)}.html?page=${hotdealItemsPage.pageIndex+2}">${hotdealItemsPage.pageIndex+2}</a></li></c:if>
                                    <c:if test="${hotdealItemsPage.pageCount - hotdealItemsPage.pageIndex > 3}"><li><a href="${baseUrl}/hotdeal/${currentCate.id}/${text:createAlias(currentCate.name)}.html?page=${hotdealItemsPage.pageIndex+3}">${hotdealItemsPage.pageIndex+3}</a></li></c:if>
                                    <c:if test="${hotdealItemsPage.pageCount - hotdealItemsPage.pageIndex > 2}"><li><a href="${baseUrl}/hotdeal/${currentCate.id}/${text:createAlias(currentCate.name)}.html?page=${hotdealItemsPage.pageIndex+2}">&gt;</a></li></c:if>
                                    <c:if test="${hotdealItemsPage.pageCount - hotdealItemsPage.pageIndex > 2}"><li><a href="${baseUrl}/hotdeal/${currentCate.id}/${text:createAlias(currentCate.name)}.html?page=${hotdealItemsPage.pageCount}">&raquo;</a></li></c:if>
                                    </ul>
                            </div><!--box-bottom-->
                        </c:if>
                    </div><!-- /hdbox-content -->
                </div><!-- /hdbox -->
            </div><!-- /page-hotdeal -->
        </div><!-- /bg-white -->
    </div><!-- /bground -->
</div><!-- container -->