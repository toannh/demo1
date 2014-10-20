<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld" %>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view" xmlns:v="http://rdf.data-vocabulary.org/#"  >
            <a class="home-button" href="${baseUrl}"></a>
            <span class="tree-normal"></span>
            <a href="${baseUrl}/tim-kiem.html">Sản phẩm</a>
            <c:forEach items="${parentCategories}" var="pCate">
                <c:if test="${pCate.id == category.id}">
                    <span class="tree-before"></span>
                    <span typeof="v:Breadcrumb" >
                        <a rel="v:url" property="v:title" title="${pCate.name}" class="last-item" href="${baseUrl}${url:browseUrl(itemSearch, pCate.name, '[{op:"mk",key:"cid",val:"'.concat(pCate.id).concat('"},{key:"order",val:"0"},{key:"page",val:"0"}]'))}">
                            ${pCate.name}
                        </a>
                    </span>
                </c:if>
                <c:if test="${pCate.id != category.id}">
                    <span class="tree-normal"></span>
                    <span typeof="v:Breadcrumb" >
                        <a rel="v:url" property="v:title" title="${pCate.name}" href="${baseUrl}${url:browseUrl(itemSearch, pCate.name, '[{op:"mk",key:"cid",val:"'.concat(pCate.id).concat('"},{key:"order",val:"0"},{key:"page",val:"0"}]'))}">${pCate.name}</a>
                    </span>
                </c:if>
            </c:forEach>
            <span class="tree-after"></span>
            <c:if test="${fn:length(itemSearch.manufacturerIds) > 0}">
                <c:forEach items="${itemSearch.manufacturerIds}" var="manId">
                    <div style="display:none" for="manufacturer" val="${manId}" class="filter-item"></div>
                </c:forEach>
            </c:if>
            <c:if test="${fn:length(itemSearch.modelIds) > 0}">
                <c:forEach items="${itemSearch.modelIds}" var="moId">
                    <div style="display:none" for="model" val="${moId}" class="filter-item"></div>
                </c:forEach>
            </c:if>
            <c:if test="${fn:length(itemSearch.cityIds) > 0}">
                <c:forEach items="${itemSearch.cityIds}" var="ciId">
                    <div style="display:none" for="city" val="${ciId}" class="filter-item"></div>
                </c:forEach>
            </c:if>
            <c:if test="${fn:length(itemSearch.properties) > 0}">
                <c:forEach items="${itemSearch.properties}" var="property">
                    <div for="property" name="${property.name}" value="${property.value}" operator="${property.operator}" class="filter-item">
                        <span class="filter-lb">${property.name}: ${property.value}</span>
                        <span class="filter-remove"></span>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${itemSearch.condition == 'NEW'}">
                <div class="filter-item"><span class="filter-lb">Hàng mới</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"condition",val:"0"}]')}'" class="filter-remove"></span></div>
                </c:if>
                <c:if test="${itemSearch.condition == 'OLD'}">
                <div class="filter-item"><span class="filter-lb">Hàng cũ</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"condition",val:"0"}]')}'" class="filter-remove"></span></div>
                </c:if>
                <c:if test="${itemSearch.listingType == 'BUYNOW'}">
                <div class="filter-item"><span class="filter-lb">Mua ngay</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"type",val:"0"}]')}'" class="filter-remove"></span></div>
                </c:if>
                <c:if test="${itemSearch.listingType == 'AUCTION'}">
                <div class="filter-item"><span class="filter-lb">Đấu giá</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"type",val:"0"}]')}'" class="filter-remove"></span></div>
                </c:if>
                <c:if test="${itemSearch.freeShip}">
                <div class="filter-item"><span class="filter-lb">Miễn phí vận chuyển</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"freeship",op:"rm"}]')}'" class="filter-remove"></span></div>
                </c:if>
                <c:if test="${itemSearch.cod}">
                <div class="filter-item"><span class="filter-lb">Giao hàng thu tiền</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"cod",op:"rm"}]')}'" class="filter-remove"></span></div>
                </c:if>
                <c:if test="${itemSearch.onlinePayment}">
                <div class="filter-item"><span class="filter-lb">Thanh toán Ngân Lượng</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"onlinepayment",op:"rm"}]')}'" class="filter-remove"></span></div>
                </c:if>
                <c:if test="${itemSearch.promotion}">
                <div class="filter-item"><span class="filter-lb">Có khuyến mại</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"promotion",op:"rm"}]')}'" class="filter-remove"></span></div>
                </c:if>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <c:if test="${topBanner != null}">
        <div class="topbanner">
            <!-- 990x90 -->
            <!--<div id='div-gpt-ad-1409797788286-0' style='width:990px; height:90px;'>-->
            <script type='text/javascript'>
