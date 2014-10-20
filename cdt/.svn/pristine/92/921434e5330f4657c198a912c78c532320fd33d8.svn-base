coupon = {};
coupon.init = function() {
    $('.timeselectstart').timeSelect();
    $('.timeselectend').timeSelect();

};

coupon.countCharacters = function() {
    var count = 125 - eval($('input[name=name]').val().length);
    $('span.countresult').html(count + '/125');
};

coupon.genCouponCode = function() {
    ajax({
        service: '/coupon/gencouponcode.json',
        loading: false,
        data: {},
        done: function(resp) {
            $('span.coupon-id').html(resp.message);
            $('input[type=hidden]#codeVal').val(resp.message);
        }
    });
};
coupon.submitCouponForm = function() {
    var discountType = $('select[name=discountType]').val();
    var datas = new Object();

    datas.sellerId = $('input[name=sellerId]').val();
    datas.name = $('input[name=name]').val();
    datas.startTime = $('input[name=startTime]').val();
    datas.endTime = $('input[name=endTime]').val();
    datas.usedquantity = $('input[name=usedquantity]').val();
    if (discountType == 1) {
        datas.discountPrice = $('input[name=discountPrice]').val();
        datas.discountPercent = 0;
    } else {
        datas.discountPercent = $('input[name=discountPrice]').val();
        datas.discountPrice = 0;
    }
    datas.minOrderValue = $('input[name=minOrderValue]').val();
    datas.code = $('input[name=codeVal]').val();
    $('[for=discountPrice').html('');
    $('[for=startTime').html('');
    $('[for=endTime').html('');
    $('[for=usedquantity').html('');
    $('[for=minOrderValue').html('');
    $('[for=name').html('');
    ajax({
        data: datas,
        service: '/coupon/add.json',
        contentType: 'json',
        type: 'post',
        loading: false,
        done: function(resp) {
            if (resp.success) {
                popup.msg("Đã thêm thành công", function() {
                    location.reload();
                });
            } else {
                $.each(resp.data, function(key, val) {
                    $('[for=' + key + ']').html(val);
                });  
            }
        }
    });

};
coupon.updateCouponStatus = function(code, sellerId) {
    popup.confirm("Bạn có chắc muốn dừng khuyến mại coupon này?", function() {
        ajax({
            service: '/coupon/updatecoupon.json',
            data: {code: code, sid: sellerId},
            loading: false,
            done: function(resp) {
                if (resp.success) {
                    location.reload();
                    $('div[code=' + code + ']').addClass("coupon-expire");
                    var el = $('div[code=' + code + ']').find('table tr td span.clr-green');
                    $(el).html("<i>Hủy</i>");
                    $(el).removeClass('clr-green');
                    $(el).addClass('clr-red');
                    $(el).parent('td').next('td').html('');
                } else {
                    popup.msg(resp.data);
                }
            }
        });
    });
};

coupon.search = function() {
    var urlParams = coupon.urlParam();

    if ($("select[name=status]").val() != "0") {
        urlParams.status = $("select[name=status]").val();
    } else {
        urlParams.status = null;
    }

    if ($("input[name=keyword]").val() != '') {
        urlParams.keyword = $("input[name=keyword]").val();
    } else {
        urlParams.keyword = null;
    }

    var queryString = "";
    var i = 1;
    $.each(urlParams, function(key, val) {
        if (val != null) {
            if (i == 1)
                queryString += "?";
            else
                queryString += "&";
            queryString += key + "=" + val;
            i++;
        }
    });
    location.href = "/user/coupon.html" + queryString;
};