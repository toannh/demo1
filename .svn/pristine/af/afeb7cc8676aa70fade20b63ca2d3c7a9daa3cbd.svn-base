
browse = {};
var vipRuntime = null;
browse.init = function (params) {
    if (params.filter == 0 && typeof params.itemSearch.keyword != 'undefined' && params.itemSearch.keyword != '') {
        location.href = baseUrl + "/s/" + params.itemSearch.keyword.replace(/\s+/g, "+").trim() + '.html' + ((params.itemSearch.pageIndex == 'undefined' || params.itemSearch.pageIndex <= 1) ? '' : "?page=" + params.itemSearch.pageIndex)
    }
    browse.catName = params.category.name;
    browse.catId = params.category.id;
    browse.itemSearch = params.itemSearch;
    browse.categoryHistogram(params.category.leaf ? params.category.parentId : params.category.id, true);
    browse.loadModelCount();
    browse.loadHistogram('manufacturer', browse.loadManufacturerHistogram);
    browse.loadHistogram('model', browse.loadModelHistogram);
    browse.loadHistogram('city', browse.loadCityHistogram);
    browse.loadHistogram('item', browse.loadItemHistogram);
    browse.loadHistogram('property', browse.loadPropertyHistogram);
    $('li[for=order_' + browse.itemSearch.orderBy + ']').addClass("active");

    if (browse.itemSearch.cod) {
        $('.mbox-title li').removeClass("active");
        $('.mbox-title li[for=codCount]').addClass("active");
        return;
    }
    if (browse.itemSearch.promotion) {
        $('.box-title li').removeClass("active");
        $('.mbox-title li[for=discountCount]').addClass("active");
        return;
    }

    $("li.sub-menu").mouseenter(function () {
        browse.loadSubmenu(this, 'item');
    });
    browse.runVips();
    $('.browser-vip').mouseenter(function () {
        clearInterval(vipRuntime);
    });
    $('.browser-vip').mouseleave(function () {
        browse.runVips();
    });
    var desc = $('.panel-intro').text();
    var link = "";
    if (typeof desc !== 'undefined' && desc.trim() !== "") {
        var key = desc.split(",");
        $.each(key, function () {
            if (this.trim() !== "") {
                var text = textUtils.removeSpecialCharacter(this.trim());
                link += "<a href='" + baseUrl + "/s/" + textUtils.createKeyword(text) + ".html'>" + text + "</a>, ";
            }
        });
    }
    $('.panel-intro').html(link);
};


browse.runVips = function () {
    if (typeof vipPageCount !== 'undefined' && vipPageCount > 1) {
        var vipPage = 1;
        vipRuntime = setInterval(function () {
            browse.loadVips(vipPage);
            if (vipPage >= vipPageCount - 1) {
                vipPage = 0;
            } else {
                vipPage++;
            }
        }, 5000);
    }
};
browse.loadSubmenu = function (el, opt) {
    var cateId = $(el).attr("for");
    $.ajax({
        url: baseUrl + '/category/getchilds.json',
        data: {id: cateId},
        success: function (resp) {
            if (resp.success) {
                $(el).find("ul[child='" + cateId + "']").remove();
                $(el).append("<ul child='" + cateId + "'><ul>");

                $.each(resp.data, function () {
                    var cssclass = '';
                    if (!this.leaf) {
                        cssclass = 'class = "sub-menu"';
                    }
                    if (opt === 'item') {
                        $('li.sub-menu ul[child="' + cateId + '"]').append('<li for="' + this.id + '" ' + cssclass + '>\
                        <a href="' + baseUrl + urlUtils.browseUrl(browse.itemSearch, this.name, [{key: "cid", op: "mk", val: this.id}]) + '">' + this.name + '<span></span></a>\
                        </li>');
                    } else if (opt === 'model') {
                        $('li.sub-menu ul[child="' + cateId + '"]').append('<li for="' + this.id + '" ' + cssclass + '>\
                        <a href="' + baseUrl + urlUtils.modelBrowseUrl(browse.modelSearch, this.name, [{key: "cid", val: this.id}]) + '">' + this.name + '<span></span></a>\
                        </li>');
                    }
                });
                if (opt === 'item') {
                    browse.categoryHistogram(cateId);
                } else if (opt === 'model') {
                    browse.categoryModelHistogram(cateId);
                }

                $("li.sub-menu li.sub-menu").mouseenter(function () {
                    browse.loadSubmenu(this, opt);
                });
            }
        },
        dataType: 'JSON',
        type: 'GET'
    });
};
browse.getUrlVars = function ()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
};

browse.loadHistogram = function (type, callback) {
    var cid = null;
    if (browse.itemSearch.categoryIds != null && browse.itemSearch.categoryIds.length > 0) {
        cid = browse.itemSearch.categoryIds[0];
    }
    var data = {
        cid: cid,
        keyword: browse.itemSearch.keyword,
        sellerId: browse.itemSearch.sellerId,
        manufacturers: JSON.stringify(browse.itemSearch.manufacturerIds),
        models: JSON.stringify(browse.itemSearch.modelIds),
        cities: JSON.stringify(browse.itemSearch.cityIds),
        properties: JSON.stringify(browse.itemSearch.properties),
        condition: browse.itemSearch.condition,
        type: browse.itemSearch.listingType,
        pricefrom: browse.itemSearch.priceFrom,
        priceto: browse.itemSearch.priceTo,
        promotionId: browse.itemSearch.promotionId
    };
    if (browse.itemSearch.freeShip) {
        data.freeship = "";
    }
    if (browse.itemSearch.cod) {
        data.cod = "";
    }
    if (browse.itemSearch.onlinePayment) {
        data.onlinepayment = "";
    }
    if (browse.itemSearch.promotion) {
        data.promotion = "";
    }
    $.ajax({
        url: baseUrl + '/histogram/' + type + '.json',
        data: data,
        success: function (resp) {
            callback(resp);
        },
        dataType: 'JSON',
        type: 'GET'
    });
};

