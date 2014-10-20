<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="btn-group">
    <c:if test="${param.pageIndex > 3}"><a href="?page=1" class="btn btn-default"><<</a></c:if>
    <c:if test="${param.pageIndex > 2}"><a href="?page=${param.pageIndex}" class="btn btn-default"><</a></c:if>
    <c:if test="${param.pageIndex > 3}"><a class="btn btn-default">...</a></c:if>
    <c:if test="${param.pageIndex >= 3}"><a href="?page=${param.pageIndex-2}" class="btn btn-default">${param.pageIndex-2}</a></c:if>
    <c:if test="${param.pageIndex >= 2}"><a href="?page=${param.pageIndex-1}" class="btn btn-default">${param.pageIndex-1}</a></c:if>
    <c:if test="${param.pageIndex >= 1}"><a href="?page=${param.pageIndex}" class="btn btn-default">${param.pageIndex}</a></c:if>
    <a class="btn btn-primary">${param.pageIndex + 1}</a>
    <c:if test="${param.pageCount - param.pageIndex >= 2}"><a href="?page=${param.pageIndex+2}" class="btn btn-default">${param.pageIndex+2}</a></c:if>
    <c:if test="${param.pageCount - param.pageIndex > 3}"><a href="?page=${param.pageIndex+3}" class="btn btn-default">${param.pageIndex+3}</a></c:if>
    <c:if test="${param.pageCount - param.pageIndex > 4}"><a class="btn btn-default">...</a></c:if>
    <c:if test="${param.pageCount - param.pageIndex > 2}"><a href="?page=${param.pageIndex+2}" class="btn btn-default">></a></c:if>
    <c:if test="${param.pageCount - param.pageIndex > 2}"><a href="?page=${param.pageCount}"  class="btn btn-default">>></a></c:if>
</div>
<div class="btn-group">
    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
        Tới trang
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu pre-scrollable">
        <c:forEach begin="1" end="${param.pageCount}" var="index">
            <li><a href="?page=${index}">${index}</a></li>
        </c:forEach>
    </ul>
</div>