<form class="form-horizontal" id="form-edit">
    <input type="hidden" name="uri" value="<%= typeof(func.uri)!=='undefined'?func.uri:'' %>"/>
    <div class="form-group">
        <label class="control-label col-sm-3">Nhóm:</label>
        <div class="col-sm-8">
            <select class="form-control" name="group">
                <option value="0">Chọn nhóm</option>
                <% if(groups!=null && groups!='undefined' && groups.length>0){ for(var i=0; i< groups.length; i++){ %>
                <option value="<%= groups[i].name %>" for="<%= groups[i].position %>" <%if(func.groupName == groups[i].name) {%> selected <% }%>><%= groups[i].name %></option>
                <% }}%>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Hoặc tạo nhóm:</label>
        <div class="col-sm-8">
            <div class="col-sm-6">
                <input name="newGroup" type="text" class="form-control" placeholder="Tên nhóm"/>
            </div>
            <div class="col-sm-6">
                <input name="groupPosition" type="text" class="form-control" placeholder="Thứ tự nhóm"/>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Tên chức năng:</label>
        <div class="col-sm-8">
            <input name="name" value="<%= typeof(func.name)!=='undefined'?func.name:'' %>" type="text" class="form-control" placeholder="Tên chức năng"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Thứ tự:</label>
        <div class="col-sm-8">
            <input name="position" value="<%= typeof(func.position)!=='undefined'?func.position:''%>" type="text" class="form-control" placeholder="Thứ tự"/>
        </div>
    </div>
    <% if(services.length > 0){ %>
    <table class="table">
        <tr>
            <th>Định nghĩa hành động</th>
            <th style="width: 90px">Sắp xếp</th>
            <th style="width: 90px">Bỏ quyền</th>
        </tr>
        <% for(var i=0; i< services.length; i++){ %>
        <tr for="service">
            <td><input type="hidden" for="uri" value="<%= services[i].uri %>"/><input for="name" value="<%= typeof(services[i].name)!=='undefined'?services[i].name:''%>" type="text" class="form-control input-sm" placeholder="Tên hành động"/><span class="help-block"><%= services[i].uri %></span></td>
            <td><input for="position" value="<%= typeof(services[i].position)!=='undefined'?services[i].position:''%>" class="form-control input-sm"/></td>
            <td style="text-align: center"><input for="skip" <%= services[i].skip?'checked':'' %> value="1" title="Bỏ chọn" type="checkbox" class="cbitemRole" /></td>
        </tr>
        <% } %>
    </table>
    <% } %>
</form>