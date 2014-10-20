                <% var fag=false; if(datas.data!=null && datas.data.categoryManufacturerHomes!=null){
                     var fag = true;
                }
                %>
<form id="form-edit" class="form-horizontal addLogo" role="form">
    <% for(var i=0; i<number; i++){ %>
    <div class="form-group">
        <label class="control-label col-sm-3">Mã thương hiệu <%=eval(i+1)%></label>
        <div class="col-sm-5">
            <input name="manufacturerId<%=eval(i+1)%>" value="<%if(fag==true){%><%=datas.data.categoryManufacturerHomes[i].manufacturerId%><% } %>" type="text" class="form-control" placeholder="Id thương hiệu <%=eval(i+1)%>" />
        </div>
    </div>
    <% } %>
</form>

