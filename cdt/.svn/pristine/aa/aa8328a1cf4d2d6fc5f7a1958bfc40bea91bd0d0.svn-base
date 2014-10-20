<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách thư
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="search" cssClass="form-vertical" method="POST" role="form" style="margin-top: 20px;">        
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="phone" type="text" class="form-control" placeholder="Số điện thoại gửi"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="receiver" type="text" class="form-control" placeholder="Đầu số"/>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select  path="success" class="form-control">
                    <form:option value="0">-- Trạng thái --</form:option>
                    <form:option value="1">Thành công</form:option>
                    <form:option value="2">Thất bại</form:option>
                </form:select>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select  path="type" class="form-control">
                    <form:option value="">-- Loại tin nhắn --</form:option>
                    <form:option value="XM">Tin nhắn xác minh</form:option>
                    <form:option value="NAP">Tin nhắn nạp xèng</form:option>
                    <form:option value="UP">Tin nhắn up tin</form:option>
                    <form:option value="TK">Tin nhắn lấy lại mật khẩu</form:option>
                </form:select>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="timeFrom" type="hidden" class="form-control timeFrom" placeholder="Ngày gửi từ"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="timeTo" type="hidden" class="form-control timeTo" placeholder="Đến ngày"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc thông tin</button>
                <button onclick="smsInbox.reset();" class="btn btn-primary"><span class="glyphicon glyphicon-refresh"></span> Reset</button>
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
            <th class="text-center">SĐT gửi</th>
            <th class="text-center">Nội dung</th>
            <th class="text-center">Đầu số</th>
            <th class="text-center">Loại SMS</th>
            <th class="text-center">Thời gian gửi</th>
            <th class="text-center">Trạng thái</th>
        </tr>        
        <jsp:useBean id="date" class="java.util.Date"></jsp:useBean>
        <c:if test="${page.pageCount > 0 }">
            <c:forEach var="sms" items="${page.data}">
                <tr <c:if test="${sms.success}">class="success"</c:if> <c:if test="${!sms.success}">class="danger"</c:if> >
                        <td class="text-center" style="vertical-align: middle">
                        ${sms.phone}
                    </td>
                    <td class="text-center" style="vertical-align: middle">${sms.message}</td>
                    <td class="text-center" style="vertical-align: middle"><b>${sms.receiver}</b></td>
                    <td class="text-center" style="vertical-align: middle">
                        <c:if test="${sms.type == 'NAP'}">
                            Nạp xèng
                        </c:if>
                        <c:if test="${sms.type == 'XM'}">
                            Xác minh tài khoản
                        </c:if>
                        <c:if test="${sms.type == 'UP'}">
                            Up tin
                        </c:if>
                        <c:if test="${sms.type == 'TK'}">
                            Lấy lại mật khẩu
                        </c:if>
                    </td>
                    <td class="text-center" style="vertical-align: middle"> 
                        <jsp:setProperty name="date" property="time" value="${sms.createTime}" />
                        <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                        </td>
                        <td class="text-center" style="vertical-align: middle">
                        ${sms.success?'<p class="label label-success">Thành công</p>':'<p class="label label-danger">Thất bại</p>'}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${page.pageCount <= 0 }">
            <tr>
                <td colspan="6" class="text-danger text-center">Không tìm thấy Sms nào!</td> 
            </tr> 
        </c:if>
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