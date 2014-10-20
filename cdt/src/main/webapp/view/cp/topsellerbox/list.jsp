<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách người bán uy tín
        </a>
    </li>
</ul>
<div class="func-container">
    <button onclick="topsellerbox.add();" type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Thêm mới người bán</button>
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center; ">Mã người bán</th>
            <th style="vertical-align: middle; text-align: center; ">Thông tin sản phẩm</th>            
            <th style="vertical-align: middle; text-align: center; ">Ảnh hiển thị</th>
            <th style="vertical-align: middle; text-align: center; width: 100px ">Vị trí</th>
            <th style="vertical-align: middle; text-align: center; ">Trạng thái</th>
            <th style="text-align: center; width: 100px;">Thao tác</th>
        </tr>   
        <c:forEach items="${topsellerboxs}" var="topsellerbox">
            <tr>
                <td style="vertical-align: middle; text-align: center;"><a href="${baseUrl}/cp/topsellerbox.html?id=${topsellerbox.sellerId}">${topsellerbox.sellerId}</td>
                <td style="vertical-align: middle; text-align: left;">
                    <b>Tổng số sản phẩm:</b> ${topsellerbox.countItem}<br/>
                </td>            
                <td style="vertical-align: middle; text-align: center;"><img src="${topsellerbox.image}"/></td>
                <td style="vertical-align: middle; text-align: center;">
                    <input type="text" value="${topsellerbox.position}" onchange="topsellerbox.changePosition('${topsellerbox.sellerId}',this.value)" class="form-control" style="min-width: 10px; height: 30px;">
                </td>
                <td class="text-center">
                    <a href="javascript:void(0);" onclick="topsellerbox.changeStatus('${topsellerbox.sellerId}');" editStatus="${topsellerbox.sellerId}">
                        <c:if test="${!topsellerbox.active}">
                            <img src="${staticUrl}/cp/img/icon-disable.png" />
                        </c:if>
                        <c:if test="${topsellerbox.active}">
                            <img src="${staticUrl}/cp/img/icon-enable.png" />
                        </c:if>
                    </a>
                </td>
                <td style="text-align: center; width: 100px;">
                    <button style="width: 100%" onclick="topsellerbox.edit('${topsellerbox.id}')" type="button" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span> Sửa</button>
                    <button style="width: 100%; margin-top: 10px;" onclick="topsellerbox.remove('${topsellerbox.id}')" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xoá</button>

                </td>
            </tr>    
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">

    </div>
</div>