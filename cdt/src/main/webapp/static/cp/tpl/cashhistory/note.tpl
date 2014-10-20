<div>
    <p>Email : <b style="color: #006cff"><%= data.email %></b></p>
    <p>Số lần bị phạt hiện tại : <b style="color: red"> <%= data.fine %></b></p>
</div>

<form class="form-horizontal" id="add-note" >
    <input type="hidden" name="id" value="" />
    <div class="form-group" name="dnote">
        <label class="control-label col-sm-2">Ghi chú</label>
        <div class="col-sm-10">
            <textarea name="note" class="form-control"></textarea>
            <span name="note_2" style="color:red; margin-top: 10px;"></span>
        </div>
    </div>
</form>