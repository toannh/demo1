<div class="box-content" style="margin-bottom: 30px; margin-left:10px;">
    <% if(typeof(data) !== 'undefined' && typeof(data.images) !== 'undefined' && data.images != null && data.images.length>0){ %>
    <ul class="thumbnails gallery" id="imageModel">
        <% $.each(data.images, function(i){ %>
        <% 
        var imageUrl = this;               
        %>
        <li  style="float: left; list-style: none; padding: 0px 5px; margin: 0px 5px; height: 100px; width: 100px; margin-bottom: 50px;" for="<%= i %>">
            <a style="cursor:pointer" >
                <img height="100px" width="100px" src="<%= imageUrl %>" class="grayscale" title="image <%= i %>" alt="Image<%= i %>">
                <div align="center" title="Xóa ảnh này" onclick="editmodel.deleteImage('<%= this %>', '<%= i%>')">
                    <span style="margin-top:10px; text-align:center" class="glyphicon glyphicon-trash"></span> xóa
                </div>
            </a>
        </li>
        <% }); %>     
        <div class="clearfix"></div>
    </ul>  
    <% }else{ %>
    <div class="nodata" style="color: red; margin:10px 150px;">Hiện tại model này chưa có ảnh</div>
    <% } %>
</div>
<div class="form-actions alert alert-success">
    <form id="image-model" class="form-horizontal" role="form">
        <div class="left">
            <input type="hidden" value="<%= data.id %>" name="id">                
            <label class="radio-inline">
                <input name="uploadType" onchange="reviewitem.changeType(this, 'upload1', 'upload2')" checked="checked" class="uploadType" type="radio" value="1"> Upload ảnh
            </label>
            <label class="radio-inline">
                <input name="uploadType" onchange="reviewitem.changeType(this, 'upload2', 'upload1')" class="uploadType" type="radio" value="2"> Download ảnh
            </label>                
            <div class="radio" id="upload1">
                <input type="file" style="display:none" id="lefile" name="image" onchange="editmodel.addImage();">
                <div style="width:300px;" class="input-group">
                    <input type="text" class="form-control" id="photoCover">
                    <a onclick="$('#lefile').click();" class="btn btn-default input-group-addon">Chọn</a>                    
                </div>
            </div>   
            <div id="upload2" style="display: none;width:300px;"  class="radio">
                <input type="text" class="form-control" name="imageUrl" onchange="editmodel.addImage();" />               
            </div>
        </div>
    </form>            
</div>