itemfollow = {};
itemfollow.init = function() {
    var gtimeNow = $("input[name=timeNow]").val();
    $.each($(".itemTime"), function() {
        var itemId = $(this).find("input[name=itemid]").val();
        var startTime = $(this).find("input[name=startTime_" + itemId + "]").val();
        var endTime = $(this).find("input[name=endTime_" + itemId + "]").val();

        $("p.item_" + itemId).html(itemfollow.countDown(startTime, endTime, gtimeNow));

        setInterval(function() {
            $("p.item_" + itemId).html(itemfollow.countDown(startTime, endTime, gtimeNow));
        }, 1000);
    });
    setInterval(function() {
        gtimeNow = eval(gtimeNow) + 1000;
    }, 1000);
};

itemfollow.countDown = function(startTime, endTime, timeNow) {
    var time = new Date();
    time.setTime(parseInt(startTime));

    var endtime = new Date();
    endtime.setTime(parseInt(endTime));

    var now = new Date();
    now.setTime(parseInt(timeNow));

    var diff = endtime - now.getTime();
    var text = '';
    if (diff <= 0) {
        text = "Đã kết thúc";
    } else {
        var bp_time_difference = Math.floor(diff / 1000);
        text = 'Còn ';
        if (itemfollow.bp_format_seconds(bp_time_difference, 86400, 100000) >= 0)
            text += itemfollow.bp_format_seconds(bp_time_difference, 86400, 100000) + " ngày<br/> ";
        text += '<span class="text-warning fs-18">';
        if (itemfollow.bp_format_seconds(bp_time_difference, 3600, 24) >= 0)
            text += itemfollow.bp_format_seconds(bp_time_difference, 3600, 24) + "h:";
        if (itemfollow.bp_format_seconds(bp_time_difference, 60, 60) >= 0)
            text += itemfollow.bp_format_seconds(bp_time_difference, 60, 60) + "':";
        if (itemfollow.bp_format_seconds(bp_time_difference, 1, 60) >= 0)
            text += itemfollow.bp_format_seconds(bp_time_difference, 1, 60);
    }
    return text + "</span>";
};

itemfollow.bp_format_seconds = function(secs, num1, num2) {
    var num = ((Math.floor(secs / num1)) % num2).toString();
    if (num.length < 2)
        num = "0" + num;
    return "" + num + "";
};


itemfollow.note = function(id, type) {
    if (type == 'display') {
        $('.note[for=' + id + ']').css({'display': 'block'});
        $('.textNote[for=' + id + ']').css({'display': 'none'});
    } else {
        $('.note[for=' + id + ']').css({'display': 'none'});
        if ($('.textNote[for=' + id + '] strong').text().trim() === 'Ghi chú:') {
            $('.textNote[for=' + id + ']').css({'display': 'none'});
        }
        if ($('.textNote[for=' + id + '] strong').text().trim() !== 'Ghi chú:') {
            $('.textNote[for=' + id + ']').css({'display': 'block'});
        }
    }
};
itemfollow.saveNote = function(id) {
    var id = id;
    var note = $('input[name=note][rel=' + id + ']').val();
    if (note == null || note == '') {
        $('div #hasError').addClass('has-error');
        $('input[name=note][rel=' + id + ']').attr("placeholder", "Nội dung ghi chú không được để trống!");
    } else {
        $('div #hasError').removeClass('has-error');
        $('input[name=note][rel=' + id + ']').removeAttr("placeholder");
        ajax({
            service: '/useritemfollow/savenote.json',
            data: {id: id, note: note},
            loading: false,
            type: 'get',
            done: function(resp) {
                if (resp.success) {
                    $('.textNote[for=' + id + '] strong').html('Ghi chú: ' + resp.data.note + '');
                    $('.note[for=' + id + ']').css({'display': 'none'});
                    $('.textNote[for=' + id + ']').css({'display': 'block'});
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    }
};

itemfollow.checkall = function(obj, namep) {
    var x = obj.checked;
    if (x) {
        $('.' + namep).attr("checked", true);
    } else {
        $('.' + namep).attr("checked", false);
    }
};

itemfollow.del = function() {
    var itemIds = [];
    $(".checkedItem").each(function() {
        if (this.checked) {
            itemIds.push($(this).val());
        }
    });
    if (itemIds.length <= 0) {
        popup.msg("Chưa có sản phẩm nào được chọn!");
    } else {
        popup.confirm('Bạn có chắc chắn muốn xóa những sản phẩm này ra khỏi danh sách sản phẩm quan tâm không?', function() {
            ajax({
                service: '/useritemfollow/del.json',
                data: itemIds,
                loading: true,
                type: 'post',
                contentType: 'json',
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
        });
    }
};


itemfollow.delSellerFollow = function() {
    var itemIds = [];
    $(".checkedItem").each(function() {
        if (this.checked) {
            itemIds.push($(this).val());
        }
    });
    if (itemIds.length <= 0) {
        popup.msg("Chưa có người bán nào được chọn!");
    } else {
        popup.confirm('Bạn có chắc chắn muốn xóa những người bán này ra khỏi danh sách người bán quan tâm không?', function() {
            ajax({
                service: '/useritemfollow/delsellerfollow.json',
                data: itemIds,
                loading: true,
                type: 'post',
                contentType: 'json',
                done: function(resp) {
                    if (resp.success) {
                        popup.msg(resp.message, function() {
                            location.reload();
                        });
                    } else {

                    }
                }
            });
        });
    }
};
//itemfollow.noteSellerFollow = function() {
//    popup.open('popup-note', 'Ghi chú', template('/user/tpl/sellerfollownote.tpl'), [
//        {
//            title: 'Thêm',
//            style: 'btn-info',
//            fn: function() {
//
//            }
//        },
//        {
//            title: 'Hủy',
//            style: 'btn-default',
//            fn: function() {
//                popup.close('popup-note');
//            }
//        }
//    ]);
//};
itemfollow.changPage = function(page) {
    if (typeof page == 'undefined' || page == '') {
        page = $('input[name=page]').val();
    }
    if (isNaN(page)) {
        popup.msg("Trang chuyển đến phải là số");
        return false;
    }
    if (window.location.href.indexOf('?page=') > -1 || window.location.href.indexOf('&page=') > -1) {
        window.location.href = window.location.href.splice("?page=")[0] + page + window.location.href.split("page=")[1];
    } else {
        window.location.href = window.location.href + "?page=" + page;
    }
};

itemfollow.urlParam = function() {
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

itemfollow.search = function(page) {
    var urlParams = itemfollow.urlParam();
    if (page == null || page < 0) {
        page = 1;
    }
    urlParams.page = page;
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
    location.href = "/user/quan-tam-cua-toi.html" + queryString;
};
