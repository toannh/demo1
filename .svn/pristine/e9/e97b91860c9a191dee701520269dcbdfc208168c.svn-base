
<form name="categoryFrom" class="form-horizontal" id="hcategory-form-edit">
    <input type="text" style="display: none" name="id" value="<%= (typeof data!=='undefined')? data.id: ''%>" />            
    <div class="form-group ancestors" style="margin-bottom: 0px;">                  
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Danh mục cha:</label>
        <div class="col-sm-8">
            <select name="parentId" class="form-control">
                <option value="">--Chọn danh mục cha--</option>
                <% for(var i=0; i < cates.length; i++){
                %>
                <option value="<%=cates[i].id%>" <%=(typeof data!=='undefined' && cates[i].id==data.parentId)?'selected="true"':""%> ><%=cates[i].name%></option>
                <%}%>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Tên danh mục:</label>
        <div class="col-sm-8">
            <input name="name" value="<%= (typeof data!=='undefined')? data.name: ''%>" type="text" class="form-control" placeholder="Tên danh mục"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Ngày bắt đầu:</label>
        <div class="col-sm-8">
            <input name="startTime" class="timeselect"  value="<%= (typeof data!=='undefined')? data.startTime: 0%>" type="hidden" class="form-control" placeholder="Thời gian bắt đầu"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Ngày kết thúc:</label>
        <div class="col-sm-8">
            <input name="endTime" class="timeselect"  value="<%= (typeof data!=='undefined')? data.endTime: 0%>" type="hidden" class="form-control" placeholder="Thời gian kết thúc"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Thứ tự:</label>
        <div class="col-sm-8">                    
            <input name="position" value="<%= (typeof data!=='undefined')? data.position: ''%>" type="text" class="form-control" placeholder="Thứ tự"/>                    
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Trạng thái:</label>
        <div class="col-sm-8">                    
            <label class="checkbox-inline" style="margin: 0px 65px;">                                
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
    <div class="form-group">
        <label class="control-label col-sm-4">Đặc biệt:</label>
        <div class="col-sm-8">                    
            <label class="checkbox-inline" style="margin: 0px 65px;">                                
                <% if((typeof data!=='undefined')&& data.special){
                %>
                <input type="checkbox" checked="checked" name="special" />Hoạt động
                <%}else{
                %>
                <input type="checkbox" name="special" />Hoạt động
                <%}%>
            </label>  
        </div>
    </div>


</form>