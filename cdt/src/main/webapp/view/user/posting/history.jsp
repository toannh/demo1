<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://chodientu.vn/text" prefix="text" %>
<%@taglib uri="http://chodientu.vn/url" prefix="url" %>


<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span><a href="${baseUrl}"> Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a></li>
        <li class="active">Quảng cáo - Khuyến mại</li>
    </ol>
    <h1 class="title-pages">Quảng cáo - Khuyến mại</h1>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li><a href="${baseUrl}/user/posting.html">Quản lý tin UP</a></li>
            <li class="active"><a href="${baseUrl}/user/postinghistory.html">Lịch sử UP tin</a></li>
        </ul>
        <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-uptin-14771506822.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn up tin
            </a></div>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="title-config-shop list-table-content">
                        <div class="row">  
                            <div class="col-lg-6 col-md-5 col-sm-5 col-xs-12 reset-padding-all">
                                <div class="form-horizontal"><label class="control-label">Tổng số lượt đã up: <strong>${dataPage.dataCount}</strong> lượt</label></div>
                            </div>                                  	
                            <div class="col-lg-6 col-md-7 col-sm-7 col-xs-12">
                                <form:form modelAttribute="postItemSearch" method="POST" role="form">
                                    <div class="form-group">
                                        <label class="col-sm-2 col-xs-3 control-label reset-padding">Lọc từ:</label>
                                        <div class="col-sm-3 col-xs-9 reset-padding">
                                            <div class="date-picker-block">
                                                <input placeholder="Từ" name="startTime" type="hidden" value="${postItemSearch.startTime > 0 ?postItemSearch.startTime:''}" class="startTime form-control hasDatepicker">
                                                <span class="glyphicon glyphicon-calendar"></span>     
                                            </div>
                                        </div>
                                        <label class="col-sm-1 col-xs-3 control-label reset-padding">Tới:</label>
                                        <div class="col-sm-3 col-xs-9 reset-padding">
                                            <div class="date-picker-block">
                                                <input placeholder="Đến" name="endTime" type="hidden" value="${postItemSearch.endTime > 0 ?postItemSearch.endTime:''}" class="endTime form-control hasDatepicker">
                                                <span class="glyphicon glyphicon-calendar"></span>     
                                            </div> 
                                        </div>
                                        <div class="col-sm-3 col-xs-12 reset-padding-all"><button type="submit" class="btn btn-default">Tìm kiếm</button></div>    
                                    </div>
                                </form:form>
                            </div>
                        </div>
                        <hr class="line-hr">    
                    </div>
                    <c:if test="${dataPage.dataCount <= 0}">
                        <div class="cdt-message bg-danger text-center">Không tìm thấy lịch sử up tin nào!</div>
                    </c:if>
                    <c:if test="${dataPage.dataCount > 0}">
                        <div class="list-table-content table-responsive">
                            <table class="table" width="100%">
                                <tbody>
                                    <tr class="warning" >
                                        <th width="25%" class="text-left" >Thời điểm UP</th>
                                        <th class="text-left" >Sản phẩm</th>
                                    </tr>
                                    <jsp:useBean id="date" class="java.util.Date" />
                                    <c:forEach items="${dataPage.data}" var="ups" varStatus="loop">
                                        <c:set var="flag" value="true"></c:set>
                                        <c:forEach items="${items}" var="item">
                                            <c:if test="${item.id == ups.itemId  && flag}" >
                                                <c:set var="flag" value="false"></c:set>
                                                    <tr>
                                                        <td valign="top" align="center"><div class="text-left">
                                                            <jsp:setProperty name="date" property="time" value="${ups.scheduleTime}" /> 
                                                            <strong><fmt:formatDate type="date" pattern="H:mm"  value="${date}"></fmt:formatDate></strong>
                                                            <fmt:formatDate value="${date}" type="date" pattern="E" var="day"></fmt:formatDate></span>
                                                            <c:choose>
                                                                <c:when test="${day=='Mon'}">
                                                                    Thứ hai,
                                                                </c:when>
                                                                <c:when test="${day=='Tue'}">
                                                                    Thứ ba,
                                                                </c:when>
                                                                <c:when test="${day=='Wed'}">
                                                                    Thứ tư,
                                                                </c:when>
                                                                <c:when test="${day=='Thu'}">
                                                                    Thứ năm,
                                                                </c:when>
                                                                <c:when test="${day=='Fri'}">
                                                                    Thứ sáu,
                                                                </c:when>
                                                                <c:when test="${day=='Sat'}">
                                                                    Thứ bảy,
                                                                </c:when>
                                                                <c:when test="${day=='Sat'}">
                                                                    Chủ nhật,
                                                                </c:when>
                                                            </c:choose>
                                                            <fmt:formatDate type="date" pattern="dd.MM.yyyy"  value="${date}"></fmt:formatDate></div>
                                                        </td>
                                                        <td>
                                                            <div class="table-content">
                                                                <div class="img-product-bill-small">
                                                                    <a href="${url:item(item.id, item.name)}"><img src="${item.images[0]}"></a>
                                                            </div>
                                                            <a href="${url:item(item.id, item.name)}">${item.name}</a>
                                                        </div>
                                                    </td>                                    
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                    <hr>
                    <div class="page-ouner clearfix">
                        <span class="pull-left">Tổng số lượt đã up: <strong>${dataPage.dataCount}</strong> lượt</span>
                        <ul class="pagination pull-right">
                            <c:if test="${dataPage.pageCount > 1}">
                                <!-- Generate first link -->
                                <c:if test="${dataPage.pageIndex!=0}">
                                    <li><a href="${baseUrl}/user/postinghistory.html">«</a></li>
                                    </c:if>
                                <!--/ End first link -->
                                <!-- Number of page to display -->
                                <c:set var="displayLink" value="3"></c:set>
                                    <!-- Set begin link and end link -->
                                <c:if test="${dataPage.pageIndex==0}">
                                    <c:set value="1" var="beginLink"></c:set>
                                    <c:set value="${dataPage.pageCount}" var="endLink"></c:set>
                                </c:if>
                                <c:if test="${dataPage.pageIndex!=0}">
                                    <c:set value="${dataPage.pageIndex}" var="beginLink"></c:set>
                                    <c:set value="${dataPage.pageIndex+2}" var="endLink"></c:set>
                                </c:if>
                                <c:if test="${(dataPage.pageIndex+1)==dataPage.pageCount}">
                                    <c:if test="${dataPage.pageIndex==1}">
                                        <c:set value="${dataPage.pageCount-displayLink+2}" var="beginLink"></c:set>
                                    </c:if>
                                    <c:if test="${dataPage.pageIndex!=1}">
                                        <c:set value="${dataPage.pageCount-displayLink+1}" var="beginLink"></c:set>
                                    </c:if>
                                    <c:set value="${dataPage.pageCount}" var="endLink"></c:set>
                                </c:if>
                                <!--/ End set begin and end link -->
                                <!-- Generate link to other page -->
                                <c:forEach begin="${beginLink}" end="${endLink}" step="1" var="p">
                                    <li class="${(dataPage.pageIndex+1)==p ?'active':''}"><a href="${baseUrl}/user/postinghistory.html?page=${p}">${p}</a></li>
                                    </c:forEach>
                                <!--/ End generate link -->
                                <!-- Generate last link -->
                                <c:if test="${(dataPage.pageIndex+1)!=dataPage.pageCount}">
                                    <li><a href="${baseUrl}/user/postinghistory.html?page=${dataPage.pageCount}">»</a></li>
                                    </c:if>
                                <!--/ End last link -->
                            </c:if>
                        </ul>
                    </div>                            
                </div>                            
            </div>                     
        </div>   
    </div>
</div>