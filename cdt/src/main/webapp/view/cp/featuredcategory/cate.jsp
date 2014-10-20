<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="nav nav-tabs" role="tablist">
    <li>
        <a href="${baseUrl}/cp/featuredcategory.html">
            <i class="fa fa-list"></i>
            Featured categories
        </a>
    </li>
    <li class="active"><a>Danh sách danh mục</a></li>
</ul>
<div class="func-container">
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center">ID </th>
            <th style="vertical-align: middle; text-align: center">Tên</th>            
            <th style="vertical-align: middle; text-align: center; width: 75px; "> Thứ tự</th>
            <th style="vertical-align: middle; text-align: center; width: 75px; "> Hiển thị</th>
            <th style="text-align: center; width: 100px;">Thao tác</th>
        </tr>     
        <c:forEach items="${cate}" var="category">
            <tr>
                <td class="text-left">
                    <c:if test="${category.leaf==true}">
                        <p>${category.categoryId}</p>
                    </c:if>
                    <c:if test="${category.leaf!=true}">
                        <p><span class="btn btn-xs btn-success" style="text-transform: uppercase"> ${category.template}</span> | <a href="${baseUrl}/cp/featuredcategory.html?catesub=${category.categoryId}">${category.categoryId}</a></p>
                        
                    </c:if>
                 </td>
                <td class="text-left">
                    <input name="categoryName" id="categoryName" type="text" value="${category.categoryName}" onchange="featuredcategory.changeNameCate(${category.categoryId},this.value);" class="form-control" style="min-width: 200px; height: 30px;">
                </td>
                <td class="text-center">
                    <input name="position" id="banner-order_4hmrphkb" type="text" value="${category.position}" onchange="featuredcategory.changePositionCate(${category.categoryId},this.value);" class="form-control" style="width: 50px; height: 30px; text-align: center;">
                </td>
                <td class="text-center">

                    <a href="javascript:void(0);" onclick="featuredcategory.changeStatusCate('${category.categoryId}');" editStatus="${category.categoryId}">
                        <c:if test="${!category.active}">
                            <img src="${staticUrl}/cp/img/icon-disable.png" />
                        </c:if>
                        <c:if test="${category.active}">
                            <img src="${staticUrl}/cp/img/icon-enable.png" />
                        </c:if>
                    </a>

                </td>
                <td style="vertical-align: middle; text-align: center">
                    
                    <button style="width: 100%; margin-top: 10px;" onclick="featuredcategory.delCate('${category.id}')" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xoá</button>
                </td>
            </tr>        
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">

    </div>
</div>