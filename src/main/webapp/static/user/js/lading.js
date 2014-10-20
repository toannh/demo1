var lading = {};

lading.action = function (_type) {
    var _orderIds = [];
    $.each($('input[for=checkall]'), function () {
        if ($(this).is(":checked"))
            _orderIds.push($(this).val());
    });
    if (_type === 'cod') {
        lading.showCod(_orderIds);
    }
    if (_type === 'ship') {
        lading.showShip(_orderIds);
    }
};

lading.showCod = function (_orderIds) {
    if (_orderIds === null || _orderIds === '' || _orderIds.length === 0) {
        popup.msg("Bạn cần chọn đơn hàng để thực hiện chức năng duyệt vận đơn");
        return false;
    }
    ajax({
        service: '/order/findbyids.json',
        data: _orderIds,
        type: 'post',
        loading: false,
        contentType: 'json',
        success: function (resp) {
            if (resp.success) {
                popup.open('popup-lading', 'Duyệt vận đơn CoD hàng loạt', template('/user/tpl/lading/cod.tpl', {data: resp.data, cod: true}), [
                    {
                        title: 'Duyệt hàng loạt',
                        style: 'btn-danger loading',
                        fn: function () {
                            lading.create('cod');
                        }
                    },
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function () {
                            popup.close('popup-lading');
                        }
                    }
                ], 'modal-lg');

                lading.drawlInfo();
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
lading.showShip = function (_orderIds) {
    if (_orderIds === null || _orderIds === '' || _orderIds.length === 0) {
        popup.msg("Bạn cần chọn đơn hàng để thực hiện chức năng duyệt vận đơn");
        return false;
    }
    ajax({
        service: '/order/findbyids.json',
        data: _orderIds,
        type: 'post',
        loading: false,
        contentType: 'json',
        success: function (resp) {
            if (resp.success) {
                popup.open('popup-lading', 'Duyệt vận đơn vận chuyển hàng loạt', template('/user/tpl/lading/cod.tpl', {data: resp.data, cod: false}), [
                    {
                        title: 'Duyệt hàng loạt',
                        style: 'btn-danger loading',
                        fn: function () {
                            lading.create();
                        }
                    },
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function () {
                            popup.close('popup-lading');
                        }
                    }
                ], 'modal-lg');

                lading.drawlInfo();
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

lading.drawlInfo = function () {
    setTimeout(function () {
        //loading city district
        var html = '<option value="0" >Chọn tỉnh/TP</option>';
        $.each(citys, function () {
            html += "<option value=" + this.id + " >" + this.name + "</option>";
        });
        $("select[data-rel=cityId]").html(html);

        $("select[data-rel=cityId]").change(function () {
            lading.changeDistrict($("select[data-rel=cityId]").val(), "0");
        });
        //Lấy thông tin kho
        ajax({
            service: '/payment/loadstock.json',
            loading: false,
            contentType: 'json',
            success: function (resp) {
                if (resp.success && resp.data !== '' && resp.data.length > 0) {
                    var html = '<label class="col-md-2 control-label">Chọn kho:</label>\
                            <div class="col-md-4"><select data-rel="stock" class="form-control"><option value="0">Chọn kho</option></select></div>';
                    $("div[data-rel=stock]").html(html);
                    html = '<option value="0">Chọn kho</option>';
                    $.each(resp.data, function () {
                        html += '<option value="' + this.id + '">' + this.name + '</option>';
                    });
                    $("select[data-rel=stock]").html(html);

                    $("select[data-rel=stock]").change(function () {
                        ajax({
                            service: '/payment/getstock.json',
                            data: {id: $("select[data-rel=stock]").val()},
                            loading: false,
                            success: function (resp) {
                                if (resp.success) {
                                    $("input[name=name]").val(resp.data.sellerName);
                                    $("input[name=phone]").val(resp.data.phone);
                                    $("input[name=address]").val(resp.data.address);
                                    $("select[data-rel=cityId]").val(resp.data.cityId).change();
                                    lading.changeDistrict($("select[data-rel=cityId]").val(), resp.data.districtId);
                                } else {
                                    $("input[name=name]").val("");
                                    $("input[name=phone]").val("");
                                    $("input[name=address]").val("");
                                }
                            }
                        });
                    });

                }
            }
        });

        //caculate
        loading.shipFee();
        $("select[data-rel=districtId]").change(function () {
            loading.shipFee();
        });

        $("input[data-ship=weight]").change(function () {
            loading.shipFee();
        });
        $("input[data-ship=price]").change(function () {
            loading.shipFee();
        });
        $("select[data-ship=protec]").change(function () {
            loading.shipFee();
        });
        $("select[data-ship=shipmentService]").change(function () {
            loading.shipFee();
        });

    }, 300);
};

lading.changeDistrict = function (cityId, districtId) {
    if (cityId === null) {
        return false;
    }
    var html = "";
    $.each(districts, function () {
        if (this.cityId === cityId) {
            if (this.id === districtId) {
                html += "<option value=" + this.id + " selected >" + this.name + "</option>";
            } else
                html += "<option value=" + this.id + " >" + this.name + "</option>";
        }
    });
    $("select[data-rel=districtId]").html(html).change();
};

loading.shipFee = function () {
    $.each($("tr[data-rel=order]"), function () {
        lading.calculator($(this).attr("data-id"));
    });
};

lading.calculator = function (_orderId) {
    lading.lading(true);
    var sellerDistrictId = $("select[name=districtId]").val();
    var sellerCityId = $("select[name=cityId]").val();
//    if (sellerCityId === '0' || sellerCityId === '0') {
//        popup.msg("Địa chỉ người bán chưa được chọn");
//        return false;
//    }
    ajax({
        service: '/order/get.json',
        data: {"id": _orderId},
        loading: false,
        success: function (resp) {
            lading.lading(false);
            if (resp.success) {
                lading.lading(true);
                var weight = $("tr[data-id=" + _orderId + "] input[data-ship=weight]").val();
                var price = $("tr[data-id=" + _orderId + "] input[data-ship=price]").val().replace(/\./g, '');

                if (weight <= 0) {
                    weight = 0;
                }
                resp.data.paymentMethod = $("tr[data-id=" + _orderId + "] input[name=paymentMethod]").val();
                resp.data.codPrice = price;
                var protec = $("tr[data-id=" + _orderId + "] select[data-ship=protec]").val() === 'protec';
                resp.data.shipmentService = $("tr[data-id=" + _orderId + "] select[data-ship=shipmentService]").val();
                ajax({
                    service: '/order/calculator.json?all=true&weight=' + weight + "&price=" + resp.data.finalPrice + "&sellerCityId=" + sellerCityId + "&sellerDistrictId=" + sellerDistrictId + "&protec=" + protec,
                    data: resp.data,
                    loading: false,
                    type: 'post',
                    contentType: 'json',
                    success: function (rp) {
                        lading.lading(false);
                        if (rp.success) {
                            if (rp.data.shipmentPrice === -1) {
                                $('tr[data-id=' + _orderId + '] p[data-ship=shipFee]').html('không tính được');
                                if (rp.data.shipmentService === 'RAPID') {
                                    $("tr[data-id=" + _orderId + "] select[data-ship=shipmentService] option[value=RAPID]").hide();
                                    $("tr[data-id=" + _orderId + "] select[data-ship=shipmentService]").val("FAST").change();
                                }
                                if (rp.data.shipmentService === 'FAST') {
                                    $("tr[data-id=" + _orderId + "] select[data-ship=shipmentService] option[value=FAST]").hide();
                                    $("tr[data-id=" + _orderId + "] select[data-ship=shipmentService]").val("SLOW").change();
                                }
                                if (rp.data.shipmentService === 'SLOW') {
                                    $("tr[data-id=" + _orderId + "] select[data-ship=shipmentService] option[value=FAST]").hide();
                                    $("tr[data-id=" + _orderId + "] select[data-ship=shipmentService]").val("SLOW").change();
                                }
                            } else {
                                $('tr[data-id=' + _orderId + '] p[data-ship=shipFee]').html(eval(rp.data.shipmentPrice).toMoney(0, ',', '.') + ' <sup class="price" >đ</sup>');
                            }
                            $('tr[data-id=' + _orderId + '] .pcod').html('');
                            if (rp.data.shipmentPCod > 1) {
                                $('tr[data-id=' + _orderId + '] .pcod').html('<label class="col-sm-4 control-label">Phí bảo hiểm:</label>\
                                        <div class="col-sm-8">\
                                        <p class="form-control-static">' + eval(rp.data.shipmentPCod).toMoney(0, ',', '.') + ' đ</p>\
                                    </div>')
                            }
                        } else {
                            lading.remove(_orderId);
                        }
                    }
                });
            } else {
                popup.msg(resp.message);
                lading.remove(_orderId);
            }
        }
    });
};

lading.remove = function (_orderId) {
    $("tr[data-id=" + _orderId + "]").remove();
};

lading.lading = function (load) {
    if (load) {
        $("button.loading").html("<img src='" + baseUrl + "/static/user/images/loading-fast.gif' style='height: 19px;' />");
    } else {
        $("button.loading").html("Duyệt hàng loạt");
    }
};

lading.create = function (type) {
    var sellerDistrictId = $("select[name=districtId]").val();
    var sellerCityId = $("select[name=cityId]").val();
    if (sellerDistrictId === '0' || sellerCityId === '0') {
        popup.msg("Địa chỉ người bán chưa được chọn");
        return false;
    }
    $.each($("tr[data-rel=order]"), function (i) {
        var oId = $(this).attr("data-id");
        lading.lading(true);
        ajax({
            service: '/order/get.json',
            data: {"id": oId},
            loading: false,
            success: function (resp) {
                lading.lading(false);
                if (resp.success) {
                    var lding = {};
                    lding.type = $("tr[data-id=" + oId + "] input[name=paymentMethod]").val();
                    lding.orderId = oId;
                    lding.sellerName = $("input[name=name]").val();
                    lding.sellerPhone = $("input[name=phone]").val();
                    lding.sellerAddress = $("input[name=address]").val();
                    lding.sellerCityId = $("select[name=cityId]").val();
                    lding.sellerDistrictId = $("select[name=districtId]").val();

                    lding.receiverName = resp.data.receiverName;
                    lding.receiverPhone = resp.data.receiverPhone;
                    lding.receiverEmail = resp.data.receiverEmail;
                    lding.receiverAddress = resp.data.receiverAddress;
                    lding.receiverCityId = resp.data.receiverCityId;
                    lding.receiverDistrictId = resp.data.receiverDistrictId;

                    lding.shipmentService = $("tr[data-id=" + oId + "] select[data-ship=shipmentService]").val();
                    var weight = $("tr[data-id=" + oId + "] input[data-ship=weight]").val();

                    if (weight <= 0) {
                        weight = 0;
                    }
                    lding.name = $("tr[data-id=" + oId + "] p[data-ship=name]").text();
                    lding.description = $("tr[data-id=" + oId + "] p[data-ship=desc]").text();
                    lding.weight = weight;
                    if (type == 'cod')
                        lding.codPrice = $("tr[data-id=" + oId + "] input[data-ship=price]").val().replace(/\./g, '');
                    lding.protec = $("tr[data-id=" + oId + "] select[data-ship=protec]").val() === 'protec';
                    lading.lading(true);
                    ajax({
                        service: '/order/createlading.json',
                        data: lding,
                        loading: false,
                        type: 'post',
                        contentType: 'json',
                        success: function (resp) {
                            lading.lading(false);
                            if (resp.success) {
                                if (resp.message !== 'BROWSE_LADING_FAIL') {
                                    $("tr[data-id=" + oId + "] p[data-sc=id]").html("Mã sc: <br/><a href='http://mc.shipchung.vn/van-don.html?sc_code=" + resp.data.scId + "' target='_blank' >" + resp.data.scId + "</a>");
                                    $("tr[data-id=" + resp.data.id + "]").addClass("success");
                                    xengplus.plus(100);
                                } else {
                                    $("tr[data-id=" + oId + "] p[data-sc=id]").html("Mã sc: <br/><a href='http://mc.shipchung.vn/van-don.html?sc_code=" + resp.data.scId + "' target='_blank' >" + resp.data.scId + "</a>");
                                    $("tr[data-id=" + resp.data.id + "]").addClass("success");
                                }
                            } else {
                                if (i == 0) {
                                    if (resp.data == 'undefined' || resp.data == null) {
                                        popup.msg(resp.message);
                                    } else {
                                        var error = "<strong>" + resp.message + ":</strong> <br/><ul style='color:red;margin-left:15px;'>";
                                        $.each(resp.data, function (key, value) {
                                            error += "<li> - " + value + " </li>";
                                        });
                                        popup.msg(error + "</ul>");
                                    }
                                }

                            }
                        }
                    });
                } else {
                    popup.close('popup-msg');
                    popup.msg(resp.message);
                    lading.remove(oId);
                }
            }
        });
    });
};
