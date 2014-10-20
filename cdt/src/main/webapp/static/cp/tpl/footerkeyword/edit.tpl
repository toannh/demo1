
<form class="form-horizontal" id="form-add">
    <input type="hidden" name="id" value="<%= (typeof data!=='undefined')? data.id: ''%>"/>
    <div class="form-group">
        <label class="control-label col-sm-2">Tiêu đề:</label>
        <div class="col-sm-10">
            <input name="title" value="<%= (typeof data!=='undefined')? data.title: ''%>" type="text" class="form-control" placeholder="Tiêu đề"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Danh mục:</label>
        <div class="col-sm-10"> 
            <select class="form-control" name="categoryId" onchange="news.loadCateSub($(this).val());">
                <option value="">---Chọn---</option>
                <% for(var i = 0; i < newsCategories.length; i++){ %>
                <% if(newsCategories[i].level == 0){ %>
                <option value="<%= newsCategories[i].id %>" <% if(newsCategories[i].id==data.categoryId){ %> selected="true" <% } %>><%= newsCategories[i].name %></option>
                <% } %>
                <% } %>
            </select>  
        </div>
    </div>
    <div class="form-group" id="sub-cate">
        <% if(data.categoryId1!= data.categoryId){ %>

        <label class="control-label col-sm-2"> </label>
        <div class="col-sm-10">
            <select class="form-control" name="categoryId1">
                <option value="">---Chọn---</option>
                <% for(var i = 0; i < newsCategories.length; i++){ %>
                <% if(newsCategories[i].level != 0){ %>
                <option value="<%= newsCategories[i].id %>" <% if(newsCategories[i].id==data.categoryId1){ %> selected="true" <% } %>><%= newsCategories[i].name %></option>
                <% } %>
                <% } %>
            </select> 
        </div>


        <% } %>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Ảnh:</label>
        <div class="col-sm-10">
            <input type="file" style="display:none" id="lefile" name="imagefile">
            <div style="width:300px;" class="input-group">
                <input type="text" class="form-control" id="photoCover">
                <a onclick="$('#lefile').click();" class="btn btn-default input-group-addon">Chọn</a>                    
            </div>
        </div>           
    </div>
    <% if(typeof data!=='undefined' && data.image !== null) {%>
        <div class="form-group">
            <label class="control-label col-sm-2"> </label>
            <div class="col-sm-10">
                <img src="<%= (typeof data!=='undefined')? data.image: ''%>" width="100" alt="Ảnh"/>
            </div>     
        </div>
        <%}%>
    <div class="form-group">
        <label class="control-label col-sm-2">Nội dung:</label>
        <div class="col-sm-10">
            <textarea name="detail" id="txt_content" class="form-control" placeholder="Nội dung"><%= (typeof data!=='undefined')? data.detail: ''%></textarea>                
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Từ khóa:</label>
        <div class="col-sm-10">
            <input name="keywords" value="<%= (typeof data!=='undefined')? data.keywords: ''%>" type="text" class="form-control" placeholder="Từ khóa cho tìm kiếm"/>
        </div>
    </div>
    <div class="form-group" style="margin-bottom: 40px;">
        <label class="control-label col-sm-2" style="padding-left: 0">Trạng thái:</label>
        <div class="col-sm-10">
            <label>
                <input type="checkbox" id="active" name="active" <% if(data.active==true){ %>checked<% } %>>&nbsp;&nbsp;
                       Hiển thị
            </label>
        </div>
    </div>

</form>
