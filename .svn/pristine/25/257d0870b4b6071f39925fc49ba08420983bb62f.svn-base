smsInbox = {};
smsInbox.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị hòm thư ChợĐiệnTử", "/cp/smsinbox.html"],
        ["Danh sách thư"]
    ]);
    $(".timeFrom").timeSelect();
    $(".timeTo").timeSelect();
};

smsInbox.reset = function() {
    $('input[name=phone]').val("");
    $('input[name=receiver]').val("");
    $('select[name=type]').val("");
    $('select[name=success]').val("0");
    $('input[name=timeFrom]').val("0");
    $('input[name=timeTo]').val("0");
};