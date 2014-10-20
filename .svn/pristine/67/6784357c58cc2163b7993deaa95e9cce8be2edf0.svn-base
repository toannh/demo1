/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
itemcrawl = {};
itemcrawl.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị danh sách sản phẩm người dùng đang quan tâm", "/cp/itemcrawl.html"],
        ["Danh sách sản phẩm người dùng đang quan tâm"]
    ]);
    $('.timeselect').timeSelect();
};

itemcrawl.resetForm = function() {
    $('input[type=text]').val("");
    $('select[name=type]').val("");
    $('input[name=createTimeFrom]').val("0");
    $('input[name=createTimeTo]').val("0");
};