message = {};

message.init = function() {
    var cuIds = message.readCookie("listMessageBox");
    if (typeof cuIds == 'undefined' || cuIds == '') {
        cuIds = [];
    }
    var fags = true;
    $('input[for=checkall]').each(function() {
        var cuIds = message.readCookie("listMessageBox");
        if (typeof cuIds != 'undefined') {
            $.each(cuIds, function() {
                $("input[for=checkall][value=" + this + "]").attr("checked", true);
            });
            if (!$(this).is(":checked")) {
                fags = false;
            }
        }
    });
    if (fags) {
        $("input[name=checkall]").attr("checked", true);
    }
    $("input[name=checkall]").change(function() {
        var cuIdsb = message.readCookie("listMessageBox");
        var listItems = new Array();
        if ($(this).is(":checked")) {
            $("input[for=checkall]").attr("checked", true);
            $('input[for=checkall]:checked').each(function(i, el) {
                listItems.push($(el).attr("value"));
            });
            for (var i = 0; i < listItems.length; i++) {
                cuIdsb.push(listItems[i]);
            }
        } else {
            $("input[for=checkall]").attr("checked", false);
            $('input[for=checkall]').each(function(i, el) {
                listItems.push($(el).attr("value"));
            });
            for (var i = 0; i < listItems.length; i++) {
                cuIdsb.push(listItems[i]);
                var index = cuIdsb.indexOf(listItems[i]);
                if (index > -1) {
                    cuIdsb.splice(index);
                }
            }
        }
        message.setCookie("listMessageBox", message.removeDuplicatesGetCopy(cuIdsb), 30);
    });

    $('input[for=checkall]').each(function() {
        $(this).change(function() {
            var checkIds = message.readCookie("listMessageBox");
            var id = $(this).val();
            if ($(this).is(":checked")) {
                if (checkIds.indexOf(id) === -1) {
                    checkIds.push(id);
                }
            } else {
                var index = checkIds.indexOf(id);
                if (index > -1) {
                    checkIds.splice(index, 1);
                }
            }
            console.log(checkIds);
            message.setCookie("listMessageBox", message.removeDuplicatesGetCopy(checkIds), 30);
        });

    });

    message.getreport();
};
message.removeDuplicatesGetCopy = function(arr) {
    var ret, len, i, j, cur, found;
    ret = [];
    len = arr.length;
    for (i = 0; i < len; i++) {
        cur = arr[i];
        found = false;
        for (j = 0; !found && (j < len); j++) {
            if (cur === arr[j]) {
                if (i === j) {
                    ret.push(cur);
                }
                found = true;
            }
        }
    }
    return ret;
};
message.setCookie = function(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
};
message.readCookie = function(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    var arr = [];
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ')
            c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) {
            arr.push(c.substring(nameEQ.length, c.length));
        }
        //return c.substring(nameEQ.length, c.length);
    }
    var list = [];
    if (typeof (arr[0] != 'undefined') && arr[0] != null) {
        var xxx = arr[0].split(',');
        for (var i = 0; i < xxx.length; i++) {
            list.push(xxx[i]);
        }
    }

    return list;

}
message.send = function() {
    ajaxSubmit({
        service: '/message/sendmessage.json',
        id: 'send-Message',
        contentType: 'json',
        done: function(rs) {
            if (rs.success) {
                popup.msg(rs.message, function() {
                    location.reload();
                });
            } else {
                popup.msg(rs.message);
            }
        }
    });
};
message.search = function(find, page) {
    var urlParams = message.urlParam();
    if (page == null || page < 0) {
        page = 1;
    }
    urlParams.page = page;
    if (find != null && find != '' && find == 'selling') {
        urlParams.find = 'selling';
        $("select[name=find]").val("selling");
    }
    if (find != null && find != '' && find == 'outDate') {
        urlParams.find = 'outDate';
        $("select[name=find]").val("outDate");
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
    location.href = "/user/quan-ly-thu.html" + queryString;
};

message.nextPage = function(page) {
    var urlParams = message.urlParam();
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
    location.href = "/user/quan-ly-thu.html" + queryString;
};
message.urlParam = function() {
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


message.changPage = function(page) {
    if (typeof page == 'undefined' || page == '') {
        page = $('input[name=page]').val();
    }
    if (isNaN(page)) {
        popup.msg("Trang chuyển đến phải là số");
        return false;
    }
    if (window.location.href.indexOf('?') > -1 && window.location.href.indexOf('=') > -1) {
        window.location.href = window.location.href + "&page=" + page;
    } else {
        window.location.href = window.location.href + "?page=" + page;
    }
};
message.getreport = function() {
    ajax({
        service: '/message/getreport.json',
        loading: false,
        done: function(resp) {
            if (resp.success) {
                $('#inboxCount').html('(' + resp.data.inbox + ')');
                $('#outboxCount').html('(' + resp.data.outbox + ')');
            }
        }
    });

};
message.repMessage = function(id) {
    ajax({
        service: '/message/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                $('#messRep').html(template('/user/tpl/detailsendmessage.tpl', {data: resp.data}));
                $('.' + id).removeClass('no-read');
            } else {

            }
        }
    });

};
message.makeNoRead = function() {
    var messIds = message.readCookie("listMessageBox");
    var cout = 0;
    if (typeof messIds === 'undefined' || messIds === '') {
        cout = 0;
    } else {
        cout = messIds.length;
    }
    if (cout !== null && cout > 0) {
        ajax({
            service: '/message/markedunread.json',
            data: messIds,
            contentType: 'json',
            type: 'POST',
            loading: false,
            done: function(resp) {
                if (resp.success) {
                    $(resp.data).each(function(i) {
                        $('.' + resp.data[i].id).addClass('no-read');
                        $('.' + resp.data[i].id + ' input[for="checkall"]').attr("checked", false);
                    });
                    $("input[name=checkall]").attr("checked", false);
                    message.setCookie("listMessageBox", null, -30);
                } else {

                }

            }
        });
    } else {
        popup.msg("Bạn chưa chọn bản ghi nào.");
    }

};
message.deleteMess = function() {
    var messIds = message.readCookie("listMessageBox");
    var cout = 0;
    if (typeof messIds === 'undefined' || messIds === '') {
        cout = 0;
    } else {
        cout = messIds.length;
    }
    if (cout !== null && cout > 0) {
        popup.confirm("Bạn có chắc muốn xóa?", function() {
            ajax({
                service: '/message/deletemess.json',
                data: messIds,
                contentType: 'json',
                type: 'POST',
                loading: false,
                done: function(resp) {
                    if (resp.success) {
                        popup.msg(resp.message);
                        message.setCookie("listMessageBox", null, -30);
                        $(messIds).each(function(i) {
                            $('.' + messIds[i]).hide();
                        });
                        $("input[name=checkall]").attr("checked", false);
                        message.getreport();
                    } else {

                    }

                }
            });
        });
    } else {
        popup.msg("Bạn chưa chọn bản ghi nào.");
    }

};

