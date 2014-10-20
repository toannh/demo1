newscategory = {};
newscategory.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị danh mục tin tức", "/cp/newscategory.html"],
        ["Danh sách danh mục tin tức"]
    ]);
};
newscategory.add = function() {
    popup.open('popup-add', 'Thêm mới danh mục tin tức', template('/cp/tpl/news/cate_form.tpl'), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function() {
                ajaxSubmit({
                    service: '/cpservice/newscategory/add.json',
                    id: 'form-add',
                    contentType: 'json',
                    done: function(rs) {
                        if (rs.success) {
                            popup.msg("Thêm mới danh mục tin thành công");
                            setTimeout(function() {
                                window.location.reload()
                            }, 2000);
                        } else {
                            popup.msg("Thêm mới danh mục tin thất bại");
                        }
                    }
                });
            }
        },
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-add');
            }
        },
    ]);


};

newscategory.edit = function(id) {
    ajax({
        service: '/cpservice/newscategory/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-add', 'Sửa thông tin danh mục tin', template('/cp/tpl/news/cate_form.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            ajaxSubmit({
                                service: '/cpservice/newscategory/edit.json',
                                id: 'form-add',
                                contentType: 'json',
                                done: function(rs) {
                                    if (rs.success) {
                                        popup.msg("Sửa danh mục tin thành công");
                                        setTimeout(function() {
                                            window.location.reload()
                                        }, 2000);
                                    } else {
                                        popup.msg("Sửa danh mục tin thất bại");
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-add');
                        }
                    }
                ]);
                $('#popup-add select[name=active]').val(resp.data.active ? "true" : "false");
                $('#popup-add select[name=showHome]').val(resp.data.showHome ? "true" : "false");
                $('#popup-add select[name=parentId]').val(resp.data.parentId);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

newscategory.del = function(id) {
    popup.confirm("Bạn có chắc muốn xóa?", function() {
        ajax({
            service: '/cpservice/newscategory/del.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    popup.msg(resp.message);
                    setTimeout(function() {
                        window.location.reload()
                    }, 2000);
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

newscategory.editStatus = function(id) {
    ajax({
        service: '/cpservice/newscategory/editstatus.json',
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

newscategory.changePosition = function(id, obj) {
    var order = $(obj).val();
    ajax({
        service: '/cpservice/newscategory/changeposition.json',
        data: {id: id, position: order},
        done: function(resp) {
            if (resp.success) {
                popup.msg(resp.message);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

newscategory.formatDateTime = function(format, _class) {
    if (_class == null || _class == 'undefined' || _class == '') {
        _class = 'timestam';
    }
    $('.' + _class).each(function() {
        var timestamp = $(this).text();
        var months = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'];
        timestamp = parseFloat(timestamp);
        var a = new Date(timestamp);
        var year = a.getFullYear();
        var month = months[a.getMonth()];
        var date = a.getDate();
        var hour = a.getHours();
        var min = a.getMinutes();
        var sec = a.getSeconds();
        var time = "";
        if (format == 'full') {
            time += hour + ':' + min + ':' + sec + " ";
        }
        time += date + '/' + month + '/' + year;
        $(this).text(time);
    });
};



newscategory.formatDateTime();

newscategory.resetForm = function() {
    $('select[name=parentId]').val("0");
    $('select[name=active]').val("0");
    $('input[type=text]').val("");
};
