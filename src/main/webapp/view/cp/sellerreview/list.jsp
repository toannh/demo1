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
            <i class="fa fa-list"></i>
            Danh sách đánh giá
        </a>
    </li>
</ul>
<div class="func-container">
    <div class="clearfix"></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${dataPage.dataCount} </strong> kết quả <strong>${dataPage.pageCount}</strong> trang.
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
            <th style="vertical-align: middle; text-align: center; ">STT</th>
            <th style="vertical-align: middle; text-align: center; ">Thông tin sản phẩm</th>            
            <th style="vertical-align: middle; text-align: center; ">Khách đánh giá</th>
            <th style="vertical-align: middle; text-align: center; ">Hành động</th>
        </tr>     
        <jsp:useBean id="date" class="java.util.Date" />
        <c:forEach items="${dataPage.data}" var="sellerreview" varStatus="itemStatus">
            <tr class="${!sellerreview.active? 'danger':''}">
                <td>${itemStatus.index+1}</td>
                <td>
                    <div class="col-xs-4 col-sm-5">
                        Mã ĐH: ${sellerreview.orderId}
                    </div>
                    <div class="col-xs-4 col-sm-5">
                        <p>Người mua: ${sellerreview.userNameBuyer}</p>
                        <p>Người bán: ${sellerreview.userNameSeller}</p>
                    </div>
                    <div class="clearfix"></div>
                    <p>Đánh giá: ${sellerreview.content}</p>
                </td>
                <td>
                    <p>Tên khách: ${sellerreview.userId}</p>
                    <p>Địa chỉ IP: ${sellerreview.ip}</p>
                    <p>Độ uy tín: 
                        <c:if test="${sellerreview.point==1}">Không hài lòng</c:if>
                        <c:if test="${sellerreview.point==2}">Bình thường</c:if>
                        <c:if test="${sellerreview.point==3}">Tốt</c:if>
                        </p>
                    <jsp:setProperty name="date" property="time" value="${sellerreview.createTime}" /> 
                    <p>Thời gian tạo: <fmt:formatDate type="date" pattern="hh:mm - dd/MM/yyyy" value="${date}"></fmt:formatDate></p>
                    </td>
                    <td style="vertical-align: middle;" class="btn-control text-center">
                        <div class="btn-group" id="${sellerreview.id}">
                        <c:if test="${sellerreview.active}">
                            <button id="reviews" onclick="sellerreview.changeStatus(${sellerreview.id})" class="btn btn-danger" style="width: 80px;"><span class="glyphicon glyphicon-download"></span>Ẩn</button>
                        </c:if>  
                        <c:if test="${!sellerreview.active}">
                            <button id="reviews" onclick="sellerreview.changeStatus(${sellerreview.id})" class="btn btn-success" style="width: 80px;"><span class="glyphicon glyphicon-download"></span>Hiện</button>
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