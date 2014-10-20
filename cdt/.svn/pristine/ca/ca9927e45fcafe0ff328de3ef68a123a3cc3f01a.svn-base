auction = {};
auction.init = function() {
    var gtimeNow = $("input[name=timeNow]").val();
    $.each($(".itemTime"), function() {
        var itemId = $(this).find("input[name=itemid]").val();
        var startTime = $(this).find("input[name=startTime_" + itemId + "]").val();
        var endTime = $(this).find("input[name=endTime_" + itemId + "]").val();

        $("p.item_" + itemId).html(auction.countDown(startTime, endTime, gtimeNow));

        setInterval(function() {
            $("p.item_" + itemId).html(auction.countDown(startTime, endTime, gtimeNow));
        }, 1000);
    });
    setInterval(function() {
        gtimeNow = eval(gtimeNow) + 1000;
    }, 1000);
};

auction.countDown = function(startTime, endTime, timeNow) {
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
        if (auction.bp_format_seconds(bp_time_difference, 86400, 100000) >= 0)
            text += auction.bp_format_seconds(bp_time_difference, 86400, 100000) + " ngày<br/> ";
        text += '<span class="text-warning fs-18">';
        if (auction.bp_format_seconds(bp_time_difference, 3600, 24) >= 0)
            text += auction.bp_format_seconds(bp_time_difference, 3600, 24) + "h:";
        if (auction.bp_format_seconds(bp_time_difference, 60, 60) >= 0)
            text += auction.bp_format_seconds(bp_time_difference, 60, 60) + "':";
        if (auction.bp_format_seconds(bp_time_difference, 1, 60) >= 0)
            text += auction.bp_format_seconds(bp_time_difference, 1, 60);
    }
    return text + "</span>";
};

auction.bp_format_seconds = function(secs, num1, num2) {
    var num = ((Math.floor(secs / num1)) % num2).toString();
    if (num.length < 2)
        num = "0" + num;
    return "" + num + "";
};

auction.note = function(id, type) {
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
auction.saveNote = function(id) {
    var id = id;
    var note = $('input[name=note][rel=' + id + ']').val();
    if (note == null || note == '') {
        $('div #hasError').addClass('has-error');
        $('input[name=note][rel=' + id + ']').attr("placeholder", "Nội dung ghi chú không được để trống!");
    } else {
        $('div #hasError').removeClass('has-error');
        $('input[name=note][rel=' + id + ']').removeAttr("placeholder");
        ajax({
            service: '/userauction/savenote.json',
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
auction.del = function() {
    popup.confirm('Bạn có chắc chắn muốn xóa những sản phẩm này?', function() {
        var itemIds = [];
        $.each($('input[name=delItem]:checked'), function() {
            itemIds.push($(this).val());
        });
        if (itemIds.length == 0) {
            popup.msg("Bạn phải chọn sản phẩm để xóa!");
        } else {
            ajax({
                service: '/userauction/del.json',
                data: itemIds,
                loading: true,
                type: 'post',
                contentType: 'json',
                done: function(resp) {
                    if (resp.success) {
                        location.reload();
                    } else {
                        popup.msg(resp.message);
                    }
                }
            });
        }
    });

};
auction.search = function() {
    var keyword = $('input[name=keyword]').val();
    if (keyword.trim() == '') {
        popup.msg("Bạn phải nhập tên hoặc mã sản phẩm để tìm kiếm!");
    } else {
        if (window.location.href.indexOf('?') > -1 && window.location.href.indexOf('=') > -1) {
            window.location.href = window.location.href + "&keyword=" + keyword;
        } else {
            window.location.href = window.location.href + "?keyword=" + keyword;
        }
    }
};

auction.bidHistory = function(itemId) {
    ajax({
        service: '/auction/bidhistory.json',
        data: {itemId: itemId},
        loading: false,
        type: 'get',
        success: function(resp) {
            if (resp.success) {
                var bidHistory = resp.data["bidHistory"];
                var bider = resp.data["bider"];
                var userCount = resp.data["userCount"];
                popup.open('popup-bid-history', 'Có ' + bidHistory.length + ' lượt đấu', template('/market/tpl/item/bidhistory.tpl', {bidHistory: bidHistory, bider: bider, userCount: userCount}), [
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-bid-history');
                        }
                    }
                ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
auction.changPage = function(page) {
    if (typeof page == 'undefined' || page == '') {
        page = $('input[name=page]').val();
    }
    if (isNaN(page)) {
        popup.msg("Trang chuyển đến phải là số");
        return false;
    }
    if (window.location.href.indexOf('?page=') > -1 || window.location.href.indexOf('&page=') > -1) {
        window.location.href = window.location.href.splice("?page=")[0]+ page+ window.location.href.split("page=")[1];
    } else {
        window.location.href = window.location.href + "?page=" + page;
    }

};



