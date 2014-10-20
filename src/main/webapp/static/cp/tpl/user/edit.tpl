<form class="form-horizontal" id="editUserForm">
    <input type="hidden" name="id" value="<%= (typeof(data) !== 'undefined')? data.id:'' %>"/>
    <div class="form-group">
        <label class="control-label col-sm-3">Tên đăng nhập</label>
        <div class="col-sm-9">
            <p class="form-control-static"><%= (typeof(data) !== 'undefined')? data.username:'' %>
                <i class="glyphicon glyphicon-<%= (typeof(data) !== 'undefined' && data.active)?'check':'unchecked' %>"></i></p></div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Địa chỉ email</label>
        <div class="col-sm-9">
            <p class="form-control-static"><%= (typeof(data) !== 'undefined')? data.email:'' %>
                <i class="glyphicon glyphicon-<%= (typeof(data) !== 'undefined' && data.emailVerified)?'check':'unchecked' %>"></i></p></div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3">Họ và tên</label>
        <div class="col-sm-9">
            <input class="form-control" name="name" value="<%= (typeof(data) !== 'undefined')? data.name:'' %>" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Mật khẩu</label>
        <div class="col-sm-9">
            <input class="form-control" name="password" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Nhập lại</label>
        <div class="col-sm-9">
            <input class="form-control" name="rePassword" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Số điện thoại</label>
        <div class="col-sm-9 has-feedback">
            <input class="form-control" name="phone" value="<%= (typeof(data) !== 'undefined')? data.phone:'' %>"/>
            <span class="glyphicon glyphicon-<%= (typeof(data) !== 'undefined' && data.phoneVerified)?'check':'unchecked' %> form-control-feedback"></span>
        </div>  
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Ngày sinh</label>
        <div class="col-sm-9">
            <input type="hidden" class="editdob" value="<%= (typeof(data) !== 'undefined')? data.dob:'0' %>" name="dob" placeholder="0" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Tỉnh/ Thành phố</label>
        <div class="col-sm-9">
            <select name="cityId" rel="edit-cityId" class="form-control" >
                <option value="0"> -- Chọn tỉnh/thành phố --</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Quận/ Huyện</label>
        <div class="col-sm-9">
            <select name="districtId"  rel="edit-districtId"class="form-control" >
                <option value="0"> -- Chọn quận huyện --</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Địa chỉ nhà</label>
        <div class="col-sm-9">
            <input class="form-control" value="<%= (typeof(data) !== 'undefined')? data.address:'' %>"  name="address" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Skype</label>
        <div class="col-sm-9">
            <input class="form-control" value="<%= (typeof(data) !== 'undefined')? data.skype:'' %>" name="skype" placeholder="Tài khoản skype" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Yahoo</label>
        <div class="col-sm-9">
            <input class="form-control" value="<%= (typeof(data) !== 'undefined')? data.yahoo:'' %>" name="yahoo" placeholder="Tài khoản yahoo" />
        </div>
    </div>
</form>