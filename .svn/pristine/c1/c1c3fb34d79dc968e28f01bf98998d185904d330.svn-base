var modelCompare = {};
modelCompare.categoryPropertyValues = [];
modelCompare.categoryId = '';
modelCompare.modelIds = [];
modelCompare.pop = function(_categoryId, _modelId) {
    modelCompare.categoryId = _categoryId;
    if (modelCompare.modelIds.indexOf(_modelId) === -1) {
        modelCompare.modelIds.push(_modelId);
    }
    ajax({
        service: '/category/getproperties.json',
        data: {id: _categoryId},
        done: function(resp) {
            if (resp.success) {
                modelCompare.categoryPropertyValues = resp.data.propertyValues;
                resp.data.modelId = _modelId;
                popup.open('popup-modelCompare', '', template('/market/tpl/modelCompare.tpl', resp.data), [
                    {
                        title: 'Đóng',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-modelCompare');
                        }
                    }
                ], 'modal-lg');
                setTimeout(function() {
                    modelCompare.drawModel(_modelId);
                }, 300);
            } else {
                popup.msg(resp.message, function() {
                    location.reload();
                });
            }
        }
    });
};

modelCompare.drawModel = function(_modelId) {
    if (_modelId === null || _modelId === '') {
        return false;
    }
    ajax({
        service: '/model/getinfo.json',
        data: {id: _modelId},
        done: function(resp) {
            if (resp.success) {
                var model = resp.data.model;
                var properties = resp.data.properties;
                //draw head
                var html = '<div class="compare-item">\
                                <div class="ci-thumb"><a target="_blank" href="' + baseUrl + urlUtils.model(model.id, model.name) + '" title="' + model.name + '" ><img src="' + (model.images === null || model.length === 0 ? baseUrl + '/market/images/no-image-product.png' : model.images[0]) + '" alt="' + model.name + '"></a></div>\
                                <div class="ci-title">\
                                    <a class="ci-link" target="_blank" href="' + baseUrl + urlUtils.model(model.id, model.name) + '" title="' + model.name + '">' + model.name + '</a>\
                                    <p>Hàng mới từ: <b class="text-danger">' + model.oldMinPrice.toMoney() + '<sup>đ</sup></b></p>\
                                    <p>Hàng cũ từ: <b class="text-danger">' + model.newMinPrice.toMoney() + '<sup>đ</sup></b></p>\
                                </div>\
                                <a target="_blank" class="btn btn-default btn-sm" href="' + baseUrl + urlUtils.model(model.id, model.name) + '" title="' + model.name + '"><span class="icon16-search"></span>Xem ngay</a>\
                                <span class="glyphicon glyphicon-trash" onclick="modelCompare.removeModel(\'' + model.id + '\')" ></span>\
                            </div>';
                $("th[for=model_" + _modelId + "]").html(html);
                //draw properties
                $.each(properties, function() {
                    var value = '';
                    if (this.inputValue !== null && this.inputValue !== '') {
                        value = this.inputValue;
                    } else if (this.categoryPropertyValueIds !== null && this.categoryPropertyValueIds.length > 0) {
                        for (var i = 0; i < modelCompare.categoryPropertyValues.length; i++) {
                            var pvalue = modelCompare.categoryPropertyValues[i]
                            for (var j = 0; j < this.categoryPropertyValueIds.length; j++) {
                                if (pvalue.id === this.categoryPropertyValueIds[j]) {
                                    if (value !== '') {
                                        value += ', ';
                                    }
                                    value += pvalue.name;
                                }
                            }
                        }
                    }
                    $("tr[for=c_" + this.categoryPropertyId + "] td[for=model_" + _modelId + "]").html(value);
                });
            } else {
                popup.msg(resp.message, function() {
                    location.reload();
                });
            }
        }
    });
};
var myVar = null;
modelCompare.searchModel = function(obj) {
    $("div[rel=autosearch]").html('');
    var keyword = $(obj).val();
    if (keyword === null || keyword === '') {
        return false;
    }
    if (myVar !== null) {
        clearTimeout(myVar);
    }
    myVar = setTimeout(function() {
        $("a.cs-search").html('<img src="' + baseUrl + '/static/market/images/loading-fast.gif" style="height:21px; margin-top:-2px;" />');
        ajax({
            service: '/model/findbycatmf.json',
            data: {categoryId: modelCompare.categoryId, keyword: keyword},
            loading: false,
            done: function(resp) {
                $("div[rel=autosearch]").html('');
                var html = "<ul>";
                if (resp.success && resp.data.length > 0) {
                    $.each(resp.data, function() {
                        if (modelCompare.modelIds.indexOf(this.id) === -1) {
                            html += '<li onclick="modelCompare.selectModel(\'' + this.id + '\')" ><a>' + this.name + '</a></li>';
                        }
                    });
                } else {
                    html += '<li class="danger" ><a>Không tìm thấy thông tin</a></li>';
                }
                html += "</ul>";
                $("div[rel=autosearch]").html(html);
                $("div[rel=autosearch]").css({"display": "block"});
                $("a.cs-search").html('<span class="glyphicon glyphicon-search"></span>');
            }
        });
    }, 500);

};

