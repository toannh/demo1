emailOutbox = {};
emailOutbox.init = function() {
     layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị hòm thư ChợĐiệnTử", "/cp/emailoutbox.html"],
        ["Danh sách thư"]
    ]);
    $(".timeFrom").timeSelect();
    $(".timeTo").timeSelect();
    $(".sentTimeFrom").timeSelect();
    $(".sentTimeTo").timeSelect();
};

emailOutbox.get = function(id) {
    ajax({
        service: '/cpservice/emailoutbox/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                if ($("#preview").length > 0) {
                    $("#preview").remove();
                }
                $("body").append(template('/cp/tpl/emailoutbox/body.tpl', resp));
                $("#preview").dialog({width: 700, draggable: false, resizable: false, modal: true});
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

emailOutbox.reSent = function(id) {
    ajax({
        service: '/cpservice/emailoutbox/resent.json',
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
emailOutbox.response = function(id) {
    ajax({
        service: '/cpservice/emailoutbox/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-error', 'Lỗi gửi mail', template('/cp/tpl/emailoutbox/response.tpl', resp), [
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-error');
                        }
                    }
                ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
