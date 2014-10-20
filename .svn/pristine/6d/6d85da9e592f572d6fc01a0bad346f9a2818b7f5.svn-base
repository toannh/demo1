<div class="row" foritem="<%= (typeof data!=='undefined')? data.id: ''%>">
    <div class="form-horizontal form-reset-col">
        <div class="col-lg-11 col-md-11 col-xs-12">
            <div class="col-sm-4">
                <div class="form-group">
                    <input type="text" class="form-control" name="name" maxlength="180" placeholder="Tiêu đề tối đa 180 ký tự">
                </div>
            </div>
            <div class="col-sm-2">
                <div class="form-group">
                    <select class="form-control" name="condition">
                        <option value="NEW">Mới</option>
                        <option value="OLD">Cũ</option>
                    </select>                                                        
                </div>
            </div>
            <div class="col-sm-2">
                <div class="form-group">
                    <input type="text" class="form-control inputnumber" name="startPrice" placeholder="Giá gốc">
                </div>
            </div>
            <div class="col-sm-2">
                <div class="form-group">
                    <input type="text" class="form-control inputnumber" name="sellPrice" placeholder="Giá bán">
                </div>
            </div>
            <div class="col-sm-2">
                <div class="form-group pull-left" style="width:89%; margin-right:5px;">
                    <input type="text" name="weight" class="form-control inputnumber" placeholder="Cân nặng">
                </div> &nbsp;
                <a  onclick="itemquick.popupWeight('<%=data.id%>');" href="#" data-toggle="modal" data-target="#ModalNoBottom"><span class="glyphicon glyphicon-question-sign pull-right"></span></a>
            </div>  
            <div class="clearfix"></div>
            <div class="clearfix mgt-15">
                <div class="col-sm-3">
                    <h6 rel="image-title"><strong>Đăng ảnh:</strong></h6>
                    <form  <%= 'id="img-add-form-bylink-' + data.id+ '"' %> method="post" >
                        <input type="hidden" name="id" value="<%=data.id%>" />
                        <input type="text" name="imageUrl" onchange="itemquick.addImageByLink('<%=data.id%>');" class="form-control" style="max-width:235px;" placeholder="Nhập URL hình ảnh vào đây">
                    </form>
                    <div class="submit-choose-img-item">
                        <div rel="img" ></div>
                        <div class="submit-img-item" >
                            <div class="img">
                                <form <%= 'id="img-add-form-' + data.id+ '"' %>  method="post" >
                                    <a class="img-upload-item" onclick="$('#image<%=data.id%>').click();" ></a>
                                    <input type="hidden" name="id" value="<%=data.id%>" />
                                    <input name="image" id="image<%=data.id%>" onchange="itemquick.addImageByLocal('<%=data.id%>');" type="file" style="display: none" />
                                </form>
                            </div>
                        </div>                                                                                                                                  
                    </div>
                </div>
                <div class="col-sm-9">
                    <div class="form-group">
                        <textarea class="form-control" for="<%=data.id%>" <%= 'id="detail_' + data.id+ '"' %> placeholder="Mô tả:Ảnh sản phẩm trong mô tả được tự động thêm vào mục Đăng ảnh."></textarea>
                    </div>
                </div>
            </div>                                              
        </div>
        <div class="col-lg-1 col-md-1 col-xs-12 reset-padding-right">
            <a href="javascript:;" onclick="itemquick.removeItem('<%=data.id%>');" class="btn btn-block btn-remove-submit">
                <span class="glyphicon glyphicon-remove"></span>
            </a>
        </div>

    </div>
    <div class="clearfix"></div>
    <hr>
</div>