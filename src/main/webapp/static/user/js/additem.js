additem = {};
additem.item = {};
additem.shopCates = {};
additem.done = false;
additem.changeWeight = false;
additem.init = function () {
    $('input[name=weight]').change(function () {
        additem.changeWeight = true;
    });
    //upload click
    $("a.img-upload-item").click(function () {
        $("#image").click();
    });
    $(".date-picker").datetimepicker({
        dateFormat: 'dd/mm/yy'
    });
    additem.initEditor('detail', 0);
    additem.done = false;
    var out = null;
    $("select, input").change(function () {
        if (out !== null)
            clearTimeout(out);

        out = setTimeout(function () {
            additem.saveItem();
        }, 300);
    });

    $("div[rel=category]").append('<div style="display:none" class="col-lg-4 col-md-4 col-sm-4 col-xs-6 box-list-category" rel="category0" >\
                                        <div class="box-oveflow"><ul class="list-category-suggestions"></ul></div></div>');
    $.each(cates, function () {
        var idCate = this.id;
        $("div[rel=category0] ul").append('<li for="' + this.id + '" ><a href="javascript:;">' + this.name + '</a></li>');
        $("div[rel=category0] ul").attr("level", this.level);
        $("div[rel=category0] ul").attr("for", "0");
        $('li[for=' + idCate + '] a').click(function () {
            additem.drawChildByCateId('0', idCate);
        });
    });

    var cateBoxWidth = 255;
    if ($('body').width() >= 992 && $('body').width() <= 1199) {
        cateBoxWidth = 248;
    }
    if ($('body').width() <= 991 && $('body').width() >= 768) {
        cateBoxWidth = 190;
    }

    if ($('body').width() <= 767) {
        cateBoxWidth = 170;
    }
    if ($('body').width() <= 345) {
        cateBoxWidth = 130;
    }
    $('div.box-list-category').width(cateBoxWidth);
    additem.builtShopCategory(null);


    $("div[rel=category0]").css({"display": "block"});
    $('input[name=category]').change(function () {
        additem.loadMamufacturer();
        additem.loadCategory($('input[name=category]').val());
    });
    $('input[name=modelId]').change(function () {
        additem.drawModel($('input[name=modelId]').val());
    });
    $("select[name=model]").change(function () {
        if ($(this).val() !== '' && $(this).val() !== '0') {
            $("input[name=modelId]").val($(this).val()).change();
        }
    });
    textUtils.inputNumberFormat('inputnumber');

    //draw item
    if (itemId !== '') {
        additem.drawItem();
        additem.changeWeight = true;
    } else {
        if (seller.shipmentType === 'FIXED') {
            if (seller.shipmentPrice > 0) {
                $("input[name=shipmentType]").filter('[value=2]').prop('checked', true);
            } else {
                $("input[name=shipmentType]").filter('[value=4]').prop('checked', true);
            }
            $("input[name=shipmentTypePrice]").val(parseFloat(seller.shipmentPrice).toMoney(0, ',', '.'));
        } else if (seller.shipmentType === 'BYWEIGHT') {
            $("input[name=shipmentType]").filter('[value=3]').prop('checked', true);
        } else {
            $("input[name=shipmentType]").filter('[value=1]').prop('checked', true);
        }
        additem.done = true;
    }

};


