

<div class="row" style="padding-bottom: 10px;">
    <% if(data != null ){ %><b>Sản phẩm:</b> <%=data.name%> <% } %>
</div>
<div class="row" style="padding-bottom: 10px;">
    <% if(data.images != null && data.images != "" && data.images.length > 0) { $.each(data.images, function(){ %>
    
    <% }); } else { %>
    <p class="alert alert-danger text-center error_hotdeal">Hotdeal chưa có ảnh</p>
    <% } %>
    <div class="frame" id="frameIMG" style="margin: 0 0.3em; float: left">
        <img id="photo" src="<%=data.images[0]%>" style="max-width: 300px">
    </div><br/>
   
</div>
<div class="form-actions alert alert-success" style="margin-top: 40px">
    <form id="form-edit" class="form-horizontal" role="form">
        <div class="left">
            <input type="hidden" value="<%= data.id %>" name="itemId">                
            <label class="radio-inline">
                <input name="uploadType" checked="checked" class="uploadType" type="radio" value="1"> Upload ảnh
            </label>
                    
            <div class="radio" id="upload1">
                <input type="file" style="display:none" id="lefile" name="images" onchange="topsellerbox.addImageItem();">
                <div style="width:300px;" class="input-group">
                    <input type="text" class="form-control" id="photoCover">
                    <a onclick="$('#lefile').click();" class="btn btn-default input-group-addon">Chọn</a>                    
                </div>
            </div>   
          
        </div>
        <input type="hidden" id="x1" name="x" value="0">
        <input type="hidden" id="y1" name="y" value="0">
        <input type="hidden" id="w" name="width" value="0">
        <input type="hidden" id="h" name="height" value="0">
    </form>            
</div>