
<div class="form form-horizontal form-checkallcod">
    <div class="form-group" >
        <label class="col-md-12"><b>Thông tin Người bán:</b></label>
    </div>
    <div class="form-group" data-rel="stock" ></div>
    <div class="form-group">
        <label class="col-md-2 control-label">Người liên hệ:</label>
        <div class="col-md-4"><input type="text" class="form-control" name="name" ></div>
        <label class="col-md-1 control-label">Điện thoại:</label>
        <div class="col-md-2"><input type="text" class="form-control" name="phone" ></div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">Địa chỉ kho hàng:</label>
        <div class="col-md-4"><input type="text" class="form-control" name="address" ></div>
        <label class="col-md-1 control-label">Tỉnh/TP:</label>
        <div class="col-md-2">
            <select class="form-control" name="cityId" data-rel="cityId" >
                <option value="0" >Chọn tỉnh/TP</option>
            </select>
        </div>
        <label class="col-md-1 control-label">Quận/Huyện:</label>
        <div class="col-md-2">
            <select class="form-control" name="districtId" data-rel="districtId" >
                <option value="0" >Chọn quận/huyện</option>
            </select>
        </div>
    </div>
</div><!-- end form -->
<div class="checkallcod-list">
    <div class="sf-row">
        <b>Thông tin vận đơn</b>
    </div>
    <div class="table-responsive">
        <table class="table">
            <thead>
                <tr>
                    <th width="15%">Mã đơn hàng</th>
                    <th width="70%">Thông tin hàng hóa</th>
                    <th width="15%">Giá trị đơn hàng</th>
                </tr>
            </thead>
            <tbody>
                <% $.each(data, function(){ %>
                <% var weight = 0; %>
                <tr data-rel="order"  data-id="<%= this.id %>" >
                    <input name="paymentMethod" value="<%= this.paymentMethod %>" type="hidden" />
                    <input data-ship="price" value="<%= eval(this.finalPrice).toMoney(0, ',', '.') %>" type="hidden" />
                    <td>
                        <a href="<%= baseUrl %>/<%= this.id %>/chi-tiet-don-hang.html" target="_blank" >
                            <%= this.id %>
                        </a>
                        <p data-sc="id" ></p>
                        <button type="button" class="btn btn-danger" onclick="lading.remove('<%= this.id %>');">Xoá</button>
                    </td>
                    <td>
                        <div class="form form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Tên hàng hóa:</label>
                                <div class="col-sm-8">
                                    <p data-ship="name" class="form-control-static">[Chợ điện tử] vận đơn vận chuyển(<%= this.id %>)</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Mô tả chi tiết:</label>
                                <div class="col-sm-8">
                                    <p data-ship="desc" class="form-control-static">
                                        Vận đơn gồm :<% $.each(this.items, function(){ %><% weight += this.weight; %><%= this.quantity %> x <%= this.itemName %> (<%= this.itemId %>),<%  }); %>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Tổng khối lượng:</label>
                                <div class="col-sm-4">
                                    <div class="input-group">
                                        <input name="weight" data-ship="weight" type="text" class="form-control" value="<%= weight %>">
                                        <span class="input-group-addon">g</span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Hình thức vận chuyển:</label>
                                <div class="col-sm-4">
                                    <select class="form-control" data-ship="shipmentService" >
                                        <option value="FAST" <%= this.shipmentService=='FAST'?'checked':'' %> >Nhanh (2-3 ngày)</option>
                                        <option value="SLOW" <%= this.shipmentService=='SLOW'?'checked':'' %> >Tiết kiệm (5-7 ngày)</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Bảo hiểm cho đơn hàng:</label>
                                <div class="col-sm-4">
                                    <select class="form-control" data-ship="protec" >
                                        <option value="none" >Không</option>
                                        <option value="protec" >Có</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Phí vận chuyển (tạm tính):</label>
                                <div class="col-sm-8">
                                    <p class="form-control-static" data-ship="shipFee" ></p>
                                </div>
                            </div>
                        </div><!-- form -->
                    </td>
                    <td><b class="text-danger"><%= eval(this.finalPrice).toMoney(0, ',', '.') %><sup>đ</sup></b></td>
                </tr>
                <%  }); %>
            </tbody>
        </table>
    </div><!-- table-responsive -->
    <div class="sf-row text-center">
        <label class="checkallcod-lb-ckh"><input type="checkbox" name="yes"  /> Tôi đồng ý tuân thủ <a target="_blank" href="#">chính sách và quy định của dịch vụ Giao hàng</a></label>
    </div>
</div>