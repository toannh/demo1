<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách tin nhắn
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="search" method="POST" role="form" style="margin-top: 20px;">
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="toEmail" type="text" class="form-control" placeholder="Email người nhận"/>
            </div>
            <div class="form-group">
                <form:input path="toUserId" type="text" class="form-control" placeholder="Mã người nhận"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="fromEmail" type="text" class="form-control" placeholder="Email người gửi"/>
            </div>
            <div class="form-group">
                <form:input path="fromUserId" type="text" class="form-control" placeholder="Mã người gửi"/>
            </div>
        </div>

        <div class="col-sm-9 col-sm-offset-3">
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>Lọc </button>
            <button type="button" class="btn btn-default" onclick="message.reset();"><span class="glyphicon glyphicon-refresh"></span> Nhập lại</button>
            <button type="button" class="btn btn-success" onclick="message.cdtSendMail();"><span class="glyphicon glyphicon-send"></span> Gửi email</button>
        </div>
    </form:form>
    <div class="clearfix"></div>
    <div class="cms-line" style="margin-top: 10px;" ></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left alert alert-success" style="padding: 10px; width: 33%;" >Tìm thấy tổng số <span style="color: tomato; font-weight: bolder">${messagePage.dataCount} </span> tin nhắn trong <span style="color: tomato; font-weight: bolder">${messagePage.pageCount}</span> trang.</h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${messagePage.pageIndex}"/>
                <jsp:param name="pageCount" value="${messagePage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center" style="vertical-align: middle">Tiêu đề</th>
            <th class="text-center" style="vertical-align: middle">Người gửi</th>
            <th class="text-center" style="vertical-align: middle">Người nhận</th>
            <th class="text-center" style="vertical-align: middle">Ngày gửi</th>
        </tr>
        <c:if test="${messagePage.dataCount <= 0}">
            <tr>
                <td colspan="5" class="text-center" style="vertical-align: middle;color: red">Chưa có tin nhắn nào!</td>
            </tr>
        </c:if>
        <c:if test="${messagePage.dataCount > 0}">
            <jsp:useBean id="date" class="java.util.Date" />
            <c:forEach var="mess" items="${messagePage.data}">
                <tr>
                    <td class="text-center" style="vertical-align: middle">${mess.subject}</td>
                    <td  style="vertical-align: middle">
                        <p><b>Mã</b>:${mess.fromUserId}</p> 
                        <p><b>Email</b>:${mess.fromEmail}</p> 
                        <p><b>Tên</b>: ${mess.fromName}</p> 
                    </td>
                    <td style="vertical-align: middle">
                        <p><b>Mã</b>:${mess.toUserId}</p> 
                        <p><b>Email</b>:${mess.toEmail}</p> 
                        <p><b>Tên</b>: ${mess.toName}</p> 
                    </td>
                    <td class="text-center" style="vertical-align: middle">
                        <p>
                            <jsp:setProperty name="date" property="time" value="${mess.createTime}" />
                            <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                            </p>
                        </td>
                    </tr>
            </c:forEach>
        </c:if>
    </table>

    <div style="margin-top: 10px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${messagePage.pageIndex}"/>
                <jsp:param name="pageCount" value="${messagePage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
