<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="http://chodientu.vn/text"%>
<%@taglib  prefix="url" uri="http://chodientu.vn/url"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<input type="hidden" name="sellerId" value="${order.sellerId}" />
<input type="hidden" name="id" value="${order.id}" />
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
                <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-dat-mua-va-thanh-toan-416060508412.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn đặt mua và thanh toán
                    </a></div>
                <div class="checkout-title">Xác nhận thông tin đặt hàng</div>
                <div class="row">
                    <div class="col-sm-6">
                        <div class="boxblue">
                            <div class="boxblue-title full-tab">
                                <label class="lb-name">Thông tin người mua</label>
                            </div>
                            <div class="boxblue-content">
                                <div class="form form-horizontal form-checkout">
                                    <div class="checkout-height"></div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Họ và Tên:<span class="text-danger">*</span></label>
                                        <div class="col-sm-9">
                                            <input for="buyer" type="text" class="form-control" name="buyerName" value="${order.buyerName}" />
                                            <!--<div class="help-block">Họ và Tên bắt buộc phải nhập</div>-->
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Số điện thoại:<span class="text-danger">*</span></label>
                                        <div class="col-sm-9">
                                            <input for="buyer" type="text" class="form-control" name="buyerPhone" value="${order.buyerPhone}" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Email:<span class="text-danger">*</span></label>
                                        <div class="col-sm-9">
                                            <input for="buyer" type="text" class="form-control" name="buyerEmail" value="${order.buyerEmail}" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Địa chỉ:<span class="text-danger">*</span></label>
                                        <div class="col-sm-9">
                                            <input for="buyer" type="text" class="form-control" name="buyerAddress" value="${order.buyerAddress}" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Thành phố:<span class="text-danger">*</span></label>
                                        <div class="col-sm-9">
                                            <select class="form-control" name="buyerCityId" val="${order.buyerCityId != null?order.buyerCityId:0}" >
                                                <option value="0" >Chọn tỉnh / thành phố</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Quận:<span class="text-danger">*</span></label>
                                        <div class="col-sm-9">
                                            <select class="form-control" name="buyerDistrictId" val="${order.buyerDistrictId!= null?order.buyerDistrictId:0}" >
                                                <option value="0" >Chọn quận / huyện</option>
                                            </select>
                                        </div>
                                    </div>
                                </div><!-- form -->
                            </div><!-- boxblue-content -->
                        </div><!-- boxblue -->
                    </div><!-- col-sm-6 -->
                    <div class="col-sm-6">
                        <div class="boxblue">
                            <div class="boxblue-title full-tab"><label class="lb-name">Thông tin người nhận</label></div>
                            <div class="boxblue-content">
                                <div class="form form-horizontal form-checkout">
                                    <div class="form-group">
                                        <div class="col-sm-9 col-sm-offset-3">
                                            <div class="checkbox">
                                                <input type="checkbox" name="sync" /> Lấy thông tin từ người mua 
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Họ và Tên:<span class="text-danger">*</span></label>
                                        <div class="col-sm-9">
                                            <input for="buyerName" type="text" class="form-control" name="receiverName" value="${order.receiverName}" />
                                            <!--<div class="help-block">Họ và Tên bắt buộc phải nhập</div>-->
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Số điện thoại:<span class="text-danger">*</span></label>
                                        <div class="col-sm-9">
                                            <input for="buyerPhone" type="text" class="form-control" name="receiverPhone" value="${order.receiverPhone}" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Email:<span class="text-danger">*</span></label>
                                        <div class="col-sm-9">
                                            <input for="buyerEmail" type="text" class="form-control" name="receiverEmail" value="${order.receiverEmail}" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Địa chỉ:<span class="text-danger">*</span></label>
                                        <div class="col-sm-9">
                                            <input for="buyerAddress" type="text" class="form-control" name="receiverAddress" value="${order.receiverAddress}" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Thành phố:<span class="text-danger">*</span></label>
                                        <div class="col-sm-9">
                                            <select class="form-control" name="receiverCityId" val="${order.receiverCityId != null ?order.receiverCityId:0}" >
                                                <option value="0" >Chọn tỉnh / thành phố</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">Quận:<span class="text-danger">*</span></label>
                                        <div class="col-sm-9">
                                            <select class="form-control" name="receiverDistrictId" val="${order.receiverDistrictId!=null?order.receiverDistrictId:0}" >
                                                <option value="0" >Chọn quận / huyện</option>
                                            </select>
                                        </div>
                                    </div>
                                </div><!-- form -->
                            </div><!-- boxblue-content -->
                        </div><!-- boxblue -->
                    </div><!-- col-sm-6 -->
                </div><!-- row -->
                <c:if test="${coupon != null}">
                    <jsp:useBean id="date" class="java.util.Date" />
                    <div class="cdt-message bg-success">
                        Mã giảm giá: <b>${coupon.code}</b> 
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
                        này sẽ được áp dụng khi bạn nhập lúc thanh toán.
                    </div>
                </c:if>
                <div class="table-responsive table-checkoutlist">
                    <table class="table">
                        <thead>
                            <tr>
                                <th class="col1">
                                    Thông tin sản phẩm từ người bán: 
                                    <a href="#${seller.name}">${seller.name}</a>
                                    <span class="tool-tip" data-toggle="tooltip" data-placement="top" title="" data-original-title="Địa chỉ người bán : ${seller.address}" sCityId="${seller.cityId}" sDistrictId="${seller.districtId}" rel="sellerAddress" >
                                        <span class="glyphicon glyphicon-exclamation-sign fs16"></span>
                                    </span>
                                </th>
                                <th class="col2">Số lượng</th>
                                <th class="col3">Đơn giá</th>
                                <th class="col4">Tổng cộng</th>
                            </tr>
                        </thead>
                        <tbody rel="items" >
                            <c:set var="price" value="0" />
                            <c:forEach var="item" items="${order.items}">
                                <c:set var="price" value="${price + (item.itemPrice * item.quantity)}" />
                                <tr for="orderItem" orderItemId="${item.id}" itemid="${item.itemId}" rel="show_${item.id}" >
                                    <td class="col1">
                                        <div class="grid">
                                            <div class="img">
                                                <span class="icon20-close" onclick="order.removeItemCart('${order.id}', '${item.id}');" ></span>
                                                <img src="${item.images[0]}" alt="${item.itemName}" />
                                            </div>
                                            <div class="g-content">
                                                <div class="g-row">
                                                    <a class="g-title" target="_blank" href="${url:item(item.itemId,item.itemName)}" title="${item.itemName}" >
                                                        ${item.itemName}
                                                    </a>
                                                </div>
                                                <c:if test="${fn:length(item.subItem) >0}">
                                                    <c:forEach items="${item.subItem}" var="subitem">
                                                        <div class="g-row">${subitem.quantity}</b> x
                                                            <c:if test="${subitem.sizeValueName!=null && subitem.sizeValueName!=''}">Kích thước: <b>${subitem.sizeValueName}</b>,</c:if> 
                                                            <c:if test="${subitem.colorValueName!=null && subitem.colorValueName!=''}">
                                                                Màu sắc: 
                                                                <div class="tiny-colorsize micro-colorsize colorsizePayment" color="${subitem.colorValueName}" size="${subitem.sizeValueName}">
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
                                                        <span rel="shipchung" >Linh hoạt theo địa chỉ người mua (${item.weight}<sub>gram</sub>)</span>
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
                                        </div>
                                    </td>
                                    <td class="col2">
                                        <div class="text-spell">
                                            <input itemquantity="${item.id}" onchange="order.updateItemCart('${order.id}', '${item.id}');" type="number" min="1" name="quantity${item.id}" class="text" value="${item.quantity}" />
                                        </div>
                                    </td>
                                    <td class="col3" for="price${item.id}" ><b>${text:numberFormat(item.itemPrice)} <sup class="price" >đ</sup></b></td>
                                    <td class="col4" for="totalprice${item.id}" ><b>${text:numberFormat(item.itemPrice * item.quantity)} <sup class="price" >đ</sup></b></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div><!-- table-responsive -->
                <div class="form form-inline form-rdtrans" show="shipchung" >
                    <div class="form-group">
                        <b>Chọn hình thức vận chuyển:</b>
                    </div>
                    <!--                    <div class="form-group">
                                            <div class="radio" name="RAPID" >
                                                <label><input type="radio" name="shipmentService"  value="RAPID" /> Hỏa tốc (dưới 2h)</label>
                                            </div>
                                        </div>-->
                    <div class="form-group" name="FAST" >
                        <div class="radio">
                            <label><input type="radio" name="shipmentService" value="FAST" > Nhanh (2-3 ngày)</label>
                        </div>
                    </div>
                    <div class="form-group" name="SLOW" >
                        <div class="radio">
                            <label><input type="radio" name="shipmentService" checked value="SLOW" > Tiết kiệm (5-7 ngày)</label>
                        </div>
                    </div>
                </div><!-- form-trans -->
                <div class="table-trans">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>
                                    Chọn hình thức thanh toán
                                    <span class="text-danger">(Miễn phí)</span>
                                    <span class="text-danger pull-right"></span>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <div class="row">
                                        <div class="col-md-7 r-padding-right">
                                            <ul class="ul-trans"> 
                                                <c:if test="${sellerInfo.scIntegrated == true}">
                                                    <li class="border-right-none">
                                                        <div class="row trans-item">
                                                            <div class="col-sm-12">
                                                                <div class="radio">
                                                                    <input class="trans-radio" name="trans-rd" type="radio" value="">Giao hàng thu tiền tận nơi (CoD)
                                                                </div>
                                                            </div><!-- col-sm-12 -->
                                                        </div><!-- row -->
                                                        <div class="row content-trans" style="display: none;">
                                                            <div class="col-sm-7">
                                                                <p class="trans-desc">Bạn hãy chọn hình thức vận chuyển giao hàng thu tiền</p>
                                                                <ul class="ul-bank">
                                                                    <li class=""><a title="Ship Chung" onclick="order.selectPM('COD')" target="_blank" href="http://shipchung.vn"><span class="icon-shipchung36"></span><span class="logo-tick"></span></a></li>
                                                                </ul>
                                                            </div><!-- col-sm-7 -->
                                                        </div>
                                                    </li>
                                                </c:if>
                                                <c:if test="${sellerInfo.nlIntegrated == true}">
                                                    <li>
                                                        <div class="row trans-item">
                                                            <div class="col-sm-12">
                                                                <div class="radio">
                                                                    <input class="trans-radio" name="trans-rd" type="radio" value="">Thẻ tín dụng
                                                                </div>
                                                            </div><!-- col-sm-12 -->
                                                        </div>
                                                        <div class="row content-trans">
                                                            <div class="col-sm-7">
                                                                <p class="trans-desc">Bạn hãy chọn loại thẻ tín dụng</p>
                                                                <ul class="ul-bank">
                                                                    <li><a onclick="order.selectPM('VISA')" title="Thẻ thanh toán VisaCard" target="_blank" href="http://www.vietnam-visa.com" class="visacrd"></a></li>
                                                                    <li><a onclick="order.selectPM('MASTER')" title="Thẻ thanh toán MasterCard" target="_blank" href="http://www.mastercard.com" class="mastercrd"></a></li>
                                                                </ul>
                                                            </div><!-- col-sm-7 -->
                                                            <div class="col-sm-5">
                                                                <p class="p-checkred"><span class="icon16-checkok"></span>Miễn phí vận chuyển cho đơn hàng có khối lượng dưới <span class="text-danger">2<sub>kg</sub></span></p>
                                                            </div><!-- col-sm-5 -->
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="row trans-item">
                                                            <div class="col-sm-12">
                                                                <div class="radio">
                                                                    <input class="trans-radio" name="trans-rd" type="radio" value="">Ngân hàng nội địa (ATM Online/Internet banking)
                                                                </div>   
                                                            </div><!-- col-sm-12 -->
                                                        </div>
                                                        <div class="row content-trans">
                                                            <div class="col-sm-7">
                                                                <p class="trans-desc">Bạn hãy chọn ngân hàng nội địa cần thanh toán</p>
                                                                <ul class="ul-bank">
                                                                    <li><a  onclick="order.selectPM('VCB', true, true)" title="Ngân hàng TMCP Ngoại Thương Việt Nam" target="_blank" href="http://www.vietcombank.com.vn" class="vietcombank"></a></li>
                                                                    <li><a onclick="order.selectPM('TCB', true, true)" title="Ngân hàng TMCP Kỹ Thương Việt Nam" target="_blank" href="http://www.techcombank.com.vn" class="techbk"></a></li>
                                                                    <li><a onclick="order.selectPM('DAB', true)" title="Ngân hàng Đông Á" target="_blank" href="http://www.dongabank.com.vn" class="dongabk"></a></li>
                                                                    <li><a onclick="order.selectPM('ICB', true)" title="Ngân hàng Công Thương Việt Nam" target="_blank" href="http://www.vietinbank.vn" class="vietinbk"></a></li>
                                                                    <li><a onclick="order.selectPM('VIB', true)" title="Ngân hàng Quốc Tế" target="_blank" href="http://www.vib.com.vn" class="vibk"></a></li>
                                                                    <li><a onclick="order.selectPM('SHB', true)" title="Ngân hàng TMCP Sài Gòn" target="_blank" href="http://www.shb.com.vn" class="shbk"></a></li>
                                                                    <li><a onclick="order.selectPM('ACB', true)" title="Ngân hàng Á Châu" target="_blank" href="http://www.acb.com.vn" class="acbbk"></a></li>
                                                                    <li><a onclick="order.selectPM('SCB', true)" title="Ngân hàng TMCP Sài Gòn Thương Tín" target="_blank" href="http://www.sacombank.com.vn" class="sacombk"></a></li>
                                                                    <li><a onclick="order.selectPM('BIDV', true)" title="Ngân hàng Đầu Tư và Phát Triển Việt Nam" target="_blank" href="http://www.bidv.com.vn" class="bidvbk"></a></li>
                                                                    <li><a onclick="order.selectPM('AGB', true)" title="Ngân hàng Nông Nghiệp và Phát Triển Nông Thôn Việt Nam" target="_blank" href="http://www.agribank.com.vn" class="agrbk"></a></li>
                                                                    <li><a onclick="order.selectPM('MB', true)" title="Ngân hàng Quân Đội" target="_blank" href="http://www.militarybank.com.vn" class="mbk"></a></li>
                                                                    <li><a onclick="order.selectPM('EXB', true)" title="Ngân hàng Xuất Nhập Khẩu Việt Nam" target="_blank" href="http://www.eximbank.com.vn" class="exembank"></a></li>
                                                                    <li><a onclick="order.selectPM('VPB', true)" title="Ngân hàng Việt Nam Thịnh Vượng" target="_blank" href="http://www.vpb.com.vn" class="vpbk"></a></li>
