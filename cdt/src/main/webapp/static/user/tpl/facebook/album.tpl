<form class="form-horizontal" style="height: 600px; overflow-y: auto; overflow-x: hidden" >
    <div class="form-group">
        <label class="control-label col-sm-3">Danh sách album</label>
        <div class="col-sm-8">
            <select name="albumfacebook" onchange="additemquick.getAlbum(this)" class="form-control" >
                <option value="0" >Chọn album</option>
                <% $.each(data, function(){ %>
                <option value="<%= this.id %>" ><%= this.name %></option>
                <% }); %>
            </select>
        </div>
    </div>
    <div class="row" data-rel="album-detail" ></div>
</form>