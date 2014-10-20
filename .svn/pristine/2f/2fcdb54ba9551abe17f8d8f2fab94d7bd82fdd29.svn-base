<form id="category-property-value">
    <textarea style="display:none" name="properties" class="data-content"></textarea>
    <input type="hidden" class="categoryPropertyId" name="categoryPropertyId"/>
    <input type="hidden" class="categoryId" name="categoryId"/>
    <table class="table table-striped table-bordered bootstrap-datatable tblInsert table-responsive">
        <thead>
            <tr class="warning">
                <th class="text-center" style="width:10px">STT</th>
                <th class="text-center" style="width: 35%">Giá trị</th>
                <th class="text-center" style="width: 20%">Giá trị lọc</th>
                <th class="text-center">Lọc</th>
                <th class="text-center" style="width: 15%">Thao tác</th>
            </tr>
        </thead>   
        <tbody>    
            <tr>
                <td class="text-center"><input name="position" type="text" style="width: 45px;" class="form-control"/></td>
                <td class="text-center"><input name="name" type="text" class="form-control col-sm-8" /></td>

                <td class="text-center"><input name="value" type="text" class="form-control col-sm-4" value="<%= this.value %>" /></td>
                <td class="text-center"><input name="filter" type='checkbox' /></td>
                <td class="text-center"><button type="button" class="btn btn-xs btn-success" onclick="category.addPropertyValue();">Thêm</button></td>
            </tr>
        </tbody>
    </table>    

    <table class="table table-striped table-bordered tblValue table-responsive" id="sortPropertyValue">
        <thead>
            <tr class="success">
                <th class="text-center" style="width:10px"></th>
                <th class="text-center" style="width: 35%">Giá trị</th>
                <th class="text-center" style="width: 20%">Giá trị lọc</th>
                <th class="text-center">Lọc</th>
                <th class="text-center" style="width: 15%">Thao tác</th>
            </tr>
        </thead>   
        <tbody>
            <% if(typeof data !== 'undefined'&& data !== null&& data.length > 0){ %>
            <% $.each(data, function(i){ %>
            <tr class="valueDraggable valueDroppable <%= this.id %>" for="<%= this.id %>">
        <input type="hidden" name="id" class="pro" for="<%= this.id %>" style="width: 25px" value="<%= this.id %>"/>
        <td class="text-center">
            <input type="text" name="position" class="form-control pro" for="<%= this.id %>" style="width: 45px;" value="<%=this.position %>"/>
        </td>
        <td class="text-center"><input name="name" type="text" class="form-control pro" for="<%= this.id %>" value="<%= this.name %>" /></td>

        <td class="text-center"><input name="value" type="text" class="form-control col-sm-4 pro" for="<%= this.id %>" value="<%= this.value %>" /></td>
        <td class="text-center"><input name="filter" type="checkbox" class="pro" for="<%= this.id %>" value="<%= this.filter %>" <%= this.filter?"checked='checked'":'' %> /></td>
        <td class="text-center">
            <button type="button" class="btn btn-xs btn-danger" onclick="category.removePropertyValue('<%= this.id %>')">Xóa</button>
        </td>
        </tr>
        <% });%>
        <% }%>
        </tbody>
    </table>   
</form>
