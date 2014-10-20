searchhistory = {};
searchhistory.init = function() {
    searchhistory.loadSearchHistory();
};

searchhistory.loadSearchHistory = function() {
    var search = textUtils.getCookie('tagSearch');
    if (search === null || search === '') {
        search = {};
    } else {
        search = JSON.parse(base64.decode(search));
    }
    var size = 0;
    var itemSearch = {};
    itemSearch.pageIndex = 0;
    
    itemSearch.status = 1;
    var html = '';
    $.each(search, function(key, val) {
        size += 1;
        itemSearch.keyword = textUtils.createKeyword(val);
        html += '<a href="' + baseUrl + urlUtils.browseUrl(itemSearch, '', false) + '" target="_blank">' + val + '</a>';
    });
    $('div[data-rel=tagSearch]').html(html);
    $('div[data-rel=counttagSearch]').html('Có <b>' + size + '</b> lệnh tìm kiếm của người dùng');
};

searchhistory.del = function() {
    textUtils.setCookie("tagSearch", "", -1);
    popup.msg('Đã xóa lịch sử tìm kiếm của người dùng!', function() {
        location.reload();
    });
};