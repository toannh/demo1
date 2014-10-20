<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách quản trị viên
        </a>
    </li>
</ul>
<div class="func-container">  
    <form:form modelAttribute="adminSearch" cssClass="form-vertical" method="POST" role="form" style="margin-top: 20px;">        
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="email" type="text" class="form-control" placeholder="Email"/>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select  path="active" class="form-control">
                    <form:option value="0"> --Trạng thái hoạt động--</form:option>
                    <form:option value="1">Đang hoạt động</form:option>
                    <form:option value="2">Đang bị khóa</form:option>
                </form:select>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc thành viên</button>
            </div>
        </div>
    </form:form>
    <div class="cms-line"></div>
    <div class="clearfix"></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${administratorPage.dataCount} </strong> kết quả <strong>${administratorPage.pageCount}</strong> trang.
        </h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${administratorPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${administratorPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center" style="vertical-align: middle" >ID</th>
            <th class="text-center" style="vertical-align: middle" >Email</th>
            <th class="text-center" style="vertical-align: middle" >Đang hoạt động</th>
            <th class="text-center" > <button onclick="administrator.add();" type="button" class="btn btn-primary btn-group"><span class="glyphicon glyphicon-plus"></span> Thêm mới</button></th>
        </tr>        
        <c:forEach var="admin" items="${administratorPage.data}">            
            <tr for="${admin.id}">
                <td class="text-center" style="vertical-align: middle" >${admin.id}</td>
                <td class="text-center" style="vertical-align: middle"  >${admin.email}</td>
                <td class="text-center" style="vertical-align: middle" >
                    <a href="javascript:void(0);" for="active_${admin.id}" onclick="administrator.active('${admin.id}')">
                        <c:if test="${admin.active}">
                            <img src="${staticUrl}/cp/img/icon-enable.png">
                        </c:if>
                        <c:if test="${!admin.active}">
                            <img src="${staticUrl}/cp/img/icon-disable.png">
                        </c:if>                        
                    </a>
                </td>
                <td style="vertical-align: middle;" class="text-center">
                    <div class="btn-group">
                        <button onclick="administrator.edit('${admin.id}')" class="btn btn-info" style="width: 130px;"><span class="glyphicon glyphicon-edit"></span> Sửa</button>
                        <button class="btn btn-success" onClick="administrator.showRoles('${admin.id}')" style="width: 130px;"><span class="glyphicon glyphicon-random"></span> Cấp quyền</button>
                        <button class="btn btn-danger" onClick="administrator.del('${admin.id}')" style="width: 130px;"><span class="glyphicon glyphicon-trash"></span> Xóa</button>
                    </div>           
                </td>
            </tr>
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${administratorPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${administratorPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>