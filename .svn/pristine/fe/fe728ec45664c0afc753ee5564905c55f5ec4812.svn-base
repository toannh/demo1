<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="text" uri="http://chodientu.vn/text" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="http://chodientu.vn/url" %>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script> 
<script src="https://apis.google.com/js/platform.js" async defer>
    {
        lang: 'vi'
    }
</script>

<c:set var="datetime" value="<%= new java.util.Date().getTime()%>" />
<div class="container">
    <div class="tree-main">
        <div class="menu-click">
            <jsp:include page="/view/market/widget/alias.jsp" />
        </div><!--menu-click-->
        <div class="tree-view" xmlns:v="http://rdf.data-vocabulary.org/#" >
            <a class="home-button" href="${baseUrl}"></a>
            <span class="tree-normal"></span>
            <a href="${baseUrl}/tim-kiem.html">Sản phẩm</a>
            <c:forEach var="catP" items="${cat}" varStatus="stt">
                <c:if test="${catP.id == category.id}">
                    <span class="tree-before"></span>
                    <span typeof="v:Breadcrumb" >
                        <a rel="v:url" property="v:title" title="${catP.name}"  class="last-item" href="${baseUrl}/mua-ban/${catP.id}/${text:createAlias(catP.name)}.html">
                            ${catP.name}
                        </a>    
                    </span>
                </c:if>
                <c:if test="${catP.id != category.id}">
                    <span class="tree-normal"></span>
                    <span typeof="v:Breadcrumb" >
                        <a rel="v:url" property="v:title" title="${catP.name}" href="${baseUrl}/mua-ban/${catP.id}/${text:createAlias(catP.name)}.html">${catP.name}</a>
                    </span>
                </c:if>
            </c:forEach>
            <span class="tree-after"></span>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <!-- Khi sản phẩm hết hàng ----------------------------------------------------------------------------------- -->
    <c:if test="${item.quantity <= 0}" >
        <div class="cdt-message bg-danger cm-icon">
            <span class="icon30-notice"></span>
            Sản phẩm này hiện nay đã hết hàng  
            <c:if test="${viewer.user != null && viewer.user.id == item.sellerId}" >,<a href="javascript:item.editItem('${item.id}');"> cập nhật lại</a> số lượng nếu bạn muốn tiếp tục đăng bán tại sàn!</c:if>
            </div>
    </c:if>
    <c:if test="${item.endTime < datetime}" >
        <div class="cdt-message bg-danger cm-icon">
            <span class="icon30-notice"></span>
            Sản phẩm này đã hết hạn đăng bán
            <c:if test="${viewer.user != null && viewer.user.id == item.sellerId}" >,<a href="javascript:item.editItem('${item.id}');"> cập nhật lại</a> thời gian đăng bán nếu bạn muốn tiếp tục đăng bán tại sàn!</c:if>
            </div>
    </c:if>
    <c:if test="${!item.approved}" >
        <div class="cdt-message bg-danger cm-icon">
            <span class="icon30-notice"></span>Sản phẩm này không được duyệt.
        </div>
    </c:if>
    <c:if test="${!item.active}" >
        <div class="cdt-message bg-danger cm-icon">
            <span class="icon30-notice"></span>Sản phẩm này bị tạm xóa.
        </div>
    </c:if>
    <c:if test="${item.quantity <= 0 || item.endTime < datetime}" >
        <div class="bv-control showProductSame">
            <h2>Sản phẩm tương tự cùng khoảng giá</h2>
            <ul>
            </ul>
        </div><!-- bv-control -->
        <div class="browser-vip pd-sameproduct">
        </div><!-- browser-vip -->
    </c:if>
    <div class="bground">
        <div class="bg-white">
            <div class="pd-detail product-detail">
                <!-- Quản trị của người bán -->
                <div class="functionAdminRole">
                </div>
                <!--End Quản trị của người bán -->
                <div class="pd-title">
                    <div class="sf-row">
                        <c:if test="${seller != null && seller.nlIntegrated && seller.scIntegrated}">
                            <span class="pd-protected"></span>
                        </c:if>
                        <c:if test="${item.manufacturerName != null}">
                            <a class="pd-brand" href="${baseUrl}${url:manufacturerUrl(item.manufacturerId)}">${item.manufacturerName}</a>
                        </c:if>
                    </div>
                    <div class="sf-row"><h1>${item.name}</h1></div>
                    <div class="sf-row">
                        <jsp:useBean id="date" class="java.util.Date" />
                        Mã tin bán: <span class="text-danger">${item.id}</span> | Lượt xem: <span class="text-danger">${item.viewCount}</span> | Cập nhật: <span class="text-danger">
                            <jsp:setProperty name="date" property="time" value="${item.updateTime}" /> 
                            <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                            </span>
                        </div>
                    </div><!-- pd-title -->
                    <div class="pd-content">
                        <div class="pd-left">
                            <div class="image-view">
                            <c:if test="${item.listingType != 'AUCTION' && (text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent) != '0')}">
                                <div class="pd-ripbon"><span>Sale&nbsp;<b>${text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent)}%</b></span></div>
                            </c:if>
                            <a href="${item.images[0]}" class ="cloud-zoom" id="zoom1" rel="adjustX: 10, adjustY:-4">
                                <img src="${(item.images != null && fn:length(item.images) >0)?item.images[0]:staticUrl.concat('/market/images/no-image-product.png')}" alt="Không thấy ảnh" />
                            </a>
                            <div class="image-view-mobile">
                                <div class="swiper-container">
                                    <div class="swiper-wrapper">
                                        <c:forEach var="img" items="${item.images}">
                                            <div class="swiper-slide">
                                                <div class="detail-mobile-img">
                                                    <img src="${img}" alt="${item.name}" />
                                                </div>
                                            </div><!-- swiper-slide -->
                                        </c:forEach>
                                    </div><!-- swiper-wrapper -->
                                </div><!-- swiper-container -->
                                <div class="dmi"></div>
                            </div><!-- image-view-mobile -->
                        </div>
                        <div class="image-desc"><span class="icon16-zoom"></span>Di chuột vào ảnh để xem hình to hơn</div>
                        <c:if test="${fn:length(item.images) > 1}">
                            <div class="pd-slider">
                                <ul class="imgdetail-slider jcarousel jcarousel-skin-tango">
                                    <c:forEach var="img" items="${item.images}" varStatus="stt">
                                        <li>
                                            <div class="is-item ${stt.first?'active':''}">
                                                <a href="${img}" class="cloud-zoom-gallery" rel="useZoom: 'zoom1', smallImage: '${img}'">
                                                    <img class="zoom-tiny-image" src="${img}" alt = "${item.name}"/>
                                                </a>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div><!--pd-slider-->
                        </c:if>
                        <ul class="pd-ulmore">
                            <li><a onclick="item.sendQuestion('${item.id}')" style="cursor: pointer">Hỏi thêm người bán về sản phẩm</a></li>
                            <li><a onclick="item.warning('OUTOFSTOCK', '${item.id}');" style="cursor: pointer">Báo hết hàng</a></li>
                            <li><a onclick="item.warning('WRONG_PRICE', '${item.id}');" style="cursor: pointer">Báo sai giá</a></li>
                            <li><a href="${baseUrl}/user/dang-ban-tuong-tu.html?id=${item.id}">Đăng bán SP tương tự</a></li>
                        </ul>
                        <div class="pd-share">
                            <ul>
                                <li class="pd-share-fb">
                                    <div id="fb-root"></div>
                                    <div class="fb-share-button" data-href="${url:item(item.id,item.name)}" data-type="button_count"></div>
                                </li>
                                <li class="pd-like-cdt"><a class="btn-like-cdt" style="cursor: pointer" onclick="item.itemFollow('${item.id}');">Quan tâm</a><span class="like-count countFollowItem">${countFollowItem}</span></li>
                                <li class="pd-like-cdt"><div class="g-plusone" data-size="tall" data-annotation="inline" data-width="300"></div></li>
                            </ul>
                        </div>
                    </div><!-- pd-left -->
                    <!-- ----------------------------------------Mua Ngay---------------------------------------------------- -->             
                    <c:if test="${item.listingType == 'BUYNOW'}">
                        <div class="pd-center">
                            <div class="row">
                                <c:if test="${item.discount}">
                                    <c:if test="${(item.discountPrice > 0 && item.discountPrice < item.sellPrice) || (item.discountPercent > 0 && item.discountPercent < 100)}">
                                        <div class="big-clock bc-right">
                                            <div class="sf-row">Giá đặc biệt chỉ còn:</div>
                                            <div class="sf-row" data-rel="showDateTime">
                                                <span class="time-count"></span>
                                                <span class="digits"></span>
                                            </div>
                                        </div><!-- big-clock -->
                                    </c:if>
                                </c:if>
                                <span class="big-price">${text:sellPrice(item.sellPrice,item.discount,item.discountPrice,item.discountPercent)} <sup class="u-price">đ</sup></span>
                            </div>
                            <c:if test="${text:discountPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent) != '0'}">
                                <div class="row">
                                    <p class="f11">
                                        <span class="old-price"><b>${text:startPrice(item.startPrice,item.sellPrice,item.discount)} <sup class="u-price">đ</sup></b></span>&nbsp;|&nbsp;Tiết kiệm: <b>${text:discountPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent)} <sup class="u-price">đ</sup></b>
                                    </p>
                                </div>
                            </c:if>  
                            <div class="row">
                                <label>Tình trạng:</label>
                                <c:if test="${item.condition == 'NEW'}">
                                    <div class="text-outer">Hàng mới</div>
                                </c:if>
                                <c:if test="${item.condition == 'OLD'}">
                                    <div class="text-outer">Hàng cũ</div>
                                </c:if>
                            </div>
                            <div class="row">
                                <label>Số lượng:</label>
                                <c:if test="${item.quantity > 0}">
                                    <div class="text-outer">Còn ${item.quantity} sản phẩm</div>
                                </c:if>
                                <c:if test="${item.quantity <= 0}">
                                    <div class="text-outer"><b class="text-danger">Hết hàng</b></div>
                                </c:if>
                            </div>
                            <div class="row">
                                <label>Hạn bán:</label>
                                <div class="text-outer">
                                    <c:if test="${item.startTime < datetime && item.endTime > datetime}">
                                        <c:set var="remainTime" value="${item.endTime - datetime}" />
                                        <fmt:formatNumber var="seconds" pattern="0" value="${remainTime / (24 * 60 * 60 * 1000)}"/>
                                        Còn ${seconds} ngày - (Hết hạn vào:<jsp:setProperty name="date" property="time" value="${item.endTime}" /> 
                                        <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate>)
                                    </c:if> 
                                    <c:if test="${item.startTime > datetime && item.endTime > datetime}">
                                        Bắt đầu bán từ ngày: <jsp:setProperty name="date" property="time" value="${item.startTime}" /> 
                                        <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                                    </c:if> 
                                    <c:if test="${item.endTime < datetime}">
                                        <b class="text-danger">Hết hạn bán</b>
                                    </c:if>
                                </div>
                            </div>
                            <div class="row">
                                <label>Phí vận chuyển:</label>
                                <div class="text-outer">
                                    <c:if test="${item.shipmentType == null || item.shipmentType == 'AGREEMENT'}">
                                        Không rõ phí
                                    </c:if>
                                    <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice == 0}">
                                        Miễn phí
                                    </c:if>
                                    <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice > 0}">
                                        Cố định: ${text:numberFormat(item.shipmentPrice)} <sup class="u-price">đ</sup>
                                    </c:if>
                                    <c:if test="${item.shipmentType == 'BYWEIGHT'}">
                                        Linh hoạt theo địa chỉ người mua&nbsp;&nbsp; <c:if test="${ct.scId == '18' || ct.scId =='52'}"><a href="#VC">» Xem chi tiết</a></c:if>
                                    </c:if>
                                </div>
                            </div>
                            <c:if test="${item.gift}"> 
                                <div class="row">
                                    <label>Ưu đãi:</label>
                                    <div class="text-outer">
                                        <c:set var="newline" value="<%= '\n'%>" />
                                        ${fn:replace(item.giftDetail, newline, "<br />")}
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${item.shipmentType == 'BYWEIGHT' && item.weight < 2000}">
                                <div class="row">
                                    <label>CĐT hỗ trợ:</label>
                                    <div class="text-outer">
                                        <p class="text-danger">Miễn phí phí vận chuyển khi thanh toán qua NgânLượng với đơn hàng không quá 2kg.</p>
                                    </div>
                                </div>
                            </c:if>
                            
                            <div class="row" data-rel="show-color">
                               
                            </div>
                            <div class="row" data-rel="show-size">
                               
                            </div>

                            <c:if test="${coupon != null}">
                                <div class="row">
                                    <label>Mã giảm giá:</label>
                                    <div class="text-outer">
                                        <p>
                                            <b>${coupon.code}</b>
                                            <c:if test="${coupon.discountPrice > 0}">
                                                <span title="" data-placement="right" data-toggle="tooltip" class="icon16-faq cdt-tooltip" data-html="true" data-original-title="<div style='text-align:left;'>Giảm ${text:numberFormat(coupon.discountPrice)}đ cho đơn hàng trên ${text:numberFormat(coupon.minOrderValue)}đ</div><div style='text-align:left;'>Thời gian áp dụng: <jsp:setProperty name="date" property="time" value="${coupon.startTime}" /> 
                                                      <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate> - <jsp:setProperty name="date" property="time" value="${coupon.endTime}" /> 
                                                      <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></div>"></span>
                                            </c:if>
                                            <c:if test="${coupon.discountPercent > 0}">
                                                <span title="" data-placement="right" data-toggle="tooltip" class="icon16-faq cdt-tooltip" data-html="true" data-original-title="<div style='text-align:left;'>Giảm ${coupon.discountPercent}% cho đơn hàng trên ${text:numberFormat(coupon.minOrderValue)}đ</div><div style='text-align:left;'>Thời gian áp dụng: <jsp:setProperty name="date" property="time" value="${coupon.startTime}" /> 
                                                      <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate> - <jsp:setProperty name="date" property="time" value="${coupon.endTime}" /> 
                                                      <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></div>"></span>
                                            </c:if>
                                        </p>
                                    </div>
                                </div>
                            </c:if>
                            <div class="row">
                                <div class="text-spell">
                                    <input name="txtNumber" type="text" onchange="order.changeNumber(this, ${item.quantity})" class="text txtNumber"  value="1" itemquantity='${item.id}' />
                                    <span class="spell-up" onclick="order.upNumber('txtNumber')" ></span>
                                    <span class="spell-down" onclick="order.downNumber('txtNumber')" ></span>
                                </div>
                                <div class="text-outer">
                                    <a class="btn btn-danger btn-lg <c:if test="${item.quantity <= 0 || item.endTime < datetime || !item.approved || item.startTime > datetime || !item.active}" >disabled</c:if>" onclick="order.buyNow('${item.id}', '${item.sellerId}');">Mua ngay</a>
                                    <a class="btn btn-primary btn-lg <c:if test="${item.quantity <= 0  || item.endTime < datetime || !item.approved || item.startTime > datetime || !item.active}" >disabled</c:if>" onclick="order.addToCart('${item.id}');">Cho vào giỏ hàng</a>
                                    </div>
                                </div>
                            <c:if test="${seller != null && seller.nlIntegrated && seller.scIntegrated}">
                                <div class="row">
                                    <label>Giao hàng:</label>
                                    <div class="text-outer">Giao hàng toàn quốc tới tận huyện, xã&nbsp;&nbsp;<c:if test="${ct.scId == '18' || ct.scId =='52'}"><a class="f11" href="#VC">» Xem chi tiết</a></c:if></div>
                                    </div>
                                    <div class="row">
                                        <div class="text-outer">
                                            <div><p class="p-checkred cdt-tooltip" data-toggle="tooltip" data-placement="right" ><span class="icon16-checkok"></span>Giao hàng nhanh nội thành từ 2h-8h</p></div>
                                            <div><p class="p-checkred cdt-tooltip" data-toggle="tooltip" data-placement="right" ><span class="icon16-checkok"></span>Giao hàng tiết kiệm nội thành từ 8h-16h</p></div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label>Thanh toán:</label>
                                        <div class="text-outer">
                                            <div><p class="p-checkred"><span class="icon16-checkok"></span>Thu tiền tận nơi trên toàn quốc (CoD) - Miễn phí</p></div>
                                            <div><p class="p-checkred"><span class="icon16-checkok"></span>Thanh toán online: Trên 30 kênh thanh toán</p></div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="text-outer">
                                            <ul class="ul-bank">
                                                <li><a title="NgânLượng.vn" target="_blank" href="http://www.nganluong.vn"><span class="icon-nl"></span></a></li>
                                                <li><a title="Thẻ thanh toán VisaCard" target="_blank" href="http://www.vietnam-visa.com" class="visacrd"></a></li>
                                                <li><a title="Thẻ thanh toán MasterCard" target="_blank" href="http://www.mastercard.com" class="mastercrd"></a></li>
                                                <li><a title="Hệ thống thanh toán trực tuyến Paypal" target="_blank" href="https://www.paypal.com" class="paypal"></a></li>
                                                <li><a><span class="icon-bank"></span></a></li>
                                            </ul>
                                            <a class="f11" href="#payment">» Xem chi tiết</a>
                                        </div>
                                    </div>
                            </c:if>
                            <c:if test="${seller != null && seller.nlIntegrated && !seller.scIntegrated}">
                                <div class="row">
                                    <label>Thanh toán:</label>
                                    <div class="text-outer">
                                        <div><p class="p-checkred"><span class="icon16-checkok"></span>Thanh toán online: Trên 30 kênh thanh toán</p></div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="text-outer">
                                        <ul class="ul-bank">
                                            <li><a title="NgânLượng.vn" target="_blank" href="http://www.nganluong.vn"><span class="icon-nl"></span></a></li>
                                            <li><a title="Thẻ thanh toán VisaCard" target="_blank" href="http://www.vietnam-visa.com" class="visacrd"></a></li>
                                            <li><a title="Thẻ thanh toán MasterCard" target="_blank" href="http://www.mastercard.com" class="mastercrd"></a></li>
                                            <li><a title="Hệ thống thanh toán trực tuyến Paypal" target="_blank" href="https://www.paypal.com" class="paypal"></a></li>
                                            <li><a href="#"><span class="icon-bank"></span></a></li>
                                        </ul>
                                        <a class="f11" href="#payment">» Xem chi tiết</a>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${seller != null && !seller.nlIntegrated && seller.scIntegrated}">
                                <div class="row">
                                    <label>Giao hàng:</label>
                                    <div class="text-outer">Giao hàng toàn quốc tới tận huyện, xã&nbsp;&nbsp;<c:if test="${ct.scId == '18' || ct.scId =='52'}"><a class="f11" href="#VC">» Xem chi tiết</a></c:if></div>
                                    </div>
                                    <div class="row">
                                        <div class="text-outer">
                                            <div><p class="p-checkred cdt-tooltip" data-toggle="tooltip" data-placement="right"><span class="icon16-checkok"></span>Giao hàng nhanh nội thành từ 2h-8h</p></div>
                                            <div><p class="p-checkred cdt-tooltip" data-toggle="tooltip" data-placement="right"><span class="icon16-checkok"></span>Giao hàng tiết kiệm nội thành từ 8h-16h</p></div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label>Thanh toán:</label>
                                        <div class="text-outer">
                                            <div><p class="p-checkred"><span class="icon16-checkok"></span>Thu tiền tận nơi trên toàn quốc (CoD) - Miễn phí</p></div>
                                        </div>
                                    </div>
                            </c:if>
                            <c:if test="${seller != null && !seller.nlIntegrated && !seller.scIntegrated}">
                                <div class="cdt-message bg-danger">
                                    <p><b class="text-danger">Cảnh báo:</b> Giao dịch trực tiếp chứa đựng khả năng lừa đảo cao. ChợĐiệnTử không bảo đảm cho giao dịch này!</p>
                                    <br>
                                    <p>Chúng tôi <b>chỉ bảo đảm</b> cho giao dịch thanh toán online qua Ngân Lượng hoặc Giao hàng - Thu tiền (CoD) bởi Shipchung. Người mua hết sức thận trọng khi giao dịch với người bán.</p>
                                    <c:if test="${viewer.user != null && viewer.user.id == item.sellerId}">
                                        <br>
                                        <p>Để không hiển thị cảnh báo này với người mua, bạn hãy liên kết tài khoản ChợĐiệnTử với tài khoản thanh toán NgânLượng và tài khoản vận chuyển ShipChung <a href="${baseUrl}/user/cau-hinh-tich-hop.html">tại dây.</a></p>
                                    </c:if>
                                </div>
                            </c:if>
                            <div class="row">
                                <span class="icon16-plus"></span>
                                <span class="text-info" style="cursor: pointer" onclick="item.sendPageSearch();"><b class="countItemSame"></b></span> sản phẩm khác cùng loại có giá từ <span class="text-danger"><b>${text:numberFormat(item.sellPrice)} <sup class="u-price">đ</sup></b></span> 
                                    <c:if test="${item.modelId == null || item.modelId == ''}">
                                    <a class="btn btn-default btn-sm" onclick="item.sendPageSearch();" >So sánh giá</a>
                                </c:if>
                            </div>
                            <c:if test="${item.modelId != null && item.modelId != ''}">
                                <div class="row">
                                    <div class="text-outer">
                                        <a class="btn btn-default btn-sm" onclick="item.sendPageSearch();" >So sánh giá</a>

                                        <a class="btn btn-default btn-sm" onclick="modelCompare.pop('${item.categoryId}', '${item.modelId}');" >So sánh sản phẩm</a>

                                    </div>
                                </div>
                            </c:if>
                        </div><!-- pd-center -->
                    </c:if>
                    <!-- ----------------------------------------Đấu giá---------------------------------------------------- --> 
                    <c:if test="${item.listingType != 'BUYNOW'}">
                        <div class="pd-center">
                            <div class="row">
                                <label>Tình trạng:</label>
                                <c:if test="${item.condition == 'NEW'}">
                                    <div class="text-outer">Hàng mới</div>
                                </c:if>
                                <c:if test="${item.condition == 'OLD'}">
                                    <div class="text-outer">Hàng mới</div>
                                </c:if>
                            </div>
                            <div class="row">
                                <label>Thời gian đấu:</label>
                                <c:if test="${item.endTime > datetime && item.startTime <= datetime}">
                                    <div class="text-outer">
                                        Còn lại&nbsp;
                                        <div class="big-clock" data-rel="timeBidBuy">
                                            <div class="sf-row countTotalDateBid">
                                                <span class="time-count"></span>
                                                <span class="digits"></span>
                                            </div>
                                        </div><!-- big-clock -->
                                    </div>
                                </c:if>
                                <c:if test="${item.endTime < datetime && item.startTime < datetime}">
                                    <div class="text-outer"><b class="text-danger">Hết hạn đấu</b></div>
                                </c:if>
                                <c:if test="${item.startTime > datetime && item.startTime > datetime}">
                                    <div class="text-outer"><b class="text-danger">Chưa bắt đầu</b></div>
                                </c:if>
                            </div>
                            <div class="row">
                                <label>Phí vận chuyển:</label>
                                <div class="text-outer">
                                    <c:if test="${item.shipmentType == null || item.shipmentType == 'AGREEMENT'}">
                                        Không rõ phí
                                    </c:if>
                                    <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice == 0}">
                                        Miễn phí
                                    </c:if>
                                    <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice > 0}">
                                        Cố định: ${text:numberFormat(item.shipmentPrice)} <sup class="u-price">đ</sup>
                                    </c:if>
                                    <c:if test="${item.shipmentType == 'BYWEIGHT'}">
                                        Linh hoạt theo địa chỉ người mua&nbsp;&nbsp; <c:if test="${ct.scId == '18' || ct.scId =='52'}"><a href="">» Xem chi tiết</a></c:if>
                                    </c:if>
                                </div>
                            </div>
                            <c:if test="${item.gift}"> 
                                <div class="row">
                                    <label>Ưu đãi:</label>
                                    <div class="text-outer">
                                        <c:set var="newline" value="<%= '\n'%>" />
                                        ${fn:replace(item.giftDetail, newline, "<br />")}
                                    </div>
                                </div>
                            </c:if> 
                            <c:if test="${item.shipmentType == 'BYWEIGHT' && item.weight < 2000}">
                                <div class="row">
                                    <label>CĐT hỗ trợ:</label>
                                    <div class="text-outer">
                                        <p class="text-danger">Miễn phí phí vận chuyển khi thanh toán qua NgânLượng với đơn hàng không quá 2kg.</p>
                                    </div>
                                </div>
                            </c:if>
                            <div class="row">
                                <div class="box-button">
                                    <div class="row">
                                        <label>Giá hiện tại:</label>
                                        <div class="text-outer">
                                            <c:if test="${item.highestBid <= 0}">
                                                <span class="price">${text:numberFormat(item.startPrice)} <sup class="u-price">đ</sup></span>
                                            </c:if>
                                            <c:if test="${item.highestBid > 0}">
                                                <span class="price">${text:numberFormat(item.highestBid)} <sup class="u-price">đ</sup></span>
                                            </c:if>
                                            &nbsp;<span class="icon20-bidgray"></span><a class="f11" href="javascript:;" onclick="item.bidHistory('${item.id}');"><b>(${item.bidCount} lượt đấu)</b></a>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label>Bước giá:</label>
                                        <div class="text-outer"><b>${text:numberFormat(item.bidStep)} <sup class="u-price">đ</sup></b></div>
                                    </div>
                                    <div class="row">
                                        <label>Đặt giá:</label>
                                        <div class="text-outer">
                                            <input name="bidPrice" type="text"  class="bid-text numberBidFormat" value="0" />
                                            <a class="btn btn-primary btn-lg bid-button"  href="javascript:;" <c:if test="${item.endTime < datetime}">disabled</c:if> onclick="item.bidItem('${item.id}');">Đặt giá</a>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="text-outer">
                                                <span class="bid-hintprice">Tối thiểu là ${text:numberFormat((item.highestBid==0?item.startPrice:item.highestBid) + item.bidStep)} <sup class="u-price">đ</sup></span>
                                            <input name="auto" type="checkbox" value="" />
                                            <span class="icon-autobid"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${item.sellPrice > 0}" >
                                <div class="row">
                                    <label>${(item.endTime < datetime && item.highestBider == viewer.user.id)?'Giá thắng cuộc:':'Giá mua ngay:'}</label>
                                    <div class="text-outer">
                                        <c:if test="${item.endTime < datetime && item.highestBider == viewer.user.id}">
                                            <span class="price">${text:numberFormat(item.highestBid)} <sup class="u-price">đ</sup></span>
                                        </c:if>
                                        <c:if test="${item.highestBider != viewer.user.id}">
                                            <span class="price">${text:numberFormat(item.sellPrice)} <sup class="u-price">đ</sup></span>
                                        </c:if>
                                        <a class="btn btn-danger btn-lg buy-button" href="javascript:;" <c:if test="${(item.endTime < datetime && item.highestBider != viewer.user.id) || !item.active}">disabled</c:if> onclick="order.buyNow('${item.id}', '${item.sellerId}');">Mua ngay</a>
                                        </div>
                                    </div>
                                    <div class="row-right">
                                        <a class="push-cart" href="javascript:;" <c:if test="${(item.endTime < datetime && item.highestBider != viewer.user.id) || !item.active}">disabled</c:if> onclick="order.addToCart('${item.id}');"><span class="icon20-pushcart"></span>Cho vào giỏ hàng</a>
                                    </div>
                            </c:if>
                            <c:if test="${item.sellPrice <= 0 && item.endTime < datetime && item.highestBider == viewer.user.id}" >
                                <div class="row">
                                    <label>Giá thắng cuộc:</label>
                                    <div class="text-outer">
                                        <span class="price">${text:numberFormat(item.highestBid)} <sup class="u-price">đ</sup></span>
                                        <a class="btn btn-danger btn-lg buy-button" href="javascript:;" onclick="order.buyNow('${item.id}', '${item.sellerId}');">Mua ngay</a>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${seller != null && seller.nlIntegrated && seller.scIntegrated}">
                                <div class="row">
                                    <label>Giao hàng:</label>
                                    <div class="text-outer">Giao hàng toàn quốc tới tận huyện, xã&nbsp;&nbsp;<a class="f11" href="#VC">» Xem chi tiết</a></div>
                                </div>
                                <div class="row">
                                    <div class="text-outer">
                                        <div><p class="p-checkred cdt-tooltip" data-toggle="tooltip" data-placement="right" ><span class="icon16-checkok"></span>Giao hàng nhanh nội thành từ 2h-8h</p></div>
                                        <div><p class="p-checkred cdt-tooltip" data-toggle="tooltip" data-placement="right" ><span class="icon16-checkok"></span>Giao hàng tiết kiệm nội thành từ 8h-16h</p></div>
                                    </div>
                                </div>
                                <div class="row">
                                    <label>Thanh toán:</label>
                                    <div class="text-outer">
                                        <div><p class="p-checkred"><span class="icon16-checkok"></span>Thu tiền tận nơi trên toàn quốc (CoD) - Miễn phí</p></div>
                                        <div><p class="p-checkred"><span class="icon16-checkok"></span>Thanh toán online: Trên 30 kênh thanh toán</p></div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="text-outer">
                                        <ul class="ul-bank">
                                            <li><a title="NgânLượng.vn" target="_blank" href="http://www.nganluong.vn"><span class="icon-nl"></span></a></li>
                                            <li><a title="Thẻ thanh toán VisaCard" target="_blank" href="http://www.vietnam-visa.com" class="visacrd"></a></li>
                                            <li><a title="Thẻ thanh toán MasterCard" target="_blank" href="http://www.mastercard.com" class="mastercrd"></a></li>
                                            <li><a title="Hệ thống thanh toán trực tuyến Paypal" target="_blank" href="https://www.paypal.com" class="paypal"></a></li>
                                            <li><a href="#"><span class="icon-bank"></span></a></li>
                                        </ul>
                                        <a class="f11" href="#payment">» Xem chi tiết</a>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${seller != null && seller.nlIntegrated && !seller.scIntegrated}">
                                <div class="row">
                                    <label>Thanh toán:</label>
                                    <div class="text-outer">
                                        <div><p class="p-checkred"><span class="icon16-checkok"></span>Thanh toán online: Trên 30 kênh thanh toán</p></div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="text-outer">
                                        <ul class="ul-bank">
                                            <li><a title="NgânLượng.vn" target="_blank" href="http://www.nganluong.vn"><span class="icon-nl"></span></a></li>
                                            <li><a title="Thẻ thanh toán VisaCard" target="_blank" href="http://www.vietnam-visa.com" class="visacrd"></a></li>
                                            <li><a title="Thẻ thanh toán MasterCard" target="_blank" href="http://www.mastercard.com" class="mastercrd"></a></li>
                                            <li><a title="Hệ thống thanh toán trực tuyến Paypal" target="_blank" href="https://www.paypal.com" class="paypal"></a></li>
                                            <li><a href="#"><span class="icon-bank"></span></a></li>
                                        </ul>
                                        <a class="f11" href="#payment">» Xem chi tiết</a>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${seller != null && !seller.nlIntegrated && seller.scIntegrated}">
                                <div class="row">
                                    <label>Giao hàng:</label>
                                    <div class="text-outer">Giao hàng toàn quốc tới tận huyện, xã&nbsp;&nbsp;<a class="f11" href="#VC">» Xem chi tiết</a></div>
                                </div>
                                <div class="row">
                                    <div class="text-outer">
                                        <div><p class="p-checkred cdt-tooltip" data-toggle="tooltip" data-placement="right" ><span class="icon16-checkok"></span>Giao hàng nhanh nội thành từ 2h-8h</p></div>
                                        <div><p class="p-checkred cdt-tooltip" data-toggle="tooltip" data-placement="right" ><span class="icon16-checkok"></span>Giao hàng tiết kiệm nội thành từ 8h-16h</p></div>
                                    </div>
                                </div>
                                <div class="row">
                                    <label>Thanh toán:</label>
                                    <div class="text-outer">
                                        <div><p class="p-checkred"><span class="icon16-checkok"></span>Thu tiền tận nơi trên toàn quốc (CoD) - Miễn phí</p></div>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${seller != null && !seller.nlIntegrated && !seller.scIntegrated}">
                                <div class="cdt-message bg-danger">
                                    <p><b class="text-danger">Cảnh báo:</b> Giao dịch trực tiếp chứa đựng khả năng lừa đảo cao. ChợĐiệnTử không bảo đảm cho giao dịch này!</p>
                                    <br>
                                    <p>Chúng tôi <b>chỉ bảo đảm</b> cho giao dịch thanh toán online qua Ngân Lượng hoặc Giao hàng - Thu tiền (CoD) bởi Shipchung. Người mua hết sức thận trọng khi giao dịch với người bán.</p>
                                    <c:if test="${viewer.user != null && viewer.user.id == item.sellerId}">
                                        <br>
                                        <p>Để không hiển thị cảnh báo này với người mua, bạn hãy liên kết tài khoản ChợĐiệnTử với tài khoản thanh toán NgânLượng và tài khoản vận chuyển ShipChung <a href="${baseUrl}/user/cau-hinh-tich-hop.html">tại dây.</a></p>
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test="${itemSamePrice != null && itemSamePrice.dataCount > 0}">
                                <div class="row">
                                    <span class="icon16-plus"></span>
                                    <span class="text-info" style="cursor: pointer" onclick="item.sendPageSearch();"><b class="countItemSame"></b></span> sản phẩm khác cùng loại có giá từ <span class="text-danger"><b>${text:numberFormat(item.sellPrice)} <sup class="u-price">đ</sup></b></span>
                                </div>
                                <div class="row">
                                    <div class="text-outer">
                                        <a class="btn btn-default btn-sm" onclick="item.sendPageSearch();" >So sánh giá</a>
                                        <c:if test="${item.modelId != null && item.modelId != ''}">
                                            <a class="btn btn-default btn-sm" onclick="modelCompare.pop('${item.categoryId}', '${item.modelId}');" >So sánh sản phẩm</a>
                                        </c:if>
                                    </div>
                                </div>
                            </c:if>
                        </div><!-- pd-center -->
                    </c:if>
                    <div class="pd-sidebar">
                        <c:if test="${shop != null}">
                            <div class="pd-user grid">
                                <div class="g-row" style="margin-bottom:7px;"><b>Thông tin shop:</b></div>
                                <c:if test="${shop.logo == null || shop.logo == ''}">
                                    <div class="img">
                                        <img src="${baseUrl}/static/market/images/no-avatar.png" alt="avatar" />
                                    </div>
                                </c:if>
                                <c:if test="${shop.logo != null && shop.logo != ''}">
                                    <div class="img">
                                        <img src="${shop.logo}" alt="${shop.title}" />
                                    </div>
                                </c:if>
                                <div class="g-content">
                                    <div class="g-row">
                                        <c:if test="${fn:length(shop.title) > 12}">
                                            <c:if test="${fn:length(shop.alias) > 12}">
                                                <c:set var="stringShopAlias"  value="${fn:substring(shop.alias, 0, 10)}" />
                                                Shop: <a href="${baseUrl}/${shop.alias}/" target="_blank" title="${shop.alias}">
                                                    <b>${stringShopAlias}...</b>
                                                </a>
                                            </c:if>
                                            <c:if test="${fn:length(shop.alias) <= 12}">
                                                Shop: <a href="${baseUrl}/${shop.alias}/"  target="_blank" title="${shop.alias}">
                                                    <b>${shop.alias}</b>
                                                </a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${fn:length(shop.title) <= 12}">
                                            Shop: <a href="${baseUrl}/${shop.alias}/"  target="_blank">
                                                <b>${shop.title}</b>
                                            </a>
                                        </c:if>
                                    </div>
                                    <div class="g-row sellerReviewPoint">Điểm uy tín: </div>
                                    <div class="g-row f11"><b class="text-danger sellerReviewPercent"></b> người mua đánh giá tốt</div>
                                </div>
                                <c:if test="${shop.address != null && shop.address != ''}">
                                    <div class="g-row"><p class="p-checkred"><span class="icon16-placegray"></span>${shop.address} (<a style="cursor: pointer" onclick="item.getMaps('${shop.address}')" data-toggle="modal" data-target="#ModalNormal">Bản đồ</a>)</p></div>
                                </c:if>
                                <c:if test="${(shop.address == null || shop.address == '') && user.address != null && user.address != ''}">
                                    <div class="g-row"><p class="p-checkred"><span class="icon16-placegray"></span>${user.address} (<a style="cursor: pointer" onclick="item.getMaps('${user.address}')" data-toggle="modal" data-target="#ModalNormal">Bản đồ</a>)</p></div>
                                </c:if>
                                <div class="g-row"><p class="p-checkred"><span class="icon16-telgray"></span>${(shop.phone != null && shop.phone != '')?shop.phone:user.phone}</p></div>
                            </div><!-- pd-user -->
                        </c:if>
                        <c:if test="${shop == null}">
                            <div class="pd-user grid">
                                <div class="g-row" style="margin-bottom:7px;"><b>Thông tin người bán:</b></div>
                                <c:if test="${user.avatar == null || user.avatar == ''}">
                                    <div class="img">
                                        <img src="${baseUrl}/static/market/images/no-avatar.png" alt="avatar" />
                                    </div>
                                </c:if>
                                <c:if test="${user.avatar != null && user.avatar != ''}">
                                    <div class="img">
                                        <img src="${user.avatar}" alt="avatar" />
                                    </div>
                                </c:if>
                                <div class="g-content">
                                    <div class="g-row">
                                        <c:if test="${user.name == null || user.name == ''}">
                                            <c:if test="${fn:length(user.username) > 12}">
                                                <c:set var="stringUsername" value="${fn:substring(user.username, 0, 10)}" />
                                                Người bán: <a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(item.sellerId).concat('"}]'))}"  target="_blank" title="${user.username}">
                                                    <b>${stringUsername}...</b>
                                                </a>
                                            </c:if>
                                            <c:if test="${fn:length(user.username) <= 12}">
                                                Người bán: <a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(item.sellerId).concat('"}]'))}"  target="_blank" title="${user.username}">
                                                    <b>${user.username}</b>
                                                </a>
                                            </c:if>   
                                        </c:if>
                                        <c:if test="${user.name != null && user.name != ''}">
                                            <c:if test="${fn:length(user.name) > 12}">
                                                <c:set var="stringName" value="${fn:substring(user.name, 0, 10)}" />
                                                Người bán: <a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(item.sellerId).concat('"}]'))}"  target="_blank" title="${user.name}">
                                                    <b>${stringName}...</b>
                                                </a>
                                            </c:if>
                                            <c:if test="${fn:length(user.name) <= 12}">
                                                Người bán: <a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(item.sellerId).concat('"}]'))}"  target="_blank" title="${user.name}">
                                                    <b>${user.name}</b>
                                                </a>
                                            </c:if>
                                        </c:if>
                                    </div>
                                    <div class="g-row sellerReviewPoint">Điểm uy tín: </div>
                                    <div class="g-row f11"><b class="text-danger sellerReviewPercent"></b> người mua đánh giá tốt</div>
                                </div>
                                <c:if test="${user.address != null && user.address != ''}">
                                    <div class="g-row"><p class="p-checkred"><span class="icon16-placegray"></span>${user.address} (<a style="cursor: pointer" onclick="item.getMaps('${user.address}')" data-toggle="modal" data-target="#ModalNormal">Bản đồ</a>)</p></div>
                                </c:if>
                                <c:if test="${user.phone != null && user.phone != ''}">
                                    <div class="g-row"><p class="p-checkred"><span class="icon16-telgray"></span>${user.phone}</p></div>
                                        </c:if>
                            </div><!-- pd-user -->
                        </c:if>
                        <ul class="pd-ulmore">
                            <li><a href="${baseUrl}/user/${item.sellerId}/ho-so-nguoi-ban.html">Hồ sơ người bán</a></li>
                            <li><a onclick="item.sendQuestion('${item.id}')" style="cursor: pointer">Hỏi thêm người bán về sản phẩm</a></li>
                            <li><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(item.sellerId).concat('"}]'))}">Sản phẩm khác cùng người bán</a></li>
                                <c:if test="${fn:length(contact) > 0}">
                                <li>
                                    <div class="custom-tip ctp-click">
                                        <a class="btn-ctp" href="">Hỗ trợ trực tuyến</a>
                                        <div class="custom-tip-pop ctp-right">
                                            <div class="ctp-inner">
                                                <span class="icon20-close"></span>
                                                <div class="ctp-content">
                                                    <div class="pd-support ps-two-col">
                                                        <c:forEach var="userContact" items="${contact}" varStatus="stt">
                                                            <div class="ps-col">
                                                                <div class="ps-row"><b>${userContact.title}</b></div>
                                                                <div class="ps-row"><span class="icon16-mobile-green"></span>${userContact.phone}</div>
                                                                <div class="ps-row"><a href="ymsgr:sendim?lee_haira"><img src='http://opi.yahoo.com/online?u=lee_haira&m=g&t=5'/>${userContact.yahoo}</a></div>
                                                                <div class="ps-row"><a href="skype:leehaira?chat"><span class="icon16-skype"></span>${userContact.skype}</a></div>
                                                                <div class="ps-row"><span class="icon16-email-red"></span>${userContact.email}</div>
                                                            </div><!-- ps-col -->
                                                            <c:if test="${stt.last}">
                                                                <div class="clearfix"></div>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div><!-- pd-support -->
                                                </div><!-- ctp-content -->
                                            </div><!-- ctp-inner -->
                                        </div><!-- custom-tip-pop -->
                                    </div><!-- custom-tip -->
                                </li>
                            </c:if>
                        </ul>
                        <c:if test="${seller != null && seller.nlIntegrated && seller.scIntegrated}">
                            <div class="pd-shadow">
                                <p class="p-checkred"><span class="icon20-protected"></span><b>Bảo vệ người mua</b></p>	
                                <ul class="pd-ulmore">
                                    <li>An toàn <b>100%</b> với CoD</li>
                                    <li>Bảo hiểm <b>100%</b> giá trị giao dịch (không quá 10.000.000) khi thanh toán qua Ngân Lượng</li>
                                </ul>
                            </div><!-- pd-shadow -->
                        </c:if>
                        <c:if test="${seller != null && seller.nlIntegrated && !seller.scIntegrated}">
                            <div class="pd-shadow">
                                <p class="p-checkred"><span class="icon20-protected"></span><b>Bảo vệ người mua</b></p>	
                                <ul class="pd-ulmore">
                                    <li>Bảo hiểm <b>100%</b> giá trị giao dịch (không quá 10.000.000) khi thanh toán qua Ngân Lượng</li>
                                </ul>
                            </div><!-- pd-shadow -->
                        </c:if>
                        <c:if test="${seller != null && !seller.nlIntegrated && seller.scIntegrated}">
                            <div class="pd-shadow">
                                <p class="p-checkred"><span class="icon20-protected"></span><b>Bảo vệ người mua</b></p>	
                                <ul class="pd-ulmore">
                                    <li>An toàn <b>100%</b> với CoD</li>
                                </ul>
                            </div><!-- pd-shadow -->
                        </c:if>
                        <c:if test="${itAdv != null && fn:length(itAdv.data) > 1 && !seller.closeAdv}">
                            <div class="pd-shadow pd-cdt-qc showADVSeller">
                                <c:if test="${viewer.user != null && viewer.user.id == item.sellerId}">
                                    <span class="icon20-close" onclick="item.closeADV();"></span>
                                </c:if>
                                <p class="p-promotion">Sản phẩm quảng cáo</p>
                                <c:if test="${viewer.user == null || viewer.user.id != item.sellerId}">
                                    <c:forEach var="it" items="${itAdv.data}" begin="0" end="2">
                                        <c:if test="${it.id != item.id}">
                                            <div class="grid">
                                                <a class="img" href="${url:item(it.id,it.name)}"><img src="${(it.images != null && fn:length(it.images) >0)?it.images[0]:staticUrl.concat('/market/images/no-image-product.png')}" alt="${it.name}" /></a>
                                                <div class="g-content">
                                                    <div class="g-row">
                                                        <c:if test="${fn:length(it.name) > 35}">
                                                            <c:set var="stringName" value="${fn:substring(it.name, 0, 34)}" />
                                                            <a class="f11" href="${url:item(it.id,it.name)}" title="${it.name}">${stringName}..</a>
                                                        </c:if>
                                                        <c:if test="${fn:length(it.name) <= 35}">
                                                            <a class="f11" href="${url:item(it.id,it.name)}">${it.name}</a>
                                                        </c:if>
                                                    </div>
                                                    <c:if test="${(text:sellPrice(it.sellPrice,it.discount,it.discountPrice,it.discountPercent)) != '0'}">
                                                        <div class="g-row"><b class="f11 text-danger">${text:sellPrice(it.sellPrice,it.discount,it.discountPrice,it.discountPercent)} <sup class="u-price">đ</sup></b></div>
                                                    </c:if>
                                                </div>
                                            </div><!-- grid -->
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${viewer.user != null && viewer.user.id == item.sellerId}">           
                                    <c:forEach var="it" items="${itAdv.data}" begin="0" end="1">
                                        <c:if test="${it.id != item.id}"> 
                                            <div class="grid">
                                                <a class="img" href="${url:item(it.id,it.name)}"><img src="${(it.images != null && fn:length(it.images) >0)?it.images[0]:staticUrl.concat('/market/images/no-image-product.png')}" alt="${it.name}" /></a>
                                                <div class="g-content">
                                                    <div class="g-row">
                                                        <c:if test="${fn:length(it.name) > 35}">
                                                            <c:set var="stringName" value="${fn:substring(it.name, 0, 34)}" />
                                                            <a class="f11" href="${url:item(it.id,it.name)}" title="${it.name}">${stringName}..</a>
                                                        </c:if>
                                                        <c:if test="${fn:length(it.name) <= 35}">
                                                            <a class="f11" href="${url:item(it.id,it.name)}">${it.name}</a>
                                                        </c:if>
                                                    </div>
                                                    <c:if test="${(text:sellPrice(it.sellPrice,it.discount,it.discountPrice,it.discountPercent)) != '0'}">
                                                        <div class="g-row"><b class="f11 text-danger">${text:sellPrice(it.sellPrice,it.discount,it.discountPrice,it.discountPercent)} <sup class="u-price">đ</sup></b></div>
                                                    </c:if>
                                                </div>
                                            </div><!-- grid -->
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${viewer.user != null && viewer.user.id == item.sellerId}">
                                    <div class="bg-warning">
                                        Bạn có muốn người mua không nhìn thấy quảng cáo này không?
                                        <br>Tắt quảng cáo: <b>30.000 xèng</b>
                                    </div>
                                </c:if>
                            </div><!-- pd-shadow -->
                        </c:if>
                    </div><!-- pd-sidebar -->
                    <div class="clearfix"></div>
                </div><!-- pd-content -->
            </div><!-- pd-detail -->
            <c:if test="${promotion != null && fn:length(itemPromotion) >= 4}">
                <div class="pd-featured">
                    <h4>${promotion.name}</h4> 
                    <p>Thời gian áp dụng từ ngày  <jsp:setProperty name="date" property="time" value="${promotion.startTime}" /> 
                        <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate> đến hết ngày <jsp:setProperty name="date" property="time" value="${promotion.endTime}" /> 
                        <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate></p>
                        <div class="pf-content">
                            <ul class="pd-featured-slider jcarousel jcarousel-skin-tango">
                            <c:forEach var="proIt" items="${itemPromotion}">
                                <c:if test="${proIt.endTime > datetime && proIt.quantity > 0 && proIt.listingType != 'AUCTION'}"> 
                                    <li>
                                        <div class="pd-item">
                                            <c:if test="${(text:percentPrice(proIt.startPrice,proIt.sellPrice,proIt.discount,proIt.discountPrice,proIt.discountPercent)) != '0'}">
                                                <span class="pd-sale"> - ${text:percentPrice(proIt.startPrice,proIt.sellPrice,proIt.discount,proIt.discountPrice,proIt.discountPercent)}% Off</span>
                                            </c:if>
                                            <div class="pd-thumb">
                                                <a href="${url:item(proIt.id,proIt.name)}">
                                                    <c:if test="${proIt.images != null && fn:length(proIt.images) > 0}">
                                                        <img src="${proIt.images[0]}" alt="${proIt.name}" />
                                                    </c:if>
                                                    <c:if test="${proIt.images == null || fn:length(proIt.images) == 0}">
                                                        <img src="${baseUrl}/static/market/images/no-avatar.png"  />
                                                    </c:if>
                                                </a>
                                            </div>
                                            <div class="pd-row"><a class="pd-link" href="${url:item(proIt.id,proIt.name)}">${proIt.name}</a></div>
                                            <div class="pd-row">
                                                <c:if test="${proIt.listingType != 'AUCTION'}">
                                                    <c:if test="${(text:sellPrice(proIt.sellPrice,proIt.discount,proIt.discountPrice,proIt.discountPercent)) != '0'}">
                                                        <span class="pd-price">${text:sellPrice(proIt.sellPrice,proIt.discount,proIt.discountPrice,proIt.discountPercent)} <sup class="u-price">đ</sup></span> 
                                                    </c:if>
                                                    <c:if test="${(text:startPrice(proIt.startPrice,proIt.sellPrice,proIt.discount)) != '0'}">
                                                        <span class="pd-oldprice">${text:startPrice(proIt.startPrice,proIt.sellPrice,proIt.discount)} <sup class="u-price">đ</sup></span>
                                                    </c:if>
                                                </c:if>
                                            </div>
                                        </div>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </c:if>
            <div class="page-detail">
                <div class="boxblue" id="tab1">
                    <div class="boxblue-title full-tab">
                        <ul class="pull-left move-tabclick">               	
                            <li class="active"><a href="#tab1">Mô tả chi tiết sản phẩm</a></li>
                            <li><a href="#tab2">Hướng dẫn mua hàng</a></li>
                            <li><a href="#tab3"><span class="icon24-fb-white"></span>Bình luận</a></li>
                            <li><a href="#tab4">Đánh giá/Nhận xét sản phẩm</a></li>
                        </ul>
                    </div><!--boxblue-title-->
                    <div class="boxblue-content">
                        <div class="detail-content">
                            <div class="bluewhite">
                                <div class="bluewhite-title"><label class="lb-name"><span class="icon16-bluewhite"></span>Thông số kỹ thuật</label></div>
                                <div class="bluewhite-content">
                                    <div class="tech-intro">
                                        <p>Chú ý: Thông số kỹ thuật chỉ mang tính tham khảo, bạn hãy liên lạc trực tiếp với người bán để có được thông tin chính xác về sản phẩm. Chân thành cảm ơn bạn!</p>
                                        <table class="table">
                                            <tbody>
                                                <c:if test="${item.manufacturerName != null && item.manufacturerName != ''}">
                                                    <tr>
                                                        <td class="col1">Nhãn hiệu</td>
                                                        <td class="col2">${item.manufacturerName}</td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${model != null}">
                                                    <tr>
                                                        <td class="col1">Model</td>
                                                        <td class="col2">
                                                            <a href="${baseUrl}/model/${model.id}/${text:createAlias(model.name)}.html">${model.name}</a>
                                                            <c:if test="${item.modelId != null && item.modelId != ''}">
                                                                <a class="btn btn-default btn-sm" onclick="modelCompare.pop('${item.categoryId}', '${item.modelId}');" >So sánh sản phẩm</a>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${item.properties != null && fn:length(item.properties) > 0}">
                                                    <c:forEach var="property" items="${item.properties}">
                                                        <c:if test="${property.categoryPropertyValueIds != null && fn:length(property.categoryPropertyValueIds) > 0}">
                                                            <c:set var="values" value="" />
                                                            <c:forEach var="value" items="${property.categoryPropertyValueIds}">
                                                                <c:forEach var="cateValue" items="${catePropertyValues}">
                                                                    <c:if test='${cateValue.categoryPropertyId==property.categoryPropertyId && cateValue.id == value && cateValue.name!=null && cateValue.name.trim()!=""}'>
                                                                        <c:set var="values" >${values} <li>${cateValue.name}</li></c:set>                                    
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:forEach>
                                                            <c:if test="${values.trim() != ''}">
                                                            <tr>
                                                                <td class="col1">
                                                                    <c:forEach var="cateProperty" items="${cateProperties}">
                                                                        <c:if test="${cateProperty.id==property.categoryPropertyId}">
                                                                            ${cateProperty.name}
                                                                        </c:if>
                                                                    </c:forEach> 
                                                                </td>
                                                                <td class="col2">
                                                                    <ul>
                                                                        ${values}
                                                                    </ul>
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${(property.categoryPropertyValueIds == null || fn:length(property.categoryPropertyValueIds) <= 0 ) && property.inputValue!=null && property.inputValue!=''}">
                                                        <tr>
                                                            <td class="col1">
                                                                <c:forEach var="cateProperty" items="${cateProperties}">
                                                                    <c:if test="${cateProperty.id==property.categoryPropertyId}">
                                                                        ${cateProperty.name}
                                                                    </c:if>
                                                                </c:forEach> 
                                                            </td>
                                                            <td class="col2">
                                                                <ul>
                                                                    ${property.inputValue}
                                                                </ul>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            </tbody>
                                        </table>
                                    </div><!--tech-intro-->
                                </div><!--bluewhite-content-->
                            </div><!--bluewhite-->
                            <div class="bluewhite">
                                <div class="bluewhite-title"><label class="lb-name"><span class="icon16-bluewhite"></span>Thông tin chi tiết</label></div>
                                <div class="bluewhite-content">
                                    <div class="detail-content" data-rel="detail_item"  >
                                        <c:if test="${itemDetail != null}">
                                            <p class="content-details" >${itemDetail.detail}</p>
                                        </c:if>
                                    </div><!--detail-content-->
                                </div><!--bluewhite-content-->
                            </div><!--bluewhite-->
                            <c:if test="${item.sellerId == viewer.user.id && (seller.salesPolicy == null || fn:length(seller.salesPolicy) <= 0 )}">
                                <div class="bluewhite">
                                    <p>Chưa quy định chính sách này! <a href="${baseUrl}/user/chinh-sach-ban-hang.html">Vào đây</a> để mô tả rõ chính sách!</p>
                                </div>
                            </c:if>
                            <c:if test="${seller.salesPolicy != null && fn:length(seller.salesPolicy) > 0}">
                                <div class="bluewhite">
                                    <div class="bluewhite-title"><label class="lb-name"><span class="icon16-bluewhite"></span>Chính sách bán hàng</label></div>
                                    <div class="bluewhite-content">
                                        <div class="detail-content">
                                            <c:forEach var="policy" items="${seller.salesPolicy}">
                                                <c:if test="${policy.type =='WARRANT'}">
                                                    <p><b>Chính sách bảo hành - đổi trả</b></p>
                                                </c:if>
                                                <c:if test="${policy.type =='SHIPPING'}">
                                                    <p><b>Chính sách vận chuyển - giao hàng</b></p>
                                                </c:if>
                                                <c:if test="${policy.type =='INSTALLATION'}">
                                                    <p><b>Chính sách lắp đặt - thi công</b></p>
                                                </c:if>
                                                <c:if test="${policy.type =='SUPPORT'}">
                                                    <p><b>Chính sách hậu mãi & chăm sóc khách hàng</b></p>
                                                </c:if>
                                                <p>${policy.description}</p>
                                                <c:if test="${policy.policy != null && fn:length(policy.policy) > 0}">
                                                    <ul>
                                                        <c:forEach var="pl" items="${policy.policy}">
                                                            <li>${pl}</li>
                                                            </c:forEach>
                                                    </ul>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div><!--detail-content-->
                    </div><!--boxblue-content-->
                </div><!--boxblue-->
                <div class="boxblue" id="tab2">
                    <div class="boxblue-title full-tab">
                        <ul class="pull-left move-tabclick">               	
                            <li><a href="#tab1">Mô tả chi tiết sản phẩm</a></li>
                            <li class="active"><a href="#tab2">Hướng dẫn mua hàng</a></li>
                            <li><a href="#tab3"><span class="icon24-fb-white"></span>Bình luận</a></li>
                            <li><a href="#tab4">Đánh giá/Nhận xét sản phẩm</a></li>
                        </ul>
                    </div><!--boxblue-title-->
                    <div class="boxblue-content">
                        <div class="detail-content">
                            <div class="bluewhite">
                                <div class="bluewhite-title"><label class="lb-name"><span class="icon16-bluewhite"></span>Hướng dẫn mua hàng</label></div>
                                <div class="bluewhite-content">
                                    <div class="detail-content">
                                        <p align="center"><img src="${baseUrl}/static/market/images/intro-buyer.png" alt="introduction" /></p>
                                    </div><!--detail-ontent-->
                                </div><!--bluewhite-content-->
                            </div><!--bluewhite-->
                            <c:if test="${ct.scId == '18' || ct.scId =='52'}">
                                <div class="bluewhite">
                                    <div class="bluewhite-title"><label class="lb-name"><a id="VC"></a><span class="icon16-bluewhite"></span>Vận chuyển - Giao hàng</label></div>
                                    <div class="bluewhite-content">
                                        <div class="detail-content">
                                            <p>Phí vận chuyển linh hoạt theo địa chỉ người mua&nbsp;&nbsp;</p>
                                            <p><b>Bảng giá tham khảo</b></p>
                                            <div class="trans-table table-responsive">
                                                <c:choose>
                                                    <c:when test="${ct.scId == '18'}">
                                                        <c:if test="${district.scId == '191' || district.scId == '190' || district.scId == '189' || district.scId == '178' || district.scId == '174' || district.scId == '173' || district.scId == '167' || district.scId == '163'}">
                                                            <table class="table">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="text-align:left;"><p class="text-info">Địa chỉ người mua</p></th>
                                                                <th><p class="text-info">Giao hành nhanh</p><p>(Dưới 24h)</p></th>
                                                                <th><p class="text-info">Giao hàng tiết kiệm</p><p>(Dưới 72h)</p></th>
                                                                <th><p class="text-info">Thu tiền tại nhà</p></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>
                                                                            <p><b>Hà Nội</b></p>
                                                                            <p><span class="trans-name">Nội thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Ba Đình, Hoàn Kiếm, Đống Đa, Hai Bà Trưng , Thanh Xuân, Cầu Giấy, Tây Hồ, Hoàng Mai"></span></p>
                                                                            <p><span class="trans-name">Ngoại thành 1</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Long Biên, Từ Liêm, Hà Đông"></span></p>
                                                                            <p><span class="trans-name">Ngoại thành 2</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Thanh Trì, Gia Lâm"></span></p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;250 gram</p>
                                                                            <p>20.000 đ</p>
                                                                            <p>30.000 đ</p>
                                                                            <p>40.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;2 kg</p>
                                                                            <p>15.000 đ</p>
                                                                            <p>20.000 đ</p>
                                                                            <p>30.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <br>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <p><b>TP. Hồ CHí Minh</b></p>
                                                                            <p><span class="trans-name">Nội thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Quận 1,2,3,4,5,6,7,8,10,11, Tân Bình, Tân Phú, Phú Nhuận, Bình Thạnh, Gò Vấp, Quận 9, 12 , Thủ Đức, Bình Tân."></span></p>
                                                                            <p><span class="trans-name">Ngoại thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Hóc Môn, Bình Chánh, Nhà Bè"></span></p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;250 gram</p>
                                                                            <p>32.000 đ</p>
                                                                            <p>38.400 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;2 kg</p>
                                                                            <p>36.000 đ</p>
                                                                            <p>46.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <br>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </c:if>
                                                        <c:if test="${district.scId == '186' || district.scId == '170' || district.scId == '186'}">
                                                            <table class="table">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="text-align:left;"><p class="text-info">Địa chỉ người mua</p></th>
                                                                <th><p class="text-info">Giao hành nhanh</p><p>(Dưới 24h)</p></th>
                                                                <th><p class="text-info">Giao hàng tiết kiệm</p><p>(Dưới 72h)</p></th>
                                                                <th><p class="text-info">Thu tiền tại nhà</p></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>
                                                                            <p><b>Hà Nội</b></p>
                                                                            <p><span class="trans-name">Nội thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Ba Đình, Hoàn Kiếm, Đống Đa, Hai Bà Trưng , Thanh Xuân, Cầu Giấy, Tây Hồ, Hoàng Mai"></span></p>
                                                                            <p><span class="trans-name">Ngoại thành 1</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Long Biên, Từ Liêm, Hà Đông"></span></p>
                                                                            <p><span class="trans-name">Ngoại thành 2</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Thanh Trì, Gia Lâm"></span></p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;250 gram</p>
                                                                            <p>40.000 đ</p>
                                                                            <p>40.000 đ</p>
                                                                            <p>50.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;2 kg</p>
                                                                            <p>30.000 đ</p>
                                                                            <p>30.000 đ</p>
                                                                            <p>40.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <br>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <p><b>TP. Hồ CHí Minh</b></p>
                                                                            <p><span class="trans-name">Nội thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Quận 1,2,3,4,5,6,7,8,10,11, Tân Bình, Tân Phú, Phú Nhuận, Bình Thạnh, Gò Vấp, Quận 9, 12 , Thủ Đức, Bình Tân."></span></p>
                                                                            <p><span class="trans-name">Ngoại thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Hóc Môn, Bình Chánh, Nhà Bè"></span></p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;250 gram</p>
                                                                            <p>32.000 đ</p>
                                                                            <p>38.400 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;2 kg</p>
                                                                            <p>36.000 đ</p>
                                                                            <p>46.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <br>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </c:if>
                                                        <c:if test="${district.scId == '176' || district.scId == '172'}">
                                                            <table class="table">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="text-align:left;"><p class="text-info">Địa chỉ người mua</p></th>
                                                                <th><p class="text-info">Giao hành nhanh</p><p>(Dưới 24h)</p></th>
                                                                <th><p class="text-info">Giao hàng tiết kiệm</p><p>(Dưới 72h)</p></th>
                                                                <th><p class="text-info">Thu tiền tại nhà</p></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>
                                                                            <p><b>Hà Nội</b></p>
                                                                            <p><span class="trans-name">Nội thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Ba Đình, Hoàn Kiếm, Đống Đa, Hai Bà Trưng , Thanh Xuân, Cầu Giấy, Tây Hồ, Hoàng Mai"></span></p>
                                                                            <p><span class="trans-name">Ngoại thành 1</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Long Biên, Từ Liêm, Hà Đông"></span></p>
                                                                            <p><span class="trans-name">Ngoại thành 2</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Thanh Trì, Gia Lâm"></span></p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;250 gram</p>
                                                                            <p>55.000 đ</p>
                                                                            <p>55.000 đ</p>
                                                                            <p>55.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;2 kg</p>
                                                                            <p>45.000 đ</p>
                                                                            <p>45.000 đ</p>
                                                                            <p>45.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <br>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <p><b>TP. Hồ CHí Minh</b></p>
                                                                            <p><span class="trans-name">Nội thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Quận 1,2,3,4,5,6,7,8,10,11, Tân Bình, Tân Phú, Phú Nhuận, Bình Thạnh, Gò Vấp, Quận 9, 12 , Thủ Đức, Bình Tân."></span></p>
                                                                            <p><span class="trans-name">Ngoại thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Hóc Môn, Bình Chánh, Nhà Bè"></span></p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;250 gram</p>
                                                                            <p>38.400 đ</p>
                                                                            <p>38.400 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;2 kg</p>
                                                                            <p>46.000 đ</p>
                                                                            <p>46.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <br>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </c:if>
                                                    </c:when>
                                                    <c:when test="${ct.scId == '52'}">
                                                        <c:if test="${district.scId == '571' || district.scId == '570' || district.scId == '569' || district.scId == '566' || district.scId == '565' || district.scId == '562' || district.scId == '561' || district.scId == '560' || district.scId == '556' || district.scId == '555' || district.scId == '552' || district.scId == '551' ||district.scId == '550' || district.scId == '549' ||district.scId == '548' }">
                                                            <table class="table">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="text-align:left;"><p class="text-info">Địa chỉ người mua</p></th>
                                                                <th><p class="text-info">Giao hành nhanh</p><p>(Dưới 24h)</p></th>
                                                                <th><p class="text-info">Giao hàng tiết kiệm</p><p>(Dưới 72h)</p></th>
                                                                <th><p class="text-info">Thu tiền tại nhà</p></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>
                                                                            <p><b>TP. Hồ CHí Minh</b></p>
                                                                            <p><span class="trans-name">Nội thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Quận 1,2,3,4,5,6,7,8,10,11, Tân Bình, Tân Phú, Phú Nhuận, Bình Thạnh, Gò Vấp"></span></p>
                                                                            <p><span class="trans-name">Ngoại thành 1</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Quận 9, 12 , Thủ Đức, Bình Tân"></span></p>
                                                                            <p><span class="trans-name">Ngoại thành 2</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Hóc Môn, Bình Chánh, Nhà Bè"></span></p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;250 gram</p>
                                                                            <p>20.000 đ</p>
                                                                            <p>30.000 đ</p>
                                                                            <p>40.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;2 kg</p>
                                                                            <p>15.000 đ</p>
                                                                            <p>20.000 đ</p>
                                                                            <p>30.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <br>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <p><b>Hà Nội</b></p>
                                                                            <p><span class="trans-name">Nội thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Ba Đình, Hoàn Kiếm, Đống Đa, Hai Bà Trưng , Thanh Xuân, Cầu Giấy, Tây Hồ, Hoàng Mai, Long Biên, Từ Liêm, Hà Đông."></span></p>
                                                                            <p><span class="trans-name">Ngoại thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Thanh Trì, Gia Lâm"></span></p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;250 gram</p>
                                                                            <p>32.000 đ</p>
                                                                            <p>38.400 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;2 kg</p>
                                                                            <p>36.000 đ</p>
                                                                            <p>46.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <br>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </c:if>
                                                        <c:if test="${district.scId == '558' || district.scId == '557' || district.scId == '564' || district.scId == '568' }">
                                                            <table class="table">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="text-align:left;"><p class="text-info">Địa chỉ người mua</p></th>
                                                                <th><p class="text-info">Giao hành nhanh</p><p>(Dưới 24h)</p></th>
                                                                <th><p class="text-info">Giao hàng tiết kiệm</p><p>(Dưới 72h)</p></th>
                                                                <th><p class="text-info">Thu tiền tại nhà</p></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>
                                                                            <p><b>TP. Hồ CHí Minh</b></p>
                                                                            <p><span class="trans-name">Nội thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Quận 1,2,3,4,5,6,7,8,10,11, Tân Bình, Tân Phú, Phú Nhuận, Bình Thạnh, Gò Vấp"></span></p>
                                                                            <p><span class="trans-name">Ngoại thành 1</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Quận 9, 12 , Thủ Đức, Bình Tân"></span></p>
                                                                            <p><span class="trans-name">Ngoại thành 2</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Hóc Môn, Bình Chánh, Nhà Bè"></span></p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;250 gram</p>
                                                                            <p>40.000 đ</p>
                                                                            <p>40.000 đ</p>
                                                                            <p>50.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;2 kg</p>
                                                                            <p>30.000 đ</p>
                                                                            <p>30.000 đ</p>
                                                                            <p>40.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <br>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <p><b>Hà Nội</b></p>
                                                                            <p><span class="trans-name">Nội thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Ba Đình, Hoàn Kiếm, Đống Đa, Hai Bà Trưng , Thanh Xuân, Cầu Giấy, Tây Hồ, Hoàng Mai, Long Biên, Từ Liêm, Hà Đông."></span></p>
                                                                            <p><span class="trans-name">Ngoại thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Thanh Trì, Gia Lâm"></span></p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;250 gram</p>
                                                                            <p>32.000 đ</p>
                                                                            <p>38.400 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;2 kg</p>
                                                                            <p>36.000 đ</p>
                                                                            <p>46.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <br>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </c:if>
                                                        <c:if test="${district.scId == '563' || district.scId == '567' || district.scId == '554' }">
                                                            <table class="table">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="text-align:left;"><p class="text-info">Địa chỉ người mua</p></th>
                                                                <th><p class="text-info">Giao hành nhanh</p><p>(Dưới 24h)</p></th>
                                                                <th><p class="text-info">Giao hàng tiết kiệm</p><p>(Dưới 72h)</p></th>
                                                                <th><p class="text-info">Thu tiền tại nhà</p></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>
                                                                            <p><b>TP. Hồ CHí Minh</b></p>
                                                                            <p><span class="trans-name">Nội thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Quận 1,2,3,4,5,6,7,8,10,11, Tân Bình, Tân Phú, Phú Nhuận, Bình Thạnh, Gò Vấp"></span></p>
                                                                            <p><span class="trans-name">Ngoại thành 1</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Quận 9, 12 , Thủ Đức, Bình Tân"></span></p>
                                                                            <p><span class="trans-name">Ngoại thành 2</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Hóc Môn, Bình Chánh, Nhà Bè"></span></p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;250 gram</p>
                                                                            <p>55.000 đ</p>
                                                                            <p>50.000 đ</p>
                                                                            <p>55.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;2 kg</p>
                                                                            <p>45.000 đ</p>
                                                                            <p>40.000 đ</p>
                                                                            <p>45.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <br>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <p><b>Hà Nội</b></p>
                                                                            <p><span class="trans-name">Nội thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Ba Đình, Hoàn Kiếm, Đống Đa, Hai Bà Trưng , Thanh Xuân, Cầu Giấy, Tây Hồ, Hoàng Mai, Long Biên, Từ Liêm, Hà Đông."></span></p>
                                                                            <p><span class="trans-name">Ngoại thành</span><span class="icon16-faq cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Thanh Trì, Gia Lâm"></span></p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;250 gram</p>
                                                                            <p>38.400 đ</p>
                                                                            <p>38.400 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <p>&lt;2 kg</p>
                                                                            <p>46.000 đ</p>
                                                                            <p>46.000 đ</p>
                                                                        </td>
                                                                        <td align="center">
                                                                            <br>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                            <p class="text-danger">Miễn phí</p>
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </c:if>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <div class="bluewhite">
                                <div class="bluewhite-title"><label class="lb-name"><a href="#" id="payment"></a><span class="icon16-bluewhite"></span>Thanh toán</label></div>
                                <div class="bluewhite-content">
                                    <div class="detail-content">
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <p class="p-checkred"><span class="icon16-nl"></span><span class="f11"><b>Thanh toán online qua NgânLượng:</b></span></p>
                                                <div class="pd-paybox">
                                                    <span class="icon42-safe"></span>
                                                    <h5>An toàn</h5>
                                                    <p>Bảo vệ trường hợp người bán gian lận</p>
                                                </div><!--pd-paybox-->
                                                <div class="pd-paybox">
                                                    <span class="icon42-fast"></span>
                                                    <h5>Nhanh chóng</h5>
                                                    <p>Hoàn tất thanh toán tức thì,không mất thời gian</p>
                                                </div><!--pd-paybox-->
                                                <div class="pd-paybox">
                                                    <span class="icon42-comfor"></span>
                                                    <h5>Tiện lợi</h5>
                                                    <p>Nhiều hình thức, hỗ trợ 24/7</p>
                                                </div><!--pd-paybox-->
                                            </div><!--col-sm-4-->
                                            <div class="col-sm-8">
                                                <p class="p-checkred"><span class="icon16-checkred"></span>Hỗ trợ thanh toán trực tuyến bằng hơn 20 loại thẻ, tài khoản ngân hàng.</p>
                                                <ul class="ul-bank">
                                                    <li><a title="Thẻ thanh toán VisaCard" target="_blank" href="http://www.vietnam-visa.com" class="visacrd"></a></li>
                                                    <li><a title="Thẻ thanh toán MasterCard" target="_blank" href="http://www.mastercard.com" class="mastercrd"></a></li>
                                                    <li><a title="Hệ thống thanh toán trực tuyến Paypal" target="_blank" href="https://www.paypal.com" class="paypal"></a></li>
                                                    <li><a title="Ngân hàng TMCP Ngoại Thương Việt Nam" target="_blank" href="http://www.vietcombank.com.vn" class="vietcombank"></a></li>
                                                    <li><a title="Ngân hàng TMCP Kỹ Thương Việt Nam" target="_blank" href="http://www.techcombank.com.vn" class="techbk"></a></li>
                                                    <li><a title="Ngân hàng Đông Á" target="_blank" href="http://www.dongabank.com.vn" class="dongabk"></a></li>
                                                    <li><a title="Ngân hàng Công Thương Việt Nam" target="_blank" href="http://www.vietinbank.vn" class="vietinbk"></a></li>
                                                    <li><a title="Ngân hàng Quốc Tế" target="_blank" href="http://www.vib.com.vn" class="vibk"></a></li>
                                                    <li><a title="Ngân hàng TMCP Sài Gòn" target="_blank" href="http://www.shb.com.vn" class="shbk"></a></li>
                                                    <li><a title="Ngân hàng Á Châu" target="_blank" href="http://www.acb.com.vn" class="acbbk"></a></li>
                                                    <li><a title="Ngân hàng TMCP Sài Gòn Thương Tín" target="_blank" href="http://www.sacombank.com.vn" class="sacombk"></a></li>
                                                    <li><a title="Ngân hàng Đầu Tư và Phát Triển Việt Nam" target="_blank" href="http://www.bidv.com.vn" class="bidvbk"></a></li>
                                                    <li><a title="Ngân hàng Nông Nghiệp và Phát Triển Nông Thôn Việt Nam" target="_blank" href="http://www.agribank.com.vn" class="agrbk"></a></li>
                                                    <li><a title="Ngân hàng Quân Đội" target="_blank" href="http://www.militarybank.com.vn" class="mbk"></a></li>
                                                    <li><a title="Ngân hàng Xuất Nhập Khẩu Việt Nam" target="_blank" href="http://www.eximbank.com.vn" class="exembank"></a></li>
                                                    <li><a title="Ngân hàng Việt Nam Thịnh Vượng" target="_blank" href="http://www.vpb.com.vn" class="vpbk"></a></li>
                                                    <li><a title="Ngân hàng TMCP Đông Nam Á" target="_blank" href="http://seabank.com.vn" class="seabk"></a></li>
                                                    <li><a title="Ngân hàng Bắc Á" target="_blank" href="http://www.baca-bank.vn" class="bacabk"></a></li>
                                                    <li><a title="Ngân hàng TMCP Hàng Hải Việt Nam" target="_blank" href="http://www.msb.com.vn" class="maritbk"></a></li>
                                                    <li><a title="Ngân hàng TMCP Xăng Dầu Petrolimex Việt Nam" target="_blank" href="http://www.pgbank.com.vn" class="pgbk"></a></li>
                                                    <li><a title="Ngân hàng TMCP Đại Dương" target="_blank" href="http://www.oceanbank.vn/Index.html" class="oceanbank"></a></li>
                                                    <li><a title="Ngân hàng TMCP Phát Triển TP.HCM" target="_blank" href="http://www.hdbank.vn" class="hdbank"></a></li>
                                                    <li><a title="Ngân hàng Dầu Khí Toàn Cầu" target="_blank" href="http://www.gpbank.com.vn" class="gpbank"></a></li>
                                                    <li><a title="Ngân hàng TMCP Việt Á" target="_blank" href="http://www.vietabank.com.vn" class="vietabank"></a></li>
                                                    <li><a title="Ngân hàng TMCP Nam Việt" target="_blank" href="http://navibank.com.vn" class="nvibank"></a></li>
                                                </ul>
                                                <p class="p-checkred"><span class="icon16-checkred"></span>Chuyển khoản ngân hàng: tại quầy giao dịch, máy ATM, Internet banking.</p>
                                                <p class="p-checkred"><span class="icon16-checkred"></span>Nộp tiền mặt: tại văn phòng NgânLượng, điểm giao dịch bưu điện.</p>
                                                <p class="p-checkred"><span class="icon16-checkred"></span>Số dư ví NgânLượng.</p>
                                            </div><!--col-sm-8-->
                                        </div><!--row-->
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <p class="p-checkred"><span class="icon16-cod"></span><span class="f11"><b>Thu tiền tận nơi trên toàn quốc bởi Shipchung:</b></span></p>
                                                <p class="p-checkred">Miễn phí hoàn toàn</p>
                                                <ul class="ul-ship">
                                                    <li><a target="_blank" href="http://ems.com.vn/" class="ems"></a></li>
                                                    <li><a target="_blank" href="http://ttcvina.com/ttc/" class="kerry"></a></li>
                                                    <li><a target="_blank" href="http://nasco.com.vn/index.aspx" class="nasco"></a></li>
                                                    <li><a target="_blank" href="http://netco.com.vn/trang-chu.aspx" class="netco"></a></li>
                                                    <li><a target="_blank" href="http://viettelpost.com.vn/" class="viettelpost"></a></li>
                                                    <li><a target="_blank" href="http://www.vnpost.vn/" class="vietnampost"></a></li>
                                                    <li><a target="_blank" href="https://giaohangnhanh.vn/" class="giaohangnhanh"></a></li>
                                                    <li><a target="_blank" href="http://giaohangtietkiem.vn/" class="ghtk"></a></li>
                                                </ul>
                                            </div><!--col-sm-12-->
                                        </div><!--row-->
                                    </div><!--detail-ontent-->
                                </div><!--bluewhite-content-->
                            </div><!--bluewhite-->
                        </div><!--detail-content-->
                    </div><!--boxblue-content-->
                </div><!--boxblue-->
                <div class="boxblue" id="tab3">
                    <div class="boxblue-title full-tab">
                        <ul class="pull-left move-tabclick">               	
                            <li><a href="#tab1">Mô tả chi tiết sản phẩm</a></li>
                            <li><a href="#tab2">Hướng dẫn mua hàng</a></li>
                            <li class="active"><a href="#tab3"><span class="icon24-fb-white"></span>Bình luận</a></li>
                            <li><a href="#tab4">Đánh giá/Nhận xét sản phẩm</a></li>
                        </ul>
                    </div><!--boxblue-title-->
                    <div class="boxblue-content">
                        <div class="detail-content">
                            <div class="fb-comments" data-href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html" data-colorscheme="light" data-numposts="5" data-width="100%"></div>
                        </div>
                    </div><!--boxblue-content-->
                </div><!--boxblue-->
                <div class="boxblue" id="tab4">
                    <div class="boxblue-title full-tab">
                        <ul class="pull-left move-tabclick">               	
                            <li><a href="#tab1">Mô tả chi tiết sản phẩm</a></li>
                            <li><a href="#tab2">Hướng dẫn mua hàng</a></li>
                            <li><a href="#tab3"><span class="icon24-fb-white"></span>Bình luận</a></li>
                            <li class="active"><a href="#tab4">Đánh giá/Nhận xét sản phẩm</a></li>
                        </ul>
                    </div><!--boxblue-title-->
                    <div class="boxblue-content">
                        <div class="review-outer">
                            <div class="review-top">
                                <label>Đánh giá</label>
                            </div><!--review-top-->
                            <div class="review-total">
                                <div class="col-sm-2">
                                    <h2 class="recommendPurchase"></h2>
                                    <p class="recommendTrue">khuyên nên mua</p>
                                </div>
                                <div class="col-sm-2">
                                    <div class="star-outer starPointAndRecommend">
                                    </div>
                                    <p class="checkPointAndRecommend"></p>
                                </div>
                                <div class="col-sm-4">
                                    <div class="row">
                                        <label class="star-name">5 sao</label>
                                        <div class="star-bar"><span class="percentFiveStar" style="width:80%;" ></span></div>
                                        <label class="star-count fiveStar"></label>
                                    </div>
                                    <div class="row">
                                        <label class="star-name">4 sao</label>
                                        <div class="star-bar"><span class="percentFourStar" style="width:18%;"></span></div>
                                        <label class="star-count fourStar"></label>
                                    </div>
                                    <div class="row">
                                        <label class="star-name">3 sao</label>
                                        <div class="star-bar"><span class="percentThreeStar" style="width:2%;"></span></div>
                                        <label class="star-count threeStar"></label>
                                    </div>
                                    <div class="row">
                                        <label class="star-name">2 sao</label>
                                        <div class="star-bar"><span class="percentTwoStar" style="width:2%;"></span></div>
                                        <label class="star-count twoStar"></label>
                                    </div>
                                    <div class="row">
                                        <label class="star-name">1 sao</label>
                                        <div class="star-bar"><span class="percentOneStar" style="width:0%;"></span></div>
                                        <label class="star-count oneStar"></label>
                                    </div>
                                </div>
                                <div class="col-sm-3 writeReview">
                                    <a class="btn btn-primary btn-lg" href="#vietdanhgia">Viết đánh giá</a>
                                </div>
                            </div><!--review-total-->
                            <div class="graybox-title reviewitem_title">
                                <label class="lb-name">Đánh giá của người dùng <span class="text-danger review">(${item.review})</span></label>
                                <div class="lb-right"><a class="active order_time" style="cursor: pointer" onclick="item.loadItemReviews(0, 2)">Mới nhất</a>&nbsp;|&nbsp;<a class="order_like" style="cursor: pointer" onclick="item.loadItemReviews(0, 2, 1)">Hữu ích nhất</a></div>
                            </div><!--graybox-title-->
                            <div class="review-list">
                            </div><!--review-list-->
                            <div class="box-control">
                                <div class="small-page review">
                                </div>
                            </div><!--box-control-->
                            <!--add comment-->
                            <div class="graybox-title commentTitleCheck" id="vietdanhgia">
                                <label class="lb-name">Viết đánh giá, chia sẻ kinh nghiệm của bạn</label>
                                <div class="lb-right">Với mỗi đánh giá bạn nhận được <span class="text-danger">200</span> xèng</div>
                            </div><!--graybox-title-->
                            <div class="form form-horizontal reviewSuccsess">
                                <div class="form-group" name="titleReview">
                                    <label class="col-sm-2 control-label">Tiêu đề đánh giá:</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="titlereview" class="form-control">
                                        <span name="titleReview" style="color: #a94442"></span>
                                    </div>
                                </div>
                                <div class="form-group review">
                                    <div class="col-sm-10 col-sm-offset-2">
                                        <div class="star-outer">
                                            <span for="1"></span>
                                            <span for="2"></span>
                                            <span for="3"></span>
                                            <span for="4"></span>
                                            <span for="5"></span>
                                        </div>
                                        <div class="radio">
                                            <label><input type="radio" name="rd-buy" checked="" value="1"> Bạn nên mua</label>
                                        </div>
                                        <div class="radio">
                                            <label><input type="radio" name="rd-buy" value="0"> Bạn không nên mua</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group" name="contentReview">
                                    <label class="col-sm-2 control-label">Kinh nghiệm dùng thực tế của tôi:</label>
                                    <div class="col-sm-10">
                                        <textarea name="contentreview" rows="4"  class="form-control"></textarea>
                                        <span name="contentReview" style="color: #a94442"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-10 col-sm-offset-2">
                                        <input  type="submit" class="btn btn-primary" value="Gửi đánh giá" onclick="item.addComment('${item.id}');">
                                    </div>
                                </div>
                            </div><!-- /form-horizontal -->
                            <!-- end add comment-->
                        </div><!--review-outer-->
                    </div><!--boxblue-content-->
                </div>
                <div class="box-history">
                    <div class="small-page others">
                    </div>
                    <div class="history-tabs">
                        <ul>
                            <li class="active" for="samecategory"><a onclick="item.loadOtherItem(0, 'samecategory')" style="cursor: pointer"><span class="icon44-folder-white" title="Sản phẩm cùng danh mục"></span><span class="ht-text">Sản phẩm cùng danh mục</span></a></li>
                            <li for="sameprice"><a onclick="item.loadOtherItem(0, 'sameprice')" style="cursor: pointer"><span class="icon44-tag-white" title="Sản phẩm cùng khoảng giá"></span><span class="ht-text">Sản phẩm cùng khoảng giá</span></a></li>
                            <li for="itemviewed"><a onclick="item.loadItemViewed('itemviewed')" style="cursor: pointer"><span class="icon44-eye-white" title="Sản phẩm vừa xem"></span><span class="ht-text">Sản phẩm vừa xem</span></a></li>
                            <li for="samemanuf"><a onclick="item.loadOtherItem(0, 'samemanuf')" style="cursor: pointer"><span class="icon44-star-white" title="Sản phẩm cùng thương hiệu"></span><span class="ht-text">Sản phẩm cùng thương hiệu</span></a></li>
                        </ul>
                    </div>
                    <div class="history-wrapper">
                        <div class="history-content">
                            <ul class="showitemOther">
                            </ul>
                        </div><!-- history-content -->
                    </div><!-- history-wrapper -->
                </div>
                <div class="iconSearchRight" style="display: none">
                    <div class="smart-search-button"><span class="icon24-search-white"></span></div>
                    <div class="smart-search">
                        <div class="ss-title">
                            <span class="icon24-search-orange"></span>
                            <span class="ss-title-text">Kết quả tìm kiếm tiếp theo</span>
                        </div>
                        <div class="ss-content contentitemIconSearchRight">
                        </div><!--ss-content-->
                    </div><!--smart-search-->
                </div>
                <!-- icon left -->
                <div class="smart-history">
                    <c:if test="${shopcategory != null && shopcategory.size() > 0}">
                        <div class="sh-inner">
                            <div class="sh-history-button" style="left: 0px;"><span class="icon44-shop-white"></span></div>
                            <div class="sh-view" style="left: -297px;">
                                <div class="sh-title">
                                    <span class="sh-title-text">Danh mục sản phẩm của shop</span>
                                    <span class="icon44-shop-white"></span>   
                                </div>
                                <div class="sh-content">
                                    <div class="sh-over">
                                        <ul class="sh-list">
                                            <c:forEach var="scat" items="${shopcategory}">
                                                <li><a target="_blank" href="${baseUrl}/${shop.alias}/browse.html?cid=${scat.id}">${scat.name}</a></li>
                                                </c:forEach>
                                        </ul>
                                    </div><!--sh-over-->
                                </div><!--sh-content-->
                            </div><!--sh-view-->
                        </div><!--sh-inner-->
                    </c:if>
                    <div class="sh-inner">
                        <div class="sh-history-button" style="left: 0px;"><span class="icon44-eye-white"></span></div>
                        <div class="sh-view" style="left: -297px;">
                            <div class="sh-title">
                                <span class="sh-title-text">Sản phẩm vừa xem</span>
                                <span class="icon44-eye-white"></span>   
                            </div>
                            <div class="sh-content">
                                <div class="sh-over itemViewedIconleft">

                                </div><!--sh-over-->
                            </div><!--sh-content-->
                        </div><!--sh-view-->
                    </div><!--sh-inner-->
                    <div class="sh-inner">
                        <div class="sh-history-button" style="left: 0px;" onclick="item.loadItemIconFloatLeft('samePriceIconLeft');"><span class="icon44-tag-white"></span></div>
                        <div class="sh-view" style="left: -297px;">
                            <div class="sh-title">
                                <span class="sh-title-text">Sản phẩm cùng khoảng giá</span>
                                <span class="icon44-tag-white"></span>   
                            </div>
                            <div class="sh-content">
                                <div class="sh-over samePriceIconLeft">
                                </div><!--sh-over-->
                            </div><!--sh-content-->
                        </div><!--sh-view-->
                    </div><!--sh-inner-->
                    <div class="sh-inner">
                        <div class="sh-history-button" style="left: 0px;" onclick="item.loadItemIconFloatLeft('samecategoryIconLeft');"><span class="icon44-folder-white"></span></div>
                        <div class="sh-view" style="left: -297px;">
                            <div class="sh-title">
                                <span class="sh-title-text">Sản phẩm cùng danh mục</span>
                                <span class="icon44-folder-white"></span>   
                            </div>
                            <div class="sh-content">
                                <div class="sh-over samecategoryIconLeft">
                                </div><!--sh-over-->
                            </div><!--sh-content-->
                        </div><!--sh-view-->
                    </div><!--sh-inner-->
                    <div class="sh-inner">
                        <div class="sh-history-button" style="left: 0px;" onclick="item.loadItemIconFloatLeft('samemanufacturerIconLeft');"><span class="icon44-star-white"></span></div>
                        <div class="sh-view" style="left: -297px;">
                            <div class="sh-title">
                                <span class="sh-title-text">Sản phẩm cùng thương hiệu</span>
                                <span class="icon44-star-white"></span>   
                            </div>
                            <div class="sh-content">
                                <div class="sh-over samemanufacturerIconLeft">
                                </div><!--sh-over-->
                            </div><!--sh-content-->
                        </div><!--sh-view-->
                    </div><!--sh-inner-->
                </div>
                <!--End icon left -->
            </div><!--page-detail-->
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
    <div class="internal-text">
        <h2>${item.name} tại chodientu.vn</h2>
    </div>
</div><!-- container -->
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

<div class="footer-banner">
    <div class="fb-inner">
        <!-- 990x90 -->
        <div id='div-gpt-ad-1409797788286-0' style='width:990px; height:90px;'>
            <script type='text/javascript'>
                googletag.cmd.push(function () {
                    googletag.display('div-gpt-ad-1409797788286-0');
                });
            </script>
        </div>
    </div>
</div>