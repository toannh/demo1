<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="text" uri="http://chodientu.vn/text" %>
<%@ taglib prefix="url" uri="http://chodientu.vn/url" %>


<c:set var="datetimenow" value="<%= new java.util.Date().getTime()%>" />
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span><a href="${baseUrl}">Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username==null?viewer.user.email:viewer.user.username}</a></li>
        <li class="active">Quan tâm sản phẩm</li>
    </ol>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-theo-doi-san-pham-quan-tam-547731283967.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn theo dõi sản phẩm quan tâm
        </a></div>  
    <h1 class="title-pages">Quan tâm của tôi</h1>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li class="active"><a href="${baseUrl}/user/quan-tam-cua-toi.html">Sản phẩm</a></li>
            <li><a href="${baseUrl}/user/quan-tam-nguoi-ban.html">Người bán</a></li>
            <li class="right-title pull-right">
                <div class="num-tin">
                    ${pageItemFollow.pageIndex + 1}/${pageItemFollow.pageCount} 
                    <c:if test="${pageItemFollow.pageIndex + 1 > 1}">
                        <button onclick="order.nextPage(${pageItemFollow.pageIndex}, false);" type="button" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </button> 
                    </c:if>
                    <c:if test="${pageItemFollow.pageIndex + 1 < pageItemFollow.pageCount}">
                        <button onclick="order.nextPage('${pageItemFollow.pageIndex + 2}', false);" type="button" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </button>
                    </c:if>
                </div>
            </li>      
        </ul>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="sub-tab-content">
                        <div class="pull-left"><strong>Có <span class="fs-20 clr-red">${pageItemFollow.dataCount}</span> sản phẩm</strong></div>
                        <div class="pull-right form-inline">
                            <div class="form-group"><input type="text" class="form-control" name="keyword" value="${(keyword != '')? keyword:''}" placeholder="Tìm kiếm nhanh"></div>
                            <div class="form-group"><button type="button" class="btn btn-default" onclick="itemfollow.search();"><span class="glyphicon glyphicon-search"></span> Tìm kiếm</button></div>                                                                                                                        
                        </div>
                        <div class="clearfix"></div>
                    </div>     
                    <div class="auction-pages">                       
                        <div class="table-responsive">
                            <c:if test="${pageItemFollow.dataCount <= 0}">
                                <div class="cdt-message bg-danger text-center">Bạn chưa có sản phẩm quan tâm nào!</div>
                            </c:if>
                            <c:if test="${pageItemFollow.dataCount  > 0}">
                                <table class="table" width="100%">
                                    <tbody><tr class="warning">
                                            <th width="5%" align="center" valign="top"><div class="text-center"><input type="checkbox" name="checkbox" onclick="itemfollow.checkall(this, 'checkedItem')" ></div></th>
                                    <th width="40%"><button class="btn btn-danger btn-sm" onclick="itemfollow.del();"><span class="glyphicon glyphicon-trash"></span> Xoá</button> &nbsp; Sản phẩm</th>
                                    <th width="18%" valign="middle"><div class="text-center">Thời hạn</div></th>
                                    <th width="20%" valign="middle"><div class="text-right">Giá</div></th>
                                    <th width="18%" valign="middle"><div class="text-center">Thao tác</div></th>
                                    </tr>
                                    <jsp:useBean id="dateNow" class="java.util.Date" />
                                    <input id="timeNow" type="hidden" name="timeNow" value="${dateNow.time}" />
                                    <c:forEach var="itemUser" items="${pageItemFollow.data}">
                                        <c:forEach items="${listItem}" var="item">
                                            <c:if test="${item.id == itemUser.itemId}">
                                                <c:set var="hasShop" value="false" /> 
                                                <c:forEach items="${shops}" var="shop">
                                                    <c:if test="${item.sellerId==shop.userId}">
                                                        <c:set var="hasShop" value="true" />
                                                        <c:set var="shopAlias" value="${shop.alias}" />
                                                    </c:if>
                                                </c:forEach>
                                                <tr>
                                                    <td valign="top" align="center"><div class="text-center"><input type="checkbox" name="${item.id}" class="checkedItem" value="${item.id}" ></div></td>
                                                    <td>
                                                        <div class="text-left">
                                                            <div class="img-invoice-pro">
                                                                <c:if test="${item.endTime<dateNow.time && item.listingType == 'AUCTION'}">
                                                                    <span class="icon-end-aution"></span>
                                                                </c:if>
                                                                <c:if test="${item.endTime>dateNow.time && item.startTime<dateNow.time && item.endTime-dateNow.time<=1800000 && item.listingType == 'AUCTION'}">
                                                                    <span class="icon-dateline"></span>
                                                                </c:if>
                                                                <img src="${(item.images != null && fn:length(item.images) >0)?item.images[0]:staticUrl.concat('/market/images/no-image-product.png')}"  alt="${item.name}" title="${item.name}" width="80" height="80">
                                                            </div>
                                                            <p><a href="${url:item(item.id,item.name)}">${item.name}</a></p>
                                                            <p class="mgt-10">
                                                                <c:if test="${item.listingType == 'AUCTION'}">
                                                                    Giá đặt cao nhất: <strong>${text:numberFormat(item.highestBid)} đ</strong>
                                                                </c:if>
                                                            </p>
                                                            <c:if test="${hasShop}">
                                                                <p>Shop: <a href="${baseUrl}/${shopAlias}/">${shopAlias}</a> (0,0%)</p>
                                                            </c:if>
                                                            <c:if test="${!hasShop}">
                                                                <p>Người bán: <a href="#">${item.sellerName}</a> (0,0%)</p>
                                                            </c:if>
                                                            <div class="clearfix"></div>
                                                        </div>
                                                        <div class="alert-aution">
                                                            <div class="alert alert-info textNote" for="${itemUser.id}" <c:if test="${itemUser.note == null || itemUser.note == ''}"> style="display: none"</c:if>><strong>Ghi chú: ${itemUser.note}</strong></div>
                                                            <div class="note" for="${itemUser.id}" style="display: none">
                                                                <div class="col-sm-10 reset-padding" id="hasError">
                                                                    <c:if test="${itemUser.note == null || itemUser.note == ''}">
                                                                        <input type="text" name="note" rel="${itemUser.id}" class="form-control" >
                                                                    </c:if>
                                                                    <c:if test="${itemUser.note != null && itemUser.note != ''}">
                                                                        <input type="text" name="note" rel="${itemUser.id}" value="${itemUser.note}" class="form-control">
                                                                    </c:if>
                                                                </div>
                                                                <div class="col-sm-2 reset-padding-all">
                                                                    <button type="button" onclick="itemfollow.saveNote('${itemUser.id}');" class="btn btn-default">Lưu lại</button>
                                                                    <button type="button" onclick="itemfollow.note('${itemUser.id}', 'hidden');" class="btn btn-default">Đóng</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <c:if test="${item.listingType == 'AUCTION'}">
                                                            <div class="text-center">
                                                                <div class="itemTime">
                                                                    <input type="hidden" name="itemid" value="${item.id}" />
                                                                    <input type="hidden" name="startTime_${item.id}" value="${item.startTime}" />
                                                                    <input type="hidden" name="endTime_${item.id}" value="${item.endTime}" />
                                                                    <p class="item_${item.id}"></p>
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${item.listingType != 'AUCTION'}">
                                                            <div class="text-center">
                                                                <div>
                                                                    <c:if test="${item.endTime < dateNow.time}">
                                                                        <p class="text-danger">Hết hạn bán</p>
                                                                    </c:if>
                                                                    <c:if test="${item.startTime > dateNow.time && item.endTime > dateNow.time}">
                                                                        <p class="text-warning">Chưa bắt đầu</p>
                                                                    </c:if>
                                                                    <c:if test="${item.startTime < dateNow.time && item.endTime > dateNow.time}">
                                                                        <p class="text-success">Đang bán</p>
                                                                    </c:if> 
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <div class="text-right">
                                                            <c:if test="${item.listingType == 'AUCTION'}">
                                                                <p><strong><span class="fa fa-legal fa-flip-horizontal"></span> </strong>(<a href="javascript:;" onclick="auction.bidHistory('${item.id}');">${item.bidCount}</a>) <span class="${item.highestBider==viewer.user.id?'text-success':'clr-red'}" >${text:numberFormat(item.highestBid)}</span> đ</p>
                                                                <p class="mgt-10">Mua ngay: <span class="clr-red"><strong>${text:numberFormat(item.sellPrice)} đ</strong></span></p>
                                                            </c:if>
                                                            <c:if test="${item.listingType != 'AUCTION'}">
                                                                <p class="mgt-10">Giá bán: <span class="clr-red"><strong>${text:sellPrice(item.sellPrice,item.discount,item.discountPrice,item.discountPercent)} đ</strong></span></p>
                                                            </c:if>
                                                            <p class="mgt-10">
                                                                Phí vận chuyển :
                                                                <c:if test="${item.shipmentType == null || item.shipmentType == 'AGREEMENT'}">
                                                                    Tự liên hệ
                                                                </c:if>
                                                                <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice == 0}">
                                                                    Miễn phí vận chuyển
                                                                </c:if>
                                                                <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice > 0}">
                                                                    <strong>+ ${text:numberFormat(item.shipmentPrice)} đ</strong> <br> phí vận chuyển
                                                                </c:if>
                                                                <c:if test="${item.shipmentType == 'BYWEIGHT'}">
                                                                    Linh hoạt theo địa chỉ người mua
                                                                </c:if>
                                                            </p>
                                                        </div>
                                                    </td>
                                                    <td valign="top" align="center">
                                                        <div class="text-center">
                                                            <c:if test="${item.listingType != 'AUCTION' && !hasShop}">
                                                                <a href="${baseUrl}/mua-ban/${item.categoryId}/san-pham.html" class="btn btn-primary btn-block btn-sm">Xem sản <br> phẩm tương tự</a>	
                                                                </c:if>
                                                                <c:if test="${item.listingType != 'AUCTION' && hasShop}">
                                                                <a class="btn btn-primary btn-block btn-sm" href="${baseUrl}/${shopAlias}/">Xem shop <br> của người bán</a>	
                                                                </c:if>
                                                                <c:if test="${item.listingType == 'AUCTION'}">
                                                                <a href="${url:item(item.id,item.name)}" target="_blank" class="btn btn-primary btn-block btn-sm">Mua ngay</a>
                                                            </c:if>
                                                            <div class="dropdown mgt-10">
                                                                <a class="dropdown-toggle" data-toggle="dropdown">Thao tác khác <b class="caret"></b></a>
                                                                <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                    <li><a onclick="itemfollow.note('${itemUser.id}', 'display');" href="javascript:;">Ghi chú</a></li>
                                                                        <c:if test="${hasShop}">
                                                                        <li><a href="${baseUrl}/mua-ban/${item.categoryId}/san-pham.html">Xem sản phẩm tương tự</a></li>
                                                                        </c:if>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                        </div>
                        <hr>
                        <div class="page-ouner clearfix">
                            <c:if test="${pageItemFollow.pageCount > 0}">
                                <span class="pull-left go-pages">
                                    <label class="control-label pull-left">Tới trang: </label>
                                    <input type="text" class="form-control pull-left" id="indexNext" name="page" value="${pageItemFollow.pageIndex+1}">
                                    <a href="javascript:;" class="btn btn-default pull-left" onclick="itemfollow.changPage()">
                                        <span class="glyphicon glyphicon-log-in"></span>
                                    </a>
                                </span>
                            </c:if>
                            <ul class="pagination pull-right">
                                <c:if test="${pageItemFollow.pageIndex > 2}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageItemFollow.pageIndex});" >«</a></li></c:if>
                                <c:if test="${pageItemFollow.pageIndex >= 3}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageItemFollow.pageIndex-2});">${pageItemFollow.pageIndex-2}</a></li></c:if>
                                <c:if test="${pageItemFollow.pageIndex >= 2}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageItemFollow.pageIndex-1});" >${pageItemFollow.pageIndex-1}</a></li></c:if>
                                <c:if test="${pageItemFollow.pageIndex >= 1}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageItemFollow.pageIndex});">${pageItemFollow.pageIndex}</a></li></c:if>
                                <li class="active" ><a class="btn btn-primary">${pageItemFollow.pageIndex + 1}</a>
                                <c:if test="${pageItemFollow.pageCount - pageItemFollow.pageIndex > 1}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageItemFollow.pageIndex+2});" >${pageItemFollow.pageIndex+2}</a></li></c:if>
                                <c:if test="${pageItemFollow.pageCount - pageItemFollow.pageIndex > 2}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageItemFollow.pageIndex+3});">${pageItemFollow.pageIndex+3}</a></li></c:if>
                                <c:if test="${pageItemFollow.pageCount - pageItemFollow.pageIndex > 2}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageItemFollow.pageIndex+2});" >»</a></li></c:if>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>                            
        </div>                     
    </div>   
</div>