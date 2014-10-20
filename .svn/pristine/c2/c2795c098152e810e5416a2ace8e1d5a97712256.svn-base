item = {};
var sameRuntime = null;
var amounts = 50000;
item.dataItem = {};
item.coutxeng = 0;
item.dataVipItems = new Array();

item.init = function (params) {
    item.itemSearch = params.itemSearch;
    item.catId = params.category.id;
    item.categoryName = params.category.name;
    item.promotion = params.itemPromotion;
    item.item = params.item;
    if (item.item.quantity <= 0 || item.item.endTime < new Date().getTime()) {
        item.loadItemSame(0);
    }
    item.loadColorAndSizeItem();
    item.countItemSame();
    item.loadOtherItem(0, 'samecategory');
    item.loadTimePromotiItem();
    textUtils.inputNumberFormat('numberBidFormat');
    item.loadItemReviews(0, 2);
    $('.review div.star-outer span').click(function () {
        $(this).addClass("icon-star choosed");
        $(this).prevAll().addClass("icon-star");
        $(this).nextAll().removeClass("icon-star");
    });
    item.checkReviewItem(item.item.id);
    item.loadInformationReview(item.item.id);
    item.loadInformationReviewSeller(item.item.sellerId);
    item.loadItemViewed('iconfloatLeft');
    item.upFree();

//view image mobile
    var detailSwiper = new Swiper('.swiper-container', {
        slidesPerView: 'auto',
        watchActiveIndex: true,
        paginationClickable: true,
        pagination: '.dmi',
        resizeReInit: true,
        keyboardControl: true,
        grabCursor: true,
        autoplay: 5000,
    });
    item.checkRoleOwnerview();
    item.iconSearchRight();
    var a = $("div[data-rel=detail_item] a");
    $.each(a, function () {
        var hvalue = $(this).attr('href');
        if (hvalue.indexOf("http://chodientu.vn/") !== -1) {
            $(this).attr("rel", "dofollow");
        } else {
            $(this).attr("rel", "nofollow");
        }
    });
};

item.loadTimePromotiItem = function () {
    var day = new Date().getTime();
    if (item.item.listingType == 'BUYNOW') {
        if (item.item.discount && item.promotion != null && item.promotion.type == 'DISCOUND') {
            if ((item.promotion.endTime - day) >= (30 * 24 * 60 * 60 * 1000)) {
                var dayNumber = (item.promotion.endTime - day) / (24 * 60 * 60 * 1000);
                $(".time-count").text(Math.floor(dayNumber));
                $(".time-count").each(function () {
                    var t = this;
                    var tout = $('.digits');
                    var ttext = $(t).html();
                    var t1 = ttext.substr(0, 1);
                    var t2 = ttext.substr(1, 1);
                    var t3 = ttext.substr(2, 1);
                    $(tout).html('<span class="number' + t1 + '"></span><span class="number' + t2 + '"></span><span class="number' + t3 + '"></span>')
                });
                $('div[data-rel=showDateTime]').append('<span class="bc-label">ngày</span>');
            } else {
                $(".digits").countdown({
                    image: "/static/market/images/digits.png",
                    format: "dd:hh:mm:ss",
                    digitWidth: 22,
                    digitHeight: 30,
                    endTime: new Date(item.promotion.endTime)
                });
                $('div[data-rel=showDateTime]').append('<div class="sf-row separation"><span class="bc-label">ngày</span><span class="bc-label">giờ</span><span class="bc-label">phút</span><span class="bc-label">giây</span></div>');
            }
        }
    } else {
        if (item.item.endTime > day && item.item.startTime <= day) {
            if ((item.item.endTime - day) >= (30 * 24 * 60 * 60 * 1000)) {
                var dayNumber = (item.item.endTime - day) / (24 * 60 * 60 * 1000);
                $(".time-count").text(Math.floor(dayNumber));
                $(".time-count").each(function () {
                    var t = this;
                    var tout = $('.digits');
                    var ttext = $(t).html();
                    var t1 = ttext.substr(0, 1);
                    var t2 = ttext.substr(1, 1);
                    var t3 = ttext.substr(2, 1);
                    $(tout).html('<span class="number' + t1 + '"></span><span class="number' + t2 + '"></span><span class="number' + t3 + '"></span>')
                });
                $('.countTotalDateBid').append('<span class="bc-label">ngày</span>');
            } else {
                $(".digits").countdown({
                    image: "/static/market/images/digits.png",
                    format: "dd:hh:mm:ss",
                    digitWidth: 22,
                    digitHeight: 30,
                    endTime: new Date(item.item.endTime)
                });
                $('div[data-rel=timeBidBuy]').append('<div class="sf-row separation"><span class="bc-label">ngày</span><span class="bc-label">giờ</span><span class="bc-label">phút</span><span class="bc-label">giây</span></div>');
            }
        }
    }
};


