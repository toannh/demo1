<form class="form-horizontal" id="item-edit-shipfee">
    <input type="hidden" name="id" value="<%= (typeof(data.id) != 'undefined')?data.id:'' %>" />

    <div class="row">
        <label>
            <input type="radio" class="submit-pay-choose" value="AGREEMENT" name="shipmentType">&nbsp;&nbsp; Không rõ phí
        </label>
    </div>
    <div class="row">
        <label>
            <input type="radio" class="submit-pay-choose" value="FIXED" name="shipmentType" id="fixed">
            &nbsp;&nbsp; Phí vận chuyển toàn quốc là : </label>
        <input type="text" class="text numberField" name="shipmentPrice" value="0"><label>&nbsp;&nbsp;VNĐ</label>
    </div>
    <div class="row">
        <label>
            <input type="radio" class="submit-pay-choose" value="FIXED" name="shipmentType" id="free">&nbsp;&nbsp; Miễn phí
        </label>
    </div>
    <div class="row">
        <label>
            <input type="radio" class="submit-pay-choose" value="BYWEIGHT" name="shipmentType">&nbsp;&nbsp; Phí vận chuyển tính theo bảng giá shipchung
        </label>
    </div>
</form>