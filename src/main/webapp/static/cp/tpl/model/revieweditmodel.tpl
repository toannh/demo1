<form class="form-horizontal" id="review-model" >
    <div class="form-group">
        <label class="control-label col-sm-4">Trạng thái</label>
        <div class="col-sm-8" id="noReview">
            <label class="control-label col-sm-3"><input name="status" type="radio" value="true" />Duyệt</label>
            <label class="control-label col-sm-6" style="margin-bottom: 10px"><input name="status" type="radio" value="false" />Yêu cầu sửa lại</label>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Ghi chú</label>
        <div class="col-sm-8">
            <textarea name="message" class="form-control" placeholder="Nhập ghi chú duyệt" ></textarea>
        </div>

    </div>

</form>