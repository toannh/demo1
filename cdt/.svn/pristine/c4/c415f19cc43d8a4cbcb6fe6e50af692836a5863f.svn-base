<form class="form-horizontal" id="model-add-form">
    
    <input type="hidden" name="id" id="id" value="<%= (typeof(data) !== 'undefined')? data.id:'' %>" />
    <div class="form-group">     
        <label class="control-label col-sm-3">Danh mục cha:</label>
        <div class="col-sm-9" style="padding: 0 30px">
            <div id="selectcategorys"></div>
            <input type="hidden" value="<%= (typeof data!=='undefined')? data.categoryId: ''%>"  name="categoryId" class="categoryId" id="categoryId"/>
        </div>
    </div>
    
    <div class="form-group">
        <label class="control-label col-sm-3">Thương hiệu</label>
        <div class="col-sm-9 ">
            <div class="input-group">
                <input type="text" name="manufacturerId" id="mfDetail" class="form-control" placeholder="Mã thương hiệu" value="<%= (typeof(data) !== 'undefined')? data.manufacturerId:'' %>" />
                <span class="input-group-btn">
                    <button class="btn btn-default" onclick="model.loadmf('mfDetail');"  type="button">Tìm</button>
                </span>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Tên model</label>
        <div class="col-sm-9">
            <input name="name" type="text" class="form-control" placeholder="Nhập tên model" value="<%= (typeof(data) !== 'undefined')? data.name:'' %>" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Ebay keyword</label>
        <div class="col-sm-9">
            <input name="ebayKeyword" type="text" class="form-control" placeholder="nhập ebay keyword" <%= (typeof(data) !== 'undefined')? data.ebayKeyword:'' %> />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Trạng thái hoạt động</label>
        <div class="col-sm-9">
            <select name="active" type="text" class="form-control">
                <option value="1">Đang hoạt động</option>
                <option value="2">Tạm khoá</option>
            </select>
        </div>
    </div>
</form>