<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="text" uri="http://chodientu.vn/text" %>
<%@ taglib prefix="url" uri="http://chodientu.vn/url" %>
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username==null?viewer.user.email:viewer.user.username}</a></li>
        <li class="active">Sản phẩm đấu giá</li>
    </ol>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-dau-gia-693378790308.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn đấu giá
        </a></div>  
    <h1 class="title-pages">Sản phẩm đấu giá</h1>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li <c:if test="${auctionSearch.tab=='' || auctionSearch.tab == 'all'}">class="active"</c:if>><a href="${baseUrl}/user/theo-doi-dau-gia.html">Tất cả (${countTab['all']})</a></li>
            <li <c:if test="${auctionSearch.tab=='running'}">class="active"</c:if>><a href="${baseUrl}/user/theo-doi-dau-gia.html?tab=running">Đang đấu (${countTab['running']})</a></li>
            <li <c:if test="${auctionSearch.tab=='success'}">class="active"</c:if>><a href="${baseUrl}/user/theo-doi-dau-gia.html?tab=success">Thắng cuộc (${countTab['success']})</a></li>                            
            <li <c:if test="${auctionSearch.tab=='unsuccess'}">class="active"</c:if>><a href="${baseUrl}/user/theo-doi-dau-gia.html?tab=unsuccess">Thất bại (${countTab['unsuccess']})</a></li>                            
            <li <c:if test="${auctionSearch.tab=='remove'}">class="active"</c:if>><a href="${baseUrl}/user/theo-doi-dau-gia.html?tab=remove">Thùng rác (${countTab['remove']})</a></li> 
                <li class="right-title pull-right">
                    <div class="num-tin">
                    ${userAuctionItemPage.pageIndex + 1}/${userAuctionItemPage.pageCount} 
                    <c:if test="${userAuctionItemPage.pageIndex + 1 > 1}">
                        <button onclick="order.nextPage(${userAuctionItemPage.pageIndex}, false);" type="button" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </button> 
                    </c:if>
                    <c:if test="${userAuctionItemPage.pageIndex + 1 < userAuctionItemPage.pageCount}">
                        <button onclick="order.nextPage('${userAuctionItemPage.pageIndex + 2}', false);" type="button" class="btn btn-default btn-sm">
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
                        <div class="pull-left"><strong>Có <span class="fs-20 clr-red">${userAuctionItemPage.dataCount}</span> sản phẩm</strong></div>
                        <div class="pull-right form-inline">
                            <div class="form-group form-group-reset">
                                <div class="input-group">
                                    <input name="keyword" type="text" class="form-control" value="${(keyword != '')? keyword:''}" placeholder="Tìm theo tên,mã SP"/>
                                    <span class="input-group-addon" style="cursor: pointer"><span class="glyphicon glyphicon-search" onclick="auction.search();" ></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>     
                    <div class="auction-pages">                       
                        <div class="table-responsive">
                            <c:if test="${userAuctionItemPage.dataCount <= 0}">
                                <div class="cdt-message bg-danger text-center">Bạn chưa có sản phẩm đấu giá nào!</div>
                            </c:if>
                            <c:if test="${userAuctionItemPage.dataCount  > 0}">
                                <table class="table" width="100%">
                                    <tr class="warning">
                                        <th width="5%" align="center" valign="top"><div class="text-center"><input type="checkbox"></div></th>
                                    <th width="40%"><button class="btn btn-danger btn-sm" onclick="auction.del();"> <span class="glyphicon glyphicon-trash" ></span> Xoá</button> &nbsp; Sản phẩm</th>
                                    <th width="18%" valign="middle"><div class="text-center">Thời hạn</div></th>
                                    <th width="20%" valign="middle"><div class="text-right">Giá</div></th>
                                    <th width="18%" valign="middle"><div class="text-center">Thao tác</div></th>
                                    </tr>
                                    <jsp:useBean id="dateNow" class="java.util.Date" />
                                    <input id="timeNow" type="hidden" name="timeNow" value="${dateNow.time}" />
                                    <c:forEach items="${userAuctionItemPage.data}" var="userItem">
                                        <c:forEach items="${items}" var="item">
                                            <c:if test="${item.id == userItem.itemId}">
                                                <c:set var="hasShop" value="false" /> 
                                                <c:forEach items="${shops}" var="shop">
                                                    <c:if test="${item.sellerId==shop.userId}">
                                                        <c:set var="hasShop" value="true" />
                                                        <c:set var="shopAlias" value="${shop.alias}" />
                                                    </c:if>
                                                </c:forEach>
                                                <tr>
                                                    <td valign="top" align="center"><div class="text-center"><input name="delItem" type="checkbox" value="${item.id}"></div></td>
                                                    <td>
                                                        <div class="text-left">
                                                            <div class="img-invoice-pro">
                                                                <c:if test="${item.endTime<dateNow.time}">
                                                                    <span class="icon-end-aution"></span>
                                                                </c:if>
                                                                <c:if test="${item.endTime>dateNow.time && item.startTime<dateNow.time && item.endTime-dateNow.time<=1800000}">
                                                                    <span class="icon-dateline"></span>
                                                                </c:if>

                                                                <img src="${(item.images != null && fn:length(item.images) >0)?item.images[0]:staticUrl.concat('/market/images/no-image-product.png')}"  alt="${item.name}" title="${item.name}" width="80" height="80"></div>
                                                            <p><a href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">${item.name}</a></p>
                                                            <p class="mgt-10">Giá cao nhất bạn đặt: <strong>${userItem.price} đ</strong></p>
                                                            <c:if test="${hasShop}">
                                                                <p>Shop: <a href="${baseUrl}/${shopAlias}/">${shopAlias}</a> (0,0%)</p>
                                                            </c:if>
                                                            <c:if test="${!hasShop}">
                                                                <p>Người bán: <a href="#">${item.sellerName}</a> (0,0%)</p>
                                                            </c:if>

                                                        </div>
                                                        <div class="alert-aution">
                                                            <div class="alert alert-info textNote" for="${userItem.id}" <c:if test="${userItem.note == null || userItem.note == ''}"> style="display: none"</c:if>><strong>Ghi chú: ${userItem.note}</strong></div>
                                                            <div class="note" for="${userItem.id}" style="display: none">
                                                                <div class="col-sm-10 reset-padding" id="hasError">
                                                                    <c:if test="${userItem.note == null || userItem.note == ''}">
                                                                        <input type="text" name="note" rel="${userItem.id}" class="form-control" >
                                                                    </c:if>
                                                                    <c:if test="${userItem.note != null && userItem.note != ''}">
                                                                        <input type="text" name="note" rel="${userItem.id}" value="${userItem.note}" class="form-control">
                                                                    </c:if>
                                                                </div>
                                                                <div class="col-sm-2 reset-padding-all">
                                                                    <button type="button" onclick="auction.saveNote('${userItem.id}');" class="btn btn-default">Lưu lại</button>
                                                                    <button type="button" onclick="auction.note('${userItem.id}', 'hidden');" class="btn btn-default">Đóng</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="text-center">
                                                            <div class="itemTime">
                                                                <input type="hidden" name="itemid" value="${item.id}" />
                                                                <input type="hidden" name="startTime_${item.id}" value="${item.startTime}" />
                                                                <input type="hidden" name="endTime_${item.id}" value="${item.endTime}" />
                                                                <p class="item_${item.id}"></p>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="text-right">
                                                            <p><strong><span class="fa fa-legal fa-flip-horizontal"></span> </strong>(<a href="javascript:;" onclick="auction.bidHistory('${item.id}');">${item.bidCount}</a>) <span class="${item.highestBider==viewer.user.id?'text-success':'clr-red'}" >${text:numberFormat(item.highestBid)}</span> đ</p>
                                                            <c:if test="${item.sellPrice > 0}">
                                                                <p class="mgt-10">Mua ngay: <span class="clr-red"><strong>${text:numberFormat(item.sellPrice)} </strong></span>đ</p>
                                                            </c:if>
                                                            <p class="mgt-10">
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
                                                            <c:if test="${item.endTime > dateNow.time}">
                                                                <c:if test="${item.highestBider==viewer.user.id}">

                                                                    <c:if test="${!hasShop}">
                                                                        <a href="${baseUrl}" class="btn btn-primary btn-block btn-sm">Xem sản phẩm<br/> cùng người bán</a>	
                                                                        </c:if>
                                                                        <c:if test="${hasShop}">
                                                                        <a href="${baseUrl}/${shopAlias}/" class="btn btn-primary btn-block btn-sm">Xem shop của<br/> người bán</a>	
                                                                        </c:if>
                                                                    <p><a href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">Tăng mức đặt giá</a></p>
                                                                </c:if>
                                                                <c:if test="${item.highestBider!=viewer.user.id}">
                                                                    <a href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html" class="btn btn-primary btn-block btn-sm">Tăng mức đặt giá</a>
                                                                </c:if>
                                                                <div class="dropdown mgt-10">
                                                                    <a class="dropdown-toggle" data-toggle="dropdown" >Thao tác khác <b class="caret"></b></a>
                                                                    <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                        <li><a href="#">Nhắn người bán</a></li>
                                                                        <li><a onclick="auction.note('${userItem.id}', 'display');" href="javascript:;">Ghi chú</a></li>
                                                                        <li><a href="${baseUrl}/mua-ban/${item.categoryId}/san-pham.html">Xem sản phẩm tương tự</a></li>
                                                                            <c:if test="${item.highestBider!=viewer.user.id && !hasShop}">
                                                                            <li><a href="#">Xem sản phẩm cùng người bán</a></li>
                                                                            </c:if>
                                                                            <c:if test="${item.highestBider!=viewer.user.id && hasShop}">
                                                                            <li><a href="${baseUrl}/${shopAlias}/">Xem shop của người bán</a></li>
                                                                            </c:if>
                                                                    </ul>
                                                                </div>
                                                            </c:if>


                                                            <c:if test="${item.endTime < dateNow.time}">
                                                                <c:if test="${!hasShop}">
                                                                    <a href="${baseUrl}/mua-ban/${item.categoryId}/san-pham.html" class="btn btn-primary btn-block btn-sm">Xem sản phẩm<br/> tương tự</a>
                                                                    <div class="dropdown mgt-10">
                                                                        <a class="dropdown-toggle" data-toggle="dropdown" >Thao tác khác <b class="caret"></b></a>
                                                                        <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                            <li><a href="#">Nhắn người bán</a></li>
                                                                            <li><a onclick="auction.note('${userItem.id}', 'display');" href="javascript:;">Ghi chú</a></li>
                                                                            <li><a href="#">Xem sản phẩm cùng người bán</a></li>
                                                                        </ul>
                                                                    </div>
                                                                </c:if>
                                                                <c:if test="${hasShop}">
                                                                    <a href="${baseUrl}/${shopAlias}/" class="btn btn-primary btn-block btn-sm">Xem shop của<br/> người bán</a>
                                                                    <div class="dropdown mgt-10">
                                                                        <a class="dropdown-toggle" data-toggle="dropdown" >Thao tác khác <b class="caret"></b></a>
                                                                        <ul class="dropdown-menu pull-right text-left" role="menu">
                                                                            <li><a href="#">Nhắn người bán</a></li>
                                                                            <li><a onclick="auction.note('${userItem.id}', 'display');" href="javascript:;">Ghi chú</a></li>
                                                                            <li><a href="${baseUrl}/mua-ban/${item.categoryId}/san-pham.html">Xem sản phẩm tương tự</a></li>
                                                                        </ul>
                                                                    </div>
                                                                </c:if>
                                                            </c:if>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </table>
                            </c:if>
                        </div>
                        <hr>
                        <div class="page-ouner clearfix">
                            <c:if test="${userAuctionItemPage.pageCount > 0}">
                                <span class="pull-left go-pages">
                                    <label class="control-label pull-left">Tới trang: </label>
                                    <input type="text" class="form-control pull-left" id="indexNext" name="page" value="${userAuctionItemPage.pageIndex+1}">
                                    <a href="javascript:;" class="btn btn-default pull-left" onclick="auction.changPage()">
                                        <span class="glyphicon glyphicon-log-in"></span>
                                    </a>
                                </span>
                            </c:if>
                            <ul class="pagination pull-right">
                                <c:if test="${userAuctionItemPage.pageIndex > 2}"><li><a href="javascript:;" onclick="auction.changPage(${userAuctionItemPage.pageIndex});" >«</a></li></c:if>
                                <c:if test="${userAuctionItemPage.pageIndex >= 3}"><li><a href="javascript:;" onclick="auction.changPage(${userAuctionItemPage.pageIndex-2});">${userAuctionItemPage.pageIndex-2}</a></li></c:if>
                                <c:if test="${userAuctionItemPage.pageIndex >= 2}"><li><a href="javascript:;" onclick="auction.changPage(${userAuctionItemPage.pageIndex-1});" >${userAuctionItemPage.pageIndex-1}</a></li></c:if>
                                <c:if test="${userAuctionItemPage.pageIndex >= 1}"><li><a href="javascript:;" onclick="auction.changPage(${userAuctionItemPage.pageIndex});">${userAuctionItemPage.pageIndex}</a></li></c:if>
                                <li class="active" ><a class="btn btn-primary">${userAuctionItemPage.pageIndex + 1}</a>
                                <c:if test="${userAuctionItemPage.pageCount - userAuctionItemPage.pageIndex > 1}"><li><a href="javascript:;" onclick="auction.changPage(${userAuctionItemPage.pageIndex+2});" >${userAuctionItemPage.pageIndex+2}</a></li></c:if>
                                <c:if test="${userAuctionItemPage.pageCount - userAuctionItemPage.pageIndex > 2}"><li><a href="javascript:;" onclick="auction.changPage(${userAuctionItemPage.pageIndex+3});">${userAuctionItemPage.pageIndex+3}</a></li></c:if>
                                <c:if test="${userAuctionItemPage.pageCount - userAuctionItemPage.pageIndex > 2}"><li><a href="javascript:;" onclick="auction.changPage(${userAuctionItemPage.pageIndex+2});" >»</a></li></c:if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>                            
        </div>                     
    </div>   
</div>