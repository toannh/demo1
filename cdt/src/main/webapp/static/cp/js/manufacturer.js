manufacturer = {};

manufacturer.init = function(params) {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị thương hiệu", "/cp/manufacturer.html"],
        ["Danh sách thương hiệu"]
    ]);
    var htmlselect = '';
    htmlselect += '<div class="form-group "><select class="form-control cateChose_0" chonse = "0">';
    htmlselect += '<option value="">-- Chọn danh mục --</option>';
    $.each(cate, function() {
        htmlselect += '<option value="' + this.id + '">' + this.name + '</option>';
    });
    htmlselect += '</select></div>';

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

    $('#selectcategorys').append(htmlselect);


    for (var i in params.parentCategorys.ancestors) {
        $('#selectcategorys select option[value=' + params.parentCategorys.ancestors[i].id + ']').attr('selected', 'selected');
    }
    $('#categoryId').val(params.categoryId);
    $('#selectcategorys select').each(function() {
        $(this).on('change', function() {
            manufacturer.changeParent(parseInt($(this).attr('chonse')), $(this).val(), 'mnSearch');
        });
    });
};
manufacturer.changeParent = function(i, id, formId) {
    var element = $('#' + formId + ' #selectcategorys .cateChose_' + i);
    if (id !== "") {
        ajax({
            service: '/cpservice/global/category/getchilds.json',
            data: {id: id},
            loading: false,
            done: function(resp) {
                if (!resp.success)
                    popup.msg(resp.message);
                else {
                    $(element).parent().nextAll().remove();
                    if (resp.data.length > 0) {
                        var html = '<div class="form-group ">\
                                <select class="form-control cateChose_' + (i + 1) + '" chonse = "' + (i + 1) + '">';
                        html += '<option value="">-- Chọn danh mục --</option>';
                        $.each(resp.data, function() {
                            html += '<option value="' + this.id + '">' + this.name + '</option>';
                        });
                        html += '</select></div>';
                        $('#' + formId + ' #selectcategorys').append(html);
                        $('#' + formId + ' #selectcategorys select').each(function() {
                            $(this).on('change', function() {
                                manufacturer.changeParent(parseInt($(this).attr('chonse')), $(this).val(), formId);
                            });
                        });
                    }
                }
                if (parseInt(id) > 0) {
                    $('#' + formId + ' #categoryId').val(id);
                } else {
                    $('#' + formId + ' #categoryId').val($('.cateChose_' + (i - 1)).val());
                }
            }
        });
    } else {
        $(element).parent().nextAll().remove();
        $('#' + formId + ' #categoryId').val($(element).prev().prev().find('select').val());
    }
};

manufacturer.del = function(id) {
    popup.confirm("Bạn có chắc chắn muốn xóa thương hiệu này?", function() {
        ajax({
            service: '/cpservice/manufacturer/del.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    popup.msg("Xóa thành công");
                    var trDel = $('tr[for=' + id + ']');
                    trDel.remove();
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    }
    );
};

manufacturer.add = function() {
    popup.open('popup-manufacture', 'Thêm Thương hiệu mới', template('/cp/tpl/manufacturer/edit.tpl'), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function() {
                ajaxUpload({
                    service: '/cpservice/manufacturer/add.json',
                    id: 'manuForm',
                    done: function(resp) {
                        if (resp.success) {
                            popup.msg("Thêm mới thành công", function() {
                                popup.close('popup-manufacture');
                                location.reload();
                            });
                        }
                    }
                });
            }
        },
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-manufacture');
            }
        }
    ]);
    $('input[id=lefile]').change(function() {
        $('#photoCover').val($(this).val());
    });
};

