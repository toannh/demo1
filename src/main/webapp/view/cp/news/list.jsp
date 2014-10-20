<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách tin tức
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="newsSearch" method="POST" role="form" style="margin-top: 20px;" class="form-vertical" >
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="id" type="text" class="form-control" placeholder="Mã tin tức"/>
            </div>
            <div class="form-group">
                <form:input path="title" type="text" class="form-control" placeholder="Tiêu đề"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="user" type="text" class="form-control" placeholder="Người tạo"/>
            </div>
            <div class="form-group">
                <form:input path="textSearch" type="text" class="form-control" placeholder="Từ khóa"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:select path="categoryIds" class="form-control" id="categoryIds" onchange="news.loadCateSearch($(this).val());" >
                    <form:option value="" label="---- Nhóm tin ----"/>
                    <c:forEach var="category" items="${newsCategories}">
                        <c:if test="${category.level ==0}">
                            <form:option value="${category.id}" label="${category.name}"/>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>

        </div>
        <input type="hidden" name="subcate_hide" value="${newsSearch.categoryId1}">
        <div class="col-sm-4" id="subcate">

        </div>
        <div class="col-sm-9">
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc danh mục</button>
            <button type="button" class="btn btn-default" onclick="news.resetForm()"><span class="glyphicon glyphicon-refresh"></span> Nhập lại</button>
        </div>
        <div class="clearfix"></div>
    </form:form>
    <div class="clearfix"></div>
    <div class="cms-line" style="margin-top: 10px;" ></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${newsPage.dataCount} </strong> kết quả <strong>${newsPage.pageCount}</strong> trang.
        </h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${newsPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${newsPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center">ID</th>
            <th class="text-center">Tiêu đề</th>
            <th class="text-center">Ảnh</th>
            <th class="text-center">Danh mục</th>
            <th class="text-center">Người tạo</th>
            <th class="text-center">Ngày tạo</th>
            <th class="text-center">Ngày sửa</th>
            <th class="text-center">Hiển thị</th>
            <th class="text-center" style="width: 155px;" >
        <p><a href="javascript:;" class="btn btn-success" onclick="news.add()" ><i class="glyphicon glyphicon-tag" ></i> Thêm mới</a></p>
        </th>
        </tr>
        <c:forEach var="news" items="${newsPage.data}">
            <tr>
                <td style="width: 60px; text-align: left;"><p><b>${news.id}</b></p>

                </td>
                <td style="width: 300px; text-align: left;"><p>${news.title}</p></td>
                <td style="width: 120px; text-align: center;">

                    <c:if test="${news.image != null && news.image != ''}">
                        <img src="${news.image}" style="width: 50px" />                    
                    </c:if>
                    <c:if test="${news.image == null|| news.image == ''}">
                        Chưa có ảnh
                    </c:if>
                </td>
                <td style=" text-align: center;"><p>${news.categoryId}</p></td>
                <td><p>${news.user}</p></td>
                <td><span class="timestam">${news.createTime}</span></td>
                <td><span class="timestam"> ${news.updateTime}</span></td>
                <td style="vertical-align: middle; text-align: center;">
                    <a href="javascript:void(0);" onclick="news.editStatus('${news.id}');" editStatus="${news.id}">
                        <c:if test="${!news.active}">
                            <img src="${staticUrl}/cp/img/icon-disable.png" />
                        </c:if>
                        <c:if test="${news.active}">
                            <img src="${staticUrl}/cp/img/icon-enable.png" />
                        </c:if>
                    </a>
                </td>
                <td style="vertical-align: middle">
                    <div class="btn-group">
                        <button onclick="news.edit('${news.id}')" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> Sửa</button>         
                        <button onclick="news.delnews('${news.id}')" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div style="margin-top: 10px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${newsPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${newsPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
