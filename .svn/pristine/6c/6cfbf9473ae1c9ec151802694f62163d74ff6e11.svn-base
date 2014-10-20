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
            Danh sách bestdealbox
        </a>
    </li>
</ul>
<div class="func-container">  
    <button onclick="bestdealbox.add();" type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Thêm mới BestDeal</button>
    <div style="margin-top: 10px">
    </div>
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center; ">ID </th>
            <th style="vertical-align: middle; text-align: center; ">Thông tin sản phẩm</th>            
            <th style="vertical-align: middle; text-align: center; ">Ảnh hiển thị</th>
            <th style="vertical-align: middle; text-align: center; width: 100px ">Trạng thái</th>
            <th style="text-align: center; width: 100px;">Thao tác</th>
        </tr>  
        <c:forEach items="${bestDealBox}" var="bestdealbox">
            <c:forEach items="${items}" var="item">
                <c:if test="${item.id==bestdealbox.itemId}">
                    <tr>
                        <td style="vertical-align: middle; text-align: center; ">${item.id} </td>
                        <td style="vertical-align: middle; text-align: center; ">
                            <input name="categoryName" id="categoryName" type="text" value="${bestdealbox.title}" onchange="bestdealbox.changeNameCate(${item.id}, this.value);" class="form-control" style="min-width: 200px; height: 30px;">

                        </td>            
                        <td style="vertical-align: middle; text-align: center; "> <img src="${(item.images != null && fn:length(item.images) >0)?item.images[0]:staticUrl.concat('/market/images/no-image-product.png')}" width="50"/></td>
                        <td class="text-center">

                            <a href="javascript:void(0);" onclick="bestdealbox.changeStatus('${bestdealbox.itemId}');" editStatus="${bestdealbox.itemId}">
                                <c:if test="${!bestdealbox.active}">
                                    <img src="${staticUrl}/cp/img/icon-disable.png" />
                                </c:if>
                                <c:if test="${bestdealbox.active}">
                                    <img src="${staticUrl}/cp/img/icon-enable.png" />
                                </c:if>
                            </a>

                        </td>
                        <td style="vertical-align: middle; text-align: center">
                            <button style="width: 100%" onclick="bestdealbox.edit('${bestdealbox.itemId}')" type="button" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span> Thay ảnh</button>
                            <button style="width: 100%; margin-top: 10px;" onclick="bestdealbox.remove('${bestdealbox.id}')" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xoá</button>
                        </td>
                    </tr>   
                </c:if>
            </c:forEach>
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">
    </div>
</div>