<form class="form-horizontal" id="changepass-form">
    <input name="email" type="hidden" value="<%= email  %>" />
    <div class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-3 control-label">Mật khẩu cũ:</label>
            <div class="col-sm-7">
                <input name="oldPass" type="password" class="form-control" placeholder="Nhập mật khẩu cũ" />
                <div class="help-block oldPass"></div>
            </div>  
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Mật khẩu mới:</label>
            <div class="col-sm-7">
                <input name="newPass" type="password" class="form-control" placeholder="Nhập mật khẩu mới"/>
                <div class="help-block newPass"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">Xác nhận mật khẩu:</label>
            <div class="col-sm-7">
                <input name="confirmPass" type="password" class="form-control" placeholder="Xác nhận mật khẩu mới"/>
                <div class="help-block confirmPass"></div>
            </div>
        </div>
    </div> 
</form>