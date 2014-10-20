order = {};
order.initSeller = function () {
    $('.timeFormOrderSeller').timeSelect();
    $('.timeToOrderSeller').timeSelect();
    order.loadsellerreview(orderIds, userId, true);
    order.loadcheckmessage(orderIds, userId);

    $("input[name=checkall]").change(function () {
        if ($(this).is(":checked")) {
            $("input[for=checkall]").attr("checked", true);
        } else {
            $("input[for=checkall]").attr("checked", false);
        }
    });
    order.loadColorOrderitem(orderIds);
};
order.initCod = function () {
    var html = '<option value="0" >Chọn tỉnh / thành phố</option>';
    $.each(citys, function () {
        html += '<option value="' + this.id + '" >' + this.name + '</option>';
    });
    $("select[name=sellerCityId]").html(html);
    $("select[name=receiverCityId]").html(html);
    $("select[name=sellerCityId]").val($("select[name=sellerCityId]").attr("val")).change();
    $("select[name=receiverCityId]").val($("select[name=receiverCityId]").attr("val")).change();

    html = '<option value="0" >Chọn quận / huyện</option>';
    $.each(districts, function () {
        if (this.cityId === $("select[name=sellerCityId]").attr("val")) {
            html += '<option value="' + this.id + '" >' + this.name + '</option>';
        }
    });
    $("select[name=sellerDistrictId]").html(html);

    html = '<option value="0" >Chọn quận / huyện</option>';
    $.each(districts, function () {
        if (this.cityId === $("select[name=receiverCityId]").attr("val")) {
            html += '<option value="' + this.id + '" >' + this.name + '</option>';
        }
    });

    $("select[name=receiverDistrictId]").html(html);
    $("select[name=sellerDistrictId]").val($("select[name=sellerDistrictId]").attr("val")).change();
    $("select[name=receiverDistrictId]").val($("select[name=receiverDistrictId]").attr("val")).change();

    $("select[name=receiverCityId]").change(function () {
        $("div[name=RAPID]").fadeIn();
        $("div[name=FAST]").fadeIn();
        $("div[name=SLOW]").fadeIn();
        if ($(this).val() !== '0' && $("select[name=receiverDistrictId]").val() !== '0') {
            order.calculator();
        }
    });

    $("select[name=receiverDistrictId]").change(function () {
        $("div[name=RAPID]").fadeIn();
        $("div[name=FAST]").fadeIn();
        $("div[name=SLOW]").fadeIn();
        if ($(this).val() !== '0' && $("select[name=receiverCityId]").val() !== '0') {
            order.calculator();
        }
    });
    $("select[name=sellerCityId]").change(function () {
        $("div[name=RAPID]").fadeIn();
        $("div[name=FAST]").fadeIn();
        $("div[name=SLOW]").fadeIn();
        if ($(this).val() !== '0' && $("select[name=sellerCityId]").val() !== '0') {
            order.calculator();
        }
    });
    $("select[name=sellerDistrictId]").change(function () {
        $("div[name=RAPID]").fadeIn();
        $("div[name=FAST]").fadeIn();
        $("div[name=SLOW]").fadeIn();
        if ($(this).val() !== '0' && $("select[name=receiverCityId]").val() !== '0') {
            order.calculator();
        }
    });
    $("input[name=finalPrice]").blur(function () {
        order.calculator();
    });
    $("input[name=shipmentService]").change(function () {
        order.calculator();
    });
    $("input[name=protec]").change(function () {
        order.calculator();
    });
    $("input[name=weight]").change(function () {
        order.calculator();
    });
    order.localChange();
    order.calculator();
    order.loadStock();

};


