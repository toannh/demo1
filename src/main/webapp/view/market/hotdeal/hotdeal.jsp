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
                    <a href="${baseUrl}/hotdeal.html" class="hotdeal-logo" ><img src="${staticUrl}/market/images/hotdeal/hotdeal-logo.png" alt="hotDEAL" /></a>
                    <div class="hotdeal-menu">
                        <ul>
                            <c:forEach items="${categories}" var="cate">
                                <c:if test="${(cate.parentId==null || cate.parentId=='') && !cate.special}">
                                    <li>
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
                <c:if test="${categoriesSpecial!=null}">
                    <div class="hotdeal-vip">
                        <c:set value="0" var="count" />
                        <c:forEach items="${hotdealItems}" var="hitem">
                            <c:if test="${hitem.hotdealCategoryId == categoriesSpecial.id && count<4}">
                                <c:set value="${count+1}" var="count" />
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
                                                <img src="${(hitem.image != null && hitem.image != '')?hitem.image:staticUrl.concat('/market/images/no-image-product.png')}"  alt="${hitem.item.name}" title="${hitem.item.name}"/>
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
                                    </div><!--big-shadow-->
                                </div><!--hd-item-->
                            </c:if>
                        </c:forEach>
                        <div class="clearfix"></div>
                    </div><!--hotdeal-vip-->
                </c:if>
                <c:forEach items="${categories}" var="cate" varStatus="stt">
                    <c:if test="${stt.index%2==0}">
                        <div class="hdbox">
                            <div class="hdbox-title">
                                <div class="hdbox-lb">
                                    <label class="lb-name">${cate.name}</label>
                                    <c:if test="${!cate.leaf}">
                                        <div class="hotdeal-submenu">
                                            <span class="hotdeal-iconmenu"></span>
                                            <div class="hs-popout">
                                                <ul>
                                                    <c:forEach items="${categoriesChilds}" var="cateChilds">
                                                        <c:if test="${cateChilds.parentId == cate.id}">
                                                            <li><a href="${baseUrl}/hotdeal/${cateChilds.id}/${text:createAlias(cateChilds.name)}.html">${cateChilds.name}</a></li>
                                                            </c:if>
                                                        </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:if>
                                    <a class="hotdeal-viewall" href="${baseUrl}/hotdeal/${cate.id}/${text:createAlias(cate.name)}.html">
                                        <span class="hotdeal-iconsearch"></span>
                                        Xem tất cả
                                    </a>
                                </div><!-- /hdbox-lb -->
                            </div><!-- /hdbox-title -->
                            <div class="hdbox-content">
                                <div class="hdhome-product">
                                    <div class="hp-left">
                                        <div class="home-item hdbig-item">
                                            <c:forEach items="${hotdealItems}" var="item">
                                                <c:if test="${item.special && item.hotdealCategoryId == cate.id}">
                                                    <div class="hoi-inner">
                                                        <c:if test="${!item.item.discount && item.item.listingType != 'AUCTION' && item.item.startPrice > item.item.sellPrice}">
                                                            <span class="hoi-sale">-${text:percentFormat((item.item.startPrice-item.item.sellPrice)/item.item.startPrice)}%</span>
                                                        </c:if>
                                                        <c:if test="${item.item.discount && item.item.listingType != 'AUCTION'}">
                                                            <span class="hoi-sale">-${text:percentFormat(item.item.discountPrice>0?item.item.discountPrice/item.item.sellPrice:item.item.discountPercent/100)}%</span>
                                                        </c:if>
                                                        <a class="hoi-thumblink" href="${baseUrl}/san-pham/${item.item.id}/${text:createAlias(item.item.name)}.html">
                                                            <img src="${(item.image != null && item.image != '')?item.image:staticUrl.concat('/market/images/no-image-product.png')}"  alt="${item.item.name}" title="${item.item.name}"/>
                                                        </a>
                                                        <div class="hoi-title">
                                                            <div class="hoi-row"><a href="${baseUrl}/san-pham/${item.item.id}/${text:createAlias(item.item.name)}.html">${item.item.name}</a></div>
                                                            <div class="hoi-row">

                                                                <c:if test="${!item.item.discount}">
                                                                    <span class="hoi-price">${text:numberFormat(item.item.sellPrice)} <sup class="u-price">đ</sup></span>
                                                                    <c:if test="${item.item.startPrice > item.item.sellPrice}">
                                                                        <span class="hoi-oldprice">${text:numberFormat(item.item.startPrice)} <sup class="u-price">đ</sup></span>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${item.item.discount}">
                                                                    <span class="hoi-price">${text:numberFormat(item.item.discountPrice>0?item.item.sellPrice-item.item.discountPrice:item.item.sellPrice*(100-item.item.discountPercent)/100)} <sup class="u-price">đ</sup></span>
                                                                    <span class="hoi-oldprice">${text:numberFormat(item.item.sellPrice)} <sup class="u-price">đ</sup></span>
                                                                </c:if>
                                                                <c:if test="${item.item.listingType=='AUCTION'}">
                                                                    <span class="icon20-bidgray"></span>
                                                                    <span class="hoi-price">${text:numberFormat(item.item.highestBid)} <sup class="u-price">đ</sup></span>
                                                                    <span class="bid-count">(${item.item.bidCount} lượt)</span>
                                                                </c:if>
                                                                <div class="hoi-icon">
                                                                    <c:if test="${item.item.onlinePayment}">
                                                                        <span class="icon16-nlgray"></span>
                                                                    </c:if>
                                                                    <c:if test="${item.item.cod}">
                                                                        <span class="icon16-codgray"></span>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="hoi-view">
                                                            <a class="hoi-btn" href="javascript:void(0);" onclick="market.quickview('${item.item.id}')" data-toggle="modal" data-target="#ModalQuickView">Xem nhanh</a>
                                                            <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div><!-- home-item -->
                                    </div><!-- /hp-left -->
                                    <div class="hp-right">
                                        <c:set var="count" value="0" />
                                        <c:forEach items="${hotdealItems}" var="item">
                                            <c:if test="${!item.special && count < 6 && item.hotdealCategoryId == cate.id}">
                                                <div class="home-item">
                                                    <div class="hoi-inner">
                                                        <c:set var="count" value="${count+1}" />
                                                        <c:if test="${!item.item.discount && item.item.listingType != 'AUCTION' && item.item.startPrice > item.item.sellPrice}">
                                                            <span class="hoi-sale">-${text:percentFormat((item.item.startPrice-item.item.sellPrice)/item.item.startPrice)}%</span>
                                                        </c:if>
                                                        <c:if test="${item.item.discount && item.item.listingType != 'AUCTION'}">
                                                            <span class="hoi-sale">-${text:percentFormat(item.item.discountPrice>0?item.item.discountPrice/item.item.sellPrice:item.item.discountPercent/100)}%</span>
                                                        </c:if>
                                                        <a class="hoi-thumblink" href="${baseUrl}/san-pham/${item.item.id}/${text:createAlias(item.item.name)}.html">
                                                            <img src="${(item.image != null && item.image != '')?item.image:staticUrl.concat('/market/images/no-image-product.png')}"  alt="${item.item.name}" title="${item.item.name}"/>
                                                        </a>
                                                        <div class="hoi-title">
                                                            <div class="hoi-row"><a href="${baseUrl}/san-pham/${item.item.id}/${text:createAlias(item.item.name)}.html">${item.item.name}</a></div>
                                                            <div class="hoi-row">

                                                                <c:if test="${!item.item.discount}">
                                                                    <span class="hoi-price">${text:numberFormat(item.item.sellPrice)} <sup class="u-price">đ</sup></span>
                                                                    <c:if test="${item.item.startPrice > item.item.sellPrice}">
                                                                        <span class="hoi-oldprice">${text:numberFormat(item.item.startPrice)} <sup class="u-price">đ</sup></span>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${item.item.discount}">
                                                                    <span class="hoi-price">${text:numberFormat(item.item.discountPrice>0?item.item.sellPrice-item.item.discountPrice:item.item.sellPrice*(100-item.item.discountPercent)/100)} <sup class="u-price">đ</sup></span>
                                                                    <span class="hoi-oldprice">${text:numberFormat(item.item.sellPrice)} <sup class="u-price">đ</sup></span>
                                                                </c:if>
                                                                <c:if test="${item.item.listingType=='AUCTION'}">
                                                                    <span class="icon20-bidgray"></span>
                                                                    <span class="hoi-price">${text:numberFormat(item.item.highestBid)} <sup class="u-price">đ</sup></span>
                                                                    <span class="bid-count">(${item.item.bidCount} lượt)</span>
                                                                </c:if>
                                                                <div class="hoi-icon">
                                                                    <c:if test="${item.item.onlinePayment}">
                                                                        <span class="icon16-nlgray"></span>
                                                                    </c:if>
                                                                    <c:if test="${item.item.cod}">
                                                                        <span class="icon16-codgray"></span>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="hoi-view">
                                                            <a class="hoi-btn" href="javascript:void(0);" onclick="market.quickview('${item.item.id}')" data-toggle="modal" data-target="#ModalQuickView">Xem nhanh</a>
                                                            <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>

                                    </div><!-- hp-right -->
                                </div><!-- /hdhome-product -->
                            </div><!-- /hdbox-content -->
                        </div><!-- /hdbox -->
                    </c:if>
                    <c:if test="${stt.index%2==1}">
                        <div class="hdbox">
                            <div class="hdbox-title">
                                <div class="hdbox-lb">
                                    <label class="lb-name">${cate.name}</label>
                                    <c:if test="${!cate.leaf}">
                                        <div class="hotdeal-submenu">
                                            <span class="hotdeal-iconmenu"></span>
                                            <div class="hs-popout">
                                                <ul>
                                                    <c:forEach items="${categoriesChilds}" var="cateChilds">
                                                        <c:if test="${cateChilds.parentId == cate.id}">
                                                            <li><a href="${baseUrl}/hotdeal/${cateChilds.id}/${text:createAlias(cateChilds.name)}.html">${cateChilds.name}</a></li>
                                                            </c:if>
                                                        </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:if>
                                    <a class="hotdeal-viewall" href="${baseUrl}/hotdeal/${cate.id}/${text:createAlias(cate.name)}.html">
                                        <span class="hotdeal-iconsearch"></span>
                                        Xem tất cả
                                    </a>
                                </div><!-- /hdbox-lb -->
                            </div><!-- /hdbox-title -->
                            <div class="hdbox-content">
                                <div class="hdhome-product hp-itemright">
                                    <div class="hp-left">
                                        <c:set var="count" value="0" />
                                        <c:forEach items="${hotdealItems}" var="item">
                                            <c:if test="${!item.special && count < 6 && item.hotdealCategoryId == cate.id}">
                                                <div class="home-item">
                                                    <div class="hoi-inner">
                                                        <c:set var="count" value="${count+1}" />
                                                        <c:if test="${!item.item.discount && item.item.listingType != 'AUCTION' && item.item.startPrice > item.item.sellPrice}">
                                                            <span class="hoi-sale">-${text:percentFormat((item.item.startPrice-item.item.sellPrice)/item.item.startPrice)}%</span>
                                                        </c:if>
                                                        <c:if test="${item.item.discount && item.item.listingType != 'AUCTION'}">
                                                            <span class="hoi-sale">-${text:percentFormat(item.item.discountPrice>0?item.item.discountPrice/item.item.sellPrice:item.item.discountPercent/100)}%</span>
                                                        </c:if>
                                                        <a class="hoi-thumblink" href="${baseUrl}/san-pham/${item.item.id}/${text:createAlias(item.item.name)}.html">
                                                            <img src="${(item.image != null && item.image != '')?item.image:staticUrl.concat('/market/images/no-image-product.png')}"  alt="${item.item.name}" title="${item.item.name}"/>
                                                        </a>
                                                        <div class="hoi-title">
                                                            <div class="hoi-row"><a href="${baseUrl}/san-pham/${item.item.id}/${text:createAlias(item.item.name)}.html">${item.item.name}</a></div>
                                                            <div class="hoi-row">

                                                                <c:if test="${!item.item.discount}">
                                                                    <span class="hoi-price">${text:numberFormat(item.item.sellPrice)} <sup class="u-price">đ</sup></span>
                                                                    <c:if test="${item.item.startPrice > item.item.sellPrice}">
                                                                        <span class="hoi-oldprice">${text:numberFormat(item.item.startPrice)} <sup class="u-price">đ</sup></span>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${item.item.discount}">
                                                                    <span class="hoi-price">${text:numberFormat(item.item.discountPrice>0?item.item.sellPrice-item.item.discountPrice:item.item.sellPrice*(100-item.item.discountPercent)/100)} <sup class="u-price">đ</sup></span>
                                                                    <span class="hoi-oldprice">${text:numberFormat(item.item.sellPrice)} <sup class="u-price">đ</sup></span>
                                                                </c:if>
                                                                <c:if test="${item.item.listingType=='AUCTION'}">
                                                                    <span class="icon20-bidgray"></span>
                                                                    <span class="hoi-price">${text:numberFormat(item.item.highestBid)} <sup class="u-price">đ</sup></span>
                                                                    <span class="bid-count">(${item.item.bidCount} lượt)</span>
                                                                </c:if>
                                                                <div class="hoi-icon">
                                                                    <c:if test="${item.item.onlinePayment}">
                                                                        <span class="icon16-nlgray"></span>
                                                                    </c:if>
                                                                    <c:if test="${item.item.cod}">
                                                                        <span class="icon16-codgray"></span>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="hoi-view">
                                                            <a class="hoi-btn" href="javascript:void(0);" onclick="market.quickview('${item.item.id}')" data-toggle="modal" data-target="#ModalQuickView">Xem nhanh</a>
                                                            <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:forEach>

                                    </div><!-- /hp-left -->
                                    <div class="hp-right">
                                        <div class="home-item hdbig-item">
                                            <c:forEach items="${hotdealItems}" var="item">
                                                <c:if test="${item.special && item.hotdealCategoryId == cate.id}">
                                                    <div class="hoi-inner">
                                                        <c:if test="${!item.item.discount && item.item.listingType != 'AUCTION' && item.item.startPrice > item.item.sellPrice}">
                                                            <span class="hoi-sale">-${text:percentFormat((item.item.startPrice-item.item.sellPrice)/item.item.startPrice)}%</span>
                                                        </c:if>
                                                        <c:if test="${item.item.discount && item.item.listingType != 'AUCTION'}">
                                                            <span class="hoi-sale">-${text:percentFormat(item.item.discountPrice>0?item.item.discountPrice/item.item.sellPrice:item.item.discountPercent/100)}%</span>
                                                        </c:if>
                                                        <a class="hoi-thumblink" href="${baseUrl}/san-pham/${item.item.id}/${text:createAlias(item.item.name)}.html">
                                                            <img src="${(item.image != null && item.image != '')?item.image:staticUrl.concat('/market/images/no-image-product.png')}"  alt="${item.item.name}" title="${item.item.name}"/>
                                                        </a>
                                                        <div class="hoi-title">
                                                            <div class="hoi-row"><a href="${baseUrl}/san-pham/${item.item.id}/${text:createAlias(item.item.name)}.html">${item.item.name}</a></div>
                                                            <div class="hoi-row">

                                                                <c:if test="${!item.item.discount}">
                                                                    <span class="hoi-price">${text:numberFormat(item.item.sellPrice)} <sup class="u-price">đ</sup></span>
                                                                    <c:if test="${item.item.startPrice > item.item.sellPrice}">
                                                                        <span class="hoi-oldprice">${text:numberFormat(item.item.startPrice)} <sup class="u-price">đ</sup></span>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${item.item.discount}">
                                                                    <span class="hoi-price">${text:numberFormat(item.item.discountPrice>0?item.item.sellPrice-item.item.discountPrice:item.item.sellPrice*(100-item.item.discountPercent)/100)} <sup class="u-price">đ</sup></span>
                                                                    <span class="hoi-oldprice">${text:numberFormat(item.item.sellPrice)} <sup class="u-price">đ</sup></span>
                                                                </c:if>
                                                                <c:if test="${item.item.listingType=='AUCTION'}">
                                                                    <span class="icon20-bidgray"></span>
                                                                    <span class="hoi-price">${text:numberFormat(item.item.highestBid)} <sup class="u-price">đ</sup></span>
                                                                    <span class="bid-count">(${item.item.bidCount} lượt)</span>
                                                                </c:if>
                                                                <div class="hoi-icon">
                                                                    <c:if test="${item.item.onlinePayment}">
                                                                        <span class="icon16-nlgray"></span>
                                                                    </c:if>
                                                                    <c:if test="${item.item.cod}">
                                                                        <span class="icon16-codgray"></span>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="hoi-view">
                                                            <a class="hoi-btn" href="javascript:void(0);" onclick="market.quickview('${item.item.id}')" data-toggle="modal" data-target="#ModalQuickView">Xem nhanh</a>
                                                            <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div><!-- home-item -->
                                    </div><!-- hp-right -->
                                </div><!-- /hdhome-product -->
                            </div><!-- /hdbox-content -->
                        </div><!-- /hdbox -->
                    </c:if>
                </c:forEach>
            </div><!-- /page-hotdeal -->
        </div><!-- /bg-white -->
    </div><!-- /bground -->
</div><!-- container -->