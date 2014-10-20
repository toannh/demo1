$(document).ready(function () {
    $('input[name=navsearch]').keyup(function (event) {
        $('input[name=headsearch]').val($(this).val());
        if (event.keyCode === 13) {
            search.go();
        }
    });
    $('input[name=headsearch]').keyup(function (event) {
        $('input[name=navsearch]').val($(this).val());
        if (event.keyCode === 13) {
            search.go();
        }
    });
    var url = window.location.pathname;
    if (url.search("mua-ban/") > 0) {
        var i = 2;
        if (url.search("mua-ban/model") > 0) {
            i = 3;
        }
        search.changeCat(url.split("/")[i]);
    }
    if (typeof textUtils.urlParam().keyword !== "undefined" && textUtils.urlParam().keyword !== null && textUtils.urlParam().keyword !== '' && textUtils.urlParam().keyword !== '') {
        $('input[name=headsearch]').val(textUtils.urlParam().keyword.replace(/\+/g, " "));
    }
});

var search = {};
search.catId = null;

search.changeCat = function (id) {
    search.catId = {};
    search.catId.id = id;
    search.catId.name = '';
    $('.navbar-search-cat li').removeClass('active');
    if (id) {
        ajax({
            service: '/cpservice/global/category/get.json',
            data: {id: id},
            loading: false,
            done: function (resp) {
                search.catId.name = resp.data.name;
            }
        });
        //search.catId.name = $('.navbar-search-cat li[for_cate=' + id + '] a').text();
        $('.navbar-search-cat li[for_cate=' + id + ']').addClass('active');
    }
};

search.go = function () {
    var _keyword = $('input[name=headsearch]').val();
    var keyword = textUtils.createKeyword(_keyword);
    if (keyword === '') {
//        return;
    } else {
        //set cookie
        var tagSearch = textUtils.getCookie("tagSearch");
        if (tagSearch === null || tagSearch === "") {
            tagSearch = base64.encode(JSON.stringify({}));
        }
        tagSearch = JSON.parse(base64.decode(tagSearch));
        tagSearch[textUtils.createAlias(_keyword)] = textUtils.createKeyword(_keyword);
        textUtils.setCookie("tagSearch", base64.encode(JSON.stringify(tagSearch)), 360);
    }

    var url = window.location.pathname;
    if (url.search("mua-ban/model") > 0) {
        var modelSearch = {};
        modelSearch.pageIndex = 0;
        modelSearch.keyword = keyword;
        modelSearch.categoryId = search.catId.id;
        modelSearch.manufacturerIds = [];
        modelSearch.properties = [];
        document.location = urlUtils.modelBrowseUrl(modelSearch, search.catId.name, false);
    } else {
        var itemSearch = {};
        var name = '';
        itemSearch.keyword = keyword;
        itemSearch.pageIndex = 0;
        itemSearch.status = 1;
        if (search.catId !== null) {
            itemSearch.categoryIds = [];
            itemSearch.categoryIds[0] = search.catId.id;
            name = search.catId.name;
            document.location = urlUtils.browseUrl(itemSearch, name, false);
        } else {
            document.location = baseUrl + "/s/" + keyword + ".html";
        }
    }
};