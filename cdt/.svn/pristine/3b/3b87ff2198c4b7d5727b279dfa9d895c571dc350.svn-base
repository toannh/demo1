item = {};

item.init = function (tab) {
    $("div.notification-block a").click(function () {
        $("div.notification-block").slideToggle("slow");
    });

    var urlParams = {};
    urlParams.tab = tab;
    ajax({
        service: '/item/countitem.json',
        data: urlParams,
        loading: false,
        done: function (resp) {
            if (resp.success && resp.data.length > 0) {
                var data = resp.data;
                for (var i = 0; i < data.length; i++) {
                    $('span[for=' + data[i].type + ']').text("(" + data[i].count + ")");
                }
            }
        }
    });
    $.each(categoryPath, function () {
        $("a[for=c_" + this.id + "]").html(this.name);
        var s = {
            categoryIds: [this.id],
        };
        $("a[for=c_" + this.id + "]").attr("href", urlUtils.browseUrl(s, this.name));
    });

    $("input[name=checkall]").change(function () {
        if ($(this).is(":checked")) {
            $("input[for=checkall]").attr("checked", true);
        } else {
            $("input[for=checkall]").attr("checked", false);
        }
    });
    textUtils.inputNumberFormat('numberType');
    item.textNoteApproved();
    item.upFree();
};