additem.builtCategory = function (cateId) {
    ajax({
        service: '/category/getancestors.json',
        data: {id: cateId},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                var params = resp.data;
                $("div[rel=category]").html('');
                if (params === null || params.cate === 'undefined' || params.cate === null || params.cate === '') {
                    $("div[rel=category]").append('<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6 box-list-category" rel="category0" >\
                                    <div class="box-oveflow"><ul for="0" level="1" class="list-category-suggestions"></ul></div></div>');
                    $.each(cates, function () {
                        $("div[rel=category0] ul").append('<li for="' + this.id + '" ><a href="javascript:;">' + this.name + '</a></li>');
                    });
                } else {
                    $.each(params.cats, function (i) {
                        $.each(this, function (j) {
                            var parentId = this.parentId === null ? '0' : this.parentId;
                            if (j === 0) {
                                $("div[rel=category]").append('<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6 box-list-category" rel="category' + parentId + '"  >\
                                    <div class="box-oveflow"><ul for="' + parentId + '" level="' + this.level + '" class="list-category-suggestions"></ul></div></div>');
                            }
                            $("div[rel=category" + parentId + "] ul").append('<li for="' + this.id + '" ><a href="javascript:;">' + this.name + '</a></li>');
                            var id = this.id;
                            $('li[for=' + id + '] a').click(function () {
                                $('div[rel=categoris] input[type=radio]:checked').prop("checked", false);
                                $("div[rel=models] div[for]").removeClass("active");
                                additem.drawChildByCateId(parentId, id);
                            });
                        });
                    });
                }
                for (var i in params.ancestors) {
                    var parentId = params.ancestors[i].parentId === null ? '0' : params.ancestors[i].parentId;
                    $('div[rel=category] ul li[for=' + params.ancestors[i].id + ']').addClass('active');
                    $("div[rel=category" + parentId + "] ul li[for=" + params.ancestors[i].id + "]").prependTo("div[rel=category" + parentId + "] ul");

                    var cateBoxWidth = 255;
                    var x = 3;
                    if ($('body').width() >= 992 && $('body').width() <= 1199) {
                        cateBoxWidth = 248;
                    }
                    if ($('body').width() <= 991 && $('body').width() >= 768) {
                        cateBoxWidth = 190;
                    }

                    if ($('body').width() <= 767) {
                        cateBoxWidth = 170;
                        x = 2;
                    }
                    if ($('body').width() <= 345) {
                        cateBoxWidth = 130;
                        x = 1;
                    }
                    $('div.box-list-category').width(cateBoxWidth);
                    var level = eval($('div[rel="category' + parentId + '"] ul').attr("level")) - 1;
                    var skipwidth = $("div[rel=category]").width();
                    if (level % x === 0) {
                        $("div[rel=category]").width(skipwidth + cateBoxWidth * x);
                        $("div.suggestions-list-category").scrollLeft($("div[rel=category]").parent().width());
                    } else {
                        if (skipwidth > cateBoxWidth * (level + 1)) {
                            $("div[rel=category]").width((cateBoxWidth * (level + x - 1)) + (45 * (level + 1) + 5 * level));
                        }
                    }
                    $('li[for=' + params.ancestors[i].id + '] a').click(function () {
                        $('div[rel=categoris] input[type=radio]:checked').prop("checked", false);
                        $("div[rel=models] div[for]").removeClass("active");
                        additem.drawChildByCateId(parentId, params.ancestors[i].id);
                    });
                }
            }
        }
    });
};
additem.builtShopCategory = function (item) {
    ajax({
        service: '/shopcategory/getbyshop.json',
        loading: false,
        done: function (resp) {
            if (resp.success && resp.data !== null && resp.data.length > 0) {
                var shopCates = resp.data;
                additem.shopCates = shopCates;
                var html = '<div class="form-group" ><label class="control-label">Danh mục sản phẩm Shop <span class="clr-red">*</span></label></div>';
                if (typeof item !== 'undefined' && item !== null && typeof item.shopCategoryId !== 'undefined' && item.shopCategoryId !== null && item.shopCategoryId != '') {
                    $.each(item.shopCategoryPath, function (i) {
                        var shopCate = this;
                        html += '<div class="form-group">';
                        html += '<select class="form-control shopCate_' + (i + 1) + '" chonsen = "' + (i + 1) + '">';
                        html += '<option value="0">--Chọn danh mục shop--</option>';
                        $.each(shopCates, function () {
                            if (i == 0 && this.level == 1 && this.active) {
                                var selected = (this.id == shopCate ? 'selected="true"' : '');
                                html += '<option value="' + this.id + '" ' + selected + '>' + this.name + '</option>';
                            }
                            if (i > 0 && this.parentId == item.shopCategoryPath[i - 1] && this.active) {
                                var selected = (this.id == shopCate ? 'selected="true"' : '');
                                html += '<option value="' + this.id + '" ' + selected + '>' + this.name + '</option>';
                            }
                        });
                        html += '</select>';
                        if (i == item.shopCategoryPath.length - 1) {
                            html += '<input type="hidden" value="' + item.shopCategoryId + '" name="shopCategoryId" />';
                        }
                        html += '</div>';
                    });
                } else {
                    html += '<div class="form-group checkerror">';
                    html += '<select class="form-control shopCate_0" chonsen = "0">';
                    html += '<option value="0">--Chọn danh mục shop--</option>';
                    $.each(shopCates, function () {
                        if (this.level == 1 && this.active) {
                            html += '<option value="' + this.id + '">' + this.name + '</option>';
                        }
                    });
                    html += '</select>';
                    html += '<input type="hidden" value="0" name="shopCategoryId" />';
                    html += '</div>';
                }
                $('div[rel=shopCategories]').html(html);
                $('div[rel=shopCategories] select').each(function () {
                    $(this).on('change', function () {
                        additem.changeShopCate(parseInt($(this).attr('chonsen')), $(this).val());
                    });
                });
            }
        }
    });
};
additem.changeShopCate = function (i, id) {
    var element = $('div[rel=shopCategories] .shopCate_' + i);
    if (id !== "") {
        $(element).parent().nextAll().remove();
        if (additem.shopCates.length > 0) {
            var hasLeaf = true;
            var html = '<div class="form-group">\
                                <select class="form-control shopCate_' + (i + 1) + '" chonsen = "' + (i + 1) + '">';
            html += '<option value="">-- Chọn danh mục shop --</option>';
            $.each(additem.shopCates, function () {
                if (this.parentId == id && this.active) {
                    html += '<option value="' + this.id + '">' + this.name + '</option>';
                    hasLeaf = false;
                }
            });
            html += '</select></div>';
            if (!hasLeaf) {
                $('div[rel=shopCategories]').append(html);
            }
            $('div[rel=shopCategories] select').each(function () {
                $(this).on('change', function () {
                    additem.changeShopCate(parseInt($(this).attr('chonsen')), $(this).val());
                });
            });
            var weight = $('input[name=weight]').val();
            if (typeof (weight) === 'undefined' || weight == null || weight.trim().length <= 0 || weight == "0" || !additem.changeWeight) {
                setTimeout(function () {
                    ajax({
                        service: '/shopcategory/get.json',
                        data: {id: id},
                        loading: false,
                        done: function (resp) {
                            if (resp.success && resp.data != null && resp.data.weight > 0) {
                                $('input[name=weight]').val(parseFloat(resp.data.weight).toMoney(0, ',', '.'));
                                additem.changeWeight = false;
                            }
                        }
                    });
                }, 200);
            }
        }
    }
    if (parseInt(id) > 0) {
        $('input[name=shopCategoryId]').val(id).change();
    } else {
        $('input[name=shopCategoryId]').val($('.shopCate_' + (i - 1)).val()).change();
    }
};
additem.drawItem = function () {
    if (itemId !== '') {
        ajax({
            service: '/item/get.json',
            data: {id: itemId},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    var item = resp.data.item;
                    additem.item = resp.data.item;
                    var itemDetail = resp.data.itemDetail;
                    var itemProperties = resp.data.properties;
                    var manufacturer = resp.data.manufacturer;
                    additem.itemId = item.id;
                    //additem.itemId = item.id;
                    $("input[name=name]").val(item.name);
                    additem.countCharacters();
                    $("input[name=modelId]").val(item.modelId);
                    if (manufacturer !== null) {
                        $("input[name=mf]").val(manufacturer.id);
                    }
                    $("input[name=shopCategoryId]").val(item.shopCategoryId);

                    additem.builtCategory(item.categoryId);
                    $('#suggestion-list').addClass("active");
                    $('li a[rel=tabcate]').parent().addClass("active");
                    $('#suggestion-cat').removeClass("active");
                    $('li a[rel=tabgen]').parent().removeClass("active");
                    $("input[name=category]").val(item.categoryId).change();
                    if (itemDetail.detail !== null && itemDetail.detail !== "")
                        for (var obj in CKEDITOR.instances) {
                            CKEDITOR.instances[obj].setData(itemDetail.detail);
                        }
                    if (item.condition === 'NEW')
                        $("input[name=condition]").filter('[value=1]').prop('checked', true);
                    else
                        $("input[name=condition]").filter('[value=2]').prop('checked', true);
                    if (item.listingType === 'AUCTION') {
                        $("input[name=bidStartPrice]").val(parseFloat(item.startPrice).toMoney(0, ',', '.'));
                        $("input[name=bidSellPrice]").val(parseFloat(item.sellPrice).toMoney(0, ',', '.'));
                        $("input[name=bidStep]").val(parseFloat(item.bidStep).toMoney(0, ',', '.'));
                        $('input[name=bidStartTime]').val(textUtils.formatTime(item.startTime, 'time'));
                        $('input[name=bidEndTime]').val(textUtils.formatTime(item.endTime, 'time'));
                        $("ul.nav-tabs li[rel=auction]").addClass("active");
                        $("ul.nav-tabs li[rel=bynow]").html('');
                        $("div#muangay").remove();
                        $("div#auction").addClass("active");
                    } else {
                        if (eval(item.startPrice) > eval(item.sellerPrice)) {
                            var percent = eval((item.startPrice - item.sellerPrice) / item.startPrice) * 100;
                            $("input[name=discountPercent]").val(Math.ceil(percent));
                        }
                        $("input[name=startPrice]").val(parseFloat(item.startPrice).toMoney(0, ',', '.'));
                        $("input[name=sellPrice]").val(parseFloat(item.sellPrice).toMoney(0, ',', '.'));

                        $('input[name=startTime]').val(textUtils.formatTime(item.startTime, 'time'));
                        var diffDay = Math.ceil((item.endTime - item.startTime) / (24 * 60 * 60 * 1000));
                        $('select[name=endDay] option[value=' + diffDay + ']').prop("selected", true);
                        $("input[name=quantity]").val(item.quantity);
                        $("ul.nav-tabs li[rel=bynow]").addClass("active");
                        $("div#muangay").addClass("active");
                    }
                    if (item.shipmentType === 'FIXED') {
                        if (item.shipmentPrice > 0) {
                            $("input[name=shipmentType]").filter('[value=2]').prop('checked', true);
                        } else {
                            $("input[name=shipmentType]").filter('[value=4]').prop('checked', true);
                        }
                        $("input[name=shipmentTypePrice]").val(parseFloat(item.shipmentPrice).toMoney(0, ',', '.'));
                    } else if (item.shipmentType === 'BYWEIGHT') {
                        $("input[name=shipmentType]").filter('[value=3]').prop('checked', true);
                        $("input[name=weight]").val(item.weight);
                    } else {
                        $("input[name=shipmentType]").filter('[value=1]').prop('checked', true);
                    }

                    setTimeout(function () {
                        additem.builtShopCategory(item);
                        //vẽ thuộc tính sản phẩm
                        $.each(itemProperties, function () {
                            if ($("div[for=cp" + this.categoryPropertyId + "]").find("input[type=radio]").length > 0) {
                                if ($("div[for=cp" + this.categoryPropertyId + "]").find("input[type=text]").length > 0 && this.inputValue !== null && this.inputValue !== '') {
                                    $("div[for=cp" + this.categoryPropertyId + "] input[name=inputValue]").val(this.inputValue);
                                } else if (this.categoryPropertyValueIds.length > 0) {
                                    $("div[for=cp" + this.categoryPropertyId + "] input[value=" + this.categoryPropertyValueIds[0] + "]").attr("checked", true);
                                }
                            } else if ($("div[for=cp" + this.categoryPropertyId + "]").find("input[type=text]").length > 0) {
                                $("div[for=cp" + this.categoryPropertyId + "] input[name=inputValue]").val(this.inputValue);
                            } else if ($("div[for=cp" + this.categoryPropertyId + "]").find("input[type=checkbox]").length > 0 && this.categoryPropertyValueIds.length > 0) {
                                var categoryPropertyId = this.categoryPropertyId;
                                $.each(this.categoryPropertyValueIds, function () {
                                    $("div[for=cp" + categoryPropertyId + "] input[value=" + this + "]").attr("checked", true);
                                });
                            }
                        });
                    }, 2000);
                    //Vẽ ảnh sản phẩm
                    var resp = new Object();
                    resp.data = item;
                    if (item.images !== null && item.images !== '')
                        additem.drawImage(resp);
                } else {
                    popup.msg(resp.message);
                }
                additem.done = true;
            }
        });
    } else {
        additem.done = true;
    }
};
additem.loadCategory = function (id) {
    ajax({
        service: '/category/getproperties.json',
        data: {id: id},
        loading: false,
        done: function (resp) {
            $("div[rel=properties] div").remove();
            $("div[data-rel=box-color-postItem]").html("");
            $("div[data-rel=box-size-postItem]").html("");
            if (resp.success) {
                if (resp.data.properties.length > 0) {
                    $.each(resp.data.properties, function () {
                        var property = this;
                        $("div[rel=properties]").append('<div class="param" for="cp' + property.id + '" value="' + property.id + '" >\
                                <label class="param-label"><span class="fa fa-plus-square"></span><span class="pram-line"></span>' + property.name + '</label>\
                                <div class="param-popup">\
                                    <div class="box-check"></div>\
                                    <div class="param-button">\
                                        <a class="btn btn-default btn-sm button-gray" for="remove">Xong</a>\
                                    </div>\
                                </div>\
                            </div>');
                        var flag = true;
                        if (property.type === 'MULTIPLE') {
                            $.each(resp.data.propertyValues, function () {
                                if (this.categoryPropertyId === property.id) {
                                    flag = false;
                                    if (textUtils.createAlias(property.name) == 'mau-sac') {
                                        additem.loadPropertyColor(property, resp.data.propertyValues);
                                        $("div[for=cp" + property.id + "] .box-check").append('<div class="grid-form"><label class="checkbox-inline"><input onchange="additem.setColor();" rel="text_color" rel-data="' + textUtils.createAlias(this.name) + '" name="cpv_' + property.id + '" type="checkbox" value="' + this.id + '"/>' + this.name + '</label></div>');
                                    } else if (textUtils.createAlias(property.name) == 'kich-cox') {
                                        additem.loadPropertySize(property, resp.data.propertyValues);
                                        $("div[for=cp" + property.id + "] .box-check").append('<div class="grid-form"><label class="checkbox-inline"><input onchange="additem.setSize();" rel-data="' + textUtils.createAlias(this.name) + '" rel="text_size" name="cpv_' + property.id + '" type="checkbox" value="' + this.id + '"/>' + this.name + '</label></div>');
                                    } else {
                                        $("div[for=cp" + property.id + "] .box-check").append('<div class="grid-form"><label class="checkbox-inline"><input name="cpv_' + property.id + '" type="checkbox" value="' + this.id + '"/>' + this.name + '</label></div>');
                                    }
                                }
                            });
                            $("div[for=cp" + property.id + "] a[for=remove]").click(function () {
                                // $("div[for=cp" + property.id + "] input[type=checkbox]").attr("checked", false);
                            });
                        } else if (property.type === 'SINGLE') {
                            $("div[for=cp" + property.id + "] .box-check").addClass("form-inline");
                            var html = '';
                            $.each(resp.data.propertyValues, function () {
                                if (this.categoryPropertyId === property.id) {
                                    flag = false;
                                    html += '<div class="grid-form"><label class="checkbox-inline"><input name="cpv_' + property.id + '" type="radio" value="' + this.id + '">' + this.name + '</label></div>';
                                }
                            });
                            $("div[for=cp" + property.id + "] .box-check").append(html);
                        } else if (property.type === 'SINGLE_OR_INPUT') {
                            flag = false;
                            $("div[for=cp" + property.id + "] .box-check").addClass("form-inline");
                            var html = '';
                            $.each(resp.data.propertyValues, function () {
                                if (this.categoryPropertyId === property.id) {
                                    html += '<div class="grid-form"><label class="checkbox-inline"><input name="cpv_' + property.id + '" type="radio" value="' + this.id + '">' + this.name + '</label></div>';
                                }
                            });
                            $("div[for=cp" + property.id + "] .box-check").append(html);

                            html += '<p><span class="checkbox-inline"><input name="inputValue" type="text" class="form-control" placeholder="Nhập thông số" /></span></p>';
                            $("div[for=cp" + property.id + "] .box-check").append(html);
                        } else if (property.type === 'INPUT') {
                            flag = false;
                            $("div[for=cp" + property.id + "] .box-check").append('<div class="grid"><span class="checkbox-inline"><input name="inputValue" type="text" class="form-control" placeholder="Nhập thông số" /></span></div>');
                        }

                        if (flag && $("input[name=modelId]").val() === '0') {
                            $("div[for=cp" + property.id + "] .param-popup").remove();
                            $("input[name=mfText]").val("");
                            $("input[name=mf]").val("0");
                        }
                    });
                }
                $('.param-label', $('.submit-param')).click(function () {
                    $('.param', $('.submit-param')).removeClass("active");
                    var parent = $(this).parent();
                    $(parent).addClass("active");
                    $('.param-popup', $('.submit-param')).hide();
                    $('.param-popup', $(parent)).show();
                });
                $('.button-gray', $('.submit-param')).click(function () {
                    var parent = $(this).parent().parent();
                    var grand = $(this).parent().parent().parent();
                    $(parent).hide();
                    $(grand).removeClass("active");
                });
            } else {
                $("div[rel=properties] div").remove();
                //xóa color,size
                $("div[data-rel=box-color-postItem]").html("");
                $("div[data-rel=box-size-postItem]").html("");
            }
        }
    });
};
additem.loadModel = function () {
    var cateId = $("input[name=category]").val();
    var mfId = $("input[name=mf]").val();
    var modelId = $("input[name=modelId]").val();
    if (cateId !== '0' && mfId !== '0') {
        ajax({
            service: '/model/findbycatmf.json',
            data: {categoryId: cateId, mfId: mfId},
            loading: false,
            done: function (resp) {
                if (resp.success && resp.data.length > 0) {
                    $("select[name=model]").html('<option value="0" >--Chọn model--</option>');
                    $.each(resp.data, function () {
                        $("select[name=model]").append('<option ' + (this.id === modelId ? 'selected' : '') + ' value="' + this.id + '" >' + this.name + '</option>');

                    });
                } else {
                    $("select[name=model]").html('<option value="0" >--không có model--</option>');
                    $("input[name=modelId]").val('0');
                }
            }
        });
    } else {
        $("select[name=model]").html('<option value="0" >--không có model--</option>');
        $("input[name=modelId]").val('0');
    }
};
additem.loadMamufacturer = function () {
    var cateId = $("input[name=category]").val();
    var mfId = $("input[name=mf]").val();

    if (cateId !== '0') {
        ajax({
            service: '/manufacturer/searchbyname.json',
            data: {categoryId: cateId},
            loading: false,
            done: function (resp) {
                if (resp.success && resp.data.data.length > 0) {
                    $("select[name=manufacturer]").html('<option value="0" >-- Chọn thương hiệu --</option>');
                    $.each(resp.data.data, function () {
                        $("select[name=manufacturer]").append('<option ' + (this.id === mfId ? 'selected' : '') + ' value="' + this.id + '" >' + this.name + '</option>');
                    });
                    $('select[name=manufacturer]').change(function () {
                        $("input[name=mf]").val($(this).val());
                        additem.loadModel();
                    });
                } else {
                    $("select[name=manufacturer]").html('<option value="0" >--Không có thương hiệu--</option>');
                    $("input[name=mf]").val('0').change();
                }

                additem.loadModel();
            }
        });
    }
};
additem.drawChildByCateId = function (par, id) {
    $("div[rel=category" + par + "] ul li").removeClass("active");
    $("div[rel=category" + par + "] ul li[for=" + id + "]").addClass("active");
    $("div[rel=category" + par + "]").nextAll().remove();
    ajax({
        service: '/category/getchilds.json',
        data: {id: id},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                if (resp.data.length > 0) {
                    $("div[rel=category" + par + "]").parent().append('<div class="col-lg-4 col-md-4 col-sm-4 col-xs-6 box-list-category" rel="category' + id + '" >\
                                                                    <div class="box-oveflow"><ul class="list-category-suggestions"></ul></div></div>');
                    $.each(resp.data, function () {
                        var idCate = this.id;
                        var leaf = this.leaf;
                        $("div[rel=category" + id + "] ul").append('<li for="' + this.id + '" ><a href="javascript:;">' + this.name + '</a></li>');
                        $("div[rel=category" + id + "] ul").attr("level", this.level);
                        $("div[rel=category" + id + "] ul").attr("for", this.parentId === null ? 0 : this.parentId);

                        $('li[for=' + idCate + '] a').click(function () {
                            additem.drawChildByCateId(id, idCate);
                            additem.loadPropertyColor(idCate);
                        });

                    });
                    var cateBoxWidth = 255;
                    var x = 3;
                    if ($('body').width() >= 992 && $('body').width() <= 1199) {
                        cateBoxWidth = 248;
                    }
                    if ($('body').width() <= 991 && $('body').width() >= 768) {
                        cateBoxWidth = 190;
                    }

                    if ($('body').width() <= 767) {
                        cateBoxWidth = 170;
                        x = 2;
                    }
                    if ($('body').width() <= 345) {
                        cateBoxWidth = 130;
                        x = 1;
                    }
                    $('div.box-list-category').width(cateBoxWidth);
                    var level = eval($('div[rel="category' + id + '"] ul').attr("level")) - 1;
                    var skipwidth = $("div[rel=category]").width();
                    if (level % x === 0) {
                        $("div[rel=category]").width(skipwidth + cateBoxWidth * x);
                        $("div.suggestions-list-category").scrollLeft($("div[rel=category]").parent().width());
                    } else {
                        if (skipwidth > cateBoxWidth * (level + 1)) {
                            $("div[rel=category]").width((cateBoxWidth * (level + x - 1)) + (45 * (level + 1) + 5 * level));
                        }
                    }
                }
                $("input[name=category]").val(id).change();
            } else {
                $("input[name=category]").val(par).change();
            }
        }
    });
};
additem.searchManufTimeout = null;
additem.timeout = null;
additem.drawDataByKeyword = function (obj) {
    if (additem.timeout !== null) {
        clearTimeout(additem.timeout);
    }
    additem.timeout = setTimeout(function () {
        var keyword = $(obj).val();
        ajax({
            service: '/item/getbykeyword.json',
            data: {keyword: keyword},
            loading: false,
            type: 'post',
            contentType: 'json',
            done: function (resp) {
                if (resp.success) {
                    $('div#suggestion-cat').html('');
                    if (resp.data.models.length > 0 || resp.data.categories.length > 0) {
                        $("ul.nav-tabs a[rel=tabgen]").parent().addClass("active");
                        $("ul.nav-tabs a[rel=tabcate]").parent().removeClass("active");
                        $("div#suggestion-list").removeClass('active');
                        $("div#suggestion-cat").addClass('active');
                    }
                    $('div#suggestion-cat').append('<div class="panel-config-shop" id="suggestion-cat-child"></div>');

                    $('div#suggestion-cat-child').append('<div class="box-step">\
                                <div class="title-config-shop">\
                                    <span>Gợi ý model</span>\
                                    <div class="col-sm-4 search-sugget-model">\
                                        <div class="input-group">\
                                                <input type="text" name="modelkeyword" class="form-control" placeholder="Tìm kiếm Model sản phẩm">\
                                            <div class="input-group-btn">\
                                                <button class="btn btn-default" type="button" onclick="additem.getModelSuggest();"><span class="glyphicon glyphicon-search"></span></button>\
                                            </div>\
                                        </div>\
                                    </div>\
                                </div>\
                                <div class="row box-suggestions" rel="models"></div>\
                            </div>');

                    additem.drawModelSuggest(resp.data.models);

                    $('div#suggestion-cat-child').append('<div class="box-step">\
                                <div class="title-config-shop">\
                                    <span>Danh mục gợi ý</span>\
                                    <div class="col-sm-4 search-sugget-model">\
                                        <div class="input-group">\
                                            <input type="text" name="categorykeyword" class="form-control" placeholder="Tìm danh mục">\
                                            <div class="input-group-btn">\
                                                <button class="btn btn-default" type="button" onclick="additem.getCategorySuggest();"><span class="glyphicon glyphicon-search"></span></button>\
                                            </div>\
                                        </div>\
                                    </div>\
                                </div>\
                                <div class="row box-suggestions" rel="categoris"></div>\
                            </div>');
                    additem.drawCategorySuggest(resp.data.categories);
                    $('div[rel=categoris]').parent().append('<p class="mgt-15">Không tìm thấy? <a onclick="additem.changetab();" href="javascript:;">Xem Danh sách danh mục</a></p>');
                    $('div#suggestion-cat-child').append('<div class="box-step">\
                                <div class="title-config-shop"><span>Danh mục đã đăng gần đây</span></div>\
                                <div class="row box-suggestions" rel="currentcategories"></div>\
                            </div>');
                    if (resp.data.currentcategories.length > 0) {
                        $.each(resp.data.currentcategories, function () {
                            var idCate = this.id;
                            var html = '<ol class="breadcrumb"><input for="rdo' + idCate + '" name="_scate" value="" type="radio"/> ';
                            $.each(this.categoris, function (i) {
                                if (this.id === idCate) {
                                    html += '<li class="active">' + this.name + '</li>';
                                } else {
                                    html += '<li><a href="javascript:;">' + this.name + '</a></li>';
                                }
                            });
                            html += '</ol>';
                            $("div[rel=currentcategories]").append(html);
                            $('input[for=rdo' + idCate + ']').click(function () {
                                if (idCate !== $('input[name=category]').val()) {
                                    $("div[rel=models] div[for]").removeClass("active");
                                    $("div[rel=categoris] input[for]").prop("checked", "false");
                                    additem.builtCategory(idCate);
                                    $("input[name=category]").val(idCate).change();
                                }
                            });
                        });
                    } else {
                        $('div[rel=currentcategories]').append('<p class="mgt-15">Bạn chưa đăng sản phẩm nào! <a onclick="additem.changetab();" href="javascript:;">Xem Danh sách danh mục</a></p>');
                    }

                    $('div#suggestion-cat').append('<div class="clearfix"></div>');
                }
            }
        });
    }, 500);
};
additem.getModelSuggest = function () {
    var keyword = $('input[name=modelkeyword]').val();
    ajax({
        service: '/item/getmodelsuggest.json',
        data: {keyword: keyword},
        loading: false,
        type: 'post',
        contentType: 'json',
        done: function (resp) {
            additem.drawModelSuggest(resp.data);
        }
    });
};
additem.drawModelSuggest = function (data) {
    $("div[rel=models]").html('');
    if (data.length > 0) {
        $.each(data, function () {
            var modelId = this.id;
            $("div[rel=models]").append('<div class="col-lg-3 col-md-3 col-sm-4 col-xs-6" for="select' + modelId + '" >\
                                            <div class="product-item-choose clearfix">\
                                                <span class="img-product-bill-small"><img class="img-responsive" src="' + (this.images !== null && this.images !== '' ? this.images[0] : '') + '" alt="' + this.name + '"></span>\
                                            ' + this.name + '</div></div>');
            $('div[for=select' + modelId + ']').click(function () {
                $("input[name=modelId]").val(modelId);
                $("div[rel=currentcategories] input[for]").prop("checked", "false");
                $("div[rel=categoris] input[for]").prop("checked", "false");
                setTimeout(function () {
                    additem.drawModel($('input[name=modelId]').val(), true);
                }, 100);
                $("div[rel=models] div[for]").removeClass("active");
                $(this).addClass("active");
            });
        });
    } else {
        $('div[rel=models]').append('<p class="mgt-15 clr-red text-center">Không tìm thấy gợi ý phù hợp!</p>');
    }
};
additem.getCategorySuggest = function () {
    var keyword = $('input[name=categorykeyword]').val();
    ajax({
        service: '/item/getcategorysuggest.json',
        data: {keyword: keyword},
        loading: false,
        type: 'post',
        contentType: 'json',
        done: function (resp) {
            additem.drawCategorySuggest(resp.data);
        }
    });
};
additem.drawCategorySuggest = function (data) {
    $("div[rel=categoris]").html('');
    if (data.length > 0) {
        $.each(data, function () {
            var idCate = this.id;
            var html = '<ol class="breadcrumb"><input for="rdo' + idCate + '" name="_scate" value="" type="radio"/> ';
            $.each(this.categoris, function (i) {
                if (this.id === idCate) {
                    html += '<li class="active">' + this.name + '</li>';
                } else {
                    html += '<li><a href="javascript:;">' + this.name + '</a></li>';
                }
            });
            html += '</ol>';
            $("div[rel=categoris]").append(html);
            $('input[for=rdo' + idCate + ']').click(function () {
                if (idCate !== $('input[name=category]').val()) {
                    $("div[rel=models] div[for]").removeClass("active");
                    $("div[rel=currentcategories] input[for]").prop("checked", "false");
                    $('input[for=rdo' + idCate + ']').prop("checked", "true");
                    additem.builtCategory(idCate);
                    $("input[name=category]").val(idCate).change();
                }
            });
        });
    } else {
        $('div[rel=categoris]').append('<p class="mgt-15 clr-red text-center">Không tìm thấy gợi ý phù hợp!</p>');
    }
};
additem.changetab = function () {
    $("ul.nav-tabs a[rel=tabgen]").parent().removeClass("active");
    $("ul.nav-tabs a[rel=tabcate]").parent().addClass("active");
    $("div#suggestion-list").addClass('active');
    $("div#suggestion-cat").removeClass('active');
};
additem.drawModel = function (id, load) {
    var mm = $("div[rel=models] div[for].active").attr("for");

    if (typeof mm != 'undefined' && mm.split("select")[1] != id) {
        $("div[rel=models] div[for].active").removeClass("active");
    }
    ajax({
        service: '/model/getinfo.json',
        data: {id: id},
        loading: false,
        done: function (resp) {
            if (resp.success) {
                $("input[name=modelId]").val(resp.data.model.id);
                if (resp.data.model.categoryId !== $("input[name=category]").val() || resp.data.model.manufacturerId !== $("input[name=mf]").val()) {
                    $("input[name=mf]").val(resp.data.model.manufacturerId);
                    $("input[name=category]").val(resp.data.model.categoryId).change();
                } else {
                    $("select[name=model]").val(resp.data.model.id);
                }
                setTimeout(function () {
                    if (load) {
                        additem.builtCategory(resp.data.model.categoryId);
                    }
                    $.each(resp.data.properties, function () {
                        if ($("div[for=cp" + this.categoryPropertyId + "]").find("input[type=radio]").length > 0) {
                            if ($("div[for=cp" + this.categoryPropertyId + "]").find("input[type=text]").length > 0 && this.inputValue !== null && this.inputValue !== '') {
                                $("div[for=cp" + this.categoryPropertyId + "] input[name=inputValue]").val(this.inputValue);
                            } else if (this.categoryPropertyValueIds.length > 0) {
                                $("div[for=cp" + this.categoryPropertyId + "] input[value=" + this.categoryPropertyValueIds[0] + "]").attr("checked", true);
                            }
                        } else if ($("div[for=cp" + this.categoryPropertyId + "]").find("input[type=text]").length > 0) {
                            $("div[for=cp" + this.categoryPropertyId + "] input[name=inputValue]").val(this.inputValue);
                        } else if ($("div[for=cp" + this.categoryPropertyId + "]").find("input[type=checkbox]").length > 0 && this.categoryPropertyValueIds.length > 0) {
                            var categoryPropertyId = this.categoryPropertyId;
                            $.each(this.categoryPropertyValueIds, function () {
                                $("div[for=cp" + categoryPropertyId + "] input[value=" + this + "]").attr("checked", true);
                            });
                        }
                    });
                }, 500);
                //add Images
                ajax({
                    service: '/item/copyimagebymodel.json',
                    data: {id: additem.itemId, modelId: resp.data.model.id},
                    loading: false,
                    done: function (resp) {
                        if (resp.success) {
                            additem.drawImage(resp);
                        }
                    }
                });
            } else {
                $("select[name=model]").val('0');
            }
        }
    });
};
additem.itemId = null;
additem.saveItem = function (fn, loading) {
    if (typeof loading == 'undefined') {
        loading = false;
    }
    if (additem.done) {
        additem.done = false;
        for (var obj in CKEDITOR.instances) {
            CKEDITOR.instances[obj].updateElement();
        }
        var detail = "";
        if (typeof ($('textarea#detail').val()) != 'undefined') {
            detail = $('textarea#detail').val();
        }
        var item = {
            id: additem.itemId,
            categoryId: ($("input[name=category]").val() === '0') ? null : $("input[name=category]").val(),
            shopCategoryId: ($('input[name=shopCategoryId]').val() === '0') ? null : $("input[name=shopCategoryId]").val(),
            modelId: ($("input[name=modelId]").val() === '0') ? null : $("input[name=modelId]").val(),
            manufacturerId: ($("input[name=mf]").val() === '0') ? null : $("input[name=mf]").val(),
            name: $("input[name=name]").val(),
            detail: detail,
            condition: ($("input[name=condition]:checked").val() == 1) ? 'NEW' : 'OLD',
            weight: 0,
        };
        var bidStartPrice = (typeof $("input[name=bidStartPrice]").val() !== 'undefined' && $("input[name=bidStartPrice]").val() !== '' && !isNaN($("input[name=bidStartPrice]").val().replace(/\./g, ''))) ? $("input[name=bidStartPrice]").val().replace(/\./g, '') : 0;
        var bidStep = (typeof $("input[name=bidStep]").val() !== 'undefined' && $("input[name=bidStep]").val() !== '' && !isNaN($("input[name=bidStep]").val().replace(/\./g, ''))) ? $("input[name=bidStep]").val().replace(/\./g, '') : 0;
        if ((bidStartPrice > 0 && bidStep > 0) || additem.item.listingType == 'AUCTION') {
            //Sản phẩm đấu giá
            item.listingType = 'AUCTION';
            item.startPrice = bidStartPrice;
            item.sellPrice = ($("input[name=bidSellPrice]").val() !== '' && !isNaN($("input[name=bidSellPrice]").val().replace(/\./g, ''))) ? $("input[name=bidSellPrice]").val().replace(/\./g, '') : 0;
            item.bidStep = bidStep;
            item.startTime = additem.converDate($('input[name=bidStartTime]').val());
            item.endTime = additem.converDate($('input[name=bidEndTime]').val());
            item.quantity = 1;
        } else {
            //Sản phẩm bán ngay
            item.listingType = 'BUYNOW';
            item.startPrice = ($("input[name=startPrice]").val() !== '' && !isNaN($("input[name=startPrice]").val().replace(/\./g, ''))) ? $("input[name=startPrice]").val().replace(/\./g, '') : 0;
            item.sellPrice = ($("input[name=sellPrice]").val() !== '' && !isNaN($("input[name=sellPrice]").val().replace(/\./g, ''))) ? $("input[name=sellPrice]").val().replace(/\./g, '') : 0;
            item.startTime = additem.converDate($('input[name=startTime]').val());
            item.endTime = $('select[name=endDay]').val();
            item.quantity = ($("input[name=quantity]").val() !== '' && !isNaN($("input[name=quantity]").val().replace(/\./g, ''))) ? $("input[name=quantity]").val().replace(/\./g, '') : 0;
        }
        item.weight = ($("input[name=weight]").val() !== '' && !isNaN($("input[name=weight]").val().replace(/\./g, ''))) ? $("input[name=weight]").val().replace(/\./g, '') : 0;
        var shipmentType = $("input[name=shipmentType]:checked").val();
        if (shipmentType == 2 || shipmentType == 4) {
            //Phí cố định
            item.shipmentType = 'FIXED';
            item.shipmentPrice = ($("input[name=shipmentTypePrice]").val() !== '' && !isNaN($("input[name=shipmentTypePrice]").val().replace(/\./g, ''))) ? $("input[name=shipmentTypePrice]").val().replace(/\./g, '') : 0;
            if (shipmentType == 4) {
                item.shipmentPrice = 0;
            }
        } else if (shipmentType == 3) {
            item.shipmentType = 'BYWEIGHT';
            item.shipmentPrice = 0;
        } else {
            //Tự liên hệ
            item.shipmentType = 'AGREEMENT';
            item.shipmentPrice = 0;
        }

        item.propertis = additem.readProperty();
        ajax({
            service: '/item/add.json',
            data: item,
            loading: loading,
            type: 'post',
            contentType: 'json',
            done: function (resp) {
                if (resp.data !== null) {
                    additem.itemId = resp.data.id;
                    if (fn) {
                        fn();
                    }
                }
                if (!resp.success) {
                    popup.msg(resp.message);
                }
                additem.done = true;
            }
        });
    }
};
additem.readProperty = function () {
    var propertis = [];
    var index = 0;
    $.each($("div.param"), function () {
        var property = new Object();
        property.categoryPropertyId = $(this).attr("value");
        property.categoryPropertyValueIds = [];
        property.inputValue = null;
        if ($("div[for=cp" + property.categoryPropertyId + "]").find("input[type=radio]").length > 0) {
            if ($("div[for=cp" + property.categoryPropertyId + "]").find("input[type=text]").length > 0 && $("div[for=cp" + property.categoryPropertyId + "] input[name=inputValue]").val() !== "") {
                property.inputValue = $("div[for=cp" + property.categoryPropertyId + "] input[name=inputValue]").val();
            }

            $.each($("div[for=cp" + property.categoryPropertyId + "]").find("input[type=radio]"), function (i) {
                if ($(this).is(":checked")) {
                    property.categoryPropertyValueIds[0] = $(this).val();
                }
            });
        } else if ($("div[for=cp" + property.categoryPropertyId + "]").find("input[type=text]").length > 0 && $("div[for=cp" + property.categoryPropertyId + "] input[name=inputValue]").val() !== "") {
            property.inputValue = $("div[for=cp" + property.categoryPropertyId + "] input[name=inputValue]").val();
        } else if ($("div[for=cp" + property.categoryPropertyId + "]").find("input[type=checkbox]").length > 0) {
            property.categoryPropertyValueIds = [];
            $.each($("div[for=cp" + property.categoryPropertyId + "]").find("input[type=checkbox]"), function (i) {
                if ($(this).is(":checked")) {
                    property.categoryPropertyValueIds[i] = $(this).val();
                }
            });
        }
        if (property.inputValue !== null || property.categoryPropertyValueIds.length > 0) {
            propertis[index] = property;
            index++;
        }
    });
    return propertis;
};
additem.addItem = function (same) {
    additem.saveItem(function () {
        var serviceUrl = '/item/submit.json';
        if (itemId !== '') {
            serviceUrl = '/item/submitedit.json';
        }
        ajax({
            service: serviceUrl,
            data: {id: additem.itemId, same: same},
            loading: true,
            done: function (resp) {
                if (resp.success) {
                    if (resp.message !== 'POST_ITEM_FAIL') {
                        popup.msg(resp.message, function () {
                            if (itemId !== '') {
                                location.href = baseUrl + "/user/dang-ban.html?id=" + itemId;
                            } else {
                                xengplus.plus(100);
                                location.href = baseUrl + "/user/dang-ban-thanh-cong.html?id=" + additem.itemId;
                            }
                        });
                    } else {
                        popup.msg("Đăng bán thành công!", function () {
                            if (itemId !== '') {
                                location.href = baseUrl + "/user/dang-ban.html?id=" + itemId;
                            } else {
                                location.href = baseUrl + "/user/dang-ban-thanh-cong.html?id=" + additem.itemId;
                            }
                        });
                    }
                } else {
                    var error = "<strong>" + resp.message + ":</strong> <br/><ul style='color:red;margin-left:15px;'>";
                    $.each(resp.data, function (key, value) {
                        error += "<li> - " + value + " </li>";
                    });
                    popup.msg(error + "</ul>");
                    $('input, select[name], textarea').each(function () {
                        $(this).parents('.checkerror').removeClass('has-error');
                        $(this).next('.help-block').remove();
                        if ($(this).attr('name') && resp.data[$(this).attr('name')]) {
                            $(this).parents('.checkerror').addClass('has-error');
                            $(this).after('<span class="help-block">' + resp.data[$(this).attr('name')] + '</span>');
                        }
                    });
                }
            }
        });
    }, true);
};
additem.addImageByLocal = function () {
    additem.image(function () {
        $("input[name=id]").val(additem.itemId);
        ajaxUpload({
            service: '/item/addimage.json',
            id: 'img-add-form',
            loading: false,
            done: function (resp) {
                additem.drawImage(resp);
            }
        });
    });
};
additem.addImageByLink = function () {
    additem.image(function () {
        var link = $("input[name=imageUrl]").val();
        if (link !== '') {
            $("input[name=id]").val(additem.itemId);
            ajaxUpload({
                service: '/item/addimage.json',
                id: 'img-add-form-bylink',
                loading: true,
                done: function (resp) {
                    additem.drawImage(resp);
                    $("input[name=imageUrl]").val('');
                }
            });
        }
    });

};
additem.image = function (fn) {
    if (fn) {
        if (typeof additem.itemId == 'undefined' || additem.itemId == null) {
            additem.saveItem(fn);
        } else {
            fn();
        }
    }
};

