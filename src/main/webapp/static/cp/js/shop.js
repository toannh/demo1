var shop = {};
shop.init = function(role) {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị shop", "/cp/shop.html"],
        ["Danh sách shop"]
    ]);
    $('.timeSelect').timeSelect();
    shop.listCity();
    var checkRole = false;
    if (role != null && role.length > 0) {
        $.each(role, function() {
            if (this.functionUri === '/cpservice/shop/adddomain') {
                checkRole = true;
            }
        });
    }
    if (checkRole) {
        $('.checkRole').css('display', 'block');
    }
};

shop.reset = function() {
    $('input[name=userId]').val("");
    $('input[name=title]').val("");
    $('input[name=createTimeFrom]').val("0");
    $('input[name=createTimeTo]').val("0");
};
shop.listCity = function() {
    ajax({
        service: '/cpservice/city/listcity.json',
        loading: false,
        done: function(rep) {
            if (rep.success) {
                var cityHTML = '<option value="">---Tỉnh/Thành phố---</option>';
                $(rep.data).each(function(i) {
                    cityHTML += '<option value="' + rep.data[i].id + '">' + rep.data[i].name + '</option>';
                });
                $('#searchShopCity').html(cityHTML);
                if (typeof cityId !== 'undefined' && cityId !== null && cityId !== '') {
                    $('select[name=cityId]').val(cityId);
                    shop.listDistrict(cityId);
                }
            }
        }
    });
};
shop.listDistrict = function(id) {
    ajax({
        service: '/cpservice/city/listdistrictbycity.json',
        data: {cityId: id},
        loading: false,
        done: function(rep) {
            if (rep.success) {
                var districtHTML = '<select name="districtId" class="form-control" id="searchShopDistrict"><option value="" selected="true">---Quận/Huyện---</option>';
                $(rep.data).each(function(i) {
                    districtHTML += '<option value="' + rep.data[i].id + '">' + rep.data[i].name + '</option>';
                });
                districtHTML += '</select>';
                $('#searchShopDistrict').html(districtHTML);
                if (typeof districtId !== 'undefined' && districtId !== null && districtId !== '') {
                    $('select[name=districtId]').val(districtId);
                }
            }
        }
    });
};

shop.addDomain = function(userId) {
    ajax({
        service: '/cpservice/shop/get.json',
        data: {userId: userId},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-add', 'Thêm domain', template('/cp/tpl/shop/adddomain.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            var id = $('#form-add-domain input[name=userId]').val();
                            var domain = $('#form-add-domain input[name=url]').val();
                            if (domain === null || domain === '') {
                                $('#form-add-domain div[rel=url]').addClass('has-error');
                                $('#form-add-domain input[name=url]').attr("placeholder", "Domain cho shop được để trống!");
                            } else {
                                $('#form-add-domain div[rel=url]').removeClass('has-error');
                                $('#form-add-domain input[name=url]').removeAttr("placeholder");
                                ajax({
                                    service: '/cpservice/shop/adddomain.json',
                                    data: {id: id, domain: domain},
                                    done: function(rs) {
                                        if (rs.success) {
                                            popup.msg(rs.message, function() {
                                                location.reload();
                                            });
                                        } else {
                                            popup.msg(rs.message, function() {
                                            });
                                        }
                                    }
                                });
                            }
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
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

shop.addSupport = function(userId) {
    popup.confirm("Bạn có chắc chắn muốn nhận chăm sóc shop này?", function() {
        ajax({
            service: '/cpservice/shop/addsupport.json',
            data: {userId: userId},
            done: function(resp) {
                if (resp.success) {
                    popup.msg("Nhận chăm sóc thành công", function() {
                        var htmlSupport = '<a type="button" class="btn btn-success" style="width: 150px;" onclick="shop.addNote(' + userId + ')">'
                                + '<span class="fa fa-android pull-left"></span> Ghi chú'
                                + '</a>';
                        $('.shop_' + userId).html(htmlSupport);
                    });

                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

shop.addNote = function(userId) {
    ajax({
        service: '/cpservice/shop/get.json',
        data: {userId: userId},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-add', 'Thêm ghi chú', template('/cp/tpl/shop/note.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            var id = $('#add-note-shop input[name=userId]').val();
                            var note = $('#add-note-shop textarea[name=note]').val();
                            ajax({
                                service: '/cpservice/shop/addnote.json',
                                data: {id: id, note: note},
                                done: function(rs) {
                                    if (rs.success) {
                                        popup.msg("Cập nhật ghi chú thành công", function() {
                                            popup.close('popup-add');
                                            var htmlSupport = '<a type="button" style="width: 150px;" class="btn btn-success" onclick="shop.addNote(' + id + ')">'
                                                    + '<span class="fa fa-android pull-left"></span> Ghi chú'
                                                    + '</a><span class="badge badge-success cdt-tooltip" data-toggle="tooltip" data-placement="top" title="' + rs.data.note + '">!</span>';
                                            $('.shop_' + id).html(htmlSupport);
                                            $('.cdt-tooltip').tooltip();
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
                            popup.close('popup-add');
                        }
                    }
                ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

shop.exportEx = function() {
    var seftUrl = baseUrl + "/cp/shop/excel.html";
    var alias = $('input[name=alias]').val();
    if (alias != null && alias != '') {
        urlParams = new Object();
        urlParams.alias = alias;
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
    } else {
        popup.msg("Chưa nhập Link Shop cần tìm kiếm , vui lòng nhập trước khi xuất Excel");
    }
};