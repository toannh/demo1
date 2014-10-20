<%-- 
    Document   : list.jsp
    Created on : Jul 14, 2014, 8:18:35 AM
    Author     : PhucTd
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="text" uri="http://chodientu.vn/text" %>
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span><a href="${baseUrl}"> Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username==null?viewer.user.email:viewer.user.username}</a></li>
        <li class="active">Quảng cáo - Khuyến mại</li>
    </ol>
    <h1 class="title-pages">E-Mail Marketing
        <span class="clearfix small">Chỉ với: <span class="clr-red">20 xèng/Email</span>. Duyệt email chậm nhất sau 2h làm việc</span>
    </h1>
    <div class="tabs-content-user">
        <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-soan-va-gui-email-marketing-55452363106.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn soạn và gửi email marketing
            </a></div>
        <ul class="tab-title-content">
            <li><a href="${baseUrl}/user/create-email-marketing.html">Soạn email marketing</a></li>
            <li class="active"><a href="${baseUrl}/user/email-marketing.html">Danh sách email marketing</a></li>
        </ul>
        <div class="tabs-content-block">

            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <c:if test="${fn:length(emails) <= 0}">
                        <div class="cdt-message bg-danger text-center">Chưa có email nào!</div>
                    </c:if>
                    <c:if test="${fn:length(emails) > 0}">
                        <div class="list-table-content table-responsive">
                            <table class="table">
                                <tr class="warning">
                                    <th width="35%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tiêu đề</th>
                                    <th width="15%" valign="middle"><div class="text-center">Số lượng cần gửi / <br>Số đã gửi</div></th>
                                <th width="12%" valign="middle"><div class="text-center">Chi phí</div></th>
                                <th width="13%" valign="middle"><div class="text-center">Tình trạng</div></th>
                                <th width="20%" valign="middle"><div class="text-center">Thao tác</div></th>
                                </tr>
                                <jsp:useBean id="date" class="java.util.Date"></jsp:useBean>
                                <c:forEach items="${emails}" var="email">
                                    <tr>
                                        <td>
                                            <div class="table-content-list">
                                                <a href="javascript:;" onclick="email.previewList('${email.id}')">${email.name}</a>
                                                <jsp:setProperty name="date" property="time" value="${email.createTime}" /> 
                                                <p class="mgt-10">Soạn lúc: <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></p>
                                                <jsp:setProperty name="date" property="time" value="${email.sendTime}" /> 
                                                <p>Kế hoạch gửi: <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></p>
                                                <p>Mã giao dịch: <span class="clr-red">${email.id}</span></p>
                                            </div>
                                        </td>
                                        <td><div class="text-center">${email.senddone}/${fn:length(email.email)}</div></td>
                                        <td><div class="text-center">${text:numberFormat(email.balance)} xèng</div></td>
                                        <td>

                                            <c:set var="currentTime" value="<%= new java.util.Date().getTime()%>" />
                                            <c:if test="${email.active}">
                                                <c:if test="${!email.run}">
                                                    <div class="text-center clr-red">Chưa chạy</div>
                                                </c:if>
                                                <c:if test="${email.run && !email.done}">
                                                    <div class="text-center text-warning">Đang chạy</div>
                                                </c:if>
                                                <c:if test="${email.done}">
                                                    <div class="text-center text-success">Đã xong</div>
                                                </c:if>

                                            </c:if>
                                            <c:if test="${!email.active}">
                                                <div class="text-center text-warning">Chưa duyệt</div>
                                            </c:if>

                                        </td>
                                        <td valign="top" align="center">
                                            <div class="text-center">
                                                <c:if test="${email.run==false}">
                                                    <button class="btn btn-default" onclick="email.selectCustomerEmail('${email.id}')">Chọn danh sách<br>khách hàng cần gửi</button>
                                                    </c:if>

                                                <p>
                                                    <c:if test="${email.run==false || email.active==false}">
                                                        <a href="javascript:;" onclick="email.editEmail('${email.id}')">Sửa </a>
                                                    </c:if>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:if>
                    <hr>
                    <div class="page-ouner clearfix">
                        <span class="pull-left"><strong>Có: ${dataPage.dataCount} kết quả</strong></span>
                        <ul class="pagination pull-right">
                            <c:if test="${dataPage.pageCount > 1}">
                                <!-- Generate first link -->
                                <c:if test="${dataPage.pageIndex!=0}">
                                    <li><a href="${baseUrl}/user/email-marketing.html">«</a></li>
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
                                    <li class="${(dataPage.pageIndex+1)==p ?'active':''}"><a href="${baseUrl}/user/email-marketing.html?page=${p}">${p}</a></li>
                                    </c:forEach>
                                <!--/ End generate link -->
                                <!-- Generate last link -->
                                <c:if test="${(dataPage.pageIndex+1)!=dataPage.pageCount}">
                                    <li><a href="${baseUrl}/user/email-marketing.html?page=${dataPage.pageCount}">»</a></li>
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
