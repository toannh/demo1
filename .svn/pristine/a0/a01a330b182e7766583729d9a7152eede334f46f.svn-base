<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<div class="container">
    <div class="box-center">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="slider-outer">
            <div class="slider-wrapper">
                <div id="heartslider" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <c:forEach items="${heartBanners}" var="heartBanner" varStatus="stt">
                            <li data-target="#heartslider" data-slide-to="${stt.index}" <c:if test="${stt.index==0}"> class="active"</c:if>><img src="${heartBanner.thumb}" alt="thumb" /><span>${heartBanner.name}</span></li>
                                </c:forEach>

                    </ol>
                    <div class="carousel-inner">
                        <c:forEach items="${heartBanners}" var="heartBanner" varStatus="stt">
                            <div class="item <c:if test="${stt.index==0}">active</c:if>">
                                <a href="${heartBanner.url}" target="_blank"><img src="${heartBanner.image}" alt="${heartBanner.name}"></a>
                            </div><!-- item -->
                        </c:forEach>


                    </div><!-- carousel-inner -->
                    <a class="left carousel-control" href="#heartslider" data-slide="prev">
                        <span class="icon-prev"></span>
                    </a>
                    <a class="right carousel-control" href="#heartslider" data-slide="next">
                        <span class="icon-next"></span>
                    </a>
                </div><!-- heartslider -->
            </div><!-- /slider-wrapper -->
            <div class="center-banner">
                <c:forEach items="${homeBanners}" var="homeBanner" varStatus="stt">
                    <div class="cb-item"><a href="${homeBanner.url}" target="_blank"><img src="${homeBanner.image}" alt="${homeBanner.name}" /></a></div>
                        </c:forEach>
            </div><!--center-banner-->
        </div><!--slider-outer-->
    </div><!-- /box-center -->
    <div class="topbanner">
        <a href="http://chodientu.vn/tin-tuc/thong-bao--chuong-trinh-thuong-xeng---gan-ket-thanh-vien-82930526079.html?rel=notification&code=82930526079" target="_blank"><img src="${baseUrl}/static/market/images/data/banner-xeng.jpg" alt="home banner"></a>
    </div><!-- /topbanner -->
    <div class="bground">
        <div class="hotdeal mobile-hidden">
            <div class="hotdeal-title">
                <a class="lb-name" href="${basicUrl}/hotdeal.html">Hot<span>deal</span></a>
            </div>
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
                <div class="hotdeal-banner">
                    <a href="${basicUrl}/hotdeal.html"><img src="${hotDealBoxBanner}" alt="hotdeal" /></a>
                </div><!-- hotdeal-banner -->
                <div class="clearfix"></div>
            </div><!-- hotdeal-content -->
        </div><!-- hotdeal -->
        <div class="hotdeal hotdeal-mobile">
            <div class="hotdeal-title"><label class="lb-name">Hot<span>deal</span></label></div>
            <div class="hotdeal-content">
                <span class="hotdeal-new"></span>
                <div class="swiper-container">
                    <div class="swiper-wrapper">
                        <c:forEach items="${hotDealBox}" var="hotdeal">
                            <div class="swiper-slide">
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
                            </div><!-- swiper-slide -->
                        </c:forEach>	
                    </div><!-- swiper-wrapper -->
                </div><!-- swiper-container -->
            </div><!-- hotdeal-content -->
        </div><!-- hotdeal -->
        <div class="boxhome ipad-hidden">
            <div class="box">
                <div class="box-title"><label class="lb-name">Bán chạy nhất từ người bán uy tín</label></div>
                <div class="tab-outer">
                    <div class="tab-title">
                        <c:forEach items="${topSellerBox}" var="topseller" varStatus="stt">
                            <a <c:if test="${stt.index==0}">class="active"</c:if> href="#usertab${stt.index+1}">
                                <img src="${topseller.image}" alt="logo" />
                                <p><b>Shop</b><span onclick="market.linkShop('${topseller.sellerName}')">${topseller.sellerName}</span></p>
                                <p>${topseller.city}</p>
                                <p>${topseller.countItem} sản phẩm</p>
                            </a>
                        </c:forEach>
                    </div>
                    <div class="tab-container">
                        <c:forEach items="${topSellerBox}" var="topsellerItem" varStatus="stt">
                            <div id="usertab${stt.index+1}" class="tab-content" <c:if test="${stt.index==0}">style="display:block;"</c:if><c:if test="${stt.index!=0}">style="display:none;"</c:if>>
                                    <div class="userhot-list">
                                    <c:forEach items="${topsellerItem.items}" var="items">

                                        <div class="home-item">
                                            <div class="hoi-inner">
                                                <c:if test="${items.listingType=='AUCTION'}">
                                                    <span class="hoi-bid"></span>
                                                </c:if>
                                                <a class="hoi-thumblink" href="${basicUrl}/san-pham/${items.id}/${text:createAlias(items.name)}.html"><img src="${(items.images != null && fn:length(items.images) >0)?items.images[0]:staticUrl.concat('/market/images/no-image-product.png')}" alt="product" /></a>
                                                <div class="hoi-title">
                                                    <div class="hoi-row"><a href="${basicUrl}/san-pham/${items.id}/${text:createAlias(items.name)}.html">${items.name}</a></div>
                                                    <div class="hoi-row"><span class="hoi-price">${text:sellPrice(items.sellPrice,items.discount,items.discountPrice,items.discountPercent)} <sup class="u-price">đ</sup></span></div>
                                                </div>
                                                <div class="hoi-view">
                                                    <a class="hoi-btn" href="javascript:;" onclick="market.quickview('${items.id}')">Xem nhanh</a>
                                                    <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                                </div>
                                            </div>
                                        </div><!-- home-item -->
                                    </c:forEach>
                                </div><!-- userhot-list -->
                            </div><!-- /tab-content -->
                        </c:forEach>

                    </div><!-- /tab-container -->
                </div><!-- /tab-outer -->
            </div><!-- box -->
            <div class="homedeal">
                <div class="homedeal-title"><span>Deal</span> Tốt nhất</div>
                <div class="homedeal-content">
                    <ul class="homedeal-slider jcarousel jcarousel-skin-tango">
                        <c:forEach items="${bestDealBox}" var="bestdeal">
                            <li>
                                <div class="deal-item">
                                    <c:if test="${bestdeal.listingType == 'BUYNOW' && (text:percentPrice(bestdeal.startPrice,bestdeal.sellPrice,bestdeal.discount,bestdeal.discountPrice,bestdeal.discountPercent))>0}">
                                        <div class="di-sale"><label>Sale&nbsp;<span>${text:percentPrice(bestdeal.startPrice,bestdeal.sellPrice,bestdeal.discount,bestdeal.discountPrice,bestdeal.discountPercent)}%</span></label></div>                    
                                    </c:if>

                                    <div class="di-thumb"><a href="${basicUrl}/san-pham/${bestdeal.id}/${text:createAlias(bestdeal.name)}.html"><img src="${(bestdeal.images != null && fn:length(bestdeal.images) >0)?bestdeal.images[0]:staticUrl.concat('/market/images/no-image-product.png')}" alt="product" /></a></div>
                                    <div class="di-line"></div>
                                    <div class="di-row"><a class="di-title" href="${basicUrl}/san-pham/${bestdeal.id}/${text:createAlias(bestdeal.name)}.html">${bestdeal.name}</a></div>
                                    <div class="di-row">
                                        <span class="di-price">${text:sellPrice(bestdeal.sellPrice,bestdeal.discount,bestdeal.discountPrice,bestdeal.discountPercent)} <sup class="u-price">đ</sup></span>
                                        <c:if test="${(text:discountPrice(bestdeal.startPrice,bestdeal.sellPrice,bestdeal.discount,bestdeal.discountPrice,bestdeal.discountPercent))!='0'}">
                                            <span class="di-oldprice">${text:startPrice(bestdeal.startPrice,bestdeal.sellPrice,bestdeal.discount)} <sup class="u-price">đ</sup></span>
                                        </c:if>

                                        <div class="di-row">
                                            <span class="di-icon"><span class="icon16-nlgray"></span></span>
                                            <span class="di-icon"><span class="icon16-codgray"></span></span>
                                            <span class="di-icon"><span class="icon16-transgray"></span></span>
                                            <div class="di-like">
                                                <div class="fb-like" data-href="${basicUrl}/san-pham/${bestdeal.id}/${text:createAlias(bestdeal.name)}.html" data-layout="button_count" data-action="like" data-show-faces="true"></div>

                                            </div>
                                        </div>
                                    </div><!-- deal-item -->
                            </li>
                        </c:forEach>
                    </ul>
                </div><!-- homedeal-content -->
            </div><!-- homedeal -->
            <div class="clearfix"></div>
        </div><!-- boxhome -->

        <c:forEach items="${featuredCategorys}" var="featuredcategory">
            <c:set var="featuredcategory" value="${featuredcategory}" scope="request" />
            <c:if test="${featuredcategory.template=='template1'}">
                <jsp:include page="/view/market/categoryspecialbox/template1.jsp"></jsp:include>
            </c:if>

            <c:if test="${featuredcategory.template=='template2'}">
                <jsp:include page="/view/market/categoryspecialbox/template2.jsp"></jsp:include>
            </c:if>
            <c:if test="${featuredcategory.template=='template3'}">
                <jsp:include page="/view/market/categoryspecialbox/template3.jsp"></jsp:include>
            </c:if>
            <c:if test="${featuredcategory.template=='template4'}">
                <jsp:include page="/view/market/categoryspecialbox/template4.jsp"></jsp:include>
            </c:if>

            <c:if test="${featuredcategory.template=='template5'}">
                <div class="twobox ipad-hidden">
                    <jsp:include page="/view/market/categoryspecialbox/template5.jsp"></jsp:include>
                </c:if>
                <c:if test="${featuredcategory.template=='template6'}">
                    <jsp:include page="/view/market/categoryspecialbox/template6.jsp"></jsp:include>
                    </div>
            </c:if>

        </c:forEach>
        <div class="homenews">
            <div class="homenews-col">
                <div class="homenews-title"><span class="icon20-user"></span>Câu chuyện thành công</div>
                <div class="homestory">
                    <ul class="homestory-slider jcarousel jcarousel-skin-tango">
                        <c:forEach items="${featuredNews}" var="featuredNewse">
                            <c:if test="${featuredNewse.type==1}">
                                <li>
                                    <div class="grid">
                                        <div class="img"><img src="${featuredNewse.image}" alt="${featuredNewse.name}" /></div>
                                        <div class="g-content">
                                            <div class="g-row"><b>${featuredNewse.title}</b></div>
                                            <div class="g-row">
                                                ${featuredNewse.content}
                                            </div>
                                            <div class="g-right"><b>${featuredNewse.name}</b></div>
                                            <div class="g-right">${featuredNewse.nameCompany}</div>
                                            <div class="g-right">shop: <a href="${featuredNewse.url}" target="_blank" rel="nofollow">${featuredNewse.url}</a></div>
                                        </div>
                                    </div><!-- grid -->
                                </li>
                            </c:if>
                        </c:forEach>

                    </ul>
                </div><!-- homestory -->
                <div class="homenews-more"><a href="${baseUrl}/tin-tuc/goc-kinh-nghiem/258010553833.html">» Xem toàn bộ</a></div>
            </div><!-- homenews-col -->
            <div class="homenews-col">
                <div class="homenews-title"><span class="icon20-comment"></span>Nhận xét của khách hàng</div>
                <div class="homecomment">
                    <ul class="homecomment-slider jcarousel jcarousel-skin-tango">
                        <c:forEach items="${featuredNews}" var="featuredNewse">
                            <c:if test="${featuredNewse.type==2}">
                                <li>
                                    <div class="grid">
                                        <div class="img"><img src="${featuredNewse.image}" alt="${featuredNewse.name}" /></div>
                                        <div class="g-content">
                                            <div class="g-row">
                                                ${featuredNewse.content}                            
                                            </div>
                                            <div class="g-right"><b>${featuredNewse.name}</b></div>
                                                    <c:if test="${featuredNewse.nameShop!=null && featuredNewse.nameShop!=''}">
                                                <div class="g-right">Chủ shop: <a href="${featuredNewse.url}" target="_blank" rel="nofollow">${featuredNewse.nameShop}</a></div>
                                                </c:if>
                                        </div>
                                    </div><!-- grid -->
                                </li>
                            </c:if>
                        </c:forEach>

                    </ul>
                </div><!-- homecomment -->
                <div class="homenews-more"><a href="${baseUrl}/tin-tuc/y-kien-khach-hang/58295249532.html">» Xem toàn bộ</a></div>
            </div><!-- homenews-col -->
            <div class="homenews-col">
                <div class="homenews-title"><span class="icon20-news"></span>Tin tức/ Sự kiện</div>
                <ul class="homenews-ul">
                    <c:forEach items="${newsHomeBoxs}" var="newsHomeBox">
                        <li><a href="${baseUrl}${url:newsDetailUrl(newsHomeBox.id,newsHomeBox.title)}">${newsHomeBox.title}</a></li>
                        </c:forEach>
                </ul>
                <div class="homenews-more text-right"><a href="${baseUrl}/tin-tuc.html" rel="nofollow">» Xem toàn bộ</a></div>
            </div><!-- homenews-col -->
        </div><!-- homenews -->

        <div class="clearfix"></div>
    </div><!-- bground -->
    <div class="internal-text">
        <h1>Mua bán, đấu giá sản phẩm thời trang, trang sức, công nghệ, điện tử... | ChợĐiệnTử - đa dạng, giá rẻ.</h1>
        <h2>Sản phẩm thời trang, trang sức liên tục cập nhât tại chodientu.vn</h2>
    </div>
</div><!-- container -->

<div class="popup-mar-bg" style="display: none" rel="popIndex" >
    <div class="popup-mar">
        <span class="pm-close" ></span>
        <c:if test="${viewer.user == null}">http://bit.ly/mien-phi-van-chuyen-ttnl
            <a target="_blank" href="http://bit.ly/Thoi-trang-trang-suc-cod-Nl">
                <img src="${baseUrl}/static/market/images/data/pop.png" alt="visa">
            </a>
        </c:if>
        <c:if test="${viewer.user != null}">
            <a target="_blank" href="http://bit.ly/mien-phi-van-chuyen-ttnl">
                <img src="${baseUrl}/static/market/images/data/pop-seller.png" alt="visa">
            </a>
        </c:if>
    </div><!--popup-mar-->
</div>

