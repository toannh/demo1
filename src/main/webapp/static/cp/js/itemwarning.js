/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var itemwarning = {};
itemwarning.initwarning = function() {
    //vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị báo cáo sản phẩm", "/cp/itemwarning.html"],
        ["Danh sách báo cáo"]
    ]);
};
itemwarning.inithistory = function() {
    //vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị lịch sử sản phẩm", "/cp/itemhistoty.html"],
        ["Danh sách lịch sử sản phẩm"]
    ]);
};
itemwarning.initSellerHistory = function() {
    //vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị lịch sử người bán", "/cp/sellerlog.html"],
        ["Danh sách log người bán"]
    ]);
};
