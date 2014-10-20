<form class="form-horizontal" id="form-add-usercontact">
    <input name="sellerId" value="<%= sellerId %>" type="hidden" />
    <div class="form-group" rel="title">
        <label class="control-label col-sm-3" name="title">Tên:</label>
        <div class="col-sm-8">
            <input name="title" type="text" class="form-control" placeholder="Tên"/>
            <span name="title" style="color: #a94442"></span>
        </div>
    </div>
    <div class="form-group" rel="email">
        <label class="control-label col-sm-3">Email:</label>
        <div class="col-sm-8">
            <input name="email" type="text" class="form-control" placeholder="Địa chỉ email"/>
            <span name="email" style="color: #a94442"></span>
        </div>
    </div>
    <div class="form-group" rel="phone">
        <label class="control-label col-sm-3">Phone:</label>
        <div class="col-sm-8">
            <input name="phone" type="text" class="form-control"  placeholder="Số điện thoại"/>
            <span name="phone" style="color: #a94442"></span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Yahoo:</label>
        <div class="col-sm-8">
            <input name="yahoo" type="text" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Sky:</label>
        <div class="col-sm-8">
            <input name="skype" type="text" class="form-control"/>
        </div>
    </div>
</form>
