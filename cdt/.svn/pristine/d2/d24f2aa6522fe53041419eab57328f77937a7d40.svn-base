
    <form class="form-horizontal" id="form-add">
        <input type="hidden" name="id" value="<%= (typeof(data) !== 'undefined')? data.id:'' %>"/>
        <div class="form-group">
            <label class="control-label col-sm-4">Tên danh mục:</label>
            <div class="col-sm-8">
                <input name="name" value="<%= (typeof(data) !== 'undefined')? data.name:'' %>" type="text" class="form-control" placeholder="Tên danh mục tin"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4">Miêu tả:</label>
            <div class="col-sm-8">
                <textarea name="description" cols="40" rows="5" class="form-control"><%= (typeof(data) !== 'undefined')? data.description:'' %></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4">Danh mục cha:</label>
            <div class="col-sm-8">
               <select class="form-control" id="selectParentId" name="parentId">
                    <option value="0">--------- Chọn ----------</option>
                    <% for(var i = 0; i < listCate.length; i ++) {%>
                        <% if(listCate[i].level==0){ %>
                            <option value="<%= listCate[i].id %>"><%= listCate[i].name %></option>
                        <% } }%>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4">Trạng thái:</label>
            <div class="col-sm-8">
                <select class="form-control" name="active">
                    <option value="0">Chọn trạng thái</option>
                    <option value="true">Active</option>
                    <option value="false">Không Active</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4">Vị trí:</label>
            <div class="col-sm-8">
                <input name="position" value="<%= (typeof(data) !== 'undefined')? data.position:'' %>" type="text" class="form-control" placeholder="Vị trí sắp sếp"/>
            </div>
        </div>
    </form>