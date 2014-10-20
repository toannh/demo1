var func = {};

func.init = function() {
//vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị quyền", "/cp/function.html"],
        ["Định nghĩa quyền"]
    ]);
};

func.remove = function(uri) {
    popup.confirm('Bạn có muốn xóa chức năng này', function() {
        ajax({
            service: '/cpservice/function/remove.json',
            data: {uri: uri},
            done: function(resp) {
                if (resp.success) {
                    popup.msg("Xóa chức năng thành công", function() {
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
func.removegroup = function(name) {
    popup.confirm('Bạn có muốn xóa nhóm này', function() {
        ajax({
            service: '/cpservice/function/removegroup.json',
            data: {name: name},
            done: function(resp) {
                if (resp.success) {
                    popup.msg("Xóa nhóm quyền thành công", function() {
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

func.edit = function(uri) {
    var func;
    var services = [];

    for (var i = 0; i < functions.length; i++) {
        if (functions[i].uri === uri) {
            func = functions[i];
        }
    }
    for (var i = 0; i < functions.length; i++) {
        if (functions[i].type === 'SERVICE' && functions[i].refUri === func.refUri) {
            services[services.length] = functions[i];
        }
    }

    popup.open('popup-map', 'Map chức năng', template('/cp/tpl/function/map.tpl', {"func": func, "services": services, "groups": groups}), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function() {
                var group = $("select[name=group]").val();
                var groupPosition = $('select[name=group] option:selected').attr('for');
                var newGroup = $("input[name=newGroup]").val();
                func.name = $("input[name=name]").val();
                func.position = ($("input[name=position]").val() === '') ? 0 : $("input[name=position]").val();
                var newGroupPosition = ($("input[name=groupPosition]").val() === '') ? 0 : $("input[name=groupPosition]").val();

                var errMess = '';
                if ((group === '' || group === '0') && newGroup === '') {
                    errMess += '<li>Chưa chọn nhóm chức năng</li>';
                } else {
                    func.groupName = (newGroup !== '') ? newGroup : group;
                    func.groupPosition = (newGroup !== '') ? newGroupPosition : groupPosition;
                }
                if (func.name === '') {
                    errMess += '<li>Chưa nhập tên chức năng</li>';
                }
                if (isNaN(func.position)) {
                    errMess += '<li>Thứ tự sắp xếp phải là số</li>';
                }

                if (isNaN(func.groupPosition)) {
                    errMess += '<li>Thứ tự nhóm phải là số</li>';
                }

                if (errMess !== '') {
                    errMess = '<ul>' + errMess + '</ul>';
                    popup.msg(errMess);
                } else {
                    var data = [];
                    func.type = 'ACTION';
                    func.position = parseInt(func.position);
                    func.groupPosition = parseInt(func.groupPosition)
                    data[data.length] = func;

                    $('#form-edit .table tr[for=service]').each(function() {
                        if (isNaN($(this).find('input[for=position]').val())) {
                            $(this).find('input[for=position]').val(0);
                        }

                        f = {};
                        f.uri = $(this).find('input[for=uri]').val();
                        f.name = $(this).find('input[for=name]').val();
                        f.position = parseInt($(this).find('input[for=position]').val());
                        f.skip = $(this).find('input[for=skip]').is(':checked');
                        f.type = 'SERVICE';
                        data[data.length] = f;
                    });

                    ajax({
                        service: '/cpservice/function/save.json',
                        data: data,
                        contentType: 'json',
                        type: 'post',
                        done: function(resp) {
                            if (resp.success) {
                                popup.msg("Sửa chức năng thành công", function() {
                                    popup.close('popup-map');
                                    location.reload();
                                });
                            } else {
                                popup.msg(resp.message);
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
                popup.close('popup-map');
            }
        }
    ]);
};

func.map = function(uri) {
    var func;
    var services = [];

    for (var i = 0; i < unmappedCpFunctions.length; i++) {
        if (unmappedCpFunctions[i].uri === uri) {
            func = unmappedCpFunctions[i];
        }
        if (unmappedCpFunctions[i].type === 'SERVICE' && unmappedCpFunctions[i].refUri === func.refUri) {
            services[services.length] = unmappedCpFunctions[i];
        }
    }

    popup.open('popup-map', 'Map chức năng', template('/cp/tpl/function/map.tpl', {"func": func, "services": services, "groups": groups}), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function() {
                var group = $("select[name=group]").val();
                var newGroup = $("input[name=newGroup]").val();
                func.name = $("input[name=name]").val();
                func.position = ($("input[name=position]").val() === '') ? 0 : $("input[name=position]").val();

                var groupPosition = $('select[name=group] option:selected').attr('for');
                var newGroupPosition = ($("input[name=groupPosition]").val() === '') ? 0 : $("input[name=groupPosition]").val();

                var errMess = '';
                if ((group === '' || group === '0') && newGroup === '') {
                    errMess += '<li>Chưa chọn nhóm chức năng</li>';
                } else {
                    func.groupName = (newGroup !== '') ? newGroup : group;
                    func.groupPosition = (newGroup !== '') ? newGroupPosition : groupPosition;
                }
                if (func.name === '') {
                    errMess += '<li>Chưa nhập tên chức năng</li>';
                }
                if (isNaN(func.position)) {
                    errMess += '<li>Thứ tự sắp xếp phải là số</li>';
                }

                if (isNaN(func.groupPosition)) {
                    errMess += '<li>Thứ tự nhóm phải là số</li>';
                }

                if (errMess !== '') {
                    errMess = '<ul>' + errMess + '</ul>';
                    popup.msg(errMess);
                } else {
                    var data = [];
                    func.type = 'ACTION';
                    func.position = parseInt(func.position);
                    func.groupPosition = parseInt(func.groupPosition)
                    data[data.length] = func;

                    $('#form-edit .table tr[for=service]').each(function() {
                        if (isNaN($(this).find('input[for=position]').val())) {
                            $(this).find('input[for=position]').val(0);
                        }

                        var f = {};
                        f.uri = $(this).find('input[for=uri]').val();
                        f.name = $(this).find('input[for=name]').val();
                        f.position = parseInt($(this).find('input[for=position]').val());
                        f.skip = $(this).find('input[for=skip]').is(':checked');
                        f.type = 'SERVICE';
                        data[data.length] = f;
                    });

                    ajax({
                        service: '/cpservice/function/save.json',
                        data: data,
                        contentType: 'json',
                        type: 'post',
                        done: function(resp) {
                            if (resp.success) {
                                popup.msg("Map chức năng thành công", function() {
                                    popup.close('popup-map');
                                    location.reload();
                                });
                            } else {
                                popup.msg(resp.message);
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
                popup.close('popup-map');
            }
        }
    ]);
};

func.editgroup = function(id) {
    var group = new Object();
    for (var i = 0; i < groups.length; i++) {
        if (groups[i].name === id) {
            group = groups[i];
        }
    }
    popup.open('popup-edit-group', 'Sửa nhóm', template('/cp/tpl/function/editgroup.tpl', group), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function() {
                var errMess = '';
                var name = $("input[name=name]").val();
                var possition = $("input[name=position]").val();
                if (isNaN(possition)) {
                    errMess += '<li>Thứ tự nhóm phải là số</li>';
                }
                if (name !== id && $.inArray(name, groups) > 0) {
                    errMess += '<li>Tên nhóm đã tồn tại</li>';
                }
                if (errMess !== '') {
                    errMess = '<ul>' + errMess + '</ul>';
                    popup.msg(errMess);
                } else {
                    var func = new Object();
                    for (var i = 0; i < functions.length; i++) {
                        if (functions[i].groupName === id) {
                            func = functions[i];
                        }
                    }
                    var data = [];
                    func.groupName = name;
                    func.groupPosition = parseInt(possition);
                    data[data.length] = func;

                    ajax({
                        service: '/cpservice/function/save.json',
                        data: data,
                        contentType: 'json',
                        type: 'post',
                        done: function(resp) {
                            if (resp.success) {
                                popup.msg("Sửa nhóm thành công", function() {
                                    popup.close('popup-edit-group');
                                    location.reload();
                                });
                            } else {
                                popup.msg(resp.message);
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
                popup.close('popup-edit-group');
            }
        }
    ]);
};