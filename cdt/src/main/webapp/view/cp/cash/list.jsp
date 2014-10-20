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
            Danh sách tài khoản xèng
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="cpCashSearch" method="POST" role="form" style="margin-top: 20px;">
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="userId" type="text" class="form-control" placeholder="Mã người dùng"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="email" type="text" class="form-control" placeholder="Nhập Email"/>
            </div>
        </div>
        <div class="col-sm-4">
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc</button>
            <button type="button" class="btn btn-default" onclick="news.resetForm()"><span class="glyphicon glyphicon-refresh"></span> Nhập lại</button>
        </div>
    </form:form>
    <div class="cms-line"></div>
    <div class="clearfix"></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${cashSearchs.dataCount} </strong> kết quả <strong>${cashSearchs.pageCount}</strong> trang.
        </h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${cashSearchs.pageIndex}"/>
                <jsp:param name="pageCount" value="${cashSearchs.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th>ID</th>
            <th>Người dùng</th>
            <th>Email</th>
            <th>Số xèng</th>

        </tr>
        <c:forEach var="cash" items="${cashSearchs.data}">

            <c:forEach var="user" items="${users}">
                <c:if test="${user.id==cash.userId}">
                    <tr>
                        <td>${cash.userId}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</th>
                        <td style="color: red">${text:numberFormat(cash.balance)}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </c:forEach>
    </table>
    <div style="margin-top: 10px">
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${cashSearchs.pageIndex}"/>
                <jsp:param name="pageCount" value="${cashSearchs.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>