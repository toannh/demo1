bestdealbox = {};
bestdealbox.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị bestdealbox", "/cp/administrator.html"],
        ["Danh sách bestdealbox"]
    ]);
};
bestdealbox.imageD = new Object();
function previewBestDeal(img, selection) {
    if (!selection.width || !selection.height)
        return;

    var scaleX = 100 / selection.width;
    var scaleY = 100 / selection.height;

    $('#preview img').css({
        width: Math.round(scaleX * 280),
        height: Math.round(scaleY * 280),
        marginLeft: -Math.round(scaleX * selection.x1),
        marginTop: -Math.round(scaleY * selection.y1)
    });
    hotdealbox.imageD.x1 = selection.x1;
    hotdealbox.imageD.y1 = selection.y1;
    hotdealbox.imageD.width = selection.width;
    hotdealbox.imageD.height = selection.height;
    // get image
    var image = document.getElementById('photo');
    // get ti le anh
    var tiLeAnh = image.naturalWidth / image.clientWidth;
    // calculate
    var calculatex1 = Math.round(selection.x1 * tiLeAnh);
    var calculatey1 = Math.round(selection.y1 * tiLeAnh);
    var calculatewidth = Math.round(selection.width * tiLeAnh);
    var calculateheight = Math.round(selection.height * tiLeAnh);
    $('#x1').val(calculatex1);
    $('#y1').val(calculatey1);
    $('#x2').val(selection.x2);
    $('#y2').val(selection.y2);
    $('#w').val(calculatewidth);
    $('#h').val(calculateheight);

}
bestdealbox.changeStatus = function(id) {
    ajax({
        service: '/cpservice/bestdealbox/changestatus.json',
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
bestdealbox.changeNameCate = function(idCate, name) {
    ajax({
        service: '/cpservice/bestdealbox/changename.json',
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
bestdealbox.add = function() {
    popup.open('popup-addHB', 'Thêm mới HotDeal', template('/cp/tpl/bestdealbox/add.tpl', null),
            [
                {
                    title: 'Lưu',
                    style: 'btn-primary',
                    fn: function() {
                        ajaxUpload({
                            service: '/cpservice/bestdealbox/add.json',
                            id: 'form-edit',
                            contentType: 'json',
                            done: function(rs) {
                                if (rs.success) {
                                    popup.msg("Thêm BestDeal thành công", function() {
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
bestdealbox.addImage = function() {
    ajaxUpload({
        service: '/cpservice/bestdealbox/addimage.json',
        id: 'form-edit',
        done: function(resp) {
            if (resp.success) {
                var imghtml = '';
                imghtml += '<img id="photo" src="' + resp.data + '" width="320">';
                $('#frameIMG').html(imghtml);
                $('#preview').html('<img src="' + resp.data + '" width="100" height="100" /><div class="clearfix"></div>');
                $('.hotdeal').hide();
                $('input[name=imageUrl]').val('');
                $('input[name=image]').val('');

                $('#photo').imgAreaSelect({minHeight: 280, minWidth: 280, handles: true, onSelectChange: previewBestDeal});
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

bestdealbox.edit = function(id) {
    ajax({
        service: '/cpservice/bestdealbox/getimagecrop.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-addHB', 'Chỉnh sửa BestDeal', template('/cp/tpl/bestdealbox/edit.tpl', resp), [
                    {
                        title: 'Lưu',
                        style: 'btn-primary',
                        fn: function() {
                            ajaxUpload({
                                service: '/cpservice/bestdealbox/edit.json',
                                id: 'form-edit',
                                loading: true,
                                contentType: 'json',
                                done: function(rs) {
                                    if (rs.success) {
                                        popup.msg("Sửa BestDeal thành công", function() {
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
                            $('#photo').imgAreaSelect({remove: true, disable: true});
                        }
                    }
                ]);
                $('#photo').imgAreaSelect({minHeight: 280, minWidth: 280, handles: true, onSelectChange: previewBestDeal});

            } else {
                popup.msg(resp.message);
            }
        }
    });
};

bestdealbox.remove = function(id) {
    popup.confirm('Bạn có muốn xóa sản phẩm này không?', function() {
        ajax({
            service: '/cpservice/bestdealbox/del.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    popup.msg("Xoá sản phẩm BestDeal thành công", function() {
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
