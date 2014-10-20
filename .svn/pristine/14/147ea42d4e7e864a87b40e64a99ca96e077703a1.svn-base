<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul class="nav nav-tabs" role="tablist">
      <li><a href="${baseUrl}/cp/landing.html"><i class="fa fa-list"></i> Danh sách landing</a></li>
            <li class="active"><a href="${baseUrl}/cp/landing.html?landingid=${landing.id}">Danh sách danh mục landing</a></li>
</ul>
<div class="func-container">
    <div style="margin-top: 10px;">
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center; ">Mã danh mục</th>
            <th style="vertical-align: middle; text-align: center; ">Tên danh mục</th>
            <th style="vertical-align: middle; text-align: center; ">Vị trí</th>
            <th style="text-align: center; width: 100px;">
                <button onclick="landing.addCategory('${landing.id}');" type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Thêm mới</button>
            </th>
        </tr>     
        <c:if test="${fn:length(listCategories) <=0}">
            <tr><td colspan="4" class="clr-red fs-18">Chưa có danh mục nào cho landing ${landing.name}!</td></tr>
        </c:if>
        <c:forEach items="${listCategories}" var="cate">
            <tr>
                <td class="text-center">${cate.id}</td>
                <td class="text-center">
                    ${cate.name}
                </td>
                <td class="text-center">
                    <input name="position" for="${cate.id}" class="form-control" style="width:50px;" onchange="landing.changePosition('${cate.id}');"  value="${cate.position}"/>
                </td>
                <td style="vertical-align: middle; text-align: center">
                    <button style="width: 100%" onclick="landing.editCategory('${cate.id}')" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span>Sửa</button>
                    <button style="width: 100%; margin-top: 10px;" onclick="landing.editItem('${cate.id}')" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span>Sửa sản phẩm</button>
                    <button style="width: 100%; margin-top: 10px;" onclick="landing.removeCategory('${cate.id}')" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xoá</button>
                </td>
            </tr>   
        </c:forEach>
    </table>
    <div style="margin-top: 10px;">
        <div class="clearfix"></div>
    </div>
</div>