shopbanner = {};
shopbanner.init = function() {

};


shopbanner.add = function() {
    if (!$('input[value=type-image]').is(':checked') && !$('input[value=type-video]').is(':checked')) {
        popup.msg("Bạn chưa chọn định dạng");
        return false;
    }
    ajaxUpload({
        service: '/shopbanner/add.json',
        id: 'form-add-banner',
        contentType: 'json',
        done: function(rs) {
            if (rs.success) {
                popup.msg("Thêm banner thành công");
                setTimeout(function() {
                    window.location.reload();
                }, 1000);
            } else {
                popup.msg("Thêm mới tin thất bại");
            }

        }
    });

};
shopbanner.edit = function() {
    if (!$('input[value=type-image]').is(':checked') && !$('input[value=type-video]').is(':checked')) {
        popup.msg("Bạn chưa chọn định dạng");
        return false;
    }
    ajaxUpload({
        service: '/shopbanner/edit.json',
        id: 'form-edit-banner',
        contentType: 'json',
        done: function(rs) {
            if (rs.success) {
                popup.msg("Sửa banner thành công");
                setTimeout(function() {
                    window.location.reload();
                }, 1000);
            } else {
                popup.msg("Thêm mới tin thất bại");
            }

        }
    });

};
shopbanner.editStatus = function(id) {
    ajax({
        service: '/shopbanner/changestatus.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                if (resp.data.active) {
                    $('span[editStatus=' + resp.data.id + ']').html('<span class="glyphicon glyphicon-ok visited"></span> Hiển thị');
                    $('span[editIcon=' + resp.data.id + ']').html('<button class="btn btn-default btn-sm" onclick="shopbanner.editStatus(\'' + resp.data.id + '\')">Ẩn</button>');
                } else {
                    $('span[editStatus=' + resp.data.id + ']').html('<span class="glyphicon glyphicon-ban-circle icon-hidden"></span> Không hiển thị');
                    $('span[editIcon=' + resp.data.id + ']').html('<button class="btn btn-success btn-sm" onclick="shopbanner.editStatus(\'' + resp.data.id + '\')">Hiện</button>');
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
shopbanner.get = function(id) {
    ajax({
        service: '/shopbanner/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                var html = "<p class='text-center'>" + resp.data.embedCode + "</p>";
                popup.open('popup-add', 'Video quảng cáo', html, [
                    {
                        title: 'Đóng',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-add');
                        }
                    }
                ]);

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
shopbanner.del = function(id) {
    popup.confirm("Bạn có chắc muốn xóa?", function() {
        ajax({
            service: '/shopbanner/del.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    popup.msg(resp.message);
                    setTimeout(function() {
                        window.location.reload();
                    }, 2000);
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

shopbanner.reload = function() {
    location.href = baseUrl + '/user/shop-banner.html';
};

$('#typeFormat').click(function() {
    if ($('input[value=type-image]').is(':checked')) {
        $('#box-type-flash-tab1').show();
        $('#box-type-video-tab1').hide();
    }
    if ($('input[value=type-video]').is(':checked')) {
        $('#box-type-video-tab1').show();
        $('#box-type-flash-tab1').hide();
    }
});
