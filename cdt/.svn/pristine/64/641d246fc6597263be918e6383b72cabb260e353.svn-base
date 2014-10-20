<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách Landing
        </a>
    </li>
</ul>
<div class="func-container">
    <div style="margin-top: 10px;">
        <h5 class="pull-left alert alert-success" style="padding: 10px; width: 33%;" >Tìm thấy tổng số <span style="color: tomato; font-weight: bolder">${listLanding.dataCount}</span> sản phẩm trong <span style="color: tomato; font-weight: bolder">${listLanding.pageCount}</span> trang.</h5>
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${listLanding.pageIndex}"/>
                <jsp:param name="pageCount" value="${listLanding.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center; ">Mã landing</th>
            <th style="vertical-align: middle; text-align: center; ">Tên landing</th>
            <th style="vertical-align: middle; text-align: center; ">Logo</th>
            <th style="vertical-align: middle; text-align: center; ">Màu sắc</th>
            <th style="text-align: center; width: 100px;">
                <button onclick="landing.addlanding();" type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Thêm mới</button>
            </th>
        </tr>   
        <c:if test="${fn:length(listLanding.data) <= 0}">
            <tr><td colspan="5" class="clr-red fs-18">Chưa có landing nào!</td></tr>
        </c:if>
        <c:forEach items="${listLanding.data}" var="landing">
            <tr>
                <td class="text-center"><a href="${baseUrl}/landing/${landing.id}/${text:createAlias(landing.name)}.html">${landing.id}</a></td>
                <td class="text-center">
                    <a href="${baseUrl}/cp/landing.html?landingid=${landing.id}" target="_blank">${landing.name}</a>
                </td>
                <td class="text-center">
                    <img src="${landing.logo}" width="100"  />
                </td>
                <td class="text-center">
                    ${landing.color}
                </td>
                <td style="vertical-align: middle; text-align: center">
                    <button style="width: 100%" onclick="landing.editlanding('${landing.id}')" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span>Sửa</button>
                    <button style="width: 100%; margin-top: 10px;" onclick="landing.removelanding('${landing.id}')" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xoá</button>
                </td>
            </tr>   
        </c:forEach>
    </table>
    <div style="margin-top: 10px;">
        <h5 class="pull-left alert alert-success" style="padding: 10px; width: 33%;" >Tìm thấy tổng số <span style="color: tomato; font-weight: bolder">${listLanding.dataCount}</span> sản phẩm trong <span style="color: tomato; font-weight: bolder">${listLanding.pageCount}</span> trang.</h5>
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${listLanding.pageIndex}"/>
                <jsp:param name="pageCount" value="${listLanding.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>