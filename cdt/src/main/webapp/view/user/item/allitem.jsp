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
        <li>
            <a href="${baseUrl}/user/profile.html">
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a>
        </li>
        <li class="active">Danh sách sản phẩm</li>
    </ol>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-quan-ly-danh-sach-san-pham-77878360704.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn quản lý danh sách sản phẩm
        </a></div>
    <h1 class="title-pages">Danh sách sản phẩm</h1>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li class="${(tab == 'all')?'active':''}">
                <a href="${baseUrl}/user/item.html?tab=all" >
                    Tất cả 
                    <span for="all" ></span>
                </a>
            </li>
            <li class="${(tab == 'completed')?'active':''}">
                <a href="${baseUrl}/user/item.html?tab=completed" >
                    Lưu tạm 
                    <span for="uncompleted" ></span>
                </a>
            </li>
            <li class="end ${(tab == 'recyclebin')?'active':''}" >
                <a href="${baseUrl}/user/item.html?tab=recyclebin">
                    Thùng rác 
                    <span for="recycle" ></span></a>
                </a>
            </li>                                                 
            <li class="right-title pull-right">
                <div class="num-tin">
                    ${dataPage.pageIndex + 1}/${dataPage.pageCount==0?'1':dataPage.pageCount}
                    <c:if test="${dataPage.pageIndex + 1 > 1}">
                        <button onclick="item.nextPage(${dataPage.pageIndex});" type="button" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </button> 
                    </c:if>
                    <c:if test="${dataPage.pageIndex + 1 < dataPage.pageCount}">
                        <button onclick="item.nextPage(${dataPage.pageIndex + 2});" type="button" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </button>
                    </c:if>
                </div>
            </li>
        </ul>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="sub-tab-content form-inline fillter-list-product">
                        <div class="form-group">
                            <select name="categoryId" class="categoryId form-control" >
                                <option value="0" >Danh mục Chợ</option>
                                <c:forEach var="cate" items="${category}">
                                    <option ${search.categoryId == cate.id?'selected':''} value="${cate.id}" >${cate.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <c:if test="${shop != null}">
                            <div class="form-group">
                                <select name="shopCategoryId" class="form-control" >
                                    <option value="0" >Danh mục shop</option>
                                    <c:forEach var="shopcate" items="${shopCategory}">
                                        <c:if test="${shopcate.level == 1}">
                                            <option ${search.shopCategoryId == shopcate.id?'selected':''} value="${shopcate.id}" >${shopcate.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </c:if>
                        <!--                        <div class="form-group">
                                                    <select class="form-control">
                                                        <option>Thời gian</option>
                                                    </select>
                                                </div>-->
                        <div class="form-group">
                            <select name="listingType" class="listingType form-control" >
                                <option value="0" >Hình thức bán</option>
                                <option ${search.listingType == 'BUYNOW'?'selected':''} value="BUYNOW" >Mua ngay</option>
                                <option ${search.listingType == 'AUCTION'?'selected':''} value="AUCTION" >Đấu giá</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <select name="condition" class="condition form-control" >
                                <option value="0" >Tình trạng SP</option>
                                <option ${search.condition == 'NEW'?'selected':''} value="NEW" >Hàng mới</option>
                                <option ${search.condition == 'OLD'?'selected':''} value="OLD" >Hàng cũ</option>
                            </select>
                        </div> 
                        <div class="form-group">
                            <select name="find" class="condition form-control" >
                                <option value="0" >Tình trạng bán</option>
                                <option ${find == 'selling'?'selected':''} value="selling" >Đang bán</option>
                                <option ${find == 'unapproved'?'selected':''} value="unapproved" >Không duyệt</option>
                                <option ${find == 'outDate'?'selected':''} value="outDate" >Đã hết hạn</option>
                                <option ${find == 'outofstock'?'selected':''} value="outofstock" >Đã hết hàng</option>
                                <option ${find == 'nobeginning'?'selected':''} value="nobeginning" >Chưa bắt đầu</option>
                            </select>
                        </div> 
                        <div class="form-group">
                            <select name="createTime" class="condition form-control" >
                                <option value="0" >Thời gian</option>
                                <option ${createTime == '3'?'selected':''} value="3" >3 ngày</option>
                                <option ${createTime == '7'?'selected':''} value="7" >7 ngày</option>
                                <option ${createTime == '30'?'selected':''} value="30" >30 ngày</option>
                                <option ${createTime == '60'?'selected':''} value="60" >60 ngày</option>
                            </select>
                        </div> 
                        <div class="form-group form-group-reset">
                            <div class="input-group">
                                <input type="text" class="form-control" name="keyword" value="${search.keyword}" placeholder="Tìm kiếm">
                                <span class="input-group-addon" style="cursor: pointer" onclick="item.search();"><span class="glyphicon glyphicon-search"  ></span></span>
                            </div>
                        </div>
                    </div>
                    <c:if test="${tab=='all'}">
                        <div class="sales-statics-pro mgt-15">
                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 reset-padding">
                                <strong><span for="selling" ></span></strong>
                                <span><a href="javascript:item.search('selling');" <c:if test="${find =='selling'}">class="text-danger"</c:if>>Sản phẩm đang bán</a></span>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 reset-padding">
                                    <strong><span for="unapproved" ></span></strong> 
                                    <span><a href="javascript:item.search('unapproved');" <c:if test="${find =='unapproved'}">class="text-danger"</c:if>>Sản phẩm không được duyệt</a></span>  
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 reset-padding">
                                    <strong><span for="outDate" ></span></strong> <span><a href="javascript:item.search('outDate');" <c:if test="${find =='outDate'}">class="text-danger"</c:if>>Sản phẩm đã hết hạn </a></span>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 reset-padding">
                                    <strong><span for="outOfStock" ></span></strong> <span><a href="javascript:item.search('outofstock');" <c:if test="${find =='outofstock'}">class="text-danger"</c:if> >Sản phẩm đã hết hàng</a></span>
                                </div>
                                <div class="clearfix"></div>                                
                            </div>
                    </c:if>
                    <c:if test="${dataPage.dataCount <= 0}">
                        <div class="cdt-message bg-danger text-center">Không tìm thấy sản phẩm nào.</div>
                    </c:if>
                    <c:if test="${dataPage.dataCount > 0}">
                        <div class="table-list-product mgt-15 table-responsive">    
                            <input type="hidden" name="maxquantity" value="${user.balance/500}" />
                            <table class="table table-striped ">
                                <thead>
                                    <tr>
                                        <th width="2%"><input type="checkbox" name="checkall" /></th>
                                        <th>
                                            <c:if test="${dataPage.dataCount > 0}">
                                    <div class="form-inline">
                                        <c:if test="${tab=='all'}">
                                            <div class="form-group">
                                                <button type="button" class="btn btn-primary" onclick="item.upItems();"><span class="glyphicon glyphicon-open"></span> Up tin</button>
                                            </div>
                                            <div class="form-group">
                                                <button type="button" class="btn btn-warning" onclick="vipitem.upVipItem();" ><span class="glyphicon glyphicon-star"></span> Mua tin VIP</button>
                                            </div>
                                            <div class="form-group">
                                                <button type="button" class="btn btn-success" onclick="item.refreshs();" ><span class="glyphicon glyphicon-refresh"></span> Làm mới</button>
                                            </div>
                                            <div class="form-group">
                                                <button type="button" class="btn btn-info" onclick="item.stopSelling();" ><span class="glyphicon glyphicon-time"></span> Ngừng bán</button>
                                            </div>
                                            <div class="form-group">
                                                <button type="button"class="btn btn-danger" onclick="item.inActive();" ><span class="glyphicon glyphicon-trash"></span> Xóa</button>
                                            </div>
                                        </c:if>
                                        <c:if test="${tab=='recyclebin'}">
                                            <div class="form-group">
                                                <button type="button" class="btn btn-success" onclick="item.restore();" ><span class="glyphicon glyphicon-refresh"></span> Khôi phục</button>
                                            </div>
                                        </c:if>
                                    </div>
                                </c:if>
                                </th>
                                <th><div class="text-center">Giá bán<span class="clr-999 clearfix">(đ)</span></div></th>
                                <th width="7%"><div class="text-center">Số lượng</div></th>
                                <th width="6%"><div class="text-center">Khối lượng<span class="clr-999 clearfix">(gram)</span></div></th>
                                <th><div class="text-center">Phí vận chuyển<span class="clr-999 clearfix">(đ)</span></div></th>
                                <th width="13%"><div class="text-center">Thao tác</div></th>
                                </tr>
                                </thead>  
                                <tbody>
                                    <c:forEach var="item" items="${dataPage.data}">
                                        <tr data-id="${item.id}" >
                                            <td><div class="text-left"><input type="checkbox" for="checkall" value="${item.id}" /></div></td>
                                            <td colspan="2">
                                                <div class="pull-left product-info list-product-seller">
                                                    <a href="${baseUrl}${url:item(item.id, item.name)}">
                                                        <span class="img-pro-hot">
                                                            <c:if test="${item.images != null && fn:length(item.images) > 0}">
                                                                <img class="img-responsive" src="${item.images[0]}" alt="${item.name}" />
                                                            </c:if>
                                                            <c:if test="${item.images == null || fn:length(item.images) == 0}">
                                                                <img class="img-responsive" src="${baseUrl}/static/user/images/no-image-product.png" alt="${item.name}" />
                                                            </c:if>
                                                        </span>
                                                    </a>
                                                    <div class="product-info-detail" >
                                                        <p itemid="${item.id}" for="name" class="icon-edit" >
                                                            <a target="_blank" href="${baseUrl}${url:item(item.id, item.name)}">${item.name}</a>
                                                            <c:if test="${item.listingType == 'BUYNOW'}">
                                                                <a class="btn-quick-edit" onclick="item.change(this)" >
                                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                                </a>
                                                            </c:if>
                                                        </p>
                                                        <p for="name_${item.id}" style="display: none" >
                                                            <input class="form-control" value="${item.name}" />
                                                        </p>
                                                        <p class="mgt-15">Mã SP: ${item.id}</p>
                                                        <p>
                                                            <c:forEach var="category" items="${item.categoryPath}" varStatus="stt" >
                                                                <a for="c_${category}" href="" target="_blank" ></a> 
                                                                ${stt.index+1 < fn:length(item.categoryPath)?'»':''}
                                                            </c:forEach>
                                                        </p>
                                                        <p class="dateline-pro form-inline">
                                                            <jsp:useBean id="date" class="java.util.Date" />
                                                            <c:if test="${item.listingType == 'BUYNOW'}">
                                                                <c:if test="${date.time <= item.endTime && item.startTime <= date.time}">
                                                                    <c:set var="remainTime" value="${item.endTime - date.time}" />
                                                                    <fmt:formatNumber var="seconds" pattern="0" value="${remainTime / (24 * 60 * 60 * 1000)}"/>
                                                                    <span class="form-group" ><span class="glyphicon glyphicon-calendar" title="Hạn bán"></span> ${seconds} ngày</span>
                                                                </c:if>
                                                                <c:if test="${date.time > item.endTime}">
                                                                    <span class="form-group clr-red"><span class="glyphicon glyphicon-calendar"></span> Hết hạn</span>
                                                                </c:if>
                                                                <c:if test="${item.startTime > date.time}">
                                                                    <span class="form-group clr-red"><span class="glyphicon glyphicon-calendar"></span> Chưa bắt đầu bán</span>
                                                                </c:if>
                                                            </c:if>
                                                            <c:if test="${item.listingType == 'AUCTION'}">
                                                                <c:if test="${item.startTime <= date.time && item.endTime >= date.time}">
                                                                    <c:set var="remainTime" value="${item.endTime - date.time}" />
                                                                    <fmt:formatNumber var="seconds" pattern="0" value="${remainTime / (24 * 60 * 60 * 1000)}"/>
                                                                    <span class="form-group text-warning">
                                                                        <span class="glyphicon glyphicon-calendar"></span> Đấu giá còn ${seconds} ngày
                                                                    </span>
                                                                </c:if>
                                                                <c:if test="${item.startTime > date.time}">
                                                                    <span class="form-group clr-red">
                                                                        <span class="glyphicon glyphicon-calendar"></span> Đấu giá chưa bắt đầu 
                                                                    </span>
                                                                </c:if>
                                                                <c:if test="${item.endTime < date.time && item.highestBider != null && item.highestBider != ''}">
                                                                    <span class="form-group text-success">
                                                                        <span class="glyphicon glyphicon-calendar"></span> Đấu giá thành công 
                                                                    </span>
                                                                </c:if>
                                                                <c:if test="${item.endTime < date.time && (item.highestBider == null || item.highestBider == '' )}">
                                                                    <span class="form-group clr-red"><span class="glyphicon glyphicon-calendar"></span> Đấu giá thất bại</span>
                                                                </c:if>
                                                            </c:if>
                                                            <span class="form-group"><span class="glyphicon glyphicon-eye-open" title="Lượt xem"></span> ${item.viewCount}</span>  
                                                            <span class="form-group"><span class="glyphicon glyphicon-comment"></span> ${item.review} </span>
                                                            <span class="form-group"><span class="glyphicon glyphicon-thumbs-up"></span> ${item.follow} </span>
                                                            <c:if test="${item.listingType == 'AUCTION'}">
                                                                <span class="form-group"><span class="fa fa-gavel"></span> ${item.bidCount} </span>
                                                            </c:if>
                                                        </p>
                                                        <p class="text-danger textNoteAppro" for="${item.id}"></p>
                                                    </div>
                                                </div>
                                                <div class="pull-right text-right block-price">
                                                    <c:if test="${item.startPrice > item.sellPrice}">
                                                        <p><strong>Giá gốc</strong></p>
                                                        <p class="old-price" itemid="${item.id}" for="startPrice" >
                                                            ${text:numberFormat(item.startPrice)} 
                                                            <c:if test="${item.listingType == 'BUYNOW'}">
                                                                <a class="btn-quick-edit" onclick="item.change(this)" >
                                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                                </a>
                                                            </c:if>
                                                        </p>
                                                        <p for="startPrice_${item.id}" class="old-price" style="display: none">
                                                            <input type="number" style="max-width: 70px;" class="form-control" value="${item.startPrice}" />
                                                        </p>
                                                    </c:if>
                                                    <c:if test="${item.sellPrice > 0}">
                                                        <p>
                                                            <strong>Giá bán 
                                                                <c:if test="${item.startPrice> 0 && item.startPrice > item.sellPrice}">
                                                                    <b class="caret"></b> 
                                                                    <span class="clr-red">${text:percentFormat((item.startPrice - item.sellPrice)/item.startPrice)}%</span>
                                                                </c:if>
                                                            </strong>
                                                        </p>
                                                        <p class="now-price" itemid="${item.id}" for="sellPrice" >
                                                            ${text:numberFormat(item.sellPrice)} 
                                                            <c:if test="${item.listingType == 'BUYNOW'}">
                                                                <a class="btn-quick-edit" onclick="item.change(this)" >
                                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                                </a>
                                                            </c:if>
                                                        </p>
                                                        <p class="now-price" style="display: none" for="sellPrice_${item.id}" >
                                                            <input style="max-width: 70px;" class="form-control numberType" value="${text:numberFormat(item.sellPrice)}" />
                                                        </p>
                                                    </c:if>
                                                    <c:if test="${item.sellPrice <= 0}">
                                                        Thỏa thuận
                                                    </c:if>
                                                    <c:if test="${item.discount}">
                                                        <p>
                                                            <strong>
                                                                <span class="clr-red">Giá KM</span> 
                                                                <b class="caret"></b> 
                                                                <span class="clr-red">${item.discountPrice > 0?text:percentFormat(item.discountPrice/item.sellPrice):(item.discountPercent > 0?item.discountPercent:'0')}%</span>
                                                            </strong>
                                                        </p>
                                                        <p>${item.discountPrice > 0?text:numberFormat(item.sellPrice - item.discountPrice):(item.discountPercent > 0?text:numberFormat(item.sellPrice * ((100-item.discountPercent)/100)):'0')}</p>
                                                    </c:if>
                                                </div>
                                            </td>                                        
                                            <td>
                                                <p class="text-center <c:if test="${item.quantity <= 0}">clr-red</c:if>" itemid="${item.id}" for="quantity" >
                                                    <c:if test="${item.quantity > 0}">
                                                        ${item.quantity}
                                                    </c:if>
                                                    <c:if test="${item.quantity <= 0 }">
                                                        0
                                                        <br>
                                                        Hết hàng
                                                    </c:if>
                                                    <c:if test="${item.listingType == 'BUYNOW'}">
                                                        <a class="btn-quick-edit" onclick="item.change(this)" >
                                                            <span class="glyphicon glyphicon-pencil"></span>
                                                        </a>
                                                    </c:if>
                                                </p>
                                                <p class="text-center" style="display: none" for="quantity_${item.id}" >
                                                    <input style="max-width: 70px;" class="form-control numberType" value="${text:numberFormat(item.quantity)}" />
                                                </p>
                                            </td>
                                            <td>
                                                <p class="text-left" itemid="${item.id}" for="weight" >
                                                    ${item.weight == 0?'Chưa xác định':item.weight}
                                                    <c:if test="${item.listingType == 'BUYNOW'}">
                                                        <a class="btn-quick-edit" onclick="item.change(this)" >
                                                            <span class="glyphicon glyphicon-pencil"></span>
                                                        </a>
                                                    </c:if>
                                                </p>
                                                <p class="text-center" style="display: none" for="weight_${item.id}" >
                                                    <input style="width: 60px;" class="form-control numberType" value="${text:numberFormat(item.weight)}" />
                                                </p>
                                            </td>
                                            <td>
                                                <div class="text-center">
                                                    <c:if test="${item.shipmentType == null || item.shipmentType == 'AGREEMENT'}">
                                                        Không rõ phí
                                                    </c:if>
                                                    <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice == 0}">
                                                        Miễn phí
                                                    </c:if>
                                                    <c:if test="${item.shipmentType == 'FIXED' && item.shipmentPrice > 0}">
                                                        Cố định: ${item.shipmentPrice} <sup class="u-price">đ</sup>
                                                    </c:if>
                                                    <c:if test="${item.shipmentType == 'BYWEIGHT'}">
                                                        Linh hoạt
                                                    </c:if>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="text-center">
                                                    <c:if test="${tab=='all'}">
                                                        <div data-rel="upS-free" data-id="${item.id}" ></div>
                                                        <c:choose>
                                                            <c:when test="${item.listingType == 'BUYNOW'}">
                                                                <a class="btn btn-primary btn-sm btn-block"  href="${baseUrl}/user/dang-ban.html?id=${item.id}" target="_blank">Sửa chi tiết</a>
                                                                <c:if test="${item.endTime > date.time && item.startTime < date.time && item.approved}">
                                                                    <c:if test="${item.quantity > 0}">
                                                                        <p>
                                                                            <a href="javascript:item.upItem('${item.id}');"  style="cursor: pointer" >Up tin</a>
                                                                        </p>
                                                                        <p>
                                                                            <a href="javascript:item.upVipItem('${item.id}');"   style="cursor: pointer"  >Mua tin vip</a>
                                                                        </p>
                                                                    </c:if>
                                                                </c:if>
                                                                <p>
                                                                    <a href="javascript:item.removeItem('${item.id}');"  style="cursor: pointer"  >Xoá</a>
                                                                </p>
                                                            </c:when>
                                                            <c:when test="${item.listingType == 'AUCTION'}">
                                                                <c:choose>
                                                                    <c:when test="${item.startTime <= date.time && item.endTime >= date.time}">
                                                                        <button type="button" class="btn btn-primary btn-sm btn-block" onclick="item.upItem('${item.id}')" >Up tin</button>
                                                                        <p>
                                                                            <a style="cursor: pointer" href="javascript:item.upVipItem('${item.id}');"   >Mua tin vip</a>
                                                                        </p>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <c:if test="${item.endTime < date.time && item.highestBider != null &&  item.highestBider != ''}">
                                                                            <button type="button" class="btn btn-primary btn-sm btn-block" onclick="item.cloneItem('${item.id}');">Đăng sản phẩm </br> tương tự</button>
                                                                        </c:if>
                                                                        <c:if test="${item.endTime < date.time && (item.highestBider == null ||  item.highestBider == '')}">
                                                                            <a class="btn btn-primary btn-sm btn-block"  href="${baseUrl}/user/dang-ban.html?id=${item.id}" target="_blank">Sửa chi tiết</a>
                                                                        </c:if>
                                                                        <p>
                                                                            <a style="cursor: pointer" href="javascript:item.removeItem('${item.id}');"  >Xoá</a>
                                                                        </p>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:when>
                                                        </c:choose>  
                                                    </c:if>
                                                    <c:if test="${tab=='completed'}">
                                                        <button type="button" class="btn btn-primary btn-sm btn-block"  onclick="item.editItem('${item.id}')">Sửa</button>
                                                    </c:if>
                                                    <c:if test="${tab=='recyclebin'}">
                                                        <button type="button"  class="btn btn-danger btn-sm btn-block" onclick="item.restoreOneItem('${item.id}');" >Khôi phục</button>
                                                    </c:if>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <c:if test="${dataPage.dataCount > 0}">
                                    <tfoot>
                                        <tr>
                                            <th width="2%">
                                                <input type="checkbox" name="checkall"/>
                                            </th>
                                            <th colspan="7">

                                    <div class="form-inline">
                                        <c:choose>
                                            <c:when test="${tab=='all'}">
                                                <div class="form-group">
                                                    <button type="button" class="btn btn-primary" onclick="item.upItems();"><span class="glyphicon glyphicon-open"></span> Up tin</button>
                                                </div>
                                                <div class="form-group">
                                                    <button type="button"class="btn btn-warning" onclick="vipitem.upVipItem();" ><span class="glyphicon glyphicon-star"></span> Mua tin VIP</button>
                                                </div>
                                                <div class="form-group">
                                                    <button type="button" class="btn btn-success" onclick="item.refreshs();" ><span class="glyphicon glyphicon-refresh"></span> Làm mới</button>
                                                </div>
                                                <div class="form-group">
                                                    <button type="button" class="btn btn-info" onclick="item.stopSelling();" ><span class="glyphicon glyphicon-time"></span> Ngừng bán</button>
                                                </div>
                                                <div class="form-group">
                                                    <button type="button" class="btn btn-danger" onclick="item.inActive();" ><span class="glyphicon glyphicon-trash"></span> Xóa</button>
                                                </div>
                                            </c:when>
                                            <c:when test="${tab=='completed'}">
                                                <div class="form-group">
                                                    <button type="button" class="btn btn-danger" onclick="item.remove();" ><span class="glyphicon glyphicon-trash"></span> Xóa</button>
                                                </div>
                                            </c:when>
                                            <c:when test="${tab=='recyclebin'}">
                                                <div class="form-group">
                                                    <button type="button" class="btn btn-success" onclick="item.restore();" ><span class="glyphicon glyphicon-refresh"></span> Khôi phục</button>
                                                </div>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    </th>
                                    </tr>
                                    </tfoot>
                                </c:if>
                            </table>
                        </div>
                    </c:if>
                    <c:if test="${dataPage.dataCount > 0}">
                        <div class="page-ouner clearfix">
                            <span class="pull-left go-pages">
                                <label class="control-label pull-left">Tới trang: </label>
                                <input type="text" name="page" id="indexNext" class="form-control pull-left" value="${dataPage.pageIndex + 1}">
                                <a onclick="item.search('${find != ''?find:''}', $('#indexNext').val());" class="btn btn-default pull-left">
                                    <span class="glyphicon glyphicon-log-in"></span>
                                </a>
                            </span>
                            <ul class="pagination pull-right">
                                <c:if test="${dataPage.pageIndex > 3}"><li><a href="javascript:item.search('${find != ''?find:''}', 1);"  style="cursor: pointer" ><<</a></li></c:if>
                                <c:if test="${dataPage.pageIndex > 2}"><li><a href="javascript:item.search('${find != ''?find:''}', ${dataPage.pageIndex});" style="cursor: pointer"  ><</a></li></c:if>
                                <c:if test="${dataPage.pageIndex > 3}"><li><a href="javascript:;" style="cursor: pointer">...</a></li></c:if>
                                <c:if test="${dataPage.pageIndex >= 3}"><li><a href="javascript:item.search('${find != ''?find:''}', ${dataPage.pageIndex - 2});" style="cursor: pointer" >${dataPage.pageIndex-2}</a></li></c:if>
                                <c:if test="${dataPage.pageIndex >= 2}"><li><a href="javascript:item.search('${find != ''?find:''}', ${dataPage.pageIndex -1});" style="cursor: pointer" >${dataPage.pageIndex-1}</a></li></c:if>
                                <c:if test="${dataPage.pageIndex >= 1}"><li><a href="javascript:item.search('${find != ''?find:''}', 1);" onclick="" style="cursor: pointer" >${dataPage.pageIndex}</a></li></c:if>
                                <li class="active" ><a class="btn btn-primary" >${dataPage.pageIndex + 1}</a>
                                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 1}"><li><a href="javascript:item.search('${find != ''?find:''}', ${dataPage.pageIndex + 2});"  style="cursor: pointer" >${dataPage.pageIndex+2}</a></li></c:if>
                                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="javascript:item.search('${find != ''?find:''}', ${dataPage.pageIndex +3});"  style="cursor: pointer"  >${dataPage.pageIndex+3}</a></li></c:if>
                                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 3}"><li><a href="javascript:;" style="cursor: pointer">...</a></c:if>
                                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="javascript:item.search('${find != ''?find:''}', ${dataPage.pageIndex +2});"  style="cursor: pointer" >></a></li></c:if>
                                <c:if test="${dataPage.pageCount - dataPage.pageIndex > 2}"><li><a href="javascript:item.search('${find != ''?find:''}', ${dataPage.pageCount});"  style="cursor: pointer" >>></a></li></c:if>
                                </ul>
                            </div>
                    </c:if>
                </div>                            
            </div>                     
        </div>   
    </div>
</div>