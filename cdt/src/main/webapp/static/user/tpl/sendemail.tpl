<form class="form-horizontal" id="form-add">

    <p>Thời gian ChợĐiệnTử duyệt email: <strong>tối đa 2h làm việc</strong></p>
    <p>Bạn đã chọn <a href="#" onclick="sellercustomer.listcustomerselect()"><%=cout%></a> khách hàng để gửi email</p>
    <% if(resp.data!=null && resp.data.length>0){ %>
    <div class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2 control-label">Chọn email:</label>
            <div class="col-sm-10">
                <select class="form-control" name="sellerEmailMarketing">
                    <option value="0">--- Chọn ---</option>
                    <% if(resp.data!=null){ for(var i = 0; i < resp.data.length; i++){ %>
                    <option value="<%= resp.data[i].id %>"><%= resp.data[i].name %></option>
                    <% }} %>
                </select>
            </div>
        </div>                    
    </div>
    <% }else{ %>
    <center><span style="color: red">Không tồn tại E-Mail Marketing</span></center>
     <% }%>
    <p class="clearfix"><span class="pull-right">Nếu bạn chưa soạn email, hãy click <a href="<%=baseUrl%>/user/email-marketing.html" target="_blank">vào đây</a> để soạn</span></p>         
    <div class="row">
        <hr style="width: 95%;">
        <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12">
            Phí gửi email: <strong><%=parseFloat(costs).toMoney(0, ',', '.')%> xèng </strong>
        </div>
        <div class="col-lg-7 col-md-5 col-sm-6 col-xs-12 text-right">
            Tài khoản xèng của bạn hiện có: <strong><%=parseFloat(xeng).toMoney(0, ',', '.')%> xèng </strong>&nbsp;<a href="<%=baseUrl%>/user/tai-khoan-xeng.html" target="_blank" class="btn btn-primary">Nạp thêm xèng</a>
        </div>
    </div>


</form>