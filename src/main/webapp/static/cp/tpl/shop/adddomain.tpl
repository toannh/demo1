<form class="form-horizontal" id="form-add-domain">
    <input type="hidden" name="userId" value="<%= (typeof data!=='undefined')? data.userId: ''%>"/>
    <div class="form-group" rel="url">
        <label class="control-label col-sm-2">Domain:</label>
        <div class="col-sm-10">
            <input name="url" value="<%= (typeof data!=='undefined')? data.url: ''%>" type="text" class="form-control" placeholder="Domain shop"/>
        </div>
    </div>
</form>
