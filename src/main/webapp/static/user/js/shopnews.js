shopnews = {};

shopnews.init = function () {
    $('.timeselectStart').timeSelect();
    $('.timeselectEnd').timeSelect();
};
shopnews.initAddnews = function () {
    editor('detail');

    $('input[id=fileupload]').change(function () {
        $('#valueImage').text($(this).val());
    });
};
shopnews.addNews = function () {
    ajaxUpload({
        service: '/shopnews/add.json',
        id: 'form-add-news',
        type: 'post',
        loading: false,
        done: function (resp) {
            if (resp.success) {
                if (resp.message !== 'POST_NEWS_FAIL') {
                    xengplus.plus(50);
                }
                popup.msg("Bài tin đã được thêm thành công trên hệ thống!", function () {
                    location.href = baseUrl + '/user/shop-news.html';
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
shopnews.editNews = function () {
    ajaxUpload({
        service: '/shopnews/edit.json',
        id: 'form-add-news',
        type: 'post',
        loading: false,
        done: function (resp) {
            if (resp.success) {
                popup.msg(resp.message, function () {
                    location.href = baseUrl + '/user/shop-news.html';
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

shopnews.del = function (id) {
    popup.confirm("Bạn có chắc muốn xóa bài tin này?", function () {
        ajax({
            service: '/shopnews/del.json',
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

shopnews.checkall = function (obj, namep) {
    var x = obj.checked;
    if (x) {
        $('.' + namep).attr("checked", true);
    } else {
        $('.' + namep).attr("checked", false);
    }
};

shopnews.delAll = function () {
    var ids = "";
    $(".checkedItem").each(function () {
        if (this.checked) {
            var name = this.name;
            ids += name + ",";
        }
    });

    if (ids.length <= 0) {
        popup.msg("Chưa có bài tin nào được chọn!");
    } else {
        popup.confirm('Bạn có chắc muốn xóa những lựa chọn này', function () {
            ajax({
                service: '/shopnews/delall.json',
                data: {
                    ids: ids
                },
                datatype: "json",
                done: function (resp) {
                    if (resp.success) {
                        popup.msg("Xóa thành công danh sách được chọn", function () {
                            location.reload();
                        });
                    }
                }
            });
        });
    }
};
shopnews.edit = function (id) {
    location.href = baseUrl + '/user/shop-add-news.html?id=' + id + '';
};