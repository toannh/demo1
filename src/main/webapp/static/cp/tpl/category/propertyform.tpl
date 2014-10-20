

<form id="category-property-form" >
    <table class="table table-striped table-bordered tbl-new table-responsive">
        <thead>
            <tr class="warning">
                <th class="text-center" style="width: 2px">STT</th>
                <th class="text-center">Tên thuộc tính</th>
                <th class="text-center" style="width: 27%">Kiểu</th>

                <th class="text-center" style="width: 18%">Toán tử</th>

                <th class="text-center" style="width: 6%">Lọc</th>
                <th class="text-center" style="width: 15%">Thao tác</th>
            </tr>
        </thead>   
        <tbody>
            <tr>                
                <td class="text-center"><input type="text" name="position" class="form-control" style="width: 45px;"/></td>
                <td class="text-center">
                    <input type="text" name="idCategory" style="display: none">
                    <input name="name" type="text" class="form-control col-sm-8"/></td>
                <td class="text-center">
                    <select name="type" class="form-control">
                        <option value="0">Vui lòng chọn</option>
                        <option value="SINGLE">Chọn một</option>
                        <option value="SINGLE_OR_INPUT">Chọn và định nghĩa</option>
                        <option value="MULTIPLE">Chọn nhiều</option>
                        <option value="INPUT">Tự định nghĩa</option>
                    </select>                                 
                </td>
                <td class="text-center">
                    <select name="operator" class="form-control">
                        <option value="0">Chọn</option>
                        <option value="EQ">=</option>
                        <option value="LTE">≤</option>
                        <option value="GTE">≥</option>
                    </select>                                
                </td>

                <td class="text-center">
                    <label>
                        <input name="filter" type="checkbox"/>
                    </label>

                </td>
                <td class="text-center">
                    <button type="button" class="btn btn-xs btn-success" onclick="category.addProperty();">Thêm</button>
                </td>
            </tr>
        </tbody>
    </table>           
    <table class="datatables table table-striped table-bordered  table-responsive" id="sortProperty">
        <thead>
            <tr class="success">
                <th class="text-center" style="width: 2px"></th>
                <th class="text-center">Tên thuộc tính</th>
                <th class="text-center" style="width: 27%">Kiểu</th>
                <th class="text-center" style="width: 18%">Toán tử</th>
                <th class="text-center" style="width: 6%">Lọc</th>
                <th class="text-center" style="width: 25%">Thao tác</th>
            </tr>
        </thead>   
        <tbody>

            <% $.each(data, function(i){ %>
            <tr class="draggable droppable <%= this.id %>" for="<%= this.id %>">
        <input type="hidden" name="id" style="width: 25px" value="<%= this.id %>"/>
        <td class="text-center">
            <input type="text" name="position" class="form-control pro" for="<%= this.id %>" style="width: 45px" value="<%=this.position %>"/>
        </td>
        <td class="text-center"><input name="name" type="text" for="<%= this.id %>" class="form-control col-sm-8 pro" value="<%= this.name %>" /></td>
        <td class="text-center" for="<%= i %>">
            <select name="type" class="form-control pro" for="<%= this.id %>">
                <option value="0">Vui lòng chọn</option>
                <option value="SINGLE" <%= this.type=="SINGLE"?'selected':'' %> >Chọn một</option>
                <option value="SINGLE_OR_INPUT" <%= this.type=="SINGLE_OR_INPUT"?'selected':'' %> >Chọn và định nghĩa</option>
                <option value="MULTIPLE" <%= this.type=="MULTIPLE"?'selected':'' %> >Chọn nhiều</option>
                <option value="INPUT" <%= this.type=="INPUT"?'selected':'' %> >Tự định nghĩa</option>
            </select>                                 
        </td>
        <td class="text-center" for="<%= i %>">
            <select name="operator" class="form-control pro" for="<%= this.id %>">
                <option value="0">Chọn</option>
                <option value="EQ" <%= this.operator=="EQ"?'selected':'' %> >=</option>
                <option value="LTE" <%= this.operator=="LTE"?'selected':'' %> >≤</option>
                <option value="GTE" <%= this.operator=="GTE"?'selected':'' %> >≥</option>
            </select>                           
        </td>

        <td class="text-center">
            <label >
                <input style="text-align: center" class="pro" for="<%= this.id %>" name="filter" type="checkbox" value="<%= this.filter %>" <%= this.filter?"checked='checked'":'' %> >
            </label>
        </td>
        <td class="text-center">
            <button type="button" class="btn btn-xs btn-primary"  onclick="category.viewPropertyValue('<%= this.id %>')">Giá trị</button>
            <button type="button" class="btn btn-xs btn-danger" onclick="category.removeProperty('<%= this.id %>')">Xóa</button>
        </td>
        </tr>
        <% });%>
        </tbody>
    </table>  
</form>
