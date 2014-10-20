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
            Danh sách Box Hotdeal trang chủ
        </a>
    </li>
</ul>
<div class="func-container">
    <button onclick="hotdealbox.add();" type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Thêm mới HotDeal</button>
    <button onclick="hotdealbox.addbanner();" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>Thay đổi banner HotDealBox</button>
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center; ">ID </th>
            <th style="vertical-align: middle; text-align: center; ">Thông tin sản phẩm</th>            
            <th style="vertical-align: middle; text-align: center; ">Ảnh hiển thị</th>
            <th style="vertical-align: middle; text-align: center; ">Trạng thái</th>

            <th style="text-align: center; width: 100px;">Thao tác</th>
        </tr>     
        <c:forEach items="${Hotdealbox}" var="item">


            <tr>
                <td class="text-center">${item.itemId}</td>
                <td class="text-left">
                    <input name="categoryName" id="categoryName" type="text" value="${item.title}" onchange="hotdealbox.changeName(${item.itemId}, this.value);" class="form-control" style="min-width: 200px; height: 30px;">

                </td>
                <td class="text-center">
                    <img src="${(item.image != null && fn:length(item.image) >0)?item.image:staticUrl.concat('/market/images/no-image-product.png')}" width="50"  alt="Ảnh sản phẩm" title="${item.title}"/>
                </td>
                <td class="text-center">

                    <a href="javascript:void(0);" onclick="hotdealbox.changeStatus('${item.itemId}');" editStatus="${item.itemId}">
                        <c:if test="${!item.active}">
                            <img src="${staticUrl}/cp/img/icon-disable.png" />
                        </c:if>
                        <c:if test="${item.active}">
                            <img src="${staticUrl}/cp/img/icon-enable.png" />
                        </c:if>
                    </a>

                </td>
                <td style="vertical-align: middle; text-align: center">
                    <button style="width: 100%" onclick="hotdealbox.edit('${item.itemId}')" type="button" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span> Thay ảnh</button>
                    <button style="width: 100%; margin-top: 10px;" onclick="hotdealbox.remove('${item.id}')" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xoá</button>
                </td>
            </tr>   


        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">

    </div>
</div>