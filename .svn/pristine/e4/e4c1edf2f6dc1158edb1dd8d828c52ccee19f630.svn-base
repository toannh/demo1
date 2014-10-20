<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a><i class="fa fa-list"></i>Danh sách tin tức</a>
    </li>
</ul>
<div class="func-container">
    <button onclick="newshomebox.add();" type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>Thêm tin tức</button>
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center; ">Mã tin</th>
            <th style="vertical-align: middle; text-align: center; ">Tiêu đề</th>            
            <th style="vertical-align: middle; text-align: center; ">Ảnh hiển thị</th>
            <th style="text-align: center; width: 100px;">Thao tác</th>
        </tr>   
        <c:forEach items="${newshomebox}" var="newshomebox">
            <tr>
                <td style="vertical-align: middle; text-align: center;">${newshomebox.id}</td>
                <td style="vertical-align: middle; text-align: left;">
                    ${newshomebox.title}
                </td>            
                <td style="vertical-align: middle; text-align: center;"><img src="${newshomebox.image}" width="100"/></td>
                <td style="text-align: center; width: 100px;">
                    <button style="width: 100%; margin-top: 10px;" onclick="newshomebox.delnews('${newshomebox.id}')" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xoá</button>

                </td>
            </tr>    
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">

    </div>
</div>