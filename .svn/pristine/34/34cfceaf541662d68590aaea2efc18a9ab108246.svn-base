<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html"> ${viewer.user.username==null?viewer.user.email:viewer.user.username}</a></li>
        <li class="active">Hoá đơn bán hàng</li>
    </ol>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-quan-ly-hoa-don-658016248615.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn quản lý hóa đơn
        </a></div>
    <h1 class="title-pages">Hoá đơn bán hàng</h1>
    <div class="tabs-content-user invoice-buyer-pages">
        <ul class="tab-title-content">
            <li class="${(tab == '')?'active':''}"><a href="${baseUrl}/user/hoa-don-ban-hang.html">Tất cả (${(tab == null)?orderSellers.dataCount:countOrder})</a></li>
            <li class="${(tab == 'paymentNEW')?'active':''}"><a href="${baseUrl}/user/hoa-don-ban-hang.html?tab=paymentNEW">Chưa thanh toán (${countPaymentNew})</a></li>
            <li class="${(tab == 'paymentPAID')?'active':''}"><a href="${baseUrl}/user/hoa-don-ban-hang.html?tab=paymentPAID">Đã thanh toán (${countPaymentPaid})</a></li>                            
            <li class="${(tab == 'shipmentNEW')?'active':''}"><a href="${baseUrl}/user/hoa-don-ban-hang.html?tab=shipmentNEW">Chưa giao hàng (${countShipmentNew})</a></li>                            
            <li class="${(tab == 'shipmentDELIVERED')?'active':''}"><a href="${baseUrl}/user/hoa-don-ban-hang.html?tab=shipmentDELIVERED">Đã giao hàng (${countShipmentDelivered})</a></li>                            
            <li class="${(tab == 'recycleBin')?'active':''}"><a href="${baseUrl}/user/hoa-don-ban-hang.html?tab=recycleBin">Thùng rác (${countRecycleBin})</a></li> 
            <!--li class="right-title pull-right">
                <div class="num-tin">
            ${orderSellers.pageIndex + 1}/${orderSellers.pageCount==0?'1':orderSellers.pageCount}
            <c:if test="${orderSellers.pageIndex + 1 > 1}">
                <button onclick="order.nextPage(${orderSellers.pageIndex}, true);" type="button" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </button> 
            </c:if>
            <c:if test="${orderSellers.pageIndex + 1 < orderSellers.pageCount}">
                <button onclick="order.nextPage('${orderSellers.pageIndex + 2}', true);" type="button" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </button>
            </c:if>
        </div>
    </li-->          
        </ul>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="sub-tab-content">
                        <div class="pull-left"><strong>Tổng cộng: <span class="fs-16 clr-red">${text:numberFormat(sumPrice.finalPrice)} <sup class="u-price">đ</sup></span></strong></div>
                        <div class="pull-right form-inline">
                            <!--
                            <div class="form-group">Xem theo:</div>
                            <div class="form-group">
                                    <select class="form-control">
                                    <option>Tất cả thời gian</option>
                                    <option>3 ngày</option>
                                    <option>7 ngày</option>
                                    <option>30 ngày</option>
                                    <option>60 ngày</option>
                                    <option>90 ngày</option>
                                </select>
                            </div> 
                            -->
                            <div class="form-group">
                                <select class="form-control" name="sort" style="width: 160px">
                                    <option value="0" ${sort==0?'selected':''}>Tất cả</option>
                                    <option value="1" ${sort==1?'selected':''}>Thời gian tạo hoá đơn tăng dần</option>
                                    <option value="2" ${sort==2?'selected':''}>Thời gian tạo hoá đơn giảm dần</option>
                                    <option value="3" ${sort==3?'selected':''}>Thời gian tạo vận đơn tăng dần</option>
                                    <option value="4" ${sort==4?'selected':''}>Thời gian tạo vận đơn giảm dần</option>
                                </select>
                            </div> 
                            <div class="form-group date-picker-block">
                                <input type="hidden" class="form-control timeFormOrderSeller" value="<c:if test="${timeForm!=0}">${timeForm}</c:if>" placeholder="Từ ngày">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </div>
                                <div class="form-group date-picker-block">
                                    <input type="hidden" class="form-control timeToOrderSeller" value="<c:if test="${timeTo!=0}">${timeTo}</c:if>" placeholder="Đến ngày">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </div>                                                                                                                    
                                <div class="form-group form-group-reset">
                                    <div class="input-group">
                                        <input name="keyword" type="text" class="form-control" value="" placeholder="Tìm kiếm">
                                        <span class="input-group-addon" style="cursor:pointer;" onclick="order.search(true);"><span class="glyphicon glyphicon-search"></span></span>
                                    </div>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    <c:if test="${!seller.scIntegrated}">
                        <div class="cdt-message bg-warning cm-icon"><span class="glyphicon glyphicon-exclamation-sign"></span>Bạn chưa tích hợp cấu hình vận chuyển, click <a target="_blank" href="${baseUrl}/user/cau-hinh-tich-hop.html">vào đây</a> để tích hợp.</div>
                    </c:if>            
                    <div class="invoice-layout-pages">
                        <div class="form-inline invoice-button">
                            <div class="form-group">
                                <button onclick="lading.action('cod');" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-check"></span> Duyệt hàng loạt CoD</button>
                            </div>  
                            <div class="form-group">
                                <button onclick="lading.action('ship');" type="button" class="btn btn-success"><span class="glyphicon glyphicon-check"></span> Duyệt hàng loạt vận chuyển</button>
                            </div>     
                            <div class="form-group">
                                <button type="button" class="btn btn-info" onclick="invoice.createSeries()"><span class="glyphicon glyphicon-plus"></span> Tạo hóa đơn hàng loạt</button>
                            </div>
                            <div class="form-group">
                                <a href="${baseUrl}/user/tao-hoa-don.html" class="btn btn-warning"><span class="glyphicon glyphicon-plus"></span> Tạo hóa đơn</a>
                            </div>                                                
                        </div>
                        <div class="invoice-title-list">
                            <div class="title-config-shop clearfix">
                                <div class="col-sm-6 col-xs-4"><input name="checkall" type="checkbox" value="1" class="chk-invoice" />Đơn hàng</div>
                                <div class="col-sm-4 col-xs-4">Người mua</div>
                                <div class="col-sm-2 col-xs-4">Tình trạng/Thao tác</div>
                            </div>
                        </div>
                        <ul class="invoice-list-item">
                            <c:forEach items="${orderSellers.data}" var="orderSeller">
                                <li class="clearfix" id="${orderSeller.id}">
                                    <c:if test="${orderSeller.shipmentMessage!=null}">
                                        <div class="col-sm-10 reset-padding-all lablel-invoice">
                                            <label class="label label-warning label-block"><strong>Trạng thái vận chuyển:</strong> ${orderSeller.shipmentMessage}</label>
                                        </div>
                                    </c:if>
                                    <div class="col-sm-6 reset-padding-all">
                                        <p class="invoiceid">
                                            <c:if test="${orderSeller.scId==null}">
                                                <input for="checkall" type="checkbox" value="${orderSeller.id}" class="chk-invoice">
                                            </c:if>
                                            <span class="tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Mã đơn hàng">Mã ĐH:</span> <a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html" target="_blank">${orderSeller.id}</a>
                                            <c:if test="${orderSeller.scId!=null}">
                                                | <span class="tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Mã vận đơn">Mã VĐ:</span>  <a href="http://mc.shipchung.vn/van-don.html?sc_code=${orderSeller.scId}" target="_blank">${orderSeller.scId}</a>
                                            </c:if>
                                            <c:if test="${orderSeller.nlId!=null}">
                                                | <span class="tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Mã thanh toán">Mã TT:</span>  <a href="#">${orderSeller.nlId}</a>
                                            </c:if>
                                        </p>
                                        <c:forEach items="${orderSeller.items}" var="items">
                                            <div class="invoice-item-product">
                                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                    <tbody><tr>
                                                            <td valign="top" class="clr-99" width="7%"><strong>${items.quantity}</strong> x </td>
                                                            <td valign="top" width="10%"><div class="img-invoice-pro"><img src="${(items.images != null && fn:length(items.images) >0)?items.images[0]:staticUrl.concat('/market/images/no-image-product.png')}" width="80" height="80"></div></td>
                                                            <td align="left" valign="top" width="83%">
                                                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                                    <tbody><tr>
                                                                            <td width="70%" align="left"><a href="${basicUrl}/san-pham/${items.itemId}/${text:createAlias(items.itemName)}.html" target="_blank">${items.itemName}</a></td>
                                                                            <td><div class="text-right"><strong>${text:numberFormat(items.itemPrice)} <sup class="u-price">đ</sup></strong> </div></td>
                                                                        </tr>
                                                                        <c:if test="${fn:length(items.subItem) >0}">
                                                                                <c:forEach items="${items.subItem}" var="subitem">
                                                                                     <tr property="${item.itemId}">
                                                                                         <td><b>${subitem.quantity}</b> x
                                                                                            <c:if test="${subitem.sizeValueName!=null && subitem.sizeValueName!=''}">Kích thước: <b>${subitem.sizeValueName}</b>,</c:if> 
                                                                                            <c:if test="${subitem.colorValueName!=null && subitem.colorValueName!=''}">
                                                                                                Màu sắc: 
                                                                                                <div class="tiny-colorsize micro-colorsize colorsizeVal" color="${subitem.colorValueName}" size="${subitem.sizeValueName}">
                                                                                                </div>
                                                                                            </c:if>
                                                                                        </td>
                                                                                    </tr>
                                                                                </c:forEach>
                                                                            </c:if>
                                                                        <tr>
                                                                            <td width="75%" align="left">
                                                                                <p class="mgt-10 text-left">
                                                                                    Phí vận chuyển: 
                                                                                    <c:if test="${items.shipmentType == null || items.shipmentType == 'AGREEMENT'}">
                                                                                        Không rõ phí
                                                                                    </c:if>
                                                                                    <c:if test="${items.shipmentType == 'FIXED' && items.shipmentPrice == 0}">
                                                                                        Miễn phí
                                                                                    </c:if>
                                                                                    <c:if test="${items.shipmentType == 'FIXED' && items.shipmentPrice > 0}">
                                                                                        Cố định
                                                                                    </c:if>
                                                                                    <c:if test="${items.shipmentType == 'BYWEIGHT'}">
                                                                                        Linh hoạt theo địa chỉ người mua&nbsp;&nbsp;
                                                                                    </c:if>
                                                                                </p>
                                                                            </td>
                                                                            <c:if test="${items.shipmentType == 'FIXED' && items.shipmentPrice > 0}">
                                                                                <td>
                                                                                    <div class="text-right"><strong>${text:numberFormat(items.shipmentPrice)}<sup class="u-price">đ</sup></strong></div>
                                                                                </td>
                                                                            </c:if>
                                                                        </tr>
                                                                    </tbody></table>
                                                            </td>
                                                        </tr>
                                                    </tbody></table>
                                            </div>
                                        </c:forEach>

                                        <div class="invoice-count">
                                            <p class="clearfix">
                                                <span class="pull-left">Tổng tiền hàng</span>
                                                <span class="pull-right">${text:numberFormat(orderSeller.totalPrice)} <sup class="u-price">đ</sup></span>      
                                            </p>
                                            <p class="clearfix">
                                                <c:if test="${orderSeller.shipmentPrice >0}">
                                                    <span class="pull-left">Phí VC</span>
                                                    <span class="pull-right">${text:numberFormat(orderSeller.shipmentPrice)} <sup class="u-price">đ</sup></span>      
                                                </c:if>
                                            </p>
                                            <p class="clearfix">
                                                <c:if test="${orderSeller.couponPrice >0}">
                                                    <span class="pull-left">Giảm trừ</span>
                                                    <span class="pull-right">${text:numberFormat(orderSeller.couponPrice)} <sup class="u-price">đ</sup></span>      
                                                </c:if>
                                            </p>

                                            <p class="clearfix">
                                                <span class="pull-left">Tổng cộng</span>
                                                <span class="pull-right clr-red">${text:numberFormat(orderSeller.finalPrice)} <sup class="u-price">đ</sup></span>
                                            </p>

                                        </div>
                                    </div>
                                    <div class="col-sm-4 col-xs-6 reset-padding-all">
                                        <div class="invoice-item-product">
                                            <p><strong>Người mua:</strong></p>
                                            <p>${orderSeller.buyerName}, </p>
                                            <p>ĐT: ${orderSeller.buyerPhone}</p>
                                            <p>Đ/c: ${orderSeller.buyerAddress} </p>

                                            <p class="mgt-10"><strong>Người nhận:</strong></p>
                                            <p>${orderSeller.receiverName}, </p>
                                            <p>ĐT: ${orderSeller.receiverPhone}</p>
                                            <p>Đ/c: ${orderSeller.receiverAddress} </p>
                                        </div>
                                        <div class="invoice-item-product">
                                            <p><strong>Hình thức thanh toán</strong>: 
                                                <c:choose>
                                                    <c:when test="${orderSeller.paymentMethod == 'NL'}">
                                                        Ví ngân lương
                                                    </c:when>
                                                    <c:when test="${orderSeller.paymentMethod == 'COD'}">
                                                        Thu tiền tại nhà
                                                    </c:when>
                                                    <c:when test="${orderSeller.paymentMethod == 'NONE'}">
                                                        Tự liên hệ
                                                    </c:when>
                                                    <c:when test="${orderSeller.paymentMethod == 'VISA' || orderSeller.paymentMethod == 'MASTER'}">
                                                        Thẻ visa
                                                    </c:when>
                                                    <c:otherwise>
                                                        Ngân hàng nội địa
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                            <p>
                                                <strong>Hình thức vận chuyển</strong>:
                                                <c:choose>
                                                    <c:when test="${orderSeller.shipmentService == 'SLOW'}">
                                                        Tiết kiệm (5-7 ngày)
                                                    </c:when>
                                                    <c:when test="${orderSeller.shipmentService == 'FAST'}">
                                                        Nhanh (2-3 ngày)
                                                    </c:when>
                                                    <c:when test="${orderSeller.shipmentService == 'RAPID'}">
                                                        Hỏa tốc (dưới 2h)
                                                    </c:when>
                                                    <c:otherwise>
                                                        Tự liên hệ
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="col-sm-2 col-xs-6 reset-padding-all">
                                        <div class="invoice-item-product text-center">
                                            <c:if test="${tab != 'recycleBin'}">
                                                <jsp:useBean id="date" class="java.util.Date" />
                                                <p>
                                                    <c:choose>
                                                        <c:when test="${orderSeller.refundStatus}">
                                                            <span class="icon16-repayall tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Đơn hàng đã hoàn tiền"></span>
                                                        </c:when>
                                                        <c:when test="${orderSeller.markSellerPayment == 'NEW' && !orderSeller.refundStatus}">
                                                            <span class="icon16-nopay tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Đơn hàng chưa thanh toán"></span>
                                                        </c:when>
                                                        <c:when test="${orderSeller.markSellerPayment == 'PENDING' && !orderSeller.refundStatus}">
                                                            <span class="icon16-nopay tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Đơn hàng chờ thanh toán"></span>
                                                        </c:when>
                                                        <c:when test="${orderSeller.markSellerPayment == 'PAID' && !orderSeller.refundStatus}">
                                                            <jsp:setProperty name="date" property="time" value="${orderSeller.paidTime}" /> 
                                                            <span class="icon16-pay tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Đơn hàng đã thanh toán <c:if test="${orderSeller.paidTime>0}"> vào lúc <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy" value="${date}"></fmt:formatDate></c:if>"></span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="icon16-nopay tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Đơn hàng chưa thanh toán"></span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${(orderSeller.markSellerShipment == 'NEW' || orderSeller.markSellerShipment == 'STOCKING') && orderSeller.markSellerShipment!='DELIVERED'}">
                                                            <span class="icon16-noship tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Chưa chuyển hàng"></span>
                                                        </c:when>
                                                        <c:when test="${orderSeller.markSellerShipment == 'DELIVERING'}">
                                                            <jsp:setProperty name="date" property="time" value="${orderSeller.shipmentUpdateTime}" />  
                                                            <span class="icon16-shipping tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Hàng đã xuất kho lúc
                                                                  <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy" value="${date}"></fmt:formatDate> và đang trên đường vận chuyển tới người mua"></span>
                                                        </c:when>
                                                        <c:when test="${orderSeller.markSellerShipment == 'DELIVERED'}">
                                                            <jsp:setProperty name="date" property="time" value="${orderSeller.deliveredTime}" />  
                                                            <span class="icon16-ship-success tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Đã chuyển hàng <c:if test="${orderSeller.deliveredTime>0}"> Vào lúc <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy" value="${date}"></fmt:formatDate></c:if>"></span>
                                                        </c:when>
                                                        <c:when test="${orderSeller.markSellerShipment == 'RETURN'}">
                                                            <span class="icon16-transfer-success tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Chuyển hoàn"></span>
                                                        </c:when>
                                                        <c:when test="${orderSeller.markSellerShipment == 'DENIED'}">
                                                            <span class="icon16-transfer-cancel tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Hủy vận đơn"></span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="icon16-noship tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Chưa Tạo vận đơn"></span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <span id="${orderSeller.id}review">
                                                        <span class="icon16-reviewgray tool-tip" data-toggle="tooltip" data-placement="top" title="" data-original-title="Chưa viết đánh giá"></span>
                                                    </span>            
                                                    <span id="${orderSeller.id}message">
                                                        <span class="icon16-getreviewgray tool-tip" data-toggle="tooltip" data-placement="top" title="" data-original-title="Chưa gửi tin nhắn"></span>
                                                    </span>  
                                                </p>
                                                <c:choose>
                                                    <c:when test="${orderSeller.markSellerPayment == 'PAID' && orderSeller.scId == null}">
                                                        <div class="mgt-10">
                                                            <button type="button" class="btn btn-primary btn-block btn-sm" onclick="order.linkLadingShip(${orderSeller.id})"> Tạo vận đơn <br> vận chuyển</button>
                                                            <button type="button" class="btn btn-primary btn-block btn-sm" onclick="order.linkLading(${orderSeller.id})">Tạo vận đơn CoD</button>
                                                        </div>
                                                        <p><a href="javascript:void();" onclick="order.sendMessgeBuyer('${orderSeller.id}')">Nhắn người mua</a></p>
                                                        <div class="dropdown mgt-10">
                                                            <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                            <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                <c:if test="${orderSeller.markSellerPayment!='PAID'}">
                                                                    <li><a href="javascript:void();" onclick="order.markPaymentStatus('${orderSeller.id}', true);">Đánh dấu đã thu tiền</a></li>
                                                                    </c:if>
                                                                    <c:if test="${orderSeller.markSellerPayment=='PAID' && (orderSeller.nlId==null || orderSeller.nlId=='')}">
                                                                    <li><a href="javascript:void();" onclick="order.unmarkPaymentStatus('${orderSeller.id}', true);">Bỏ đánh dấu đã thu tiền</a></li>
                                                                    </c:if>
                                                                    <c:if test="${orderSeller.markSellerShipment!='DELIVERED'}">
                                                                    <li><a href="javascript:void();" onclick="order.markShipmentStatus('${orderSeller.id}', true);">Đánh dấu đã giao hàng</a></li>
                                                                    </c:if>
                                                                    <c:if test="${orderSeller.markSellerShipment=='DELIVERED'}">
                                                                    <li><a href="javascript:void();" onclick="order.unmarkShipmentStatus('${orderSeller.id}', true);">Bỏ đánh dấu đã giao hàng</a></li>
                                                                    </c:if>    
                                                                <li><a href="javascript:void();" onclick="order.refund('${orderSeller.id}')">${orderSeller.refundStatus?'Bỏ đánh dấu':'Đánh dấu'} hoàn tiền</a></li>
                                                                <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                <li><a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html">Xem hoá đơn</a></li>
                                                                <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                            </ul>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${orderSeller.markSellerPayment == 'PAID' && orderSeller.markSellerShipment == 'NEW'}">
                                                        <div class="mgt-10">
                                                            <a href="javascript:void();" class="btn btn-primary btn-sm" onclick="order.sendMessgeBuyer('${orderSeller.id}')">Nhắn người mua</a>
                                                        </div>
                                                        <div class="dropdown mgt-10">
                                                            <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                            <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                <li><a href="javascript:void();" onclick="order.refund('${orderSeller.id}')">${orderSeller.refundStatus?'Bỏ đánh dấu':'Đánh dấu'} hoàn tiền</a></li>
                                                                <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                <li><a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html">Xem hoá đơn</a></li>
                                                                <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                            </ul>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${orderSeller.markSellerPayment == 'PAID' && orderSeller.scId != null && orderSeller.markSellerShipment == 'DELIVERING'}">
                                                        <div class="mgt-10">
                                                            <a href="javascript:void();" class="btn btn-primary btn-sm" onclick="order.sendMessgeBuyer('${orderSeller.id}')">Nhắn người mua</a>
                                                        </div>
                                                        <div class="dropdown mgt-10">
                                                            <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                            <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                <li><a href="javascript:void();" onclick="order.refund('${orderSeller.id}')">${orderSeller.refundStatus?'Bỏ đánh dấu':'Đánh dấu'} hoàn tiền</a></li>
                                                                <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                <li><a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html">Xem hoá đơn</a></li>
                                                                <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                            </ul>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${orderSeller.markSellerPayment == 'PAID' && orderSeller.scId != null && orderSeller.markSellerShipment == 'RETURN'}">
                                                        <div class="mgt-10">
                                                            <a href="javascript:void();" class="btn btn-primary btn-sm" onclick="order.sendMessgeBuyer('${orderSeller.id}')">Nhắn người mua</a>
                                                        </div>
                                                        <p><a href="javascript:void();" onclick="order.refund('${orderSeller.id}')">${orderSeller.refundStatus?'Bỏ đánh dấu':'Đánh dấu'} hoàn tiền</a></p>
                                                        <div class="dropdown mgt-10">
                                                            <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                            <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                <li><a href="javascript:void();" onclick="order.refund('${orderSeller.id}')">${orderSeller.refundStatus?'Bỏ đánh dấu':'Đánh dấu'} hoàn tiền</a></li>
                                                                <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                <li><a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html">Xem hoá đơn</a></li>
                                                                <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                            </ul>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${orderSeller.markSellerPayment == 'PAID' && orderSeller.scId != null && orderSeller.markSellerShipment == 'DELIVERED'}">
                                                        <div class="mgt-10">
                                                            <button type="button" class="btn btn-primary btn-block btn-sm" onclick="order.review(${orderSeller.id});">Đánh giá uy tín</button>
                                                        </div>
                                                        <p><a href="javascript:void();" onclick="order.sendMessgeBuyer('${orderSeller.id}')">Nhắn người mua</a></p>
                                                        <div class="dropdown mgt-10">
                                                            <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                            <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                <c:if test="${orderSeller.markSellerPayment!='PAID'}">
                                                                    <li><a href="javascript:void();" onclick="order.markPaymentStatus('${orderSeller.id}', true);">Đánh dấu đã thu tiền</a></li>
                                                                    </c:if>
                                                                    <c:if test="${orderSeller.markSellerPayment=='PAID' && (orderSeller.nlId==null || orderSeller.nlId=='')}">
                                                                    <li><a href="javascript:void();" onclick="order.unmarkPaymentStatus('${orderSeller.id}', true);">Bỏ đánh dấu đã thu tiền</a></li>
                                                                    </c:if>
                                                                    <c:if test="${orderSeller.markSellerShipment!='DELIVERED'}">
                                                                    <li><a href="javascript:void();" onclick="order.markShipmentStatus('${orderSeller.id}', true);">Đánh dấu đã giao hàng</a></li>
                                                                    </c:if>
                                                                    <c:if test="${orderSeller.markSellerShipment=='DELIVERED' && (orderSeller.scId==null || orderSeller.scId=='')}">
                                                                    <li><a href="javascript:void();" onclick="order.unmarkShipmentStatus('${orderSeller.id}', true);">Bỏ đánh dấu đã giao hàng</a></li>
                                                                    </c:if>    
                                                                <li><a href="javascript:void();" onclick="order.refund('${orderSeller.id}')">${orderSeller.refundStatus?'Bỏ đánh dấu':'Đánh dấu'} hoàn tiền</a></li>
                                                                <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                <li><a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html">Xem hoá đơn</a></li>
                                                                <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                            </ul>
                                                        </div>
                                                    </c:when>

                                                    <c:when test="${orderSeller.markSellerPayment == 'PAID' && orderSeller.scId != null && orderSeller.markSellerShipment == 'DENIED'}">
                                                        <div class="mgt-10">
                                                            <a href="javascript:void();" class="btn btn-primary btn-block btn-sm disabled" onclick="order.refund('${orderSeller.id}')">${orderSeller.refundStatus?'Bỏ đánh dấu':'Đánh dấu'} <br> hoàn tiền</a>
                                                        </div>
                                                        <!--<p><a href="javascript:void();" onclick="order.review(${orderSeller.id});">Đánh giá uy tín</a></p>-->
                                                        <div class="dropdown mgt-10">
                                                            <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                            <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                <c:if test="${orderSeller.markSellerPayment!='PAID'}">
                                                                    <li><a href="javascript:void();" onclick="order.markPaymentStatus('${orderSeller.id}', true);">Đánh dấu đã thu tiền</a></li>
                                                                    </c:if>
                                                                    <c:if test="${orderSeller.markSellerPayment=='PAID' && (orderSeller.nlId==null || orderSeller.nlId=='')}">
                                                                    <li><a href="javascript:void();" onclick="order.unmarkPaymentStatus('${orderSeller.id}', true);">Bỏ đánh dấu đã thu tiền</a></li>
                                                                    </c:if>
                                                                    <c:if test="${orderSeller.markSellerShipment!='DELIVERED'}">
                                                                    <li><a href="javascript:void();" onclick="order.markShipmentStatus('${orderSeller.id}', true);">Đánh dấu đã giao hàng</a></li>
                                                                    </c:if>
                                                                    <c:if test="${orderSeller.markSellerShipment=='DELIVERED'}">
                                                                    <li><a href="javascript:void();" onclick="order.unmarkShipmentStatus('${orderSeller.id}', true);">Bỏ đánh dấu đã giao hàng</a></li>
                                                                    </c:if>    
                                                                <li><a href="javascript:void();" onclick="order.refund('${orderSeller.id}')">${orderSeller.refundStatus?'Bỏ đánh dấu':'Đánh dấu'} hoàn tiền</a></li>
                                                                <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                <li><a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html">Xem hoá đơn</a></li>
                                                                <li><a href="javascript:void();" onclick="order.sendMessgeBuyer('${orderSeller.id}')">Nhắn người mua</a></li>
                                                                <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                            </ul>
                                                        </div>
                                                    </c:when>

                                                    <c:otherwise>
                                                        <c:if test="${orderSeller.scId == null}">
                                                            <c:if test="${orderSeller.paymentMethod != 'NONE'}">
                                                                <div class="mgt-10">
                                                                    <button type="button" class="btn btn-primary btn-block btn-sm" onclick="order.linkLading(${orderSeller.id})">Tạo vận đơn CoD</button>
                                                                    <button type="button" class="btn btn-primary btn-block btn-sm" onclick="order.linkLadingShip(${orderSeller.id})">Tạo vận đơn <br> vận chuyển</button>
                                                                </div>
                                                                <div class="dropdown mgt-10">
                                                                    <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                    <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                        <li> <a onclick="order.linkEditOrder(${orderSeller.id})">Sửa hoá đơn</a></li>
                                                                            <c:if test="${orderSeller.markSellerPayment!='PAID'}">
                                                                            <li><a href="javascript:void();" onclick="order.markPaymentStatus('${orderSeller.id}', true);">Đánh dấu đã thu tiền</a></li>
                                                                            </c:if>
                                                                            <c:if test="${orderSeller.markSellerPayment=='PAID' && (orderSeller.nlId==null || orderSeller.nlId=='')}">
                                                                            <li><a href="javascript:void();" onclick="order.unmarkPaymentStatus('${orderSeller.id}', true);">Bỏ đánh dấu đã thu tiền</a></li>
                                                                            </c:if>
                                                                            <c:if test="${orderSeller.markSellerShipment!='DELIVERED'}">
                                                                            <li><a href="javascript:void();" onclick="order.markShipmentStatus('${orderSeller.id}', true);">Đánh dấu đã giao hàng</a></li>
                                                                            </c:if>
                                                                            <c:if test="${orderSeller.markSellerShipment=='DELIVERED' && (orderSeller.scId==null || orderSeller.scId=='')}">
                                                                            <li><a href="javascript:void();" onclick="order.unmarkShipmentStatus('${orderSeller.id}', true);">Bỏ đánh dấu đã giao hàng</a></li>
                                                                            </c:if>   

                                                                        </li>
                                                                        <li onclick="order.sendMessgeBuyer('${orderSeller.id}')"><a>Nhắn người mua</a></li>
                                                                        <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                        <li><a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html">Xem hoá đơn</a></li>
                                                                        <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                                    </ul>
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${orderSeller.paymentMethod == 'NONE'}">
                                                                <div class="mgt-10">
                                                                    <button type="button" class="btn btn-primary btn-block btn-sm" onclick="order.linkEditOrder(${orderSeller.id})">Sửa hoá đơn</button>
                                                                    <button type="button" class="btn btn-primary btn-block btn-sm" onclick="order.linkLading(${orderSeller.id})">Tạo vận đơn CoD</button>
                                                                    <button type="button" class="btn btn-primary btn-block btn-sm" onclick="order.linkLadingShip(${orderSeller.id})">Tạo vận đơn <br> vận chuyển</button>
                                                                </div>
                                                                <div class="dropdown mgt-10">
                                                                    <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                    <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                        <li onclick="order.sendMessgeBuyer('${orderSeller.id}')"><a>Nhắn người mua</a></li>
                                                                            <c:if test="${orderSeller.markSellerPayment!='PAID'}">
                                                                            <li><a href="javascript:void();" onclick="order.markPaymentStatus('${orderSeller.id}', true);">Đánh dấu đã thu tiền</a></li>
                                                                            </c:if>
                                                                            <c:if test="${orderSeller.markSellerPayment=='PAID' && (orderSeller.nlId==null || orderSeller.nlId=='')}">
                                                                            <li><a href="javascript:void();" onclick="order.unmarkPaymentStatus('${orderSeller.id}', true);">Bỏ đánh dấu đã thu tiền</a></li>
                                                                            </c:if>
                                                                            <c:if test="${orderSeller.markSellerShipment!='DELIVERED'}">
                                                                            <li><a href="javascript:void();" onclick="order.markShipmentStatus('${orderSeller.id}', true);">Đánh dấu đã giao hàng</a></li>
                                                                            </c:if>
                                                                            <c:if test="${orderSeller.markSellerShipment=='DELIVERED' && (orderSeller.scId==null || orderSeller.scId=='')}">
                                                                            <li><a href="javascript:void();" onclick="order.unmarkShipmentStatus('${orderSeller.id}', true);">Bỏ đánh dấu đã giao hàng</a></li>
                                                                            </c:if>   
                                                                        <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                        <li><a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html">Xem hoá đơn</a></li>
                                                                        <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                                    </ul>
                                                                </div>
                                                            </c:if>

                                                        </c:if>

                                                        <c:if test="${orderSeller.scId != null}">
                                                            <c:choose>
                                                                <c:when test="${orderSeller.markSellerPayment != 'PAID' && orderSeller.markSellerShipment == 'NEW'}">
                                                                    <div class="mgt-10">
                                                                        <a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html" target="_blank" class="btn btn-primary btn-block btn-sm">Xem hoá đơn</a>
                                                                        <!--<button type="button" class="btn btn-primary btn-block btn-sm" onclick="order.review(${orderSeller.id});">Đánh giá uy tín</button>-->
                                                                    </div>
                                                                    <div class="dropdown mgt-10">
                                                                        <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                        <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                            <li onclick="order.sendMessgeBuyer('${orderSeller.id}')"><a>Nhắn người mua</a></li>
                                                                            <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                            <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                                        </ul>
                                                                    </div>
                                                                </c:when>
                                                                <c:when test="${orderSeller.markSellerPayment != 'PAID' && orderSeller.markSellerShipment == 'DELIVERING'}">
                                                                    <div class="mgt-10">
                                                                        <a href="javascript:void();" class="btn btn-primary btn-sm" onclick="order.sendMessgeBuyer('${orderSeller.id}')">Nhắn người mua</a>
                                                                    </div>
                                                                    <div class="dropdown mgt-10">
                                                                        <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                        <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                            <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                            <li><a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html">Xem hoá đơn</a></li>
                                                                            <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                                        </ul>
                                                                    </div>
                                                                </c:when>
                                                                <c:when test="${orderSeller.markSellerPayment != 'PAID' && orderSeller.markSellerShipment == 'DELIVERED'}">
                                                                    <div class="mgt-10">
                                                                        <a href="javascript:void();" class="btn btn-primary btn-block btn-sm" onclick="order.review(${orderSeller.id});">Đánh giá uy tín</a>
                                                                    </div>
                                                                    <p><a href="javascript:void();" onclick="order.sendMessgeBuyer('${orderSeller.id}')">Nhắn người mua</a></p>
                                                                    <div class="dropdown mgt-10">
                                                                        <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                        <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                            <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                            <li><a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html">Xem hoá đơn</a></li>
                                                                            <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                                        </ul>
                                                                    </div>
                                                                </c:when>
                                                                <c:when test="${orderSeller.markSellerPayment != 'PAID' && orderSeller.markSellerShipment == 'RETURN'}">
                                                                    <div class="mgt-10">
                                                                        <!--<a href="javascript:void();" class="btn btn-primary btn-block btn-sm" onclick="order.review(${orderSeller.id});">Đánh giá uy tín</a>-->
                                                                        <a href="javascript:void();" class="btn btn-primary btn-block btn-sm" onclick="order.sendMessgeBuyer('${orderSeller.id}')">Nhắn người mua</a>
                                                                    </div>
                                                                    <div class="dropdown mgt-10">
                                                                        <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                        <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                            <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                            <li><a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html">Xem hoá đơn</a></li>
                                                                            <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                                        </ul>
                                                                    </div>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <div class="mgt-10">
                                                                        <a href="${baseUrl}/${orderSeller.id}/chi-tiet-don-hang.html" target="_blank" class="btn btn-primary btn-block btn-sm">Xem hoá đơn</a>
                                                                        <!--<button type="button" class="btn btn-primary btn-block btn-sm" onclick="order.review(${orderSeller.id});">Đánh giá uy tín</button>-->
                                                                    </div>
                                                                    <div class="dropdown mgt-10">
                                                                        <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                        <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                            <li onclick="order.sendMessgeBuyer('${orderSeller.id}')"><a>Nhắn người mua</a></li>
                                                                            <li onclick="order.statusNote('${orderSeller.id}', 'display');"><a>Ghi chú</a></li>
                                                                            <li><a href="javascript:void();" onclick="order.removeOrder('${orderSeller.id}')">Xoá</a></li>
                                                                        </ul>
                                                                    </div>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </c:if>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:if>
                                            <c:if test="${tab == 'recycleBin'}">
                                                <div class="mgt-10">
                                                    <button type="button" class="btn btn-primary btn-block btn-sm" onclick="order.recoveryOrder('${orderSeller.id}')">Khôi phục</button>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="col-sm-12 reset-padding-all lablel-invoice textNote" for="${orderSeller.id}" <c:if test="${orderSeller.sellerNote == null || orderSeller.sellerNote == ''}"> style="display: none"</c:if> >
                                        <label class="label label-info label-block" ><strong>Ghi chú:</strong><span rel="noteText_${orderSeller.id}"> ${orderSeller.sellerNote}</span></label>
                                    </div>
                                    <div class="statusNote" for="${orderSeller.id}" style="display: none">
                                        <div class="col-sm-12 lablel-invoice">
                                            <div class="form-horizontal">
                                                <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" id="hasError">
                                                    <c:if test="${orderSeller.sellerNote == null || orderSeller.sellerNote == ''}">
                                                        <input type="text" name="note" rel="${orderSeller.id}" class="form-control" >
                                                    </c:if>
                                                    <c:if test="${orderSeller.sellerNote != null && orderSeller.sellerNote != ''}">
                                                        <input type="text" name="note" rel="${orderSeller.id}" value="${orderSeller.sellerNote}" class="form-control">
                                                    </c:if>
                                                </div>
                                                <div class="col-lg-2 col-md-3 col-sm-3 reset-padding-all">
                                                    <button type="button" onclick="order.saveNoteSeller('${orderSeller.id}');" class="btn btn-default">Lưu lại</button>
                                                    <button type="button" onclick="order.statusNote('${orderSeller.id}', 'hidden');" class="btn btn-default">Đóng</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="page-ouner clearfix">
                        <span class="pull-left go-pages">
                            <label class="control-label pull-left">Tới trang: </label>
                            <input type="text" class="form-control pull-left" id="indexNext" value="${orderSellers.pageIndex+1}">
                            <a href="javascript:void();" onclick="order.search(true, $('#indexNext').val())" class="btn btn-default pull-left">
                                <span class="glyphicon glyphicon-log-in"></span>
                            </a>
                        </span>
                        <ul class="pagination pull-right">
                            <c:if test="${orderSellers.pageIndex > 3}"><li><a href="javascript:void();" onclick="order.search(true, 1)" style="cursor: pointer" ><<</a></li></c:if>
                            <c:if test="${orderSellers.pageIndex > 2}"><li><a href="javascript:void();" onclick="order.search(true, ${orderSellers.pageIndex})" style="cursor: pointer"  ><</a></li></c:if>
                            <c:if test="${orderSellers.pageIndex > 3}"><li><a href="javascript:void();"style="cursor: pointer">...</a></li></c:if>
                            <c:if test="${orderSellers.pageIndex >= 3}"><li><a href="javascript:void();" onclick="order.search(true, ${orderSellers.pageIndex - 2})" style="cursor: pointer" >${orderSellers.pageIndex-2}</a></li></c:if>
                            <c:if test="${orderSellers.pageIndex >= 2}"><li><a href="javascript:void();" onclick="order.search(true, ${orderSellers.pageIndex -1})" style="cursor: pointer" >${orderSellers.pageIndex-1}</a></li></c:if>
                            <c:if test="${orderSellers.pageIndex >= 1}"><li><a href="javascript:void();" onclick="order.search(true, 1)" style="cursor: pointer" >${orderSellers.pageIndex}</a></li></c:if>
                            <li class="active" ><a class="btn btn-primary">${orderSellers.pageIndex + 1}</a>
                            <c:if test="${orderSellers.pageCount - orderSellers.pageIndex > 1}"><li><a href="javascript:void();" onclick="order.search(true, ${orderSellers.pageIndex + 2})" style="cursor: pointer" >${orderSellers.pageIndex+2}</a></li></c:if>
                            <c:if test="${orderSellers.pageCount - orderSellers.pageIndex > 2}"><li><a href="javascript:void();" onclick="order.search(true, ${orderSellers.pageIndex +3})" style="cursor: pointer"  >${orderSellers.pageIndex+3}</a></li></c:if>
                            <c:if test="${orderSellers.pageCount - orderSellers.pageIndex > 3}"><li><a href="javascript:void();" style="cursor: pointer">...</a></c:if>
                            <c:if test="${orderSellers.pageCount - orderSellers.pageIndex > 2}"><li><a href="javascript:void();" onclick="order.search(true, ${orderSellers.pageIndex +2})" style="cursor: pointer" >></a></li></c:if>
                            <c:if test="${orderSellers.pageCount - orderSellers.pageIndex > 2}"><li><a href="javascript:void();" onclick="order.search(true, ${orderSellers.pageCount})" style="cursor: pointer" >>></a></li></c:if>
                        </ul>
                    </div>

                </div>                            
            </div>                     
        </div>   
    </div>
</div>