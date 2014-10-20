<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<c:forEach items="${featuredcategory.categorySubs}" var="categorySubItem">
    <div class="box box-blue ipad-hidden">
        <div class="box-title">
            <label class="lb-name">${featuredcategory.categoryName}</label>
            <div class="pull-right">
                <ul class="${featuredcategory.id}">
                    <c:forEach items="${featuredcategory.categorySubHome}" var="cate">
                        <li <c:if test="${cate.primary}"> class="active"</c:if> id=""><a href="javascript:;" class="tab${cate.id}" onclick="market.loadtab('${featuredcategory.id}', '${cate.id}');">${cate.name}</a></li>
                        </c:forEach>
                </ul>
            </div>
        </div><!-- box-title -->
        <div class="box-content" id="${featuredcategory.id}">
            <div class="box-product bp-template3">
                <div class="bp-banner3">
                    <c:forEach items="${categorySubItem.categoryBannerHomes}" var="banner">
                        <c:if test="${banner.position==4}">
                            <a href="${banner.url}"><img src="${banner.image}" alt="banner" rel="nofollow"></a>
                            </c:if>
                        </c:forEach>
                </div>
                <div class="home-category bp-cat1">
                    <c:forEach items="${categorySubItem.categoryBannerHomes}" var="banner">
                        <c:if test="${banner.position==1}">
                            <a href="${banner.url}"><img src="${banner.image}" alt="banner" rel="nofollow"></a>
                            </c:if>
                        </c:forEach>
                </div><!-- home-category -->
                <div class="home-category bp-cat2">
                    <c:forEach items="${categorySubItem.categoryBannerHomes}" var="banner">
                        <c:if test="${banner.position==2}">
                            <a href="${banner.url}"><img src="${banner.image}" alt="banner" rel="nofollow"></a>
                            </c:if>
                        </c:forEach>
                </div><!-- home-category -->
                <div class="home-category bp-cat3">
                    <c:forEach items="${categorySubItem.categoryBannerHomes}" var="banner">
                        <c:if test="${banner.position==3}">
                            <a href="${banner.url}"><img src="${banner.image}" alt="banner" rel="nofollow"></a>
                            </c:if>
                        </c:forEach>
                </div><!-- home-category -->
                <c:forEach items="${categorySubItem.categoryItemHomes}" var="categoryItemHome">
                    <c:forEach items="${featuredcategory.items}" var="item">
                        <c:if test="${categoryItemHome.position==1}">
                            <c:if test="${item.id==categoryItemHome.itemId}">
                                <div class="home-item bp1">
                                    <div class="hoi-inner">
                                        <c:if test="${item.listingType=='AUCTION'}">
                                            <span class="hoi-bid"></span>
                                        </c:if>
                                        <c:if test="${item.listingType == 'BUYNOW' && (text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))>0}">
                                            <span class="hoi-sale">-${text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent)}%</span>
                                        </c:if> 
                                        <a class="hoi-thumblink" href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html"><img src="${categoryItemHome.image}" alt="product"></a>
                                        <div class="hoi-title">
                                            <div class="hoi-row"><a href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">${categoryItemHome.title}</a></div>
                                            <div class="hoi-row"><span class="hoi-price">${text:sellPrice(item.sellPrice,item.discount,item.discountPrice,item.discountPercent)} <sup class="u-price">đ</sup></span>
                                                <c:if test="${(text:discountPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))!='0'}">
                                                    <span class="hoi-oldprice">${text:startPrice(item.startPrice,item.sellPrice,item.discount)} <sup class="u-price">đ</sup></span>
                                                </c:if>    
                                            </div>
                                        </div>
                                        <div class="hoi-view">
                                            <a class="hoi-btn" href="javascript:;" onclick="market.quickview('${item.id}')">Xem nhanh</a>
                                            <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                        </div>
                                    </div>
                                </div><!-- home-item -->
                            </c:if>
                        </c:if>
                        <c:if test="${categoryItemHome.position==2}">
                            <c:if test="${item.id==categoryItemHome.itemId}">
                                <div class="home-item bp2">
                                    <div class="hoi-inner">
                                        <c:if test="${item.listingType=='AUCTION'}">
                                            <span class="hoi-bid"></span>
                                        </c:if>
                                        <c:if test="${item.listingType == 'BUYNOW' && (text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))>0}">
                                            <span class="hoi-sale">-${text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent)}%</span>
                                        </c:if> 
                                        <a class="hoi-thumblink" href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html"><img src="${categoryItemHome.image}" alt="product"></a>
                                        <div class="hoi-title">
                                            <div class="hoi-row"><a href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">${categoryItemHome.title}</a></div>
                                            <div class="hoi-row"><span class="hoi-price">${text:sellPrice(item.sellPrice,item.discount,item.discountPrice,item.discountPercent)} <sup class="u-price">đ</sup></span>
                                                <c:if test="${(text:discountPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))!='0'}">
                                                    <span class="hoi-oldprice">${text:startPrice(item.startPrice,item.sellPrice,item.discount)} <sup class="u-price">đ</sup></span>
                                                </c:if>    
                                            </div>
                                        </div>
                                        <div class="hoi-view">
                                            <a class="hoi-btn" href="javascript:;" onclick="market.quickview('${item.id}')">Xem nhanh</a>
                                            <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                        </div>
                                    </div>
                                </div><!-- home-item -->
                            </c:if>
                        </c:if>
                        <c:if test="${categoryItemHome.position==3}">
                            <c:if test="${item.id==categoryItemHome.itemId}">
                                <div class="home-item bp3">
                                    <div class="hoi-inner">
                                        <c:if test="${item.listingType=='AUCTION'}">
                                            <span class="hoi-bid"></span>
                                        </c:if>
                                        <c:if test="${item.listingType == 'BUYNOW' && (text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))>0}">
                                            <span class="hoi-sale">-${text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent)}%</span>
                                        </c:if> 
                                        <a class="hoi-thumblink" href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html"><img src="${categoryItemHome.image}" alt="product"></a>
                                        <div class="hoi-title">
                                            <div class="hoi-row"><a href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">${categoryItemHome.title}</a></div>
                                            <div class="hoi-row"><span class="hoi-price">${text:sellPrice(item.sellPrice,item.discount,item.discountPrice,item.discountPercent)} <sup class="u-price">đ</sup></span>
                                                <c:if test="${(text:discountPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))!='0'}">
                                                    <span class="hoi-oldprice">${text:startPrice(item.startPrice,item.sellPrice,item.discount)} <sup class="u-price">đ</sup></span>
                                                </c:if>    
                                            </div>
                                        </div>
                                        <div class="hoi-view">
                                            <a class="hoi-btn" href="javascript:;" onclick="market.quickview('${item.id}')">Xem nhanh</a>
                                            <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                        </div>
                                    </div>
                                </div><!-- home-item -->
                            </c:if>
                        </c:if>
                        <c:if test="${categoryItemHome.position==4}">
                            <c:if test="${item.id==categoryItemHome.itemId}">
                                <div class="home-item bp4">
                                    <div class="hoi-inner">
                                        <c:if test="${item.listingType=='AUCTION'}">
                                            <span class="hoi-bid"></span>
                                        </c:if>
                                        <c:if test="${item.listingType == 'BUYNOW' && (text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))>0}">
                                            <span class="hoi-sale">-${text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent)}%</span>
                                        </c:if> 
                                        <a class="hoi-thumblink" href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html"><img src="${categoryItemHome.image}" alt="product"></a>
                                        <div class="hoi-title">
                                            <div class="hoi-row"><a href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">${categoryItemHome.title}</a></div>
                                            <div class="hoi-row"><span class="hoi-price">${text:sellPrice(item.sellPrice,item.discount,item.discountPrice,item.discountPercent)} <sup class="u-price">đ</sup></span>
                                                <c:if test="${(text:discountPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))!='0'}">
                                                    <span class="hoi-oldprice">${text:startPrice(item.startPrice,item.sellPrice,item.discount)} <sup class="u-price">đ</sup></span>
                                                </c:if>    
                                            </div>
                                        </div>
                                        <div class="hoi-view">
                                            <a class="hoi-btn" href="javascript:;" onclick="market.quickview('${item.id}')">Xem nhanh</a>
                                            <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                        </div>
                                    </div>
                                </div><!-- home-item -->
                            </c:if>
                        </c:if>
                    </c:forEach> </c:forEach>
                    <div class="home3-tabouter">
                        <div class="tab-outer">
                            <div class="tab-title">
                            <c:forEach items="${categorySubItem.categoryManufacturerHomes}" var="logo" varStatus="stt">
                                <a href="javascript:;" onclick="market.loadmodel(${categorySubItem.categorySubId},${logo.position}, this)" <c:if test="${stt.index+1==1}"> class="active" </c:if>>
                                    <img src="${logo.image}" alt="logo"><span class="home3-bullet"></span>
                                </a>
                            </c:forEach>
                        </div>
                        <div class="tab-container" id="viewModel">
                            <div id="home3tab1" class="tab-content" style="display:block;">

                                <c:forEach items="${featuredcategory.models}" var="model" begin="0" end="0">
                                    <div class="home3-product">
                                        <div class="hp-thumb"><a href="${basicUrl}/model/${model.id}/${text:createAlias(model.name)}.html"><img src="${model.images[0]}" alt="${model.id}"></a></div>
                                        <div class="hp-row">Model: ${model.name}</div>
                                        <c:if test="${model.oldMinPrice>0 || model.newMinPrice>0}">
                                            <c:set value="${model.newMinPrice>0?model.newMinPrice:0}" var="priceNewMin"></c:set>
                                            <c:set value="${model.oldMinPrice>0?model.oldMinPrice:0}" var="priceOldMin"></c:set>
                                            <c:if test="${priceOldMin>0}">
                                                <c:set value="${priceOldMin}" var="priceTK"></c:set>
                                            </c:if>
                                            <c:if test="${priceNewMin>0}">
                                                <c:set value="${priceNewMin}" var="priceTK"></c:set>
                                            </c:if>
                                            <c:if test="${model.oldMinPrice>0 && model.newMinPrice>0}">
                                                <c:set value="${priceNewMin>priceOldMin?priceOldMin:priceNewMin}" var="priceTK"></c:set>
                                            </c:if>
                                            <div class="hp-row">Giá tham khảo: <span class="hp-price">${text:numberFormat(priceTK)}<sup class="u-price">đ</sup></span></div>
                                        </c:if>
                                        <div class="hp-row">Hiện có: <a href="${baseUrl}${url:browseUrl(itemSearch, featuredcategory.categoryName, '[{key:"cid",op:"rm"},{op:"mk",key:"models",val:"'.concat(model.id).concat('"}]'))}">${model.countShop} cửa hàng đang bán</a></div>
                                    </div>
                                </c:forEach>
                                <div class="home3-line"></div>
                                <ul class="home3-ul">
                                    <c:forEach items="${featuredcategory.models}" var="model" begin="1" end="4">
                                        <li><a href="${basicUrl}/model/${model.id}/${text:createAlias(model.name)}.html">${model.name}</a></li>
                                        </c:forEach>
                                </ul>
                            </div><!-- /tab-content -->

                        </div><!-- /tab-container -->
                    </div><!-- /tab-outer -->
                </div><!-- home3-tabouter -->
            </div><!-- box-product -->
        </div><!-- box-content -->
    </div>
</c:forEach>