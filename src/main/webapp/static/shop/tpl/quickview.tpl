<!--Popup --->    
<!-- Modal -->
<div class="row">
    <div class="col-lg-5 col-md-5 col-sm-5 col-xs-12 img-quickview">
        <% if( data.listingType != 'AUCTION' && textUtils.percentFormat(data.startPrice,data.sellPrice,data.discount,data.discountPrice,data.discountPercent) != '0'){ %>
        <div class="sticker-price" style="z-index: 9999"><%= textUtils.percentFormat(data.startPrice,data.sellPrice,data.discount,data.discountPrice,data.discountPercent) %> %</div>
        <%}%>
        <div class="image-view-outer">                    
            <div class="image-view">
                <a href="<%=(typeof data!=='undefined' && typeof data.images!=='undefined')? data.images[0]: '' %>" class ="cloud-zoom" id="zoom1" rel="adjustY:0, adjustX:0,zoomWidth:370">
                    <img src="<%=(typeof data!=='undefined' && typeof data.images!=='undefined')? data.images[0]: ''%>" />
                </a>
            </div>
        </div>   
        <%
        if(typeof data!=='undefined' && typeof data.images!=='undefined' && data.images.length>0){
        %>
        <div class="pup-thumb-small">                    
            <div id="carousel-thumb" class="carousel slide" data-ride="carousel">
                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <%
                    if(data.images.length <= 4){
                    %>
                    <div class="item active">
                        <%
                        for(i = 0; i < data.images.length; i++){
                        %>
                        <a href="<%=data.images[i]%>" class="cloud-zoom-gallery" rel="useZoom: 'zoom1', smallImage: '<%=data.images[i]%>'">
                            <img class="zoom-tiny-image" src="<%=data.images[i]%>" width="50" height="50" alt = "Thumbnail 1"/>
                        </a>
                        <%
                        }
                        %>
                    </div>
                    <%
                    }
                    %>
                    <%
                    if(data.images.length > 4){
                    %>
                    <div class="item">
                        <%
                        for(i = 0; i < 5; i++){
                        %>
                        <a href="<%=data.images[i]%>" class="cloud-zoom-gallery" rel="useZoom: 'zoom1', smallImage: '<%=data.images[i]%>'">
                            <img class="zoom-tiny-image" src="<%=data.images[i]%>" width="50" height="50" alt = "Thumbnail 1"/>
                        </a>
                        <%
                        }
                        %>
                    </div>
                    <div class="item">
                        <%
                        for(i = 5; i < data.images.length; i++){
                        %>
                        <a href="<%=data.images[i]%>" class="cloud-zoom-gallery" rel="useZoom: 'zoom1', smallImage: '<%=data.images[i]%>'">
                            <img class="zoom-tiny-image" src="<%=data.images[i]%>" width="50" height="50" alt = "Thumbnail 1"/>
                        </a>
                        <%
                        }
                        %>
                    </div>
                    <%
                    }
                    %>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-thumb" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="right carousel-control" href="#carousel-thumb" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
            </div>
        </div>
        <%
        }
        %>
    </div>
    <div class="col-lg-7 col-md-7 col-sm-7 col-xs-12 info-pro-quickview">
        <h1><a href="<%= baseUrl %>/san-pham/<%= data.id %>/<%= textUtils.createAlias(data.name) %>.html"><%=data.name%></a></h1>
        <p>
        <div class="fb-like" data-href="<%= baseUrl %>/san-pham/<%= data.id %>/<%= textUtils.createAlias(data.name) %>.html" data-width="102" data-layout="button_count" data-action="like" data-show-faces="true" data-share="true"></div>
        <g:plusone></g:plusone>
        </p>
        <div class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-3 control-label">Tình trạng:</label>
                <div class="col-sm-9"><%=data.condition=='NEW'?'Hàng mới':'Hàng cũ'%></div>
            </div>
            <%
            if(data.listingType==='BUYNOW'){
            %>
            <div class="form-group">
                <label class="col-sm-3 control-label">Hạn bán:</label>
                <div class="col-sm-9">Bắt đầu bán: <%=textUtils.formatTime(data.startTime,'hour')%> - Hết hạn <%=textUtils.formatTime(data.endTime,'hour')%></div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Giá bán</label>
                <div class="col-sm-9">
                    <span class="product-item-price"><strong><%=textUtils.sellPrice(data.sellPrice,data.discount,data.discountPrice,data.discountPercent)%>  VNĐ</strong></span>
                    <% if(textUtils.discountPrice(data.startPrice,data.sellPrice,data.discount,data.discountPrice,data.discountPercent) != 0) {  %>
                    <span class="product-item-price-old"><%=textUtils.startPrice(data.startPrice,data.sellPrice,data.discount)%> VNĐ</span>
                    <%}%>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Số lượng:</label>
                <div class="col-sm-9"><input type="number" name="#" value="1" max="<%= data.quantity%>" min="1" style="min-width: 50px;"/> / <%= data.quantity%> sản phẩm</div>
            </div>
            <% if(data.gift){
            %>
            <div class="form-group">
                <label class="col-sm-3 control-label">Ưu đãi:</label>
                <div class="col-sm-9">
                    <p> - <%=data.giftDetail%></p>
                </div>
            </div>
            <%}%>
            <!--
            <div class="form-group">
                <label class="col-sm-3 control-label">Size:</label>
                <div class="col-sm-9">
                    <div class="size-block">
                        <button type="button" class="btn">S</button>
                        <button type="button" class="btn active">M</button>
                        <button type="button" class="btn">L</button>
                        <button type="button" class="btn">XL</button>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Màu sắc:</label>
                <div class="col-sm-9">
                    <div class="color-block">
                        <button type="button" class="btn" style="background:#F00">&nbsp;</button>
                        <button type="button" class="btn active" style="background:#00F">&nbsp;</button>
                        <button type="button" class="btn" style="background:#FF0">&nbsp;</button>
                        <button type="button" class="btn" style="background:#C00">&nbsp;</button>
                    </div>
                </div>
            </div>
            -->
            <%
            }else if(data.listingType==='AUCTION'){
            %>

            <div class="form-group">
                <label class="col-sm-3 control-label">Thời gian đấu:</label>
                <div class="col-sm-9">Còn <span class="clr-blink"><%= index.formatTimeBidBuyShop(data.endTime) %> ngày</span> (<%=textUtils.formatTime(data.endTime,'hour')%>)</div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Giá đang đấu:</label>
                <div class="col-sm-9">
                    <% if(data.highestBid > 0 ){ %>
                    <span class="product-item-price"><strong><%=parseFloat(data.highestBid).toMoney(0, ',', '.')%> VNĐ</strong></span>
                    <% }else{ %>
                    <span class="product-item-price"><strong><%=parseFloat(data.startPrice).toMoney(0, ',', '.')%> VNĐ</strong></span>
                    <% } %>
                    <span class="fa fa-gavel"></span> (<%=data.bidCount%> lượt đấu)
                </div>
            </div>
            <%
            if(data.sellPrice > 0){
            %>
            <div class="form-group">
                <label class="col-sm-3 control-label">Giá mua ngay:</label>
                <div class="col-sm-9">
                    <span class="product-item-price"><strong><%=parseFloat(data.sellPrice).toMoney(0, ',', '.')%> VNĐ</strong></span>
                </div>
            </div>
            <%}%>

            <%}%>
            <div class="form-group">
                <label class="col-sm-3 control-label">Phí vận chuyển:</label>
                <div class="col-sm-9">
                    <% if(data.shipmentType == null || data.shipmentType == 'AGREEMENT'){ %> 
                    Không rõ phí
                    <% }%>
                    <% if(data.shipmentType == 'FIXED' && data.shipmentPrice == 0){ %> 
                    Miễn phí
                    <% }%>
                    <% if(data.shipmentType == 'FIXED' && data.shipmentPrice >0 ){ %> 
                    Cố định: <%=data.shipmentPrice%> <sup class="u-price">đ</sup>
                    <% }%>
                    <% if(data.shipmentType == 'BYWEIGHT'){ %> 
                    Linh hoạt theo địa chỉ người mua&nbsp;&nbsp;
                    <% }%>
                </div>
            </div>   
            <div class="form-group">
                <label class="col-sm-3 control-label">Giao hàng:</label>
                <div class="col-sm-9">
                    <div><p>Giao hàng toàn quốc tới tận huyện, xã</p></div>
                    <div><p class="p-checkred tool-tip" data-toggle="tooltip" data-placement="right" title="Phí vận chuyển từ: 12.000 đ"><span class="icon16-checkok"></span>Giao hàng nhanh nội thành từ 2h-8h</p></div>
                    <div><p class="p-checkred tool-tip" data-toggle="tooltip" data-placement="right" title="Phí vận chuyển từ: 12.000 đ"><span class="icon16-checkok"></span>Giao hàng tiết kiệm nội thành từ 8h-16h</p></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">Thanh toán:</label>
                <div class="col-sm-9">
                    <div><p class="p-checkred"><span class="icon16-checkok"></span>Thu tiền tận nơi trên toàn quốc (CoD) - Miễn phí</p></div>
                    <div><p class="p-checkred"><span class="icon16-checkok"></span>Thanh toán online: Trên 30 kênh thanh toán</p></div>
                    <div>
                        <ul class="ul-bank">
                            <li><a title="NgânLượng.vn" target="_blank" href="http://www.nganluong.vn"><span class="icon-nl"></span></a></li>
                            <li><a title="Thẻ thanh toán VisaCard" target="_blank" href="http://www.vietnam-visa.com" class="visacrd"></a></li>
                            <li><a title="Thẻ thanh toán MasterCard" target="_blank" href="http://www.mastercard.com" class="mastercrd"></a></li>
                            <li><a title="Hệ thống thanh toán trực tuyến Paypal" target="_blank" href="https://www.paypal.com" class="paypal"></a></li>
                            <li><a href="#"><span class="icon-bank"></span></a></li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>

    </div>   
    <hr />                                                          
    <div class="form-group">
        <div class="col-sm-offset-1 col-sm-11">
            <!---<a onclick="index.redirect('<%= data.id %> ', '<%= data.name %>');" href="#" class="cart-bnt"><span class="fa fa-shopping-cart"></span> Cho vào giỏ hàng</a>--->
            <button type="button" class="btn btn-warning btn-lg pull-right" onclick="index.redirect('<%= data.id %> ', '<%= data.name %>');" >Xem chi tiết</button>
        </div>
    </div>
</div>
</div>
