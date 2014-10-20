user = {};

user.initChangeProfile = function(cityId, districtId) {
    var html = "";
    $.each(citys, function() {
        if (this.id == cityId)
            html += "<option value=" + this.id + " selected >" + this.name + "</option>";
        else
            html += "<option value=" + this.id + " >" + this.name + "</option>";
    });
    $("select[name=cityId]").append(html);
    html = "";
    $.each(districts, function() {
        if (this.cityId == cityId) {
            if (this.id == districtId) {
                html += "<option value=" + this.id + " selected >" + this.name + "</option>";
            } else
                html += "<option value=" + this.id + " >" + this.name + "</option>";
        }
    });
    $("select[name=districtId]").html(html);
    $('input[name=dob]').datepicker({dateFormat: "dd/mm/yy"});

};

user.changePass = function() {
    var data = new Object();
    var email = $('.control-label[name=email] span.val').text();
    popup.open('popup-changePass', 'Đổi mật khẩu', template('/user/tpl/changePassWord.tpl', {email: email}), [
        {
            title: 'Đổi mật khẩu',
            style: 'btn-success',
            fn: function() {
                data.email = email;
                data.oldPass = $('input[name=oldPass]').val();
                data.newPass = $('input[name=newPass]').val();
                data.confirmPass = $('input[name=confirmPass]').val();
                ajax({
                    service: '/user/changepass.json',
                    loading: false,
                    data: data,
                    type: 'post',
                    contentType: 'json',
                    done: function(resp) {
                        if (resp.success) {
                            //alert(resp.message);
                            popup.close('popup-changePass');
                            popup.msg(resp.message);
                        } else {
                            // remove old message && error class
                            $("div.oldPass,div.newPass,div.confirmPass").parents('.form-group').removeClass("has-error");
                            $("div.oldPass,div.newPass,div.confirmPass").html("");
                            if (resp.data.oldPass != null) {
                                if ($('input[name=oldPass]').val() == '') {
                                    $("div.oldPass").html("Mật khẩu cũ không được để trống !");
                                    $("div.oldPass").parents('.form-group').addClass("has-error");
                                } else {
                                    $("div.oldPass").html(resp.data.oldPass);
                                    $("div.oldPass").parents('.form-group').addClass("has-error");
                                }
                            }
                            if (resp.data.confirmPass != null) {
                                if ($('input[name=confirmPass]').val() == '') {
                                    $("div.confirmPass").html("Mật khẩu mới không được để trống !");
                                    $("div.confirmPass").parents('.form-group').addClass("has-error");
                                } else {
                                    $("div.confirmPass").html(resp.data.confirmPass);
                                    $("div.confirmPass").parents('.form-group').addClass("has-error");
                                }
                            }
                            if ($('input[name=newPass]').val() == '') {
                                $("div.newPass").html("Xác nhận mật khẩu không được để trống !");
                                $("div.newPass").parents('.form-group').addClass("has-error");
                            } else {
                                if (resp.data.newPass != null) {
                                    $("div.newPass").html(resp.data.newPass);
                                    $("div.newPass").parents('.form-group').addClass("has-error");
                                }
                            }
                        }
                    }
                });
            }
        },
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-changePass');
            }
        }
    ]);
};

user.findDistrict = function(obj) {
    $('div[for=city]').removeClass("has-error");
    $('div[for=district]').removeClass("has-error");
    var html = "";
    var cityId = $(obj).val();
    if (cityId != '') {
        $.each(districts, function() {
            if (this.cityId == cityId)
                html += "<option value=" + this.id + " >" + this.name + "</option>";
        });
    }
    $("select[name=districtId]").html(html);
};
user.oldValue = '';
user.quickEdit = function(el) {
    var parentEl = $(el).parents('label');
    var oldValue = parentEl.children('span.val').text();
    var quickEdit = '<input class="form-control" rel="change" style="width:285px;margin-bottom:5px" type="text" value="' + oldValue.trim() + '">';
    parentEl.html(quickEdit);
    parentEl.children('input').focus();
};

