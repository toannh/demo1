<div class="popup-content-normal clearfix">
    <div class="col-lg-12 col-md-12 col-xs-12">
        <p><strong>Bạn đang tiến hành lập lịch up tự động cho tin bán sau: </strong></p>
        <% var ids = new Array(); %>
        <div class="main-content" style="overflow: auto; max-height: 150px;" >
            <% $.each(data, function(){ %>
            <% ids.push(this.id); %>
            <div class="table-content clearfix" style="margin-top: 5px;">
                <span class="img-product-bill-small">
                    <img src="<%= this.images[0] %>" style="border-radius: 3px" />
                </span>
                <p><a><%= this.name %></a></p>
                <p>ID: <span class="clr-red"><%= this.id %></span><p>
            </div>
            <% }); %>
        </div>
        <hr>
        <div class="clearfix"></div>
        <div class="tabs-content-block">
            <ul class="nav nav-tabs">
                <li class="active"><a rel="uptype" value="cal" href="#plan-upnews" data-toggle="tab">Kế hoạch up tin</a></li>
                <li class=""><a rel="uptype" value="now" href="#plan-up-now" data-toggle="tab">Up ngay</a></li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="plan-upnews">
                    <div class="row">
                        <div class="form-horizontal mgt-15">
                            <span rel='upCal'>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Số lần up: <span class="clr-red">*</span></label>
                                    <div class="col-sm-10">
                                        <input type="number" value="0" name="quantity" class="form-control pull-left"> <p class="form-control-static" rel="maxup" ></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Ngày trong tuần: <span class="clr-red">*</span></label>
                                    <div class="col-sm-10">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="dayall" > Tất cả các ngày trong tuần
                                            </label>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="checkbox-inline">
                                            <label>
                                                <input type="checkbox" for="dayall" value="2" name="2" > Thứ 2
                                            </label>
                                        </div>
                                        <div class="checkbox-inline">
                                            <label>
                                                <input type="checkbox" for="dayall" value="3" name="3" > Thứ 3
                                            </label>
                                        </div>
                                        <div class="checkbox-inline">
                                            <label>
                                                <input type="checkbox" for="dayall" value="4" name="4" > Thứ 4
                                            </label>
                                        </div>
                                        <div class="checkbox-inline">
                                            <label>
                                                <input type="checkbox" for="dayall" value="5" name="5" > Thứ 5
                                            </label>
                                        </div>
                                        <div class="checkbox-inline">
                                            <label>
                                                <input type="checkbox" for="dayall" value="6" name="6" > Thứ 6
                                            </label>
                                        </div>
                                        <div class="checkbox-inline">
                                            <label>
                                                <input type="checkbox" for="dayall" value="7" name="7" > Thứ 7
                                            </label>
                                        </div>
                                        <div class="checkbox-inline">
                                            <label>
                                                <input type="checkbox" for="dayall" value="1" name="1" > Chủ nhật
                                            </label>
                                        </div>          
                                    </div>
                                </div>
                                <div class="form-group tinvip-date">
                                    <label class="col-sm-2 control-label">Thời điểm trong ngày: <span class="clr-red">*</span></label>
                                    <div class="col-sm-10">
                                        <span rel="timeofday" ></span>
                                        <div class="col-row">
                                            <input type="text" id="timepicker" style="width: 0px; border: none" />
                                        </div>
                                        <div class="clearfix"></div>
                                        <p class="mgt-10" rel="quantityUp" >Bạn đang cài: 0 lần/ngày</p>
                                    </div>
                                    <p class="text-danger col-sm-offset-2" error='quantityUp' ></p>
                                </div>                    
                            </span>
                        </div>
                    </div>
                    <span rel='upCal'>
                        <p><strong>Chi phí up tin</strong></p>
                        <div class="list-table-content table-responsive mgt-10">
                            <table class="table table-bordered" align="center" rel='upinfo' >
                                <thead>
                                    <tr class="warning">
                                        <td><strong>Số tin up</strong></td>
                                        <td><strong>Lượt up/tin</strong></td>
                                        <td><strong>Tổng lượt up</strong></td>
                                        <td><strong>Chi phí up tin (xèng)</strong></td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="4" class="text-center text-danger" >Không có lượt uptin nào được cấu hình</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </span>
                    <!--
                   <hr>
                    <div class="button-block-popup clearfix">
                        <span class="pull-right">
                            <button class="btn btn-lg btn-danger">Thanh toán</button>
                        </span>
                    </div>
                    -->
                    <hr style="margin:5px 0;">
                    <div class="form-group text-center mgt-15">
                        Hiện bạn đang có: <strong class="clr-red"><%=parseFloat(xeng).toMoney(0, ',', '.')%> xèng</strong><br>
                        Bạn có thể nạp thêm xèng để mua được nhiều lượt up <a href="<%= baseUrl %>/user/tai-khoan-xeng.html" target="_blank">tại đây</a>
                    </div>
                </div>
                <div class="tab-pane" id="plan-up-now">
                    <div class="row content-popup-user">
                        <div class="col-lg-12 col-md-12 col-xs-12">
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-xs-6">
                                    <div class="button-block-popup clearfix">
                                        <p class="text-left"><strong>Up ngay bằng xèng</strong></p>
                                        <p class="text-left">Chi phí up ngay/tin nhắn: <strong class="clr-red">1.000 đ</strong></p>        
                                        <!-- <p class="text-left"><button class="btn btn-lg btn-danger">Thanh toán</button></p> -->
                                        <p class="text-left mgt-25">
                                            Hiện bạn đang có: <strong class="clr-red"><%=parseFloat(xeng).toMoney(0, ',', '.')%> xèng</strong><br>
                                            Bạn có thể nạp thêm xèng để mua được nhiều lượt up <a href="<%= baseUrl %>/user/tai-khoan-xeng.html" target="_blank">tại đây</a>
                                        </p>                                        
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-xs-6">
                                    <p class="text-left"><strong>Up ngay bằng SMS</strong></p>
                                    <p class="text-left">Nhắn tin cú pháp: <span class="fs-16 clr-red">CDT UP ID_MÃ_SẢN_PHẨM <span class="clr-black">gửi</span> 8155</span></p>
                                    <p class="text-left"><strong class="clr-red">Lưu ý: </strong>Mỗi số điện thoại chỉ nhắn được <strong class="clr-red">10tin/ngày.</strong><br>Mỗi lần cách nhau ít nhất 3 phút</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>