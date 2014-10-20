<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username != null ? viewer.user.username : viewer.user.email}</a></li>
        <li class="active" rel="text-menu">Đổi xèng lấy mã thẻ</li>
    </ol>
    <h1 class="title-pages" rel="text-header">Đổi xèng lấy mã thẻ</h1>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li class="active" style="cursor: pointer" rel="topupCard"><a  onclick="topup.changeTab('topupCard');">Đổi mã thẻ</a></li>
            <li style="cursor: pointer" rel="topupTel"><a  onclick="topup.changeTab('topupTel');">Nạp thẻ điện thoại</a></li>
        </ul>
        <div class="tabs-content-block">

            <div class="row" rel="topupCard" id="topupCard">
                <div class="form form-horizontal">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <select class="form-control" rel="service" onchange="topup.changeAmountTopUpCard('topupCard');">
                                <option value="">Chọn nhà mạng</option>
                                <option value="MOBIFONE">MOBIFONE</option>
                                <option value="VIETTEL">VIETTEL</option>
                                <option value="VINAPHONE">VINAPHONE</option>
                                <option value="GMOBILE">GMOBILE</option>
                                <option value="VIETNAMMOBILE">VIETNAMMOBILE</option>
                            </select>
                        </div>
                        <div class="col-sm-3">
                            <select class="form-control" rel="parValue" onchange="topup.changeAmountTopUpCard('topupCard');">
                                <option value="">Chọn mệnh giá thẻ</option>
                                <option value="CARD_10000">10.000</option>
                                <option value="CARD_20000">20.000</option>
                                <option value="CARD_30000">30.000</option>
                                <option value="CARD_50000">50.000</option>
                                <option value="CARD_100000">100.000</option>
                                <option value="CARD_200000">200.000</option>
                                <option value="CARD_300000">300.000</option>
                                <option value="CARD_500000">500.000</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Số điện thoại:</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">${viewer.user.phone}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Email nhận mã thẻ:</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">${viewer.user.email}</p>
                        </div>
                    </div>
                    <div class="form-group" hidden="" name="service">
                        <label class="control-label col-sm-3">Loại thẻ:</label>
                        <div class="col-sm-9">
                            <p class="form-control-static" name="service"></p>
                        </div>
                    </div>
                    <div class="form-group" hidden="" name="amount">
                        <label class="control-label col-sm-3">Mệnh giá thẻ:</label>
                        <div class="col-sm-9">
                            <p class="form-control-static" name="amount">0<sup>đ</sup></p>
                        </div>
                    </div>
                    <div class="form-group" hidden="" name="cashPayment">
                        <label class="control-label col-sm-3">Số xèng cần thanh toán:</label>
                        <div class="col-sm-9">
                            <p class="form-control-static" name="cashPayment">0 xèng</p>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <button type="submit" class="btn btn-danger" onclick="topup.paymentTopupCard();">Đồng ý</button>
                            <button type="reset" class="btn btn-default" onclick="topup.cancelPayment();">Huỷ</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <p class="form-control-static text-danger">* Lưu ý: Mã thẻ và số serial sẽ được gửi vào mail và SMS của bạn.</p>
                        </div>
                    </div>
                </div><!-- /form -->
            </div><!-- /row -->


            <div class="row" hidden="" rel="topupTel" id="topupTel">
                <div class="form form-horizontal">
                    <div class="form-group">
                        <div class="col-sm-12">
                            <p class="form-control-static">Nạp tiền cho thuê bao trả trước.</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-3">
                            <select class="form-control" rel="parValue" onchange="topup.changeAmount('topupTel');">
                                <option value="">Chọn mệnh giá thẻ</option>
                                <option value="CARD_10000">10.000</option>
                                <option value="CARD_20000">20.000</option>
                                <option value="CARD_30000">30.000</option>
                                <option value="CARD_50000">50.000</option>
                                <option value="CARD_100000">100.000</option>
                                <option value="CARD_200000">200.000</option>
                                <option value="CARD_300000">300.000</option>
                                <option value="CARD_500000">500.000</option>
                            </select>
                        </div>
                        <div class="col-sm-3">
                            <input name="phone" type="text" class="form-control" placeholder="Số điện thoại">
                        </div>
                    </div>
                    <div class="form-group" hidden="" name="amount">
                        <label class="control-label col-sm-3">Số tiền cần nạp:</label>
                        <div class="col-sm-9">
                            <p class="form-control-static" name="amount">0<sup>đ</sup></p>
                        </div>
                    </div>
                    <div class="form-group" hidden="" name="cashPayment">
                        <label class="control-label col-sm-3">Số xèng cần thanh toán:</label>
                        <div class="col-sm-9">
                            <p class="form-control-static" name="cashPayment">0 xèng</p>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <button type="submit" class="btn btn-danger" onclick="topup.paymentTopupTel();">Đồng ý</button>
                            <button type="reset" class="btn btn-default" onclick="topup.cancelPayment();">Huỷ</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <p class="form-control-static text-danger">* Lưu ý: Tài khoản  của bạn sẽ được cộng tiền ngay sau khi thực hiện giao dịch.</p>
                        </div>
                    </div>
                </div><!-- /form -->
            </div>

        </div><!-- /tabs-content-block -->
    </div><!-- /tabs-content-user -->
</div>