message.reply = function() {
    ajaxSubmit({
        service: '/message/replymessage.json',
        id: 'entry-Message',
        contentType: 'json',
        done: function(rs) {
            if (rs.success) {
                popup.msg(rs.message, function() {
                    //location.reload();
                });
            } else {
                popup.msg(rs.message);
            }
        }
    });
};

var myVar = null;
message.searchUserByEmail = function(_email) {
    if (myVar !== null) {
        clearTimeout(myVar);
    }
    myVar = setTimeout(function() {
        ajax({
            service: '/message/getuserbyemail.json',
            data: {email: _email},
            loading: false,
            done: function(rs) {
                if (rs.success) {
                    var htmlMess = '<ul>';
                    var dataUser = rs.data.data;
                    $.each(dataUser, function(i) {
                        htmlMess += '<li onclick="message.setEmail(\'' + dataUser[i].email + '\')"><a href="javascript:;"><b>' + dataUser[i].username + '</b> (' + dataUser[i].email + ')</a></li>';
                    });
                    htmlMess += '</ul>';
                    $('.compare-autosearch').html(htmlMess);
                    $(".compare-autosearch").hover(function() {
                    }, function() {
                        $('.compare-autosearch').html("");
                    });
                } else {
                    popup.msg(rs.message);
                }
            }
        });
    }, 300);

};
message.setEmail = function(_email) {
    $('input[name=toEmail]').val(_email);
    $('.compare-autosearch').html("");
};