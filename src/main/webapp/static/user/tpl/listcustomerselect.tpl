
<table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
    <tr class="success">
        <th class="text-center">Mã</th>
        <th class="text-left">Phone</th>
        <th class="text-left">Email</th>

    </tr>  
    <% if(resp.data!=null){ for(var i = 0; i < resp.data.length; i++){ %>
   
    <tr>
        <td class="text-center"><%= resp.data[i].id %></td>
        <td class="text-left"><%= resp.data[i].phone %></td>
        <td class="text-left"><%= resp.data[i].email %></td>
    </tr>   
    <% }} %>


</table>