user.submitQuickEdit = function(el) {
    var parentEl = $(el).parent('label');
    var newValue = $(el).val();
    var type = $(parentEl).attr('name');
//    var iconVerified = '';
//    if (newValue.length > 0) {
//        $(parentEl).parents(".form-group").removeClass("has-error");
//        $(parentEl).parent("div").children(".help-block").html("");
//    }
//    if (type == "phone") {
//        if (!/^[0]{1}[0-9]{9,10}$/.test(newValue) || newValue.length < 10) {
//            $('div[for=phone]').children('div').append("<div class='help-block' style='color:red'>Số điện thoại phải là dãy số từ 10-11 số và bắt đầu bằng 0 !</div>");
//        } else {
//            $(parentEl).parents(".form-group").removeClass("has-error");
//            $(parentEl).parent("div").children(".help-block").html("");
//            var className = curUser[type + 'Verified'] ? 'ok' : 'false';
//            if (curUser[type] != null && curUser[type] != '')
//                iconVerified = '<span class="icon-check-' + className + '"><span class="glyphicon glyphicon-remove"></span> Phone</span> &nbsp;';
//        }
//    }
////    var update = '<span class="val">' + newValue + '</span> &nbsp; ' + iconVerified + '<a href="javascript:void(0)"><span class="glyphicon glyphicon-pencil" onclick="user.quickEdit(this);"></span></a>';
////    if (type == 'name' || (/^[0]{1}[0-9]{9,10}$/.test(newValue) && newValue.length >= 10)) {
////        parentEl.html(update);
////    }
//    // disable or not save button
//    if ($("#quick_edit_field").length)
//        $("#btnUpdate").attr("disabled", "disabled");
//    else
//        $("#btnUpdate").removeAttr("disabled");
};

