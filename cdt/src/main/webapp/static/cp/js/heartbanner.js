
heartbanner = {};
heartbanner.init = function() {
layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị heart banner", "/cp/heartbanner.html"],
        ["Danh sách heart banner"]
    ]);
    $("#banner-order_4hmrphkb").change(function() {

        var id = $(this).attr('for');
        var position = $(this).val();
        if (isNaN(parseInt(position)) || parseInt(position) === 0) {
            popup.msg('Thứ tự phải là số và lớn hơn 0');
            $(this).parent().addClass('has-error');
            // $(this).find('input[name=position]').focus();
            return false;
        }
        heartbanner.changeOrder(id, position);
    });
};
heartbanner.changeOrder = function(id, position) {
    ajax({
        service: '/cpservice/heartbanner/changeorder.json',
        data: {id: id, position: position},
        done: function(resp) {
            if (resp.success) {
                document.location = $(location).attr('href');
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
heartbanner.add = function() {
    popup.open('popup-addHB', 'Thêm mới Heart Banner', template('/cp/tpl/heartbanner/edit.tpl', null),
            [
                {
                    title: 'Lưu',
                    style: 'btn-primary',
                    fn: function() {
                        ajaxUpload({
                            service: '/cpservice/heartbanner/add.json',
                            id: 'form-edit',
                            loading: true,
                            contentType: 'json',
                            done: function(rs) {
                                if (rs.success) {
                                    popup.msg("Thêm banner mới thành công", function() {
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
    $('input[id=fileThumb]').change(function() {
        $('#photoCoverThumb').val($(this).val());
    });
};
heartbanner.editStatus = function(id) {
    ajax({
        service: '/cpservice/heartbanner/editstatus.json',
        data: {id: id},
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
heartbanner.edit = function(id) {
    ajax({
        service: '/cpservice/heartbanner/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-addHB', 'Chỉnh sửa Heart Banner', template('/cp/tpl/heartbanner/edit.tpl', resp.data),
                        [
                            {
                                title: 'Lưu',
                                style: 'btn-primary',
                                fn: function() {
                                    if ($("input[name=order]").val() == '')
                                        $("input[name=order]").val('0');

                                    if ($("input[name=dateTo]").val() < $("input[name=dateFrom]").val()) {
                                        popup.msg("Ngày hết hạn không được nhỏ hơn ngày tạo");
                                    } else {
                                        ajaxUpload({
                                            service: '/cpservice/heartbanner/edit.json',
                                            id: 'form-edit',
                                            contentType: 'json',
                                            done: function(rs) {
                                                if (rs.success) {
                                                    popup.msg("Sửa banner thành công", function() {
                                                        location.reload();
                                                    });
                                                } else {
                                                    popup.msg(rs.message);
                                                }
                                            }
                                        });
                                    }
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
                $('input[id=fileThumb]').change(function() {
                    $('#photoCoverThumb').val($(this).val());
                });

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
heartbanner.remove = function(id) {
    popup.confirm('Bạn có muốn xóa banner này không?', function() {
        ajax({
            service: '/cpservice/heartbanner/remove.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    popup.msg("Xoá banner thành công", function() {
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
