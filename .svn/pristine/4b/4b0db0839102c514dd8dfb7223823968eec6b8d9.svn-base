<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách email
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="search" cssClass="form-vertical" method="POST" role="form" style="margin-top: 20px;">        
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="email" type="text" class="form-control" placeholder="Email"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc email</button>
                <button type="button" class="btn btn-success" onclick="email.exportEmailNewsletter();"><span class="glyphicon glyphicon-list"></span> Xuất excel</button>
            </div>
        </div>
    </form:form>
    <div class="cms-line"></div>
    <div class="clearfix"></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${page.dataCount} </strong> kết quả <strong>${page.pageCount}</strong> trang.
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
            <th class="text-center">Email</th>
            <th class="text-center">Thời gian đăng ký</th>
            <th class="text-center">Chức năng</th>
        </tr>    
        <c:if test="${page.dataCount <= 0}">
            <tr>
                <td colspan="3" class="text-center danger">Không tìm thấy email nào!</td> 
            </tr>
        </c:if>
        <c:if test="${page.dataCount > 0}">
            <c:forEach var="email" items="${page.data}">
                <jsp:useBean id="date" class="java.util.Date" />
                <tr>
                    <td>${email.email}</td>
                    <td>
                        <p>
                            <jsp:setProperty name="date" property="time" value="${email.createTime}" />
                            <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                            </p>
                        </td>
                        <td class="text-center" style="vertical-align: middle">
                            <button type="button" class="btn btn-danger" onclick="email.delEmailNewsletter('${email.email}');"><span class="glyphicon glyphicon-remove"></span> Xóa</button>
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
</div>