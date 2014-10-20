<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://chodientu.vn/url" prefix="url" %>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a>
            <span class="tree-before"></span>
            <a class="last-item" href="javascript:;"> Chi tiết đơn hàng</a>
            <span class="tree-after"></span>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <div class="bg-white">
            <div class="invoice-wrapper">
                <div class="invoice-top">
                    <!--<a href="javascript:;" style=""><span class="icon24-email"></span></a>-->
                    <!--<a href="javascript:;" onclick="window.print();"><span class="icon24-print"></span></a>-->
                </div><!-- invoice-top -->
                <div class="invoice-border">
                    <div class="invoice-header">
                        <div class="row">
                            <div class="col-sm-7">
                                <div class="grid">
                                    <div class="img">
                                        <c:if test="${shop.logo==null || shop.logo==''}">
                                            <img src="${baseUrl}/static/user/images/data/AvatatShop-Default.png" />
                                        </c:if>
                                        <c:if test="${shop.logo!=null && shop.logo!=''}">
                                            <img src="${shop.logo}" />
                                        </c:if>
                                    </div>
                                    <div class="g-content">
                                        <c:if test="${shop!=null}">
                                            <div class="g-row">
                                                <h4>${shop.title}</h4>
                                            </div>
                                            <div class="g-row">
                                                <p>Địa chỉ: ${shop.address}</p>
                                                <p>Điện thoại: ${shop.phone}</p>
                                                <p>Email: ${shop.email}</p>
                                            </div>
                                        </c:if>
                                        <c:if test="${shop==null}">
                                            <div class="g-row">
                                                <p>Địa chỉ: ${seller.address}</p>
                                                <p>Điện thoại: ${seller.phone}</p>
                                                <p>Email: ${seller.email}</p>
                                            </div>
                                        </c:if>
                                    </div>
                                </div><!-- grid -->
                            </div><!-- col-sm-7 -->
                            <div class="col-sm-5">
                                <div class="grid text-right">
                                    <div class="g-row">
                                        <p>
                                            <jsp:useBean id="date" class="java.util.Date"></jsp:useBean>
                                            <jsp:setProperty name="date" value="${order.createTime}" property="time"></jsp:setProperty>
                                            Ngày <fmt:formatDate type="date" pattern="dd"  value="${date}"></fmt:formatDate> tháng
                                            <fmt:formatDate type="date" pattern="MM"  value="${date}"></fmt:formatDate> năm 
                                            <fmt:formatDate type="date" pattern="yyyy"  value="${date}"></fmt:formatDate>
                                            </p>
                                        </div>
                                        <div class="g-row">
                                            <p><b>Mã đơn hàng:</b> ${order.id}</p>
                                    </div>
                                    <c:if test="${order.scId!=null && order.scId!=''}">
                                        <div class="g-row">
                                            <p><b>Mã vận chuyển:</b> ${order.scId}</p>
                                        </div>
                                    </c:if>
                                    <c:if test="${order.nlId!=null && order.nlId!=''}">
                                        <div class="g-row">
                                            <p><b>Mã thanh toán:</b> ${order.nlId}</p>
                                        </div>
                                    </c:if>
                                    <div class="g-row">
                                        <c:if test="${order.paymentStatus=='PAID'}">
                                            <div class="pay-compleate-info">Đã thanh toán</div>
                                        </c:if>
                                        <c:if test="${order.paymentStatus!='PAID'}">
                                            <div class="pay-compleate-info">Chưa thanh toán</div>
                                        </c:if>
                                    </div>
                                </div><!-- grid -->
                            </div><!-- col-sm-5 -->
                        </div><!-- row -->
                    </div><!-- invoice-header -->
                    <div class="invoice-content">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="fi-title">Thông tin người mua</div>
                                <div class="form form-horizontal form-invoice">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Họ và tên:</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${order.buyerName}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Số điện thoại:</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${order.buyerPhone}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">E-Mail:</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${order.buyerEmail}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Địa chỉ:</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${order.buyerAddress}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Thành phố:</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">
                                                <c:forEach items="${cities}" var="city">
                                                    <c:if test="${city.id==order.buyerCityId}">${city.name}</c:if>
                                                </c:forEach>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Quận:</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">
                                                <c:forEach items="${districts}" var="district">
                                                    <c:if test="${district.id==order.buyerDistrictId}">${district.name}</c:if>
                                                </c:forEach></p>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- col-sm-6 -->
                            <div class="col-sm-6">
                                <div class="fi-title">Thông tin người nhận</div>
                                <div class="form form-horizontal form-invoice">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Họ và tên:</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${order.receiverName}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Số điện thoại:</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${order.receiverPhone}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">E-Mail:</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${order.receiverEmail}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Địa chỉ:</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">${order.receiverAddress}</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Thành phố:</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">
                                                <c:forEach items="${cities}" var="city">
                                                    <c:if test="${city.id==order.receiverCityId}">${city.name}</c:if>
                                                </c:forEach>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4">Quận:</label>
                                        <div class="col-sm-8">
                                            <p class="form-control-static">
                                                <c:forEach items="${districts}" var="district">
                                                    <c:if test="${district.id==order.receiverDistrictId}">${district.name}</c:if>
                                                </c:forEach>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- col-sm-6 -->
                        </div><!-- row -->
                        <div class="row">
                            <div class="col-sm-12">
                                <p>Hình thức thanh toán: 
                                    <c:choose>
                                        <c:when test="${order.paymentMethod=='COD'}">Giao hàng thu tiền tận nơi (CoD)</c:when>
                                        <c:when test="${order.paymentMethod=='NONE'}">Người mua người bán tự thỏa thuận</c:when>
                                        <c:when test="${order.paymentMethod=='NL'}">Thanh toán qua Ngân Lượng</c:when>
                                        <c:when test="${order.paymentMethod=='VISA'||order.paymentMethod=='MASTER'}">Thanh toán qua VISA</c:when>
                                        <c:otherwise>
                                            Thanh toán qua ngân hàng nội địa ${order.paymentMethod}
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                                <p>Hình thức vận chuyển: 
                                    <c:if test="${order.shipmentService=='RAPID'}">Hỏa tốc</c:if>
                                    <c:if test="${order.shipmentService=='FAST'}">Giao hàng nhanh</c:if>
                                    <c:if test="${order.shipmentService=='SLOW'}">Giao hàng tiết kiệm</c:if>
                                    </p>
                                </div>
                            </div><!-- row -->
                            <div class="invoice-list">
                                <div class="il-title">
                                    <div class="row">
                                        <div class="col-sm-8"><span class="il-label">Thông tin sản phẩm</span></div>
                                        <div class="col-sm-1 text-center">Số lượng</div>
                                        <div class="col-sm-2 text-right">Thành tiền</div>
                                        <div class="col-sm-1"></div>
                                    </div>
                                </div><!-- il-title -->

                            <c:forEach items="${order.items}" var="item">
                                <div class="il-item">
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <div class="grid">
                                                <div class="img">
                                                    <img src="${item.images[0]}"  alt="product">
                                                </div>
                                                <div class="g-content">
                                                    <div class="g-row">
                                                        <a href="${url:item(item.itemId,item.itemName)}" target="_blank">${item.itemName}</a>
                                                    </div>
                                                    <c:if test="${fn:length(item.subItem) >0}">
                                                        <c:forEach items="${item.subItem}" var="subitem">
                                                            <div class="g-row">(${subitem.quantity})
                                                                <c:if test="${subitem.sizeValueName!=null && subitem.sizeValueName!=''}">-Kích thước: <b>${subitem.sizeValueName}</b>,</c:if> 
                                                                <c:if test="${subitem.colorValueName!=null && subitem.colorValueName!=''}">
                                                                    Màu sắc: 
                                                                    <div class="tiny-colorsize micro-colorsize colorsizeInvoice" color="${subitem.colorValueName}" size="${subitem.sizeValueName}">
                                                                    </div>
                                                                </c:if>
                                                            </div>
                                                        </c:forEach>
                                                    </c:if>
                                                    <div class="g-row">
                                                        <p>Người bán: <a href="${baseUrl}/${shop.alias}/">${seller.name}</a></p>
                                                        <p>Phí vận chuyển: 
                                                            <c:if test="${item.shipmentType == null || item.shipmentType == 'AGREEMENT'}">
                                                                Không rõ phí
                                                            </c:if>
                                                            <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice == 0}">
                                                                Miễn phí
                                                            </c:if>
                                                            <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice > 0}">
                                                                Cố định
                                                            </c:if>
                                                            <c:if test="${item.shipmentType == 'BYWEIGHT'}">
                                                                Linh hoạt theo địa chỉ người mua
                                                            </c:if>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-1 text-center"><span class="mobile-sl">Số lượng:&nbsp;</span>${item.quantity}</div>
                                        <div class="col-sm-2 text-right"><b>${text:numberFormat(item.itemPrice*item.quantity)}<sup>đ</sup></b></div>
                                        <div class="col-sm-1"></div>
                                    </div>
                                </div><!-- il-item -->
                            </c:forEach>
                        </div><!-- invoice-list -->
                        <div class="row">
                            <c:if test="${order.note!=null && order.note!=''}">
                                <div class="col-sm-12">
                                    <p>*Ghi chú của khách hàng: ${order.note}</p>
                                </div>
                            </c:if>
                        </div><!-- row -->
                        <div class="form form-horizontal form-totalinvoice">
                            <div class="form-group">
                                <label class="control-label col-sm-9"><b>Tổng tiền hàng:</b></label>
                                <div class="col-sm-2 text-right"><p class="form-control-static"><b>${text:numberFormat(order.totalPrice)}<sup>đ</sup></b></p></div>
                                <div class="col-sm-1"></div>
                            </div>
                            <c:if test="${order.shipmentPrice>0}">
                                <div class="form-group">
                                    <label class="control-label col-sm-9">Phí vận chuyển:</label>
                                    <div class="col-sm-2 text-right"><p class="form-control-static">${text:numberFormat(order.shipmentPrice)}<sup>đ</sup></p></div>
                                    <div class="col-sm-1"></div>
                                </div>
                            </c:if>
                            <c:if test="${order.couponPrice>0}">
                                <div class="form-group">
                                    <label class="control-label col-sm-9">Giảm giá:</label>
                                    <div class="col-sm-2 text-right"><p class="form-control-static">-${text:numberFormat(order.couponPrice)}<sup>đ</sup></p></div>
                                    <div class="col-sm-1"></div>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label class="control-label col-sm-9"><b>TỔNG PHẢI THANH TOÁN:</b></label>
                                <div class="col-sm-2 text-right"><p class="form-control-static ft-15"><b>${text:numberFormat(order.finalPrice)}<sup>đ</sup></b></p></div>
                                <div class="col-sm-1"></div>
                            </div>
                        </div><!-- form-totalinvoice -->
                    </div><!-- invoice-content -->
                </div><!-- invoice-border -->
            </div><!-- invoice-wrapper -->
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
</div>