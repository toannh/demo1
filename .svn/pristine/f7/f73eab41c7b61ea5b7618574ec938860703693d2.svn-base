<div class="mca-bill-item" invoice-rel="<%=this.data%>" id="<%=this.data%>">
    <div class="row message_invoice"></div>
    <div class="row">
        <div class="col-md-5 r-padding-right  mca-left">
            <div class="mca-title"><b>Thông tin người mua</b><button type="button" class="btn btn-danger btn-sm" onclick="invoice.removeOrderInvoice('<%= this.data%>')">Xoá hoá đơn</button></div>
            <div class="form form-horizontal mca-form">
                <div class="form-group">
                    <div class="col-sm-6 padding-right-5">
                        <input name="buyerName" for="buyer" type="text" class="form-control" placeholder="Họ và tên">
                    </div><!-- col -->

                    <div class="col-sm-6 padding-left-5">
                        <input name="buyerPhone" for="buyer" type="text" class="form-control" placeholder="Điện thoại">
                    </div><!-- col -->
                </div><!-- form-group -->
                <div class="form-group">
                    <div class="col-sm-6 padding-right-5">
                        <input name="buyerEmail" for="buyer" type="text" class="form-control" placeholder="Email">
                    </div><!-- col -->
                    <div class="col-sm-6 padding-left-5">
                        <input name="buyerAddress" for="buyer" type="text" class="form-control" placeholder="Địa chỉ">
                    </div><!-- col -->
                </div><!-- form-group -->
                <div class="form-group">
                    <div class="col-sm-6 padding-right-5">
                        <select class="form-control" name="buyerCityId">
                            <option>Chọn Tỉnh/ Thành</option>
                        </select>
                    </div><!-- col -->
                    <div class="col-sm-6 padding-left-5">
                        <select class="form-control" name="buyerDistrictId">
                            <option>Chọn Quận/ Huyện</option>
                        </select>
                    </div><!-- col -->
                </div><!-- form-group -->
            </div><!-- mca-form -->
            <div class="mca-title">
                <b>Thông tin người nhận</b>
                <div class="pull-right"><label><input name="syncInvoiceSeries" checked="checked" type="checkbox" onclick="invoice.syncProfileSeries('<%=this.data%>');">Giống thông tin người mua</label></div>
            </div>
            <div class="form form-horizontal mca-form cityInvoice" style="display:none;">
                <div class="form-group">
                    <div class="col-sm-6 padding-right-5">
                        <input name="receiverName" for="buyerName" type="text" class="form-control" placeholder="Họ và tên">
                    </div><!-- col -->
                    <div class="col-sm-6 padding-left-5">
                        <input name="receiverPhone" for="buyerPhone" type="text" class="form-control" placeholder="Điện thoại">
                    </div><!-- col -->
                </div><!-- form-group -->
                <div class="form-group">
                    <div class="col-sm-6 padding-right-5">
                        <input name="receiverEmail" for="buyerEmail" type="text" class="form-control" placeholder="Email">
                    </div><!-- col -->
                    <div class="col-sm-6 padding-left-5">
                        <input name="receiverAddress" for="buyerAddress" type="text" class="form-control" placeholder="Địa chỉ">
                    </div><!-- col -->
                </div><!-- form-group -->
                <div class="form-group">
                    <div class="col-sm-6 padding-right-5">
                        <select class="form-control" name="receiverCityId">
                            <option>Chọn Tỉnh/ Thành</option>
                        </select>
                    </div><!-- col -->
                    <div class="col-sm-6 padding-left-5">
                        <select class="form-control" name="receiverDistrictId">
                            <option>Chọn Quận/ Huyện</option>
                        </select>
                    </div><!-- col -->
                </div><!-- form-group -->
            </div><!-- mca-form -->
        </div><!-- col -->
        <div class="col-md-7 r-padding-left">
            <div class="mca-search">
                <div class="input-group">
                    <input type="text" value="" onkeypress="invoice.searchItemCustomer(15, '<%=this.data%>');" class="form-control" name="itemKeyword" placeholder="Nhập ID hoặc tên sản phẩm">
                    <span style="cursor:pointer;" class="input-group-addon" onclick="invoice.searchItemCustomer(15, '<%=this.data%>')"><span class="glyphicon glyphicon-search"></span></span>
                    <div class="compare-autosearch" style="display:block;overflow: auto; max-height: 200px;">
                    </div>
                </div>
            </div><!-- mca-search -->
            <div class="mca-list list-product-in-bill" id="itemSearch<%=this.data%>">

            </div><!-- list-product-in-bill -->
            <div class="total-money-bill mca-bill">
                <div class="grid-form clearfix">
                    <span class="pull-left">Tổng tiền hàng</span>
                    <span class="pull-right" rel="totalPrice"></span>
                </div>
                <div class="grid-form clearfix">
                    <span class="pull-left">Hình thức thanh toán</span>
                    <span class="pull-right">Giao hàng tại nhà (CoD)</span>
                </div>
                <div class="grid-form clearfix">Tổng phí vận chuyển</div>
                <div class="grid-form clearfix">
                    <div class="pull-left form-inline">
                        <div class="checkbox">
                            <label><input name="shipmentService<%=this.data%>" type="radio" value="FAST"> Giao hàng nhanh</label>
                        </div>
                        <div class="checkbox">
                            <label><input name="shipmentService<%=this.data%>" type="radio" value="SLOW" checked="checked"> Giao hàng tiết kiệm</label>
                        </div>
                    </div>
                    <span class="pull-right" rel="shipmentPrice">-</span>
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
                    <span class="pull-left"><strong>Nhập mã coupon </strong></span>
                    <div class="pull-right">
                        <div class="col-xs-9">
                            <input type="text" name="couponId" class="form-control" value="">
                            <input type="hidden" name="couponPrice" class="form-control" value="0">
                        </div>
                        <div class="col-xs-2">
                            <button type="button" class="btn btn-default" onclick="invoice.addCouponSeries('<%=this.data%>');">Tính</button>
                        </div>
                    </div>
                </div>
                <div class="grid-form clearfix">
                    <span class="pull-left"><strong>TỔNG PHẢI THANH TOÁN</strong></span>
                    <span class="pull-right clr-red" rel="finalPrice"></span>
                </div>                                                                               
            </div><!-- total-money-bill -->
        </div><!-- col -->
    </div><!-- row -->
</div><!-- mca-bill-item -->
