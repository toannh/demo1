<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="text" uri="http://chodientu.vn/text" %>
<%@ taglib prefix="url" uri="http://chodientu.vn/url" %>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa"></i>
            Quản trị log người bán
        </a>
    </li>
</ul>
<div class="func-container" > 
    <form:form modelAttribute="sellerHistorySearch" cssClass="form-vertical" method="POST" role="form" style="margin-top: 20px;">        
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="sellerId" type="text" class="form-control" placeholder="Mã người bán"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:select path="first" type="text" class="form-control">
                    <form:option value="0" label="Tất cả hoạt động" />
                    <form:option value="1" label="Hoạt động lần đầu" />
                </form:select>
            </div>
        </div>
        <div class="col-sm-4">  

            <div class="form-group">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Tìm log hoạt động</button>
            </div>
        </div>
    </form:form>
    <div class="cms-line"></div>
    <div class="clearfix"></div>

    <c:if test="${page == null}">
        <div style="margin-top: 10px">
        </div>
        <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
            <tr>
                <td class="text-center danger">Nhập mã người bán và click nút tìm kiếm!</td>
            </tr>    
        </table>
    </c:if>
    <c:if test="${page != null}">
        <div style="margin-top: 10px;">
            <h5 class="pull-left" style="padding: 10px; width: 33%;" >
                Tìm thấy <strong>${text:numberFormat(page.dataCount)} </strong> kết quả <strong>${text:numberFormat(page.pageCount)}</strong> trang.
            </h5> 
            <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
                <jsp:include page="/view/cp/widget/paging.jsp">
                    <jsp:param name="pageIndex" value="${page.pageIndex}"/>
                    <jsp:param name="pageCount" value="${page.pageCount}"/>
                </jsp:include>
            </div>
            <div class="clearfix"></div>
        </div>
        <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
            <tr>
                <th class="text-center">Mã người bán</th>
                <th class="text-center">Mã hoạt động</th>
                <th class="text-center">Thông tin log</th>
                <th class="text-center">Lần đầu</th>
                <th class="text-center">Thời gian cụ thể</th>
            </tr> 
            <c:if test="${page.dataCount <= 0}">
                <tr>
                    <td class="text-center danger"  colspan="4">Người bán này chưa có lịch sử log nào!</td>
                </tr>  
            </c:if>
            <c:if test="${page.dataCount > 0}">
                <c:forEach var="seller" items="${page.data}" >
                    <jsp:useBean id="date" class="java.util.Date" />
                    <tr>
                        <td class="text-center">${seller.sellerId}</td>
                        <td class="text-center">${seller.objectId}</td>
                        <td>
                            ${seller.message}
                        </td>
                        <td class="text-center">
                                ${!seller.first?'<i class="glyphicon glyphicon-unchecked"></i>':'<i class="glyphicon glyphicon-check"></i>'}
                            </td>
                            <td class="text-center">
                                <p>   
                                <jsp:setProperty name="date" property="time" value="${seller.updateTime}" />
                                <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                                </p>
                            </td>
                        </tr>  
                </c:forEach>
            </c:if>
        </table>
        <div style="margin-top: 10px; margin-bottom:10px">
            <div class="btn-toolbar pull-right">
                <jsp:include page="/view/cp/widget/paging.jsp">
                    <jsp:param name="pageIndex" value="${page.pageIndex}"/>
                    <jsp:param name="pageCount" value="${page.pageCount}"/>
                </jsp:include>
            </div>
            <div class="clearfix"></div>
        </div>
    </c:if>  
</div>