<% var fag = true; for(var i=0; i< items.length; i++){  if(items[i].id==data.itemId && fag){ fag = false;
%> 
<div class="pu-review-item">
    <div class="row">
        <div class="col-sm-6">
            <p><%=data.content%></p>


            <p>Sản phẩm: <span><%=items[i].name%></span> (<%=parseFloat(items[i].sellPrice).toMoney(0, ',', '.')%> <sup>đ</sup>)</p>

        </div>
        <div class="col-sm-2"><a target="_blank" href="<%= baseUrl %>/san-pham/<%=items[i].id %>/<%= textUtils.createAlias(items[i].name) %>.html">» Xem sản phẩm</a></div>
        <div class="col-sm-2"><p>Người bán: *****</p></div>
        <div class="col-sm-2"><p><%=textUtils.formatTime(data.createTime,'hour')%></p></div>
    </div><!-- row -->
</div><!-- pu-review-item -->
<% }} %>
