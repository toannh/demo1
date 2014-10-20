
var notification = {};

notification.config = function () {
    var data = [
        ["82930526079", "Chương trình thưởng xèng gắn kết thành viên trên ChợĐiệnTử", "http://chodientu.vn/tin-tuc/thong-bao--chuong-trinh-thuong-xeng---gan-ket-thanh-vien-82930526079.html"],
        ["397378558467", "Chương trình miễn phí vận chuyển khi thanh toán qua Ngân Lượng trên ChợĐiệnTử", "http://chodientu.vn/tin-tuc/chuong-trinh-mien-phi-van-chuyen-khi-thanh-toan-qua-ngan-luong-tren-chodientu-397378558467.html"],
        ["502705593153", "Hướng dẫn thanh toán để được nhận ưu đãi miễn phí vận chuyển", "http://chodientu.vn/tin-tuc/huong-dan-thanh-toan-de-duoc-huong-uu-dai-mien-phi-van-chuyen--502705593153.html"],
        ["584288551362", "Thông báo mời tham gia chương trình promotion tại CDT", "http://chodientu.vn/tin-tuc/thong-bao-moi-tham-gia-chuong-trinh-promotion-tai-cdt--584288551362.html"],
        ["468219533299", "Tăng lượt up tin miễn phí cho người bán tại CDT", "http://chodientu.vn/tin-tuc/tang-luot-uptin-mien-phi-cho-nguoi-ban-468219533299.html"]
    ];
    return data;
};

notification.write = function () {
    var data = notification.config();
    var nf = textUtils.getCookie("cdtnotification");
    if (typeof nf === "undefined" || nf === '') {
        nf = {};
    } else {
        nf = JSON.parse(base64.decode(nf));
    }

    if (typeof data === "undefined" || data.length === 0) {
        return;
    }

    var history = [];
    $.each(nf, function (key, val) {
        history.push(val);
    });

    for (var i = 0; i < history.length; i++) {
        var hs = history[i];
        for (var j = 0; j < data.length; j++) {
            var index = data[j][0].indexOf(hs);
            if (index !== -1) {
                data.splice(j, 1);
            }
        }
    }

    if (typeof data !== "undefined" && data.length > 0) {
        var text = '';
        $.each(data, function (aaa, news) {
            var url = news[2] + "?rel=notification&code=" + news[0] + "";
//            var url = baseUrl + '/tin-tuc/' + textUtils.createAlias(news[1]) + '-' + news[0] + '.html?rel=notification&code=' + news[0] + '';
            text += '<li><a href="' + url + '" target="_blank">' + news[1] + '</a></li>';
        });
        var html = '<div class="smart-notification">\
                    <div class="sn-inner">\
                        <div class="sn-button active"><span class="icon42-bell-transparent"></span><span class="sn-nummer">' + data.length + '</span></div>\
                        <div class="sn-view">\
                            <div class="sn-title">\
                                <span class="icon42-bell-orange"></span>   \
                            </div>\
                            <div class="sn-content">\
                                <ul data-rel="Notification"> ' + text + '\
                                </ul>\
                            </div>\
                        </div>\
                    </div>\
                    </div>';
    }
    $("body").after(html);
};

notification.read = function () {
    var code = textUtils.urlParam().code;
    if (typeof code === "undefined" || code === '')
        return;
    var nf = textUtils.getCookie("cdtnotification");
    if (typeof nf === "undefined" || nf === '') {
        nf = base64.encode(JSON.stringify({}));
    }
    nf = JSON.parse(base64.decode(nf));
    nf[textUtils.createAlias("code_" + code)] = code;
    textUtils.setCookie("cdtnotification", base64.encode(JSON.stringify(nf)), 360);
};

$(document).ready(function () {
    notification.write();
});