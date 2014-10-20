administrator = {};

administrator.init = function(params) {
//vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị viên", "/cp/administrator.html"],
        ["Danh sách quản trị viên"]
    ]);
};

administrator.del = function(id) {
    popup.confirm("Bạn có chắc chắn muốn người dùng này?", function() {
        ajax({
            service: '/cpservice/administrator/del.json',
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

administrator.add = function() {
    popup.open('popup-administrator', 'Thêm mới quản trị viên', template('/cp/tpl/administrator/edit.tpl'), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function() {
                ajaxSubmit({
                    service: '/cpservice/administrator/add.json',
                    id: 'adminForm',
                    done: function(resp) {
                        if (resp.success) {
                            popup.msg("Thêm mới thành công", function() {
                                popup.close('popup-administrator');
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
                popup.close('popup-administrator');
            }
        }
    ]);
};

administrator.edit = function(id) {
    ajax({
        service: '/cpservice/administrator/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-administrator', 'Sửa quản trị viên', template('/cp/tpl/administrator/edit.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            ajaxSubmit({
                                service: '/cpservice/administrator/edit.json',
                                id: 'adminForm',
                                done: function(result) {
                                    if (result.success) {
                                        popup.msg("Sửa thành công", function() {
                                            popup.close('popup-administrator');
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
                            popup.close('popup-administrator');
                        }
                    }
                ]);
                $('#popup-administrator input[name=active]').attr("checked", resp.data.active);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

administrator.active = function(id) {
    var oldIcon = $('a[for=active_' + id + ']').html();
    $('a[for=active_' + id + ']').html('');
    ajax({
        service: '/cpservice/administrator/active.json',
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
                popup.msg('Không thể thay đổi trạng thái hoạt động của người dùng này');
            }
        }
    });
};

administrator.showRoles = function(id) {
    ajax({
        service: '/cpservice/administrator/getroles.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            var func = [];
            var services = [];
            for (var i = 0; i < functions.length; i++) {
                if (functions[i].type === 'ACTION') {
                    func[func.length] = functions[i];
                }
            }
            for (var i = 0; i < functions.length; i++) {
                if (functions[i].type === 'SERVICE') {
                    services[services.length] = functions[i];
                }
            }
            popup.open('popup-roles', 'Cấp quyền quản trị', template('/cp/tpl/administrator/roles.tpl', {"func": func, "services": services}), [
                {
                    title: 'Lưu',
                    style: 'btn-primary',
                    fn: function() {
                        var data = [];
                        $.each($('input.role:checked'), function() {
                            var role = {};
                            role.administratorId = id;
                            role.functionUri = $(this).val();
                            role.refUri = $(this).attr("for");
                            data[data.length] = role;
                        });
                        ajax({
                            service: '/cpservice/administrator/saveroles.json',
                            data: data,
                            contentType: 'json',
                            type: 'post',
                            done: function(resp) {
                                if (resp.success) {
                                    popup.msg("Cấp quyền thành công", function() {
                                        popup.close('popup-roles');
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
                        popup.close('popup-roles');
                    }
                }
            ]);
            if (resp.success && resp.data !== null) {
                $.each(resp.data, function() {
                    var role = this;
                    $.each($('input.role'), function() {
                        if ($(this).val() === role.functionUri) {
                            $(this).prop("checked", true);
                        }
                    });
                });
            }
        }
    });
};
administrator.checkAll = function(index, obj) {
    $(".sub_" + index).each(function() {
        if ($(obj).is(":checked")) {
            $(this).prop("checked", true);
        } else {
            $(this).prop("checked", false);
        }
    });
};
