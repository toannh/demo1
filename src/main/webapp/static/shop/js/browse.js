browse = {};
browse.init = function(params){
    browse.itemSearch = params.itemSearch;
};

browse.quickView = function(id) {
    ajax({
        service: '/item/getitem.json',
        loading: false,
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                 $('#popup-quick-view .container-fluid').html(template('/shop/tpl/quickview.tpl',resp));
            } else {
                popup.msg(resp.message);
            }
            $('.cloud-zoom, .cloud-zoom-gallery').CloudZoom();
             $('.imgdetail-slider').jcarousel({
                scroll: 1,
                auto: 0,
                animation: 800,
                wrap: 'last'
            });
        }
        
    });
};

browse.changeOrder = function(element) {
    document.location = baseUrl + urlUtils.shopBrowseUrl(browse.itemSearch, shop.alias, [{key: "order", val: $(element).val()},{key: "page", val: eval(browse.itemSearch.pageIndex)+1}]);
};