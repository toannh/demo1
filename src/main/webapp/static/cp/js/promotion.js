promotion = {};
promotion.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị khuyến mãi", "/cp/promotion.html"],
        ["Danh sách khuyến mãi"]
    ]);
    $('.timeselect').timeSelect();
};
