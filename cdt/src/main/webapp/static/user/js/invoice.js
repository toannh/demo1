invoice = {};
invoice.listItem = [];
invoice.listOrderItem = [];
invoice.couponPrice = "";
invoice.coupon = "";
invoice.listItemSeries = [];
invoice.listOrderSeries = [];
invoice.listItemObjSeries = {};
invoice.listOrderItemSeries = [];
invoice.couponPriceSeries = "";
invoice.couponSeries = "";
invoice.init = function () {
    var html = '<option value="0">Chọn tỉnh / thành phố</option>';
    $.each(citys, function () {
        html += '<option value="' + this.id + '" >' + this.name + '</option>';
    });
    $('select[name=buyerCityId]').html(html);
    $("select[name=receiverCityId]").html(html);
    html = '<option value="0" >Chọn quận / huyện</option>';
    $.each(districts, function () {
        html += '<option value="' + this.id + '" >' + this.name + '</option>';
    });
    $('select[name=buyerDistrictId]').html(html);
    html = '<option value="0" >Chọn quận / huyện</option>';
    $.each(districts, function () {
        html += '<option value="' + this.id + '" >' + this.name + '</option>';
    });
    $("select[name=receiverDistrictId]").html(html);
    $("input[name=syncInvoice]").change(function () {
        if ($(this).is(":checked")) {
            invoice.syncProfile();
        }
    });
    $.each($("[for=buyer]"), function () {
        $(this).change(function () {
            invoice.syncProfile();
        });
    });
    invoice.localChange();
    invoice.syncProfile();
    $('input[name=itemKeyword]').keyup(function (event) {
        $('input[name=itemKeyword]').val($(this).val());
        if (event.keyCode === 13) {
            invoice.searchItem(200);
        }
    });
    setTimeout(function () {
        invoice.searchItem(10);
    }, 2000);
    $("input[name=syncInvoice]").attr("checked", "checked");

    $("select[name=receiverCityId]").change(function () {
        var _orderId = $('input[name=orderId]').val();
        ajax({
            service: '/invoice/getinvoice.json',
            data: {orderId: _orderId},
            loading: false,
            success: function (resp) {
                var orderData = invoice.getData(_orderId);
                orderData.items = resp.data.items;
                invoice.calculator(orderData);
            }
        });
    });
    $("select[name=receiverDistrictId]").change(function () {
        var _orderId = $('input[name=orderId]').val();
        ajax({
            service: '/invoice/getinvoice.json',
            data: {orderId: _orderId},
            loading: false,
            success: function (resp) {
                var orderData = invoice.getData(_orderId);
                orderData.items = resp.data.items;
                invoice.calculator(orderData);
            }
        });
    });
    $('input:radio[name=shipmentService]').change(function () {
        var _orderId = $('input[name=orderId]').val();
        ajax({
            service: '/invoice/getinvoice.json',
            data: {orderId: _orderId},
            loading: false,
            success: function (resp) {
                var orderData = invoice.getData(_orderId);
                orderData.items = resp.data.items;
                invoice.calculator(orderData);
            }
        });
    });
    $('.cdtDiscountShipment').hide();
};
invoice.initSeries = function (classId) {
    //$('.mca-bill-item').css('border', '1px dotted #F58787');
    var html = '<option value="0">Chọn tỉnh / thành phố</option>';
    $.each(citys, function () {
        html += '<option value="' + this.id + '" >' + this.name + '</option>';
    });
    $('#' + classId + ' select[name=buyerCityId]').html(html);
    $('#' + classId + ' select[name=receiverCityId]').html(html);
    html = '<option value="0" >Chọn quận / huyện</option>';
    $.each(districts, function () {
        html += '<option value="' + this.id + '" >' + this.name + '</option>';
    });
    $('#' + classId + ' select[name=buyerDistrictId]').html(html);
    $('#' + classId + ' select[name=receiverDistrictId]').html(html);
    $.each($('#' + classId + ' [for=buyer]'), function () {
        $(this).change(function () {
            invoice.syncProfileSeries(classId);
        });
    });
    $('#' + classId + ' select[name=receiverCityId]').change(function () {
        var arrItemS = [];
        $.each($('#' + classId + ' .itemInvo'), function (i) {
            arrItemS.push($(this).attr("for"));
        });
        ajax({
            service: '/invoice/getorderitemseries.json',
            data: {itemIds: JSON.stringify(arrItemS), orderId: classId},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    var orderItems = invoice.getDataSeries(classId);
                    orderItems.items = [];
                    orderItems.items = resp.data;
                    invoice.calculatorSeries(orderItems);
                }
            }});
    });
    $('#' + classId + ' select[name=receiverDistrictId]').change(function () {
        var arrItemS = [];
        $.each($('#' + classId + ' .itemInvo'), function (i) {
            arrItemS.push($(this).attr("for"));
        });
        ajax({
            service: '/invoice/getorderitemseries.json',
            data: {itemIds: JSON.stringify(arrItemS), orderId: classId},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    var orderItems = invoice.getDataSeries(classId);
                    orderItems.items = [];
                    orderItems.items = resp.data;
                    invoice.calculatorSeries(orderItems);
                }
            }});
    });

    $('#' + classId + ' input:radio[name=shipmentService' + classId + ']').change(function () {
        var arrItemS = [];
        $.each($('#' + classId + ' .itemInvo'), function (i) {
            arrItemS.push($(this).attr("for"));
        });


        ajax({
            service: '/invoice/getorderitemseries.json',
            data: {itemIds: JSON.stringify(arrItemS), orderId: classId},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    var orderItems = invoice.getDataSeries(classId);
                    orderItems.items = [];
                    orderItems.items = resp.data;
                    invoice.calculatorSeries(orderItems);
                }
            }});
    });
    $('.cdtDiscountShipment').hide();
};
invoice.localChange = function () {
    $("select[name=buyerCityId]").change(function () {
        if ($("input[name=syncInvoice]").is(":checked")) {
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
        if ($("input[name=syncInvoice]").is(":checked")) {
            $("select[name=receiverDistrictId]").val($("select[name=buyerDistrictId]").val()).change();
        }
    });
};
invoice.localChangeSeries = function () {
    $("select[name=buyerCityId]").change(function () {
        var divInvo = $(this).parents('.mca-bill-item').attr("invoice-rel");
        if ($("#" + divInvo + " input[name=syncInvoiceSeries]").is(":checked")) {
            $("#" + divInvo + " select[name=receiverCityId]").val($(this).val()).change();
        }
        var html = '<option value="0" >Chọn quận / huyện</option>';
        $.each(districts, function () {
            if (this.cityId === $("#" + divInvo + " select[name=buyerCityId]").val()) {
                html += '<option value="' + this.id + '" >' + this.name + '</option>';
            }
        });
        $("#" + divInvo + " select[name=buyerDistrictId]").html(html);
    });
    $("select[name=receiverCityId]").change(function () {
        var divInvo = $(this).parents('.mca-bill-item').attr("invoice-rel");
        var html = '<option value="0" >Chọn quận / huyện</option>';
        $.each(districts, function () {
            if (this.cityId === $("#" + divInvo + " select[name=receiverCityId]").val()) {
                html += '<option value="' + this.id + '" >' + this.name + '</option>';
            }
        });
        $("#" + divInvo + " select[name=receiverDistrictId]").html(html);


    });
    $("select[name=buyerDistrictId]").change(function () {
        var divInvo = $(this).parents('.mca-bill-item').attr("invoice-rel");
        if ($("#" + divInvo + " input[name=syncInvoiceSeries]").is(":checked")) {
            $("#" + divInvo + " select[name=receiverDistrictId]").val($("#" + divInvo + " select[name=buyerDistrictId]").val()).change();
        }
    });
    $("input[name=syncInvoiceSeries]").click(function () {
        var divInvo = $(this).parents('.mca-bill-item').attr("invoice-rel");
        if ($("#" + divInvo + " input[name=syncInvoiceSeries]").is(":checked")) {
            $("#" + divInvo + " .cityInvoice").hide();
        } else {
            $("#" + divInvo + " .cityInvoice").show();
        }
    });

};

invoice.syncProfile = function () {
    if ($("input[name=syncInvoice]").is(":checked")) {
        $.each($("[for=buyer]"), function () {
            var name = $(this).attr("name");
            $("[for=" + name + "]").val($(this).val()).change();
        });
        $("select[name=receiverCityId]").val($("select[name=buyerCityId]").val()).change();
        ;
        $("select[name=receiverDistrictId]").val($("select[name=buyerDistrictId]").val()).change();
        ;
    }
};
invoice.selectItem = function () {
    ajax({
        service: '/item/getitems.json',
        data: {ids: JSON.stringify(invoice.listItem)},
        loading: false,
        done: function (resp) {
            var htmlItem = '';
            var dataItem = resp.data.items;
            var _orderId = $('input[name=orderId]').val();
            var orderData = invoice.getData(_orderId);
            orderData.items = [];
            var itemids = [];
            $.each(dataItem, function (i) {
                itemids.push(dataItem[i].id);
            });
            ajax({
                service: '/invoice/getorderitem.json',
                data: {itemIds: JSON.stringify(itemids)},
                loading: false,
                done: function (resp) {
                    if (resp.success) {
                        orderData.items = resp.data;
                        invoice.listOrderItem = orderData.items;
                        invoice.calculator(orderData);
                        $.each(dataItem, function (i) {
                            var orderItemId = '';
                            var orderQuantity = '';
                            $.each(resp.data, function (j) {
                                if (dataItem[i].id === resp.data[j].itemId) {
                                    orderItemId = resp.data[j].id;
                                    orderQuantity = resp.data[j].quantity;
                                }
                            });
                            var freeShipping = '';
                            var giftItem = '';
                            if (dataItem[i].gift) {
                                giftItem = 'Có quà tặng <span class="tool-tip" data-toggle="tooltip" data-placement="top" title="" data-original-title="' + dataItem[i].giftDetail + '">' +
                                        '<span class="glyphicon glyphicon-question-sign"></span>' +
                                        '</span>';
                            } else {
                                giftItem = 'Không có quà tặng';
                            }
                            if (typeof dataItem[i].shipmentType === 'undefined' || dataItem[i].shipmentType === null || dataItem[i].shipmentType === 'AGREEMENT') {
                                freeShipping = 'Không rõ phí';
                            }
                            if (dataItem[i].shipmentType === 'FIXED' && dataItem[i].shipmentPrice === 0) {
                                freeShipping = 'Miễn phí';
                            }
                            if (dataItem[i].shipmentType === 'FIXED' && dataItem[i].shipmentPrice > 0) {
                                freeShipping = 'Cố định: ' + parseFloat(dataItem[i].shipmentPrice).toMoney(0, ',', '.') + '<sup class="u-price">đ</sup>';
                            }
                            if (dataItem[i].shipmentType === 'BYWEIGHT') {
                                freeShipping = 'Linh hoạt theo địa chỉ người mua';
                            }
                            var priceNumber = 0;
                            if (dataItem[i].listingType === 'BUYNOW') {
                                priceNumber = textUtils.sellPrice(dataItem[i].sellPrice, dataItem[i].discount, dataItem[i].discountPrice, dataItem[i].discountPercent);
                            }
                            var linkItem = baseUrl + "/san-pham/" + dataItem[i].id + "/" + textUtils.createAlias(dataItem[i].name) + ".html";
                            htmlItem += '<div class="row form-reset-col clearfix itemInvo' + dataItem[i].id + '">' +
                                    '<div class="col-lg-9 col-md-9 col-sm-8 col-xs-8 pull-left">' +
                                    '<span class="img-product-bill">' +
                                    '<a href="javascript:;"><img src="' + dataItem[i].images[0] + '" class="img-responsive"></a>' +
                                    '<i class="icon-remove-img" onclick="invoice.removeItemSelect(\'' + dataItem[i].id + '\')"></i>' +
                                    '</span>' +
                                    '<a href="' + linkItem + '" target="_blank">' + dataItem[i].name + '</a>' +
                                    '<p>' + giftItem + '</p>' +
                                    '<p>' + freeShipping + '</p>' +
                                    '</div>' +
                                    '<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 pull-right">' +
                                    '<p><strong>' + priceNumber + ' <sup class="u-price">đ</sup></strong> <br>x</p>' +
                                    '<p><input itemquantity="" type="number" min="1" value="' + orderQuantity + '" class="form-control" onchange="invoice.updateItemInvoice(' + _orderId + ',' + orderItemId + ');"></p>' +
                                    '</div>' +
                                    '</div>';
                        });
                        $('#itemSelectBill').html(htmlItem);
                        $('.tool-tip').tooltip();
                    }
                }
            });

        }
    });
};
invoice.removeItemSelect = function (id) {
    var index = invoice.listItem.indexOf(id);
    if (index > -1) {
        invoice.listItem.splice(index, 1);
    }
    invoice.selectItem();
    $(".invoiceCheckBox[for=" + id + "]").attr("checked", false);
};
invoice.searchItem = function (size) {
    var itemSearch = {};
    itemSearch.pageIndex = 1;
    itemSearch.status = 1;
    if (typeof size !== 'underfine' && size !== null && size !== '') {
        itemSearch.pageSize = size;
    } else {
        itemSearch.pageSize = 200;
    }

    itemSearch.keyword = $('input[name=itemKeyword]').val().trim();
    ajax({
        service: '/item/search.json',
        loading: false,
        data: itemSearch,
        type: 'GET',
        done: function (resp) {
            if (resp.success) {
                var htmlItem = '';
                var dataItem = resp.data.products;
                $.each(dataItem, function (i) {
                    var linkItem = baseUrl + "/san-pham/" + dataItem[i].id + "/" + textUtils.createAlias(dataItem[i].name) + ".html";
                    var priceNumber = 0;
                    if (dataItem[i].listingType === 'BUYNOW') {
                        priceNumber = textUtils.sellPrice(dataItem[i].sellPrice, dataItem[i].discount, dataItem[i].discountPrice, dataItem[i].discountPercent);
                    }
                    htmlItem += '<li class="clearfix">' +
                            '<input type="checkbox" class="invoiceCheckBox" for="' + dataItem[i].id + '">' +
                            '<div class="product-item-choose">' +
                            '<span class="img-product-bill-small">' +
                            '<a href="javascript:;"><img src="' + dataItem[i].images[0] + '" class="img-responsive" title="' + dataItem[i].name + '" alt=""></a>' +
                            '</span>' +
                            '<a href="' + linkItem + '" target="_blank" class="title-product-choose" title="' + dataItem[i].name + '">' + dataItem[i].name + '</a>' +
                            '<p>Giá: <strong>' + priceNumber + ' <sup class="u-price">đ</sup></strong></p>' +
                            '</div>' +
                            '</li>';
                });
                $('#htmlItemSearch').html(htmlItem);
                $('.invoiceCheckBox').each(function () {
                    $(this).change(function () {
                        var id = $(this).attr("for");
                        if ($(this).is(":checked")) {
                            if (invoice.listItem.indexOf(id) === -1) {
                                invoice.listItem.push(id);
                            }

                        } else {
                            var index = invoice.listItem.indexOf(id);
                            if (index > -1) {
                                invoice.listItem.splice(index, 1);
                            }
                        }
                        invoice.selectItem();
                    });

                });
                $.each($('.invoiceCheckBox'), function () {
                    if ($(this).is(":checked")) {
                        var idItem = $(this).attr("for");
                        invoice.listItem.push(idItem);
                    }
                });
                if ((typeof (invoice.listItem) != 'undefined') && invoice.listItem.length > 0) {
                    $.each(invoice.listItem, function () {
                        $(".invoiceCheckBox[for=" + this + "]").attr("checked", true);
                    });
                }

            }
        }
    });
};
invoice.calculator = function (_orderData) {

    ajax({
        service: '/order/calculator.json',
        data: _orderData,
        loading: false,
        type: 'post',
        contentType: 'json',
        success: function (resp) {
            $("div.loading-fast").hide();
            if (resp.success) {
                invoice.couponPrice = resp.data.couponPrice;
                var orderItems = resp.data.items;
                $.each(orderItems, function (i) {
                    $('.itemInvo' + orderItems[i].itemId + ' input[type=number]').attr("itemquantity", orderItems[i].id);
                });
                $("span[rel=cdtDiscountShipment]").html("-");
                $("span[rel=totalPrice]").html('<b>' + resp.data.totalPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                if (resp.data.shipmentPrice === -1) {
                    $("span[rel=shipmentPrice]").html('-');
                    $("span[rel=shipmentPriceF]").html('-');
                    if (resp.data.receiverCityId !== '0' && resp.data.receiverDistrictId !== '0') {
                        $("span[rel=shipmentPrice]").html("Liên hệ sau");
                        if (resp.data.shipmentService === 'RAPID') {
                            $("input[name=shipmentService][value=FAST]").attr("checked", true).change();
                        }
                        if (resp.data.shipmentService === 'FAST') {
                            $("input[name=shipmentService][value=SLOW]").attr("checked", true).change();
                        }
                        if (resp.data.shipmentService === 'SLOW') {
                            $("input[name=shipmentService][value=SLOW]").attr("checked", true).change();
                        }
                    } else {
                        if (resp.data.shipmentService === 'SLOW') {
                            $("span[rel=shipmentPrice]").html("Chưa rõ phí");
                        }
                        if (resp.data.shipmentService === 'FAST') {
                            $("span[rel=shipmentPriceF]").html("Chưa rõ phí");
                        }
                        $("span[rel=finalPrice]").html('<b>' + parseFloat((resp.data.finalPrice === 0) ? 0 : (resp.data.finalPrice + 1)).toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                    }
                } else {
                    $("span[rel=shipmentPrice]").html('-');
                    $("span[rel=shipmentPriceF]").html('-');
                    if (resp.data.shipmentService === 'SLOW') {
                        $("span[rel=shipmentPrice]").html('<b>' + resp.data.shipmentPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                    }
                    if (resp.data.shipmentService === 'FAST') {
                        $("span[rel=shipmentPriceF]").html('<b>' + resp.data.shipmentPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                    }
                }
                if (resp.data.receiverCityId !== '0' && resp.data.receiverDistrictId !== '0') {
                    $("span[rel=finalPrice]").html('<b>' + parseFloat((resp.data.finalPrice == 0) ? 0 : (resp.data.finalPrice)).toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                } else {
                    $("span[rel=finalPrice]").html('<b>' + resp.data.totalPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                    if (resp.data.shipmentService === 'SLOW') {
                        $("span[rel=shipmentPrice]").html("Chưa rõ phí");
                    }
                    if (resp.data.shipmentService === 'FAST') {
                        $("span[rel=shipmentPriceF]").html("Chưa rõ phí");
                    }
                }
                $('.cdtDiscountShipment').hide();
                if (resp.data.cdtDiscountShipment !== null && resp.data.cdtDiscountShipment > 0) {
                    $('.cdtDiscountShipment').show();
                    $("span[rel=cdtDiscountShipment]").html('<b>-' + resp.data.cdtDiscountShipment.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                }
                var htmlC = '';
                if (resp.data.couponId !== null && resp.data.couponId !== '') {
                    htmlC = resp.data.couponPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup>';
                    $(".couponShow").html(htmlC);
                }


            } else {
                invoice.coupon = '';
                popup.msg(resp.message);
            }
        }
    });
};
invoice.calculatorSeries = function (_orderData) {
    ajax({
        service: '/invoice/calculatorinvoice.json',
        data: _orderData,
        loading: false,
        type: 'post',
        contentType: 'json',
        success: function (resp) {
            $("div.loading-fast").hide();
            if (resp.success) {
                var orders = resp.data;

                $.each(orders, function (i) {
                    var htmlC = '';
                    if (orders[i].couponId !== null && orders[i].couponId !== '') {
                        htmlC = '-' + orders[i].couponPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup>';
                        $("#" + orders[i].id + " .couponShow").html(htmlC);
                    }
                    $("#" + orders[i].id + " span[rel=totalPrice]").html('<b>' + orders[i].totalPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                    if (orders[i].shipmentPrice === -1) {
                        $("#" + orders[i].id + " span[rel=shipmentPrice]").html('-');
                        if (orders[i].receiverCityId !== '0' && orders[i].receiverDistrictId !== '0') {
                            $("#" + orders[i].id + " span[rel=shipmentPrice]").html("Liên hệ sau");
                            if (orders[i].shipmentService === 'RAPID') {
                                $("#" + orders[i].id + " input[name=shipmentService" + orders[i].id + "][value=FAST]").attr("checked", true).change();
                            }
                            if (orders[i].shipmentService === 'FAST') {
                                $("#" + orders[i].id + " input[name=shipmentService" + orders[i].id + "][value=SLOW]").attr("checked", true).change();
                            }
                            if (orders[i].shipmentService === 'SLOW') {
                                $("#" + orders[i].id + " input[name=shipmentService" + orders[i].id + "][value=SLOW]").attr("checked", true).change();
                            }
                        } else {
                            if (orders[i].shipmentService === 'SLOW') {
                                $("#" + orders[i].id + " span[rel=shipmentPrice]").html("Chưa rõ phí");
                            }
                            if (orders[i].shipmentService === 'FAST') {
                                $("#" + orders[i].id + " span[rel=shipmentPrice]").html("Chưa rõ phí");
                            }
                            $("#" + orders[i].id + " span[rel=finalPrice]").html('<b>' + parseFloat((orders[i].finalPrice == 0) ? 0 : (orders[i].finalPrice)).toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                        }
                    } else {
                        $("#" + orders[i].id + " span[rel=shipmentPrice]").html('-');
                        if (orders[i].shipmentService === 'SLOW') {
                            $("#" + orders[i].id + " span[rel=shipmentPrice]").html('<b>' + orders[i].shipmentPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                        }
                        if (orders[i].shipmentService === 'FAST') {
                            $("#" + orders[i].id + " span[rel=shipmentPrice]").html('<b>' + orders[i].shipmentPrice.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                        }
                    }
                    if (orders[i].receiverCityId !== '0' && orders[i].receiverDistrictId !== '0') {
                        $("#" + orders[i].id + " span[rel=finalPrice]").html('<b>' + parseFloat((orders[i].finalPrice == 0) ? 0 : (orders[i].finalPrice)).toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                    } else {
                        $("#" + orders[i].id + " span[rel=finalPrice]").html('<b>' + parseFloat((orders[i].finalPrice == 0) ? 0 : (orders[i].finalPrice)).toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                        if (orders[i].shipmentService === 'SLOW') {
                            $("#" + orders[i].id + " span[rel=shipmentPrice]").html("Chưa rõ phí");
                        }
                        if (orders[i].shipmentService === 'FAST') {
                            $("#" + orders[i].id + " span[rel=shipmentPrice]").html("Chưa rõ phí");
                        }
                    }
                    $("#" + orders[i].id + " input[name=couponPrice]").val(orders[i].couponPrice);
                    $("#" + orders[i].id + " input[name=couponPrice]").val(orders[i].couponPrice);

                    if (orders[i].cdtDiscountShipment !== null && orders[i].cdtDiscountShipment > 0) {
                        $("#" + orders[i].id + " .cdtDiscountShipment").show();
                        $("#" + orders[i].id + " span[rel=cdtDiscountShipment]").html('<b>-' + orders[i].cdtDiscountShipment.toMoney(0, ',', '.') + '<sup class="price">đ</sup></b>');
                    }
                });

                invoice.listOrderSeries = orders;
            } else {
                invoice.coupon = '';
                popup.msg(resp.message);
            }
        }
    });
};
invoice.updateItemInvoice = function (_orderId, _orderItemId) {
    var quantity = $("[itemquantity=" + _orderItemId + "]").val() > 0 ? $("[itemquantity=" + _orderItemId + "]").val() : 1;
    ajax({
        service: '/invoice/updateinvoice.json',
        data: {orderId: _orderId, orderitemid: _orderItemId, quantity: quantity},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                var orderData = invoice.getData(_orderId);
                orderData.items = resp.data;
                invoice.calculator(orderData);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
invoice.updateItemInvoiceSeries = function (_orderId, _orderItemId) {
    var quantity = $("#" + _orderId + " [itemquantity=" + _orderItemId + "]").val() > 0 ? $("#" + _orderId + " [itemquantity=" + _orderItemId + "]").val() : 1;
    ajax({
        service: '/invoice/updateinvoiceseries.json',
        data: {orderId: _orderId, orderitemid: _orderItemId, quantity: quantity},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                var orderData = invoice.getDataSeries(_orderId);
                orderData.items = [];
                orderData.items = resp.data;
                invoice.calculatorSeries(orderData);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
invoice.getData = function (_orderId) {
    var orderData = {};
    orderData.id = _orderId;
    orderData.sellerId = $("input[name=sellerId]").val();
    orderData.ignoreCustomer = true;
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
    orderData.paymentMethod = 'NONE';
    //orderData.paymentType = order.payment.type;     orderData.couponPrice = invoice.couponPrice;
    orderData.couponId = invoice.coupon;
    return orderData;
};
invoice.getDataSeries = function (_orderId) {
    var orderData = {};
    orderData.id = _orderId;
    orderData.sellerId = userId;
    orderData.ignoreCustomer = true;
    orderData.buyerName = $("#" + _orderId + " input[name=buyerName]").val();
    orderData.buyerPhone = $("#" + _orderId + " input[name=buyerPhone]").val();
    orderData.buyerEmail = $("#" + _orderId + " input[name=buyerEmail]").val();
    orderData.buyerAddress = $("#" + _orderId + " input[name=buyerAddress]").val();
    orderData.buyerCityId = $("#" + _orderId + " select[name=buyerCityId]").val();
    orderData.buyerDistrictId = $("#" + _orderId + " select[name=buyerDistrictId]").val();
    orderData.receiverName = $("#" + _orderId + " input[name=receiverName]").val();
    orderData.receiverPhone = $("#" + _orderId + " input[name=receiverPhone]").val();
    orderData.receiverEmail = $("#" + _orderId + " input[name=receiverEmail]").val();
    orderData.receiverAddress = $("#" + _orderId + " input[name=receiverAddress]").val();
    orderData.receiverCityId = $("#" + _orderId + " select[name=receiverCityId]").val();
    orderData.receiverDistrictId = $("#" + _orderId + " select[name=receiverDistrictId]").val();
    orderData.shipmentService = $("#" + _orderId + " input[name=shipmentService" + _orderId + "]").val();
    $.each($("#" + _orderId + " input[name=shipmentService" + _orderId + "]"), function () {
        if ($(this).is(":checked")) {
            orderData.shipmentService = $(this).val();
        }
    });
    orderData.paymentMethod = 'NONE';
    orderData.couponPrice = $("#" + _orderId + " input[name=couponPrice]").val();
    orderData.couponId = $("#" + _orderId + " input[name=couponId]").val();
    return orderData;
};
invoice.addCoupon = function (_orderId) {
    invoice.coupon = $("input[name=couponId]").val();
    if (invoice.coupon === '') {
        popup.msg("Bạn chưa điền mã coupon");
        return false;
    }
    ajax({
        service: '/invoice/updateinvoice.json',
        data: {orderId: _orderId, orderitemid: '', quantity: 0},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                var orderData = invoice.getData(_orderId);
                orderData.items = resp.data;
                invoice.calculator(orderData);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
invoice.create = function (_orderId) {
    ajax({
        service: '/invoice/getinvoice.json',
        data: {orderId: _orderId},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                var orderData = invoice.getData(_orderId);
                orderData.items = resp.data.items;
                if (orderData.items === null || orderData.items.length <= 0) {
                    popup.msg("Bạn chưa chọn sản phẩm nào");
                    return 0;
                }
                ajax({
                    service: '/invoice/create.json',
                    data: orderData,
                    loading: false,
                    type: 'post',
                    contentType: 'json',
                    success: function (resp) {
                        if (resp.success) {
                            popup.msg(resp.message, function () {
                                location.reload();
                            });

                        } else {
                            $("._error").removeClass('has-error');
                            $(".help-block").remove();
                            $.each(resp.data, function (key, message) {
                                $("[name=" + key + "]").after('<div class="help-block">' + message + '</div>');
                                //                                $("[name=" + key + "]").attr("placeholder", message);
                                $("[name=" + key + "]").parent().parent().addClass('has-error _error');
                            });
                        }

                    }
                });
            } else {
                popup.msg("Bạn chưa chọn sản phẩm nào");
            }
        }
    });
};
invoice.createSeries = function () {
    ajax({
        service: '/invoice/gencodeinvoice.json',
        loading: false,
        success: function (resp) {
            popup.open('popup-add', 'Tạo hoá đơn hàng loạt', template('/user/tpl/invoicecreateseries.tpl', resp), [
                {
                    title: 'Thêm mới',
                    style: 'btn-primary',
                    fn: function () {
                        ajax({
                            service: '/invoice/gencodeinvoice.json',
                            loading: false,
                            done: function (resp) {
                                $('.modal-body').append(template('/user/tpl/invoicecreateseries.tpl', resp));
                                invoice.initSeries(resp.data);
                                invoice.localChangeSeries();
                                invoice.syncProfileSeries(resp.data);
                            }
                        });
                    }},
                {
                    title: 'Tạo hóa đơn',
                    style: 'btn-danger',
                    fn: function () {
                        var ordersTmp = invoice.listOrderSeries;
                        var ordersVip = [];
                        $.each(ordersTmp, function (i) {
                            var order = invoice.getDataSeries(ordersTmp[i].id);
                            order.items = ordersTmp[i].items;
                            if (order.items.length > 0) {
                                ordersVip.push(order);
                            } else {
                                var htmlError = '<div class="col-md-12">' +
                                        '<div class="cdt-message bg-danger">' +
                                        'Chưa chọn sản phẩm nào!' +
                                        '</div>' +
                                        '</div>';
                                $('#' + ordersTmp[i].id + ' .message_invoice').html(htmlError);
                            }

                        });

                        ajax({
                            service: '/invoice/createseries.json',
                            data: ordersVip,
                            contentType: 'json',
                            type: 'post',
                            loading: false,
                            done: function (resp) {
                                if (resp.success) {
                                    $("._error").removeClass('has-error');
                                    $(".help-block").remove();
                                    var errors = resp.data;

                                    $.each(errors, function (k, v) {
                                        if (!errors[k].success) {
                                            var htmlError = '<div class="col-md-12">' +
                                                    '<div class="cdt-message bg-danger">' +
                                                    'Hoá đơn bị lỗi!' +
                                                    '</div>' +
                                                    '</div>';
                                            $('#' + k + ' .message_invoice').html(htmlError);

                                        } else {
                                            var htmlError = '<div class="col-md-12">' +
                                                    '<div class="cdt-message bg-success">' +
                                                    'Đã tạo thành công!' +
                                                    '</div>' +
                                                    '</div>';
                                            $('#' + k + ' .message_invoice').html(htmlError);
                                            var orderArray = invoice.listOrderSeries;
                                            var orderMP = [];
                                            $.each(orderArray, function (i) {
                                                if (orderArray[i] !== null && orderArray.length > 0 && orderArray[i].id !== k) {
                                                    orderMP.push(orderArray[i]);
                                                }
                                            });
                                            invoice.listOrderSeries = orderMP;

                                        }
                                        $.each(errors[k].data, function (key, message) {
                                            $("#" + k + " [name=" + key + "]").after('<div class="help-block">' + message + '</div>');
                                            $("#" + k + " [name=" + key + "]").parent().addClass('has-error _error');
                                        });
                                    });

                                } else {


                                }

                            }
                        });
                        $.each($('.mca-bill-item'), function (i) {
                            var classId = $(this).attr("invoice-rel");
                            var arrItemS = [];
                            $.each($('#' + classId + ' .itemInvo'), function (i) {
                                arrItemS.push($(this).attr("for"));
                            });
                            if (typeof arrItemS === 'undefined' || arrItemS.length <= 0) {
                                var htmlError = '<div class="col-md-12">' +
                                        '<div class="cdt-message bg-danger">' +
                                        'Bạn chưa chọn sản phẩm nào!' +
                                        '</div>' +
                                        '</div>';
                                $('#' + classId + ' .message_invoice').html(htmlError);
                            }
                        });
                    }
                }
            ], 'modal-lg', true);
            invoice.initSeries(resp.data);
            invoice.localChangeSeries();

        }
    });
};
var varTmp = null;
invoice.searchItemCustomer = function (size, classInvoice) {
    if (varTmp !== null) {
        clearTimeout(varTmp);
    }
    varTmp = setTimeout(function () {
        var itemSearch = {};
        itemSearch.pageIndex = 1;
        itemSearch.status = 1;
        if (typeof size !== 'underfine' && size !== null && size !== '') {
            itemSearch.pageSize = size;
        } else {
            itemSearch.pageSize = 20;
        }

        itemSearch.keyword = $('#' + classInvoice + ' input[name=itemKeyword]').val().trim();
        ajax({
            service: '/item/search.json',
            loading: false,
            data: itemSearch,
            type: 'GET',
            done: function (resp) {
                if (resp.success) {
                    var htmlItem = '';
                    var htmlItemUl = '';
                    var arrItem = [];
                    var pItem = {};
                    var dataItem = resp.data.products;
                    $.each(dataItem, function (i) {
                        var priceNumber = 0;
                        if (dataItem[i].listingType === 'BUYNOW') {
                            priceNumber = textUtils.sellPrice(dataItem[i].sellPrice, dataItem[i].discount, dataItem[i].discountPrice, dataItem[i].discountPercent);
                        }
                        //htmlItemUl += '<li for="' + dataItem[i].id + '"><a href="#">' + dataItem[i].name + '</a></li>';
                        htmlItemUl += '<li for="' + dataItem[i].id + '">' +
                                '<a>' +
                                '<img class="pull-left" src="' + dataItem[i].images[0] + '" alt="img" width="32" height="32">' +
                                '<b class="ca-title">' + dataItem[i].name + '</b>' +
                                '<span class="ca-price">' + priceNumber + ' <sup class="u-price">đ</sup></span>' +
                                '</a>' +
                                '</li>';
                    });
                    //$('#itemSearch' + classInvoice).html(htmlItem);
                    $('#' + classInvoice + ' .compare-autosearch').show();
                    htmlItemUl = '<ul>' + htmlItemUl + '</ul>';
                    $('#' + classInvoice + ' .compare-autosearch').html(htmlItemUl);
                    if (dataItem.length <= 0) {
                        // $('#itemSearch' + classInvoice).html('<span style="color:red">Không tìm thấy sản phẩm nào</span>');
                    }
                    $.each($('#' + classInvoice + ' .itemInvo'), function (i) {
                        arrItem.push($(this).attr("for"));
                    });
                    $('.compare-autosearch ul li').click(function () {
                        var itemId = $(this).attr("for");
                        $.each(dataItem, function (i) {
                            var fag = true;
                            if (arrItem.length > 0) {
                                var index = arrItem.indexOf(itemId);
                                if (index > -1) {
                                    fag = false;
                                }
                            }
                            if (itemId !== null && itemId === dataItem[i].id && fag) {
                                pItem = dataItem[i];
                                var linkItem = baseUrl + "/san-pham/" + dataItem[i].id + "/" + textUtils.createAlias(dataItem[i].name) + ".html";
                                var priceNumber = 0;
                                var shipmentType = '';
                                if (dataItem[i].listingType === 'BUYNOW') {
                                    priceNumber = textUtils.sellPrice(dataItem[i].sellPrice, dataItem[i].discount, dataItem[i].discountPrice, dataItem[i].discountPercent);
                                }
                                if (dataItem[i].shipmentType === null || dataItem[i].shipmentType === 'AGREEMENT') {
                                    shipmentType = 'Phí vận chuyển: Tự thỏa thuận';
                                }
                                if (dataItem[i].shipmentType === 'FIXED' && dataItem[i].shipmentPrice === 0) {
                                    shipmentType = 'Miễn phí';
                                }
                                if (dataItem[i].shipmentType === 'FIXED' && dataItem[i].shipmentPrice > 0) {
                                    shipmentType = parseFloat(dataItem[i].shipmentPrice).toMoney(0, ',', '.') + '<sup class="u-price">đ</sup></b> Phí vận chuyển';
                                }
                                if (dataItem[i].shipmentType === 'BYWEIGHT') {
                                    shipmentType = 'Phí vận chuyển: Linh hoạt theo địa chỉ người mua';
                                }
                                var giftItem = '';
                                if (dataItem[i].gift) {
                                    giftItem = 'Có quà tặng <span class="tool-tip" data-toggle="tooltip" data-placement="top" title="" data-original-title="' + dataItem[i].giftDetail + '">' +
                                            '<span class="glyphicon glyphicon-question-sign"></span>' +
                                            '</span>';
                                } else {
                                    giftItem = 'Không có quà tặng';
                                }
                                htmlItem = '<div class="row form-reset-col clearfix itemInvo" for="' + dataItem[i].id + '">' +
                                        '<div class="col-md-9 col-sm-8">' +
                                        '<input name="itemquantity" itemquantity="' + dataItem[i].id + '" type="text" class="mca-number form-control" value="1" onchange="invoice.updateItemInvoiceSeries(' + classInvoice + ',' + dataItem[i].id + ');">' +
                                        '<span class="img-product-bill">' +
                                        '<a href="' + linkItem + '" target="_blank"><img class="img-responsive" width="75" height="75" src="' + dataItem[i].images[0] + '"></a>' + '<i class="icon-remove-img" onclick="invoice.removeItemSelectSeries(' + classInvoice + ',' + dataItem[i].id + ')"></i>' +
                                        '</span>' +
                                        '<a href="' + linkItem + '" target="_blank">' + dataItem[i].name + '</a>' +
                                        '<p>' + giftItem + '</p>' +
                                        '<p>' + shipmentType + '</p>' +
                                        '</div>' +
                                        '<div class="col-md-3 col-sm-4 text-right">' +
                                        '<p><strong>' + priceNumber + '<sup class="u-price">đ</sup></strong></p>' +
                                        '</div>' +
                                        '</div><!-- row -->';
                                return 0;
                            }
                        });

                        //invoice.calculatorSeries(orderItems);
                        $('#' + classInvoice + ' .compare-autosearch').hide();
                        $('#itemSearch' + classInvoice).append(htmlItem);
                        $('.tool-tip').tooltip();
                        var arrItemS = [];
                        $.each($('#' + classInvoice + ' .itemInvo'), function (i) {
                            arrItemS.push($(this).attr("for"));
                        });
                        ajax({
                            service: '/invoice/getorderitemseries.json', data: {itemIds: JSON.stringify(arrItemS), orderId: classInvoice},
                            loading: false,
                            done: function (resp) {
                                if (resp.success) {
                                    var orderItems = invoice.getDataSeries(classInvoice);
                                    orderItems.items = [];
                                    orderItems.items = resp.data;
                                    invoice.calculatorSeries(orderItems);
                                } else {
                                    popup.msg(resp.message);
                                }
                            }
                        });
                        $('input[name=itemKeyword]').val('');
                    });
                    $('.compare-autosearch').hover(function () {
                    }, function () {
                        $('#' + classInvoice + ' .compare-autosearch').hide();
                    });


                }
            }
        });
    }, 500);
};
invoice.removeOrderInvoice = function (id) {
    popup.confirm("Bạn có chắc muốn xóa đơn hàng này không?", function () {
        $('#' + id).remove();
        if ($('.mca-bill-item').length === 0) {
            popup.close('popup-add');
        }
    });
};
invoice.syncProfileSeries = function (classInvo) {
    if ($('#' + classInvo + ' input[name=syncInvoiceSeries]').is(":checked")) {
        $.each($("#" + classInvo + " [for=buyer]"), function () {
            var name = $(this).attr("name");
            $("#" + classInvo + " [for=" + name + "]").val($(this).val()).change();
        });
        $("#" + classInvo + " select[name=receiverCityId]").val($("#" + classInvo + " select[name=buyerCityId]").val()).change();
        ;
        $("#" + classInvo + " select[name=receiverDistrictId]").val($("#" + classInvo + " select[name=buyerDistrictId]").val()).change();
        ;
    }
};
invoice.getInvoiceSeries = function (itemids, orderId) {
    ajax({
        service: '/invoice/getorderitemseries.json',
        data: {itemIds: JSON.stringify(itemids), orderId: orderId},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                return resp.data;

            }
        }
    });
};
invoice.removeItemSelectSeries = function (classInvoice, itemid) {
    $('#' + classInvoice + ' .itemInvo[for=' + itemid + ']').remove();
    setTimeout(function () {
        var arrItemS = [];
        $.each($('#' + classInvoice + ' .itemInvo'), function (i) {
            arrItemS.push($(this).attr("for"));
        });
        ajax({
            service: '/invoice/getorderitemseries.json',
            data: {itemIds: JSON.stringify(arrItemS), orderId: classInvoice},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    var orderItems = invoice.getDataSeries(classInvoice);
                    orderItems.items = [];
                    orderItems.items = resp.data;
                    invoice.calculatorSeries(orderItems);
                }
            }});
    }, 500);
};
invoice.addCouponSeries = function (_orderId) {
    var coupon = $("#" + _orderId + " input[name=couponId]").val();
    if (coupon === '') {
        popup.msg("Bạn chưa điền mã coupon");
        return false;
    }
    ajax({
        service: '/invoice/updateinvoiceseries.json',
        data: {orderId: _orderId, orderitemid: '', quantity: 0},
        loading: false,
        success: function (resp) {
            if (resp.success) {
                var orderData = invoice.getDataSeries(_orderId);
                orderData.items = resp.data;
                orderData.couponId = coupon;
                invoice.calculatorSeries(orderData);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};