browse.categoryHistogram = function (cateId, ignoreSearch) {
    var data = {
        cid: cateId,
        keyword: browse.itemSearch.keyword,
        sellerId: browse.itemSearch.sellerId,
        manufacturers: JSON.stringify(browse.itemSearch.manufacturerIds),
        models: JSON.stringify(browse.itemSearch.modelIds),
        cities: JSON.stringify(browse.itemSearch.cityIds),
        properties: JSON.stringify(browse.itemSearch.properties),
        condition: browse.itemSearch.condition,
        type: browse.itemSearch.listingType,
        pricefrom: browse.itemSearch.priceFrom,
        priceto: browse.itemSearch.priceTo,
        ignoreSearch: ignoreSearch
    };
    if (browse.itemSearch.freeShip) {
        data.freeship = "";
    }
    if (browse.itemSearch.cod) {
        data.cod = "";
    }
    if (browse.itemSearch.onlinePayment) {
        data.onlinepayment = "";
    }
    if (browse.itemSearch.promotion) {
        data.promotion = "";
    }
    $.ajax({
        url: baseUrl + '/histogram/category.json',
        data: data,
        dataType: 'JSON',
        type: 'GET',
        success: function (resp) {
            if (resp.success && resp.data.length > 0) {
                for (var i = 0; i < resp.data.length; i++) {
                    if (resp.data[i].count > 0) {
                        $('.menuleft li[for=' + resp.data[i].id + '] a span').html(resp.data[i].count);
                    } else {
                        $('.menuleft li[for=' + resp.data[i].id + ']').remove();
                    }
                }
            }
        }
    });
};
browse.loadModelCount = function () {
    var cid = null;
    if (browse.itemSearch.categoryIds != null && browse.itemSearch.categoryIds.length > 0) {
        cid = browse.itemSearch.categoryIds[0];
    }
    var data = {
        cid: cid,
        keyword: browse.itemSearch.keyword,
        sellerId: browse.itemSearch.sellerId,
        manufacturers: JSON.stringify(browse.itemSearch.manufacturerIds),
        properties: JSON.stringify(browse.itemSearch.properties),
        status: browse.itemSearch.status
    };

    $.ajax({
        url: baseUrl + '/histogram/modelcount.json',
        data: data,
        dataType: 'JSON',
        type: 'GET',
        success: function (resp) {
            if (resp.success) {
                if (resp.data > 0) {
                    $('.spanModelCount').html(' (' + parseFloat(resp.data).toMoney(0, ',', '.') + ')');
                } else {
                    $('.spanModelCount').parents('li').remove();
                }
            }
        }
    });
};
browse.loadManufacturerHistogram = function (resp) {
    if (resp.success) {
        browse.manufacturerHistogram = browse.cleanArray(resp.data);
    } else {
        browse.manufacturerHistogram = [];
    }
    browse.showManufaturerHistogram();
};

browse.loadModelHistogram = function (resp) {
    if (resp.success) {
        browse.modelHistogram = browse.cleanArray(resp.data);
        var index = [];
        $.each(browse.modelHistogram, function () {
            if (this.name == null || this.name == '' || this.name == 'null') {
                index.push(browse.modelHistogram.indexOf(this));
            }
        });
        if (index.length > 0) {
            $.each(index, function () {
                browse.modelHistogram.splice(this, 1);
            });
        }
    } else {
        browse.modelHistogram = [];
    }
    browse.showModelHistogram();
};

browse.loadCityHistogram = function (resp) {
    if (resp.success) {
        browse.cityHistogram = browse.cleanArray(resp.data);
    } else {
        browse.cityHistogram = [];
    }
    browse.showCityHistogram();
};

browse.loadItemHistogram = function (resp) {
    if (resp.success) {
        browse.itemHistogram = resp.data;
    } else {
        browse.itemHistogram = [];
    }
    browse.showItemHistogram();
};

browse.loadPropertyHistogram = function (resp) {
    if (resp.success) {
        browse.propertyHistogram = resp.data;
    } else {
        browse.propertyHistogram = [];
    }
    browse.showPropertyHistogram();
};

