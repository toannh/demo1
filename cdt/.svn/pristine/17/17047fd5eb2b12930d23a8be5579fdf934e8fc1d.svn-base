/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

advbanner = {};
advbanner.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị banner quảng cáo", "/cp/advbanner.html"],
        ["Danh sách banner"]
    ]);
};
advbanner.add = function() {
    popup.open('popup-add-banner', 'Thêm mới banner quảng cáo', template('/cp/tpl/advbanner/editform.tpl', {cates: cates}), [
        {
            title: 'Thêm',
            style: 'btn-info',
            fn: function() {
                ajaxUpload({
                    service: '/cpservice/advbanner/addbanner.json',
                    id: 'banner-form-edit',
                    contentType: 'json',
                    done: function(resp) {
                        if (resp.success) {
                            popup.msg(resp.message, function() {
                                popup.close('popup-add-banner');
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
                popup.close('popup-add-banner');
            }
        }
    ]);
    $('input[id=lefile]').change(function() {
        $('#photoCover').val($(this).val());
    });
    $('select[name=position]').change(function() {
        var position = $(this).val();
        if (position == 'BACKEND_USER') {
            $('div[for=categoryId]').css({"display": "none"});
        } else {
            $('div[for=categoryId]').css({"display": "block"});
        }
    });


};
advbanner.edit = function(id) {
    ajax({
        service: '/cpservice/advbanner/getbanner.json',
        data: {id: id},
        type: 'get',
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-edit-banner', 'Sửa banner quảng cáo', template('/cp/tpl/advbanner/editform.tpl', {cates: cates, data: resp.data}), [
                    {
                        title: 'Lưu',
                        style: 'btn-info',
                        fn: function() {
                            ajaxUpload({
                                service: '/cpservice/advbanner/addbanner.json',
                                id: 'banner-form-edit',
                                contentType: 'json',
                                done: function(resp) {
                                    if (resp.success) {
                                        popup.msg(resp.message, function() {
                                            popup.close('popup-edit-banner');
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
                            popup.close('popup-edit-banner');
                        }
                    }
                ]);
                $('input[id=lefile]').change(function() {
                    $('#photoCover').val($(this).val());
                });
                if (resp.data.position == 'BACKEND_USER') {
                    $('div[for=categoryId]').css({"display": "none"});
                }
                $('select[name=position]').change(function() {
                    var position = $(this).val();
                    if (position == 'BACKEND_USER') {
                        $('div[for=categoryId]').css({"display": "none"});
                    } else {
                        $('div[for=categoryId]').css({"display": "block"});
                    }
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
advbanner.del = function(id) {
    ajax({
        service: '/cpservice/advbanner/delete.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                $('table tr[for=' + id + ']').remove();
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
advbanner.changeStatus = function(id) {
    ajax({
        service: '/cpservice/advbanner/changestatus.json',
        data: {id: id},
        type: 'get',
        done: function(resp) {
            if (resp.success) {
                if (resp.data.active) {
                    $('a[editStatus=' + resp.data.id + ']').html('<img src="' + staticUrl + '/cp/img/icon-enable.png">');
                } else {
                    $('a[editStatus=' + resp.data.id + ']').html('<img src="' + staticUrl + '/cp/img/icon-disable.png">');
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
