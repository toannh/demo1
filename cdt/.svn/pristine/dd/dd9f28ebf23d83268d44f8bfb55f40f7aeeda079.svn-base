shop = {};
shop.initStepOne = function (ucityId, udistrictId) {
    editor('infoAbout');
    editor('infoGuide');
    editor('infoContact');
    editor('infoFooter');
    setTimeout(function () {
        shop.showAddress();
    }, 100);

    var html = "";
    $.each(citys, function () {
        if (this.id === ucityId)
            html += "<option value=" + this.id + " selected >" + this.name + "</option>";
        else
            html += "<option value=" + this.id + " >" + this.name + "</option>";
    });
    $("select[name=cityId]").append(html);
    html = "";
    $.each(districts, function () {
        if (this.cityId === ucityId) {
            if (this.id === udistrictId) {
                html += "<option value=" + this.id + " selected >" + this.name + "</option>";
            } else
                html += "<option value=" + this.id + " >" + this.name + "</option>";
        }
    });
    $("select[name=districtId]").html(html);
};

shop.findDistrict = function (obj) {
    $('div[for=city]').removeClass("has-error");
    $('div[for=district]').removeClass("has-error");
    var html = "";
    var cityId = $(obj).val();
    if (cityId != '') {
        $.each(districts, function () {
            if (this.cityId == cityId)
                html += "<option value=" + this.id + " >" + this.name + "</option>";
        });
    }
    $("select[name=districtId]").html(html);
};
shop.showPreview = function (coords) {
    if (parseInt(coords.w) > 0) {
        // get image
        var container = $('#imgCrop');
        var images = document.getElementById('imgCrop');
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
shop.quickEdit = function () {
    $("#btnUpdate").attr("disabled", "disabled");
    var strValue = $('.shopUrl').text().trim().split("http://chodientu.vn/")[1];
    var value = strValue.trim().replace('/', '');
    var change = 'http://chodientu.vn/ <input class="text" name="shopUrl"  onblur="shop.saveShopUrl();" type="text" style="width:100px;" value="' + value + '" />';
    $('.shopUrl').html(change);
};
shop.saveShopUrl = function () {
    var value = $('input[name=shopUrl]').val().trim();
    var regex = /^[a-zA-Z0-9_]{1,100}$/;
    if (!regex.test(value)) {
        popup.msg("Địa chỉ này sai định dạng !");
        return false;
    }
    ajax({
        service: '/shop/getshopbyalias.json',
        data: {value: value},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                $('label[for=alias]').parents('.form-group').removeClass('has-error');
                $('label[for=alias]').next('span[for="alias"]').html('');
                $('.shopUrl').html('http://chodientu.vn/' + value.trim() + '/<a><span class="glyphicon glyphicon-pencil" onclick="shop.quickEdit()" style="cursor: pointer;margin-left: 5px"></span></a>');
            } else {
                if (resp.message != null) {
                    $('label[for=alias]').parents('.form-group').addClass('has-error');
//                    $('div[rel=checkAlias]').children('div[for=alias').css({"color": "#999"});
//                    $('label[for=alias]').css({"color": "#999"});
                    $('label[for=alias]').next('span[for=alias]').text('Địa chỉ truy cập này đã có người sử dụng, bạn hãy chọn 1 địa chỉ khác !');
                    $("#btnUpdate").attr("disabled", "disabled");
                } else if (resp.message == 'Not allow') {
                    popup.msg("Bạn phải đăng nhập lại để thực hiện chức năng này !");
                } else {
                    $('label[for=alias]').parents('.form-group').removeClass('has-error');
                    $('label[for=alias]').next('span[for=alias]').text('');
                    $('label[for=alias]').css({"color": "black"});
                    $('.shopUrl').html('http://chodientu.vn/' + value.trim() + '/<a><span class="glyphicon glyphicon-pencil" onclick="shop.quickEdit()" style="cursor: pointer;margin-left: 5px"></span></a>');
                }
            }
        }
    });
    $("#btnUpdate").removeAttr("disabled");
};

