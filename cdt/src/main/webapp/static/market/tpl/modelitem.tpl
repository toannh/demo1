<% 
if(itemPage.dataCount <= 0 && (itemSearch.orderBy <= 0 && itemSearch.listingType==null && itemSearch.condition==null && itemSearch.cityIds.length <= 0)) {
%>
<div class="compare-price-no">
    <p>Không có sản phẩm nào để so sánh giá</p>
    <p>Hãy đăng bán sản phẩm này để được hiện thị tại đây</p>
    <a class="btn btn-danger btn-lg" href="<%= baseUrl %>/user/dang-ban.html">Đăng bán ngay</a>
</div><!--compare-price-no-->
<%} else{
%>
<div class="box-control box-control-tiny">
    <label>Giá: </label>
    <ul>
        <li onclick="model.loadModelItem(0, 0, '<%=itemSearch.listingType%>', '<%=itemSearch.condition%>', '<%=itemSearch.cityIds[0]%>')" <%if(itemSearch.orderBy==0){%> class="active" <%}%>><a href="javascript:;">Mặc định</a></li>
        <li onclick="model.loadModelItem(0, 1, '<%=itemSearch.listingType%>', '<%=itemSearch.condition%>', '<%=itemSearch.cityIds[0]%>')" <%if(itemSearch.orderBy==1){%> class="active" <%}%>><a href="javascript:;">Giảm dần</a></li>
        <li onclick="model.loadModelItem(0, 2, '<%=itemSearch.listingType%>', '<%=itemSearch.condition%>', '<%=itemSearch.cityIds[0]%>')" <%if(itemSearch.orderBy==2){%> class="active" <%}%>><a href="javascript:;">Tăng dần</a></li>
    </ul>
    &nbsp;&nbsp;
    <label>Loại SP: </label>
    <ul>
        <li <%if(itemSearch.listingType==null) {%> class="active" <%}%>><a href="javascript:;" onclick="model.loadModelItem(0, '<%=itemSearch.orderBy%>','', '<%=itemSearch.condition%>', '<%=itemSearch.cityIds[0]%>')">Toàn bộ</a></li>
        <li <%if(itemSearch.listingType=='AUCTION'){%> class="active" <%}%>><a href="javascript:;" onclick="model.loadModelItem(0, '<%=itemSearch.orderBy%>','AUCTION', '<%=itemSearch.condition%>', '<%=itemSearch.cityIds[0]%>')">Đấu giá</a></li>
        <li <%if(itemSearch.listingType=='BUYNOW'){%> class="active" <%}%>><a href="javascript:;" onclick="model.loadModelItem(0, '<%=itemSearch.orderBy%>','BUYNOW', '<%=itemSearch.condition%>', '<%=itemSearch.cityIds[0]%>')">Mua ngay</a></li>
    </ul>
    <div class="pull-right">
        <label>Tình trạng SP: </label>
        <select class="form-control" style="width:85px;" onchange="model.changeCondition(this,'<%=itemSearch.orderBy%>','<%=itemSearch.listingType%>', '<%=itemSearch.cityIds[0]%>');">
            <option>Toàn bộ</option>
            <option value="NEW" <%if(itemSearch.condition=='NEW'){%> selected="true" <%}%>>Hàng mới</option>
            <option value="OLD" <%if(itemSearch.condition=='OLD'){%> selected="true" <%}%>>Hàng cũ</option>
        </select>
        &nbsp;&nbsp;
        <label>Điểm bán: </label>
        <select class="form-control" style="width:90px;" onchange="model.changeCity(this,'<%=itemSearch.orderBy%>','<%=itemSearch.listingType%>', '<%=itemSearch.condition%>');">
            <option value="" >Toàn quốc</option>
            <%for(var i=0; i< cities.length; i++){
            %>
            <option value="<%= cities[i].id %>" <%if(itemSearch.cityIds[0]==cities[i].id){%> selected="true" <%}%>><%= cities[i].name %></option>
            <%}%> 
        </select>
    </div>
</div><!--box-control-->
<div class="compare-price table-responsive">                            
    <table class="table">
        <thead>
            <tr>
                <th width="15%">Giá<span class="icon16-arrow-updown"></span></th>
                <th width="10%">Tình trạng</th>
                <th width="15%">Hình thức vận chuyển</th>
                <th width="25%">Hình thức thanh toán</th>
                <th width="35%">Người bán</th>
            </tr>
        </thead>
        <tbody>
            <%
            for(var i=0; i< itemPage.data.length; i++){
            %>
            <tr>
                <td>
                    <p class="cp-price"><%=parseFloat(itemPage.data[i].sellPrice).toMoney(0, ',', '.')%> <sup class="u-price">đ</sup></p>
                    <p class="cp-fee">
                        <%if(itemPage.data[i].shipmentType == null || itemPage.data[i].shipmentType == 'AGREEMENT'){%>Phí vận chuyển: Tự thỏa thuận<%}%>
                        <%if(itemPage.data[i].shipmentType == 'FIXED' && itemPage.data[i].shipmentPrice == 0){%>Miễn phí vận chuyển<%}%>
                        <%if(itemPage.data[i].shipmentType == 'FIXED' && itemPage.data[i].shipmentPrice > 0){%><b>+<%=parseFloat(itemPage.data[i].shipmentPrice).toMoney(0, ',', '.')%> <sup class="u-price">đ</sup></b> Phí vận chuyển<%}%>
                        <%if(itemPage.data[i].shipmentType == 'BYWEIGHT'){%>Phí vận chuyển: Linh hoạt theo đỉa chỉ người mua<%}%>
                    </p>
                </td>
                <td>
                    <%if(itemPage.data[i].condition=='OLD'){%><p>Cũ</p><%}%>
                    <%if(itemPage.data[i].condition=='NEW'){%><p>Mới</p><%}%>
                    <%if(itemPage.data[i].gift){%><p>Có quà tặng</p><%}%>
                </td>
                <td>
                    <%if(itemPage.data[i].cod){
                    %> 
                    <p class="cp-checksquare"><span class="icon16-checkok"></span>Nhanh</p>
                    <p class="cp-checksquare"><span class="icon16-checkok"></span>Tiết kiệm</p>
                    <%}else{
                    %>  
                    <p>Liên hệ để biết</p>
                    <%}%>
                </td>
                <td>
                    <%if(itemPage.data[i].cod || itemPage.data[i].onlinePayment){
                    %>  
                    <%if(itemPage.data[i].onlinePayment){
                    %>
                    <p class="cp-checksquare"><span class="icon16-checkok"></span>Thanh toán online qua Ngân Lượng</p>
                    <%}%>
                    <%if(itemPage.data[i].cod){
                    %>
                    <p class="cp-checksquare"><span class="icon16-checkok"></span>Thu tiền tận nơi trên toàn quốc (CoD)</p>
                    <%}%>
                    <%}%>
                    <%if(!itemPage.data[i].cod && !itemPage.data[i].onlinePayment){
                    %>
                    <p>Giao dịch trực tiếp</p>
                    <p class="text-danger">ChợĐiệnTử không đảm bảo</p>
                    <%}%>
                </td>
                <td>
                    
                    <% 
                    var hasShop = false;
                    var shop = {};
                    for(var j = 0 ;j < shops.length ;j++){
                        if(shops[j].userId == itemPage.data[i].sellerId){
                            hasShop = true;
                           shop = shops[j];
                        }
                    }
                    if(!hasShop){
                        var seller={};
                        for(var j = 0 ;j < sellers.length ;j++){
                            if(sellers[j].id == itemPage.data[i].sellerId){
                                seller = sellers[j];
                            }
                        }
                    %>
                    
                    <div class="grid">
                        <a class="img" href="#">
                            <% if(typeof seller.avatar!='undefined' && seller.avatar!=null && seller.avatar!=''){
                            %>
                                <img src="<%=seller.avatar%>" alt="avatar" />
                            <%}else{
                            %>
                                <img src="<%=staticUrl%>/market/images/no-avatar.png" alt="avatar" />
                            <%}%>
                        </a>             
                        <div class="g-content">
                            <div class="g-row"><a href="#"><%=seller.username%></a></div>
                            <div class="g-row">
                                <%if(typeof seller.phone!='undefined' && seller.phone!=null && seller.phone!=''){
                                %>
                                <span class="icon16-telgray"></span><span class="cp-tel"><%=seller.phone%></span>
                                <%}%>
                                <div class="custom-tip">
                                    <a href="#">Hỗ trợ</a>
                                    <div class="custom-tip-pop ctp-right">
                                        <div class="ctp-inner">
                                            <div class="ctp-content">
                                                <div class="pd-support">
                                                    <div class="ps-col">
                                                        <%if(typeof seller.phone!='undefined' && seller.phone!=null && seller.phone!=''){
                                                        %>
                                                        <div class="ps-row"><span class="icon16-mobile-green"></span><%=seller.phone%></div>
                                                        <%}%>
                                                        <%if(typeof seller.yahoo!='undefined' && seller.yahoo!=null && seller.yahoo!=''){
                                                        %>
                                                        <div class="ps-row"><a href="ymsgr:sendim?<%=seller.yahoo%>"><img src='http://opi.yahoo.com/online?u=<%=seller.yahoo%>&m=g&t=5'/><%=seller.yahoo%></a></div>
                                                        <%}%>
                                                        <%if(typeof seller.skype!='undefined' && seller.skype!=null && seller.skype!=''){
                                                        %>
                                                        <div class="ps-row"><a href="skype:<%=seller.skype%>?chat"><span class="icon16-skype"></span><%=seller.skype%></a></div>
                                                        <%}%>
                                                        <%if(typeof seller.email!='undefined' && seller.email!=null && seller.email !=''){
                                                        %>
                                                        <div class="ps-row"><span class="icon16-email-red"></span><%=seller.email%></div>
                                                        <%}%>
                                                    </div><!-- ps-col -->
                                                    <div class="clearfix"></div>
                                                </div><!-- pd-support -->
                                            </div><!-- ctp-content -->
                                        </div><!-- ctp-inner -->
                                    </div><!-- custom-tip-pop -->
                                </div><!-- custom-tip -->
                                <div class="pull-right">
                                    <a class="btn btn-default btn-sm" onclick="model.pageSellerNoShop('<%= seller.id %>');" style="cursor: pointer"><span class="icon16-search"></span>Xem</a>
                                </div>
                            </div>
                            <div class="g-row" onclick="model.getMaps('<%=seller.address%>')" style="cursor: pointer" data-toggle="modal" data-target="#ModalNormal"><span class="icon16-placegray"></span>Xem địa chỉ người bán</div>
                        </div>
                    </div>
                   <% }else{ %>
                    <div class="grid">
                        <a class="img" href="<%= baseUrl %>/<%=shop.alias%>/">
                            <% if(typeof shop.logo!='undefined' && shop.logo!=null && shop.logo!=''){
                            %>
                                <img src="<%=shop.logo%>" alt="avatar" />
                            <%}else{
                            %>
                                <img src="<%=staticUrl%>/market/images/no-avatar.png" alt="avatar" />
                            <%}%>
                        </a>             
                        <div class="g-content">
                            <div class="g-row"><a href="<%= baseUrl %>/<%=shop.alias%>/"><%=shop.alias%></a></div>
                            <div class="g-row">
                                <%if(typeof shop.phone!='undefined' && shop.phone!=null && shop.phone!=''){
                                %>
                                <span class="icon16-telgray"></span><span class="cp-tel"><%=shop.phone%></span>
                                <%}%>
                                <%if(typeof shop.shopContacts!='undefined' && shop.shopContacts!=null && shop.shopContacts.length>0){
                                %>
                                <div class="custom-tip">
                                    <a href="javascript:;">Hỗ trợ</a>
                                    <div class="custom-tip-pop ctp-right">
                                        <div class="ctp-inner">
                                            <div class="ctp-content">
                                                <%
                                                var cclass='';
                                                if(shop.shopContacts.length >= 2 && shop.shopContacts.length < 5){
                                                    cclass = 'ps-two-col';
                                                 }else if(shop.shopContacts.length >= 5){
                                                    cclass = 'ps-three-col';
                                                 }%>
                                                <div  <%= 'class="pd-support '+cclass+'"' %>>
                                                    <%for(var k=0;k< shop.shopContacts.length; k++){
                                                    %>
                                                    <div class="ps-col">
                                                        <div class="ps-row"><b><%=shop.shopContacts[k].title%></b></div>
                                                        <%if(typeof shop.shopContacts[k].phone!='undefined' && shop.shopContacts[k].phone!=null && shop.shopContacts[k].phone!=''){
                                                        %>
                                                        <div class="ps-row"><span class="icon16-mobile-green"></span><%=shop.shopContacts[k].phone%></div>
                                                        <%}%>
                                                        <%if(typeof shop.shopContacts[k].yahoo!='undefined' && shop.shopContacts[k].yahoo!=null && shop.shopContacts[k].yahoo!=''){
                                                        %>
                                                        <div class="ps-row"><a href="ymsgr:sendim?<%=shop.shopContacts[k].yahoo%>"><img src='http://opi.yahoo.com/online?u=<%=shop.shopContacts[k].yahoo%>&m=g&t=5'/><%=shop.shopContacts[k].yahoo%></a></div>
                                                        <%}%>
                                                        <%if(typeof shop.shopContacts[k].skype!='undefined' && shop.shopContacts[k].skype!=null && shop.shopContacts[k].skype!=''){
                                                        %>
                                                        <div class="ps-row"><a href="skype:<%=shop.shopContacts[k].skype%>?chat"><span class="icon16-skype"></span><%=shop.shopContacts[k].skype%></a></div>
                                                        <%}%>
                                                        <%if(typeof shop.shopContacts[k].email!='undefined' && shop.shopContacts[k].email!=null && shop.shopContacts[k].email !=''){
                                                        %>
                                                        <div class="ps-row"><span class="icon16-email-red"></span><%=shop.shopContacts[k].email%></div>
                                                        <%}%>
                                                    </div><!-- ps-col -->
                                                    <%if((shop.shopContacts.length>2 && shop.shopContacts.length<5 && i%2==1)||(shop.shopContacts.length>5 && i%3==2)){
                                                    %>
                                                    <div class="clearfix"></div>
                                                    <%}}%>
                                                </div><!-- pd-support -->
                                            </div><!-- ctp-content -->
                                        </div><!-- ctp-inner -->
                                    </div><!-- custom-tip-pop -->
                                </div><!-- custom-tip -->
                                <%}%>
                                <div class="pull-right">
                                    <a class="btn btn-default btn-sm" target="_blank" href="<%= baseUrl %>/<%= shop.alias %>"><span class="icon16-search"></span>Xem</a>
                                </div>
                            </div>
                            <div class="g-row" style="cursor: pointer" onclick="model.getMaps('<%=shop.address%>')" data-toggle="modal" data-target="#ModalNormal"><span class="icon16-placegray"></span>Xem địa chỉ shop</div>
                        </div>
                    </div>
                    <%}%>
                </td>
            </tr>
            <%}%>
        </tbody>
    </table>
</div>
<div class="clearfix"></div>
<%}%>