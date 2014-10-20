<form class="form-horizontal" id="add-note-shop" >
    <input type="hidden" name="userId" value="<%= (typeof data!=='undefined')? data.userId: ''%>" />
    <div class="form-group">
        <label class="control-label col-sm-4">Ghi chú</label>
        <div class="col-sm-8">
            <textarea name="note" class="form-control"><%= (typeof data!=='undefined')? data.note: ''%></textarea>
        </div>
    </div>
</form>