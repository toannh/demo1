/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
userlock = {};
userlock.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị khóa tài khoản", "/cp/userlock.html"],
        ["Danh sách tài khoản bị khóa"]
    ]);
    $('.timeselect').timeSelect();
};

userlock.resetForm = function() {
    $('input[type=text]').val("");
    $('select[name=done]').val("0");
    $('input[name=startTime]').val("0");
    $('input[name=endTime]').val("0");
    $('input[name=createTimeFrom]').val("0");
    $('input[name=createTimeTo]').val("0");
};

userlock.unlock = function(id) {
    popup.confirm("Bạn chắc chắn muốn mở khóa tài khoản này chứ ?", function() {
        ajax({
            service: '/cpservice/userlock/stoprun.json',
            data: {id: id},
            type: "post",
            loading: false,
            done: function(resp) {
                if (resp.success) {
                    var cls = $('tr[rel-line=' + id + ']').attr('class');
                    $('td[rel-btn=' + id + ']').empty();
                    $('tr[rel-line=' + id + ']').removeClass(cls).addClass("text-center success");
                    $('td[rel-lbl=' + id + ']').html('<label class="label label-success">Mở khóa</label>');
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};