news = {};

news.init = function() {

    //vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị tin tức", "/cp/news.html"],
        ["Danh sách tin tức"]
    ]);

    var idcateG = $('#categoryIds').val();
    if (idcateG !== null && idcateG !== '' && typeof idcateG !== 'undefined') {
        news.loadCateSearch(idcateG);
    }

};
news.add = function() {
    popup.open('popup-add', 'Thêm mới tin tức', template('/cp/tpl/news/add.tpl'), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function() {
                ajaxUpload({
                    service: '/cpservice/news/add.json',
                    id: 'form-add',
                    contentType: 'json',
                    done: function(rs) {
                        if (rs.success) {
                            popup.msg("Thêm mới tin thành công");
                        } else {
                            popup.msg("Thêm mới tin thất bại");
                        }
                        setTimeout(function() {
                            window.location.reload();
                        }, 1000);
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

    $('input[id=lefile]').change(function() {
        $('#photoCover').val($(this).val());
    });
    editor('txt_content');
};

news.edit = function(id) {
    ajax({
        service: '/cpservice/news/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-add', 'Sửa tin', template('/cp/tpl/news/edit.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            ajaxUpload({
                                service: '/cpservice/news/edit.json',
                                id: 'form-add',
                                contentType: 'json',
                                done: function(rs) {
                                    if (rs.success) {
                                        popup.msg("Sửa tin thành công");
                                    } else {
                                        popup.msg("Sửa tin thất bại");
                                    }
                                    setTimeout(function() {
                                        window.location.reload();
                                    }, 1000);
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

                editor('txt_content');
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
news.loadCateSub = function(id) {
    if (id === "") {
        $('#sub-cate').html("");
    }
    ajax({
        service: '/cpservice/newscategory/getchilds.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                var html = "";
                var cate = "";

                if (resp.data.length > 0) {
                    $.each(resp.data, function() {
                        cate += '<option value="' + this.id + '">' + this.name + '</option>';
                    });
                    html += '<label class="control-label col-sm-2"></label>\n\
                            <div class="col-sm-10">\n\
                                <select class="form-control" name="categoryId1">\n\
                                <option value="">---Chọn---</option>' + cate + '\n\
                                </select>\n\
                            </div>';
                }
                $('#sub-cate').html(html);
            } else {
                popup.msg(resp.message);
            }
        }
    });

};

news.editStatus = function(id) {
    ajax({
        service: '/cpservice/news/editstatus.json',
        data: {id: id},
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

news.delnews = function(id) {
    popup.confirm("Bạn có chắc muốn xóa?", function() {
        ajax({
            service: '/cpservice/news/del.json',
            data: {id: id},
            done: function(resp) {
                if (resp.success) {
                    popup.msg(resp.message);
                    setTimeout(function() {
                        window.location.reload();
                    }, 2000);
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

news.resetForm = function() {
    $('select[name=categoryId]').val("");
    $('select[name=categoryId1]').val("");
    $('#categoryId1').hide();
    $('input[type=text]').val("");
};

news.loadCateSearch = function(id) {
    if (id === "") {
        $('#subcate').html("");
    }
    ajax({
        service: '/cpservice/newscategory/getchilds.json',
        data: {id: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                var html = "";
                var cate = "";

                if (resp.data.length > 0) {
                    $.each(resp.data, function() {
                        cate += '<option value="' + this.id + '">' + this.name + '</option>';
                    });
                    html += '<div class="form-group">\n\
                                <select class="form-control" name="categoryId1" id="categoryId1">\n\
                                <option value="">---Chọn---</option>' + cate + '\n\
                                </select>\n\
                            </div>';
                }

                $('#subcate').html(html);
                if ($('input[name=subcate_hide]').val() != null && $('input[name=subcate_hide]').val() != '') {
                    var vall = $('input[name=subcate_hide]').val();
                    $('option[value=' + vall + ']').attr('selected', 'selected');
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });

};
