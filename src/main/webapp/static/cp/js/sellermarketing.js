sellerMarketing = {};
sellerMarketing.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị marketting người bán", "/cp/administrator.html"],
        ["Danh sách quản trị viên"]
    ]);
    $(".timeFrom").timeSelect();
    $(".timeTo").timeSelect();
};
sellerMarketing.activeEmail = function(id, type) {
    var msg = "Duyệt email marketing này ?";
    if (type == "inactive")
        msg = "Không duyệt email marketing này ?";
    popup.confirm(msg, function() {
        ajax({
            service: '/cpservice/emailmarketing/changeactiveemail.json',
            data: {id: id},
            loading: false,
            done: function(resp) {
                console.log(resp);
                if (resp.success) {
                    popup.msg(resp.message);
                    setTimeout(function() {
                        location.reload();
                    }, 2000);
                } else {
                    if (resp.message != null)
                        popup.msg(resp.message);
                    else
                        popup.msg("Email marketing này chưa có danh sách người gửi !");
                }
            }
        });
    });
};

sellerMarketing.previewEmail = function(id) {
    var emailMarketing = new Object();
    emailMarketing.name = $("." + id).children('td[for=name]').text().trim();
    emailMarketing.sendTime = $("." + id).children("td[for=sendTime]").text().trim();
    emailMarketing.content = $("." + id).children("td[for=content]").text().trim();
    emailMarketing.template = $("." + id).children("td[for=template]").text().trim();
    emailMarketing.sellerId = $("." + id).children("td[for=sellerId]").text().trim();
    ajax({
        service: '/cpservice/sellermarketing/preview.json',
        data: emailMarketing,
        loading: true,
        contentType: 'json',
        type: 'post',
        done: function(response) {
            if (response.success) {
                popup.open('popup-email-preview', 'Xem trước nội dung email', template('/user/tpl/emailpreview.tpl', {template: response.data}), [
                    {
                        title: 'Thoát',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-email-preview');
                        }
                    }
                ], 'modal-lg');
            } else {
                popup.msg(response.message);
            }
        }
    });
};

sellerMarketing.activeSms = function(id, type) {
    var msg = "Duyệt sms marketing này ?";
    if (type == "inactive")
        msg = "Không duyệt sms marketing này ?";
    popup.confirm(msg, function() {
        ajax({
            service: '/cpservice/smsmarketing/changeactivesms.json',
            data: {id: id},
            done: function(resp) {
                console.log(resp);
                if (resp.success) {
                    popup.msg(resp.message, function() {
                        location.reload();
                    });
                    setTimeout(function() {
                        location.reload();
                    }, 2000);
                } else {
                    if (resp.message != null)
                        popup.msg(resp.message);
                    else
                        popup.msg("Sms marketing này chưa có danh sách số điện thoại cần gửi !");
                }
            }
        });
    });
};
