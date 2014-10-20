<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>  

<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a>
            <span class="tree-before"></span>
            <a class="last-item">Giỏ hàng</a>
            <span class="tree-after"></span>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <div class="bg-white">
            <div class="checkout">
                <div class="checkout-title">Xem giỏ hàng</div>
                <div class="cart-title">
                    <div class="col1">Sản phẩm</div>
                    <div class="col2">Thông tin người bán</div>
                </div><!-- cart-title -->
                <c:forEach var="cart" items="${viewer.cart}">
                    <c:set var="price" value="0" />
                    <div class="cart-seller" sellerId="${cart.sellerId}" id="${cart.sellerId}">
                        <div class="col1">
                            <div class="cs-listitem">
                                <c:forEach var="item" items="${cart.items}">
                                    <c:set var="price" value="${price + (item.itemPrice * item.quantity)}" />
                                    <div class="grid" rel="show_${item.id}" > 
                                        <div class="cs-text">
                                            <input itemquantity="${item.id}" onchange="order.updateToCart('${cart.id}', '${item.id}')" type="number" class="form-control" value="${item.quantity}">
                                            &nbsp;&nbsp;x
                                        </div>
                                        <div class="img">
                                            <img src="${item.images[0]}" alt="${item.itemName}">
                                            <div class="cs-remove" onclick="order.removeItem('${item.id}')" >
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </div>
                                        </div>
                                        <div class="g-content">
                                            <div class="g-row">
                                                <a class="g-title" target="_blnak" href="${url:item(item.itemId,item.itemName)}" title="${item.itemName}" >${item.itemName}</a>
                                                <span class="cs-price" _tprice="${item.id}" >${text:numberFormat(item.itemPrice * item.quantity)} <sup class="price" >đ</sup></span>
                                            </div>
                                            <c:if test="${fn:length(item.subItem) >0}">
                                                <c:forEach items="${item.subItem}" var="subitem">
                                                    <div class="g-row"><b>${subitem.quantity}</b> x
                                                        <c:if test="${subitem.sizeValueName!=null && subitem.sizeValueName!=''}">-Kích thước: <b>${subitem.sizeValueName}</b>,</c:if> 
                                                                                            <c:if test="${subitem.colorValueName!=null && subitem.colorValueName!=''}">
                                                                                                Màu sắc: 
                                                                                                <div class="tiny-colorsize micro-colorsize colorsizeViewcart" color="${subitem.colorValueName}" size="${subitem.sizeValueName}">
                                                                                                </div>
                                                                                            </c:if>
                                                    </div>
                                                </c:forEach>
                                            </c:if>
                                            <div class="g-row">
                                                <strong>Phí vận chuyển:</strong> 
                                                <c:if test="${item.shipmentType == null || item.shipmentType == 'AGREEMENT'}">
                                                    Không rõ phí
                                                </c:if>
                                                <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice == 0}">
                                                    Miễn phí
                                                </c:if>
                                                <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice > 0}">
                                                    Cố định ${text:numberFormat(item.shipmentPrice)} <sup class="u-price">đ</sup>
                                                </c:if>
                                                <c:if test="${item.shipmentType == 'BYWEIGHT'}">
                                                    Linh hoạt theo địa chỉ người mua (${item.weight}<sub>gram</sub>)
                                                </c:if>
                                            </div>
                                            <c:if test="${item.giftDetail != null && item.giftDetail != ''}">
                                                <div class="g-row"><b>Khuyến mại kèm theo:</b></div>
                                                <div class="g-row" style="padding-left: 10px;">
                                                    <c:set var="newline" value="<%= '\n'%>" />
                                                    ${fn:replace(item.giftDetail, newline, "<br />")}
                                                </div>
                                            </c:if>
                                        </div>
                                    </div><!-- grid -->
                                </c:forEach>
                            </div><!-- cs-listitem -->
                            <div class="cs-total">
                                <span class="cst-label">Tổng cộng</span>
                                <span class="cst-total" price="${cart.id}" >${text:numberFormat(price)} <sup class="price">đ</sup></span>
                            </div><!-- cs-total -->
                        </div><!-- col1 -->
                        <div class="col2">
                            <c:if test="${cart.shop != null}">
                                <div class="grid">
                                    <div class="img">
                                        <img src="${cart.shop.logo == null?'/static/lib/no-avatar.png':cart.shop.logo}" alt="${cart.shop.title}">
                                    </div>
                                    <div class="g-content">
                                        <div class="g-row">
                                            Shop: 
                                            <a href="${baseUrl}/${cart.shop.alias}/" target="_blank" >
                                                <b>${cart.shop.title}</b>
                                            </a>
                                        </div>
                                        <!--<div class="g-row f11">Điểm uy tín: 23325 | <b class="text-danger">90%</b> người mua đánh giá tốt</div>-->
                                    </div>
                                    <div class="g-row">
                                        <p class="p-checkred">
                                            <span class="icon16-placegray"></span>
                                            ${cart.shop.address} (<a onclick="item.getMaps('${cart.shop.address}')" data-toggle="modal" data-target="#ModalNormal" >Bản đồ</a>)
                                        </p>
                                    </div>
                                    <div class="g-row"><p class="p-checkred"><span class="icon16-telgray"></span>${cart.shop.phone}</p></div>
                                </div><!-- grid -->
                            </c:if>
                            <c:if test="${cart.shop == null}">
                                <div class="grid">
                                    <div class="img">
                                        <img src="${cart.user.avatar == null?'/static/lib/no-avatar.png':cart.user.avatar}" alt="${cart.user.name}">
                                    </div>
                                    <div class="g-content">
                                        <div class="g-row">
                                            Người bán: 
                                            <a><b>${cart.user.name}</b></a>
                                        </div>
                                        <!--<div class="g-row f11">Điểm uy tín: 23325 | <b class="text-danger">90%</b> người mua đánh giá tốt</div>-->
                                    </div>
                                    <div class="g-row">
                                        <p class="p-checkred">
                                            <span class="icon16-placegray"></span>
                                            ${cart.user.address}
                                        </p>
                                    </div>
                                    <div class="g-row"><p class="p-checkred"><span class="icon16-telgray"></span>${cart.user.phone}</p></div>
                                </div><!-- grid -->
                            </c:if>

                        </div><!-- col2 -->
                        <div class="col3">
                            <a class="btn btn-primary" href="${baseUrl}/${cart.id}/thanh-toan-gio-hang.html">Thanh toán</a>
                        </div><!-- col3 -->
                    </div><!-- cart-seller -->
                </c:forEach>
            </div><!-- checkout -->
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
</div>

<div class="modal fade" id="ModalNormal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
                <div id="map-canvas"></div>
            </div><!-- end modal-body -->
        </div><!-- end modal-content -->
    </div><!-- end modal-dialog -->
</div><!-- end Modal -->