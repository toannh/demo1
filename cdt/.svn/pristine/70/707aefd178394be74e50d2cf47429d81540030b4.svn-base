<%-- 
    Document   : listsms.jsp
    Created on : Jul 14, 2014, 8:37:25 AM
    Author     : TheHoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="text" uri="http://chodientu.vn/text" %>

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username != null?viewer.user.username:viewer.user.email}</a></li>
        <li class="active">Quảng cáo - Khuyến mại</li>
    </ol>
    <h1 class="title-pages">SMS Marketing <span class="clearfix small">Chỉ với: <span class="clr-red">100 xèng/SMS</span>. Duyệt tin nhắn chậm nhất sau 2h làm việc</span></h1>
    <div class="tabs-content-user">
        <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-soan-va-gui-sms-714567998304.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn soạn và gửi SMS
            </a></div>
        <ul class="tab-title-content">
            <li><a href="${baseUrl}/user/create-sms-marketing.html">Soạn tin nhắn SMS</a></li>
            <li class="active"><a href="${baseUrl}/user/sms-marketing.html">Danh sách tin nhắn SMS</a></li>
        </ul>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <c:if test="${fn:length(pageSms.data) <=0}">
                        <div class="cdt-message bg-danger text-center">Không tìm thấy sms nào!</div>
                    </c:if>
                    <c:if test="${fn:length(pageSms.data) > 0}">
                        <div class="list-table-content table-responsive">
                            <table class="table" width="100%">
                                <tbody>
                                    <tr class="warning">
                                        <th width="5%" style="text-align: center">STT</th>
                                        <th width="35%">Tin nhắn SMS</th>
                                        <th width="15%" valign="middle"><div class="text-center">Số SMS gửi / Số đã gửi</div></th>
                                <th width="12%" valign="middle"><div class="text-center">Chi phí</div></th>
                                <th width="13%" valign="middle"><div class="text-center">Tình trạng</div></th>
                                <th width="13%" valign="middle"><div class="text-center">Thao tác</div></th>
                                </tr>
                                <c:forEach var="sms" items="${pageSms.data}" varStatus="stt">
                                    <jsp:useBean id="date" class="java.util.Date" />
                                    <tr>
                                        <td valign="top" align="center" style="width: 20px">${stt.index + 1}</td>
                                        <td>
                                            <div class="table-content-list">
                                                <h6 class="title-sms cdt-tooltip" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="${sms.content}"><a href="javascript:;">${sms.name}</a></h6>
                                                <p>Soạn lúc:  <jsp:setProperty name="date" property="time" value="${sms.createTime}" />
                                                    <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></p>
                                                <p>Kế hoạch gửi: <jsp:setProperty name="date" property="time" value="${sms.sendTime}" />
                                                    <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></p>
                                                <p>Mã giao dịch: <span class="clr-red">${sms.id}</span></p>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="text-center">${sms.senddone} / ${fn:length(sms.phone)} </div>
                                        </td>
                                        <td><div class="text-center">${text:numberFormat(sms.balance)}<br>xèng</div></td>
                                        <td>
                                            <c:if test="${sms.active}">
                                                <c:if test="${!sms.run}">
                                                    <div class="text-center clr-red">Chưa chạy</div>
                                                </c:if>
                                                <c:if test="${sms.run && !sms.done}">
                                                    <div class="text-center text-warning">Đang chạy</div>
                                                </c:if>
                                                <c:if test="${sms.done}">
                                                    <div class="text-center text-success">Đã xong</div>
                                                </c:if>

                                            </c:if>
                                            <c:if test="${!sms.active}">
                                                <div class="text-center text-warning">Chưa duyệt</div>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${!sms.run || !sms.active}">
                                                <div class="text-center"><button type="button" class="btn btn-default btn-sm" onclick="email.editSmsMarketing('${sms.id}');">Sửa</button></div>
                                            </c:if>
                                            <c:if test="${sms.run && !sms.done}">
                                                <div class="text-center"><button type="button" class="btn btn-default btn-sm" disabled="">Sửa</button></div>
                                            </c:if>
                                            <c:if test="${sms.done && sms.run}">
                                                <div class="text-center"><button type="button" class="btn btn-default btn-sm" disabled="">Sửa</button></div>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>


                                </tbody>
                            </table>
                        </div>
                    </c:if>
                    <hr>
                    <div class="page-ouner clearfix">
                        <span class="pull-left"><strong>Có: ${pageSms.dataCount} kết quả</strong></span>
                        <c:if test="${pageSms.dataCount > 0}">
                            <ul class="pagination pull-right">
                                <c:if test="${pageSms.pageIndex > 3}"><li><a href="${baseUrl}/user/sms-marketing.html?page=1" href="javascript:;"><<</a></li></c:if>
                                <c:if test="${pageSms.pageIndex > 2}"><li><a href="${baseUrl}/user/sms-marketing.html?page=${pageSms.pageIndex}" ><</a></li></c:if>
                                <c:if test="${pageSms.pageIndex > 3}"><li><a>...</a></li></c:if>
                                <c:if test="${pageSms.pageIndex >= 3}"><li><a href="${baseUrl}/user/sms-marketing.html?page=${pageSms.pageIndex - 2}">${pageSms.pageIndex-2}</a></li></c:if>
                                <c:if test="${pageSms.pageIndex >= 2}"><li><a href="${baseUrl}/user/sms-marketing.html?page=${pageSms.pageIndex - 1}" >${pageSms.pageIndex-1}</a></li></c:if>
                                <c:if test="${pageSms.pageIndex >= 1}"><li><a href="${baseUrl}/user/sms-marketing.html?page=${pageSms.pageIndex}" >${pageSms.pageIndex}</a></li></c:if>
                                <li class="active" ><a class="btn btn-primary">${pageSms.pageIndex + 1}</a>
                                <c:if test="${pageSms.pageCount - pageSms.pageIndex > 1}"><li><a href="${baseUrl}/user/sms-marketing.html?page=${pageSms.pageIndex+2}" >${pageSms.pageIndex+2}</a></li></c:if>
                                <c:if test="${pageSms.pageCount - pageSms.pageIndex > 2}"><li><a href="${baseUrl}/user/sms-marketing.html?page=${pageSms.pageIndex+3}" >${pageSms.pageIndex+3}</a></li></c:if>
                                <c:if test="${pageSms.pageCount - pageSms.pageIndex > 3}"><li><a >...</a></c:if>
                                <c:if test="${pageSms.pageCount - pageSms.pageIndex > 2}"><li><a href="${baseUrl}/user/sms-marketing.html?page=${pageSms.pageIndex+2}" >></a></li></c:if>
                                <c:if test="${pageSms.pageCount - pageSms.pageIndex > 2}"><li><a href="${baseUrl}/user/sms-marketing.html?page=${pageSms.pageCount}" >>></a></li></c:if>
                                </ul>
                        </c:if>
                    </div>
                </div>                            
            </div>                     
        </div>   
    </div>
</div>