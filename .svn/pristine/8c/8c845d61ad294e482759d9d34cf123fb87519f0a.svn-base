
topsellerbox = {};
topsellerbox.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị người bán uy tín", "/cp/topsellerbox.html"],
        ["Danh sách người bán uy tín"]
    ]);
};
topsellerbox.initItem = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị người bán uy tín", "/cp/topsellerbox.html"],
        ["Danh sách sản phẩm của người bán"]
    ]);
};
topsellerbox.imageD = new Object();
function previewTopSeller(coords) {
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
topsellerbox.add = function() {
    popup.open('popup-addHB', 'Thêm mới TopSellerBox', template('/cp/tpl/topsellerbox/add.tpl', null),
            [
                {
                    title: 'Lưu',
                    style: 'btn-primary',
                    fn: function() {
                        ajaxUpload({
                            service: '/cpservice/topsellerbox/add.json',
                            id: 'form-edit',
                            contentType: 'json',
                            done: function(rs) {
                                if (rs.success) {
                                    popup.msg("Thêm Sheller thành công", function() {
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


};
topsellerbox.addItem = function(sellerId) {
    popup.open('popup-addHB', 'Thêm mới sản phẩm vào TopSellerBox', template('/cp/tpl/topsellerbox/additem.tpl', null),
            [
                {
                    title: 'Lưu',
                    style: 'btn-primary',
                    fn: function() {
                        ajaxSubmit({
                            service: '/cpservice/topsellerbox/additem.json?sellerId=' + sellerId,
                            id: 'form-add',
                            contentType: 'json',
                            done: function(rs) {
                                if (rs.success) {
                                    popup.msg(rs.message, function() {
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
topsellerbox.changeNameItem = function(idSeller, idCate, name) {
    var data = new Object();
    data.itemId = idCate;
    data.title = name;
    var sellerbox = [];
    sellerbox.push(data);
    ajax({
        service: '/cpservice/topsellerbox/changenameitem.json',
        data: {sellerId: idSeller, topSellerBoxItemForms: sellerbox},
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
topsellerbox.changePositionItem = function(idSeller, idCate, position) {
    var data = new Object();
    data.itemId = idCate;
    data.position = position;
    var sellerbox = [];
    sellerbox.push(data);
    ajax({
        service: '/cpservice/topsellerbox/changepositionitem.json',
        data: {sellerId: idSeller, topSellerBoxItemForms: sellerbox},
        type: 'post',
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {
                location.reload();
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
topsellerbox.addImageItem = function() {
    ajaxUpload({
        service: '/cpservice/topsellerbox/addimageitem.json',
        id: 'form-edit',
        done: function(resp) {
            if (resp.success) {
                var imghtml = '';
                imghtml += '<img id="photo" src="' + resp.data + '">';
                $('#frameIMG').html(imghtml);
                $('#preview').html('<img src="' + resp.data + '" width="100" height="100" /><div class="clearfix"></div>');
                $('.hotdeal').hide();
                $('input[name=imageUrl]').val('');
                $('input[name=image]').val('');
                $('#photo').Jcrop({
                    onChange: previewTopSeller,
                    minSize: [201, 192],
                    aspectRatio: 192 / 201
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
topsellerbox.editItem = function(id) {
    ajax({
        service: '/cpservice/topsellerbox/getimageitemcrop.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-addHB', 'Thay ảnh sản phẩm TopSellerBox', template('/cp/tpl/topsellerbox/edititem.tpl', resp),
                        [
                            {
                                title: 'Lưu',
                                style: 'btn-primary',
                                fn: function() {
                                    ajaxUpload({
                                        service: '/cpservice/topsellerbox/edititem.json',
                                        id: 'form-edit',
                                        loading: true,
                                        contentType: 'json',
                                        done: function(rs) {
                                            if (rs.success) {
                                                popup.msg("Sửa Top người bán uy tín thành công", function() {
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
                    onChange: previewTopSeller,
                    minSize: [201, 192],
                    aspectRatio: 192 / 201
                });

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
topsellerbox.changeStatusItem = function(idSeller, idCate) {
    var data = new Object();
    data.itemId = idCate;
    var sellerbox = [];
    sellerbox.push(data);
    ajax({
        service: '/cpservice/topsellerbox/changestatusitem.json',
        data: {sellerId: idSeller, topSellerBoxItemForms: sellerbox},
        type: 'post',
        loading: false,
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {
                if (resp.data.active === 'true') {
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
topsellerbox.changeStatus = function(idSeller) {
    ajax({
        service: '/cpservice/topsellerbox/editstatus.json',
        data: {sellerId: idSeller},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                if (resp.data.active) {
                    $('a[editStatus=' + resp.data.sellerId + ']').html('<img src="' + staticUrl + '/cp/img/icon-enable.png">');
                } else {
                    $('a[editStatus=' + resp.data.sellerId + ']').html('<img src="' + staticUrl + '/cp/img/icon-disable.png">');
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });

};
topsellerbox.changePosition = function(idSeller, val) {
    ajax({
        service: '/cpservice/topsellerbox/editposition.json',
        data: {sellerId: idSeller, value: val},
        loading: true,
        done: function(resp) {
            if (resp.success) {

            } else {
                popup.msg(resp.message);
            }
        }
    });

};
topsellerbox.addImage = function() {
    ajaxUpload({
        service: '/cpservice/topsellerbox/addimage.json',
        id: 'form-edit',
        done: function(resp) {
            if (resp.success) {
                var imghtml = '';
                imghtml += '<img id="photo" src="' + resp.data + '" style="max-width: 200px">';
                $('#frameIMG').html(imghtml);
                $('#preview').html('<img src="' + resp.data + '" width="50" height="50" /><div class="clearfix"></div>');
                $('.hotdeal').hide();
                $('input[name=imageUrl]').val('');
                $('input[name=image]').val('');
                $('#photo').Jcrop({
                    onChange: previewTopSeller,
                    minSize: [50, 50],
                    aspectRatio: 50 / 50
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

topsellerbox.edit = function(id) {

    ajax({
        service: '/cpservice/topsellerbox/getimagecrop.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-addHB', 'Chỉnh sửa HotDeal', template('/cp/tpl/topsellerbox/edit.tpl', resp),
                        [
                            {
                                title: 'Lưu',
                                style: 'btn-primary',
                                fn: function() {
                                    ajaxUpload({
                                        service: '/cpservice/topsellerbox/edit.json',
                                        id: 'form-edit',
                                        loading: true,
                                        contentType: 'json',
                                        done: function(rs) {
                                            if (rs.success) {
                                                popup.msg("Sửa TopSeller thành công", function() {
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
                $('#photo').imgAreaSelect({minHeight: 50, minWidth: 50, aspectRatio: '50:50', handles: true, onSelectChange: previewTopSeller});


            } else {
                popup.msg(resp.message);
            }
        }
    });
};

topsellerbox.remove = function(id) {
    popup.confirm('Bạn có muốn người bán này không', function() {
        ajax({
            service: '/cpservice/topsellerbox/del.json',
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
topsellerbox.removeItem = function(itemId, sellerId) {
    popup.confirm('Bạn có muốn xóa thuộc người bán này không', function() {
        ajax({
            service: '/cpservice/topsellerbox/delitem.json',
            data: {itemId: itemId, shellerId: sellerId},
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
