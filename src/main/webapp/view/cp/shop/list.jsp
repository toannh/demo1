<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@taglib uri="http://chodientu.vn/text" prefix="text" %>
<%@taglib uri="http://chodientu.vn/url" prefix="url" %>
<!DOCTYPE html>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách shop
        </a>
    </li>
</ul> 
<div class="func-container">
    <form:form modelAttribute="shopSearch" role="form" style="margin-top:20px;">
        <div class="col-sm-3">
            <div class="form-group">
                <form:input path="userId" type="text" class ="form-control" placeholder="Mã shop,mã người bán"/>
            </div>
            <div class="form-group">
                <form:input path="alias" type="text" class ="form-control" placeholder="Link shop"/>
            </div>

            <div class="form-group">
                <form:input path="url" type="text" class ="form-control" placeholder="Domain shop"/>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <form:input path="title" type="text" class ="form-control" placeholder="Tiêu đề shop"/>
            </div>
            <div class="form-group">
                <form:input path="phone" type="text" class ="form-control" placeholder="Phone shop"/>
            </div>
            <div class="form-group">
                <form:input path="email" type="text" class ="form-control" placeholder="Email shop"/>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <form:input path="createTimeFrom" type="hidden" class ="form-control timeSelect" placeholder="Ngày tạo từ"/>
            </div>
            <div class="form-group">
                <form:input path="createTimeTo" type="hidden" class ="form-control timeSelect" placeholder="Đến ngày"/>
            </div>
            <div class="form-group">
                <form:input path="supporter" type="text" class ="form-control" placeholder="Nhân viên chăm sóc"/>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <select name="cityId" class="form-control" id="searchShopCity" onchange="shop.listDistrict($(this).val())">
                </select>
            </div>
            <div class="form-group" id="searchShopDistrict">
            </div>
        </div>
        <div class="col-sm-4">
            <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-search" ></i> Tìm kiếm</button>
            <button type="button" class="btn btn-primary" onclick="shop.reset();"><i class="glyphicon glyphicon-refresh" ></i> Làm lại</button>
            <button type="button" class="btn btn-success" onclick="shop.exportEx();"><i class="glyphicon glyphicon-list" ></i>Xuất Excel</button>
        </div>
        <div class="clearfix"></div>
    </form:form>
    <div class="cms-line" style="margin-top: 10px"></div>
    <div class="clearfix"></div>
    <div>
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
        <thead>
            <tr>
                <th class="center" style="text-align: center; vertical-align: middle;">Mã</th>
                <th class="center" style="text-align: center; vertical-align: middle;width: 400px">Tên shop</th>
                <th class="center" style="text-align: center; vertical-align: middle;">Domain</th>
                <th class="center" style="text-align: center; vertical-align: middle">Tên người bán</th>
                <th class="center" style="text-align: center; vertical-align: middle">Ngày tạo</th>
                <th class="center" style="text-align: center; vertical-align: middle;width: 180px">Chức năng</th>
            </tr>
        </thead>
        <c:if test="${dataPage.dataCount <= 0}">
            <tr> 
                <td colspan="4" style=" vertical-align: middle;color: red" class="text-center">Không tìm thấy shop nào!</td>
            </tr>
        </c:if>
        <c:if test="${dataPage.dataCount > 0}">
            <tbody>
                <jsp:useBean id="date" class="java.util.Date" />
                <c:forEach var="shop" items="${dataPage.data}">
                    <c:forEach var="user" items="${users}">
                        <c:if test="${shop.userId == user.id}">
                            <tr> 
                                <td style=" vertical-align: middle">
                                    ${shop.userId}
                                </td>
                                <td style=" vertical-align: middle">
                                    <p><a href="${baseUrl}/${shop.alias}" target="_blank">${shop.title}</a></p>
                                </td>
                                <td style=" vertical-align: middle">
                                    ${shop.url}
                                </td>
                                <td style=" vertical-align: middle">
                                    <p><a href="${baseUrl}/user/${user.id}/ho-so-nguoi-ban.html" target="_blank">${user.username !=null?user.username:user.email}</a></p>
                                </td>
                                <td style=" vertical-align: middle" class="text-center">
                                    <p>   
                                        <jsp:setProperty name="date" property="time" value="${shop.createTime}" />
                                        <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                                        </p>
                                    </td>
                                    <td class="btn-horizontal text-center">                        
                                        <div class="shop_${shop.userId}">
                                        <a onclick="shop.addDomain('${shop.userId}');" class="btn btn-primary checkRole" style="width: 150px;display: none; margin-left: 10px"><span class="fa fa-tags pull-left"></span> Add domain</a>
                                        <c:if test="${shop.supporter == null || shop.supporter ==''}">
                                            <a onclick="shop.addSupport('${shop.userId}');" class="btn btn-default" style="width: 150px;"><span class="fa fa-list pull-left"></span> Nhận support</a>
                                        </c:if>
                                        <c:if test="${shop.supporter!=null && shop.supporter==viewer.administrator.email}">
                                            <a onclick="shop.addNote('${shop.userId}');" class="btn btn-success" style="width: 150px;"><span class="fa fa-android pull-left"></span> Ghi chú</a>
                                            <c:if test="${shop.note!= null && shop.note!= ''}">
                                                <span class="badge badge-success cdt-tooltip" data-toggle="tooltip" data-placement="top" title="${shop.note}">!</span>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${shop.supporter != null && shop.supporter != viewer.administrator.email}">
                                            ${shop.supporter}<c:if test="${shop.note!= null && shop.note!= ''}"> <span class="badge badge-success cdt-tooltip" data-toggle="tooltip" data-placement="top" title=" ${shop.note}">!</span></c:if>
                                        </c:if>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </tbody>
        </c:if>
    </table>
    <div style="margin-top: 10px; margin-bottom: 10px;">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>