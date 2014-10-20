<form class="form-horizontal" id="form-edit-shopnewscategory">
    <input name="id" value="<%= (typeof data!=='undefined')? data.id: ''%>" style="display: none" />
    <input name="userId" value="<%= (typeof data!=='undefined')? data.userId: ''%>" style="display: none" />
    <div class="form-group">
        <label class="control-label col-sm-3">Tên:</label>
        <div class="col-sm-8">
            <input name="name" type="text" class="form-control" value="<%= (typeof data!=='undefined')? data.name: ''%>" placeholder="Tên"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Danh mục cha:</label>
        <div class="col-sm-8">
            <select name="parentId" class="form-control">
                <option value="">Là danh mục cấp 1</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Mô tả:</label>
        <div class="col-sm-8">
            <textarea name="description" class="form-control"><%= (typeof data!=='undefined')? data.description: ''%></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Thứ tự:</label>
        <div class="col-sm-8">
            <input name="position" type="text"class="form-control" value="<%= (typeof(data) != 'undefined')? data.position:'' %>"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Trạng thái hiển thị:</label>
        <div class="col-sm-8">
            <% if(data.active){ %>
            <input value="1" name="active" type="checkbox" checked="check"> 
            <% }else{ %>
            <input value="1" name="active" type="checkbox">
            <% } %>
        </div>
    </div>
</form>