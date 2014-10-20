<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld" %>

<div class="navigator">
    <div class="container">
        <a href="#menu" class="m-expand"></a>
        <a class="navigator-logo" href="${baseUrl}" title="${title}" ><img src="${staticUrl}/market/images/frame/navigator-logo.png" alt="logo" /></a>
        <a class="ebay-coporation" href="http://ebay.vn" title="Liên doanh với ebay  - Uy tín số 1"  target="_blank">Liên doanh với <span class="ebay-logo-small"></span> - Uy tín số 1<span class="ebay-arrowdown"></span></a>
        <div class="menu-top">
            <ul>
                <li><a href="${baseUrl}/hotdeal.html">Hotdeal</a></li>
                <li><a href="${baseUrl}/tin-tuc/huong-dan-nguoi-mua/824548042719.html">Hướng dẫn</a></li>
                <li>
                    <a>Liên hệ</a>
                    <div class="popmenu">
                        <span class="popmenu-bullet"></span>
                        <div class="popmenu-large">
                            <div class="popmenu-support">
                                <div class="grid">
                                    <div class="img"><img src="${staticUrl}/market/images/frame/icon-phone54.png" alt="Support"></div>
                                    <div class="g-content">
                                        <div class="g-row"><b class="text-danger">Tổng đài hỗ trợ Chợ Điện Tử</b></div>
                                        <div class="g-row">Liên hệ ngay để nhận được sự hỗ trợ khi: gặp lỗi khi mua hàng, bán hàng và sử dụng CĐT, cần khiếu nại, bị lừa đảo v.v..</div>
                                        <div class="g-row"><b>Hà Nội</b><b class="pull-right">TP. Hồ Chí Minh</b></div>
                                        <div class="g-row">Tel: 1900-585-888 (Nhánh 107 &amp; 117)<span class="pull-right">Tel: 1900-585-888 (Nhánh 117)</span></div>
                                    </div>
                                </div><!-- /grid -->
                                <div class="grid">
                                    <div class="img"><img src="${staticUrl}/market/images/frame/icon-folder54.png" alt="Support"></div>
                                    <div class="g-content">
                                        <div class="g-row"><b class="text-danger">Phòng Quản trị danh mục</b></div>
                                        <div class="g-row">Liên hệ khi bạn có sản phẩm khuyến mại, hàng hot muốn được quảng bá trên trang chủ và danh mục</div>
                                    </div>
                                    <div class="support-table">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Hà Nội</th>
                                                    <th class="text-right">TP. Hồ Chí Minh</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        Tel: 0979 881 684
                                                        <div>
                                                            <a href="ymsgr:sendim?quantri_sanpham"><img src="http://opi.yahoo.com/online?u=quantri_sanpham&amp;m=g&amp;t=5"></a>
                                                            <a href="skype:phanquylongqb?chat"><span class="icon16-skype"></span></a>
                                                        </div>
                                                    </td>
                                                    <td class="text-right">
                                                        Tel: 0909 586 799
                                                        <div>
                                                            <a href="ymsgr:sendim?cdt_hcmc33"><img src="http://opi.yahoo.com/online?u=cdt_hcmc33&amp;m=g&amp;t=5"></a>
                                                            <a href="skype:cdt_hcmc33?chat"><span class="icon16-skype"></span></a>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div><!-- /grid -->
                                <div class="grid">
                                    <div class="img"><img src="${staticUrl}/market/images/frame/icon-user54.png" alt="Support"></div>
                                    <div class="g-content">
                                        <div class="g-row"><b class="text-danger">Phòng Kinh doanh Chợ Điện Tử</b></div>
                                        <div class="g-row">Liên hệ mở Shop miễn phí, mua lượt up tin, tin VIP</div>
                                        <div class="g-row"><b>Hà Nội</b><b class="pull-right">TP. Hồ Chí Minh</b></div>
                                        <div class="g-row">Tel: 0912 059 048<span class="pull-right">Tel: 0977 647 939</span></div>
                                    </div>
                                </div><!-- /grid -->
                            </div><!-- /popmenu-support -->
                        </div><!-- /popmenu-large -->
                    </div><!-- /popmenu -->
                </li>
            </ul>
        </div><!-- /menu-top -->
        <div class="box-search">
            <div class="search-choose">
                <span class="search-menu"></span>
                <span class="icon-arrowdown"></span>
                <div class="popmenu">
                    <span class="popmenu-bullet"></span>
                    <ul class="box-menu navbar-search-cat" >
                        <li>
                            <a href="javascript:search.changeCat();" title="Danh mục sản phẩm"  >Tất cả danh mục</a>
                        </li>
                        <c:forEach var="item" items="${categories}" >
                            <li for_cate="${item.id}" >
                                <a href="javascript:search.changeCat('${item.id}');" title="${item.name}" >${item.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div><!-- /popmenu -->
            </div>
            <div class="search-inner"><input name="navsearch" type="text" class="text" placeholder="Tìm kiếm, Ví dụ: đầm dự tiệc, iphone 6, ipad, samsung galaxy S5" /></div>
            <input onclick="search.go()" type="button" class="btn-search" value="Tìm kiếm" />
        </div><!-- /box-search -->
        <a href="#user" class="m-user" ></a>
        <div class="boxadmin">
            <ul>
                <c:if test="${viewer == null || viewer.user == null}">
                    <li class="login-li">Xin chào! 
                        <a title="Đăng nhập" onclick="auth.login('${requestScope['javax.servlet.forward.request_uri']}')">Đăng nhập</a> 
                        hoặc 
                        <a title="Đăng ký" href="${baseUrl}/user/signup.html">đăng ký</a>
                    </li>
                    <li class="sell-li">
                        <a title="Đăng bán" target="_blank" href="${baseUrl}/user/dang-ban.html">Đăng bán</a>
                    </li>
                </c:if>
                <c:if test="${viewer != null && viewer.user != null}">
                    <li>
                        <a href="${baseUrl}/user/profile.html" title="${viewer.user.username}" >
                            <span class="avatar">
                                <img src="${viewer.user.avatar == null?'/static/lib/no-avatar.png':viewer.user.avatar}" alt="avatar" /></span>
                                <c:if test="${viewer.user.username == null}">
                                    <c:if test="${fn:length(viewer.user.email) <= 15}">
                                    <span class="color-user">${viewer.user.email}</span>
                                </c:if>
                                <c:if test="${fn:length(viewer.user.email) > 15}">
                                    <c:set var="stringUsername"  value="${fn:substring(viewer.user.email, 0, 15)}" />
                                    <span class="color-user">${stringUsername}</span>
                                </c:if>
                            </c:if>
                            <c:if test="${viewer.user.username != null}">
                                <c:if test="${fn:length(viewer.user.username) <= 15}">
                                    <span class="color-user">${viewer.user.username}</span>
                                </c:if>
                                <c:if test="${fn:length(viewer.user.username) > 15}">
                                    <c:set var="stringUsername"  value="${fn:substring(viewer.user.username, 0, 15)}" />
                                    <span class="color-user">${stringUsername}</span>
                                </c:if>
                            </c:if>
                            <c:if test="${(viewer.user.username == null || viewer.user.username =='') && (viewer.user.email == null ||  viewer.user.email =='') && (viewer.user.name != null && viewer.user.name != '')}">
                                <c:if test="${fn:length(viewer.user.name) <= 15}">
                                    <span class="color-user">${viewer.user.name}</span>
                                </c:if>
                                <c:if test="${fn:length(viewer.user.name) > 15}">
                                    <c:set var="stringUsername"  value="${fn:substring(viewer.user.name, 0, 15)}" />
                                    <span class="color-user">${stringUsername}</span>
                                </c:if>
                            </c:if>  
                            <c:if test="${(viewer.user.username == null || viewer.user.username =='') && (viewer.user.email == null ||  viewer.user.email =='') && (viewer.user.name == null || viewer.user.name == '')}">
                                <span class="color-user">Khách hàng</span>
                            </c:if>    
                            <span class="icon-arrowdown"></span>
                        </a>
                        <div class="popmenu popmenu-right">
                            <span class="popmenu-bullet"></span>
                            <div class="popmenu-small">
                                <div class="user-avatar">
                                    <div class="avatar"><img src="${viewer.user.avatar == null?'/static/lib/no-avatar.png':viewer.user.avatar}" alt="avatar" /></div>
                                    <div class="sf-row">
                                        <a href="${baseUrl}/user/profile.html" title="Thông tin cá nhân" >
                                            <b>${viewer.user.name == null?viewer.user.username:viewer.user.name}</b>
                                        </a>
                                    </div>
                                    <div class="sf-row">${viewer.user.username == null?viewer.user.email:viewer.user.username}</div>
                                    <c:if test="${viewer.administrator != null}">
                                        <div class="sf-row"><a href="${baseUrl}/cp/index.html" title="Quản trị hệ thống" ><span class="icon-arrowright-blue"></span>Quản trị hệ thống</a></div>
                                    </c:if>
                                    <c:if test="${viewer.administrator == null}">
                                        <div class="sf-row">&nbsp;</div>
                                    </c:if>
                                </div>
                                <div class="popmenu-line"></div>
                                <ul class="popmenu-ul bg-gray text-right">
                                    <li><a href="${baseUrl}/user/profile.html">Thông tin cá nhân<span class="icon20-user"></span></a></li>
                                    <li><a href="${baseUrl}/user/quan-ly-thu.html" >Tin nhắn <span class="icon20-email"></span></a></li>
                                    <li class="green-li"><a href="${baseUrl}/user/signout.html">Đăng xuất<span class="icon20-logout"></span></a></li>
                                </ul>
                                <div class="popmenu-line"></div>
                                <div class="user-xeng">
                                    <div class="sf-row">Xèng hiện có: <b>${text:numberFormat(cash.balance)} Xèng</b></div>
                                    <div class="sf-row"><a target="_blank" href="${baseUrl}/user/tai-khoan-xeng.html">Mua xèng</a></div>
                                </div>
                            </div><!-- /popmenu-small -->
                        </div><!-- /popmenu -->
                    </li>
                    <li>
                        <a>Mua hàng
                            <!--<span class="msg-red">0</span>-->
                        </a>
                        <div class="popmenu popmenu-right">
                            <span class="popmenu-bullet"></span>
                            <div class="popmenu-medium">
                                <ul class="popmenu-ul text-right">
                                    <li>
                                        <a title="Đơn hàng của tôi" href="${baseUrl}/user/don-hang-cua-toi.html">
                                            Đơn hàng của tôi<span class="icon20-mylist"></span>
                                        </a>
                                    </li>
                                    <li>
                                        <a title="Sản phẩm đang theo dõi" href="${baseUrl}/user/theo-doi-dau-gia.html">
                                            Sản phẩm đang theo dõi<span class="icon20-eye"></span>
                                        </a>
                                    </li>
                                </ul>
                                <div class="userproduct-list">
                                    <div class="grid">
                                        <div class="g-row">Không tìm thấy sản phẩm</div>
                                    </div><!-- /grid -->
                                </div><!-- /userproduct-list -->
                            </div><!-- /popmenu-medium -->
                        </div><!-- /popmenu -->
                    </li>

                    <li>
                        <a>Bán hàng
                            <c:if test="${statusCount > 0}" >
                                <span class="msg-red">${statusCount}</span>
                            </c:if>
                        </a>
                        <div class="popmenu popmenu-right">
                            <span class="popmenu-bullet"></span>
                            <div class="popmenu-small">
                                <ul class="popmenu-ul text-right">
                                    <li class="green-li">
                                        <a title="Đăng bán sản phẩm" href="${baseUrl}/user/dang-ban.html">
                                            Đăng bán<span class="icon20-push"></span></a>
                                    </li>
                                    <li><a title="Danh sách sản phẩm" href="${baseUrl}/user/item.html">
                                            Danh sách sản phẩm<span class="icon20-list"></span>
                                        </a>
                                    </li>
                                    <li><a title="Hoá đơn bán hàng" href="${baseUrl}/user/hoa-don-ban-hang.html">
                                            Hoá đơn bán hàng<span class="icon20-menu"></span>
                                        </a>
                                    </li>
                                </ul>
                                <div class="popmenu-line"></div>
                                <ul class="popmenu-ul text-right">
                                    <li><a title="Quản lý up tin" href="${baseUrl}/user/posting.html">Quản lý up tin<span class="icon20-pagelist"></span></a></li>
                                    <li><a title="Quản lý tin VIP" href="${baseUrl}/user/vipitem.html">Quản lý tin VIP<span class="icon20-heart"></span></a></li>
                                            <c:if test="${marketshop == null}">
                                        <li>
                                            <a title="Mở shop" href="${baseUrl}/user/open-shop-step1.html">Mở shop <span class="icon20-shop"></span></a>
                                        </li>
                                    </c:if>
                                    <c:if test="${marketshop != null}">
                                        <li>
                                            <a title="Quản trị Shop" href="${baseUrl}/user/cau-hinh-shop-step1.html">Quản trị Shop <span class="icon20-shop"></span></a>
                                        </li>
                                    </c:if>
                                </ul>
                                <c:if test="${marketshop != null}">
                                    <div class="popmenu-line"></div>
                                    <ul class="popmenu-ul">
                                        <li><a href="${baseUrl}/user/item.html?find=outofstock">Sản phẩm hết hàng<span class="popmenu-count">(${outOfStock})</span></a></li>
                                        <li><a href="${baseUrl}/user/item.html?find=outDate">Sản phẩm hết hạn<span class="popmenu-count">(${outDate})</span></a></li>
                                        <li><a href="${baseUrl}/user/item.html?find=unapproved">Sản phẩm không được duyệt<span class="popmenu-count">(${unapproved})</span></a></li>
                                        <li><a>Đơn hàng chờ xử lý<span class="popmenu-count">(0)</span></a></li>
                                        <li><a>Hàng đang giao qua ShipChung<span class="popmenu-count">(0)</span></a></li>
                                        <li><a>Hàng bị chuyển hoàn<span class="popmenu-count">(0)</span></a></li>
                                        <!--<li><a>Cần đánh giá uy tín<span class="popmenu-count">(0)</span></a></li>-->
                                    </ul>
                                </c:if>
                            </div><!-- /popmenu-small -->
                        </div><!-- /popmenu -->
                    </li>
                </c:if>
                <li class="cart-li">
                    <a href="${baseUrl}/gio-hang.html" rel="totalCart" >
                        <span class="icon-cart"></span>
                        <c:if test="${fn:length(viewer.cart) > 0}">
                            <span class="msg-red">${itemCart}</span>
                        </c:if>
                    </a>
                    <div class="popmenu popmenu-right">
                        <span class="popmenu-bullet"></span>
                        <c:if test="${fn:length(viewer.cart) <= 0}">
                            <div class="popmenu-medium">
                                <div class="usercart-list" cart="show" >
                                    <div class="grid">
                                        <div class="g-row">Hiện giỏ hàng trống</div>
                                    </div><!-- /grid -->
                                </div><!-- /usercart-list -->
                                <div class="usercart-total" style="display: none" >
                                    <div class="sf-row">Tổng tiền hàng: <span class="ut-count" rel="cartPrice" >0 <sup class="price">đ</sup></span></div>
                                    <br>
                                    <div class="sf-row"><a href="${baseUrl}/gio-hang.html" title="Chi tiết đơn hàng"><span class="icon-arrowright-blue"></span><b>Xem chi tiết</b></a></div>
                                </div><!-- /usercart-total -->
                            </div><!-- /popmenu-medium -->
                        </c:if>
                        <c:if test="${fn:length(viewer.cart) > 0}">
                            <div class="popmenu-medium">
                                <div class="usercart-list" cart="show" >
                                    <c:forEach var="cart" items="${viewer.cart}">
                                        <c:set var="price" value="0" />
                                        <c:forEach var="item" items="${cart.items}">
                                            <c:set var="price" value="${price + (item.itemPrice * item.quantity)}" />
                                            <div class="grid" rel="show_${item.itemId}" >
                                                <span class="usercart-remove" onclick="order.removeItem('${item.id}')" >X</span>
                                                <span class="usercart-price">${text:numberFormat(item.itemPrice * item.quantity)} <sup class="price">đ</sup></span>
                                                <span class="usercart-number">x${item.quantity}</span>
                                                <div class="img"><img src="${item.images[0]}" alt="${item.itemName}"></div>
                                                <div class="g-content">
                                                    <div class="g-row"><a href="${url:item(item.itemId, item.itemName)}" target="_blank" >${item.itemName}</a></div>
                                                </div>
                                            </div><!-- /grid -->
                                        </c:forEach>
                                    </c:forEach>
                                </div><!-- /usercart-list -->
                                <div class="usercart-total">
                                    <div class="sf-row">Tổng tiền hàng: <span class="ut-count" rel="cartPrice" >${text:numberFormat(price)} <sup class="price">đ</sup></span></div>
                                    <br>
                                    <div class="sf-row"><a href="${baseUrl}/gio-hang.html" title="Chi tiết đơn hàng"><span class="icon-arrowright-blue"></span><b>Xem chi tiết</b></a></div>
                                </div><!-- /usercart-total -->
                            </div>
                        </c:if>

                    </div><!-- /popmenu -->
                </li>
            </ul>
        </div><!-- /boxadmin -->
    </div><!-- /container -->
</div><!-- /navigator -->

<nav id="menu" style="display: none" >
    <ul>
        <c:forEach var="item" items="${categories}" >
            <li>
                <a href="${baseUrl}${url:browse(item.id, item.name)}" title="Danh mục ${item.name}" >${item.name}</a>
                <ul>
                    <c:forEach var="subItem" items="${subCategories}" >
                        <c:if test="${subItem.parentId == item.id}">
                            <li>
                                <a href="${baseUrl}${url:browse(subItem.id, subItem.name)}" title="Danh mục ${subItem.name}" >${subItem.name}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
        <li><a href="#">Xem tất cả</a></li>
        <li class="white-li"><a href="#">Hotdeal</a></li>
        <li class="white-li"><a href="#">Hướng dẫn</a></li>
        <li class="white-li"><a href="#">Trợ giúp</a></li>
        <li class="white-li"><a href="#">Liên hệ</a></li>
    </ul>
</nav>
<nav id="user" style="display: none" >
    <ul>
        <li>
            <a href="${baseUrl}/user/profile.html" title="Thông tin cá nhân" >${viewer.user.username == null?viewer.user.email:viewer.user.username}</a>
            <ul>
                <li>
                    <a href="${baseUrl}/user/profile.html" title="Thông tin cá nhân" >Trang cá nhân</a>
                </li>
                <c:if test="${viewer.administrator != null}">
                    <li>
                        <a href="${baseUrl}/cp/index.html">Quản trị hệ thống</a>
                    </li>
                </c:if>
                <!--<li><a>Tin nhắn (0)</a></li>-->
            </ul>
        </li>
        <li>
            <a href="javascript:;">Mua hàng</a>
            <ul>
                <li><a href="${baseUrl}/user/don-hang-cua-toi.html">Đơn hàng của tôi</a></li>
                <li><a href="${baseUrl}/user/theo-doi-dau-gia.html">Sản phẩm đang theo dõi</a></li>
            </ul>
        </li>
        <li>
            <a href="javascript:;">Bán hàng</a>
            <ul>
                <li><a title="Đăng bán sản phẩm" href="${baseUrl}/user/dang-ban.html" >Đăng bán</a></li>
                <li><a title="Danh sách sản phẩm" href="${baseUrl}/user/item.html" >Danh sách sản phẩm</a></li>
                <li><a title="Hoá đơn bán hàng" href="${baseUrl}/user/hoa-don-ban-hang.html">Hoá đơn bán hàng</a></li>
                <li><a title="Quản lý up tin" href="${baseUrl}/user/posting.html" >Quản lý up tin</a></li>
                <li><a title="Quản lý tin VIP" href="${baseUrl}/user/vipitem.html" >Quản lý tin VIP</a></li>

                <c:if test="${marketshop == null}">
                    <li>
                        <a title="Mở shop" href="${baseUrl}/user/open-shop-step1.html">Mở shop</a>
                    </li>
                </c:if>
                <c:if test="${marketshop != null}">
                    <li>
                        <a title="Quản trị Shop" href="${baseUrl}/user/cau-hinh-shop-step1.html">Quản trị Shop</a>
                    </li>
                </c:if>
            </ul>
        </li>
        <li class="white-li"><a href="${baseUrl}/user/signout.html" >Đăng xuất</a></li>
    </ul>
</nav>