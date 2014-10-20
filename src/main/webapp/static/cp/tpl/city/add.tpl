<form class="form-horizontal" id="city-add-form">
    <input type="hidden" name="id" id="id" value="<%= (typeof(data) !== 'undefined')? data.id:'' %>" />
    <div class="form-group">
        <label class="control-label col-md-4">Tỉnh / thành phố </label>
        <div class="col-md-8" id="selectcategorys">
            <input name="name" type="text" class="form-control" placeholder="Nhập tên " value="<%= (typeof(data) !== 'undefined')? data.name:'' %>" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-4">Vị trí</label>
        <div class="col-md-8" id="selectcategorys">
            <input name="position" type="text" class="form-control" placeholder="Nhập vị trí" value="<%= (typeof(data) !== 'undefined')? data.position:'' %>" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-md-4">Mã ship chung:</label>
        <div class="col-md-8" id="selectcategorys">
            <input name="scId" type="text" class="form-control" placeholder="Nhập mã ship chung" value="<%= (typeof(data) !== 'undefined')? data.scId:'' %>" />
        </div>
    </div>
</form>