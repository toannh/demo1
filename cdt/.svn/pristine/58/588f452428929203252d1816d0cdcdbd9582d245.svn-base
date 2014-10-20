<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách lịch sử giao dịch ShipChung
        </a>
    </li>
</ul>
<div class="func-container">
    <div class="clearfix"></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left alert alert-success" style="padding: 10px;" >Tìm thấy tổng số <span style="color: tomato; font-weight: bolder">${scHistoryData.dataCount} </span> email trong <span style="color: tomato; font-weight: bolder">${scHistoryData.pageCount}</span> trang.</h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${scHistoryData.pageIndex}"/>
                <jsp:param name="pageCount" value="${scHistoryData.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center">Id</th>
            <th class="text-center">Url</th>
            <th class="text-center">Function</th>
            <th class="text-center" style="width: 200px" > Chức năng </th>
        </tr>        
        <c:forEach var="schistory" items="${scHistoryData.data}">   
            <tr>
                <td class="text-center">
                    ${schistory.id}
                </td>
                <td class="text-center" >
                    ${schistory.url}
                </td>
                <td>
                    <c:forEach items="${schistory.params}" var="entry">
                        <c:if test="${entry.key == 'function'}">
                            ${entry.value}
                        </c:if>
                    </c:forEach>
                </td>

                <td class="text-center"  style="vertical-align: middle" >
                    <button title="Chi tiết" type="button" class="btn btn-success"  onclick="schistory.detailParams('${schistory.id}');" >
                        <span class="glyphicon glyphicon-list"></span>Xem chi tiết
                    </button>
                </td>
            </tr>      
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${scHistoryData.pageIndex}"/>
                <jsp:param name="pageCount" value="${scHistoryData.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>