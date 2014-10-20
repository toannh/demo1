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
            Danh sách người bán
        </a>
    </li>
</ul>
<div class="func-container"> 
    <form:form modelAttribute="sellerSearch" cssClass="form-vertical" method="POST" role="form" style="margin-top: 20px;">        
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="userId" type="text" class ="form-control" placeholder="Mã shop,mã người bán"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:select path="nlIntegrated" class="form-control">
                    <form:option value="0" label="Tất cả trạng thái tích hợp Ngân Lượng"/>
                    <form:option value="1" label="Đã tích hợp"/>
                    <form:option value="2" label="Chưa tích hợp"/> 
                </form:select>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:select path="scIntegrated" class="form-control">
                    <form:option value="0" label="Tất cả trạng thái tích hợp Ship Chung"/>
                    <form:option value="1" label="Đã tích hợp"/>
                    <form:option value="2" label="Chưa tích hợp"/> 
                </form:select>
            </div>
        </div>
        <div class="col-sm-4"> 
            <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-search" ></i> Tìm kiếm</button>
            <button type="button" class="btn btn-primary" onclick="seller.reset();"><i class="glyphicon glyphicon-refresh" ></i> Làm lại</button>
            <a class="btn btn-success" href="${baseUrl}/cp/seller/excelshop.html"><span class="glyphicon glyphicon-list"></span> Xuất excel</a>

        </div>
        <div class="clearfix"></div>
    </form:form>
    <div class="cms-line" style="margin-top: 10px"></div>
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
        <thead>
            <tr>
                <th class="center" style="text-align: center; vertical-align: middle;">Mã</th>
                <th class="center" style="text-align: center; vertical-align: middle;">API Code</th>
                <th class="center" style="text-align: center; vertical-align: middle">Tên người bán</th>
                <th class="center" style="text-align: center; vertical-align: middle">Tích hợp NL</th>
                <th class="center" style="text-align: center; vertical-align: middle">Tích hợp SC</th>
                <th class="center" style="text-align: center; vertical-align: middle">Chức năng</th>
            </tr>
        </thead>
        <c:if test="${dataPage.dataCount <= 0}">
            <tr> 
                <td colspan="3" style=" vertical-align: middle;color: red">Không tìm thấy người bán nào!</td>
            </tr>
        </c:if>
        <c:if test="${dataPage.dataCount > 0}">
            <tbody>
                <jsp:useBean id="date" class="java.util.Date" />
                <c:forEach var="seller" items="${dataPage.data}">
                    <c:forEach var="user" items="${users}">
                        <c:if test="${seller.userId == user.id}">
                            <tr> 
                                <td style=" vertical-align: middle" class="text-center" >
                                    ${seller.userId}
                                </td>
                                <td style="vertical-align: middle" class="text-center" data-rel="${seller.userId}" ></td>
                                <td style=" vertical-align: middle" class="text-center" >
                                    <p><a href="${baseUrl}/user/${seller.userId}/ho-so-nguoi-ban.html" target="_blank">${user.username !=null?user.username:user.email}</a></p>
                                </td>
                                <td style=" vertical-align: middle" class="text-center">
                                    ${seller.nlIntegrated?'<label class="label label-success">Đã tích hợp</label>':'<label class="label label-danger">Chưa tích hợp</label>'}
                                </td>
                                <td style=" vertical-align: middle" class="text-center">
                                    ${seller.scIntegrated?'<label class="label label-success">Đã tích hợp</label>':'<label class="label label-danger">Chưa tích hợp</label>'}
                                </td>
                                <td style=" vertical-align: middle" class="text-center">
                                    <div class="btn-group">
                                        <button type="submit" class="btn btn-success" onclick="seller.syncItem('${seller.userId}', '${seller.nlIntegrated}', '${seller.scIntegrated}');"><i class="glyphicon glyphicon-sort" ></i> Đồng bộ SP</button>
                                        <button type="submit" class="btn btn-toolbar" onclick="seller.createCode('${seller.userId}');"><i class="glyphicon glyphicon-tags" ></i> Tạo code API</button>
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