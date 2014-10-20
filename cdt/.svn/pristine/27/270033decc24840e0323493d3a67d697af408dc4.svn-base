<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span><a href="${baseUrl}"> Trang chủ</a></li>
        <li>
            <a href="${baseUrl}/user/profile.html">
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a>
        </li>
        <li class="active">Tài khoản</li>
    </ol>
    <h1 class="title-pages">Bảng điều khiển</h1>
    <jsp:useBean id="date" class="java.util.Date" />
    <div class="tabs-content-user">
        <div class="row dashboard-pages">
            <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 reset-padding">
                <h4>Thông tin liên hệ</h4>
                <div class="border-box-cod">
                    <p>Thành viên: <a href="${baseUrl}/user/profile.html"><strong>${user.name}</strong></a></p>
                                <jsp:setProperty name="date" property="time" value="${user.joinTime}" /> 
                    <p class="mgt-15"><span class="fa fa-calendar"></span> Tham gia ngày: <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate></p>
                    <c:if test="${user.address!=null && user.address!=''}"><p><span class="fa fa-map-marker"></span> ${user.address}</p></c:if>
                    <c:if test="${user.email!=null && user.email!=''}"><p><span class="fa fa-envelope"></span> ${user.email} </p></c:if>
                    <c:if test="${user.phone!=null && user.phone!=''}"><p><span class="fa fa-phone"></span> ${user.phone}</p></c:if>
                    <c:if test="${user.yahoo!=null && user.yahoo!=''}"><p><span class="fa fa-comments"></span> ${user.yahoo}</p></c:if>
                    <c:if test="${user.skype!=null && user.skype!=''}"><p><span class="fa fa-skype"></span> ${user.skype}</p></c:if>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 reset-padding">
                    <h4>Đánh giá uy tín người bán</h4>
                <c:if test="${userReview.totalPoint > 0}">
                    <div class="border-box-cod">
                        
                        <p><span class="clr-red"><strong><fmt:formatNumber value="${userReview.good * 100 / (userReview.good + userReview.normal + userReview.bad)}" pattern="0" />%</strong></span> người mua đánh giá tốt</p>	
                        <div class="mgt-15 clearfix">
                            <div class="col-sm-3 reset-padding-all">Tốt</div>
                            <div class="col-sm-7 reset-padding-right">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: ${userReview.good * 100/userReview.totalPoint}%"></div>
                                </div>                                            
                            </div>
                            <div class="col-sm-2">${userReview.good}</div>

                        </div>
                        <div class="clearfix">
                            <div class="col-sm-3 reset-padding-all">Trung bình</div>
                            <div class="col-sm-7 reset-padding-right">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:${userReview.normal * 100/userReview.totalPoint}%"></div>
                                </div>                                            
                            </div>
                            <div class="col-sm-2">${userReview.normal}</div>
                        </div>
                        <div class="clearfix">
                            <div class="col-sm-3 reset-padding-all">Không hài lòng</div>
                            <div class="col-sm-7 reset-padding-right">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: ${userReview.bad * 100/userReview.totalPoint}%"></div>
                                </div>                                            
                            </div>
                            <div class="col-sm-2">${userReview.bad}</div>
                        </div>   
                    </div>
                </c:if>
                <c:if test="${userReview.totalPoint <=0}">
                    <div class="border-box-cod">Chưa có đánh giá nào</div>
                </c:if>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 reset-padding-all">
                <h4>Đánh giá uy tín gần đây</h4>
                <div class="border-box-cod">
                    <c:forEach items="${sellerReviewlist}" var="reviewlist">
                        <c:set var="flag" value="true" />
                        <c:forEach items="${userByIds}" var="users">
                            <c:if test="${users.id==reviewlist.userId && flag}">
                                <c:set var="flag" value="false" />
                                <p><span class="glyphicon glyphicon-ok-circle"></span>
                                    <c:set var="originalDetail" value="${fn:substring(fn:trim(reviewlist.content), 0, 40)}" />
                                    ${originalDetail} ...
                                </p>
                                <jsp:setProperty name="date" property="time" value="${reviewlist.createTime}" /> 

                                <p class="text-right"><a href="${baseUrl}/user/${users.id}/ho-so-nguoi-ban.html" target="_blank">${users.name}</a>, <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate></p>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </div>
            </div>

            <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 reset-padding">
                <h4>Mua hàng</h4>
                <div class="border-box-cod">                                  
                    <p><strong class="col-sm-2 reset-padding-all">${buyerPaymentPaid}</strong> <a href="${baseUrl}/user/don-hang-cua-toi.html?tab=paymentPAID">Đơn hàng đã thanh toán</a></p>
                    <p><strong class="col-sm-2 reset-padding-all">${buyerPaymentNotPaid}</strong> <a href="${baseUrl}/user/don-hang-cua-toi.html?tab=paymentNEW">Đơn hàng chưa thanh toán</a></p>
                    <p><strong class="col-sm-2 reset-padding-all">${buyerShipNEW}</strong> <a href="${baseUrl}/user/don-hang-cua-toi.html?tab=shipmentNEW">Đơn hàng chưa được giao</a></p>
                    <p><strong class="col-sm-2 reset-padding-all">${countTab.running}</strong> <a href="${baseUrl}/user/theo-doi-dau-gia.html?tab=running">Sản phẩm đang đấu giá</a></p>
                    <p><strong class="col-sm-2 reset-padding-all">${itemStatusHistogram_all}</strong> <a href="${baseUrl}/user/item.html">Tất cả sản phẩm</a></p>
                    <!--<p><strong class="col-sm-1 reset-padding-all">2</strong> <a href="#">Sản phẩm quan tâm sắp hết hạn</a></p>-->
                </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 reset-padding">
                <h4>Bán hàng</h4>
                <div class="border-box-cod">                                  
                    <p><strong class="col-sm-2 reset-padding-all">${sellerPaymentPaid}</strong> <a href="${baseUrl}/user/hoa-don-ban-hang.html?tab=paymentPAID"> Đơn hàng đã thanh toán</a></p>
                    <p><strong class="col-sm-2 reset-padding-all">${sellerPaymentNotPaid}</strong> <a href="${baseUrl}/user/hoa-don-ban-hang.html?tab=paymentNEW"> Đơn hàng chưa thanh toán</a></p>
                    <p><strong class="col-sm-2 reset-padding-all">${sellerShipNEW}</strong> <a href="${baseUrl}/user/hoa-don-ban-hang.html?tab=shipmentNEW"> Đơn hàng chưa giao</a></p>
                    <p><strong class="col-sm-2 reset-padding-all">${itemStatusHistogram_selling}</strong> <a href="${baseUrl}/user/item.html?page=1&find=selling"> Sản phẩm đang bán</a></p>
                    <p><strong class="col-sm-2 reset-padding-all">${itemStatusHistogram_uncompleted}</strong> <a href="${baseUrl}/user/item.html?tab=completed"> Sản phẩm cần cập nhật</a></p>
                    <p class="mgt-25">Bạn hiện có: <span class="clr-red text-normal">${text:numberFormat(cash.balance)}</span> xèng<br>
                        <a href="${baseUrl}/user/tai-khoan-xeng.html" target="_blank">Nạp xèng</a> | <a href="${baseUrl}/user/vipitem.html" target="_blank">Mua tin VIP</a> | <a href="${baseUrl}/user/posting.html" target="_blank">Up tin</a></p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6 col-xs-12 reset-padding-all">
                <h4>Shop</h4>
                <div class="border-box-cod">
                    <c:if test="${shop!=null}">
                        <p><span class="glyphicon glyphicon-asterisk small clr-999"></span> Vào trang Shop <a href="${baseUrl}/${shop.alias}/" target="_blank">${shop.alias}</a></p>
                        </c:if>
                        <c:if test="${shop==null}">
                        <p><span class="glyphicon glyphicon-asterisk small clr-999"></span> Bạn chưa <a href="${baseUrl}/user/open-shop-step1.html">mở Shop bán hàng</a> tại ChợĐiệnTử</p>
                    </c:if>
                    <c:if test="${!seller.nlIntegrated}">
                        <p class="mgt-15"><span class="glyphicon glyphicon-asterisk small clr-999"></span>  Bạn chưa liên kết tài khoản thanh toán online</p>
                        <p class="text-center"><a href="${baseUrl}/user/cau-hinh-tich-hop.html" target="_blank" class="btn btn-default btn-sm"><span class="icon-no-nl"></span> Liên kết ngay</a></p>
                    </c:if>
                    <c:if test="${seller.nlIntegrated}">
                        <p class="mgt-15"><span class="glyphicon glyphicon-asterisk small clr-999"></span>  Liên kết tài khoản thanh toán online</p>

                        <a class="btn btn-default btn-sm btn-block"><span class="icon-nl"></span>${seller.nlEmail}</a>

                    </c:if>
                    <c:if test="${!seller.scIntegrated}">   
                        <p class="mgt-15"><span class="glyphicon glyphicon-asterisk small clr-999"></span> Bạn chưa liên kết tài khoản <strong>Shipchung</strong></p>
                        <p class="text-center"><a href="${baseUrl}/user/cau-hinh-tich-hop.html" target="_blank" class="btn btn-default btn-sm"><span class="icon-no-sc"></span> Liên kết ngay</a></p>       
                    </c:if> 
                    <c:if test="${seller.scIntegrated}">  
                        <p class="mgt-15"><span class="glyphicon glyphicon-asterisk small clr-999"></span> Liên kết tài khoản <strong>Shipchung</strong></p>
                        <a class="btn btn-default btn-sm btn-block"><span class="icon-sc"></span> ${seller.scEmail}</a>
                    </c:if> 
                </div>
            </div>                            

        </div>
        <div class="row dashboard-pages">
            <h4>Có thể bạn chưa biết</h4>
            <div class="border-box-cod box-dont-know">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 reset-padding"> 
                    <h5>Vì sao nên mua hàng tại ChợĐiệnTử</h5>
                    <div class="clearfix">
                        <div class="col-sm-1 reset-padding"><span class="fa fa-qrcode"></span></div>
                        <div class="col-sm-11">
                            <h5>Sản phẩm phong phú</h5>
                            Hàng triệu sản phẩm thường xuyên được cập nhật
                        </div>
                    </div>
                    <div class="clearfix">
                        <div class="col-sm-1 reset-padding"><span class="fa fa-tags"></span></div>
                        <div class="col-sm-11">
                            <h5>Giá rẻ</h5>
                            Nhiều chương trình ưu đãi giảm giá và đấu giá hấp dẫn
                        </div>
                    </div>
                    <div class="clearfix">
                        <div class="col-sm-1 reset-padding"><span class="fa fa-user"></span></div>
                        <div class="col-sm-11">
                            <h5>Người bán uy tín</h5>
                            Nhiều người bán lớn trên thị trường offline đã tham gia CĐT
                        </div>
                    </div>
                    <div class="clearfix">
                        <div class="col-sm-1 reset-padding"><span class="fa fa-credit-card"></span></div>
                        <div class="col-sm-11">
                            <h5>Thanh toán Online an toàn, tiện lợi</h5>
                            Thanh toán qua NgânLượng với thẻ tín dụng, thẻ ATM, internet banking, tài khoản ví NgânLượng .., được bảo hiểm 100%
                        </div>
                    </div>
                    <div class="clearfix">
                        <div class="col-sm-1 reset-padding"><span class="fa fa-truck"></span></div>
                        <div class="col-sm-11">
                            <h5>Thanh toán sau nhận hàng (CoD)</h5>
                            Rất nhiều người bán hỗ trợ, an toàn 100%
                        </div>
                    </div>
                    <div class="clearfix">
                        <div class="col-sm-1 reset-padding"><span class="fa fa-thumbs-up"></span></div>
                        <div class="col-sm-11">
                            <h5>Môi trường giao dịch lành mạnh</h5>
                            Hệ thống đánh giá uy tín, giúp người mua lựa chọn người bán đảm bảo nhất 
                        </div>
                    </div>                                
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 reset-padding"> 
                    <h5>Vì sao nên bán hàng tại ChợĐiệnTử </h5>
                    <div class="clearfix">
                        <div class="col-sm-1 reset-padding"><span class="fa fa-bar-chart-o"></span></div>
                        <div class="col-sm-11">
                            <h5>Tăng doanh thu</h5>
                            Bán hàng tới trên 3 triệu thành viên, tốc độ phát triển nhanh
                        </div>
                    </div>
                    <div class="clearfix">
                        <div class="col-sm-1 reset-padding"><span class="fa fa-shield"></span></div>
                        <div class="col-sm-11">
                            <h5>Uy tín</h5>
                            Website tiên phong, lâu đời với sự đầu tư của tập đoàn Thương mại điện tử lớn nhất toàn cầu eBay
                        </div>
                    </div>
                    <div class="clearfix">
                        <div class="col-sm-1 reset-padding"><span class="fa fa-usd"></span></div>
                        <div class="col-sm-11">
                            <h5>Thu tiền dễ dàng</h5>
                            <span class="glyphicon glyphicon-asterisk small clr-999"></span> Thu tiền NGAY qua NgânLượng.vn với 30 ngân hàng.<br>
                            <span class="glyphicon glyphicon-asterisk small clr-999"></span> Giao hàng - Thu tiền (CoD) qua ShipChung.vn.
                        </div>
                    </div>
                    <div class="clearfix">
                        <div class="col-sm-1 reset-padding"><span class="fa fa-truck"></span></div>
                        <div class="col-sm-11">
                            <h5>Giao hàng nhanh, theo dõi hành trình gửi hàng</h5>
                            Mạng lưới đối tác vận chuyển nhiều và rộng khắp 63 tỉnh/thành
                        </div>
                    </div>
                    <div class="clearfix">
                        <div class="col-sm-1 reset-padding"><span class="fa fa-bullhorn"></span></div>
                        <div class="col-sm-11">
                            <h5>Công cụ bán hàng tiện ích, hiệu quả</h5>
                            <span class="glyphicon glyphicon-asterisk small clr-999"></span> Nhiều hình thức bán hàng phong phú: mua ngay, đấu giá, khuyến mại <br>
                            <span class="glyphicon glyphicon-asterisk small clr-999"></span> Nhiều công cụ marketing hiệu quả: Shop chuyên nghiệp, Uptin, tin Vip, Popnet, Adnet, Email marketing, SEO
                        </div>
                    </div>

                </div>
                <div class="clearfix"></div>
            </div>

        </div>                        
    </div>
</div>