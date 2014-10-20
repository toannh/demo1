<div class="row">
    <table class="datatables table table-striped table-bordered  table-responsive" >
        <thead>
            <tr class="success" >
                <th style="text-align: center; vertical-align: middle;" >Quận / huyện</th>
                <th style="text-align: center; vertical-align: middle;width: 70px">Thứ tự</th>
                <th style="text-align: center; vertical-align: middle;width: 70px" >Mã ship chung</th>
                <th style="text-align: center; vertical-align: middle" >
                    <button onclick="city.addDistrict('<%= cityId %>')" class="btn btn-success" ><i class="glyphicon glyphicon-plus"></i> Thêm </button>
                </th>
            </tr>
        </thead>
        <% $.each(data, function(){ %>
        <tr>
            <td style=" text-align: center;vertical-align: middle; min-width: 200px">
                <input name="name_<%= this.id %>" type="text"  value="<%= this.name %> " class="form-control" style="text-align: center" />
            </td>
            <td class="text-center" style="vertical-align: middle;" >
                <input name="position_<%= this.id %>" type="text" value="<%= this.position %> " class="form-control" style="text-align: center" />
            </td>
            <td style="text-align: center; vertical-align: middle">
                <input name="scId_<%= this.id %>" type="text" value="<%= this.scId %> " class="form-control" style="text-align: center" />
            </td>
            <td style="vertical-align: middle; text-align: center;">
                <button type="button" class="btn btn-success" onclick="city.editDistrict('<%= this.id %>', '<%= cityId %>')" >
                    <span class="glyphicon glyphicon-edit"></span> Sửa</button>
                <button type="button" class="btn btn-danger" onclick="city.delDistrict('<%= this.id %>', '<%= cityId %>')" >
                    <span class="glyphicon glyphicon-trash"></span> Xóa</button>
            </td>
        </tr>
        <% });  %>
    </table>
</div>
