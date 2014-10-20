order = {};

order.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị đơn hàng", "/cp/order.html"],
        ["Danh sách đơn hàng"]
    ]);
    $('.createTimeForm').timeSelect();
    $('.createTimeTo').timeSelect();
    $('.paidTimeForm').timeSelect();
    $('.paidTimeTo').timeSelect();

    $("a").tooltip({placement: 'top'});
    $("span").tooltip({placement: 'top'});

    var html = '<option value="0">Chọn tỉnh / thành phố - Người Bán</option>';
    $.each(citys, function() {
        html += '<option value="' + this.id + '" >' + this.name + '</option>';
    });
    $('select[name=sellerCityId]').html(html);
    var html = '<option value="0">Chọn tỉnh / thành phố - Người nhận</option>';
    $.each(citys, function() {
        html += '<option value="' + this.id + '" >' + this.name + '</option>';
    });
    $("select[name=receiverCityId]").html(html);
    var html = '<option value="0">Chọn tỉnh / thành phố - Người mua</option>';
    $.each(citys, function() {
        html += '<option value="' + this.id + '" >' + this.name + '</option>';
    });
    $("select[name=buyerCityId]").html(html);

    $('select[name=sellerCityId]').change(function() {
        var htmlSellerCityId = '<select name="sellerDistrictId" id="sellerDistrictId" class="form-control"><option value="0">Chọn quận / huyện</option>';
        $.each(districts, function() {
            if (this.cityId === $('select[name=sellerCityId]').val()) {
                htmlSellerCityId += '<option value="' + this.id + '" >' + this.name + '</option>';
            }
        });
        htmlSellerCityId += '</select>';
        $('.sellerDistrictId').html(htmlSellerCityId);
        if ($(this).val() === '0') {
            $('.sellerDistrictId').html("");
        }
    });
    $('select[name=receiverCityId]').change(function() {
        var htmlSellerCityId = '<select name="receiverDistrictId" id="receiverDistrictId" class="form-control"><option value="0">Chọn quận / huyện</option>';
        $.each(districts, function() {
            if (this.cityId === $('select[name=receiverCityId]').val()) {
                htmlSellerCityId += '<option value="' + this.id + '" >' + this.name + '</option>';
            }
        });
        htmlSellerCityId += '</select>';
        $('.receiverDistrictId').html(htmlSellerCityId);
        if ($(this).val() === '0') {
            $('.receiverDistrictId').html("");
        }
    });
    $('select[name=buyerCityId]').change(function() {
        var htmlSellerCityId = '<select name="buyerDistrictId" id="buyerDistrictId" class="form-control"><option value="0">Chọn quận / huyện</option>';
        $.each(districts, function() {
            if (this.cityId === $('select[name=buyerCityId]').val()) {
                htmlSellerCityId += '<option value="' + this.id + '" >' + this.name + '</option>';
            }
        });
        htmlSellerCityId += '</select>';
        $('.buyerDistrictId').html(htmlSellerCityId);
        if ($(this).val() === '0') {
            $('.buyerDistrictId').html("");
        }
    });
    if (typeof $('.sellerDistrictId').attr("city") !== 'undefined' && $('.sellerDistrictId').attr("city") !== null && $('.sellerDistrictId').attr("city") !== '') {
        $('select[name=sellerCityId]').val($('.sellerDistrictId').attr("city"));
        if (typeof $('.sellerDistrictId').attr("dist") !== 'undefined' && $('.sellerDistrictId').attr("dist") !== null && $('.sellerDistrictId').attr("dist") !== '') {

            var htmlSellerCityId = '<select name="sellerDistrictId" id="sellerDistrictId" class="form-control"><option value="0">Chọn quận / huyện</option>';
            $.each(districts, function() {
                if (this.cityId === $('select[name=sellerCityId]').val()) {
                    htmlSellerCityId += '<option value="' + this.id + '" >' + this.name + '</option>';
                }
            });
            htmlSellerCityId += '</select>';
            $('.sellerDistrictId').html(htmlSellerCityId);
            $('select[name=sellerDistrictId]').val($('.sellerDistrictId').attr("dist"));
        }
    }
    if (typeof $('.receiverDistrictId').attr("city") !== 'undefined' && $('.receiverDistrictId').attr("city") !== null && $('.receiverDistrictId').attr("city") !== '') {
        $('select[name=receiverCityId]').val($('.receiverDistrictId').attr("city"));
        if (typeof $('.receiverDistrictId').attr("dist") !== 'undefined' && $('.receiverDistrictId').attr("dist") !== null && $('.receiverDistrictId').attr("dist") !== '') {

            var htmlSellerCityId = '<select name="receiverDistrictId" id="receiverDistrictId" class="form-control"><option value="0">Chọn quận / huyện</option>';
            $.each(districts, function() {
                if (this.cityId === $('select[name=receiverCityId]').val()) {
                    htmlSellerCityId += '<option value="' + this.id + '" >' + this.name + '</option>';
                }
            });
            htmlSellerCityId += '</select>';
            $('.receiverDistrictId').html(htmlSellerCityId);
            $('select[name=receiverDistrictId]').val($('.receiverDistrictId').attr("dist"));
        }
    }
    if (typeof $('.buyerDistrictId').attr("city") !== 'undefined' && $('.buyerDistrictId').attr("city") !== null && $('.buyerDistrictId').attr("city") !== '') {
        $('select[name=buyerCityId]').val($('.buyerDistrictId').attr("city"));
        if (typeof $('.buyerDistrictId').attr("dist") !== 'undefined' && $('.buyerDistrictId').attr("dist") !== null && $('.buyerDistrictId').attr("dist") !== '') {

            var htmlSellerCityId = '<select name="buyerDistrictId" id="buyerDistrictId" class="form-control"><option value="0">Chọn quận / huyện</option>';
            $.each(districts, function() {
                if (this.cityId === $('select[name=buyerCityId]').val()) {
                    htmlSellerCityId += '<option value="' + this.id + '" >' + this.name + '</option>';
                }
            });
            htmlSellerCityId += '</select>';
            $('.buyerDistrictId').html(htmlSellerCityId);
            $('select[name=buyerDistrictId]').val($('.buyerDistrictId').attr("dist"));
        }
    }

    $.each(citys, function(i) {
        $.each($('.loadLast'), function() {
            var cityId = $(this).attr("city");
            if(citys[i].id==cityId){
                $('.loadLast[city='+cityId+']').html(citys[i].name);
            }
        });
    });
    $.each(districts, function(i) {
        $.each($('.loadLast'), function() {
            var distId = $(this).attr("dist");
            if(districts[i].id==distId){
                $('.loadLast[dist='+distId+']').html(districts[i].name);
            }
        });
    });
   

};


