tagsearchhistory = {};
tagsearchhistory.init = function() {
    $('.timeSelect').timeSelect();
    tagsearchhistory.loadTagSearchByUser();
    tagsearchhistory.loadItemViewed('iconfloatLeft');
};

tagsearchhistory.loadTagSearchByUser = function(page) {
    var tagsearch = {};
    tagsearch.pageIndex = page;
    tagsearch.pageSize = 300;
    if ($('input[name=keyword]').val() !== null && $('input[name=keyword]').val() !== '') {
        tagsearch.keyword = $('input[name=keyword]').val();
    }
    if ($('input[name=createTimeFrom]').val() !== null && $('input[name=createTimeFrom]').val() !== 'undefined') {
        tagsearch.createTimeFrom = $('input[name=createTimeFrom]').val();
    }
    if ($('input[name=createTimeTo]').val() !== null && $('input[name=createTimeTo]').val() !== 'undefined') {
        tagsearch.createTimeTo = $('input[name=createTimeTo]').val();
    }
    ajax({
        service: '/tag/loadtagbyuser.json',
        data: tagsearch,
        loading: false,
        type: 'POST',
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {
                var html = '';
                if (resp.data.dataCount > 0) {
                    var itemSearch = {};
                    itemSearch.pageIndex = 0;
                    itemSearch.status = 1;
                    $.each(resp.data.data, function() {
                        itemSearch.keyword = textUtils.createKeyword(this.keyword);
                        html += '<a href="' + baseUrl + urlUtils.browseUrl(itemSearch, '', false) + '" target="_blank">' + this.keyword + '</a>';
                    });
                    $('div[data-rel=counttagSearch]').html('Có <b>' + resp.data.dataCount + '</b> lệnh tìm kiếm của người dùng');
                    var page = '';
                    if (resp.data.pageIndex > 2) {
                        page += '<li><a href="javascript:;" onclick="tagsearchhistory.nextPage(' + eval(resp.data.pageIndex) + ');" >«</a></li>';
                    }
                    if (resp.data.pageIndex >= 3) {
                        page += '<li><a href="javascript:;" onclick="tagsearchhistory.nextPage(' + eval(resp.data.pageIndex - 2) + ');">' + eval(resp.data.pageIndex - 2) + '</a></li>';
                    }
                    if (resp.data.pageIndex >= 2) {
                        page += '<li><a href="javascript:;" onclick="tagsearchhistory.nextPage(' + eval(resp.data.pageIndex - 1) + ');" >' + eval(resp.data.pageIndex - 1) + '</a></li>';
                    }
                    if (resp.data.pageIndex >= 1) {
                        page += '<li><a href="javascript:;" onclick="tagsearchhistory.nextPage(' + eval(resp.data.pageIndex) + ');">' + eval(resp.data.pageIndex) + '</a></li>';
                    }
                    page += ' <li class="active" ><a>' + eval(resp.data.pageIndex + 1) + '</a></li>';
                    if (resp.data.pageCount - resp.data.pageIndex > 1) {
                        page += '<li><a href="javascript:;" onclick="tagsearchhistory.nextPage(' + eval(resp.data.pageIndex + 2) + ');" >' + eval(resp.data.pageIndex + 2) + '</a></li>';
                    }
                    if (resp.data.pageCount - resp.data.pageIndex > 2) {
                        page += '<li><a href="javascript:;" onclick="tagsearchhistory.nextPage(' + eval(resp.data.pageIndex + 3) + ');">' + eval(resp.data.pageIndex + 3) + '</a></li>';
                    }
                    if (resp.data.pageCount - resp.data.pageIndex > 2) {
                        page += '<li><a href="javascript:;" onclick="tagsearchhistory.nextPage(' + eval(resp.data.pageIndex + 2) + ');" >»</a></li>';
                    }
                    $('ul[data-rel=pageSearchHistory]').html(page);
                } else {
                    html = '<h4 class="text-danger">Bạn chưa tìm kiếm từ khóa nào!</h4>';
                    $('div[data-rel=counttagSearch]').html('');
                    $('div[data-rel=pageSearchHistory]').html('');
                }
                $('div[data-rel=tagSearch]').html(html);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};


tagsearchhistory.loadItemViewed = function(positionShow) {
    var itemIds = textUtils.getCookie('itemviews');
    var ids = JSON.parse(itemIds);
    ids = JSON.parse(ids);
    ajax({
        service: '/item/getitembyids.json?positionShow=' + positionShow,
        data: ids,
        loading: false,
        type: 'POST',
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {
                if (positionShow === 'iconfloatLeft') {
                    var data = resp.data['itemviewdList'];
                    if (data.length > 0) {
                        $('div[data-rel=aaaaaaaaaaaaa]').html('');
                        for (var i = 0; i < (data.length > 7 ? 7 : data.length); i++) {
                            $('div[data-rel=aaaaaaaaaaaaa]').append(template('/market/tpl/itemIconleftDetail.tpl', {data: data[i]}));
                        }
                    }
                }
            }
        }
    });
};


tagsearchhistory.nextPage = function(page) {
    var urlParams = tagsearchhistory.urlParam();
    urlParams.page = page;
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
    location.href = "/lich-su-tim-kiem-cua-ban.html" + queryString;
};
tagsearchhistory.urlParam = function() {
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

tagsearchhistory.changPage = function(page) {
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