item.loadColorAndSizeItem = function () {
    var htColor = '';
    var htSize = '';
    var htmlColor = '';
    var htmlSize = '';

    if (proprt != null && proprt.length > 0) {
        $.each(proprt, function () {
            var prt = this;
            if (cateProperties != null && cateProperties.length > 0) {
                $.each(cateProperties, function () {
                    var catePro = this;
                    if (prt.categoryPropertyId == this.id && textUtils.createAlias(this.name) == 'mau-sac') {
                        $.each(catePropertyValues, function () {
                            var catePTV = this;
                            $.each(prt.categoryPropertyValueIds, function () {
                                if (this == catePTV.id) {
                                    htColor += '<div class="tc-item"  data-property-name="' + catePro.name + '" data-property-Id="' + prt.categoryPropertyId + '" data-propertyvalue-id="' + catePTV.id + '" data-propertyvalue-name="' + catePTV.name + '"  onclick="item.checkColorAndSize(this);" style="background-color:' + property.getCollor(textUtils.createAlias(catePTV.name)) + ';"></div>';
                                }
                            });
                        });
                        htmlColor += '<label>Màu sắc:</label>\
                                            <div class="text-outer">\
                                                <div class="tiny-colorsize tc-cursor" data-rel="collor" >' + htColor + '\
                                            </div>\
                                      </div>';

                    }
                    if (prt.categoryPropertyId == this.id && textUtils.createAlias(this.name) == 'kich-cox') {
                        $.each(catePropertyValues, function () {
                            var catePTV = this;
                            $.each(prt.categoryPropertyValueIds, function () {
                                if (this == catePTV.id) {
                                    htSize += '<div class="tc-item" onclick="item.checkColorAndSize(this);" data-property-name="' + catePro.name + '" data-property-Id="' + prt.categoryPropertyId + '" data-propertyvalue-id="' + catePTV.id + '" data-propertyvalue-name="' + catePTV.name + '" >' + catePTV.name + '</div>';
                                }
                            });
                        });
                        htmlSize += '<label>Kích thước:</label>\
                                <div class="text-outer">\
                                 	<div class="tiny-colorsize tc-cursor" data-rel="size" >' + htSize + '\
                                    </div>\
                                </div>';
                    }
                });
            }
        });
    }
    $('div[data-rel=show-color]').html(htmlColor);
    $('div[data-rel=show-size]').html(htmlSize);
};

item.loadItemSame = function (page) {
    var data = {};
    data.pageIndex = page;
    data.pageSize = 5;
    data.categoryIds = [];
    data.categoryIds.push(item.catId);
    data.priceFrom = item.item.sellPrice - (item.item.sellPrice / 10);
    data.priceTo = item.item.sellPrice + (item.item.sellPrice / 10);
    ajax({
        service: '/item/getotheritem.json',
        data: data,
        contentType: 'json',
        loading: false,
        type: 'post',
        done: function (resp) {
            if (resp.success) {
                var data = resp.data['itemPage'];
                var shops = resp.data['shops'];
                if (data.data.length > 0) {
                    $('.pd-sameproduct').html('');
                    for (var i = 0; i < data.data.length; i++) {
                        $('.pd-sameproduct').append(template('/market/tpl/itemvip.tpl', {data: data.data[i], shops: shops, cities: cities, itemSearch: item.itemSearch}));
                    }
                    $('.pd-sameproduct').append('<div class="clearfix"></div>');
                    $('.bv-control ul').html('');

                    if (data.pageIndex > 0) {
                        $('.bv-control ul').append('<li class="pre"><a onclick="item.loadItemSame(' + eval(data.pageIndex - 1) + ');">&lt;</a></li>');
                    }
                    if (data.pageIndex > 1) {
                        $('.bv-control ul').append('<li><a onclick="item.loadItemSame(' + eval(data.pageIndex - 2) + ');">' + eval(data.pageIndex - 2) + '</a></li>');
                    }
                    if (data.pageIndex > 0) {
                        $('.bv-control ul').append('<li><a onclick="item.loadItemSame(' + eval(data.pageIndex - 1) + ');">' + eval(data.pageIndex - 1) + '</a></li>');
                    }
                    $('.bv-control ul').append('<li class="active"><a>' + eval(data.pageIndex) + '</a></li>');
                    if (data.pageCount - data.pageIndex > 1) {
                        $('.bv-control ul').append('<li><a onclick="item.loadItemSame(' + eval(data.pageIndex + 1) + ')">' + eval(data.pageIndex + 1) + '</a></li>');
                    }
                    if (data.pageCount - data.pageIndex > 2) {
                        $('.bv-control ul').append('<li><a onclick="item.loadItemSame(' + eval(data.pageIndex + 2) + ')">' + eval(data.pageIndex + 2) + '</a></li>');
                    }
                    if (data.pageCount - data.pageIndex > 1) {
                        $('.bv-control ul').append('<li class="next"><a  onclick="item.loadItemSame(' + eval(data.pageIndex + 1) + ')">&gt;</a></li>');
                    }
                } else {
                    $('.showProductSame').hide();
                }
            }
        }
    });
};

item.countItemSame = function () {
    var data = {};
    data.pageIndex = 0;
    data.pageSize = 1;
    data.categoryIds = [];
    data.categoryIds.push(item.catId);
    data.priceFrom = item.item.sellPrice - (item.item.sellPrice / 10);
    data.priceTo = item.item.sellPrice + (item.item.sellPrice / 10);
    ajax({
        service: '/item/countitembyitemsearch.json',
        data: data,
        loading: false,
        type: 'POST',
        contentType: 'json',
        done: function (resp) {
            if (resp.success) {
                $('.countItemSame').text(resp.data.dataCount);
            }
        }
    });
};

