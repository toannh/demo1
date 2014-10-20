<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách tin nhắn ChoDienTu
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="smsOutboxSearch" cssClass="form-vertical" method="POST" role="form" style="margin-top: 20px;">        
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="receiver" type="text" class="form-control" placeholder="Người nhận"/>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select  path="type" class="form-control">
                    <form:option value="0">-- Loại sms --</form:option>
                    <form:option value="1">Đơn hàng mới</form:option>
                    <form:option value="2">Đấu giá thắng</form:option>
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
    <div class="clearfix"></div>
    <div class="cms-line" style="margin-top: 10px;" ></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left alert alert-success" style="padding: 10px; width: 33%;" >Tìm thấy tổng số <span style="color: tomato; font-weight: bolder">${page.dataCount} </span> người dùng trong <span style="color: tomato; font-weight: bolder">${page.pageCount}</span> trang.</h5>            
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
            <th class="text-center">Ngày yêu cầu</th>
            <th class="text-center">Người nhận</th>
            <th class="text-center">Loại SMS</th>
            <th class="text-center">Đã gửi</th>
            <th class="text-center">Thành công</th>
            <th class="text-center"> Chức năng </th>
        </tr>        
        <c:forEach var="item" items="${page.data}">   
            <jsp:useBean id="date" class="java.util.Date" />
            <tr>
                <td class="text-center">
                    <p>
                        <jsp:setProperty name="date" property="time" value="${item.time}" />
                        <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                        </p>
                    </td>
                    <td class="text-center" style="vertical-align: middle"  >
                        <p>${item.receiver}</p>
                    <p><c:if test="${item.sentTime > 0}">
                            <jsp:setProperty name="date" property="time" value="${item.sentTime}" />
                            <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                        </c:if></p>
                </td>
                <td class="text-center" style="vertical-align: middle" >
                    <c:choose>
                        <c:when test="${item.type=='CREATE_ORDER_SELLER'}">
                            Tạo đơn hàng người bán
                        </c:when>
                        <c:when test="${item.type=='SMS_MARKETING'}">
                            SMS MARKETING
                        </c:when>
                        <c:otherwise>
                            ${item.type}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="text-center"  style="vertical-align: middle" >
                    ${!item.sent?'<i class="glyphicon glyphicon-unchecked"></i>':'<i class="glyphicon glyphicon-check"></i>'}</td>
                <td class="text-center" style="vertical-align: middle"  >
                    ${!item.success?'<i class="glyphicon glyphicon-unchecked"></i>':'<i class="glyphicon glyphicon-check"></i>'}</td>
                <td class="text-center"  style="vertical-align: middle" >
                    <div class="btn-group">
                        <button type="submit" class="btn btn-success"  onclick="smsOutbox.reSent('${item.id}');" >
                            <span class="glyphicon glyphicon-refresh"></span> Gửi lại</button>
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