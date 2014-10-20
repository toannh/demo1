
<form class="form-horizontal" id="banner-form-edit">
    <input type="text" style="display: none" name="id" value="<%= (typeof data!=='undefined')? data.id: ''%>" />            
    <div class="form-group ancestors" style="margin-bottom: 0px;">                  
    </div>
    <div class="form-group" for="categoryId">
        <label class="control-label col-sm-3">Danh mục cha:</label>
        <div class="col-sm-9">
            <select name="categoryId" class="form-control">
                <option value="">--Chọn danh mục cha--</option>
                <% for(var i=0; i < cates.length; i++){
                %>
                <option value="<%=cates[i].id%>" <%=(typeof data!=='undefined' && cates[i].id==data.categoryId)?'selected="true"':""%> ><%=cates[i].name%></option>
                <%}%>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Link:</label>
        <div class="col-sm-9">
            <input name="link" value="<%= (typeof data!=='undefined')? data.link: ''%>" type="text" class="form-control" placeholder="Link banner"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3"> Ảnh</label>

        <input type="file" style="display:none" id="lefile" name="banner">
        <div style="width:300px;padding:0 15px;" class="input-group">
            <input type="text" class="form-control" id="photoCover" name="photoCover"/>
            <a onclick="$('#lefile').click();" class="btn btn-default input-group-addon">Chọn</a>    
        </div>
        <label class="control-label col-sm-9"> </label>
        <% if(typeof(data) !== 'undefined'){ %>
        <img src="<%=data.banner%>" style="padding-left: 20px;"/>
        <% } %>     

    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Title:</label>
        <div class="col-sm-9">                    
            <input name="title" value="<%= (typeof data!=='undefined')? data.title: ''%>" type="text" class="form-control" placeholder="Title"/>                    
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Vị trí:</label>
        <div class="col-sm-9">
            <select name="position" class="form-control">
                <option value="">--Chọn vị trí banner--</option>
                <option value="BROWSE_TOP" <%=(typeof data!=='undefined' && data.position=='BROWSE_TOP')?'selected="true"':""%>>Top banner danh mục</option>
                <option value="BROWSE_CONTENT" <%=(typeof data!=='undefined' && data.position=='BROWSE_CONTENT')?'selected="true"':""%> >Banner danh mục</option>
                <option value="BACKEND_USER" <%=(typeof data!=='undefined' && data.position=='BACKEND_USER')?'selected="true"':""%> >Banner backend user</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Trạng thái:</label>
        <div class="col-sm-9">                    
            <label class="checkbox-inline">                                
                <% if((typeof data!=='undefined')&& data.active){
                %>
                <input type="checkbox" checked="checked" name="active" />Hoạt động
                <%}else{
                %>
                <input type="checkbox" name="active" />Hoạt động
                <%}%>
            </label>  
        </div>
    </div>
</form>