item.loadItemViewed = function (positionShow) {
    var itemIds = textUtils.getCookie('itemviews');
    var ids = JSON.parse(itemIds);
    ids = JSON.parse(ids);
    ajax({
        service: '/item/getitembyids.json?positionShow=' + positionShow,
        data: ids,
        loading: false,
        type: 'POST',
        contentType: 'json',
        done: function (resp) {
            if (resp.success) {
                if (positionShow === 'iconfloatLeft') {
                    var data = resp.data['itemviewdList'];
                    if (data.length > 0) {
                        $('.itemViewedIconleft').html('');
                        for (var i = 0; i < (data.length > 10 ? 10 : data.length); i++) {
                            $('.itemViewedIconleft').append(template('/market/tpl/itemIconleftDetail.tpl', {data: data[i]}));
                        }
                    }
                } else {
                    var data = resp.data['itemviewdPage'];
                    var shops = resp.data['shops'];
                    $('li[for=sameprice]').removeClass('active');
                    $('li[for=itemviewed]').addClass('active');
                    $('li[for=samemanuf]').removeClass('active');
                    $('li[for=samecategory]').removeClass('active');
                    $('.others').hide();
                    if (data.data.length > 0) {
                        $('.showitemOther').html('');
                        for (var i = 0; i < (data.data.length > 4 ? 4 : data.data.length); i++) {
                            $('.showitemOther').append('<li>' + template('/market/tpl/itemvip.tpl', {data: data.data[i], shops: shops}) + '</li>');
                        }
                    }

                }
            }
        }
    });
};
item.loadOtherItem = function (page, tab) {
    var data = {};
    data.pageIndex = page;
    data.pageSize = 4;
    var itemId = item.item.id;
    if (tab == 'sameprice') {
        $('li[for=samemanuf]').removeClass('active');
        $('li[for=itemviewed]').removeClass('active');
        $('li[for=samecategory]').removeClass('active');
        $('li[for=sameprice]').addClass('active');
        data.categoryIds = [];
        data.categoryIds.push(item.catId);
        data.priceFrom = item.item.sellPrice - (item.item.sellPrice / 10);
        data.priceTo = item.item.sellPrice + (item.item.sellPrice / 10);
    } else if (tab == 'samemanuf') {
        $('li[for=sameprice]').removeClass('active');
        $('li[for=itemviewed]').removeClass('active');
        $('li[for=samecategory]').removeClass('active');
        $('li[for=samemanuf]').addClass('active');
        data.manufacturerIds = [];
        data.manufacturerIds.push(item.item.manufacturerId);
    } else if (tab == 'samecategory') {
        $('li[for=sameprice]').removeClass('active');
        $('li[for=itemviewed]').removeClass('active');
        $('li[for=samemanuf]').removeClass('active');
        $('li[for=samecategory]').addClass('active');
        data.categoryIds = [];
        data.categoryIds.push(item.item.categoryId);
    }
    ajax({
        service: '/item/getotheritem.json',
        data: data,
        contentType: 'json',
        loading: false,
        type: 'post',
        done: function (resp) {
            if (resp.success) {
                var data = resp.data['itemPage'];
                var shops = resp.data['shops'];
                if (data.data.length > 1) {
                    $('.showitemOther').html('');
                    for (var i = 0; i < data.data.length; i++) {
                        if (data.data[i].id != itemId) {
                            $('.showitemOther').append('<li>' + template('/market/tpl/itemvip.tpl', {data: data.data[i], shops: shops}) + '</li>');
                        }
                    }
                    $('.others').html('');
                    if (data.pageIndex > 0) {
                        $('.others').append('<a class="small-button pre" onclick="item.loadOtherItem(' + eval(data.pageIndex - 1) + ',\'' + tab + '\')"></a>');
                    }
                    if (data.pageIndex < data.pageCount - 1) {
                        $('.others').append('<a class="small-button next" onclick="item.loadOtherItem(' + eval(data.pageIndex + 1) + ',\'' + tab + '\')"></a>');
                    }
                } else {
                    $('.showitemOther').html('<div class="cdt-message bg-danger text-center">Không tìm thấy sản phẩm nào!</div>');
                    $('.others').html('');
                }
            }
        }
    });
};
item.loadItemIconFloatLeft = function (tab) {
    var data = {};
    data.pageIndex = 0;
    data.pageSize = 5;
    var itemId = item.item.id;
    if (tab == 'samePriceIconLeft') {
        data.categoryIds = [];
        data.categoryIds.push(item.catId);
        data.priceFrom = item.item.sellPrice - (item.item.sellPrice / 10);
        data.priceTo = item.item.sellPrice + (item.item.sellPrice / 10);
    } else if (tab == 'samemanufacturerIconLeft') {
        data.manufacturerIds = [];
        data.manufacturerIds.push(item.item.manufacturerId);
    } else if (tab == 'samecategoryIconLeft') {
        data.categoryIds = [];
        data.categoryIds.push(item.item.categoryId);
    }
    ajax({
        service: '/item/getotheritem.json',
        data: data,
        contentType: 'json',
        loading: false,
        type: 'post',
        done: function (resp) {
            if (resp.success) {
                var data = resp.data['itemPage'];
                if (data.data.length > 1) {
                    $('.samePriceIconLeft').html('');
                    $('.samemanufacturerIconLeft').html('');
                    $('.samecategoryIconLeft').html('');
                    for (var i = 0; i < data.data.length; i++) {
                        if (data.data[i].id != itemId) {
                            if (tab == 'samePriceIconLeft') {
                                $('.samePriceIconLeft').append(template('/market/tpl/itemIconleftDetail.tpl', {data: data.data[i]}));
                            } else if (tab == 'samecategoryIconLeft') {
                                $('.samecategoryIconLeft').append(template('/market/tpl/itemIconleftDetail.tpl', {data: data.data[i]}));
                            } else if (tab == 'samemanufacturerIconLeft') {
                                $('.samemanufacturerIconLeft').append(template('/market/tpl/itemIconleftDetail.tpl', {data: data.data[i]}));
                            }
                        }
                    }
                } else {
                    var html = '';
                    if (tab == 'samePriceIconLeft') {
                        html = '<div class="sh-noproduct">\
                                        <p>Hiện không có sản phẩm cùng khoảng giá. Hãy đăng bán ngay để được hiển thị tại đây</p>\
                                        <a class="btn btn-danger btn-lg" href="' + baseUrl + '/user/dang-ban.html">Đăng bán ngay</a>\
                                    </div>';
                        $('.samePriceIconLeft').html(html);
                    } else if (tab == 'samemanufacturerIconLeft') {
                        html = '<div class="sh-noproduct">\
                                        <p>Hiện không có sản phẩm cùng thương hiệu. Hãy đăng bán ngay để được hiển thị tại đây</p>\
                                        <a class="btn btn-danger btn-lg" href="' + baseUrl + '/user/dang-ban.html">Đăng bán ngay</a>\
                                    </div>';
                        $('.samemanufacturerIconLeft').html(html);
                    } else if (tab == 'samecategoryIconLeft') {
                        html = '<div class="sh-noproduct">\
                                        <p>Hiện không có sản phẩm cùng danh mục. Hãy đăng bán ngay để được hiển thị tại đây</p>\
                                        <a class="btn btn-danger btn-lg" href="' + baseUrl + '/user/dang-ban.html">Đăng bán ngay</a>\
                                    </div>';
                        $('.samecategoryIconLeft').html(html);
                    }
                }
            }
        }
    });
};
item.getMaps = function (address) {
    address = address + ",Việt Nam";
    var geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(15.00682, 100.83734);
    var mapOptions = {
        zoom: 15,
        center: latlng
    };
    geocoder.geocode({'address': address}, function (results, status) {
        if (status === google.maps.GeocoderStatus.OK) {
            $('#map-canvas').css('width', $('#ModalNormal .modal-dialog').width() * 0.95);
            $('#map-canvas').css('height', $('#ModalNormal .modal-dialog').width() * 2 / 3);
            var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location
            });
        } else {
            $('#map-canvas').css('height', '80');
            $('#map-canvas').html("<h5 class='text-center' style='color:red'>Không tìm thấy địa chỉ này!</h5>");
        }
    });
};
item.map = function () {
    var map_canvas = document.getElementById('map-canvas');
    var map_options = {
        center: new google.maps.LatLng(44.5403, -78.5463),
        zoom: 8,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    var map = new google.maps.Map(map_canvas, map_options)
};
item.sendPageSearch = function () {
    var data = {};
    data.categoryIds = [];
    data.categoryIds.push(item.catId);
    data.priceFrom = item.item.sellPrice - (item.item.sellPrice / 10);
    data.priceTo = item.item.sellPrice + (item.item.sellPrice / 10);
    location.href = baseUrl + urlUtils.browseUrl(data, item.categoryName);
};
/**
 *  Đấu giá
 */


item.bidItem = function (itemId) {
    var price = $('input[name=bidPrice]').val();
    if ((typeof price === 'undefined')) {
        price = 0;
    } else {
        price = price.replace(/\./g, '');
    }

    var auto = $('input[name=auto]').is(":checked");
    ajax({
        service: '/auction/bid.json',
        data: {itemId: itemId, price: price, auto: auto},
        type: 'get',
        done: function (resp) {
            if (resp.success) {
                popup.msg(resp.message, function () {
                    popup.close('popup-add-landing');
                    location.reload();
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
item.bidHistory = function (itemId) {
    ajax({
        service: '/auction/bidhistory.json',
        data: {itemId: itemId},
        loading: false,
        type: 'get',
        done: function (resp) {
            if (resp.success) {
                var bidHistory = resp.data["bidHistory"];
                var bider = resp.data["bider"];
                var userCount = resp.data["userCount"];
                popup.open('popup-bid-history', 'Có ' + bidHistory.length + ' lượt đấu', template('/market/tpl/item/bidhistory.tpl', {bidHistory: bidHistory, bider: bider, userCount: userCount}), [
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function () {
                            popup.close('popup-bid-history');
                        }
                    }
                ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
item.editItem = function (id) {
    if (id == null || id == '') {
        location.href = baseUrl + '/user/dang-ban.html';
    } else {
        location.href = baseUrl + '/user/dang-ban.html?id=' + id + '';
    }
};
item.removeItem = function (_id) {
    popup.confirm("Bạn chắc chắn muốn xóa sản phẩm này ?", function () {
        var ids = [];
        ids.push(_id);
        ajax({
            service: '/item/inactive.json',
            data: {ids: JSON.stringify(ids)},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    popup.msg(resp.message, function () {
                        location.href = baseUrl + '/user/item.html';
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

item.addComment = function (id) {
    var reviewItem = new Object();
    reviewItem.itemId = id;
    reviewItem.title = $('input[name=titlereview]').val();
    reviewItem.content = $('textarea[name=contentreview]').val();
    var checkRecommend = $('input[name=rd-buy]:checked').val();
    if (checkRecommend == 1) {
        reviewItem.recommended = true;
    } else {
        reviewItem.recommended = false;
    }
    reviewItem.point = $('.review div.star-outer span.choosed').attr('for');
    $('div[name=titleReview]').removeClass('has-error');
    $('div[name=contentReview]').removeClass('has-error');
    $('span[name=titleReview]').text('');
    $('span[name=contentReview]').text('');
    ajax({
        service: '/itemreview/commentitem.json',
        data: reviewItem,
        contentType: 'json',
        type: 'post',
        loading: false,
        done: function (resp) {
            if (resp.success) {
                xengplus.plus(200);
                $('.reviewSuccsess').html('<div class="cdt-message bg-success cm-icon"><span class="glyphicon glyphicon-ok"></span>Bạn đã được cộng 200 xèng vào tài khoản!</div>');
                item.loadItemReviews(0, 2);
                item.loadInformationReview(item.item.id);
            } else if (resp.message == 'CASH_FAIL') {
                $('.reviewSuccsess').html('<div class="cdt-message bg-success cm-icon"><span class="glyphicon glyphicon-ok"></span>Bạn đã comment đánh giá sản phẩm này!</div>');
                item.loadItemReviews(0, 2);
                item.loadInformationReview(item.item.id);
            } else {
                popup.msg(resp.message);
                $.each(resp.data, function (type, value) {
                    $('div[name=' + type + 'Review]').addClass('has-error');
                    $('span[name=' + type + 'Review]').text(value);
                });
            }
        }
    });
};
item.loadItemReviews = function (page, size, order) {
    ajax({
        service: '/itemreview/getitemreview.json',
        data: {itemId: item.item.id, pageIndex: page, pageSize: size, active: 1, orderBy: order},
        contentType: 'json',
        loading: false,
        type: 'post',
        success: function (resp) {
            if (resp.success) {
                var itemReviwes = resp.data['itemReviews'].data;
                var reviewers = resp.data['reviewers'];
                var html = "";
                if (itemReviwes !== null && itemReviwes.length > 0) {
                    $.each(itemReviwes, function () {
                        var review = this;
                        var username = '';
                        html += '<div class="grid">';
                        $.each(reviewers, function () {
                            if (this.id === review.userId) {
                                if (this.username != null && this.username != '') {
                                    username = this.username;
                                } else {
                                    username = this.email;
                                }
                                html += '<div class="img">\
                                        <div class="g-row"><a class="avatar"><img src="' + ((this.avatar !== null && this.avatar !== '') ? this.avatar : staticUrl + '/market/images/no-avatar.png') + '" /></a></div>\
                                        <div class="g-row"><a class="g-smalltitle" href="#">' + this.username + '</a></div>\
                                        <div class="g-row">';
                                if (this.skype !== null && this.skype !== '') {
                                    html += '<a href="skype:' + this.skype + '?chat"><span class="icon16-skype"></span></a>';
                                }
                                if (this.yahoo !== null && this.yahoo !== '') {
                                    html += '<a href="ymsgr:sendim?' + this.yahoo + '"><img src="http://opi.yahoo.com/online?u=' + this.yahoo + '&m=g&t=5"/></a>';
                                }
                                if (this.email !== null && this.email !== '') {
                                    html += '<a href="mail:' + this.email + '"><span class="icon-emailblue"></span></a>';
                                }
                                html += '</div></div>';
                            }
                        });
                        html += '<div class="g-content">\
                            <div class="g-row"><span class="g-title">' + this.title + '</span><span class="g-time">' + textUtils.formatTime(this.createTime, 'time') + '</span></div>\
                            <div class="g-row">';
                        if (this.point == 0) {
                            html += '  <div class="star-outer">\
                                         <span></span>\
                                         <span></span>\
                                         <span></span>\
                                        <span></span>\
                                         <span></span>\
                                   </div>';
                        } else if (this.point == 1) {
                            html += '  <div class="star-outer">\
                                         <span class="icon-star"></span>\
                                         <span></span>\
                                         <span></span>\
                                         <span></span>\
                                         <span></span>\
                                   </div>';
                        } else if (this.point == 2) {
                            html += '  <div class="star-outer">\
                                         <span class="icon-star"></span>\
                                         <span class="icon-star"></span>\
                                         <span></span>\
                                        <span></span>\
                                         <span></span>\
                                   </div>';
                        } else if (this.point == 3) {
                            html += '  <div class="star-outer">\
                                         <span class="icon-star"></span>\
                                         <span class="icon-star"></span>\
                                         <span class="icon-star"></span>\
                                         <span></span>\
                                         <span></span>\
                                   </div>';
                        } else if (this.point == 4) {
                            html += '  <div class="star-outer">\
                                         <span class="icon-star"></span>\
                                         <span class="icon-star"></span>\
                                         <span class="icon-star"></span>\
                                         <span class="icon-star"></span>\
                                         <span></span>\
                                   </div>';
                        } else if (this.point == 5) {
                            html += '  <div class="star-outer">\
                                         <span class="icon-star"></span>\
                                         <span class="icon-star"></span>\
                                         <span class="icon-star"></span>\
                                         <span class="icon-star"></span>\
                                         <span class="icon-star"></span>\
                                   </div>';
                        }
                        if (this.recommended) {
                            html += '- Bạn nên mua';
                        } else {
                            html += '- Bạn không nên mua';
                        }
                        html += '</div>\
                            <div class="g-row">' + this.content + '</div>\
                            <div class="g-row">\
                                 <span class="review-plus">200 xèng đã được cộng vào tài khoản của ' + username + '</span>\
                                 <span class="vote-textup" onclick="item.likeComment(\'' + this.id + '\',\'' + item.item.id + '\')" for="' + this.id + '"><span class="icon-voteup"></span>(' + this.like + ')</span>\
                             </div>\
                        </div></div>';
                    });
                    $('.review-list').html(html);
                    $('.graybox-title .review').html('(' + resp.data['itemReviews'].dataCount + ')');
                    $('.box-control .review').html('');
                    if (resp.data['itemReviews'].pageIndex > 0) {
                        $('.box-control .review').html('<a class="small-button pre" onclick="item.loadItemReviews(' + eval(resp.data['itemReviews'].pageIndex - 1) + ',' + size + ',' + order + ')"></a>');
                    }
                    if (resp.data['itemReviews'].pageIndex < resp.data['itemReviews'].pageCount - 1) {
                        $('.box-control .review').html('<a class="small-button next" onclick="item.loadItemReviews(' + eval(resp.data['itemReviews'].pageIndex + 1) + ',' + size + ',' + order + ')"></a>');
                    }
                    if (order == 1) {
                        $('.order_like').parent().find('a').removeClass('active');
                        $('.order_like').addClass('active');
                    } else {
                        $('.order_like').parent().find('a').removeClass('active');
                        $('.order_time').addClass('active');
                    }
                } else {
                    $('.reviewitem_title').hide();
                }
            }
        }
    });
};
item.likeComment = function (commentId, itemId) {
    ajax({
        service: '/itemreview/likecommnet.json',
        data: {commentId: commentId, itemId: itemId},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                $('.vote-textup[for=' + resp.data.id + ']').html('<span class="icon-voteup"></span>(' + resp.data.like + ')');
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
item.checkReviewItem = function (idItem) {
    ajax({
        service: '/itemreview/checkcommentbyuser.json',
        data: {idItem: idItem},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                $('.commentTitleCheck').hide();
                $('.reviewSuccsess').hide();
                $('.writeReview').hide();
            }
        }
    });
};
item.loadInformationReview = function (itemId) {
    ajax({
        service: '/itemreview/loadinforeviewitem.json',
        data: {itemId: itemId},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                if (resp.data != null) {
                    if (resp.data.recommended <= 0 && resp.data.comment <= 0) {
                        $('.recommendPurchase').text('Chưa có đánh giá!');
                        $('.recommendTrue').hide();
                    } else {
                        $('.recommendPurchase').text(resp.data.recommended / resp.data.comment * 100 + '%');
                        $('.recommendTrue').show();
                    }
                    $('.checkPointAndRecommend').text(resp.data.point + ' điểm / ' + resp.data.comment + ' đánh giá');
                    $('.fiveStar').text(resp.data.five);
                    $('.fourStar').text(resp.data.four);
                    $('.threeStar').text(resp.data.three);
                    $('.twoStar').text(resp.data.two);
                    $('.oneStar').text(resp.data.one);
                    var percentFive = resp.data.five / resp.data.comment * 100;
                    var percentFour = resp.data.four / resp.data.comment * 100;
                    var percentThree = resp.data.three / resp.data.comment * 100;
                    var percentTwo = resp.data.two / resp.data.comment * 100;
                    var percentOne = resp.data.one / resp.data.comment * 100;
                    $('.percentThreeStar').css('width', '' + percentThree + '%');
                    $('.percentFiveStar').css('width', '' + percentFive + '%');
                    $('.percentFourStar').css('width', '' + percentFour + '%');
                    $('.percentTwoStar').css('width', '' + percentTwo + '%');
                    $('.percentOneStar').css('width', '' + percentOne + '%');
                    var html = '';
                    if (resp.data.point <= 0) {
                        html = '<span></span><span></span><span></span><span></span><span></span>';
                    } else if (resp.data.point > 0 && resp.data.point <= 1) {
                        html = '<span class="icon-star"></span><span></span><span></span><span></span><span></span>';
                    } else if (resp.data.point > 1 && resp.data.point <= 2) {
                        html = '<span class="icon-star"></span><span class="icon-star"></span><span></span><span></span><span></span>';
                    } else if (resp.data.point > 2 && resp.data.point <= 3) {
                        html = '<span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span></span><span></span>';
                    } else if (resp.data.point > 3 && resp.data.point <= 4) {
                        html = '<span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span></span>';
                    } else if (resp.data.point > 4) {
                        html = '<span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span><span class="icon-star"></span>';
                    }
                    $('.starPointAndRecommend').html(html);
                }
            }
        }
    });
};
item.itemFollow = function (itemId) {
    ajax({
        service: '/itemreview/interestitem.json',
        data: {itemId: itemId},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                $('.countFollowItem').text(resp.data);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
item.warning = function (warningtype, itemId) {
    ajax({
        service: '/itemreview/warningoutofstock.json',
        data: {warningtype: warningtype, itemId: itemId},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                popup.msg("Cảm ơn bạn đã thông báo cho chúng tôi! Chúng tôi sẽ xem lại và điều chỉnh lại cho hợp lý");
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

item.upItem = function (_id) {
    listItems = [];
    listItems.push(_id);
    if (listItems.length > 0) {
        upschedule.payment(listItems);
    } else {
        popup.msg("Bạn hãy chọn sản phẩm để tiếp tục");
    }
};
item.upVipItem = function (_id) {
    listItems = [];
    listItems.push(_id);
    if (listItems.length > 0) {
        vipitem.payment(listItems);
    } else {
        popup.msg("Bạn hãy chọn sản phẩm để tiếp tục");
    }
};

item.sendQuestion = function (id) {
    ajax({
        service: '/item/get.json',
        data: {id: id},
        done: function (resp) {
            if (resp.success) {
                popup.open('popup-add', 'Hỏi thêm người bán về sản phẩm', template('/market/tpl/item/sendquestiontoseller.tpl', resp), [
                    {
                        title: 'Gửi',
                        style: 'btn-primary',
                        fn: function () {
                            ajaxSubmit({
                                service: '/item/sendquestion.json',
                                id: 'form-send',
                                contentType: 'json',
                                done: function (rs) {
                                    if (rs.success) {
                                        popup.msg(rs.message, function () {
                                            location.reload();
                                        });
                                    } else {
                                        // popup.msg(rs.message);
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: 'Đóng',
                        style: 'btn-default',
                        fn: function () {
                            popup.close('popup-add');
                        }
                    }
                ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

item.loadInformationReviewSeller = function (sellerId) {
    ajax({
        service: '/sellerreview/loadinforeviewseller.json',
        data: {sellerId: sellerId},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                $('.sellerReviewPoint').text("Điểm uy tín : " + resp.data.totalPoint + "");
                var reviewPercent = resp.data.good * 100 / (resp.data.good + resp.data.normal + resp.data.bad);
                reviewPercent = Math.round(reviewPercent);
                if (reviewPercent == 0 || isNaN(reviewPercent)) {
                    $('.sellerReviewPercent').parent('div').hide();
                } else {
                    $('.sellerReviewPercent').parent('div').show();
                    $('.sellerReviewPercent').text(reviewPercent + '%');
                }
            }
        }
    });
};

item.closeADV = function () {
    popup.confirm("<h4>Bạn đồng ý tắt chức năng này.</h4></br>Với giá: <b class='text-danger'>30.000</b> xèng", function () {
        ajax({
            service: '/item/closeadv.json',
            loading: true,
            done: function (resp) {
                if (resp.success) {
                    popup.msg(resp.message, function () {
                        if (resp.data.closeAdv) {
                            $('.showADVSeller').hide();
                        }
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

item.checkRoleOwnerview = function () {
    var checkRole = true;
    var htmlRole = '';
    if (adminRole != null && adminRole.length > 0) {
        $.each(adminRole, function () {
            if (this.functionUri == '/cp/item') {
                checkRole = false;
            }
        });
    }
    if (viewer != null && viewer.id != null && viewer.id != '' && viewer.id == item.item.sellerId && !checkRole && item.item.approved) {
        htmlRole = ' <div class="pd-userpost">\
                        <span class="icon24-user"></span>Quản trị của Admin ChợĐiệnTử\
                         <a onclick="item.editItem(\'' + item.item.id + '\');" href="javascript:;"><span class="icon16-edit"></span>Sửa</a>\
                        <a onclick="item.removeItem(\'' + item.item.id + '\');" href="javascript:;" ><span class="icon16-remove"></span>Xoá</a>\
                        <a onclick="item.upVipItem(\'' + item.item.id + '\');" href="javascript:;" ><span class="icon16-starvip"></span>Mua tin VIP</a>\
                        <a onclick="item.upItem(\'' + item.item.id + '\');"  href="javascript:;"><span class="icon16-uptin"></span>Up tin </a>\
                        <a style="cursor: pointer" data-id="' + item.item.id + '" data-rel="upS-free" ><a>\
                        <div class="pull-right">\
                            <a class="btn-admin-checknotok" onclick="item.adminUnApproved(\'' + item.item.id + '\');"  href="javascript:;"><i class="fa fa-minus-circle"></i>Không duyệt</a>\
                        </div>\
                    </div>';
    } else if (viewer != null && viewer.id != null && viewer.id != '' && viewer.id == item.item.sellerId && !checkRole && !item.item.approved) {
        htmlRole = ' <div class="pd-userpost">\
                        <span class="icon24-user"></span>Quản trị của Admin ChợĐiệnTử\
                         <a onclick="item.editItem(\'' + item.item.id + '\');" href="javascript:;"><span class="icon16-edit"></span>Sửa</a>\
                        <a onclick="item.removeItem(\'' + item.item.id + '\');" href="javascript:;" ><span class="icon16-remove"></span>Xoá</a>\
                        <a onclick="item.upVipItem(\'' + item.item.id + '\');" href="javascript:;" ><span class="icon16-starvip"></span>Mua tin VIP</a>\
                        <a onclick="item.upItem(\'' + item.item.id + '\');"  href="javascript:;"><span class="icon16-uptin"></span>Up tin </a>\
                        <a style="cursor: pointer" data-id="' + item.item.id + '" data-rel="upS-free" ><a><div class="pull-right">\
                            <a class="btn-admin-checkok" onclick="item.adminApproved(\'' + item.item.id + '\');"  href="javascript:;"><i class="fa fa-minus-circle"></i>Duyệt</a>\
                        </div>\
                    </div>';
    } else if (viewer != null && viewer.id != null && viewer.id != '' && viewer.id == item.item.sellerId && checkRole) {
        htmlRole = ' <div class="pd-userpost">\
                        <span class="icon24-user"></span>Quản trị của người bán\
                         <a onclick="item.editItem(\'' + item.item.id + '\');" href="javascript:;"><span class="icon16-edit"></span>Sửa</a>\
                        <a onclick="item.removeItem(\'' + item.item.id + '\');" href="javascript:;" ><span class="icon16-remove"></span>Xoá</a>\
                        <a onclick="item.upVipItem(\'' + item.item.id + '\');" href="javascript:;" ><span class="icon16-starvip"></span>Mua tin VIP</a>\
                        <a onclick="item.upItem(\'' + item.item.id + '\');"  href="javascript:;"><span class="icon16-uptin"></span>Up tin </a>\
                        <a style="cursor: pointer" data-id="' + item.item.id + '" data-rel="upS-free" ><a><span class="pull-right">Up ngay bằng SMS: soạn <b class="text-danger">CDT UP ' + item.item.id + '</b> gửi <b class="text-danger">8155 </b><span class="icon16-faq"></span></span>\
                    </div>';
    } else if (!checkRole && !item.item.approved) {
        htmlRole = ' <div class="pd-userpost">\
                        <span class="icon24-user"></span>Quản trị của Admin ChợĐiệnTử\
                        <div class="pull-right">\
                            <a class="btn-admin-checknotok" onclick="item.adminApproved(\'' + item.item.id + '\');"  href="javascript:;"><i class="fa fa-minus-circle"></i>Duyệt</a>\
                        </div>\
                    </div>';
    } else if (!checkRole && item.item.approved) {
        htmlRole = ' <div class="pd-userpost">\
                        <span class="icon24-user"></span>Quản trị của Admin ChợĐiệnTử\
                        <div class="pull-right">\
                            <a class="btn-admin-checknotok" onclick="item.adminUnApproved(\'' + item.item.id + '\');"  href="javascript:;"><i class="fa fa-minus-circle"></i>Không duyệt</a>\
                        </div>\
                    </div>';
    }
    $('.functionAdminRole').html(htmlRole);
};

item.adminUnApproved = function (id) {
    popup.open('popup-unApproveItem', 'Không duyệt sản phẩm', template('/market/tpl/item/itemunapprove.tpl'), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function () {
                var message = $('textarea[name=message]').val();
                if (message == null || message == '') {
                    $('textarea[name=message]').parents('.form-group').addClass('has-error');
                    $('span[for=message]').text('Bạn chưa nhập lý do hạ sản phẩm!');
                } else {
                    ajax({
                        service: '/item/disapproved.json',
                        loading: true,
                        data: {itemId: id, message: message},
                        done: function (resp) {
                            if (resp.success) {
                                popup.msg(resp.message, function () {
                                    location.reload();
                                });
                            } else {
                                popup.msg(resp.message);
                            }
                        }
                    });
                }
            }
        }
    ]);
};

item.adminApproved = function (id) {
    popup.open('popup-ApproveItem', 'Duyệt sản phẩm', template('/market/tpl/item/itemapprove.tpl'), [
        {
            title: 'Đồng ý',
            style: 'btn-primary',
            fn: function () {
                ajax({
                    service: '/item/approved.json',
                    loading: true,
                    data: {itemId: id},
                    done: function (resp) {
                        if (resp.success) {
                            popup.msg(resp.message, function () {
                                location.reload();
                            });
                        } else {
                            popup.msg(resp.message);
                        }
                    }
                });
            }
        }
    ]);
};

item.urlParam = function () {
    var urlParams;
    var match,
            pl = /\+/g, // Regex for replacing addition symbol with a space
            search = /([^&=]+)=?([^&]*)/g,
            decode = function (s) {
                return decodeURIComponent(s.replace(pl, " "));
            },
            query = window.location.search.substring(1);
    urlParams = {};
    while (match = search.exec(query))
        urlParams[decode(match[1])] = decode(match[2]);
    return urlParams;
};


item.iconSearchRight = function () {
    var urlParams = item.urlParam();
    if (typeof urlParams.keyword !== 'undefined' && urlParams.keyword !== null && urlParams.keyword !== '') {
        $('.iconSearchRight').css("display", "block");
        item.loadItemSearchIconRight(0);
    }
};
item.loadItemSearchIconRight = function (pageIndex) {
    var urlParams = item.urlParam();
    var data = {};
    data.pageIndex = pageIndex;
    data.pageSize = 1;
    data.keyword = urlParams.keyword;
    ajax({
        service: '/item/getotheritem.json',
        data: data,
        contentType: 'json',
        loading: false,
        type: 'post',
        done: function (resp) {
            if (resp.success) {
                var data = resp.data['itemPage'];
                var shops = resp.data['shops'];
                $('.contentitemIconSearchRight').html('');
                if (data.data.length > 0) {
                    if (item.item.id == data.data[0].id) {
                        item.loadItemSearchIconRight(data.pageIndex + 1);
                    } else {
                        $('.contentitemIconSearchRight').append(template('/market/tpl/item/itemIconSearchRight.tpl', {data: data.data[0], shops: shops, pageIndex: data.pageIndex, itemSearch: item.itemSearch}));
                    }
                } else {
                    $('.contentitemIconSearchRight').html('<h5 class="text-center">Không tìm thấy sản phẩm nào!</h5>');
                }
            }
        }
    });
};

item.nextItemIconSearchRight = function (id, name) {
    var urlParam = window.location.search.substring(1);
    location.href = baseUrl + '/san-pham/' + id + '/' + textUtils.createAlias(name) + '.html?' + urlParam + '';
};

item.upFree = function (id) {
    ajax({
        service: '/item/upfree.json',
        data: {id: id},
        loading: false,
        done: function (resp) {
            var upQuantity = 5 - eval(resp.data);
            if (upQuantity > 0) {
                if (resp.success) {
                    popup.msg("Sản phẩm đã được đưa lên đầu danh mục, bạn còn " + upQuantity + " lượt up tin miễn phí");
                }
                $("a[data-rel=upS-free]").html('<span class="icon16-uptin"></span><span class="text-danger cdt-tooltip" data-toggle="tooltip" data-placement="top" title="" data-original-title="Bạn còn ' + upQuantity + ' lần up tin miễn phí trong ngày">Up tin miễn phí</span>');
                $("a[data-rel=upS-free]").click(function () {
                    item.upFree($("a[data-rel=upS-free]").attr("data-id"));
                });

                $(".cdt-tooltip").tooltip();
            } else {
                $("â[data-rel=upS-free]").html('');
            }
        }
    });
};
item.checkColorAndSize = function (obj) {
    var parent = $(obj).parent();
    $('.logo-tick', $(parent)).remove();
    $('.tc-item', $(parent)).removeClass("active");
    $(obj).addClass("active");
    $(obj).append("<span class='logo-tick'></span>");
    return false;
};