shophomeitem = {};
// Hành động hiện thời
shophomeitem.action = 'add';
// Danh sách sản phẩm lưu tạm
shophomeitem.items = new Array();
shophomeitem.init = function() {
    $("input[name=checkItem]").change(function() {
        if ($(this).is(":checked")) {
            $("input[for=checkallbox]").attr("checked", true);
        } else {
            $("input[for=checkallbox]").attr("checked", false);
        }
    });
};
$("#btnSelectItem").click(function() {
    shophomeitem.action = 'add';
    // clear item cu
    shophomeitem.items.length = 0;
    shophomeitem.items = $("#addItem").find("input[name=itemIds]").val().trim().split(',');
    shophomeitem.selectItem();
});
shophomeitem.showIconBox = function() {
    popup.open('popup-icon', 'Chèn Icon', template('/user/tpl/iconlist.tpl'), [
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-icon');
            }
        }
    ], 'modal-lg');
    $("#iconBox div").click(function() {
        $("input[name=icon]").val($(this).text().trim());
        popup.close('popup-icon');
    });
};
shophomeitem.addShopHomeItem = function() {
    $(".help-block[for=error]").html("");
    $(".form-group").removeClass("has-error");
    var shopHomeItem = new Object();
    shopHomeItem.icon = $("input[name=icon]").val();
    shopHomeItem.name = $("input[name=name]").val();
    shopHomeItem.itemIds = $("input[name=itemIds]").val().split(",");
    shopHomeItem.position = $("input[name=position]").val();
    shopHomeItem.active = $("select[name=active]").find(":selected").val() == "true" ? true : false;
    ajax({
        service: '/shophomeitem/add.json',
        loading: true,
        data: shopHomeItem,
        contentType: 'json',
        type: 'post',
        done: function(resp) {
            if (resp.success) {
                popup.msg(resp.message);
                setTimeout(function() {
                    location.reload();
                }, 3000);
            } else {
                if ($("input[name=itemIds]").val() == '') {
                    $('input[name=itemIds]').parents('.form-group').addClass('has-error');
                    $('input[name=itemIds]').after('<span class="help-block" for="error">Id sản phẩm không được trống</span>');
                    valid = false;
                }
                if (shopHomeItem.position == '') {
                    $('input[name=position]').parents('.form-group').addClass('has-error');
                    $('input[name=position]').after('<span class="help-block" for="error">Thứ tự box không được trống</span>');
                    valid = false;
                }
                $.each(resp.data, function(type, value) {
                    $('input[name=' + type + ']').parents('.form-group').addClass('has-error');
                    $('input[name=' + type + ']').after('<span class="help-block" for="error">' + value + '</span>');
                });
            }
        }
    });
};
shophomeitem.pagination = function(pageIndex) {
    this.getProductByPageIndex(pageIndex);
}
shophomeitem.search = function() {
    this.getProductByPageIndex(1);
};
shophomeitem.getProductByPageIndex = function(pageIndex) {
    if (shophomeitem.action == 'add' && shophomeitem.items == 0) {
        shophomeitem.items = $("#addItem").find("input[name=itemIds]").val().trim().split(',');
    }
    else if (shophomeitem.action == 'edit' && shophomeitem.items == 0) {
        shophomeitem.items = $("#editItem").find("input[name=itemIds]").val().trim().split(',');
    }
    var itemSearch = new Object();
    // Tìm theo id
    itemSearch.id = $('input[name=itemId]').val().trim();
    // Số trang cần lấy
    itemSearch.pageIndex = pageIndex;
    itemSearch.pageSize = 5;
    // Tìm theo tên sản phẩm
    itemSearch.keyword = $('input[name=itemKeyword]').val().trim();
    ajax({
        service: '/item/search.json',
        loading: false,
        data: itemSearch,
        type: 'GET',
        done: function(resp) {
            if (resp.success) {
                var firstTr = '<tr><th colspan="3"><div class="checkbox-inline"><label class="control-label"><input type="checkbox" name="checkItems"/> Chọn tất cả</label></div></th></tr>';
                $(".tblSelectItem").html(firstTr);
                $("input[name=checkItems]").change(function() {
                    if ($(this).is(":checked")) {
                        $("input[for=itemcheckall]").attr("checked", 'checked');
                        $("input[for=itemcheckall]").each(function() {
                            if ($(this).is(':checked'))
                                $('button[for=' + $(this).val() + "]").attr('disabled', 'disabled');
                            var index = shophomeitem.items.indexOf(" " + $(this).val());
                            var index2 = shophomeitem.items.indexOf($(this).val());
                            if (index == -1 && index2 == -1) {
                                shophomeitem.items.push($(this).val());
                            }
                        });
                    } else {
                        $("input[for=itemcheckall]").attr("checked", false);
                        $("input[for=itemcheckall]").each(function() {
                            if ($(this).not(':checked'))
                                $('button[for=' + $(this).val() + "]").removeAttr('disabled');
                            var index = shophomeitem.items.indexOf(" " + $(this).val());
                            var index2 = shophomeitem.items.indexOf($(this).val());
                            //alert(+index + ":" + index2);
                            if (index > -1) {
                                shophomeitem.items.splice(index, 1);
                            } else if (index2 > -1) {
                                shophomeitem.items.splice(index2, 1);
                            }
                        });
                    }
                    //console.log(shophomeitem.items);
                });
                var products = resp.data.products;
                var productsLength = products.length;
                if (productsLength == 0) {
                    var html = '<tr><td colspan="3">Không có sản phẩm !</td></tr>';
                    $(".tblSelectItem").append(html);
                } else {
                    var checked = '';
                    var disabled = '';
                    var tr = '';
                    var checkTotal = 0; // biến này để kiểm tra xem tất cả sản phẩm có được check hết không ?
                    // nếu check hết thì check luôn nút checkAll
                    for (var i = 0; i < products.length; i++) {
                        checked = (shophomeitem.items.indexOf(products[i].id) != -1 || shophomeitem.items.indexOf(" " + products[i].id) != -1) ? 'checked="checked"' : '';
                        disabled = (shophomeitem.items.indexOf(products[i].id) != -1 || shophomeitem.items.indexOf(" " + products[i].id) != -1) ? 'disabled="disabled"' : '';
                        if (checked == 'checked="checked"')
                            checkTotal++;
                        tr = '<tr>' +
                                '<td width="2%"><input onclick="shophomeitem.checkItem(this)" type="checkbox" ' + checked + ' value=' + products[i].id + ' for="itemcheckall"></td>' +
                                '<td><div class="table-content-inner">' +
                                '<div class="img-product-bill-small"><img src="' + products[i].images[0] + '" alt="Chưa có ảnh"></div>' +
                                '<p>' + products[i].name + '</p>' +
                                '<p>Mã SP: ' + products[i].id + '</p>' +
                                '</div></td>' +
                                '<td width="15%"><button type="button" for="' + products[i].id + '" onclick="shophomeitem.selectSingle(' + products[i].id + ')" ' + disabled + ' class="btn btn-default btn-block btn-sm">Chọn</button></td>' +
                                '</tr>';
                        $(".tblSelectItem").append(tr);
                    }
                    if (checkTotal == 5) {
                        $("input[name=checkItems]").attr('checked', true);
                    }
                }
                var dataPage = resp.data.dataPage;
                var itemSearch = resp.data.itemSearch;
                // Phân trang sản phẩm
                $("#pagination").html("");
                if (dataPage.pageCount > 1) {
                    var display = 3;
                    var begin = 0;
                    var end = 0;
                    if (itemSearch.pageIndex != 0) {
                        $("#pagination").append('<li><a href="javascript:;" onclick="shophomeitem.pagination(1)">«</a></li>');
                        begin = itemSearch.pageIndex;
                        end = begin + 2;
                    } else {
                        begin = 1;
                        if ((begin + 2) > dataPage.pageCount)
                            end = begin + 1;
                        else
                            end = begin + 2;
                    }
                    if (itemSearch.pageIndex + 1 == dataPage.pageCount) {
                        if (itemSearch.pageIndex == 1) {
                            begin = dataPage.pageCount - display + 2;
                        }
                        if (itemSearch.pageIndex != 1)
                            begin = dataPage.pageCount - display + 1;
                        end = dataPage.pageCount;
                    }
                    for (var j = begin; j <= end; j++) {
                        var active = (itemSearch.pageIndex + 1) == j ? 'active' : '';
                        var link = '<li class="' + active + '"><a href="javascript:;" onclick="shophomeitem.pagination(' + j + ')">' + j + '</a></li>';
                        $("#pagination").append(link);
                    }
                    if (itemSearch.pageIndex + 1 != end) {
                        $("#pagination").append('<li><a href="javascript:;" onclick="shophomeitem.pagination(' + dataPage.pageCount + ')">»</a></li>');
                    }
                }
            }
            else {
                popup.msg(resp.message);
            }
        }
    });
};
shophomeitem.selectItem = function() {
    if (shophomeitem.action == "edit") {
        // clear item cu
        shophomeitem.items.length = 0;
        shophomeitem.items = $("#editItem").find("input[name=itemIds]").val().trim().split(',');
    }
    popup.open('popup-select-product', 'Chọn sản phẩm', template('/user/tpl/selectitem.tpl'));
    this.getProductByPageIndex(1);
};

