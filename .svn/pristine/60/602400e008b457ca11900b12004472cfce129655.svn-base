
<h4>Re: <%=data.subject%></h4>
<hr>
<div class="messenger-header clearfix">
    <div class="col-sm-6 reset-padding">
        <div class="img-avatar-user pull-left">
            <% if(data.fromImage!=null && data.fromImage!='') { %>
            <img src="<%=data.fromImage%>">
            <% }else{ %>
            <img src="<%=staticUrl%>/user/images/data/avatar-user.png">
            <% } %>
        </div>
        <p>Người gửi: <strong><%=data.fromName%></strong> (<%=data.fromEmail%>)</p>
        <p>Thời gian gửi: <%=textUtils.formatTime(data.createTime,'hour')%></p>
    </div>

</div>
<div class="messenger-detail mgt-25">
    <div class="row">
        <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
            <%=data.content%>
        </div>
        <% if(data.itemId!=null && data.itemId!=''){ %>
        <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 reset-padding">
            <div class="border-product-relations">
                <h4>Nội dung thư liên quan tới sản phẩm: </h4>
                <div class="box-buy-product">
                    <span class="img-buy-product pull-left">
                        <% if(data.item.images.length>0) { %>
                        <img src="<%=data.item.images[0]%>" class="img-responsive" />
                        <% }else{ %>
                        <img src="<%=staticUrl%>/user/images/data/avatar-user.png" class="img-responsive" />
                        <% } %>

                    </span>
                    <div class="desc-product">
                        <a target="_blank" href="<%= baseUrl %>/san-pham/<%=data.item.id %>/<%= textUtils.createAlias(data.item.name) %>.html" title="<%=data.item.name%>"><%=data.item.name%></a>
                        <p>Mã SP: <%=data.item.id%></p>
                        <p>Người bán: <%=data.item.sellerName%></p>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <form id="entry-Message">
                <div class="media mgt-25">
                    <div class="img-avatar-user pull-left">
                        <% if(data.toImage!=null && data.toImage!='') { %>
                        <img src="<%=data.toImage%>"  class="media-object"/>
                        <% }else{ %>
                        <img src="<%=staticUrl%>/user/images/data/avatar-user.png" class="media-object" />
                        <% } %>
                    </div>
                    <input type="hidden" name="subject" class="form-control" value="<%=data.subject%>">   
                    <input type="hidden" name="toEmail" class="form-control" value="<%=data.fromEmail%>">   
                    <input type="hidden" name="itemId" class="form-control" value="<%=data.itemId%>">   
                    <div class="media-body">
                        <div class="form-group">
                            <textarea class="form-control" name="content" rows="5" placeholder="Nhấp vào đây để trả lời hoạc chuyển tiếp"></textarea>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row mgt-25">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-right"><button type="submit" class="btn btn-danger btn-lg" onclick="message.reply()">Gửi đi</button></div>
    </div>
</div>

