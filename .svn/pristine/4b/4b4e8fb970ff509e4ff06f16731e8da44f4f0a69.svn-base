/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
category = {};
category.id;
category.ancestors;
category.properties = {};
category.currentManuId = 0;
category.currentMapCate = '';
category.manufacturer;

category.init = function () {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị danh mục sản phẩm", "/cp/category.html"],
        ["Danh sách danh mục"]
    ]);
};

category.buildAncestors = function (id, e, action) {
    ajax({
        service: '/cpservice/global/category/getancestors.json',
        data: {id: id},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                category.ancestors = resp.data.ancestors;
                if (!resp.data.cate.leaf) {
                    resp.data.cats.splice(resp.data.cate.level);
                }
                var htmlShow = '<label class="control-label col-sm-4">Danh mục cha: </label>';
                htmlShow += '<div class="col-sm-8">';
                $.each(resp.data.cats, function (i) {
                    var count = 0;
                    $.each(this, function (j) {
                        if (count === 0 && j === 0) {
                            if (i === 0) {
                                htmlShow += '<select class="form-control cateChose' + i + '" chonse="' + i + '" level="' + this.level + '">';
                                htmlShow += '<option value="0">None</option>';
                            }
                            if (i > 0) {
                                htmlShow += '<div class="chosen glyphicon">';
                                htmlShow += '<span style="margin:0 150px;" forselect="' + i + '"  class="glyphicon glyphicon-chevron-down"></span></div>';
                                htmlShow += '<br/><select chonse="' + i + '" class="form-control cateChose' + i + '" level="' + this.level + '" parentId="' + this.parentId + '">';
                                htmlShow += '<option value="0">None</option>';
                            }
                            count++;
                        }
                        if (this.name && typeof this.name !== "undefined") {
                            htmlShow += '<option value="' + this.id + '">' + this.name + '</option>';
                        }
                    });
                    htmlShow += '</select>';
                });
                htmlShow += '</div>';
                $('#' + e + ' .ancestors').html(htmlShow);
                for (var i in category.ancestors) {
                    if (action === 'add') {
                        $('.ancestors select option[value=' + category.ancestors[i].id + ']').attr('selected', 'selected');
                    } else {
                        if (category.ancestors[i].id !== id) {
                            $('.ancestors select option[value=' + category.ancestors[i].id + ']').attr('selected', 'selected');
                        }
                    }
                    if (category.ancestors.length > 1 && i === category.ancestors.length - 1) {
                        $('#category-form-edit input[name=parentId]').val(category.ancestors[i - 1].id);
                    }
                    if (category.ancestors.length === 1) {
                        $('#category-form-edit input[name=parentId]').val(category.ancestors[i].id);
                    }
                }
                $('#' + e + ' .ancestors select').each(function () {
                    $(this).on('change', function () {
                        category.changeParent(parseInt($(this).attr('chonse')), e, $(this).val());
                    });
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

category.changeParent = function (i, e, id) {
    var element = $('#' + e + ' .ancestors .cateChose' + i);
    if (id !== 0) {
        ajax({
            service: '/cpservice/global/category/getchilds.json',
            data: {id: id},
            done: function (rs) {
                if (!rs.success)
                    popup.msg(rs.message);
                else {
                    $(element).nextAll().remove();
                    if (rs.data.length > 0) {
                        var html = '<div class="chosen glyphicon">';
                        var count = 0;
                        html += '<span style="margin:0 150px;" forselect="' + (i + 1) + '"  class="glyphicon glyphicon-chevron-down"></span></div>';
                        $.each(rs.data, function () {
                            if (count === 0) {
                                html += '<br/><select chonse="' + (i + 1) + '" class="form-control cateChose' + (i + 1) + '" level="' + this.level + '" parentId="' + this.parentId + '">';
                                html += '<option value="0">None</option>';
                                count++;
                            }
                            html += '<option value="' + this.id + '">' + this.name + '</option>';
                        });
                        html += '</select>';
                        $('#' + e + ' .ancestors .col-sm-8').append(html);
                        $('#' + e + ' .ancestors select').each(function () {
                            $(this).on('change', function () {
                                category.changeParent($(this).attr('chonse'), e, $(this).val());
                            });
                        });
                    }
                }
                if (parseInt(id) !== 0) {
                    $('#' + e + ' .parentId').val(id);
                } else {
                    $('#' + e + ' .parentId').val($('#' + e + ' .ancestors .cateChose' + (i - 1)).val());
                }
            }
        });
    } else {
        $(element).nextAll().remove();
        $('#' + e + ' .parentId').val($(element).parent().prev().prev().find('select').val());
    }
};
category.add = function (id) {
    popup.open('popup-category-add', 'Thêm mới danh mục', template('/cp/tpl/category/editform.tpl'), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function () {
                var position = parseInt($('#category-form-edit input[name=position]').val());
                var weight = parseInt($('#category-form-edit input[name=weight]').val());
                if (isNaN(position)) {
                    $('#category-form-edit input[name=position]').val(0);
                }
                if (isNaN(weight)) {
                    $('#category-form-edit input[name=weight]').val(0);
                }
                var htmlAppend = '';
                var arr = new Array();
                var i = 0;
                $('#category-form-edit select').each(function () {
                    var id = $(this).val();
                    if (id !== 0) {
                        arr[i] = id;
                        i++;
                    }
                });

                var data = new Object();
                data.name = $('#category-form-edit input[name=name]').val();
                data.parentId = $('#category-form-edit input[name=parentId]').val();
                data.description = $('#category-form-edit textarea[name=description]').val();
                data.weight = $('#category-form-edit input[name=weight]').val();
                data.position = $('#category-form-edit input[name=position]').val();
                data.ebayCategoryId = $('#category-form-edit input[name=ebayCategoryId]').val();
                if ($('#category-form-edit input[name=active]').val() == 'on') {
                    data.active = true;
                } else {
                    data.active = false;
                }
                htmlAppend += '<input type="hidden" name="path" value="' + arr + '" />';
                $('#category-form-edit').append(htmlAppend);
                ajaxSubmit({
                    service: '/cpservice/category/add.json',
                    id: 'category-form-edit',
                    contentType: 'json',
                    data: data,
                    type: 'post',
                    done: function (resp) {
                        if (resp.success) {
                            popup.msg("Thêm  mới danh mục thành công", function () {
                                document.location = $(location).attr('href');
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
            fn: function () {
                popup.close('popup-category-add');
            }
        }
    ]);
    setTimeout(function () {
        category.buildAncestors(id, 'category-form-edit', 'add');
    }, 200);
};

category.edit = function (id) {
    ajax({
        service: '/cpservice/global/category/get.json',
        data: {id: id},
        done: function (resp) {
            if (resp.success) {
                popup.open('popup-category-edit', 'Sửa danh mục', template('/cp/tpl/category/editform.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function () {
                            var position = parseInt($('#category-form-edit input[name=position]').val());
                            if (isNaN(position)) {
                                $('#category-form-edit input[name=position]').val(1);
                            }
                            var weight = parseInt($('#category-form-edit input[name=weight]').val());
                            if (isNaN(weight)) {
                                $('#category-form-edit input[name=weight]').val(0);
                            }
                            var catId = $('#category-form-edit input[name=parentId]').val();
                            var cId = $('#category-form-edit input[name=id]').val();
                            if (catId === cId) {
                                $('#category-form-edit input[name=parentId]').val('');
                            }
                            var htmlAppend = '';
                            var arr = new Array();
                            var i = 0;
                            $('#category-form-edit select').each(function () {
                                var id = $(this).val();
                                if (id !== 0) {
                                    if (arr.length > 0) {
                                        var flag = false;
                                        for (var j in arr)
                                            if (arr[j] === id)
                                                flag = true;
                                        if (!flag)
                                            arr[i] = id;
                                    } else {
                                        arr[i] = id;
                                    }
                                    i++;
                                }
                            });
                            $('#category-form-edit').append(htmlAppend);
                            ajaxSubmit({
                                service: '/cpservice/category/update.json',
                                id: 'category-form-edit',
                                contentType: 'json',
                                done: function () {
                                    if (resp.success) {
                                        popup.msg("Sửa danh mục thành công", function () {
                                            document.location = $(location).attr('href');
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
                        fn: function () {
                            popup.close('popup-category-edit');
                        }
                    }
                ]);
                $("select[name=icon]").val(resp.data.icon);
            } else {
                popup.msg(resp.message);
            }
        }
    });
    setTimeout(function () {
        category.buildAncestors(id, 'category-form-edit', 'edit');
    }, 200);
};

category.del = function (id) {
    popup.confirm("Bạn có chắc chắn muốn xóa danh mục này không?", function () {
        ajax({
            service: '/cpservice/category/del.json',
            data: {id: id},
            done: function (resp) {
                if (resp.success) {
                    popup.msg("Xóa danh mục thành công", function () {
                        setTimeout(function () {
                            location.reload(500);
                        }, 200);
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

category.clearManu = function () {
    $('.table_manu').remove();
};

category.updateProperty = function (ref) {
    var id = $(ref).attr('for');
    if (category.validateProperty('datatables')) {
        var position = $('#sortProperty .' + id + ' input[name=position]').val();
        var type = $('#sortProperty .' + id + ' select[name=type]').val();
        var operator = $('#sortProperty .' + id + ' select[name=operator]').val();
        var name = $('#sortProperty .' + id + ' input[name=name]').val();
        var filter = $('#sortProperty .' + id + ' input[type=checkbox]').is(':checked') ? true : false;

        var datas = new Object();
        datas.id = id;
        datas.name = name;
        datas.position = position;
        datas.filter = filter;
        datas.type = type;
        datas.operator = operator;

        ajaxSubmit({
            service: '/cpservice/category/editproperty.json',
            data: datas,
            contentType: 'json',
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    $('#category-property-form .datatables tbody tr[for=' + id + '] td').find('input,select').removeAttr("disabled");
                } else {
                    popup.msg(resp.message);
                }
            }

        });

        $('#category-property-form .datatables tbody tr[for=' + id + '] td').find('input,select').attr("disabled", "disabled");
    } else {
        return false;
    }
};

category.editProperty = function (id) {
    ajax({
        service: '/cpservice/global/category/getproperties.json',
        data: {id: id},
        done: function (resp) {
            if (resp.success) {
                category.properties = resp.data;
                category.id = id;
                popup.open('popup-property', 'Thuộc tính danh mục', template("/cp/tpl/category/propertyform.tpl", resp), [
                    {
                        title: 'Đóng',
                        style: "btn-default",
                        fn: function () {
                            popup.close('popup-property');
                        }
                    }
                ]);
                $("input[name=idCategory]").val(id);
                $('#sortProperty tr').find('input,select').change(function () {
                    category.updateProperty(this);
                });
            }
        }
    });
};

category.addProperty = function () {
    var data = new Object();
    data.categoryId = $("input[name=idCategory]").val();
    data.position = $(".tbl-new input[name=position]").val();
    data.name = $(".tbl-new input[name=name]").val();
    data.type = $(".tbl-new select[name=type]").val();
    data.filter = $(".tbl-new input[name=filter]").val();
    data.operator = $(".tbl-new select[name=operator]").val();
    if ($(".tbl-new input[name=filter]").is(':checked')) {
        data.filter = true;
    } else {
        data.filter = false;
    }
    if (category.validateProperty('tbl-new')) {
        ajaxSubmit({
            service: '/cpservice/category/addproperty.json',
            contentType: 'json',
            data: data,
            done: function (rs) {
                if (rs.success) {


                    var html = template("/cp/tpl/category/viewproperty.tpl", rs);
                    $('#category-property-form .datatables tbody').append(html);
                    if (rs.data.filter === true) {
                        $(".f" + rs.data.id).attr('checked', 'true');
                    }
                    $(".t" + rs.data.id).val(rs.data.type);
                    $(".o" + rs.data.id).val(rs.data.operator);
                    $('#sortProperty tr').find('input,select').change(function () {
                        category.updateProperty(this);
                    });

                } else {
                    popup.msg("Có lỗi xảy ra");
                }

            }
        });
    } else {
        return false;

    }

}
category.validateProperty = function (tblClass) {
    var check = true;
    $('#category-property-form .' + tblClass + ' tbody tr td').removeClass('has-error');
    $.each($('#category-property-form .' + tblClass + ' tbody tr'), function () {
        var name = $(this).find('input[name=name]').val();
        if (name.length < 2) {
            popup.msg('Tên thuộc tính phải lớn hơn 2 ký tự.');
            check = false;
            $(this).find('input[name=name]').parent().addClass('has-error');
            $(this).find('input[name=name]').focus();
            return false;
        }
        if (parseInt($(this).find('select[name=type]').val()) === 0) {
            $(this).find('select[name=type]').parent().addClass('has-error');
            popup.msg('Chưa chọn kiểu thuộc tính.');
            check = false;
            return false;
        }
        if (parseInt($(this).find('select[name=operator]').val()) === 0) {
            $(this).find('select[name=operator]').parent().addClass('has-error');
            popup.msg('Chưa chọn kiểu thuộc tính.');
            check = false;
            return false;
        }
    });
    return check;
};
category.removeProperty = function (id) {

    popup.confirm("Bạn có chắc chắn muốn xóa thuộc tính này không?", function () {
        ajax({
            service: '/cpservice/category/delproperty.json',
            data: {id: id},
            done: function (resp) {
                if (resp.success) {
                    $('#category-property-form .' + id).remove();
                    popup.msg("Xóa thuộc tính thành công");
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
category.updatePropertyValue = function (res) {
    var id = $(res).attr('for');
    if (category.validatePropertyValue('tblValue')) {
        var position = $('#sortPropertyValue .' + id + ' input[name=position]').val();
        var name = $('#sortPropertyValue .' + id + ' input[name=name]').val();
        var value = $('#sortPropertyValue .' + id + ' input[name=value]').val();
        var filter = $('#sortPropertyValue .' + id + ' input[type=checkbox]').is(':checked') ? true : false;
        var datas = new Object();
        datas.id = id;
        datas.name = name;
        datas.position = position;
        datas.filter = filter;
        datas.value = value;
        ajaxSubmit({
            service: '/cpservice/category/editpropertyvalue.json',
            data: datas,
            loading: false,
            contentType: 'json',
            done: function (resp) {
                if (resp.success) {
                    $('#category-property-value .tblValue tbody tr[for=' + id + '] td').find('input,select').removeAttr("disabled");
                } else {
                    popup.msg(resp.message);
                }
            }
        });
        $('#category-property-value .tblValue tbody tr[for=' + id + '] td').find('input,select').attr("disabled", "disabled");
    } else {
        return false;
    }

};
category.viewPropertyValue = function (id) {
    ajax({
        service: '/cpservice/global/category/getpropertyvalue.json',
        loading: false,
        data: {id: id},
        done: function (resp) {
            if (resp.success) {
                category.properties = resp.data;
                category.id = id;
                popup.open('popup-propertyvalue', 'Giá trị thuộc tính', template("/cp/tpl/category/propertyvalueform.tpl", resp), [
                    {
                        title: 'Đóng',
                        style: "btn-default",
                        fn: function () {
                            popup.close('popup-propertyvalue');
                        }
                    }
                ]);
                $("input[name=categoryPropertyId]").val(id);
                $("input[name=categoryId]").val($("input[name=idCategory]").val());

                $('#category-property-value .tblValue tbody tr').mouseover(function () {

                    var id = $(this).attr('for');
                    $('#category-property-value .tblValue tbody tr.' + id + ' td').css("background-color", "#FFEAE0");
                    $(this).mouseout(function () {
                        $('#category-property-value .tblValue tbody tr.' + id + ' td').css("background-color", "white");
                    });
                });
                $('#sortPropertyValue tr').find('input,select').change(function () {
                    category.updatePropertyValue(this);
                });
            }
        }
    });
};
category.addPropertyValue = function () {
    var data = new Object();
    data.categoryPropertyId = $("input[name=categoryPropertyId]").val();
    data.categoryId = $("input[name=categoryId]").val();
    data.position = $(".tblInsert input[name=position]").val();
    data.name = $(".tblInsert input[name=name]").val();
    data.value = $(".tblInsert input[name=value]").val();
    if ($(".tblInsert input[name=filter]").is(':checked')) {
        data.filter = true;
    } else {
        data.filter = false;
    }
    if (category.validatePropertyValue('tblInsert')) {
        ajaxSubmit({
            service: '/cpservice/category/addpropertyvalue.json',
            contentType: 'json',
            data: data,
            done: function (rs) {
                if (rs.success) {
                    var html = template("/cp/tpl/category/viewpropertyvalue.tpl", rs);
                    $('#category-property-value .tblValue tbody').append(html);
                    if (rs.data.filter === true) {
                        $(".f" + rs.data.id).attr('checked', 'true');
                    }
                    $('#category-property-value .tblValue tbody tr').mouseover(function () {

                        var id = $(this).attr('for');
                        $('#category-property-value .tblValue tbody tr.' + id + ' td').css("background-color", "#FFEAE0");
                        $(this).mouseout(function () {
                            $('#category-property-value .tblValue tbody tr.' + id + ' td').css("background-color", "white");
                        });
                    });
                    $('#sortPropertyValue tr').find('input,select').change(function () {
                        category.updatePropertyValue(this);
                    });
                } else {
                    popup.msg("Có lỗi xảy ra");
                }

            }
        });
    } else {
        return false;

    }

}
category.validatePropertyValue = function (tblClass) {
    var check = true;
    $('#category-property-value .' + tblClass + ' tbody tr td').removeClass('has-error');
    $.each($('#category-property-value .' + tblClass + ' tbody tr'), function () {
        var name = $(this).find('input[name=name]').val();
        var value = $(this).find('input[name=value]').val();
        if (name.length < 1) {
            popup.msg('Tên giá trị phải lớn hơn 1 ký tự.');
            check = false;
            $(this).find('input[name=name]').parent().addClass('has-error');
            $(this).find('input[name=name]').focus();
            return false;
        }
        if (isNaN(parseInt(value)) || parseInt(value) === 0) {
            popup.msg('Giá trị phải là số và lớn hơn 0.');
            $(this).find('input[name=value]').parent().addClass('has-error');
            $(this).find('input[name=value]').focus();
            check = false;
            return false;
        }
    });
    return check;
};
category.removePropertyValue = function (id) {
    popup.confirm("Bạn có chắc chắn muốn xóa danh mục này không?", function () {
        ajax({
            service: '/cpservice/category/delpropertyvalue.json',
            data: {id: id},
            done: function (resp) {
                if (resp.success) {
                    $('#category-property-value .' + id).remove();
                    popup.msg("Xóa thuộc tính thành công");
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

category.manufacturer = function (id) {
    ajax({
        service: '/cpservice/category/viewmanufacturer.json',
        data: {id: id},
        done: function (resp) {
            if (resp.success) {
                category.currentMapCate = id;
                resp.id = id;
                popup.open('popup-category-map', 'Map Thương hiệu', template('/cp/tpl/category/mapform.tpl', resp), [
                    {
                        title: 'Hoàn thành',
                        style: 'btn-primary',
                        fn: function () {
                            popup.close('popup-category-map');
                        }
                    }
                ]);
                $('a[page]').each(function () {
                    $(this).click(function () {
                        var pageIndex = $('#map-form input[name=pageIndex]').val($(this).attr('page'));
                        category.filterManufacturer(id, 2);
                    });
                });
                $('input[name=idCategorys]').val(id);
            }
        }
    });
};
category.filterManufacturer = function (id, next) {
    var manufacturerId = $('#map-form input[name=manufacturerId]').val();
    var manufacturerName = $('#map-form input[name=manufacturerName]').val();
    var pageSize = parseInt($('#map-form input[name=pageSize]').val());
    var pageIndex = 0;
    if (next === 2)
        pageIndex = parseInt($('#map-form input[name=pageIndex]').val());
    ajax({
        service: '/cpservice/category/viewmanufacturer.json',
        data: {
            id: id,
            manufacturerId: manufacturerId,
            manufacturerName: manufacturerName,
            pageIndex: pageIndex,
            pageSize: pageSize
        },
        done: function (resp) {
            if (resp.success) {
                var html = '';
                for (var i in resp.data.data) {
                    var manufacturer = resp.data.data[i];
                    html += '<tr for="' + manufacturer.id + '">\
                                <td class="text-center">' + manufacturer.id + '</td>\
                                <td class="text-center">' + manufacturer.name + '</td>\
                                <td class="text-center">\
                                <button style="width: 100px;" onclick="category.delmanu(\'' + id + '\' , \'' + manufacturer.id + '\')" class="btn btn-danger">Xóa</button>\
                            </td></tr>';
                }
                $('#view-map-manu tbody').html(html);
                var paging = '<div class="pull-left mgr-top-10" style="margin: 10px 10px;">\
                    Tìm thấy <strong style="color: red" class="count-manu" data="' + resp.data.dataCount + '">' + resp.data.dataCount + '</strong> thương hiệu đã được Map \
                    </div>';
                if (resp.data.pageCount && resp.data.pageCount > 0) {
                    var pIndex = parseInt(resp.data.pageIndex);
                    var pageCount = parseInt(resp.data.pageCount);
                    paging += '<div class="btn-group" >';
                    if (pIndex > 3) {
                        paging += '<a class="btn btn-default" page="1"><<</a>';
                    }
                    if (pIndex > 2) {
                        paging += '<a  class="btn btn-default" page="' + pIndex + '"><</a>';
                    }
                    if (pIndex > 3) {
                        paging += '<a class="btn btn-default">...</a>';
                    }
                    if (pIndex >= 3) {
                        paging += '<a class="btn btn-default" page="' + (pIndex - 2) + '">' + (pIndex - 2) + '</a>';
                    }
                    if (pIndex >= 2) {
                        paging += '<a class="btn btn-default" page="' + (pIndex - 1) + '">' + (pIndex - 1) + '</a>';
                    }
                    if (pIndex >= 1) {
                        paging += '<a class="btn btn-default" page="' + pIndex + '">' + pIndex + '</a>';
                    }
                    paging += '<a class="btn btn-primary"> ' + (pIndex + 1) + '</a>';
                    if (pageCount - pIndex > 2) {
                        paging += '<a class="btn btn-default" page="' + (pIndex + 2) + '">' + (pIndex + 2) + '</a>';
                    }
                    if (pageCount - pIndex > 3) {
                        paging += '<a class="btn btn-default" page="' + (pIndex + 3) + ' ">' + (pIndex + 3) + '</a>';
                    }
                    if (pageCount - pIndex > 4) {
                        paging += '<a class="btn btn-default">...</a>';
                    }
                    if (pageCount - pIndex > 2) {
                        paging += '<a class="btn btn-default" page="' + (pIndex + 2) + '">></a>';
                    }
                    if (pageCount - pIndex > 2) {
                        paging += '<a class="btn btn-default" page="' + pageCount + '">>></a>';
                    }
                    paging += '</div>';
                }
                $('.paging').html(paging);
                $('a[page]').each(function () {
                    $(this).click(function () {
                        var pageIndex = $('#map-form input[name=pageIndex]').val($(this).attr('page'));
                        category.filterManufacturer(id, 2);
                    });
                });
            }
        }
    });
};
category.delmanu = function (catId, manuId) {

    popup.confirm("Bạn có chắc chắn muốn xóa thương hiệu này khỏi danh mục này không?", function () {
        ajax({
            service: '/cpservice/category/delmapmanufacturer.json',
            data: {catid: catId, manufacturerId: manuId},
            done: function (resp) {
                if (resp.success) {
                    $('tr[for=' + manuId + ']').remove();
                    var countmanu = parseInt($('.count-manu').attr('data'));
                    $('.count-manu').html((parseInt($('.count-manu').attr('data')) - 1));
                    $('.count-manu').attr('data', (parseInt($('.count-manu').attr('data')) - 1));
                    if (countmanu === 1) {
                        $('#view-map-manu tbody').prepend('<tr><td colspan="3">\
                                <div class="nodata" style="color:red;text-align: center">Hiện tại chưa có thương hiệu nào được Map với danh mục này</div>\
                                </td></tr>');
                    }
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
category.addMapForm = function () {
    $('.table_manu').remove();
    var manufacturerId = "'manufacturerId'";

    $('#map-form').after('<table class="table_manu table table-striped table-bordered table-responsive">\
        <tr><td>\
            <div class="input-group col-sm-12">\
                    <input id="manufacturerId" name="manufacturerId" placeholder="Nhập mã thương hiệu" class="form-control" type="text" value="">\
                    <span class="input-group-btn">\
                        <button class="btn btn-default" onclick="category.loadmf(' + manufacturerId + ');" type="button">Tìm</button>\
                    </span>\
                </div>\
        </td>\
        <td align="left" class="col-sm-6">\
            <a class="btn btn-warning" onclick="category.mapManu();">Map thương hiệu</a> \
            <a class="btn btn-default" onclick="category.clearManu();">Hủy bỏ</a></td>\
        </tr></table>');

};
category.loadmf = function (objName) {
    var resp = new Object();
    resp.objName = objName;
    popup.open('popup-search-mf', 'Tìm kiếm thương hiệu', template('/cp/tpl/category/loadmf.tpl', resp), [
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function () {
                popup.close('popup-search-mf');
            }
        }]);
};
category.searchmf = function (obj, objName) {
    ajax({
        service: '/cpservice/global/model/searchmf.json',
        loading: false,
        data: {keyword: $(obj).val()},
        done: function (resp) {
            if (resp.success) {
                var html = "";
                if (resp.data.dataCount == 0) {
                    html += '<tr><td colspan="3" class="text-center text-danger">Không tìm thấy thương hiệu</td></tr>';
                } else {
                    $.each(resp.data.data, function () {
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
category.mapManu = function () {
    var manufacturerId = $('#manufacturerId').val();
    var categoryID = $('input[name=idCategorys]').val();
    ajax({
        service: '/cpservice/category/addmapmanufacturer.json',
        data: {
            manuId: manufacturerId,
            cateId: categoryID
        },
        done: function (result) {
            if (result.success) {
                popup.msg("Map thương hiệu thành công");
                var html = template('/cp/tpl/category/viewmapmanu.tpl', result)
                $('#view-map-manu tbody').prepend(html);
                $('.count-manu').html((parseInt($('.count-manu').attr('data')) + 1));
                $('.count-manu').attr('data', (parseInt($('.count-manu').attr('data')) + 1));
                $('.nodata').parent().parent().remove();

            } else {
                popup.msg(result.message);
            }
        }
    });
};

category.index = function () {
    ajax({
        service: '/cpservice/category/index.json',
        loading: false,
        done: function (resp) {
            popup.msg(resp.message);
        }
    });
};

category.unindex = function () {
    ajax({
        service: '/cpservice/category/unindex.json',
        done: function (resp) {
            popup.msg(resp.message);
        }
    });
};


category.exportExcel = function () {
    var seftUrl = baseUrl + "/cpservice/category/excel.html";
    var queryString = "";
    var i = 1;
    location.href = seftUrl + queryString;
};

category.exportExcelCategoryByLeafDisplay = function () {
    var seftUrl = baseUrl + "/cp/category/excelbyleaf.html";
    var queryString = "";
    var i = 1;
    location.href = seftUrl + queryString;
};