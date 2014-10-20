<form class="form-horizontal" id="landing-add-form">
    <div class="row">
        <div class="col-xs-5">
            <input name="itemId" type="text" class="form-control"  placeholder="Id sản phẩm">
        </div>
        <div class="col-xs-2">
            <button type="button" class="btn btn-primary" onclick="landing.saveItem('<%= cateId %>')" >
                <i class="glyphicon glyphicon-edit" ></i> Thêm mới
            </button>
        </div>
    </div>
    <div class="row">
        <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
            <thead>
                <tr class="success" >
                    <th style="text-align: center; vertical-align: middle;" >ID</th>
                    <th style="text-align: center; vertical-align: middle;" >Tên sản phẩm</th>
                    <th style="text-align: center; vertical-align: middle;" >Nổi bật</th>
                    <th style="text-align: center; vertical-align: middle;" >Chức năng</th>
                </tr>
            </thead>

            <tbody class="body" >
                <% if(data == null || data.length <= 0) { %>
                <tr><td class="text-center" colspan="6"><p style="color: red ;" > Chưa có sản phẩm nào! </p></td></tr>
                <% } else { %>
                <% $.each(data , function(){ %>
                <tr for="<%= this.id%>">
                    <% if(typeof this.item == 'undefined' || this.item == null) { %>
                        <td colspan="2" style="color: red">Sản phẩm chính đã bị xóa.</td>
                    <% } else { %>
                        <td class="text-center" style="vertical-align: middle;"><%= this.item.id %></td>
                        <td class="text-center" style="vertical-align: middle;"><%= this.item.name %></td>
                    <% }%>
                    <td class="text-center" style="vertical-align: middle;"><input name="special"  onclick="landing.changeCategoryItemSpecial(this, '<%= this.id%>');" for='<%= this.id+"_special" %>' type="checkbox" <%=this.special?'checked="true"':''%> /></td>
                    <td class="text-center" style="vertical-align: middle">
                        <button type="button" class="btn btn-danger" onclick="landing.deleteitem('<%= this.id %>');"  ><i class="glyphicon glyphicon-trash"></i> Xóa</button>
                    </td>
                </tr>
                <% }); } %>
            </tbody>

        </table>
    </div>
</form>