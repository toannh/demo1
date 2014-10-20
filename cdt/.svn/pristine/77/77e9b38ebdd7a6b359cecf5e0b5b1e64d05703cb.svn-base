urlUtils = {};

urlUtils.browseUrl = function(itemSearch, name, changes) {
    var itemSearch = jQuery.extend(true, {}, itemSearch);
    if (changes && changes !== null && changes.length > 0) {
        for (var i = 0; i < changes.length; i++) {
            var op = changes[i].op;
            var key = changes[i].key;
            var val = changes[i].val;

            if (key === "cid") {
                if (op === "mk") {
                    itemSearch.categoryIds = [];
                    itemSearch.categoryIds.push(val);
                } else if (op === "rm") {
                    itemSearch.categoryIds = [];
                    itemSearch.categoryId = null;
                }
            }
            if (key === "keyword") {
                if (op === "mk") {
                    itemSearch.keyword = val;
                } else if (op === "rm") {
                    itemSearch.keyword = null;
                }
            }
            if (key === "sellerId") {
                if (op === "mk") {
                    itemSearch.sellerId = val;
                } else if (op === "rm") {
                    itemSearch.sellerId = null;
                }
            }
            if (key === "promotionId") {
                if (op === "mk") {
                    itemSearch.promotionId = val;
                } else if (op === "rm") {
                    itemSearch.promotionId = null;
                }
            }
            if (key === "manufacturers") {
                if (op === "mk") {
                    if (itemSearch.manufacturerIds.indexOf(val) > -1) {
                        itemSearch.manufacturerIds.splice(itemSearch.manufacturerIds.indexOf(val));
                    }
                    itemSearch.manufacturerIds.push(val);
                } else if (op === "rm") {
                    if (itemSearch.manufacturerIds.indexOf(val) > -1) {
                        itemSearch.manufacturerIds.splice(itemSearch.manufacturerIds.indexOf(val));
                    }
                } else if (op === "cl") {
                    itemSearch.manufacturerIds = [];
                }
            }
            if (key === "models") {
                if (op === "mk") {
                    if (itemSearch.modelIds.indexOf(val) > -1) {
                        itemSearch.modelIds.splice(itemSearch.modelIds.indexOf(val));
                    }
                    itemSearch.modelIds.push(val);
                } else if (op === "rm") {
                    if (itemSearch.modelIds.indexOf(val) > -1) {
                        itemSearch.modelIds.splice(itemSearch.modelIds.indexOf(val));
                    }
                } else if (op === "cl") {
                    itemSearch.modelIds = [];
                }
            }
            if (key === "cities") {
                if (op === "mk") {
                    if (itemSearch.cityIds.indexOf(val) > -1) {
                        itemSearch.cityIds.splice(itemSearch.cityIds.indexOf(val));
                    }
                    itemSearch.cityIds.push(val);
                } else if (op === "rm") {
                    if (itemSearch.cityIds.indexOf(val) > -1) {
                        itemSearch.cityIds.splice(itemSearch.cityIds.indexOf(val));
                    }
                } else if (op === "cl") {
                    itemSearch.cityIds = [];
                }
            }
            if (key === "properties") {
                if (op === "mk") {
                    jval = $.parseJSON(val);
                    $.each(itemSearch.properties, function(i) {
                        if (itemSearch.properties[i].name === jval.name && itemSearch.properties[i].value === jval.value && itemSearch.properties[i].operator === jval.operator) {
                            itemSearch.properties.splice(i, 1);
                            return false;
                        }
                    });
                    itemSearch.properties.push(jval);
                } else if (op === "rm") {
                    jval = $.parseJSON(val);
                    $.each(itemSearch.properties, function(i) {
                        if (itemSearch.properties[i].name == jval.name && itemSearch.properties[i].value == jval.value && itemSearch.properties[i].operator == jval.operator) {
                            itemSearch.properties.splice(i, 1);
                            return false;
                        }
                    });
                } else if (op === "cl") {
                    itemSearch.properties = [];
                }
            }

            if (key === "freeship") {
                if (op === "mk") {
                    itemSearch.freeShip = true;
                } else if (op === "rm") {
                    itemSearch.freeShip = false;
                }
            }
            if (key === "cod") {
                if (op === "mk") {
                    itemSearch.cod = true;
                } else if (op === "rm") {
                    itemSearch.cod = false;
                }
            }
            if (key === "onlinepayment") {
                if (op === "mk") {
                    itemSearch.onlinePayment = true;
                } else if (op === "rm") {
                    itemSearch.onlinePayment = false;
                }
            }
            if (key === "promotion") {
                if (op === "mk") {
                    itemSearch.promotion = true;
                } else if (op === "rm") {
                    itemSearch.promotion = false;
                }
            }
            if (key === "type") {
                itemSearch.listingType = val;
            }
            if (key === "condition") {
                itemSearch.condition = val;
            }
            if (key === "pricefrom") {
                itemSearch.priceFrom = val;
            }
            if (key === "priceto") {
                itemSearch.priceTo = val;
            }
            if (key === "order") {
                itemSearch.orderBy = val;
            }
            if (key === "page") {
                itemSearch.pageIndex = val;
            }
        }
    }
    var k = 0;
    if (typeof itemSearch.categoryIds === 'undefined' || itemSearch.categoryIds === null || itemSearch.categoryIds.length <= 0) {
        k = 1;
        var url = "/tim-kiem.html";
    } else {
        var url = "/mua-ban/" + itemSearch.categoryIds[0] + "/" + textUtils.createAlias(name) + ".html";
    }
    var search = '';
    var keyword = itemSearch.keyword;
    if (typeof itemSearch.pageIndex == 'undefined') {
        itemSearch.pageIndex = 0;
    }
    var pageIndex = itemSearch.pageIndex;

    if ((itemSearch.modelIds != null && itemSearch.modelIds.length > 0)
            || (typeof (itemSearch.manufacturerIds) !== 'undefined' && itemSearch.manufacturerIds !== null && itemSearch.manufacturerIds.length > 0)
            || (typeof (itemSearch.cityIds) !== 'undefined' && itemSearch.cityIds !== null && itemSearch.cityIds.length > 0)
            || itemSearch.orderBy > 0
            || (typeof (itemSearch.properties) !== 'undefined' && itemSearch.properties !== null && itemSearch.properties.length > 0)
            || (itemSearch.condition == 'NEW' || itemSearch.condition == 'OLD')
            || (itemSearch.listingType == 'AUCTION' || itemSearch.listingType == 'BUYNOW')
            || (itemSearch.priceFrom > 0 || itemSearch.priceTo > 0) || itemSearch.cod == true
            || itemSearch.freeShip == true || itemSearch.onlinePayment == true || itemSearch.promotion == true
            || (typeof (itemSearch.sellerId) !== 'undefined' && itemSearch.sellerId !== null && itemSearch.sellerId != '')
            || (typeof (itemSearch.promotionId) !== 'undefined' && itemSearch.promotionId !== null && itemSearch.promotionId != '')) {
        itemSearch.keyword = null;
        itemSearch.pageIndex = 0;

        search = "filter=" + base64.encode(JSON.stringify(itemSearch));
    }
    if (keyword && keyword !== "") {
        if (search == '' && k == 1) {
            return '/s/' + keyword.replace(/\s+/g, "+").trim() + '.html' + ((pageIndex == 'undefined' || pageIndex <= 1) ? '' : "?page=" + pageIndex);
        } else {
            return url + "?keyword=" + keyword.replace(/\s+/g, "+").trim() + ((pageIndex == 'undefined' || pageIndex <= 1) ? '' : "&page=" + pageIndex) + (search == "" ? "" : ("&" + search));
        }
    } else {
        return url + (search === '' ? ((pageIndex == 'undefined' || pageIndex <= 1) ? '' : "?page=" + pageIndex)
                : (((pageIndex == 'undefined' || pageIndex <= 1) ? '?' : "?page=" + pageIndex + "&") + search));
    }
};

