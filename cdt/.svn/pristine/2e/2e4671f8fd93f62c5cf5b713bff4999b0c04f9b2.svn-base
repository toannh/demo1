/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
homebanner = {};
homebanner.initHomeCategory = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị Home Banner", "/cp/homebanner.html"],
        ["Danh sách Home Banner"]
    ]);
    $('#banner-form-add input[name=name]').focus();
};
homebanner.checkValid = function() {

    var name = $('#banner-form-add input[name=name]');
    var url = $('#banner-form-add input[name=url]');
    var image = $('#banner-form-add input[name=image]');
    var position = $('#banner-form-add select[name=position]');
    if (name.val() === 'undefined' || name.val() === '') {
        $('#banner-form-add input[name=name]').focus();
        return false;
    }

    if (position.val() === "-1") {
        $('#banner-form-add select[name=position]').focus();
    }
    if (url.val() === 'undefined' || url.val() === '') {
        $('#banner-form-add input[name=url]').focus();
        return false;
    }
    if (image.val() === 'undefined' || image.val() === '') {
        $('#banner-form-add input[name=image]').focus();
        return false;
    }

    return true;
}
homebanner.saveBanner = function(action) {
    popup.open('popup-add-banner', '<h4><strong>' + action + ' Banner</strong></h4>',
            template('/cp/tpl/homebanner/bannerAddForm.tpl', null), [{
            title: 'Lưu lại',
            style: 'btn-info',
            fn: function() {
                if (homebanner.checkValid()) {
                    ajaxUpload({
                        service: '/cpservice/homebanner/save.json',
                        id: 'banner-form-add',
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
            }
        }, {
            title: 'Bỏ',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-add-banner');
            }
        }
    ]);
    $('input[id=lefile]').change(function() {
        $('#photoCover').val($(this).val());
    });
    $('#banner-form-add input[name=name]').focus();
};

homebanner.editBanner = function(action, id) {
    ajax({
        service: '/cpservice/homebanner/getbyid.json',
        data: {id: id},
        done: function(resp) {
            popup.open('popup-add-banner', '<h4><strong>' + action + ' Banner</strong></h4>',
                    template('/cp/tpl/homebanner/bannerAddForm.tpl', resp), [{
                    title: 'Lưu lại',
                    style: 'btn-info',
                    fn: function() {
                        ajaxUpload({
                            service: '/cpservice/homebanner/save.json',
                            id: 'banner-form-add',
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
                }, {
                    title: 'Bỏ',
                    style: 'btn-default',
                    fn: function() {
                        popup.close('popup-add-banner');
                    }
                }
            ]);
            $('input[id=lefile]').change(function() {
                $('#photoCover').val($(this).val());
            });
            $('#banner-form-add input[name=name]').focus();
        }
    });
};
homebanner.activeBanner = function(id) {
    ajax({
        service: '/cpservice/homebanner/changestatus.json',
        data: {id: id},
        done: function(resp) {
            location.reload();
        }
    });
}
homebanner.deleteBanner = function(id) {
    popup.confirm('Bạn có chắc chắn xóa banner này!', function() {
        ajax({
            service: '/cpservice/homebanner/delete.json',
            data: {id: id},
            done: function(resp) {
                location.reload();
            }
        });
    });
}
homebanner.previewImg = function(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            $('#previewImg').attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    }
};
homebanner.getImage = function() {
    var url = $("#editItem input[name=urlImage]").val();
    ajax({
        service: '/cpservice/homebanner/getImage.json?url=' + url,
        contentType: 'json',
        type: 'get',
        done: function(resp) {

            if (resp.success) {
                var image = new Image();
                image.src = resp.data;
                var img = $("#k img").attr("src", resp.data);
                $("#k img").attr("style", "position: absolute; -webkit-transform: rotate(0deg); top: 0px; left: 0px;width=" + image.width + "px;height=" + image.height + "px;");
                console.log(img);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
