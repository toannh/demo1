cash = {};
cash.init = function() {
    $('.timeselect').timeSelect();
    cash.changeAmount();
};
// modify paymentNL
cash.paymentNL = function(_id) {
    var obj = new Object();
    if (typeof _id !== 'undefined' && _id !== null && _id !== '')
        obj.id = _id;
    obj.amount = $("select[name=amount]").val();
    obj.spentQuantity = $("input[name=spentQuantity]").val();
    obj.paymentMethod = $("input[name=paymentMethod]").val().toUpperCase();
    $.each($("input[name=paymentMethod]"), function() {
        if ($(this).is(":checked")) {
            obj.paymentMethod = $(this).val().toUpperCase();
        }
    });
    if (obj.paymentMethod === null || obj.paymentMethod === '') {
        obj.paymentMethod = 'NL';
    }
    if (isNaN(obj.spentQuantity) || obj.spentQuantity <= 0) {
        obj.spentQuantity = 1;
    }

    ajax({
        service: '/cash/payment.json',
        data: obj,
        contentType: 'json',
        loading: true,
        type: 'post',
        done: function(resp) {
            if (resp.success) {
                document.location = resp.data;
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

cash.changeAmount = function() {
    var amount = $("select[name=amount]").val();
    var spentQuantity = $("input[name=spentQuantity]").val();
    if (isNaN(spentQuantity) || spentQuantity <= 0) {
        spentQuantity = 1;
        $("input[name=spentQuantity]").val(1);
    }
    var blance = amount * spentQuantity;
    var paymentMethod = $("input[name=paymentMethod]").val();
    $.each($("input[name=paymentMethod]"), function() {
        if ($(this).is(":checked")) {
            paymentMethod = $(this).val();
        }
    });
    var discount = cash.discount(blance, paymentMethod);
    $('.totalAmount').html(cash.moneyFormat(blance) + " <sup class='u-price'>đ</sup>");
    $('.paymentAmount').html(cash.moneyFormat(eval(blance) - eval(discount)) + " <sup class='u-price'>đ</sup>");
    if (discount > 0) {
        $('.discountAmount').fadeIn();
        $('.discountAmount > span').html("Giảm: " + cash.moneyFormat(discount) + " <sup class='u-price'>đ</sup>");
    } else {
        $('.discountAmount').hide();
    }
};

cash.discount = function(_blance, _paymentMethod) {
    var _amount = eval(_blance);
    if (_amount >= 1000000 && _amount <= 2000000) {
        _amount = _amount * 0.05;
    } else if (_amount >= 3000000 && _amount <= 5000000) {
        _amount = _amount * 0.1;
    } else if (_amount >= 10000000) {
        _amount = _amount * 0.15;
    } else {
        _amount = 0;
    }
    //Giảm 20% khi thanh toán bằng visa
    if (_paymentMethod === 'visa') {
        _amount += eval(_blance - _amount) * 0.2;
    }
    return _amount;
};

cash.moneyFormat = function(number, decimals, dec_point, thousands_sep) {
    number = (number + '').replace(/[^0-9+\-Ee.]/g, '');
    var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            sep = (typeof thousands_sep === 'undefined') ? '.' : thousands_sep,
            dec = (typeof dec_point === 'undefined') ? ',' : dec_point,
            s = '',
            toFixedFix = function(n, prec) {
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

