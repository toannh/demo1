<div class="box box-violet mobile-hidden">
    <div class="box-title">
        <label class="lb-name"><%=this.featuredCategororyName%></label>
        <input name="idCategory" type="hidden" value="<%=this.categorySubId%>"/>
        <% if(this.categoryBannerHomes!=null){
        %>
        <% for(var i = 0; i < this.categoryBannerHomes.length; i++){ %>
        <% if(this.categoryBannerHomes[i].position == 1){ 
        var banner1 = true;
        var banner_image1 = this.categoryBannerHomes[i].image;
        var banner_url1 = this.categoryBannerHomes[i].url;
        }
        %> 
        <input name="bannerIdT1" type="hidden" value="<%=this.categoryBannerHomes[i].id%>"/>
        <% } %>

        <%}%>
        <% if(this.categoryItemHomes!=null){
        %>
        <% for(var i = 0; i < this.categoryItemHomes.length; i++){ %>
        <% if(this.categoryItemHomes[i].position == 1){ 
        var item1 = true;
        var image1 = this.categoryItemHomes[i].image;
        var title1 = this.categoryItemHomes[i].title; 
        %>
        <input name="itemIdT1" type="hidden" value="<%=this.categoryItemHomes[i].itemId%>"/>
        <% } %>
        <% if(this.categoryItemHomes[i].position == 2){ 
        var item2 = true;
        var image2 = this.categoryItemHomes[i].image;
        var title2 = this.categoryItemHomes[i].title; 
        %>
        <input name="itemIdT2" type="hidden" value="<%=this.categoryItemHomes[i].itemId%>"/>
        <% } %>
        <% if(this.categoryItemHomes[i].position == 3){ 
        var item3 = true;
        var image3 = this.categoryItemHomes[i].image;
        var title3 = this.categoryItemHomes[i].title; 
        %>
        <input name="itemIdT3" type="hidden" value="<%=this.categoryItemHomes[i].itemId%>"/>
        <% } %>
        <% if(this.categoryItemHomes[i].position == 4){ 
        var item4 = true;
        var image4 = this.categoryItemHomes[i].image;
        var title4 = this.categoryItemHomes[i].title; 
        %>
        <input name="itemIdT4" type="hidden" value="<%=this.categoryItemHomes[i].itemId%>"/>
        <% } %>
        <% if(this.categoryItemHomes[i].position == 5){ 
        var item5 = true;
        var image5 = this.categoryItemHomes[i].image;
        var title5 = this.categoryItemHomes[i].title; 
        %>
        <input name="itemIdT5" type="hidden" value="<%=this.categoryItemHomes[i].itemId%>"/>
        <% } %>

        <% } %>       

        <% } %>
        <div class="pull-right">

        </div>
    </div><!-- box-title -->
    <div class="box-content">
        <div class="box-product bp-template4">

            <div class="bp-brand4 home-item">
                <div class="hoi-view">
                     <p class="hoi-btn"><a href="javascript:void();" onclick="featuredcategory.addLogo('<%=this.id%>', 4, 4)">Thay Logo</a> |
                     <a href="javascript:void();" onclick="featuredcategory.addBanner(1, '<%=this.id%>', 4)">Ảnh nền</a></p>
                </div>
                <div class="bb-inner">
                    <% if(this.categoryManufacturerHomes!=null){
                    for(var i = 0; i < this.categoryManufacturerHomes.length; i++){ %>
                    <div class="bb-item"><a href="javascript:void();"><img src="<%=this.categoryManufacturerHomes[i].image%>" alt="logo"></a></div>
                    <%}} else { %>
                    <div class="bb-item"><a href="#"><img src="<%=staticUrl%>/market/images/data/logo1.png" alt="logo"></a></div>
                    <div class="bb-item"><a href="#"><img src="<%=staticUrl%>/market/images/data/logo2.png" alt="logo"></a></div>
                    <div class="bb-item"><a href="#"><img src="<%=staticUrl%>/market/images/data/logo3.png" alt="logo"></a></div>
                    <div class="bb-item"><a href="#"><img src="<%=staticUrl%>/market/images/data/logo4.png" alt="logo"></a></div>
                    <%} %>
                </div>
                <% if(banner1==true){ %>
                <a href="#"><img src="<%=banner_image1%>" alt="banner"></a>
                <%}else{ %>
                <a href="#"><img src="<%=staticUrl%>/market/images/data/bp-banner4.jpg" alt="banner"></a>
                <%} %>
            </div>

            <div class="home-item squarehome-item bp1">
                <div class="hoi-inner">
                    <a class="hoi-thumblink" href="#">
                        <a class="hoi-thumblink" href="javascript:void();">
                            <% if(item1==true){ %>
                            <img src="<%=image1%>" alt="product">
                            <%}else{ %>
                            <img src="<%=staticUrl%>/market/images/data/image7.jpg" alt="product">
                            <%}%>
                        </a>
                    </a>
                    <div class="hoi-title">
                        <div class="hoi-row">
                            <% if(item1==true){ %>
                            <a href="javascript:void();"> <%=title1%></a>
                            <%}else{ %>
                            <a href="javascript:void();">Giầy lười nam tính của Hugo Boss</a>
                            <%}%>

                        </div>
                        <div class="hoi-row"><span class="hoi-price" style="display: none">1.050.000 đ</span></div>
                    </div>
                    <div class="hoi-view">
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addItem(1, '<%=this.id%>', 4)">Thay SP</a>
                    </div>

                </div>
            </div><!-- home-item -->
            <div class="home-item mediumhome-item bp2">
                <div class="hoi-inner">
                    <a class="hoi-thumblink" href="#">
                        <% if(item2==true){ %>
                        <img src="<%=image2%>" alt="product">
                        <%}else{ %>
                        <img src="<%=staticUrl%>/market/images/data/image6.jpg" alt="product">
                        <%}%>

                    </a>
                    <div class="hoi-title">
                        <div class="hoi-row">
                            <% if(item2==true){ %>
                            <a href="javascript:void();"> <%=title2%></a>
                            <%}else{ %>
                            <a href="javascript:void();">Giầy lười nam tính của Hugo Boss</a>
                            <%}%>
                        </div>
                        <div class="hoi-row"><span class="hoi-price" style="display: none">1.050.000 đ</span></div>
                    </div>
                    <div class="hoi-view">
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addItem(2, '<%=this.id%>', 4)">Thay SP</a>
                    </div>
                </div>
            </div><!-- home-item -->
            <div class="home-item mediumhome-item bp3">
                <div class="hoi-inner">
                    <a class="hoi-thumblink" href="#">
                        <% if(item3==true){ %>
                        <img src="<%=image3%>" alt="product">
                        <%}else{ %>
                        <img src="<%=staticUrl%>/market/images/data/image5.jpg" alt="product">
                        <%}%>
                    </a>
                    <div class="hoi-title">
                        <div class="hoi-row">
                            <% if(item3==true){ %>
                            <a href="javascript:void();"> <%=title3%></a>
                            <%}else{ %>
                            <a href="javascript:void();">Giầy lười nam tính của Hugo Boss</a>
                            <%}%>
                        </div>
                        <div class="hoi-row"><span class="hoi-price" style="display: none">1.050.000 đ</span></div>
                    </div>
                    <div class="hoi-view">
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addItem(3, '<%=this.id%>', 4)">Thay SP</a>
                    </div>
                </div>
            </div><!-- home-item -->
            <div class="home-item mediumhome-item bp4">
                <div class="hoi-inner">
                    <a class="hoi-thumblink" href="#">
                        <% if(item4==true){ %>
                        <img src="<%=image4%>" alt="product">
                        <%}else{ %>
                        <img src="<%=staticUrl%>/market/images/data/image4.jpg" alt="product">
                        <%}%>

                    </a>
                    <div class="hoi-title">
                        <div class="hoi-row">
                            <% if(item4==true){ %>
                            <a href="javascript:void();"> <%=title4%></a>
                            <%}else{ %>
                            <a href="javascript:void();">Giầy lười nam tính của Hugo Boss</a>
                            <%}%>
                        </div>
                        <div class="hoi-row"><span class="hoi-price" style="display: none">1.050.000 đ</span></div>
                    </div>
                    <div class="hoi-view">
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addItem(4, '<%=this.id%>', 4)">Thay SP</a>
                    </div>
                </div>
            </div><!-- home-item -->
            <div class="home-item highthome-item bp5">
                <div class="hoi-inner">
                    <a class="hoi-thumblink" href="#">
                        <% if(item5==true){ %>
                        <img src="<%=image5%>" alt="product">
                        <%}else{ %>
                        <img src="<%=staticUrl%>/market/images/data/image9.jpg" alt="product">
                        <%}%>
                    </a>
                    <div class="hoi-title">
                        <div class="hoi-row">
                            <% if(item5==true){ %>
                            <a href="javascript:void();"> <%=title5%></a>
                            <%}else{ %>
                            <a href="javascript:void();">Giầy lười nam tính của Hugo Boss</a>
                            <%}%>
                        </div>
                        <div class="hoi-row"><span class="hoi-price" style="display: none">1.050.000 đ</span></div>
                    </div>
                    <div class="hoi-view">
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addItem(5, '<%=this.id%>', 4)">Thay SP</a>
                    </div>
                </div>
            </div><!-- home-item -->
        </div><!-- box-product -->
    </div><!-- box-content -->
</div>