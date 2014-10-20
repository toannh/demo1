featurednews = {};
featurednews.init = function() {
};
featurednews.add = function() {
    popup.open('popup-addHB', 'Thêm mới tin tức nổi bật', template('/cp/tpl/featurednews/add.tpl', null),
            [
                {
                    title: 'Lưu',
                    style: 'btn-primary',
                    fn: function() {
                        ajaxUpload({
                            service: '/cpservice/featurednews/add.json',
                            id: 'form-add',
                            contentType: 'json',
                            done: function(rs) {
                                if (rs.success) {
                                    popup.msg("Thêm tin tức thành công", function() {
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
    editor('txt_content');

};
featurednews.edit = function(id) {
    ajax({
        service: '/cpservice/featurednews/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-add', 'Sửa tin tức nổi bật', template('/cp/tpl/featurednews/add.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            ajaxUpload({
                                service: '/cpservice/featurednews/edit.json',
                                id: 'form-add',
                                contentType: 'json',
                                done: function(rs) {
                                    if (rs.success) {
                                        popup.msg("Sửa tin thành công", function() {
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
                            popup.close('popup-add');
                        }
                    }
                ]);
                editor('txt_content');

                $('input[id=lefile]').change(function() {
                    $('#photoCover').val($(this).val());
                });

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
featurednews.del = function(id) {
    popup.confirm("Bạn có chắc muốn xóa?", function() {
        ajax({
            service: '/cpservice/featurednews/del.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    popup.msg(resp.message);
                    setTimeout(function() {
                        window.location.reload();
                    }, 2000);
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};