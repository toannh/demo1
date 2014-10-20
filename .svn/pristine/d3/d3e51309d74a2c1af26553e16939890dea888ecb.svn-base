var facebookClient = {};
facebookClient.init = function () {
    if (typeof (FB) === 'undefined') {
        loading.hide();
        popup.msg("Chức năng đăng bán bằng facebook hiện chưa sẵn sàng, bạn vui lòng thử lại!");
        return false;
    }
};

facebookClient.signin = function (fn) {
    loading.show();
    facebookClient.init();
    FB.login(function (response) {
        if (response.authResponse) {
            if (fn)
                fn(response);
        } else {
            loading.hide();
            popup.msg("Hệ thống không thể lấy được thông tin của bạn từ facebook");
            return false;
        }
    }, {scope: 'user_photos,friends_photos,user_photo_video_tags,friends_photo_video_tags'});
};

facebookClient.getAlbum = function (fn) {
    facebookClient.signin(function (response) {
        console.log(response);
        FB.api(
                "/me/albums",
                {
                    access_token: response.authResponse.accessToken
                }, function (response) {
            console.log(response);
            loading.hide();
            if (fn)
                fn(response);
        });
    });
};

facebookClient.getphoto = function (id, fn) {
    facebookClient.signin(function (response) {
        console.log(response);
        FB.api(
                "/" + id + "/photos",
                function (response) {
                    loading.hide();
                    if (fn)
                        fn(response);
                }
        );
    });
};