browse.showManufaturerHistogram = function () {
    if (browse.manufacturerHistogram.length > 0) {

        $('.pbox[for=manufacturer]').html('<div class="pbox-title"><label class="lb-name">Thương hiệu</label></div>\
                    <div class="pbox-content">\
                        <div class="box-check panel-scroll">\
                        </div><!--box-check-->\
                    </div><!--pbox-content-->\
                <div class="pbox-more"><a data-toggle="modal"  onclick="browse.showPopup(\'manufacturer\')" data-target="#ModalFilter"></a></div>');
        var data = browse.manufacturerHistogram;
        var count = data.length;
        if (data.length > 10) {
            count = 10;
            $('.pbox[for=manufacturer] .pbox-more a').text('Còn ' + (data.length - 10) + ' thương hiệu khác...');
        } else {
            $('.pbox[for=manufacturer] .pbox-more a').text('Có ' + data.length + ' thương hiệu.');
        }

        for (var i = 0; i < count; i++) {
            $('.pbox[for=manufacturer] .panel-scroll').append('<div class="row"><input for="' + data[i].id + '" type="checkbox" id="checkman-' + data[i].id + '"/><label for="checkman-' + data[i].id + '">' + '<span>(' + data[i].count + ')</span><b>' + data[i].name + '</b></label></div>');
        }
        for (var i = 0; i < data.length; i++) {
            $('.tree-view div[for=manufacturer][val=' + data[i].id + ']').html('<span class="filter-lb">' + data[i].name + '</span><span class="filter-remove"></span>');
            $('.tree-view div[for=manufacturer][val=' + data[i].id + ']').show();
            $('.filter-choose div[for=manufacturer][val=' + data[i].id + ']').html('<span class="filter-lb">' + data[i].name + '</span><span class="filter-remove"></span>');
            $('.filter-choose div[for=manufacturer][val=' + data[i].id + ']').show();
            $('#ModalFilter .filter-right div[for=manufacturer]').append('<div class="row"><input for="' + data[i].id + '" type="checkbox" id="checkmanu_' + data[i].id + '" /><label for="checkmanu_' + data[i].id + '"><span>(' + data[i].count + ')</span><b>' + data[i].name + '</b></label></div>');
        }
        for (var i = 0; i < browse.itemSearch.manufacturerIds.length; i++) {
            $('.pbox[for=manufacturer] input[for=' + browse.itemSearch.manufacturerIds[i] + ']').attr("checked", "checked");
            $('#ModalFilter .filter-right div[for=manufacturer] input[for=' + browse.itemSearch.manufacturerIds[i] + ']').attr("checked", "checked");
        }
        $('#ModalFilter .filter-left li').click(function () {
            browse.showPopup($(this).attr('for'));
        });
        $('.pbox[for=manufacturer] input[for], #ModalFilter .filter-right div[for=manufacturer] input[for]').change(function () {
            if ($(this).is(":checked")) {
                document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "mk", key: "manufacturers", val: $(this).attr('for')}]);
            } else {
                document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: "manufacturers", val: $(this).attr('for')}]);
            }
        });
        $('.tree-view div[for=manufacturer] span.filter-remove').click(function () {
            document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: "manufacturers", val: $(this).parent().attr('val')}]);
        });
        $('.filter-choose div[for=manufacturer] span.filter-remove').click(function () {
            document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: "manufacturers", val: $(this).parent().attr('val')}]);
        });
    } else {
        $('#ModalFilter .filter-left li[for=manufacturer]').remove();
        $('#ModalFilter .filter-right div[for=manufacturer]').remove();
    }
};

browse.showModelHistogram = function () {
    if (browse.modelHistogram.length > 0) {
        $('.pbox[for=model]').html('<div class="pbox-title"><label class="lb-name">Models</label></div>\
                    <div class="pbox-content">\
                        <div class="box-check panel-scroll">\
                        </div><!--box-check-->\
                    </div><!--pbox-content-->\
                <div class="pbox-more"><a data-toggle="modal" data-target="#ModalFilter" onclick="browse.showPopup(\'model\')"></a></div>');
        var data = browse.modelHistogram;
        var count = data.length;
        if (data.length > 10) {
            count = 10;
            $('.pbox[for=model] .pbox-more a').text('Còn ' + (data.length - 10) + ' model khác...');
        } else {
            $('.pbox[for=model] .pbox-more a').text('Có ' + data.length + ' model.');
        }
        for (var i = 0; i < count; i++) {
            $('.pbox[for=model] .panel-scroll').append('<div class="row"><input for="' + data[i].id + '" type="checkbox" id="checkmo-' + data[i].id + '"/><label for="checkmo-' + data[i].id + '"><span>(' + data[i].count + ')</span><b>' + data[i].name + '</b></label></div>');
        }
        $('.modelCount').append(' (' + data[0].total + ')');
        $('#ModalFilter .filter-left li').click(function () {
            browse.showPopup($(this).attr('for'));
        });
        for (var i = 0; i < data.length; i++) {
            $('.tree-view div[for=model][val=' + data[i].id + ']').html('<span class="filter-lb">' + data[i].name + '</span><span class="filter-remove"></span>');
            $('.tree-view div[for=model][val=' + data[i].id + ']').show();
            $('.filter-choose div[for=model][val=' + data[i].id + ']').html('<span class="filter-lb">' + data[i].name + '</span><span class="filter-remove"></span>');
            $('.filter-choose div[for=model][val=' + data[i].id + ']').show();
            $('#ModalFilter .filter-right div[for=model]').append('<div class="row"><input for="' + data[i].id + '" type="checkbox" id="checkmod_' + data[i].id + '" /><label for="checkmod_' + data[i].id + '"><span>(' + data[i].count + ')</span><b>' + data[i].name + '</b></label></div>');
        }
        for (var i = 0; i < browse.itemSearch.modelIds.length; i++) {
            $('.pbox[for=model] input[for=' + browse.itemSearch.modelIds[i] + ']').attr("checked", "checked");
            $('#ModalFilter .filter-right div[for=model] input[for=' + browse.itemSearch.modelIds[i] + ']').attr("checked", "checked");
        }
        $('.pbox[for=model] input[for], #ModalFilter .filter-right div[for=model] input[for]').change(function () {
            if ($(this).is(":checked")) {
                document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "mk", key: "models", val: $(this).attr('for')}]);
            } else {
                document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: "models", val: $(this).attr('for')}]);
            }
        });
        $('.tree-view div[for=model] span.filter-remove').click(function () {
            document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: "models", val: $(this).parent().attr('val')}]);
        });
        $('.filter-choose div[for=model] span.filter-remove').click(function () {
            document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: "models", val: $(this).parent().attr('val')}]);
        });
    } else {
        $('#ModalFilter .filter-left li[for=model]').remove();
        $('#ModalFilter .filter-right div[for=model]').remove();
    }
};

