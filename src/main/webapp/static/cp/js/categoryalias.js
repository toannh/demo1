/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
categoryalias = {};

categoryalias.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị danh mục trang chủ", "/cp/administrator.html"],
        ["Danh sách danh mục trang chủ"]
    ]);
};
categoryalias.add = function() {
    popup.open('popup-add-alias', 'Thêm mới alias', template('/cp/tpl/categoryalias/edit.tpl', {categories: categories}), [
        {
            title: 'Lưu',
            style: 'btn-primary',
            fn: function() {
                ajaxUpload({
                    service: '/cpservice/categoryalias/add.json',
                    id: 'aliasForm',
                    contentType: "json",
                    done: function(resp) {
                        if (resp.success) {
                            popup.close('popup-add-alias');
                            location.reload();
                        } else {
                            popup.msg(resp.message);
                        }
                    }
                });
            }
        },
        {
            title: 'Bỏ qua',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-add-alias');
            }
        }
    ]);
    $('input[id=lefile]').change(function() {
        $('#photoCover').val($(this).val());
    });
    $('input[id=lefile1]').change(function() {
        $('#photoCover1').val($(this).val());
    });
    $('input[id=lefile2]').change(function() {
        $('#photoCover2').val($(this).val());
    });
    $('input[id=lefile3]').change(function() {
        $('#photoCover3').val($(this).val());
    });
};
categoryalias.edit = function(id) {
    ajax({
        service: '/cpservice/categoryalias/get.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-edit-alias', 'Sửa chi tiết alias', template('/cp/tpl/categoryalias/edit.tpl', {categories: categories, data: resp.data}), [
                    {
                        title: 'Lưu',
                        style: 'btn-primary',
                        fn: function() {
                            ajaxUpload({
                                service: '/cpservice/categoryalias/edit.json',
                                id: 'aliasForm',
                                contentType: "json",
                                done: function(resp) {
                                    if (resp.success) {
                                        popup.close('popup-edit-alias');
                                        location.reload();
                                    } else {
                                        popup.msg(resp.message);
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: 'Bỏ qua',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-edit-alias');
                        }
                    }
                ]);
                $('input[id=lefile]').change(function() {
                    $('#photoCover').val($(this).val());
                });
                $('input[id=lefile1]').change(function() {
                    $('#photoCover1').val($(this).val());
                });
                $('input[id=lefile2]').change(function() {
                    $('#photoCover2').val($(this).val());
                });
                $('input[id=lefile3]').change(function() {
                    $('#photoCover3').val($(this).val());
                });
            }
        }
    });
};
categoryalias.editTopics = function(id) {
    ajax({
        service: '/cpservice/categoryalias/get.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                var category = {};
                $.each(categories, function() {
                    if (this.id === resp.data.categoryId) {
                        category = this;
                    }
                });
                popup.open('popup-edit-topic', 'Đổi alias topic tại danh mục: ' + category.name, template('/cp/tpl/categoryalias/edittopic.tpl', resp), [
                    {
                        title: 'Lưu',
                        style: 'btn-primary',
                        fn: function() {
                            ajaxUpload({
                                service: '/cpservice/categoryalias/edittopics.json',
                                id: 'aliasForm',
                                contentType: "json",
                                done: function(resp) {
                                    if (resp.success) {
                                        popup.close('popup-edit-topic');
                                        location.reload();
                                    } else {
                                        popup.msg(resp.message);
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: 'Bỏ qua',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-edit-topic');
                        }
                    }
                ]);
                $('input[for=lefile]').change(function() {
                    $('#photoCover' + $(this).attr('pos')).val($(this).val());
                });
            }
        }
    });
};
categoryalias.editManufacturers = function(id) {
    ajax({
        service: '/cpservice/categoryalias/get.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                var category = {};
                $.each(categories, function() {
                    if (this.id === resp.data.categoryId) {
                        category = this;
                    }
                });
                var manufIds = '';
                if (resp.data !== null && resp.data.manufacturerIds !== null && resp.data.manufacturerIds !== 'undefined') {
                    $.each(resp.data.manufacturerIds, function() {
                        manufIds += this + ",";
                    });
                }

                popup.open('popup-edit-manufacturer', 'Đổi alias thương hiệu tại danh mục: ' + category.name, template('/cp/tpl/categoryalias/editmanufacturer.tpl', {manufIds: manufIds}), [
                    {
                        title: 'Lưu',
                        style: 'btn-primary',
                        fn: function() {

                            manufIds = $('input[name=manufacturerIds]').val();
                            var ids = [];
                            $.each(manufIds.split(","), function() {
                                if (this !== null && this !== 'undefined' && this.trim() !== '') {
                                    ids.push(this.trim());
                                }
                            });

                            ajax({
                                service: '/cpservice/categoryalias/editmanufacturers.json',
                                data: {id: id, manufacturerIds: ids},
                                contentType: "json",
                                type: "post",
                                done: function(resp) {
                                    if (resp.success) {
                                        popup.close('popup-edit-manufacturer');
                                        location.reload();
                                    } else {
                                        popup.msg(resp.message);
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: 'Bỏ qua',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-edit-manufacturer');
                        }
                    }
                ]);
            }
        }
    });
};
categoryalias.timeout = null;
categoryalias.loadmf = function(objName, formId) {
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
    if (categoryalias.timeout !== null) {
        clearTimeout(categoryalias.timeout);
    }
    $("#manufName").keyup(function() {
        categoryalias.timeout = setTimeout(function() {
            categoryalias.searchmf('#manufName', objName, formId);
        }, 500);
    });

};

categoryalias.searchmf = function(obj, objName, formId) {
    var data = {};
    data.keyword = $(obj).val();
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
                        html += '<th class="text-center"><button type="button" onclick="categoryalias.selectmf(\'' + this.id + '\', \'' + objName + '\', \'' + formId + '\');" class="btn btn-info">Chọn</button></th>';
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

categoryalias.selectmf = function(idmf, objName, formId) {
    $("#" + formId + ' input[name=' + objName + ']').val($("#" + formId + ' input[name=' + objName + ']').val() + idmf + ",");
    popup.close('popup-search-mf');
};

categoryalias.changePosition = function(aliasId, position) {
    ajax({
        service: '/cpservice/categoryalias/editposition.json',
        loading: true,
        data: {aliasId: aliasId, position: position},
        done: function(resp) {
            if (resp.success) {
                location.reload();
            } else {
                popup.msg(resp.message);
            }
        }
    });
};