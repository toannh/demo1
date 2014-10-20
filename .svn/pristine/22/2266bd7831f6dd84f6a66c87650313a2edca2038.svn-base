<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="http://chodientu.vn/text"%>
<%@taglib  prefix="url" uri="http://chodientu.vn/url"%>
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