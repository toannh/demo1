<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a><i class="fa fa-tags"></i> Danh sách vận đơn</a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="ladingSearch" role="form" style="margin-top:20px;">
        <div class="col-sm-3">
            <div class="form-group">
                <form:input path="scId" type="text" class ="form-control" placeholder="Mã vận đơn"/>
            </div>
            <div class="form-group">
                <form:input path="sellerPhone" type="text" class ="form-control" placeholder="Phone người gửi"/>
            </div>
            <div class="form-group">
                <form:input path="sellerName" type="text" class ="form-control" placeholder="Tên người gửi"/>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <form:input path="receiverEmail" type="text" class ="form-control" placeholder="Email người nhận"/>
            </div>
            <div class="form-group">
                <form:input path="receiverPhone" type="text" class ="form-control" placeholder="Phone người nhận"/>
            </div>
            <div class="form-group">
                <form:input path="receiverName" type="text" class ="form-control" placeholder="Tên người nhận"/>
            </div>
        </div>

        <div class="col-sm-3">
            <div class="form-group">
                <form:input path="timeFrom" type="hidden" class ="form-control timestamp" placeholder="Ngày tạo từ"/>
            </div>
            <div class="form-group">
                <form:input path="timeTo" type="hidden" class ="form-control timestamp" placeholder="Ngày tạo đến"/>
            </div>
        </div>
        <div class="col-sm-3">

            <div class="form-group">
                <form:select path="shipmentStatus" class="form-control">
                    <form:option value="0" label="Trạng thái vận đơn"/>
                    <form:option value="1" label="Chưa duyệt"/>
                    <form:option value="2" label="Chưa lấy hàng"/>
                    <form:option value="3" label="Đang giao hàng"/>
                    <form:option value="4" label="Chuyển hoàn"/>
                    <form:option value="5" label="Đã hủy"/>
                    <form:option value="6" label="Hàng đã tới tay người mua"/>
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
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${text:numberFormat(dataPage.dataCount)} </strong> kết quả <strong>${text:numberFormat(dataPage.pageCount)}</strong> trang.
        </h5> 

        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <c:forEach items="${allPrice}" var="temp">
        <h5>${temp.key}:  <strong class="clr-red"> ${text:numberFormat(temp.value)}</strong> <span class="u-price">đ</span> </h5>
    </c:forEach>
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center; ">STT</th>
            <th style="vertical-align: middle; text-align: center; ">Thông tin vận đơn</th>
            <th style="vertical-align: middle; text-align: center; ">Hàng hóa</th>            
        </tr>     
        <jsp:useBean id="date" class="java.util.Date" />
        <c:forEach items="${dataPage.data}" var="lading" varStatus="ladingStatus">
            <tr>
                <td>${ladingStatus.index+1}</td>
                <td>
                    <p>Mã VĐ: <a href="http://mc.shipchung.vn/van-don.html?sc_code=${lading.scId}" target="_blank">${lading.scId}</a></p>
                    <p style="text-align: center;font-weight: bold">Thông tin người gửi/nhận</p>
                    <div style="text-align: left;border-right:1px dashed rgb(185, 185, 187);" class="col-xs-6 col-sm-6">
                        <p>Tên người gửi: ${lading.sellerName}</p>
                        <p>Phone: ${lading.sellerPhone}</p>
                        <p>Địa chỉ: ${lading.sellerAddress}</p>
                    </div>
                    <div style="text-align: left;" class="col-xs-6 col-sm-6">
                        <p>Tên người nhận: ${lading.receiverName}</p>
                        <p>Email: ${lading.receiverEmail}</p>
                        <p>Phone: ${lading.receiverPhone}</p>
                        <p>Địa chỉ: ${lading.receiverAddress}</p>

                    </div>
                </td>
                <td>
                    <p>Tên hàng hóa:  ${lading.name}</p>  
                    <p>Số lượng:  ${lading.shipmentPCod}  </p>
                    <p>Khối lượng:  ${lading.weight} gram  </p>
                    <p>Giá trị:  ${text:numberFormat(lading.shipmentPrice)} <sup class="u-price">đ</sup></p>
                    <p>Mô tả:  ${lading.description} </p> 
                    <p><strong>Hình thức vận chuyển</strong>:
                        <c:choose>
                            <c:when test="${lading.shipmentService == 'SLOW'}">
                                Tiết kiệm (5-7 ngày)
                            </c:when>
                            <c:when test="${lading.shipmentService == 'FAST'}">
                                Nhanh (2-3 ngày)
                            </c:when>
                            <c:when test="${lading.shipmentService == 'RAPID'}">
                                Hỏa tốc (dưới 2h)
                            </c:when>
                            <c:otherwise>
                                Tự liên hệ
                            </c:otherwise>
                        </c:choose>
                    </p>
                    <p><strong>Trạng thái:  </strong>
                        <c:choose>
                            <c:when test="${(lading.shipmentStatus == 'NEW' || lading.shipmentStatus == 'STOCKING')}">
                                Chưa chuyển hàng
                            </c:when>
                            <c:when test="${lading.shipmentStatus == 'DELIVERING'}">
                                Hàng đã xuất kho
                            </c:when>
                            <c:when test="${lading.shipmentStatus == 'DELIVERED'}">
                                Đã chuyển hàng
                            </c:when>
                            <c:when test="${lading.shipmentStatus == 'RETURN'}">
                                Chuyển hoàn
                            </c:when>
                            <c:when test="${lading.shipmentStatus == 'DENIED'}">
                                Đã hủy
                            </c:when>
                            <c:otherwise>
                                Chưa tạo vân đơn
                            </c:otherwise>
                        </c:choose>
                    </p> 

                </td>
            </tr> 
        </c:forEach>

    </table>
    <div style="margin-top: 10px; margin-bottom:20px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div><div class="clearfix"></div>
    </div>
</div>