additem.drawImage = function (rs) {
    if (rs.data.images !== null && rs.data.images !== '' && rs.data.images.length > 0) {
        ajax({
            service: '/item/genimages.json',
            data: {ids: JSON.stringify(rs.data.images)},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    $("div[rel=img] div").remove();
                    $.each(rs.data.images, function () {
                        var imgId = this;
                        $.each(resp.data, function (key, value) {
                            if (imgId == key) {
                                $("div[rel=img]").append('<div for="img' + key + '" imgageId="' + key + '" class="submit-img-item">\
                                    <span class="glyphicon glyphicon-trash"></span>\
                                    <div class="img" style="width:50px;height:50px;overflow:hidden;"><img src="' + value + '" /></div>\
                                </div>');
                                $('div[for=img' + key + '] .glyphicon-trash').click(function () {
                                    additem.delImages(additem.itemId, $('div[for=img' + key + ']').attr("imgageId"));
                                });
                                $('div[for=img' + key + '] .img').click(function () {
                                    additem.changeDefaultImage(key);
                                });
                            }
                        });
                    });

                    $("input[name=imageUrl]").val("");
                    $("input[name=image]").val("");
                    additem.getdefaultimage();

                }
            }
        });
    }
    $("div[rel=loading]").css({"display": "none"});
};
additem.getdefaultimage = function () {
    ajax({
        service: '/item/getdefaultimage.json',
        data: {id: additem.itemId},
        loading: false,
        done: function (result) {
            if (result.success) {
                var ex = false;
                $.each($("div[imgageid]"), function () {
                    if (!ex && $(this).attr('imgageid') == result.data) {
                        $('.icon-img-default').remove();
                        $(this).append('<div class="icon-img-default"></div>');
                        ex = true;
                    }
                });
                $('#photos').html('<img id="photo" style="max-height: 225px;max-width: 225px;" src="' + $("div[rel=img] div[imgageid=" + result.data + "] img").attr('src') + '" for="' + result.data + '" />');
                $('#photos').css("background", "none");
                $('#photo').Jcrop({
                    onSelect: additem.previewItemDefaultImage, aspectRatio: 1
                });
                $('.jcrop-keymgr').css("position", "absolute");
            }
        }
    });
};
additem.delImages = function (id, imageName) {
    popup.confirm("Bạn có chắc chắn muốn xóa ảnh này?", function () {
        ajax({
            service: '/item/delimage.json',
            data: {id: id, name: imageName},
            loading: false,
            done: function (resp) {
                if (resp.success) {
                    $('div[rel=img] div[for=img' + imageName + ']').remove();
                    additem.getdefaultimage();
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
additem.converDate = function (dateTime) {
    if (typeof dateTime != 'undefined') {
        var date = new Date();

        var mm = dateTime.split(' ');
        if (typeof mm != 'undefined') {
            var dd = mm[0].split('/');
            date.setFullYear(dd[2]);
            date.setMonth(dd[1] - 1);
            date.setDate(dd[0]);

            if (typeof mm[1] != 'undefined') {
                var hh = mm[1].split(':');
                date.setHours(hh[0], hh[1], 0, 0);
            }
        }
        var timestamp = new Date(date).getTime();
        return (timestamp === 0 ? new Date().getTime() : timestamp);
    }
};
additem.countCharacters = function () {
    var count = 180 - eval($('input[name=name]').val().length);
    if (count <= 0) {
        $('input[name=name]').val($('input[name=name]').val().substring(0, 180));
        count = 0;
        $('strong.countName').html('Còn ' + count + '/180');
        return false;
    }
    $('strong.countName').html('Còn ' + count + '/180');
};


additem.initEditor = function (id) {
    CKEDITOR.on('instanceReady', function (e) {
        e.editor.on('blur', function (ev) {
            additem.editorEdited = e.editor.checkDirty();
            if (additem.editorEdited) {
                additem.downloadImageFromEditor(id);
                e.editor.resetDirty();
            }
        });
    });
    CKEDITOR.basePath = staticUrl + '/lib/ckeditor/';

    var config = {extraPlugins: 'confighelper', autoGrow_maxHeight: 400};
    CKEDITOR.replace(id, config);
};

additem.downloadImageFromEditor = function (id) {
    additem.image(function () {
        for (var obj in CKEDITOR.instances) {
            CKEDITOR.instances[obj].updateElement();
        }
        var detail = $('textarea#' + id).val();
        var temp_container = $("<div></div>");
        var arrImg = new Array();
        temp_container.html(detail);
        temp_container.find("img").each(function (i, el) {
            arrImg.push($(el).attr('src'));
        });
        if (arrImg.length > 0) {
            ajax({
                service: '/item/downloadfromeditor.json',
                data: {imgs: JSON.stringify(arrImg), id: additem.itemId},
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
                        for (var obj in CKEDITOR.instances) {
                            CKEDITOR.instances[obj].setData(temp_container.html());
                        }
                        if (typeof resp.data["images"] !== 'undefined' && resp.data["images"] != null) {
                            $.each(resp.data["images"], function (key, value) {
                                $("div[rel=img]").append('<div for="img' + key + '" imgageId="' + key + '" class="submit-img-item">\
                                    <span class="glyphicon glyphicon-trash"></span>\
                                    <div class="img" style="width:50px;height:50px;overflow:hidden;"><img src="' + value + '" /></div>\
                                </div>');
                                $('div[for=img' + key + '] .glyphicon-trash').click(function () {
                                    additem.delImages(additem.itemId, $('div[for=img' + key + ']').attr("imgageId"));
                                });
                                $('div[for=img' + key + '] .img').click(function () {
                                    additem.changeDefaultImage(key);
                                });
                            });
                            additem.getdefaultimage();
                        }
                    }
                }
            });
        }
    });
};

additem.changeDefaultImage = function (imageId) {
    ajax({
        service: '/item/setdefaultimage.json',
        data: {imageId: imageId, id: additem.itemId},
        loading: true,
        done: function (resp) {
            if (resp.success) {
                $('div[rel=img] .icon-img-default').remove();
                $.each($("div[rel=img] div[imgageid=" + resp.data + "]"), function (i) {
                    if (i == 0) {
                        $(this).append('<div class="icon-img-default"></div>');
                    }
                });
                $('#photo').attr('src', $("div[rel=img] div[imgageid=" + resp.data + "] img").attr('src'));
                $('#photo').attr('for', resp.data);
                $('#photo').parent().css("background", "none");

                $('.jcrop-holder img').attr('src', $("div[rel=img] div[imgageid=" + resp.data + "] img").attr('src'));
                $('.jcrop-holder img').attr('for', resp.data);

                $('#photo').Jcrop({
                    onSelect: additem.previewItemDefaultImage, aspectRatio: 1
                });
                $('.jcrop-keymgr').css("position", "absolute");
            }
        }
    });
};
additem.previewItemDefaultImage = function (selection) {
    if (parseInt(selection.w) <= 0 && parseInt(selection.h) <= 0) {
        return;
    }
    popup.confirm('Bạn có chắc chắn chọn vị trí ảnh này?', function () {
        // calculate
        var container = $('#photo');
        var images = document.getElementById('photo');
        // get ti le anh
        var tiLeAnh = images.naturalWidth / container.width();
        // calculate
        var calculatex1 = Math.round(selection.x * tiLeAnh);
        var calculatey1 = Math.round(selection.y * tiLeAnh);
        var calculatewidth = Math.round(selection.w * tiLeAnh);
        var calculateheight = Math.round(selection.h * tiLeAnh);


        var imageId = $('#photo').attr('for');
        if (calculateheight >= 300 || calculatewidth >= 300) {
            ajax({
                service: '/item/cropdefaultimage.json',
                data: {id: additem.itemId, x: calculatex1, y: calculatey1, width: calculatewidth, height: calculateheight, imageId: imageId},
                loading: true,
                done: function (resp) {
                    if (resp.success) {
                        popup.msg('Cắt ảnh thành công');
                        $.each($("div[rel=img] div[imgageid=" + imageId + "]"), function (i) {
                            if (i == 0) {
                                $("div[rel=img] div[imgageid=" + imageId + "] img").attr('src', resp.data);
                                $("div[rel=img] div[imgageid=" + imageId + "]").attr('imgageid', resp.message);
                            }
                        });
                        $("div[rel=img] div[imgageid=" + resp.message + "]").attr('for', resp.message);
                        $('#photo').attr('src', resp.data);
                        $('#photo').attr('for', resp.message);
                    } else {
                        popup.msg(resp.message);
                    }
                }
            });
        } else {
            setTimeout(function () {
                popup.msg('Ảnh cắt phải có kích thước tối thiểu 1 chiều là 300px');
            }, 200);
        }

    });

};
additem.calcWeight = function () {
    var length = $('input[name=dataLength]').val().replace(/\./g, '');
    var width = $('input[name=dataWidth]').val().replace(/\./g, '');
    var height = $('input[name=dataHeight]').val().replace(/\./g, '');

    $('input[name=weight]').val(Math.ceil(length * width * height / 6000));
};

additem.setColor = function (obj) {
    var html = '';
    var alias;
    if (typeof obj !== 'undefined') {
        alias = $(obj).attr("data-alias");
        $("input[rel-data=" + alias + "]").attr("checked", $(obj).is(":checked"));
    }
    $.each($('input[rel=text_color]'), function () {
        alias = $(this).attr("rel-data");
        $("input[data-alias=" + alias + "]").attr("checked", $(this).is(":checked"));
        if (this.checked) {
            html += '<div class="tc-item" style="background-color:' + property.getCollor(alias) + ';"></div>';
        }
    });
    $('div[data-rel=show-color]').html(html);
};

additem.loadPropertyColor = function (cProperty, propertyValues) {
    var html = "";
    var flag = false;
    if (textUtils.createAlias(cProperty.name) === 'mau-sac') {
        $.each(propertyValues, function () {
            if (this.categoryPropertyId === cProperty.id) {
                flag = true;
                html += '<div class="col-xs-4 padding-all-5">\
                                            <div class="checkbox">\
                                                <label class="one-line"><input onclick="additem.setColor(this);" data-alias="' + textUtils.createAlias(this.name) + '" type="checkbox"><div class="tc-item" style="background-color:' + property.getCollor(textUtils.createAlias(this.name)) + ';"></div></label>\
                                            </div>\
                                        </div>';
            }
        });
    }

    if (flag) {
        var box = '<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-right">\
                        <div class="control-label">Màu sắc:</div>\
                        <div class="row">\
                            <div class="submit-param z-index-100">\
                                <div class="param">\
                                    <label class="param-label"><span class="fa fa-plus-square"></span><span class="pram-line"></span>Chọn màu sắc</label>\
                                    <div class="param-popup">\
                                        <div class="row" data-rel="show-list-color"></div>\
                                        <div class="param-button">\
                                            <a class="btn btn-primary btn-sm button-gray">Lưu</a>\
                                            <a class="btn btn-default btn-sm button-gray">Bỏ qua</a>\
                                        </div>\
                                    </div>\
                                </div>\
                                <div class="tiny-colorsize" data-rel="show-color">\
                                </div>\
                            </div>   \
                        </div>\
                    </div>';
        $('div[data-rel=box-color-postItem]').html(box);
    }
    $('div[data-rel=show-list-color]').html(html);
};

additem.setSize = function (obj) {
    var html = '';
    var alias;
    if (typeof obj !== 'undefined') {
        alias = $(obj).attr("data-alias");
        $("input[rel-data=" + alias + "]").attr("checked", $(obj).is(":checked"));
    }
    $.each($('input[rel=text_size]'), function () {
        alias = $(this).attr("rel-data");
        $("input[data-alias=" + alias + "]").attr("checked", $(this).is(":checked"));
        if (this.checked) {
            html += ' <div class="tc-item" style="text-transform: uppercase;")>' + alias + '</div>';
        }
    });
    $('div[data-rel=show-Size]').html(html);
};

additem.loadPropertySize = function (cProperty, propertyValues) {
    var html = "";
    var flag = false;
    if (textUtils.createAlias(cProperty.name) === 'kich-cox') {
        $.each(propertyValues, function () {
            if (this.categoryPropertyId === cProperty.id) {
                flag = true;
                html += '<div class="col-xs-4 padding-all-5">\
                                            <div class="checkbox">\
                                                <label class="one-line"><input onclick="additem.setSize(this);" data-alias="' + textUtils.createAlias(this.name) + '" type="checkbox"> ' + this.name + '</label>\
                                            </div>\
                                        </div>';
            }
        });
    }

    if (flag) {
        var box = '<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 pull-left">\
                    <div class="control-label">Kích thước:</div>\
                    <div class="row">\
                        <div class="submit-param z-index-101">\
                            <div class="param">\
                                <label class="param-label"><span class="fa fa-plus-square"></span><span class="pram-line"></span>Chọn kích thước</label>\
                                <div class="param-popup">\
                                    <div class="row" data-rel="show-list-size">\
                                    </div>\
                                    <div class="param-button">\
                                        <a class="btn btn-primary btn-sm button-gray">Lưu</a>\
                                        <a class="btn btn-default btn-sm button-gray">Bỏ qua</a>\
                                    </div>\
                                </div>\
                            </div>\
                            <div class="tiny-colorsize" data-rel="show-Size">\
                            </div>\
                        </div>\
                    </div>\
                </div>';
        $('div[data-rel=box-size-postItem]').html(box);
    }
    $('div[data-rel=show-list-size]').html(html);
};