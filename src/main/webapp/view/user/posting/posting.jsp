<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://chodientu.vn/url" prefix="url" %>

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span><a href="${baseUrl}"> Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a></li>
        <li class="active">Quảng cáo - Khuyến mại</li>
    </ol>
    <h1 class="title-pages">Quản lý up tin</h1>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-uptin-14771506822.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn up tin
        </a></div>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li class="active"><a href="${baseUrl}/user/posting.html">Quản lý up tin</a></li>
            <li><a href="${baseUrl}/user/postinghistory.html">Lịch sử UP tin</a></li>
        </ul>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="sub-tab-content">
                        <div class="form-inline">
                            <div class="form-group">Tình trạng:</div>
                            <div class="form-group">
                                <select class="form-control" name="run" onchange="upschedule.search();">
                                    <option value="0">Toàn bộ</option>
                                    <option value="1" <c:if test="${run == 1}">selected=""</c:if>>Đang chạy</option>
                                    <option value="2" <c:if test="${run == 2}">selected=""</c:if>>Chưa chạy</option>
                                    <option value="3" <c:if test="${run == 3}">selected=""</c:if>>Đã xong</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    <c:if test="${dataPage.dataCount <= 0}">
                        <div class="cdt-message bg-danger text-center">Không tìm thấy thông tin yêu cầu!</div>
                    </c:if>
                    <c:if test="${dataPage.dataCount > 0}">
                        <div class="list-table-content table-responsive margin-top-fix">
                            <table class="table" width="100%">
                                <tbody>
                                    <tr class="warning">
                                        <th width="43%">
                                            &nbsp; Tin Up</th>
                                        <th width="15%" valign="middle"><div class="text-left">Lịch up</div></th>
                                <th width="15%" valign="middle"><div class="text-center">Số lượt mua</div></th>
                                <th width="20%" valign="middle"><div class="text-left">Tình trạng</div></th>
                                </tr>
                                <jsp:useBean id="date" class="java.util.Date" />
                                <jsp:useBean id="dateNow" class="java.util.Date" />
                                <c:forEach items="${dataPage.data}" var="ups">
                                    <c:set var="flag" value="true" />
                                    <c:forEach items="${items}" var="item">
                                        <c:if test="${item.id == ups.itemId && flag}" >
                                            <c:set var="flag" value="false" />
                                            <tr>
                                                <td>
                                                    <div class="table-content">
                                                        <span class="img-list-tinvip"><img src="${item.images[0]}" class="img-responsive"></span>
                                                        <a href="${url:item(item.id, item.name)}">${item.name}</a>
                                                        <p class="mgt-15">ID: <span class="clr-red">${item.id}</span></p>
                                                        <p>Tạo lịch: 
                                                            <jsp:setProperty name="date" property="time" value="${ups.createTime}" /> 
                                                            <fmt:formatDate type="date" pattern="H:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                                                            </p>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="text-left">
                                                            <p><strong>Up theo lịch</strong></p>
                                                        ${fn:length(ups.upTime)} lần/ngày<br>
                                                        <c:forEach items="${ups.upDay}" var="day" varStatus="loop">
                                                            <c:if test="${loop.index==0}">
                                                                <c:if test="${day!=1}">Thứ </c:if>
                                                            </c:if>
                                                            <c:if test="${day==1}">chủ nhật</c:if>
                                                            <c:if test="${day==2}">2</c:if>
                                                            <c:if test="${day==3}">3</c:if>
                                                            <c:if test="${day==4}">4</c:if>
                                                            <c:if test="${day==5}">5</c:if>
                                                            <c:if test="${day==6}">6</c:if>
                                                            <c:if test="${day==7}">7</c:if>
                                                        </c:forEach>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="text-center">
                                                        <p><strong>${ups.useQuantity}/${ups.quantity}</strong></p>
                                                    </div>
                                                </td>
                                                <td>
                                                    <c:set var="currentTime" value="<%= new java.util.Date().getTime()%>" />
                                                    <jsp:setProperty name="date" property="time" value="${currentTime}" /> 
                                                    <c:if test="${ups.run==false}">
                                                        <p class="clr-red">
                                                            <strong>
                                                                Chưa chạy 
                                                            </strong>
                                                        </p>
                                                        <p>Lượt tới:<br/>
                                                            <jsp:setProperty name="date" property="time" value="${ups.nextTurn}" /> 
                                                            <fmt:formatDate type="date" pattern="H:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                                                            </p>
                                                    </c:if>
                                                    <c:if test="${ups.run==true}">
                                                        <c:if test="${ups.done==false}">
                                                            <p class="text-success">
                                                                <strong>
                                                                    Đang chạy theo lịch
                                                                </strong>
                                                            </p>
                                                            <p>Lượt tới:<br/>
                                                                <jsp:setProperty name="date" property="time" value="${ups.nextTurn}" /> 
                                                                <fmt:formatDate type="date" pattern="H:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                                                                    <br/>
                                                                    » <a href="javascript:;" onclick="upschedule.getUpScheduleHistory('${ups.id}')">Xem lịch sử UP</a>
                                                            </p>
                                                        </c:if>
                                                        <c:if test="${ups.done==true}">
                                                            <p class="clr-red"><strong>Đã up xong</strong></p>
                                                            <p>» <a href="javascript:;" onclick="upschedule.getUpScheduleHistory('${ups.id}')">Xem lịch sử UP</a></p>
                                                            <button type="button" class="btn btn-default btn-block btn-sm" onclick="item.upItem('${ups.itemId}')">Sửa và chạy lại lịch up</button>
                                                        </c:if>
                                                    </c:if>
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
                        <span class="pull-left"><strong>Tổng cộng: ${dataPage.dataCount} kết quả</strong></span>
                        <ul class="pagination pull-right">
                            <c:if test="${dataPage.pageCount > 1}">
                                <!-- Generate first link -->
                                <c:if test="${dataPage.pageIndex!=0}">
                                    <li><a href="${baseUrl}/user/posting.html">«</a></li>
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
                                    <li class="${(dataPage.pageIndex+1)==p ?'active':''}"><a href="${baseUrl}/user/posting.html?page=${p}">${p}</a></li>
                                    </c:forEach>
                                <!--/ End generate link -->
                                <!-- Generate last link -->
                                <c:if test="${(dataPage.pageIndex+1)!=dataPage.pageCount}">
                                    <li><a href="${baseUrl}/user/posting.html?page=${dataPage.pageCount}">»</a></li>
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