browse.showCityHistogram = function () {
    if (browse.cityHistogram.length > 0) {
        $('.pbox[for=city]').html('<div class="pbox-title"><label class="lb-name">Nơi bán</label></div>\
                    <div class="pbox-content">\
                        <div class="box-check panel-scroll">\
                        </div><!--box-check-->\
                    </div><!--pbox-content-->\
                <div class="pbox-more"><a data-toggle="modal" data-target="#ModalFilter" onclick="browse.showPopup(\'city\')"></a></div>');
        var data = browse.cityHistogram;
        var count = data.length;
        if (data.length > 10) {
            count = 10;
            $('.pbox[for=city] .pbox-more a').text('Còn ' + (data.length - 10) + ' nơi bán khác...');
        } else {
            $('.pbox[for=city] .pbox-more a').text('Có ' + data.length + ' nơi bán.');
        }
        for (var i = 0; i < count; i++) {
            $('.tree-view div[for=city][val=' + data[i].id + ']').html('<span class="filter-lb">' + data[i].name + '</span><span class="filter-remove"></span>');
            $('.tree-view div[for=city][val=' + data[i].id + ']').show();
            $('.filter-choose div[for=city][val=' + data[i].id + ']').html('<span class="filter-lb">' + data[i].name + '</span><span class="filter-remove"></span>');
            $('.filter-choose div[for=city][val=' + data[i].id + ']').show();
            $('.pbox[for=city] .panel-scroll').append('<div class="row"><input for="' + data[i].id + '" type="checkbox" id="checkci-' + data[i].id + '"/><label for="checkci-' + data[i].id + '"><span>(' + data[i].count + ')</span><b>' + data[i].name + '</b></label></div>');
        }
        for (var i = 0; i < data.length; i++) {
            $('#ModalFilter .filter-right div[for=city]').append('<div class="row"><input for="' + data[i].id + '" type="checkbox" id="checkcit_' + data[i].id + '" /><label for="checkcit_' + data[i].id + '"><span>(' + data[i].count + ')</span><b>' + data[i].name + '</b></label></div>');
        }

        $('#ModalFilter .filter-left li').click(function () {
            browse.showPopup($(this).attr('for'));
        });
        for (var i = 0; i < browse.itemSearch.cityIds.length; i++) {
            $('.pbox[for=city] input[for=' + browse.itemSearch.cityIds[i] + ']').attr("checked", "checked");
            $('#ModalFilter .filter-right div[for=city] input[for=' + browse.itemSearch.cityIds[i] + ']').attr("checked", "checked");
        }
        $('.pbox[for=city] input[for], #ModalFilter .filter-right div[for=city] input[for]').change(function () {
            if ($(this).is(":checked")) {
                document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "mk", key: "cities", val: $(this).attr('for')}]);
            } else {
                document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: "cities", val: $(this).attr('for')}]);
            }
        });
        $('.tree-view div[for=city] span.filter-remove').click(function () {
            document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: "cities", val: $(this).parent().attr('val')}]);
        });
        $('.filter-choose div[for=city] span.filter-remove').click(function () {
            document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: "cities", val: $(this).parent().attr('val')}]);
        });
    } else {
        $('#ModalFilter .filter-left li[for=city]').remove();
        $('#ModalFilter .filter-right div[for=city]').remove();
    }
};

browse.showItemHistogram = function () {
    if (browse.itemHistogram.length > 0) {
        var data = browse.itemHistogram;
        for (var i = 0; i < data.length; i++) {
            if (data[i].count > 0) {
                $('.pbox .row[for=' + data[i].type + '] span').text('(' + data[i].count + ')');
            } else {
                $('.pbox .row[for=' + data[i].type + ']').remove();
            }
            if (data[i].type === 'listingtypeBuynow') {
                if (data[i].count > 0) {
                    $('.pbox .row[for=listingType][val="BUYNOW"] span').text('(' + data[i].count + ')');
                } else {
                    $('.pbox .row[for=listingType][val="BUYNOW"]').remove();
                }
                if (data[i].total - data[i].count > 0) {
                    $('.pbox .row[for=listingType][val="AUCTION"] span').text('(' + (data[i].total - data[i].count) + ')');
                } else {
                    $('.pbox .row[for=listingType][val="AUCTION"]').remove();
                }
            }
            if (data[i].type === 'conditionNew') {
                if (data[i].count > 0) {
                    $('.pbox .row[for=condition][val="NEW"] span').text('(' + data[i].count + ')');
                } else {
                    $('.pbox .row[for=condition][val="NEW"]').remove();
                }
                if (data[i].total - data[i].count > 0) {
                    $('.pbox .row[for=condition][val="OLD"] span').text('(' + (data[i].total - data[i].count) + ')');
                } else {
                    $('.pbox .row[for=condition][val="OLD"]').remove();
                }
            }
            if (data[i].type === 'promotion') {
                if (data[i].count > 0) {
                    $('li[for=discountCount] a span').text('(' + parseFloat((data[i].count)).toMoney(0, ',', '.') + ')');
                } else {
                    $('li[for=discountCount]').remove();
                }
            }
            if (data[i].type === 'cod') {
                if (data[i].count > 0) {
                    $('li[for=codCount] a span').text('(' + parseFloat((data[i].count)).toMoney(0, ',', '.') + ')');
                } else {
                    $('li[for=codCount]').remove();
                    $('div[for=codCount]').remove();
                }
            }
        }
        $('.pbox .row[for]').each(function () {
            var key = $(this).attr('for');
            var val = $(this).attr('val');
            var param = $(this).attr('param');
            if (val) {
                $('.pbox .row[for=' + key + '][val=' + browse.itemSearch[key] + '] input').attr("checked", "checked");
                $(this).find('input').change(function () {
                    if ($(this).is(":checked")) {
                        document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "mk", key: param, val: val}]);
                    } else {
                        document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "mk", key: param, val: 0}]);
                    }
                });
            } else {
                if (browse.itemSearch[key]) {
                    $('.pbox .row[for=' + key + '] input').attr("checked", "checked");
                }
                $(this).find('input').change(function () {
                    if ($(this).is(":checked")) {
                        document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "mk", key: param}]);
                    } else {
                        document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: param}]);
                    }
                });
            }
        });
    }
};

