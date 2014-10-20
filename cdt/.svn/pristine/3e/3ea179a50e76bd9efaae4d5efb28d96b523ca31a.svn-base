index = {};
index.init = function() {
};

index.redirect = function(id, name) {
    window.open(baseUrl + "/san-pham/" + id + "/" + textUtils.createAlias(name) + ".html");
};

index.followSeller = function(sellerId) {
    ajax({
        service: '/user/follow.json?id=' + sellerId,
        contentType: 'json',
        loading: false,
        done: function(resp) {
            if (resp.success) {
                $('.like-count').html(resp.data);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
index.formatTimeBidBuyShop = function(endTime) {
    var timeBid = (endTime - new Date().getTime()) / (24 * 60 * 60 * 1000);
    return Math.floor(timeBid);
};