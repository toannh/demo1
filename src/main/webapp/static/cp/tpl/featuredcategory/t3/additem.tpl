
<form id="form-edit-uploadImg" style="display:none">
            <input type="file" style="display:none" id="lefile" name="image" onchange="featuredcategory.uploadImageItem();">
           
</form>
<form id="form-edit" class="form-horizontal" role="form">
<div class="form-group">
    <label class="control-label col-sm-2">Thay ảnh</label>
    <div class="col-sm-5">
        <input name="downloadImageItem" type="text" class="form-control" placeholder="Điền Url ảnh muốn lấy về hoặc đường dẫn file">
        <input name="urlOld" type="hidden" value="" class="form-control">
        <input name="widthF" type="hidden" value="<%=width%>" class="form-control">
        <input name="heightF" type="hidden" value="<%=height%>" class="form-control">
        
    </div>
    <div class="col-sm-5">
        <button type="button" class="btn btn-default" style="text-transform : none" onclick="featuredcategory.downloadImageItem();">Lấy ảnh về</button>

        <button type="button" class="btn btn-default" style="text-transform : none" onclick="$('#lefile').click();" >Upload từ máy</button>
    </div>
</div>
<div class="form-group" style=" width: 100%; min-height: 300px; margin-left: 5px;">
    <label class="control-label col-xs-8" style="text-align: left;">
        <span style="font-weight: bold">Kích thước mặc định:</span> [width]: <%=width%>px | [height]: <%=height%>px 

    </label>
    <div class="row">
        <div class="col-xs-14 col-md-9">
            <a class="thumbnail" style="min-height: 200px">

                <% var fag=false; if(datas.data!=null && datas.data.categoryItemHomes!=null){
                %>
                <%  for(var i=0;i< datas.data.categoryItemHomes.length; i++){ %>
                <% 
                var positionS = datas.data.categoryItemHomes[i].position;
                if(positionS>=1 && positionS<=5){
                var fag = true;
                var itemId = datas.data.categoryItemHomes[i].itemId;
                var title = datas.data.categoryItemHomes[i].title;
                %>
                <img id="photo" for="<%=datas.data.id+datas.data.categoryItemHomes[i].itemId %>" src="<%=datas.data.categoryItemHomes[i].image %>" />
                <% }} %>

                <% } %>     
                <% if(datas.data==null){ %>
                <img id="photo" for="" src="<%=staticUrl%>/market/images/data/image6.jpg" />
                <%}%>

            </a>
        </div>
        <div class="col-xs-8 col-md-3">
            <label class="control-label col-xs-8" style="text-align: left">x1 <input type="text" id="x1" class="form-control" disabled="true" name="x" value="0"></label>
            <label class="control-label col-xs-8" style="text-align: left">y1<input type="text" id="y1" class="form-control" disabled="true" name="y" value="0"></label>
            <label class="control-label col-xs-8" style="text-align: left">Width<input type="text" id="w" class="form-control" disabled="true" name="width" value="0"></label>
            <label class="control-label col-xs-8" style="text-align: left">Height<input type="text" id="h" class="form-control" disabled="true" name="height" value="0"></label>
        </div>

    </div>
</div>

<div class="form-group">
    <label class="control-label col-sm-2">ID sản phẩm</label>
    <div class="col-sm-5">
        <input name="itemId" value="<%if(fag==true){%><%=itemId%><% } %>" type="text" class="form-control" placeholder="Điền Id sản phẩm tại đây">

    </div>
</div>
<div class="form-group">
    <label class="control-label col-sm-2">Tiêu đề</label>
    <div class="col-sm-5">
        <input name="title" value="<%if(fag==true){%><%=title%> <%}%>" type="text" class="form-control" placeholder="Tiêu đề SP không quá 70 kí tự">
    </div>
</div>
</form>

