<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@ taglib uri="http://chodientu.vn/text" prefix="text"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 

<jsp:useBean id="dateValue" class="java.util.Date" />
<input type="hidden" name="promId" value="${promotion.id}" />
<input type="hidden" name="target" value="ITEM" />
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
        <li class="active">Khuyến mại - Giảm giá</li>
    </ol>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-tao-khuyen-mai-giam-gia-theo-san-pham-316715655304.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn tạo khuyến mại giảm giá theo sản phẩm
        </a></div>    
    <h1 class="title-pages">Giảm giá sản phẩm</h1>
    <div class="tabs-content-user">
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
                <p class="text-right clearfix">Chiến dịch giảm giá sẽ được quảng bá rộng rãi trên ChợĐiệnTử. <a href="#" data-toggle="modal" data-target="#ModalNormal">Xem ví dụ</a> </p>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">   
                <div class="form-horizontal mgt-10 form-reset-label">
                    <div class="form-group">
                        <label class="col-lg-2 col-md-3 col-xs-12 control-label">Bắt đầu:</label>
                        <div class="col-lg-10 col-md-9 col-xs-12">
                            <div class="date-picker-block">
                                <input type="hidden" name="startTime" class="form-control timeselectstart" placeholder="Ngay lập tức">
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
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <h6 class="mgt-15">
                        <div class="text-danger errorMessages" for="categories"></div>
                    </h6>
                    <div class="border-box-cod promotion-content">
                        <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 reset-padding">
                            <div class="title-config-shop">Danh mục</div>
                            <div class="promotion-menu-product form-group">
                                <ul class="clearfix">
                                    <c:forEach items="${category}" var="cate1">
                                        <li for="${cate1.id}" level="root" >
                                            <div class="radio"><label><input type="checkbox" name="catechk" value="${cate1.id}"  class="${cate1.id}" onclick="promotion.getCateChildsCheck('${cate1.id}', '', 1);" /> 
                                                    <a>${cate1.name}</a>
                                                    <span for="${cate1.id}"></span></label></div>
                                        </li>
                                    </c:forEach>                                                                                                      
                                </ul>
                            </div>
                            <div class="promotion-footer text-right">
                                <button type="button" class="btn btn-default btn-sm" onclick="promotion.resetcate();"  >Làm lại</button>
                            </div>
                        </div>  
                        <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12 reset-padding-right">
                            <div class="title-config-shop">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td width="45%">Sản phẩm</td>
                                        <td><div class="text-center">Giá hiện tại</div></td>
                                        <td><div class="text-center cdt-tooltip" data-toggle="tooltip" data-placement="bottom" data-original-title="Là số tiền mà bạn muốn giảm đối với sản phẩm">Giảm giá<span class="icon16-faq"></span></div></td>
                                        <td><div class="text-center cdt-tooltip" data-toggle="tooltip" data-placement="bottom" data-original-title="Là % giá mà bạn muốn giảm đối với sản phẩm">Giảm <span class="clr-red">(%)</span><span class="icon16-faq"></span></div></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="form-group promotion-search-pro">
                                <div class="input-group">
                                    <input type="text" name="search" class="form-control" placeholder="Tìm kiếm sản phẩm">
                                    <div class="input-group-btn">
                                        <button class="btn btn-default" type="button" onclick="promotion.search();" >
                                            <span class="glyphicon glyphicon-search"></span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="promotion-menu-product table-responsiv">
                                <div class="list-table-content">
                                    <table class="table table-list-catetory tbl-items"></table>
                                </div>
                            </div>
                            <div class="promotion-footer">
                                <input type="hidden" value="0" name="pageIndex" />
                                <ul class="pagination pull-left" style="margin: 0px auto 10px;" >
                                    <li title="back" ><a onclick="promotion.pagePre()" >«</a></li>
                                    <li><a><span class="fillpage">1/1</span></a></li>
                                    <li title="next" ><a onclick="promotion.pageNext()" >»</a></li>
                                </ul>
                                <button type="button" class="btn btn-default btn-sm pull-right" onclick="promotion.resetitems();" >Làm lại</button>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>                          
            </div>                            

            <div class="clearfix"></div>
            <div class="form-group text-center mgt-15">
                <button class="btn btn-lg btn-danger" onclick="promotion.savePromotion('ITEM', 'DISCOUND')"  >
                    <c:if test="${promotion==null || promotion.id == null}">
                        Khởi tạo
                    </c:if>
                    <c:if test="${promotion.id != null}">
                        Cập nhật
                    </c:if>
                </button>
            </div>                          
        </div>  
        <div class="clearfix fillter-promotion-pages">
            <strong class="pull-left"><p class="form-control-static">Danh sách các chiến dịch giảm giá sản phẩm</p></strong>
            <!--                <div class="input-group pull-right col-sm-5">
                                <span class="col-sm-7 col-xs-7 reset-padding-right"><input type="text" class="form-control" placeholder="Tìm kiếm..."></span>
                                <span class="col-sm-5 col-xs-5 reset-padding-all"><select class="form-control">
                                        <option>Tiêu chí</option>
                                    </select>
                                </span>
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button>
                                </div>
                            </div>-->
        </div>
        <c:if test="${dataPage.dataCount <= 0}">
            <div class="cdt-message bg-danger text-center">Không tìm thấy chương trình khuyến mãi nào!</div>
        </c:if>
        <c:if test="${dataPage.dataCount > 0}">
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
                    <c:forEach var="item" items="${dataPage.data}">
                        <tr>
                            <td><div class="text-left" onclick="promotion.viewDetail('${item.id}')"><a style="cursor: pointer">${item.name}</a></div></td>
                            <td>
                                <div class="text-left">Từ: 
                                    <jsp:setProperty name="dateValue" property="time" value="${item.startTime}" />
                                    <fmt:formatDate value="${dateValue}" pattern="dd/MM/yyyy" />
                                    <br>Đến: <jsp:setProperty name="dateValue" property="time" value="${item.endTime}" />
                                    <fmt:formatDate value="${dateValue}" pattern="dd/MM/yyyy" />
                                </div>
                            </td>
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
        </c:if>
        <hr>
        <div class="page-ouner clearfix">
            <ul class="pagination pull-right">
                <c:if test="${dataPage.pageIndex > 3}"><li><a href="${baseUrl}/user/itempromotion.html?page=1" href="javascript:;"><<</a></li></c:if>
                <c:if test="${dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/itempromotion.html?page=${dataPage.pageIndex}" ><</a></li></c:if>
                <c:if test="${dataPage.pageIndex > 3}"><li><a>...</a></li></c:if>
                <c:if test="${dataPage.pageIndex >= 3}"><li><a href="${baseUrl}/user/itempromotion.html?page=${dataPage.pageIndex - 2}">${dataPage.pageIndex-2}</a></li></c:if>
                <c:if test="${dataPage.pageIndex >= 2}"><li><a href="${baseUrl}/user/itempromotion.html?page=${dataPage.pageIndex - 1}" >${dataPage.pageIndex-1}</a></li></c:if>
                <c:if test="${dataPage.pageIndex >= 1}"><li><a href="${baseUrl}/user/itempromotion.html?page=${dataPage.pageIndex}" >${dataPage.pageIndex}</a></li></c:if>
                <li class="active" ><a class="btn btn-primary">${dataPage.pageIndex + 1}</a>
                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 1}"><li><a href="${baseUrl}/user/itempromotion.html?page=${dataPage.pageIndex+2}" >${dataPage.pageIndex+2}</a></li></c:if>
                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/itempromotion.html?page=${dataPage.pageIndex+3}" >${dataPage.pageIndex+3}</a></li></c:if>
                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 3}"><li><a >...</a></c:if>
                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/itempromotion.html?page=${dataPage.pageIndex+2}" >></a></li></c:if>
                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="${baseUrl}/user/itempromotion.html?page=${dataPage.pageCount}" >>></a></li></c:if>
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
                    <img src="${baseUrl}/static/user/images/pop-coupon-product.png" />
            </div><!-- end modal-body -->
        </div><!-- end modal-content -->
    </div><!-- end modal-dialog -->
</div><!-- end Modal -->