<div class="row">
    <% if(data.images != null && data.images != "" && data.images.length > 0) { $.each(data.images, function(){ %>
    <div class="col-xs-6 col-md-3">
        <a class="thumbnail">
            <img style="height: 100px;" src="<%=this %>" />
        </a>
    </div>
    <% }); } else { %>
    <p class="alert alert-danger text-center" >Sản phẩm chưa có ảnh</p>
    <% } %>
</div>