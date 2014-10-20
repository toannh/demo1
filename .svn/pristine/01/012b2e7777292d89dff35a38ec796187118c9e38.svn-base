<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách quản lý nạp thẻ điện thoại
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="topUpSearch" method="POST" role="form" style="margin-top: 20px;">
        <div class="col-sm-4">
            <div class="form-group">
                <form:hidden path="createTimeFrom"  class="form-control timeselect" placeholder="Ngày tạo từ"/>
            </div>

            <div class="form-group">
                <form:hidden path="createTimeTo"  class="form-control timeselect" placeholder="Đến ngày"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="phone" type="text" class="form-control" placeholder="Số điện thoại"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="requestId" type="text" class="form-control" placeholder="Mã yêu cầu"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <select name="type" class='form-control'>
                    <option value="">--Tất cả--</option>
                    <option value="buyCardTelco" ${topUpSearch.type == 'buyCardTelco' ? 'selected' : ''}>Mua thẻ</option>
                    <option value="topupTelco" ${topUpSearch.type == 'topupTelco' ? 'selected' : ''}>Nạp thẻ</option>
                </select>
            </div>
        </div>
        <div class="col-sm-4">
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc</button>
            <button type="button" class="btn btn-default" onclick="topup.resetForm()"><span class="glyphicon glyphicon-refresh"></span> Nhập lại</button>
        </div>
    </form:form>
    <div class="clearfix"></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${dataPage.dataCount} </strong> kết quả <strong>${dataPage.pageCount}</strong> trang.
        </h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr class="text-center">
            <th>STT</th>
            <th>Email</th>
            <th>Mã yêu cầu</th>
            <th>Mã lỗi</th>
            <th>Kiểu thanh toán</th>
            <th>Mệnh giá</th>
            <th>Số điện thoại</th>
            <th>Ngày khởi tạo</th>
            <th>Ngày chỉnh sửa</th>
            <th>Trạng thái</th>
            <th>Chức năng</th>
        </tr>

        <c:if test="${dataPage.dataCount > 0}">
            <c:forEach var="topup" items="${dataPage.data}" varStatus="i">
                <jsp:useBean id="date" class="java.util.Date" />
                <tr class="text-center ${i.index % 2 == 0 ? 'success' : 'primary'}">
                    <td>${i.index + 1}</td>
                    <td rel="${topup.userId}"></td>
                    <td>${topup.requestId}</td>
                    <td>${topup.errorCode}</td>
                    <td>${topup.type == 'buyCardTelco' ? 'Mua thẻ' : 'Nạp thẻ' }</td>
                    <td><c:if test="${topup.amount != ''}">${text:numberFormat(topup.amount)} VNĐ</c:if></td>
                    <td>${topup.phone}</td>
                    <td>
                        <p>
                            <jsp:setProperty name="date" property="time" value="${topup.createTime}"/>
                            <fmt:formatDate value="${date}" type="date" pattern="HH:mm dd/MM/yyyy"></fmt:formatDate>
                            </p>
                        </td>
                        <td>
                            <p>
                            <jsp:setProperty name="date" property="time" value="${topup.updateTime}"/>
                            <fmt:formatDate value="${date}" type="date" pattern="HH:mm dd/MM/yyyy"></fmt:formatDate>
                            </p>
                        </td>
                        <td>
                        <c:choose>
                            <c:when test="${topup.success}"><label class="label label-success">${topup.message}</label></c:when>
                            <c:when test="${!topup.success}"><label class="label label-danger">${topup.message}</label></c:when>
                        </c:choose>
                    </td>
                    <c:if test="${topup.type == 'buyCardTelco'}">
                        <td><button type="button"  onclick="topup.view('${topup.id}');" class="btn btn-primary">Xem</button></td>
                    </c:if>
                    <c:if test="${topup.type == 'topupTelco'}">
                        <td></td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${dataPage.dataCount <= 0}">
            <tr><td colspan="10" class='text-center text-danger'>Không tìm thấy dữ liệu tương ứng</td></tr>
        </c:if>
    </table>
    <div style="margin-top: 10px">
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>