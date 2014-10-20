<%-- 
    Document   : list
    Created on : Nov 8, 2013, 9:23:02 AM
    Author     : Phuongdt
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách danh mục tin tức
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="catNewsSearch" method="POST" role="form" style="margin-top: 20px;">
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="id" type="text" class="form-control" placeholder="Mã danh mục"/>
            </div>
            <div class="form-group">
                <form:input path="name" type="text" class="form-control" placeholder="Tên danh mục"/>
            </div>

        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:select path="active" class="form-control">
                    <form:option value="0" label="Chọn trạng thái"/>
                    <form:option value="1" label="Đã Active"/>
                    <form:option value="2" label="Chưa Active"/>
                </form:select>
            </div>
            <div class="form-group">
                <form:select path="parentId" class="form-control">
                    <form:option value="0" label="---- Chọn danh mục cha ----" />
                    <c:forEach var="cate" items="${newsCate}">
                        <c:if test="${cate.level==0}">
                            <form:option value="${cate.id}" label="${cate.name}"></form:option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3">
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc danh mục</button>
            <button type="button" class="btn btn-default" onclick="newscategory.resetForm()"><span class="glyphicon glyphicon-refresh"></span> Nhập lại</button>
        </div>
    </form:form>
    <div class="clearfix"></div>
    <div class="cms-line" style="margin-top: 10px;" ></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left alert alert-success" style="padding: 10px; width: 33%;" >Tìm thấy tổng số <span style="color: tomato; font-weight: bolder">${categoryNewsPage.dataCount} </span> danh mục trong <span style="color: tomato; font-weight: bolder">${categoryNewsPage.pageCount}</span> trang.</h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${categoryNewsPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${categoryNewsPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th>ID</th>
            <th>Tên danh mục</th>
            <th>Miêu tả</th>
            <th>Thời gian tạo/Cập nhật</th>
            <th>Thứ tự</th>
            <th>Trạng thái</th>
            <th>
        <p><a href="javascript:;" class="btn btn-success" onclick="newscategory.add()" ><i class="glyphicon glyphicon-tag" ></i> Thêm mới</a></p>
        </th>
        </tr>
        <c:forEach var="categorynews" items="${categoryNewsPage.data}">
            <c:if test="${categorynews.level==0}">
                <tr for="${categorynews.id}">
                    <td style="width: 100px;"><p><b>${categorynews.id}</b></p></td>
                    <td>
                        <p>${categorynews.name}</p>
                    </td>
                    <td style="width: 200px;">
                        <p>${categorynews.description}</p>
                    </td>
                    <td style="width: 250px;">
                        <p><b>Tạo: </b><span class="timestam"> ${categorynews.createTime}</span></p>
                        <p><b>Cập nhật: </b><span class="timestam"> ${categorynews.updateTime}</span></p>
                    </td>
                    <td style="vertical-align: middle; text-align: center" for="${categorynews.id}">
                        <input name="position" class="form-control" type="text" value="${categorynews.position}" style="width: 50px; height: 30px; text-align: center;" onchange="newscategory.changePosition('${categorynews.id}', this)"/>
                    </td>
                    <td style="width: 100px;vertical-align: middle; text-align: center">
                        <a href="javascript:void(0);" onclick="newscategory.editStatus('${categorynews.id}');" editStatus="${categorynews.id}">
                            <c:if test="${!categorynews.active}">
                                <img src="${staticUrl}/cp/img/icon-disable.png" />
                            </c:if>
                            <c:if test="${categorynews.active}">
                                <img src="${staticUrl}/cp/img/icon-enable.png" />
                            </c:if>
                        </a>
                    </td>
                    <td style="width: 155px">
                        <div class="btn-group">
                            <button onclick="newscategory.edit('${categorynews.id}')" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> Sửa</button>
                            <button onclick="newscategory.del('${categorynews.id}')" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</button>
                        </div>
                    </td>
                </tr>
                <c:forEach var="categorynews1" items="${categoryNewsPage.data}">
                    <c:if test="${categorynews1.parentId==categorynews.name}">
                        <tr for="${categorynews1.id}">
                            <!--<td style="width: 100px;"><p><b>${categorynews1.id}</b></p></td>-->
                            <td></td>
                            <td>
                                <p style="padding-left: 20px;">--- ${categorynews1.name}</p>
                            </td>
                            <td style="width: 200px;">
                                <p>${categorynews1.description}</p>
                            </td>
                            <td style="width: 250px;">
                                <p><b>Tạo: </b><span class="timestam"> ${categorynews1.createTime}</span></p>
                                <p><b>Cập nhật: </b><span class="timestam"> ${categorynews1.updateTime}</span></p>
                            </td>
                            <td style="vertical-align: middle; text-align: center" for="${categorynews1.id}">
                                <input name="position" type="text" value="${categorynews1.position}" style="width: 50px; height: 30px; text-align: center;" class="form-control" onchange="newscategory.changePosition('${categorynews1.id}', this)"/>
                            </td>
                            <td style="width: 100px;vertical-align: middle; text-align: center">
                                <a  href="javascript:void(0);" onclick="newscategory.editStatus('${categorynews1.id}');" editStatus="${categorynews1.id}">
                                    <c:if test="${!categorynews1.active}">
                                        <img src="${staticUrl}/cp//img/icon-disable.png" />
                                    </c:if>
                                    <c:if test="${categorynews1.active}">
                                        <img src="${staticUrl}/cp/img/icon-enable.png" />
                                    </c:if>
                                </a>
                            </td>
                            <td style="width: 155px">
                                <div class="btn-group">
                                    <button onclick="newscategory.edit('${categorynews1.id}')" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> Sửa</button>
                                    <button onclick="newscategory.del('${categorynews1.id}')" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</button>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>

            </c:if>
        </c:forEach>
    </table>
    <div style="margin-top: 10px">
        <h5 class="pull-left">Tìm thấy tổng số ${categoryNewsPage.dataCount} danh mục trong ${categoryNewsPage.pageCount} trang.</h5>
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${categoryNewsPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${categoryNewsPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
