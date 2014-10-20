    
<form class="form-horizontal" id ="form-edit" role="form" >
    <input type="hidden" name="id" value="<%= (typeof(data) !== 'undefined')? data.id:'' %>"/>
    <input type="hidden" name="image" value="<%= (typeof(data) !== 'undefined')? data.image:'' %>"/>        
    <div class="form-group">
        <label class="control-label col-sm-4"> Tên Banner</label>
        <div class="col-sm-8">
            <input name="name" value="<%= (typeof(data) !== 'undefined')? data.name:'' %>" type="text" class="form-control" placeholder="Tên Banner"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4"> Link sản phẩm</label>
        <div class="col-sm-8">
            <input name="url" value="<%= (typeof(data) !== 'undefined')? data.url:'' %>" type="text" class="form-control" placeholder="Link tới sản phẩm"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4"> Ảnh</label>

        <input type="file" style="display:none" id="lefile" name="image">
        <div style="width:300px;padding: 18px;" class="input-group">
            <input type="text" class="form-control" id="photoCover" name="photoCover"/>
            <a onclick="$('#lefile').click();" class="btn btn-default input-group-addon">Chọn</a>    
        </div>
        <label class="control-label col-sm-4"> </label>
        <% if(typeof(data) !== 'undefined'){ %>
        <img src="<%=data.image%>" style="padding-left: 20px;"/>
        <% } %>     

    </div>
    <div class="form-group">
        <label class="control-label col-sm-4"> Ảnh Thumb</label>

        <input type="file" style="display:none" id="fileThumb" name="imageThumb">
        <div style="width:300px;padding: 18px;" class="input-group">
            <input type="text" class="form-control" id="photoCoverThumb" name="photoCoverThumb"/>
            <a onclick="$('#fileThumb').click();" class="btn btn-default input-group-addon">Chọn</a>    
        </div>
        <label class="control-label col-sm-4"> </label>
        <% if(typeof(data) !== 'undefined'){ %>
        <img src="<%=data.thumb%>" style="padding-left: 20px;"/>
        <% } %>     

    </div>
    <div class="form-group">
        <label class="control-label col-sm-4"> Số thứ tự</label>
        <div class="col-sm-8">
            <input name="position" value="<%= (typeof(data) !== 'undefined')? data.position: 0 %>" type="text" class="form-control" placeholder="Số thứ tự"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Trạng thái:</label>
        <div class="col-sm-8">
            <div class="radio-inline col-md-5">
                <label style="font-weight: normal">
                    <% if((typeof data!=='undefined')&& data.active){ %>
                    <input type="radio" checked="checked" name="active" value="true"> Kích hoạt
                    <% }else{%>
                            <input type="radio" name="active" value="true"> Kích hoạt
                    <%}%>
                </label>
            </div>
            <div class="radio-inline col-md-5">
                <label style="font-weight: normal">
                    <% if((typeof data!=='undefined')&& !data.active){ %>
                    <input type="radio" checked="checked" name="active" value="false"> Khóa
                    <% }else{%>
                            <input type="radio" name="active" value="false"> Khóa
                        <% }%>
                </label>
            </div>
        </div>
    </div>
</form>