modelCompare.selectModel = function(_modelId) {
    if (modelCompare.modelIds.indexOf(_modelId) === -1) {
        modelCompare.modelIds.push(_modelId);
    }
    $("div[rel=autosearch]").html('');
    $("div[rel=autosearch]").css({"display": "none"});
    var head, index;
    $.each($("tr[rel=head] th"), function(i) {
        if (typeof $(this).attr("for") === 'undefined') {
            console.log(modelCompare.modelIds.length);
            if (modelCompare.modelIds.length <= 2 && $(this).find(".compare-search input").length > 0) {

            } else {
                head = this;
                index = i;
                $(head).attr("for", "model_" + _modelId);
                return false;
            }
        }
    });
    $.each($("tr[rel=body]"), function() {
        $(this).children().eq(index).attr("for", "model_" + _modelId);
    });
    modelCompare.drawModel(_modelId);
};

modelCompare.removeModel = function(_modelId) {
    var index = modelCompare.modelIds.indexOf(_modelId);
    modelCompare.modelIds.splice(index, 1);

    //draw body
    index = -1;
    $.each($("tr[rel=head] th"), function(i) {
        if (typeof $(this).attr("for") !== 'undefined' && $(this).attr("for") === "model_" + _modelId) {
            index = i;
            return false;
        }
    });
    $.each($("tr[rel=body]"), function() {
        $(this).children().eq(index).html("");
        $(this).children().eq(index).attr("for", '');
    });

    //draw head
    var html = '<div class="compare-upload"></div>';
    if ($(".compare-search input").length === 0) {
        html += '<div class="compare-search">\
            <input onfocus="modelCompare.searchModel(this);" onkeyup="modelCompare.searchModel(this);" type="text" class="text" placeholder="Nhập tên sản phẩm để so sánh" />\
            <a class="cs-search"><span class="glyphicon glyphicon-search"></span></a>\
            <div class="compare-autosearch" rel="autosearch" style="overflow-y: auto; max-height: 400px;" ></div>';
    }
    html += '</div>';
    $("tr[rel=head] th[for=model_" + _modelId + "]").html(html);
    $("tr[rel=head] th[for=model_" + _modelId + "]").removeAttr("for");
};

modelCompare.compare = function(_categoryId, _modelIds) {
    console.log(_modelIds);
    if (_modelIds !== '' && _modelIds.length > 0) {
        if (typeof _modelIds[0] !== 'undefined') {
            modelCompare.pop(_categoryId, _modelIds[0]);
        }
        if (typeof _modelIds[1] !== 'undefined') {
            setTimeout(function() {
                modelCompare.selectModel(_modelIds[1]);
            }, 500);
        }
        if (typeof _modelIds[2] !== 'undefined') {
            setTimeout(function() {
                modelCompare.selectModel(_modelIds[2]);
            }, 500);
        }
    } else {
        popup.msg("Bạn cần chọn model để thực hiện chức năng này");
    }
};