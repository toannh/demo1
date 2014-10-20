<div class="popup-content-normal clearfix" style="width: 583px">
    <div class="statics-popup clearfix">
        <div class="clearfix right">
            <span class="fs12 fr" style="color:#999">Mọi quảng cáo VIP đều bắt đầu từ 0h00' tới 23h50'</span>
        </div>               
    </div><!--End thống kê tài khoản-->
    <div class="scroll-content-popup clearfix">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table" >
            <tbody>
                <% var ids = new Array(); %>
                <% $.each(data, function(){ %>
                <% ids.push(this.id); %>
                <tr>
                    <td width="65%" valign="top">
                        <div class="table-content-news-vip">
                            <span class="img-list-tinvip">
                                <a> 
                                    <img src="<%= this.images[0] %>" class="img-responsive" />
                                </a>
                            </span>
                            <p><a><%= this.name %></a></p>
                            <p>ID: <%= this.id %></p>
                            <p>Mục hiển thị tin vip: <span style="color: #428bca"><%= this.categoryName %> </span> </p>
                        </div>
                    </td>
                    <td valign="top">
                        <div class="text-left form-choose-newsvip">
                            <p>Chọn lịch quảng cáo</p>
                            <div class="reset-padding-all select-time-vip" >
                                <div class="date-picker-block">
                                    <input class="form-control" type="text" placeholder="Ngày bắt đầu" pro="startTime" for="<%= this.id %>" name="timeItemVip" /> 
                                    <span class="glyphicon glyphicon-calendar"></span>    
                                </div>
                                <div class="errorMessages text-danger" for="error_Time<%= this.id %>"></div>
                                <div class="date-picker-block" style="margin-top: 10px;" >
                                    <input class="form-control" type="text" placeholder="Ngày kết thúc" pro="endTime" for="<%= this.id %>" name="timeItemVip" /> 
                                    <span class="glyphicon glyphicon-calendar"></span>    
                                </div>
                                <div class="errorMessages text-danger" for="<%= this.id %>"></div>
                            </div>
                        </div>
                    </td>
                </tr>
                <% }); %>

            </tbody></table>              
    </div>
    <div class="statics-popup clearfix">
        <div class="clearfix">
            <strong class="fs16">Số xèng phải thanh toán: <span class="fs20 clr-org" id="valXeng">0</span> xèng</strong><div class="errorMessages" for="error_Monney"></div>
        </div>
        <div class="mgt-15 clearfix">
            <span class="mgt-5" style="display:inline-block; margin-right:10px;">Số xèng trong tài khoản: <%=parseFloat(xeng).toMoney(0, ',', '.')%> xèng </span><a href="<%=baseUrl %>/user/tai-khoan-xeng.html" target="_bank" class="button-blue">Nạp thêm xèng</a>
        </div>                      
    </div><!--End thống kê tài khoản-->
</div>