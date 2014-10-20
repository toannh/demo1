<form name="bannerForm" class="form-horizontal" id="banner-form-add">
    <input type="text" style="display: none" name="id" value="<%= (typeof data!=='undefined')? data.id: ''%>" />            
    <div class="form-group">
        <div class="col-sm-2">
            <label>Name<span class="required">(*)</span></label>
        </div>        
        <div class="col-sm-8">
            <input name="name" class="form-control" placeholder="Nhập tên banner" value="<%= (typeof data!=='undefined')? data.name: ''%>"/>            
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-2" >
            <label>Vị trí<span class="required">(*)</label>
        </div>
        <div class="col-sm-4" >
            <select class="form-control" name="position" >
                <option value="-1">---Chọn Vị trí----</option>
                <option <%= (typeof data!=='undefined' && data.position==1)? 'selected': ''%> value="1">Vị trí 1</option>
                <option <%= (typeof data!=='undefined' && data.position==2)? 'selected': ''%> value="2">Vị trí 2</option>
                <option <%= (typeof data!=='undefined' && data.position==3)? 'selected': ''%> value="3">Vị trí 3</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-2">
            <label>Url<span class="required">(*)</label>
        </div>
        <div class="col-sm-4">
            <input name="url" value="<%= (typeof data!=='undefined')? data.url: ''%>" type="url" class="form-control" placeholder="URL topic"/>
        </div>        
    </div>

    <div class="form-group">
        <div class="col-sm-2">
            <label>Image<span class="required">(*)</label>
        </div>
        <div class="col-sm-4">
            <input type="file" style="display:none" id="lefile" name="image" onchange="homebanner.previewImg(this)">
            <div style="width:225px;" class="input-group">
                <input type="text" class="form-control" id="photoCover">
                <a onclick="$('#lefile').click();" class="btn btn-default input-group-addon">Chọn</a>                    
            </div>
            <img id="previewImg" width="200" src="<%= (typeof data!=='undefined')? data.image: '/static/cp/img/preview_icon.png'%>" onclick="$('#lefile').click();" />

        </div>
    
    </div>
    <div class="form-group">
        <div class="col-sm-2">
            <label>Active</label>
        </div>
        <div class="col-sm-8">
            <% if((typeof data!=='undefined')&& data.active){%>
            <input type="checkbox" checked="checked" name="active" value="true" />
            <%}else{%>
            <input type="checkbox" name="active" value="true"/>
            <%}%>
        </div>        
    </div>
</form>