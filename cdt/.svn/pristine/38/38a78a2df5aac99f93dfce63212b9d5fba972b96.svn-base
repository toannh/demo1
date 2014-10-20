<div class="box box-blue mobile-hidden">
    <div class="box-title">
        <label class="lb-name"><%=this.featuredCategororyName%></label>
        <input name="idCategory" type="hidden" value="<%=this.categorySubId%>"/>
        <% if(this.categoryBannerHomes!=null){
        %>

        <% for(var i = 0; i < this.categoryBannerHomes.length; i++){ %>
        <input name="bannerIdT<%=this.categoryBannerHomes[i].position %>" type="hidden" value="<%=this.categoryBannerHomes[i].id%>"/>
        <% if(this.categoryBannerHomes[i].position == 1){ 
        var banner1 = true;

        var banner_image1 = this.categoryBannerHomes[i].image;
        var banner_url1 = this.categoryBannerHomes[i].url;
        }
        if(this.categoryBannerHomes[i].position == 2){ 
        var banner2 = true;
        var banner_image2 = this.categoryBannerHomes[i].image;
        var banner_url2 = this.categoryBannerHomes[i].url;
        }
        if(this.categoryBannerHomes[i].position == 3){ 
        var banner3 = true;
        var banner_image3 = this.categoryBannerHomes[i].image;
        var banner_url3 = this.categoryBannerHomes[i].url;
        }
        if(this.categoryBannerHomes[i].position == 4){ 
        var banner4 = true;
        var banner_image4 = this.categoryBannerHomes[i].image;
        var banner_url4 = this.categoryBannerHomes[i].url;
        }
        %> 

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

        <%}%>
        <div class="pull-right">

        </div>
    </div><!-- box-title -->
    <div class="box-content">
        <div class="box-product bp-template3" style="z-index: 11">
            <div class="bp-banner3 home-item">
                <div class="hoi-view">
                    <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addBanner(4, '<%=this.id%>', 3);">Thay Banner</a>
                </div>
                
                <a href="javascript:void();">
                    <% if(banner4==true){ %>
                    <img src="<%=banner_image4%>" alt="banner">
                    <%}else{ %>
                    <img src="<%=staticUrl%>/market/images/data/bp-banner3.jpg" alt="banner">
                    <%}%>
                </a>
            </div>
            <div class="home-category bp-cat1 home-item" style="z-index: 11">
                    <div class="hoi-view">
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addBanner(1, '<%=this.id%>', 3);">Thay Banner</a>
                    </div>
                    <a href="javascript:void();">
                        <% if(banner1==true){ %>
                        <img src="<%=banner_image1%>" alt="banner">
                        <%}else{ %>
                        <img src="<%=staticUrl%>/market/images/data/home-category1.png" alt="category">
                        <%}%>
                    </a>
            </div><!-- home-category -->
            <div class="home-category bp-cat2 home-item" style="z-index: 11">
                    <div class="hoi-view">
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addBanner(2, '<%=this.id%>', 3);">Thay Banner</a>
                    </div>
                    <a href="javascript:void();">
                        <% if(banner2==true){ %>
                        <img src="<%=banner_image2%>" alt="banner">
                        <%}else{ %>
                        <img src="<%=staticUrl%>/market/images/data/home-category2.png" alt="category">
                        <%}%>
                    </a>
            </div><!-- home-category -->
            <div class="home-category bp-cat3 home-item" style="z-index: 11">
                    <div class="hoi-view">
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addBanner(3, '<%=this.id%>', 3);">Thay Banner</a>
                    </div>
                    <a href="javascript:void();">
                        <% if(banner3==true){ %>
                        <img src="<%=banner_image3%>" alt="banner">
                        <%}else{ %>
                        <img src="<%=staticUrl%>/market/images/data/home-category3.png" alt="category">
                        <%}%>
                    </a>
            </div><!-- home-category -->
            <div class="home-item bp1" style="z-index: 11">
                <div class="hoi-inner">
                    <a class="hoi-thumblink" href="javascript:void();">
                        <% if(item1==true){ %>
                        <img src="<%=image1%>" alt="product">
                        <%}else{ %>
                        <img src="<%=staticUrl%>/market/images/data/image9.jpg" alt="product">
                        <%}%>
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
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addItem(1, '<%=this.id%>', 3);">Thay SP</a>
                    </div>
                </div>
            </div><!-- home-item -->
            <div class="home-item bp2" style="z-index: 11">
                <div class="hoi-inner">
                    <a class="hoi-thumblink" href="javascript:void();">
                        <% if(item2==true){ %>
                        <img src="<%=image2%>" alt="product">
                        <%}else{ %>
                        <img src="<%=staticUrl%>/market/images/data/image10.jpg" alt="product">
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
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addItem(2, '<%=this.id%>', 3);">Thay SP</a>
                    </div>
                </div>
            </div><!-- home-item -->
            <div class="home-item bp3" style="z-index: 11">
                <div class="hoi-inner">
                    <a class="hoi-thumblink" href="javascript:void();">
                        <% if(item3==true){ %>
                        <img src="<%=image3%>" alt="product">
                        <%}else{ %>
                        <img src="<%=staticUrl%>/market/images/data/image7.jpg" alt="product">
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
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addItem(3, '<%=this.id%>', 3);">Thay SP</a>
                    </div>
                </div>
            </div><!-- home-item -->
            <div class="home-item bp4" style="z-index: 11">
                <div class="hoi-inner">
                    <a class="hoi-thumblink" href="javascript:void();">
                        <% if(item4==true){ %>
                        <img src="<%=image4%>" alt="product">
                        <%}else{ %>
                        <img src="<%=staticUrl%>/market/images/data/image8.jpg" alt="product">
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
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addItem(4, '<%=this.id%>', 3);">Thay SP</a>
                    </div>
                </div>
            </div><!-- home-item -->
            <div class="home3-tabouter  home-item">
                <div class="tab-outer">
                    <div class="hoi-view">
                        <a class="hoi-btn" href="javascript:void();" onclick="featuredcategory.addmodelmanufacture('<%=this.id%>', 5, 3);">Thay Logo</a>
                    </div>
                    <div class="tab-title">
                        <% if(this.categoryManufacturerHomes!=null){
                        for(var i = 0; i < this.categoryManufacturerHomes.length; i++){ %>
                        <div class="bb-item" <%if(i==0){%> class="active"<%}%> ><a href="javascript:void();"><img src="<%=this.categoryManufacturerHomes[i].image%>" alt="logo"></a></div>
                        <%}} else { %>
                        <a class="active" href="javascript:void();home3tab1"><img src="<%=staticUrl%>/market/images/data/logo1.png" alt="logo"><span class="home3-bullet"></span></a>
                        <a href="javascript:void();home3tab2" class=""><img src="<%=staticUrl%>/market/images/data/logo2.png" alt="logo"><span class="home3-bullet"></span></a>
                        <a href="javascript:void();home3tab3" class=""><img src="<%=staticUrl%>/market/images/data/logo3.png" alt="logo"><span class="home3-bullet"></span></a>
                        <a href="javascript:void();home3tab4"><img src="<%=staticUrl%>/market/images/data/logo4.png" alt="logo"><span class="home3-bullet"></span></a>
                        <a href="javascript:void();home3tab5"><img src="<%=staticUrl%>/market/images/data/logo5.png" alt="logo"><span class="home3-bullet"></span></a>
                        <%}%>
                    </div>
                    <div class="tab-container">
                        <div id="home3tab1" class="tab-content" style="display: block;">
                            <div class="home3-product">
                                <div class="hp-thumb"><a href="javascript:void();"><img src="<%=staticUrl%>/market/images/data/image8.jpg" alt="model HTC"></a></div>
                                <div class="hp-row">Model: HTC 8X</div>
                                <div class="hp-row">Giá tham khảo: <span class="hp-price">15.900.000 đ</span></div>
                                <div class="hp-row">Hiện có: <a href="javascript:void();">50 cửa hàng đang bán</a></div>
                            </div>
                            <div class="home3-line"></div>
                            <ul class="home3-ul">
                                <li><a href="javascript:void();">HTC One 16GB</a></li>
                                <li><a href="javascript:void();">HTC One Mini</a></li>
                                <li><a href="javascript:void();">HTC One 18</a></li>
                                <li><a href="javascript:void();">HTC Desire 816</a></li>
                            </ul>
                        </div><!-- /tab-content -->
                        <div id="home3tab2" class="tab-content" style="display: none;">
                            Đang cập nhật - tab 2
                        </div><!-- /tab-content -->
                        <div id="home3tab3" class="tab-content" style="display: none;">
                            Đang cập nhật - tab 3
                        </div><!-- /tab-content -->
                        <div id="home3tab4" class="tab-content" style="display: none;">
                            Đang cập nhật - tab 4
                        </div><!-- /tab-content -->
                        <div id="home3tab5" class="tab-content" style="display: none;">
                            Đang cập nhật - tab 5
                        </div><!-- /tab-content -->
                    </div><!-- /tab-container -->
                </div><!-- /tab-outer -->
            </div><!-- home3-tabouter -->
        </div><!-- box-product -->
    </div><!-- box-content -->
</div>