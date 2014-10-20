<form name="itemFrom" class="form-horizontal" id="item-form">
    <input type="hidden" name="id" value="<%= (typeof data!=='undefined')? data.id: ''%>"  />                    
    <div class="form-group" >
        <label class="control-label col-sm-3">Tên sản phẩm:</label>
        <div class="col-sm-9">
            <input name="name" value="<%= (typeof data!=='undefined')? data.name: ''%>" type="text" class="form-control" placeholder="Tên sản phẩm"/>
        </div>
    </div>
    <div class="form-group">     
        <label class="control-label col-sm-3">Danh mục cha:</label>
        <div class="col-sm-9" style="padding: 0 30px;">
            <div id="selectcategorys"></div>
            <input type="hidden" value="<%= (typeof data!=='undefined')? data.categoryId: ''%>"  name="categoryId" class="categoryId" id="categoryId"/>
        </div>
    </div>        
    <div class="form-group">
        <label class="control-label col-sm-3">Thương hiệu:</label>
        <div class="col-sm-9">     
            <div class="input-group">
                <input type="text" name="manufacturerId" readonly="true" class="form-control" placeholder="Mã thương hiệu"  value="<%= (typeof data!=='undefined')? data.manufacturerId: ''%>" />
                <span class="input-group-btn">
                    <button class="btn btn-default" onclick="item.loadmf('manufacturerId', 'edit','item-form');"  type="button">Tìm</button>
                </span>
            </div>               
        </div>            
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Model:</label>
        <div class="col-sm-9">                    
            <div class="input-group">
                <input type="text" name="modelId" readonly="true" class="form-control" placeholder="Mã model" value="<%= (typeof data!=='undefined')? data.modelId: ''%>" />
                <span class="input-group-btn">
                    <button class="btn btn-default" onclick="item.loadmodel('modelId', 'edit','item-form');"  type="button">Tìm</button>
                </span>
            </div> 
        </div>
    </div>
    <div class="form-group">            
        <label class="control-label col-sm-3">Loại sản phẩm: </label>
        <div class="col-sm-9">     
            <select class="form-control" name="listingType" id="listingType" <% if(typeof data!=='undefined'&& data.listingType){ %>disabled="true"<% } %>>
                    <option <% if(typeof data!=='undefined'&& data.listingType=='BUYNOW'){ %>selected<% } %> value="BUYNOW">Mua ngay</option>
                <option <% if(typeof data!=='undefined'&& data.listingType=='AUCTION'){ %>selected<% } %> value="AUCTION">Đấu giá</option>
            </select>
        </div>
    </div>
    <div class="form-group">   
        <label class="control-label col-sm-3">Người bán:</label>
        <div class="col-sm-9">                                                 
            <input name="sellerName"  id="sellerName" value="<%= (typeof data!=='undefined')? data.sellerName: ''%>" type="text" class="form-control" placeholder="Nhập tên người bán"/>
        </div>            
    </div>
    <%  
    var muangay = 'style="border: 1px solid #FFA8AC; border-radius: 5px; padding: 10px 40px 10px 88px; margin-bottom:30px;"'; 
    var daugia = 'style="border: 1px solid #FFA8AC; border-radius: 5px; padding: 10px 40px 10px 88px; margin-bottom:30px;"';
    %>
    <% if((typeof data!=='undefined')&& data.listingType == 2){ %>
    <% muangay = 'style="border: 1px solid #FFA8AC; border-radius: 5px; padding: 10px 40px 10px 88px; margin-bottom:30px;display:none"'; %>        
    <% }else{ %>
    <% daugia = 'style="border: 1px solid #FFA8AC; border-radius: 5px; padding: 10px 40px 10px 88px; margin-bottom:30px;display:none"'; %>        
    <% } %>
    <fieldset id="daugia" <%= daugia %> >
              <legend style="margin: 8px 20px; border: 0; width: auto; color: #EA151E; font-size: 16px;" > Đấu giá </legend>
        <div class="form-group">
            <label class="control-label col-sm-3">Giá khởi điểm:</label>
            <div class="col-sm-9"> 
                <div class="input-group">
                    <input name="startPrice" value="<%= (typeof data!=='undefined')? data.startPrice: 0%>" type="text" class="form-control" placeholder="Giá gốc">
                    <span class="input-group-addon">VNĐ</span>
                </div>                      
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">Bán ngay:</label>
            <div class="col-sm-9">  
                <div class="input-group">
                    <input name="sellPrice" value="<%= (typeof data!=='undefined')? data.sellPrice: 0%>"  type="text" class="form-control" placeholder="Bán ngay">
                    <span class="input-group-addon">VNĐ</span>
                </div>                    
            </div>                
        </div>      
        <div class="form-group">
            <label class="control-label col-sm-3"> Bước giá:</label>
            <div class="col-sm-9"> 
                <div class="input-group">
                    <input name="bidStep" value="<%= (typeof data!=='undefined')? data.bidStep: 0%>" type="text" class="form-control" placeholder="Bước giá">                        
                    <span class="input-group-addon">VNĐ</span>
                </div>             
            </div>
        </div>  

    </fieldset>

    <fieldset  id="muangay" <%= muangay %>>
               <legend style="margin: 8px 20px; border: 0; width: auto;  color: #EA151E; font-size: 16px;" > Mua ngay </legend>
        <div class="form-group">
            <label class="control-label col-sm-3 ">Giá gốc:</label>
            <div class="col-sm-9">
                <div class="input-group">
                    <input name="startPrice" value="<%= (typeof data!=='undefined')? data.startPrice: 0%>" type="text" class="form-control" placeholder="Giá gốc">
                    <span class="input-group-addon">VNĐ</span>
                </div>                                   
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3"> Giá bán:</label>
            <div class="col-sm-9">     
                <div class="input-group">
                    <input name="sellPrice" value="<%= (typeof data!=='undefined')? data.sellPrice: 0%>" type="text" class="form-control" placeholder="Giá bán">
                    <span class="input-group-addon">VNĐ</span>
                </div>                       
            </div>
        </div>  
        <div class="form-group">
            <label class="control-label col-sm-3">Mức giảm:</label>
            <div class="col-sm-9">  
                <div class="input-group">
                    <input name="discountPercent" value="<%= (typeof data!=='undefined')? data.discountPercent: 0%>"  type="text" class="form-control" placeholder="Mức giảm">
                    <span class="input-group-addon">%</span>
                </div>                    
            </div>                
        </div>  
        <div class="form-group">
            <label class="control-label col-sm-3">Số lượng:</label>
            <div class="col-sm-9">     
                <input name="quantity" value="<%= (typeof data!=='undefined')? data.quantity: 1%>" type="text" class="form-control" placeholder="Số lượng"/>              
            </div>
        </div> 
    </fieldset>

    <div class="form-group">
        <label class="control-label col-sm-3">Thời gian bắt đầu:</label>
        <div class="col-sm-9">     
            <input name="startTime" class="timeselect"  value="<%= (typeof data!=='undefined')? data.startTime: 0%>" type="hidden" class="form-control" placeholder="Thời gian bắt đầu"/>              
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Hạn bán:</label>
        <div class="col-sm-9">     
            <select class="form-control" name="endTime">
                <option value="30">30 ngày</option>
                <option value="40">40 ngày</option>
                <option value="60">60 ngày</option>
            </select> 
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Tỉnh thành :</label>
        <div class="col-sm-9">     
            <select class="form-control" name="cityId" id="cityId">
                <option value="">-- Tỉnh thành phố --</option>
                <% for(var i=0; i< cities.length; i++){ %>
                <option value="<%= cities[i].id %>" <% if(cities[i].id == data.cityId){ %>selected="true"<%}%>><%= cities[i].name %></option>
                <%}%>
            </select>                
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Cân nặng:</label>
        <div class="col-sm-9">   
            <div class="input-group">
                <input name="weight" value="<%= (typeof data!=='undefined')? data.weight: 0%>" type="text" class="form-control" placeholder="Cân nặng"/>
                <span class="input-group-addon">gram</span>
            </div>  
        </div>            
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Tình trạng sản phẩm:</label>
        <div class="col-sm-9">
            <div class="radio-inline col-md-4">
                <label style="font-weight: normal">
                    <input type="radio"  name="condition" value="NEW" <% if((typeof data!=='undefined')&& data.condition=="NEW") {%>checked="checked"<% } %>> Hàng mới
                </label>
            </div>
            <div class="radio-inline col-md-5">
                <label style="font-weight: normal">
                    <input type="radio" name="condition" value="OLD" <% if((typeof data!=='undefined')&& data.condition=="OLD") {%>checked="checked"<% } %>> Hàng cũ
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">Trạng thái:</label>
        <div class="col-sm-9">
            <div class="checkbox-inline col-md-5">
                <label style="font-weight: normal">
                    <input type="checkbox" name="active" <% if((typeof data!=='undefined')&& data.active) {%>checked="checked"<% } %> > Kích hoạt
                </label>
            </div>
        </div>
    </div>
</form>