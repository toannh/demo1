var auth = {};

auth.login = function(_uri) {
    popup.open('popup-login', 'Đăng nhập', template('/market/tpl/signin.tpl', {uri: _uri}), []);
};

auth.addEmailFooterMarket = function() {
    ajaxSubmit({
        service: '/email/addemail.json',
        id: 'email-add-form-footer',
        type: 'post',
        contentType: 'json',
        loading: false,
        done: function(resp) {
            if (resp.success) {
                popup.msg(resp.message,function(){
                    location.reload();
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};