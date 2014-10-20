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
            Danh sách lịch sử Khóa Tài Khoản
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="userLockSearch" method="POST" role="form" style="margin-top: 20px;">
        <div class="col-sm-4">
            <div class="form-group">
                <form:hidden path="startTime"  class="form-control timeselect" placeholder="Ngày bắt đầu"/>
            </div>

            <div class="form-group">
                <form:hidden path="endTime"  class="form-control timeselect" placeholder="Ngày kết thúc"/>
            </div>
        </div>

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
                <form:input path="userId" type="text" class="form-control" placeholder="Mã người dùng"/>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="form-group">
                <select name="done" class='form-control'>
                    <option value="0">--Tất cả--</option>
                    <option value="1" ${userLockSearch.done == 1 ? 'selected' : ''}>Mở khóa</option>
                </select>
            </div>
        </div>

        <div class="col-sm-4">
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc</button>
            <button type="button" class="btn btn-default" onclick="userlock.resetForm()"><span class="glyphicon glyphicon-refresh"></span> Nhập lại</button>
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
        <jsp:useBean id="date" class="java.util.Date" />
        <tr class="text-center">
            <th>STT</th>
            <th>Mã đối tượng</th>
            <th>Người duyệt</th>
            <th>Ghi chú</th>
            <th>Ngày tạo</th>
            <th>Ngày chỉnh sửa</th>
            <th>Ngày bắt đầu</th>
            <th>Ngày kết thúc</th>
            <th>Trạng thái</th>
            <th>Chức năng</th>
        </tr>
        <c:forEach var="user" items="${dataPage.data}" varStatus="i">
            <tr rel-line="${user.id}" class="text-center ${!user.run ? 'success' : 'danger'}" >
                <td>${i.index + 1}</td>
                <td>${user.userId}</td>
                <td>${user.admin}</td>
                <td>${user.note}</td>
                <td><p>   
                        <jsp:setProperty name="date" property="time" value="${user.createTime}" />
                        <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                        </p></td>
                    <td><p>   
                        <jsp:setProperty name="date" property="time" value="${user.updateTime}" />
                        <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                        </p></td>
                    <td><p>   
                        <jsp:setProperty name="date" property="time" value="${user.startTime}" />
                        <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                        </p></td>
                    <td>
                        <p>   
                        <jsp:setProperty name="date" property="time" value="${user.endTime}" />
                        <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                        </p>
                    </td>
                    <td rel-lbl="${user.id}">
                    <c:choose>
                        <c:when test="${user.run && !user.done}"><label class="label label-danger">Bị khóa</label></c:when>
                        <c:when test="${user.done && !user.run}"><label class="label label-success">Mở khóa</label></c:when>
                        <c:otherwise><label class="label label-default">Đang xử lý</label></c:otherwise>
                    </c:choose>
                </td>
                <td rel-btn="${user.id}">
                    <c:if test="${user.run && !user.done}">
                        <button  type="button" style="width: 100px;" onclick="userlock.unlock('${user.id}');" class="btn btn-success">Mở khóa</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
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