order.loadStock = function () {
    var sellerStock = {};
    ajax({
        service: '/payment/loadstock.json',
        data: sellerStock,
        loading: false,
        done: function (resp) {
            if (resp.success) {
                var html = '';
                $.each(resp.data, function () {
                    html += '<option value="' + this.id + '">' + this.name + '</option>';
                });
                $('select[name=stock]').append(html);

                $('select[name=stock]').change(function () {
                    ajax({
                        service: '/payment/getstock.json',
                        data: {id: $("select[name=stock]").val()},
                        loading: false,
                        success: function (resp) {
                            if (resp.success) {
                                $("input[name=sellerAddress]").val(resp.data.name).change();
                                $("select[name=sellerCityId]").val(resp.data.cityId).change();
                                $("select[name=sellerDistrictId]").val(resp.data.districtId).change();
                                $("input[name=sellerName]").val(resp.data.sellerName).change();
                                $("input[name=sellerPhone]").val(resp.data.phone).change();
                            } else {
                                $("input[name=name]").val("");
                                $("input[name=phone]").val("");
                                $("input[name=address]").val("");
                            }
                        }
                    });
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

order.search = function (check, page) {
    var urlParams = order.urlParam();
    if (page == null || page < 0) {
        page = 1;
    }
    urlParams.page = page;
    if ($("input[name=keyword]").val() !== "") {
        urlParams.keyword = $("input[name=keyword]").val();
    } else {
        urlParams.keyword = null;
    }
    if ($("select[name=sort]").val() !== '' && $("select[name=sort]").val() !== "0") {
        urlParams.sort = $("select[name=sort]").val();
    } else {
        urlParams.sort = null;
    }
    if ($(".timeFormOrderSeller").val() !== "") {
        urlParams.timeForm = $(".timeFormOrderSeller").val();
    } else {
        urlParams.timeForm = null;
    }
    if ($(".timeToOrderSeller").val() !== "") {
        urlParams.timeTo = $(".timeToOrderSeller").val();
    } else {
        urlParams.timeTo = null;
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
    if (check === false) {
        location.href = "/user/don-hang-cua-toi.html" + queryString;
    } else {
        location.href = "/user/hoa-don-ban-hang.html" + queryString;
    }
};//

order.urlParam = function () {
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

order.localChange = function () {
    $("select[name=sellerCityId]").change(function () {
        var html = '<option value="0" >Chọn quận / huyện</option>';
        $.each(districts, function () {
            if (this.cityId === $("select[name=sellerCityId]").val()) {
                html += '<option value="' + this.id + '" >' + this.name + '</option>';
            }
        });
        $("select[name=sellerDistrictId]").html(html);
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
};

order.changePrice = function (num) {
    var val = $(num).val().replace(/\./g, '');
    if (val !== '' && !isNaN(val)) {
        $(num).val(parseFloat(val).toMoney(0, ',', '.')).change();
    } else {
        $(num).val(0).change();
    }
};


order.calculator = function () {
    setTimeout(function () {
        var id = $("input[name=id]").val();
        var weight = $("input[name=weight]").val();
        var price = $("input[name=finalPrice]").val().replace(/\./g, '');
        var sellerDistrictId = $("select[name=sellerDistrictId]").val();
        var sellerCityId = $("select[name=sellerCityId]").val();
        if (weight <= 0) {
            popup.msg("Khối lượng và số tiền cần thu cod phải nhập");
        }

        $("div.loading-fast").fadeIn("slow");
        ajax({
            service: '/order/get.json',
            data: {"id": id},
            loading: false,
            success: function (resp) {
                if (resp.success) {
                    resp.data.codPrice = price;
                    var protec = false;

                    $.each($("input[name=protec]"), function () {
                        if ($(this).is(":checked")) {
                            protec = ($(this).val() === 'protec');
                        }
                    });

                    resp.data.shipmentService = $("input[name=shipmentService]").val();
                    $.each($("input[name=shipmentService]"), function () {
                        if ($(this).is(":checked")) {
                            resp.data.shipmentService = $(this).val();
                        }
                    });

                    resp.data.receiverCityId = $("select[name=receiverCityId]").val();
                    resp.data.receiverDistrictId = $("select[name=receiverDistrictId]").val();

                    resp.data.paymentMethod = $("input[name=paymentMethod]").val();
                    ajax({
                        service: '/order/calculator.json?all=true&weight=' + weight + "&price=" + resp.data.finalPrice + "&sellerCityId=" + sellerCityId + "&sellerDistrictId=" + sellerDistrictId + "&protec=" + protec,
                        data: resp.data,
                        loading: false,
                        type: 'post',
                        contentType: 'json',
                        success: function (rp) {
                            $("div.loading-fast").hide();
                            if (rp.success) {
                                $("span[rel=codPrice]").html(eval(rp.data.codPrice).toMoney(0, ',', '.') + ' <sup class="price" >đ</sup>');
                                if (rp.data.shipmentPrice === -1) {
                                    $("span[rel=shipmentPrice]").html('Không hỗ trợ');
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
                                } else {
                                    $("span[rel=shipmentPrice]").html(eval(rp.data.shipmentPrice).toMoney(0, ',', '.') + ' <sup class="price" >đ</sup>');
                                }
                                if (protec === true) {
                                    $("[rel=_proteced]").fadeIn();
                                    if (rp.data.shipmentPCod > 0) {
                                        $("span[rel=proteced]").html(eval(rp.data.shipmentPCod).toMoney(0, ',', '.') + ' <sup class="price" >đ</sup>');
                                    } else {
                                        $("span[rel=proteced]").html('Miễn phí');
                                    }
                                } else {
                                    $("[rel=_proteced]").hide();
                                }

                                //CDT hỗ trợ phí vận chuyển

                                if (rp.data.cdtDiscountShipment > 0) {
                                    $("span[rel=cdtshipmentprice]").parent().fadeIn();
                                    $("span[rel=cdtshipmentprice]").html("-" + eval(rp.data.cdtDiscountShipment).toMoney(0, ',', '.') + ' <sup class="price" >đ</sup>');
                                } else {
                                    $("span[rel=cdtshipmentprice]").parent().hide();
                                }
                            } else {
                                popup.msg(rp.message);
                            }
                        }
                    });
                } else {
                    $("div.loading-fast").hide();
                    popup.msg(resp.message);
                }
            }
        });
    }, 300);
};


order.createLading = function () {

//    if (!$("input[name=yes]").is(":checked")) {
//        popup.msg("Bạn cần đồng ý tuân thủ chính sách và quy định của dịch vụ Giao hàng - Thu tiền (CoD)");
//        return false;
//    }
    $("div.loading-fast").fadeIn("slow");
    var lading = {};
    lading.type = $("input[name=paymentMethod]").val();
    lading.orderId = $("input[name=id]").val();
    lading.sellerName = $("input[name=sellerName]").val();
    lading.sellerPhone = $("input[name=sellerPhone]").val();
    lading.sellerAddress = $("input[name=sellerAddress]").val();
    lading.sellerCityId = $("select[name=sellerCityId]").val();
    lading.sellerDistrictId = $("select[name=sellerDistrictId]").val();

    lading.receiverName = $("input[name=receiverName]").val();
    lading.receiverPhone = $("input[name=receiverPhone]").val();
    lading.receiverEmail = $("input[name=receiverEmail]").val();
    lading.receiverAddress = $("input[name=receiverAddress]").val();
    lading.receiverCityId = $("select[name=receiverCityId]").val();
    lading.receiverDistrictId = $("select[name=receiverDistrictId]").val();

    lading.shipmentService = $("input[name=shipmentService]").val();
    $.each($("input[name=shipmentService]"), function () {
        if ($(this).is(":checked")) {
            lading.shipmentService = $(this).val();
        }
    });

    lading.name = $("input[name=name]").val();
    lading.description = $("textarea[name=description]").val();
    lading.weight = $("input[name=weight]").val();
    lading.codPrice = $("input[name=finalPrice]").val().replace(/\./g, '');
    lading.protec = false;
    $.each($("input[name=protec]"), function () {
        if ($(this).is(":checked")) {
            lading.protec = ($(this).val() === 'protec');
        }
    });

    ajax({
        service: '/order/createlading.json',
        data: lading,
        loading: false,
        type: 'post',
        contentType: 'json',
        success: function (resp) {
            if (resp.success) {
                if (resp.message !== 'BROWSE_LADING_FAIL') {
                    xengplus.plus(100);
                    popup.msg("Mã vận đơn <a target='_blank' href='http://mc.shipchung.vn/van-don.html?sc_code=" + resp.data.scId + "'>" + resp.data.scId + "</a>", function () {
                        location.href = baseUrl + "/user/hoa-don-ban-hang.html";
                    });
                } else {
                    popup.msg("Mã vận đơn <a target='_blank' href='http://mc.shipchung.vn/van-don.html?sc_code=" + resp.data.scId + "'>" + resp.data.scId + "</a>", function () {
                        location.href = baseUrl + "/user/hoa-don-ban-hang.html";
                    });
                }
            } else {
                popup.msg(resp.message);
                $("._error").removeClass('has-error');
                $.each(resp.data, function (key, message) {
                    $("[name=" + key + "]").after('<div class="help-block">' + message + '</div>');
                    $("[name=" + key + "]").attr("placeholder", message);
                    $("[name=" + key + "]").parent().parent().addClass('has-error _error');
                });
            }
            $("div.loading-fast").hide("slow");
        }
    });

};

order.initOrderBuyer = function () {
    order.loadsellerreview(orderIds, userId, false);
    order.loadcheckmessage(orderIds, userId);
    order.loadColorOrderitem(orderIds);
};
order.statusNote = function (id, type) {
    if (type == 'display') {
        $('.statusNote[for=' + id + ']').css({'display': 'block'});
        $('.textNote[for=' + id + ']').css({'display': 'none'});
    } else {
        $('.statusNote[for=' + id + ']').css({'display': 'none'});
        if ($('span[rel=noteText_' + id + ']').text().trim() === '') {
            $('.textNote[for=' + id + ']').css({'display': 'none'});
        }
        if ($('span[rel=noteText_' + id + ']').text().trim() !== '') {
            $('.textNote[for=' + id + ']').css({'display': 'block'});
        }
    }
};
order.saveNote = function (id) {
    var id = id;
    var note = $('input[name=note][rel=' + id + ']').val();
    if (note == null || note == '') {
        $('div #hasError').addClass('has-error');
        $('input[name=note][rel=' + id + ']').attr("placeholder", "Nội dung ghi chú không được để trống!");
    } else {
        $('div #hasError').removeClass('has-error');
        $('input[name=note][rel=' + id + ']').removeAttr("placeholder");
        ajaxSubmit({
            service: '/order/savenote.json',
            data: {id: id, note: note},
            loading: false,
            type: 'POST',
            done: function (resp) {
                if (resp.success) {
                    $('.statusNote[for=' + id + ']').css({'display': 'none'});
                    $('.textNote[for=' + id + ']').css({'display': 'block'});
                    $('span[rel=noteText_' + id + ']').text(' ' + resp.data.note + '');
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    }
};

order.nextPage = function (page, check) {
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
    if (check == false) {
        location.href = "/user/don-hang-cua-toi.html" + queryString;
    } else {
        location.href = "/user/hoa-don-ban-hang.html" + queryString;
    }
};
order.saveNoteSeller = function (id) {
    var id = id;
    var note = $('input[name=note][rel=' + id + ']').val();
    if (note == null || note == '') {
        $('div #hasError').addClass('has-error');
        $('input[name=note][rel=' + id + ']').attr("placeholder", "Nội dung ghi chú không được để trống!");
    } else {
        $('div #hasError').removeClass('has-error');
        $('input[name=note][rel=' + id + ']').removeAttr("placeholder");
        ajaxSubmit({
            service: '/order/savenoteseller.json',
            data: {id: id, note: note},
            loading: false,
            type: 'POST',
            done: function (resp) {
                if (resp.success) {
                    $('.statusNote[for=' + id + ']').css({'display': 'none'});
                    $('.textNote[for=' + id + ']').css({'display': 'block'});
                    $('span[rel=noteText_' + id + ']').text(' ' + resp.data.sellerNote + '');
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    }
};
order.linkLadingShip = function (id) {
    if (id !== '') {
        var url = baseUrl + "/user/" + id + "/tao-van-don-van-chuyen.html";
        window.open(url, '_blank');
    }

};
order.linkLading = function (id) {
    if (id !== '') {
        var url = baseUrl + "/user/" + id + "/tao-van-don-cod.html";
        window.open(url, '_blank');
    }
};
order.linkEditOrder = function (id) {
    if (id !== '') {
        var url = baseUrl + "/" + id + "/thanh-toan-gio-hang.html";
        window.open(url, '_blank');
    }
};

order.removeOrder = function (id) {
    popup.confirm("Bạn có chắc muốn xóa đơn hàng này ?", function () {
        ajax({
            service: '/order/removeorder.json',
            data: {id: id},
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
order.recoveryOrder = function (id) {
    popup.confirm("Bạn có chắc muốn khôi phục đơn hàng này ?", function () {
        ajax({
            service: '/order/removeorder.json',
            data: {id: id},
            done: function (resp) {
                if (resp.success) {
                    popup.msg("Khôi phục đơn hàng thành công", function () {
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
order.markPaymentStatus = function (orderId, seller) {
    ajax({
        service: '/order/markpaymentstatus.json',
        data: {orderId: orderId, seller: seller},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                popup.msg("Thay đổi trạng thái thanh toán thành công", function () {
                    location.reload();
                });

            } else {
                popup.msg(resp.message);
            }
        }
    });

};
order.markShipmentStatus = function (orderId, seller) {
    ajax({
        service: '/order/markshipmentstatus.json',
        data: {orderId: orderId, seller: seller},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                popup.msg("Thay đổi trạng thái vận chuyển thành công", function () {
                    location.reload();
                });

            } else {
                popup.msg(resp.message);
            }
        }
    });

};
order.unmarkPaymentStatus = function (orderId, seller) {
    ajax({
        service: '/order/unmarkpaymentstatus.json',
        data: {orderId: orderId, seller: seller},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                popup.msg("Thay đổi trạng thái thanh toán thành công", function () {
                    location.reload();
                });

            } else {
                popup.msg(resp.message);
            }
        }
    });

};
order.unmarkShipmentStatus = function (orderId, seller) {
    ajax({
        service: '/order/unmarkshipmentstatus.json',
        data: {orderId: orderId, seller: seller},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                popup.msg("Thay đổi trạng thái vận chuyển thành công", function () {
                    location.reload();
                });

            } else {
                popup.msg(resp.message);
            }
        }
    });

};
order.refund = function (orderId) {
    ajax({
        service: '/order/refund.json',
        data: {orderId: orderId},
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

};

order.review = function (id) {
    ajax({
        service: '/order/get.json',
        data: {id: id},
        done: function (resp) {
            if (resp.success) {
                popup.open('popup-add', 'Đánh giá uy tín', template('/user/tpl/sellerreview.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function () {
                            ajaxSubmit({
                                service: '/sellerreview/review.json',
                                id: 'form-review',
                                contentType: 'json',
                                done: function (rs) {
                                    if (rs.success) {
                                        popup.close('popup-add');
                                        popup.msg("<p>Cám ơn bạn đã đánh giá uy tín!</p><p>Điều này góp phần tạo nên cộng đồng mua bán và môi trường giao dịch an toàn, lành mạnh tại ChợĐiệnTử.</p>");
                                    } else {
                                        popup.msg(resp.message);
                                    }

                                }
                            });
                        }
                    },
                    {
                        title: 'Hủy',
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
order.sendMessge = function (id) {
    ajax({
        service: '/order/get.json',
        data: {id: id},
        done: function (resp) {
            if (resp.success) {
                popup.open('popup-add', 'Nhắn người bán', template('/user/tpl/sendmess.tpl', resp), [
                    {
                        title: 'Gửi tin nhắn',
                        style: 'btn-primary',
                        fn: function () {
                            ajaxSubmit({
                                service: '/order/sendmessge.json',
                                id: 'form-message',
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
                        title: 'Hủy',
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
order.sendMessgeBuyer = function (id) {
    ajax({
        service: '/order/get.json',
        data: {id: id},
        done: function (resp) {
            if (resp.success) {
                popup.open('popup-add', 'Nhắn người mua', template('/user/tpl/sendmessbuyer.tpl', resp), [
                    {
                        title: 'Gửi tin nhắn',
                        style: 'btn-primary',
                        fn: function () {
                            ajaxSubmit({
                                service: '/order/sendmessge.json',
                                id: 'form-message',
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
                        title: 'Hủy',
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

order.loadsellerreview = function (orderIds, userId, seller) {
    if (typeof orderIds !== 'undefined' && orderIds !== null && orderIds.length > 0) {
        ajax({
            service: '/sellerreview/getbyorderids.json?userId=' + userId + '&seller=' + seller,
            data: orderIds,
            type: 'post',
            contentType: 'json',
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    $(resp.data).each(function (i) {
                        var timeH = textUtils.formatTime(resp.data[i].updateTime, 'hour');
                        $('#' + resp.data[i].orderId + 'review').html('<span class="icon16-review tool-tip" data-toggle="tooltip" data-placement="top" title="" data-original-title="Đã viết đánh giá lúc: ' + timeH + '"></span>');
                    });
                    $('.tool-tip').tooltip();
                } else {

                }
            }
        });
    }

};
order.loadcheckmessage = function (orderIds, fromUserId) {
    if (typeof orderIds !== 'undefined' && orderIds !== null && orderIds.length > 0) {
        ajax({
            service: '/message/getbyorderids.json?fromUserId=' + fromUserId,
            data: orderIds,
            type: 'post',
            contentType: 'json',
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    $(resp.data).each(function (i) {
                        $('#' + resp.data[i] + 'message').html('<span class="icon16-getreview tool-tip" data-toggle="tooltip" data-placement="top" title="" data-original-title="Đã gửi tin nhắn"></span>');
                    });
                    $('.tool-tip').tooltip();
                } else {

                }
            }
        });
    }

};

order.linkSearchItem = function (idCate, nameCate) {
    var data = {};
    data.categoryIds = [];
    data.categoryIds.push(idCate);
    var url = baseUrl + urlUtils.browseUrl(data, nameCate);
    window.open(url, '_blank');
};

order.loadColorOrderitem = function (orderIds) {
    $.each(orderIds, function (i) {
        $.each($('#' + orderIds[i] + ' .colorsizeVal'), function () {
            var color = property.getCollor(textUtils.createAlias($(this).attr('color')));
            $(this).html('<div style="background-color:' + color + '" class="tc-item" title="' + $(this).attr('color') + '"></div>');
        });
    });
};