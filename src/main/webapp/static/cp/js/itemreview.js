itemreview = {};

itemreview.init = function() {
    //vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị bình luận sản phẩm", "/cp/itemreview.html"],
        ["Danh sách bình luận sản phẩm"]
    ]);
};
itemreview.changeStatus = function(itemId, reviewId) {
    ajax({
        service: '/cpservice/itemreview/changestatus.json',
        data: {itemId: itemId, reviewId: reviewId},
        loading:false,
        done: function(resp) {
            if (resp.success) {
                if (resp.data.active) {
                    $('#' + resp.data.id + ' #reviews').removeClass("btn-success");
                    $('#' + resp.data.id + ' #reviews').addClass("btn-danger");
                    $('#' + resp.data.id + ' #reviews').html('<span class="glyphicon glyphicon-download"></span> Ẩn');
                    $('#' + resp.data.id).parents('tr').removeClass("danger");
                } else {
                    $('#' + resp.data.id + ' #reviews').removeClass("btn-danger");
                    $('#' + resp.data.id + ' #reviews').addClass("btn-success");
                    $('#' + resp.data.id + ' #reviews').html('<span class="glyphicon glyphicon-download"></span> Hiện');
                    $('#' + resp.data.id).parents('tr').addClass("danger");
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });

};
itemreview.listitemreviewlike = function(reviewId) {
    ajax({
        service: '/cpservice/itemreview/getitemreviewlike.json',
        data:{reviewId:reviewId},
        loading: true,
        done: function(rep) {
            if (rep.success) {
                popup.open('popup-select-product', 'Danh sách khách hàng', template('/cp/tpl/itemreview/list.tpl',rep), [
                    {
                        title: 'Xong',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-select-product');
                        }
                    }
                ]);
            }
        }
    });
};
