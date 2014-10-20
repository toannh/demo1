<nav id="user">
    <ul>
        <li>
            <a href="<%=baseUrl%>/user/profile.html"><span class="glyphicon glyphicon-user"></span> <%= name==null?username:name %></a>
            <ul>
                <li><a href="<%=baseUrl%>/user/profile.html"><span class="glyphicon glyphicon-user"></span> Thông tin cá nhân</a></li>
                <li><a href="<%=baseUrl%>/user/message.html"><span class="glyphicon glyphicon-envelope"></span> Tin nhắn (0)</a></li>
            </ul>
        </li>
        <li>
            <a href="<%=baseUrl%>/user/order.html"><span class="glyphicon glyphicon-gift"></span> Mua hàng</a>
            <ul>
                <li><a href="<%=baseUrl%>/user/order.html"><span class="glyphicon glyphicon-download-alt"></span> Đơn hàng của tôi</a></li>
                <li><a href="<%=baseUrl%>/user/watching.html"><span class="glyphicon glyphicon-eye-open"></span> Sản phẩm đang theo dõi</a></li>
            </ul>
        </li>
        <li>
            <a href="<%=baseUrl%>/user/item.html"><span class="glyphicon glyphicon-plus-sign"></span> Bán hàng</a>
            <ul>
                <li><a href="<%=baseUrl%>/user/dang-ban.html"><span class="glyphicon glyphicon-plus-sign"></span> Đăng bán</a></li>
                <li><a href="<%=baseUrl%>/user/item.html"><span class="glyphicon glyphicon-list"></span> Danh sách sản phẩm</a></li>
                <li><a href="<%=baseUrl%>/user/myorder.html"><span class="glyphicon glyphicon-list-alt"></span> Hoá đơn bán hàng</a></li>
                <li><a href="<%=baseUrl%>/user/upschedule.html"><span class="glyphicon glyphicon-upload"></span> Quản lý up tin</a></li>
                <li><a href="<%=baseUrl%>/user/vipitem.html"><span class="glyphicon glyphicon-check"></span> Quản lý tin VIP</a></li>
                <li><a href="<%=baseUrl%>/user/shop.html"><span class="glyphicon glyphicon-send"></span> Quản trị Shop</a></li>
            </ul>
        </li>
        <li class="white-li"><a href="<%=baseUrl%>/user/signout.html"><span class="glyphicon glyphicon-share"></span> Đăng xuất</a></li>
    </ul>
</nav><!--User--->