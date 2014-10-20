<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa"></i>
            Quản trị cảnh báo sản phẩm
        </a>
    </li>
</ul>
<div class="func-container" >
    <form:form modelAttribute="search" role="form" style="margin-top:20px;" cssClass="form-vertical">
        <div class="col-sm-4">
            <div class="form-group">
                <form:select path="type" class="form-control">
                    <form:option value="" label="Tất cả loại cảnh báo"/>
                    <form:option value="OUTOFSTOCK" label="Cảnh báo hết hàng"/>
                    <form:option value="WRONG_PRICE" label="Cảnh báo sai giá"/> 
                </form:select>
            </div>
        </div>
        <div class="col-sm-6">
            <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-search" ></i> Tìm kiếm</button>
        </div>
        <div class="clearfix" style="margin-bottom: 20px;"></div>
    </form:form>
    <div class="cms-line"></div>
    <div class="clearfix"></div>
    <div style="margin-top: 10px;">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${text:numberFormat(dataPage.dataCount)} </strong> kết quả <strong>${text:numberFormat(dataPage.pageCount)}</strong> trang.
        </h5> 
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>            
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center; ">Người cảnh báo</th>            
            <th style="vertical-align: middle; text-align: center; ">Loại cảnh báo</th>
            <th style="vertical-align: middle; text-align: center; ">Thông tin sản phẩm</th>
        </tr>     
        <jsp:useBean id="date" class="java.util.Date" />
        <c:if test="${dataPage.dataCount <= 0}">
            <tr>
                <td colspan="3" class="text-center" style="color: red">Không tìm thấy cảnh báo nào!</td>
            </tr>
        </c:if>
        <c:if test="${dataPage.dataCount > 0}">
            <c:forEach var="warning" items="${dataPage.data}">
                <tr>
                    <td>
                        <p>Email: ${warning.userEmail}</p>
                        <p>Tên: ${warning.userName}</p>
                    </td>
                    <td>
                        ${warning.type=='OUTOFSTOCK'?'Báo hết hàng':'Báo sai giá'}
                    </td>
                    <td>
                        <p>Mã SP:<a href="${url:item(warning.itemId,warning.itemName)}" target="_blank">${warning.itemId}</a></p>
                        <p>Mã người bán: ${warning.sellerId}</p>
                        <p>Tên người bán: ${warning.sellerName}</p>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <div style="margin-top: 10px; margin-bottom:20px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div><div class="clearfix"></div>
    </div>
</div>