newshomebox = {};
newshomebox.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị Box tin tức trang chủ", "/cp/newshomebox.html"],
        ["Danh sách tin tức trang chủ"]
    ]);
};
newshomebox.add = function() {
    popup.open('popup-addHB', 'Thêm mới tin tức', template('/cp/tpl/newshomebox/add.tpl', null),
            [
                {
                    title: 'Lưu',
                    style: 'btn-primary',
                    fn: function() {
                        ajaxSubmit({
                            service: '/cpservice/newshomebox/add.json',
                            id: 'form-edit',
                            contentType: 'json',
                            done: function(rs) {
                                if (rs.success) {
                                    popup.msg("Thêm HotDeal thành công", function() {
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

};
newshomebox.delnews = function(id) {
    popup.confirm("Bạn có chắc muốn xóa?", function() {
        ajax({
            service: '/cpservice/newshomebox/del.json',
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