var model = {};
model.init = function(params) {
    //vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị model", "/cp/model.html"],
        ["Danh sách model"]
    ]);
    model.builtCategory(params, 'modelSearch');
};
model.builtCategory = function(params, formId) {
    var htmlselect = '';
    if (params.categoryId === 'undefined' || params.categoryId === null || params.categoryId === '') {
        htmlselect += '<div class="form-group "><select class="form-control cateChose_0" chonse = "0">';
        htmlselect += '<option value="">-- Chọn danh mục --</option>';
        $.each(category, function() {
            htmlselect += '<option value="' + this.id + '">' + this.name + '</option>';
        });
        htmlselect += '</select></div>';
    }
    $.each(params.parentCategorys.cats, function(i) {
        $.each(this, function(j) {
            if (j === 0) {
                htmlselect += '<div class="form-group ">\
                                    <select class="form-control cateChose_' + (i + 1) + '" chonse = "' + (i + 1) + '">';
                htmlselect += '<option value="">-- Chọn danh mục --</option>';
            }
            htmlselect += '<option value="' + this.id + '">' + this.name + '</option>';
        });
        htmlselect += '</select></div>';
    });

    $('#' + formId + ' #selectcategorys').append(htmlselect);
    for (var i in params.parentCategorys.ancestors) {
        $('#' + formId + ' #selectcategorys select option[value=' + params.parentCategorys.ancestors[i].id + ']').attr('selected', 'selected');
    }
    $('#' + formId + ' #categoryId').val(params.categoryId);
    $('#' + formId + ' #selectcategorys select').each(function() {
        $(this).on('change', function() {
            manufacturer.changeParent(parseInt($(this).attr('chonse')), $(this).val(), formId);
            $('#mfDetail').val('');
        });
    });
};

model.loadmf = function(objName) {
    if (objName === 'mfDetail') {
        var cateId = $('#model-add-form input[name=categoryId]').val() === "0" ? "" : $('#model-add-form input[name=categoryId]').val();
        if (cateId === null || cateId === "" || cateId === "0") {
            popup.msg("Danh mục phải được chọn trước");
            return false;
        }
    }
    var resp = new Object();
    resp.objName = objName;
    popup.open('popup-search-mf', 'Tìm kiếm thương hiệu', template('/cp/tpl/model/loadmf.tpl', resp), [
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-search-mf');
            }
        }]);
    model.searchmf('input[name=name]', objName);
};

model.searchmf = function(obj, objName) {
    if (objName === 'mfDetail') {
        var cateId = $('input[name=categoryId]').val() === "0" ? "" : $('input[name=categoryId]').val();
    }
    ajax({
        service: '/cpservice/global/model/searchmf.json',
        loading: false,
        data: {keyword: $(obj).val(), cateId: cateId},
        done: function(resp) {
            if (resp.success) {
                var html = "";
                if (resp.data.dataCount === 0) {
                    html += '<tr><td colspan="3" class="text-center text-danger">Không tìm thấy thương hiệu</td></tr>';
                } else {
                    $.each(resp.data.data, function() {
                        html += '<tr>';
                        html += '<th class="text-center">' + this.id + '</th>';
                        html += '<th class="text-center">' + this.name + '</th>';
                        html += '<th class="text-center"><button type="button" onclick="model.selectmf(\'' + this.id + '\', \'' + objName + '\');" class="btn btn-info">Chọn</button></th>';
                        html += '</tr>';
                    });
                }

                $(".loadmf").html(html);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

model.selectmf = function(idmf, objName) {
    $("#" + objName).val(idmf);
    popup.close('popup-search-mf');
};


model.loadData = function(categoryId) {
    if (categoryId === 'undefined' || categoryId === null || categoryId === "") {
        var html = '<option value="0">-- Chọn danh mục --</option>';
        $.each(category, function() {
            html += '<option value = "' + this.id + '" >' + this.name + '</option>';
        });
        $("select.dcategory_0").html(html);
    } else {

    }
};




model.changeStatus = function(id) {
    ajax({
        service: '/cpservice/model/changestatus.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                var html = "";
                if (resp.data.active == true) {
                    html = '<i onclick="model.changeStatus(\'' + id + '\');"  class="glyphicon glyphicon-check"></i>';
                } else {
                    html = '<i onclick="model.changeStatus(\'' + id + '\');" class="glyphicon glyphicon-unchecked"></i>';
                }
                $(".active_" + id).html(html);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

model.showImages = function(id) {
    ajax({
        service: '/cpservice/global/model/getmodel.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-model-show-images', 'Hình ảnh model', template('/cp/tpl/model/images.tpl', {data: resp.data}), [
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
model.showPropertiesEditFalse = function(id) {
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

model.requestEdit = function(id) {
    ajax({
        service: '/cpservice/global/model/getmodel.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-model-request', 'Yêu cầu chỉnh sửa model', template('/cp/tpl/model/request.tpl', resp), [
                    {
                        title: 'Yêu cầu sửa',
                        style: 'btn-info',
                        fn: function() {
                            ajaxSubmit({
                                service: '/cpservice/model/disapproved.json',
                                id: 'request-model',
                                type: 'post',
                                contentType: 'json',
                                done: function(resp) {
                                    if (resp.success) {
                                        popup.msg(resp.message, function() {
                                            popup.close('popup-model-request');
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
                        title: 'Đóng',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-model-request');
                        }
                    }
                ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

model.index = function() {
    ajax({
        service: '/cpservice/model/index.json',
        done: function(resp) {
            popup.msg(resp.message);
        }
    });
};
model.unindex = function() {
    ajax({
        service: '/cpservice/model/unindex.json',
        done: function(resp) {
            popup.msg(resp.message);
        }
    });
};