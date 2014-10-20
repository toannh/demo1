
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-user"></i>
            Danh sách thành viên
        </a>
    </li>
</ul>
<div class="func-container" > 
    <form:form modelAttribute="userSearch" cssClass="form-vertical" method="POST" role="form" style="margin-top: 20px;">        
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="username" type="text" class="form-control" placeholder="Tên đăng nhập hoặc ID"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="email" type="text" class="form-control" placeholder="Địa chỉ email"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="phone" type="text" class="form-control" placeholder="Số điện thoại"/>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select  path="active" class="form-control">
                    <form:option value="0">-- Trạng thái hoạt động --</form:option>
                    <form:option value="1">Đang hoạt động</form:option>
                    <form:option value="2">Đang bị khóa</form:option>
                </form:select>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select  path="emailVerified" class="form-control">
                    <form:option value="0">-- Kích hoạt email--</form:option>
                    <form:option value="1">Được kích hoạt</form:option>
                    <form:option value="2">Chưa được kích hoạt</form:option>
                </form:select>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select  path="phoneVerified" class="form-control">
                    <form:option value="0"> -- Kích hoạt số điện thoại --</form:option>
                    <form:option value="1">Được kích hoạt</form:option>
                    <form:option value="2">Chưa được kích hoạt</form:option>
                </form:select>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select rel="cityId" path="cityId" class="form-control">
                    <form:option value="0"> -- Chọn thành phố --</form:option>
                    <c:forEach var="city" items="${citys}">
                        <form:option value="${city.id}">${city.name}</form:option>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select rel="districtId" path="districtId" class="form-control">
                    <form:option value="0"> -- Chọn quận huyện --</form:option>
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
            <th class="text-center">ID</th>
            <th class="text-center">Tên đăng nhập</th>
            <th class="text-center">Email</th>
            <th class="text-center">Số điện thoại</th>
            <th class="text-center">Đang hoạt động</th>
            <th class="text-center"> Chức năng </th>
        </tr>        
        <c:forEach var="item" items="${page.data}">            
            <tr <c:if test="${item.emailVerified && item.phoneVerified}">class="success"</c:if> >
                <td class="text-center">${item.id}</td>
                <td class="text-center">${item.username}</td>
                <td class="text-center">
                    ${item.email} 
                    <span class="pull-right" style="cursor: pointer" onclick="user.changeEmailVerified('${item.id}');" rel="emailVerified_${item.id}">${!item.emailVerified?'<i class="glyphicon glyphicon-unchecked"></i>':'<i class="glyphicon glyphicon-check"></i>'}</span>
                </td>
                <td class="text-center">
                    ${item.phone} 
                    <span class="pull-right" style="cursor: pointer" onclick="user.changePhoneVerified('${item.id}');"  rel="phoneVerified_${item.id}">${!item.phoneVerified?'<i class="glyphicon glyphicon-unchecked"></i>':'<i class="glyphicon glyphicon-check"></i>'}</span>
                </td>
                <td class="text-center" rel="active_${item.id}" >
                    <span style="cursor: pointer" onclick="user.changeActive('${item.id}');"  rel="active_${item.id}">
                        ${!item.active?'<i class="glyphicon glyphicon-unchecked"></i>':'<i class="glyphicon glyphicon-check"></i>'}
                    </span>
                </td>
                <td class="text-center">
                    <button type="button" class="btn btn-info" onclick="user.edit('${item.id}')">
                        <span class="glyphicon glyphicon-edit"></span> Sửa</button>
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