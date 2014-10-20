
market = {};
market.init = function(params) {
    market.itemSearch = params.itemSearch;
    market.catId = params.category;
    market.categoryName = params.category;
//    console.log(market.itemSearch);
    var mySwiper = new Swiper('.swiper-container', {
        slidesPerView: 'auto',
        watchActiveIndex: true,
        paginationClickable: true,
        resizeReInit: true,
        keyboardControl: true,
        grabCursor: true,
        autoplay: 5000
    });
};
$(document).ready(function() {
    var myTimer;
    $('.box .box-content').hover(function() {
        clearInterval(myTimer);
        var ccc = $(this).attr('id');
        var arrTab = [];
        $('ul.' + ccc).find('a').each(function() {
            var res = $(this).attr('class').replace("tab", "");
            arrTab.push(res);
        });
        myTimer = setInterval(function() {
            var item = arrTab[Math.floor(Math.random() * arrTab.length)];
            market.loadtab(ccc, item);
        }, 30000);
    }, function() {
        clearInterval(myTimer);

    });
});
market.sendPageSearch = function(categoryId, categoryName, sellPrice) {
    market.itemSearch.categoryIds = [];
    market.itemSearch.categoryIds.push(categoryId);
    market.itemSearch.priceFrom = parseInt(sellPrice - (sellPrice / 10));
    market.itemSearch.priceTo = parseInt(sellPrice + (sellPrice / 10));

    var url = baseUrl + urlUtils.browseUrl(market.itemSearch, categoryName);
    window.open(url, '_blank');
};
market.loadtab = function(classId, id) {
    $('ul.' + classId).find('a').each(function() {
        $(this).parent('li').removeClass('active');
    });
    $('ul.' + classId).find('a.tab' + id).parent('li').addClass('active');
    $.ajax({
        url: baseUrl + '/market/gettab.json',
        data: {'id': id},
        dataType: 'JSON',
        success: function(resp) {
            if (resp.success) {
                $('#' + classId).html(template('/market/tpl/featuredcategory/' + resp.data.template + '.tpl', resp.data));
            }
        }
    });
};
market.loadmodel = function(subId, position, el) {
    $.ajax({
        url: baseUrl + '/market/getmodelbymanufac.json',
        data: {subId: subId, position: position},
        dataType: 'JSON',
        success: function(resp) {
            if (resp.success) {
                var idModel = "";
                var htmlModel = "";
                var imageModel = "";
                var nameModel = "";
                var newMinPrice = 0;
                var countShop = 0;
                var ulLiModel = "";
                var linkM = "";
                var priceThamKhao = 0;
                if (resp.data.models !== null) {
                    for (var i = 0; i < 1; i++) {
                        idModel = resp.data.models[i].id;
                        imageModel = resp.data.models[i].images[0];
                        nameModel = resp.data.models[i].name;
                        countShop = resp.data.models[i].countShop;
                        var newMinPrice = resp.data.models[i].newMinPrice;
                        var oldMinPrice = resp.data.models[i].oldMinPrice;
                        var pri = 0;
                        if (oldMinPrice > 0) {
                            pri =  oldMinPrice;
                        }
                        if (newMinPrice > 0) {
                            pri =  newMinPrice;
                        } 
                        if (newMinPrice > 0 && oldMinPrice > 0) {
                            pri =  newMinPrice>oldMinPrice?oldMinPrice:newMinPrice;
                        } 
                        priceThamKhao = parseFloat(pri).toMoney(0, ',', '.');
                        linkM = baseUrl + '/model/' + resp.data.models[i].id + '/' + textUtils.createAlias(resp.data.models[i].name) + '.html';
                    }
                    for (var i = 1; i < resp.data.models.length; i++) {
                        ulLiModel += '<li><a href="' + baseUrl + '/model/' + resp.data.models[i].id + '/' + textUtils.createAlias(resp.data.models[i].name) + '.html">' + resp.data.models[i].name + '</a></li>';
                    }
                    var linkModelSeller = urlUtils.browseUrl(market.itemSearch, nameModel, [{key: "cid", op: "rm"}, {op: "mk", key: "models", val: idModel}]);

                    htmlModel += '<div id="home3tab1" class="tab-content" style="display:block;">' +
                            '<div class="home3-product">' +
                            '<div class="hp-thumb"><a href="' + linkM + '"><img src="' + imageModel + '" alt="' + nameModel + '"></a></div>' +
                            '<div class="hp-row">Model: ' + nameModel + '</div>' +
                            '<div class="hp-row">Giá tham khảo: <span class="hp-price">'+ priceThamKhao+'<sup class="u-price">đ</sup></span></div>' +
                            '<div class="hp-row">Hiện có: <a href="' + linkModelSeller + '">' + countShop + ' cửa hàng đang bán</a></div>' +
                            '</div>' +
                            '<div class="home3-line"></div>' +
                            '<ul class="home3-ul">' + ulLiModel +
                            '</ul>' +
                            '</div>';
                }
                $('#viewModel').html(htmlModel);
                $('.tab-title').find('a').each(function() {
                    $(this).removeClass('active');
                });
                $(el).addClass('active');

            }
        }
    });
};

market.quickview = function(id) {
    ajax({
        service: '/market/getitem.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-add', 'Chi tiết sản phẩm', template('/market/tpl/quickviewitem.tpl', resp), [
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-add');
                        }
                    }
                ], 'modal-lg');

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
market.linkShop = function(name) {
    if (name !== '') {
        var url = baseUrl + "/" + name + "/";
        window.open(url, '_blank');
    }
};


