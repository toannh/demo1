<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="nav nav-tabs" role="tablist">
     <li><a href="${baseUrl}/cp/topsellerbox.html"><i class="fa fa-list"></i> Danh sách người bán chạy</a></li>
     <li class="active"><a><i class="fa fa-tag"></i> Danh sách sản phẩm bán chạy</a></li>
</ul>
<div class="func-container">
    <button onclick="topsellerbox.addItem('${param.id}');" type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>Thêm sản phẩm</button>
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center; ">Mã sản phẩm</th>
            <th style="vertical-align: middle; text-align: center; ">Thông tin sản phẩm</th>            
            <th style="vertical-align: middle; text-align: center; ">Ảnh hiển thị</th>
            <th style="vertical-align: middle; text-align: center; width: 100px ">Vị trí</th>
            <th style="vertical-align: middle; text-align: center; ">Trạng thái</th>

            <th style="text-align: center; width: 100px;">Thao tác</th>
        </tr>   
        <c:forEach items="${topSellerItemBoxs}" var="item">
            <tr>
                <td style="vertical-align: middle; text-align: center;">${item.itemId}</td>
                <td style="vertical-align: middle; text-align: left;">
                    <input type="text" value="${item.title}" onchange="topsellerbox.changeNameItem('${param.id}',${item.itemId}, this.value);" class="form-control" style="min-width: 200px; height: 30px;">
                </td>            
                <td style="vertical-align: middle; text-align: center;"><img src="${(item.image != null && fn:length(item.image) >0)?item.image:staticUrl.concat('/market/images/no-image-product.png')}" width="50"/></td>
                <td style="vertical-align: middle; text-align: center;">
                    <input type="text" value="${item.position}" onchange="topsellerbox.changePositionItem('${param.id}',${item.itemId}, this.value);" class="form-control" style="min-width: 10px; height: 30px;">
                </td>
                <td class="text-center">
                    <a href="javascript:void(0);" onclick="topsellerbox.changeStatusItem('${param.id}', '${item.itemId}');" editStatus="${item.itemId}">
                        <c:if test="${!item.active}">
                            <img src="${staticUrl}/cp/img/icon-disable.png" />
                        </c:if>
                        <c:if test="${item.active}">
                            <img src="${staticUrl}/cp/img/icon-enable.png" />
                        </c:if>
                    </a>
                </td>

                <td style="text-align: center; width: 100px;">
                    <button style="width: 100%" onclick="topsellerbox.editItem('${item.itemId}')" type="button" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span> Thay ảnh</button>
                    <button style="width: 100%; margin-top: 10px;" onclick="topsellerbox.removeItem('${item.itemId}', '${param.id}')" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xoá</button>

                </td>
            </tr>    
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">

    </div>
</div>