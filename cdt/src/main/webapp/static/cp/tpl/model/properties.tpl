<form class="form-horizontal" id="viewPropertys">
    <% 
    if(data.categoryProperties != null && data.categoryProperties != "") { 
    $.each(data.categoryProperties, function(){ 
    var cProperty = this.id;
    var name = this.name;
    %>

    <% if(this.type == 'SINGLE') { %>
    <div class="row">
        <div class="form-group">
            <label class="control-label col-sm-3"><%=name %></label>
            <div class="col-sm-9">
                <select class="form-control" for="<%= this.id %>" name="selectPro">
                    <option value="0" >- chọn <%= name %> - </option>
                    <%  $.each(data.categoryPropertyValues, function(){  %>
                    <% if(this.categoryPropertyId == cProperty) { %>
                    <option value="<%= this.id %>" ><%= this.name %></option>
                    <%  }});  %>
                </select>
            </div>
        </div>
    </div>
    <% } %>
    <% if(this.type == 'SINGLE_OR_INPUT') { %>
    <div class="row">
        <div class="form-group">
            <label class="control-label col-sm-3"><%= name %></label>
            <div class="col-sm-5">
                <select class="form-control" name="selectinput" for="<%= this.id %>">
                    <option value="0" >- chọn <%= this.name %> - </option>
                    <%  $.each(data.categoryPropertyValues, function(){  %>
                    <% if(this.categoryPropertyId == cProperty) { %>
                    <option value="<%= this.id %>"><%= this.name %></option>
                    <%  }});  %>
                </select>
            </div>
            <label class="control-label col-sm-1"> hoặc </label>
            <div class="col-sm-3">
                <input name="inputselect"  for="<%= this.id %>" type="text"  class="form-control" value=""  />
            </div>
        </div>
    </div>
    <% } %>
    <% if(this.type == 'MULTIPLE') { %>
    <div class="row" for="<%= this.id %>">
        <div class="form-group">
            <label class="control-label col-sm-3"><%= name %></label>
            <div class="col-sm-9">
                <%  $.each(data.categoryPropertyValues, function(){  %>
                <% if(this.categoryPropertyId == cProperty) { %>
                <label class="checkbox-inline col-sm-3">
                    <input type="checkbox" for="<%= this.id %>" names="<%=cProperty %>"> <%= this.name %>
                </label>
                <%  }});  %>
            </div>
        </div>
    </div>
    <% } %>
    <% if(this.type == 'INPUT') { %>
    <div class="row">
        <div class="form-group">
            <label class="control-label col-sm-3"><%= name %></label>
            <div class="col-sm-9">
                <input type="text" for="<%= this.id %>" value="" class="form-control" />
            </div>
        </div>
    </div>
    <% } %>

    <% }); } %>
</form>