item.change = function (obj) {
    var h2 = $(obj).parent("p");
    var id = h2.attr("itemid");
    h2.css({"display": "none"});
    var colName = h2.attr("for") + '_' + id;
    $("[for=" + colName + "]").css({"display": "block"});
    $("[for=" + colName + "]").children("input").blur(function () {
        var quantity = typeof $("[for=quantity_" + id + "] input").val() !== 'undefined' ? $("[for=quantity_" + id + "] input").val().replace(/\./g, "") : 0;
        var weight = typeof $("[for=weight_" + id + "] input").val() !== 'undefined' ? $("[for=weight_" + id + "] input").val().replace(/\./g, "") : 0;
        var startPrice = typeof $("[for=startPrice_" + id + "] input").val() !== 'undefined' ? $("[for=startPrice_" + id + "] input").val().replace(/\./g, "") : 0;
        var sellPrice = typeof $("[for=sellPrice_" + id + "] input").val() !== 'undefined' ? $("[for=sellPrice_" + id + "] input").val().replace(/\./g, "") : 0;

        var data = {
            id: id,
            fieldUpdate: h2.attr("for"),
            name: $("[for=name_" + id + "] input").val(),
            quantity: quantity,
            weight: weight,
            startPrice: startPrice,
            sellPrice: sellPrice
        };

        ajax({
            service: '/item/quickedit.json',
            data: data,
            loading: false,
            type: 'post',
            contentType: 'json',
            done: function (resp) {
                if (resp.success) {
                    $("p[itemid=" + data.id + "][for=name]").html('<a>' + data.name + ' </a><a class="btn-quick-edit" onclick="item.change(this)" ><span class="glyphicon glyphicon-pencil"></span></a>');
                    $("p[itemid=" + data.id + "][for=quantity]").html(parseFloat(data.quantity).toMoney(0, ',', '.') + ' <a class="btn-quick-edit" onclick="item.change(this)" ><span class="glyphicon glyphicon-pencil"></span></a>');
                    $("p[itemid=" + data.id + "][for=weight]").html(parseFloat(data.weight).toMoney(0, ',', '.') + ' <a class="btn-quick-edit" onclick="item.change(this)" ><span class="glyphicon glyphicon-pencil"></span></a>');
                    $("p[itemid=" + data.id + "][for=startPrice]").html(parseFloat(data.startPrice).toMoney(0, ',', '.') + ' <a class="btn-quick-edit" onclick="item.change(this)" ><span class="glyphicon glyphicon-pencil"></span></a>');
                    $("p[itemid=" + data.id + "][for=sellPrice]").html(parseFloat(data.sellPrice).toMoney(0, ',', '.') + ' <a class="btn-quick-edit" onclick="item.change(this)" ><span class="glyphicon glyphicon-pencil"></span></a>');
                    h2.css({"display": "block"});
                    $("[for=" + colName + "]").css({"display": "none"});
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

item.numberFormat = function (number) {
    return number;
};
item.getSelectedItems = function () {
    var listItems = new Array();
    $('input[for=checkall]:checked').each(function (i, el) {
        listItems.push($(el).attr("value"));
    });
    return listItems;
};

item.upItems = function () {
    listItems = item.getSelectedItems();
    if (listItems.length > 0) {
        upschedule.payment(listItems);
    } else {
        popup.msg("Bạn hãy chọn sản phẩm để tiếp tục");
    }
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

item.search = function (find, page) {
    var urlParams = item.urlParam();
    if (page == null || page < 0) {
        page = 1;
    }
    urlParams.page = page;
    if (find != null && find != '' && find == 'selling') {
        urlParams.find = 'selling';
        $("select[name=find]").val("selling");
    }
    if (find != null && find != '' && find == 'outDate') {
        urlParams.find = 'outDate';
        $("select[name=find]").val("outDate");
    }
    if (find != null && find != '' && find == 'unapproved') {
        urlParams.find = 'unapproved';
        $("select[name=find]").val("unapproved");
    }
    if (find != null && find != '' && find == 'outofstock') {
        urlParams.find = 'outofstock';
        $("select[name=find]").val("outofstock");
    } else {
        urlParams.find = $("select[name=find]").val();
    }
    if ($("select[name=categoryId]").val() != "0") {
        urlParams.categoryId = $("select[name=categoryId]").val();
    } else {
        urlParams.categoryId = null;
    }
    if ($("select[name=createTime]").val() != "0") {
        urlParams.createTime = $("select[name=createTime]").val();
    } else {
        urlParams.createTime = null;
    }
    if ($("select[name=shopCategoryId]").val() != "0") {
        urlParams.shopCategoryId = $("select[name=shopCategoryId]").val();
    } else {
        urlParams.shopCategoryId = null;
    }

    if ($("select[name=lastTime]").val() != "0") {
        urlParams.lastTime = $("select[name=lastTime]").val();
    } else {
        urlParams.lastTime = null;
    }
    if ($("select[name=listingType]").val() != '' && $("select[name=listingType]").val() != "0") {
        urlParams.listingType = $("select[name=listingType]").val();
    } else {
        urlParams.listingType = null;
    }
    if ($("select[name=condition]").val() != '' && $("select[name=condition]").val() != "0") {
        urlParams.condition = $("select[name=condition]").val();
    } else {
        urlParams.condition = null;
    }
    if ($("input[name=keyword]").val() != '') {
        urlParams.keyword = $("input[name=keyword]").val();
    } else {
        urlParams.keyword = null;
    }
    var queryString = "";
    var i = 1;
    $.each(urlParams, function (key, val) {
        if (val != null) {
            if (i == 1)
                queryString += "?";
            else
                queryString += "&";
            queryString += key + "=" + val;
            i++;
        }
    });
    location.href = "/user/item.html" + queryString;
};

item.nextPage = function (page) {
    var urlParams = item.urlParam();
    urlParams.page = page;
    var queryString = "";
    var i = 1;
    $.each(urlParams, function (key, val) {
        if (val != null) {
            if (i == 1)
                queryString += "?";
            else
                queryString += "&";
            queryString += key + "=" + val;
            i++;
        }
    });
    location.href = "/user/item.html" + queryString;
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


item.changPage = function (page) {
    if (typeof page == 'undefined' || page == '') {
        page = $('input[name=page]').val();
    }
    if (isNaN(page)) {
        popup.msg("Trang chuyển đến phải là số");
        return false;
    }
    if (window.location.href.indexOf('?') > -1 && window.location.href.indexOf('=') > -1) {
        window.location.href = window.location.href + "&page=" + page;
    } else {
        window.location.href = window.location.href + "?page=" + page;
    }
};


item.inActive = function () {
    var ids = [];
    var index = 0;
    $.each($("input[for=checkall]"), function () {
        if ($(this).is(":checked")) {
            ids[index] = $(this).val();
            index++;
        }
    });
    if (ids.length == 0) {
        popup.msg("Bạn cần chọn sản phẩm nào để thực hiện chức năng này.");
        return false;
    }
    popup.confirm("Bạn chắc chắn muốn thay đổi trạng thái sản phẩm này?", function () {
        ajax({
            service: '/item/inactive.json',
            data: {ids: JSON.stringify(ids)},
            loading: false,
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
    });
};

item.restore = function () {
    var ids = [];
    var index = 0;
    $.each($("input[for=checkall]"), function () {
        if ($(this).is(":checked")) {
            ids[index] = $(this).val();
            index++;
        }
    });
    if (ids.length == 0) {
        popup.msg("Bạn cần chọn sản phẩm nào để thực hiện chức năng này.");
        return false;
    }
    popup.confirm("Bạn chắc chắn muốn khôi phục những sản phẩm này?", function () {
        ajax({
            service: '/item/inactive.json',
            data: {ids: JSON.stringify(ids)},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    popup.msg("Khôi phục thành công danh sách được chọn!", function () {
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
item.restoreOneItem = function (id) {
    var ids = [];
    ids.push(id);
    popup.confirm("Bạn chắc chắn muốn khôi phục sản phẩm này?", function () {
        ajax({
            service: '/item/inactive.json',
            data: {ids: JSON.stringify(ids)},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    popup.msg("Khôi phục thành công!", function () {
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
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
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

item.deleteItem = function (_id) {
    popup.confirm("Bạn chắc chắn muốn xóa sản phẩm này ?", function () {
        var ids = [];
        ids.push(_id);
        ajax({
            service: '/item/remove.json',
            data: {ids: JSON.stringify(ids)},
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
    });
};

item.refresh = function (_id) {
    popup.confirm("Bạn chắc chắn muốn gia hạn sản phẩm này ?", function () {
        var ids = [];
        ids.push(_id);
        ajax({
            service: '/item/refreshitem.json',
            data: {ids: JSON.stringify(ids)},
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
    });
};

item.refreshs = function () {
    var ids = [];
    var index = 0;
    $.each($("input[for=checkall]"), function () {
        if ($(this).is(":checked")) {
            ids[index] = $(this).val();
            index++;
        }
    });
    if (ids.length == 0) {
        popup.msg("Bạn cần chọn sản phẩm nào để thực hiện chức năng làm mới.");
        return false;
    }
    ajax({
        service: '/item/refreshitem.json',
        data: {ids: JSON.stringify(ids)},
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
};
item.stopSelling = function () {
    var ids = [];
    var index = 0;
    $.each($("input[for=checkall]"), function () {
        if ($(this).is(":checked")) {
            ids[index] = $(this).val();
            index++;
        }
    });
    if (ids.length == 0) {
        popup.msg("Bạn cần chọn sản phẩm nào để thực hiện chức năng ngừng bán.");
        return false;
    }
    popup.open("pop-msg", "Cảnh báo!", "<p>Bạn chắc chắn muốn dừng bán những sản phẩm này</p>", [
        {
            title: 'Đồng ý',
            className: 'button',
            fn: function () {
                ajax({
                    service: '/item/stopselling.json',
                    data: {ids: JSON.stringify(ids)},
                    loading: false,
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
        },
        {
            title: 'Đóng',
            fn: function () {
                popup.close("pop-msg");
            }
        }
    ]);
};
item.remove = function () {
    var ids = [];
    var index = 0;
    $.each($("input[for=checkall]"), function () {
        if ($(this).is(":checked")) {
            ids[index] = $(this).val();
            index++;
        }
    });
    if (ids.length == 0) {
        popup.msg("Bạn cần chọn sản phẩm nào để thực hiện chức năng xóa.");
        return false;
    }
    popup.open("pop-msg", "Cảnh báo!", "<p>Bạn chắc chắn muốn xóa những sản phẩm này</p>", [
        {
            title: 'Đồng ý',
            className: 'button',
            fn: function () {
                ajax({
                    service: '/item/remove.json',
                    data: {ids: JSON.stringify(ids)},
                    loading: false,
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
        },
        {
            title: 'Đóng',
            fn: function () {
                popup.close("pop-msg");
            }
        }
    ]);
};


item.cloneItem = function (id) {
    popup.confirm("Bạn chắc chắn muốn đăng một sản phẩm tương tự sản phẩm này ?", function () {
        location.href = baseUrl + '/user/dang-ban-tuong-tu.html?id=' + id + '';
    });
};

item.editItem = function (id) {
    if (id == null || id == '') {
        location.href = baseUrl + '/user/dang-ban.html';
    } else {
        location.href = baseUrl + '/user/dang-ban.html?id=' + id + '';
    }
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
        }
    });
};

item.textNoteApproved = function () {
    var urlParams = item.urlParam();
    if (typeof urlParams.find !== 'undefined' && urlParams.find !== null && urlParams.find !== '' && urlParams.find == 'unapproved') {
        item.drawingNoteApproved();
    }
};

item.drawingNoteApproved = function () {
    var ids = [];
    $.each($('p[for=name]'), function () {
        ids.push($(this).attr('itemid'));
    });
    if (ids !== null && ids.length > 0) {
        ajax({
            service: '/item/getnoteapproveditem.json',
            data: {ids: JSON.stringify(ids)},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    if (resp.data != null && resp.data.length > 0) {
                        $.each(resp.data, function () {
                            if (this.message !== null && this.message !== '') {
                                $('.textNoteAppro[for=' + this.itemId + ']').html('<b>Lý do không duyệt:</b>' + this.message + '');
                            }
                        });
                    }
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    }
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
                    $("tr[data-id=" + id + "]").addClass("success");
                    popup.msg("Sản phẩm đã được đưa lên đầu danh mục, bạn còn " + upQuantity + " lượt up tin miễn phí");
                }
                $("div[data-rel=upS-free]").each(function () {
                    $(this).html('<button onclick="item.upFree(\'' + $(this).attr("data-id") + '\');" type="button" class="btn btn-danger btn-sm btn-block cdt-tooltip" data-toggle="tooltip" data-placement="left" title="" data-original-title="Bạn còn ' + upQuantity + ' lần up tin miễn phí trong ngày">Up miễn phí</button>');
                });
                $(".cdt-tooltip").tooltip();
            } else {
                $("div[data-rel=upS-free]").html('');
            }
        }
    });
};