urlUtils.modelBrowseUrl = function(modelSearch, name, changes) {
    var modelSearch = jQuery.extend(true, {}, modelSearch);
    if (changes && changes !== null && changes.length > 0) {
        for (var i = 0; i < changes.length; i++) {
            var op = changes[i].op;
            var key = changes[i].key;
            var val = changes[i].val;
            if (key === "cid") {
                modelSearch.categoryId = val;
            }
            if (key === "keyword") {
                if (op === "mk") {
                    modelSearch.keyword = val;
                } else if (op === "rm") {
                    modelSearch.keyword = null;
                }
            }
            if (key === "manufacturers") {
                if (op === "mk") {
                    if (modelSearch.manufacturerIds.indexOf(val) > -1) {
                        modelSearch.manufacturerIds.splice(modelSearch.manufacturerIds.indexOf(val));
                    }
                    modelSearch.manufacturerIds.push(val);
                } else if (op === "rm") {
                    if (modelSearch.manufacturerIds.indexOf(val) > -1) {
                        modelSearch.manufacturerIds.splice(modelSearch.manufacturerIds.indexOf(val));
                    }
                } else if (op === "cl") {
                    modelSearch.manufacturerIds = [];
                }
            }
            if (key === "properties") {
                if (op === "mk") {
                    jval = $.parseJSON(val);
                    $.each(modelSearch.properties, function(i) {
                        if (modelSearch.properties[i].name === jval.name && modelSearch.properties[i].value === jval.value && modelSearch.properties[i].operator === jval.operator) {
                            modelSearch.properties.splice(i, 1);
                            return false;
                        }
                    });
                    modelSearch.properties.push(jval);
                } else if (op === "rm") {
                    jval = $.parseJSON(val);
                    $.each(modelSearch.properties, function(i) {
                        if (modelSearch.properties[i].name == jval.name && modelSearch.properties[i].value == jval.value && modelSearch.properties[i].operator == jval.operator) {
                            modelSearch.properties.splice(i, 1);
                            return false;
                        }
                    });
                } else if (op === "cl") {
                    modelSearch.properties = [];
                }
            }
            if (key === "order") {
                modelSearch.orderBy = val;
            }
            if (key === "page") {
                modelSearch.pageIndex = val;
            }
        }
    }
    if (typeof modelSearch.categoryId === 'undefined' || modelSearch.categoryId === null) {
        var url = "/tim-kiem-model.html";
    } else {
        var url = "/mua-ban/model/" + modelSearch.categoryId + "/" + textUtils.createAlias(name) + ".html";
    }
    var search = '';
    var keyword = modelSearch.keyword;
    if (typeof modelSearch.pageIndex == 'undefined') {
        modelSearch.pageIndex = 0;
    }
    var pageIndex = modelSearch.pageIndex;
    if ((modelSearch.manufacturerIds !== null && modelSearch.manufacturerIds.length > 0)
            || modelSearch.orderBy > 0 || (modelSearch.properties !== null && modelSearch.properties.length > 0) || modelSearch.pageIndex > 1) {
        modelSearch.keyword = null;
        modelSearch.pageIndex = 0;
        search = "search=" + base64.encode(JSON.stringify(modelSearch));
    }

    if (keyword && keyword !== "") {
        return url + "?keyword=" + keyword.replace(/\s+/g, "+") + ((pageIndex == 'undefined' || pageIndex <= 1) ? '' : "&page=" + pageIndex) + (search === '' ? '' : ("&" + search));
    } else {
        return url + (search === '' ? ((pageIndex == 'undefined' || pageIndex <= 1) ? '' : "?page=" + pageIndex) : (((pageIndex == 'undefined' || pageIndex <= 1) ? '?' : "?page=" + pageIndex + "&") + search));
    }
};
urlUtils.manufacturerUrl = function(id) {
    //location.href "";
    var itemSearch = {};
    itemSearch.manufacturerIds = [];
    itemSearch.manufacturerIds.push(id);
    var url = urlUtils.modelBrowseUrl(itemSearch, null, null);
    location.href = url;
};


