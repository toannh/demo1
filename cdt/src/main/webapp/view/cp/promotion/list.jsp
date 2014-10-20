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
            Danh sách khuyến mãi
        </a>
    </li>
</ul>
<div class="func-container">  
    <form:form modelAttribute="promotionSearch" method="POST" role="form" style="margin-top: 20px;">
        <div class="col-sm-4">
            <div class="form-group">
                <form:input path="sellerId" type="text" class="form-control" placeholder="Mã người bán"/>
            </div>
            <div class="form-group">
                <form:input path="startTime" type="hidden" class="form-control timeselect" placeholder="Từ ngày"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <form:select path="type" type="text" class="form-control">
                    <form:option value="" label="Kiểu giảm giá" />
                    <form:option value="DISCOUND" label="Giảm giá sản phẩm" />
                    <form:option value="GIFT" label="Giảm giá có quà tặng" />
                </form:select>
            </div>
            <div class="form-group">
                <form:input path="endTime" type="hidden" class="form-control timeselect" placeholder="Đến ngày"/>
            </div>
        </div>
        <div class="col-sm-4">
<!--            <div class="form-group">
                <form:select path="status" type="text" class="form-control">
                    <form:option value="0" label="Trạng thái" />
                    <form:option value="1" label="Chưa chạy" />
                    <form:option value="2" label="Đang chạy" />
                    <form:option value="3" label="Hết hạn" />
                    <form:option value="4" label="Đã hủy" />
                </form:select>
            </div>-->
            <div class="form-group">
                <form:select path="target" type="text" class="form-control">
                    <form:option value="" label="Mục tiêu khuyến mãi" />
                    <form:option value="CATEGORY" label="Danh mục sản phẩm chợ" />
                    <form:option value="ITEM" label="Chi tiết sản phẩm" />
                    <form:option value="SHOP_CATEGORY" label="Danh mục Shop" />
                </form:select>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3" style="margin-bottom:  10px">
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc </button>
            <button type="button" class="btn btn-default" onclick="news.resetForm()"><span class="glyphicon glyphicon-refresh"></span> Nhập lại</button>
        </div>
    </form:form>
    <div class="cms-line"></div>
    <div class="clearfix"></div>
    <div>
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
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center" style="vertical-align: middle" >STT</th>
            <th class="text-center" style="vertical-align: middle">Tên chương trình</th>
            <th class="text-center" style="vertical-align: middle">Người bán</th>
            <th class="text-center" style="vertical-align: middle">Thời gian bắt đầu</th>
            <th class="text-center" style="vertical-align: middle">Thời gian kết thúc</th>
            <th class="text-center" style="vertical-align: middle">Kiểu giảm giá</th>       
            <th class="text-center" style="vertical-align: middle">Mục tiêu</th>
            <th class="text-center" style="vertical-align: middle">Trạng thái</th>   
        </tr>
        <jsp:useBean id="date" class="java.util.Date" />
        <c:set var="currentTime" value="<%= new java.util.Date().getTime()%>" />
        <c:forEach items="${dataPage.data}" var="promotion" varStatus="sttIndex">
            <tr> 
                <td class="text-left" style="vertical-align: middle">${sttIndex.index+1}</td>
                <td class="text-left" style="vertical-align: middle"><a target="_blank" href="${baseUrl}/${promotion.sellerName}/browse.html?promotionId=${promotion.id}">${promotion.name}</td>
                <td class="text-left" style="vertical-align: middle">${promotion.sellerName}</td>
                <td class="text-center" style="vertical-align: middle">
                    <jsp:setProperty name="date" property="time" value="${promotion.startTime}" /> 
                    <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                    </td>
                    <td class="text-center" style="vertical-align: middle">
                    <jsp:setProperty name="date" property="time" value="${promotion.endTime}" /> 
                    <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                    </td>
                    <td class="text-center" style="vertical-align: middle">
                    <c:if test="${promotion.type=='GIFT'}">Có quà tặng</c:if>
                    <c:if test="${promotion.type=='DISCOUND'}">Giảm giá</c:if>
                    </td>
                    <td class="text-center" style="vertical-align: middle">
                    <c:if test="${promotion.target=='ITEM'}">Chi tiết sản phẩm</c:if>
                    <c:if test="${promotion.target=='CATEGORY'}">Danh mục sản phẩm</c:if>
                    <c:if test="${promotion.target=='SHOP_CATEGORY'}">Danh mục Shop</c:if>
                    </td>
                    <td class="text-center" style="vertical-align: middle">
                    <c:if test="${promotion.active==true}">
                        <c:choose>
                            <c:when test="${promotion.startTime > currentTime}">
                                <div class="text-center"><strong>Chưa bắt đầu</strong></div>
                            </c:when>
                            <c:when test="${promotion.endTime < currentTime}">
                                <div class="text-center text-danger"><strong>Hết hạn</strong></div>
                            </c:when>
                            <c:otherwise>    
                                <c:set var="remainTime" value="${promotion.endTime - currentTime}" />
                                <fmt:formatNumber var="seconds" pattern="0" value="${remainTime / (24 * 60 * 60 * 1000)}"/>
                                <div class="text-center text-success"><strong>Còn ${seconds} ngày</strong></div>
                            </c:otherwise>
                        </c:choose> 
                    </c:if>
                    <c:if test="${!promotion.active}">
                        <div class="text-center text-danger"><strong>Huỷ</strong></div>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div style="margin-top: 10px">
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>