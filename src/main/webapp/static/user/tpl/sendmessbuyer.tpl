<form id="form-message">
    <div class="form modal-form">
        <div class="form-group">
            <p class="form-control-static">Nhắn tin tới người mua: <a href="#"><%=data.buyerName%></a> về đơn hàng</p>
        </div>
        <div class="form-group">
            <label>Tiêu đề:</label>
        </div>
        <div class="form-group">
            <div class="input-group col-sm-12">
                <span class="input-group-addon">Nhắn người mua</span>
                <input name="subject" type="text" class="form-control" placeholder="Nhập tiêu đề">
            </div>
        </div>
        <input name="fromEmail" type="hidden" class="form-control" value="<%=data.buyerEmail%>">
        <input name="orderId" type="hidden" class="form-control" value="<%=data.id%>">
        <div class="form-group">
            <textarea name="content" rows="" class="form-control" placeholder="Nội dung cần nhắn"></textarea>
        </div>
    </div>
</form>