<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="text" uri="http://chodientu.vn/text" %>
<jsp:useBean id="dateValue" class="java.util.Date" />

<input type="hidden" name="userid" value="${viewer.user.id}" />
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li>
            <a href="${baseUrl}/user/profile.html">
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a>
        </li>
        <li class="active">Khuyến mại - Giảm giá</li>
    </ol>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-tao-khuyen-mai-coupon-264605519989.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn tạo khuyến mại Coupon
        </a></div>    
    <h1 class="title-pages">Coupon</h1>
    <div class="tabs-content-user">
        <div class="row row-reset">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="clearfix">
                    <input name="sellerId" value="${viewer.user.id}" type="hidden"/>
                    <h6 class="pull-left"><strong>Tên chương trình: <span class="clr-red">*</span></strong></h6>
                    <span class="pull-right small mgt-15">Tối đa 125 ký tự - <span class="clr-org"><strong>Còn <span class="countresult" >125/125</span></strong></span> ký tự</span>
                </div>
                <p class="clearfix">
                    <input name="name" onkeyup="coupon.countCharacters()" onkeypress="coupon.countCharacters()" onkeydown="coupon.countCharacters()" maxlength="125" type="text" class="form-control" placeholder="Nhập tên chương trình">
                </p>
                <div class="text-danger" for="name"></div>
                <p class="text-right clearfix">Chiến dịch giảm giá sẽ được quảng bá rộng rãi trên ChợĐiệnTử.&nbsp;<a href="#" data-toggle="modal" data-target="#ModalNormal">Xem ví dụ</a></p>
            </div>
            <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">   
                <div class="form-horizontal mgt-10 form-reset-label">
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-xs-12 control-label">Bắt đầu:</label>
                        <div class="col-lg-9 col-md-9 col-xs-12">
                            <div class="date-picker-block">
                                <input name="startTime" type="hidden" class="form-control timeselectstart" placeholder="Ngay lập tức">
                                <span class="glyphicon glyphicon-calendar"></span>     
                            </div>                                      
                            <div class="text-danger" for="startTime"></div>
                        </div>
                    </div>                                  
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-xs-3 control-label">Mức giảm:</label>
                        <div class="col-sm-5 col-md-5 col-xs-6">
                            <input type="text" name="discountPrice" class="form-control number">
                            <div class="text-danger" for="discountPrice"></div>
                        </div>
                        <div class="col-sm-3 col-md-4 col-xs-3 reset-padding">
                            <select class="form-control" name="discountType" >
                                <option value="1">đ</option>
                                <option value="2">%</option>
                            </select>                                      	
                        </div>
                    </div>                                                 
                </div>
            </div>
            <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">   
                <div class="form-horizontal mgt-10 form-reset-label">
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-xs-12 control-label">Kết thúc:</label>
                        <div class="col-lg-9 col-md-9 col-xs-12">
                            <div class="date-picker-block">
                                <input name="endTime" type="hidden" class="form-control timeselectend" placeholder="Sau 7 ngày">
                                <span class="glyphicon glyphicon-calendar"></span>     
                            </div>             
                            <div class="text-danger" for="endTime"></div>
                        </div>
                    </div>                                  
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Giá trị đơn giá tối thiểu:</label>
                        <div class="col-sm-4">
                            <input type="text" name="minOrderValue" class="form-control">      
                            <div class="text-danger" for="minOrderValue"></div>
                        </div>         
                        <label class="col-sm-2 control-label reset-padding">Số lần sử dụng:</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" name="usedquantity" >       
                            <div class="text-danger" for="usedquantity"></div>
                        </div>                                                               
                    </div>     

                </div>
            </div>
            <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
                <div class="form-horizontal">                                  
                    <h6 style="margin-bottom:0"><strong>Mã coupon:</strong></h6>
                    <div class="form-group">
                        <label class="col-lg-3 col-md-3 col-xs-4 control-label" >
                            <div class="capchar-box text-center"  style="background: #f2f9aa; border: 1px solid #CCC; color: #1f8c00; font-size: 25px; height:45px; line-height:45px;" >
                                <span class="coupon-id"></span>
                                <input id="codeVal" type="hidden" value="" name="codeVal"/>
                            </div>
                        </label>
                        <div class="col-lg-9 col-md-9 col-xs-8">
                            <div class="form-control-static">
                                Mã Coupon là mã để khách hàng của bạn sử dụng khi thanh toán hóa đơn.<br>
                                Mã Coupon dài 4-6 ký tự bao gồm: a-z, A-Z, 0-9.
                            </div>                                      
                        </div>
                    </div>
                </div>  
            </div>  
            <div class="clearfix"></div>
            <div class="form-group text-center">
                <button class="btn btn-lg btn-danger" onclick="coupon.submitCouponForm();" >Khởi tạo</button>
            </div>                          
        </div>  
        <div class="clearfix fillter-promotion-pages">
            <strong class="pull-left"><p class="form-control-static">Danh sách các chiến dịch coupon</p></strong>
            <form class="input-group pull-right col-sm-5" >
                <span class="col-sm-7 col-xs-7 reset-padding-right">
                    <input type="text" name="keyword" value="${keyword}" class="form-control" placeholder="Tên chương trình">
                </span>
                <span class="col-sm-5 col-xs-5 reset-padding-all">
                    <select class="form-control" name="status">
                        <option value="0">Trạng thái</option>
                        <option value="1" ${status==1?'selected':''} >Chưa bắt đầu</option>
                        <option value="2" ${status==2?'selected':''} >Đang hoạt động</option>
                        <option value="3" ${status==3?'selected':''} >Hủy</option>
                    </select>
                </span>
                <div class="input-group-btn">
                    <button class="btn btn-default" >
                        <span class="glyphicon glyphicon-search" onclick="coupon.search();"></span>
                    </button>
                </div>
            </form>
        </div>
        <c:if test="${dataPage.dataCount <= 0}">
            <div class="cdt-message bg-danger text-center">Không tìm thấy chương trình khuyến mãi nào!</div>
        </c:if>
        <c:if test="${dataPage.dataCount > 0}">
            <div class="table-responsive list-box-product mgt-10">
                <table class="table" width="100%">
                    <tbody><tr class="warning">
                            <th width="8%">
                    <div class="text-center">Mã KM</div>
                    </th>
                    <th width="25%"><div class="text-lefts">Tên chương trình</div></th>
                    <th width="12%" valign="middle"><div class="text-left">Thời gian</div></th>
                    <th width="10%" valign="middle"><div class="text-center">Số lượng <br>sử dụng</div></th>
                    <th width="10%" valign="middle"><div class="text-center">Giảm giá</div></th>
                    <th width="10%" valign="middle"><div class="text-center">Giá tối <br> thiểu (vnđ)</div></th>
                    <th width="15%" valign="middle"><div class="text-center">Tình trạng</div></th>
                    <th width="15%" valign="middle"><div class="text-center">Thao tác</div></th>  
                    </tr>
                    <c:set var="currentTime" value="<%= new java.util.Date().getTime()%>" />
                    <c:forEach var="item" items="${dataPage.data}">
                        <tr>
                            <td valign="top"><div class="text-center">${item.code}</div></td>
                            <td><div class="text-left">${item.name}</div></td>
                            <td>
                                <div class="text-left">Từ: 
                                    <jsp:setProperty name="dateValue" property="time" value="${item.startTime}" />
                                    <fmt:formatDate value="${dateValue}" pattern="dd/MM/yyyy" />
                                    <br>Đến: <jsp:setProperty name="dateValue" property="time" value="${item.endTime}" />
                                    <fmt:formatDate value="${dateValue}" pattern="dd/MM/yyyy" />
                                </div>
                            </td>
                            <td><div class="text-center">${item.usedquantity}</div></td>
                            <td>
                                <div class="text-center">
                                    <c:if test="${item.discountPrice > 0}" > ${text:numberFormat(item.discountPrice)} <sup class="u-price">đ</sup></c:if>
                                    <c:if test="${item.discountPercent > 0}" >${item.discountPercent} %</c:if>
                                    </div>
                                </td>
                                <td><div class="text-center">${text:numberFormat(item.minOrderValue)}</div></td>
                            <td>
                                <c:if test="${item.active==true}">
                                    <c:choose>
                                        <c:when test="${item.startTime > currentTime}">
                                            <div class="text-center"><strong>Chưa bắt đầu</strong></div>
                                        </c:when>
                                        <c:when test="${item.endTime < currentTime}">
                                            <div class="text-center text-danger"><strong>Hết hạn</strong></div>
                                        </c:when>
                                        <c:otherwise>    
                                            <c:set var="remainTime" value="${item.endTime - currentTime}" />
                                            <fmt:formatNumber var="seconds" pattern="0" value="${remainTime / (24 * 60 * 60 * 1000)}"/>
                                            <div class="text-center text-success"><strong>Còn ${seconds} ngày</strong></div>
                                        </c:otherwise>
                                    </c:choose> 
                                </c:if>
                                <c:if test="${item.active!=true}">
                                    <div class="text-center text-danger"><strong>Huỷ</strong></div>
                                </c:if>
                            </td>
                            <td valign="top" align="center">
                                <div class="text-center">
                                    <button <c:if test="${!item.active}">disabled="true"</c:if> onclick="coupon.updateCouponStatus('${item.code}', '${viewer.user.id}')" class="btn btn-default btn-sm">Dừng</button>
                                    </div>
                                </td>
                            </tr>
                    </c:forEach>                                                          
                    </tbody>
                </table>
            </div>
        </c:if>
        <hr>
        <div class="page-ouner clearfix">
            <c:set value="" var="qString" />
            <c:if test="${keyword != null && keyword!=''}">
                <c:set value="${qString}&keyword=${keyword}" var="qString" />
            </c:if>
            <c:if test="${status > 0}">
                <c:set value="${qString}&status=${status}" var="qString" />
            </c:if>
            <ul class="pagination pull-right">
                <c:if test="${dataPage.pageIndex > 3}"><li><a href="${baseUrl}/user/coupon.html?page=1${qString}" href="javascript:;"><<</a></li></c:if>
                <c:if test="${dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/coupon.html?page=${dataPage.pageIndex}${qString}" ><</a></li></c:if>
                <c:if test="${dataPage.pageIndex > 3}"><li><a>...</a></li></c:if>
                <c:if test="${dataPage.pageIndex >= 3}"><li><a href="${baseUrl}/user/coupon.html?page=${dataPage.pageIndex - 2}${qString}">${dataPage.pageIndex-2}</a></li></c:if>
                <c:if test="${dataPage.pageIndex >= 2}"><li><a href="${baseUrl}/user/coupon.html?page=${dataPage.pageIndex - 1}${qString}" >${dataPage.pageIndex-1}</a></li></c:if>
                <c:if test="${dataPage.pageIndex >= 1}"><li><a href="${baseUrl}/user/coupon.html?page=${dataPage.pageIndex}${qString}" >${dataPage.pageIndex}</a></li></c:if>
                <li class="active" ><a class="btn btn-primary">${dataPage.pageIndex + 1}</a>
                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 1}"><li><a href="${baseUrl}/user/coupon.html?page=${dataPage.pageIndex+2}${qString}" >${dataPage.pageIndex+2}</a></li></c:if>
                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/coupon.html?page=${dataPage.pageIndex+3}${qString}" >${dataPage.pageIndex+3}</a></li></c:if>
                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 3}"><li><a >...</a></c:if>
                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/coupon.html?page=${dataPage.pageIndex+2}${qString}" >></a></li></c:if>
                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/coupon.html?page=${dataPage.pageCount}${qString}" >>></a></li></c:if>
                </ul>
            </div>
        </div>
    </div>

    <div class="modal fade" id="ModalNormal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                </div>
                <div class="modal-body">
                    <img src="${baseUrl}/static/user/images/pop-coupon.png" />
            </div><!-- end modal-body -->
        </div><!-- end modal-content -->
    </div><!-- end modal-dialog -->
</div><!-- end Modal -->