footerkeyword = {};

footerkeyword.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị xu hướng tìm kiếm", "/cp/footerkeyword.html"],
        ["Danh sách từ khóa tìm kiếm"]
    ]);
};

footerkeyword.genUrl = function() {
    var keyword = $('input[rel=check_keyword]').val();
    keyword = textUtils.createKeyword(keyword);
    $('input[rel=check_url]').val('http://chodientu.vn/s/' + keyword + '.html');
};

footerkeyword.add = function() {
    popup.open('popup-add', 'Thêm mới từ khóa', template('/cp/tpl/footerkeyword/add.tpl'), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function() {
                ajaxSubmit({
                    service: '/cpservice/footerkeyword/add.json',
                    id: 'form-add',
                    contentType: 'json',
                    done: function(rs) {
                        if (rs.success) {
                            popup.msg(rs.message);
                        } else {
                            popup.msg("Thêm mới tin thất bại");
                        }
                        setTimeout(function() {
                            window.location.reload();
                        }, 1000);
                    }
                });
            }
        },
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-add');
            }
        }
    ]);
};


footerkeyword.addnew = function() {
    var keyword = $('input[rel=check_keyword]').val();
    var url = $('input[rel=check_url]').val();
    var common = $('input[name=common]').is(':checked');
    var footerkeyword = {};
    footerkeyword.keyword = keyword;
    footerkeyword.url = url;
    footerkeyword.common = common;
    ajax({
        service: '/cpservice/footerkeyword/add.json',
        data: footerkeyword,
        contentType: 'json',
        type: 'post',
        loading: false,
        done: function(rs) {
            $('input[rel=check_keyword]').parent('.form-group').removeClass('has-error');
            $('input[rel=check_url]').parent('.form-group').removeClass('has-error');
            $('span[rel=check_keyword]').text("");
            $('span[rel=check_url]').text("");
            if (rs.success) {
                var html = template('/cp/tpl/footerkeyword/rowkeyword.tpl', {data: rs.data});
                $('tbody[data-rel="content"]').prepend(html);
                $('input[rel=check_keyword]').val("");
                $('input[rel=check_url]').val("");
            } else {
                $.each(rs.data, function(bbbb, aaaa) {
                    $('input[rel=check_' + bbbb + ']').parent('.form-group').addClass('has-error');
                    $('span[rel=check_' + bbbb + ']').text(aaaa);
                });
            }
        }
    });
};


footerkeyword.changePosition = function(id, position) {
    ajax({
        data: {id: id, position: position},
        service: "/cpservice/footerkeyword/changepositon.json",
        done: function(resp) {
            if (resp.success) {
                document.location = $(location).attr('href');
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
footerkeyword.changeStatus = function(id) {
    ajax({
        service: '/cpservice/footerkeyword/changestatus.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                if (resp.data.active) {
                    $('a[editStatus=' + resp.data.id + ']').html('<img src="' + staticUrl + '/cp/img/icon-enable.png">');
                } else {
                    $('a[editStatus=' + resp.data.id + ']').html('<img src="' + staticUrl + '/cp/img/icon-disable.png">');
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });
};


footerkeyword.changeCommon = function(id) {
    ajax({
        service: '/cpservice/footerkeyword/changecommon.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                if (resp.data.common) {
                    $('a[editCommon=' + resp.data.id + ']').html('<img src="' + staticUrl + '/cp/img/icon-enable.png">');
                } else {
                    $('a[editCommon=' + resp.data.id + ']').html('<img src="' + staticUrl + '/cp/img/icon-disable.png">');
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });
};


footerkeyword.changeKeyword = function(id, keyword) {
    ajax({
        service: '/cpservice/footerkeyword/changekeyword.json',
        data: {id: id, keyword: keyword},
        type: 'post',
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
footerkeyword.changeUrl = function(id, url) {
    ajax({
        service: '/cpservice/footerkeyword/changeurl.json',
        data: {url: url, id: id},
        type: 'post',
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
footerkeyword.del = function(id) {
    var idCheck = id;
    ajax({
        service: '/cpservice/footerkeyword/del.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                $('tr[rel=' + idCheck + ']').remove();
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

$(document).ready(function() {
    $('input[name=keyword]').keyup(function(event) {
        if (event.keyCode === 13) {
            footerkeyword.addnew();
        }
    });
    $('input[name=url]').keyup(function(event) {
        if (event.keyCode === 13) {
            footerkeyword.addnew();
        }
    });
});