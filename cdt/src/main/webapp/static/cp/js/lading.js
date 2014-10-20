var lading = {};
lading.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị vận đơn", "/cp/lading.html"],
        ["Danh sách vận đơn"]
    ]);
    $('.timestamp').timeSelect();
};