
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="nav nav-tabs" role="tablist">
    <li><a href="${baseUrl}/cp/model.html">Danh sách model</a></li>
    <li><a href="${baseUrl}/cp/addmodel.html">Danh sách model mới được tạo</a></li>
    <li><a href="${baseUrl}/cp/editmodel.html">Danh sách model yêu cầu sửa</a></li>
    <li class="active"><a href="${baseUrl}/cp/reviewmodel.html"><span class="glyphicon glyphicon-plus"></span>Duyệt model tạo</a></li>
    <li><a href="${baseUrl}/cp/reviewmodel.html?task=edit"><span class="glyphicon glyphicon-pencil"></span>Duyệt model sửa</a></li>
</ul>
<div class="func-container" >
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center" style="vertical-align: middle">Mã</th>
            <th class="text-center" style="vertical-align: middle" >Model</th>
            <th class="text-center" style="vertical-align: middle" >Danh mục</th>
            <th class="text-center" style="vertical-align: middle" >Thương hiệu</th>
            <th class="text-center" style="width: 460px; vertical-align: middle;" >

            </th>
        </tr>        
        <c:forEach var="item" items="${data}">            
            <tr>
                <td class="text-center" style="vertical-align: middle">${item.id}</td>
                <td class="text-center" style="vertical-align: middle">${item.name}</td>
                <td class="text-center" style="vertical-align: middle">${item.categoryName}</td>
                <td class="text-center" style="vertical-align: middle">${item.manufacturerName}</td>
                <td class="text-left" style="vertical-align: middle">

                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" onclick="reviewmodel.showDetailEditFalse('${item.id}');" >
                            <span class="glyphicon glyphicon-edit"></span> Chi tiết</button>
                        <button type="button" class="btn btn-warning" onclick="model.showImages('${item.id}');" >
                            <span class="glyphicon glyphicon-list"></span> Hình ảnh</button>
                        <button type="button" class="btn btn-info"  onclick="reviewmodel.showPropertiesEditFalse('${item.id}');"  >
                            <span class="glyphicon glyphicon-random"></span> Thuộc tính</button>
                        <button type="button" class="btn btn-success" onclick="reviewmodel.reviewEditModel('${item.id}');">
                            <span class="glyphicon glyphicon-adjust"></span>Duyệt model</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${fn:length(data) == 0}">
            <tr>
                <td colspan="6" class="text-center text-danger">Không tìm thấy model nào</td>
            </tr>   
        </c:if>
    </table>
</div>