<form class="form-horizontal" id="aliasForm">
    <input type="hidden" name="id" value="<%= (typeof(data) !== 'undefined')? data.id:'' %>"/>
    <div class="form-group">
        <label class="control-label col-sm-3">Title</label>
        <div class="col-sm-9">
            <input name="title" value="<%= (typeof(data) !== 'undefined')? data.title:'' %>" type="text" class="form-control" placeholder="Title"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Subtitle</label>
        <div class="col-sm-9">
            <input name="subTitle" value="<%= (typeof(data) !== 'undefined')? data.subTitle:'' %>" type="text" class="form-control" placeholder="Subtitle"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Danh mục</label>
        <div class="col-sm-9">
            <select name="categoryId" class="form-control">
                <option label="-- Chọn danh mục --" />
                <% if(categories!=null && (typeof(categories) !== 'undefined')){ for(i=0;i < categories.length;i++){%>
                    <option value="<%=categories[i].id%>" <% if(typeof(data) !== 'undefined' && categories[i].id==data.categoryId){%>selected="true"<%}%> label="<%=categories[i].name%>" />
                <%}}%>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3">Banner 1</label>
        <div class="col-sm-4">
            <input name="bannerUrl" value="<%= (typeof(data) !== 'undefined')? data.bannerUrl:'' %>" type="text" class="form-control" placeholder="Url banner"/>
        </div>
        <div class="col-sm-5">
            <div class="input-group">
                <input type="text" class="form-control" id="photoCover">
                <a onclick="$('#lefile').click();" class="btn btn-default input-group-addon">Chọn file</a>                    
            </div>
            <input type="file" style="display:none" id="lefile" name="image">
        </div>
    </div>
    <%if(typeof(data) !== 'undefined' && data.image!=null && data.image!='undefined' && data.image!=''){
    %>
    <div class="form-group">
        <label class="control-label col-sm-3">&nbsp;</label>
        <div class="col-sm-9">
            <img src="<%= data.image%>" />
        </div>
    </div>
<%}%>
    <div class="form-group">
        <label class="control-label col-sm-3">Banner 2</label>
        <div class="col-sm-4">
            <input name="bannerUrl1" value="<%= (typeof(data) !== 'undefined')? data.bannerUrl1:'' %>" type="text" class="form-control" placeholder="Url banner"/>
        </div>
        <div class="col-sm-5">
            <div class="input-group">
                <input type="text" class="form-control" id="photoCover1">
                <a onclick="$('#lefile1').click();" class="btn btn-default input-group-addon">Chọn file</a>                    
            </div>
            <input type="file" style="display:none" id="lefile1" name="image1">
        </div>
    </div>
    <%if(typeof(data) !== 'undefined' && data.image1!=null && data.image1!='undefined' && data.image1!=''){
    %>
    <div class="form-group">
        <label class="control-label col-sm-3">&nbsp;</label>
        <div class="col-sm-9">
            <img src="<%= data.image1%>" />
        </div>
    </div>
<%}%>
    <div class="form-group">
        <label class="control-label col-sm-3">Banner 3</label>
        <div class="col-sm-4">
            <input name="bannerUrl2" value="<%= (typeof(data) !== 'undefined')? data.bannerUrl2:'' %>" type="text" class="form-control" placeholder="Url banner"/>
        </div>
        <div class="col-sm-5">
            <div class="input-group">
                <input type="text" class="form-control" id="photoCover2">
                <a onclick="$('#lefile2').click();" class="btn btn-default input-group-addon">Chọn file</a>                    
            </div>
            <input type="file" style="display:none" id="lefile2" name="image2">
        </div>
    </div>
    <%if(typeof(data) !== 'undefined' && data.image2!=null && data.image2!='undefined' && data.image2!=''){
    %>
    <div class="form-group">
        <label class="control-label col-sm-3">&nbsp;</label>
        <div class="col-sm-9">
            <img src="<%= data.image2%>" />
        </div>
    </div>
<%}%>
    <div class="form-group">
        <label class="control-label col-sm-3">Banner 4</label>
        <div class="col-sm-4">
            <input name="bannerUrl3" value="<%= (typeof(data) !== 'undefined')? data.bannerUrl3:'' %>" type="text" class="form-control" placeholder="Url banner"/>
        </div>
        <div class="col-sm-5">
            <div class="input-group">
                <input type="text" class="form-control" id="photoCover3">
                <a onclick="$('#lefile3').click();" class="btn btn-default input-group-addon">Chọn file</a>                    
            </div>
            <input type="file" style="display:none" id="lefile3" name="image3">
        </div>
    </div>
    <%if(typeof(data) !== 'undefined' && data.image3!=null && data.image3!='undefined' && data.image3!=''){
    %>
    <div class="form-group">
        <label class="control-label col-sm-3">&nbsp;</label>
        <div class="col-sm-9">
            <img src="<%= data.image3%>" />
        </div>
    </div>
<%}%>
    <div class="form-group">
        <label class="control-label col-sm-3">&nbsp;</label>
        <div class="col-sm-9">
            <label>
                <input type="checkbox"  id="active" name="active" <%if(typeof(data) !== 'undefined' && data.active==true){%>checked="checked"<%}%>>&nbsp;&nbsp;
                Hoạt động
            </label>
        </div>
    </div>
</form>