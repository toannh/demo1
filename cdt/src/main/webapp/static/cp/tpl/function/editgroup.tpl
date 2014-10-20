<form class="form-horizontal" id="form-edit">
    <div class="form-group">
        <label class="control-label col-sm-3">Tên nhóm :</label>
        <div class="col-sm-8">
            <input name="name" type="text" class="form-control" placeholder="Tên nhóm"  value="<%= typeof(name)!=='undefined'?name:'' %>"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Vị trí :</label>
        <div class="col-sm-8">
            <input name="position" type="text" class="form-control" placeholder="Thứ tự nhóm"  value="<%= typeof(position)!=='undefined'?position:'0' %>"/>
        </div>
    </div>

</form>