browse.showPropertyHistogram = function () {
    if (browse.propertyHistogram.length > 0) {
        var data = browse.propertyHistogram;
        for (var i = 0; i < data.length; i++) {
            if (data[i].count > 0) {
                $('.pbox[for=property]').append('<div class="pbox-title"><label class="lb-name">' + data[i].name + '</label>\
                    <a class="lb-more" href="#" data-toggle="modal" data-target="#ModalFilter" onclick="browse.showPopup(\'' + textUtils.createAlias(data[i].name) + '\')">Xem thêm</a></div>\
                    <div class="pbox-content">\
                        <div class="box-check panel-scroll">\
                        </div><!--box-check-->\
                    </div><!--pbox-content-->');
                $('#ModalFilter .filter-left ul').append('<li for="' + textUtils.createAlias(data[i].name) + '"><a>' + data[i].name + '</a></li>');
                $('#ModalFilter .filter-right').append('<div for="' + textUtils.createAlias(data[i].name) + '" name="' + data[i].name + '" class="box-check"></div>');
                for (var j = 0; j < data[i].values.length; j++) {
                    var value = data[i].values[j];
                    var operator = data[i].operator;
                    if (value.count > 0) {
                        var op = '';
                        if (value.operator === 'LTE') {
                            op = '≤ ';
                        }
                        if (value.operator === 'GTE') {
                            op = '≥ ';
                        }
                        $('#ModalFilter .filter-right div[for=' + textUtils.createAlias(data[i].name) + ']').append('<div class="row"><input name="' + data[i].name + '" operator="' + operator + '" value="' + value.name + '" rangevalue="' + value.value + '" type="checkbox" id="checkpro_' + textUtils.createAlias(data[i].name + value.value) + data[i].operator + '" /><label for="checkpro_' + textUtils.createAlias(data[i].name + value.name) + value.value + '"><span>(' + value.count + ')</span><b>' + op + value.name + '</b></label></div>');
                    }
                }
            }
        }
        for (var i = 0; i < browse.itemSearch.properties.length; i++) {
            var property = browse.itemSearch.properties[i];
            $('#ModalFilter .filter-right #checkpro_' + textUtils.createAlias(property.name + property.value) + property.operator).attr('checked', 'checked');
        }
        $('#ModalFilter .filter-right input').change(function () {
            var pro = {};
            pro.name = $(this).attr('name');
            pro.value = $(this).attr('rangevalue');
            pro.operator = $(this).attr('operator');
            if ($(this).is(":checked")) {
                document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "mk", key: 'properties', val: JSON.stringify(pro)}]);
            } else {
                document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: 'properties', val: JSON.stringify(pro)}]);
            }
        });
        $('#ModalFilter .filter-left li').click(function () {
            browse.showPopup($(this).attr('for'));
        });
        $('.tree-view div[for=property] span.filter-remove').click(function () {
            var pro = {};
            pro.name = $(this).parent().attr('name');
            pro.value = $(this).parent().attr('value');
            pro.operator = $(this).parent().attr('operator');
            document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: 'properties', val: JSON.stringify(pro)}]);
        });
        $('.filter-choose div[for=property] span.filter-remove').click(function () {
            var pro = {};
            pro.name = $(this).parent().attr('name');
            pro.value = $(this).parent().attr('value');
            pro.operator = $(this).parent().attr('operator');
            document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "rm", key: 'properties', val: JSON.stringify(pro)}]);
        });
    }
};


