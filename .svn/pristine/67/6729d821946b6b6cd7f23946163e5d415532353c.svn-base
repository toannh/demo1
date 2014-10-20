<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@ taglib uri="http://chodientu.vn/text" prefix="text"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 

<jsp:useBean id="dateValue" class="java.util.Date" />
<input type="hidden" name="promId" />
<input type="hidden" name="target" value="SHOP_CATEGORY" />
<input type="hidden" name="type" value="DISCOUND" />
<input type="hidden" name="userid" value="${viewer.user.id}" />

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li>
            <a href="${baseUrl}/user/profile.html">
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a>
        </li>
        <li class="active">Giảm giá danh mục shop</li>
    </ol>
    <h1 class="title-pages">Giảm giá danh mục shop</h1>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li><a href="${baseUrl}/user/categorypromotion.html">KM theo danh mục chợ</a></li>
            <li class="active"><a href="${baseUrl}/user/shopcategorypromotion.html">KM theo danh mục Shop</a></li>
        </ul>
        <div class="row row-reset">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="clearfix">
                    <h6 class="pull-left"><strong>Tên chương trình: <span class="clr-red">*</span></strong></h6>
                    <span class="pull-right small mgt-10">Tối đa 125 ký tự - <span class="clr-org"><strong>Còn <span class="countresult" >125/125</span></strong></span> ký tự</span>
                </div>
                <p class="clearfix">
                    <input onkeyup="promotion.countCharacters()" onkeypress="promotion.countCharacters()" onkeydown="promotion.countCharacters()" maxlength="125" type="text" class="form-control name-promotion" name="promotionName" placeholder="Nhập tên chương trình">
                </p>
                <div class="text-danger errorMessages" for="promotionName"></div>
                <p class="text-right clearfix">Chiến dịch giảm giá sẽ được quảng bá rộng rãi trên ChợĐiệnTử. <a href="#" data-toggle="modal" data-target="#ModalNormal" >Xem ví dụ</a> </p>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">   
                <div class="form-horizontal mgt-10 form-reset-label">
                    <div class="form-group">
                        <label class="col-lg-2 col-md-3 col-xs-12 control-label">Bắt đầu:</label>
                        <div class="col-lg-10 col-md-9 col-xs-12">
                            <div class="date-picker-block">
                                <input name="startTime" type="hidden" class="form-control timeselectstart" placeholder="Ngay lập tức">
                                <span class="glyphicon glyphicon-calendar"></span>     
                            </div>
                            <div class="text-danger errorMessages" for="startTime"  ></div>
                        </div>
                    </div>                                                                                                                    
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">   
                <div class="form-horizontal mgt-10 form-reset-label">
                    <div class="form-group">
                        <label class="col-lg-2 col-md-3 col-xs-12 control-label reset-padding">Kết thúc:</label>
                        <div class="col-lg-10 col-md-9 col-xs-12">
                            <div class="date-picker-block">
                                <input type="hidden" name="endTime" class="form-control timeselectend" placeholder="Sau 7 ngày">
                                <span class="glyphicon glyphicon-calendar"></span>     
                            </div>
                            <div class="text-danger errorMessages" for="endTime"  ></div>
                        </div>
                    </div>                                  
                </div>
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="text-danger errorMessages" for="categories" ></div>
                <div class="list-table-content table-responsive mgt-15">

                    <table class="table table-bordered table-list-catetory" style="margin-bottom: 0px;" >
                        <tbody>
                            <tr class="warning">
                                <th width="47%">Danh mục</th>
                                <th width="26.5%" ><div class="text-center cdt-tooltip" data-toggle="tooltip" data-placement="bottom" data-original-title="Là số tiền mà bạn muốn giảm đối với sản phẩm">Giảm giá<span class="icon16-faq"></span></div></th>
                        <th width="26.5%" >
                        <div class="text-center cdt-tooltip" data-toggle="tooltip" data-placement="bottom" data-original-title="Là % giá mà bạn muốn giảm đối với sản phẩm">Giảm <span class="clr-red">(%)</span><span class="icon16-faq"></span></div>
                        </th>
                        </tr>                             
                        </tbody>
                    </table>
                    <div class="promotion-menu-product clearfix" style="height: auto !important;" >
                        <ul class="clearfix">
                            <c:forEach var="cate1" items="${category}" varStatus="">
                                <li class="clearfix" for="${cate1.id}">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td  width="47%" >
                                                <a onclick="promotion.getCateChilds('${cate1.id}', '', 0, 'shop');" >
                                                    <span class="glyphicon glyphicon-chevron-right icon-parent"></span> 
                                                    ${cate1.name}
                                                </a> 
                                                <span  for="${cate1.id}"></span>
                                            </td>
                                            <td width="26.5%" >
                                                <div class="text-center" for="${cate1.id}_price" >
                                                    0 
                                                    <a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm('${cate1.id}_price')" ></span></a>
                                                </div>
                                            </td>
                                            <td width="26.5%" >
                                                <div class="text-center" for="${cate1.id}_per" >
                                                    0 
                                                    <a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm('${cate1.id}_per')" ></span></a>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                    <span for="sub_${cate1.id}"></span>         
                                </li>
                            </c:forEach>
                        </ul>                                     
                    </div>
                </div>
            </div>  
            <div class="clearfix"></div>
            <div class="form-group text-center">
                <button class="btn btn-lg btn-danger" onclick="promotion.savePromotion('SHOP_CATEGORY', 'DISCOUND');" >
                    <c:if test="${promotion==null || promotion.id == null}">
                        Khởi tạo
                    </c:if>
                    <c:if test="${promotion.id != null}">
                        Cập nhật
                    </c:if>
                </button>
            </div>                          
        </div> 
    </div>
    <div class="clearfix fillter-promotion-pages">
        <strong class="pull-left"><p class="form-control-static">Danh sách các chiến dịch giảm giá danh mục</p></strong>
    </div>
    <div class="table-responsive list-box-product mgt-10">
        <table class="table" width="100%">
            <tbody>
                <tr class="warning">
                    <th width="25%"><div class="text-lefts">Tên chương trình</div></th>
            <th width="12%" valign="middle"><div class="text-left">Thời gian</div></th>
            <!--<th width="10%" valign="middle"><div class="text-center">Số lượng <br>sử dụng</div></th>-->
            <!--<th width="10%" valign="middle"><div class="text-center">Tổng số <br> tiền đã KM</div></th>-->
            <th width="15%" valign="middle"><div class="text-center">Tình trạng</div></th>
            <th width="15%" valign="middle"><div class="text-center">Thao tác</div></th>  
            </tr>
            <c:set var="currentTime" value="<%= new java.util.Date().getTime()%>" />
            <c:if test="${dataPage.dataCount == 0}">
                <tr>
                    <td colspan="6" class="text-center text-danger">Không tìm thấy chương trình khuyến mãi nào</td>
                </tr>
            </c:if>
            <c:forEach var="item" items="${dataPage.data}">
                <tr>
                    <td><div class="text-left">${item.name}</div></td>
                    <td>
                        <div class="text-left">Từ: 
                            <jsp:setProperty name="dateValue" property="time" value="${item.startTime}" />
                            <fmt:formatDate value="${dateValue}" pattern="dd/MM/yyyy" />
                            <br>Đến: <jsp:setProperty name="dateValue" property="time" value="${item.endTime}" />
                            <fmt:formatDate value="${dateValue}" pattern="dd/MM/yyyy" />
                        </div>
                    </td>
                    <td>
                        <c:if test="${item.active}">
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
                        <c:if test="${!item.active}">
                            <div class="text-center text-danger"><strong>Huỷ</strong></div>
                        </c:if>
                    </td>
                    <td valign="top" align="center">
                        <div class="text-center">
                            <c:if test="${item.endTime > currentTime }">
                                <a class="btn btn-default btn-sm" type="button" onclick="promotion.update('${item.id}',${item.startTime > currentTime?'2':'1' })" >Sửa</a> 
                                <c:if test="${item.active}">
                                    <button class="btn btn-default btn-sm" type="button" onclick="promotion.stopPromotion('${item.id}')" >Dừng</button>                                
                                </c:if>
                            </c:if>
                            <button class="btn btn-default btn-sm" type="button" onclick="promotion.update('${item.id}', '0')" >Tạo tương tự</button>                                
                        </div>
                    </td>
                </tr>
            </c:forEach>                                                         
            </tbody>
        </table>
    </div>
    <hr>
    <div class="page-ouner clearfix">
        <ul class="pagination pull-right">
            <c:if test="${dataPage.pageIndex > 3}"><li><a href="${baseUrl}/user/shopcategorypromotion.html?page=1" href="javascript:;"><<</a></li></c:if>
            <c:if test="${dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/shopcategorypromotion.html?page=${dataPage.pageIndex}" ><</a></li></c:if>
            <c:if test="${dataPage.pageIndex > 3}"><li><a>...</a></li></c:if>
            <c:if test="${dataPage.pageIndex >= 3}"><li><a href="${baseUrl}/user/shopcategorypromotion.html?page=${dataPage.pageIndex - 2}">${dataPage.pageIndex-2}</a></li></c:if>
            <c:if test="${dataPage.pageIndex >= 2}"><li><a href="${baseUrl}/user/shopcategorypromotion.html?page=${dataPage.pageIndex - 1}" >${dataPage.pageIndex-1}</a></li></c:if>
            <c:if test="${dataPage.pageIndex >= 1}"><li><a href="${baseUrl}/user/shopcategorypromotion.html?page=${dataPage.pageIndex}" >${dataPage.pageIndex}</a></li></c:if>
            <li class="active" ><a class="btn btn-primary">${dataPage.pageIndex + 1}</a>
            <c:if test="${dataPage.pageCount - dataPage.pageIndex > 1}"><li><a href="${baseUrl}/user/shopcategorypromotion.html?page=${dataPage.pageIndex+2}" >${dataPage.pageIndex+2}</a></li></c:if>
            <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/shopcategorypromotion.html?page=${dataPage.pageIndex+3}" >${dataPage.pageIndex+3}</a></li></c:if>
            <c:if test="${dataPage.pageCount - dataPage.pageIndex > 3}"><li><a >...</a></c:if>
            <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/shopcategorypromotion.html?page=${dataPage.pageIndex+2}" >></a></li></c:if>
            <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/shopcategorypromotion.html?page=${dataPage.pageCount}" >>></a></li></c:if>
            </ul>
        </div>
    </div>

    <div class="modal fade" id="ModalNormal" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                </div>
                <div class="modal-body">
                    <img src="${baseUrl}/static/user/images/pop-coupon-product.png" />
            </div><!-- end modal-body -->
        </div><!-- end modal-content -->
    </div><!-- end modal-dialog -->
</div><!-- end Modal -->