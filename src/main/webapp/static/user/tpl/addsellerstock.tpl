<form class="form-horizontal" id="form-add-sellerStock">
    <input type="hidden" name="id" value="<%= (typeof data!=='undefined')? data.id: ''%>"/>
    <div class="form-group" rel="title">
        <label class="control-label col-sm-3" name="name">Tên:</label>
        <div class="col-sm-8">
            <input name="name" type="text" class="form-control" value="<%= (typeof data!=='undefined')? data.name: ''%>" placeholder="Tên kho"/>
            <span name="name" style="color: #a94442"></span>
        </div>
    </div>
    <div class="form-group" rel="address">
        <label class="control-label col-sm-3">Địa chỉ:</label>
        <div class="col-sm-8">
            <input name="address" type="text" class="form-control" value="<%= (typeof data!=='undefined')? data.address: ''%>" placeholder="Địa chỉ kho"/>
            <span name="address" style="color: #a94442"></span>
        </div>
    </div>
    <div class="form-group" rel="cityId">
        <label class="control-label col-sm-3">Tình thành:</label>
        <div class="col-sm-8">
            <select name="cityId" class="form-control">
                <option></option>
            </select>
            <span name="cityId" style="color: #a94442"></span>
        </div>
    </div>
    <div class="form-group" rel="districtId">
        <label class="control-label col-sm-3">Quận huyện:</label>
        <div class="col-sm-8">
            <select name="districtId" class="form-control">
                <option></option>
            </select>
            <span name="districtId" style="color: #a94442"></span>
        </div>
    </div>
    <div class="form-group" rel="phone">
        <label class="control-label col-sm-3">Điện thoại:</label>
        <div class="col-sm-8">
            <input name="phone" type="text" class="form-control" value="<%= (typeof data!=='undefined')? data.phone: ''%>" placeholder="Số điện thoại"/>
            <span name="phone" style="color: #a94442"></span>
        </div>
    </div>
    <div class="form-group" rel="sellerName">
        <label class="control-label col-sm-3">Người liên hệ:</label>
        <div class="col-sm-8">
            <input name="sellerName" type="text" class="form-control" value="<%= (typeof data!=='undefined')? data.sellerName: ''%>" placeholder="Tên người liên hệ"/>
            <span name="sellerName" style="color: #a94442"></span>
        </div>
    </div>
    <div class="form-group" rel="order">
        <label class="control-label col-sm-3">Vị trí:</label>
        <div class="col-sm-8">
            <input name="order" type="text" class="form-control"  value="<%= (typeof data!=='undefined')? data.order: ''%>" placeholder="Vị trí sắp xếp"/>
            <span name="order" style="color: #a94442"></span>
        </div>
    </div>
</form>
