<div class="row">
        <% if(data!= null) { %>
        <div class="col-xs-6 col-md-3">
            <a class="thumbnail">
                <img src="<%=data %>" />
            </a>
        </div>
        <% } else { %>
        <p class="alert alert-danger text-center">HotDealBox chưa có ảnh</p>
        <% } %>
       
    </div>
<div class="form-actions alert alert-success">
    <form id="form-edit" class="form-horizontal" role="form">
        <div class="left">
            <input type="hidden" value="" name="id">                
            <label class="radio-inline">
                <input name="uploadType" checked="checked" class="uploadType" type="radio" value="1"> Upload ảnh
            </label>
                       
            <div class="radio" id="upload1">
                <input type="file" style="display:none" id="lefile" name="images">
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