schistory = {};
schistory.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị lịch sử giao dịch ShipChung", "/cp/schistory.html"],
        ["Danh sách lịch sử giao dịch ShipChung"]
    ]);
};
schistory.detailParams = function(id) {
    ajax({
        service: '/cpservice/schistory/gethistory.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (typeof resp != 'undefined' && resp != null) {
                popup.open('popup-view', 'Chi tiết', template('/cp/tpl/schistory/detail.tpl', {data: resp}), [
                    {
                        title: 'Đóng',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-view');
                        }
                    }
                ]);
            } else {
                popup.msg("Giao dịch không tồn tại hoặc đã bị xóa!");
            }
        }
    });
};

