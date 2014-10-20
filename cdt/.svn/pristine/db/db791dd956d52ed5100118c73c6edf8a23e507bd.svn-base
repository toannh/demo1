additemquick = {};
additemquick.itemNoChangeWeight = [];
additemquick.init = function () {
    var numberItem = 5;
    if ($('body').width() <= 767) {
        numberItem = 1;
    }
    for (var i = 0; i < numberItem; i++) {
        additemquick.createNewItem();
    }

    // build category
    $.each(cates, function () {
        $("select[for=cate].category_0").append("<option value='" + this.id + "' >" + this.name + "</option>");
    });
    $("select[for=cate].category_0").change(function () {
        additemquick.builtCategory(0, $(this).val());
    });

    // build shopcategory
    if (typeof scates != 'undefined' && scates.length > 0) {
        $.each(scates, function () {
            $("select[for=shopcate].shopCate_0").append("<option value='" + this.id + "' >" + this.name + "</option>");
        });
        $("select[for=shopcate].shopCate_0").change(function () {
            additemquick.builtShopCategory(0, $(this).val());
        });
    }
};
additemquick.createNewItem = function (name, img) {
    ajax({
        service: '/itemquick/updateproductquick.json',
        type: 'POST',
        done: function (resp) {
            if (resp.success) {
                $('div.listItem').append(template('/user/tpl/quickitemtemp/addquick.tpl', resp));
                textUtils.inputNumberFormat('inputnumber');
                additemquick.initEditor('detail_' + resp.data.id);
                additemquick.itemNoChangeWeight.push(resp.data.id);
                $('div[foritem=' + resp.data.id + '] input[name=weight]').change(function () {
                    additemquick.itemNoChangeWeight.splice(additemquick.itemNoChangeWeight.indexOf(resp.data.id), 1);
                });
                if (typeof name !== 'undefined') {
                    $("div[foritem=" + resp.data.id + "] input[name=name]").val(name).change();
                    $("#img-add-form-bylink-" + resp.data.id + " input[name=imageUrl]").val(img).change();
                    ajax({
                        service: '/item/addimage.json',
                        data: {itemId: resp.data.id, img: img},
                        loading: false,
                        done: function (resp) {
                            additemquick.drawImage(resp.data.id, resp);
                        }
                    });
                }
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
additemquick.removeItem = function (itemId) {
    popup.confirm("Bạn có chắc chắn muốn xóa?", function () {
        ajax({
            service: '/itemquick/removeproductquick.json',
            data: {id: itemId},
            done: function (resp) {
                if (resp.success) {
                    $('div[foritem=' + itemId + ']').remove();
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
additemquick.builtCategory = function (level, pId) {
    var element = $('select[for="cate"].category_' + level);
    if (typeof pId != 'undefined' && pId != '' && pId !== 0) {
        ajax({
            service: '/category/getchilds.json',
            data: {id: pId},
            loading: false,
            done: function (resp) {
                if (!resp.success)
                    popup.msg(resp.message);
                else {
                    $(element).parent().nextAll().remove();
                    if (resp.data.length > 0) {
                        var html = '<div class="form-group"><select for="cate" class="form-control category_' + (level + 1) + '" level="' + (level + 1) + '" >';
                        html += '<option value="">-- Chọn danh mục --</option>';
                        $.each(resp.data, function () {
                            html += '<option value="' + this.id + '">' + this.name + '</option>';
                        });
                        html += '</select></div>';
                        $('select[for="cate"].category_' + level).parent().parent().append(html);

                        $('select[for="cate"].category_' + (level + 1)).change(function () {
                            additemquick.builtCategory(level + 1, $(this).val());
                        });
                    }
                }
                $('input[name=categoryId]').val($('select[for="cate"].category_' + level).val());
            }
        });
    } else {
        $(element).parent().nextAll().remove();
        $('input[name=categoryId]').val($(element).parent().prev().prev().find('select').val());
    }

};

additemquick.builtShopCategory = function (level, pId) {
    var element = $('select[for="shopcate"].shopCate_' + level);
    if (typeof pId != 'undefined' && pId != '' && pId !== 0) {
        ajax({
            service: '/shopcategory/getchilds.json',
            data: {id: pId},
            loading: false,
            done: function (resp) {
                if (!resp.success)
                    popup.msg(resp.message);
                else {
                    $(element).parent().nextAll().remove();
                    if (resp.data.length > 0) {
                        var html = '<div class="form-group"><select for="shopcate" class="form-control shopCate_' + (level + 1) + '" level="' + (level + 1) + '" >';
                        html += '<option value="">-- Chọn danh mục --</option>';
                        $.each(resp.data, function () {
                            html += '<option value="' + this.id + '">' + this.name + '</option>';
                        });
                        html += '</select></div>';
                        $('select[for="shopcate"].shopCate_' + level).parent().parent().append(html);

                        $('select[for="shopcate"].shopCate_' + (level + 1)).change(function () {
                            additemquick.builtShopCategory(level + 1, $(this).val());
                        });
                    }
                }
                $('input[name=shopCategoryId]').val($('select[for="shopcate"].shopCate_' + level).val());
            }
        });

        if (additemquick.itemNoChangeWeight.length > 0) {
            ajax({
                service: '/shopcategory/get.json',
                data: {id: pId},
                loading: false,
                done: function (resp) {
                    if (resp.success && resp.data != null && resp.data.weight > 0) {
                        $.each(additemquick.itemNoChangeWeight, function () {
                            $('div[foritem=' + this + '] input[name=weight]').val(parseFloat(resp.data.weight).toMoney(0, ',', '.'));
                        });
                    }
                }
            });
        }

    } else {
        $(element).parent().nextAll().remove();
        $('input[name=shopCategoryId]').val($(element).parent().prev().prev().find('select').val());
    }
};

additemquick.activeNow = function () {
    popup.confirm("Bạn có chắc chắn muốn kích hoạt chức năng này?", function () {
        ajax({
            service: '/itemquick/activenow.json',
            type: 'get',
            done: function (resp) {
                if (resp.success) {
                    popup.msg("Kích hoạt thành công chức năng đăng bán nhanh", function () {
                        window.location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });

};

additemquick.initEditor = function (id) {
    CKEDITOR.on('instanceReady', function (e) {
        if (e.editor.name == id) {
            e.editor.on('blur', function (ev) {
                additemquick.editorEdited = e.editor.checkDirty();
                if (additemquick.editorEdited) {
                    additemquick.downloadImageFromEditor(e.editor.name);
                    e.editor.resetDirty();
                }
            });
        }
    });
    CKEDITOR.basePath = staticUrl + '/lib/ckeditor/';

    var config = {extraPlugins: 'confighelper', autoGrow_maxHeight: 400};
    CKEDITOR.replace(id, config);
};

additemquick.downloadImageFromEditor = function (id) {
    var itemId = id.split("detail_")[1];
    var detail = CKEDITOR.instances[id].getData();
    var temp_container = $("<div></div>");
    var arrImg = new Array();
    temp_container.html(detail);
    temp_container.find("img").each(function (i, el) {
        arrImg.push($(el).attr('src'));
    });
    if (arrImg.length > 0) {
        ajax({
            service: '/item/downloadfromeditor.json',
            data: {imgs: JSON.stringify(arrImg), id: itemId},
            loading: true,
            done: function (resp) {
                if (resp.success) {
                    $.each(resp.data['imagesMapping'], function (keyword, value) {
                        temp_container.find("img").each(function (i, el) {
                            if ($(el).attr('src') == keyword) {
                                $(el).attr('src', value);
                            }
                        });
                    });
                    CKEDITOR.instances[id].setData(temp_container.html());

                    if (typeof resp.data["images"] !== 'undefined' && resp.data["images"] != null) {
                        $.each(resp.data["images"], function (key, value) {
                            $("div[foritem=" + itemId + "] div[rel=img]").append('<div for="img' + key + '" imgageId="' + key + '" class="submit-img-item">\
                                    <span class="glyphicon glyphicon-trash"></span>\
                                    <div class="img" style="width:55px;height:55px;overflow:hidden;"><img src="' + value + '" /></div>\
                                </div>');
                            $('div[foritem=' + itemId + '] div[for=img' + key + '] .glyphicon-trash').click(function () {
                                additemquick.delImages(itemId, $('div[for=img' + key + ']').attr("imgageId"));
                            });
                            $('div[foritem=' + itemId + '] div[for=img' + key + '] .img').click(function () {
                                additemquick.changeDefaultImage(key, itemId);
                            });
                        });
                        additemquick.getdefaultimage(itemId);
                    }
                }
            }
        });
    }
    if (detail.length > 0) {
        ajax({
            service: '/itemquick/updatedetail.json',
            data: {detail: detail, itemId: itemId},
            loading: false,
            type: 'post',
            contentType: 'json',
            done: function (resp) {
            }
        });
    }
};

additemquick.changeDefaultImage = function (imageId, itemId) {
    ajax({
        service: '/item/setdefaultimage.json',
        data: {imageId: imageId, id: itemId},
        loading: true,
        done: function (resp) {
            if (resp.success) {
                $('div[foritem=' + itemId + '] div[rel=img] .icon-img-default').remove();
                $.each($("div[foritem=" + itemId + "] div[rel=img] div[imgageid=" + resp.data + "]"), function (i) {
                    if (i == 0) {
                        $(this).append('<div class="icon-img-default"></div>');
                    }
                });
            }
        }
    });
};

additemquick.getdefaultimage = function (itemId) {
    ajax({
        service: '/item/getdefaultimage.json',
        data: {id: itemId},
        loading: false,
        done: function (result) {
            if (result.success) {
                var ex = false;
                $('div[foritem=' + itemId + '] div[rel=img] .icon-img-default').remove();
                $.each($("div[foritem=" + itemId + "] div[imgageid]"), function () {
                    if (!ex && $(this).attr('imgageid') == result.data) {
                        $(this).append('<div class="icon-img-default"></div>');
                        ex = true;
                    }
                });
            }
        }
    });
};

additemquick.addImageByLocal = function (itemId) {
    $("div[foritem=" + itemId + "] input[name=id]").val(itemId);
    ajaxUpload({
        service: '/item/addimage.json',
        id: 'img-add-form-' + itemId,
        loading: false,
        done: function (resp) {
            additemquick.drawImage(itemId, resp);
        }
    });
};

additemquick.delImages = function (id, imageName) {
    popup.confirm("Bạn có chắc chắn muốn xóa ảnh này?", function () {
        ajax({
            service: '/item/delimage.json',
            data: {id: id, name: imageName},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    $('div[foritem=' + id + '] div[rel=img] div[for=img' + imageName + ']').remove();
                    additemquick.getdefaultimage(id);
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

additemquick.popupWeight = function (itemId) {
    var html = '<p><strong>Điền kích thước</strong><span class="small clr-red">Chỉ áp dụng cho hàng hóa cồng kềnh</span></p>\
                <div class="form-inline conversion-feature">\
                    <div class="form-group">\
                        <input class="form-control inputnumber" name="dataLength" type="text" placeholder="Dài (cm)">\
                    </div>\
                    <div class="form-group">\
                        <input class="form-control inputnumber" name="dataWidth" type="text" placeholder="Rộng (cm)">\
                    </div>\
                    <div class="form-group">\
                        <input class="form-control inputnumber" name="dataHeight" type="text" placeholder="Cao (cm)">\
                    </div>\
                    <div class="text-center mgt-15"><button class="btn btn-default btn-sm " onclick="additemquick.calcWeight(\'' + itemId + '\')" data-dismiss="modal">Tính năng quy đổi</button></div>\
                </div>';
    $('.modal-body').html(html);
    textUtils.inputNumberFormat('inputnumber');
};

additemquick.calcWeight = function (itemId) {
    var length = $('input[name=dataLength]').val().replace(/\./g, '');
    var width = $('input[name=dataWidth]').val().replace(/\./g, '');
    var height = $('input[name=dataHeight]').val().replace(/\./g, '');

    $('div[foritem=' + itemId + '] input[name=weight]').val(Math.ceil(length * width * height / 6000));
};
additemquick.drawImage = function (itemId, rs) {
    if (rs.data.images !== null && rs.data.images !== '' && rs.data.images.length > 0) {
        ajax({
            service: '/item/genimages.json',
            data: {ids: JSON.stringify(rs.data.images)},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    $("div[foritem=" + itemId + "] div[rel=img] div").remove();
                    $.each(rs.data.images, function () {
                        var imgId = this;
                        $.each(resp.data, function (key, value) {
                            if (imgId == key) {
                                $("div[foritem=" + itemId + "] div[rel=img]").append('<div for="img' + key + '" imgageId="' + key + '" class="submit-img-item">\
                                    <span class="glyphicon glyphicon-trash"></span>\
                                    <div class="img" style="width:55px;height:55px;overflow:hidden;"><img src="' + value + '" /></div>\
                                </div>');
                                $('div[for=img' + key + '] .glyphicon-trash').click(function () {
                                    additemquick.delImages(itemId, $('div[for=img' + key + ']').attr("imgageId"));
                                });
                                $('div[foritem=' + itemId + '] div[for=img' + key + '] .img').click(function () {
                                    additemquick.changeDefaultImage(key, itemId);
                                });
                            }
                        });
                    });

                    $("input[name=imageUrl]").val("");
                    $("input[name=image]").val("");
                    additemquick.getdefaultimage(itemId);

                }
            }
        });
    }
};
additemquick.getdefaultimage = function (itemId) {
    ajax({
        service: '/item/getdefaultimage.json',
        data: {id: itemId},
        loading: false,
        done: function (result) {
            if (result.success) {
                $('div[foritem=' + itemId + '] div[rel=img] .icon-img-default').remove();
                $.each($("div[foritem=" + itemId + "] div[rel=img] div[imgageid=" + result.data + "]"), function (i) {
                    if (i == 0) {
                        $(this).append('<div class="icon-img-default"></div>');
                    }
                });
            }
        }
    });
};

additemquick.submit = function () {
    var listItem = [];
    var error = false;
    $.each($('div[foritem]'), function () {
        var id = $(this).attr('foritem');
        $('div[foritem=' + id + '] .form-group').removeClass("has-error");
        if (typeof (CKEDITOR.instances['detail_' + id].getData()) == 'undefined' || CKEDITOR.instances['detail_' + id].getData().trim().length <= 0) {
            $('#detail_' + id).parents(".form-group").css("border", "1px solid #a94442");
            $('#detail_' + id).parents(".form-group").css("border-radius", "4px");
            error = true;
        } else {
            $('#detail_' + id).parents(".form-group").css("border", "none");
        }
        var name = $('div[foritem=' + id + '] input[name=name]').val();
        if (typeof name == 'undefined' || name.trim().length <= 0 || name.trim().length > 180) {
            $('div[foritem=' + id + '] input[name=name]').parent().addClass("has-error");
            error = true;
        }
        var condition = $('div[foritem=' + id + '] select[name=condition]').val();
        if (typeof condition == 'undefined' || (condition != "OLD" && condition != "NEW")) {
            $('div[foritem=' + id + '] select[name=condition]').parent().addClass("has-error");
            error = true;
        }
        var price = $('div[foritem=' + id + '] input[name=sellPrice]').val();
        if (typeof price == 'undefined' || price.trim().length <= 0 || price.replace(/\./g, '') <= 0) {
            $('div[foritem=' + id + '] input[name=sellPrice]').parent().addClass("has-error");
            error = true;
        }
        price = price.replace(/\./g, '');
        if (typeof ($('div[foritem=' + id + '] div[rel=img]')) == 'undefined' || $('div[foritem=' + id + '] div[rel=img]').html().trim() == "") {
            $('div[foritem=' + id + '] h6[rel="image-title"]').css("color", "#a94442");
            error = true;
        } else {
            $('div[foritem=' + id + '] h6[rel="image-title"]').css("color", "black");
        }

        var weight = $('div[foritem=' + id + '] input[name=weight]').val();
        if (weight.length > 0)
            weight = weight.replace(/\./g, '');
        weight = isNaN(weight) ? 0 : weight;

        var categoryId = $('input[name=categoryId]').val();
        var shopCategoryId = $('input[name=shopCategoryId]').val();
        var item = {
            id: id,
            name: name,
            condition: condition,
            sellPrice: price,
            weight: weight,
            categoryId: categoryId,
            shopCategoryId: shopCategoryId
        };
        listItem[listItem.length] = item;
    });

    if (error) {
        popup.msg("Vui lòng điền đầy đủ thông tin");
        return false;
    }
    if (listItem.length <= 0) {
        popup.msg("Bạn cần đăng bán ít nhất 1 sản phẩm");
    } else {
        ajax({
            service: '/itemquick/submit.json',
            data: listItem,
            loading: true,
            type: 'post',
            contentType: 'json',
            done: function (result) {
                if (result.success) {
                    if (result.message === 'POST_ITEM_FAIL') {
                        xengplus.plus(100);
                        popup.msg("Đăng bán thành công!", function () {
                            location.href = baseUrl + "/user/item.html";
                        });
                    } else {
                        popup.msg(result.message, function () {
                            location.href = baseUrl + "/user/item.html";
                        });
                    }
                } else {
                    popup.msg(result.message);
                    $('input, select[name], textarea').each(function () {
                        $(this).parents('.form-group').removeClass('has-error');
                        if ($(this).attr('name') && result.data[$(this).attr('name')]) {
                            $(this).parents('.form-group').addClass('has-error');
                        }
                    });
                }
            }
        });
    }
};

additemquick.addImageByLink = function (itemId) {
    var link = $("#img-add-form-bylink-" + itemId + " input[name=imageUrl]").val();
    if (typeof link !== 'undefined' && link.trim() !== '') {
        ajaxUpload({
            service: '/item/addimage.json',
            id: 'img-add-form-bylink-' + itemId,
            loading: false,
            done: function (resp) {
                additemquick.drawImage(itemId, resp);
                $("input[name=imageUrl]").val('');
            }
        });
    }
};

additemquick.facebook = function () {
    $.each($('div[foritem]'), function () {
        var itemId = $(this).attr('foritem');
        ajax({
            service: '/itemquick/removeproductquick.json',
            data: {id: itemId},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    $('div[foritem=' + itemId + ']').remove();
                }
            }
        });
    });
    facebookClient.getAlbum(function (resp) {

        if (typeof resp === 'undefined' || typeof resp.data === 'undefined' || resp.data.length === 0) {
            popup.msg("ChoDienTu.Vn không tìm thấy album nào trên tài khoản facebook của bạn!");
            return false;
        }
        popup.open("pop-facebook-album", "Danh sách album từ facebook của bạn", template("/user/tpl/facebook/album.tpl", resp), [
            {
                title: 'Chọn ảnh',
                className: 'button',
                fn: function () {
                    var al = [];
                    $.each($("input[data-rel=fb-photo]"), function () {
                        var img = $(this).val();
                        var name = $(this).attr("alt");
                        if (al.indexOf(img) === -1 && $(this).is(":checked")) {
                            var img = $(this).val();
                            al.push(img);
                            additemquick.createNewItem(name, img);
                        }
                    });
                    if (al.length === 0) {
                        popup.msg("Bạn chưa chọn ảnh cho sản phẩm");
                        return false;
                    }
                    popup.close("pop-facebook-album");

                }
            },
            {
                title: 'Đóng',
                fn: function () {
                    popup.close("pop-facebook-album");
                }
            }
        ], 'modal-lg');
    });
};

additemquick.getAlbum = function (obj) {
    $("div[data-rel=album-detail]").html("");
    var albumId = $(obj).val();
    if (albumId === '0')
        return false;
    facebookClient.getphoto(albumId, function (resp) {
        console.log(resp);
        if (typeof resp === 'undefined' || typeof resp.data === 'undefined' || resp.data.length === 0) {
            popup.msg("ChoDienTu.Vn không tìm thấy ảnh trên album bạn chọn");
            return false;
        }
        $.each(resp.data, function () {
            $("div[data-rel=album-detail]").append('<div class="col-sm-6 col-md-4">\
                                            <div class="thumbnail" style="min-height:300px;" >\
                                                <div style="overflow:hidden; height:200px; border-botton:1px solid #ddd" ><img src="' + this.images[0].source + '" alt="' + (typeof this.name !== 'undefined' ? this.name : 'Không có mô tả') + '"></div>\
                                                <div class="caption"><label>\
                                                      <input data-rel="fb-photo" type="checkbox" style="float:left" value="' + this.images[0].source + '" alt="' + (typeof this.name !== 'undefined' ? this.name : 'Không có mô tả') + '" >\
                                                    <p style="overflow:hidden; max-height:100px; display:block; margin-left:15px;" >' + (typeof this.name !== 'undefined' ? this.name : 'Không có mô tả') + '</p>\
                                                </div></label>\
                                            </div>\
                                        </div>');
        });
    });
};

