<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách thư ChợĐiệnTử
        </a>
    </li>
</ul>
<div class="func-container"> 
    <form:form modelAttribute="emailOutboxSearch" cssClass="form-vertical" method="POST" role="form" style="margin-top: 20px;">        
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="from" type="text" class="form-control" placeholder="Địa chỉ email người gửi"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="to" type="text" class="form-control" placeholder="Địa chỉ email người nhận"/>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select  path="type" class="form-control">
                    <form:option value="0">-- Loại email --</form:option>
                    <form:option value="1">Xác nhận email</form:option>
                    <form:option value="2">Lấy lại mật khẩu</form:option>
                    <form:option value="3">Gửi mật khẩu mới</form:option>
                </form:select>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="timeFrom" type="hidden" class="form-control timeFrom" placeholder="Ngày yêu cầu gửi từ"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="timeTo" type="hidden" class="form-control timeTo" placeholder="Ngày yêu cầu gửi đến"/>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select  path="sent" class="form-control">
                    <form:option value="0">-- Trạng thái --</form:option>
                    <form:option value="1">Đã gửi</form:option>
                    <form:option value="2">Đang chờ</form:option>
                </form:select>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="sentTimeFrom" type="hidden" class="form-control sentTimeFrom" placeholder="Ngày gửi từ"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="sentTimeTo" type="hidden" class="form-control sentTimeTo" placeholder="Ngày gửi đến"/>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select  path="success" class="form-control">
                    <form:option value="0">-- Thành công --</form:option>
                    <form:option value="1">Gửi thành công</form:option>
                    <form:option value="2">Gửi thất bại</form:option>
                </form:select>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc thông tin</button>
            </div>
        </div>
    </form:form>
    <div class="cms-line"></div>
    <div class="clearfix"></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${page.dataCount} </strong> kết quả <strong>${page.pageCount}</strong> trang.
        </h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${page.pageIndex}"/>
                <jsp:param name="pageCount" value="${page.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center">Người gửi</th>
            <th class="text-center">Người nhận</th>
            <th class="text-center">Loại email</th>
            <th class="text-center">Tiêu đề</th>
            <th class="text-center">Đã gửi</th>
            <th class="text-center">Thành công</th>
            <th class="text-center" style="width: 200px" > Chức năng </th>
        </tr>        
        <c:forEach var="item" items="${page.data}">   
            <jsp:useBean id="date" class="java.util.Date" />
            <tr class="${!item.success?'danger':'success'}" >
                <td class="text-center">
                    <p>${item.from}</p>
                    <p>
                        <jsp:setProperty name="date" property="time" value="${item.time}" />
                        <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                        </p>
                    </td>
                    <td class="text-center" style="vertical-align: middle"  >
                        <p>${item.to}</p>
                    <p><c:if test="${item.sentTime > 0}">
                            <jsp:setProperty name="date" property="time" value="${item.sentTime}" />
                            <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                        </c:if></p>
                </td>
                <td class="text-center" style="vertical-align: middle" >
                    <p>
                        <c:choose>
                            <c:when test="${item.type == 'VERIFY'}" >Xác nhận email</c:when>
                            <c:when test="${item.type == 'NEWPASSWORD'}" >Gửi mật khẩu mới</c:when>
                            <c:when test="${item.type == 'SHOP_CONTACT'}" >Liên hệ shop</c:when>
                            <c:when test="${item.type == 'EMAIL_MARKETING'}" >Email quảng cáo</c:when>
                            <c:when test="${item.type == 'CREATE_ORDER_BUYER'}" >Tạo đơn hàng của người bán</c:when>
                            <c:when test="${item.type == 'CREATE_ORDER_SELLER'}" >Tạo đơn hàng của người mua</c:when>
                            <c:when test="${item.type == 'TOPUP_CARD_TEL'}" >Mua thẻ điện thoại</c:when>
                            <c:when test="${item.type == 'TOPUP_TEL'}" >Nạp điện thoại</c:when>
                            <c:otherwise>${item.type}</c:otherwise>
                        </c:choose>
                    </p>
                </td>
                <td class="text-center"  style="vertical-align: middle" >
                    ${item.subject}
                </td>
                <td class="text-center"  style="vertical-align: middle" >
                    ${!item.sent?'<i class="glyphicon glyphicon-unchecked"></i>':'<i class="glyphicon glyphicon-check"></i>'}
                </td>
                <td class="text-center" style="vertical-align: middle"  >
                    ${!item.success?'<i class="glyphicon glyphicon-unchecked"></i>':'<i class="glyphicon glyphicon-check"></i>'}
                </td>
                <td class="text-center"  style="vertical-align: middle" >
                    <div class="btn-group">
                        <button title="Chi tiết" type="button" class="btn btn-info" onclick="emailOutbox.get('${item.id}');" >
                            <span class="glyphicon glyphicon-export"></span>
                        </button>
                        <c:if test="${!item.success && item.response != null}">
                            <button title="Lỗi gửi mail" type="button" class="btn btn-danger"  onclick="emailOutbox.response('${item.id}');" >
                                <span class="glyphicon glyphicon-tag"></span>
                            </button>
                        </c:if>
                        <button title="Gửi lại" type="button" class="btn btn-success"  onclick="emailOutbox.reSent('${item.id}');" >
                            <span class="glyphicon glyphicon-refresh"></span>
                        </button>
                    </div>
                </td>
            </tr>      
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${page.pageIndex}"/>
                <jsp:param name="pageCount" value="${page.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>