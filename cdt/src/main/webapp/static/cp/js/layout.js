var layout = {};
layout.homeinit = function() {
    layout.breadcrumb([
        ["Trang chủ"]
    ]);
};

layout.breadcrumb = function(_data) {
    var breadcrumb = ' <li><a href="' + baseUrl + '/cp/index.html"><span class="glyphicon glyphicon-home"></span></a></li>';
    if (typeof _data !== "undefined") {
        $.each(_data, function(index, bcrumb) {
            if (typeof bcrumb === 'object' && bcrumb.length >= 2) {
                breadcrumb += '<li><a href="' + bcrumb[1] + '" ' + (bcrumb.length >= 3 ? 'target="_blank"' : '') + ' >' + bcrumb[0] + '</a></li>';
            } else if (typeof bcrumb === 'object' && bcrumb.length === 1) {
                breadcrumb += '<li class="active" >' + bcrumb[0] + '</li>';
            }
        });
    }
    $("ol[data-rel=breadcrumb]").html(breadcrumb);
};
