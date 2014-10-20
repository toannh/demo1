<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${param.pageCount > 0}">
    <div style="float:left;">
        Tới Trang: 
        <select name="gopage" class="form-control" style="float: none; margin: -26px 0px 0px 75px;">  
            <c:forEach begin="0" end="${param.pageCount-1}" var="page">    
                <option <c:if test="${userSearch.pageIndex == page}">selected</c:if> value="${page}">${page+1}</option>                
            </c:forEach>
        </select>
    </div>
<ul class="pagination pull-right">    
    <c:if test="${param.pageIndex > 2&& param.pageCount > 3}">
        <li onclick="changePageIndex(0);"><a href="javascript:void(0);">Đầu</a>
    </c:if>
    <c:if test="${param.pageIndex >= 1&& param.pageCount > 3}">
        <li onclick="changePageIndex(${param.pageIndex-1});"><a href="javascript:void(0);">Trước</a>    
    </c:if>
   <c:choose>  
        <c:when test="${param.pageCount > 3}"> 
            <c:if test="${param.pageIndex > 2}">
                <li><a>...</a></li> 
            </c:if>
            <c:choose>
                <c:when test="${param.pageIndex >= 2}">
                    <c:if test="${param.pageCount-param.pageIndex >= 3}">
                        <c:forEach begin="${param.pageIndex-2}" end="${param.pageIndex+2}" var="page" varStatus="key">                        
                            <li <c:if test="${param.pageIndex == key.index}">class="active"</c:if> onclick="changePageIndex(${key.index});">
                                <a href="javascript:void(0);">${page+1}</a>
                            </li>                                    
                        </c:forEach>
                    </c:if>
                    <c:if test="${param.pageCount-param.pageIndex < 3}">
                        <c:forEach begin="${param.pageIndex-2}" end="${param.pageIndex+(param.pageCount-param.pageIndex-1)}" var="page" varStatus="key">                        
                            <li <c:if test="${param.pageIndex == key.index}">class="active"</c:if> onclick="changePageIndex(${key.index});">
                                <a href="javascript:void(0);">${page+1}</a>
                            </li>                                    
                        </c:forEach>
                    </c:if>
                </c:when>
                <c:otherwise>
                   <c:forEach begin="0" end="${param.pageIndex+2}" var="page" varStatus="key">                        
                        <li <c:if test="${param.pageIndex == key.index}">class="active"</c:if> onclick="changePageIndex(${key.index});">
                            <a href="javascript:void(0);">${page+1}</a>
                        </li>                                    
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <c:if test="${param.pageCount-param.pageIndex > 3}">
                <li><a>...</a></li>                        
            </c:if>
        </c:when>  
        <c:otherwise>
            <c:forEach begin="0" end="${param.pageCount-1}" var="page" varStatus="key">                        
                <li <c:if test="${param.pageIndex == key.index}">class="active"</c:if> onclick="changePageIndex(${key.index});">
                    <a href="javascript:void(0);">${page+1}</a>
                </li>
            </c:forEach>
        </c:otherwise>  
    </c:choose>       
    <c:if test="${param.pageCount > 3&& param.pageCount-param.pageIndex >= 2}">
        <li onclick="changePageIndex(${param.pageIndex+1});" class="next"><a href="javascript:void(0);">Sau</a></li>
    </c:if>
    <c:if test="${param.pageCount-param.pageIndex >= 2&& param.pageCount > 3}">
        <li onclick="changePageIndex(${param.pageCount-1});"><a href="javascript:void(0);">Cuối</a></li>
    </c:if>
</ul>
</c:if>