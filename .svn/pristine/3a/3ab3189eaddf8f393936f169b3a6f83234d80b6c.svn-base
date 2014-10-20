<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@taglib uri="http://chodientu.vn/text" prefix="text" %>
<%@taglib uri="http://chodientu.vn/url" prefix="url" %>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách đơn hàng
        </a>
    </li>
</ul>
<div class="func-container"> 
    <form:form modelAttribute="orderSearch" role="form" style="margin-top:20px;">
        <div class="col-sm-3">
            <div class="form-group">
                <form:input path="id" type="text" class ="form-control" placeholder="Mã hóa đơn"/>
            </div>
            <div class="form-group">
                <form:input path="nlId" type="text" class ="form-control" placeholder="Mã thanh toán"/>
            </div>
            <div class="form-group">
                <form:input path="scId" type="text" class ="form-control" placeholder="Mã vận đơn"/>
            </div>
            <div class="form-group">
                <form:input path="itemId" type="text" class ="form-control" placeholder="Mã sản phẩm"/>
            </div>
            <div class="form-group">
                <form:input path="keyword" type="text" class ="form-control" placeholder="Tên sản phẩm"/>
            </div>
            <div class="form-group">
                <form:input path="sellerId" type="text" class ="form-control" placeholder="Mã người bán"/>
            </div>
            <div class="form-group">
                <form:select path="sellerCityId" class="form-control">
                </form:select>
            </div>
            <div class="form-group sellerDistrictId" dist="${orderSearch.sellerDistrictId}" city="${orderSearch.sellerCityId}"></div>
        </div>
        <div class="col-sm-3">

            <div class="form-group">
                <form:input path="createTimeFrom" type="hidden" class ="form-control createTimeForm" placeholder="Ngày tạo từ"/>
            </div>
            <div class="form-group">
                <form:input path="paidTimeFrom" type="hidden" class ="form-control paidTimeForm" placeholder="Ngày thanh toán từ"/>
            </div>
            <div class="form-group">
                <form:input path="shipmentCreateTimeFrom" type="hidden" class ="form-control paidTimeForm" placeholder="Ngày tạo vận đơn từ"/>
            </div>
            <div class="form-group">
                <form:input path="receiverEmail" type="text" class ="form-control" placeholder="Email người nhận"/>
            </div>
            <div class="form-group">
                <form:input path="receiverPhone" type="text" class ="form-control" placeholder="Phone người nhận"/>
            </div>
            <div class="form-group">
                <form:input path="receiverName" type="text" class ="form-control" placeholder="Tên người nhận"/>
            </div>
            <div class="form-group">
                <form:select path="receiverCityId" class="form-control">
                </form:select>
            </div>
            <div class="form-group receiverDistrictId" dist="${orderSearch.receiverDistrictId}" city="${orderSearch.receiverCityId}"></div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <form:input path="createTimeTo" type="hidden" class ="form-control createTimeTo" placeholder="Đến ngày"/>
            </div>
            <div class="form-group">
                <form:input path="paidTimeTo" type="hidden" class ="form-control paidTimeTo" placeholder="Đến ngày"/>
            </div>
            <div class="form-group">
                <form:input path="shipmentCreateTimeTo" type="hidden" class ="form-control paidTimeForm" placeholder="Đến ngày"/>
            </div>
            <div class="form-group">
                <form:input path="buyEmail" type="text" class ="form-control" placeholder="Email người mua"/>
            </div>
            <div class="form-group">
                <form:input path="buyPhone" type="text" class ="form-control" placeholder="Phone người mua"/>
            </div>
            <div class="form-group">
                <form:input path="buyName" type="text" class ="form-control" placeholder="Tên người mua"/>
            </div>
            <div class="form-group">
                <form:select path="buyerCityId" class="form-control">
                </form:select>
            </div>
            <div class="form-group buyerDistrictId" dist="${orderSearch.buyerDistrictId}" city="${orderSearch.buyerCityId}"></div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <form:input path="sellerEmail" type="text" class ="form-control" placeholder="Email người bán"/>
            </div>
            <div class="form-group">
                <form:input path="sellerPhone" type="text" class ="form-control" placeholder="Phone người bán"/>
            </div>
            <div class="form-group">
                <form:select path="paymentMethod" class="form-control">
                    <form:option value="0" label="Hình thức thanh toán"/>
                    <form:option value="1" label="Thu tiền tại nhà (Cod)"/>
                    <form:option value="2" label="Thanh toán ngân lượng"/> 
                    <form:option value="3" label="Tự liên hệ" />
                </form:select>
            </div>
            <div class="form-group">
                <form:select path="paymentStatusSearch" class="form-control">
                    <form:option value="0" label="Trạng thái thanh toán"/>
                    <form:option value="1" label="Chưa thanh toán"/>
                    <form:option value="2" label="Đã thanh toán"/>
                    <form:option value="3" label="Chờ thanh toán"/>
                </form:select>
            </div>
            <div class="form-group">
                <form:select path="shipmentStatusSearch" class="form-control">
                    <form:option value="0" label="Trạng thái vận đơn"/>
                    <form:option value="1" label="Chưa duyệt"/>
                    <form:option value="2" label="Chưa lấy hàng"/>
                    <form:option value="3" label="Đang giao hàng"/>
                    <form:option value="4" label="Chuyển hoàn"/>
                    <form:option value="5" label="Đã hủy"/>
                    <form:option value="6" label="Hàng đã tới tay người mua"/>
                </form:select>
            </div>
            <div class="form-group">
                <form:select path="refundStatus" class="form-control">
                    <form:option value="0" label="Trạng thái refund"/>
                    <form:option value="1" label="Không refund"/>
                    <form:option value="2" label="Refund toàn bộ"/>
                </form:select>
            </div>


        </div>
        <div class="col-sm-6 col-sm-offset-4">
            <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-search" ></i> Tìm kiếm</button>
            <button type="reset" class="btn btn-primary"><i class="glyphicon glyphicon-refresh" ></i> Làm lại</button>
        </div>
    </form:form>
    <div class="clearfix"></div>
    <div class="cms-line" style="margin-top: 10px;" ></div>
    <div style="margin-top: 20px;">
        <h5 class="pull-left alert alert-success" style="padding: 10px; width: 33%;">Tìm thấy tổng số <span style="color: tomato; font-weight: bolder">${dataPage.dataCount} </span> đơn hàng trong <span style="color: tomato; font-weight: bolder">${dataPage.pageCount}</span> trang.</h5>        
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <h5>Tổng số tiền hàng:  <strong class="clr-red"> ${text:numberFormat(sumPrice.totalPrice)}</strong> <span class="u-price">đ</span> </h5>
    <h5>Tổng số tiền thanh toán:  <strong class="clr-red"> ${text:numberFormat(sumPrice.finalPrice)}</strong>  <span class="u-price">đ</span>  </h5>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="center" style="text-align: center; vertical-align: middle">STT</th>
            <th class="center" style="text-align: center; vertical-align: middle; width: 150px;">Mã hóa đơn</th>
            <th class="center" colspan="2" style="text-align: center; vertical-align: middle">Thông tin liên hệ</th>
            <th class="center" style="text-align: center; vertical-align: middle">Thông tin hóa đơn</th>
        </tr>
        <tbody>
            <jsp:useBean id="date" class="java.util.Date" />
            <c:forEach items="${dataPage.data}" var="orders" varStatus="orderStatus">
                <tr <c:if test="${orders.paymentStatus=='PAID'}">class="success"</c:if>> 
                    <td style=" vertical-align: middle">${orderStatus.index+1}</td>
                    <td style=" width: 150px; vertical-align: middle">
                        <jsp:setProperty name="date" property="time" value="${orders.createTime}" /> 
                        Mã hóa đơn:
                        <a href="${basicUrl}/${orders.id}/chi-tiet-don-hang.html" target="_blank" data-toggle="tooltip" data-placement="top" title="Ngày tạo: <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy" value="${date}"></fmt:formatDate>">${orders.id}</a>
                        <c:if test="${orders.scId!=null}">
                            <jsp:setProperty name="date" property="time" value="${orders.shipmentCreateTime}" /> 
                            <br/>Mã vận đơn: <a href="http://mc.shipchung.vn/van-don.html?sc_code=${orders.scId}" target="_blank" data-toggle="tooltip" data-placement="top" title="Ngày tạo: <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy" value="${date}"></fmt:formatDate>      | Ngày cập nhật: <jsp:setProperty name="date" property="time" value="${orders.shipmentUpdateTime}" /><fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy" value="${date}"></fmt:formatDate> ">${orders.scId}</a>
                        </c:if>
                        <c:if test="${orders.nlId!=null}">
                            <jsp:setProperty name="date" property="time" value="${orders.paidTime}" /> 
                            <br/>Mã thanh toán: <span class="label label-success" data-toggle="tooltip" data-placement="top" title="Thanh toán lúc: <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy" value="${date}"></fmt:formatDate>">${orders.nlId}</span>
                        </c:if>
                    </td>
                    <td style="text-align: left; width: 350px;">
                        <p style="text-align: center;font-weight: bold">Thông tin người bán</p>
                        <p>${orders.user.name}</p>
                        <p>UserName: ${orders.user.username}</p>
                        <p>Email: ${orders.user.email}</p>
                        <p>Phone: ${orders.user.phone}</p>
                        <p>Địa chỉ: ${orders.user.address}</p>
                        <c:if test="${orders.note!=null && orders.note!=''}">
                            <p>Ghi chú: ${orders.sellerNote}</p>
                        </c:if>
                        <hr/>
                        <p style="text-align: center;font-weight: bold">Trạng thái đánh dấu</p>
                        <div style="text-align: left;border-right:1px dashed rgb(185, 185, 187);" class="col-xs-6 col-sm-6">
                            <p style="color: green">Người bán </p>
                            <c:if test="${orders.markSellerPayment!=null}"><p>Thanh toán: ${orders.markSellerPayment=='PAID' ?'Đã':'Chưa'} TT</p></c:if>
                            <c:if test="${orders.markSellerShipment!=null}"><p>Giao hàng:  ${orders.markSellerShipment=='DELIVERED' ?'Đã':'Chưa'} GH</p></c:if>
                            <c:if test="${orders.refundStatus!=null}"><p>Hoàn tiền: ${orders.refundStatus?'Đã':'Chưa'} HT</p></c:if>
                            </div>
                            <div style="text-align: left;" class="col-xs-6 col-sm-6">
                                <p style="color: green">Người mua</p>
                            <c:if test="${orders.markBuyerPayment!=null}"><p>Thanh toán: ${orders.markBuyerPayment=='PAID' ?'Đã':'Chưa'} TT</p></c:if>
                            <c:if test="${orders.markBuyerShipment!=null}"><p>Giao hàng: ${orders.markBuyerShipment=='DELIVERED' ?'Đã':'Chưa'} GH</p></c:if>
                            </div>

                        </td>
                        <td style="text-align: left; width: 300px;"> 
                            <p style="text-align: center;font-weight: bold">Thông tin người mua/nhận</p>
                            <p>Tên người mua: ${orders.buyerName}</p>
                        <p>Email: ${orders.buyerEmail}</p>
                        <p>Phone: ${orders.buyerPhone}</p>
                        <p>Địa chỉ: ${orders.buyerAddress}</p>
                        <p>Tỉnh/Thành phố: <span class="loadLast" city="${orders.buyerCityId}"></span></p>
                        <p>Quận huyện: <span class="loadLast" dist="${orders.buyerDistrictId}"></span></p>
                        <c:if test="${orders.note!=null && orders.note!=''}">
                            <p>Ghi chú: ${orders.note}</p>
                        </c:if>
                        <hr/>
                        <p>Tên người nhận: ${orders.receiverName}</p>
                        <p>Email: ${orders.receiverEmail}</p>
                        <p>Phone: ${orders.receiverPhone}</p>
                        <p>Địa chỉ: ${orders.receiverAddress}</p>
                        <p>Tỉnh/Thành phố: <span class="loadLast" city="${orders.receiverCityId}"></span></p>
                        <p>Quận huyện: <span class="loadLast" dist="${orders.receiverDistrictId}"></span></p>
                    </td>
                    <td style="vertical-align: middle">
                        <c:forEach items="${orders.items}" var="orderItem">
                            <p>
                                <span style="display: inline-block;">
                                    <span class="badge">${orderItem.quantity}</span> <a href="${basicUrl}/san-pham/${orderItem.itemId}/${text:createAlias(orderItem.itemName)}.html" target="_blank">${orderItem.itemName}</a>
                                </span>
                                <span style="display: inline-block; float: right; text-align: right">${text:numberFormat(orderItem.itemPrice)}  <span class="u-price">đ</span> </span>
                            </p>
                        </c:forEach>
                        <hr>
                        <p>Giảm giá: <span style="display: inline-block; float: right; text-align: right">
                                <c:if test="${orders.couponPrice >0}">
                                    ${text:numberFormat(orders.couponPrice)} <sup class="u-price">đ</sup>      
                                </c:if>
                                <c:if test="${orders.couponPrice <=0}">
                                    Không giảm giá
                                </c:if>

                            </span></p>

                        <c:if test="${orders.shipmentPrice >0}">
                            <p>Phí vận chuyển: 
                                <span style="display: inline-block; float: right; text-align: right"> 
                                    ${text:numberFormat(orders.shipmentPrice)} <sup class="u-price">đ</sup>
                                </span>
                            </p>

                        </c:if>

                        <p>Tổng giá trị: <span style="display: inline-block; float: right; text-align: right">
                                ${text:numberFormat(orders.totalPrice)} <sup class="u-price">đ</sup> 
                            </span></p>
                        <p>Thanh toán: <span style="display: inline-block; float: right; text-align: right">
                                ${text:numberFormat(orders.finalPrice)} <sup class="u-price">đ</sup> 
                            </span></p>
                        <hr>


                        <p><strong>Hình thức thanh toán</strong>: <span style="display: inline-block; float: right; text-align: right">
                                <c:choose>
                                    <c:when test="${orders.paymentMethod == 'NL'}">
                                        Ví ngân lương
                                    </c:when>
                                    <c:when test="${orders.paymentMethod == 'COD'}">
                                        Thu tiền tại nhà
                                    </c:when>
                                    <c:when test="${orders.paymentMethod == 'NONE'}">
                                        Tự liên hệ
                                    </c:when>
                                    <c:when test="${orders.paymentMethod == 'VISA' || orders.paymentMethod == 'MASTER'}">
                                        Thẻ visa
                                    </c:when>
                                    <c:otherwise>
                                        Ngân hàng nội địa
                                    </c:otherwise>
                                </c:choose></span>
                        </p>

                        <p><strong>Hình thức vận chuyển</strong>:<span style="display: inline-block; float: right; text-align: right">
                                <c:choose>
                                    <c:when test="${orders.shipmentService == 'SLOW'}">
                                        Tiết kiệm (5-7 ngày)
                                    </c:when>
                                    <c:when test="${orders.shipmentService == 'FAST'}">
                                        Nhanh (2-3 ngày)
                                    </c:when>
                                    <c:when test="${orders.shipmentService == 'RAPID'}">
                                        Hỏa tốc (dưới 2h)
                                    </c:when>
                                    <c:otherwise>
                                        Tự liên hệ
                                    </c:otherwise>
                                </c:choose></span>
                        </p>
                        <p><strong>Trạng thái vận đơn:</strong><span style="display: inline-block; float: right; text-align: right">
                                <c:choose>
                                    <c:when test="${(orders.shipmentStatus == 'NEW' || orders.shipmentStatus == 'STOCKING')}">
                                        Chưa chuyển hàng
                                    </c:when>
                                    <c:when test="${orders.shipmentStatus == 'DELIVERING'}">
                                        Hàng đã xuất kho
                                    </c:when>
                                    <c:when test="${orders.shipmentStatus == 'DELIVERED'}">
                                        Đã chuyển hàng
                                    </c:when>
                                    <c:when test="${orders.shipmentStatus == 'RETURN'}">
                                        Chuyển hoàn
                                    </c:when>
                                    <c:when test="${orders.shipmentStatus == 'DENIED'}">
                                        Đã hủy
                                    </c:when>
                                    <c:otherwise>
                                        Chưa tạo vận đơn
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        </p>
                        <p><strong>Trạng thái thanh toán:</strong><span style="display: inline-block; float: right; text-align: right">
                                <c:if test="${orders.paymentStatus!='PAID'}">
                                    <span class="label label-danger">Chưa thanh toán</span>
                                </c:if>
                                <c:if test="${orders.paymentStatus=='PAID'}">
                                    <span class="label label-success">Đã thanh toán</span>
                                </c:if>
                            </span>
                        </p>

                    </td>

                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div style="margin-top: 10px; margin-bottom: 10px;">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>