//                    googletag.cmd.push(function() {
//                        googletag.display('div-gpt-ad-1409797788286-0');
//                    });
            </script>
            <!--</div>-->
            <a href="${topBanner.link}"><img src="${topBanner.banner}" alt="${topBanner.title}" /></a>
        </div>
    </c:if>
    <c:if test="${itemSearch.keyword != null && itemSearch.keyword !='' && itemPage.dataCount<=0}">
        <div class="search-key-search cdt-message bg-warning">Sản phẩm "<b>${itemSearch.keyword}</b>" không có trên hệ thống sàn của ChợĐiệnTử. Bạn có thể tìm kiếm sản phẩm khác</div>
    </c:if>
    <div class="bground">
        <div class="bg-white">
            <div class="sidebar">
                <div class="big-label">
                    <div class="bl-inner">
                        <h3>
                            <c:if test="${category.leaf}">
                                <c:forEach items="${parentCategories}" var="pCate">
                                    <c:if test="${pCate.id == category.parentId}">
                                        ${pCate.name}
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${!category.leaf}">
                                ${category.name}
                            </c:if>
                        </h3>
                    </div>
                </div>
                <div class="menuleft">
                    <ul>
                        <c:forEach items="${listCategories}" var="cate">
                            <li for="${cate.id}" class="<c:if test="${!cate.leaf}" >sub-menu</c:if><c:if test="${category.id == cate.id}" > active</c:if>">
                                <a href="${baseUrl}${url:browseUrl(itemSearch, cate.name, '[{op:"mk",key:"cid",val:"'.concat(cate.id).concat('"},{key:"order",val:"0"},{key:"page",val:"0"}]'))}">${cate.name}<span></span></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="pbox">
                    <div class="pbox-title"><label class="lb-name">Giá</label></div>
                    <div class="pbox-content">
                        <div class="box-check">
                            <div class="row">
                                <input for="priceFrom" <c:if test="${itemSearch.priceFrom > 0}">value="${itemSearch.priceFrom}"</c:if> type="text" class="from-text" placeholder="Từ" />
                                    &nbsp;-&nbsp;
                                    <input for="priceTo" <c:if test="${itemSearch.priceTo > 0}">value="${itemSearch.priceTo}"</c:if> type="text" class="to-text" placeholder="Đến" />
                                    <a onclick="browse.findByPrice()" class="btn-check">Xem</a>
                                </div>
                                <div for="freeShip" param="freeship" class="row"><input type="checkbox" id="freeship" /><label for="freeship"><span></span><b>Miễn phí vận chuyển</b></label></div>
                                <div for="promotion" param="promotion" class="row"><input type="checkbox" id="promotion" /><label for="promotion"><span></span><b>Có giảm giá</b></label></div>
                                <div for="cod" param="cod" class="row"><input type="checkbox" id="cod" /><label for="cod"><span></span><b>COD</b></label></div>
                                <div for="onlinePayment" param="onlinepayment" class="row"><input type="checkbox" id="onlinepayment" /><label for="onlinepayment"><span></span><b>TT NgânLượng</label></b></div>
                            </div><!--box-check-->
                        </div><!--pbox-content-->
                    </div><!--pbox-->
                    <div class="pbox" for="manufacturer">
                    </div><!--pbox-->
                    <div class="pbox" for="model">
                    </div><!--pbox-->
                    <div class="pbox">
                        <div class="pbox-title"><label class="lb-name">Tình trạng</label></div>
                        <div class="pbox-content">
                            <div class="box-check panel-scroll">
                                <div for="condition" param="condition" val="NEW" class="row"><input id="condition_new" type="checkbox" /><label for="condition_new"><span></span><b>Mới </b></label></div>
                                <div for="condition" param="condition" val="OLD" class="row"><input id="condition_old" type="checkbox" /><label for="condition_old"><span></span><b>Cũ </b></label></div>
                            </div><!--box-check-->
                        </div><!--pbox-content-->
                    </div><!--pbox-->
                    <div class="pbox">
                        <div class="pbox-title"><label class="lb-name">Loại hình</label></div>
                        <div class="pbox-content">
                            <div class="box-check panel-scroll">
                                <div for="listingType" param="type" val="AUCTION" class="row"><input id="listingtype_aution" type="checkbox" /><label for="listingtype_auction">Đấu giá <span></span></label></div>
                                <div for="listingType" param="type" val="BUYNOW" class="row"><input id="listingtype_buynow" type="checkbox" /><label for="listingtype_buynow">Mua ngay <span></span></label></div>
                            </div><!--box-check-->
                        </div><!--pbox-content-->
                    </div><!--pbox-->
                    <div class="pbox" for="city"></div><!--pbox-->
                    <div class="pbox more-filter" for="property"></div><!--pbox-->
                <c:if test="${category.metaDescription != null && category.metaDescription!=''}"><div class="panel-intro">${category.metaDescription}</div></c:if><!--pannel-intro-->
                    <div class="sidebar-banner">
                        <!-- banner place -->
                        <!-- 200x200 -->
                        <div id='div-gpt-ad-1383805719385-0' style='width:200px; height:200px;'>
                            <script type='text/javascript'>
                                googletag.cmd.push(function () {
                                    googletag.display('div-gpt-ad-1383805719385-0');
                                });
                            </script>
                        </div>
                    </div><!-- sidebar-banner -->
                </div><!-- sidebar -->
                <div class="main">

                <c:if test="${contentBanner != null}">
                    <div class="banner-category">
                        <a href="${contentBanner.link}"><img src="${contentBanner.banner}" alt="${contentBanner.title}" /></a>
                    </div> <!--banner-category -->
                </c:if>

                <c:if test="${itemSearch.sellerId != null && seller!=null}">
                    <div class="product-user-info">
                        <div class="sf-row">Tất cả sản phẩm từ người bán:</div>
                        <div class="sf-row">
                            <div class="pui-avatar">
                                <img src="${(seller.avatar == null || seller.avatar=='')?('/static/lib/no-avatar.png'):seller.avatar}" alt="avatar" />
                            </div>
                            <b>${(seller.username == null || seller.username=="")?seller.email:seller.username}</b>
                        </div>
                        <div class="sf-row"><div class="star-outer"><span class="icon-star"></span></div>Điểm uy tín: <span class="text-primary">${sellerReview.totalPoint}</span></div>
                        <div class="sf-row"><span class="icon16-like-green"></span>Đánh giá tốt: <span class="text-primary"><fmt:formatNumber value="${sellerReview.good * 100/((sellerReview.good + sellerReview.normal + sellerReview.bad)<=0?1:(sellerReview.good + sellerReview.normal + sellerReview.bad))}" pattern="0" />%</span></div>
                        <div class="sf-right">
                            <div class="pd-like-cdt"><a onclick="browse.followSeller('${seller.id}');" class="btn-like-cdt" href="javascript:;">Quan tâm</a><span class="like-count">${sellerFollowCount}</span></div>
                        </div>

                    </div><!-- product-user-info -->
                </c:if>
                <div class="mbox">
                    <div class="mbox-title full-tab">
                        <ul>
                            <li class="active"><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{op:"rm",key:"cod"},{op:"rm",key:"promotion"},{op:"rm",key:"page"},{op:"rm",key:"order"}]')}">Sản phẩm <span>(${text:numberFormat(itemPage.dataCount)})</span></a></li>
                            <li><a href="${baseUrl}${url:browseToModel(itemSearch, category.name)}" >Model <span class="spanModelCount"></span></a></li>
                            <li for="discountCount"><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{op:"rm",key:"cod"},{op:"mk",key:"promotion"},{op:"rm",key:"page"},{op:"rm",key:"order"}]')}" >Có giảm giá <span></span></a></li>
                            <li for="codCount">
                                <a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{op:"mk",key:"cod"},{op:"rm",key:"promotion"},{op:"rm",key:"page"},{op:"rm",key:"order"}]')}" >Có COD <span></span></a>
                                <div class="tab-hind">
                                    <span class="tab-hind-bullet"></span>
                                    <div class="tab-hind-content">Hàng có áp dụng CoD (Giao hàng Thu tiền)</div>
                                </div>
                            </li>
                        </ul>
                    </div><!-- mbox-title -->

                    <div class="mbox-content">
                        <div class="bv-control">
                            <ul>
                                <c:if test="${vipPages.pageCount > 0}"><li class="active"><a>1</a></li></c:if>
                                <c:if test="${vipPages.pageCount > 1}"><li><a onclick="browse.loadVips(1);">1</a></li></c:if>
                                <c:if test="${vipPages.pageCount > 2}"><li><a onclick="browse.loadVips(2);">2</a></li></c:if>
                                <c:if test="${vipPages.pageCount > 1}"><li class="next"><a onclick="browse.loadVips(1);">&gt;</a></li></c:if>
                                </ul>
                            </div><!-- bv-control -->
                            <div class="browser-vip">
                                <span class="vip-lb"></span>
                            <c:if test="${vips!= null && fn:length(vips)>0}">
                                <c:forEach items="${vips}" var="item" >
                                    <div class="product-item">
                                        <div class="big-shadow">
                                            <c:if test="${item.listingType != 'AUCTION' && text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent) != '0'}">
                                                <span class="item-sale">${text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent)}%</span>
                                            </c:if>
                                            <a class="view-now" href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">Xem ngay</a>
                                            <div class="item-img">
                                                <a href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">
                                                    <img src="${(item.images != null && fn:length(item.images) >0)?item.images[0]:staticUrl.concat('/market/images/no-image-product.png')}"  alt="${item.name}" title="${item.name}"/>
                                                </a>
                                            </div>
                                            <div class="item-text">
                                                <a class="item-link" href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">
                                                    <c:forEach items="${cities}" var="city" >                                                        
                                                        <c:if test="${city.id == item.cityId}">
                                                            <span>${city.name}:&nbsp;</span>
                                                        </c:if>
                                                    </c:forEach>
                                                    ${item.name}
                                                </a>
                                                <div class="item-row">
                                                    <c:if test="${item.listingType=='BUYNOW'}">
                                                        <span class="item-price">${text:sellPrice(item.sellPrice, item.discount, item.discountPrice, item.discountPercent)} <sup class="u-price">đ</sup></span>
                                                        <c:if test="${text:startPrice(item.startPrice, item.sellPrice, item.discount) != '0'}">
                                                            <span class="old-price">${text:startPrice(item.startPrice, item.sellPrice, item.discount)} <sup class="u-price">đ</sup></span>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${item.listingType=='AUCTION'}">
                                                        <span class="icon20-bidgray"></span>
                                                        <span class="item-price">${text:numberFormat(item.highestBid<=0?item.startPrice:item.highestBid)} <sup class="u-price">đ</sup></span>
                                                        <span class="bid-count">(${item.bidCount} lượt)</span>
                                                    </c:if>
                                                </div>
                                                <div class="item-row bg-item">
                                                    <div class="item-shop">
                                                        <c:set var="hasShop" value="false" /> 
                                                        <c:forEach items="${shops}" var="shop">
                                                            <c:if test="${item.sellerId==shop.userId}">
                                                                <c:set var="hasShop" value="true" />
                                                                <span class="icon16-shop"></span>
                                                                <span class="shop-lb">Shop:</span>
                                                                <c:if test="${fn:length(shop.title)>75}"><a href="${baseUrl}/${shop.alias}/">${shop.alias}</a></c:if>
                                                                <c:if test="${fn:length(shop.title)<=75}"><a href="${baseUrl}/${shop.alias}/">${shop.title}</a></c:if>

                                                            </c:if>
                                                        </c:forEach>
                                                        <c:if test="${!hasShop}">
                                                            <span class="icon16-usernormal"></span>
                                                            <a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"0"},{key:"cid",op:"rm"},{key:"order",val:"0"},{key:"sellerId",op:"mk",val:"'.concat(item.sellerId).concat('"}]'))}">${item.sellerName}</a>
                                                        </c:if>
                                                        <c:if test="${item.condition=='NEW'}">
                                                            <span class="item-status">(Hàng mới)</span>
                                                        </c:if>
                                                        <c:if test="${item.condition=='OLD'}">
                                                            <span class="item-status">(Hàng cũ)</span>
                                                        </c:if>
                                                    </div>
                                                    <div class="item-icon">
                                                        <c:if test="${item.onlinePayment}">
                                                            <p>
                                                                <span class="icon16-nlgray"></span>
                                                                <span class="icon-desc">Thanh toán qua NgânLượng</span>
                                                            </p>
                                                        </c:if>
                                                        <c:if test="${item.cod}">
                                                            <p>
                                                                <span class="icon16-codgray"></span>
                                                                <span class="icon-desc">Giao hàng và thu tiền</span>
                                                            </p>
                                                        </c:if>
                                                        <c:if test="${item.shipmentType == 'BYWEIGHT' && item.weight < 2000}">
                                                            <p>
                                                                <span class="icon16-transgray"></span>
                                                                <span class="icon-desc">Miễn phí vận chuyển</span>
                                                            </p>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                            <ul class="item-hover">
                                                <c:if test="${item.images != null && fn:length(item.images) > 1}">
                                                    <c:forEach var="image" items="${item.images}" end="4">
                                                        <li>
                                                            <span><img src="${image}" alt="item" /></span>
                                                        </li>
                                                    </c:forEach>
                                                </c:if>
                                            </ul>
                                        </div><!--big-shadow-->
                                    </div><!--product-item-->
                                </c:forEach>
                            </c:if>
                            <c:if test="${fn:length(vips) < 4}">
                                <c:forEach begin="0" end="${3 - fn:length(vips)}">
                                    <div class="product-item item-vip">
                                        <div class="big-shadow">
                                            <div class="item-img"><a href="${baseUrl}/user/vipitem.html"><img src="${staticUrl}/market/images/item-vip.jpg" alt="item vip" /></a></div>
                                            <div class="item-text">
                                                <div class="item-row">
                                                    <span class="item-price">30.000 Xèng</span>
                                                </div>
                                                <div class="item-row">
                                                    (Giá trị khoảng: <b>30.000 đ</b>)
                                                </div>
                                                <div class="item-row bg-item">
                                                    <a class="iv-buy" href="${baseUrl}/user/vipitem.html">Mua ngay</a>
                                                </div>
                                            </div>
                                        </div><!--big-shadow-->
                                    </div><!--product-item-->
                                </c:forEach>
                            </c:if>
                            <div class="clearfix"></div>
                        </div><!-- browser-vip -->
                        <div class="internal-top">
                            <c:if test="${itemSearch.sellerId != null && itemSearch.sellerId !='' && seller !=null}">
                                <h1>${category.name}</h1>
                            </c:if>
                            <c:if test="${itemSearch.sellerId == null || itemSearch.sellerId =='' || seller ==null}">
                                <c:if test="${itemSearch.keyword != null && itemSearch.keyword!=''}">
                                    <h1>Xem ${itemSearch.keyword} tại danh mục ${category.name} liên tục cập nhật nhật, giá rẻ.</h1>
                                </c:if>
                                <c:if test="${itemSearch.keyword == null || itemSearch.keyword==''}">
                                    <h1>Xem sản phẩm ${category.name} liên tục cập nhật, giá rẻ.</h1>
                                </c:if>
                            </c:if>
                            <div class="internal-related">
                                Từ khoá: 
                                <c:forEach var="keyword" items="${keywordCommon}">
                                    <a href="${keyword.url}" target="_blank">${keyword.keyword}</a>
                                </c:forEach>
                            </div>
                        </div>
                        <c:if test="${itemPage.dataCount<=0}">
                            <div class="hotdeal hotdeal-widget">
                                <div class="hotdeal-title"><a href="${basicUrl}/hotdeal.html" class="lb-name">Hot<span>deal</span></a></div>
                                <div class="hotdeal-content">
                                    <span class="hotdeal-new"></span>
                                    <div class="hotdeal-list">
                                        <c:forEach items="${hotDealBox}" var="hotdeal">
                                            <div class="hotdeal-item">
                                                <c:if test="${hotdeal.listingType == 'BUYNOW' && (text:percentPrice(hotdeal.startPrice,hotdeal.sellPrice,hotdeal.discount,hotdeal.discountPrice,hotdeal.discountPercent))>0}">
                                                    <span class="hi-sale"><div class="sticker-price">-${text:percentPrice(hotdeal.startPrice,hotdeal.sellPrice,hotdeal.discount,hotdeal.discountPrice,hotdeal.discountPercent)}%</div> </span>
                                                </c:if>

                                                <div class="hi-thumb"><a href="${basicUrl}/san-pham/${hotdeal.id}/${text:createAlias(hotdeal.name)}.html"><img src="${(hotdeal.images != null && fn:length(hotdeal.images) >0)?hotdeal.images[0]:staticUrl.concat('/market/images/no-image-product.png')}" alt="${hotdeal.name}" /></a>
                                                    <div class="hi-view">
                                                        <a class="hi-btn" href="javascript:;" onclick="market.quickview('${hotdeal.id}')" data-toggle="modal" data-target="#ModalQuickView">Xem nhanh</a>
                                                    </div>
                                                </div>
                                                <div class="hi-row"><a class="hi-title" href="${basicUrl}/san-pham/${hotdeal.id}/${text:createAlias(hotdeal.name)}.html">${hotdeal.name}</a></div>
                                                <div class="hi-row"><span class="hi-price">${text:sellPrice(hotdeal.sellPrice,hotdeal.discount,hotdeal.discountPrice,hotdeal.discountPercent)} <sup class="u-price">đ</sup></span>
                                                    <c:if test="${(text:discountPrice(hotdeal.startPrice,hotdeal.sellPrice,hotdeal.discount,hotdeal.discountPrice,hotdeal.discountPercent))!='0'}">
                                                        <span class="hi-oldprice">${text:startPrice(hotdeal.startPrice,hotdeal.sellPrice,hotdeal.discount)} <sup class="u-price">đ</sup></span>
                                                    </c:if>
                                                </div>
                                            </div><!-- hotdeal-item -->
                                        </c:forEach>
                                    </div><!-- hotdeal-list -->
                                    <div class="clearfix"></div>
                                </div><!-- hotdeal-content -->
                            </div><!-- hotdeal -->
                        </c:if>
                        <c:if test="${itemPage.dataCount>0}">
                            <div class="box-control">
                                <label>Hiện <b>${text:numberFormat(itemPage.pageIndex*itemPage.pageSize+1)}-${text:numberFormat((itemPage.dataCount < (itemPage.pageIndex+1)*itemPage.pageSize)?itemPage.dataCount:(itemPage.pageIndex+1)*itemPage.pageSize)}</b> trong <b>${text:numberFormat(itemPage.dataCount)}</b> tin bán theo:</label>
                                <ul>
                                    <li for="order_0"><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"order",val:"0"}]')}">Phù hợp nhất</a></li>
                                    <li for="order_1"><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"order",val:"1"}]')}">Giá giảm dần</a></li>
                                    <li for="order_2"><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"order",val:"2"}]')}">Giá tăng dần</a></li>
                                </ul>
                                <ul class="pagination">
                                    <c:if test="${itemPage.pageIndex > 3}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"1"}]')}" >&laquo;</a></li></c:if>
                                    <c:if test="${itemPage.pageIndex > 2}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageIndex).concat('"}]'))}" >&lt;</a></li></c:if>
                                    <c:if test="${itemPage.pageIndex >= 2}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageIndex-1).concat('"}]'))}" >${itemPage.pageIndex-1}</a></li></c:if>
                                    <c:if test="${itemPage.pageIndex >= 1}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageIndex).concat('"}]'))}" >${itemPage.pageIndex}</a></li></c:if>
                                    <li class="active"><a>${itemPage.pageIndex + 1}</a></li>
                                    <c:if test="${itemPage.pageCount - itemPage.pageIndex >= 2}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageIndex+2).concat('"}]'))}">${itemPage.pageIndex+2}</a></li></c:if>
                                    <c:if test="${itemPage.pageCount - itemPage.pageIndex > 3}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageIndex+3).concat('"}]'))}">${itemPage.pageIndex+3}</a></li></c:if>
                                    <c:if test="${itemPage.pageCount - itemPage.pageIndex > 2}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageIndex+2).concat('"}]'))}">&gt;</a></li></c:if>
                                    <c:if test="${itemPage.pageCount - itemPage.pageIndex > 2}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageCount).concat('"}]'))}">&raquo;</a></li></c:if>
                                    </ul>
                                    <div class="mobile-filter">
                                        <a href="#smart-filter">Lọc sản phẩm</a>
                                    </div>
                                </div><!--box-control-->
                                <div class="product-list">
                                <c:forEach items="${itemPage.data}" var="item"> 
                                    <div class="product-item">
                                        <div class="big-shadow">
                                            <c:if test="${item.listingType != 'AUCTION' && text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent) != '0'}">
                                                <span class="item-sale">${text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent)}%</span>
                                            </c:if>
                                            <a class="view-now" href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html?ref=${ref}${(itemSearch.keyword != null && itemSearch.keyword!='')?('&keyword='.concat(itemSearch.keyword.replaceAll('\\s','\\+'))):''}">Xem ngay</a>
                                            <div class="item-img">
                                                <a href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html?ref=${ref}${(itemSearch.keyword != null && itemSearch.keyword!='')?('&keyword='.concat(itemSearch.keyword.replaceAll('\\s','\\+'))):''}">
                                                    <img src="${(item.images != null && fn:length(item.images) >0)?item.images[0]:staticUrl.concat('/market/images/no-image-product.png')}"  alt="${item.name}" title="${item.name}"/>
                                                </a>
                                            </div>
                                            <div class="item-text">
                                                <a class="item-link" href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html?ref=${ref}${(itemSearch.keyword != null && itemSearch.keyword!='')?('&keyword='.concat(itemSearch.keyword.replaceAll('\\s','\\+'))):''}">
                                                    <c:forEach items="${cities}" var="city" >                                                        
                                                        <c:if test="${city.id == item.cityId}">
                                                            <span>${city.name}:&nbsp;</span>
                                                        </c:if>
                                                    </c:forEach>
                                                    ${item.name}
                                                </a>
                                                <div class="item-row">
                                                    <c:if test="${item.listingType=='BUYNOW'}">
                                                        <span class="item-price">${text:sellPrice(item.sellPrice, item.discount, item.discountPrice, item.discountPercent)} <sup class="u-price">đ</sup></span>
                                                        <c:if test="${text:startPrice(item.startPrice, item.sellPrice, item.discount) != '0'}">
                                                            <span class="old-price">${text:startPrice(item.startPrice, item.sellPrice, item.discount)} <sup class="u-price">đ</sup></span>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${item.listingType=='AUCTION'}">
                                                        <span class="icon20-bidgray"></span>
                                                        <span class="item-price">${text:numberFormat(item.highestBid<=0?item.startPrice:item.highestBid)} <sup class="u-price">đ</sup></span>
                                                        <span class="bid-count">(${item.bidCount} lượt)</span>
                                                    </c:if>
                                                </div>
                                                <div class="item-row bg-item">
                                                    <div class="item-shop">
                                                        <c:set var="hasShop" value="false" /> 
                                                        <c:forEach items="${shops}" var="shop">
                                                            <c:if test="${item.sellerId==shop.userId}">
                                                                <c:set var="hasShop" value="true" />
                                                                <span class="icon16-shop"></span>
                                                                <span class="shop-lb">Shop:</span>
                                                                <c:if test="${fn:length(shop.title)>75}"><a href="${baseUrl}/${shop.alias}/">${shop.alias}</a></c:if>
                                                                <c:if test="${fn:length(shop.title)<=75}"><a href="${baseUrl}/${shop.alias}/">${shop.title}</a></c:if>

                                                            </c:if>
                                                        </c:forEach>
                                                        <c:if test="${!hasShop}">
                                                            <span class="icon16-usernormal"></span>
                                                            <a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"0"},{key:"cid",op:"rm"},{key:"order",val:"0"},{key:"sellerId",op:"mk",val:"'.concat(item.sellerId).concat('"}]'))}">${item.sellerName}</a>
                                                        </c:if>
                                                        <c:if test="${item.condition=='NEW'}">
                                                            <span class="item-status">(Hàng mới)</span>
                                                        </c:if>
                                                        <c:if test="${item.condition=='OLD'}">
                                                            <span class="item-status">(Hàng cũ)</span>
                                                        </c:if>
                                                    </div>
                                                    <div class="item-icon">
                                                        <c:if test="${item.onlinePayment}">
                                                            <p>
                                                                <span class="icon16-nlgray"></span>
                                                                <span class="icon-desc">Thanh toán qua NgânLượng</span>
                                                            </p>
                                                        </c:if>
                                                        <c:if test="${item.cod}">
                                                            <p>
                                                                <span class="icon16-codgray"></span>
                                                                <span class="icon-desc">Giao hàng và thu tiền</span>
                                                            </p>
                                                        </c:if>
                                                        <c:if test="${item.shipmentType == 'BYWEIGHT' && item.weight < 2000}">
                                                            <p>
                                                                <span class="icon16-transgray"></span>
                                                                <span class="icon-desc">Miễn phí vận chuyển</span>
                                                            </p>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                            <ul class="item-hover">
                                                <c:if test="${item.images != null && fn:length(item.images) > 1}">
                                                    <c:forEach var="image" items="${item.images}" end="4">
                                                        <li>
                                                            <span><img  src="${image}" alt="item" /></span>
                                                        </li>
                                                    </c:forEach>
                                                </c:if>
                                            </ul>
                                        </div><!--big-shadow-->
                                    </div><!--product-item-->
                                </c:forEach>
                                <div class="clearfix"></div>
                            </div><!-- product-list -->
                            <div class="box-bottom">
                                <ul class="pagination">
                                    <c:if test="${itemPage.pageIndex > 3}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"1"}]')}" >&laquo;</a></li></c:if>
                                    <c:if test="${itemPage.pageIndex > 2}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageIndex).concat('"}]'))}" >&lt;</a></li></c:if>
                                    <c:if test="${itemPage.pageIndex >= 2}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageIndex-1).concat('"}]'))}" >${itemPage.pageIndex-1}</a></li></c:if>
                                    <c:if test="${itemPage.pageIndex >= 1}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageIndex).concat('"}]'))}" >${itemPage.pageIndex}</a></li></c:if>
                                    <li class="active"><a>${itemPage.pageIndex + 1}</a></li>
                                    <c:if test="${itemPage.pageCount - itemPage.pageIndex >= 2}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageIndex+2).concat('"}]'))}">${itemPage.pageIndex+2}</a></li></c:if>
                                    <c:if test="${itemPage.pageCount - itemPage.pageIndex > 3}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageIndex+3).concat('"}]'))}">${itemPage.pageIndex+3}</a></li></c:if>
                                    <c:if test="${itemPage.pageCount - itemPage.pageIndex > 2}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageIndex+2).concat('"}]'))}">&gt;</a></li></c:if>
                                    <c:if test="${itemPage.pageCount - itemPage.pageIndex > 2}"><li><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"page",val:"'.concat(itemPage.pageCount).concat('"}]'))}">&raquo;</a></li></c:if>
                                    </ul>
                                </div><!--box-bottom-->
                            <%--<c:if test="${fn:length(munufData.data) > 0}">
                                <div class="boxlogo-title"><img class="brand-title" src="${staticUrl}/market/images/brand-title.png" alt="Thương hiệu nổi tiếng" /></div>
                                <div class="boxlogo-content">
                                    <c:forEach items="${munufData.data}" var="logo">
                                        <c:if test="${logo.imageUrl != null && logo.imageUrl != ''}">
                                            <div class="logoitem"><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{op: "mk",key:"manufacturers",val:"'.concat(logo.id).concat('"}]'))}"><img src="${logo.imageUrl}" alt="${logo.name}" /></a></div>
                                                </c:if>
                                                <c:if test="${logo.imageUrl == null || logo.imageUrl == ''}">
                                            <div class="logoitem"><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{op: "mk",key:"manufacturers",val:"'.concat(logo.id).concat('"}]'))}">${logo.name}</a></div>
                                            </c:if>
                                        </c:forEach>
                                </div><!--boxlogo-content-->
                                <c:if test="${munufData.pageCount > 1}">
                                    <div class="box-bottom">
                                        <div class="small-page" for="manuf">
                                            <a class="small-button next" onclick="browse.loadManufactuterByPage(1);" ></a>
                                        </div>
                                    </div><!--box-bottom-->
                                </c:if>
                            </c:if> --%> 
                        </c:if>
                    </div><!-- mbox-content -->
                </div><!-- mbox -->
            </div><!-- main -->
            <div class="clearfix"></div>
            <div class="footer-banner">
                <div class="fb-inner">
                    <!-- 970x90 -->
                    <div id='div-gpt-ad-1409797103321-0' style='width:970px; height:90px;'>
                        <script type='text/javascript'>
                            googletag.cmd.push(function () {
                                googletag.display('div-gpt-ad-1409797103321-0');
                            });
                        </script>
                    </div>
                </div>
            </div>
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
    <div class="internal-text">
        <c:if test="${itemSearch.sellerId != null && itemSearch.sellerId !='' && seller !=null}">
            <h2>${category.name} cập nhật giá rẻ từ người bán ${seller.username} tại chodientu.vn</h2>
        </c:if>
        <c:if test="${itemSearch.sellerId == null || itemSearch.sellerId =='' || seller ==null}">
            <c:if test="${itemSearch.keyword != null && itemSearch.keyword!=''}">
                <h2>${itemSearch.keyword}/${category.name} giá rẻ, liên tục cập nhật tại chodientu.vn</h2>
            </c:if>
            <c:if test="${itemSearch.keyword == null || itemSearch.keyword==''}">
                <h2>${category.name} giá rẻ, liên tục cập nhật tại chodientu.vn</h2>
            </c:if>
        </c:if>
    </div>
