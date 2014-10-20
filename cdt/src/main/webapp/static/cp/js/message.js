message = {};
message.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị tin nhắn", "/cp/message.html"],
        ["Danh sách tin nhắn"]
    ]);
};
message.reset = function() {
    $('input[name=fromEmail]').val("");
    $('input[name=fromUserId]').val("");
    $('input[name=toEmail]').val("");
    $('input[name=toUserId]').val("");
};
message.pop = function() {
    popup.open('popup-search-email', 'Tìm kiếm email', template('/cp/tpl/message/loademail.tpl'), [
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-search-email');
            }
        }]);
};

message.cdtSendMail = function() {
    popup.open('popup-sendmail', 'ChợĐiệnTử - Gửi mail', template('/cp/tpl/message/send.tpl'), [
        {
            title: 'Gửi',
            style: 'btn-primary',
            fn: function() {
                $('div[rel=subject]').removeClass('has-error');
                $('span[rel=subject]').text('');
                $('div[rel=toEmail]').removeClass('has-error');
                $('span[rel=toEmail]').text('');
                $('div[rel=content]').removeClass('has-error');
                $('span[rel=content]').text('');
                var sendMessage = new Object();
                sendMessage.subject = '[ChợĐiệnTử]' + $('#send-message-emailcdt input[name=subject]').val() + '';
                sendMessage.toEmail = $('#send-message-emailcdt input[name=toEmail]').val();
                sendMessage.content = $('#send-message-emailcdt textarea[name=content]').val();
                if (sendMessage.subject == null || sendMessage.subject == '') {
                    $('div[rel=subject]').addClass('has-error');
                    $('span[rel=subject]').text('Bạn chưa nhập tiêu đề tin');
                }
                if (sendMessage.toEmail == null || sendMessage.toEmail == '') {
                    $('div[rel=toEmail]').addClass('has-error');
                    $('span[rel=toEmail]').text('Bạn chưa nhập địa chỉ email nhận tin!');
                }
                if (sendMessage.content == null || sendMessage.content == '') {
                    $('div[rel=content]').addClass('has-error');
                    $('span[rel=content]').text('Bạn chưa nhập nội dung tin!');
                }
                if (sendMessage.subject != null && sendMessage.subject != '' && sendMessage.toEmail != null && sendMessage.toEmail != '' && sendMessage.content == null || sendMessage.content != null && sendMessage.content == null || sendMessage.content != '') {
                    ajaxSubmit({
                        service: '/cpservice/message/sendmessagecdt.json',
                        data: sendMessage,
                        loading: false,
                        type: 'post',
                        contentType: 'json',
                        done: function(resp) {
                            if (resp.success) {
                                var emailUnSuccess = resp.data["emailUnSuccess"];
                                var emailSuccess = resp.data["emailSuccess"];
                                if (emailSuccess != null && emailSuccess.length > 0 && (emailUnSuccess == null || emailUnSuccess.length <= 0)) {
                                    popup.msg("Gửi tin nhắn thành công!", function() {
                                        location.reload();
                                    });
                                } else if (emailSuccess != null && emailSuccess.length > 0 && (emailUnSuccess != null || emailUnSuccess.length > 0)) {
                                    var htmlSuccess = '';
                                    var htmlUnSuccess = '';
                                    $.each(emailSuccess, function() {
                                        htmlSuccess += '<li>' + this + '</li>';
                                    });
                                    $.each(emailUnSuccess, function() {
                                        htmlUnSuccess += '<li>' + this + '</li>';
                                    });
                                    popup.msg("Gửi tin nhắn thành công đến email :  <ul>" + htmlSuccess + "</ul>,<br>Email không hợp lệ hoặc không tìm thấy,không thể gửi tin nhắn:<ul> " + htmlUnSuccess + "</ul>", function() {
                                        location.reload();
                                    });
                                } else if (emailSuccess == null || emailSuccess.length <= 0) {
                                    popup.msg("Gửi tin nhắn không thành công, không có email nào hợp lệ!", function() {
                                        location.reload();
                                    });
                                }
                            } else {
                                popup.msg(resp.message);
                            }
                        }
                    });
                }
            }
        }
    ]);
};
message.searchEmail = function() {
    var usersearch = {};
    usersearch.email = $('input[name=email]').val();
    if (usersearch.email === null || usersearch.email === '') {
        return false;
    }
    ajax({
        service: '/user/findbyemail.json',
        data: usersearch,
        loading: true,
        done: function(resp) {
            if (resp.success) {
                var html = "";
                if (resp.data.length <= 0) {
                    html += '<tr><td colspan="2" class="text-center text-danger">Không tìm thấy email nào!</td></tr>';
                } else {
                    $.each(resp.data, function() {
                        html += '<tr>';
                        html += '<th class="text-center">' + this.email + '</th>';
                        html += '<th class="text-center"><button type="button" onclick="message.selectEmail(\'' + this.email + '\');" class="btn btn-info">Chọn</button></th>';
                        html += '</tr>';
                    });
                }
                $(".loademailSearch").html(html);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

message.selectEmail = function(email) {
    var mailCheck = $('#send-message-emailcdt input[name=toEmail]').val();
    if (mailCheck === null || mailCheck === '') {
        mailCheck = email;
    } else {
        mailCheck = mailCheck + ',' + email;
    }
    $('#send-message-emailcdt input[name=toEmail]').val(mailCheck);
    popup.close('popup-search-email');
};
