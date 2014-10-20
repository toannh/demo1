smsOutbox = {};
smsOutbox.init = function() {
    $(".timeFrom").timeSelect();
    $(".timeTo").timeSelect();
    $(".sentTimeFrom").timeSelect();
    $(".sentTimeTo").timeSelect();
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị tin nhắn ChoDienTu", "/cp/smsoutbox.html"],
        ["Danh sách tin nhắn ChoDienTu"]
    ]);
};


smsOutbox.reSent = function(id) {
    ajax({
        service: '/cpservice/smsoutbox/resent.json',
        data: {id: id},
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

