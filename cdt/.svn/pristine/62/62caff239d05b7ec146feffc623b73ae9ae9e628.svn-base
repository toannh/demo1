itemquick = {};
itemquick.userId = "";
itemquick.itemNoChangeWeight = [];
itemquick.init = function() {
    
    //vẽ breadcrumb
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị sản phẩm", "/cp/item.html"],
        ["Đăng nhanh"]
    ]);
    
    // build category
    $.each(cates, function() {
        $("select[for=cate].category_0").append("<option value='" + this.id + "' >" + this.name + "</option>");
    });
    $("select[for=cate].category_0").change(function() {
        itemquick.builtCategory(0, $(this).val());
    });
    $('input[name=sellerName]').on("change", function() {
        itemquick.checkSeller();
    });
    textUtils.inputNumberFormat('inputnumber');
    $('input[name=numberItem]').on("change", function() {
        itemquick.loadItem();
    });

};
itemquick.loadItem = function() {
    var numberItem = $('input[name=numberItem]').val();
    if (typeof numberItem == 'undefined' || numberItem.trim().length <= 0 || numberItem.replace(/\./g, '') <= 0) {
        popup.msg("Số lượng sản phẩm cần đăng phải lớn hơn 0");
        return false;
    }
    if (typeof numberItem !== 'undefined' && parseInt(numberItem.replace(/\./g, '')) > 20) {
        popup.msg("Đăng tối đa 20 sản phẩm trong 1 lần đăng");
        return false;
    }
    numberItem = numberItem.replace(/\./g, '');
    for (var i = 0; i < numberItem; i++) {
        itemquick.createNewItem();
    }
    $('input[name=numberItem]').parent().parent().remove();
};
itemquick.createNewItem = function() {
    ajax({
        service: '/cpservice/itemquick/updateproductquick.json',
        type: 'POST',
        done: function(resp) {
            if (resp.success) {
                $('div.listItem').append(template('/cp/tpl/quickitemtemp/addquick.tpl', resp));

                //Scroll bar hotseller
                $('.col-sm-3.reset-padding .submit-choose-img-item').slimScroll({
                    height: '120px',
                });
                setTimeout(function() {
                    textUtils.inputNumberFormat('inputnumber');
                }, 200);

                itemquick.initEditor('detail_' + resp.data.id);

                itemquick.itemNoChangeWeight.push(resp.data.id);
                $('div[foritem=' + resp.data.id + '] input[name=weight]').change(function() {
                    itemquick.itemNoChangeWeight.splice(itemquick.itemNoChangeWeight.indexOf(resp.data.id), 1);
                });
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

itemquick.removeItem = function(itemId) {
    popup.confirm("Bạn có chắc chắn muốn xóa?", function() {
        ajax({
            service: '/cpservice/itemquick/removeproductquick.json',
            data: {id: itemId},
            done: function(resp) {
                if (resp.success) {
                    $('div[foritem=' + itemId + ']').remove();
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
itemquick.builtCategory = function(level, pId) {
    var element = $('select[for="cate"].category_' + level);
    if (typeof pId != 'undefined' && pId != '' && pId !== 0) {
        ajax({
            service: '/cpservice/global/category/getchilds.json',
            data: {id: pId},
            loading: false,
            done: function(resp) {
                if (!resp.success)
                    popup.msg(resp.message);
                else {
                    $(element).parent().nextAll().remove();
                    if (resp.data.length > 0) {
                        var html = '<div class="form-group" style="margin-right:5px;"><select for="cate" class="form-control category_' + (level + 1) + '" level="' + (level + 1) + '" >';
                        html += '<option value="">-- Chọn danh mục --</option>';
                        $.each(resp.data, function() {
                            html += '<option value="' + this.id + '">' + this.name + '</option>';
                        });
                        html += '</select></div>';
                        $('select[for="cate"].category_' + level).parent().parent().append(html);

                        $('select[for="cate"].category_' + (level + 1)).change(function() {
                            itemquick.builtCategory(level + 1, $(this).val());
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

itemquick.builtShopCategory = function(level, pId) {
    var element = $('select[for="shopcate"].shopCate_' + level);
    if (typeof pId != 'undefined' && pId != '' && pId !== 0) {
        ajax({
            service: '/cpservice/global/shopcategory/getchilds.json',
            data: {id: pId},
            loading: false,
            done: function(resp) {
                if (!resp.success)
                    popup.msg(resp.message);
                else {
                    $(element).parent().nextAll().remove();
                    if (resp.data.length > 0) {
                        var html = '<div class="form-group" style="margin-right:5px;"><select for="shopcate" class="form-control shopCate_' + (level + 1) + '" level="' + (level + 1) + '" >';
                        html += '<option value="">-- Chọn danh mục --</option>';
                        $.each(resp.data, function() {
                            html += '<option value="' + this.id + '">' + this.name + '</option>';
                        });
                        html += '</select></div>';
                        $('select[for="shopcate"].shopCate_' + level).parent().parent().append(html);

                        $('select[for="shopcate"].shopCate_' + (level + 1)).change(function() {
                            itemquick.builtShopCategory(level + 1, $(this).val());
                        });
                    }
                }
                $('input[name=shopCategoryId]').val($('select[for="shopcate"].shopCate_' + level).val());
            }
        });

        if (itemquick.itemNoChangeWeight.length > 0) {
            ajax({
                service: '/cpservice/global/shopcategory/get.json',
                data: {id: pId},
                loading: false,
                done: function(resp) {
                    if (resp.success && resp.data != null && resp.data.weight > 0) {
                        $.each(itemquick.itemNoChangeWeight, function() {
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
itemquick.initEditor = function(id) {
    CKEDITOR.on('instanceReady', function(e) {
        if (e.editor.name == id) {
            e.editor.on('blur', function(ev) {
                itemquick.editorEdited = e.editor.checkDirty();
                if (itemquick.editorEdited) {
                    itemquick.downloadImageFromEditor(e.editor.name);
                    e.editor.resetDirty();
                }
            });
        }
    });
    CKEDITOR.basePath = staticUrl + '/lib/ckeditor/';

    var config = {extraPlugins: 'confighelper', autoGrow_maxHeight: 400};
    CKEDITOR.replace(id, config);
};

itemquick.downloadImageFromEditor = function(id) {
    var itemId = id.split("detail_")[1];
    var detail = CKEDITOR.instances[id].getData();
    var temp_container = $("<div></div>");
    var arrImg = new Array();
    temp_container.html(detail);
    temp_container.find("img").each(function(i, el) {
        arrImg.push($(el).attr('src'));
    });
    if (arrImg.length > 0) {
        ajax({
            service: '/cpservice/itemquick/downloadfromeditor.json',
            data: {imgs: JSON.stringify(arrImg), id: itemId},
            loading: true,
            done: function(resp) {
                if (resp.success) {
                    $.each(resp.data['imagesMapping'], function(keyword, value) {
                        temp_container.find("img").each(function(i, el) {
                            if ($(el).attr('src') == keyword) {
                                $(el).attr('src', value);
                            }
                        });
                    });
                    CKEDITOR.instances[id].setData(temp_container.html());

                    if (typeof resp.data["images"] !== 'undefined' && resp.data["images"] != null) {
                        $.each(resp.data["images"], function(key, value) {
                            $("div[foritem=" + itemId + "] div[rel=img]").append('<div for="img' + key + '" imgageId="' + key + '" class="submit-img-item">\
                                    <span class="glyphicon glyphicon-trash"></span>\
                                    <div class="img" style="width:55px;height:55px;overflow:hidden;"><img src="' + value + '" /></div>\
                                </div>');
                            $('div[foritem=' + itemId + '] div[for=img' + key + '] .glyphicon-trash').click(function() {
                                itemquick.delImages(itemId, $('div[for=img' + key + ']').attr("imgageId"));
                            });
                            $('div[foritem=' + itemId + '] div[for=img' + key + '] .img').click(function() {
                                itemquick.changeDefaultImage(key, itemId);
                            });
                        });
                        itemquick.getdefaultimage(itemId);
                    }
                }
            }
        });
    }
    if (detail.length > 0) {
        ajax({
            service: '/cpservice/itemquick/updatedetail.json',
            data: {detail: detail, itemId: itemId},
            loading: false,
            type: 'post',
            contentType: 'json',
            done: function(resp) {
            }
        });
    }
};

itemquick.changeDefaultImage = function(imageId, itemId) {
    ajax({
        service: '/cpservice/itemquick/setdefaultimage.json',
        data: {imageId: imageId, id: itemId},
        loading: true,
        done: function(resp) {
            if (resp.success) {
                $('div[foritem=' + itemId + '] div[rel=img] .icon-img-default').remove();
                $.each($("div[foritem=" + itemId + "] div[rel=img] div[imgageid=" + resp.data + "]"), function(i) {
                    if (i == 0) {
                        $(this).append('<div class="icon-img-default"></div>');
                    }
                });
            }
        }
    });
};

itemquick.getdefaultimage = function(itemId) {
    ajax({
        service: '/cpservice/itemquick/getdefaultimage.json',
        data: {id: itemId},
        loading: false,
        done: function(result) {
            if (result.success) {
                var ex = false;
                $('div[foritem=' + itemId + '] div[rel=img] .icon-img-default').remove();
                $.each($("div[foritem=" + itemId + "] div[imgageid]"), function() {
                    if (!ex && $(this).attr('imgageid') == result.data) {
                        $(this).append('<div class="icon-img-default"></div>');
                        ex = true;
                    }
                });
            }
        }
    });
};

itemquick.addImageByLocal = function(itemId) {
    ajaxUpload({
        service: '/cpservice/itemquick/addimage.json',
        id: 'img-add-form-' + itemId,
        loading: false,
        done: function(resp) {
            itemquick.drawImage(itemId, resp);
        }
    });
};

itemquick.delImages = function(id, imageName) {
    popup.confirm("Bạn có chắc chắn muốn xóa ảnh này?", function() {
        ajax({
            service: '/cpservice/itemquick/delimage.json',
            data: {id: id, name: imageName},
            loading: false,
            done: function(resp) {
                if (resp.success) {
                    $('div[foritem=' + id + '] div[rel=img] div[for=img' + imageName + ']').remove();
                    itemquick.getdefaultimage(id);
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};

itemquick.popupWeight = function(itemId) {
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
                    <div class="text-center mgt-15"><button class="btn btn-default btn-sm " onclick="itemquick.calcWeight(\'' + itemId + '\')" data-dismiss="modal">Tính năng quy đổi</button></div>\
                </div>';
    $('.modal-body').html(html);
    textUtils.inputNumberFormat('inputnumber');
};

itemquick.calcWeight = function(itemId) {
    var length = $('input[name=dataLength]').val().replace(/\./g, '');
    var width = $('input[name=dataWidth]').val().replace(/\./g, '');
    var height = $('input[name=dataHeight]').val().replace(/\./g, '');

    $('div[foritem=' + itemId + '] input[name=weight]').val(Math.ceil(length * width * height / 6000));
};
itemquick.drawImage = function(itemId, rs) {
    if (rs.data.images !== null && rs.data.images !== '' && rs.data.images.length > 0) {
        ajax({
            service: '/cpservice/itemquick/genimages.json',
            data: {ids: JSON.stringify(rs.data.images)},
            loading: false,
            done: function(resp) {
                if (resp.success) {
                    $("div[foritem=" + itemId + "] div[rel=img] div").remove();
                    $.each(rs.data.images, function() {
                        var imgId = this;
                        $.each(resp.data, function(key, value) {
                            if (imgId == key) {
                                $("div[foritem=" + itemId + "] div[rel=img]").append('<div for="img' + key + '" imgageId="' + key + '" class="submit-img-item">\
                                    <span class="glyphicon glyphicon-trash"></span>\
                                    <div class="img" style="width:55px;height:55px;overflow:hidden;"><img src="' + value + '" /></div>\
                                </div>');
                                $('div[for=img' + key + '] .glyphicon-trash').click(function() {
                                    itemquick.delImages(itemId, $('div[for=img' + key + ']').attr("imgageId"));
                                });
                                $('div[foritem=' + itemId + '] div[for=img' + key + '] .img').click(function() {
                                    itemquick.changeDefaultImage(key, itemId);
                                });
                            }
                        });
                    });

                    $("input[name=imageUrl]").val("");
                    $("input[name=image]").val("");
                    itemquick.getdefaultimage(itemId);

                }
            }
        });
    }
};
itemquick.getdefaultimage = function(itemId) {
    ajax({
        service: '/cpservice/itemquick/getdefaultimage.json',
        data: {id: itemId},
        loading: false,
        done: function(result) {
            if (result.success) {
                $('div[foritem=' + itemId + '] div[rel=img] .icon-img-default').remove();
                $.each($("div[foritem=" + itemId + "] div[rel=img] div[imgageid=" + result.data + "]"), function(i) {
                    if (i == 0) {
                        $(this).append('<div class="icon-img-default"></div>');
                    }
                });
            }
        }
    });
};

itemquick.submit = function() {
    var listItem = [];
    var error = false;
    var sellerName = $('input[name=sellerName]').val();
    if (typeof sellerName !== 'undefined') {
        sellerName = sellerName.trim();
    }
    $.each($('div[foritem]'), function() {
        var id = $(this).attr('foritem');
        $('div[foritem=' + id + '] .form-group').removeClass("has-error");
        if (typeof (CKEDITOR.instances['detail_' + id]) == 'undefined' || typeof (CKEDITOR.instances['detail_' + id].getData()) == 'undefined' || CKEDITOR.instances['detail_' + id].getData().trim().length <= 0) {
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
        var startPrice = $('div[foritem=' + id + '] input[name=startPrice]').val();
        if (typeof startPrice !== 'undefined') {
            startPrice = startPrice.replace(/\./g, '');
        } else {
            startPrice = 0;
        }

        var price = $('div[foritem=' + id + '] input[name=sellPrice]').val();
        if (typeof price == 'undefined' || price.trim().length <= 0 || parseInt(price.replace(/\./g, '')) <= 0) {
            $('div[foritem=' + id + '] input[name=sellPrice]').parent().addClass("has-error");
            error = true;
        }
        if (typeof price !== 'undefined')
            price = price.replace(/\./g, '');

        if (typeof ($('div[foritem=' + id + '] div[rel=img]')) == 'undefined' || typeof ($('div[foritem=' + id + '] div[rel=img]').html()) == 'undefined' || $('div[foritem=' + id + '] div[rel=img]').html().trim() == "") {
            $('div[foritem=' + id + '] h6[rel="image-title"]').css("color", "#a94442");
            error = true;
        } else {
            $('div[foritem=' + id + '] h6[rel="image-title"]').css("color", "black");
        }

        var weight = $('div[foritem=' + id + '] input[name=weight]').val();
        if (typeof weight !== 'undefined' && weight.length > 0)
            weight = weight.replace(/\./g, '');
        else {
            weight = 0;
        }
        weight = isNaN(weight) ? 0 : weight;

        var categoryId = $('input[name=categoryId]').val();
        var shopCategoryId = $('input[name=shopCategoryId]').val();
        var item = {
            id: id,
            name: name,
            condition: condition,
            sellerName: sellerName,
            startPrice: startPrice,
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
            service: '/cpservice/itemquick/submit.json',
            data: listItem,
            loading: true,
            type: 'post',
            contentType: 'json',
            done: function(result) {
                if (result.success) {
                    popup.open('popup-confirm', 'Xác nhận', '<div class="container" style="min-width: 300px">' + result.message + '. Bạn có muốn tiếp tục đăng bán?</div>', [{
                            title: "Đồng ý",
                            style: "btn-primary",
                            fn: function() {
                                $('div.listItem').html('<div style="margin: 15px 0; background: #fff8c9;border-radius: 5px; padding: 10px; font-weight: bold;" class="row">\
                                            <div class="col-sm-4">Tiêu đề</div>\
                                            <div class="col-sm-2">Tình trạng sản phẩm</div>\
                                            <div class="col-sm-2">Giá gốc(VNĐ)</div>\
                                            <div class="col-sm-2">Giá bán(VNĐ)</div>\
                                            <div class="col-sm-2">Cân nặng(Gram)</div>\
                                        </div>');
                                $('div[for=quick]').html('<div class="form-inline" style="margin-top:15px;">\
                                            <div class="form-group">\
                                                <label class="control-label">Số lượng sản phẩm cần đăng bán</label>\
                                                <input type="text" class="form-control inputnumber" maxlength="2" name="numberItem" value="0" placeholder="Số lượng sản phẩm" />\
                                            </div>\
                                        </div>');
                                textUtils.inputNumberFormat('inputnumber');
                                $('input[name=numberItem]').on("change", function() {
                                    itemquick.loadItem();
                                });
                                popup.close('popup-confirm');
                            }
                        }, {
                            title: 'Từ chối',
                            fn: function() {
                                location.href = baseUrl + "/cp/item.html";
                            }
                        }]);
                } else {
                    if (typeof result.data == 'undefined' || result.data == null) {
                        popup.msg(result.message);
                    } else {
                        var error = "<strong>" + result.message + ":</strong> <br/><ul style='color:red;margin-left:15px;'>";
                        $.each(result.data, function(key, value) {
                            error += "<li> - " + value + " </li>";
                        });
                        popup.msg(error + "</ul>");
                    }
                    $('input, select[name], textarea').each(function() {
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
itemquick.checkSeller = function() {
    var sellerName = $('input[name=sellerName]').val();
    ajax({
        service: '/cpservice/user/checkseller.json?username=' + sellerName,
        loading: true,
        contentType: 'json',
        done: function(result) {
            $('input[name=sellerName]').nextAll().remove();
            $('.shopCates').html('');
            if (result.success) {
                if (result.data.user.active && result.data.shop != null) {
                    if (result.data.user.phone == null || result.data.user.phone == "") {
                        $('.seller').append('<button class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Shop chưa có số điện thoại</button>');
                    } else if (result.data.shop.cityId == null || result.data.shop.cityId == "" || result.data.shop.districtId == null || result.data.shop.districtId == "") {
                        $('.seller').append('<button class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> Shop chưa có địa chỉ</button>');
                    } else if (result.data.seller.nlIntegrated && result.data.seller.scIntegrated) {
                        $('.seller').append('<button class="btn btn-info"><span class="glyphicon glyphicon-ok"></span> User tồn tại</button>');
                        $('.seller').append('<span> Link shop: <a href="' + baseUrl + '/' + result.data.shop.alias + '/">' + baseUrl + '/' + result.data.shop.alias + '/</a></span>');
                        // build shopcategory
                        if (typeof result.data.scates != 'undefined' && result.data.scates.length > 0) {
                            $('.shopCates').html('<div class="form-inline" style="margin-top:15px;">\
                                                <div class="form-group">\
                                                    <label class="control-label"><strong>Chọn danh mục shop cần đăng: <span style="color: red;">*</span></strong> </label>\
                                                </div>\
                                                <div class="form-group">\
                                                    <input type="hidden" name="shopCategoryId" value="" />\
                                                    <select class="form-control shopCate_0" for="shopcate" level="0" >\
                                                        <option value="" >Chọn danh mục shop</option>\
                                                    </select>\
                                                </div>\
                                            </div>');
                            $.each(result.data.scates, function() {
                                $("select[for=shopcate].shopCate_0").append("<option value='" + this.id + "' >" + this.name + "</option>");
                            });
                            $("select[for=shopcate].shopCate_0").change(function() {
                                itemquick.builtShopCategory(0, $(this).val());
                            });
                        }
                    } else {
                        $('.seller').append('<button class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> User chưa tích hợp NL & SC</button>');
                    }
                }
                if (!result.data.user.active) {
                    $('.seller').append('<button class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> User đang bị khóa</button>');
                } else if (result.data.shop == null) {
                    $('.seller').append('<button class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> User chưa có shop</button>');
                }
            } else {
                $('.seller').append('<button class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> User không tồn tại</button>');
            }
        }
    });
};
itemquick.addImageByLink = function(itemId) {
    var link = $("#img-add-form-bylink-" + itemId + " input[name=imageUrl]").val();
    if (typeof link !== 'undefined' && link.trim() !== '') {
        ajaxUpload({
            service: '/cpservice/itemquick/addimage.json',
            id: 'img-add-form-bylink-' + itemId,
            loading: false,
            done: function(resp) {
                itemquick.drawImage(itemId, resp);
                $("input[name=imageUrl]").val('');
            }
        });
    }
};