browse.findByPrice = function () {
    var priceFrom = parseInt($('input[for=priceFrom]').val());
    var priceTo = parseInt($('input[for=priceTo]').val());
    document.location = baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{key: 'pricefrom', val: priceFrom}, {key: 'priceto', val: priceTo}]);
};
browse.cleanArray = function (source) {
    var data = [];
    var j = 0;
    for (var i = 0; i < source.length; i++) {
        if (source[i].count > 0) {
            data[j] = source[i];
            j++;
        }
    }
    return data;
};
browse.loadManufactuterByPage = function (page) {
    $.ajax({
        url: baseUrl + '/manufacturer/search.json',
        data: {categoryId: browse.itemSearch.categoryId, page: page},
        dataType: 'JSON',
        type: 'GET',
        success: function (resp) {
            if (resp.success && resp.data.data.length > 0) {
                $('.boxlogo-content').html('');
                for (var i = 0; i < resp.data.data.length; i++) {

                    if (resp.data.data[i].imageUrl !== null && typeof resp.data.data[i].imageUrl !== 'undefined' && resp.data.data[i].imageUrl !== '') {
                        $('.boxlogo-content').append('<div class="logoitem"><a href="' + baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "mk", key: "manufacturers", val: resp.data.data[i].id}]) + '"><img src="' + resp.data.data[i].imageUrl + '" alt="' + resp.data.data[i].name + '" /></a></div>');
                    } else {
                        $('.boxlogo-content').append('<div class="logoitem"><a href="' + baseUrl + urlUtils.browseUrl(browse.itemSearch, browse.catName, [{op: "mk", key: "manufacturers", val: resp.data.data[i].id}]) + '">' + resp.data.data[i].name + '</a></div>');
                    }
                }
                $('.small-page[for=manuf]').html('');
                if (resp.data.pageIndex > 0) {
                    $('.small-page[for=manuf]').append('<a class="small-button pre" onclick="browse.loadManufactuterByPage(' + eval(resp.data.pageIndex - 1) + ')"></a>');
                }
                if (resp.data.pageIndex < resp.data.pageCount - 1) {
                    $('.small-page[for=manuf]').append('<a class="small-button next" onclick="browse.loadManufactuterByPage(' + eval(resp.data.pageIndex + 1) + ')"></a>');
                }
            }
        }
    });
};
browse.loadVips = function (page) {
    $.ajax({
        url: baseUrl + '/vipitem/search.json',
        data: {categoryId: browse.catId, page: page},
        dataType: 'JSON',
        type: 'GET',
        success: function (resp) {
            if (resp.success) {
                var data = resp.data['items'];
                var shops = resp.data['shops'];
                var vipPage = resp.data['vipPage'];
                $('.browser-vip').html('');
                for (var i = 0; i < data.length; i++) {
                    $('.browser-vip').append(template('/market/tpl/itemvip.tpl', {data: data[i], shops: shops, cities: cities, itemSearch: browse.itemSearch}));
                }
                if (data.length < 4) {
                    $('.browser-vip').append('<div class="product-item item-vip">\
                                            <div class="big-shadow">\
                                                <div class="item-img"><a href="' + baseUrl + '/user/vipitem.html"><img src="' + staticUrl + '/market/images/item-vip.jpg" alt="item vip" /></a></div>\
                                                <div class="item-text">\
                                                    <div class="item-row">\
                                                        <span class="item-price">30.000 Xèng</span>\
                                                    </div>\
                                                    <div class="item-row">\
                                                        (Giá trị khoảng: <b>30.000 đ</b>)\
                                                    </div>\
                                                    <div class="item-row bg-item">\
                                                        <a class="iv-buy" href="' + baseUrl + '/user/vipitem.html">Mua ngay</a>\
                                                    </div>\
                                                </div>\
                                            </div>\
                      </div>');
                }
                $('.browser-vip').append('<div class="clearfix"></div>');
                $('.bv-control ul').html('');
                if (vipPage.pageIndex > 0) {
                    $('.bv-control ul').append('<li class="pre"><a onclick="browse.loadVips(' + eval(vipPage.pageIndex - 1) + ');">&lt;</a></li>');
                }
                if (vipPage.pageIndex > 1) {
                    $('.bv-control ul').append('<li><a onclick="browse.loadVips(' + eval(vipPage.pageIndex - 2) + ');">' + eval(vipPage.pageIndex - 2) + '</a></li>');
                }
                if (vipPage.pageIndex > 0) {
                    $('.bv-control ul').append('<li><a onclick="browse.loadVips(' + eval(vipPage.pageIndex - 1) + ');">' + eval(vipPage.pageIndex - 1) + '</a></li>');
                }
                $('.bv-control ul').append('<li class="active"><a>' + eval(vipPage.pageIndex) + '</a></li>');

                if (vipPage.pageCount - vipPage.pageIndex > 1) {
                    $('.bv-control ul').append('<li><a onclick="browse.loadVips(' + eval(vipPage.pageIndex + 1) + ')">' + eval(vipPage.pageIndex + 1) + '</a></li>');
                }
                if (vipPage.pageCount - vipPage.pageIndex > 2) {
                    $('.bv-control ul').append('<li><a onclick="browse.loadVips(' + eval(vipPage.pageIndex + 2) + ')">' + eval(vipPage.pageIndex + 2) + '</a></li>');
                }
                if (vipPage.pageCount - vipPage.pageIndex > 1) {
                    $('.bv-control ul').append('<li class="next"><a  onclick="browse.loadVips(' + eval(vipPage.pageIndex + 1) + ')">&gt;</a></li>');
                }
            }
        }
    });
};
browse.showPopup = function (property) {
    $('#ModalFilter .filter-right div[for]').hide();
    $('#ModalFilter .filter-right div[for=' + property + ']').show();
    $('#ModalFilter .filter-left li').removeClass('active');
    $('#ModalFilter .filter-left li[for=' + property + ']').addClass('active');
    $('#ModalFilter .filter-right .filter-title').text($('#ModalFilter .filter-right div[for=' + property + ']').attr('name'));
};



/***
 * 
 * 
 * browse model js function ....
 * 
 */


browse.initModel = function (params) {
    browse.catName = params.category.name;
    browse.catId = params.category.id;
    browse.modelSearch = params.modelSearch;
    browse.itemSearch = itemSearch;
    browse.loadModelItemCount();
    browse.categoryModelHistogram(params.category.leaf ? params.category.parentId : params.category.id, true);
    browse.loadModelBrowseHistogram('manufacturer', browse.loadModelManufacturerHistogram);
    browse.loadModelBrowseHistogram('property', browse.loadModelPropertyHistogram);
    $('li[for=order_' + browse.modelSearch.orderBy + ']').addClass("active");

    $("li.sub-menu").mouseenter(function () {
        browse.loadSubmenu(this, 'model');
    });
    browse.runVips();
    $('.browser-vip').mouseenter(function () {
        clearInterval(vipRuntime);
    });
    $('.browser-vip').mouseleave(function () {
        browse.runVips();
    });
};

browse.loadModelBrowseHistogram = function (type, callback) {
    var data = {
        cid: browse.modelSearch.categoryId,
        keyword: browse.modelSearch.keyword,
        manufacturers: JSON.stringify(browse.modelSearch.manufacturerIds),
        properties: JSON.stringify(browse.modelSearch.properties)
    };

    $.ajax({
        url: baseUrl + '/histogram/model/' + type + '.json',
        data: data,
        success: function (resp) {
            callback(resp);
        },
        dataType: 'JSON',
        type: 'GET'
    });
};

