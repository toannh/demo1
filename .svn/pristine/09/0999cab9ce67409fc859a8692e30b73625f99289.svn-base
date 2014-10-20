var seller = {};
seller.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị người bán", "/cp/seller.html"],
        ["Danh sách người bán"]
    ]);
    seller.findSellerAPIByUserIds(userIds);
};

seller.reset = function() {
    $('input[name=userId]').val("");
    $('select[name=nlIntegrated]').val("0");
    $('select[name=scIntegrated]').val("0");
};

seller.syncItem = function(userId, nlIntegrated, scIntegrated) {
    ajax({
        service: '/cpservice/seller/syncitem.json',
        data: {userId: userId, nlIntegrated: nlIntegrated, scIntegrated: scIntegrated},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                popup.msg(resp.message, function() {
                    location.reload();
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
seller.createCode = function(userId) {
    popup.confirm("Bạn có chắc muốn thay đổi mã API không ?", function() {
        ajax({
            service: '/cpservice/seller/createapicode.json',
            data: {userId: userId},
            loading: false,
            done: function(resp) {
                if (resp.success) {
                    $('td[data-rel=' + resp.data.userId + ']').html(resp.data.code);
                    $('td[data-rel=' + resp.data.userId + ']').addClass("success");
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
seller.findSellerAPIByUserIds = function(userIds) {
    ajax({
        service: '/cpservice/seller/findbyuserids.json',
        data: {userIds: JSON.stringify(userIds)},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                $.each(resp.data, function() {
                    $('td[data-rel=' + this.userId + ']').html(this.code);
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};