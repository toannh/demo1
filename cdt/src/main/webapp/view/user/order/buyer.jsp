<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username != null?viewer.user.username:viewer.user.email}</a></li>
        <li class="active">Đơn hàng của tôi</li>
    </ol>
    <h1 class="title-pages">Đơn hàng của tôi</h1>
    <div class="tabs-content-user invoice-buyer-pages">
        <ul class="tab-title-content">
            <li class="${(tab == '')?'active':''}"><a href="${url:orderBuyer()}">Tất cả (${countOrder})</a></li>
            <li class="${(tab == 'paymentNEW')?'active':''}"><a href="${url:orderBuyer()}?tab=paymentNEW">Chưa thanh toán (${countPaymentNew})</a></li>
            <li class="${(tab == 'paymentPAID')?'active':''}"><a href="${url:orderBuyer()}?tab=paymentPAID">Đã thanh toán (${countPaymentPaid})</a></li>
            <li class="${(tab == 'shipmentNEW')?'active':''}"><a href="${url:orderBuyer()}?tab=shipmentNEW">Chưa nhận hàng(${countShipmentNew})</a></li>                            
            <li class="${(tab == 'shipmentDELIVERED')?'active':''}"><a href="${url:orderBuyer()}?tab=shipmentDELIVERED">Đã nhận hàng(${countShipmentDelivered})</a></li>                            
            <li class="${(tab == 'recycleBin')?'active':''}"><a href="${url:orderBuyer()}?tab=recycleBin">Thùng rác (${countRecycleBin})</a></li> 
            <!--li class="right-title pull-right">
                <div class="num-tin">
            ${pageOrder.pageIndex + 1}/${pageOrder.pageCount==0?'1':pageOrder.pageCount}
            <c:if test="${pageOrder.pageIndex + 1 > 1}">
                <button onclick="order.nextPage(${pageOrder.pageIndex}, false);" type="button" class="btn btn-default btn-sm">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </button> 
            </c:if>
            <c:if test="${pageOrder.pageIndex + 1 < pageOrder.pageCount}">
                <button onclick="order.nextPage('${pageOrder.pageIndex + 2}', false);" type="button" class="btn btn-default btn-sm">
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
                            <div class="form-group">Xem theo:</div>
                            <div class="form-group">
                                <select class="form-control" name="timeselect">
                                    <option value="0" ${timeselect == ''?'selected':''}>Tất cả thời gian</option>
                                    <option value="3" ${timeselect == '3'?'selected':''}>3 ngày</option>
                                    <option value="7" ${timeselect == '7'?'selected':''}>7 ngày</option>
                                    <option value="30" ${timeselect == '30'?'selected':''}>30 ngày</option>
                                    <option value="60" ${timeselect == '60'?'selected':''}>60 ngày</option>
                                </select>
                                <input name="timeForm" type="hidden" />
                                <input name="timeTo" type="hidden" />
                            </div>
                            <div class="form-group form-group-reset">
                                <div class="input-group">
                                    <input name="keyword" type="text" class="form-control" value="${(keyword != '')? keyword:''}" placeholder="Tìm kiếm"/>
                                    <span class="input-group-addon" style="cursor: pointer"><span class="glyphicon glyphicon-search" onclick="order.search(false);" ></span></span>
                                </div>
                            </div>

                        </div>
                        <div class="clearfix"></div>
                    </div>  
                    <c:if test="${fn:length(pageOrder.data) <= 0}">
                        <div class="cdt-message bg-danger text-center">Không tìm thấy đơn hàng nào!</div>
                    </c:if>    
                    <c:if test="${fn:length(pageOrder.data) > 0}">
                        <div class="invoice-layout-pages">
                            <div class="invoice-title-list">
                                <div class="title-config-shop clearfix">
                                    <div class="col-sm-6 col-xs-4">Đơn hàng</div>
                                    <div class="col-sm-4 col-xs-4">Người bán</div>
                                    <div class="col-sm-2 col-xs-4">Tình trạng/Thao tác</div>
                                </div>
                            </div>
                            <ul class="invoice-list-item">
                                <jsp:useBean id="date" class="java.util.Date" />
                                <c:forEach var="order" items="${pageOrder.data}">
                                    <li class="clearfix" id="${order.id}">
                                        <c:if test="${order.shipmentMessage != null && order.shipmentMessage != ''}">
                                            <div class="col-sm-10 reset-padding-all lablel-invoice">
                                                <label class="label label-warning label-block"><strong>Trạng thái vận chuyển:</strong> ${order.shipmentMessage}</label>
                                            </div>
                                        </c:if>
                                        <div class="col-sm-6 reset-padding-all">
                                            <p class="invoiceid">
                                                <span class="tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Mã đơn hàng">Mã ĐH:</span> <a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">${order.id}</a>
                                                <c:if test="${order.scId!=null}">
                                                    | <span class="tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Mã vận đơn">Mã VĐ:</span>  <a href="http://mc.shipchung.vn/van-don.html?sc_code=${order.scId}" target="_blank">${order.scId}</a>
                                                </c:if>
                                                <c:if test="${order.nlId!=null}">
                                                    | <span class="tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Mã thanh toán">Mã TT:</span>  <a href="#">${order.nlId}</a>
                                                </c:if>
                                            </p>
                                            <c:set var="price" value="0" />
                                            <c:forEach var="item" items="${order.items}" >
                                                <div class="invoice-item-product">
                                                    <c:set var="price" value="${price + (item.itemPrice * item.quantity)}" />
                                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                        <tbody>
                                                            <tr>
                                                                <td valign="top" class="clr-99" width="7%"><strong>${item.quantity}</strong> x </td>
                                                                <td valign="top" width="10%"><div class="img-invoice-pro"><img src="${item.images[0]}" width="80" height="80"></div></td>
                                                                <td align="left" valign="top" width="83%">
                                                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                                        <tbody>
                                                                            <tr>
                                                                                <td width="70%" align="left"><a href="${url:item(item.itemId,item.itemName)}" target="_blank">${item.itemName}</a></td>
                                                                                <td>
                                                                                    <div class="text-right"><strong>${text:numberFormat(item.itemPrice)} <sup> đ</sup></strong> </div>
                                                                                </td>
                                                                            </tr>
                                                                            <c:if test="${fn:length(item.subItem) >0}">
                                                                                <c:forEach items="${item.subItem}" var="subitem">
                                                                                    <tr property="${item.itemId}">
                                                                                        <td>${subitem.quantity}</b> x
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
                                                                                        <c:if test="${item.shipmentType == null || item.shipmentType == 'AGREEMENT'}">
                                                                                            Không rõ phí
                                                                                        </c:if>
                                                                                        <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice == 0}">
                                                                                            Miễn phí
                                                                                        </c:if>
                                                                                        <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice > 0}">
                                                                                            Cố định ${text:numberFormat(item.shipmentPrice)}
                                                                                        </c:if>
                                                                                        <c:if test="${item.shipmentType == 'BYWEIGHT'}">
                                                                                            Linh hoạt theo địa chỉ người mua&nbsp;&nbsp;
                                                                                        </c:if>
                                                                                    </p>
                                                                                </td>
                                                                                <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice > 0}">
                                                                                    <td>
                                                                                        <div class="text-right"><strong>${text:numberFormat(item.shipmentPrice)}<sup>đ</sup></strong></div>
                                                                                    </td>
                                                                                </c:if>
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </c:forEach>
                                            <div class="invoice-count">
                                                <p class="clearfix">
                                                    <span class="pull-left">Tổng tiền hàng</span>
                                                    <span class="pull-right">${text:numberFormat(order.totalPrice)} <sup>đ</sup></span>      
                                                </p>
                                                <p class="clearfix">
                                                    <c:if test="${order.shipmentPrice >0}">
                                                        <span class="pull-left">Phí VC</span>
                                                        <span class="pull-right">${text:numberFormat(order.shipmentPrice)} <sup>đ</sup></span>      
                                                    </c:if>
                                                </p>
                                                <p class="clearfix">
                                                    <c:if test="${order.couponPrice >0}">
                                                        <span class="pull-left">Giảm trừ</span>
                                                        <span class="pull-right">${text:numberFormat(order.couponPrice)} <sup>đ</sup></span>      
                                                    </c:if>
                                                </p>

                                                <p class="clearfix">
                                                    <span class="pull-left">Tổng cộng</span>
                                                    <span class="pull-right clr-red">${text:numberFormat(order.finalPrice)} <sup>đ</sup></span>
                                                </p>

                                            </div>
                                        </div>
                                        <div class="col-sm-4 col-xs-6 reset-padding-all">
                                            <div class="invoice-item-product">
                                                <p>Người bán: <a href="#">${order.user.name != null?order.user.name:order.user.username}</a></p>
                                                <p>Điện thoại: ${order.user.phone}</p>
                                                <p>Email: <a href="#">${order.user.email}</a></p>
                                                <p>Đ/c: ${order.user.address} </p>
                                            </div>
                                            <div class="invoice-item-product">
                                                <p><strong>Hình thức thanh toán</strong>: 
                                                    <c:choose>
                                                        <c:when test="${order.paymentMethod == 'NL'}">
                                                            Ví ngân lượng
                                                        </c:when>
                                                        <c:when test="${order.paymentMethod == 'COD'}">
                                                            Thu tiền tại nhà
                                                        </c:when>
                                                        <c:when test="${order.paymentMethod == 'NONE'}">
                                                            Tự liên hệ
                                                        </c:when>
                                                        <c:when test="${order.paymentMethod == 'VISA' || order.paymentMethod == 'MASTER'}">
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
                                                        <c:when test="${order.shipmentService == 'SLOW'}">
                                                            Tiết kiệm (5-7 ngày)
                                                        </c:when>
                                                        <c:when test="${order.shipmentService == 'FAST'}">
                                                            Nhanh (2-3 ngày)
                                                        </c:when>
                                                        <c:when test="${order.shipmentService == 'RAPID'}">
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
                                            <c:if test="${tab != 'recycleBin'}">
                                                <div class="invoice-item-product text-center">
                                                    <p>
                                                        <c:choose>
                                                            <c:when test="${order.markBuyerPayment == 'NEW'}">
                                                                <span class="icon16-nopay tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Đơn hàng chưa thanh toán"></span>
                                                            </c:when>
                                                            <c:when test="${order.markBuyerPayment == 'PENDING'}">
                                                                <span class="icon16-nopay tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Đơn hàng chờ thanh toán"></span>
                                                            </c:when>
                                                            <c:when test="${order.markBuyerPayment == 'PAID'}">
                                                                <jsp:setProperty name="date" property="time" value="${order.paidTime}" /> 
                                                                <span class="icon16-pay tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Đơn hàng đã thanh toán <c:if test="${order.paidTime>0}"> vào lúc <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy" value="${date}"></fmt:formatDate></c:if>"></span>
                                                            </c:when>

                                                            <c:otherwise>
                                                                <span class="icon16-nopay tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Đơn hàng chưa thanh toán"></span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <c:choose>
                                                            <c:when test="${order.markBuyerShipment == 'NEW' || order.markBuyerShipment == 'STOCKING'}">
                                                                <span class="icon16-noship tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Chưa chuyển hàng"></span>
                                                            </c:when>
                                                            <c:when test="${order.markBuyerShipment == 'DELIVERING'}">
                                                                <span class="icon16-shipping tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Đang chuyển hàng"></span>
                                                            </c:when>
                                                            <c:when test="${order.markBuyerShipment == 'DELIVERED'}">
                                                                <jsp:setProperty name="date" property="time" value="${order.shipmentUpdateTime}" /> 
                                                                <span class="icon16-ship-success tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Hàng đã chuyển <c:if test="${order.shipmentUpdateTime>0}">vào lúc <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy" value="${date}"></fmt:formatDate></c:if>"></span>
                                                            </c:when>
                                                            <c:when test="${order.markBuyerShipment == 'RETURN'}">
                                                                <span class="icon16-transfer-success tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Chuyển hoàn"></span>
                                                            </c:when>
                                                            <c:when test="${order.markBuyerShipment == 'DENIED'}">
                                                                <span class="icon16-transfer-cancel tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Hủy vận đơn"></span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:if test="${order.scId == null}">
                                                                    <span class="icon16-noship tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Chưa duyệt vận đơn"></span>
                                                                </c:if>
                                                                <c:if test="${order.scId != null}">
                                                                    <span class="icon16-noship tool-tip" data-toggle="tooltip" data-placement="top" title="" data-html="true" data-original-title="Chưa chuyển hàng"></span>
                                                                </c:if>
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <span id="${order.id}review">
                                                            <span class="icon16-reviewgray tool-tip" data-toggle="tooltip" data-placement="top" title="" data-original-title="Chưa viết đánh giá"></span>
                                                        </span>            
                                                        <span id="${order.id}message">
                                                            <span class="icon16-getreviewgray tool-tip" data-toggle="tooltip" data-placement="top" title="" data-original-title="Chưa gửi tin nhắn"></span>
                                                        </span>
                                                    </p>
                                                    <c:choose>
                                                        <c:when test="${order.markBuyerPayment!='PAID' && order.scId == null && order.paymentMethod=='NONE'}">
                                                            <div class="mgt-10">
                                                                <button onclick="order.linkEditOrder('${order.id}');" type="button" class="btn btn-primary btn-sm">Sửa hóa đơn</button>
                                                            </div>
                                                            <p><a href="javascript:;" onclick="order.sendMessge('${order.id}');">Nhắn người bán</a></p>
                                                            <div class="dropdown mgt-10">
                                                                <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                    <li><a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">Xem hoá đơn</a></li>
                                                                        <c:if test="${order.markBuyerPayment!='PAID'}">
                                                                        <li><a href="javascript:;" onclick="order.markPaymentStatus('${order.id}', false);">Đánh dấu đã thanh toán</a></li>
                                                                        </c:if>
                                                                        <c:if test="${order.markBuyerPayment=='PAID' && (order.nlId==null || order.nlId=='')}">
                                                                        <li><a href="javascript:;" onclick="order.unmarkPaymentStatus('${order.id}', false);">Bỏ đánh dấu đã thanh toán</a></li>
                                                                        </c:if>
                                                                        <c:if test="${order.markBuyerShipment!='DELIVERED'}">
                                                                        <li><a href="javascript:;" onclick="order.markShipmentStatus('${order.id}', false);">Đánh dấu đã nhận hàng</a></li>
                                                                        </c:if>
                                                                        <c:if test="${order.markBuyerShipment=='DELIVERED' && (order.scId==null || order.scId=='')}">
                                                                        <li><a href="javascript:;" onclick="order.unmarkShipmentStatus('${order.id}', false);">Bỏ đánh dấu đã nhận hàng</a></li>
                                                                        </c:if>
                                                                    <li onclick="order.statusNote('${order.id}', 'display');"><a>Ghi chú</a></li>
                                                                    <li onclick="order.removeOrder('${order.id}');"><a>Xoá</a></li>
                                                                    <li><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(order.sellerId).concat('"}]'))}" target="_blank">Xem sản phẩm cùng người bán</a></li>                                                            
                                                                    <li><a href="javascript:;" onclick="order.linkSearchItem(${order.items[0].categoryPath[0]}, '${order.items[0].nameCategory}');">Xem sản phẩm tương tự</a></li>
                                                                </ul>
                                                            </div>
                                                        </c:when>
                                                        <c:when test="${order.markBuyerPayment!='PAID' && order.scId == null}">
                                                            <div class="mgt-10">
                                                                <button onclick="order.linkEditOrder('${order.id}');" type="button" class="btn btn-primary btn-sm">Thanh toán</button>
                                                                <div class="custom-tip ctp-icon">
                                                                    <span class="glyphicon glyphicon-question-sign"></span>
                                                                    <div class="custom-tip-pop ctp-right">
                                                                        <div class="ctp-inner">
                                                                            <div class="ctp-content">
                                                                                <div class="tooltip-nl">
                                                                                    <h5><strong>THANH TOÁN ONLINE QUA NGÂN LƯỢNG</strong></h5>
                                                                                    <p>Không cần mở tài khoản NgânLượng, chỉ cần có tài khoản hoặc thẻ ngân hàng</p>
                                                                                    <h6 class="text-warning"><strong>Lợi ích dành cho người mua</strong></h6>
                                                                                    <div class="clearfix">
                                                                                        <strong>• Tiện lợi:</strong><br> Hỗ trợ nhiều hình thức như thẻ tín dụng, thẻ ATM, chuyển khoản qua internet banking, thẻ cào điện thoại, số dư ví NgânLượng ...<br>
                                                                                        <strong>• Yên tâm</strong><br>
                                                                                        Được NgânLượng và ChợĐiênTử là trung gian nhận tiền bảo vệ quyền lợi khi gặp sự cố. Bảo hiểm tới 100% giá trị hàng hóa. <br>
                                                                                        <strong>• Nhanh</strong><br>
                                                                                        Ngồi một chỗ mua hàng dễ dàng với nhiều lựa chọn. <br>
                                                                                        <strong>• Miễn phí hoàn toàn</strong>
                                                                                    </div>
                                                                                </div>
                                                                            </div><!-- ctp-content -->
                                                                        </div><!-- ctp-inner -->
                                                                    </div><!-- custom-tip-pop -->
                                                                </div>
                                                            </div>
                                                            <p><a onclick="order.linkEditOrder('${order.id}');">Sửa hoá đơn</a></p>
                                                            <div class="dropdown mgt-10">
                                                                <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                    <li><a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">Xem hoá đơn</a></li>
                                                                    <li><a href="javascript:;" onclick="order.sendMessge('${order.id}');">Nhắn người bán</a></li>
                                                                        <c:if test="${order.markBuyerPayment!='PAID'}">
                                                                        <li><a href="javascript:;" onclick="order.markPaymentStatus('${order.id}', false);">Đánh dấu đã thanh toán</a></li>
                                                                        </c:if>
                                                                        <c:if test="${order.markBuyerPayment=='PAID' && (order.nlId==null || order.nlId=='')}">
                                                                        <li><a href="javascript:;" onclick="order.unmarkPaymentStatus('${order.id}', false);">Bỏ đánh dấu đã thanh toán</a></li>
                                                                        </c:if>
                                                                        <c:if test="${order.markBuyerShipment!='DELIVERED'}">
                                                                        <li><a href="javascript:;" onclick="order.markShipmentStatus('${order.id}', false);">Đánh dấu đã nhận hàng</a></li>
                                                                        </c:if>
                                                                        <c:if test="${order.markBuyerShipment=='DELIVERED' && (order.scId==null || order.scId=='')}">
                                                                        <li><a href="javascript:;" onclick="order.unmarkShipmentStatus('${order.id}', false);">Bỏ đánh dấu đã nhận hàng</a></li>
                                                                        </c:if>
                                                                    <li onclick="order.statusNote('${order.id}', 'display');"><a>Ghi chú</a></li>
                                                                    <li onclick="order.removeOrder('${order.id}');"><a>Xoá</a></li>
                                                                    <li><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(order.sellerId).concat('"}]'))}" target="_blank">Xem sản phẩm cùng người bán</a></li>
                                                                    <li><a href="javascript:;" onclick="order.linkSearchItem(${order.items[0].categoryPath[0]}, '${order.items[0].nameCategory}');">Xem sản phẩm tương tự</a></li>
                                                                </ul>
                                                            </div>
                                                        </c:when>

                                                        <c:when test="${order.markBuyerPayment!='PAID' && order.scId != null && order.markBuyerShipment == 'DELIVERING'}">
                                                            <div class="mgt-10">
                                                                <a href="javascript:;" class="btn btn-primary btn-sm" onclick="order.sendMessge('${order.id}');">Nhắn người bán</a>
                                                            </div>

                                                            <div class="dropdown mgt-10">
                                                                <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác<b class="caret"></b></a>
                                                                <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                    <li><a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">Xem hóa đơn</a></li>
                                                                        <c:if test="${order.markBuyerPayment!='PAID'}">
                                                                        <li><a href="javascript:;" onclick="order.markPaymentStatus('${order.id}', false);">Đánh dấu đã thanh toán</a></li>
                                                                        </c:if>
                                                                        <c:if test="${order.markBuyerPayment=='PAID' && (order.nlId==null || order.nlId=='')}">
                                                                        <li><a href="javascript:;" onclick="order.unmarkPaymentStatus('${order.id}', false);">Bỏ đánh dấu đã thanh toán</a></li>
                                                                        </c:if>
                                                                    <li onclick="order.statusNote('${order.id}', 'display');"><a>Ghi chú</a></li>
                                                                    <li onclick="order.removeOrder('${order.id}');"><a>Xoá</a></li>
                                                                    <li><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(order.sellerId).concat('"}]'))}" target="_blank">Xem sản phẩm cùng người bán</a></li> 
                                                                    <li><a href="javascript:;" onclick="order.linkSearchItem(${order.items[0].categoryPath[0]}, '${order.items[0].nameCategory}');">Xem sản phẩm tương tự</a></li>
                                                                </ul>
                                                            </div> 
                                                        </c:when>
                                                        <c:when test="${order.markBuyerPayment=='PAID' && order.scId != null && order.markBuyerShipment == 'NEW'}">
                                                            <div class="mgt-10">
                                                                <a href="javascript:;" class="btn btn-primary btn-sm" onclick="order.sendMessge('${order.id}');">Nhắn người bán</a>
                                                            </div>
                                                            <p><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(order.sellerId).concat('"}]'))}" target="_blank">Xem sản phẩm cùng người bán</a></p>
                                                            <div class="dropdown mgt-10">
                                                                <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác<b class="caret"></b></a>
                                                                <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                    <li onclick="order.review(${order.id});"><a>Đánh giá uy tín</a></li>
                                                                        <c:if test="${order.markBuyerShipment!='DELIVERED'}">
                                                                        <li><a href="javascript:;" onclick="order.markShipmentStatus('${order.id}', false);">Đánh dấu đã nhận hàng</a></li>
                                                                        </c:if>
                                                                        <c:if test="${order.markBuyerShipment=='DELIVERED' && (order.scId==null || order.scId=='')}">
                                                                        <li><a href="javascript:;" onclick="order.unmarkShipmentStatus('${order.id}', false);">Bỏ đánh dấu đã nhận hàng</a></li>
                                                                        </c:if>
                                                                    <li><a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">Xem hóa đơn</a></li>
                                                                    <li onclick="order.statusNote('${order.id}', 'display');"><a>Ghi chú</a></li>
                                                                    <li onclick="order.removeOrder('${order.id}');"><a>Xoá</a></li>
                                                                    <li><a href="javascript:;" onclick="order.linkSearchItem(${order.items[0].categoryPath[0]}, '${order.items[0].nameCategory}');">Xem sản phẩm tương tự</a></li>
                                                                </ul>
                                                            </div> 
                                                        </c:when>
                                                        <c:when test="${order.markBuyerPayment=='PAID' && order.scId != null && order.markBuyerShipment == 'DELIVERING'}">
                                                            <div class="mgt-10">
                                                                <a href="javascript:;" class="btn btn-primary btn-sm" onclick="order.sendMessge('${order.id}');">Nhắn người bán</a>
                                                            </div>
                                                            <p><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(order.sellerId).concat('"}]'))}" target="_blank">Xem sản phẩm cùng người bán</a></p>
                                                            <div class="dropdown mgt-10">
                                                                <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác<b class="caret"></b></a>
                                                                <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                    <li onclick="order.review(${order.id});"><a>Đánh giá uy tín</a></li>
                                                                        <c:if test="${order.markBuyerPayment!='PAID'}">
                                                                        <li><a href="javascript:;" onclick="order.markPaymentStatus('${order.id}', false);">Đánh dấu đã thanh toán</a></li>
                                                                        </c:if>
                                                                        <c:if test="${order.markBuyerPayment=='PAID' && (order.nlId==null || order.nlId=='')}">
                                                                        <li><a href="javascript:;" onclick="order.unmarkPaymentStatus('${order.id}', false);">Bỏ đánh dấu đã thanh toán</a></li>
                                                                        </c:if>
                                                                    <li><a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">Xem hóa đơn</a></li>
                                                                    <li onclick="order.statusNote('${order.id}', 'display');"><a>Ghi chú</a></li>
                                                                    <li onclick="order.removeOrder('${order.id}');"><a>Xoá</a></li>
                                                                    <li><a href="javascript:;" onclick="order.linkSearchItem(${order.items[0].categoryPath[0]}, '${order.items[0].nameCategory}');">Xem sản phẩm tương tự</a></li>
                                                                </ul>
                                                            </div> 
                                                        </c:when>
                                                        <c:when test="${order.markBuyerPayment=='PAID' && order.scId != null && order.markBuyerShipment == 'DELIVERED'}">
                                                            <div class="mgt-10">
                                                                <button type="button" class="btn btn-primary btn-sm" onclick="order.review(${order.id});">Đánh giá uy tín</button>
                                                            </div>
                                                            <p><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(order.sellerId).concat('"}]'))}" target="_blank">Xem sản phẩm cùng người bán</a></p>
                                                            <div class="dropdown mgt-10">
                                                                <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác<b class="caret"></b></a>
                                                                <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                    <li><a a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">Xem hóa đơn</a></li>
                                                                        <c:if test="${order.markBuyerPayment!='PAID'}">
                                                                        <li><a href="javascript:;" onclick="order.markPaymentStatus('${order.id}', false);">Đánh dấu đã thanh toán</a></li>
                                                                        </c:if>
                                                                        <c:if test="${order.markBuyerPayment=='PAID' && (order.nlId==null || order.nlId=='')}">
                                                                        <li><a href="javascript:;" onclick="order.unmarkPaymentStatus('${order.id}', false);">Bỏ đánh dấu đã thanh toán</a></li>
                                                                        </c:if>
                                                                    <li onclick="order.sendMessge('${order.id}');"><a>Nhắn người bán</a></li>
                                                                    <li onclick="order.statusNote('${order.id}', 'display');"><a>Ghi chú</a></li>
                                                                    <li onclick="order.removeOrder('${order.id}');"><a>Xoá</a></li>
                                                                    <li><a href="javascript:;" onclick="order.linkSearchItem(${order.items[0].categoryPath[0]}, '${order.items[0].nameCategory}');">Xem sản phẩm tương tự</a></li>
                                                                </ul>
                                                            </div>
                                                        </c:when>
                                                        <c:when test="${order.markBuyerPayment=='PAID' && order.scId != null && order.markBuyerShipment == 'RETURN'}">
                                                            <div class="mgt-10">
                                                                <a href="javascript:;" class="btn btn-primary btn-sm" onclick="order.sendMessge('${order.id}');">Nhắn người bán</a>
                                                            </div>
                                                            <p><a onclick="order.review(${order.id});">Đánh giá uy tín</a></p>
                                                            <div class="dropdown mgt-10">
                                                                <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác<b class="caret"></b></a>
                                                                <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                    <li><a a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">Xem hóa đơn</a></li>
                                                                    <li onclick="order.statusNote('${order.id}', 'display');"><a>Ghi chú</a></li>
                                                                    <li onclick="order.removeOrder('${order.id}');"><a>Xoá</a></li>
                                                                    <li><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(order.sellerId).concat('"}]'))}" target="_blank">Xem sản phẩm cùng người bán</a></li>                                                            
                                                                    <li><a href="javascript:;" onclick="order.linkSearchItem(${order.items[0].categoryPath[0]}, '${order.items[0].nameCategory}');">Xem sản phẩm tương tự</a></li>
                                                                </ul>
                                                            </div>
                                                        </c:when>
                                                        <c:when test="${order.markBuyerPayment == 'PAID' && order.markBuyerShipment == 'DENIED'}">
                                                            <div class="mgt-10">
                                                                <a href="javascript:;" class="btn btn-primary btn-sm" onclick="order.sendMessge('${order.id}');">Nhắn người bán</a>
                                                            </div>
                                                            <!--<p><a>Đánh giá uy tín</a></p>-->
                                                            <div class="dropdown mgt-10">
                                                                <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác<b class="caret"></b></a>
                                                                <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                    <li><a a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">Xem hóa đơn</a></li>
                                                                    <li onclick="order.statusNote('${order.id}', 'display');"><a>Ghi chú</a></li>
                                                                    <li onclick="order.removeOrder('${order.id}');"><a>Xoá</a></li>
                                                                    <!-- <li><a href="#">Xem sản phẩm cùng người bán</a></li>                                                            
                                                                    <li><a href="#">Xem sản phẩm tương tự</a></li>-->
                                                                </ul>
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:if test="${order.markBuyerPayment != 'PAID' && order.scId == null}">
                                                                <div class="mgt-10">
                                                                    <button onclick="order.linkEditOrder('${order.id}');" type="button" class="btn btn-primary btn-sm">Thanh toán</button>
                                                                    <div class="custom-tip ctp-icon">
                                                                        <span class="glyphicon glyphicon-question-sign"></span>
                                                                        <div class="custom-tip-pop ctp-right">
                                                                            <div class="ctp-inner">
                                                                                <div class="ctp-content">
                                                                                    <div class="tooltip-nl">
                                                                                        <h5><strong>THANH TOÁN ONLINE QUA NGÂN LƯỢNG</strong></h5>
                                                                                        <p>Không cần mở tài khoản NgânLượng, chỉ cần có tài khoản hoặc thẻ ngân hàng</p>
                                                                                        <h6 class="text-warning"><strong>Lợi ích dành cho người mua</strong></h6>
                                                                                        <div class="clearfix">
                                                                                            <strong>• Tiện lợi:</strong><br> Hỗ trợ nhiều hình thức như thẻ tín dụng, thẻ ATM, chuyển khoản qua internet banking, thẻ cào điện thoại, số dư ví NgânLượng ...<br>
                                                                                            <strong>• Yên tâm</strong><br>
                                                                                            Được NgânLượng và ChợĐiênTử là trung gian nhận tiền bảo vệ quyền lợi khi gặp sự cố. Bảo hiểm tới 100% giá trị hàng hóa. <br>
                                                                                            <strong>• Nhanh</strong><br>
                                                                                            Ngồi một chỗ mua hàng dễ dàng với nhiều lựa chọn. <br>
                                                                                            <strong>• Miễn phí hoàn toàn</strong>
                                                                                        </div>
                                                                                    </div>
                                                                                </div><!-- ctp-content -->
                                                                            </div><!-- ctp-inner -->
                                                                        </div><!-- custom-tip-pop -->
                                                                    </div>
                                                                </div>
                                                                <p><a onclick="order.linkEditOrder('${order.id}');">Sửa hoá đơn</a></p>
                                                                <div class="dropdown mgt-10">
                                                                    <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                    <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                        <li><a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">Xem hoá đơn</a></li>
                                                                        <!--<li><a href="#">Nhắn người bán</a></li>-->
                                                                        <c:if test="${order.markBuyerPayment!='PAID'}">
                                                                            <li><a href="javascript:;" onclick="order.markPaymentStatus('${order.id}', false);">Đánh dấu đã thanh toán</a></li>
                                                                            </c:if>
                                                                            <c:if test="${order.markBuyerPayment=='PAID' && (order.nlId==null || order.nlId=='')}">
                                                                            <li><a href="javascript:;" onclick="order.unmarkPaymentStatus('${order.id}', false);">Bỏ đánh dấu đã thanh toán</a></li>
                                                                            </c:if>
                                                                            <c:if test="${order.markBuyerShipment!='DELIVERED'}">
                                                                            <li><a href="javascript:;" onclick="order.markShipmentStatus('${order.id}', false);">Đánh dấu đã nhận hàng</a></li>
                                                                            </c:if>
                                                                            <c:if test="${order.markBuyerShipment=='DELIVERED'}">
                                                                            <li><a href="javascript:;" onclick="order.unmarkShipmentStatus('${order.id}', false);">Bỏ đánh dấu đã nhận hàng</a></li>
                                                                            </c:if>
                                                                        <li onclick="order.statusNote('${order.id}', 'display');"><a>Ghi chú</a></li>
                                                                        <li onclick="order.removeOrder('${order.id}');"><a>Xoá</a></li>
                                                                        <!-- <li><a href="#">Xem sản phẩm cùng người bán</a></li>                                                            
                                                                        <li><a href="#">Xem sản phẩm tương tự</a></li>-->
                                                                    </ul>
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${order.markBuyerPayment != 'PAID' && order.scId != null}">
                                                                <div class="mgt-10">
                                                                    <a href="javascript:;" class="btn btn-primary btn-sm" onclick="order.sendMessge('${order.id}');">Nhắn người bán</a>
                                                                </div>

                                                                <div class="dropdown mgt-10">
                                                                    <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                    <ul class="dropdown-menu pull-right text-left" role="menu">

                                                                        <li><a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">Xem hoá đơn</a></li>
                                                                        <li onclick="order.statusNote('${order.id}', 'display');"><a>Ghi chú</a></li>
                                                                            <c:if test="${order.markBuyerPayment!='PAID'}">
                                                                            <li><a href="javascript:;" onclick="order.markPaymentStatus('${order.id}', false);">Đánh dấu đã thanh toán</a></li>
                                                                            </c:if>
                                                                            <c:if test="${order.markBuyerPayment=='PAID' && (order.nlId==null || order.nlId=='')}">
                                                                            <li><a href="javascript:;" onclick="order.unmarkPaymentStatus('${order.id}', false);">Bỏ đánh dấu đã thanh toán</a></li>
                                                                            </c:if>
                                                                        <li onclick="order.removeOrder('${order.id}');"><a>Xoá</a></li>
                                                                        <li><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(order.sellerId).concat('"}]'))}" target="_blank">Xem sản phẩm cùng người bán</a></li>                                                            
                                                                        <li><a href="javascript:;" onclick="order.linkSearchItem(${order.items[0].categoryPath[0]}, '${order.items[0].nameCategory}');">Xem sản phẩm tương tự</a></li>
                                                                    </ul>
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${order.markBuyerPayment == 'PAID' && order.scId != null}">
                                                                <div class="mgt-10">
                                                                    <a href="javascript:;" class="btn btn-primary btn-sm" onclick="order.sendMessge('${order.id}');">Nhắn người bán</a>
                                                                </div>

                                                                <div class="dropdown mgt-10">
                                                                    <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                    <ul class="dropdown-menu pull-right text-left" role="menu">

                                                                        <li><a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">Xem hoá đơn</a></li>
                                                                        <li onclick="order.statusNote('${order.id}', 'display');"><a>Ghi chú</a></li>
                                                                            <c:if test="${order.markBuyerPayment!='PAID'}">
                                                                            <li><a href="javascript:;" onclick="order.markPaymentStatus('${order.id}', false);">Đánh dấu đã thanh toán</a></li>
                                                                            </c:if>
                                                                            <c:if test="${order.markBuyerPayment=='PAID' && (order.nlId==null || order.nlId=='')}">
                                                                            <li><a href="javascript:;" onclick="order.unmarkPaymentStatus('${order.id}', false);">Bỏ đánh dấu đã thanh toán</a></li>
                                                                            </c:if>
                                                                        <li onclick="order.removeOrder('${order.id}');"><a>Xoá</a></li>
                                                                        <li><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(order.sellerId).concat('"}]'))}" target="_blank">Xem sản phẩm cùng người bán</a></li>                                                            
                                                                        <li onclick="order.review(${order.id});"><a>Đánh giá uy tín</a></li>
                                                                        <li><a href="javascript:;" onclick="order.linkSearchItem(${order.items[0].categoryPath[0]}, '${order.items[0].nameCategory}');">Xem sản phẩm tương tự</a></li>
                                                                    </ul>
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${order.markBuyerPayment == 'PAID' && order.scId == null}">
                                                                <div class="mgt-10">
                                                                    <a href="javascript:;" onclick="order.sendMessge('${order.id}');" class="btn btn-primary btn-sm">Nhắn người bán</a>
                                                                </div>
                                                                <p><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(order.sellerId).concat('"}]'))}" target="_blank">Xem sản phẩm cùng người bán</a></p>
                                                                <div class="dropdown mgt-10">
                                                                    <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác<b class="caret"></b></a>
                                                                    <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                        <li><a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" target="_blank">Xem hóa đơn</a></li>
                                                                        <li onclick="order.review(${order.id});"><a>Đánh giá uy tín</a></li>
                                                                            <c:if test="${order.markBuyerPayment!='PAID'}">
                                                                            <li><a href="javascript:;" onclick="order.markPaymentStatus('${order.id}', false);">Đánh dấu đã thanh toán</a></li>
                                                                            </c:if>
                                                                            <c:if test="${order.markBuyerPayment=='PAID' && (order.nlId==null || order.nlId=='')}">
                                                                            <li><a href="javascript:;" onclick="order.unmarkPaymentStatus('${order.id}', false);">Bỏ đánh dấu đã thanh toán</a></li>
                                                                            </c:if>
                                                                            <c:if test="${order.markBuyerShipment!='DELIVERED'}">
                                                                            <li><a href="javascript:;" onclick="order.markShipmentStatus('${order.id}', false);">Đánh dấu đã nhận hàng</a></li>
                                                                            </c:if>
                                                                            <c:if test="${order.markBuyerShipment=='DELIVERED' && (order.scId==null || order.scId=='')}">
                                                                            <li><a href="javascript:;" onclick="order.unmarkShipmentStatus('${order.id}', false);">Bỏ đánh dấu đã nhận hàng</a></li>
                                                                            </c:if>
                                                                        <li onclick="order.statusNote('${order.id}', 'display');"><a>Ghi chú</a></li>
                                                                        <li onclick="order.removeOrder('${order.id}');"><a>Xoá</a></li>
                                                                        <li><a href="javascript:;" onclick="order.linkSearchItem(${order.items[0].categoryPath[0]}, '${order.items[0].nameCategory}');">Xem sản phẩm tương tự</a></li>
                                                                    </ul>
                                                                </div> 
                                                            </c:if>

                                                        </c:otherwise>
                                                    </c:choose>

                                                </div>
                                            </c:if>
                                            <c:if test="${tab == 'recycleBin'}">
                                                <div class="mgt-10">
                                                    <button type="button" class="btn btn-primary btn-block btn-sm" onclick="order.recoveryOrder('${order.id}')">Khôi phục</button>
                                                </div>
                                            </c:if>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="col-sm-12 reset-padding-all lablel-invoice textNote" for="${order.id}" <c:if test="${order.note == null || order.note == ''}"> style="display: none"</c:if> >
                                            <label class="label label-info label-block" ><strong>Ghi chú:</strong><span rel="noteText_${order.id}"> ${order.note}</span></label>
                                        </div>
                                        <div class="statusNote" for="${order.id}" style="display: none">
                                            <div class="col-sm-12 lablel-invoice">
                                                <div class="form-horizontal">
                                                    <div class="col-lg-10 col-md-9 col-sm-9 col-xs-7" id="hasError">
                                                        <c:if test="${order.note == null || order.note == ''}">
                                                            <input type="text" name="note" rel="${order.id}" class="form-control" >
                                                        </c:if>
                                                        <c:if test="${order.note != null && order.note != ''}">
                                                            <input type="text" name="note" rel="${order.id}" value="${order.note}" class="form-control">
                                                        </c:if>
                                                    </div>
                                                    <div class="col-lg-2 col-md-3 col-sm-3 reset-padding-all">
                                                        <button type="button" onclick="order.saveNote('${order.id}');" class="btn btn-default">Lưu lại</button>
                                                        <button type="button" onclick="order.statusNote('${order.id}', 'hidden');" class="btn btn-default">Đóng</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${pageOrder.dataCount > 0}">
                        <div class="page-ouner clearfix">
                            <span class="pull-left go-pages">
                                <label class="control-label pull-left">Tới trang: </label>
                                <input type="text" class="form-control pull-left" id="indexNext" value="${pageOrder.pageIndex+1}">
                                <a onclick="order.search(false, $('#indexNext').val())" class="btn btn-default pull-left">
                                    <span class="glyphicon glyphicon-log-in"></span>
                                </a>
                            </span>
                            <ul class="pagination pull-right">
                                <c:if test="${pageOrder.pageIndex > 3}"><li><a href="javascript:;" onclick="order.search(false, 1)" href="javascript:;"><<</a></li></c:if>
                                <c:if test="${pageOrder.pageIndex > 2}"><li><a href="javascript:;" onclick="order.search(false, ${pageOrder.pageIndex})"><</a></li></c:if>
                                <c:if test="${pageOrder.pageIndex > 3}"><li><a>...</a></li></c:if>
                                <c:if test="${pageOrder.pageIndex >= 3}"><li><a href="javascript:;" onclick="order.search(false, ${pageOrder.pageIndex - 2})" >${pageOrder.pageIndex-2}</a></li></c:if>
                                <c:if test="${pageOrder.pageIndex >= 2}"><li><a href="javascript:;" onclick="order.search(false, ${pageOrder.pageIndex - 1})" >${pageOrder.pageIndex-1}</a></li></c:if>
                                <c:if test="${pageOrder.pageIndex >= 1}"><li><a href="javascript:;" onclick="order.search(false, ${pageOrder.pageIndex})"  >${pageOrder.pageIndex}</a></li></c:if>
                                <li class="active" ><a class="btn btn-primary">${pageOrder.pageIndex + 1}</a>
                                <c:if test="${pageOrder.pageCount - pageOrder.pageIndex > 1}"><li><a href="javascript:;" onclick="order.search(false, ${pageOrder.pageIndex +2})"  >${pageOrder.pageIndex+2}</a></li></c:if>
                                <c:if test="${pageOrder.pageCount - pageOrder.pageIndex > 2}"><li><a href="javascript:;" onclick="order.search(false, ${pageOrder.pageIndex +3})"  >${pageOrder.pageIndex+3}</a></li></c:if>
                                <c:if test="${pageOrder.pageCount - pageOrder.pageIndex > 3}"><li><a href="javascript:;" >...</a></c:if>
                                <c:if test="${pageOrder.pageCount - pageOrder.pageIndex > 2}"><li><a href="javascript:;" onclick="order.search(false, ${pageOrder.pageIndex +2})" >></a></li></c:if>
                                <c:if test="${pageOrder.pageCount - pageOrder.pageIndex > 2}"><li><a href="javascript:;" onclick="order.search(false, ${pageOrder.pageCount})"  >>></a></li></c:if>
                                </ul>
                            </div>
                    </c:if>
                </div>
            </div>                            
        </div>                     
    </div>   
</div>