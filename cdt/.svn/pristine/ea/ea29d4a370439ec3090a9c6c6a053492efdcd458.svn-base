/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
cashhistory = {};
cashhistory.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị thưởng phạt xèng", "/cp/cashhistory.html"],
        ["Danh sách thưởng phạt xèng"]
    ]);
    $('.timeselect').timeSelect();
    cashhistory.getUserByIds();
};

cashhistory.getUserByIds = function() {
    ajax({
        service: '/cpservice/user/getbyids.json',
        data: userIds,
        type: 'post',
        loading: false,
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {
                $.each(resp.data, function() {
                    $('td[rel=' + this.id + ']').text(this.email);
                });
            } else {
                popup.msg(resp.message);
            }
        }
    })
};

cashhistory.resetForm = function() {
    $('input[type=text]').val("");
    $('select[name=type]').val("");
    $('input[name=startTime]').val("0");
    $('input[name=endTime]').val("0");
};

cashhistory.fine = function(id) {
    ajax({
        service: '/cpservice/cashhistory/getfine.json',
        data: {id: id},
        type: "post",
        loading: false,
        done: function(respone) {
            if (respone.success) {
                data = {};
                data.email = $('#getText').text();
                data.fine = respone.data;
                popup.open('popup-fine', 'Phạt xèng', template('/cp/tpl/cashhistory/note.tpl', data), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            var note = $('textarea[name=note]').val();
                            if (note === '' || note === null) {
                                $('div[name=dnote]').addClass('has-error');
                                $('span[name=note_2]').text("Vui lòng nhập ghi chú");
                            } else {
                                $('div[name=dnote]').removeClass('has-error');
                                $('span[name=note_2]').empty();
                                ajax({
                                    service: '/cpservice/cashhistory/fine.json',
                                    data: {id: id, note: note},
                                    type: "post",
                                    done: function(resp) {
                                        if (resp.success) {
                                            popup.close('popup-fine');
                                            $('td[rel-data-note=' + id + ']').text(resp.data.note);
                                            $('td[rel-data-admin=' + id + ']').text(resp.data.admin);
                                            $('tr[rel-data-line=' + id + ']').removeClass("text-center").addClass("text-center danger");
                                            $('td[rel-data-btn=' + id + ']').html('<label class="label label-danger">Phạt xèng</label>');
                                            $('td[rel-data-btn2=' + id + ']').empty();
                                        } else {
                                            popup.msg(resp.message);
                                        }
                                    }
                                })
                            }
                        }
                    },
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-fine');
                        }
                    }
                ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

cashhistory.unAppro = function(id) {
    ajax({
        service: '/cpservice/cashhistory/getfine.json',
        data: {id: id},
        type: "post",
        loading: false,
        done: function(respone) {
            if (respone.success) {
                data = {};
                data.email = $('#getText').text();
                data.fine = respone.data;

                popup.open('popup-fine-note', 'Không duyệt', template('/cp/tpl/cashhistory/notecard.tpl'), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            var note = $('textarea[name=note]').val();
                            if (note === '' || note === null) {
                                $('div[name=dnote]').addClass('has-error');
                                $('span[name=note_2]').text("Vui lòng nhập ghi chú");
                            } else {
                                $('div[name=dnote]').removeClass('has-error');
                                $('span[name=note_2]').empty();
                                ajax({
                                    service: '/cpservice/cashhistory/fine.json',
                                    data: {id: id, note: note, unappro: true},
                                    type: "post",
                                    done: function(resp) {
                                        if (resp.success) {
                                            popup.close('popup-fine-note');
                                            $('td[rel-data-note=' + id + ']').text(resp.data.note);
                                            $('td[rel-data-admin=' + id + ']').text(resp.data.admin);
                                            $('tr[rel-data-line=' + id + ']').removeClass("text-center").addClass("text-center danger");
                                            $('td[rel-data-btn=' + id + ']').html('<label class="label label-danger">Không duyệt</label>');
                                            $('td[rel-data-btn2=' + id + ']').empty();
                                        } else {
                                            popup.msg(resp.message);
                                        }
                                    }
                                })
                            }
                        }
                    },
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-fine-note');
                        }
                    }
                ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
cashhistory.exportEx = function() {
    var seftUrl = baseUrl + "/cp/cashhistory/excel.html";
    urlParams = new Object();
    urlParams.type = $("select[name=type]").val();
    urlParams.objectId = $("input[name=objectId]").val();
    urlParams.admin = $("input[name=admin]").val();
    urlParams.startTime = $("input[name=startTime]").val() > 0 ? $("input[name=startTime]").val() : "0";
    urlParams.endTime = $("input[name=endTime]").val() > 0 ? $("input[name=endTime]").val() : "0";
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
    location.href = seftUrl + queryString;
};