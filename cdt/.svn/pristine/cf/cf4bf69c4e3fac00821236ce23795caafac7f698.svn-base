<!-- template update row trong list News Category -->
<td style="width: 100px;"><p><b><%= id %></b></p></td>
<td>
    <p><%= name %></p>
</td>
<td style="width: 200px;">
    <p><%= description %></p>
</td>
<td style="width: 150px;">
   Danh mục: <% %>
</td>
<td style="width:250px;">
    <p><b>Kích hoạt:</b><%= active ? 'Đã active' : 'Chưa active' %></p>
    <p><b>Vị trí sắp xếp:</b> <%= order %></p>
</td>
<td style="width: 250px;">
    <p><b>Tạo: </b><span class="time"><%= timeCreate %></span></p>
    <p><b>Cập nhật: </b><span class="time"><%= timeUpdate %></span></p>
</td>
<td style="width: 150px">
    <div class="form-group">
        <button class="btn btn-primary form-control" onclick="categorynews.edit('<%= id %>')"><span class="glyphicon glyphicon-edit"></span> Sửa</button>
    </div>
    <div class="form-group">
        <button class="btn btn-danger form-control" onclick="categorynews.delCat('<%= id%>')"><span class="glyphicon glyphicon-trash"></span> Xóa</button>
    </div>
</td>