user.updateProfile = function() {
    var id = $('input[name=id]').val();
    var name = $('label[name=name]').children('input[rel=change]').val();
    var phone = $('label[name=phone]').children('input[rel=change]').val();
    var cityId = $('select[name=cityId]').val();
    var districtId = $('select[name=districtId]').val();
    //convert dob to timestamp
    var birthday = $('input[name=dob]').val();
    birthday = birthday.split("/");
    var newDob = birthday[1] + "," + birthday[0] + "," + birthday[2];
    var dob = new Date(newDob).getTime();
    var gender = $('input[name=gender]:checked').val();
    var address = $('input[name=address]').val();
    var yahoo = $('input[name=yahoo]').val();
    var skype = $('input[name=skype]').val();
    $('div[for=phone]').removeClass("has-error");
    $('div[relCheck=phone]').remove();
    var data = {
        id: id,
        name: name,
        phone: phone,
        cityId: cityId,
        districtId: districtId,
        dob: dob,
        gender: gender,
        address: address,
        yahoo: yahoo,
        skype: skype
    };
    ajax({
        service: '/user/updateprofile.json',
        loading: false,
        contentType: 'json',
        type: 'post',
        data: data,
        done: function(resp) {
            if (resp.success) {
                popup.msg(resp.message);
                setTimeout(function() {
                    location.reload();
                }, 3000);
            } else {
                $('.help-block').remove();
                if (resp.data.cityId != null) {
                    $('div[for=city]').addClass("has-error");
                    $('div[for=city]').children('div').append("<div class='help-block'>" + resp.data.cityId + "</div>");
                }
                if (resp.data.districtId != null) {
                    $('div[for=district]').addClass("has-error");
                    $('div[for=district]').children('div').append("<div class='help-block'>" + resp.data.districtId + "</div>");
                }
                if (resp.data.phone != null) {
                    $('div[for=phone]').addClass("has-error");
                    $('div[for=phone]').children('div').append("<div class='help-block' relCheck='phone'>" + resp.data.phone + "</div>");
                }
                if (resp.data.dob != null) {
                    $('div[for=dob]').addClass("has-error");
                    $('div[for=dob]').find('.date-picker-block').append("<div class='help-block'>" + resp.data.dob + "</div>");
                }
                if (resp.data.name != null) {
                    $('div[for=name]').addClass("has-error");
                    $('div[for=name]').children('div').append("<div class='help-block'>" + resp.data.name + "</div>");
                }
                if (resp.data.address != null) {
                    $('div[for=address]').addClass("has-error");
                    $('div[for=address]').children('div').append("<div class='help-block'>" + resp.data.address + "</div>");
                }
            }
        }
    });
};
user.showPreview = function(coords)
{
    if (parseInt(coords.w) > 0) {
        // get image
        var container = $('#photo');
        var images = document.getElementById('photo');
        // get ti le anh
        var tiLeAnh = images.naturalWidth / container.width();
        // calculate
        var calculatex1 = Math.round(coords.x * tiLeAnh);
        var calculatey1 = Math.round(coords.y * tiLeAnh);
        var calculatewidth = Math.round(coords.w * tiLeAnh);
        var calculateheight = Math.round(coords.h * tiLeAnh);
        $('#x1').val(calculatex1);
        $('#y1').val(calculatey1);
        $('#w').val(calculatewidth);
        $('#h').val(calculateheight);
    }

}
user.changeAvatar = function(id) {
    ajax({
        service: '/user/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-add', 'Thay ảnh đại diện', template('/user/tpl/changeavatar.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            var userId = $('#photo').attr("for");
                            var x = $('#x1').val();
                            var y = $('#y1').val();
                            var width = $('#w').val();
                            var height = $('#h').val();
                            ajax({
                                service: '/user/changeavatar.json',
                                data: {userId: userId, width: width, height: height, x: x, y: y},
                                done: function(rs) {
                                    if (rs.success) {
                                        popup.msg(rs.message, function() {
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
                        title: 'Đóng',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-add');
                        }
                    }
                ]);
                setTimeout(function() {
                    $('#photo').Jcrop({
                        onChange: user.showPreview,
                        minSize: [80, 80],
                        aspectRatio: 1 / 1
                    });
                }, 300);
                $('#rd-upload-link').click(function() {
                    $(this).attr('checked', 'checked');
                    $('.box-upload-img').hide();
                    $('.box-upload-link').show();
                });
                $('#rd-upload-img').click(function() {
                    $(this).attr('checked', 'checked');
                    $('.box-upload-link').hide();
                    $('.box-upload-img').show();
                });

            } else {
                popup.msg(resp.message);
            }

        }
    });
};
user.uploadImageAvatar = function() {
    ajaxUpload({
        service: '/user/uploadimageavatar.json',
        id: 'form-edit-uploadImg',
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {
                $('.thumbnail').html('<img id="photo" for="' + resp.data.userId + '" src="' + resp.data.image + '" style="max-height: 225px; max-width: 225px;" />');
                setTimeout(function() {
                    $('#photo').Jcrop({
                        onChange: user.showPreview,
                        minSize: [80, 80],
                        aspectRatio: 1 / 1
                    });
                }, 300);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
user.downloadImageAvatar = function() {
    var url = $('input[name=downloadImageAvatar]').val();
    var userId = $('#photo').attr("for");
    ajax({
        data: {url: url, userId: userId},
        service: '/user/downloadimageavatar.json',
        done: function(resp) {
            if (resp.success) {
                $('.thumbnail').html('<img id="photo" for="' + userId + '" src="' + resp.data + '" style="max-height: 225px; max-width: 225px;" />');
                $('#photo').Jcrop({
                    onChange: user.showPreview,
                    minSize: [80, 80],
                    aspectRatio: 1 / 1
                });
                $('input[name=downloadImageAvatar]').val('');
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
user.avatarUpload = function(el) {
    ajaxUpload({
        service: '/user/updateavatar.json',
        id: 'avatarUpload',
        loading: false,
        type: 'POST',
        done: function(rs) {
            if (rs.success) {
                popup.msg(rs.message);
                $(el).hide();
                setTimeout(function() {
                    location.reload();
                }, 3000);
            } else {
                popup.msg(rs.message);
            }
        }
    });

};