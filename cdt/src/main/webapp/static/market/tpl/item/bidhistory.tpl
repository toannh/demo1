<div class="bid-pop-title">Người tham gia: <b><%=userCount%></b>, Lượt đấu: <b><%=bidHistory.length%></b></div>
<div class="bid-table">
    <table class="table">
        <thead>
            <tr>
                <th>TT</th>
                <th>Người tham gia</th>
                <th>Giá đấu</th>
                <th>Thời gian đấu</th>
            </tr>
        </thead>
        <tbody>
            <%
            for(var i=0; i < bidHistory.length; i++){
                for(var j=0; j < bider.length; j++){
                 if(bidHistory[i].biderId == bider[j].id){
            %>
            <tr>
                <td><%=(i+1)%></td>
                <td>
                    <span class="text-primary">
                        <%
                        var name = "";
                        if(bider[j].username!= null && bider[j].username != ""){
                              name = bider[j].username;
                        }else if(bider[j].name!= null && bider[j].name != ""){
                              name = bider[j].name;
                        }else if(bider[j].email!= null && bider[j].email != ""){
                              name = bider[j].email;
                        }
                        %>
                        <%= name.substring(0, 1)+ '****'+ name.substring(name.length-1, name.length) %>
                    </span>
                    <% if(bidHistory[i].autoBiding){
                    %>
                    &nbsp;&nbsp;<span class="icon-autobid"></span>
                    <%}%>
                </td>
                <td><%=parseFloat(bidHistory[i].bid).toMoney(0, ',', '.')%> đ</td>
                <td><%=textUtils.formatTime(bidHistory[i].time,'hourfirst')%></td>
            </tr>
            <%
            break;
            }}}
            %>
        </tbody>
    </table>
</div>
<div class="bid-pop-bottom">Nếu 2 người cùng đặt một mức giá, người đặt giá đầu tiên sẽ được ưu tiên đặt giá thành công.</div>