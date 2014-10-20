payment = {};
payment.init = function() {
    payment.loadStock();
    $("select#discountPercent").change(function() {
        if ($(this).val() === '1') {
            $('.discountType input').attr('name', 'discountPercent');
            $('.discountType input').removeClass('inputNumber');
        }
        if ($(this).val() === '2') {
            $('.discountType input').attr('name', 'discountPrice');
            $('.discountType input').addClass('inputNumber');
            textUtils.inputNumberFormat('inputNumber');
        }
    });
    payment.loadSupportFee('ONLINEPAYMENT');
    payment.loadSupportFee('COD');
    textUtils.inputNumberFormat('inputNumber');
};
$(document).ready(function() {
    var orderIdStock = $('#orderIdStock').val();
    var orderIdExternal = $('#orderIdExternal').val();
    $('.btn-payment-stock').html('<a class="button" onclick="javascript: paymentCall(\'stock\', ' + orderIdStock + ');">Thanh toán</a>');
    $('.btn-payment-external').html('<a class="button" onclick="javascript: paymentCall(\'external\', ' + orderIdExternal + ');">Thanh toán</a>');
    contExpand = $('.content-expand');
    $('.grid').each(function() {
        var radio = $(this).children('input');
        $(this).click(function(e) {
            radio.attr('checked', 'checked');
            if ($(this).find('.content-expand').css('display') == 'none') {
                contExpand.slideUp();
                $(this).find('.content-expand').slideDown();
            }
        });
    });
    $('a.logoBank, input[name="rd-bank"]').each(function() {
        var li_bank = $(this).closest('li'),
                rd_bank = li_bank.find('input[name="rd-bank"]');
        $(this).click(function() {
            $('.listBank').find('li').removeClass('active');
            li_bank.addClass('active');
            rd_bank.attr('checked', 'checked');
            if ($(this).closest('.content-expand').find('.button-control').css('display') == 'none') {
                $('.button-control').hide('');
                $(this).parents('.content-expand').find('.button-control').slideDown('');
            }
        });
    });
});
function paymentCall(obj, orderId) {
    var method = $('input[name=r_payment]:checked').val();
    if (method != 'NL' && method != 'COD')
    {
        var bankCode = $('input[name="rd-bank"]:checked').val();
    }
    if ((method == 'ATM_ONLINE' || method == 'ATM_OFFLINE' || method == 'NH_OFFLINE') && !bankCode)
    {
        alert('Bạn phải chọn ngân hàng.');
        return false;
    }
    if (method == 'VISA' && !bankCode)
    {
        alert('Bạn phải chọn loại thẻ VISA hoặc MASTERCARD.');
        return false;
    }
    if (method == 'TTVP' && !bankCode)
    {
        alert('Bạn phải chọn văn phòng nộp tiền.');
        return false;
    }
    $.ajax({
        url: baseUrl + '/frontend/payment/createTransaction',
        dataType: 'json',
        type: 'get',
        data: {
            'method': method,
            'orderId': orderId,
            'bankCode': bankCode
        },
        beforeSend: function(data) {
            $('body').append('<div class="loading" style="display: block;"><span class="loading-img"></span><span class="loading-text">Đang xử lý....Vui lòng chờ trong giây lát!</span></div>');
        },
        success: function(data) {
            $('.loading').remove();
            if (data != null && data.error_code == 'codErr') {
                alert('Địa chỉ người nhận không hỗ trợ phương thức COD. Vui lòng chọn lại phương thức thanh toán!');
                return;
            }
            if (method == 'COD') {
                document.location.href = baseUrl + '/frontend/payment/orderSuccess/id/' + orderId;
                return;
            }
            if (data.error_code != '00') {
                alert('Không thể kết nối với phương thức thanh toán hiện tại.');
            }
            else {
//                console.log(data.checkout_url);return false;
                if (method == 'NL' || method == 'ATM_ONLINE' || method == 'VISA') {
                    location.href = data.checkout_url;
                }
                else {
                    location.href = baseUrl + '/frontend/payment/success?orderId=' + orderId + '&checkout_url=' + encodeURIComponent(data.checkout_url);
                    /*objHtml = $('#in-country-info');
                     objHtml.html('' +
                     '<h3>Cảm ơn bạn đã đặt hàng!</h3>' +
                     '<h4>Chúng tôi đã nhận được thông tin để tiến hành đặt hàng.</h4>' +
                     '<iframe src="'+ data.checkout_url +'" width="100%" height="500px" style="border: 1px #ccc solid"></iframe>' +
                     '<div class="yc-bottom">' +
                     '<a class="button fright" href="'+ baseUrl +'/frontend/user/bills/id/'+ orderId +'">Xem hoá đơn</a>' +
                     '<a href="'+ baseUrl +'" class="btn-back">Tiếp tục mua hàng</a>' +
                     '</div>');*/
                }
            }
        }
    });
}

