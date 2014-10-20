<div class="form-horizontal form-reset-col" id="editItem">
    <div class="form-group">
        <label class="col-sm-2 control-label">Icon đại diện:</label>
        <div class="col-sm-7">
            <input type="text" readonly="readonly" class="form-control" value="<%= item.icon %>" name="icon" placeholder="Chọn icon">
        </div>
        <div class="col-sm-2 reset-padding"><button type="button" onclick="shophomeitem.showIconBox()" class="btn btn-default">Chèn icon</button></div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Tên box nổi bật:</label>
        <div class="col-sm-7">
            <input type="text" class="form-control" name="name" value="<%= item.name %>" placeholder="Tên box nổi bật">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Danh sách ID sản phẩm:</label>
        <div class="col-sm-7">
            <input type="text" class="form-control" name="itemIds" value="<%= item.itemIds %>" placeholder="Danh sách id sản phẩm cách nhau bởi dấu ,">
        </div>
        <div class="col-sm-2 reset-padding"><button type="button" onclick="shophomeitem.selectItem()" class="btn btn-default">Chọn sản phẩm</button></div>
    </div>  
    <div class="form-group">
        <label class="col-sm-2 control-label">Vị trí hiển thị:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control"  name="position" value="<%= item.position %>" placeholder="Vị trí hiển thị">                                                
        </div>
    </div>
</div>