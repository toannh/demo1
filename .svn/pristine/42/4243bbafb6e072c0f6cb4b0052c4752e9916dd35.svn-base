<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="text" uri="http://chodientu.vn/text" %>
<%@ taglib prefix="url" uri="http://chodientu.vn/url" %>

<input type="hidden" name="id" value="${order.id}" />
<input type="hidden" name="paymentMethod" value="COD" />

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li>
            <a href="${baseUrl}/user/profile.html">
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a>
        </li>
        <li class="active">Hoá đơn bán hàng</li>
    </ol>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-tao-van-don-cod-105631538618.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn tạo vận đơn COD
        </a></div>      
    <h1 class="title-pages">Tạo vận đơn Giao hàng - Thu tiền (CoD)</h1>
    <div class="tabs-content-user">
        <div class="row row-reset">
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
                <div class="row row-reset"> 
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-left">
                        <h2 class="title-box-user">Địa chỉ kho hàng<span class="clr-red">*</span></h2>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Kho: </label>
                                <div class="col-sm-9">
                                    <select name="stock" class="form-control">
                                        <option value="">--Chọn kho--</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Địa chỉ: </label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" value="${user.address}" name="sellerAddress" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Tỉnh/TP:</label>
                                <div class="col-sm-9">
                                    <select class="form-control" name="sellerCityId" val="${user.cityId}" >
                                        <option value="0" >Chọn tỉnh / thành phố</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Quận/Huyện:</label>
                                <div class="col-sm-9">
                                    <select class="form-control" name="sellerDistrictId" val="${user.districtId}" >
                                        <option value="0" >Chọn quận / huyện</option>
                                    </select>
                                </div>
                            </div>    
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Người liên hệ: </label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" value="${user.name}" name="sellerName" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Điện thoại:</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" value="${user.phone}" name="sellerPhone" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right">
                        <h2 class="title-box-user">Người nhận hàng <span class="clr-red">*</span></h2>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Địa chỉ: </label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" value="${order.receiverAddress}" name="receiverAddress">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Tỉnh/TP:</label>
                                <div class="col-sm-9">
                                    <select class="form-control" name="receiverCityId" val="${order.receiverCityId}" >
                                        <option value="0" >Chọn tỉnh / thành phố</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Quận/Huyện:</label>
                                <div class="col-sm-9">
                                    <select class="form-control" name="receiverDistrictId" val="${order.receiverDistrictId}"  >
                                        <option value="0" >Chọn quận / huyện</option>
                                    </select>
                                </div>
                            </div>                                                 
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Họ và tên: </label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" value="${order.receiverName}" name="receiverName" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Điện thoại:</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" value="${order.receiverPhone}" name="receiverPhone">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">E-Mail:</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" value="${order.receiverEmail}" name="receiverEmail" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <h2 class="title-box-user">Nội dung vận đơn</h2>
                        <div class="form-horizontal">
                            <fieldset disabled>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Tên hàng hoá: </label>
                                    <div class="col-sm-6">
                                        <input type="text" name="name" class="form-control" value="[Chợ điện tử] vận đơn cod (${order.id})">
                                    </div>
                                    <div class="col-sm-3 clr-999">Tối đa 170 ký tự</div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Mô tả chi tiết: </label>
                                    <div class="col-sm-6">
                                        <textarea name="description" class="form-control" rows="5"> Vận đơn gồm :<c:forEach var="item" items="${order.items}" >${item.quantity} X ${item.itemName} (${item.itemId}),</c:forEach> </textarea>
                                        </div>
                                        <div class="col-sm-3 clr-999">Tối đa 500 ký tự</div>
                                    </div>
                                </fieldset>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Tổng khối lượng:<br>(Gram) </label>
                                    <div class="col-sm-3">
                                    <c:set var="weight" value="0" />
                                    <c:forEach var="item" items="${order.items}" >
                                        <c:set var="weight" value="${weight + (item.weight * item.quantity)}" />
                                    </c:forEach>
                                    <input type="text" class="form-control" value="${weight}" name="weight" >
                                </div>
                                <div class="col-sm-6 clr-999">Tạm tính theo khai báo của người bán khi đăng bán sản phẩm. 
                                    Xin vui lòng điền chính xác để hãng vận chuyển tới lấy hàng.</div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Số tiền người bán cần thu hộ CoD (vnđ)</label>
                                <div class="col-sm-3">
                                    <input type="text" name="finalPrice" class="form-control price-lg clr-org" onkeyup="order.changePrice(this);" value="${text:numberFormat(order.finalPrice)}">
                                </div>
                                <div class="col-sm-6 clr-999">Có thể sửa lại nếu người mua đã thanh toán trước một phần
                                    Phí CoD người bán phải trả xem ở dưới</div>
                            </div>  
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Hình thức vận chuyển:</label>
                                <div class="col-sm-9">
                                    <!--                                    <div class="radio" name="RAPID" >
                                                                            <label><input type="radio" name="shipmentService" ${order.shipmentService=='RAPID'?'checked':''} value="RAPID" /> Hỏa tốc (dưới 2h)</label>
                                                                        </div>-->
                                    <div class="radio" name="FAST" >
                                        <label><input type="radio" name="shipmentService" ${order.shipmentService=='FAST'?'checked':''} value="FAST" > Nhanh (2-3 ngày)</label>
                                    </div>
                                    <div class="radio" name="SLOW" >
                                        <label><input type="radio" name="shipmentService" ${order.shipmentService=='SLOW'?'checked':''} value="SLOW" > Tiết kiệm (5-7 ngày)</label>
                                    </div>
                                </div>
                            </div>  
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Bảo hiểm cho đơn hàng:</label>
                                <div class="col-sm-9">
                                    <div class="radio">
                                        <label><input type="radio" name="protec" value="protec"> Có</label>
                                    </div>
                                    <div class="radio">
                                        <label><input type="radio" name="protec" checked="" value="none"> Không</label>
                                    </div>
                                </div>
                            </div>  
                        </div>
                    </div>
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <h2 class="title-box-user">Chi phí dự kiến người bán phải trả cho ShipChung</h2>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <div class="border-box-cod clearfix">
                                    <div class="header-box-cod clearfix">
                                        <span class="pull-left">Phí CoD:</span>
                                        <span class="pull-right"><strong>Miễn phí</strong></span>
                                    </div>
                                    <div class="content-box-cod clearfix">
                                        <span class="pull-left">- Mức phí CoD (tính trên tiền thu hộ CoD)</span>
                                        <span class="pull-right">0%</span>
                                    </div>
                                    <div class="content-box-cod clearfix">
                                        <span class="pull-left">- Tiền thu hộ CoD</span>
                                        <span class="pull-right" rel="codPrice" >${text:numberFormat(order.finalPrice)} <sup class="price" >đ</sup></span>
                                    </div>                                                    
                                    <div class="content-box-cod clearfix" rel="_proteced" >
                                        <span class="pull-left">- Phí bảo hiểm</span>
                                        <span class="pull-right" rel="proteced" ></span>
                                    </div>
                                    <div class="header-box-cod clearfix">
                                        <span class="pull-left">Phí vận chuyển (tạm tính):</span>
                                        <span class="pull-right" rel="shipmentPrice" ><strong>${text:numberFormat(order.shipmentPrice)} <sup class="price" >đ</sup></strong></span>
                                    </div>
                                    <div class="content-box-cod clearfix" style="display: none" >
                                        <span class="pull-left">- ChoDienTu hỗ trợ phí vận chuyển</span>
                                        <span class="pull-right" rel="cdtshipmentprice" > 0đ</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <p class="clr-org"><strong>Lưu ý:</strong></p>
                                <p>Phí vận chuyển chỉ là tạm tính, dựa trên khai báo cân nặng phía trên. Cân nặng thực tế của sản phẩm được xác định lại khi hãng vận chuyển tới lấy hàng và phí vận chuyển thực tế sẽ được cập nhật lại trên vận đơn tại tài khoản ShipChung. Người bán có thể kiểm tra lại bằng cách đăng nhập vào ShipChung.vn</p>
                                <p class="mgt-25">Tiền hàng thu được sẽ được ShipChung thanh toán vào tài khoản NgânLượng của người bán.</p>
                                <p class="mgt-25">Nếu đơn hàng CoD không thành công, ShipChung vẫn tính phí giao hàng với người bán những miễn phí chuyển hoàn.</p>
                            </div>
                            <!--<input type="checkbox" checked name="yes" > <strong>Tôi đồng ý tuân thủ <a href="#">chính sách và quy định của dịch vụ Giao hàng</a></strong>-->
                        </div>
                        <div class="mgt-25" style="text-align:center;">
                            <button type="button" onclick="order.createLading();" class="btn btn-lg btn-danger">Duyệt vận đơn</button> 
                            <a href="#" class="btn-remove">
                                <span class="glyphicon glyphicon glyphicon-remove"></span> Huỷ
                            </a>
                        </div>
                    </div>
                </div>                                
            </div>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                <div class="box-left-content-cod">
                    <div class="title-box-cod">Vì sao nên chấp nhận hình thức Giao hàng - Thu tiền</div>
                    <ul class="list-question-item">
                        <li>
                            <h5>Kích thích mua hàng: </h5>
                            <p>100% người mua thích CoD. Người bán có thể giảm doanh số nếu không chấp nhận hình thức này.</p>
                        </li>
                        <li>
                            <h5>Miễn phí</h5>
                            <p>Miễn phí CoD toàn quốc &amp; Miễn phí chuyển hoàn nếu người mua từ chối nhận hàng</p>
                        </li>
                        <li>
                            <h5>Thời gian tạm giữ tiền ngắn</h5>
                            <p>Thanh toán tiền hàng 2 lần/tuần vào thứ 3 &amp; thứ 6</p>
                        </li>
                        <li>
                            <h5>Mạng lưới vận chuyển hàng rộng khắp</h5>
                            <p>Tới từng quận/huyện trên 63 tỉnh/thành cả nước</p>
                        </li>
                        <li>
                            <h5>Tiện dụng</h5>
                            <p>Kiểm tra hoặc tra cứu hành trình hàng Online</p>
                        </li>
                    </ul>
                </div>
                <div class="box-left-content-cod">
                    <div class="title-box-cod">Mã đơn hàng: <a href="${baseUrl}/${order.id}//chi-tiet-don-hang.html">${order.id}</a></div>
                    <div class="table-content-box-cod">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tbody>
                                <c:forEach var="item" items="${order.items}" >
                                    <tr>
                                        <td width="15%" valign="middle" align="center">${item.quantity} x</td>
                                        <td valign="top" align="left">
                                            <div class="box-buy-product">
                                                <span class="img-buy-product">
                                                    <img src="${item.images[0]}" class="img-responsive" />
                                                </span>
                                                <div class="pull-left desc-product">
                                                    <a href="${url:item(item.itemId, item.itemName)}" target="_blank" class="title-product-choose" title="${item.itemName}">
                                                        ${item.itemName}
                                                    </a>
                                                    <p>Mã SP: ${item.itemId}</p>
                                                    <p>Giá SP: <strong class="clr-red">${text:numberFormat(item.itemPrice)} <sup class="price">đ</sup></strong></p>
                                                    <p>
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
                                                            Linh hoạt theo địa chỉ người mua
                                                        </c:if>
                                                    </p>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="total-money-bill clearfix" style="font-size:11px;">
                            <div class="grid-form clearfix">
                                <span class="pull-left">Tổng tiền hàng</span>
                                <span class="pull-right">${text:numberFormat(order.totalPrice)} <sup class="u-price">đ</sup></span>
                            </div>
                            <div class="grid-form clearfix">
                                <span class="pull-left">Tổng phí vận chuyển</span>
                                <span class="pull-right">${text:numberFormat(order.shipmentPrice)} <sup class="u-price">đ</sup></span>
                            </div>
                            <c:if test="${order.cdtDiscountShipment > 0}">
                                <div class="grid-form clearfix">
                                    <span class="pull-left">Chợ điện tử hỗ trợ phí vận chuyển</span>
                                    <span class="pull-right">-${text:numberFormat(order.cdtDiscountShipment)} <sup class="u-price">đ</sup></span>
                                </div>
                            </c:if>

                            <!--                            <div class="grid-form clearfix">
                                                            <span class="pull-left">Khuyến mại hóa đơn</span>
                                                            <span class="pull-right">1.220.000 <sup class="u-price">đ</sup></span>
                                                        </div>-->
                            <div class="grid-form mgt-25 clearfix">
                                <span class="pull-left">Thanh toán</span>
                                <span class="pull-right">
                                    <strong class="clr-red fs-16">
                                        ${text:numberFormat(order.finalPrice)} <sup class="u-price">đ</sup>
                                    </strong>
                                </span>
                            </div>
                            <div class="grid-form clearfix">
                                <span class="pull-left">HT vận chuyển</span>
                                <span class="pull-right">
                                    <c:if test="${order.shipmentService == 'SLOW'}">
                                        Giao hàng tiết kiệm
                                    </c:if>
                                    <c:if test="${order.shipmentService == 'FAST'}">
                                        Giao hàng nhanh
                                    </c:if>
                                    <c:if test="${order.shipmentService == 'RAPID'}">
                                        Giao hàng hỏa tốc
                                    </c:if>
                                </span>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>