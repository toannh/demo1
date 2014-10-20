<li>
    <a href="<%=baseUrl%>/user/profile.html" title="<%= username==null?email:username %>"><span class="avatar"><img src="<%=avatar%>" alt="avatar" /></span><span class="color-user"><%= username==null?email:username %></span><span class="icon-arrowdown"></span></a>
    <div class="popmenu popmenu-right">
        <span class="popmenu-bullet"></span>
        <div class="popmenu-small">
            <div class="user-avatar">
                <div class="avatar"><img src="<%=avatar%>" alt="avatar" /></div>
                <div class="sf-row"><a href="<%=baseUrl%>/user/profile.html" title="<%= username==null?email:username %>"><b><%= name==null?username:name %></b></a></div>
                <div class="sf-row"><%=username==null?email:username%></div>
                <div class="sf-row">&nbsp;</div>
            </div>
            <div class="popmenu-line"></div>
            <ul class="popmenu-ul bg-gray text-right">
                <li><a href="<%=baseUrl%>/user/profile.html">Thông tin cá nhân<span class="icon20-user"></span></a></li>
                <li><a href="<%=baseUrl%>/user/message.html">Tin nhắn <b>(0)</b><span class="icon20-email"></span></a></li>
                <li class="green-li"><a href="<%=baseUrl%>/user/signout.html">Đăng xuất<span class="icon20-logout"></span></a></li>
            </ul>
            <div class="popmenu-line"></div>

            <div class="user-xeng">
                <div class="sf-row">Xèng hiện có: <b><%= balance.toMoney(0, ',', '.') %> Xèng</b></div>
                <div class="sf-row"><a href="<%=baseUrl%>/user/cash.html">Mua xèng</a></div>
            </div>
        </div><!-- /popmenu-small -->
    </div><!-- /popmenu -->
</li>
<li>
    <a>Mua hàng</a>
    <div class="popmenu popmenu-right">
        <span class="popmenu-bullet"></span>
        <div class="popmenu-medium">
            <ul class="popmenu-ul text-right">
                <li><a href="<%=baseUrl%>/user/don-hang-cua-toi.html">Đơn hàng của tôi<span class="icon20-mylist"></span></a></li>
                <li><a href="<%=baseUrl%>/user/theo-doi-dau-gia.html">Sản phẩm đang theo dõi<span class="icon20-eye"></span></a></li>
            </ul>
        </div><!-- /popmenu-medium -->
    </div><!-- /popmenu -->
</li>
<li>
    <a>Bán hàng</a>
    <div class="popmenu popmenu-right">
        <span class="popmenu-bullet"></span>
        <div class="popmenu-small">
            <ul class="popmenu-ul text-right">
                <li class="green-li"><a href="<%=baseUrl%>/user/dang-ban.html">Đăng bán<span class="icon20-push"></span></a></li>
                <li><a href="<%=baseUrl%>/user/item.html">Danh sách sản phẩm<span class="icon20-list"></span></a></li>
                <li><a href="<%=baseUrl%>/user/hoa-don-ban-hang.html">Hoá đơn bán hàng<span class="icon20-menu"></span></a></li>
            </ul>
            <div class="popmenu-line"></div>
            <ul class="popmenu-ul text-right">
                <li><a href="<%=baseUrl%>/user/posting.html">Quản lý up tin<span class="icon20-pagelist"></span></a></li>
                <li><a href="<%=baseUrl%>/user/vipitem.html">Quản lý tin VIP<span class="icon20-heart"></span></a></li>
                <% if(shop==null){ %>
                <li><a href="<%=baseUrl%>/user/open-shop-step1.html">Mở shop<span class="icon20-shop"></span></a></li>
                <% } else { %>
                <li><a href="<%=baseUrl%>/user/cau-hinh-shop-step1.html">Quản trị Shop<span class="icon20-shop"></span></a></li>
                <% } %>
            </ul>
        </div><!-- /popmenu-small -->
    </div><!-- /popmenu -->
</li>
<li>
    <a href="<%=baseUrl%>/gio-hang.html"><span class="icon-cart"></span></a>
    <div class="popmenu popmenu-right">
        <span class="popmenu-bullet"></span>
        <div class="popmenu-medium">
            <div class="usercart-list">
                <div class="grid">
                    <div class="g-row">Bạn chưa chọn mua sản phẩm nào vào giỏ hàng!</div>
                </div><!-- /grid -->
            </div><!-- /usercart-list -->
        </div><!-- /popmenu-medium -->
    </div><!-- /popmenu -->
</li>