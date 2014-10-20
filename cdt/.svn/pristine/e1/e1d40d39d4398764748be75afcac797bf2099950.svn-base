sellerreview = {};

sellerreview.init = function() {
     layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị đánh giá uy tin", "/cp/sellerreview.html"],
        ["Danh sách đánh giá"]
    ]);
};
sellerreview.changeStatus = function(id) {
    ajax({
        service: '/cpservice/sellerreview/changestatus.json',
        data: {id:id},
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
