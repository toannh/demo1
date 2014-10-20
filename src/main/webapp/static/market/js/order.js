order = {};
order.payment = {};
order.coupon = "";
order.couponPrice = "";
order.upNumber = function (className) {
    var val = $("input." + className).val();
    if (isNaN(val) || val < 0)
        $("input." + className).val(0);
    else {
        val = eval(val) + eval(1);
        $("input." + className).val(val);
    }
};
order.downNumber = function (className) {
    var val = $("input." + className).val();
    if (isNaN(val) || val <= 1)
        $("input." + className).val(1);
    else {
        val = eval(val) - eval(1);
        $("input." + className).val(val);
    }
};
order.changeNumber = function (obj, quantity) {
    var val = $(obj).val();
    if (isNaN(val) || val <= 1) {
        $(obj).val(1);
        return false;
    } else {
        if (val > quantity) {
            $(obj).val(quantity);
        }
        return true;
    }
};
order.buyNow = function (_itemId, _sellerId) {
    var quantity = $("[itemquantity=" + _itemId + "]").val() > 0 ? $("[itemquantity=" + _itemId + "]").val() : 1;
    var subItem = order.getSubItem(quantity);
    if (subItem == false) {
        return false;
    }
    ajax({
        service: '/order/addtocart.json',
        data: {itemId: _itemId, quantity: quantity, subItem: base64.encode(JSON.stringify(subItem))},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                //drawl nav
                order.drawlNav(resp.data);
                var orderId = '';
                $.each(resp.data, function () {
                    if (this.sellerId === _sellerId) {
                        orderId = this.id;
                    }
                });
                if (orderId !== '') {
                    location.href = baseUrl + '/' + orderId + '/thanh-toan-gio-hang.html';
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
order.addToCart = function (_itemId) {
    var quantity = $("[itemquantity=" + _itemId + "]").val() > 0 ? $("[itemquantity=" + _itemId + "]").val() : 1;
    var subItem = order.getSubItem(quantity);
    if (subItem == false) {
        return false;
    }
    ajax({
        service: '/order/addtocart.json',
        data: {itemId: _itemId, quantity: quantity, subItem: base64.encode(JSON.stringify(subItem))},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                //drawl nav
                order.drawlNav(resp.data);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

order.getSubItem = function (quantity) {
    var subItem = {};
    var exitsCollor = false, exitsSize = false;
    if ($('div[data-rel=collor]').length > 0) {
        exitsCollor = true;
        $('div[data-rel=collor] .tc-item').each(function () {
            if ($(this).attr("class").indexOf("active") > -1) {
                subItem.colorId = $(this).attr("data-property-id");
                subItem.colorName = $(this).attr("data-property-name");
                subItem.colorValueId = $(this).attr("data-propertyvalue-id");
                subItem.colorValueName = $(this).attr("data-propertyvalue-name");
                return false;
            }
        });
    }
    if ($('div[data-rel=size]').length > 0) {
        exitsSize = true;
        $('div[data-rel=size] .tc-item').each(function () {
            if ($(this).attr("class").indexOf("active") > -1) {
                subItem.sizeId = $(this).attr("data-property-id");
                subItem.sizeName = $(this).attr("data-property-name");
                subItem.sizeValueId = $(this).attr("data-propertyvalue-id");
                subItem.sizeValueName = $(this).attr("data-propertyvalue-name");
                return false;
            }
        });
    }
    if (exitsCollor && typeof subItem.colorId == 'undefined') {
        popup.msg("Bạn chưa chọn màu sắc sản phẩm sản phẩm");
        return false;
    }
    if (exitsSize && typeof subItem.sizeId == 'undefined') {
        popup.msg("Bạn chưa chọn kích cỡ sản phẩm sản phẩm");
        return false;
    }
    subItem.quantity = quantity;
    return subItem;
};

order.drawlNav = function (data) {
    var html = '';
    var total = 0;
    var price = 0;
    $.each(data, function () {
        total += eval(this.items !== null ? this.items.length : 0);
        $.each(this.items, function () {
            price += eval(this.itemPrice * this.quantity);
            html += '<div class="grid" rel="show_' + this.itemId + '" >\
                                <span class="usercart-remove" onclick="order.removeItem(\'' + this.id + '\')" >X</span>\
                                <span class="usercart-price">' + eval(this.itemPrice * this.quantity).toMoney(0, ',', '.') + '<sup class="price">đ</sup></span>\
                                <span class="usercart-number">x' + this.quantity + '</span>\
                                <div class="img"><img src="' + this.images + '" alt="' + this.itemName + '"></div>\
                                <div class="g-content">\
                                    <div class="g-row"><a href="' + baseUrl + '/san-pham/' + this.itemId + '/' + textUtils.createAlias(this.itemName) + '.html" target="_blank" >' + this.itemName + '</a></div>\
                                </div>\
                            </div>';
        });
        $("div[cart=show]").html(html);
    });
    if (total > 0) {
        total = '<span class="icon-cart"></span><span class="msg-red">' + total + '</span>';
    } else {
        total = '<span class="icon-cart"></span>';
    }
    $("a[rel=totalCart]").html(total);
    $("span[rel=cartPrice]").parent().parent().css({"display": "block"});
    $("span[rel=cartPrice]").html(price.toMoney(0, ',', '.'));
};


order.drawlViewCart = function (data) {
    $(".cart-seller").css({"display": "none"});
    $.each(data, function () {
        $(".cart-seller[sellerId=" + this.sellerId + "]").css({"display": "block"});
        var price = 0;
        $.each(this.items, function () {
            price += eval(this.itemPrice * this.quantity);
        });
        $("span[price=" + this.id + "]").html(price.toMoney(0, ',', '.') + '<sup class="price">đ</sup>');
    });
};

order.removeItem = function (_id) {
    ajax({
        service: '/order/removeitem.json',
        data: {id: _id},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                $('[rel=show_' + _id + ']').css({"display": "none"});
                order.drawlNav(resp.data);
                order.drawlViewCart(resp.data);
            } else {
                $("div[cart=show]").html('<div class="grid"><div class="g-row">Hiện giỏ hàng trống</div></div>');
                $(".usercart-total").remove();
                $("a[rel=totalCart]").html('<span class="icon-cart"></span>');
            }
        }
    });
};

order.updateToCart = function (_orderId, _orderItemId) {
    var quantity = $("[itemquantity=" + _orderItemId + "]").val() > 0 ? $("[itemquantity=" + _orderItemId + "]").val() : 1;
    ajax({
        service: '/order/updatecart.json',
        data: {orderId: _orderId, orderitemid: _orderItemId, quantity: quantity},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                $.each(resp.data, function () {
                    var price = 0;
                    order.drawlNav(resp.data);
                    $.each(this.items, function () {
                        var tprice = eval(this.itemPrice * this.quantity);
                        price += tprice;
                        $("span[_tprice=" + this.id + "]").html(tprice.toMoney(0, ',', '.') + '<sup class="price" >đ</sup>');
                    });
                    $("span[price=" + this.id + "]").html(price.toMoney(0, ',', '.') + '<sup class="price">đ</sup>');
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

order.initPayment = function () {

    //draw city and district
    var tooltip = $("span[rel=sellerAddress]").attr("data-original-title") + " (";
    var html = '<option value="0" >Chọn tỉnh / thành phố</option>';
    $.each(citys, function () {
        html += '<option value="' + this.id + '" >' + this.name + '</option>';
        if (this.id === $("span[rel=sellerAddress]").attr("sCityId")) {
            tooltip += this.name;
        }
    });
    $("select[name=buyerCityId]").html(html);
    $("select[name=receiverCityId]").html(html);
    $("select[name=buyerCityId]").val($("select[name=buyerCityId]").attr("val")).change();
    $("select[name=receiverCityId]").val($("select[name=receiverCityId]").attr("val")).change();

    html = '<option value="0" >Chọn quận / huyện</option>';
    $.each(districts, function () {
        if (this.id === $("span[rel=sellerAddress]").attr("sDistrictId")) {
            tooltip += "/" + this.name;
        }
        if (this.cityId === $("select[name=buyerCityId]").attr("val")) {
            html += '<option value="' + this.id + '" >' + this.name + '</option>';
        }
    });
    $("span[rel=sellerAddress]").attr("data-original-title", tooltip + ")");
    $("select[name=buyerDistrictId]").html(html);

    html = '<option value="0" >Chọn quận / huyện</option>';
    $.each(districts, function () {
        if (this.cityId === $("select[name=receiverCityId]").attr("val")) {
            html += '<option value="' + this.id + '" >' + this.name + '</option>';
        }
    });

    $("select[name=receiverDistrictId]").html(html);

    $("select[name=buyerDistrictId]").val($("select[name=buyerDistrictId]").attr("val")).change();
    $("select[name=receiverDistrictId]").val($("select[name=receiverDistrictId]").attr("val")).change();

    if ($("span[rel=shipchung]").length === 0) {
        $("div[show=shipchung]").hide();
    }

    $("input[name=sync]").change(function () {
        if ($(this).is(":checked")) {
            order.syncProfile();
        }
    });
    order.localChange();
    $.each($("[for=buyer]"), function () {
        $(this).change(function () {
            order.syncProfile();
        });
    });
    order.syncProfile();
    order.updateItemCart($("input[name=id]").val(), '');
    $("select[name=receiverCityId]").change(function () {
        if ($(this).val() !== '0' && $("select[name=receiverDistrictId]").val() !== '0') {
            $("div[name=RAPID]").fadeIn();
            $("div[name=FAST]").fadeIn();
            $("div[name=SLOW]").fadeIn();
            order.updateItemCart($("input[name=id]").val(), '');
        }
    });
    $("select[name=receiverDistrictId]").change(function () {
        if ($(this).val() !== '0' && $("select[name=receiverCityId]").val() !== '0') {
            $("div[name=RAPID]").fadeIn();
            $("div[name=FAST]").fadeIn();
            $("div[name=SLOW]").fadeIn();
            order.updateItemCart($("input[name=id]").val(), '');
        }
    });
    $("input[name=shipmentService]").change(function () {
        order.updateItemCart($("input[name=id]").val(), '');
    });

    //Mặc định chọn NL
//    order.selectPM('NL');
    $.each($('.colorsizePayment'), function () {
        var color = property.getCollor(textUtils.createAlias($(this).attr('color')));
        $(this).html('<div style="background-color:' + color + '" class="tc-item" title="' + $(this).attr('color') + '"></div>');
    });

};
order.initViewcart = function () {
    $.each($('.cart-seller'), function (i) {
        var sellerId = $(this).attr('sellerId');
        $.each($('#' + sellerId + ' .colorsizeViewcart'), function () {
            var color = property.getCollor(textUtils.createAlias($(this).attr('color')));
            $(this).html('<div style="background-color:' + color + '" class="tc-item" title="' + $(this).attr('color') + '"></div>');
        });
    });
};
order.initInvoice = function () {
    $.each($('.colorsizeInvoice'), function () {
        var color = property.getCollor(textUtils.createAlias($(this).attr('color')));
        $(this).html('<div style="background-color:' + color + '" class="tc-item" title="' + $(this).attr('color') + '"></div>');
    });
};
order.localChange = function () {
    $("select[name=buyerCityId]").change(function () {
        if ($("input[name=sync]").is(":checked")) {
            $("select[name=receiverCityId]").val($(this).val()).change();
        }
        var html = '<option value="0" >Chọn quận / huyện</option>';
        $.each(districts, function () {
            if (this.cityId === $("select[name=buyerCityId]").val()) {
                html += '<option value="' + this.id + '" >' + this.name + '</option>';
            }
        });
        $("select[name=buyerDistrictId]").html(html);
    });
    $("select[name=receiverCityId]").change(function () {
        var html = '<option value="0" >Chọn quận / huyện</option>';
        $.each(districts, function () {
            if (this.cityId === $("select[name=receiverCityId]").val()) {
                html += '<option value="' + this.id + '" >' + this.name + '</option>';
            }
        });
        $("select[name=receiverDistrictId]").html(html);
    });

    $("select[name=buyerDistrictId]").change(function () {
        if ($("input[name=sync]").is(":checked")) {
            $("select[name=receiverDistrictId]").val($(this).val()).change();
        }
    });
};

order.syncProfile = function () {
    if ($("input[name=sync]").is(":checked")) {
        $.each($("[for=buyer]"), function () {
            var name = $(this).attr("name");
            $("[for=" + name + "]").val($(this).val()).change();
        });
        $("select[name=receiverCityId]").val($("select[name=buyerCityId]").val()).change();
        $("select[name=receiverDistrictId]").val($("select[name=buyerDistrictId]").val()).change();
    }
};

order.selectPM = function (_method, _atm, _banking) {
    order.payment.method = _method;
    if (_atm === true) {
        $("[bank=ATM]").css({"display": "block"});
        $("[bank=ATM] input[type=radio]").attr({"checked": true});
        order.payment.type = 'ATM';
    } else {
        $("[bank=ATM]").css({"display": "none"});
    }
    if (_banking === true) {
        $("[bank=BANKING]").css({"display": "block"});
    } else {
        $("[bank=BANKING]").css({"display": "none"});
    }
    order.updateItemCart($("input[name=id]").val(), '');
};

order.selectAction = function (_type) {
    order.payment.type = _type;
};

order.paymentAction = function (_orderId) {
    $("div.loading-fast").fadeIn("slow");
    var quantity = 1;
    ajax({
        service: '/order/updatecart.json',
        data: {orderId: _orderId, orderitemid: '', quantity: quantity},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                var orderData = order.getData(_orderId);
                $.each(resp.data, function () {
                    if (this.id === _orderId) {
                        orderData.items = this.items;
                    }
                });
                var serviceUrl = '/order/create.json';
                if (typeof (textUtils.urlParam().copyId) !== "undefined" && textUtils.urlParam().copyId !== null && textUtils.urlParam().copyId !== '') {
                    serviceUrl += '?copyId=' + textUtils.urlParam().copyId;
                }

                if (orderData.paymentMethod === null || orderData.paymentMethod === '') {
                    popup.msg("Bạn cần chọn hình thức thanh toán để thanh toán cho đơn hàng");
                    return false;
                }

                ajax({
                    service: serviceUrl,
                    data: orderData,
                    loading: false,
                    type: 'post',
                    contentType: 'json',
                    success: function (resp) {
                        $("div.loading-fast").hide("slow");
                        if (resp.success) {
                            googleAnalytics.add(orderData, 'order');
                            popup.msg(resp.message, function () {
                                location.href = resp.data;
                            });
                        } else {
                            if (resp.message == null) {
                                location.reload();
                                return false;
                            }
                            popup.msg(resp.message);
                            $("._error").removeClass('has-error');
                            $.each(resp.data, function (key, message) {
                                $("[name=" + key + "]").after('<div class="help-block">' + message + '</div>');
//                                $("[name=" + key + "]").attr("placeholder", message);
                                $("[name=" + key + "]").parent().parent().addClass('has-error _error');
                            });
                        }
                    }
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

order.removeItemCart = function (_orderId, _id) {
    ajax({
        service: '/order/removeitem.json',
        data: {id: _id},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                $('[rel=show_' + _id + ']').css({"display": "none"});
                order.drawlNav(resp.data);
                var orderData = order.getData(_orderId);
                $.each(resp.data, function () {
                    if (this.id === _orderId) {
                        orderData.items = this.items;
                        order.calculator(orderData);
                    }
                });
            } else {
                $("div[cart=show]").html('<div class="grid"><div class="g-row">Hiện giỏ hàng trống</div></div>');
                $(".usercart-total").remove();
                $("a[rel=totalCart]").html('<span class="icon-cart"></span>');
                popup.msg("Đơn hàng không còn sản phẩm,về trang chủ chọn sản phẩm thêm vào giỏ hàng", function () {
                    location.href = baseUrl + "/index.html";
                });
            }
        }
    });
};

order.updateItemCart = function (_orderId, _orderItemId) {
    var quantity = $("[itemquantity=" + _orderItemId + "]").val() > 0 ? $("[itemquantity=" + _orderItemId + "]").val() : 1;
    ajax({
        service: '/order/updatecart.json',
        data: {orderId: _orderId, orderitemid: _orderItemId, quantity: quantity},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                order.drawlNav(resp.data);
                var orderData = order.getData(_orderId);
                $.each(resp.data, function () {
                    if (this.id === _orderId) {
                        orderData.items = this.items;
                        order.calculator(orderData);
                    }
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

order.addCoupon = function (_orderId) {
    order.coupon = $("input[name=couponId]").val();
    if (order.coupon === '') {
        popup.msg("Bạn chưa điền mã coupon");
        return false;
    }
    ajax({
        service: '/order/updatecart.json',
        data: {orderId: _orderId, orderitemid: '', quantity: 0},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                order.drawlNav(resp.data);
                var orderData = order.getData(_orderId);
                $.each(resp.data, function () {
                    if (this.id === _orderId) {
                        orderData.items = this.items;
                        order.calculator(orderData);
                    }
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

order.calculator = function (_orderData) {
    $("div.loading-fast").fadeIn("slow");
    ajax({
        service: '/order/calculator.json',
        data: _orderData,
        loading: false,
        type: 'post',
        contentType: 'json',
        success: function (resp) {
            $("div.loading-fast").hide();
            if (resp.success) {
                order.couponPrice = resp.data.couponPrice;
                $("div[rel=shipmentPrice]").css({"display": "block"});
                if (resp.data.shipmentPrice === -1) {
                    if (resp.data.receiverCityId !== '0' && resp.data.receiverDistrictId !== '0') {
                        $("div[rel=shipmentPrice] .text-right").html("Liên hệ sau");
                        if (resp.data.shipmentService === 'RAPID') {
                            $("div[name=RAPID]").hide();
                            $("input[name=shipmentService][value=FAST]").attr("checked", true).change();
                        }
                        if (resp.data.shipmentService === 'FAST') {
                            $("div[name=FAST]").hide();
                            $("input[name=shipmentService][value=SLOW]").attr("checked", true).change();
                        }
                        if (resp.data.shipmentService === 'SLOW') {
                            $("div[name=FAST]").hide();
                            $("input[name=shipmentService][value=SLOW]").attr("checked", true).change();
                        }
                    }
                } else {
                    $("div[rel=shipmentPrice] .text-right").html('<b>' + resp.data.shipmentPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                }
                //draw item
                $.each(resp.data.items, function () {
                    $("td[for=price" + this.id + "]").html('<b>' + this.itemPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                    $("td[for=totalprice" + this.id + "]").html('<b>' + eval(this.itemPrice * this.quantity).toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');

                });
                //draw coupon
                var html = '';
                if (resp.data.couponId === null || resp.data.couponId === '') {
                    html = '<div class="form-group" rel="couponShow" >\
                                <label class="col-sm-6 control-label">Coupon giảm giá (nếu có):</label>\
                                <div class="col-sm-3 tt2">\
                                    <input name="couponId" type="text" class="form-control" placeholder="Nhập mã">\
                                </div>\
                                <div class="col-sm-3">\
                                    <button class="btn btn-primary btn-coupon" onclick="order.addCoupon(\'' + resp.data.id + '\');" >Sử dụng</button>\
                                </div>\
                            </div>';
                } else {
                    html = '<div class="form-group" rel="couponShow" >\
                                <label class="col-sm-6 control-label">Coupon giảm giá (' + resp.data.couponId + '):</label>\
                                <div class="col-sm-3 tt2">\
                                    <p class="form-control-static text-right">-' + resp.data.couponPrice.toMoney(0, ',', '.') + ' <sup class="price">đ</sup></p>\
                                </div>\
                            </div>';
                }
                $("div[rel=couponShow]").html(html);
                //drawl totalprice
                $("p[rel=totalPrice]").html('<b>' + resp.data.totalPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                $("p[rel=finalPrice]").html('<b>' + resp.data.finalPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                order.drawMarketing(resp.data);
            } else {
                order.coupon = '';
                popup.msg(resp.message);
            }
        }
    });
};

order.getData = function (_orderId) {
    var orderData = {};
    orderData.id = _orderId;
    orderData.sellerId = $("input[name=sellerId]").val();

    orderData.buyerName = $("input[name=buyerName]").val();
    orderData.buyerPhone = $("input[name=buyerPhone]").val();
    orderData.buyerEmail = $("input[name=buyerEmail]").val();
    orderData.buyerAddress = $("input[name=buyerAddress]").val();
    orderData.buyerCityId = $("select[name=buyerCityId]").val();
    orderData.buyerDistrictId = $("select[name=buyerDistrictId]").val();

    orderData.receiverName = $("input[name=receiverName]").val();
    orderData.receiverPhone = $("input[name=receiverPhone]").val();
    orderData.receiverEmail = $("input[name=receiverEmail]").val();
    orderData.receiverAddress = $("input[name=receiverAddress]").val();
    orderData.receiverCityId = $("select[name=receiverCityId]").val();
    orderData.receiverDistrictId = $("select[name=receiverDistrictId]").val();

    orderData.shipmentService = $("input[name=shipmentService]").val();
    $.each($("input[name=shipmentService]"), function () {
        if ($(this).is(":checked")) {
            orderData.shipmentService = $(this).val();
        }
    });


    orderData.paymentMethod = (typeof order.payment.method !== 'undefined') ? order.payment.method : null;
    orderData.paymentType = order.payment.type;
    orderData.couponId = order.coupon;
    orderData.note = $("textarea[name=note]").val();
    orderData.couponPrice = order.couponPrice;

    return orderData;
};

order.drawMarketing = function (_order) {
    if (_order === null || _order.items === null || _order.items === '') {
        return false;
    }
    var discount = false;
    $.each(_order.items, function () {
        if (this.categoryPath.indexOf('2924') > -1) {
            discount = true;
            return false;
        }
    });
    if (_order.cdtDiscountShipment > 0) {
        $("p[rel=cdtshipment]").html('<b>-' + _order.cdtDiscountShipment.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
        $("p[rel=cdtshipment]").parent().parent().css({"display": "block"});
    } else {
        $("p[rel=cdtshipment]").parent().parent().css({"display": "none"});
    }
};