browse.categoryModelHistogram = function (cateId, ignoreSearch) {
    var data = {
        cid: cateId,
        keyword: browse.modelSearch.keyword,
        manufacturers: JSON.stringify(browse.modelSearch.manufacturerIds),
        properties: JSON.stringify(browse.modelSearch.properties),
        ignoreSearch: ignoreSearch
    };

    $.ajax({
        url: baseUrl + '/histogram/model/category.json',
        data: data,
        dataType: 'JSON',
        type: 'GET',
        success: function (resp) {
            if (resp.success && resp.data.length > 0) {
                for (var i = 0; i < resp.data.length; i++) {
                    if (resp.data[i].count > 0) {
                        $('.menuleft li[for=' + resp.data[i].id + '] a span').html(resp.data[i].count);
                    } else {
                        $('.menuleft li[for=' + resp.data[i].id + ']').remove();
                    }
                }
            }
        }
    });
};
browse.loadModelItemCount = function () {
    var data = {
        cid: browse.modelSearch.categoryId,
        keyword: browse.modelSearch.keyword,
        manufacturers: JSON.stringify(browse.modelSearch.manufacturerIds),
        properties: JSON.stringify(browse.modelSearch.properties),
        status: browse.modelSearch.status
    };

    $.ajax({
        url: baseUrl + '/histogram/model/item.json',
        data: data,
        dataType: 'JSON',
        type: 'GET',
        success: function (resp) {
            if (resp.success) {
                if (resp.data > 0) {
                    $('.itemCount').append(' (' + parseFloat(resp.data).toMoney(0, ',', '.') + ')');
                } else {
                    $('.itemCount').parents('li').remove();
                }
            }
        }
    });
};
browse.loadModelManufacturerHistogram = function (resp) {
    if (resp.success) {
        browse.modelManufacturerHistogram = browse.cleanArray(resp.data);
    } else {
        browse.modelManufacturerHistogram = [];
    }
    browse.showModelManufaturerHistogram();
};
browse.loadModelPropertyHistogram = function (resp) {
    if (resp.success) {
        browse.modelPropertyHistogram = resp.data;
    } else {
        browse.modelPropertyHistogram = [];
    }
    browse.showModelPropertyHistogram();
};

browse.showModelManufaturerHistogram = function () {
    if (browse.modelManufacturerHistogram.length > 0) {
        $('.pbox[for=manufacturer]').html('<div class="pbox-title"><label class="lb-name">Thương hiệu</label></div>\
                    <div class="pbox-content">\
                        <div class="box-check panel-scroll">\
                        </div><!--box-check-->\
                    </div><!--pbox-content-->\
                <div class="pbox-more"><a data-toggle="modal"  onclick="browse.showPopup(\'manufacturer\')" data-target="#ModalFilter"></a></div>');
        var data = browse.modelManufacturerHistogram;
        var count = data.length;
        if (data.length > 10) {
            count = 10;
            $('.pbox[for=manufacturer] .pbox-more a').text('Còn ' + (data.length - 10) + ' thương hiệu khác...');
        } else {
            $('.pbox[for=manufacturer] .pbox-more a').text('Có ' + data.length + ' thương hiệu.');
        }

        for (var i = 0; i < count; i++) {
            $('.pbox[for=manufacturer] .panel-scroll').append('<div class="row"><input for="' + data[i].id + '" type="checkbox" id="checkman-' + data[i].id + '"/><label for="checkman-' + data[i].id + '"><span>(' + data[i].count + ')</span><b>' + data[i].name + '</b></label></div>');
        }
        for (var i = 0; i < data.length; i++) {
            $('.tree-view div[for=manufacturer][val=' + data[i].id + ']').html('<span class="filter-lb">' + data[i].name + '</span><span class="filter-remove"></span>');
            $('.tree-view div[for=manufacturer][val=' + data[i].id + ']').show();
            $('.filter-choose div[for=manufacturer][val=' + data[i].id + ']').html('<span class="filter-lb">' + data[i].name + '</span><span class="filter-remove"></span>');
            $('.filter-choose div[for=manufacturer][val=' + data[i].id + ']').show();
            $('#ModalFilter .filter-right div[for=manufacturer]').append('<div class="row"><input for="' + data[i].id + '" type="checkbox" id="checkmanu_' + data[i].id + '" /><label for="checkmanu_' + data[i].id + '"><span>(' + data[i].count + ')</span><b>' + data[i].name + '</b></label></div>');
        }
        for (var i = 0; i < browse.modelSearch.manufacturerIds.length; i++) {
            $('.pbox[for=manufacturer] input[for=' + browse.modelSearch.manufacturerIds[i] + ']').attr("checked", "checked");
            $('#ModalFilter .filter-right div[for=manufacturer] input[for=' + browse.modelSearch.manufacturerIds[i] + ']').attr("checked", "checked");
        }
        $('#ModalFilter .filter-left li').click(function () {
            browse.showPopup($(this).attr('for'));
        });
        $('.pbox[for=manufacturer] input[for], #ModalFilter .filter-right div[for=manufacturer] input[for]').change(function () {
            if ($(this).is(":checked")) {
                document.location = baseUrl + urlUtils.modelBrowseUrl(browse.modelSearch, browse.catName, [{op: "mk", key: "manufacturers", val: $(this).attr('for')}]);
            } else {
                document.location = baseUrl + urlUtils.modelBrowseUrl(browse.modelSearch, browse.catName, [{op: "rm", key: "manufacturers", val: $(this).attr('for')}]);
            }
        });
        $('.tree-view div[for=manufacturer] span.filter-remove').click(function () {
            document.location = baseUrl + urlUtils.modelBrowseUrl(browse.modelSearch, browse.catName, [{op: "rm", key: "manufacturers", val: $(this).parent().attr('val')}]);
        });
        $('.filter-choose div[for=manufacturer] span.filter-remove').click(function () {
            document.location = baseUrl + urlUtils.modelBrowseUrl(browse.modelSearch, browse.catName, [{op: "rm", key: "manufacturers", val: $(this).parent().attr('val')}]);
        });
    } else {
        $('#ModalFilter .filter-left li[for=manufacturer]').remove();
        $('#ModalFilter .filter-right div[for=manufacturer]').remove();
    }
};

