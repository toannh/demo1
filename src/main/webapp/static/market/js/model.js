model = {};
model.init = function (params) {
    model.modelId = params.model.id;
    model.model = params.model;
    model.loadModelTopReview();
    model.loadModelReview(0, 2);
    model.loadOtherItem(0, 'samemanuf');
    model.loadModelItem(0, 0, '', '', []);
    $('.review div.star-outer span').click(function () {
        $(this).prevAll().addClass("icon-star");
        $(this).addClass("icon-star choosed");
        $(this).nextAll().removeClass("icon-star");
    });
    model.loadStarModel();
};

model.sendReview = function () {
    var title = $('input[name=title]').val();
    var content = $('textarea[name=content]').val();
    var point = $('.review div.star-outer span.choosed').attr('for');
    var recomment = $('.review input[name=rd-buy]:checked').val();

    $('textarea[name=content]').parents('.form-group').removeClass('has-error');
    $('textarea[name=content]').next('.help-block').remove();
    if (typeof content === 'undefined' || content.trim() === '') {
        $('textarea[name=content]').parents('.form-group').addClass('has-error');
        $('textarea[name=content]').after('<span class="help-block">Nội dung đánh giá phải được nhập</span>');
        return false;
    }
    ajax({
        service: '/model/sendreview.json',
        data: {modelId: model.modelId, title: title, content: content, point: point, recommended: recomment == 1},
        contentType: 'json',
        loading: false,
        type: 'post',
        success: function (resp) {
            if (resp.success) {
                if (resp.message !== 'COMMENT_MODEL_FAIL') {
                    xengplus.plus(200);
                    $('.reviewBox').html('<div class="cdt-message bg-success cm-icon"><span class="glyphicon glyphicon-ok"></span>Bạn đã được cộng 200 xèng vào tài khoản!</div>');
                    model.loadModelReview(0, 2);
                    model.loadStarModel();
                } else {
                    $('.reviewBox').html('<div class="cdt-message bg-success cm-icon"><span class="glyphicon glyphicon-ok"></span>Bạn đã comment đánh giá model này!</div>');
                    model.loadModelReview(0, 2);
                    model.loadStarModel();
                }
            }
            else {
                popup.msg(resp.message);
            }
        }
    });
};

