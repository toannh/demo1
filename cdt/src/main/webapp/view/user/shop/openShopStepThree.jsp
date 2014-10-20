<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a>
            <span class="tree-normal"></span>
            <a href="${baseUrl}/user/open-shop-step1.html">Mở shop</a>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <div class="bg-white">
            <div class="page-openshop">
                <div class="openshop-title">Mở shop miễn phí</div>
                <div class="openshop-step row">
                    <div class="col-sm-4 active">
                        <div class="os-circle">Bước<span>1</span></div>
                        <div class="os-title"><div class="os-cell">Tạo tài khoản trên <span class="text-danger">ChợĐiệnTử</span></div></div>
                    </div><!-- col-sm-4 -->
                    <div class="col-sm-4 active">
                        <div class="os-circle">Bước<span>2</span></div>
                        <div class="os-title"><div class="os-cell">Chứng thực tài khoản</div></div>
                    </div><!-- col-sm-4 -->
                    <div class="col-sm-4 active">
                        <div class="os-circle">Bước<span>3</span></div>
                        <div class="os-title"><div class="os-cell">Liên kết với<br>Ngân Lượng và Shipchung</div></div>
                    </div><!-- col-sm-4 -->
                </div><!-- openshop-step -->
                <div class="openshop-content step3-active">
                    <span class="oc-bullet"></span>
                    <c:if test="${seller.nlIntegrated && seller.scIntegrated && seller.nlEmail != null && seller.scEmail != null && seller.nlEmail != '' && seller.scEmail != ''}">
                        <div class="step3-desc">
                            Bạn đã <b class="text-danger">Liên kết</b> tài khoản của bạn với tài khoản <span class="text-danger">NgânLượng</span> và <span class="text-danger">Shipchung</span>!
                            <span class="r-time" style="color: red" ></span>
                        </div>
                    </c:if>
                    <c:if test="${!seller.nlIntegrated && !seller.scIntegrated && seller.nlEmail == null && seller.scEmail == null}">
                        <div class="step3-desc">
                            Bạn chưa <b class="text-danger">Liên kết</b> tài khoản của bạn với tài khoản <span class="text-danger"> NgânLượng</span> và <span class="text-danger">Shipchung</span>! Hãy thực hiện <b class="text-danger">Liên kết</b> để hoàn thành việc mở shop.
                        </div>
                    </c:if>
                    <c:if test="${!seller.nlIntegrated && seller.scIntegrated && seller.nlEmail == null && seller.scEmail != null}">
                        <div class="step3-desc">
                            Bạn đã <b class="text-danger">Liên kết</b> tài khoản của bạn với tài khoản<span class="text-danger"> Shipchung</span>! Hãy thực hiện <b class="text-danger"> Liên kết </b> tài khoản  <span class="text-danger"> NgânLượng</span>  để hoàn thành việc mở shop.
                        </div>
                    </c:if>
                    <c:if test="${seller.nlIntegrated && !seller.scIntegrated && seller.nlEmail != null && seller.scEmail == null}">
                        <div class="step3-desc">
                            Bạn đã <b class="text-danger">Liên kết</b> tài khoản của bạn với tài khoản<span class="text-danger"> NgânLượng</span>! Hãy thực hiện <b class="text-danger">Liên kết </b> tài khoản <span class="text-danger">Shipchung</span>  để hoàn thành việc mở shop.
                        </div>
                    </c:if>
                    <div class="border-config-payment"> 
                        <h5 class="title-config-payment">Liên kết tài khoản Ngân Lượng để chấp nhận Thanh toán Online <span class="glyphicon glyphicon-question-sign tool-tip" data-toggle="tooltip" data-placement="top" title="" data-original-title="Chưa làm tooltip nhé"></span> </h5>                                        
                        <div class="row mgt-15">
                            <div class="col-lg-8 col-md-7 col-xs-12">
                                <ul class="list-policy-payment">
                                    <li><strong>Miễn phí hoàn toàn:</strong> Áp dụng mọi hình thức thanh toán Online (qua thẻ tín dụng, tài khoản Ngân hàng, ví NgânLượng)</li>
                                    <li><strong>Tăng doanh số:</strong> Không chấp nhận thanh toán Online là từ chối khách hàng chuộng sự tiện lợi.</li>
                                    <li><strong>Giảm chi phí:</strong> Tỷ lệ người mua hủy đơn hàng và trả hàng rất thấp</li>
                                    <li><strong>Nhận NGAY tiền hàng:</strong> Ngay sau khi Khách hàng thanh toán</li>
                                    <li><strong>Tiện lợi &amp; Đơn giản:</strong> Trên 30 phương thức thu tiền</li>
                                    <li><strong>Người mua tin tưởng hơn:</strong> Ngân Lượng bảo hiểm 100%</li>
                                    <li><strong>Bảo vệ người bán:</strong> Hạn chế rủi ro charge back</li>

                                </ul>
                            </div>
                            <c:if test="${seller.nlEmail != null && seller.nlIntegrated}">
                                <div class="col-lg-4 col-md-5 col-xs-12">
                                    <a  class="btn btn-default btn-sm btn-block"><span class="icon-nl"></span> ${seller.nlEmail}</a>
                                    <p class="text-right" onclick="shop.nlShopUnlinked();"><a style="cursor: pointer" id="nl-shop-unlinked">Huỷ liên kết</a></p>
                                </div>   
                            </c:if>
                            <c:if test="${seller.nlEmail == null || !seller.nlIntegrated}">
                                <div class="col-lg-4 col-md-5 col-xs-12">
                                    <a  class="btn btn-default btn-sm btn-block" id="nl-shop-integration"><span class="icon-no-nl"></span> Liên kết tài khoản Ngân Lượng</a>
                                    <a href="https://www.nganluong.vn/?portal=nganluong&page=user_register">Đăng ký</a> nếu chưa có tài khoản NgânLượng
                                </div>
                            </c:if>
                        </div>
                        <div class="clearfix"></div>
                        <h5 class="title-config-payment mgt-15">Liên kết tài khoản ShipChung để sử dụng dịch vụ Giao hàng - Thu tiền (CoD) hoặc vận chuyển thông thường <span class="glyphicon glyphicon-question-sign tool-tip" data-toggle="tooltip" data-placement="top" title="" data-original-title="Chưa làm tooltip nhé"></span> </h5>
                        <div class="row mgt-15">
                            <div class="col-lg-8 col-md-7 col-xs-12">
                                <p class="clr-red"><strong>Giao hàng - Thu tiền (CoD)</strong></p>
                                <ul class="list-policy-payment">
                                    <li><strong>Kích thích mua hàng:</strong> 100% người mua thích CoD. Người bán có thể giảm doanh số nếu không chấp nhận hình thức này.</li>
                                    <li><strong>Miễn phí:</strong> Miễn phí CoD toàn quốc &amp; Miễn phí chuyển hoàn nếu người mua từ chối nhận hàng</li>
                                    <li><strong>Thời gian tạm giữ tiền ngắn:</strong> Thanh toán tiền hàng 1 tuần/lần vào thứ 6</li>                                                     
                                    <li><strong>Mạng lưới vận chuyển hàng rộng khắp:</strong> Tới từng quận/huyện trên 63 tỉnh/thành cả nước</li>
                                    <li><strong>Tiện dụng:</strong> Kiểm tra hoặc tra cứu hành trình hàng Online</li>

                                </ul>
                                <p class="clr-red  mgt-15"><strong>Dịch vụ vận chuyển của ShipChung</strong></p>
                                <ul class="list-policy-payment">
                                    <li><strong>Miễn phí hoàn toàn:</strong> Áp dụng mọi hình thức thanh toán Online (qua thẻ tín dụng, tài khoản Ngân hàng, ví NgânLượng)</li>
                                    <li><strong>Tăng doanh số:</strong> Không chấp nhận thanh toán Online là từ chối khách hàng chuộng sự tiện lợi.</li>
                                    <li><strong>Giảm chi phí:</strong> Tỷ lệ người mua hủy đơn hàng và trả hàng rất thấp</li>
                                    <li><strong>Nhận NGAY tiền hàng:</strong> Ngay sau khi Khách hàng thanh toán</li>
                                    <li><strong>Tiện lợi &amp; Đơn giản:</strong> Trên 30 phương thức thu tiền</li>
                                    <li><strong>Người mua tin tưởng hơn:</strong> Ngân Lượng bảo hiểm 100%</li>
                                    <li><strong>Bảo vệ người bán:</strong> Hạn chế rủi ro charge back</li>

                                </ul>                                                
                            </div>
                            <c:if test="${seller.scEmail != null && seller.scIntegrated}">
                                <div class="col-lg-4 col-md-5 col-xs-12">
                                    <a class="btn btn-default btn-sm btn-block"><span class="icon-sc"></span> ${seller.scEmail}</a>
                                    <p class="text-right" onclick="shop.scShopUnlinked();"><a id="sc-shop-unlinked" style="cursor: pointer">Huỷ liên kết</a></p>
                                </div>
                            </c:if>
                            <c:if test="${seller.scEmail == null || !seller.scIntegrated}">
                                <div class="col-lg-4 col-md-5 col-xs-12">
                                    <a class="btn btn-default btn-sm btn-block" id="sc-shop-integration"><span class="icon-no-sc"></span> Liên kết tài khoản ShipChung</a>
                                    <a href="https://www.nganluong.vn/?portal=nganluong&page=user_register">Đăng ký</a> nếu chưa có tài khoản ShipChung
                                </div>
                            </c:if>
                        </div>
                    </div> <!--border-config-payment--->
                    <div class="sf-row text-center">
                        <a class="btn-remove" href="${baseUrl}"><span class="glyphicon glyphicon glyphicon-remove"></span> Huỷ</a>
                        <a href="${baseUrl}/user/openshop-finish.html" class="btn btn-danger btn-lg ${(seller.scIntegrated && seller.nlIntegrated)?'':'disabled'}">Tiếp tục<span class="glyphicon glyphicon-circle-arrow-right"></span></a>
                    </div>
                </div><!-- openshop-content -->
                <div class="sf-row"><input name="stepcheck" type="checkbox" disabled="true" checked="check" value="0" class="step-chk" >Tôi đã đọc, hiểu và chấp nhận các <a href="#">điều kiện mở Shop bán hàng tại ChợĐiệnTử</a></div>
            </div>
        </div>
    </div>
</div>