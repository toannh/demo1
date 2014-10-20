city = {};

city.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị tỉnh / thành phố", "/cp/city.html"],
        ["Danh sách tỉnh thành"]
    ]);
};

city.add = function() {
    popup.open('popup-add-city', 'Thêm mới tỉnh /thành phố', template('/cp/tpl/city/add.tpl', null), [
        {
            title: 'Tạo mới',
            style: 'btn-info',
            fn: function() {
                ajaxSubmit({
                    service: '/cpservice/city/addcity.json',
                    id: 'city-add-form',
                    contentType: 'json',
                    done: function(resp) {
                        if (resp.success) {
                            popup.msg(resp.message, function() {
                                location.reload();
                            });
                        } else {
                            popup.msg(resp.message);
                        }
                    }
                });
            }
        },
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-add-city');
            }
        }
    ]);
};

city.edit = function(id) {
    ajax({
        service: '/cpservice/global/city/getcitybyid.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-edit-city', 'Sửa thông tin tỉnh /thành phố', template('/cp/tpl/city/add.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-info',
                        fn: function() {
                            ajaxSubmit({
                                service: '/cpservice/city/editcity.json',
                                id: 'city-add-form',
                                contentType: 'json',
                                done: function(rs) {
                                    if (rs.success) {
                                        popup.msg("Sửa thông tin tỉnh / thành phố thành công", function() {
                                            location.reload();
                                        });
                                    } else {
                                        popup.msg(rs.message);
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-edit-city');
                        }
                    }
                ]);
            }
        }
    });
};

city.listDistrict = function(id) {
    ajax({
        service: '/cpservice/global/city/getdistrictbycityid.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                resp.cityId = id;
                popup.open('popup-list-district', 'Danh sách quận / huyện', template('/cp/tpl/district/list.tpl', resp), [
                    {
                        title: 'Đóng',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-list-district');
                        }
                    }
                ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};


city.delCity = function(id) {
    popup.confirm("Bạn muốn xóa tỉnh / thành phố này không?", function() {
        ajax({
            service: '/cpservice/city/delcity.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    popup.msg(resp.message, function() {
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

city.delDistrict = function(id, cityId) {
    popup.confirm("Bạn muốn xóa quận / huyện này không?", function() {
        ajax({
            service: '/cpservice/city/deldistrict.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    popup.msg(resp.message, function() {
                        city.listDistrict(cityId);
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

city.addDistrict = function(cityid) {
    var resp = new Object();
    resp.cityId = cityid;
    popup.open('popup-add-district', 'Thêm mới quận/huyện', template('/cp/tpl/district/adddistrict.tpl', resp), [
        {
            title: " Lưu ",
            style: "btn-primary",
            fn: function() {
                $("input[for=cityId]").val(cityid);
                ajaxSubmit({
                    service: '/cpservice/city/adddistrict.json',
                    id: 'add-district-form',
                    contentType: 'json',
                    done: function(resp) {
                        if (resp.success) {
                            popup.msg("Thêm mới quận huyện thành công!", function() {
                                popup.close('popup-add-district');
                                city.listDistrict(cityid);
                            });
                        } else
                            popup.msg(resp.message);
                    }
                });
            }
        },
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-add-district');
            }
        }
    ]);
};

city.editDistrict = function(id, cityId) {
    var name = $("input[name=name_" + id + "]").val();
    var position = $("input[name=position_" + id + "]").val();
    var scId = $("input[name=scId_" + id + "]").val();
    if (name == '') {
        popup.msg("Tên không được để trống!");
    } else {
        popup.confirm("Bạn muốn thay đổi thông tin của quận / huyện này không?", function() {
            ajax({
                service: '/cpservice/city/editdistrict.json',
                data: {id: id, name: name, position: position, scId: scId},
                done: function(resp) {
                    if (resp.success) {
                        popup.msg(resp.message, function() {
                            city.listDistrict(cityId);
                        });
                    } else {
                        popup.msg(resp.message);
                    }
                }
            });
        });
    }
};