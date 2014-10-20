var viewer = {};

viewer.init = function() {
    ajax({
        service: '/user/auth.json',
        loading: false,
        success: function(resp) {
            if (resp.success) {
                $('#navbar-user').html(template('/shop/tpl/navbarusersigned.tpl', resp.data));
                if ($('#user').length <= 0) {
                    $('body').append(template('/shop/tpl/navbarusersignedmenu.tpl', resp.data));
                    $('nav#user').mmenu();
                }
            } else {
                if ($('#user').length <= 0) {
                    $('body').append(template('/shop/tpl/navbarusermenu.tpl'));
                    $('nav#user').mmenu();
                }
            }
        }
    });
};

$(document).ready(function() {
    viewer.init();
});