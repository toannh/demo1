
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="text" uri="http://chodientu.vn/text" %>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách thương hiệu
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="mnSearch" cssClass="form-vertical" method="POST" role="form" style="margin-top: 20px;">        
        <form:input path="categoryId" type="hidden"/>
        <div class="col-sm-7">  
            <div class="form-group ">
                <form:input path="name" type="text" class="form-control" placeholder="Tên thương hiệu"/>
            </div>
            <div class="form-group ">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc thương hiệu</button>
            </div>
        </div>
        <div class="form-group col-md-5" id="selectcategorys"></div>
    </form:form>
    <div class="clearfix"></div>
    <div class="cms-line" style="margin-top: 10px;" ></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left alert alert-success" style="padding: 10px; width: 33%;" >Tìm thấy tổng số <span style="color: tomato; font-weight: bolder">${manufacturersPage.dataCount} </span> thương hiệu trong <span style="color: tomato; font-weight: bolder">${manufacturersPage.pageCount}</span> trang.</h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${manufacturersPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${manufacturersPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <div style="margin-top: 10px;">
        <h5 class="pull-left alert alert-success" style="padding: 10px;" >
            <span style="color: tomato; font-weight: bolder">${text:numberFormat(countInElasticseach)} / ${text:numberFormat(realCount)}</span> thương hiệu đã được index.
            <button type="button"  class="btn btn-success" onclick="manufacturer.index();"><span class="glyphicon glyphicon-plus"></span> Index </button>
            <button type="button"  class="btn btn-danger" onclick="manufacturer.unindex();"><span class="glyphicon glyphicon-remove"></span> Xóa Index </button>
        </h5>

        <div class="clearfix"></div>
    </div>         
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center">ID</th>
            <th class="text-center" >Thương hiệu</th>
            <th class="text-center" >Logo</th>
            <th width="100px" style="text-align:center">Hiển thị</th>
            <th class="text-center"> <button onclick="manufacturer.add();" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Thêm mới</button></th>
        </tr>        
        <c:forEach var="manu" items="${manufacturersPage.data}">            
            <tr for="${manu.id}">
                <td align="center">${manu.id}</td>
                <td>${manu.name}</td>
                <td align="center">                    
                    <c:if test="${manu.imageUrl != null&& manu.imageUrl != ''}">
                        <img src="${manu.imageUrl}" alt="${manu.name}" style="width: 200px" />                    
                    </c:if>
                    <c:if test="${manu.imageUrl == null|| manu.imageUrl == ''}">
                        Chưa có ảnh
                    </c:if>
                </td>
                <td style="width: 70px" align="center">
                    <a href="javascript:void(0);" for="active_${manu.id}" onclick="manufacturer.active('${manu.id}')">
                        <c:if test="${manu.active}">
                            <img src="${staticUrl}/cp/img/icon-enable.png">
                        </c:if>
                        <c:if test="${!manu.active}">
                            <img src="${staticUrl}/cp/img/icon-disable.png">
                        </c:if>                        
                    </a>
                </td>
                <td style="vertical-align: middle; width: 140px" class="btn-control text-center">
                    <div class="btn-group">
                        <button onclick="manufacturer.edit('${manu.id}')" class="btn btn-info" style="width: 130px;"><span class="glyphicon glyphicon-edit"></span> Sửa</button>
                    </div>
                    <div class="form-group" style="margin-top:5px">
                        <button class="btn btn-success" onClick="manufacturer.showMap('${manu.id}')" style="width: 130px;"><span class="glyphicon glyphicon-random"></span> Map</button>
                    </div>
                    <div class="btn-group">
                        <button class="btn btn-danger" onClick="manufacturer.del('${manu.id}')" style="width: 130px;"><span class="glyphicon glyphicon-trash"></span> Xóa</button>
                    </div>                    
                </td>
            </tr>
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${manufacturersPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${manufacturersPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>