//user payment integrate 
$("#nl-integration").click(function() {
    popup.open("nl-confirm", "Tích hợp ngân lượng", "Bạn sẽ được chuyển sang NgânLượng.vn để đăng nhập vào tài khoản của mình, sau khi đăng nhập thành công bạn sẽ được đưa trở lại trang này với mối liên kết đã được thiết lập.", [
        {
            title: 'Đồng ý',
            className: 'button',
            fn: function() {
                ajax({
                    service: '/payment/nlIntegrate.json',
                    loading: false,
                    done: function(resp) {
                        if (resp.success) {
                            window.location.href = resp.data;
                        } else {
                            popup.msg(resp.message);
                        }
                    }
                });
            }
        },
        {
            title: 'Đóng',
            className: 'ui-button-text',
            fn: function() {
                popup.close("nl-confirm");
            }
        }
    ]);
//    var nganluongUrl = $(this).attr('value');
});
//user payment integrate 
$("#sc-integration").click(function() {
    popup.open("nl-confirm", "Tích hợp Ship Chung", "Bạn có muốn tích hợp shipchung để hưởng lợi ưu đãi của dịch vụ.", [
        {
            title: 'Đồng ý',
            className: 'button',
            fn: function() {
//                ajax({
//                    service: '/payment/nlIntegrate.json?type=sc',
//                    loading: false,
//                    done: function(resp) {
//                        if (resp.success) {
//                            window.location.href = resp.data;
//                        } else {
//                            popup.msg(resp.message);
//                        }
//                    }
//                });
//                var redirect_uri = baseUrl + "/user/cau-hinh-tich-hop.html";
//                location.href = "http://mc.shipchung.vn/openid.html?response_type=code&client_id=cdt_access&state=login&redirect_uri=" + redirect_uri;
                location.href = "http://mc.shipchung.vn/openid.html?response_type=code&client_id=cdt_access&state=login";
            }
        },
        {
            title: 'Đóng',
            className: 'ui-button-text',
            fn: function() {
                popup.close("nl-confirm");
            }
        }
    ]);
//    var nganluongUrl = $(this).attr('value');

});
$("#nl-unlinked").click(function() {
    popup.open("nl-confirm", "Tích hợp ngân lượng", "Bạn có chắc chắn hủy liên kết với NgânLượng.vn không?", [
        {
            title: 'Đồng ý',
            className: 'button',
            fn: function() {
                ajax({
                    service: '/payment/nlUnlinked.json',
                    loading: false,
                    done: function(resp) {
                        if (resp.success) {
                            popup.close("nl-confirm");
                            popup.msg("Bạn đã hủy kết nối với NgânLượng.vn thành công!</br><span style='color:red'>Cảnh báo</span>: Nếu bạn có shop  thì shop của bạn sẽ tạm dừng hoạt động và sẽ hoạt động lại sau khi bạn liên kết trở lại.", function() {
                                window.location.href = "../user/cau-hinh-tich-hop.html";
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
            className: 'ui-button-text',
            fn: function() {
                popup.close("nl-confirm");
            }
        }
    ]);
});
$("#sc-unlinked").click(function() {
    popup.open("nl-confirm", "Tích hợp ShipChung", "Bạn có chắc chắn hủy liên kết với Shipchung không?", [
        {
            title: 'Đồng ý',
            className: 'button',
            fn: function() {
                ajax({
                    service: '/payment/nlUnlinked.json?type=sc',
                    loading: false,
                    done: function(resp) {
                        if (resp.success) {
                            popup.close("nl-confirm");
                            popup.msg("Bạn đã hủy kết nối với Shipchung thành công!</br><span style='color:red'>Cảnh báo</span>: Nếu bạn có shop  thì shop của bạn sẽ tạm dừng hoạt động và sẽ hoạt động lại sau khi bạn liên kết trở lại.", function() {
                                window.location.href = "../user/cau-hinh-tich-hop.html";
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
            className: 'ui-button-text',
            fn: function() {
                popup.close("nl-confirm");
            }
        }
    ]);
});
$('input[name=submit-pay-choose]').click(function() {
    if ($(this).val() === '3') {
        $('input[name=shipmentPrice]').focus();
    }
});
$('input[name=shipmentPrice]').click(function() {

    $('input[name=submit-pay-choose][value=3]').prop('checked', true);
});
payment.saveShipmentConfig = function(_update) {
    var shipmentPrice = null;
    var shipmentType = $('input[name=shipmentType]').filter(':checked').val();
    if (shipmentType != null && shipmentType == 'FIXED') {
        if ($('input[name=shipmentFree]').is(":checked")) {
            shipmentPrice = 0;
        } else {
            shipmentPrice = $('input[name=shipmentPrice]').val();
        }
    }
    if (isNaN(shipmentPrice) && shipmentType == 'FIXED' && shipmentPrice != '' && shipmentPrice != null && !$('input[name=shipmentFree]').is(":checked")) {
        popup.msg('Phí vận chuyển phải là số!');
    } else if (shipmentType == 'FIXED' && !$('input[name=shipmentFree]').is(":checked") && shipmentPrice <= 0) {
        popup.msg('Phí vận chuyển cố định toàn quốc phải lớn hơn 0!');
    } else if (shipmentType == '' || shipmentType == null) {
        popup.msg('Bạn chưa chọn hình thức vận chuyển nào!');
    } else {
        var obj = new Object();
        obj.shipmentType = shipmentType;
        obj.shipmentPrice = shipmentPrice;
        ajax({
            data: obj,
            service: '/payment/addshippingfee.json?update=' + _update,
            contentType: 'json',
            type: 'post',
            loading: false,
            done: function(rss) {
                if (rss.success) {
                    popup.open("nl-confirm", "Tích hợp ShipChung", "Cập nhật phí vận chuyển thành công", [
                        {
                            title: 'Đóng',
                            className: 'ui-button-text',
                            fn: function() {
                                popup.close("nl-confirm");
                                location.reload();
                            }
                        }
                    ]);
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    }
};
payment.initSellerPolicy = function() {
    $.each($("input[type=checkbox]"), function() {
        $(this).attr("rel", (textUtils.createAlias($(this).val())));
    });
    if (sellerPolicy != null && sellerPolicy.length > 0) {
        $.each(sellerPolicy, function() {
            $('textarea[for=' + this.type + ']').val(this.description);
            if (this.policy != null && this.policy.length > 0) {
                var policy = this.policy;
                $.each(this.policy, function() {
                    var val = textUtils.createAlias(this);
                    $("input[rel=" + val + "]").attr("checked", true);
                });
            }
        });
    }
};
payment.saveSellerPolicy = function() {
    var salesPolicy = [];
    var valueWARRANT = [];
    var valueSHIPPING = [];
    $.each($('input[for=WARRANT]').filter(':checked'), function() {
        valueWARRANT.push($(this).val());
    });
    $.each($('input[for=SHIPPING]').filter(':checked'), function() {
        valueSHIPPING.push($(this).val());
    });
    var WARRANT = new Object();
    WARRANT.type = 'WARRANT';
    WARRANT.description = $('textarea[name=descriptionWARRANT]').val();
    WARRANT.policy = valueWARRANT;
    var SHIPPING = new Object();
    SHIPPING.type = 'SHIPPING';
    SHIPPING.description = $('textarea[name=descriptionSHIPPING]').val();
    SHIPPING.policy = valueSHIPPING;
    var INSTALLATION = new Object();
    INSTALLATION.type = 'INSTALLATION';
    INSTALLATION.description = $('textarea[name=descriptionINSTALLATION]').val();
    var SUPPORT = new Object();
    SUPPORT.type = 'SUPPORT';
    SUPPORT.description = $('textarea[name=descriptionSUPPORT]').val();
    salesPolicy.push(WARRANT, SHIPPING, INSTALLATION, SUPPORT);
    ajaxSubmit({
        data: salesPolicy,
        service: '/payment/savesellerpolicy.json',
        type: 'post',
        contentType: 'json',
        loading: false,
        done: function(resp) {
            if (resp.success) {
                popup.msg(resp.message, function() {
                    location.reload();
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
payment.loadStock = function() {
    var sellerStock = {};
    ajax({
        service: '/payment/loadstock.json',
        data: sellerStock,
        loading: false,
        done: function(resp) {
            if (resp.success) {
                var html = '';
                if (resp.data != null && resp.data.length > 0) {
                    $('.listStock').html('');
                    $.each(resp.data, function() {
                        var id = this.id;
                        if (this.active) {
                            html += '<tr rel="' + id + '">\
                                <td width="60%"><div class="text-left cdt-tooltip" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="' + this.address + '">' + this.name + '</div></td >\
                                <td width="40%"><div class="text-left"><a style="cursor: pointer" onclick="payment.hideStock(\'' + id + '\')">Ẩn</a>&nbsp;|&nbsp;<a style="cursor: pointer" onclick="payment.editStock(\'' + id + '\');">Sửa</a>&nbsp;|&nbsp;<a style="cursor: pointer" onclick="payment.delStock(\'' + id + '\');">Xoá</a></div></td>\
                                </tr>';
                        } else {
                            html += '<tr class="text-muted" rel="' + id + '">\
                                <td width="60%"><div class="text-left cdt-tooltip" data-toggle="tooltip" data-placement="bottom" title="" data-original-title="' + this.address + '">' + this.name + '</div></td >\
                                <td width="40%"><div class="text-left"><a onclick="payment.hideStock(\'' + id + '\');" style="cursor: pointer">Hiện</a>&nbsp;|&nbsp;<a onclick="payment.editStock(\'' + id + '\');" style="cursor: pointer">Sửa</a>&nbsp;|&nbsp;<a onclick="payment.delStock(\'' + id + '\');" style="cursor: pointer">Xoá</a></div></td>\
                                </tr>';
                        }
                    });
                } else {
                    html = '<tr><td class="text-center danger" colspan="2">Chưa có kho nào!</td></tr>';
                }
                $('.listStock').append(html);
                $('.cdt-tooltip').tooltip();
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
payment.addStock = function() {
    popup.open('popup-add-stock', 'Thêm mới kho', template('/user/tpl/addsellerstock.tpl'), [
        {
            title: 'Thêm',
            style: 'btn-info',
            fn: function() {
                $('input[name=phone]').val("1" + $('input[name=phone]').val());
                ajaxSubmit({
                    service: '/payment/addstock.json',
                    id: 'form-add-sellerStock',
                    type: 'post',
                    contentType: 'json',
                    done: function(resp) {
                        if (resp.success) {
                            popup.msg(resp.message, function() {
                                popup.close('popup-add-stock');
                                payment.loadStock();
                            });
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
            fn: function() {
                popup.close('popup-add-stock');
            }
        }
    ]);
    var html = '<option value="">-- Chọn tỉnh thành --</option>';
    $.each(citys, function() {
        html += '<option value="' + this.id + '">' + this.name + '</option>';
    });
    $("select[name=cityId]").html(html);
    $("select[name=cityId]").change(function() {
        if ($(this).val() == 0) {
            $("select[name=districtId]").val(0);
        } else {
            var html = '<option value="">-- Chọn quận huyện --</option>';
            var cityId = $(this).val();
            $.each(districts, function() {
                if (cityId == this.cityId) {
                    html += '<option value="' + this.id + '">' + this.name + '</option>';
                }
            });
            $("select[name=districtId]").html(html);
        }
    });
};

payment.delStock = function(id) {
    popup.confirm("Bạn có chắc muốn xóa kho này?", function() {
        ajax({
            service: '/payment/delstock.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    popup.msg(resp.message, function() {
                        $('tr[rel=' + id + ']').remove();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

payment.editStock = function(id) {
    ajax({
        service: '/payment/getstock.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-edit', 'Sửa thông tin kho', template('/user/tpl/addsellerstock.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            $('input[name=phone]').val("1" + $('input[name=phone]').val());
                            ajaxSubmit({
                                service: '/payment/editstock.json',
                                id: 'form-add-sellerStock',
                                type: 'post',
                                contentType: 'json',
                                done: function(resp) {
                                    popup.close('popup-edit');
                                    if (resp.success) {
                                        popup.msg(resp.message, function() {
                                            payment.loadStock();
                                        });
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
                        fn: function() {
                            popup.close('popup-edit');
                        }
                    }
                ]);

                var html = '<option value="">-- Chọn tỉnh thành --</option>';
                $.each(citys, function() {
                    html += '<option ' + ((this.id == resp.data.cityId) ? 'selected' : '') + ' value="' + this.id + '">' + this.name + '</option>';
                });
                $("select[name=cityId]").html(html);

                html = "";
                $.each(districts, function() {
                    if (this.cityId == resp.data.cityId) {
                        html += '<option ' + ((this.id == resp.data.districtId) ? 'selected' : '') + ' value="' + this.id + '">' + this.name + '</option>';
                    }
                });
                $("select[name=districtId]").html(html);
                $("select[name=cityId]").change(function() {
                    if ($(this).val() == 0) {
                        $("select[name=districtId]").val(0);
                    } else {
                        var html = '<option value="">-- Chọn quận huyện --</option>';
                        var cityId = $(this).val();
                        $.each(districts, function() {
                            if (cityId == this.cityId) {
                                html += '<option value="' + this.id + '">' + this.name + '</option>';
                            }
                        });
                        $("select[name=districtId]").html(html);
                    }
                });

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
payment.hideStock = function(id) {
    popup.confirm("Bạn có chắc muốn thực hiện thao tác này?", function() {
        ajax({
            service: '/payment/hidestock.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    popup.msg("Thành công!", function() {
                        payment.loadStock();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
payment.addSupportFee = function(type) {
    $.each($('.inputNumber'), function() {
        $(this).val($(this).val().replace(/\./g, ''));
    });

    if (typeof type !== 'undefined' && type !== null && type === 'ONLINEPAYMENT') {
        ajaxSubmit({
            service: '/payment/addsupportfee.json?typeFee=ONLINEPAYMENT',
            id: 'form-add-sellersupportfee',
            type: 'post',
            contentType: 'json',
            done: function(resp) {
                if (resp.success) {
                    payment.loadSupportFee('ONLINEPAYMENT');
                    payment.loadSupportFee('COD');
                    payment.clearInputSupportFee();
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    }
    if (typeof type !== 'undefined' && type !== null && type === 'COD') {
        ajaxSubmit({
            service: '/payment/addsupportfee.json?typeFee=COD',
            id: 'form-add-sellersupportfeeCoD',
            type: 'post',
            contentType: 'json',
            done: function(resp) {
                if (resp.success) {
                    payment.loadSupportFee('ONLINEPAYMENT');
                    payment.loadSupportFee('COD');
                    payment.clearInputSupportFee();

                } else {
                    popup.msg(resp.message);
                }
            }
        });
    }

}

payment.closeBoxSupportFee = function(type) {
    if (typeof type !== 'undefined' && type !== null && type === 'ONLINEPAYMENT') {
        $('.onlinePaymentBox').hide();
        $('.sugONLINEPAYMENT').show();
    }
    if (typeof type !== 'undefined' && type !== null && type === 'COD') {
        $('.codBox').hide();
        $('.sugCOD').show();
    }
}
payment.openBoxSupportFee = function(type) {
    if (typeof type !== 'undefined' && type !== null && type === 'ONLINEPAYMENT') {
        $('.onlinePaymentBox').show();
        $('.sugONLINEPAYMENT').hide();
    }
    if (typeof type !== 'undefined' && type !== null && type === 'COD') {
        $('.codBox').show();
        $('.sugCOD').hide();
    }
}
payment.loadSupportFee = function(type) {
    ajax({
        service: '/payment/listsupportfee.json',
        data: {feeType: type},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                var onlinePaymentHTML = "";
                $(resp.data.data).each(function(i) {
                    var discountText = "";
                    if (resp.data.data[i].discountPercent > 0) {
                        discountText = resp.data.data[i].discountPercent + "%";
                    }
                    if (resp.data.data[i].discountPrice > 0) {
                        discountText = parseFloat(resp.data.data[i].discountPrice).toMoney(0, ',', '.') + " VNĐ";
                    }
                    onlinePaymentHTML += '<p class="mgt-15">' +
                            '<strong>Mức ' + (i + 1) + '</strong>' +
                            '<a class="pull-right" href="javascript:;" onclick="payment.delSupportFee(' + resp.data.data[i].id + ');"><span class="glyphicon glyphicon-trash"></span></a>' +
                            '</p>' +
                            '<div class="form-group">' +
                            '<label class="control-label col-sm-3 col-xs-12">Giá trị hoá đơn từ</label>' +
                            '<div class="col-sm-7 col-xs-9"><p class="form-control-static">' + parseFloat(resp.data.data[i].minOrderPrice).toMoney(0, ',', '.') + ' <sup class="u-price">đ</sup></p></div>' +
                            '</div>' +
                            '<div class="form-group">' +
                            '<label class="control-label col-sm-3 col-xs-12">Mức giảm giá</label>' +
                            '<div class="col-sm-7 col-xs-9"><p class="form-control-static">Tối thiểu ' + discountText + '</p></div>' +
                            '</div>';
                });
                $('#onlinePaymentHTML' + type).html(onlinePaymentHTML);
                if (resp.data.data.length > 0) {
                    $('.sug' + type).hide();
                } else {
                    $('.sug' + type).show();
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });

};
payment.delSupportFee = function(id) {
    popup.confirm("Bạn có chắc muốn thực hiện thao tác này?", function() {
        ajax({
            service: '/payment/delsupportfee.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    payment.loadSupportFee('ONLINEPAYMENT');
                    payment.loadSupportFee('COD');
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
payment.clearInputSupportFee = function() {
    $('input[name=minOrderPrice]').val('');
    $('input[name=discountPrice]').val('');
    $('input[name=discountPercent]').val('');
};

payment.openShop = function() {
    popup.confirm("Bạn đã cấu hình Ngân Lượng và Ship Chung! Bạn có muốn mở shop không?", function() {
        location.href = baseUrl + "/user/open-shop-step3.html";
    });
};