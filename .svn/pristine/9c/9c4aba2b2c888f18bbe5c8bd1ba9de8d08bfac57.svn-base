<form class="form-horizontal" id="manuForm">
    <input type="text" style="display: none" name="id" value="<%= (typeof(data) !== 'undefined')? data.id:'' %>"/>
    <div class="form-group">
        <label class="control-label col-sm-4">Tên thương hiệu</label>
        <div class="col-sm-8">
            <input name="name" value="<%= (typeof(data) !== 'undefined')? data.name:'' %>" type="text" class="form-control" placeholder="Tên thương hiệu"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4"> Ảnh</label>
        <div class="radio col-sm-8">
            <input type="file" style="display:none" id="lefile" name="image">
            <div style="width:300px;" class="input-group">
                <input type="text" class="form-control" id="photoCover">
                <a onclick="$('#lefile').click();" class="btn btn-default input-group-addon">Chọn</a>                    
            </div>
        </div>           
    </div>
    <% if(typeof(data) !== 'undefined' && typeof(data.imageUrl) !== 'undefined' && data.imageUrl != null && data.imageUrl != '') { %> 
    <div class="form-group">
        <label class="control-label col-sm-4">&nbsp;</label>
        <div class="radio">
            <img src="<%= data.imageUrl %>" style="width:100px;" />
        </div>           
    </div>
    <% } %>
    <div class="form-group">
        <label class="control-label col-sm-4">Mô tả</label>
        <div class="col-sm-8">
            <textarea name="description" rows="5" cols="45"><%= (typeof(data) !== 'undefined')? data.description:'' %></textarea>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4">&nbsp;</label>
        <div class="col-sm-8">
            <label>
                <input type="checkbox"  id="global" name="global">&nbsp;&nbsp;
                Thương hiệu quốc tế
            </label>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">&nbsp;</label>
        <div class="col-sm-8">
            <label>
                <input type="checkbox"  id="active" name="active">&nbsp;&nbsp;
                Hiển thị
            </label>
        </div>
    </div>
</form>