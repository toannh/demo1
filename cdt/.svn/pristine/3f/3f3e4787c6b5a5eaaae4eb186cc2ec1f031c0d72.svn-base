
    <form name="mapform" class="form-horizontal form-block" id="map-form" style="margin-top: -25px; margin-bottom: 10px; border: 1px solid #E9E9E9;"> 
        <input type="text" name="idCategorys" style="display: none">
        <div class="form-group" >       
            <div class="col-sm-12" style="margin-top: 10px;">
                <label class="control-label col-sm-4" style="text-align: left;">Id thương hiệu:</label>
                <div class="col-sm-5">
                    <input name="manufacturerId" type="text" class="form-control" placeholder="Id thương hiệu"/>
                </div>
                <label class="control-label col-sm-4">Tên thương hiệu:</label>
                <div class="col-sm-5">
                    <input name="manufacturerName" style="margin-top: 5px;" type="text" class="form-control" placeholder="Tên thương hiệu"/>
                </div>
            </div>
            <input type="hidden" name="pageIndex" value="" ><input type="hidden" name="pageSize" value="10">
            <div class="col-sm-9 col-sm-offset-3 buttonmap" style="margin:17px">
                <button type="button" class="btn btn-success" onclick="category.filterManufacturer('<%= id %>',1);" style="padding: 8px 61px;">Lọc</button>
                <button type="button" class="btn btn-primary" onclick="category.addMapForm();" style="padding: 8px 20px;">Map thêm thương hiệu</button>
            </div>            
        </div>        
    </form>    
  
<div class="clearfix"></div>
<div class="btn-toolbar pull-right clearfix paging">
    <div class="pull-left mgr-top-10" style="margin: 10px 10px;">
        Tìm thấy <strong style="color: red" class="count-manu" data="<%= data.dataCount%>"><%= data.dataCount%></strong> thương hiệu đã được Map<br/>
    </div>
    <% if(data.pageCount && data.pageCount > 0){%>
    <div class="btn-group" >
        <% if(data.pageIndex > 3){ %><a class="btn btn-default" page="1"><<</a> <% } %>
        <% if(data.pageIndex > 2){ %><a  class="btn btn-default" page="<%= data.pageIndex %>"><</a><% }%>
        <% if(data.pageIndex > 3){%> <a class="btn btn-default">...</a> <%}%>
        <% if(data.pageIndex >= 3){%> <a class="btn btn-default" page="<%= data.pageIndex-2 %>"><%= data.pageIndex-2 %></a> <%}%>   
        <% if(data.pageIndex >= 2){%> <a class="btn btn-default" page="<%= data.pageIndex-1 %>"><%= data.pageIndex-1 %></a> <%}%>           
        <% if(data.pageIndex >= 1){%> <a class="btn btn-default" page="<%= data.pageIndex %>"><%= data.pageIndex %></a> <%}%>           
        <a class="btn btn-primary"><%= data.pageIndex + 1 %></a>
        <% if(data.pageCount - data.pageIndex > 2){%> <a class="btn btn-default" page="<%= data.pageIndex+2 %>"><%= data.pageIndex+2 %></a> <%}%> 
        <% if(data.pageCount - data.pageIndex > 3){%> <a class="btn btn-default" page="<%= data.pageIndex+3 %>"><%= data.pageIndex+3 %></a> <%}%> 
        <% if(data.pageCount - data.pageIndex > 4){%> <a class="btn btn-default">...</a></a> <%}%> 
        <% if(data.pageCount - data.pageIndex > 2){%> <a class="btn btn-default" page="<%= data.pageIndex+2 %>">></a> <%}%> 
        <% if(data.pageCount - data.pageIndex > 2){%> <a class="btn btn-default" page="<%= data.pageCount %>">>></a> <%}%>     
    </div>    
    <%}%>
   
</div>
<div class="clearfix" style="margin: 10px 0"></div>
<table id="view-map-manu" class="table table-striped table-bordered table-responsive">
    <thead>
        <tr class="success">
            <th class="text-center">ID</th>
            <th class="text-center">Tên Thương hiệu</th>
            <th class="text-center" style="width: 150px;">Thao tác</th>
        </tr>
    </thead>
    <tbody>
        <% if(data.data.length > 0){ %>
        <% $.each(data.data ,function(){ %>
        <tr for="<%= this.id %>" >
            <td class="text-center"><%= this.id %></td>
            <td class="text-center"><%= this.name %></td>
            <td class="text-center">
                <button style="width: 100px;" class="btn btn-danger" onclick="category.delmanu('<%= id %>', '<%= this.id %>');">Xóa</button>
            </td>
        </tr>
        <%});%>    
        <% }else{ %>
        <tr>
            <td colspan="3">
                <div class="nodata" style="color:red;text-align: center">Hiện tại chưa có thương hiệu nào được Map với danh mục này</div>
            </td>
        </tr>
        <% } %>
    </tbody>
</table>
<div class="btn-toolbar pull-right paging" >
    <div class="pull-left mgr-top-10" style="margin: 10px 10px;">
        Tìm thấy <strong style="color: red" class="count-manu" data="<%= data.dataCount%>"><%= data.dataCount%></strong> thương hiệu đã được Map<br/>
    </div>
    <% if(data.pageCount && data.pageCount > 0){%>
    <div class="btn-group">
        <% if(data.pageIndex > 3){ %><a class="btn btn-default" page="1"><<</a> <% } %>
        <% if(data.pageIndex > 2){ %><a  class="btn btn-default" page="<%= data.pageIndex %>"><</a><% }%>
        <% if(data.pageIndex > 3){%> <a class="btn btn-default">...</a> <%}%>
        <% if(data.pageIndex >= 3){%> <a class="btn btn-default" page="<%= data.pageIndex-2 %>"><%= data.pageIndex-2 %></a> <%}%>   
        <% if(data.pageIndex >= 2){%> <a class="btn btn-default" page="<%= data.pageIndex-1 %>"><%= data.pageIndex-1 %></a> <%}%>           
        <% if(data.pageIndex >= 1){%> <a class="btn btn-default" page="<%= data.pageIndex %>"><%= data.pageIndex %></a> <%}%>           
        <a class="btn btn-primary"><%= data.pageIndex + 1 %></a>
        <% if(data.pageCount - data.pageIndex > 2){%> <a class="btn btn-default" page="<%= data.pageIndex+2 %>"><%= data.pageIndex+2 %></a> <%}%> 
        <% if(data.pageCount - data.pageIndex > 3){%> <a class="btn btn-default" page="<%= data.pageIndex+3 %>"><%= data.pageIndex+3 %></a> <%}%> 
        <% if(data.pageCount - data.pageIndex > 4){%> <a class="btn btn-default">...</a></a> <%}%> 
        <% if(data.pageCount - data.pageIndex > 2){%> <a class="btn btn-default" page="<%= data.pageIndex+2 %>">></a> <%}%> 
        <% if(data.pageCount - data.pageIndex > 2){%> <a class="btn btn-default" page="<%= data.pageCount %>">>></a> <%}%>     
    </div>    
    <%}%>
</div>