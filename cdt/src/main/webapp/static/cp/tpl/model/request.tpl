<form class="form-horizontal" id="request-model" >
    <input type="hidden" name="id" value="<%= data.id %>" />
    <div class="form-group">
        <label class="control-label col-sm-4">Người yêu cầu</label>
        <div class="col-sm-8">
            <input type="text" name="asignedEmail" class="form-control" placeholder="Địa chỉ email người yêu cầu chỉnh sửa" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Ghi chú</label>
        <div class="col-sm-8">
            <textarea name="message" class="form-control" ></textarea>
        </div>
    </div>
</form>