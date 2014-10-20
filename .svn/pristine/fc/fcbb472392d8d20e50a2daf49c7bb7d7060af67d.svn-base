var editmodel = {};
editmodel.init = function() {
    //vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị model", "/cp/model.html"],
        ["Danh sách model đã sửa"]
    ]);
};
editmodel.edit = function() {
    popup.open('popup-add-model', 'Thêm mới thông tin cơ bản model', template('/cp/tpl/model/add.tpl', {category: category}), [
        {
            title: 'Tạo mới',
            style: 'btn-info',
            fn: function() {
                ajaxSubmit({
                    service: '/cpservice/editmodel/edit.json',
                    id: 'model-add-form',
                    contentType: 'json',
                    done: function(resp) {
                        if (resp.success) {
                            popup.msg(resp.message, function() {
                                popup.close('popup-add-model');
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
                popup.close('popup-add-model');
            }
        }
    ]);
    var params = {};
    params.categoryId = '';
    params.parentCategorys = [];
    params.parentCategorys.cats = [];
    params.parentCategorys.ancestors = [];

    model.builtCategory(params, 'model-add-form');
};

editmodel.addImage = function() {
    ajaxUpload({
        service: '/cpservice/editmodel/addimage.json',
        id: 'image-model',
        done: function(resp) {
            if (resp.success) {
                var imghtml = '';
                for (var i in resp.data) {
                    imghtml += '<li  style="float: left; list-style: none; padding: 0px 5px; margin: 0px 5px; height: 100px; width: 100px; margin-bottom: 50px;" for="' + i + '">\
                                    <a style="cursor:pointer" >\
                                        <img height="100px" width="100px" src="' + resp.data[i] + '" class="grayscale" title="image ' + i + '" alt="Image ' + i + '">\
                                        <div align="center" title="Xóa ảnh này" onclick="editmodel.deleteImage(\'' + resp.data[i] + '\', ' + i + ')">\
                                        <span style="margin-top:10px; text-align:center" class="glyphicon glyphicon-trash"></span> xóa\</div>\
                                    </div></a></li>';
                }
                imghtml += '<div class="clearfix"></div>';
                $('#imageModel').html(imghtml);
                $('#imageModel').nextAll().remove();
                $('input[name=imageUrl]').val('');
                $('input[name=image]').val('');
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

editmodel.deleteImage = function(name, i) {
    popup.confirm('Bạn có chắc chắn muốn xóa?', function() {
        ajax({
            service: '/cpservice/editmodel/delimage.json',
            data: {
                id: $('#image-model input[name=id]').val(),
                name: name
            },
            loading: false,
            done: function(resp) {
                if (resp.success) {
                    $('li[for=' + i + ']').remove();
                    if (resp.data.images.length < 1) {
                        $('#imageModel').parent().append('<div class="nodata" style="color: red; margin:10px 150px;">Hiện tại model này chưa có ảnh</div><div class="clearfix"></div>');
                    }
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

editmodel.editDetail = function(id) {
    ajax({
        service: '/cpservice/global/model/getmodel.json',
        data: {id: id},
        done: function(result) {
            if (result.success) {
                popup.open('popup-edit-model', 'Sửa thông tin cơ bản model', template('/cp/tpl/model/add.tpl', result), [
                    {
                        title: 'Lưu',
                        style: 'btn-primary',
                        fn: function() {
                            ajaxSubmit({
                                service: '/cpservice/editmodel/edit.json',
                                id: 'model-add-form',
                                contentType: 'json',
                                done: function(resp) {
                                    if (resp.success) {
                                        popup.msg(resp.message, function() {
                                            popup.close('popup-edit-model');
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
                            popup.close('popup-edit-model');
                        }
                    }
                ]);
                ajax({
                    service: '/cpservice/global/category/getancestors.json',
                    data: {id: result.data.categoryId},
                    done: function(resp) {
                        if (resp.success) {
                            var params = {};
                            params.categoryId = result.data.categoryId;
                            params.parentCategorys = [];
                            params.parentCategorys.cats = resp.data.cats;
                            params.parentCategorys.ancestors = resp.data.ancestors;

                            model.builtCategory(params, 'model-add-form');
                        }
                    }
                });
                $('select[name=active]').val(result.data.active === true ? 1 : 2);
            } else {
                popup.msg(result.message);
            }
        }
    });
};
editmodel.successEdit = function(id) {
    popup.open('popup-category-add', 'Báo cáo đã sửa', template('/cp/tpl/model/successadd.tpl'), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function() {
                var messenger = $('#request-model textarea[name=message]').val();
                if (messenger === 'undefined' || messenger === '' || messenger === null) {
                    $('#request-model textarea[name=message]').parents('.form-group').addClass('has-error');
                    $('#request-model textarea[name=message]').focus();
                    return false;
                }
                popup.confirm('Bạn đã chắc chắn sửa xong Model?', function() {
                    ajaxSubmit({
                        service: '/cpservice/editmodel/successedit.json',
                        data: {modelId: id, message: messenger},
                        type: 'post',
                        contentType: 'json',
                        done: function(resp) {
                            if (resp.success) {
                                popup.msg(resp.message);
                                location.reload();
                            } else {
                                popup.msg(resp.message);
                            }
                        }
                    });
                });
            }
        },
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-category-add');
            }
        }
    ]);


};
editmodel.editProperties = function(id) {
    ajax({
        service: '/cpservice/global/model/getproperties.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-model-show-properties', 'Thuộc tính model', template('/cp/tpl/model/properties.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            var list = reviewitem.getPropertiesValues(resp);
                            ajax({
                                service: '/cpservice/editmodel/editproperties.json',
                                contentType: 'json',
                                data: {id: id, propertys: JSON.stringify(list)},
                                type: 'post',
                                done: function(resp) {
                                    if (resp.success) {
                                        popup.msg(resp.message, function() {
                                            popup.close('popup-model-show-properties');

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
                            popup.close('popup-model-show-properties');
                        }
                    }
                ]);

                item.setSelectedPropertiesValue(resp);
            } else {
                popup.msg(resp.message);
            }

        }

    });

};
editmodel.showImages = function(id) {
    ajax({
        service: '/cpservice/global/model/getmodel.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-model-show-images', 'Hình ảnh model', template('/cp/tpl/model/modelEditImagesEdit.tpl', {data: resp.data}), [
                    {
                        title: 'Đóng',
                        style: 'btn-info',
                        fn: function() {
                            popup.close('popup-model-show-images');
                        }
                    }
                ]);

            } else {
                popup.msg(resp.message);
            }

        }
    });
};

editmodel.editDetail = function(id) {
    ajax({
        service: '/cpservice/global/model/getmodel.json',
        data: {id: id},
        done: function(result) {
            if (result.success) {
                popup.open('popup-edit-model', 'Sửa thông tin cơ bản model', template('/cp/tpl/model/add.tpl', result), [
                    {
                        title: 'Lưu',
                        style: 'btn-primary',
                        fn: function() {
                            ajaxSubmit({
                                service: '/cpservice/editmodel/edit.json',
                                id: 'model-add-form',
                                contentType: 'json',
                                done: function(resp) {
                                    if (resp.success) {
                                        popup.msg(resp.message, function() {
                                            popup.close('popup-edit-model');
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
                            popup.close('popup-edit-model');
                        }
                    }
                ]);
                ajax({
                    service: '/cpservice/global/category/getancestors.json',
                    data: {id: result.data.categoryId},
                    done: function(resp) {
                        if (resp.success) {
                            var params = {};
                            params.categoryId = result.data.categoryId;
                            params.parentCategorys = [];
                            params.parentCategorys.cats = resp.data.cats;
                            params.parentCategorys.ancestors = resp.data.ancestors;

                            model.builtCategory(params, 'model-add-form');
                        }
                    }
                });
                $('select[name=active]').val(result.data.active === true ? 1 : 2);
            } else {
                popup.msg(result.message);
            }
        }
    });
};
