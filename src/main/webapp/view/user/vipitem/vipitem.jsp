<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://chodientu.vn/text" prefix="text" %>
<%@taglib uri="http://chodientu.vn/url" prefix="url" %>

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li>
            <a href="${baseUrl}/user/profile.html">
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a>
        </li>
        <li class="active">Marketing - Quảng bá</li>
    </ol>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-mua-tin-vip-986705507152.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn mua tin Vip
        </a></div>
    <h1 class="title-pages">Quản lý tin VIP</h1>
    <div class="tabs-content-user">
        <div class="row row-reset">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <c:if test="${dataPage.dataCount == 0}">
                    <p class="text-center mgt-25"><strong>Chưa có tin VIP nào!</strong><br>
                        <strong>Bạn phải vào <a href="${baseUrl}/user/item.html">Danh sách sản phẩm</a> để chọn sản phẩm mua tin VIP</strong></p>
                    <p class="text-center mgt-25">Ảnh thao tác sử dụng tại trang <a href="${baseUrl}/user/item.html">Danh sách sản phẩm</a></p>
                    <div class="mgt-10 text-center"><img src="${baseUrl}/static/user/images/muatinvip.png"></div>
                    </c:if>
                    <c:if test="${dataPage.dataCount > 0}">
                    <div class="list-table-content table-responsive">
                        <table class="table" width="100%">
                            <tbody>
                                <tr class="warning">
                                    <th width="45%">
                                        <!--<button class="btn btn-danger btn-sm">
                                            <span class="glyphicon glyphicon-trash"></span> Xoá
                                        </button> &nbsp; -->
                                        Tin Vip</th>
                                    <th width="22%" valign="middle"><div class="text-left">Danh mục</div></th>
                            <th width="18%" valign="middle"><div class="text-left">Thời gian</div></th>
                            <th width="10%" valign="middle"><div class="text-center">Tình trạng</div></th>
                            </tr>
                            <jsp:useBean id="date" class="java.util.Date" />
                            <c:forEach items="${dataPage.data}" var="itemvip">
                                <c:set var="flag" value="true"></c:set>
                                <c:forEach items="${items}" var="item">
                                    <c:if test="${item.id == itemvip.itemId && flag}">
                                        <c:set var="flag" value="false"></c:set>
                                            <tr>
                                                <td>
                                                    <div class="table-content">
                                                        <span class="img-list-tinvip">
                                                            <img src="${item.images[0]}" width="100" class="img-responsive" >
                                                    </span>
                                                    <a target="_blank" href="${url:item(item.id, item.name)}" title="${item.name}" >${item.name}</a>
                                                    <p>
                                                        Mua tin VIP lúc: 
                                                        <jsp:setProperty name="date" property="time" value="${itemvip.createTime}" /> 
                                                        <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></p>
                                                        <jsp:setProperty name="date" property="time" value="${itemvip.to-itemvip.from}" /> 
                                                        <fmt:formatDate type="date" pattern="d" value="${date}" var="xeng"></fmt:formatDate>
                                                    <p>Chi phí: <strong>${text:numberFormat(xeng*30000)} xèng</strong></p>
                                                    <p>Mã giao dịch: <span class="clr-red">${itemvip.id}</span></p>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="text-left">
                                                    <p><strong>Vip danh mục</strong></p>
                                                    <c:forEach items="${itemvip.categoryPath}" var="catePath">
                                                        <c:forEach items="${categorys}" var="cate">
                                                            <c:if test="${catePath==cate.id}">
                                                                <a target="_blank" href="${url:browse(cate.id, cate.name)}">${cate.name}</a> (Cấp ${cate.level}<c:if test="${cate.leaf}">- Cuối</c:if>) 
                                                                <c:if test="${!cate.leaf}"> » </c:if>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:forEach>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="text-left">
                                                    <p><strong>Trong <jsp:setProperty name="date" property="time" value="${itemvip.to-itemvip.from}" /> 
                                                            <fmt:formatDate type="date" pattern="d" value="${date}"></fmt:formatDate> ngày</strong></p>
                                                    <strong>Từ:</strong> <jsp:setProperty name="date" property="time" value="${itemvip.from}" /> 
                                                    <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate><br>
                                                    <strong>Tới:</strong> <jsp:setProperty name="date" property="time" value="${itemvip.to}" /> 
                                                    <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                                                    </div>
                                                </td>
                                                <td>
                                                <c:set var="currentTime" value="<%= new java.util.Date().getTime()%>" />

                                                <c:if test="${!itemvip.active}">
                                                    <div class="text-center clr-red"><strong>Đã dừng</strong></div>
                                                </c:if>
                                                <c:if test="${itemvip.active}">
                                                    <c:if test="${currentTime < itemvip.to}">
                                                        <div class="text-center text-success"><strong>Đang chạy</strong></div>
                                                    </c:if>
                                                    <c:if test="${currentTime>itemvip.to}">
                                                        <div class="text-center clr-red"><strong>Đã dừng</strong></div>
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
                    <hr>
                    <div class="page-ouner clearfix">
                        <span class="pull-left"><strong>Có: ${dataPage.dataCount} kết quả / ${dataPage.pageCount} trang</strong></span>
                        <ul class="pagination pull-right">
                            <c:if test="${dataPage.pageIndex > 3}"><li><a href="${baseUrl}/user/vipitem.html?page=1" href="javascript:;"><<</a></li></c:if>
                            <c:if test="${dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/vipitem.html?page=${dataPage.pageIndex}" ><</a></li></c:if>
                            <c:if test="${dataPage.pageIndex > 3}"><li><a>...</a></li></c:if>
                            <c:if test="${dataPage.pageIndex >= 3}"><li><a href="${baseUrl}/user/vipitem.html?page=${dataPage.pageIndex - 2}">${dataPage.pageIndex-2}</a></li></c:if>
                            <c:if test="${dataPage.pageIndex >= 2}"><li><a href="${baseUrl}/user/vipitem.html?page=${dataPage.pageIndex - 1}" >${dataPage.pageIndex-1}</a></li></c:if>
                            <c:if test="${dataPage.pageIndex >= 1}"><li><a href="${baseUrl}/user/vipitem.html?page=${dataPage.pageIndex}" >${dataPage.pageIndex}</a></li></c:if>
                            <li class="active" ><a class="btn btn-primary">${dataPage.pageIndex + 1}</a>
                            <c:if test="${dataPage.pageCount - dataPage.pageIndex > 1}"><li><a href="${baseUrl}/user/vipitem.html?page=${dataPage.pageIndex+2}" >${dataPage.pageIndex+2}</a></li></c:if>
                            <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/vipitem.html?page=${dataPage.pageIndex+3}" >${dataPage.pageIndex+3}</a></li></c:if>
                            <c:if test="${dataPage.pageCount - dataPage.pageIndex > 3}"><li><a >...</a></c:if>
                            <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/vipitem.html?page=${dataPage.pageIndex+2}" >></a></li></c:if>
                            <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/vipitem.html?page=${dataPage.pageCount}" >>></a></li></c:if>
                            </ul>
                        </div>
                </c:if>
            </div>                            
        </div>                            
    </div>
</div>