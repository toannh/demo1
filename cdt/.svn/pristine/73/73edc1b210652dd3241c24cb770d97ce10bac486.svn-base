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
            Danh sách quản lý sản phẩm đang quan tâm của người dùng
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="crawlSearch" method="POST" role="form" style="margin-top: 20px;">
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
                <form:input path="itemId" type="text" class="form-control" placeholder="Mã sản phẩm"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <select name="type" class='form-control'>
                    <option value="">--Tất cả--</option>
                    <option value="CREATE" ${crawlSearch.type == 'CREATE' ? 'selected' : ''}>Được khởi tạo</option>
                </select>
            </div>
        </div>
        <div class="col-sm-4">
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc</button>
            <button type="button" class="btn btn-default" onclick="itemcrawl.resetForm()"><span class="glyphicon glyphicon-refresh"></span> Nhập lại</button>
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
            <th>Mã sản phẩm</th>
            <th>Ngày tạo</th>
            <th>Kiểu</th>
        </tr>

        <c:if test="${dataPage.dataCount > 0}">
            <jsp:useBean id="date" class="java.util.Date" />
            <c:forEach items="${dataPage.data}" varStatus="i" var="itcrawl">
                <tr class="text-center">
                    <td>${i.index + 1}</td>
                    <td>${itcrawl.itemId}</td>
                    <td>
                        <jsp:setProperty name="date" value="${itcrawl.createTime}" property="time"/>
                        <fmt:formatDate value="${date}" pattern="HH:mm dd/MM/yyyy" type="date"></fmt:formatDate>
                        </td>
                        <td>${itcrawl.type == "CREATE" ? "Được khởi tạo" : "Chưa được khởi tạo"}</td>
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