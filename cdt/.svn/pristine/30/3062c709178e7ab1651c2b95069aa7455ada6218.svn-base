<div class="row" foritem="<%= (typeof data!=='undefined')? data.id: ''%>">
    <div class="form-horizontal form-reset-col">
        <div class="col-lg-11 col-md-11 col-xs-12 reset-padding-all">
            <div class="col-sm-5">
                <div class="form-group">
                    <input type="text" class="form-control" maxlength="180" name="name" placeholder="Tiêu đề tối đa 180 ký tự">
                </div>
            </div>
            <div class="col-sm-3">
                <div class="form-group">
                    <select class="form-control" name="condition">
                        <option value="NEW">Mới</option>
                        <option value="OLD">Cũ</option>
                    </select>                                                        
                </div>
            </div>
            <div class="col-sm-2">
                <div class="form-group">
                    <input type="text" class="form-control inputnumber" name="sellPrice" placeholder="Giá bán">
                </div>
            </div>
            <div class="col-sm-2 reset-padding-right">
                <div class="form-group pull-left" style="width:89%; margin-right:5px;">
                    <input type="text" name="weight" class="form-control inputnumber" placeholder="Cân nặng">
                </div> &nbsp;
                <a  onclick="additemquick.popupWeight('<%=data.id%>');" href="#" data-toggle="modal" data-target="#ModalNoBottom"><span class="glyphicon glyphicon-question-sign pull-right"></span></a>
            </div>  
            <div class="clearfix"></div>
            <div class="clearfix mgt-15">
                <div class="col-sm-3 reset-padding">
                    <h6 rel="image-title"><strong>Đăng ảnh:</strong></h6>
                    <form  <%= 'id="img-add-form-bylink-' + data.id+ '"' %> method="post" >
                        <input type="hidden" name="id" value="<%=data.id%>" />
                        <input type="text" name="imageUrl" onchange="additemquick.addImageByLink('<%=data.id%>');" class="form-control" style="max-width:235px;" placeholder="Nhập URL hình ảnh vào đây">
                    </form>
                    <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 120px;"><div class="submit-choose-img-item" style="overflow: hidden; width: auto; height: 120px;">
                            <div rel="img" ></div>
                            <div class="submit-img-item" >
                                <div class="img">
                                    <form <%= 'id="img-add-form-' + data.id+ '"' %>  method="post" >
                                        <a class="img-upload-item" onclick="$('#image<%=data.id%>').click();" ></a>
                                        <input type="hidden" name="id" value="<%=data.id%>" />
                                        <input name="image" id="image<%=data.id%>" onchange="additemquick.addImageByLocal('<%=data.id%>');" type="file" style="display: none" />
                                    </form>
                                </div>
                            </div>                                                                                                                                  
                        </div><div class="slimScrollBar" style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-top-left-radius: 7px; border-top-right-radius: 7px; border-bottom-right-radius: 7px; border-bottom-left-radius: 7px; z-index: 99; right: 1px; height: 60px; background: rgb(0, 0, 0);"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-top-left-radius: 7px; border-top-right-radius: 7px; border-bottom-right-radius: 7px; border-bottom-left-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(51, 51, 51);"></div></div>
                </div>
                <div class="col-sm-9">
                    <div class="form-group">
                        <textarea class="form-control" for="<%=data.id%>" <%= 'id="detail_' + data.id+ '"' %> placeholder="Mô tả:Ảnh sản phẩm trong mô tả được tự động thêm vào mục Đăng ảnh."></textarea>
                    </div>
                </div>
            </div>                                              
        </div>
        <div class="col-lg-1 col-md-1 col-xs-12 reset-padding-right">
            <a href="javascript:;" onclick="additemquick.removeItem('<%=data.id%>');" class="btn btn-block btn-remove-submit">
                <span class="fa fa-times-circle"></span>
            </a>
        </div>

    </div>
    <div class="clearfix"></div>
    <hr>
</div>