<% var fag=false; if(datas.data!=null && datas.data.categoryManufacturerHomes!=null){
var fag = true;
}
%>
<form id="form-edit" class="form-horizontal addLogo" role="form">
    <% for(var i=0; i<number; i++){ %>
        <div class="form-group">
            <div class="ccc">
                <label class="control-label col-sm-3">Mã thương hiệu <%=eval(i+1)%></label>
                <div class="col-sm-3">
                    <input name="manufacturerId<%=eval(i+1)%>" value="<%if(fag==true){%><%=datas.data.categoryManufacturerHomes[i].manufacturerId%><% } %>" type="text" class="form-control" placeholder="Id thương hiệu <%=eval(i+1)%>" />
                </div>
            </div>
            <div class="cc">
                <label class="control-label col-sm-2">Model</label>
                <div class="col-sm-4">
                    <input name="modelIds<%=eval(i+1)%>" value="<%if(fag==true){%><%=datas.data.categoryManufacturerHomes[i].modelIds%><% } %>" type="text" class="form-control" placeholder="ID model cách nhau bởi dấu ," />
                </div>
            </div>
        </div>

        <% } %>
</form>