</div><!-- container -->
<div class="modal fade" id="ModalFilter" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
                <div class="filter-choose">
                    <c:if test="${fn:length(itemSearch.manufacturerIds) > 0}">
                        <c:forEach items="${itemSearch.manufacturerIds}" var="manId">
                            <div style="display:none" for="manufacturer" val="${manId}" class="filter-item"></div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${fn:length(itemSearch.modelIds) > 0}">
                        <c:forEach items="${itemSearch.modelIds}" var="moId">
                            <div style="display:none" for="model" val="${moId}" class="filter-item"></div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${fn:length(itemSearch.cityIds) > 0}">
                        <c:forEach items="${itemSearch.cityIds}" var="ciId">
                            <div style="display:none" for="city" val="${ciId}" class="filter-item"></div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${fn:length(itemSearch.properties) > 0}">
                        <c:forEach items="${itemSearch.properties}" var="property">
                            <div for="property" name="${property.name}" value="${property.value}" operator="${property.operator}" class="filter-item">
                                <span class="filter-lb">${property.name}: ${property.value}</span>
                                <span class="filter-remove"></span>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${itemSearch.condition == 'NEW'}">
                        <div class="filter-item"><span class="filter-lb">Hàng mới</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"condition",val:"0"}]')}'" class="filter-remove"></span></div>
                        </c:if>
                        <c:if test="${itemSearch.condition == 'OLD'}">
                        <div class="filter-item"><span class="filter-lb">Hàng cũ</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"condition",val:"0"}]')}'" class="filter-remove"></span></div>
                        </c:if>
                        <c:if test="${itemSearch.listingType == 'BUYNOW'}">
                        <div class="filter-item"><span class="filter-lb">Mua ngay</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"type",val:"0"}]')}'" class="filter-remove"></span></div>
                        </c:if>
                        <c:if test="${itemSearch.listingType == 'AUCTION'}">
                        <div class="filter-item"><span class="filter-lb">Đấu giá</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"type",val:"0"}]')}'" class="filter-remove"></span></div>
                        </c:if>
                        <c:if test="${itemSearch.freeShip}">
                        <div class="filter-item"><span class="filter-lb">Miễn phí vận chuyển</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"freeship",op:"rm"}]')}'" class="filter-remove"></span></div>
                        </c:if>
                        <c:if test="${itemSearch.cod}">
                        <div class="filter-item"><span class="filter-lb">Giao hàng thu tiền</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"cod",op:"rm"}]')}'" class="filter-remove"></span></div>
                        </c:if>
                        <c:if test="${itemSearch.onlinePayment}">
                        <div class="filter-item"><span class="filter-lb">Thanh toán Ngân Lượng</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"onlinepayment",op:"rm"}]')}'" class="filter-remove"></span></div>
                        </c:if>
                        <c:if test="${itemSearch.promotion}">
                        <div class="filter-item"><span class="filter-lb">Có khuyến mại</span><span onclick="document.location = '${baseUrl}${url:browseUrl(itemSearch, category.name, '[{key:"promotion",op:"rm"}]')}'" class="filter-remove"></span></div>
                        </c:if>
                </div><!-- filter-choose -->
                <div class="filter-other">
                    <div class="filter-left">
                        <ul>
                            <li for="manufacturer"><a>Thương hiệu</a></li>
                            <li for="model"><a>Model</a></li>
                            <li for="city"><a>Nơi bán</a></li>
                        </ul>
                    </div><!--filter-left-->
                    <div class="filter-right">
                        <div class="filter-title"></div>
                        <div for="manufacturer" name="Thương hiệu" class="box-check"></div>
                        <div for="model" name="Model" class="box-check"></div>
                        <div for="city" name="Nơi bán" class="box-check"></div>

                    </div><!--filter-right-->
                </div><!--filter-other-->
            </div><!-- end modal-body -->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Huỷ bỏ</button>
            </div>
        </div><!-- end modal-content -->
    </div><!-- end modal-dialog -->
</div><!-- end Modal -->