shop.updateInfo = function () {
//    var aaa = $('.shopUrl').text().substring(20);
    var strAlias = $('.shopUrl').text().substring(20);
    var alias = strAlias.trim().replace('/', '');
    var sh = new Object();
    sh.userId = $('input[name=slId]').val();
    sh.alias = alias;
    sh.title = $('input[name=title]').val();
    sh.email = $('input[name=email]').val();
    sh.phone = $('input[name=phone]').val();
    sh.address = $('input[name=address]').val();
    sh.cityId = $('select[name=cityId]').val();
    sh.districtId = $('select[name=districtId]').val();
    $('input[name=title]').parents('.form-group').removeClass('has-error');
    $('input[name=title]').next('span[for="error"]').remove();
    $('input[name=email]').parents('.form-group').removeClass('has-error');
    $('input[name=email]').next('span[for="error"]').remove();
    $('input[name=phone]').parents('.form-group').removeClass('has-error');
    $('input[name=phone]').next('span[for="error"]').remove();
    $('input[name=address]').parents('.form-group').removeClass('has-error');
    $('input[name=address]').next('span[for="error"]').remove();
    $('div[for=city]').removeClass('has-error');
    $('select[name=cityId]').next('span[for="error"]').remove();
    $('div[for=district]').removeClass('has-error');
    $('select[name=districtId]').next('span[for="error"]').remove();
    ajax({
        service: '/shop/add.json',
        data: sh,
        contentType: 'json',
        type: 'post',
        loading: false,
        done: function (rs) {
            if (rs.success) {
                popup.msg(rs.message, function () {
                    $('input[name=shopId]').val(rs.data.userId);
                    shop.showAddress();
                });
            } else {
                $.each(rs.data, function (type, value) {
                    $('input[name=' + type + ']').parents('.form-group').addClass('has-error');
                    $('div[for=' + type + ']').css({"color": "#999"});
                    $('input[name=' + type + ']').after('<span class="help-block" for="error">' + value + '</span>');
                    if (type == 'cityId') {
                        $('div[for=city]').addClass("has-error");
                        $('div[for=city]').children('div').append("<span class='help-block' for='error'>" + value + "</span>");
                    }
                    if (type == 'districtId') {
                        $('div[for=district]').addClass("has-error");
                        $('div[for=district]').children('div').append("<span class='help-block' for='error'>" + value + "</span>");
                    }
                });
            }
        }
    });
};
shop.insertUserContact = function () {
    var addContactForm = new Object();
    var sellerId = $('input[name=slId]').val();
    popup.open('popup-add', 'Thêm user hỗ trợ', template('/user/tpl/addusercontact.tpl', {sellerId: sellerId}), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function () {
                $('#form-add-usercontact div[rel=title]').removeClass('has-error');
                $('#form-add-usercontact div[rel=email]').removeClass('has-error');
                $('#form-add-usercontact div[rel=phone]').removeClass('has-error');
                $('#form-add-usercontact span[name=title]').text("");
                $('#form-add-usercontact span[name=email]').text("");
                $('#form-add-usercontact span[name=phone]').text("");

                addContactForm.sellerId = sellerId;
                addContactForm.title = $('#form-add-usercontact input[name=title]').val();
                addContactForm.phone = $('#form-add-usercontact input[name=phone]').val();
                addContactForm.email = $('#form-add-usercontact input[name=email]').val();
                addContactForm.skype = $('#form-add-usercontact input[name=skype]').val();
                addContactForm.yahoo = $('#form-add-usercontact input[name=yahoo]').val();
                ajax({
                    service: '/shopcontact/add.json',
                    data: addContactForm,
                    contentType: 'json',
                    type: 'post',
                    loading: true,
                    done: function (rs) {
                        if (rs.success) {
                            popup.msg(rs.message, function () {
                                var html = '<tr for="' + rs.data.id + '">\                                                 <td>' + rs.data.title + '</td>\
                                                <td>' + rs.data.email + '</td>\
                                                <td>' + rs.data.phone + '</td>\
                                                <td>' + rs.data.yahoo + '</td>\
                                                <td>' + rs.data.skype + '</td>\
                                                <td><div class="text-center">\
                                                    <a onclick="shop.editUserContact(' + rs.data.id + ');" style="cursor: pointer">Sửa</a> | \
                                                    <a onclick="shop.delUserContact(' + rs.data.id + ');" style="cursor: pointer">Xoá</a>\
                                                </div></td>\
                                            </tr>';
                                $('.listContact').append(html);
                                popup.close('popup-add');
                            });
                        } else {
                            $.each(rs.data, function (type, value) {
                                $('#form-add-usercontact div[rel=' + type + ']').addClass('has-error');
                                $('#form-add-usercontact span[name=' + type + ']').text(value);
                            });
                        }
                    }
                });
            }
        },
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function () {
                popup.close('popup-add');
            }
        }
    ]);
};
shop.delUserContact = function (id) {
    popup.confirm("Bạn có chắc muốn xóa user hỗ trợ này ?", function () {
        ajax({
            service: '/shopcontact/del.json',
            data: {id: id},
            done: function (resp) {
                if (resp.success) {
                    popup.msg(resp.message, function () {
                        $('tr[for=' + id + ']').remove();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
shop.editUserContact = function (id) {
    var editContactForm = new Object();
    ajax({
        service: '/shopcontact/get.json',
        data: {id: id},
        loading: true,
        done: function (resp) {
            if (resp.success) {
                popup.open('popup-edit', 'Sửa thông tin user hỗ trợ', template('/user/tpl/editusercontact.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function () {
                            $('#form-edit-usercontact div[rel=title]').removeClass('has-error');
                            $('#form-edit-usercontact div[rel=email]').removeClass('has-error');
                            $('#form-edit-usercontact div[rel=phone]').removeClass('has-error');
                            $('#form-edit-usercontact span[name=title]').text("");
                            $('#form-edit-usercontact span[name=email]').text("");
                            $('#form-edit-usercontact span[name=phone]').text("");

                            editContactForm.id = id;
                            editContactForm.sellerId = resp.data.sellerId;
                            editContactForm.title = $('#form-edit-usercontact input[name=title]').val();
                            editContactForm.phone = $('#form-edit-usercontact input[name=phone]').val();
                            editContactForm.email = $('#form-edit-usercontact input[name=email]').val();
                            editContactForm.skype = $('#form-edit-usercontact input[name=skype]').val();
                            editContactForm.yahoo = $('#form-edit-usercontact input[name=yahoo]').val();
                            ajax({
                                service: '/shopcontact/edit.json',
                                data: editContactForm,
                                contentType: 'json',
                                type: 'post',
                                loading: true,
                                done: function (rs) {
                                    if (rs.success) {
                                        popup.msg(rs.message, function () {
                                            var html = '<td>' + rs.data.title + '</td>\
                                                <td>' + rs.data.email + '</td>\
                                                <td>' + rs.data.phone + '</td>\
                                                <td>' + rs.data.yahoo + '</td>\
                                                <td>' + rs.data.skype + '</td>\
                                                <td><div class="text-center">\
                                                    <a onclick="shop.editUserContact(' + rs.data.id + ');" style="cursor: pointer">Sửa</a> | \
                                                    <a onclick="shop.delUserContact(' + rs.data.id + ');" style="cursor: pointer">Xóa</a>\
                                                </div></td>';
                                            $('tr[for=' + rs.data.id + ']').html(html);
                                            popup.close('popup-edit');
                                        });
                                    } else {
                                        $.each(rs.data, function (type, value) {
                                            $('#form-edit-usercontact div[rel=' + type + ']').addClass('has-error');
                                            $('#form-edit-usercontact span[name=' + type + ']').text(value);
                                        });
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: 'Hủy',
                        style: 'btn-default',
                        fn: function () {
                            popup.close('popup-edit');
                        }
                    }
                ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

$("#fileupload").on("change", function () {
    // clear error
    $(".imgError").css({"color": "#000"}).html("Cho phép các định dạng jpg, png, gif");
    var files = !!this.files ? this.files : [];
    if (!files.length)
        $(".imgError").css({"color": "red"}).html("Vui lòng chọn ảnh !"); // no file selected, or no FileReader support
    if (/^image/.test(files[0].type)) { // only image file
        ajaxUpload({
            service: '/user/uploadShopLogo.json',
            id: 'logoUpload',
            contentType: 'json',
            loading: false,
            type: 'post',
            done: function (resp) {
                if (resp.success) {
                    //popup.msg('Upload ảnh thành công !');
                    var img = "<img src=" + resp.data.image + " width='313' id='imgCrop' />";
                    $('.imgPlaceHolder').html(img);
                    $('.imgPlaceHolder').parent("div").children(".text-center").removeClass("hide");
                    // area = showCrop();
                    $("#saveImage").removeAttr("disabled");
                    $('#imgCrop').Jcrop({
                        minSize: [147, 130],
                        aspectRatio: 147 / 130,
                        onSelect: shop.showPreview,
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    } else {
        // not image file
        $(".imgError").css({"color": "red"}).html("Chỉ cho phép các định dạng jpg, png, gif !");
    }
});

function showCrop() {
    if ($("#imgCrop").length > 0) {
        return $('#imgCrop').Jcrop({
            minSize: [147, 130],
            aspectRatio: 147 / 130,
            onSelect: shop.showPreview
        });
    }
}
;
shop.saveShopLogo = function () {
    var calculatex1 = $('#x1').val();
    var calculatey1 = $('#y1').val();
    var calculatewidth = $('#w').val();
    var calculateheight = $('#h').val();
    ajaxSubmit({
        service: '/user/cropShopLogo.json?width=' + calculatewidth + '&height=' + calculateheight + '&x1=' + calculatex1 + '&y1=' + calculatey1,
        type: 'post',
        contentType: 'json',
        loading: false,
        done: function (response) {
            console.log(response);
            if (response.success) {
                //$(".note").css({"color": "#42b449"}).html(response.message);
                popup.msg('Cắt hình ảnh thành công !');
                window.setTimeout(function () {
                    location.reload();
                }, 2000);
            } else {
                console.log(response);
            }
        }
    });
};
shop.saveinfoShop = function (type) {
    var value = null;
    var id = $('input[name=shopId]').val();
    if (type == 'infoAbout') {
        value = CKEDITOR.instances['infoAbout'].getData();
    }
    if (type == 'infoGuide') {
        value = CKEDITOR.instances['infoGuide'].getData();
    }
    if (type == 'infoFooter') {
        value = CKEDITOR.instances['infoFooter'].getData();
    }
    ajax({
        service: '/shop/updateinfo.json',
        data: {id: id, type: type, value: value},
        type: 'post',
        loading: false,
        done: function (resp) {
            if (resp.success) {
                popup.msg(resp.message, function () {
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
shop.nextStep = function (step) {
    if (step == 3 || step == 4) {
        popup.msg('Chức năng đang được hoàn thiện !');
    } else {
        location.href = baseUrl + '/user/cau-hinh-shop-step' + step + '.html';
    }
};

shop.showAddress = function () {
    var diachi = $('input[name=address]').val();
    var address = diachi + ",Việt Nam";
    var map = new GMap2(document.getElementById("googleMap"));
//    map.addControl(new GSmallMapControl());
//    map.addControl(new GMapTypeControl());
    geocoder = new GClientGeocoder();
    if (geocoder) {
        geocoder.getLatLng(address, function (point) {
            if (point) {
                $('input[for=lat]').val(point.lat().toFixed(5));
                $('input[for=lng]').val(point.lng().toFixed(5));
                map.clearOverlays()
                if (address == ',Việt Nam') {
                    map.setCenter(point, 5);
                } else {
                    map.setCenter(point, 15);
                }
                var marker = new GMarker(point, {draggable: true});
                map.addOverlay(marker);

                GEvent.addListener(map, "moveend", function () {
                    map.clearOverlays();
                    var center = map.getCenter();
                    var marker = new GMarker(center, {draggable: true});
                    map.addOverlay(marker);
                    $('input[for=lat]').val(center.lat().toFixed(5)).change();
                    $('input[for=lng]').val(center.lng().toFixed(5)).change();

                    GEvent.addListener(marker, "dragend", function () {
                        var pt = marker.getPoint();
                        map.panTo(pt);
                        $('input[for=lat]').val(pt.lat().toFixed(5)).change();
                        $('input[for=lat]').val(pt.lng().toFixed(5)).change();
                    });
                });
            }
        });
    }
};

shop.changeMap = function () {
    var id = $('input[name=shopId]').val();
    var lat = $('input[for=lat]').val();
    var lng = $('input[for=lng]').val();
    if (id == null || id == '') {
        popup.msg("Bạn chưa tạo shop, không thể cập nhật địa chỉ cho shop!")
    } else {
        ajax({
            service: '/shop/changemap.json',
            data: {id: id, lat: lat, lng: lng},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    popup.msg(resp.message);
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    }
};
/**
 * Thêm danh mục shop
 * @returns {undefined}
 */

shop.addShopCategory = function () {
    var name = $("input[name=categoryName]").val();
    var parentId = null;
    var check = $("select[name=parentId]").val();
    if (check != 0) {
        parentId = $("select[name=parentId]").val();
    }
    var weight = $('input[name=weight]').val();
    var shopCate = new Object();
    shopCate.name = name;
    shopCate.weight = weight;
    shopCate.userId = $('input[name=shopId]').val();
    shopCate.parentId = parentId;
    $('div[for=name]').text('');
    $('div[for=parentId]').text('');

    ajax({
        service: '/shopcategory/add.json',
        data: shopCate,
        type: 'post',
        contentType: 'json',
        loading: false,
        done: function (resp) {
            if (resp.success) {
                popup.msg(resp.message, function () {
                    location.reload();
                });
            } else {
                $.each(resp.data, function (type, value) {
                    $('div[for=' + type + ']').text(value);
                });
            }
        }
    });
};

/**
 * Xóa danh mục shop theo id
 * @param {type} id
 * @returns {undefined}
 */
shop.removeCategoryShop = function (id) {
    popup.confirm("Bạn có chắc muốn xóa danh mục này?", function () {
        ajax({
            service: '/shopcategory/del.json',
            data: {id: id},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    popup.msg(resp.message, function () {
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
/**
 * Sửa thông tin danh mục shop
 * @param {type} id
 * @returns {undefined}
 */
shop.editCategoryShop = function (id) {
    ajax({
        service: '/shopcategory/get.json',
        data: {id: id},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                popup.open('popup-edit', 'Sửa thông tin danh mục ' + resp.data.name + '', template('/user/tpl/editshopcategory.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function () {
                            ajaxSubmit({
                                service: '/shopcategory/edit.json',
                                id: 'form-edit-shopcategory',
                                contentType: 'json',
                                loading: false,
                                type: 'post',
                                done: function (rs) {
                                    if (rs.success) {
                                        popup.msg(rs.message, function () {
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
                        fn: function () {
                            popup.close('popup-edit');
                        }
                    }
                ]);
                var html = '<option value="0"> Là danh mục cấp 1 </option>';
                $.each(listCat, function () {
                    if (this.parentId == null) {
                        var idlv1 = this.id;
                        if (resp.data.parentId == this.id) {
                            html += '<option value="' + this.id + '"selected>' + this.name + '</option>';
                        } else {
                            html += '<option value="' + this.id + '">' + this.name + '</option>';
                        }
                        $.each(listCat, function () {
                            if (this.parentId == idlv1) {
                                if (resp.data.parentId == this.id) {
                                    html += '<option value="' + this.id + '"selected> -- ' + this.name + '</option>';
                                } else {
                                    html += '<option value="' + this.id + '"> -- ' + this.name + '</option>';
                                }
                            }
                        });
                    }
                });
                $('#form-edit-shopcategory select[name=parentId]').html(html);
            }
        }
    });
};

/**
 * Thay đổi trạng thái hiển thị
 * @param {type} id
 * @returns {undefined}
 */
shop.changeActive = function (id) {
    ajax({
        service: '/shopcategory/changeactive.json',
        data: {id: id},
        loading: false,
        done: function (rs) {
            if (rs.success) {
                popup.msg(rs.message, function () {
                    location.reload();
                });
            } else {
                popup.msg(rs.message);
            }
        }
    });
};
/**
 * Thay đổi trạng thái nổi bật trang chủ
 * @param {type} id
 * @returns {undefined}
 */
shop.changeHome = function (id) {
    popup.confirm("Bạn có chắc muốn thay đổi trạng thái nổi bật trang chủ của danh mục này?", function () {
        ajax({
            service: '/shopcategory/changehome.json',
            data: {id: id},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    popup.msg(resp.message, function () {
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

/**
 * Thêm danh mục tin tức cho shop 
 */
shop.addShopNewsCategory = function () {
    var name = $("input[name=shopNewsCategoryName]").val();
    var parentId = null;
    var check = $("input[name=parentNewsCate]").filter(':checked').val();
    if (check != 0) {
        parentId = $("select[name=parentId]").val();
    }
    var shopNewsCate = new Object();
    shopNewsCate.name = name;
    shopNewsCate.userId = $('input[name=shopId]').val();
    shopNewsCate.parentId = parentId;
    $('div[for=name]').text('');
    $('div[for=parentId]').text('');
    ajax({
        service: '/shopnewscategory/add.json',
        data: shopNewsCate,
        type: 'post',
        contentType: 'json',
        loading: false,
        done: function (resp) {
            if (resp.success) {
                popup.msg(resp.message, function () {
                    location.reload();
                });
            } else {
                $.each(resp.data, function (type, value) {
                    $('div[for=' + type + ']').text(value);
                });
            }
        }
    });
};

/**
 * Xóa danh mục  tin tức  theo id
 * @param {type} id
 * @returns {undefined}
 */
shop.removeNewsCategory = function (id) {
    popup.confirm("Bạn có chắc muốn xóa danh mục tin tức này?", function () {
        ajax({
            service: '/shopnewscategory/del.json',
            data: {id: id},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    popup.msg(resp.message, function () {
                        location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
/**
 * Thay đổi trạng thái hiển thị của 
 * @param {type} id
 * @returns {undefined}
 */
shop.changeActiveNewsCategory = function (id) {
    ajax({
        service: '/shopnewscategory/changeactive.json',
        data: {id: id},
        loading: false,
        done: function (rs) {
            if (rs.success) {
                popup.msg(rs.message, function () {
                    location.reload();
                });
            } else {
                popup.msg(rs.message);
            }
        }
    });
};
/**
 *Sửa thông tin danh mục tin tức của shop 
 * @param {type} id
 * @returns {undefined}
 */
shop.editNewsCategory = function (id) {
    ajax({
        service: '/shopnewscategory/get.json',
        data: {id: id},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                popup.open('popup-edit', 'Sửa thông tin danh mục ' + resp.data.name + '', template('/user/tpl/editshopnewscategory.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function () {
                            ajaxSubmit({
                                service: '/shopnewscategory/edit.json',
                                id: 'form-edit-shopnewscategory',
                                contentType: 'json',
                                loading: false,
                                type: 'post',
                                done: function (rs) {
                                    if (rs.success) {
                                        popup.msg(rs.message, function () {
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
                        fn: function () {
                            popup.close('popup-edit');
                        }
                    }
                ]);
                var html = '<option value="0"> Là danh mục cấp 1 </option>';
                $.each(listNewsCat, function () {
                    if (this.parentId == null) {
                        if (resp.data.parentId == this.id) {
                            html += '<option value="' + this.id + '"selected>' + this.name + '</option>';
                        } else {
                            html += '<option value="' + this.id + '">' + this.name + '</option>';
                        }
                    }
                });
                $('#form-edit-shopnewscategory select[name=parentId]').html(html);
            }
        }
    });
};

shop.updatePhone = function () {
    var userId = $('input[name=userId]').val();
    var phone = $('input[name=phone]').val();
    ajax({
        service: '/user/updatephone.json',
        data: {userId: userId, phone: phone},
        loading: false,
        done: function (rs) {
            if (rs.success) {
                var usernameOrEmail = null;
                var html = '';
                if (rs.data.username != null && rs.data.username != '') {
                    usernameOrEmail = rs.data.username;
                    html = ' <span class="oc-bullet"></span>\
                        <div class="step-content">\
                            <div class="sf-row">\
                                Tài khoản của bạn chưa được kích hoạt số điện thoại. Bạn phải kích hoạt số điện thoại để bắt đầu bán hàng trên ChợĐiệnTử\
                                <br>\
                                (Soạn tin <b class="text-danger">CDT XM ' + usernameOrEmail + '</b> gửi tới <b class="text-danger">8255</b> để xác minh. Phí <b>2.000đ/tin nhắn</b>)\
                            </div>\
                            <div class="sf-row">\
                                        <a class="btn-remove" href="${baseUrl}"><span class="glyphicon glyphicon glyphicon-remove"></span> Huỷ</a>\
                                        <a href="#" class="btn btn-danger btn-lg disabled">Tiếp tục<span class="glyphicon glyphicon-circle-arrow-right"></span></a>\
                                    </div>\
                        </div>';
                } else {
                    usernameOrEmail = rs.data.email;
                    html = ' <span class="oc-bullet"></span>\
                        <div class="step-content">\
                            <div class="sf-row">\
                                Tài khoản của bạn chưa được kích hoạt số điện thoại. Bạn phải kích hoạt số điện thoại để bắt đầu bán hàng trên ChợĐiệnTử\
                                <br>\
                                (Soạn tin <b class="text-danger">CDT XM ' + usernameOrEmail + '</b> gửi tới <b class="text-danger">8255</b> để xác minh. Phí <b>2.000đ/tin nhắn</b>)\
                            </div>\
                            <div class="sf-row">\
                                        <a class="btn-remove" href="${baseUrl}"><span class="glyphicon glyphicon glyphicon-remove"></span> Huỷ</a>\
                                        <a href="#" class="btn btn-danger btn-lg disabled">Tiếp tục<span class="glyphicon glyphicon-circle-arrow-right"></span></a>\
                                    </div>\
                        </div>';
                }
                $('.step2-active').html(html);
            } else {
                if (rs.data.phone != null) {
                    $("#err_phone").html(rs.data.phone);
                }
            }
        }
    });
};

shop.initOpenStepTwo = function () {
    setInterval(function () {
        ajax({
            service: '/user/checkphoneverified.json',
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    var html = '<span class="oc-bullet"></span>\
                                <div class="step-content">\
                                    <div class="sf-row">Tài khoản của bạn đã được chứng thực số điện thoại</div>\
                                    <span class="r-time" ></span>\
                                    <div class="sf-row"><a class="btn btn-danger btn-lg" href="' + baseUrl + '/user/open-shop-step3.html">Tiếp tục<span class="glyphicon glyphicon-circle-arrow-right"></span></a></div>\
                                </div>';

                    $('.openshop-content').html(html);
                    textUtils.redirect('/user/open-shop-step3.html', 30000);
                }
            }
        });
    }, 5000);
};


shop.nlShopUnlinked = function () {
    popup.open("nl-confirm", "Tích hợp ngân lượng", "Bạn có chắc chắn hủy liên kết với NgânLượng.vn không?", [
        {
            title: 'Đồng ý',
            className: 'button',
            fn: function () {
                ajax({
                    service: '/payment/nlUnlinked.json',
                    loading: false,
                    done: function (resp) {
                        if (resp.success) {
                            popup.close("nl-confirm");
                            popup.msg("Bạn đã hủy kết nối với NgânLượng.vn thành công!", function () {
                                window.location.href = "../user/open-shop-step3.html";
                            });
                        } else {
                            popup.msg(resp.message);
                        }
                    }
                });
            }
        },
        {
            title: 'Đóng',
            className: 'ui-button-text',
            fn: function () {
                popup.close("nl-confirm");
            }
        }
    ]);
};

shop.scShopUnlinked = function () {
    popup.open("nl-confirm", "Tích hợp ShipChung", "Bạn có chắc chắn hủy liên kết với Shipchung không?", [
        {
            title: 'Đồng ý',
            className: 'button',
            fn: function () {
                ajax({
                    service: '/payment/nlUnlinked.json?type=sc',
                    loading: false,
                    done: function (resp) {
                        if (resp.success) {
                            popup.close("nl-confirm");
                            popup.msg("Bạn đã hủy kết nối với Shipchung thành công!", function () {
                                window.location.href = "../user/open-shop-step3.html";
                            });
                        } else {
                            popup.msg(resp.message);
                        }
                    }
                });
            }
        },
        {
            title: 'Đóng',
            className: 'ui-button-text',
            fn: function () {
                popup.close("nl-confirm");
            }
        }
    ]);
};

shop.initOpenStepThree = function () {
    $("#nl-shop-integration").click(function () {
        popup.open("nl-confirm", "Tích hợp ngân lượng", "Bạn sẽ được chuyển sang NgânLượng.vn để đăng nhập vào tài khoản của mình, sau khi đăng nhập thành công bạn sẽ được đưa trở lại trang này với mối liên kết đã được thiết lập.", [
            {
                title: 'Đồng ý',
                className: 'button',
                fn: function () {
                    ajax({
                        service: '/shop/nlIntegrate.json',
                        loading: false,
                        done: function (resp) {
                            if (resp.success) {
                                window.location.href = resp.data;
                            } else {
                                popup.msg(resp.message);
                            }
                        }
                    });
                }
            },
            {
                title: 'Đóng',
                className: 'ui-button-text',
                fn: function () {
                    popup.close("nl-confirm");
                }
            }
        ]);
    });
    $("#sc-shop-integration").click(function () {
        popup.open("nl-confirm", "Tích hợp Ship Chung", "Bạn có muốn tích hợp shipchung để hưởng lợi ưu đãi của dịch vụ.", [
            {
                title: 'Đồng ý',
                className: 'button',
                fn: function () {
//                    ajax({
//                        service: '/shop/nlIntegrate.json?type=sc',
//                        loading: false,
//                        done: function(resp) {
//                            if (resp.success) {
//                                window.location.href = resp.data;
//                            } else {
//                                popup.msg(resp.message);
//                            }
//                        }
//                    });
//                    var redirect_uri = baseUrl + "/user/cau-hinh-tich-hop.html";
//                    location.href = "http://mc.shipchung.vn/openid.html?response_type=code&client_id=cdt_access&state=login&redirect_uri=" + redirect_uri;
                    location.href = "http://mc.shipchung.vn/openid.html?response_type=code&client_id=cdt_access&state=login";
                }
            },
            {
                title: 'Đóng',
                className: 'ui-button-text',
                fn: function () {
                    popup.close("nl-confirm");
                }
            }
        ]);
    });
};

shop.nextStepOpenShop = function () {
    if (!$('input[name=stepcheck]').attr('checked')) {
        $('#err_check').html("Bạn phải đồng ý với <a href='#'>điều kiện mở Shop bán hàng tại ChợĐiệnTử</a>");
    } else {
        $('#err_check').html('');
        location.href = baseUrl + '/user/open-shop-step2.html';
    }
};
shop.FinishOpen = function () {
    xengplus.plus(2000);
};