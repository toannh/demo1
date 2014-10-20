<form name="categoryFrom" class="form-horizontal" id="lcategory-form-edit">
    <input type="text" style="display: none" name="id" value="<%= (typeof data!=='undefined')? data.id: ''%>" />            
    <div class="form-group">
        <label class="control-label col-sm-4">Tên landing:</label>
        <div class="col-sm-8">
            <input name="name" value="<%= (typeof data!=='undefined')? data.name: ''%>" type="text" class="form-control" placeholder="Tên landing"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Màu sắc:</label>
        <div class="col-sm-8">
            <input name="color" id="colorpicker" value="<%= (typeof data!=='undefined')? data.color: ''%>" class="form-control"  />
        </div>
    </div>
    
    <div class="form-group">
        <label class="control-label col-sm-4">Ảnh logo</label>
        <div class="col-sm-8">
            <div class="input-group">
                <input type="text" class="form-control" id="photoCover" value="<%= (typeof data!=='undefined')? data.logo: ''%>">
                <a onclick="$('#lefile').click();" class="btn btn-default input-group-addon">Chọn file</a>                    
            </div>
            <input type="file" style="display:none" id="lefile" name="logo">
        </div>
    </div>
    <%if(typeof(data) !== 'undefined' && data.logo!=null && data.logo!='undefined' && data.logo!=''){
    %>
    <div class="form-group">
        <label class="control-label col-sm-5">&nbsp;</label>
        <div class="col-sm-7">
            <img width="100" src="<%= data.logo%>" />
        </div>
    </div>
<%}%>
    <div class="form-group">
        <label class="control-label col-sm-4">Ảnh nền</label>
        <div class="col-sm-8">
            <div class="input-group">
                <input type="text" class="form-control" id="photoCover1" value="<%= (typeof data!=='undefined')? data.background: ''%>">
                <a onclick="$('#lefile1').click();" class="btn btn-default input-group-addon">Chọn file</a>                    
            </div>
            <input type="file" style="display:none" id="lefile1" name="background">
        </div>
    </div>
    <%if(typeof(data) !== 'undefined' && data.background!=null && data.background!='undefined' && data.background!=''){
    %>
    <div class="form-group">
        <label class="control-label col-sm-5">&nbsp;</label>
        <div class="col-sm-7">
            <img width="200" src="<%= data.background%>" />
        </div>
    </div>
<%}%>
</form>