shophomeitem.selectSingle = function(item) {
    var index = shophomeitem.items.indexOf(item);
    var index2 = shophomeitem.items.indexOf(" " + item);
    if (index > -1 || index2 > -1) {
        popup.msg("Sản phẩm này đã được chọn, vui lòng chọn sản phẩm khác.");
    } else {
        shophomeitem.items.push(item);
        var ids = '';
        for (var i = 0; i < shophomeitem.items.length; i++) {
            if (shophomeitem.items[i] !== '') {
                ids += shophomeitem.items[i] + ',';
            }
        }
        if (shophomeitem.action == 'add') {
            $("#addItem").find("input[name=itemIds]").val(ids);
        } else {
            $("#editItem").find("input[name=itemIds]").val(ids);
        }
        popup.close('popup-select-product');
    }
};
shophomeitem.selectAll = function() {
    var ids = '';
    var itemsLength = shophomeitem.items.length;
    if (itemsLength <= 1) {
        popup.msg('Vui lòng chọn ít nhất 1 sản phẩm !');
    } else {
        for (var i = 0; i < itemsLength; i++) {
            if (shophomeitem.items[i] !== '') {
                ids += shophomeitem.items[i].trim() + ',';
            }
        }
        if (shophomeitem.action == 'add') {
            $("#addItem").find("input[name=itemIds]").val(ids);
        } else {
            $("#editItem").find("input[name=itemIds]").val(ids);
        }
        popup.close('popup-select-product');
    }
};
shophomeitem.changeActive = function(itemId) {
    var text = $("td." + itemId).text().trim();
    var newText = (text == "Hiển thị" ? "Hiện" : "Ẩn");
    var iconClass = (text == "Hiển thị" ? "ban-circle icon-hidden" : "ok visited");
    var html = "<span class='glyphicon glyphicon-" + iconClass + "'></span> " + (text == "Hiển thị" ? "Không hiển thị" : "Hiển thị");
    ajax({
        service: '/shophomeitem/changeactive.json',
        loading: false,
        data: {id: itemId},
        type: 'GET',
        done: function(resp) {
            if (resp.success) {
                $("td." + itemId).html(html);
                $("td." + itemId).next("td").find(".btn-active").text(newText);
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
shophomeitem.remove = function(itemId) {
    popup.confirm("Bạn thực sự muốn xóa box sản phẩm này ?", function() {
        ajax({
            service: '/shophomeitem/remove.json',
            loading: false,
            data: {id: itemId},
            type: 'GET',
            done: function(resp) {
                if (resp.success) {
                    $("td." + itemId).parent("tr").remove();
                    popup.msg(resp.message);
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
shophomeitem.getSelectedItems = function() {
    var listItems = new Array();
    $('input[for=checkallbox]:checked').each(function(i, el) {
        listItems.push($(el).attr("value"));
    });
    return listItems;
};
shophomeitem.removes = function() {
    var items = this.getSelectedItems();
    if (items.length == 0) {
        popup.msg("Vui lòng chọn ít nhất 1 box sản phẩm.");
    } else {
        popup.confirm("Bạn thực sự muốn xóa các box sản phẩm này ?", function() {
            ajax({
                service: '/shophomeitem/removes.json',
                loading: false,
                data: {ids: JSON.stringify(items)},
                type: 'GET',
                done: function(resp) {
                    if (resp.success) {
                        $("input[for=checkall]:checked").each(function(i, el) {
                            $("td." + items[i]).parent("tr").remove();
                        });
                        popup.msg(resp.message);
                    } else {
                        popup.msg(resp.message);
                    }
                }
            })
        });
    }
};
shophomeitem.edit = function(itemId) {
    $("#addItem").find("input[name=itemIds]").val("");
    shophomeitem.action = 'edit';
    var shopHomeItem = new Object();
    shopHomeItem.id = itemId;
    shopHomeItem.icon = $('td.' + itemId).parent('tr').children('td[for=icon]').text().trim();
    shopHomeItem.name = $('td.' + itemId).parent('tr').children('td[for=name]').text().trim();
    shopHomeItem.itemIds = $('td.' + itemId).parent('tr').children('td[for=itemIds]').text().trim();
    // clear item cu
    shophomeitem.items.length = 0;
    shophomeitem.items = shopHomeItem.itemIds.split(',');
    shopHomeItem.position = $('td.' + itemId).parent('tr').children('td[for=position]').text().trim();
    popup.open('popup-edit', 'Sửa box sản phẩm', template('/user/tpl/editshophomeitem.tpl', {item: shopHomeItem}), [
        {
            title: 'Lưu lại',
            style: 'btn-success',
            fn: function() {
                var data = new Object();
                data.id = itemId;
                data.icon = $("#editItem input[name=icon]").val();
                data.name = $("#editItem input[name=name]").val();
                data.itemIds = $("#editItem input[name=itemIds]").val().split(",");
                data.position = $("#editItem input[name=position]").val();
                var valid = true;
                if ($("#editItem input[name=itemIds]").val() == '') {
                    $('#editItem input[name=itemIds]').parents('.form-group').addClass('has-error');
                    $('#editItem input[name=itemIds]').after('<span class="help-block" for="error">Id sản phẩm không được trống</span>');
                    valid = false;
                }
                if (data.position == '') {
                    $('#editItem input[name=position]').parents('.form-group').addClass('has-error');
                    $('#editItem input[name=position]').after('<span class="help-block" for="error">Thứ tự box không được trống</span>');
                    valid = false;
                }
                if (valid) {
                    ajax({
                        service: '/shophomeitem/edit.json',
                        loading: false,
                        data: data,
                        type: 'post',
                        contentType: 'json',
                        done: function(resp) {
                            if (resp.success) {
                                popup.close('popup-edit');
                                popup.msg(resp.message);
                                setTimeout(function() {
                                    location.reload();
                                }, 3000);
                            } else {
                                $.each(resp.data, function(type, value) {
                                    $('#editItem input[name=' + type + ']').parents('.form-group').addClass('has-error');
                                    $('#editItem input[name=' + type + ']').after('<span class="help-block" for="error">' + value + '</span>');
                                });
                            }
                        }
                    });
                }
            }
        },
        {
            title: 'Hủy',
            style: 'btn-default',
            fn: function() {
                popup.close('popup-edit');
                $("input[name=icon]").val("");
                $("#addItem").find("input[name=itemIds]").val("");
            }
        }
    ]);
};
// su kien goi khi check/uncheck san pham
shophomeitem.checkItem = function(item) {
    if ($(item).is(':checked')) {
        $('button[for=' + $(item).attr('value') + "]").attr('disabled', 'disabled');
        var index = shophomeitem.items.indexOf($(item).attr('value'));
        var index2 = shophomeitem.items.indexOf(" " + $(item).attr('value'));
        if (index == -1 && index2 == -1) {
            shophomeitem.items.push($(item).attr('value'));
        }
    } else {
        $('button[for=' + $(item).attr('value') + "]").removeAttr('disabled');
        var index = shophomeitem.items.indexOf($(item).attr('value'));
        var index2 = shophomeitem.items.indexOf(" " + $(item).attr('value'));
        if (index > -1) {
            shophomeitem.items.splice(index, 1);
        } else if (index2 > -1) {
            shophomeitem.items.splice(index2, 1);
        }
    }
};
