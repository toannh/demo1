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
            Danh sách lịch sử Thưởng - Phạt xèng
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="cashHistorySearch" method="POST" role="form" style="margin-top: 20px;">
        <div class="col-sm-4">
            <div class="form-group">
                <form:hidden path="startTime"  class="form-control timeselect" placeholder="Ngày tạo từ"/>
            </div>
            <div class="form-group">
                <form:hidden path="endTime"  class="form-control timeselect" placeholder="Đến ngày"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="objectId" type="text" class="form-control" placeholder="Đối tượng"/>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="admin" type="text" class="form-control" placeholder="Người duyệt"/>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="form-group">
                <select name='type' class='form-control'>
                    <option value="">--Tất cả--</option>
                    <option value="COMMENT_MODEL_REWARD" ${cashHistorySearch.type == 'COMMENT_MODEL_REWARD'?'selected':''}>Đánh giá model</option>
                    <option value="SELLER_POST_NEWS" ${cashHistorySearch.type == 'SELLER_POST_NEWS'?'selected':''} >Viết bài tin tức</option>
                    <option value="COMMENT_ITEM_REWARD" ${cashHistorySearch.type == 'COMMENT_ITEM_REWARD'?'selected':''} >Đánh giá sản phẩm</option>
                    <option value="VIEW_PAGE" ${cashHistorySearch.type == 'VIEW_PAGE'?'selected':''} >Xem trang</option>
                    <option value="SIGNIN" ${cashHistorySearch.type == 'SIGNIN'?'selected':''} >Đăng nhập</option>
                    <option value="REGISTER" ${cashHistorySearch.type == 'REGISTER'?'selected':''} >Đăng ký</option>
                    <option value="SELLER_CREATE_PROMOTION" ${cashHistorySearch.type == 'SELLER_CREATE_PROMOTION'?'selected':''} >Tạo khuyến mãi</option>
                    <option value="EMAIL_VERIFIED" ${cashHistorySearch.type == 'EMAIL_VERIFIED'?'selected':''} >Kích hoạt Mail</option>
                    <option value="PHONE_VERIFIED" ${cashHistorySearch.type == 'PHONE_VERIFIED'?'selected':''} >Kích hoạt Phone</option>
                    <option value="OPEN_SHOP" ${cashHistorySearch.type == 'OPEN_SHOP'?'selected':''} >Mở shop</option>
                    <option value="BROWSE_LADING" ${cashHistorySearch.type == 'BROWSE_LADING'?'selected':''} >Duyệt vận đơn</option>
                    <option value="SELLER_POST_ITEM" ${cashHistorySearch.type == 'SELLER_POST_ITEM'?'selected':''} >Đăng bán</option>
                    <option value="PAYMENT_SUSSESS_NL" ${cashHistorySearch.type == 'PAYMENT_SUSSESS_NL'?'selected':''} >Thanh toán qua ngân lượng</option>
                    <option value="PAYMENT_SUSSESS_COD" ${cashHistorySearch.type == 'PAYMENT_SUSSESS_COD'?'selected':''} >Thanh toán qua COD</option>
                </select>
            </div>
        </div>
                
        <div class="col-sm-8">
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc</button>
            <button type="button" class="btn btn-default" onclick="cashhistory.resetForm()"><span class="glyphicon glyphicon-refresh"></span> Nhập lại</button>
            <button type="button" class="btn btn-success" onclick="cashhistory.exportEx()"><span class="glyphicon glyphicon-list"></span> Xuất Excel</button>
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
            <th>Người thao tác</th>
            <th>Kiểu</th>
            <th>Số xèng</th>
            <th>Thời gian</th>
            <th>Đối tượng</th>
            <th>Ghi chú</th>
            <th>Message</th>
            <th>Người duyệt</th>
            <th>Trạng thái</th>
            <th>Chức năng</th>
        </tr>

        <c:if test="${dataPage.dataCount > 0}">
            <c:forEach var="cash" items="${dataPage.data}" varStatus="i">
                <jsp:useBean id="date" class="java.util.Date" />
                <tr rel-data-line="${cash.id}" class="text-center ${cash.fine && !cash.unAppro ? "danger" : ""}" >
                    <td>${i.index + 1}</td>
                    <td rel="${cash.userId}" id='getText'></td>
                    <td>
                        <a href="${baseUrl}${cash.url}" target="_blank">
                            <c:choose>
                                <c:when test="${cash.type == 'COMMENT_MODEL_REWARD'}">Đánh giá model</c:when>
                                <c:when test="${cash.type == 'COMMENT_ITEM_REWARD'}">Đánh giá sản phẩm</c:when>
                                <c:when test="${cash.type == 'SELLER_POST_NEWS'}">Viết bài tin tức</c:when>
                                <c:when test="${cash.type == 'VIEW_PAGE'}">Xem trang</c:when>
                                <c:when test="${cash.type == 'SIGNIN'}">Đăng nhập</c:when>
                                <c:when test="${cash.type == 'REGISTER'}">Đăng ký</c:when>
                                <c:when test="${cash.type == 'SELLER_CREATE_PROMOTION'}">Tạo khuyến mãi</c:when>
                                <c:when test="${cash.type == 'EMAIL_VERIFIED'}">Kích hoạt Mail</c:when>
                                <c:when test="${cash.type == 'PHONE_VERIFIED'}">Kích hoạt Phone</c:when>
                                <c:when test="${cash.type == 'OPEN_SHOP'}">Mở shop</c:when>
                                <c:when test="${cash.type == 'BROWSE_LADING'}">Duyệt vận đơn</c:when>
                                <c:when test="${cash.type == 'PAYMENT_SUSSESS_NL'}">Thanh toán qua ngân lượng</c:when>
                                <c:when test="${cash.type == 'PAYMENT_SUSSESS_COD'}">Thanh toán qua COD</c:when>
                                <c:when test="${cash.type == 'SELLER_POST_ITEM'}">Đăng bán</c:when>
                                <c:otherwise>Khác</c:otherwise>
                            </c:choose>
                        </a>
                    </td>
                    <td>${text:numberFormat(cash.balance)}</td>
                    <td>
                        <p>   
                            <jsp:setProperty name="date" property="time" value="${cash.createTime}" />
                            <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                            </p>
                        </td>
                        <td class="col-xs-2">${cash.objectId}</td>
                    <td class="col-xs-1" rel-data-note="${cash.id}">${cash.note}</td>
                    <td class="col-xs-1">${cash.message}</td>
                    <td rel-data-admin="${cash.id}">${cash.admin}</td>
                    <td rel-data-btn="${cash.id}">
                        <c:choose>
                            <c:when test="${cash.fine}"><label class="label label-danger">Phạt xèng</label></c:when>
                            <c:when test="${cash.unAppro}"><label class="label label-danger">Không duyệt</label></c:when>
                            <c:when test="${!cash.unAppro}"><label class="label label-success">Đã duyệt</label></c:when>
                        </c:choose>
                    </td>
                    <td rel-data-btn2="${cash.id}">
                        <c:choose>
                            <c:when test="${cash.type == 'COMMENT_MODEL_REWARD' || cash.type == 'COMMENT_ITEM_REWARD' || cash.type == 'SELLER_POST_ITEM'}">
                                <c:if test="${!cash.unAppro && !cash.fine}">
                                    <button  type="button" class="btn btn-warning" style="width: 100px;" onclick="cashhistory.unAppro('${cash.id}');">Không Duyệt</button>
                                    <button  type="button" class="btn btn-danger" style="width: 100px; margin-top: 10px;"  onclick="cashhistory.fine('${cash.id}');">Phạt xèng</button>
                                </c:if>
                            </c:when>
                            <c:when test="${cash.type == 'COMMENT_ITEM_REWARD' || cash.type == 'PAYMENT_SUSSESS_NL' || cash.type == 'SELLER_CREATE_PROMOTION'}">
                                <c:if test="${!cash.fine}">
                                    <button type="button" style="width: 100px;" onclick="cashhistory.fine('${cash.id}');" class="btn btn-danger">Phạt xèng</button>
                                </c:if>
                            </c:when>
                        </c:choose>
                    </td>

                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${dataPage.dataCount <= 0}">
            <tr><td colspan="11" class='text-center text-danger'>Không tìm thấy dữ liệu tương ứng</td></tr>
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