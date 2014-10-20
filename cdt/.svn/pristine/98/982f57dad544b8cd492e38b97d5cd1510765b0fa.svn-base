
<div class="pd-detail product-detail">
    <div class="pd-title">
        <%if(typeof data!=='undefined' && data.manufacturerName != null ){ %>
        <div class="sf-row">
            <a class="pd-brand" href="javascript:;" onclick="urlUtils.manufacturerUrl('<%=data.manufacturerId%>');"><%=data.manufacturerName%></a>
        </div>
        <%}%>
        <div class="sf-row"><h1><%=data.name%></h1></div>
        <div class="sf-row">
            Mã tin bán: <span class="text-danger"><%=data.id%></span> | Lượt xem: <span class="text-danger"><%=data.viewCount%></span> | Cập nhật: <span class="text-danger"><%=textUtils.formatTime(data.updateTime,'hour')%></span>
        </div>
    </div><!-- pd-title -->
    <div class="pd-content">
        <div class="pd-left">
            <div class="image-view">
                <% if(data.listingType == 'BUYNOW' && textUtils.percentFormat(data.startPrice,data.sellPrice,data.discount,data.discountPrice,data.discountPercent)>0){ 
                %>                
                <div class="pd-ripbon"><span>Sale&nbsp;<b><%=textUtils.percentFormat(data.startPrice,data.sellPrice,data.discount,data.discountPrice,data.discountPercent)%>%</b></span></div>
                <%}%>
                <a href="<%=data.images[0]%>" class="cloud-zoom" id="zoom1" rel="adjustX: 10, adjustY:-4">
                    <img src="<%=data.images[0]%>" alt="Không thấy ảnh" />
                </a>
            </div>
            <div class="image-desc"><span class="icon16-zoom"></span>Di chuột vào ảnh để xem hình to hơn</div>
            <% if(data.images.length > 1){ %>
            <div class="pd-slider">
                <ul class="imgdetail-slider jcarousel jcarousel-skin-tango">

                    <%
                    var j = 1;
                    for(i = 0; i < data.images.length; i++){
                    %>
                    <li <%='class="jcarousel-item jcarousel-item-horizontal jcarousel-item-'+j+' jcarousel-item-'+j+'-horizontal"'%> jcarouselindex="<%=j%>" style="float: left; list-style: none;">
                        <div class="is-item active">
                            <a href="<%=data.images[i]%>" class="cloud-zoom-gallery" rel="useZoom: 'zoom1', smallImage: '<%=data.images[i]%>'">
                                <img class="zoom-tiny-image" src="<%=data.images[i]%>" alt="Thumbnail <%=j%>">
                            </a>
                        </div>
                    </li>
                    <%
                    j++;
                    }
                    %>

                </ul>
            </div><!--pd-slider-->
            <% } %>
        </div><!-- pd-left -->
        <div class="pd-center">
            <% if(data.listingType == 'BUYNOW'){ 
            %> 
            <div class="row">
                <span class="big-price"><%=textUtils.sellPrice(data.sellPrice,data.discount,data.discountPrice,data.discountPercent)%> <sup class="u-price">đ</sup></span>
            </div>
            <div class="row">
                <% if(textUtils.discountPrice(data.startPrice,data.sellPrice,data.discount,data.discountPrice,data.discountPercent) != 0) {  %>
                <p class="f11"><span class="old-price"><b><%=textUtils.startPrice(data.startPrice,data.sellPrice,data.discount)%> <sup class="u-price">đ</sup></b></span>&nbsp;|&nbsp;Tiết kiệm: <b><%=textUtils.discountPrice(data.startPrice,data.sellPrice,data.discount,data.discountPrice,data.discountPercent)%> <sup class="u-price">đ</sup></b></p>
                <%}%>
            </div>
            <%}%>


            <div class="pd-line"></div>
            <div class="row">
                <label>Phí vận chuyển:</label>
                <div class="text-outer">
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
            <% if(data.gift){ %> 
                <div class="row">
                    <label>Ưu đãi:</label>
                    <div class="text-outer">
                        <%=data.giftDetail.replace(/\n/g, '</br>')%>
                    </div>
                </div>
            <% }%>
            <div class="row">
                <label>Giao hàng:</label>
                <div class="text-outer">Giao hàng toàn quốc tới tận huyện, xã&nbsp;&nbsp;</div>
            </div>
            <div class="row">
                <div class="text-outer">
                    <div><p class="p-checkred cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Phí vận chuyển từ: 12.000 đ"><span class="icon16-checkok"></span>Giao hàng nhanh nội thành từ 2h-8h</p></div>
                    <div><p class="p-checkred cdt-tooltip" data-toggle="tooltip" data-placement="right" title="" data-original-title="Phí vận chuyển từ: 12.000 đ"><span class="icon16-checkok"></span>Giao hàng tiết kiệm nội thành từ 8h-16h</p></div>
                </div>
            </div>
            <div class="row">
                <label>Thanh toán:</label>
                <div class="text-outer">
                    <div><p class="p-checkred"><span class="icon16-checkok"></span>Thu tiền tận nơi trên toàn quốc (CoD) - Miễn phí</p></div>
                    <div><p class="p-checkred"><span class="icon16-checkok"></span>Thanh toán online: Trên 30 kênh thanh toán</p></div>
                </div>
            </div>
            <div class="row">
                <div class="text-outer">
                    <ul class="ul-bank">
                        <li><a title="NgânLượng.vn" target="_blank" href="http://www.nganluong.vn"><span class="icon-nl"></span></a></li>
                        <li><a title="Thẻ thanh toán VisaCard" target="_blank" href="http://www.vietnam-visa.com" class="visacrd"></a></li>
                        <li><a title="Thẻ thanh toán MasterCard" target="_blank" href="http://www.mastercard.com" class="mastercrd"></a></li>
                        <li><a title="Hệ thống thanh toán trực tuyến Paypal" target="_blank" href="https://www.paypal.com" class="paypal"></a></li>
                        <li><a href="#"><span class="icon-bank"></span></a></li>
                    </ul>

                </div>
            </div>
            <div class="row">
                <span class="icon16-plus"></span>
                <span class="text-info"><b><a href="javascript:;" onclick="market.sendPageSearch('<%=data.categoryId%>', '<%=data.categoryName%>', <%= data.sellPrice%> );" target="_blank"> <%=data.countItemSamePrice%></a></b></span> sản phẩm khác cùng loại có giá từ <span class="text-danger"><b> <%=parseFloat(data.sellPrice).toMoney(0, ',', '.')%><sup class="u-price">đ</sup></b></span>
            </div>
            <div class="pd-line"></div>
            <div class="row text-center">
                <a class="btn btn-primary btn-lg" href="<%= baseUrl %>/san-pham/<%=data.id %>/<%= textUtils.createAlias(data.name) %>.html">Xem chi tiết sản phẩm</a>
            </div>
        </div><!-- pd-center -->
        <div class="clearfix"></div>
    </div><!-- pd-content -->
</div><!-- pd-detail -->	