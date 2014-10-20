<form id="form-edit-uploadImg" style="display:none">
    <input type="file" style="display:none" id="lefile" name="avatar" onchange="user.uploadImageAvatar();">
    <input type="hidden" name="userId" value="<%=data.id%>" />
</form>
<form class="form-horizontal" id="form-add">

    <div class="form form-horizontal modal-form">
        <div class="form-group">
            <label class="control-label col-md-3">Chọn:</label>
            <div class="col-md-9">
                <label class="radio-inline">
                    <input type="radio" name="rd-avatar" value="option1" id="rd-upload-img" checked="checked"> Tải từ máy lên
                </label>
                <label class="radio-inline">
                    <input type="radio" name="rd-avatar" value="option1" id="rd-upload-link"> Đường dẫn ảnh
                </label> 
            </div>
        </div>
        <div class="form-group box-upload-link"style="display: none;">
            <label class="control-label col-md-3">Đường dẫn ảnh:</label>
            <div class="col-md-6"><input name="downloadImageAvatar" type="text" class="form-control"></div>
            <div class="col-md-3"><button type="button" class="btn btn-default" onclick="user.downloadImageAvatar()">Lấy ảnh</button></div>
        </div>
        <div class="form-group box-upload-img" style="display: block;" >
            <label class="control-label col-md-3">Tải từ máy lên:</label>
            <div class="col-md-9"><button type="button" class="btn btn-default" onclick="$('#lefile').click();">Chọn ảnh</button></div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Hiển thị:</label>
            <div class="col-md-6">
                <a class="thumbnail">
                    <% if(data.avatar!=null && data.avatar!=''){ %>
                    <img id="photo" for="<%=data.id%>" src="<%=data.avatar%>" style="max-height: 225px; max-width: 225px;"/>
                    <%}else {%>
                    <img id="photo" for="" src="<%=staticUrl%>/user/images/no-avatar_2.png" />
                    <%}%>
                </a>
            </div>
        </div>
    </div><!-- end modal-form -->
    <input type="hidden" id="x1" class="form-control" disabled="true" name="x" value="0"></label>
    <input type="hidden" id="y1" class="form-control" disabled="true" name="y" value="0"></label>
    <input type="hidden" id="w" class="form-control" disabled="true" name="width" value="0"></label>
    <input type="hidden" id="h" class="form-control" disabled="true" name="height" value="0"></label>
   
</form>