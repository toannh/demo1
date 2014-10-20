
<form name="categoryFrom" class="form-horizontal" id="category-form-edit" style="width: 100%;">
    <input type="text" style="display: none" name="id" value="<%= (typeof data!=='undefined')? data.id: ''%>" />            
    <div class="form-group ancestors" style="margin-bottom: 0px;">                  
    </div>
    <div class="form-group">
        <div class="controls">
            <input type="text" style="display: none" value="<%= (typeof data!=='undefined')? data.parentId: ''%>"  name="parentId" class="parentId"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Tên danh mục:</label>
        <div class="col-sm-8">
            <input name="name" value="<%= (typeof data!=='undefined')? data.name: ''%>" type="text" class="form-control" placeholder="Tên danh mục"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Mô tả:</label>
        <div class="col-sm-8">                    
            <textarea name="description" class="form-control" placeholder="Mô tả"><%= (typeof data!=='undefined')? data.description: ''%></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Khối lượng:</label>
        <div class="col-sm-8">                    
            <input name="weight" value="<%= (typeof data!=='undefined')? data.weight: ''%>" type="text" class="form-control" placeholder="Khối lượng"/>                    
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Thứ tự:</label>
        <div class="col-sm-8">                    
            <input name="position" value="<%= (typeof data!=='undefined')? data.position: ''%>" type="text" class="form-control" placeholder="Thứ tự"/>                    
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Id danh mục ebay:</label>
        <div class="col-sm-8">                    
            <input name="ebayCategoryId" value="<%= (typeof data!=='undefined')? data.ebayCategoryId: ''%>" type="text" class="form-control" placeholder="Id danh mục ebay"/>                    
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Ebay keyword:</label>
        <div class="col-sm-8">                    
            <input name="ebayKeyword" value="<%= (typeof data!=='undefined')? data.ebayKeyword: ''%>" type="text" class="form-control" placeholder="Ebay keyword"/>                    
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Meta Keyword:</label>
        <div class="col-sm-8">                    
            <textarea name="metaKeyword" class="form-control" placeholder="Meta Keyword"><%= (typeof data!=='undefined')? data.metaKeyword: ''%></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-4">Meta Description:</label>
        <div class="col-sm-8">                    
            <textarea name="metaDescription" class="form-control" placeholder="Meta Description"><%= (typeof data!=='undefined')? data.metaDescription: ''%></textarea>
        </div>
    </div>
    <%if(typeof data!=='undefined' && data.level==1){ %>
    <div class="form-group">
        <label class="control-label col-sm-4">Icon:</label>
        <div class="col-sm-8">                    
            <select name="icon" class="form-control">
                <option value="">--Chọn icon--</option>
                <option value="icon42-fashion">icon42-fashion</option>
                <option value="icon42-cosmetics">icon42-cosmetics</option>
                <option value="icon42-tel">icon42-tel</option>
                <option value="icon42-computer">icon42-computer</option>
                <option value="icon42-camera">icon42-camera</option>
                <option value="icon42-phone">icon42-phone</option>
                <option value="icon42-book">icon42-book</option>
                <option value="icon42-kid">icon42-kid</option>
                <option value="icon42-homeitem">icon42-homeitem</option>
                <option value="icon42-sofa">icon42-sofa</option>
                <option value="icon42-scan">icon42-scan</option>
                <option value="icon42-bycicle">icon42-bycicle</option>
                <option value="icon42-game">icon42-game</option>
                <option value="icon42-tennis">icon42-tennis</option>
                <option value="icon42-drink">icon42-drink</option>
                <option value="icon42-watch">icon42-watch</option>
                <option value="icon42-relax">icon42-relax</option>
                <option value="icon42-machines">icon42-machines</option>
                <option value="icon42-animals">icon42-animals</option>
            </select>
        </div>
    </div>
    <% } %>
    <div class="form-group">
        <label class="control-label col-sm-4">Trạng thái:</label>
        <div class="col-sm-8">                    
            <label class="checkbox-inline">                                
                <% if((typeof data!=='undefined')&& data.active){%>
                <input type="checkbox" checked="checked" name="active" placeholder="Cho phép đăng sản phâm"/>Hoạt động
                <%}else{%>
                <input type="checkbox" name="active" placeholder="Cho phép đăng sản phâm"/>Hoạt động
                <%}%>
            </label>  
        </div>
    </div>


</form>