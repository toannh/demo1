/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
item = {};

item.init = function(params) {
    //vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị sản phẩm", "/cp/item.html"],
        ["Danh sách sản phẩm"]
    ]);
    $('.timeselect').timeSelect();
    model.builtCategory(params, 'itemSearch');
};
item.timeout = null;
item.loadmf = function(objName, option, formId) {
    var cateId = '';
    if (option === 'edit') {
        cateId = $('#' + formId + ' input[name=categoryId]').val();
        if (cateId === 'undefined' || cateId === null || cateId === '') {
            popup.msg("Danh mục phải được chọn");
            return false;
        }
    }
    var resp = new Object();
    resp.message = "Không tìm thấy thương hiệu";
    popup.open('popup-search-mf', 'Tìm kiếm thương hiệu', template('/cp/tpl/item/loadmf.tpl', resp), [
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-search-mf');
            }
        }]);
    if (item.timeout !== null) {
        clearTimeout(item.timeout);
    }
    $("#manufName").keyup(function() {
        item.timeout = setTimeout(function() {
            item.searchmf('#manufName', objName, formId, cateId);
        }, 500);
    });

};

item.searchmf = function(obj, objName, formId, cateId) {
    var data = {};
    data.keyword = $(obj).val();
    if (cateId !== 'undefined' && cateId !== null && cateId !== '') {
        data.cateId = cateId;
    }
    ajax({
        service: '/cpservice/global/model/searchmf.json',
        loading: false,
        data: data,
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
                        html += '<th class="text-center"><button type="button" onclick="item.selectmf(\'' + this.id + '\', \'' + objName + '\', \'' + formId + '\');" class="btn btn-info">Chọn</button></th>';
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

item.selectmf = function(idmf, objName, formId) {
    $("#" + formId + ' input[name=' + objName + ']').val(idmf);
    popup.close('popup-search-mf');
};
item.loadmodel = function(objName, option, formId) {
    var cateId = '';
    var manufId = '';
    if (option === 'edit') {
        cateId = $('#' + formId + ' input[name=categoryId]').val();
        manufId = $('#' + formId + ' input[name=manufacturerId]').val();
        if (manufId === 'undefined' || manufId === null || manufId === '') {
            popup.msg("Thương hiệu phải được chọn trước");
            return false;
        }
    }

    var resp = new Object();
    resp.message = "Không tìm thấy model";
    popup.open('popup-search-mf', 'Tìm kiếm model', template('/cp/tpl/item/loadmf.tpl', resp), [
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-search-mf');
            }
        }]);

    if (item.timeout !== null) {
        clearTimeout(item.timeout);
    }
    $("#manufName").keyup(function() {
        item.timeout = setTimeout(function() {
            item.searchmodel('#manufName', objName, cateId, manufId, formId);
        }, 500);
    });

};

item.searchmodel = function(obj, objName, cateId, manufId, formId) {
    var data = {};
    data.keyword = $(obj).val();
    if (cateId !== 'undefined' && cateId !== null && cateId !== '') {
        data.categoryId = cateId;
    }
    if (manufId !== 'undefined' && manufId !== null && manufId !== '') {
        data.manufacturerId = manufId;
    }
    ajax({
        service: '/cpservice/global/model/searchmodel.json',
        loading: false,
        data: data,
        done: function(resp) {
            if (resp.success) {
                var html = "";
                if (resp.data.dataCount === 0) {
                    html += '<tr><td colspan="3" class="text-center text-danger">Không tìm thấy model</td></tr>';
                } else {
                    $.each(resp.data.data, function() {
                        html += '<tr>';
                        html += '<th class="text-center">' + this.id + '</th>';
                        html += '<th class="text-center">' + this.name + '</th>';
                        html += '<th class="text-center"><button type="button" onclick="item.selectmf(\'' + this.id + '\', \'' + objName + '\', \'' + formId + '\');" class="btn btn-info">Chọn</button></th>';
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


item.loadseller = function(objName, formId) {
    var resp = new Object();
    resp.message = "Không tìm thấy người bán";
    popup.open('popup-search-mf', 'Tìm kiếm người bán', template('/cp/tpl/item/loadmf.tpl', resp), [
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-search-mf');
            }
        }]);

    if (item.timeout !== null) {
        clearTimeout(item.timeout);
    }
    $("#manufName").keyup(function() {
        item.timeout = setTimeout(function() {
            item.searchseller('#manufName', objName, formId);
        }, 500);
    });

};

item.searchseller = function(obj, objName, formId) {
    var data = {};
    data.keyword = $(obj).val();
    ajax({
        service: '/cpservice/global/user/searchuser.json',
        loading: false,
        data: data,
        done: function(resp) {
            if (resp.success) {
                var html = "";
                if (resp.data.dataCount === 0) {
                    html += '<tr><td colspan="3" class="text-center text-danger">Không tìm thấy seller</td></tr>';
                } else {
                    $.each(resp.data.data, function() {
                        html += '<tr>';
                        html += '<th class="text-center">' + this.id + '</th>';
                        html += '<th class="text-center">' + this.username + '</th>';
                        html += '<th class="text-center"><button type="button" onclick="item.selectmf(\'' + this.id + '\', \'' + objName + '\', \'' + formId + '\');" class="btn btn-info">Chọn</button></th>';
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


item.viewProperties = function(itemId) {
    ajax({
        service: '/cpservice/global/item/getproperties.json',
        data: {id: itemId},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-item-show-properties', 'Thuộc tính sản phẩm', template('/cp/tpl/model/properties.tpl', resp), [
                    {
                        title: 'Đóng',
                        style: 'btn-info',
                        fn: function() {
                            popup.close('popup-item-show-properties');
                        }
                    }]);
                item.setSelectedPropertiesValue(resp);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
item.setSelectedPropertiesValue = function(resp) {
    $.each(resp.data.categoryProperties, function(i) {
        $.each(resp.data.properties, function(j) {
            if (resp.data.categoryProperties[i].type === 'INPUT') {
                $.each($('#viewPropertys input[for=' + resp.data.categoryProperties[i].id + ']'), function() {
                    if (resp.data.categoryProperties[i].id === resp.data.properties[j].categoryPropertyId) {
                        $(this).val(resp.data.properties[j].inputValue);
                    }
                });
            }
            if (resp.data.categoryProperties[i].type === 'MULTIPLE') {
                var string = '';
                $.each($('#viewPropertys .row[for=' + resp.data.categoryProperties[i].id + ']').find('input[type="checkbox"]'), function() {
                    if (resp.data.categoryProperties[i].id === resp.data.properties[j].categoryPropertyId) {
                        string = resp.data.properties[j].categoryPropertyValueIds;
                    }
                });
                $.each(string, function(n) {
                    $('#viewPropertys .row input[type=checkbox][for=' + string[n] + ']').prop('checked', true);
                });
            }
            if (resp.data.categoryProperties[i].type === 'SINGLE_OR_INPUT') {
                $.each($('#viewPropertys select[name=selectinput][for=' + resp.data.categoryProperties[i].id + ']'), function() {
                    if (resp.data.categoryProperties[i].id === resp.data.properties[j].categoryPropertyId) {
                        var inputVal = resp.data.properties[j].inputValue;
                        if (inputVal !== null && inputVal !== 'undefined' && inputVal !== '') {
                            $('#viewPropertys input[name=inputselect][for=' + resp.data.categoryProperties[i].id + ']').val(inputVal);
                            $('#viewPropertys select[name=selectinput][for=' + resp.data.categoryProperties[i].id + ']').find('option[value=' + resp.data.properties[j].categoryPropertyValueIds + ']').removeAttr('selected', 'selected');
                        }
                    }
                    if (resp.data.categoryProperties[i].id === resp.data.properties[j].categoryPropertyId) {
                        $('#viewPropertys select[name=selectinput][for=' + resp.data.categoryProperties[i].id + ']').find('option[value=' + resp.data.properties[j].categoryPropertyValueIds + ']').attr('selected', 'selected');
                    }
                });
            }
            if (resp.data.categoryProperties[i].type === 'SINGLE') {
                $.each($('#viewPropertys select[for=' + resp.data.categoryProperties[i].id + '][name=selectPro]'), function() {
                    if (resp.data.categoryProperties[i].id === resp.data.properties[j].categoryPropertyId) {
                        $('#viewPropertys select[for=' + resp.data.categoryProperties[i].id + '][name=selectPro]').find('option[value=' + resp.data.properties[j].categoryPropertyValueIds + ']').attr('selected', 'selected');
                    }
                });
            }

        });
    });
};

item.del = function(itemId) {
    popup.confirm("Bạn có chắc chắn muốn xóa sản phẩm này?", function() {
        ajax({
            service: '/cpservice/item/del.json',
            data: {id: itemId},
            done: function(resp) {
                if (resp.success) {
                    popup.msg("Xóa thành công")
                    var trDel = $('tr[for=' + itemId + ']');
                    trDel.remove();
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

item.showImages = function(id) {
    ajax({
        service: '/cpservice/global/item/getitem.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-item-show-images', 'Ảnh sản phẩm', template('/cp/tpl/item/images.tpl', {data: resp.data}), [
                    {
                        title: 'Đóng',
                        style: 'btn-info',
                        fn: function() {
                            popup.close('popup-item-show-images');
                        }
                    }
                ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
item.disapprove = function(itemId) {
    popup.open('disapproved-item', 'Yêu cầu sửa sản phẩm', template('/cp/tpl/model/successadd.tpl', null), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function() {
                var messenger = $('#request-model textarea[name=message]').val()
                ajaxSubmit({
                    service: '/cpservice/item/disapproved.json',
                    data: {itemId: itemId, message: messenger},
                    type: 'post',
                    contentType: 'json',
                    done: function(resp) {
                        if (resp.success) {
                            popup.msg(resp.message, function() {
                                popup.close('disapproved-item');
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
                popup.close('disapproved-item');
            }
        }
    ]);
};
item.index = function() {
    ajax({
        service: '/cpservice/item/index.json',
        done: function(resp) {
            popup.msg(resp.message);
        }
    });
};
item.unindex = function() {
    ajax({
        service: '/cpservice/item/unindex.json',
        done: function(resp) {
            popup.msg(resp.message);
        }
    });
};

