vipitem = {};
var amounts = 30000;
vipitem.dataItem = {};
vipitem.coutxeng = 0;
vipitem.dataVipItems = new Array();

vipitem.upVipItem = function() {
    listItems = item.getSelectedItems();
    if (listItems.length > 0) {
        vipitem.payment(listItems);
    } else {
        popup.msg("Bạn hãy chọn sản phẩm để tiếp tục");
    }
};
vipitem.millisToDaysHoursMinutes = function(date) {
    var splitT = date.split('/');
    var newDate = splitT[1] + "," + splitT[0] + "," + splitT[2];
    var timeStamp = new Date(newDate).getTime()
    return timeStamp;
}

vipitem.payment = function(items) {
    var date = new Date(new Date().getTime());
    var dateNoconvert = date.getDate() + '/0' + (date.getMonth() + 1) + '/' + date.getFullYear();
    if (items != "" && items.length > 0) {
        ajax({
            service: '/item/getitems.json',
            data: {ids: JSON.stringify(items)},
            loading: false,
            done: function(resp) {
                vipitem.dataItem = resp.data['items'];
                var xengTotal = resp.data['xeng'];
                if (resp.success) {
                    popup.open('popup-vipItem', 'Mua tin VIP', template('/user/tpl/vip.tpl', {data: resp.data['items'], xeng: resp.data['xeng']}), [
                        {
                            title: 'Mua tin vip',
                            style: 'btn-info',
                            fn: function() {
                                var datas = new Array();
                                $.each(resp.data['items'], function() {
                                    var idItem = this.id;
                                    var tmp = new Object();
                                    var dateStartNoConvert = $('.select-time-vip input[for=' + this.id + '][pro=startTime]').val();
                                    var dateEndNoConvert = $('.select-time-vip input[for=' + this.id + '][pro=endTime]').val();

                                    var timeFrom = vipitem.millisToDaysHoursMinutes($('.select-time-vip input[for=' + this.id + '][pro=startTime]').val());
                                    var timeTo = vipitem.millisToDaysHoursMinutes($('.select-time-vip input[for=' + this.id + '][pro=endTime]').val());
                                    tmp.itemId = idItem;
                                    if (dateStartNoConvert === dateNoconvert) {
                                        tmp.from = (new Date().getTime());
                                        tmp.to = (timeTo + 86399000);
                                    } else {
                                        tmp.from = timeFrom;
                                        tmp.to = (timeTo + 86399000);
                                    }
                                    datas.push(tmp);
                                });
                                ajax({
                                    data: datas,
                                    service: '/vipitem/add.json',
                                    contentType: 'json',
                                    type: 'post',
                                    loading: false,
                                    done: function(resp) {
                                        console.log(resp);
                                        if (resp.success) {
                                            popup.open('popup-vipItem', 'Thông báo mua tin vip', template('/user/tpl/messtinvip.tpl', {xengs: (xengTotal - vipitem.coutxeng)}), [
                                                {
                                                    title: 'Đóng',
                                                    className: 'button-gray',
                                                    fn: function() {
                                                        popup.close("popup-vipItem");
                                                        location.reload();
                                                    }
                                                }
                                            ]);
                                        } else {
                                            $('.errorMessages').html("");
                                            $.each(resp.data, function(key, val) {
                                                if (key === 'error_Monney') {
                                                    popup.msg(val);
                                                    return false;
                                                }
                                                $('.errorMessages[for=' + key + ']').html(val);
                                            });
                                        }
                                    }
                                });
                            }
                        },
                        {
                            title: 'Hủy',
                            className: 'button-gray',
                            fn: function() {
                                popup.close("popup-vipItem");
                            }
                        }
                    ]);
                    //$('#popup-vipItem').css("width","600px");
                    $('input[name=timeItemVip]').datepicker({dateFormat: 'dd/mm/yy'});
                    $("input[name=timeItemVip]").change(function() {
                        var arrTime = new Array();
                        $.each(vipitem.dataItem, function() {
                            var idItem = this.id;
                            var tmp = new Object();

                            var dateStartNoConvert = $('.select-time-vip input[for=' + this.id + '][pro=startTime]').val();
                            var dateEndNoConvert = $('.select-time-vip input[for=' + this.id + '][pro=endTime]').val();

                            var timeFrom = vipitem.millisToDaysHoursMinutes($('input[for=' + idItem + '][pro=startTime]').val());
                            var timeTo = vipitem.millisToDaysHoursMinutes($('input[for=' + idItem + '][pro=endTime]').val());
                            tmp.itemId = this.id;
                            if (dateStartNoConvert === dateNoconvert) {
                                tmp.from = new Date().getTime();
                                tmp.to = (timeTo + 86399000);
                            } else {
                                tmp.from = timeFrom;
                                tmp.to = (timeTo + 86399000);
                            }
                            if (tmp.to < tmp.from) {
                                $('.errorMessfromages[for=' + this.id + ']').html("Thời gian kết thúc phải lớn hơn thời gian bắt đầu");
                                return false;
                            }
                            arrTime.push(tmp);
                        });
                        //vipitem.dataVipItems= arrTime;
                        var count = 0;
                        $.each(arrTime, function() {
                            count += eval((this.to - this.from) / 1000 / 24 / 60 / 60);
                        });
                        if (count > 0 && count < 1) {
                            count = 1;
                        }
                        count = Math.ceil(count);
                        vipitem.coutxeng = count * amounts;
                        $('#valXeng').html((parseFloat(count * amounts).toMoney(0, ',', '.') !== 'NaN') ? parseFloat(count * amounts).toMoney(0, ',', '.') : 0);
                    });
                } else {
                    return false;
                }
            }
        });
    } else {
        popup.msg("Không tồn tại Item nào được chọn");
    }
};