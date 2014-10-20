upschedule = {};
upschedule.price = 500;

upschedule.initHistory = function() {
    $('.startTime').timeSelect();
    $('.endTime').timeSelect();
};

upschedule.init = function() {
    $("input[name=checkposting]").change(function() {
        if ($(this).is(":checked")) {
            $("input[for=checkall]").attr("checked", true);
        } else {
            $("input[for=checkall]").attr("checked", false);
        }
    });
};
upschedule.checkadd = function(obj) {
    var name = obj.name;
    if ($(obj).is(":checked")) {
        $("." + name).attr({checked: true});
    } else {
        $("." + name).attr({checked: false});
    }
};
upschedule.getSelectedItems = function() {
    var listItems = new Array();
    $('input[for=checkall]:checked').each(function(i, el) {
        listItems.push($(el).attr("value"));
    });
    return listItems;
};
upschedule.removes = function() {
    var ids = new Array();
    ids = upschedule.getSelectedItems();
    if (ids.length == 0) {
        popup.msg("Vui lòng chọn ít nhất 1 tin up.");
    } else {
        popup.confirm('Bạn có chắc muốn xóa các tin up này ?', function() {
            ajax({
                service: '/upschedule/removes.json',
                data: {ids: JSON.stringify(ids)},
                loading: false,
                done: function(resp) {
                    popup.msg(resp.message);
                    setTimeout(function() {
                        location.reload();
                    }, 2000);
                }
            });
        });
    }
};
function millisToDaysHoursMinutes(date) {
    var splitT = date.split('/');
    var dd = splitT[0];
    var mm = splitT[1];
    var yy = splitT[2];
    var dates = new Date(yy, mm, dd);
    return Date.parse(mm + '.' + dd + '.' + yy);
}
;

