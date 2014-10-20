var reviewmodel = {};
reviewmodel.init = function() {
    //vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị model", "/cp/model.html"],
        ["Danh sách model cần duyệt"]
    ]);
};
reviewmodel.showDetailEditFalse = function(id) {
    ajax({
        service: '/cpservice/global/model/getmodel.json',
        data: {id: id},
        done: function(result) {
            if (result.success) {
                popup.open('popup-edit-model', 'Sửa thông tin cơ bản model', template('/cp/tpl/model/add.tpl', result), [
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
                            $('#model-add-form').find('input,select,button').attr("disabled", "disabled");
                        }
                    }
                });
                $('select[name=active]').val(result.data.active === true ? 1 : 2);
                $('#model-add-form').find('input,select,button').attr("disabled", "disabled");
            } else {
                popup.msg(result.message);
            }
        }

    });
};
reviewmodel.showPropertiesEditFalse = function(id) {
    ajax({
        service: '/cpservice/global/model/getproperties.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-model-show-properties', 'Thuộc tính model', template('/cp/tpl/model/properties.tpl', resp), [
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-model-show-properties');
                        }
                    }
                ]);
                item.setSelectedPropertiesValue(resp);
                $('#viewPropertys').find('input,select,button').attr("disabled", "disabled");
            } else {
                popup.msg(resp.message);
            }

        }

    });

};
reviewmodel.reviewEditModel = function(id) {
    popup.open('review-model', 'Duyệt Model', template('/cp/tpl/model/revieweditmodel.tpl'), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function() {
                var messenger = $('#review-model textarea[name=message]').val();
                var idNextUpdate = $('#review-model input[name=nextUpdaterId]').val();
                var status = $('#review-model input[name=status]:checked').val();
                if (status == true) {
                    status = 1;
                } else {
                    status = 2;
                }
                if (messenger === 'undefined' || messenger === '' || messenger === null) {
                    $('#review-model textarea[name=message]').parents('.form-group').addClass('has-error');
                    $('#review-model textarea[name=message]').focus();
                    return false;
                } else {
                    $('#review-model textarea[name=message]').parents('.form-group').removeClass('has-error');
                }
                popup.confirm('Bạn đã chắc chắn thao tác duyệt xong Model?', function() {
                    ajaxSubmit({
                        service: '/cpservice/reviewmodel/revieweditmodel.json',
                        data: {modelId: id, message: messenger, status: status, nextUpdaterId: idNextUpdate},
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
                popup.close('review-model');
            }
        }
    ]);

    var tmt = true;
    $('#review-model input[name=status][value=false]').on("click", function() {
        ajax({
            service: '/cpservice/reviewmodel/getdetailmodellog.json',
            data: {id: id},
            loading: false,
            done: function(resp) {
                if (resp.success) {
                    var html = '<input name="nextUpdaterId" type="text" class="form-control col-sm-5" value="' + resp.data + '" placeholder="Nhập email người sửa lại" title="Người sửa trước đó"/> ';
                    if (tmt === true) {
                        $('#noReview').append(html);
                        tmt = false;
                    }
                }
            }
        });



    });

    $('#review-model input[name=status][value=true]').on("click", function() {
        $('input[name=nextUpdaterId]').remove();
        tmt = true;
    });

};

reviewmodel.showImages = function(id) {
    ajax({
        service: '/cpservice/global/model/getmodel.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-model-show-images', 'Hình ảnh model', template('/cp/tpl/model/imagesEdit.tpl', {rootImage: imageUrl, data: resp.data}), [
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