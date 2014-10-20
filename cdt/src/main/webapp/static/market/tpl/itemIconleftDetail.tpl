<div class="grid">
    <div class="img"><a href="<%= baseUrl %>/san-pham/<%= data.id %>/<%= textUtils.createAlias(data.name) %>.html"><img src="<%=(typeof data.images != 'undefined' && data.images.length >0)?data.images[0]:staticUrl+'/market/images/no-image-product.png'%>" alt="<%=data.name%>"></a></div>
    <div class="g-content">
        <div class="g-row"><a class="g-title" href="<%= baseUrl %>/san-pham/<%= data.id %>/<%= textUtils.createAlias(data.name) %>.html"><%=data.name%></a></div>
        <% if(data.listingType=='BUYNOW' && textUtils.sellPrice(data.sellPrice,data.discount,data.discountPrice,data.discountPercent) != '0'){ %>
        <div class="g-row"><%=textUtils.sellPrice(data.sellPrice,data.discount,data.discountPrice,data.discountPercent)%><sup class="u-price">đ</sup></div>
        <% } %>
    </div>
</div><!--grid-->