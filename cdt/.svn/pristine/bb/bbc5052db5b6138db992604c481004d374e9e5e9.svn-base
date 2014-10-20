<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li>
            <a href="${baseUrl}/user/profile.html" target="_blank" >
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}
            </a>
        </li>
        <li class="active">Hoá đơn bán hàng</li>
    </ol>
    <h1 class="title-pages">Tạo hoá đơn</h1>
    <div class="tabs-content-user">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-left">
                <h2 class="title-box-user">Thông tin người mua</h2>
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Họ và tên: <span class="clr-red">*</span></label>
                        <div class="col-sm-9">
                            <input type="text" for="buyer" name="buyerName" class="form-control" placeholder="Nhập họ và tên người mua">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Điện thoại: <span class="clr-red">*</span></label>
                        <div class="col-sm-9">
                            <input type="text" for="buyer" name="buyerPhone" class="form-control" placeholder="Nhập số điện thoại người mua">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Email: <span class="clr-red">*</span></label>
                        <div class="col-sm-9">
                            <input type="email" for="buyer" name="buyerEmail" class="form-control" placeholder="Nhập email người mua">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Địa chỉ: <span class="clr-red">*</span></label>
                        <div class="col-sm-9">
                            <input type="text" for="buyer" name="buyerAddress" class="form-control" placeholder="Nhập địa chỉ người mua">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Tỉnh/Thành: <span class="clr-red">*</span></label>
                        <div class="col-sm-9">
                            <select class="form-control" name="buyerCityId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Quận/Huyện: <span class="clr-red">*</span></label>
                        <div class="col-sm-9">
                            <select class="form-control" name="buyerDistrictId">
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right">
                <h2 class="title-box-user">Thông tin người nhận <span class="pull-right text-title-box-user"><input type="checkbox" name="syncInvoice"> Giống thông tin người mua</span></h2>
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Họ và tên: <span class="clr-red">*</span></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" for="buyerName" name="receiverName" placeholder="Nhập họ và tên người nhận">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Điện thoại: <span class="clr-red">*</span></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" for="buyerPhone" name="receiverPhone" placeholder="Nhập số điện thoại người nhận">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Email: <span class="clr-red">*</span></label>
                        <div class="col-sm-9">
                            <input type="email" class="form-control" for="buyerEmail" name="receiverEmail" placeholder="Nhập email người nhận">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Địa chỉ: <span class="clr-red">*</span></label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" for="buyerAddress" name="receiverAddress" placeholder="Nhập địa chỉ người nhân">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Tỉnh/Thành: <span class="clr-red">*</span></label>
                        <div class="col-sm-9">
                            <select class="form-control" name="receiverCityId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Quận/Huyện: <span class="clr-red">*</span></label>
                        <div class="col-sm-9">
                            <select class="form-control" name="receiverDistrictId">
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row box-choose-all-product">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-left">
                <div class="tabs-content-block">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#choose-products" data-toggle="tab">Hãy chọn sản phẩm</a></li>
                    </ul>                                        
                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div class="tab-pane active" id="choose-products">
                            <div class="input-group">
                                <input type="text" placeholder="Tìm kiếm" value="" class="form-control" name="itemKeyword">
                                <input type="hidden" value="${viewer.user.id}" class="form-control" name="sellerId">
                                <input type="hidden" value="${orderIdInvoice}" class="form-control" name="orderId">
                                <span style="cursor:pointer;" class="input-group-addon" onclick="invoice.searchItem(200);"><span class="glyphicon glyphicon-search"></span></span>
                            </div>
                            <br>
                            <div class="choose-product-col" style="width:100%;">
                                <ul class="list-product-choose" id="htmlItemSearch">
                                </ul>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right">
                <div class="tabs-content-block">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#bill-content" data-toggle="tab">Nội dung hoá đơn</a></li>
                    </ul>                                        
                    <!-- Tab panes -->
                    <div class="tab-content ">
                        <div class="tab-pane active" id="bill-content">
                            <div class="list-product-in-bill" id="itemSelectBill">
                            </div>
                            <div class="total-money-bill clearfix">
                                <div class="grid-form clearfix">
                                    <span class="pull-left"><strong>Tổng tiền hàng</strong></span>
                                    <span class="pull-right"><span rel="totalprice"></span></span>
                                </div>
                                <div class="grid-form clearfix">
                                    <span class="pull-left"><strong>Hình thức thanh toán</strong></span>
                                    <span class="pull-right">Giao hàng tại nhà (CoD)</span>
                                </div>
                                <div class="grid-form clearfix">
                                    <strong>Tổng phí vận chuyển</strong>
                                </div>
                                <div class="grid-form clearfix">
                                    <div class="pull-left form-inline">
                                        <div class="checkbox">
                                            <label><input type="radio" name="shipmentService" value="SLOW" checked="checked"> Chuyển phát tiết kiệm</label>
                                        </div>
                                    </div>
                                    <span class="pull-right" rel="shipmentPrice"></span>
                                </div>
                                <div class="grid-form clearfix">
                                    <div class="pull-left form-inline">
                                        <div class="checkbox">
                                            <label><input type="radio" name="shipmentService" value="FAST"> Chuyển phát nhanh</label>
                                        </div>
                                    </div>
                                    <span class="pull-right" rel="shipmentPriceF"> - </span>
                                </div>
                                 <div class="grid-form clearfix cdtDiscountShipment">
                                    <span class="pull-left"><strong>ChợĐiệnTử hỗ trợ phí vận chuyển:</strong></span>
                                    <span class="pull-right"><span rel="cdtDiscountShipment">-</span></span>
                                </div>
                                <div class="grid-form clearfix">
                                    <span class="pull-left"><strong>Khuyễn mãi Coupon: </strong></span>
                                    <span class="pull-right couponShow">-</span>
                                </div>
                                <div class="grid-form clearfix">                                            
                                    <div class="row form-reset-col">
                                        <div class="col-xs-5 form-horizontal">
                                            <p class="form-control-static"><b>Nhập mã coupon</b></p>
                                        </div>
                                        <div class="col-xs-5">
                                                <input type="text" name="couponId" class="form-control" value="">
                                        </div>
                                        <div class="col-xs-2">
                                            <button type="button" class="btn btn-default" onclick="invoice.addCoupon();">Tính</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="grid-form clearfix">
                                    <span class="pull-left"><strong>TỔNG PHẢI THANH TOÁN</strong></span>
                                    <span class="pull-right clr-red fs-20"><span rel="finalPrice"></span></span>
                                </div>                                                                               

                            </div>
                        </div>                                      
                    </div>
                    <button class="btn btn-lg btn-danger" onclick="invoice.create(${orderIdInvoice});">Tạo hoá đơn</button> <a href="#" class="btn-remove"><span class="glyphicon glyphicon glyphicon-remove"></span> Huỷ</a>
                </div>
            </div>                            


        </div>
    </div>
</div>