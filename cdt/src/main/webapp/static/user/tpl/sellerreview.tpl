<form id="form-review">
    <p><b>Mã đơn hàng:</b> <a href="<%=baseUrl%>/<%=data.id%>/chi-tiet-don-hang.html" target="_blank"><%=data.id%></a>  <b><% if(data.scId!=null && data.scId!=''){ %>| Mã vận đơn: </b><a href="http://mc.shipchung.vn/van-don.html?sc_code=<%=data.scId%>"><%=data.scId%></a><%}%></p>
    <p>Đánh giá uy tín giúp xây dựng cộng đồng mua bán và môi trường giao dịch an toàn, lành mạnh.</p>
    <div class="form">
        <div class="form-group row">
            <input name="orderId" value="<%=data.id%>" type="hidden" />
            <div class="col-sm-4">
                <div class="checkbox">
                    <label><input name="point" type="radio"  value="2" <% if(data.sellerReview!=null){ %><%= (data.sellerReview.point==2)? 'checked': ''%><%}%> > Tốt</label>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="checkbox">
                    <label><input name="point" type="radio" value="1"  <% if(data.sellerReview!=null){ %><%= (data.sellerReview.point==1)? 'checked': ''%><%}%> > Bình thường</label>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="checkbox">
                    <label><input name="point" type="radio" value="0"  <% if(data.sellerReview!=null){ %><%= (data.sellerReview.point==0)? 'checked': ''%><%}%>  > Không hài lòng</label>
                </div>
            </div>
        </div><!-- checkbox -->
        <div class="form-group">
            <p class="form-control-static">Cụ thể đánh giá/ góp ý:</p>
        </div>
        <div class="form-group">
            <textarea name="content" rows="" class="form-control"><% if(data.sellerReview!=null){ %><%=data.sellerReview.content%><%}%></textarea>
        </div>
    </div><!-- end form -->
</form>