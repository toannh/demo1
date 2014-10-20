<div class="form form-horizontal">
    <div class="form-group">
        <label class="control-label col-sm-2">Gửi đi:</label>
        <div class="col-sm-10">
            <textarea class="form-control" placeholder="Thông tin gửi đi"><%= (typeof data!=='undefined')? data.params.params: ''%></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Nhận:</label>
        <div class="col-sm-10">
            <textarea class="form-control" placeholder="Thông tin nhận lại"><%= (typeof data!=='undefined')? data.resp: ''%></textarea>
        </div>
    </div>
</div>