<% var fag=false; if(datas.data!=null && datas.data.categoryBannerHomes!=null){
%>
<%  for(var i=0;i< datas.data.categoryBannerHomes.length; i++){ %>
<% 
var fag = true;
var bannerId = datas.data.categoryBannerHomes[i].id;
var url = datas.data.categoryBannerHomes[i].url;
var image = datas.data.categoryBannerHomes[i].image;

}}
%>
<form id="form-edit-uploadImg" style="display:none">
    <input type="file" style="display:none" id="lefile" name="image" onchange="featuredcategory.uploadImageItem();">         
</form>
<form id="form-edit" class="form-horizontal" role="form">
    <div class="form-group">
        <label class="control-label col-sm-2">Thay ảnh</label>
        <div class="col-sm-5">
            <input name="downloadImageItem" type="text" class="form-control" placeholder="Điền Url ảnh muốn lấy về hoặc đường dẫn file">
            <input name="widthF" type="hidden" value="<%=width%>" class="form-control">
            <input name="heightF" type="hidden" value="<%=height%>" class="form-control">
             <input name="id" type="hidden" value="<%if(fag==true){%><%=bannerId%><% } %>" class="form-control">
        </div>
        <div class="col-sm-5">
            <button type="button" class="btn btn-default" style="text-transform : none" onclick="featuredcategory.downloadImageItem();">Lấy ảnh về</button>
            <button type="button" class="btn btn-default" style="text-transform : none" onclick="$('#lefile').click();">Upload từ máy</button>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">URL</label>
        <div class="col-sm-5">
            <input name="url" value="<%if(fag==true){%><%=url%><% } %>" type="text" class="form-control" placeholder="Link dẫn banner">
        </div>
    </div>
    <div class="form-group" style=" width: 100%; min-height: 300px; margin-left: 5px;">
        <label class="control-label col-xs-8" style="text-align: left;">
            <span style="font-weight: bold">Kích thước mặc định:</span> [width]: <%=width%>px | [height]: <%=height%>px 
        </label>
        <div class="row">
            <div class="col-xs-13 col-md-11">
                <a class="thumbnail" style="min-height: 200px">
                    <%if(fag==true){%>
                    <img id="photo" for="<%=bannerId%>" src="<%=image%>" />   
                    <% }else{ %>
                    <img id="photo" for="" src="<%=staticUrl%>/market/images/data/bp-banner1.jpg" />  
                   <% } %>            
                </a>
            </div>
            <div class="col-xs-6 col-md-3">
                <label class="control-label col-xs-6" style="text-align: left">x1 <input type="text" id="x1" class="form-control" disabled="true" name="x" value="0"></label>
                <label class="control-label col-xs-6" style="text-align: left">y1<input type="text" id="y1" class="form-control" disabled="true" name="y" value="0"></label>
                <label class="control-label col-xs-6" style="text-align: left">Width<input type="text" id="w" class="form-control" disabled="true" name="width" value="0"></label>
                <label class="control-label col-xs-6" style="text-align: left">Height<input type="text" id="h" class="form-control" disabled="true" name="height" value="0"></label>
            </div>

        </div>
    </div>


</form>

