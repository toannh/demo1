<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
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