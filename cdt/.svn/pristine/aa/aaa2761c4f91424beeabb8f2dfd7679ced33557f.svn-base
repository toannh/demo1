var landing = {};
landing.init = function(){
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị Landing", "/cp/landing.html"],
        ["Danh sách Landing"]
    ]);
};
landing.addlanding = function() {
    popup.open('popup-add-landing', 'Thêm mới landing', template('/cp/tpl/landing/editlanding.tpl'), [
        {
            title: 'Thêm',
            style: 'btn-info',
            fn: function() {
                ajaxUpload({
                    service: '/cpservice/landing/addlanding.json',
                    id: 'lcategory-form-edit',
                    contentType: 'json',
                    done: function(resp) {
                        if (resp.success) {
                            popup.msg(resp.message, function() {
                                popup.close('popup-add-landing');
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
                popup.close('popup-add-landing');
            }
        }
    ]);

    $('#colorpicker').ColorPicker({
        onShow: function(colpkr) {
            $(colpkr).fadeIn(500);
            return false;
        },
        onHide: function(colpkr) {
            $(colpkr).fadeOut(500);
            return false;
        },
        onChange: function(hsb, hex, rgb) {
            $('input[name=color]').val('#' + hex);
        }
    });
    $('.colorpicker').css("z-index", "99999999999");

    $('input[id=lefile]').change(function() {
        $('#photoCover').val($(this).val());
    });
    $('input[id=lefile1]').change(function() {
        $('#photoCover1').val($(this).val());
    });
};
landing.editlanding = function(landingId) {
    ajax({
        service: '/cpservice/landing/getlanding.json',
        data: {id: landingId},
        type: 'get',
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-add-landing', 'Sửa landing', template('/cp/tpl/landing/editlanding.tpl', {data: resp.data}), [
                    {
                        title: 'Lưu',
                        style: 'btn-info',
                        fn: function() {
                            ajaxUpload({
                                service: '/cpservice/landing/addlanding.json',
                                id: 'lcategory-form-edit',
                                contentType: 'json',
                                done: function(result) {
                                    if (result.success) {
                                        popup.msg(result.message, function() {
                                            popup.close('popup-add-landing');
                                            location.reload();
                                        });
                                    } else {
                                        popup.msg(result.message);
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-add-landing');
                        }
                    }
                ]);

                $('#colorpicker').ColorPicker({
                    color: resp.data.color,
                    onShow: function(colpkr) {
                        $(colpkr).fadeIn(500);
                        return false;
                    },
                    onHide: function(colpkr) {
                        $(colpkr).fadeOut(500);
                        return false;
                    },
                    onChange: function(hsb, hex, rgb) {
                        $('input[name=color]').val('#' + hex);
                    }
                });
                $('.colorpicker').css("z-index", "99999999999");

                $('input[id=lefile]').change(function() {
                    $('#photoCover').val($(this).val());
                });
                $('input[id=lefile1]').change(function() {
                    $('#photoCover1').val($(this).val());
                });

            } else {
                popup.msg(resp.message);
            }
        }
    });
};

landing.removelanding = function(id) {
    ajax({
        service: '/cpservice/landing/deletelanding.json',
        data: {id: id},
        type: 'get',
        done: function(resp) {
            popup.msg(resp.message);
        }
    });
};




landing.addCategory = function(landingId) {
    popup.open('popup-add-category', 'Thêm mới danh mục landing', template('/cp/tpl/landing/editcategory.tpl', {landingId: landingId}), [
        {
            title: 'Thêm',
            style: 'btn-info',
            fn: function() {
                ajaxSubmit({
                    service: '/cpservice/landing/addlandingcategory.json',
                    id: 'lcategory-form-edit',
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
};


landing.editCategory = function(id) {
    ajax({
        service: '/cpservice/landing/getcategory.json',
        data: {id: id},
        type: 'get',
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-add-category', 'Sửa danh mục landing', template('/cp/tpl/landing/editcategory.tpl', {data: resp.data}), [
                    {
                        title: 'Lưu',
                        style: 'btn-info',
                        fn: function() {
                            ajaxSubmit({
                                service: '/cpservice/landing/addlandingcategory.json',
                                id: 'lcategory-form-edit',
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
            } else {
                popup.msg(resp.message);
            }
        }
    });

};

landing.changePosition = function(cateId) {
    ajax({
        service: '/cpservice/landing/changeposition.json',
        data: {id: cateId, position: $('input[for=' + cateId + ']').val()},
        type: 'get',
        done: function(resp) {
            popup.msg(resp.message);
        }
    });
};
landing.removeCategory = function(cateId) {
    ajax({
        service: '/cpservice/landing/deletecategory.json',
        data: {id: cateId},
        type: 'get',
        done: function(resp) {
            popup.msg(resp.message);
        }
    });
};

landing.editItem = function(cateId) {
    ajax({
        service: '/cpservice/landing/getitembycate.json',
        data: {id: cateId},
        type: 'get',
        done: function(resp) {
            popup.open('popup-add-item', 'Sửa sản phẩm', template('/cp/tpl/landing/additem.tpl', {cateId: cateId, data: resp.data}), [
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
landing.saveItem = function(cateId) {
    var data = {};
    data.itemId = $('input[name=itemId]').val();
    data.landingCategoryId = cateId;
    ajax({
        service: '/cpservice/landing/addlandingitem.json',
        data: data,
        contentType: 'json',
        type: 'post',
        done: function(resp) {
            if (resp.success) {
                popup.open('crop-image', 'Sửa ảnh sản phẩm', template('/cp/tpl/landing/image.tpl', {data: resp.data, width: 318, height: 318}), [
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
                                service: '/cpservice/landing/cropimage.json',
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

                if ($('.body tr[for]').length <= 0) {
                    $('.body').html('');
                }
                var el = $('tr[for="' + resp.data.id + '"]');
                if (typeof $(el).html() == 'undefined' || $(el).html().trim().length <= 0) {
                    var html = '<tr for="' + resp.data.id + '"><td class="text-center" style="vertical-align: middle;">' + resp.data.item.id + '</td>\
                           <td class="text-center" style="vertical-align: middle;">' + resp.data.item.name + '</td>\
                            <td class = "text-center" style = "vertical-align: middle;" > <input onclick="landing.changeCategoryItemSpecial(this,\'' + resp.data.id + '\');" name="special" for="' + resp.data.id + '_special" type="checkbox" /> </td>';
                    html += '<td class="text-center" style="vertical-align: middle">\
                        <button type="button" class="btn btn-danger" onclick="landing.deleteitem(' + resp.data.id + ');"  ><i class="glyphicon glyphicon-trash"></i> Xóa</button>\
                     </td></tr>';
                    $('.body').prepend(html);
                }
            }
        }
    });
};
landing.deleteitem = function(hItemId) {
    ajax({
        service: '/cpservice/landing/deleteitem.json',
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
landing.changeCategoryItemSpecial = function(el, id) {
    ajax({
        service: '/cpservice/landing/changeitemspecial.json',
        data: {id: id, special: $(el).is(':checked')},
        done: function(resp) {
            if (resp.success) {
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
landing.uploadImageItem = function() {
    var width = $('input[name=widthF]').val();
    var height = $('input[name=heightF]').val();
    ajaxUpload({
        service: '/cpservice/landing/uploadimage.json',
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
landing.downloadImageItem = function() {
    var url = $('input[name=downloadImageItem]').val();
    var targetId = $('input[name=targetId]').val();

    var width = $('input[name=widthF]').val();
    var height = $('input[name=heightF]').val();
    ajax({
        data: {imageUrl: url, targetId: targetId},
        service: "/cpservice/landing/downloadimage.json",
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
