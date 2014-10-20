<form class="form-horizontal" id="rolesForm">
    <% for(i=0; i< func.length; i++) { %>
    <div class="row" >
        <h4 class="alert alert-success" style="padding: 5px 10px;" > 
            <input class="role" onclick="administrator.checkAll('<%= i %>',this)" type="checkbox" for="<%= func[i].refUri %>" value="<%= func[i].uri %>" > <%= func[i].name %>
        </h4>
    </div>
    <div class="row" >
        <% for(j=0; j< services.length; j++) { %>
        <% if(services[j].refUri == func[i].refUri){ %>
        <% var subclass='class= "sub_'+i+' role"' %>
        <div class="col-sm-4">
            <label class="checkbox-inline">
                <input type="checkbox" name="cbxRole" value="<%= services[j].uri %>" for="<%= services[j].refUri %>" <%= subclass %> /> <%= services[j].name %>
            </label>
        </div>
        <% } }%>
    </div>
    <% } %>
</form>