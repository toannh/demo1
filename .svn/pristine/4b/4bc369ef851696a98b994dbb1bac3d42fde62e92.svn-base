
<form class="form-horizontal" id="form-add">
    <input type="hidden" name="id" value="<%= (typeof data!=='undefined')? data.id: ''%>"/>
    <div class="form-group">
        <label class="control-label col-sm-3">Tiêu đề:</label>
        <div class="col-sm-9">
            <input name="title" value="<%= (typeof data!=='undefined')? data.title: ''%>" type="text" class="form-control" placeholder="Tiêu đề"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Danh mục:</label>
        <div class="col-sm-9"> 
            <select class="form-control" name="type">
                <option value="0">---Chọn---</option>
                <option value="1" <% if(typeof data!=='undefined'){ if(data.type==1){%> selected="true"<%}}%>>Câu chuyện thành công</option>
                <option value="2" <% if(typeof data!=='undefined'){ if(data.type==2){%> selected="true"<%}}%>>Nhận xét của khách hàng</option>
            </select>  
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Họ tên</label>
        <div class="col-sm-9">
            <input name="name" value="<%= (typeof data!=='undefined')? data.name: ''%>" type="text" class="form-control" placeholder="Nhập họ tên"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Tên Shop</label>
        <div class="col-sm-9">
            <input name="nameShop" value="<%= (typeof data!=='undefined')? data.nameShop: ''%>" type="text" class="form-control" placeholder="Nhập tên shop"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Tên công ty</label>
        <div class="col-sm-9">
            <input name="nameCompany" value="<%= (typeof data!=='undefined')? data.nameCompany: ''%>" type="text" class="form-control" placeholder="Nhập tên công ty"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Link dẫn</label>
        <div class="col-sm-9">
            <input name="url" value="<%= (typeof data!=='undefined')? data.url: ''%>" type="text" class="form-control" placeholder="Nhập đường link"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3">Ảnh:</label>
        <div class="col-sm-9">
            <input type="file" style="display:none" id="lefile" name="image">
            <div style="width:300px;" class="input-group">
                <input type="text" class="form-control" id="photoCover">
                <a onclick="$('#lefile').click();" class="btn btn-default input-group-addon">Chọn</a>                    
            </div>
        </div>           
    </div>
    <% if(typeof data!=='undefined' && data.image !== null) {%>
        <div class="form-group">
            <label class="control-label col-sm-3"> </label>
            <div class="col-sm-9">
                <img src="<%= (typeof data!=='undefined')? data.image: ''%>" width="100" alt="Ảnh"/>
            </div>     
        </div>
        <%}%>
    <div class="form-group">
        <label class="control-label col-sm-3">Nội dung:</label>
        <div class="col-sm-9">
            <textarea name="content" id="txt_content" class="form-control" placeholder="Nội dung"><%= (typeof data!=='undefined')? data.content: ''%></textarea>                
        </div>
    </div>
   
    <div class="form-group" style="margin-bottom: 40px;">
        <label class="control-label col-sm-3" style="padding-left: 0">Trạng thái:</label>
        <div class="col-sm-9">
            <label>
                <input type="checkbox"  id="active" <% if(typeof data!=='undefined'){ if(data.active==true){%>checked="true"<%}}%> name="active">&nbsp;&nbsp;
                Hiển thị
            </label>
        </div>
    </div>

</form>
