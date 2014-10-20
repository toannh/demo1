<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="http://chodientu.vn/text"%>
<%@taglib  prefix="url" uri="http://chodientu.vn/url"%>

<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a>
            <span class="tree-before"></span>
            <a class="last-item">Giỏ hàng</a>
            <span class="tree-after"></span>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <div class="bg-white">
            <div class="checkout">
                <div class="boxblue">
                    <div class="boxblue-title full-tab"><label class="lb-name">Hoàn tất đơn hàng</label></div>
                    <div class="boxblue-content">
                        <div class="main-content checkout-final">
                            <p><b>MÃ ĐƠN HÀNG: <span class="text-danger">${order.id}</span></b></p>
                            <br>
                            <p>
                                Cám ơn bạn đã mua hàng tại ChợĐiệnTử!  
                                <c:if test="${order.paymentMethod == 'COD'}">
                                    Bạn đã chọn hình thức Giao hàng - Thu tiền (CoD), trong vòng 24-72h bạn sẽ nhận được hàng và số tiền cần thanh toán khi nhận hàng là: <b class="text-danger">${text:numberFormat(order.finalPrice)} <span class="price" >đ</span></b></p>
                                </c:if>
                                <c:if test="${order.paymentMethod == 'NONE'}">
                                Người bán sẽ liên hệ với bạn trong thời gian sớm nhất
                            </c:if>
                            <br>
                            <ul>
                                <li><b>Bạn có thể theo dõi hành trình của hàng đã mua trên màn hình quản lý mua hàng cá nhân.</b></li>
                                <li><b>Trong trường hợp không nhận được hàng đúng như mô tả trong thời gian thỏa thuận, bạn có thể liên hệ với Trung tâm hỗ trợ Khách hàng của ChợĐiệnTử để được hỗ trợ.</b></li>
                                <li><b>Đừng quên đánh giá uy tín người bán sau giao dịch nhằm xây dựng một cộng đồng mua bán trong sạch, hiệu quả tại ChợĐiệnTử.</b></li>
                            </ul>
                            <br>
                            <p>Bạn có muốn:</p>
                            <ul>
                                <li>Xem lại <a href="${baseUrl}/${order.id}/chi-tiet-don-hang.html" >hóa đơn mua hàng</a></li>
                                <li>Trở về màn hình <a  href="${baseUrl}/user/don-hang-cua-toi.html">Quản lý mua hàng</a></li>
                                <li>Trở về <a href="${baseUrl}" >trang chủ ChợĐiệnTử</a></li>
                            </ul>
                        </div><!-- checkout-final -->
                    </div><!-- boxblue-content -->
                </div><!-- boxblue -->
            </div><!-- checkout -->
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
</div>