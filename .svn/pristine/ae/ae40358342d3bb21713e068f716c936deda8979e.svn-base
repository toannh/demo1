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
            Quản trị lịch sử sản phẩm
        </a>
    </li>
</ul>
<div class="func-container" > 
    <form:form modelAttribute="search" cssClass="form-vertical" method="POST" role="form" style="margin-top: 20px;">        
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="itemId" type="text" class="form-control" placeholder="Mã sản phẩm"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Tìm lịch sử</button>
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
                <td class="text-center">Nhập mã sản phẩm và click nút tìm kiếm!</td>
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
                <th class="text-center">Mã sp</th>
                <th class="text-center">Thông tin log</th>
            </tr> 
            <c:if test="${page.dataCount <= 0}">
                <tr>
                    <td class="text-center danger"  colspan="2">Sản phẩm này chưa có lịch sử log nào!</td>
                </tr>  
            </c:if>
            <c:if test="${page.dataCount > 0}">
                <c:forEach var="item" items="${page.data}" >
                    <jsp:useBean id="date" class="java.util.Date" />
                    <tr>
                        <td class="text-center">${item.itemId}</td>
                        <td>
                            <p>${item.admin?'Admin':'Người bán'}:${item.admin?item.adminEmail:item.sellerName}
                                <b>
                                    <c:choose>
                                        <c:when test="${item.itemHistoryType == 'DELETE'}">xóa</c:when>
                                        <c:when test="${item.itemHistoryType == 'CREATE'}">đăng</c:when>
                                        <c:when test="${item.itemHistoryType == 'EDIT'}">sửa</c:when>
                                        <c:otherwise>cập nhật</c:otherwise>
                                    </c:choose>
                                </b>
                                sản phẩm lúc  <jsp:setProperty name="date" property="time" value="${item.createTime}" />
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