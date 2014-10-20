<form class="form-horizontal" id="form-add">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin-top: 10px">
        <div class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-2 control-label">Tiêu đề:</label>
                <div class="col-sm-10">
                    <input type="text" name="nameCustomer" placeholder="Tiêu đề gửi email" class="form-control" >
                    <div class="help-block"></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Thời điểm gửi Email:</label>
                <div class="col-sm-5">
                    <div class="date-picker-block">
                        <input type="hidden" readonly="readonly" name="sendDate" class="form-control hasDatepicker" placeholder="Thời điểm gửi email" />
                        <span class="glyphicon glyphicon-calendar"></span>   
                        <div class="help-block"></div>
                    </div>                                      
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Mẫu Email:</label>
                <div class="col-sm-5">
                    <select class="form-control" name="template">
                        <option value="TEMPLATE_ONE">Giao diện 1</option>
                    </select>
                    <div class="help-block"></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Nội dung Email:</label>
                <div class="col-sm-10">
                    <textarea class="form-control" placeholder="Nội dung email" id="txt_content" name="content" rows="5"></textarea>                                    
                    <div class="help-block"></div>
                    <a href="javascript:;" onclick="email.preview()" class="pull-right">Xem trước nội dung gửi đi</a>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Nhập Email gửi thử</label>
                <div class="col-sm-8">
                    <input type="text" placeholder="Nhập email gửi thử" name="emailTry" class="form-control">
                    <div class="help-block"></div>
                </div>
                <div class="col-sm-2 reset-padding">
                     <a href="javascript:;" onclick="email.trySendMail()" class="btn btn-primary btn-block">Gửi thử</a>
                </div>
            </div>  

        </div>
    </div>  
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