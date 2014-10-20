<form class="form-horizontal" id="form-add">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-2 control-label" for="name">Tiêu đề:</label>
                <div class="col-sm-10">
                    <input type="text" name="nameSMS" class="form-control" placeholder="Tiêu đề chỉ để người bán quản lý" value="">
                    <span class="help-block" for="name"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label" for="sendTime">Thời điểm gửi SMS:</label>
                <div class="col-sm-8">
                    <div class="checkbox-inline" style="padding-top:0px; margin-right:15px;">
                        <label class="control-label"><input type="checkbox" name="checkTime"> Gửi ngay</label>
                    </div>
                    <div class="date-picker-block" style="display:inline-block; min-width:255px;">
                        <input type="hidden" name="sendTime" class="form-control timesend" placeholder="Đặt lịch gửi">                       
                        <span class="glyphicon glyphicon-calendar"></span>
                    </div>                                      
                    <span class="help-block" for="sendTime"></span>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-2 control-label" for="content">Nội dung sms</label>
                <div class="col-sm-10">
                    <textarea class="form-control" name="content" rows="5" onkeyup="email.countCharacters()" onkeypress="email.countCharacters()" onkeydown="email.countCharacters()"></textarea>
                    <span class="pull-right small mgt-15">Tối đa 130 ký tự - <span class="clr-org"><strong>Còn <span class="countresult">130/130</span></strong></span> ký tự</span>
                    <span class="help-block" for="content"></span>
                </div>
            </div>
           
            <div class="form-group">
                <div class="col-sm-12"><strong class="clr-red">Lưu ý:</strong> Tin nhắn của bạn sẽ được gửi đi sau khi CĐT đã kiểm duyệt" Sau nút lưu lại</div>
            </div>
        </div>
    </div>
    <div class="row">
        <hr style="width: 95%;">
        <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12">
            Phí gửi SMS: <strong><%=parseFloat(costs).toMoney(0, ',', '.')%> xèng </strong>
        </div>
        <div class="col-lg-7 col-md-5 col-sm-6 col-xs-12 text-right">
            Tài khoản xèng của bạn hiện có: <strong><%=parseFloat(xeng).toMoney(0, ',', '.')%> xèng </strong>&nbsp;<a href="<%=baseUrl%>/user/tai-khoan-xeng.html" target="_blank" class="btn btn-primary">Nạp thêm xèng</a>
        </div>
    </div>
</form>