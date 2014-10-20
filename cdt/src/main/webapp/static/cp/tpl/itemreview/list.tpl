
<table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
    <tr class="success">
        <th class="text-center">Mã khách</th>
        <th class="text-left">Tên</th>
        <th class="text-left">IP</th>
        <th class="text-left">Thời gian</th>
    </tr>
    <% if(data.length>0){ for(var i=0; i< data.length ; i++){ 
        %>
    <tr>
        <td class="text-center"><%=data[i].userId%></td>
        <td class="text-left"><%=data[i].userName%></td>
        <td class="text-left"><%=data[i].ip%></td>
        <td class="text-left"><%=textUtils.formatTime(data[i].createTime,'hour')%></td>
    </tr>  
  <% }} else{ %>
  <tr><td class="text-center" colspan="4" style="color: red">Không tồn tại người Like nào</td></tr>
  <%}%>
</table>
