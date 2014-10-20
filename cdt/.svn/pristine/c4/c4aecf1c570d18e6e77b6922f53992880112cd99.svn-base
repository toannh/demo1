
user = {};
user.init = function() {
    //vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị thành viên", "/cp/user.html"],
        ["Danh sách thành viên"]
    ]);

    $("select[rel=cityId]").change(function() {
        if ($(this).val() == 0) {
            $("select[rel=districtId]").val(0);
        } else {
            var html = '<option value="0">-- Chọn quận huyện --</option>';
            var cityId = $(this).val();
            $.each(districts, function() {
                if (cityId == this.cityId) {
                    html += '<option value="' + this.id + '">' + this.name + '</option>';
                }
            });
            $("select[rel=districtId]").html(html);
        }
    });
};

user.changeEmailVerified = function(id) {
    ajax({
        service: '/cpservice/user/changeemailverified.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                var html = "";
                if (resp.data.emailVerified) {
                    html = '<i class="glyphicon glyphicon-check"></i>';
                } else {
                    html = '<i class="glyphicon glyphicon-unchecked"></i>';
                }
                $("span[rel=emailVerified_" + id + ']').html(html);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
user.changePhoneVerified = function(id) {
    ajax({
        service: '/cpservice/user/changephoneverified.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                var html = "";
                if (resp.data.phoneVerified) {
                    html = '<i class="glyphicon glyphicon-check"></i>';
                } else {
                    html = '<i class="glyphicon glyphicon-unchecked"></i>';
                }
                $("span[rel=phoneVerified_" + id + ']').html(html);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

user.changeActive = function(id) {
    ajax({
        service: '/cpservice/user/changeactive.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                var html = "";
                if (resp.data.active) {
                    html = '<i class="glyphicon glyphicon-check"></i>';
                } else {
                    html = '<i class="glyphicon glyphicon-unchecked"></i>';
                }
                $("span[rel=active_" + id + ']').html(html);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

user.edit = function(id) {
    ajax({
        service: '/cpservice/user/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-edit-user', 'Thông tin tài khoản ' + resp.data.name, template('/cp/tpl/user/edit.tpl', resp), [
                    {
                        title: 'Sửa',
                        style: 'btn-info',
                        fn: function() {
                            $('#editUserForm input[name=phone]').val("1" + $('#editUserForm input[name=phone]').val());
                            ajaxSubmit({
                                service: '/cpservice/user/edit.json',
                                id: 'editUserForm',
                                type: 'post',
                                contentType: 'json',
                                done: function(resp) {
                                    if (resp.success) {
                                        popup.close('popup-edit-user');
                                        popup.msg(resp.message, function() {
                                            location.reload();
                                        });
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-edit-user');
                        }
                    }
                ]);

                $(".editdob").timeSelect();
                var html = '<option value="0">-- Chọn tỉnh thành phố --</option>';
                $.each(citys, function() {
                    html += '<option ' + ((this.id == resp.data.cityId) ? 'selected' : '') + ' value="' + this.id + '">' + this.name + '</option>';
                });
                $("select[rel=edit-cityId]").html(html);
                html = "";
                $.each(districts, function() {
                    if (this.cityId == resp.data.cityId) {
                        html += '<option ' + ((this.id == resp.data.districtId) ? 'selected' : '') + ' value="' + this.id + '">' + this.name + '</option>';
                    }
                });
                $("select[rel=edit-districtId]").html(html);
                $("select[rel=edit-cityId]").change(function() {
                    if ($(this).val() == 0) {
                        $("select[rel=edit-districtId]").val(0);
                    } else {
                        var html = '<option value="0">-- Chọn quận huyện --</option>';
                        var cityId = $(this).val();
                        $.each(districts, function() {
                            if (cityId == this.cityId) {
                                html += '<option value="' + this.id + '">' + this.name + '</option>';
                            }
                        });
                        $("select[rel=edit-districtId]").html(html);
                    }
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};