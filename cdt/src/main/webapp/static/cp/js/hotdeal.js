var hotdeal = {};
hotdeal.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị HotDeal", "/cp/hotdeal.html"],
        ["Danh sách HotDeal"]
    ]);
};
hotdeal.add = function() {
    popup.open('popup-add-category', 'Thêm mới danh mục hotdeals', template('/cp/tpl/hotdeal/editform.tpl', {cates: cates}), [
        {
            title: 'Thêm',
            style: 'btn-info',
            fn: function() {
                ajaxSubmit({
                    service: '/cpservice/hotdeal/addhotdealcategory.json',
                    id: 'hcategory-form-edit',
                    contentType: 'json',
                    done: function(resp) {
                        if (resp.success) {
                            popup.msg(resp.message, function() {
                                popup.close('popup-add-category');
                                location.reload();
                            });
                        } else {
                            popup.msg(resp.message);
                        }
                    }
                });
            }
        },
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-add-category');
            }
        }
    ]);
    $('.timeselect').timeSelect();
};
hotdeal.editCate = function(id) {
    ajax({
        service: '/cpservice/hotdeal/getcategory.json',
        data: {id: id},
        type: 'get',
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-add-category', 'Sửa danh mục hotdeals', template('/cp/tpl/hotdeal/editform.tpl', {cates: cates, data: resp.data}), [
                    {
                        title: 'Lưu',
                        style: 'btn-info',
                        fn: function() {
                            ajaxSubmit({
                                service: '/cpservice/hotdeal/addhotdealcategory.json',
                                id: 'hcategory-form-edit',
                                contentType: 'json',
                                done: function(resp) {
                                    if (resp.success) {
                                        popup.msg(resp.message, function() {
                                            popup.close('popup-add-category');
                                            location.reload();
                                        });
                                    } else {
                                        popup.msg(resp.message);
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-add-category');
                        }
                    }
                ]);
                $('.timeselect').timeSelect();
            } else {
                popup.msg(resp.message);
            }
        }
    });

};
hotdeal.changeStatus = function(cateId) {
    ajax({
        service: '/cpservice/hotdeal/changestatus.json',
        data: {id: cateId},
        type: 'get',
        done: function(resp) {
            if (resp.success) {
                if (resp.data.active) {
                    $('a[editStatus=' + resp.data.id + ']').html('<img src="' + staticUrl + '/cp/img/icon-enable.png">');
                } else {
                    $('a[editStatus=' + resp.data.id + ']').html('<img src="' + staticUrl + '/cp/img/icon-disable.png">');
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
hotdeal.changeSpecial = function(cateId) {
    ajax({
        service: '/cpservice/hotdeal/changespecial.json',
        data: {id: cateId},
        type: 'get',
        done: function(resp) {
            if (resp.success) {
                if (resp.data.special) {
                    $('a[editSpecial=' + resp.data.id + ']').html('<img src="' + staticUrl + '/cp/img/icon-enable.png">');
                } else {
                    $('a[editSpecial=' + resp.data.id + ']').html('<img src="' + staticUrl + '/cp/img/icon-disable.png">');
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
hotdeal.changePosition = function(cateId) {
    ajax({
        service: '/cpservice/hotdeal/changeposition.json',
        data: {id: cateId, position: $('input[for=' + cateId + ']').val()},
        type: 'get',
        done: function(resp) {
            popup.msg(resp.message);
        }
    });
};
hotdeal.removeCate = function(cateId) {
    ajax({
        service: '/cpservice/hotdeal/deletecategory.json',
        data: {id: cateId},
        type: 'get',
        done: function(resp) {

            if (resp.success) {
                popup.msg(resp.message, function() {
                    location.reload();
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
hotdeal.editItemHome = function(cateId) {
    ajax({
        service: '/cpservice/hotdeal/getitemhomebycate.json',
        data: {id: cateId},
        type: 'get',
        done: function(resp) {
            popup.open('popup-add-item', 'Sửa sản phẩm', template('/cp/tpl/hotdeal/additem.tpl', {cateId: cateId, data: resp.data, home: true}), [
                {
                    title: 'Xong',
                    style: 'btn-default',
                    fn: function() {
                        popup.close('popup-add-item');
                    }
                }
            ]);
        }
    });
};
hotdeal.editItem = function(cateId) {
    ajax({
        service: '/cpservice/hotdeal/getitembycate.json',
        data: {id: cateId},
        type: 'get',
        done: function(resp) {
            popup.open('popup-add-item', 'Sửa sản phẩm', template('/cp/tpl/hotdeal/additem.tpl', {cateId: cateId, data: resp.data, home: false}), [
                {
                    title: 'Xong',
                    style: 'btn-default',
                    fn: function() {
                        popup.close('popup-add-item');
                    }
                }
            ]);
        }
    });
};
hotdeal.saveItem = function(cateId, home) {
    var data = {};
    data.itemId = $('input[name=itemId]').val();
    data.home = home;
    data.hotdealCategoryId = cateId;
    ajax({
        service: '/cpservice/hotdeal/addhotdealitem.json',
        data: data,
        contentType: 'json',
        type: 'post',
        done: function(resp) {
            if (resp.success) {
                if (home) {
                    popup.open('crop-image', 'Sửa ảnh sản phẩm', template('/cp/tpl/hotdeal/image.tpl', {data: resp.data, width: 350, height: 350}), [
                        {
                            title: 'Lưu',
                            style: 'btn-info',
                            fn: function() {
                                var data = {
                                    targetId: resp.data.id,
                                    imageId: $('#photo').attr("for"),
                                    x: $('#x1').val(),
                                    y: $('#y1').val(),
                                    width: $('#w').val(),
                                    height: $('#h').val()
                                };
                                ajax({
                                    service: '/cpservice/hotdeal/cropimage.json',
                                    data: data,
                                    contentType: 'json',
                                    type: 'post',
                                    done: function(rs) {
                                        if (rs.success) {
                                            popup.msg(rs.message, function() {
                                                popup.close('crop-image');
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
                                popup.close('crop-image');
                            }
                        }
                    ]);
                    if (resp.data.item != null && resp.data.item.images != null && resp.data.item.images.length > 0) {
                        $('.thumbnail').html('<img id="photo" for="' + resp.data.item.images[0] + '" src="' + resp.data.image + '" />');
                    }
                    var width = $('input[name=widthF]').val();
                    var height = $('input[name=heightF]').val();
                    setTimeout(function() {
                        $('#photo').Jcrop({
                            onChange: showPreview,
                            minSize: [width, height],
                            aspectRatio: width / height
                        });
                    }, 300);

                }
                if ($('.body tr[for]').length <= 0) {
                    $('.body').html('');
                }
                var el = $('tr[for="' + resp.data.id + '"]');
                if (typeof $(el).html() == 'undefined' || $(el).html().trim().length <= 0) {
                    var html = '<tr for="' + resp.data.id + '"><td class="text-center" style="vertical-align: middle;">' + resp.data.item.id + '</td>\
                           <td class="text-center" style="vertical-align: middle;">' + resp.data.item.name + '</td>';
                    if (home) {
                        html += '<td class = "text-center" style = "vertical-align: middle;" > <input onclick="hotdeal.changeCategoryItemSpecial(this,\'' + resp.data.id + '\');" name="special" for="' + resp.data.id + '_special" type="checkbox" /> </td>';
                    }
                    html += '<td class="text-center" style="vertical-align: middle">\
                        <button type="button" class="btn btn-danger" onclick="hotdeal.deleteitem(' + resp.data.id + ');"  ><i class="glyphicon glyphicon-trash"></i> Xóa</button>\
                     </td></tr>';
                    $('.body').prepend(html);
                }
            }
        }
    });
};
hotdeal.deleteitem = function(hItemId) {
    ajax({
        service: '/cpservice/hotdeal/deleteitem.json',
        data: {id: hItemId},
        done: function(resp) {
            if (resp.success) {
                $('.body tr[for=' + hItemId + ']').remove();
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
hotdeal.changeCategoryItemSpecial = function(el, id) {
    if ($(el).is(':checked')) {
        ajax({
            service: '/cpservice/hotdeal/changeitemspecial.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    $('input[disabled=true]').removeAttr('checked');
                    $('input[disabled=true]').removeAttr('disabled');
                    $('input[for=' + id + '_special]').prop('disabled', 'true');
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    }

};

hotdeal.uploadImageItem = function() {
    var width = $('input[name=widthF]').val();
    var height = $('input[name=heightF]').val();
    ajaxUpload({
        service: '/cpservice/hotdeal/uploadimage.json',
        id: 'image-form-i',
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {
                $('.thumbnail').html('<img id="photo" for="' + resp.data.imageId + '" src="' + resp.data.imageUrl + '" />');
                $('#photo').Jcrop({
                    onChange: showPreview,
                    minSize: [width, height],
                    aspectRatio: width / height
                });

                $('input[name=downloadImageItem]').val('');

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
hotdeal.downloadImageItem = function() {
    var url = $('input[name=downloadImageItem]').val();
    var targetId = $('input[name=targetId]').val();

    var width = $('input[name=widthF]').val();
    var height = $('input[name=heightF]').val();
    ajax({
        data: {imageUrl: url, targetId: targetId},
        service: "/cpservice/hotdeal/downloadimage.json",
        type: "post",
        contentType: "json",
        done: function(resp) {
            if (resp.success) {
                $('.thumbnail').html('<img id="photo" for="' + resp.data.imageId + '" src="' + resp.data.imageUrl + '" />');
                $('#photo').Jcrop({
                    onChange: showPreview,
                    minSize: [width, height],
                    aspectRatio: width / height
                });
                $('input[name=downloadImageItem]').val('');
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