manufacturer.edit = function(id) {
    ajax({
        service: '/cpservice/global/manufacturer/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-manufacture', 'Sửa thương hiệu', template('/cp/tpl/manufacturer/edit.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            ajaxUpload({
                                service: '/cpservice/manufacturer/edit.json',
                                id: 'manuForm',
                                done: function(result) {
                                    if (result.success) {
                                        popup.msg("Sửa thành công", function() {
                                            popup.close('popup-manufacture');
                                            location.reload();
                                        });
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-manufacture');
                        }
                    }
                ]);
                $('#popup-manufacture input[name=global]').attr("checked", resp.data.global);
                $('#popup-manufacture input[name=active]').attr("checked", resp.data.active);
                $('input[id=lefile]').change(function() {
                    $('#photoCover').val($(this).val());
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

manufacturer.active = function(id) {
    var oldIcon = $('a[for=active_' + id + ']').html();
    $('a[for=active_' + id + ']').html('');
    ajax({
        service: '/cpservice/manufacturer/active.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                if (resp.data.active) {
                    $('a[for=active_' + id + ']').html('<img src="' + staticUrl + '/cp/img/icon-enable.png">');
                } else {
                    $('a[for=active_' + id + ']').html('<img src="' + staticUrl + '/cp/img/icon-disable.png">');
                }
            } else {
                $('a[for=active_' + id + ']').html(oldIcon);
                popup.msg('Không thể thay đổi trạng thái hoạt động của thương hiệu này');
            }
        }
    });
};

manufacturer.showMap = function(id) {
    ajax({
        service: '/cpservice/global/manufacturer/getmaps.json',
        data: {id: id},
        done: function(resp) {
            popup.open('popup-map', 'Map Thương hiệu vào danh mục', template('/cp/tpl/manufacturer/map.tpl', resp), [
                {
                    title: 'Hoàn tất',
                    style: 'btn-primary',
                    fn: function() {
                        popup.close('popup-map');
                    }
                }
            ]);
            $('input#manuId').val(id);
        }
    });
};

manufacturer.addMap = function() {
    var manuId = $('input#manuId').val();
    var cateId = $('input#cateId').val();
    var cateName = $('input#cateName').val();
    ajax({
        service: '/cpservice/manufacturer/addmap.json',
        data: {manuId: manuId, cateId: cateId},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                var newRow = '<tr for="' + cateId + '"><td>' + cateName + '</td> \
                                <td><button class="btn btn-danger" onclick="manufacturer.delMap(\'' + cateId + '\')">Xóa</button></td></tr>';
                $("#mapList tr").first().after(newRow);
                var el = $('div #cateTree').find('select[for=0]');
                el.val("");
                $('input#cateId').val('');
                $('input#cateName').val('');
                $('span#btnMap').hide();
                el.nextAll().remove();
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

manufacturer.delMap = function(cateId) {
    var manuId = $('input#manuId').val();
    ajax({
        service: '/cpservice/manufacturer/delmap.json',
        data: {manuId: manuId, cateId: cateId},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                $('#mapList tr[for=' + cateId + ']').remove();
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
manufacturer.selectCate = function(el) {
    var id = $(el).find(':selected').val();
    var cateName = $(el).find(':selected').text();
    var level = $(el).attr('for');
    $('input#cateId').val(id);
    $('input#cateName').val(cateName);
    var el = $('div #cateTree').find('select[for=' + level + ']');
    if (id == 0) {
        el.nextAll().remove();
        return false;
    }
    ajax({
        service: '/cpservice/global/category/getchilds.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                if (resp.data !== null && resp.data.length > 0) {
                    manufacturer.buildCateTree(resp.data, level);
                }
                $('span#btnMap').css('display', 'block');
            }
        }
    });
};

manufacturer.buildCateTree = function(data, level) {
    var el = $('div #cateTree').find('select[for=' + level + ']');
    el.nextAll().remove();
    level = parseInt(level) + 1;
    var html = '<label class="control-label col-md-12" style="text-align:center"><span class="glyphicon glyphicon-chevron-down"></span></label>';
    html += '<select class="form-control cateTree" for="' + level + '" onchange="manufacturer.selectCate(this)">   \
                    <option value="0">Chọn danh mục</option>';
    $.each(data, function() {
        var leaf = '';
        if (this.leaf)
            leaf = 'leaf="1"';
        html += '<option value="' + this.id + '"' + leaf + '>' + this.name + '</option>';
    });
    html += '</select>';
    $('div #cateTree').append(html);
};

manufacturer.index = function() {
    ajax({
        service: '/cpservice/manufacturer/index.json',
        loading: false,
        done: function(resp) {
            popup.msg(resp.message);
        }
    });
};

manufacturer.unindex = function() {
    ajax({
        service: '/cpservice/manufacturer/unindex.json',
        done: function(resp) {
            popup.msg(resp.message);
        }
    });
};
