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
                    <th width="10%">Mã đơn hàng</th>
                    <th width="60%">Thông tin hàng hóa</th>
                    <th width="30%">Thông tin sản phẩm</th>
                </tr>
            </thead>
            <tbody>
                <% $.each(data, function(){ %>
                <% var weight = 0; %>
                <tr data-rel="order"  data-id="<%= this.id %>" >

                    <%if(!cod){
                    %>
            <input name="paymentMethod" value="<%= this.paymentMethod %>" type="hidden" />
            <input data-ship="price" value="<%= eval(this.finalPrice).toMoney(0, ',', '.') %>" type="hidden" />
            <%}else{
            %>
            <input name="paymentMethod" value="COD" type="hidden" />
            <%}%>
            <td align="center">
                <p>
                    <a href="<%= baseUrl %>/<%= this.id %>/chi-tiet-don-hang.html" target="_blank" >
                        <%= this.id %>
                    </a>
                </p>
                <p data-sc="id" ></p>
                <button type="button" class="btn btn-danger" onclick="lading.remove('<%= this.id %>');">Xoá</button>
            </td>
            <td>
                <div class="form form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Tên hàng hóa:</label>
                        <div class="col-sm-8">
                            <p data-ship="name" class="form-control-static">[Chợ điện tử] vận đơn <%= cod?'cod':'vận chuyển' %> (<%= this.id %>)</p>
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
                        <div class="col-sm-5">
                            <div class="input-group">
                                <input name="weight" data-ship="weight" type="text" class="form-control" value="<%= weight %>">
                                <span class="input-group-addon">g</span>
                            </div>
                        </div>
                    </div>
                    <%if(cod){
                    %>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Số tiền người bán cần thu hộ:</label>
                        <div class="col-sm-5">
                            <div class="input-group">
                                <input data-ship="price" type="text" class="form-control" onkeyup="order.changePrice(this);"  value="<%= eval(this.finalPrice).toMoney(0, ',', '.') %>">
                                <span class="input-group-addon">đ</span>
                            </div>
                        </div>
                    </div>
                    <%}%>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Hình thức vận chuyển:</label>
                        <div class="col-sm-5">
                            <select class="form-control" data-ship="shipmentService" >
                                <!--<option value="RAPID" <%= this.shipmentService=='RAPID'?'checked':'' %>  >Hỏa tốc (dưới 2h)</option>-->
                                <option value="FAST" <%= this.shipmentService=='FAST'?'selected':'' %> >Nhanh (2-3 ngày)</option>
                                <option value="SLOW" <%= this.shipmentService=='SLOW'?'selected':'' %> >Tiết kiệm (5-7 ngày)</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Bảo hiểm cho đơn hàng:</label>
                        <div class="col-sm-5">
                            <select class="form-control" data-ship="protec" >
                                <option value="none" >Không</option>
                                <option value="protec" >Có</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group pcod">
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Phí vận chuyển (tạm tính):</label>
                        <div class="col-sm-8">
                            <p class="form-control-static" data-ship="shipFee" ></p>
                        </div>
                    </div>
                </div><!-- form -->
            </td>
            <td>
                <% $.each(this.items, function(){ %>
                <div class="box-buy-product">
                    <span class="count-buy-product">X <%= this.quantity %></span>
                    <div class="pull-left desc-product">
                        <a class="title-product-choose" target="_blank" href="<%=baseUrl%>/san-pham/<%=this.itemId%>/<%=textUtils.createAlias(this.itemName)%>.html"><%= this.itemName %></a>
                        <p>Mã SP: <%= this.itemId %>,<%= this.shipmentType %>,<%= this.shipmentPrice %></p>
                        <p>Giá SP: <strong class="clr-red"><%= eval(this.itemPrice).toMoney(0, ',', '.')%> đ</strong></p>
                        <p>
                            <% if(this.shipmentType == null || this.shipmentType == 'AGREEMENT'){ %> 
                            Không rõ phí vận chuyển
                            <% }%>
                            <% if(this.shipmentType == 'FIXED' && this.shipmentPrice == 0){ %> 
                            Miễn phí vận chuyển
                            <% }%>
                            <% if(this.shipmentType == 'FIXED' && this.shipmentPrice >0 ){ %> 
                            Cố định: <%=this.shipmentPrice%> <sup class="u-price">đ</sup>
                            <% }%>
                            <% if(this.shipmentType == 'BYWEIGHT'){ %> 
                            Linh hoạt theo địa chỉ người mua&nbsp;&nbsp;
                            <% }%>
                        </p>
                    </div>
                </div>
                <%});%>
                <div class="clearfix"></div>
                <div class="total-money-bill clearfix">
                    <div class="grid-form clearfix">
                        <span class="pull-left">Tổng tiền hàng</span>
                        <span class="pull-right"><%= eval(this.totalPrice).toMoney(0, ',', '.') %> đ</span>
                    </div>
                    <div class="grid-form clearfix">
                        <span class="pull-left">Tổng phí vận chuyển</span>
                        <span class="pull-right"><%= eval(this.shipmentPrice - this.cdtDiscountShipment).toMoney(0, ',', '.') %> đ</span>
                    </div>
                    <div class="grid-form clearfix">
                        <span class="pull-left">Khuyến mại hóa đơn</span>
                        <span class="pull-right"><%= eval(this.couponPrice).toMoney(0, ',', '.') %> đ</span>
                    </div>
                    <br>
                    <div class="grid-form clearfix">
                        <span class="pull-left">Thanh toán</span>
                        <span class="pull-right"><strong class="clr-red fs-16"><%= eval(this.finalPrice).toMoney(0, ',', '.') %> đ</strong></span>
                    </div>
                    <div class="grid-form clearfix">
                        <span class="pull-left">HT vận chuyển</span>
                        <span class="pull-right">
                            Giao hàng <%=(this.shipmentService=='FAST'?'nhanh':'tiết kiệm')%>
                        </span>
                    </div> 
                </div>
            </td>
            </tr>
            <%  }); %>
            </tbody>
        </table>
    </div><!-- table-responsive -->
</div>