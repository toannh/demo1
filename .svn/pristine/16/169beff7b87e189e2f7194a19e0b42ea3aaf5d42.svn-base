<form class="form-horizontal" id="mapForm">
    <input type="text" id="manuId" style="display: none" />
    <input type="text" id="cateId" style="display: none" />
    <input type="text" id="cateName" style="display: none"/>
    <div class="form-group">
        <label class="control-label col-md-3">Danh mục</label>
        <div class="col-md-7" id="cateTree">
            <select class="form-control cateTree" for="0" onchange="manufacturer.selectCate(this,'map');">
                <option value="">Chọn danh mục</option>
                <% for(var i = 0; i < cate.length; i++){ %>
                <option value="<%= cate[i].id %>"><%= cate[i].name %></option>
                <% } %>
            </select>
        </div>
        <p style="clear:both"></p>
        <span class="control-label col-md-12" style="text-align: center; display:none" id="btnMap">
            <button onclick="manufacturer.addMap();" class="btn btn-primary" type="button">Map</button>
        </span>
    </div>

</form>
<div class="panel-heading">
    <h3 class="panel-title">Các danh mục đã được map</h3>
</div>
<div class="panel-content">
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px" id="mapList">
       
            <tr>
                <th style="text-align:center" >Danh mục</th>
                <th style="text-align:center" width="80px"></th>
            </tr>
            <% if(typeof(data) !== 'undefined' && data!=null && data.length>0) for(var i = 0; i < data.length; i++){ %>
            <tr for="<%= data[i].id %>"> 
                <td><%= data[i].name %></td>
                <td>
                    <button onclick="manufacturer.delMap('<%= data[i].id %>')"  class='btn btn-danger'>Xóa</button>
                </td>
            </tr>
            <% } %>

        <tbody></tbody>
    </table>
</div>

