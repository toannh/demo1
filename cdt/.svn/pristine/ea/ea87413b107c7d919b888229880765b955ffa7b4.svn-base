var shopcontact = {};
shopcontact.send = function() {
    ajaxSubmit({
        service: '/shopcontact/send.json',
        id: 'shopContact',
        contentType: 'json',
        loading: false,
        done: function(resp) {
            if (resp.success) {
                popup.msg(resp.message, function() {
                    location.reload();
                });
            } else {
               
            }
        }
    });
};