browse.showModelPropertyHistogram = function () {
    if (browse.modelPropertyHistogram.length > 0) {
        var data = browse.modelPropertyHistogram;
        for (var i = 0; i < data.length; i++) {
            if (data[i].count > 0) {
                $('.pbox[for=property]').append('<div class="pbox-title"><label class="lb-name">' + data[i].name + '</label>\
                    <a class="lb-more" href="#" data-toggle="modal" data-target="#ModalFilter" onclick="browse.showPopup(\'' + textUtils.createAlias(data[i].name) + '\')">Xem thêm</a></div>\
                    <div class="pbox-content">\
                        <div class="box-check panel-scroll">\
                        </div><!--box-check-->\
                    </div><!--pbox-content-->');
                $('#ModalFilter .filter-left ul').append('<li for="' + textUtils.createAlias(data[i].name) + '"><a>' + data[i].name + '</a></li>');
                $('#ModalFilter .filter-right').append('<div for="' + textUtils.createAlias(data[i].name) + '" name="' + data[i].name + '" class="box-check"></div>');
                for (var j = 0; j < data[i].values.length; j++) {
                    var value = data[i].values[j];
                    var operator = data[i].operator;
                    if (value.count > 0) {
                        var op = '';
                        if (value.operator === 'LTE') {
                            op = '≤ ';
                        }
                        if (value.operator === 'GTE') {
                            op = '≥ ';
                        }
                        $('#ModalFilter .filter-right div[for=' + textUtils.createAlias(data[i].name) + ']').append('<div class="row"><input name="' + data[i].name + '" operator="' + operator + '" value="' + value.name + '" rangevalue="' + value.value + '" type="checkbox" id="checkpro_' + textUtils.createAlias(data[i].name + value.value) + data[i].operator + '" /><label for="checkpro_' + textUtils.createAlias(data[i].name + value.name) + value.value + '"><span>(' + value.count + ')</span><b>' + op + value.name + '</b></label></div>');
                    }
                }
            }
        }
        for (var i = 0; i < browse.modelSearch.properties.length; i++) {
            var property = browse.modelSearch.properties[i];
            $('#ModalFilter .filter-right #checkpro_' + textUtils.createAlias(property.name + property.value) + property.operator).attr('checked', 'checked');
        }
        $('#ModalFilter .filter-right input').change(function () {
            var pro = {};
            pro.name = $(this).attr('name');
            pro.value = $(this).attr('rangevalue');
            pro.operator = $(this).attr('operator');
            if ($(this).is(":checked")) {
                document.location = baseUrl + urlUtils.modelBrowseUrl(browse.modelSearch, browse.catName, [{op: "mk", key: 'properties', val: JSON.stringify(pro)}]);
            } else {
                document.location = baseUrl + urlUtils.modelBrowseUrl(browse.modelSearch, browse.catName, [{op: "rm", key: 'properties', val: JSON.stringify(pro)}]);
            }
        });
        $('#ModalFilter .filter-left li').click(function () {
            browse.showPopup($(this).attr('for'));
        });
        $('.tree-view div[for=property] span.filter-remove').click(function () {
            var pro = {};
            pro.name = $(this).parent().attr('name');
            pro.value = $(this).parent().attr('value');
            pro.operator = $(this).parent().attr('operator');
            document.location = baseUrl + urlUtils.modelBrowseUrl(browse.modelSearch, browse.catName, [{op: "rm", key: 'properties', val: JSON.stringify(pro)}]);
        });
        $('.filter-choose div[for=property] span.filter-remove').click(function () {
            var pro = {};
            pro.name = $(this).parent().attr('name');
            pro.value = $(this).parent().attr('value');
            pro.operator = $(this).parent().attr('operator');
            document.location = baseUrl + urlUtils.modelBrowseUrl(browse.modelSearch, browse.catName, [{op: "rm", key: 'properties', val: JSON.stringify(pro)}]);
        });
    }
};

browse.initCategories = function () {
    var ids = [];

    $.each($(".cateLv2"), function () {
        var child = this;
        if ($(this).find("li").length == 0) {
            $(this).remove();
        }
        $(this).find("a.cb-more").click(function () {
            $(child).find("li").css({"display": "block"});
            $(this).css({"display": "none"});
        });
    });

    $.each($("div[for=child]"), function () {
        var cid = $(this).attr("cateId");
        if ($(this).find("li").length > 0) {
            if ($(this).find("li").length > 5) {
                $(this).find("a.cb-more").css({"display": "block"});
            }
            var child = this;
            $.each($(this).find("li"), function (index) {
                if (index >= 5) {
                    $(this).css({"display": "none"});
                } else {
                    $(this).css({"display": "block"});
                }
            });
            $(this).find("a.cb-more").click(function () {
                $(child).find("li").css({"display": "block"});
                $(this).css({"display": "none"});
            });
            ids.push(cid);
            $(this).attr("show", true);
        }
    });

    $.each($("div[for=cateRoot]"), function () {
        var id = $(this).attr("id");
        $.each($("div[child=" + id + "]"), function (index) {
            if ((index + 1) % 4 === 0) {
                $(this).after('<div class="category-line"></div>');
            }
        });
    });

    $(window).scroll(function () {
        var navHeight = $(".navigator").height();
        var navCat = $(".category-list-icon").height();
        if ($(this).scrollTop() >= (navHeight + navCat)) {
            $('.category-list-icon').addClass("category-fixed");
            $('body').css("padding-top", (navHeight + navCat));
        }
        if ($(this).scrollTop() < (navHeight + navCat)) {
            $('.category-list-icon').removeClass("category-fixed");
            $('body').css("padding-top", "0");
        }
    });
};

browse.followSeller = function (sellerId) {
    ajax({
        service: '/user/follow.json?id=' + sellerId,
        contentType: 'json',
        loading: false,
        done: function (resp) {
            if (resp.success) {
                $('.like-count').html(resp.data);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};