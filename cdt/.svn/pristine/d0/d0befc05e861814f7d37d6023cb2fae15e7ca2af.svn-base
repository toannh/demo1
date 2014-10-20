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
            Danh sách bình luận
        </a>
    </li>
</ul>
<div class="func-container" >
    <div style="margin-top: 20px;">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${text:numberFormat(dataPage.dataCount)} </strong> kết quả <strong>${text:numberFormat(dataPage.pageCount)}</strong> trang.
        </h5>
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center; ">STT</th>
            <th style="vertical-align: middle; text-align: center; ">Thông tin sản phẩm</th>            
            <th style="vertical-align: middle; text-align: center; ">Khách đánh giá</th>
            <th style="vertical-align: middle; text-align: center; ">Hành động</th>
        </tr>     
        <jsp:useBean id="date" class="java.util.Date" />
        <c:forEach items="${dataPage.data}" var="itemreview" varStatus="itemStatus">
            <tr class="${!itemreview.active? 'danger':''}">
                <td>${itemStatus.index+1}</td>
                <td>
                    <div class="col-xs-4 col-sm-5">
                        Mã SP: ${itemreview.itemId}
                        <p><a href="${url:item(itemreview.itemId,itemreview.item.name)}" target="_blank">${itemreview.item.name}</a></p>
                    </div>
                    <div class="col-xs-4 col-sm-5">
                        <p>Người bán: ${itemreview.item.sellerName}</p>
                        <p><img src="${itemreview.item.images[0]}" height="100"/></p>
                    </div>

                </td>
                <td>
                    <p>Tên khách: ${itemreview.user.username}</p>
                    <p>Phone: ${itemreview.user.phone}</p>
                    <p>Địa chỉ IP: ${itemreview.ip}</p>
                    <p>Điểm: ${itemreview.point}</p>
                    <p>Lượt thích: ${itemreview.like}</p>
                    <jsp:setProperty name="date" property="time" value="${itemreview.createTime}" /> 
                    <p>Thời gian tạo: <fmt:formatDate type="date" pattern="hh:mm - dd/MM/yyyy" value="${date}"></fmt:formatDate></p>
                        <p></p>
                    </td>
                    <td style="vertical-align: middle;" class="btn-control text-center">
                        <div class="btn-group" id="${itemreview.id}">
                        <button onclick="itemreview.listitemreviewlike(${itemreview.id})" class="btn btn-primary" style="width: 80px;"><span class="glyphicon glyphicon-list"></span>Like</button>
                        <c:if test="${itemreview.active}">
                            <button id="reviews" onclick="itemreview.changeStatus(${itemreview.itemId},${itemreview.id})" class="btn btn-danger" style="width: 80px;"><span class="glyphicon glyphicon-download"></span>Ẩn</button>
                        </c:if>  
                        <c:if test="${!itemreview.active}">
                            <button id="reviews" onclick="itemreview.changeStatus(${itemreview.itemId},${itemreview.id})" class="btn btn-success" style="width: 80px;"><span class="glyphicon glyphicon-download"></span>Hiện</button>
                        </c:if>
                    </div>
                </td>
            </tr>
        </c:forEach>

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