model.loadModelReview = function (page, size, order) {
    ajax({
        service: '/model/getmodelreview.json',
        data: {modelId: model.modelId, pageIndex: page, pageSize: size, active: 1, orderBy: order},
        contentType: 'json',
        loading: false,
        type: 'post',
        success: function (resp) {
            if (resp.success) {
                var modelReviwes = resp.data['modelReviews'].data;
                var reviewers = resp.data['reviewers'];
                var html = "";
                if (modelReviwes !== null && modelReviwes.length > 0) {
                    $.each(modelReviwes, function () {
                        var review = this;
                        var username = '';
                        html += '<div class="grid">';
                        $.each(reviewers, function () {
                            if (this.id === review.userId) {
                                username = this.username;
                                html += '<div class="img">\
                                        <div class="g-row"><a class="avatar" href="#"><img src="' + ((this.avatar !== null && this.avatar !== '') ? this.avatar : staticUrl + '/market/images/no-avatar.png') + '" /></a></div>\
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
                            <div class="g-row"><span class="g-title">' + this.title + '</span><span class="g-time">' + textUtils.formatTime(this.time, 'time') + '</span></div>\
                            <div class="g-row">\
                                 <div class="star-outer"></div>';
                        if (this.recommended) {
                            html += '- Bạn nên mua';
                        } else {
                            html += '- Bạn không nên mua';
                        }
                        html += '</div>\
                            <div class="g-row">' + this.content + '</div>\
                            <div class="g-row">\
                                 <span class="review-plus">200 xèng đã được cộng vào tài khoản của ' + username + '</span>\
                                 <span class="vote-textup" onclick="model.likeComment(\'' + this.id + '\')" for="' + this.id + '"><span class="icon-voteup"></span>(' + this.like + ')</span>\
                             </div>\
                        </div></div>';
                    });
                    $('.review-list').html(html);
                    $('.graybox-title .review').html('(' + resp.data['modelReviews'].dataCount + ')');
                    $('.box-control .review').html('');
                    if (resp.data['modelReviews'].pageIndex > 0) {
                        $('.box-control .review').html('<a class="small-button pre" onclick="model.loadModelReview(' + eval(resp.data['modelReviews'].pageIndex - 1) + ',' + size + ',' + order + ')"></a>');
                    }
                    if (resp.data['modelReviews'].pageIndex < resp.data['modelReviews'].pageCount - 1) {
                        $('.box-control .review').html('<a class="small-button next" onclick="model.loadModelReview(' + eval(resp.data['modelReviews'].pageIndex + 1) + ',' + size + ',' + order + ')"></a>');
                    }
                    if (order == 1) {
                        $('.order_like').parent().find('a').removeClass('active');
                        $('.order_like').addClass('active');
                    } else {
                        $('.order_like').parent().find('a').removeClass('active');
                        $('.order_time').addClass('active');
                    }
                } else {
                    $('.review_title').hide();
                }

            }
        }
    });
};
model.loadModelTopReview = function () {
    ajax({
        service: '/model/getmodelreview.json',
        data: {modelId: model.modelId, pageIndex: 0, pageSize: 1, active: 1, orderBy: 1},
        contentType: 'json',
        loading: false,
        type: 'post',
        success: function (resp) {
            if (resp.success) {
                var modelReviews = resp.data['modelReviews'].data;
                var reviewers = resp.data['reviewers'];
                if (modelReviews !== null && modelReviews.length > 0) {
                    $('.topreview').html('<div class="model-topreview"><span class="mt-bullet"></span>\
                                <div class="mt-text">' + modelReviews[0].content + '</div>\
                                <div class="mt-right">' + reviewers[0].username + '&nbsp;&nbsp;|&nbsp;&nbsp;<span>' + textUtils.formatTime(modelReviews[0].time, 'time') + '</span></div></div>');
                }
            }
        }
    });
};


model.loadStarModel = function () {
    ajax({
        service: '/model/getmodelreview.json',
        data: {modelId: model.modelId, pageIndex: 0, pageSize: 10000},
        contentType: 'json',
        loading: false,
        type: 'post',
        done: function (resp) {
            if (resp.success) {
                var oneStar = 0;
                var twoStar = 0;
                var threeStar = 0;
                var fourStar = 0;
                var fiveStar = 0;
                var modelReview = resp.data['modelReviews'];
                $.each(modelReview.data, function () {
                    if (this.point == 1) {
                        oneStar += 1;
                    }
                    if (this.point == 2) {
                        twoStar += 1;
                    }
                    if (this.point == 3) {
                        threeStar += 1;
                    }
                    if (this.point == 4) {
                        fourStar += 1;
                    }
                    if (this.point == 5) {
                        fiveStar += 1;
                    }
                });
                $('.fiveStar').text(fiveStar);
                $('.fourStar').text(fourStar);
                $('.threeStar').text(threeStar);
                $('.twoStar').text(twoStar);
                $('.oneStar').text(oneStar);

                var percentFive = fiveStar / modelReview.dataCount * 100;
                var percentFour = fourStar / modelReview.dataCount * 100;
                var percentThree = threeStar / modelReview.dataCount * 100;
                var percentTwo = twoStar / modelReview.dataCount * 100;
                var percentOne = oneStar / modelReview.dataCount * 100;

                $('.percentThreeStar').css('width', '' + percentThree + '%');
                $('.percentFiveStar').css('width', '' + percentFive + '%');
                $('.percentFourStar').css('width', '' + percentFour + '%');
                $('.percentTwoStar').css('width', '' + percentTwo + '%');
                $('.percentOneStar').css('width', '' + percentOne + '%');
            }
        }
    });
};

model.likeComment = function (commentId) {
    ajax({
        service: '/model/likecommnet.json',
        data: {commentId: commentId},
        contentType: 'json',
        loading: false,
        type: 'post',
        success: function (resp) {
            if (resp.success) {
                $('.vote-textup[for=' + resp.data.id + ']').html('<span class="icon-voteup"></span>(' + resp.data.like + ')');
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

model.loadOtherItem = function (page, tab) {
    var data = {};
    data.pageIndex = page;
    data.pageSize = 4;
    if (tab == 'sameprice') {
        $('li[for=samemanuf]').removeClass('active');
        $('li[for=sameprice]').addClass('active');
        data.categoryIds = [];
        data.categoryIds.push(model.model.categoryId);
        if (model.model.newMaxPrice > 0) {
            data.priceFrom = model.model.newMaxPrice * 1.1;
            data.priceFrom = model.model.newMaxPrice * 0.9;
        }
        if (model.model.oldMaxPrice > 0) {
            data.priceFrom = model.model.oldMaxPrice * 1.1;
            data.priceFrom = model.model.oldMaxPrice * 0.9;
        }
        if (model.model.oldMinPrice > 0) {
            data.priceFrom = model.model.oldMinPrice * 1.1;
            data.priceFrom = model.model.oldMinPrice * 0.9;
        }
        if (model.model.newMinPrice > 0) {
            data.priceFrom = model.model.newMinPrice * 1.1;
            data.priceFrom = model.model.newMinPrice * 0.9;
        }
    } else {
        $('li[for=sameprice]').removeClass('active');
        $('li[for=samemanuf]').addClass('active');
        data.manufacturerIds = [];
        data.manufacturerIds.push(model.model.manufacturerId);
    }
    ajax({
        service: '/item/getotheritem.json',
        data: data,
        contentType: 'json',
        loading: false,
        type: 'post',
        success: function (resp) {
            if (resp.success) {
                var data = resp.data['itemPage'];
                var shops = resp.data['shops'];
                if (data.data.length > 0) {
                    $('.product-other-list').html('');
                    for (var i = 0; i < data.data.length; i++) {
                        $('.product-other-list').append(template('/market/tpl/itemvip.tpl', {data: data.data[i], shops: shops, cities: cities, itemSearch: itemSearch}));
                    }

                    $('.product-other-list').append('<div class="clearfix"></div>');
                    $('.others').html('');
                    if (data.pageIndex > 0) {
                        $('.others').append('<a class="small-button pre" onclick="model.loadOtherItem(' + eval(data.pageIndex - 1) + ',\'' + tab + '\')"></a>');
                    }
                    if (data.pageIndex <= 0) {
                        $('.others').append('<a class="small-button first pre"></a>');
                    }
                    if (data.pageIndex < data.pageCount - 1) {
                        $('.others').append('<a class="small-button next" onclick="model.loadOtherItem(' + eval(data.pageIndex + 1) + ',\'' + tab + '\')"></a>');
                    }
                    if (data.pageIndex >= data.pageCount - 1) {
                        $('.others').append('<a class="small-button last next"></a>');
                    }
                }
            }
        }
    });
};
model.loadModelItem = function (page, order, type, condition, city) {
    var data = {};
    data.pageIndex = page;
    data.orderBy = order;
    if (type == 'BUYNOW' || type == 'AUCTION') {
        data.listingType = type;
    }
    if (condition == 'NEW' || condition == 'OLD') {
        data.condition = condition;
    }
    if (typeof city != 'undefined' && city.length > 0) {
        data.cityIds = [];
        data.cityIds.push(city);
    }
    data.modelIds = [];
    data.modelIds.push(model.model.id);
    ajax({
        service: '/item/getmodelitems.json',
        data: data,
        contentType: 'json',
        loading: false,
        type: 'post',
        success: function (resp) {
            if (resp.success) {
                var itemPage = resp.data['itemPage'];
                var shops = resp.data['shops'];
                var sellers = resp.data['sellers'];
                var itemSearch = resp.data['itemSearch'];
                $('.model_item').html('');
                $('.model_item').append(template('/market/tpl/modelitem.tpl', {itemPage: itemPage, shops: shops, cities: cities, sellers: sellers, itemSearch: itemSearch}));

                $('.model_item_page').html('');
                if (itemPage.pageIndex > 0) {
                    $('.model_item_page').append('<a class="small-button pre" onclick="model.loadModelItem(' + eval(itemPage.pageIndex - 1) + ',' + itemSearch.orderBy + ',\'' + itemSearch.listingType + '\',\'' + itemSearch.condition + '\',' + itemSearch.cities + ')"></a>');
                }
                if (itemPage.pageIndex < itemPage.pageCount - 1) {
                    $('.model_item_page').append('<a class="small-button next" onclick="model.loadModelItem(' + eval(itemPage.pageIndex + 1) + ',' + itemSearch.orderBy + ',\'' + itemSearch.listingType + '\',\'' + itemSearch.condition + '\',' + itemSearch.cities + ')"></a>');
                }
            }
        }
    });
};

model.changeCondition = function (el, order, type, city) {
    var condition = $(el).val();

    if (condition == 'NEW' || condition == 'OLD') {
        model.loadModelItem(0, order, type, condition, city);
    } else {
        model.loadModelItem(0, order, type, '', city);
    }
};
model.changeCity = function (el, order, type, condition) {
    var cityId = $(el).val();
    if (typeof cityId !== 'undefined' && cityId != '') {
        model.loadModelItem(0, order, type, condition, cityId);
    } else {
        model.loadModelItem(0, order, type, condition, []);
    }
};

model.getMaps = function (address) {
    address = address + ",Việt Nam";
    var geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(20.992114, 105.86245);
    var mapOptions = {
        zoom: 18,
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
            popup.msg("Địa chỉ này chưa được đánh dấu trên bản đồ");
        }
    });
};

model.compare = function () {
    var ids = new Array();
    var categoryId = '';
    $('input[for=selectModel]:checked').each(function (i, el) {
        if (ids.length < 3) {
            if (categoryId === '')
                categoryId = $(el).attr("c");
            ids.push($(el).attr("value"));
        }
    });
    modelCompare.compare(categoryId, ids);
};

model.pageSellerNoShop = function (sellerId) {
    var itemSearch = {};
    itemSearch.manufacturerIds = [];
    itemSearch.modelIds = [];
    itemSearch.cityIds = [];
    itemSearch.properties = [];
    itemSearch.sellerId = sellerId;
    location.href = baseUrl + urlUtils.browseUrl(itemSearch, '', '[]');
};