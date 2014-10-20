<form class="form-horizontal" id="adminForm">
    <input type="hidden" name="id" value="<%= (typeof(data) !== 'undefined')? data.id:'' %>"/>
    <div class="form-group">
        <label class="control-label col-sm-3">Email</label>
        <div class="col-sm-9">
            <input name="email" value="<%= (typeof(data) !== 'undefined')? data.email:'' %>" type="text" class="form-control" placeholder="Email"/>
        </div>
    </div>
    
    <div class="form-group">
        <label class="control-label col-sm-3">Mô tả</label>
        <div class="col-sm-9">
            <textarea name="description" rows="5" cols="45"><%= (typeof(data) !== 'undefined')? data.description:'' %></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">&nbsp;</label>
        <div class="col-sm-9">
            <label>
                <input type="checkbox"  id="active" name="active">&nbsp;&nbsp;
                Hoạt động
            </label>
        </div>
    </div>
</form>