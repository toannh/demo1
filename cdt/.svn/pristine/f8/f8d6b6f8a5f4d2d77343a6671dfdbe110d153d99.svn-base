email = {};
email.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị email nhận khuyến mại", "/cp/email.html"],
        ["Danh sách email"]
    ]);
};


email.delEmailNewsletter = function(email) {
    ajax({
        service: '/cpservice/emailnewsletter/del.json',
        data: {email: email},
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

email.exportEmailNewsletter = function() {
    var seftUrl = baseUrl + "/cp/email/excel.html";
    urlParams = new Object();
    urlParams.email = $('input[name=email]').val();
    var queryString = "";
    var i = 1;
    $.each(urlParams, function(key, val) {
        if (val != null) {
            if (i == 1)
                queryString += "?";
            else
                queryString += "&";
            queryString += key + "=" + val;
            i++;
        }
    });
    location.href = seftUrl + queryString;
};