urlUtils.shopBrowseUrl = function(itemSearch, alias, changes) {
    var itemSearch = jQuery.extend(true, {}, itemSearch);
    if (changes && changes !== null && changes.length > 0) {
        for (var i = 0; i < changes.length; i++) {
            var op = changes[i].op;
            var key = changes[i].key;
            var val = changes[i].val;
            if (key === "cid") {
                itemSearch.shopCategoryId = val;
            }
            if (key === "keyword") {
                if (op === "mk") {
                    itemSearch.keyword = val;
                } else if (op === "rm") {
                    itemSearch.keyword = null;
                }
            }
            if (key === "order") {
                itemSearch.order = val;
            }
            if (key === "page") {
                itemSearch.pageIndex = val;
            }
        }
    }
    params = [];
    var index = 0;
    if (typeof itemSearch.shopCategoryId !== 'undefined' && itemSearch.shopCategoryId !== null && itemSearch.shopCategoryId !== '') {
        params[index] = "cid=" + itemSearch.shopCategoryId;
        index++;
    }
    if (itemSearch.keyword && itemSearch.keyword !== "") {
        params[index] = "keyword=" + itemSearch.keyword.replace(/\s+/g, "+");
        index++;
    }
    if (itemSearch.order > 0) {
        params[index] = "order=" + itemSearch.order;
        index++;
    }
    if (itemSearch.pageIndex > 0) {
        params[index] = "page=" + itemSearch.pageIndex;
        index++;
    }


    var strParams = "";
    var first = true;
    for (var i = 0; i < params.length; i++) {
        if (first) {
            first = false;
            strParams += "?";
        } else {
            strParams += "&";
        }
        strParams += params[i];
    }
    return "/" + alias + "/browse.html" + strParams;
};

urlUtils.model = function(_id, _name) {
    return "/model/" + _id + "/" + textUtils.createAlias(_name) + ".html";
};
