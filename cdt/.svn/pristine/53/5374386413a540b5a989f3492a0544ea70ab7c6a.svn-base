auth = {};

auth.reloadCaptcha = function() {
    $('.box-captcha img').hide();
    $('.box-captcha img').attr('src', baseUrl + '/captcha.api?time=' + new Date().getTime());
    $('.box-captcha img').show();
};

auth.init = function() {
    $('.box-captcha img').attr('src', baseUrl + '/captcha.api?time=' + new Date().getTime());
    $('input[name=dob]').datepicker({dateFormat: "dd/mm/yy", appendText: "(Ngày/Tháng/Năm)"});
    $('select[name=districtId] option[value!=0]').hide();
    $('select[name=districtId] option[for=' + $('select[name=cityId]').val() + ']').show();
    $('select[name=cityId]').change(function() {
        $('select[name=districtId] option[value!=0]').hide();
        $('select[name=districtId] option[for=' + $(this).val() + ']').show();
        $('select[name=districtId]').val(0);
    });
};

auth.login = function() {
    popup.open('popup-login', 'Đăng nhập', template('/user/tpl/signin.tpl', null), []);
};


auth.addEmailFooterBackEndUser = function() {
    ajaxSubmit({
        service: '/email/addemail.json',
        id: 'email-add-form-footer',
        type: 'post',
        contentType: 'json',
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