upschedule.payment = function(items, upnow) {
    var xengUpTin = 0;
    var maxquantity = Math.floor(($("input[name=maxquantity]").val() !== "") ? $("input[name=maxquantity]").val() : 1);
    if (maxquantity <= 0) {
        popup.msg('Số xèng trong tài khoản không đủ để thực hiện giao dịch này');
        return false;
    }
    if (items !== "" && items.length > 0) {
        ajax({
            service: '/item/getitems.json',
            data: {ids: JSON.stringify(items)},
            loading: false,
            done: function(resp) {
                if (resp.success) {
                    var xengUp = (resp.data['xeng'] > 0) ? resp.data['xeng'] : 0;
                    popup.open('popup-upschedule', 'Cài đặt Up tin theo lịch', template('/user/tpl/posting.tpl', {data: resp.data['items'], xeng: xengUp}), [
                        {
                            title: 'Thanh toán',
                            style: 'btn-danger',
                            fn: function() {
                                var ups = [];
                                var type = $('span[rel=upCal]').attr("style");
                                type = (typeof (type) !== 'undefined' && $('span[rel=upCal]').attr("style").indexOf('block') > 0) ? 1 : 0;
                                var quantity = $("input[name=quantity]").val() * items.length;
                                if (type <= 0) {
                                    quantity = 2 * items.length;
                                }
                                var scheduleTime = [];
                                var upTime = [];
                                if (type > 0) {
                                    $.each($('input[for=dayall]'), function() {
                                        if ($(this).is(":checked")) {
                                            scheduleTime.push($(this).val());
                                        }
                                    });
                                    $.each($('span[rel=timeofday] div.col-row'), function() {
                                        var time = $(this).attr('uptime');
                                        if (time !== '') {
                                            time = time.replace("_", ":");
                                            time = upschedule.convertTime(time);
                                            if (upTime.indexOf(time) === -1) {
                                                upTime.push(time);
                                            }
                                        }
                                    });
                                    if (quantity <= 0 || scheduleTime.length === 0 || upTime.length === 0) {
                                        popup.msg("Bạn cần cấu hình đầy đủ thông tin, Số lượt up và thời gian up tin cho sản phẩm");
                                        return false;
                                    }
                                }
                                $.each(resp.data['items'], function() {
                                    var number = $("input[name=quantity]").val();
                                    var up = {};
                                    up.itemId = this.id;
                                    up.quantity = number;
                                    up.upDay = scheduleTime;
                                    up.upTime = upTime;
                                    ups.push(up);
                                });
                                //Call service save
                                ajax({
                                    service: '/upschedule/add.json',
                                    data: {upSchedule: ups, type: type},
                                    type: "post",
                                    loading: false,
                                    contentType: 'json',
                                    done: function(resp) {
                                        if (resp.success) {
                                            popup.open('popup-upschedule', 'Cài đặt Up tin theo lịch', template('/user/tpl/messuptin.tpl', {xengs: xengUp - (quantity * 500)}), [
                                                {
                                                    title: 'Đóng',
                                                    className: 'button-gray',
                                                    fn: function() {
                                                        popup.close("popup-upschedule");
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
                        }
                    ], 'modal-lg');
                    setTimeout(function() {
                        if (upnow === 'now') {
                            $('span[rel=upCal]').css({"display": "none"});
                            $('.nap-xeng').css({"display": "block"});
                            $("a[rel=uptype][value=now]").parent("li").addClass("active");
                            $("a[rel=uptype][value=cal]").parent("li").removeClass("active");
                        } else {
                            $('span[rel=upCal]').css({"display": "block"});
                            $('.nap-xeng').css({"display": "none"});
                        }
                        $("a[rel=uptype]").click(function() {
                            if ($(this).attr("value") === 'now') {
                                $('span[rel=upCal]').css({"display": "none"});
                                $('.nap-xeng').css({"display": "block"});
                            } else {
                                $('span[rel=upCal]').css({"display": "block"});
                                $('.nap-xeng').css({"display": "none"});
                            }
                        });
                        //draw quantity up
                        var maxUp = parseFloat(xengUp / upschedule.price);
                        var quantityUp = maxUp.toMoney(0, ',', '.');
                        $("p[rel=maxup]").text("Tối đa " + quantityUp + " lần/" + items.length + ' sản phẩm');
                        $("input[name=quantity]").change(function() {
                            var quantity = eval($(this).val()) * items.length;
                            if (isNaN(quantity) || quantity < 0 || eval(quantity) > maxUp) {
                                $(this).val(0);
                                quantity = 0;
                            }
                            $("p[rel=maxup]").text("Còn " + eval(maxUp - quantity).toMoney(0, ',', '.') + '/' + quantityUp + " lần");
                            upschedule.upchange(items.length);
                            return false;
                        });
                        //Select day 
                        $("input[name=dayall]").change(function() {
                            $("input[for=dayall]").attr({"checked": $(this).is(":checked")});
                            upschedule.upchange(items.length);
                        });
                        //select time of day
                        $('#timepicker').timepicker({
                            showOn: 'button',
                            button: '.addTime',
                            onClose: function(dateText, inst) {
                                dateText = (dateText === '') ? "00:00" : dateText;
                                var i = dateText.replace(":", "_");
                                if ($('div[uptime=' + i + ']').length > 0) {
                                    return false;
                                }
                                $('div[error=quantityUp]').text();
                                var html = '<div class="col-row" uptime="' + i + '" ><div class="date-picker-block">\
                                    <input type="text" disabled value="' + dateText + '" class="form-control" placeholder="_ _ : _ _">\
                                    <span style="cursor: pointer" class="glyphicon glyphicon-remove" event="remove_' + i + '"  ></span>\
                                </div></div>';
                                $("span[rel=timeofday]").append(html);
                                $('span[event=remove_' + i + ']').click(function() {
                                    $(this).parent().parent().remove();
                                    upschedule.upchange(items.length);
                                });
                                upschedule.upchange(items.length);
                            }
                        });
                        $(".ui-datepicker-trigger").text("Thêm");
                        $(".ui-datepicker-trigger").addClass("btn btn-primary");
                    }, 500);
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    } else {
        popup.msg("Bạn cần chọn danh sách sản phẩm để đăng tin");
    }
};


upschedule.convertTime = function(_time) {
    _time = _time + '';
    var time = _time.split(":");
    var timemap = 0;

    if (time[0] < 0 || time[1] < 0 || time[1] > 59 || time[0] > 23 || time[1] < 0 || time[1] > 59) {
        timemap = 0;
    } else {
        var date = new Date(1970, 0, 01, time[0], time[1], 00);
        timemap = date.getTime();
    }
    return timemap;
};

upschedule.upchange = function(itemcount) {
    //count up quantity
    var quantity = $("input[name=quantity]").val();
    var countUp = $("span[rel=timeofday] > div.col-row").length;
    $("p[rel=quantityUp]").html('Bạn đang cài: ' + countUp + ' lần/ngày');
    //draw table info uptin
    var html = '<tr><td  class="text-center">' + itemcount + '</td><td class="text-center" >' + quantity + '</td><td class="text-center" >' +
            eval(quantity * itemcount) + '</td><td class="text-center" >' + (eval(quantity * itemcount) * upschedule.price) + '</td></tr>';
    $("table[rel=upinfo] > tbody").html(html);
};

Number.prototype.toMoney = function(decimals, decimal_sep, thousands_sep) {
    var n = this,
            c = isNaN(decimals) ? 2 : Math.abs(decimals), //if decimal is zero we must take it, it means user does not want to show any decimal
            d = decimal_sep || '.', //if no decimal separator is passed we use the dot as default decimal separator (we MUST use a decimal separator)

            t = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep, //if you don't want to use a thousands separator you can pass empty string as thousands_sep value

            sign = (n < 0) ? '-' : '',
            //extracting the absolute value of the integer part of the number and converting to string
            i = parseInt(n = Math.abs(n).toFixed(c)) + '',
            j = ((j = i.length) > 3) ? j % 3 : 0;
    return sign + (j ? i.substr(0, j) + t : '') + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : '');
};
// get up schedule history list by scheduleId
upschedule.getUpScheduleHistory = function(scheduleId) {
    ajax({
        service: '/upschedule/getbyupScheduleid.json',
        data: {id: scheduleId},
        loading: false,
        type: 'GET',
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-upScheduleHistory', 'Lịch sử up tin', template('/user/tpl/upscheduleHistory.tpl'), [
                    {
                        title: 'Đóng',
                        style: 'btn-success',
                        fn: function() {
                            popup.close('popup-upScheduleHistory');
                        }
                    }
                ]);
                console.log(resp);
                $('.totalUp').html(resp.data.length + ' lượt');
                var i = 1;
                // clear table first
                $('.history-up table').html('');
                jQuery.each(resp.data, function(index, row) {
                    var date = new Date(row.scheduleTime);
                    var dayInWeek = date.customFormat('#DDD#');
                    var hourAndMinute = date.customFormat('#hh#h#mm#');
                    var dayMonthYear = date.customFormat('#DD#.#MM#.#YYYY#');
                    var tr = "<tr><td width='5%'>" + i + "</td>\
                                <td>" + hourAndMinute + " " + dayInWeek + " ngày " + dayMonthYear + "</td>\
                                </tr>";
                    $('.history-up table').append(tr);
                    i++;
                });
            } else {
                popup.msg('Có lỗi xảy ra trong quá trình truyền dữ liệu !');
            }
        }
    });
};
Date.prototype.customFormat = function(formatString) {
    var YYYY, YY, MMMM, MMM, MM, M, DDDD, DDD, DD, D, hhh, hh, h, mm, m, ss, s, ampm, AMPM, dMod, th;
    var dateObject = this;
    YY = ((YYYY = dateObject.getFullYear()) + "").slice(-2);
    MM = (M = dateObject.getMonth() + 1) < 10 ? ('0' + M) : M;
    MMM = (MMMM = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"][M - 1]).substring(0, 3);
    DD = (D = dateObject.getDate()) < 10 ? ('0' + D) : D;
    DDD = (DDDD = ["Chủ nhật", "Thứ hai", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7"][dateObject.getDay()]);
    th = (D >= 10 && D <= 20) ? 'th' : ((dMod = D % 10) == 1) ? 'st' : (dMod == 2) ? 'nd' : (dMod == 3) ? 'rd' : 'th';
    formatString = formatString.replace("#YYYY#", YYYY).replace("#YY#", YY).replace("#MMMM#", MMMM).replace("#MMM#", MMM).replace("#MM#", MM).replace("#M#", M).replace("#DDDD#", DDDD).replace("#DDD#", DDD).replace("#DD#", DD).replace("#D#", D).replace("#th#", th);
    h = (hhh = dateObject.getHours());
    if (h == 0)
        h = 24;
    if (h > 12)
        h -= 12;
    hh = h < 10 ? ('0' + h) : h;
    AMPM = (ampm = hhh < 12 ? 'am' : 'pm').toUpperCase();
    mm = (m = dateObject.getMinutes()) < 10 ? ('0' + m) : m;
    ss = (s = dateObject.getSeconds()) < 10 ? ('0' + s) : s;
    return formatString.replace("#hhh#", hhh).replace("#hh#", hh).replace("#h#", h).replace("#mm#", mm).replace("#m#", m).replace("#ss#", ss).replace("#s#", s).replace("#ampm#", ampm).replace("#AMPM#", AMPM);
};


upschedule.search = function() {
    var urlParams = upschedule.urlParam();

    if ($("select[name=run]").val() != "0") {
        urlParams.run = $("select[name=run]").val();
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
    if ($("select[name=run]").val() == "0") {
        queryString = "";
    }
    location.href = "/user/posting.html" + queryString;
};

upschedule.urlParam = function() {
    var urlParams;
    var match,
            pl = /\+/g, // Regex for replacing addition symbol with a space
            search = /([^&=]+)=?([^&]*)/g,
            decode = function(s) {
                return decodeURIComponent(s.replace(pl, " "));
            },
            query = window.location.search.substring(1);
    urlParams = {};
    while (match = search.exec(query))
        urlParams[decode(match[1])] = decode(match[2]);
    return urlParams;
};