promotion = {};
promotion.init = function (param) {
    promotion.loadHistogram('', param.type);
    $('.timeselectstart').timeSelect();
    $('.timeselectend').timeSelect();
};
promotion.countCharacters = function () {
    var count = 125 - eval($('input[name=promotionName]').val().length);
    $('span.countresult').html(count + '/125');
};
var itemData = {};
promotion.update = function (id, same) {
    if (typeof same != 'undefined' && same == "1") {
        popup.confirm("Khuyến mại này sẽ bị dừng và tạo mới 1 khuyến mại tương tự. Bạn có muốn tiếp tục thao tác này không?", function () {
            location.href = location.protocol + '//' + location.host + location.pathname + "?id=" + id;
        });
    } else if (typeof same != 'undefined' && same == "2") {
        location.href = location.protocol + '//' + location.host + location.pathname + "?id=" + id;
    } else {
        location.href = location.protocol + '//' + location.host + location.pathname + "?same=1&id=" + id;
    }
};
promotion.edit = function (data) {
    var pro = data.promotion;
    if (typeof pro !== "undefined") {
        promotion.loadHistogram('', (pro.target == 'SHOP_CATEGORY' ? 'shop' : ''));
        $('input[name=promotionName]').val(pro.name);
        promotion.countCharacters();
        $('input[name=startTime]').val(pro.startTime);
        $('input[name=endTime]').val(pro.endTime);
        $('input[name=promId]').val(pro.id);
        $('input[name=minOrderPrice]').val(pro.minOrderPrice);
        $('.timeselectstart').timeSelect();
        $('.timeselectend').timeSelect();
        if (typeof pro.categories !== "undefined") {
            $.each(pro.categories, function () {
                var cate = this;
                listTemp.push({
                    "categoryId": cate.categoryId,
                    "shopCategoryId": cate.shopCategoryId,
                    "discountPrice": eval(cate.discountPrice),
                    "discountPercent": cate.discountPercent
                });
                if (typeof this.catePath != null) {
                    $.each(this.catePath, function () {
                        if (pro.target == 'SHOP_CATEGORY') {
                            if (cate.shopCategoryId != this) {
                                promotion.getCateChilds(this, cate, 0, 'shop');
                            } else {
                                if (eval(cate.discountPrice) > 0) {
                                    var add = '<span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + cate.shopCategoryId + '_price\')"></span>' + eval(cate.discountPrice) + '';
                                    $('div[for="' + cate.shopCategoryId + '_price"]').html(add);
                                }
                                if (eval(cate.discountPercent) > 0) {
                                    var add = '<span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + cate.shopCategoryId + '_per\')"></span>' + cate.discountPercent + '<span> %</span>';
                                    $('div[for="' + cate.shopCategoryId + '_per"]').html(add);
                                }
                            }

                        } else if (pro.target == 'CATEGORY') {
                            if (cate.categoryId != this) {
                                promotion.getCateChilds(this, cate, 0);
                            } else {
                                if (eval(cate.discountPrice) > 0) {
                                    var add = '<span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + cate.categoryId + '_price\')"></span>' + eval(cate.discountPrice) + '';
                                    $('div[for="' + cate.categoryId + '_price"]').html(add);
                                }
                                if (eval(cate.discountPercent) > 0) {
                                    var add = '<span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + cate.categoryId + '_per\')"></span>' + cate.discountPercent + '<span> %</span>';
                                    $('div[for="' + cate.categoryId + '_per"]').html(add);
                                }
                            }
                        }
                    });
                }

            });
        }
        if (typeof (pro.items) !== "undefined") {
            itemData = data.promotion;
            var listCate = new Array();
            $.each(pro.items, function () {
                var item = this;
                listTemp.push({
                    "itemId": item.itemId,
                    "discountPrice": item.discountPrice,
                    "discountPercent": item.discountPercent,
                    "gift": item.gift,
                    "oldPrice": item.oldPrice
                });
                if (listCate.indexOf(item.categoryId) < 0) {
                    listCate.push(item.categoryId);
                }
                if (typeof this.catePath != 'undefined') {
                    promotion.getCateChildsCheck(this.catePath[0], item, 0);
                }
            });
            promotion.getitembycate(listCate, null);
        }
    }
};
promotion.getCateChilds = function (cateid, cate, index, type) {
    var service = '/category/getchilds.json';
    if (type == 'shop') {
        service = '/shopcategory/getchilds.json';
    }
    if ($('li[for=' + cateid + '] ul.clearfix').length > 0) {
        $('li[for=' + cateid + '] ul.clearfix').remove();
    } else {
        ajax({
            service: service,
            loading: false,
            type: 'get',
            data: {id: cateid},
            done: function (resp) {
                if (resp.success && resp.data.length > 0) {
                    if ($('span[for=sub_' + cateid + '] ul.clearfix').length <= 0) {
                        var append = '<ul class="clearfix">';
                        $.each(resp.data, function () {
                            append += '<li style="padding-left: 0px; margin-left: 0px;" class="clearfix" for="' + this.id + '">\
                            <table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>';
                            if (this.level === 2) {
                                append += '<td width="47%" class="sub-category-product" onclick="promotion.getCateChilds(' + this.id + ',\'\',0,\'' + type + '\')">';
                            }
                            if (this.level === 3) {
                                append += '<td width="47%" class="sub-sub-category-product" onclick="promotion.getCateChilds(' + this.id + ',\'\',0,\'' + type + '\')">';
                            }
                            if (this.level === 4) {
                                append += '<td width="47%" class="sub-level2" onclick="promotion.getCateChilds(' + this.id + ',\'\',0,\'' + type + '\')">';
                            }
                            if (this.level === 5) {
                                append += '<td width="47%" class="sub-level3" onclick="promotion.getCateChilds(' + this.id + ',\'\',0,\'' + type + '\')">';
                            }
                            if (this.level === 6) {
                                append += '<td width="47%" class="sub-level4" onclick="promotion.getCateChilds(' + this.id + ',\'\',0,\'' + type + '\'))">';
                            }

                            var cateId = this.id;
                            var ediscountprice = 0;
                            var ediscountpercent = 0;
                            $.each(listTemp, function () {
                                if (this.categoryId === cateId || this.shopCategoryId === cateId) {
                                    ediscountpercent = this.discountPercent;
                                    ediscountprice = eval(this.discountPrice);
                                }
                            });
                            append += '<a><span class="glyphicon glyphicon-share-alt"></span> ' + this.name + '</a> <span for="' + this.id + '"></span></td>' +
                                    '<td width="26.5%" ><div class="text-center" for="' + this.id + '_price">' + ediscountprice +
                                    '<a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + this.id + '_price\')"></span></a>';
                            append += '</td><td width="26.5%" ><div class="text-center" for="' + this.id + '_per">' + ediscountpercent +
                                    '<a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + this.id + '_per\')"></span></a>';
                            if (ediscountpercent > 0) {
                                append += '<span> %</span>';
                            }

                            append += '</div></td></tr></table><span for="sub_' + this.id + '"></span></li>';
                        });
                        append += '</ul>';
                        $('span[for=sub_' + cateid + ']').append(append);
                        promotion.loadHistogram(cateid, type);
                    }
                    if (typeof cate !== "undefined") {
                        if (eval(cate.discountPrice) > 0) {
                            var add = +eval(cate.discountPrice) + '<a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + (type == 'shop' ? cate.shopCategoryId : cate.categoryId) + '_price\')"></span></a>';
                            $('div[for="' + (type == 'shop' ? cate.shopCategoryId : cate.categoryId) + '_price"]').html(add);
                        }
                        if (eval(cate.discountPercent) > 0) {
                            var add = +cate.discountPercent + '%<a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + (type == 'shop' ? cate.shopCategoryId : cate.categoryId) + '_per\')"></span></a>';
                            $('div[for="' + (type == 'shop' ? cate.shopCategoryId : cate.categoryId) + '_per"]').html(add);
                        }
                        promotion.getCateChilds(cate.catePath[index + 1], cate, index + 1, type)
                    }
                }
            }
        });
    }
};
promotion.editProm = function (tag, flag) {
    var change;
    if (tag.split("_")[1] === "per") {
        var value = $('div[for="' + tag + '"]').text().trim().split("%")[0];
        change = '<input class="num" onblur="promotion.savechange(\'' + tag + '\',\'' + flag + '\')" name="' + tag + '" type="text"  value="' + value + '%" style="width:55px"/>';
    } else {
        var value = $('div[for="' + tag + '"]').text().trim();
        change = '<input class="num" onblur="promotion.savechange(\'' + tag + '\',\'' + flag + '\')" name="' + tag + '" type="text"  value="' + value + '" style="width:55px" />';
    }
    $('div[for="' + tag + '"]').html(change);
    $('input[name=' + tag + ']').focus();
    textUtils.inputNumberFormat('num');
};
promotion.savechange = function (tag, flag) {
    var value = $('input[name="' + tag + '"]').val().split(/%/)[0].trim() === '' ? '0' : $('input[name="' + tag + '"]').val().split(/%/)[0].trim();
    value = value.replace(/\./g, '');
    var price = 0, percent = 0;
    var id = tag.split("_")[0];
    if (tag.split("_")[1] === "per" && value >= 100) {
        popup.msg("Phần trăm giảm giá phải nhỏ hơn 100");
        return false;
    } else if (tag.split("_")[1] === "price") {
        var realPrice = $('td[for="real_' + id + '"]').attr("value");
        if (typeof realPrice !== 'undefined' && eval(realPrice) <= eval(value)) {
            popup.msg("Giảm giá phải nhỏ hơn giá bán của sản phẩm");
            return false;
        }
    }
    var change;
    if (tag.split("_")[1] === "per") {
        change = value + '%<a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + tag + '\',\'' + flag + '\')"></span></a>';
        if (value !== '0') {
            var reset = '0';
            reset = reset + '<a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + id + '_price\',\'' + flag + '\')"></span></a>';
            $('div[for="' + id + '_price"]').html(reset);
        }
        percent = value;
    } else {
        var text = "";
        if (eval(value) > 0) {
            $.each(value.split("."), function () {
                text += this;
            });
        } else {
            text = 0;
        }
        if (flag != 'item') {
            change = promotion.monnyFormat(text) + '<a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + tag + '\',\'' + flag + '\')"></span></a>';
        } else {
            change = promotion.monnyFormat(text) + '<a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + tag + '\',\'' + flag + '\')"></span></a>';
        }
        if (value !== '0') {
            var reset = '0%<a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + id + '_per\',\'' + flag + '\')"></span></a>';
            $('div[for="' + id + '_per"]').html(reset);
        }
        price = text;
    }
    $('div[for="' + tag + '"]').html(change);
    var target = $('input[name=target]').val();
    if (target == 'CATEGORY') {
        var exist = false;
        $.each(listTemp, function () {
            if (this.categoryId == id) {
                this.discountPrice = price;
                this.discountPercent = percent;
                exist = true;
            }
        });
        if (!exist) {
            listTemp.push({
                "categoryId": id,
                "discountPrice": price,
                "discountPercent": percent
            });
        }
    } else if (target == 'SHOP_CATEGORY') {
        var exist = false;
        $.each(listTemp, function () {
            if (this.shopCategoryId == id) {
                this.discountPrice = price;
                this.discountPercent = percent;
                exist = true;
            }
        });
        if (!exist) {
            listTemp.push({
                "shopCategoryId": id,
                "discountPrice": price,
                "discountPercent": percent
            });
        }
    } else if (target == 'ITEM') {
        var exist = false;
        $.each(listTemp, function () {
            if (this.itemId == id) {
                this.discountPrice = price;
                this.discountPercent = percent;
                exist = true;
            }
        });
        if (!exist) {
            listTemp.push({
                "itemId": id,
                "discountPrice": price,
                "discountPercent": percent,
                "oldPrice": parseFloat($('td[for=real_' + id + ']').attr("value"))
            });
        }
    }
};
promotion.savePromotion = function (target, type) {
    var id = $('input[name=promId]').val();
    var name = $('input[name=promotionName]').val();
    var startTime = $('input[name=startTime]').val();
    var endTime = $('input[name=endTime]').val();
    var minOrderPrice = 0;
    if (isNaN(minOrderPrice)) {
        popup.msg("Giá trị nhỏ nhất của đơn hàng chỉ được nhập số!");
        return false;
    }
    var data = {
        id: id,
        active: 1,
        name: name,
        target: target,
        type: type,
        startTime: startTime,
        endTime: endTime,
        minOrderPrice: minOrderPrice,
        categories: [],
        items: []
    };
    if (type == 'DISCOUND') {
        if (target == 'CATEGORY') {
            $.each(listTemp, function () {
                if (this.discountPrice > 0 || this.discountPercent > 0) {
                    data.categories.push({
                        "categoryId": this.categoryId,
                        "discountPrice": eval(this.discountPrice),
                        "discountPercent": this.discountPercent
                    });
                }
            });
        } else if (target == 'SHOP_CATEGORY') {
            $.each(listTemp, function () {
                if (this.discountPrice > 0 || this.discountPercent > 0) {
                    data.categories.push({
                        "shopCategoryId": this.shopCategoryId,
                        "discountPrice": eval(this.discountPrice),
                        "discountPercent": this.discountPercent
                    });
                }
            });
        } else if (target == 'ITEM') {
            $.each(listTemp, function () {
                if (this.discountPrice > 0 || this.discountPercent > 0) {
                    data.items.push({
                        "itemId": this.itemId,
                        "discountPrice": this.discountPrice,
                        "discountPercent": this.discountPercent,
                        "oldPrice": this.oldPrice
                    });
                }
            });
        }
    } else {
        $.each(listTemp, function () {
            if (this.gift.length > 0) {
                data.items.push({
                    "itemId": this.itemId,
                    "gift": this.gift
                });
            }
        });
    }
    ajax({
        service: '/promotion/addcategorypromotion.json',
        data: data,
        contentType: 'json',
        type: 'post',
        loading: false,
        done: function (resp) {
            if (resp.success) {
                if (resp.message !== 'SELLER_CREATE_PROMOTION_FAIL') {
                    var id = resp.data.id;
                    xengplus.plus(1000);
                    popup.msg(resp.message, function () {
                        location.href = location.protocol + '//' + location.host + location.pathname;
                    });
                } else {
                    var id = resp.data.id;
                    popup.msg('Tạo khuyến mại thành công!', function () {
                        location.href = location.protocol + '//' + location.host + location.pathname;
                    });
                }
            }
            else {
                $.each($('.errorMessages'), function () {
                    var name = $(this).attr('for');
                    if (resp.data[name] !== undefined && resp.data[name] !== null && resp.data[name] !== '') {
                        $(this).html(resp.data[name]);
                    } else {
                        $('.errorMessages[for=' + name + ']').html("");
                    }
                });  
            }
        }
    });
};
promotion.loadHistogram = function (cid, type) {
    var service = '/histogram/category.json';
    if (type == 'shop') {
        service = '/histogram/shopcategory.json';
    }
    var userid = $('input[name=userid]').val();
    promotion.showButonNB();
    ajax({
        service: service,
        type: 'GET',
        loading: false,
        data: {cid: cid, sellerId: userid, approve: 1, type: 'BUYNOW', catetype: type},
        done: function (resp) {
            if (resp.success && resp.data.length > 0) {
                var data = resp.data;
                for (var i = 0; i < data.length; i++) {
                    if (data[i].count > 0) {
                        $('span[for=' + data[i].id + ']').text('(' + data[i].count + ')');
                    } else {
                        $('li[for=' + data[i].id + ']').remove();
                    }
                }
                $.each($('ul.clearfix'), function () {
                    if ($(this).find("li").length <= 0) {
                        $(this).remove();
                    }
                });
            }
        },
    });
};
promotion.stopPromotion = function (promId) {
    popup.confirm("Bạn có chắc chắn muốn dừng khuyến mại này không?", function () {
        ajax({
            service: '/promotion/stoppromotion.json',
            loading: false,
            data: {promId: promId},
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
promotion.monnyFormat = function (number, decimals, dec_point, thousands_sep) {
    number = (number + '').replace(/[^0-9+\-Ee.]/g, '');
    var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            sep = (typeof thousands_sep === 'undefined') ? '.' : thousands_sep,
            dec = (typeof dec_point === 'undefined') ? ',' : dec_point,
            s = '',
            toFixedFix = function (n, prec) {
                var k = Math.pow(10, prec);
                return '' + Math.round(n * k) / k;
            };
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
};
promotion.countPromotion = function () {
    var count = 100 - eval($('textarea[name=promotionGift]').val().length);
    $.each($('textarea[name=itemgift]'), function () {
        var exist = false;
        var a = this;
        $.each(listTemp, function () {
            if (this.itemId == $(a).attr("for") && this.gift != '') {
                exist = true;
            }
        });
        if (!exist) {
            $(this).val($('textarea[name=promotionGift]').val());
        }
    });
    $('span.countresults').html(count + '/100');
};
promotion.getCateChildsCheck = function (cateid, item, index) {
    if (typeof cateid !== 'undefined') {
        if (cateid.indexOf("_") !== -1) {
            var id = cateid.split("_")[cateid.split("_").length - 1];
        } else {
            var id = cateid;
        }
        if ($('li[for=' + id + '] ul.clearfix').length > 0 && !$('input[class=' + id + ']').is(':checked')) {
            $('li[for=' + id + '] ul.clearfix').remove();
        } else {
            ajax({
                service: '/category/getchilds.json',
                data: {id: id},
                loading: false,
                done: function (resp) {
                    if (resp.success && resp.data.length > 0) {
                        if ($('li[for=' + id + '] ul.clearfix').length <= 0) {
                            var append = '<ul class="sub-check-list-cat clearfix">';
                            $.each(resp.data, function () {
                                append += '<li class="clearfix" for="' + this.id + '" level="child">' +
                                        '<div class="radio"><label><input type="checkbox" name="catechk" value="' + cateid + "_" + this.id + '"  class="' + this.id + '" onclick="promotion.getCateChildsCheck(\'' + this.id + '\',\'\')" /> <a>' +
                                        this.name + '</a> <span for="' + this.id + '"></span></label></div></li>';
                            });
                            append += '</ul>';
                            $('li[for=' + id + ']').append(append);
                            promotion.loadHistogram(cateid);
                        }
                    }
                    if (typeof item !== "undefined" && item != null && item != '') {
                        $('input[class=' + id + ']').attr({checked: true});
                        promotion.getCateChildsCheck(item.catePath[index + 1], item, index + 1);
                    }
                }
            });
        }
    }
    var cate = new Array();
    $.each($('input[name=catechk]:checked'), function () {
        $.each($(this).val().split("_"), function () {
            cate.push(this);
        });
    });
    if (typeof item == "undefined" || item == null || item == '') {
        promotion.getitembycate(cate, null);
    }
};
promotion.getitembycate = function (cateid, keyword, pageIndex) {
    if (pageIndex == 'undefined' || pageIndex <= 0) {
        pageIndex = 0;
    }
    var type = $('input[name=type]').val();
    $('table.tbl-items tr').remove();
    if (cateid !== 'undefined' && cateid != null && cateid != '') {
        ajax({
            service: '/item/getitembycateids.json',
            data: {cids: JSON.stringify(cateid), keyword: keyword, page: pageIndex},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    $('span.fillpage').html(resp.data.pageIndex + 1 + "/" + resp.data.pageCount);
                    $('input[name=pageIndex]').val(resp.data.pageIndex);
                    $.each(resp.data.data, function () {
                        var img = "";
                        if (this.images != null && this.images.length > 0) {
                            img = this.images[0];
                        }
                        var name = this.id + " - " + this.name;
                        if (name.length > 50) {
                            name = name.substr(0, 50) + "...";
                        }
                        var item = this;
                        var append = '', gift = '';
                        var eDiscountPrice = 0, eDiscountPercent = 0;
                        $.each(listTemp, function () {
                            if (this.itemId == item.id) {
                                gift = this.gift == null ? '' : this.gift;
                                eDiscountPercent = this.discountPercent;
                                eDiscountPrice = this.discountPrice;
                            }
                        });
                        if (type == 'GIFT') {
                            append = '<tr><td><div class="text-center"><input type="checkbox" onclick="promotion.saveTempGift(' + item.id + ')" name="itemchk" class="" for="' + item.id + '"';
                            if (gift != '') {
                                append += 'checked="checked"';
                            } else {
                                if (typeof ($('textarea[name=contentgift]').val()) !== 'undefined' && $('textarea[name=contentgift]').val().trim() !== ''
                                        && $('textarea[name=contentgift]').val().trim() != 'Ví dụ: Mua sản phẩm này tặng kèm lịch năm mới:') {
                                    gift = $('textarea[name=contentgift]').val().trim();
                                }
                            }

                            append += '/></div></td>' +
                                    '<td width="55%" valign="top"><div class="table-content"><span class="img-product-bill-small">' +
                                    '<img src="' + img + '" width="40" height="40" /></span>' + name + '</div></td>' +
                                    '<td valign="top"><div class="text-center"><textarea name="itemgift" onblur="promotion.saveTempGift(' + item.id + ')" rows="2" class="desc-coupon form-control" for="' + item.id + '">' + gift + '</textarea></div></td>' +
                                    '</tr>';
                        } else {
                            append = ' <tr >' +
                                    '<td width="50%" valign="top" style="padding:5px;" ><div class="table-content"><span class="img-product-bill-small">' +
                                    '<a><img src="' + img + '" class="img-responsive" /></a></span>' + name + '</div></td>' +
                                    '<td width="18%"  valign="top" for="real_' + item.id + '" value="' + item.sellPrice + '"><div class="text-center">' + promotion.monnyFormat(item.sellPrice) + '</div></td>' +
                                    '<td  width="17%" valign="top"><div class="text-center" for="' + item.id + '_price">' + promotion.monnyFormat(eDiscountPrice) +
                                    ' <a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + item.id + '_price\',\'item\')"></span></a></div></td>' +
                                    '<td  width="20%" valign="top"><div class="edit-coupon text-center" for="' + item.id + '_per">' + eDiscountPercent + '% ' +
                                    '<a class="btn-quick-edit"><span class="glyphicon glyphicon-pencil" onclick="promotion.editProm(\'' + item.id + '_per\',\'item\')"></span></a></div></td>' +
                                    '</tr>';
                        }
                        $('table.tbl-items').append(append);
                    });
                }
            }
        });
    }
};
promotion.resetcate = function () {
    $.each($('li[level=child]'), function () {
        $(this).parent().remove();
    });
    $.each($('input[name=catechk]:checked'), function () {
        $(this).removeAttr("checked");
    });
    promotion.loadHistogram('');
    $('table.tbl-items tr').remove();
};
promotion.resetitems = function () {
    var cate = new Array();
    $.each($('input[name=catechk]:checked'), function () {
        $.each($(this).val().split("_"), function () {
            if (cate.indexOf(this) < 0) {
                cate.push(this);
            }
        });
    });
    listTemp = [];
    if (typeof itemData !== "undefined" && typeof (itemData.items) !== "undefined") {
        $.each(itemData.items, function () {
            var item = this;
            listTemp.push({
                "itemId": item.itemId,
                "discountPrice": item.discountPrice,
                "discountPercent": item.discountPercent,
                "gift": item.gift,
                "oldPrice": item.oldPrice
            });
        });
    }
    promotion.getitembycate(cate, null);
};
promotion.showButonNB = function () {
    $('li[title=next]').show();
    $('li[title=back]').show();
}
promotion.pagePre = function () {
    var listcate = new Array();
    $.each($('input[name=catechk]:checked'), function () {
        $.each($(this).val().split("_"), function () {
            listcate.push(this);
        });
    });
    var pageIndex = $('input[name=pageIndex]').val();
    var keyword = $('input[name=search]').val().trim();
    promotion.showButonNB();
    promotion.getitembycate(listcate, keyword, eval(pageIndex) - 1);
};
promotion.pageNext = function () {
    var numN = $('.fillpage').text();
    var arraySplit = numN.split("/");
    var newArray = [];
    for (i = 0; i < arraySplit.length; i++) {
        newArray.push(arraySplit[i]);
    }
    var listcate = new Array();
    $.each($('input[name=catechk]:checked'), function () {
        $.each($(this).val().split("_"), function () {
            listcate.push(this);
        });
    });
    var pageIndex = $('input[name=pageIndex]').val();
    var keyword = $('input[name=search]').val().trim();
    if (eval(newArray[0]) >= eval(newArray[1])) {
        $('li[title=next]').hide();
        pageIndex--;
    }
    promotion.getitembycate(listcate, keyword, eval(pageIndex) + 1);
};
promotion.saveTempGift = function (id) {
    var type = $('input[name=type]').val();
    if (type == 'GIFT') {
        if ($('input[for=' + id + ']').is(':checked')) {
            var gift = $('textarea[for=' + id + ']').val();
            var exist = false;
            $.each(listTemp, function () {
                if (this.itemId == id) {
                    this.gift = gift
                    exist = true;
                }
            });
            if (!exist) {
                listTemp.push({
                    "itemId": id,
                    "gift": gift
                });
            }

        }
    }
};
promotion.search = function () {
    var keyword = $('input[name=search]').val().trim();
    if (keyword == 'Tìm kiếm') {
        keyword = null;
    }
    var cate = new Array();
    $.each($('input[name=catechk]:checked'), function () {
        $.each($(this).val().split("_"), function () {
            cate.push(this);
        });
    });
    promotion.getitembycate(cate, keyword);
};


promotion.viewDetail = function (id, page) {

    popup.open('popup-view-promotion', 'Sản phẩm nhận được khuyến mại', template('/user/tpl/promotiondetail.tpl', {}), [
        {
            title: 'Đóng',
            style: 'btn-default',
            fn: function () {
                popup.close('popup-view-promotion');
            }
        }
    ]);
    promotion.drawDetailPromotion(id, 1);
};
promotion.drawDetailPromotion = function (id, page) {
    ajax({
        service: '/promotion/getdetailpromotion.json',
        data: {promId: id, page: page},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                var promotionItems = resp.data.dataPage.data;
                var items = resp.data.items;
                var html = '<tr class="warning">\
                    <th width="20%"><div class="text-lefts">Mã sản phẩm</div></th>\
                    <th  valign="middle"><div class="text-left">Tên sản phẩm</div></th>\
                    <th  valign="middle"><div class="text-center">Khuyến mại</div></th>\
                </tr>';
                if (promotionItems.length > 0) {
                    $.each(promotionItems, function (i) {
                        var promItem = this;
                        $.each(items, function () {
                            if (this.id == promItem.itemId) {
                                var km = '';
                                if (typeof promItem.gift !== 'undefined' && promItem.gift != null && promItem.gift != '') {
                                    km = promItem.gift;
                                } else if (promItem.discountPercent > 0) {
                                    km = promItem.discountPercent + ' %';
                                } else {
                                    km = promItem.discountPrice + ' <sup class="u-price">đ</sup>';
                                }
                                html += '<tr for="item">\
                        <td>' + this.id + '</td>\
                        <td><a href="' + baseUrl + "/san-pham/" + this.id + "/" + textUtils.createAlias(this.name) + '.html" target="_blank">' + this.name + '</a></td>\
                        <td>' + km + '</td>\
                    </tr>';
                            }
                        });
                    });
                } else {
                    html += '<tr><td colspan="3" class="clr-red">Không có sản phẩm nào</td></tr>';
                }
                $('#itemData').html(html);

                var page = '';
                if (resp.data.dataPage.pageCount > 1) {
                    page += '<div class="page-ouner clearfix"><ul class="pagination pull-right" >';
                    if (resp.data.dataPage.pageIndex > 2)
                        page += '<li onclick="promotion.drawDetailPromotion(' + id + ', 1)"><a href="javascript:;">«</a></li >';
                    if (resp.data.dataPage.pageIndex >= 3)
                        page += '<li onclick="promotion.drawDetailPromotion(' + id + ', ' + (resp.data.dataPage.pageIndex - 2) + ')"> <a href="javascript:;"> ' + (resp.data.dataPage.pageIndex - 2) + '</a></li >';
                    if (resp.data.dataPage.pageIndex >= 2)
                        page += '<li onclick="promotion.drawDetailPromotion(' + id + ', ' + (resp.data.dataPage.pageIndex - 1) + ')"> <a href="javascript:;" > ' + (resp.data.dataPage.pageIndex - 1) + ' </a></li >';
                    if (resp.data.dataPage.pageIndex >= 1)
                        page += '<li onclick="promotion.drawDetailPromotion(' + id + ', ' + (resp.data.dataPage.pageIndex) + ')"> <a href="javascript:;" > ' + (resp.data.dataPage.pageIndex) + ' </a></li >';

                    page += '<li class="active" > <a class = "btn btn-primary" > ' + (resp.data.dataPage.pageIndex + 1) + '</a>';

                    if (resp.data.dataPage.pageCount - resp.data.dataPage.pageIndex > 1)
                        page += '<li onclick="promotion.drawDetailPromotion(' + id + ', ' + (resp.data.dataPage.pageIndex + 2) + ')"> <a href="javascript:;"> ' + (resp.data.dataPage.pageIndex + 2) + '</a></li >';
                    if (resp.data.dataPage.pageCount - resp.data.dataPage.pageIndex > 2)
                        page += '<li onclick="promotion.drawDetailPromotion(' + id + ', ' + (resp.data.dataPage.pageIndex + 3) + ')"><a href="javascript:;"> ' + (resp.data.dataPage.pageIndex + 3) + '</a></li >';
                    if (resp.data.dataPage.pageCount - resp.data.dataPage.pageIndex > 3)
                        page += '<li onclick="promotion.drawDetailPromotion(' + id + ', ' + resp.data.dataPage.pageCount + ')"><a href="javascript:;">»</a></li >';
                    page += '</div></ul>';
                }

                $("#pagination").html(page);
            }
        }
    });
};
