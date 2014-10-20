var auth = {};

auth.init = function() {

};

auth.googleSingin = function() {
    var clientId = '164279864027-pd99l9vgndqg0to2cfca9dgumu9f7c7v.apps.googleusercontent.com';
    var apiKey = 'AIzaSyB7gyXGvaYFwXPrRYpwXDJa1R42uchwxi4';
    var scopes = 'https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/plus.profile.emails.read';
    gapi.client.setApiKey(apiKey);
    window.setTimeout(function() {
        gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: false}, function(authResult) {
            gapi.client.load('plus', 'v1', function() {
                var request = gapi.client.plus.people.get({
                    'userId': 'me'
                });
                request.execute(function(resp) {
                    if (resp != "" && resp.result != "") {
                        var user = {
                            email: resp.emails[0].value,
                            description: 'Tên : ' + resp.displayName + ' , giới tính : ' + resp.gender,
                        };
                        ajax({
                            service: '/cpservice/global/auth/signin.json',
                            data: user,
                            type: "post",
                            contentType: 'json',
                            done: function(resp) {
                                if (resp.success) {
                                    location.href = baseUrl + resp.data;
                                } else {
                                    popup.msg("Tài khoản " + user.email + " không có quyền đăng nhập hệ thống quản trị ChoDienTu.vn");
                                }
                            }
                        });
                    }
                });
            });
        });
    }, 1);
};

auth.googleSignout = function() {
    gapi.auth.signOut();
    location.href = baseUrl + "/cp/auth/signout.html";
};