featuredcategory = {};
featuredcategory.init = function() {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị CM nổi bật", "/cp/featuredcategory.html"],
        ["Danh sách CM nổi bật"]
    ]);
};
featuredcategory.countCharacters = function() {
    var count = 70 - eval($('input[name=title]').val().trim().length);
    $('span.countresult').html(count + '/70');
};
function showPreview(coords)
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
function previewFeaturedCategory(img, selection) {
    if (!selection.width || !selection.height) {
        return;
    }
    var scaleX = 100 / selection.width;
    var scaleY = 100 / selection.height;

    $('#preview img').css({
        width: Math.round(scaleX * 120),
        height: Math.round(scaleY * 120),
        marginLeft: -Math.round(scaleX * selection.x1),
        marginTop: -Math.round(scaleY * selection.y1)
    });

    // get image
    var image = document.getElementById('photo');
    // get ti le anh
    var tiLeAnh = image.naturalWidth / image.clientWidth;

    // calculate
    var calculatex1 = Math.round(selection.x1 * tiLeAnh);
    var calculatey1 = Math.round(selection.y1 * tiLeAnh);
    var calculatewidth = Math.round(selection.width * tiLeAnh);
    var calculateheight = Math.round(selection.height * tiLeAnh);
    $('#x1').val(calculatex1);
    $('#y1').val(calculatey1);
    $('#x2').val(selection.x2);
    $('#y2').val(selection.y2);
    $('#w').val(calculatewidth);
    $('#h').val(calculateheight);
}
featuredcategory.add = function() {
    popup.open('popup-addHB', 'Thêm chuyên mục nổi bật', template('/cp/tpl/featuredcategory/add.tpl', null),
            [
                {
                    title: 'Lưu',
                    style: 'btn-primary',
                    fn: function() {
                        ajaxSubmit({
                            service: '/cpservice/featuredcategory/add.json',
                            id: 'form-categorys',
                            contentType: 'json',
                            done: function(rs) {
                                if (rs.success) {
                                    popup.msg("Thêm chuyên mục nổi bật thành công", function() {
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
                        popup.close('popup-addHB');
                    }
                }
            ]);
    ajax({
        service: "/cpservice/featuredcategory/getchilds.json",
        done: function(resp) {
            var htmlCate = "";
            htmlCate += "<option value='0'>-- Chọn danh mục --</option>";
            $.each(resp, function() {
                htmlCate += "<option value='" + this.id + "'>" + this.name + "</option>";
            });
            $('#cateC').html(htmlCate);
        }
    });

};
featuredcategory.changeTemplate = function(val) {
    var htmlT = "";
    if (val == '' || val === '0') {
        popup.msg("Mời bạn chọn lại template");
        $('#demoTem').html("");
        return false;

    }
    htmlT += "<img src='" + staticUrl + "/cp/img/demo/" + val + ".jpg' width='580'/>";
    $('#demoTem').html(htmlT);
};
featuredcategory.addsub = function(id) {
    popup.open('popup-addHB', 'Thêm chuyên mục nổi bật con', template('/cp/tpl/featuredcategory/addsub.tpl', {id: id}),
            [
                {
                    title: 'Lưu',
                    style: 'btn-primary',
                    fn: function() {
                        ajaxSubmit({
                            service: '/cpservice/featuredcategory/addsub.json',
                            id: 'form-categorys',
                            contentType: 'json',
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
                    title: 'Hủy',
                    style: 'btn-default',
                    fn: function() {
                        popup.close('popup-addHB');

                    }
                }
            ]);
    ajax({
        data: {id: id},
        service: "/cpservice/global/category/getchilds.json",
        done: function(resp) {
            if (resp.success) {
                var htmlCateSub = "";
                htmlCateSub += "<option value='0'>-- Chọn danh mục con --</option>";
                $.each(resp.data, function() {
                    htmlCateSub += "<option value='" + this.id + "'>" + this.name + "</option>";
                });

                $('#form-categorys select[name=categorySubId]').html(htmlCateSub);
            } else {
                popup.msg(resp.message);
            }
        }
    });

};

featuredcategory.saveTemplate = function(id) {
    var subId = $('select[name=subId]').val();
    ajax({
        data: {id: subId},
        service: "/cpservice/featuredcategory/gettemplate.json",
        done: function(resp) {
            if (resp.success) {
                $('#content_box').html(template('/cp/tpl/featuredcategory/' + resp.data.template + '.tpl', resp.data));
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
featuredcategory.delCate = function(id) {
    popup.confirm("Bạn có chắc muốn xóa?", function() {
        ajax({
            service: '/cpservice/featuredcategory/delfeaturedcategory.json',
            data: {idCategory: id},
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
featuredcategory.delCateSub = function(id) {
    popup.confirm("Bạn có chắc muốn xóa?", function() {
        ajax({
            service: '/cpservice/featuredcategory/delfeaturedcategorysub.json',
            data: {idCategory: id},
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
featuredcategory.changePositionCate = function(idCate, position) {
    ajax({
        data: {idCategory: idCate, position: position},
        service: "/cpservice/featuredcategory/changepositonfeaturedcategory.json",
        done: function(resp) {
            if (resp.success) {
                document.location = $(location).attr('href');
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
featuredcategory.changePositionCateSub = function(idCate, position) {
    ajax({
        data: {idCategory: idCate, position: position},
        service: "/cpservice/featuredcategory/changepositonfeaturedcategorysub.json",
        done: function(resp) {
            if (resp.success) {
                document.location = $(location).attr('href');
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
featuredcategory.changeNameCate = function(idCate, name) {
    ajax({
        service: '/cpservice/featuredcategory/changenamefeaturedcategory.json',
        data: {categoryName: name, categoryId: idCate},
        type: 'post',
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
featuredcategory.changeNameCateSub = function(idCate, name) {
    ajax({
        service: '/cpservice/featuredcategory/changenamefeaturedcategorysub.json',
        data: {categorySubName: name, categorySubId: idCate},
        type: 'post',
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
featuredcategory.changeStatusCate = function(id) {
    ajax({
        service: '/cpservice/featuredcategory/changestatusfeaturedcategory.json',
        data: {idCategory: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                if (resp.data.active) {
                    $('a[editStatus=' + resp.data.categoryId + ']').html('<img src="' + staticUrl + '/cp/img/icon-enable.png">');
                } else {
                    $('a[editStatus=' + resp.data.categoryId + ']').html('<img src="' + staticUrl + '/cp/img/icon-disable.png">');
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });

};
featuredcategory.changeStatusCateSub = function(id) {
    ajax({
        service: '/cpservice/featuredcategory/changestatusfeaturedcategorysub.json',
        data: {idCategory: id},
        loading: false,
        done: function(resp) {
            if (resp.success) {
                if (resp.data.active) {
                    $('a[editStatus=' + resp.data.categorySubId + ']').html('<img src="' + staticUrl + '/cp/img/icon-enable.png">');
                } else {
                    $('a[editStatus=' + resp.data.categorySubId + ']').html('<img src="' + staticUrl + '/cp/img/icon-disable.png">');
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });

};
featuredcategory.uploadImageItem = function() {
    var width = $('input[name=widthF]').val();
    var height = $('input[name=heightF]').val();
    var itemIdDel = $('#photo').attr("for");
    ajaxUpload({
        service: '/cpservice/featuredcategory/uploadimageItem.json?width=' + width + '&height=' + height + '&itemIdDel=' + itemIdDel,
        id: 'form-edit-uploadImg',
        contentType: 'json',
        done: function(resp) {
            if (resp.success) {
                $('.thumbnail').html('<img id="photo" for="' + resp.data.itemId + '" src="' + resp.data.image + '" />');
                $('#photo').Jcrop({
                    onChange: showPreview,
                    minSize: [width, height],
                    aspectRatio: width / height
                });

                $('input[name=downloadImageItem]').val('');

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
featuredcategory.downloadImageItem = function() {
    var url = $('input[name=downloadImageItem]').val();
    var itemId = $('#photo').attr("for");

    var width = $('input[name=widthF]').val();
    var height = $('input[name=heightF]').val();
    ajax({
        data: {url: url, itemId: itemId, width: width, height: height},
        service: "/cpservice/featuredcategory/downloadimageItem.json",
        done: function(resp) {
            if (resp.success) {
                $('.thumbnail').html('<img id="photo" for="' + resp.data.itemId + '" src="' + resp.data.image + '" />');
                $('#photo').Jcrop({
                    onChange: showPreview,
                    minSize: [width, height],
                    aspectRatio: width / height
                });
                $('input[name=downloadImageItem]').val('');
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
featuredcategory.addItemSize = [];
featuredcategory.addItem = function(position, idFeaturedCategory, tpl) {
    var templateL = tpl;
    if (tpl === 1) {
        if (position >= 1 && position <= 2) {
            featuredcategory.addItemSize.width = 298;
            featuredcategory.addItemSize.height = 381;
        }
        if (position >= 3 && position <= 8) {
            featuredcategory.addItemSize.width = 199;
            featuredcategory.addItemSize.height = 190;
        }
    }
    if (tpl === 2) {
        if (position === 1) {
            featuredcategory.addItemSize.width = 395;
            featuredcategory.addItemSize.height = 375
        }
        if (position >= 2 && position <= 5) {
            featuredcategory.addItemSize.width = 199;
            featuredcategory.addItemSize.height = 190;
        }
    }
    if (tpl === 3) {
        if (position >= 1 && position <= 4) {
            featuredcategory.addItemSize.width = 199;
            featuredcategory.addItemSize.height = 190;
        }
    }
    if (tpl === 4) {
        if (position === 1) {
            featuredcategory.addItemSize.width = 401;
            featuredcategory.addItemSize.height = 383
        }
        if (position >= 2 && position <= 4) {
            featuredcategory.addItemSize.width = 201;
            featuredcategory.addItemSize.height = 260;
        }
        if (position === 5) {
            featuredcategory.addItemSize.width = 185;
            featuredcategory.addItemSize.height = 345
        }
    }
    if (tpl === 5) {
        if (position >= 1 && position <= 4) {
            featuredcategory.addItemSize.width = 199;
            featuredcategory.addItemSize.height = 190;
        }
    }
    if (tpl === 6) {
        if (position >= 1 && position <= 4) {
            featuredcategory.addItemSize.width = 199;
            featuredcategory.addItemSize.height = 190;
        }
    }
    var itemId = null;
    itemId = $('input[name=itemIdT' + position + ']').val();
    if (itemId === undefined) {
        itemId = 0;
    }
    var idCategory = $('input[name=idCategory]').val();
    var wM = featuredcategory.addItemSize.width;
    var hM = featuredcategory.addItemSize.height;
    ajax({
        service: '/cpservice/featuredcategory/getitemfeaturedcategorysub.json',
        data: {itemId: itemId, idCategory: idCategory},
        done: function(resp) {
            if (resp.success) {
                //
                popup.open('popup-addHB', 'Thay sản phẩm', template('/cp/tpl/featuredcategory/additem.tpl', {width: featuredcategory.addItemSize.width, height: featuredcategory.addItemSize.height, datas: resp}),
                        [
                            {
                                title: 'Lưu',
                                style: 'btn-primary',
                                fn: function() {
                                    var datas = new Object();
                                    datas.id = idFeaturedCategory;
                                    datas.categorySubId = idCategory;
                                    datas.categoryItemHomes = [];
                                    var categoryItemHome = {};
                                    categoryItemHome.itemId = $('input[name=itemId]').val();
                                    categoryItemHome.position = position;
                                    categoryItemHome.title = $('input[name=title]').val();
                                    categoryItemHome.x = $('#x1').val();
                                    categoryItemHome.y = $('#y1').val();
                                    categoryItemHome.width = $('#w').val();
                                    categoryItemHome.height = $('#h').val();
                                    categoryItemHome.image = $('#photo').attr("src");
                                    categoryItemHome.idItemOld = $('#photo').attr("for");
                                    datas.categoryItemHomes.push(categoryItemHome);
                                    ajax({
                                        service: '/cpservice/featuredcategory/additemtemplate.json?tpl=' + tpl,
                                        data: datas,
                                        type: 'POST',
                                        contentType: 'json',
                                        done: function(rs) {
                                            if (rs.success) {
                                                popup.msg(rs.message, function() {
                                                    popup.close('popup-addHB');
                                                    featuredcategory.saveTemplate(datas.categorySubId);
                                                    //location.reload();
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
                                    popup.close('popup-addHB');
                                }
                            }
                        ]);

                featuredcategory.countCharacters();
                $('#photo').Jcrop({
                    onChange: showPreview,
                    minSize: [wM, hM],
                    aspectRatio: wM / hM
                });

            } else {
                popup.msg(resp.message);
            }
        }
    });


};

featuredcategory.addBanner = function(position, idFeaturedCategory, tpl) {
    var idCategory = $('input[name=idCategory]').val();
    var templateL = tpl;
    var bannerId = null;
    var width = 0;
    var height = 0;
    bannerId = $('input[name=bannerIdT' + position + ']').val();

    if (bannerId === undefined) {
        bannerId = 0;
    }
    if (tpl === 1) {
        width = 400;
        height = 381;
    }
    if (tpl === 2) {
        width = 198;
        height = 381;
    }
    if (tpl === 4) {
        width = 598;
        height = 122;
    }
    if (tpl === 3) {
        if (position === 4) {
            width = 398;
            height = 190;
        } else {
            width = 199;
            height = 126;
        }
    }
    if (tpl === 5) {
        width = 399;
        height = 190;
    }
    ajax({
        service: '/cpservice/featuredcategory/getbannerfeaturedcategorysub.json',
        data: {bannerId: bannerId, idCategory: idCategory},
        done: function(resp) {
            if (resp.success) {
                ///t' + templateL + '
                popup.open('popup-addHB', 'Thay Banner', template('/cp/tpl/featuredcategory/addbanner.tpl', {width: width, height: height, datas: resp}),
                        [
                            {
                                title: 'Lưu',
                                style: 'btn-primary',
                                fn: function() {
                                    var datas = new Object();
                                    datas.id = idFeaturedCategory;
                                    datas.categorySubId = idCategory;
                                    datas.categoryBannerHomes = [];
                                    var categoryBannerHome = {};
                                    //categoryBannerHome.bannerId = $('input[name=bannerIdT1]').val();
                                    categoryBannerHome.position = position;
                                    categoryBannerHome.id = $('input[name=id]').val();
                                    categoryBannerHome.url = $('input[name=url]').val();
                                    categoryBannerHome.x = $('#x1').val();
                                    categoryBannerHome.y = $('#y1').val();
                                    categoryBannerHome.width = $('#w').val();
                                    categoryBannerHome.height = $('#h').val();
                                    categoryBannerHome.image = $('#photo').attr("src");
                                    categoryBannerHome.idBannerOld = $('#photo').attr("for");
                                    datas.categoryBannerHomes.push(categoryBannerHome);
                                    ajax({
                                        service: '/cpservice/featuredcategory/additemtemplate.json?tpl=' + tpl,
                                        data: datas,
                                        type: 'POST',
                                        contentType: 'json',
                                        done: function(rs) {
                                            if (rs.success) {
                                                popup.msg(rs.message, function() {
                                                    popup.close('popup-addHB');
                                                    featuredcategory.saveTemplate(datas.categorySubId);
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

                                    popup.close('popup-addHB');
                                }
                            }
                        ], 'modal-lg');
                $('#photo').Jcrop({
                    onChange: showPreview,
                    minSize: [width, height],
                    aspectRatio: width / height
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

featuredcategory.addLogo = function(idFeaturedCategory, numbers, tpl) {
    var idCategory = $('input[name=idCategory]').val();
    ajax({
        service: '/cpservice/featuredcategory/getmanufacturerfeaturedcategorysub.json',
        data: {idCategory: idCategory},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-addHB', 'Thay sản phẩm', template('/cp/tpl/featuredcategory/addlogo.tpl', {number: numbers, datas: resp}),
                        [
                            {
                                title: 'Lưu',
                                style: 'btn-primary',
                                fn: function() {
                                    var datas = new Object();
                                    datas.id = idFeaturedCategory;
                                    datas.categorySubId = idCategory;
                                    datas.categoryManufacturerHomes = [];
                                    for (var i = 1; i <= numbers; i++) {
                                        var categoryManufacturerHome = {};
                                        categoryManufacturerHome.manufacturerId = $('input[name=manufacturerId' + i + ']').val();
                                        categoryManufacturerHome.position = i;
                                        datas.categoryManufacturerHomes.push(categoryManufacturerHome);
                                    }

                                    ajax({
                                        service: '/cpservice/featuredcategory/additemtemplate.json?tpl=' + tpl,
                                        data: datas,
                                        type: 'POST',
                                        contentType: 'json',
                                        done: function(rs) {
                                            if (rs.success) {
                                                popup.msg(rs.message, function() {
                                                    popup.close('popup-addHB');
                                                    featuredcategory.saveTemplate(datas.categorySubId);
                                                });
                                            } else {
                                                $.each($('.addLogo input'), function() {
                                                    var name = $(this).attr('name');
                                                    if (typeof rs.data[name] !== 'undefined') {
                                                        $(this).parents('.form-group').removeClass('has-error');
                                                        $(this).next('.help-block').remove();
                                                        $(this).parents('.form-group').addClass('has-error');
                                                        $(this).after('<span class="help-block">' + rs.data[name] + '</span>');
                                                    } else {
                                                        $(this).parents('.form-group').removeClass('has-error');
                                                        $(this).next('.help-block').remove();
                                                    }
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

                                    popup.close('popup-addHB');
                                }
                            }
                        ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
featuredcategory.addmodelmanufacture = function(idFeaturedCategory, numbers, tpl) {
    var idCategory = $('input[name=idCategory]').val();
    ajax({
        service: '/cpservice/featuredcategory/getmanufacturerfeaturedcategorysub.json',
        data: {idCategory: idCategory},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-addHB', 'Thay sản phẩm', template('/cp/tpl/featuredcategory/addmodelmanufacture.tpl', {number: numbers, datas: resp}),
                        [
                            {
                                title: 'Lưu',
                                style: 'btn-primary',
                                fn: function() {
                                    var datas = new Object();
                                    datas.id = idFeaturedCategory;
                                    datas.categorySubId = idCategory;
                                    datas.categoryManufacturerHomes = [];
                                    for (var i = 1; i <= numbers; i++) {
                                        var categoryManufacturerHome = {};
                                        categoryManufacturerHome.modelIds = [];
                                        categoryManufacturerHome.manufacturerId = $('input[name=manufacturerId' + i + ']').val();
                                        categoryManufacturerHome.position = i;
                                        categoryManufacturerHome.modelIds = $('input[name=modelIds' + i + ']').val().split(",");
                                        datas.categoryManufacturerHomes.push(categoryManufacturerHome);
                                    }
                                    $("#addLogo input[name=]").each(function() {
                                        $(this).toggleClass("example");
                                    });
                                    ajax({
                                        service: '/cpservice/featuredcategory/additemtemplate.json?tpl=' + tpl,
                                        data: datas,
                                        type: 'POST',
                                        contentType: 'json',
                                        done: function(rs) {
                                            if (rs.success) {
                                                popup.msg(rs.message, function() {
                                                    popup.close('popup-addHB');
                                                    featuredcategory.saveTemplate(datas.categorySubId);
                                                });
                                            } else {
                                                if (rs.message != null && rs.message != '') {
                                                    popup.msg(rs.message);
                                                }
                                                $.each($('.addLogo input'), function() {
                                                    var name = $(this).attr('name');
                                                    if (typeof rs.data[name] !== 'undefined') {
                                                        $(this).parents('.form-group').removeClass('has-error');
                                                        $(this).next('.help-block').remove();
                                                        $(this).parents('.form-group').addClass('has-error');
                                                        $(this).after('<span class="help-block">' + rs.data[name] + '</span>');
                                                    } else {
                                                        $(this).parents('.form-group').removeClass('has-error');
                                                        $(this).next('.help-block').remove();
                                                    }
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

                                    popup.close('popup-addHB');
                                }
                            }
                        ]);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

featuredcategory.get = function(id) {
    if ($('input[name=title]').val().trim() === '' && $('input[name=itemId]').val().trim() !== '') {
        ajax({
            service: '/cpservice/global/item/getitem.json',
            data: {'id': id},
            done: function(resp) {
                if (resp.success) {
                    $('input[name=title]').val(resp.data.name);
                    featuredcategory.countCharacters();
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    }
};


