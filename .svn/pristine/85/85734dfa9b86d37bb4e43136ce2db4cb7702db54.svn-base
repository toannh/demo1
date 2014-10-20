
hotdealbox = {};
hotdealbox.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị Box HotDeal", "/cp/hotdealbox.html"],
        ["Danh sách Box HotDeal trang chủ"]
    ]);
};
hotdealbox.imageD = new Object();
function preview(coords) {
    if (parseInt(coords.w) > 0) {
        // get image
        var container = $('#photo');
        var images = document.getElementById('photo');
        // get ti le anh
        var tiLeAnh = images.naturalWidth / container.width();
        // calculate
        var calculatex1 = Math.round(coords.x * tiLeAnh);
        var calculatey1 = Math.round(coords.y * tiLeAnh);
        var calculatewidth = Math.round(coords.w * tiLeAnh);
        var calculateheight = Math.round(coords.h * tiLeAnh);
        $('#x1').val(calculatex1);
        $('#y1').val(calculatey1);
        $('#w').val(calculatewidth);
        $('#h').val(calculateheight);
    }

}
hotdealbox.changeName = function(idCate, name) {
    ajax({
        service: '/cpservice/hotdealbox/changename.json',
        data: {title: name, itemId: idCate},
        type: 'post',
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
hotdealbox.changeStatus = function(id) {
    ajax({
        service: '/cpservice/hotdealbox/changestatus.json',
        data: {itemId: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                if (resp.data.active) {
                    $('a[editStatus=' + resp.data.itemId + ']').html('<img src="' + staticUrl + '/cp/img/icon-enable.png">');
                } else {
                    $('a[editStatus=' + resp.data.itemId + ']').html('<img src="' + staticUrl + '/cp/img/icon-disable.png">');
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });

};
hotdealbox.add = function() {
    popup.open('popup-addHB', 'Thêm mới HotDeal', template('/cp/tpl/hotdealbox/add.tpl', null),
            [
                {
                    title: 'Lưu',
                    style: 'btn-primary',
                    fn: function() {
                        ajaxUpload({
                            service: '/cpservice/hotdealbox/add.json',
                            id: 'form-edit',
                            contentType: 'json',
                            done: function(rs) {
                                if (rs.success) {
                                    popup.msg("Thêm HotDeal thành công", function() {
                                        location.reload();
                                    });
                                } else {
                                    popup.msg(rs.message);
                                }
                            }
                        });
                    }
                },
                {
                    title: 'Hủy',
                    style: 'btn-default',
                    fn: function() {
                        popup.close('popup-addHB');
                    }
                }
            ]);

};
hotdealbox.addImage = function() {
    ajaxUpload({
        service: '/cpservice/hotdealbox/addimage.json',
        id: 'form-edit',
        done: function(resp) {
            if (resp.success) {
                var imghtml = '';
                imghtml += '<img id="photo" src="' + resp.data + '" width="200" height="200">';
                $('#frameIMG').html(imghtml);
                $('#preview').html('<img src="' + resp.data + '" width="100" height="100" /><div class="clearfix"></div>');
                $('.hotdeal').hide();
                $('input[name=imageUrl]').val('');
                $('input[name=image]').val('');
                $('#photo').Jcrop({
                    onChange: preview,
                    minSize: [158, 158],
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

hotdealbox.edit = function(id) {

    ajax({
        service: '/cpservice/hotdealbox/getimagecrop.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-addHB', 'Chỉnh sửa HotDeal', template('/cp/tpl/hotdealbox/edit.tpl', resp),
                        [
                            {
                                title: 'Lưu',
                                style: 'btn-primary',
                                fn: function() {
                                    ajaxUpload({
                                        service: '/cpservice/hotdealbox/edit.json',
                                        id: 'form-edit',
                                        loading: true,
                                        contentType: 'json',
                                        done: function(rs) {
                                            if (rs.success) {
                                                popup.msg("Sửa HotDeal thành công", function() {
                                                    location.reload();
                                                });
                                            } else {
                                                popup.msg(rs.message);
                                            }
                                        }
                                    });
                                }
                            },
                            {
                                title: 'Hủy',
                                style: 'btn-default',
                                fn: function() {
                                    popup.close('popup-addHB');
                                }
                            }
                        ]);
                $('#photo').Jcrop({
                    onChange: preview,
                    minSize: [158, 158],
                });

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
hotdealbox.addbanner = function() {
    ajax({
        service: '/cpservice/hotdealbox/getbanner.json',
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-addHB', 'Thay đổi Banner HotDeal', template('/cp/tpl/hotdealbox/editbanner.tpl', resp),
                        [
                            {
                                title: 'Thay đổi',
                                style: 'btn-primary',
                                fn: function() {
                                    ajaxUpload({
                                        service: '/cpservice/hotdealbox/editbanner.json',
                                        id: 'form-edit',
                                        loading: true,
                                        contentType: 'json',
                                        done: function(rs) {
                                            if (rs.success) {
                                                popup.msg("Sửa Banner HotDeal thành công", function() {
                                                    location.reload();
                                                });
                                            } else {
                                                popup.msg(rs.message);
                                            }
                                        }
                                    });
                                }
                            },
                            {
                                title: 'Hủy',
                                style: 'btn-default',
                                fn: function() {
                                    popup.close('popup-addHB');
                                }
                            }
                        ]);
                $('input[id=lefile]').change(function() {
                    $('#photoCover').val($(this).val());
                });

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
hotdealbox.remove = function(id) {
    popup.confirm('Bạn có muốn xóa sản phẩm này không?', function() {
        ajax({
            service: '/cpservice/hotdealbox/del.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    popup.msg("Xoá sản phẩm hotdeal thành công", function() {
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
