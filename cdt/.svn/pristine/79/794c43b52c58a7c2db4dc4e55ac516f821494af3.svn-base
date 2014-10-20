email = {};

email.initEmail = function() {
    $(".hasDatepicker").timeSelect();
    editor('txt_content');
};
email.selectCustomer = function() {
    ajax({
        service: '/cpservice/news/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-add', 'Sửa tin', template('/cp/tpl/news/edit.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            ajaxUpload({
                                service: '/cpservice/news/edit.json',
                                id: 'form-add',
                                contentType: 'json',
                                done: function(rs) {

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
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
email.createEmail = function() {
    var cout = 0;
    var value = sellercustomer.readCookie('itemListCustomers');
    if (typeof value == 'undefined' || value == '') {
        cout = 0;
    } else {
        cout = value.length;
    }
    var body = CKEDITOR.instances['txt_content'].getData();
    var emailMarketing = new Object();
    emailMarketing.name = $("input[name=name]").val();
    emailMarketing.sendTime = $("input[name=sendDate]").val();
    emailMarketing.content = body;
    emailMarketing.email = value;
    emailMarketing.template = $("select[name=template]").find(':selected').val();
    ajax({
        service: '/emailmarketing/createemail.json',
        loading: true,
        data: emailMarketing,
        contentType: 'json',
        type: 'post',
        done: function(resp) {
            if (resp.success) {
                popup.msg(resp.message);
                sellercustomer.setCookie("itemListCustomers", null, -30);
                setTimeout(function() {
                    window.location = baseUrl + "/user/email-marketing.html";
                }, 2000);
            } else {
                $(".form-group").removeClass("has-error");
                $(".help-block").html("");
                if (resp.data.name != null) {
                    $("input[name=name]").parents(".form-group").addClass("has-error");
                    $("input[name=name]").parent('div').children('.help-block').html("Tiêu đề email không được bỏ trống.");
                }
                if (resp.data.content != null) {
                    $("textarea[name=content]").parents(".form-group").addClass("has-error");
                    $("textarea[name=content]").parent('div').children('.help-block').html("Nội dung email không được bỏ trống.");
                }
                if (resp.data.sendDate != null) {
                    $("input[name=sendDate]").parents(".form-group").addClass("has-error");
                    $("input[name=sendDate]").parent('div').children('.help-block').html("Thời gian gửi phải lớn hơn thời gian hiện tại");
                }
                if ($("input[name=sendDate]").val() == '0') {
                    $("input[name=sendDate]").parents(".form-group").addClass("has-error");
                    $("input[name=sendDate]").parent('div').children('.help-block').html("Thời điểm gửi mail không được bỏ trống.");
                }
                if (cout <= 0 || cout == undefined) {
                    $(".customers").parents(".form-group").addClass("has-error");
                    $('.customers_error').show();
                    $('.customers_error').html("Chưa chọn khách hàng nào");
                } else {
                    $('.customers_error').hide();
                }
            }
        }
    });
};

email.editEmail = function(emailId) {
    window.location = baseUrl + "/user/create-email-marketing.html?edit=" + emailId;
};

email.updateEmail = function(id) {
    var body = CKEDITOR.instances['txt_content'].getData();
    var emailMarketing = new Object();
    emailMarketing.id = id;
    emailMarketing.name = $("input[name=name]").val();
    emailMarketing.sendTime = $("input[name=sendDate]").val();
    emailMarketing.content = body;
    emailMarketing.template = $("select[name=template]").find(':selected').val();
    //console.log(emailMarketing);
    ajax({
        service: '/emailmarketing/updateEmail.json',
        loading: true,
        data: emailMarketing,
        contentType: 'json',
        type: 'post',
        done: function(resp) {
            if (resp.success) {
                sellercustomer.setCookie("itemListCustomers", null, -30);
                popup.msg(resp.message);
                setTimeout(function() {
                    window.location = baseUrl + "/user/email-marketing.html";
                }, 2000);
            } else {
                $(".form-group").removeClass("has-error");
                $(".help-block").html("");
                if (resp.data.name != null) {
                    $("input[name=name]").parents(".form-group").addClass("has-error");
                    $("input[name=name]").parent('div').children('.help-block').html("Tiêu đề email không được bỏ trống.");
                }
                if (resp.data.content != null) {
                    $("textarea[name=content]").parents(".form-group").addClass("has-error");
                    $("textarea[name=content]").parent('div').children('.help-block').html("Nội dung email không được bỏ trống.");
                }
                if (resp.data.sendDate != null) {
                    $("input[name=sendDate]").parents(".form-group").addClass("has-error");
                    $("input[name=sendDate]").parent('div').children('.help-block').html("Thời gian gửi phải lớn hơn thời gian hiện tại");
                }
                if ($("input[name=sendDate]").val() == '0') {
                    $("input[name=sendDate]").parents(".form-group").addClass("has-error");
                    $("input[name=sendDate]").parent('div').children('.help-block').html("Thời điểm gửi mail không được bỏ trống.");
                }
            }
            console.log(resp);
        }
    });
};

email.selectCustomerEmail = function(emailId) {
    location.href = baseUrl + '/user/custormers.html?marketing=' + emailId + '';
};

email.viewReport = function(emailId) {
    alert('Xem bao cao !!!');
};

email.preview = function() {
    var emailMarketing = new Object();
    var body = CKEDITOR.instances['txt_content'].getData();
    emailMarketing.name = $("input[name=name]").val();
    emailMarketing.sendTime = $("input[name=sendDate]").val();
    emailMarketing.content = body;
    emailMarketing.template = $("select[name=template]").find(':selected').val();
    ajax({
        service: '/emailmarketing/preview.json',
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
email.previewList = function(id) {
    ajax({
        service: '/emailmarketing/getemail.json',
        data: {id: id},
        loading: false,
        done: function(response) {
            if (response.success) {
                var emailMarketing = new Object();
                emailMarketing.name = response.data.name;
                emailMarketing.sendTime = response.data.sendTime;
                emailMarketing.content = response.data.content;
                emailMarketing.template = response.data.template;;
                ajax({
                    service: '/emailmarketing/preview.json',
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
            }
        }
    });


};

email.trySendMail = function() { 
    var email = $('input[name=emailTry]').val();
    var body = CKEDITOR.instances['txt_content'].getData();
    var emailMarketing = new Object();
    emailMarketing.name = $("input[name=name]").val();
    emailMarketing.sendTime = $("input[name=sendDate]").val();
    emailMarketing.content = body;
    emailMarketing.template = $("select[name=template]").find(':selected').val();
    if (email === '') {
        popup.msg('Vui lòng nhập địa chỉ email.');
    } else if (!/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/.test(email)) {
        popup.msg('Địa chỉ email không hợp lệ.');
    } else {
        emailMarketing.email = [email];
        ajax({
            service: '/emailmarketing/trysendmail.json',
            data: emailMarketing,
            contentType: 'json',
            loading: false,
            type: 'post',
            done: function(response) {
                popup.msg(response.message);
            }
        });
    }
};
//----------------------------------------------------------------------------//
email.initSms = function() {
    $(".timesend").timeSelect();
};
email.createSms = function() {
    var cout = 0;
    var value = sellercustomer.readCookie('itemListCustomers');
    if (typeof value == 'undefined' || value == '') {
        cout = 0;
    } else {
        cout = value.length;
    }
    var smslMarketing = new Object();
    smslMarketing.name = $("input[name=name]").val();
    smslMarketing.phone = value;
    smslMarketing.content = $("textarea[name=content]").val();
    if ($('input[name=checkTime]').attr('checked')) {
        smslMarketing.sendTime = new Date().getTime() + 60000;
    } else {
        smslMarketing.sendTime = $("input[name=sendTime]").val();
    }
    $('label[for=name]').parent('.form-group').removeClass(' has-error');
    $('label[for=sendTime]').parent('.form-group').removeClass(' has-error');
    $('label[for=content]').parent('.form-group').removeClass(' has-error');
    $('span[for=name]').html('');
    $('span[for=sendTime]').html('');
    $('span[for=content]').html('');
    ajax({
        service: '/emailmarketing/createsms.json',
        data: smslMarketing,
        loading: true,
        contentType: 'json',
        type: 'POST',
        done: function(resp) {
            if (resp.success) {
                popup.msg(resp.message, function() {
                    sellercustomer.setCookie("itemListCustomers", null, -30);
                    location.href = baseUrl + '/user/sms-marketing.html';
                });
            } else {
                //popup.msg(resp.message);
                $.each(resp.data, function(type, value) {
                    $('label[for=' + type + ']').parent('.form-group').addClass(' has-error');
                    $('span[for=' + type + ']').html(value);
                });
                if (cout <= 0 || cout == undefined) {
                    $(".customers").parents(".form-group").addClass("has-error");
                    $('.customers_error').show();
                    $('.customers_error').html("Chưa chọn khách hàng nào");
                } else {
                    $('.customers_error').hide();
                }
            }
        }
    });
};
email.editSmsMarketing = function(id) {
    location.href = baseUrl + '/user/create-sms-marketing.html?id=' + id + '';
};

email.editSaveSmsMarketing = function() {
    var smslMarketing = new Object();
    smslMarketing.id = $('input[name=id]').val();
    smslMarketing.name = $("input[name=name]").val();
    smslMarketing.sendTime = $("input[name=sendTime]").val();
    smslMarketing.content = $("textarea[name=content]").val();

    $('label[for=name]').parent('.form-group').removeClass(' has-error');
    $('label[for=sendTime]').parent('.form-group').removeClass(' has-error');
    $('label[for=content]').parent('.form-group').removeClass(' has-error');
    $('span[for=name]').html('');
    $('span[for=sendTime]').html('');
    $('span[for=content]').html('');
    ajax({
        service: '/emailmarketing/editsms.json',
        data: smslMarketing,
        loading: true,
        contentType: 'json',
        type: 'POST',
        done: function(resp) {
            if (resp.success) {
                popup.msg(resp.message, function() {
                    location.href = baseUrl + '/user/sms-marketing.html';
                });
            } else {
                popup.msg(resp.message);
                $.each(resp.data, function(type, value) {
                    $('label[for=' + type + ']').parent('.form-group').addClass(' has-error');
                    $('span[for=' + type + ']').html(value);
                });
            }
        }
    });
};

email.countCharacters = function() {
    var count = 130 - eval($('textarea[name=content]').val().length);
    $('span.countresult').html(count + '/130');
};