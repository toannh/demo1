
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li>
            <a href="${baseUrl}/user/profile.html" target="_blank" >
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a>
        </li>
        <li class="active"><c:if test="${id.trim() != ''}">Sửa tin đăng bán</c:if><c:if test="${id.trim() == ''}">Đăng bán sản phẩm</c:if></li>
        </ol>
            <h1 class="title-pages"><c:if test="${id.trim() != ''}">Sửa tin đăng bán</c:if><c:if test="${id.trim() == ''}">Đăng bán sản phẩm</c:if></h1>
        <div class="tabs-content-user">
            <ul class="tab-title-content">
                    <li class="active"><a href="${baseUrl}/user/dang-ban.html">Đăng 1 tin bán</a></li>
            <li><a href="${baseUrl}/user/dang-ban-nhanh.html">Đăng nhanh</a></li>
<!--            <li><a href="#">Đăng Exel</a></li>
            <li><a href="#">Đăng Dịch vụ API, Crawling</a></li>-->
        </ul>
        <div class="tabs-content-block">
            <div class="tabs-content-user">
                <h3>Chúc mừng bạn đã đăng bán thành công!</h3>
                <ul class="about-cdt-step clearfix">
                    <li><a href="#">Giới thiệu các công cụ bán hàng hiệu quả tại ChợĐiệnTử</a></li>
                    <li><a href="${baseUrl}/user/dang-ban.html">Tiếp tục đăng bán</a></li>
                    <li><a href="${baseUrl}/user/dang-ban-tuong-tu.html?id=${item.id}">Đăng bán sản phẩm tương tự</a></li>
                    <li><a href="${baseUrl}/user/item.html">Quản trị sản phẩm đăng bán</a></li>
                    <li><a href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">Xem sản phẩm bạn vừa đăng</a></li>
                </ul>
                <c:if test="${seller==null || !seller.nlIntegrated || !seller.scIntegrated}">
                    <p>Hiện tại bạn chưa liên kết
                        <c:if test="${seller==null || !seller.nlIntegrated}">tài khoản thanh toán Online <strong>NgânLượng</strong> và</c:if>
                        <c:if test="${seller==null || !seller.scIntegrated}">tài khoản vận chuyển, giao hàng thu tiền toàn quốc <strong>ShipChung</strong></c:if> để hỗ trợ hoạt động bán hàng hiệu quả hơn tại <strong>ChợĐiệnTử</strong>. </p>
                        <div class="tabs-content-block">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#home" data-toggle="tab">Có thể bạn chưa biết</a></li>
                            </ul>                                        
                            <!-- Tab panes -->
                            <div class="tab-content ">
                                <div class="tab-pane active" id="home">
                                    <div class="row submit-success-info">
                                    <c:if test="${seller==null || !seller.nlIntegrated}">
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                            <h4 class="clr-org">Vì sao nên chấp nhận thanh toán qua NgânLượng</h4>
                                            <ul class="list-question-item">
                                                <li>
                                                    <h5>Miễn phí </h5>
                                                    <p>Áp dụng mọi hình thức thanh toán Online (qua thẻ tín dụng, tài khoản Ngân hàng, ví NgânLượng)</p>
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
                                            <p class="btn-position"><a href="${baseUrl}/user/cau-hinh-tich-hop.html" class="btn btn-default">Đăng ký tại đây</a></p>
                                        </div>
                                    </c:if>
                                    <c:if test="${seller==null || !seller.scIntegrated}">
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                            <h4 class="clr-org">Vì sao nên chấp nhận hình thức Giao hàng - Thu tiền</h4>
                                            <ul class="list-question-item">
                                                <li>
                                                    <h5>Kích thích mua hàng: </h5>
                                                    <p>100% người mua thích CoD. Người bán có thể giảm doanh số nếu không chấp nhận hình thức này.</p>
                                                </li>
                                                <li>
                                                    <h5>Miễn phí</h5>
                                                    <p>Miễn phí CoD toàn quốc & Miễn phí chuyển hoàn nếu người mua từ chối nhận hàng</p>
                                                </li>
                                                <li>
                                                    <h5>Thời gian tạm giữ tiền ngắn</h5>
                                                    <p>Thanh toán tiền hàng 2 lần/tuần vào thứ 3 & thứ 6</p>
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
                                            <p class="btn-position"><a href="${baseUrl}/user/cau-hinh-tich-hop.html" class="btn btn-default">Đăng ký tại đây</a></p>
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
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
                                            <p class="btn-position"><a href="${baseUrl}/user/cau-hinh-tich-hop.html" class="btn btn-default">Đăng ký tại đây</a></p>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

</div>