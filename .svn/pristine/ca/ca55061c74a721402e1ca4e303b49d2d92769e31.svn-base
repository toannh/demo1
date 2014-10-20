<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input type="hidden" name="uri" value="${uri}" />
<div class="container">
    <c:if test="${bannertop != null}">
        <div class="topbanner">
            <a href="${bannertop.link}" target="_blank"><img alt="${bannertop.title}" src="${bannertop.banner}" ></a>
        </div>
    </c:if>
    <div class="bground pages-user">
        <div class="top-bg-user"></div>
        <div class="row">
            <tiles:insertAttribute name="main" />
            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 slidebar-left">
                <div class="widget-menu-user clearfix">
                    <div class="drop-shadow"></div>
                    <div class="widget-left user-widget-block">
                        <img src="${viewer.user.avatar == null?'/static/lib/no-avatar.png':viewer.user.avatar}" alt="avatar" class="img-circle pull-left" width="60" height="60">
                        <div class="user-info-widget">
                            <a href="${baseUrl}/user/profile.html" title="Thông tin cá nhân">
                                ${viewer.user.name == null?'Khách hàng':viewer.user.name}
                            </a>
                            <div>${viewer.user.username == null?viewer.user.email:viewer.user.username}</div>
                        </div>
                        <div class="clearfix"></div>
                    </div><!--User info-->
                    <div class="panel-group" id="accordion">
                        <div class="panel panel-default">
                            <div class="menu-panel">
                                <h4><a href="${baseUrl}/user/${viewer.user.id}/ho-so-nguoi-ban.html"><span class="icon-user icon-dashboard"></span>Bảng điều khiển</a></h4>
                            </div>
                            <div class="panel-heading">

                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                        <span class="icon-header-panel"></span>
                                        Mua hàng
                                        <b class="caret"></b>
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="menu-panel">
                                        <h4><a href="${baseUrl}/user/don-hang-cua-toi.html"><span class="icon-user icon-my-bill"></span>Đơn hàng của tôi</a></h4>
                                    </div>
                                    <div class="menu-panel">
                                        <h4><a href="${baseUrl}/user/theo-doi-dau-gia.html"><span class="icon-user icon-auction-pro"></span>Sản phẩm đấu giá</a></h4>
                                    </div>
                                    <div class="menu-panel">
                                        <h4><a href="${baseUrl}/user/quan-tam-cua-toi.html"><span class="icon-user icon-interest"></span>Quan tâm của tôi</a></h4>
                                    </div>
                                </div>
                            </div>
                        </div><!--Menu mua hàng-->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne1">
                                        <span class="icon-header-panel"></span>Bán hàng <b class="caret"></b>
                                    </a>

                                </h4>
                            </div>
                            <div id="collapseOne1" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="menu-panel">
                                        <h4><a href="${baseUrl}/user/dang-ban.html"><span class="icon-user icon-for-sales"></span>Đăng bán</a></h4>
                                    </div>
                                    <div class="menu-panel">
                                        <h4><a href="${baseUrl}/user/item.html"><span class="icon-user icon-list-pro"></span>Danh sách sản phẩm</a></h4>
                                    </div>
                                    <div class="menu-panel">
                                        <h4><a href="${baseUrl}/user/hoa-don-ban-hang.html"><span class="icon-user icon-buy-shipping"></span>Hoá đơn bán hàng & Vận đơn </a></h4>
                                    </div>
                                    <div class="menu-panel">
                                        <h4><a href="${baseUrl}/user/custormers.html"><span class="icon-user icon-list-customor"></span>Danh sách khách hàng </a></h4>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion1" href="#collapse-coupont" class="collapsed">
                                                    <span class="icon-coupont-header"></span>Khuyến mại - Giảm giá<b class="caret"></b>
                                                </a>

                                            </h4>

                                        </div>
                                        <div id="collapse-coupont" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <div class="menu-panel">
                                                    <h4><a href="${baseUrl}/user/itempromotion.html"><span class="icon-user icon-user-caret"></span>Giảm giá sản phẩm</a></h4>
                                                </div>
                                                <div class="menu-panel">
                                                    <h4><a href="${baseUrl}/user/categorypromotion.html"><span class="icon-user icon-user-caret"></span>Giảm giá danh mục</a></h4>
                                                </div>
                                                <div class="menu-panel">
                                                    <h4><a href="${baseUrl}/user/coupon.html"><span class="icon-user icon-user-caret"></span>Giảm giá theo Coupon</a></h4>
                                                </div>
                                                <div class="menu-panel">
                                                    <h4><a href="${baseUrl}/user/giftpromotion.html"><span class="icon-user icon-user-caret"></span>Khuyến mại quà tặng</a></h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion2" href="#collapse-pr" class="collapsed">
                                                    <span class="icon-marketing-pr"></span>Marketing - Quảng bá<b class="caret"></b>
                                                </a>

                                            </h4>

                                        </div>
                                        <div id="collapse-pr" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <div class="menu-panel">
                                                    <h4><a href="${baseUrl}/user/create-sms-marketing.html"><span class="icon-user icon-user-caret"></span>SMS Marketing</a></h4>
                                                </div>
                                                <div class="menu-panel">
                                                    <h4><a href="${baseUrl}/user/create-email-marketing.html"><span class="icon-user icon-user-caret"></span>Email Marketing</a></h4>
                                                </div>
                                                <div class="menu-panel">
                                                    <h4><a href="${baseUrl}/user/posting.html"><span class="icon-user icon-user-caret"></span>Up tin</a></h4>
                                                </div>
                                                <div class="menu-panel">
                                                    <h4><a href="${baseUrl}/user/vipitem.html"><span class="icon-user icon-user-caret"></span>Tin VIP</a></h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--                                    <div class="menu-panel">
                                                                            <h4><a href="#"><span class="icon-user icon-report-buss"></span>Báo cáo kinh doanh</a></h4>
                                                                        </div>-->
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion2" href="#collapse-config" class="collapsed">
                                                    <span class="icon-config-menu"></span>Cấu hình<b class="caret"></b>
                                                </a>

                                            </h4>

                                        </div>
                                        <div id="collapse-config" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <div class="menu-panel">
                                                    <h4><a href="${baseUrl}/user/cau-hinh-tich-hop.html"><span class="icon-user icon-user-caret"></span>Cấu hình thanh toán & vận chuyển</a></h4>
                                                </div>
                                                <div class="menu-panel">
                                                    <h4><a href="${baseUrl}/user/chinh-sach-ban-hang.html"><span class="icon-user icon-user-caret"></span>Chính sách bán hàng</a></h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>           
                                    <c:if test="${ushop == null}">
                                        <div class="menu-panel">
                                            <h4><a href="${baseUrl}/user/open-shop-step1.html"><span class="icon-user icon-create-shop"></span>Mở shop </a></h4>
                                        </div>
                                    </c:if>
                                    <c:if test="${ushop != null}">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a data-toggle="collapse" data-parent="#accordion2" href="#collapse-config-shop" class="collapsed">
                                                        <span class="icon-config-shop"></span>Quản trị shop<b class="caret"></b>
                                                    </a>

                                                </h4>
                                            </div>
                                            <div id="collapse-config-shop" class="panel-collapse collapse in">
                                                <div class="panel-body">
                                                    <div class="menu-panel">
                                                        <h4><a href="${baseUrl}/user/cau-hinh-shop-step1.html"><span class="icon-user icon-user-caret"></span>Cấu hình Shop</a></h4>
                                                    </div>
                                                    <div class="menu-panel">
                                                        <h4><a href="${baseUrl}/user/shop-banner.html"><span class="icon-user icon-user-caret"></span>Quản trị nội dung</a></h4>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div><!--Menu bán hàng-->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapse-user">
                                        <span class="icon-header-panel"></span>Quản lý cá nhân <b class="caret"></b>
                                    </a>

                                </h4>

                            </div>
                            <div id="collapse-user" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div class="menu-panel">
                                        <h4><a href="${baseUrl}/user/profile.html"><span class="icon-user icon-user-info"></span>Thông tin cá nhân</a></h4>
                                    </div>
                                    <div class="menu-panel">
                                        <h4><a href="${baseUrl}/user/quan-ly-thu.html"><span class="icon-user icon-messenger-notifition"></span>Tin nhắn & Thông báo</a></h4>
                                    </div>
                                    <div class="menu-panel">
                                        <h4><a href="${baseUrl}/user/tai-khoan-xeng.html"><span class="icon-user icon-xeng-account"></span>Tài khoản xèng</a></h4>
                                    </div>
                                </div>
                            </div>
                        </div><!--Menu Quản lý cá nhân-->
                    </div><!--Panel menu user-->
                </div>
                <!--                <div class="widget-menu-user box-left-content">
                                    <h4 class="title-box"><span class="icon-header-noti"></span> Thông báo</h4>
                                    <div class="widget-content">
                                        <div class="noti-block clearfix">
                                            <ul class="noti-block-item">
                                                <li>                                                            	
                                                    <h4>13/10/2013</h4>
                                                    <a href="#">Thông báo thay đổi chính sách phí người bán đảm bảo</a>
                                                </li>
                                                <li>                                                            	
                                                    <h4>13/10/2013</h4>
                                                    <a href="#">Thông báo thay đổi chính sách phí người bán đảm bảo</a>
                                                </li>
                                                <li>                                                            	
                                                    <h4>13/10/2013</h4>
                                                    <a href="#">Thông báo thay đổi chính sách phí người bán đảm bảo</a>
                                                </li>	
                                            </ul>
                                            <p class="pull-right small">» <a href="#">Xem toàn bộ</a></p>
                                        </div>
                                    </div>
                                </div> Thông báo-->
                <c:if test="${inOrder}">
                    <div class="widget-menu-user box-left-content">
                        <h4 class="title-box"><span class="icon-header-noti"></span> Chú giải biểu tượng</h4>
                        <div class="widget-content">
                            <div class="noti-block clearfix">
                                <p><strong>Tình trạng thanh toán</strong></p>
                                <p><span class="icon16-nopay"></span> Chưa thanh toán</p>
                                <p><span class="icon16-pay"></span> Đã thanh toán</p>
                                <p><span class="icon16-repaypart"></span> Hoàn một phần tiền</p>
                                <p><span class="icon16-repayall"></span> Hoàn toàn bộ tiền</p>
                                <hr>
                                <p><strong>Tình trạng vận chuyển</strong></p>
                                <p><span class="icon16-noship"></span> Chưa lấy hàng</p>
                                <p><span class="icon16-shipping"></span> Đang giao hàng</p>
                                <p><span class="icon16-ship-success"></span> Giao hàng thành công</p>
                                <p><span class="icon16-transfer"></span> Chuyển hoàn</p>
                                <p><span class="icon16-transfer-cancel"></span> Huỷ vận đơn</p>
                                <hr>
                                <p><strong>Đánh giá uy tín</strong></p>
                                <p><span class="icon16-reviewgray"></span> Chưa viết đánh giá</p>
                                <p><span class="icon16-review"></span> Đã viết đánh giá</p>
                                <p><span class="icon16-getreviewgray"></span> Chưa nhận được đánh giá</p>
                                <p><span class="icon16-getreview"></span> Đã nhận được đánh giá</p>

                            </div>
                        </div>
                    </div> <!--Chú giải biểu tượng-->
                </c:if>
                <div class="widget-menu-user box-left-content">
                    <h4 class="title-box"><span class="icon-header-question"></span> Có thể bạn chưa biết</h4>
                    <div class="widget-content">
                        <ul class="question-block">
                            <li class="clearfix">                                                            	
                                <h4 class="clr-org">Vì sao nên chấp nhận thanh toán qua NgânLượng</h4>
                                <ul class="list-question-item">
                                    <li>
                                        <h5>Miễn phí </h5>
                                        <p>Trừ trường hợp người mua thanh toán qua thẻ tín dụng</p>
                                    </li>
                                    <li>
                                        <h5>Tăng doanh số</h5>
                                        <p>Không chấp nhận thanh toán Online là từ chối khách hàng chuộng sự tiện lợi.</p>
                                    </li>
                                    <li>
                                        <h5>Giảm chi phí</h5>
                                        <p>Tỷ lệ người mua hủy đơn hàng và trả hàng rất thấp</p>
                                    </li>
                                    <li>
                                        <h5>Nhận NGAY tiền hàng</h5>
                                        <p>Ngay sau khi Khách hàng thanh toán</p>
                                    </li>
                                    <li>
                                        <h5>Tiện lợi & Đơn giản</h5>
                                        <p>Trên 30 phương thức thu tiền</p>
                                    </li>
                                    <li>
                                        <h5>Người mua tin tưởng hơn</h5>
                                        <p>Ngân Lượng bảo hiểm 100%</p>
                                    </li>
                                    <li>
                                        <h5>Bảo vệ người bán</h5>
                                        <p>Hạn chế rủi ro charge back</p>
                                    </li>
                                </ul>
                                <p class="pull-right small">» Đăng ký <a href="#">tại đây</a></p>
                            </li>
                            <li class="clearfix">                                                            	
                                <h4 class="clr-org">Vì sao nên chấp nhận hình thức Giao hàng - Thu tiền</h4>
                                <ul class="list-question-item">
                                    <li>
                                        <h5>Kích thích mua hàng: </h5>
                                        <p>100% người mua thích CoD. Người bán có thể giảm doanh số nếu không chấp nhận hình thức này.</p>
                                    </li>
                                    <li>
                                        <h5>Miễn phí</h5>
                                        <p>Miễn phí CoD toàn quốc &amp; Miễn phí chuyển hoàn nếu người mua từ chối nhận hàng</p>
                                    </li>
                                    <li>
                                        <h5>Thời gian tạm giữ tiền ngắn</h5>
                                        <p>Thanh toán tiền hàng 2 lần/tuần vào thứ 3 &amp; thứ 6</p>
                                    </li>
                                    <li>
                                        <h5>Mạng lưới vận chuyển hàng rộng khắp</h5>
                                        <p>Tới từng quận/huyện trên 63 tỉnh/thành cả nước</p>
                                    </li>
                                    <li>
                                        <h5>Tiện dụng</h5>
                                        <p>Kiểm tra hoặc tra cứu hành trình hàng Online</p>
                                    </li>
                                </ul>
                                <p class="pull-right small">» Đăng ký <a href="#">tại đây</a></p>
                            </li>
                            <li class="clearfix">                                                            	
                                <h4 class="clr-org">Vì sao nên sử dụng dịch vụ vận chuyển của ShipChung</h4>
                                <ul class="list-question-item">
                                    <li>
                                        <h5>Phí vận chuyển thấp</h5>
                                        <p>Nhờ liên kết với nhiều đối tác vận chuyển</p>
                                    </li>
                                    <li>
                                        <h5>Nhiều phương thức giao hàng</h5>
                                        <p>Giao hàng hỏa tốc, Giao hàng nhanh, Giao hàng tiết kiệm</p>
                                    </li>
                                    <li>
                                        <h5>Mạng lưới vận chuyển hàng rộng khắp </h5>
                                        <p>Tới từng quận/huyện trên 63 tỉnh/thành cả nước</p>
                                    </li>
                                    <li>
                                        <h5>Tiện dụng</h5>
                                        <p>Kiểm tra hoặc tra cứu hành trình hàng Online</p>
                                    </li>
                                </ul>
                                <p class="pull-right small">» Đăng ký <a href="#">tại đây</a></p>
                            </li>
                        </ul>                                
                    </div>
                </div> <!---Có thể bạn chưa biết-->
            </div>
        </div>
    </div>
</div>