<!--                                                                    <li><a onclick="order.selectPM('SB', true)" title="Ngân hàng TMCP Đông Nam Á" target="_blank" href="http://seabank.com.vn" class="seabk"></a></li>-->
                                                                    <li><a onclick="order.selectPM('PGB', true)" title="Ngân hàng TMCP Xăng Dầu Petrolimex Việt Nam" target="_blank" href="http://www.pgbank.com.vn" class="pgbk"></a></li>
                                                                    <li><a onclick="order.selectPM('OJB', true)" title="Ngân hàng TMCP Đại Dương" target="_blank" href="http://www.oceanbank.vn/Index.html" class="oceanbank"></a></li>
                                                                    <li><a onclick="order.selectPM('HDB', true)" title="Ngân hàng TMCP Phát Triển TP.HCM" target="_blank" href="http://www.hdbank.vn" class="hdbank"></a></li>
                                                                    <li><a onclick="order.selectPM('GPB', true)" title="Ngân hàng Dầu Khí Toàn Cầu" target="_blank" href="http://www.gpbank.com.vn" class="gpbank"></a></li>
                                                                    <li><a onclick="order.selectPM('VAB', true)" title="Ngân hàng TMCP Việt Á" target="_blank" href="http://www.vietabank.com.vn" class="vietabank"></a></li>
                                                                    <li><a onclick="order.selectPM('NVB', true)" title="Ngân hàng TMCP Nam Việt" target="_blank" href="http://navibank.com.vn" class="nvibank"></a></li>
                                                                </ul>
                                                            </div><!-- col-sm-7 -->
                                                            <div class="col-sm-5">
                                                                <p class="p-checkred"><span class="icon16-checkok"></span>Miễn phí vận chuyển cho đơn hàng có khối lượng dưới <span class="text-danger">2<sub>kg</sub></span></p>
                                                                <!--<p class="p-checkred"><span class="icon16-checkok"></span>Miễn phí vận chuyển, tối đa <span class="text-danger">50.000 đ</span></p>-->
                                                                <br>
                                                                <div class="radio" bank="ATM" style="display: none" >
                                                                    <label><input onclick="order.selectAction('ATM');" name="rd-ai" type="radio" />ATM Online</label>
                                                                </div>
                                                                <div class="radio" bank="BANKING" style="display: none" >
                                                                    <label><input onclick="order.selectAction('BANKING');" name="rd-ai" type="radio" />Internet banking</label>
                                                                </div>
                                                            </div><!-- col-sm-5 -->
                                                        </div>
                                                    </li>
                                                    <li>
                                                        <div class="row trans-item">
                                                            <div class="col-sm-12">
                                                                <div class="radio">
                                                                    <input class="trans-radio" name="trans-rd" type="radio" value="">Ví NgânLượng (<a href="http://www.nganluong.vn" target="_blank">www.nganluong.vn</a>)
                                                                </div>
                                                            </div><!-- col-sm-12 -->
                                                        </div>
                                                        <div class="row content-trans">
                                                            <div class="col-sm-7">
                                                                <p class="trans-desc">Bạn hãy chọn hình thức thanh toán qua ví NgânLượng</p>
                                                                <ul class="ul-bank">
                                                                    <li class="">
                                                                        <a onclick="order.selectPM('NL')"  title="Ví ngân lượng" target="_blank" href="http://www.nganluong.vn">
                                                                            <span class="icon-nganluong36"></span>
                                                                            <!--<span class="logo-tick"></span>-->
                                                                        </a>
                                                                    </li>
                                                                </ul>
                                                            </div><!-- col-sm-7 -->
                                                            <div class="col-sm-5">
                                                                <p class="p-checkred"><span class="icon16-checkok"></span>Miễn phí vận chuyển cho đơn hàng có khối lượng dưới <span class="text-danger">2<sub>kg</sub></span></p>
                                                                <!--<p class="p-checkred"><span class="icon16-checkok"></span>Miễn phí vận chuyển, tối đa <span class="text-danger">50.000 đ</span></p>-->
                                                            </div><!-- col-sm-5 -->
                                                        </div>
                                                    </li>
                                                </c:if>
                                                <li>
                                                    <div class="row trans-item">
                                                        <div class="col-sm-12">
                                                            <div class="radio">
                                                                <input class="trans-radio" name="trans-rd" type="radio" value="">Người mua người bán tự giao dịch
                                                            </div>
                                                        </div><!-- col-sm-12 -->
                                                    </div>
                                                    <div class="row content-trans">
                                                        <div class="col-sm-7">
                                                            <p class="trans-desc">Bạn hãy chọn hình thức tự giao dịch</p>
                                                            <ul class="ul-bank">
                                                                <li>
                                                                    <a onclick="order.selectPM('NONE')" title="Người mua người bán tự giao dịch" href="#">
                                                                        <span class="icon35-person-gray"></span>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div><!-- col-sm-7 -->
                                                    </div>
                                                </li>
                                            </ul>
                                        </div><!-- col-md-7 -->
                                        <div class="col-md-5">
                                            <div class="form form-horizontal form-totalcheckout">
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label"><b>Tổng tiền hàng:</b></label>
                                                    <div class="col-sm-3 tt2">
                                                        <p class="form-control-static text-right" rel="totalPrice" ><b>${text:numberFormat(price)} <sup class="price">đ</sup></b></p>
                                                    </div>
                                                </div>
                                                <div class="form-group" style="display: none" >
                                                    <label class="col-sm-6 control-label">ChợĐiệnTử hỗ trợ tiền hàng:</label>
                                                    <div class="col-sm-3 tt2">
                                                        <p class="form-control-static text-right">-20.000 đ</p>
                                                    </div>
                                                </div>
                                                <div class="form-group" style="display: none" >
                                                    <label class="col-sm-6 control-label">Người bán hỗ trợ tiền hàng:</label>
                                                    <div class="col-sm-3 tt2">
                                                        <p class="form-control-static text-right">-20.000 đ</p>
                                                    </div>
                                                </div>
                                                <div class="form-group" rel="shipmentPrice" style="display: none" >
                                                    <label class="col-sm-6 control-label">Phí vận chuyển:</label>
                                                    <div class="col-sm-3 tt2">
                                                        <p class="form-control-static text-right">0 <sup class="price">đ</sup></p>
                                                    </div>
                                                </div>
                                                <div class="form-group" style="display: none" >
                                                    <label class="col-sm-6 control-label">ChợĐiệnTử hỗ trợ phí vận chuyển:</label>
                                                    <div class="col-sm-3 tt2">
                                                        <p class="form-control-static text-right" rel="cdtshipment" >-0 đ</p>
                                                    </div>
                                                </div>
                                                <div class="form-group" style="display: none" >
                                                    <label class="col-sm-6 control-label">Người bán hỗ trợ phí vận chuyển:</label>
                                                    <div class="col-sm-3 tt2">
                                                        <p class="form-control-static text-right">-50.000 đ</p>
                                                    </div>
                                                </div>
                                                <c:if test="${order.couponId == null}">
                                                    <div class="form-group" rel="couponShow" >
                                                        <label class="col-sm-6 control-label">Coupon giảm giá (nếu có):</label>
                                                        <div class="col-sm-3 tt2">
                                                            <input name="couponId" type="text" class="form-control" placeholder="Nhập mã">
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <button class="btn btn-primary btn-coupon" onclick="order.addCoupon('${order.id}');" >Sử dụng</button>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${order.couponId != null}">
                                                    <div class="form-group" rel="couponShow" >
                                                        <label class="col-sm-6 control-label">Coupon giảm giá (${order.couponId}):</label>
                                                        <div class="col-sm-3 tt2">
                                                            <p class="form-control-static text-right">-${text:numberFormat(couponPrice)} <sup class="price">đ</sup></p>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label"><b>TỔNG PHẢI THANH TOÁN:</b></label>
                                                    <div class="col-sm-3 tt2">
                                                        <p class="form-control-static text-right f15" rel="finalPrice" ><b>${text:numberFormat(price)} <sup class="price">đ</sup></b></b></p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-12">
                                                        <textarea name="note" rows="4" class="form-control" placeholder="Ghi chú"></textarea> 
                                                    </div>   
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-12 text-right">
                                                        <input onclick="order.paymentAction('${order.id}');" type="button" class="btn btn-danger btn-lg" value="Xác nhận">
                                                    </div>   
                                                </div>
                                            </div><!-- form -->
                                        </div><!-- col-md-5 -->
                                    </div><!-- row -->
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div><!-- table-trans -->
            </div><!-- checkout -->
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
</div>