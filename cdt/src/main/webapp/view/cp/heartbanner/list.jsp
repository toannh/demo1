<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-tags"></i>
            Danh sách Heart Banner
        </a>
    </li>
</ul>
<div class="func-container">
    <button onclick="heartbanner.add();" type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Thêm mới banner </button>
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center">Ảnh </th>
            <th style="vertical-align: middle; text-align: center">Thông tin banner</th>            
            <th style="vertical-align: middle; text-align: center; width: 75px; "> Thứ tự</th>
            <th style="vertical-align: middle; text-align: center; width: 75px; "> Hiển thị</th>
            <th class="text-center" style="width: 155px;">Thao tác</th>
        </tr>     
        <c:forEach items="${bannerSearchs.data}" var="hbanner">
            <tr>
                <td class="text-center"><img src="${hbanner.image}"/></td>
                <td class="text-left">
                    <p>${hbanner.name}</p>
                    <p>Link : <a href="${hbanner.url}" target="_blank">${hbanner.url}</a></p>

                </td>
                <td class="text-center">
                    <input name="position" id="banner-order_4hmrphkb" for="${hbanner.id}" type="text" value="${hbanner.position}" class="form-control" style="width: 50px; height: 30px; text-align: center;">
                </td>
                <td class="text-center">

                    <a href="javascript:void(0);" onclick="heartbanner.editStatus('${hbanner.id}');" editStatus="${hbanner.id}">
                        <c:if test="${!hbanner.active}">
                            <img src="${staticUrl}/cp/img/icon-disable.png" />
                        </c:if>
                        <c:if test="${hbanner.active}">
                            <img src="${staticUrl}/cp/img/icon-enable.png" />
                        </c:if>
                    </a>

                </td>
                <td style="vertical-align: middle">
                    <div class="btn-group">
                        <button onclick="heartbanner.edit('${hbanner.id}')" type="button" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span> Sửa</button>
                        <button onclick="heartbanner.remove('${hbanner.id}')" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xoá</button>
                    </div>
                </td>
            </tr>        
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">

    </div>
</div>