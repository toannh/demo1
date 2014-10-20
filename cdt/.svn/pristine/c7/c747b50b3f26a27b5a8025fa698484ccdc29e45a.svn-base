sellercustomer = {};
sellercustomer.xeng = 0;
sellercustomer.init = function() {
    $('.alpha-search a').click(function() {
        var cname = $(this).attr('for');
        sellercustomer.searchCharN(cname);
    });
    $("#uploadExcel").click(function() {
        $("#fileData").click();
    });
    var fags = true;
    $('input[for=checkall]').each(function() {

        var cuIds = sellercustomer.readCookie("itemList");
        if (typeof cuIds != 'undefined') {
            var input = this;
            $.each(cuIds, function() {
                $("input[for=checkall][value=" + this + "]").attr("checked", true);
            });
            if (!$(this).is(":checked")) {
                fags = false;
            }
        }
        var cuIdsHide = sellercustomer.readCookie("itemListHide");
        if (typeof cuIdsHide != 'undefined') {
            var input = this;
            $.each(cuIdsHide, function() {
                $("input[for=checkall][value=" + this + "]").attr("checked", true);
                $("input[for=checkall][value=" + this + "]").attr("disabled", "disabled");
                $("input[for=checkall][value=" + this + "]").removeAttr("for");
            });
        }
    });
    if (fags) {
        $("input[name=checkall]").attr("checked", true);
    }

    var cuIds = sellercustomer.readCookie("itemList");

    if (typeof cuIds == 'undefined' || cuIds == '') {
        cuIds = [];
    }
    $('input[for=checkall]').each(function() {

        $(this).change(function() {
            var id = $(this).val();
            if ($(this).is(":checked")) {
                if (cuIds.indexOf(id) === -1) {
                    cuIds.push(id);
                }
            } else {
                var index = cuIds.indexOf(id);
                if (index > -1) {
                    cuIds.splice(index, 1);
                }
            }
            sellercustomer.setCookie("itemList", cuIds, 30);
        });

    });

    $("input[name=checkall]").change(function() {
        var listItems = new Array();
        if ($(this).is(":checked")) {
            $("input[for=checkall]").attr("checked", true);
            $('input[for=checkall]:checked').each(function(i, el) {
                listItems.push($(el).attr("value"));
            });
            for (var i = 0; i < listItems.length; i++) {
                cuIds.push(listItems[i]);
            }
        } else {
            $("input[for=checkall]").attr("checked", false);
            $('input[for=checkall]').each(function(i, el) {
                listItems.push($(el).attr("value"));
            });
            for (var i = 0; i < listItems.length; i++) {
                cuIds.push(listItems[i]);
                var index = cuIds.indexOf(listItems[i]);
                if (index > -1) {
                    cuIds.splice(index);
                }
            }
        }
        sellercustomer.setCookie("itemList", sellercustomer.removeDuplicatesGetCopy(cuIds), 30);
    });
    if (typeof marketing !== "undefined" && marketing !== null && marketing !== 'null') {
        ajax({
            service: '/sellercustomer/listcustomerids.json',
            data: {idMarketingEmail: marketing},
            loading: false,
            done: function(resp) {
                if (resp.success) {
                    sellercustomer.setCookie("itemList", null, -30);
                    sellercustomer.setCookie("itemListHide", null, -30);
                    sellercustomer.setCookie("itemListHide", resp.data, 30);
                    $('input[for=checkall]').each(function() {
                        var cuIdsHide = sellercustomer.readCookie("itemListHide");
                        if (typeof cuIdsHide != 'undefined') {
                            var input = this;
                            $.each(cuIdsHide, function() {
                                $("input[for=checkall][value=" + this + "]").attr("checked", true);
                                $("input[for=checkall][value=" + this + "]").attr("disabled", "disabled");
                            });
                        }

                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });

    }
    $('ul.popmenu-ul li.green-li').click(function() {
        sellercustomer.setCookie("itemList", null, -30);
        sellercustomer.setCookie("itemListHide", null, -30);

    });
};
sellercustomer.countCharacters = function() {
    var count = 170 - eval($('textarea[name=mess]').val().trim().length);
    $('#countChar').html('Số ký tự còn lại:' + count);
};
sellercustomer.getSelectedItems = function() {
    var listItems = new Array();
    $('input[for=checkall]:checked').each(function(i, el) {
        listItems.push($(el).attr("value"));
    });
    if (listItems != null) {
        sellercustomer.setCookie("itemList", listItems, 30);
    }
    return listItems;
};
sellercustomer.removeDuplicatesGetCopy = function(arr) {
    var ret, len, i, j, cur, found;
    ret = [];
    len = arr.length;
    for (i = 0; i < len; i++) {
        cur = arr[i];
        found = false;
        for (j = 0; !found && (j < len); j++) {
            if (cur === arr[j]) {
                if (i === j) {
                    ret.push(cur);
                }
                found = true;
            }
        }
    }
    return ret;
};
sellercustomer.readCookie = function(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    var arr = [];
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ')
            c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) {
            arr.push(c.substring(nameEQ.length, c.length));
        }
        //return c.substring(nameEQ.length, c.length);
    }
    var list = [];
    if (typeof (arr[0] != 'undefined') && arr[0] != null) {
        var xxx = arr[0].split(',');
        for (var i = 0; i < xxx.length; i++) {
            list.push(xxx[i]);
        }
    }

    return list;

}
sellercustomer.setCookie = function(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
};

sellercustomer.activeNow = function() {
    popup.confirm("Bạn có chắc chắn muốn kích hoạt chức năng này?", function() {
        ajax({
            service: '/sellercustomer/activenow.json',
            type: 'POST',
            done: function(resp) {
                if (resp.success) {
                    popup.msg("Kích hoạt thành công chức năng sms và email marketing", function() {
                        window.location.reload();
                    });
                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });

};
sellercustomer.del = function(id) {
    popup.confirm("Bạn có chắc chắn muốn xóa khách hàng này?", function() {
        ajax({
            service: '/sellercustomer/del.json',
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
sellercustomer.removeall = function() {
    var cuIds = sellercustomer.readCookie("itemList");
    var cout = 0;
    if (typeof cuIds === 'undefined' || cuIds === '') {
        cout = 0;
    } else {
        cout = cuIds.length;
    }

    if (cout !== null && cout > 0) {
        popup.confirm("Bạn có chắc chắn muốn xóa khách hàng này?", function() {
            ajax({
                service: '/sellercustomer/removeall.json',
                data: cuIds,
                type: 'post',
                contentType: 'json',
                done: function(resp) {
                    if (resp.success) {
                        popup.msg(resp.message);
                        sellercustomer.setCookie("itemList", null, -30);
                        sellercustomer.setCookie("itemListHide", null, -30);
                        setTimeout(function() {
                            window.location.reload();
                        }, 2000);
                    } else {
                        popup.msg(resp.message);
                    }
                }
            });
        });
    } else {
        popup.msg("Bạn chưa chọn khách hàng nào");
    }

};

sellercustomer.listcustomerselect = function() {
    ajax({
        service: '/sellercustomer/getxeng.json',
        loading: false,
        done: function(rep) {
            if (rep.success) {
                sellercustomer.xeng = rep.data.xeng;
                popup.open('popup-select-product', 'Danh sách khách hàng', template('/user/tpl/listcustomersendemail.tpl', {xeng: sellercustomer.xeng}), [
                    {
                        title: 'Xong',
                        style: 'btn-default',
                        fn: function() {
                            popup.close('popup-select-product');
                        }
                    }
                ]);
                sellercustomer.getCustomerByPageIndex(1);
            }
        }
    });
};
sellercustomer.pagination = function(pageIndex) {
    sellercustomer.getCustomerByPageIndex(pageIndex);
}
sellercustomer.mathXeng = function(nameCookie) {
    //Tinh xeng email
    var price = 0;
    if (typeof smsMarketing !== 'undefined' && smsMarketing > 0) {
        price = 100;
    } else {
        price = 20;
    }
    var value = sellercustomer.readCookie(nameCookie);
    var dem = 0;
    if (typeof value == 'undefined' || value == '') {
        dem = 0;
    } else {
        dem = value.length;
    }
    return dem * price;
}
sellercustomer.getCustomerByPageIndex = function(pageIndex) {
    var customerSearch = new Object();
    // Số trang cần lấy
    customerSearch.pageIndex = pageIndex;
    ajax({
        service: '/sellercustomer/listcustomerbyseller.json',
        loading: false,
        data: customerSearch,
        type: 'GET',
        done: function(resp) {
            if (resp.success) {
                var customer = resp.data.data;
                var customerLength = customer.length;

                var firstTr = '<tr class="warning"><th width="5%" align="center" valign="top"><div class="text-center"><input type="checkbox" name="checkallCustomers"></div></th><th width="95%" valign="top"><strong>Chọn tất cả khách hàng (Đã chọn <span class="countSelect"></span>/' + resp.data.dataCount + ' khách hàng)</strong></th></tr>';
                $(".tblSelectCustomer").html(firstTr);
                if (customerLength == 0) {
                    var html = '<tr><td colspan="3">Không tồn tại khách hàng nào !</td></tr>';
                    $(".tblSelectItem").append(html);
                } else {
                    var checked = '';
                    var disabled = '';
                    var tr = '';
                    var checkTotal = 0; // biến này để kiểm tra xem tất cả sản phẩm có được check hết không ?
                    // nếu check hết thì check luôn nút checkAll
                    for (var i = 0; i < customer.length; i++) {
                        if (checked == 'checked="checked"')
                            checkTotal++;
                        tr = '<tr>' +
                                '<td valign="top" align="center"><div class="text-center"><input type="checkbox" for="checkallCustomers" value="' + customer[i].id + '"></div></td>' +
                                '<td>' +
                                '<div class="table-content-inner">' +
                                '<p><strong>' + customer[i].name + '</strong></p>' +
                                '<p><strong>Tel:</strong> ' + customer[i].phone + '   |   <strong>Email:</strong> ' + customer[i].email + '</p>' +
                                '<p> ' + customer[i].address + '</p>' +
                                '</div>' +
                                '</td>' +
                                '</tr>';
                        $(".tblSelectCustomer").append(tr);
                    }

                }
                var dataPage = resp.data;
                var itemSearch = {};
                itemSearch = customerSearch;
                // Phân trang sản phẩm
                $("#pagination").html("");
                if (dataPage.pageCount > 1) {
                    var display = 3;
                    var begin = 0;
                    var end = 0;

                    if (dataPage.pageIndex != 0) {
                        $("#pagination").append('<li><a href="javascript:;" onclick="sellercustomer.pagination(1)">«</a></li>');
                        begin = dataPage.pageIndex;
                        end = begin + 2;
                    } else {
                        begin = 1;
                        if ((begin + 2) > dataPage.pageCount)
                            end = begin + 1;
                        else
                            end = begin + 2;
                    }
                    if (dataPage.pageIndex + 1 == dataPage.pageCount) {
                        if (dataPage.pageIndex == 1) {
                            begin = dataPage.pageCount - display + 2;
                        }
                        if (dataPage.pageIndex != 1)
                            begin = dataPage.pageCount - display + 1;
                        end = dataPage.pageCount;
                    }
                    for (var j = begin; j <= end; j++) {
                        var active = (dataPage.pageIndex + 1) == j ? 'active' : '';
                        var link = '<li class="' + active + '"><a href="javascript:;" onclick="sellercustomer.pagination(' + j + ')">' + j + '</a></li>';
                        $("#pagination").append(link);
                    }
                    if (dataPage.pageIndex + 1 != end) {
                        $("#pagination").append('<li><a href="javascript:;" onclick="sellercustomer.pagination(' + dataPage.pageCount + ')">»</a></li>');
                    }
                }
                var fag = true;

                $('input[for=checkallCustomers]').each(function() {
                    var customersIds = sellercustomer.readCookie("itemListCustomers");
                    if (typeof customersIds != 'undefined') {
                        var input = this;
                        $.each(customersIds, function() {
                            $("input[for=checkallCustomers][value=" + this + "]").attr("checked", true);
                        });
                    }
                    if (!$(this).is(":checked")) {
                        fag = false;
                    }
                });
                if (fag) {
                    $("input[name=checkallCustomers]").attr('checked', true);
                }


                var customersIds = sellercustomer.readCookie("itemListCustomers");
                if (typeof customersIds == 'undefined' || customersIds == '') {
                    customersIds = [];
                }
                $('input[for=checkallCustomers]').each(function() {
                    $(this).change(function() {
                        var id = $(this).val();
                        if ($(this).is(":checked")) {
                            if (customersIds.indexOf(id) === -1) {
                                customersIds.push(id);
                            }
                        } else {
                            var index = customersIds.indexOf(id);
                            if (index > -1) {
                                customersIds.splice(index, 1);
                            }
                        }

                        var dem = 0;
                        if (typeof customersIds == 'undefined' || customersIds == '') {
                            dem = 0;
                        } else {
                            dem = customersIds.length;
                        }
                        $('.countSelect').html(dem);
                        sellercustomer.setCookie("itemListCustomers", sellercustomer.removeDuplicatesGetCopy(customersIds), 30);
                        $('.xengTotal').html(sellercustomer.mathXeng('itemListCustomers') + " xèng");
                    });

                });

                $("input[name=checkallCustomers]").change(function() {
                    var listItems = new Array();
                    if ($(this).is(":checked")) {
                        $("input[for=checkallCustomers]").attr("checked", true);
                        $('input[for=checkallCustomers]:checked').each(function(i, el) {
                            listItems.push($(el).attr("value"));
                        });
                        for (var i = 0; i < listItems.length; i++) {
                            customersIds.push(listItems[i]);
                        }
                    } else {
                        $("input[for=checkallCustomers]").attr("checked", false);
                        $('input[for=checkallCustomers]').each(function(i, el) {
                            listItems.push($(el).attr("value"));
                        });
                        for (var i = 0; i < listItems.length; i++) {
                            customersIds.push(listItems[i]);
                            var index = customersIds.indexOf(listItems[i]);
                            if (index > -1) {
                                customersIds.splice(index);
                            }
                        }
                    }

                    sellercustomer.setCookie("itemListCustomers", sellercustomer.removeDuplicatesGetCopy(customersIds), 30);
                    $('.xengTotal').html(sellercustomer.mathXeng('itemListCustomers') + " xèng");
                    var dem = 0;
                    if (typeof customersIds == 'undefined' || customersIds == '') {
                        dem = 0;
                    } else {
                        dem = customersIds.length;
                    }
                    $('.countSelect').html(dem);

                });
                var dem = 0;
                if (typeof customersIds == 'undefined' || customersIds == '') {
                    dem = 0;
                } else {
                    dem = customersIds.length;
                }
                $('.countSelect').html(dem);
                $('.xengTotal').html(sellercustomer.mathXeng('itemListCustomers') + " xèng");

            }
            else {
                popup.msg(resp.message);
            }
        }
    });
};

sellercustomer.sendSMS = function(customerIds) {
    var cout = 0;

    if (customerIds !== null && customerIds !== '' && customerIds !== undefined) {
        var listItems = new Array();
        listItems.push(customerIds);
        cout = listItems.length;

        sellercustomer.setCookie("itemList", null, -30);
        sellercustomer.setCookie("itemList", listItems, 30);
    } else {
        var cuIds = sellercustomer.readCookie("itemList");
        if (typeof cuIds == 'undefined' || cuIds == '') {
            cout = 0;
        } else {
            cout = cuIds.length;
        }
    }
    var value = sellercustomer.readCookie('itemList');
    var xeng = 0;
    var costs = 0;
    if (cout !== null && cout > 0) {
        ajax({
            service: '/sellercustomer/getxengsms.json',
            loading: false,
            done: function(rep) {
                if (rep.success) {
                    xeng = rep.data.xeng;
                    costs = rep.data.costs * cout;
                }
            }
        });
        ajax({
            service: '/sellercustomer/getlistsendsms.json',
            done: function(resp) {
                if (resp.success) {
                    popup.open('popup-add', 'Gửi SMS', template('/user/tpl/sendsmscustomer.tpl', {resp: resp, cout: cout, costs: costs, xeng: xeng}), [
                        {
                            title: 'Đồng ý trả',
                            style: 'btn-primary',
                            fn: function() {
                                var smslMarketing = {};
                                smslMarketing.id = $('select[name=sellerSMSMarketing]').val();
                                smslMarketing.phone = value;
                                smslMarketing.name = $("input[name=nameSMS]").val();
                                smslMarketing.content = $("textarea[name=content]").val();
                                if ($('input[name=checkTime]').attr('checked')) {
                                    smslMarketing.sendTime = new Date().getTime() + 60000;
                                } else {
                                    smslMarketing.sendTime = $("input[name=sendTime]").val();
                                }
                                $('label[for=name]').parent('.form-group').removeClass(' has-error');
                                $('label[for=sendTime]').parent('.form-group').removeClass(' has-error');
                                $('label[for=content]').parent('.form-group').removeClass(' has-error');
                                $('span[for=name]').html('');
                                $('span[for=sendTime]').html('');
                                $('span[for=content]').html('');
                                ajax({
                                    service: '/emailmarketing/createsms.json',
                                    data: smslMarketing,
                                    contentType: 'json',
                                    type: 'POST',
                                    done: function(resp) {
                                        if (resp.success) {
                                            popup.msg("Bạn đã thanh thanh toán thành công", function() {
                                                sellercustomer.setCookie("itemList", null, -30);
                                                location.reload();
                                            });
                                        } else {
                                            $.each(resp.data, function(type, value) {
                                                $('label[for=' + type + ']').parent('.form-group').addClass(' has-error');
                                                $('span[for=' + type + ']').html(value);
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
                                popup.close('popup-add');
                            }
                        }
                    ], 'modal-lg');
                } else {
                    popup.msg(resp.message);
                }
                $(".timesend").timeSelect();
            }
        });
    } else {
        popup.msg("Bạn chưa chọn khách hàng nào");
    }
};
sellercustomer.sendEmail = function(customerIds) {
    var cout = 0;

    if (customerIds !== null && customerIds !== '' && customerIds !== undefined) {
        var listItems = new Array();
        listItems.push(customerIds);
        cout = listItems.length;

        sellercustomer.setCookie("itemList", null, -30);
        sellercustomer.setCookie("itemList", listItems, 30);
    } else {
        var cuIds = sellercustomer.readCookie("itemList");
        if (typeof cuIds == 'undefined' || cuIds == '') {
            cout = 0;
        } else {
            cout = cuIds.length;
        }
    }

    var value = sellercustomer.readCookie('itemList');
    var xeng = 0;
    var costs = 0;
    if (cout !== null && cout > 0) {
        ajax({
            service: '/sellercustomer/getxeng.json',
            loading: false,
            done: function(rep) {
                if (rep.success) {
                    xeng = rep.data.xeng;
                    costs = rep.data.costs * cout;
                }
            }
        });
        ajax({
            service: '/sellercustomer/getlistsendemail.json',
            done: function(resp) {
                if (resp.success) {
                    popup.open('popup-add', 'Gửi E-Mail', template('/user/tpl/sendemailcustomer.tpl', {resp: resp, cout: cout, costs: costs, xeng: xeng}), [
                        {
                            title: 'Gửi Email',
                            style: 'btn-danger',
                            fn: function() {
                                var emailMarketing = {};
                                var body = CKEDITOR.instances['txt_content'].getData();
                                emailMarketing.id = $('select[name=sellerEmailMarketing]').val();
                                emailMarketing.email = value;
                                emailMarketing.name = $("input[name=nameCustomer]").val();
                                emailMarketing.sendTime = $("input[name=sendDate]").val();
                                emailMarketing.content = body;
                                emailMarketing.template = $("select[name=template]").find(':selected').val();
                                ajax({
                                    service: '/emailmarketing/createemail.json',
                                    data: emailMarketing,
                                    contentType: 'json',
                                    type: 'POST',
                                    done: function(resp) {
                                        if (resp.success) {
                                            popup.msg(resp.message);
                                            sellercustomer.setCookie("itemList", null, -30);
                                            setTimeout(function() {
                                                window.location = baseUrl + "/user/custormers.html";
                                            }, 2000);
                                        } else {
                                            $(".form-group").removeClass("has-error");
                                            $(".help-block").html("");
                                            if (resp.data.name != null) {
                                                $("input[name=nameCustomer]").parents(".form-group").addClass("has-error");
                                                $("input[name=nameCustomer]").parent('div').children('.help-block').html("Tiêu đề email không được bỏ trống.");
                                            }
                                            if (resp.data.content != null) {
                                                $("textarea[name=content]").parents(".form-group").addClass("has-error");
                                                $("textarea[name=content]").parent('div').children('.help-block').html("Nội dung email không được bỏ trống.");
                                            }
                                            if (resp.data.sendDate != null) {
                                                $("input[name=sendDate]").parents(".form-group").addClass("has-error");
                                                $("input[name=sendDate]").parent('div').children('.help-block').html("Thời gian gửi phải lớn hơn thời gian hiện tại");
                                            }
                                            if ($("input[name=sendDate]").val() == '0') {
                                                $("input[name=sendDate]").parents(".form-group").addClass("has-error");
                                                $("input[name=sendDate]").parent('div').children('.help-block').html("Thời điểm gửi mail không được bỏ trống.");
                                            }
                                            if (cout <= 0 || cout == undefined) {
                                                $(".customers").parents(".form-group").addClass("has-error");
                                                $('.customers_error').show();
                                                $('.customers_error').html("Chưa chọn khách hàng nào");
                                            } else {
                                                $('.customers_error').hide();
                                            }
                                        }
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
                    ], 'modal-lg');
                } else {
                    popup.msg(resp.message);
                }
                $(".hasDatepicker").timeSelect();
                editor('txt_content');
            }
        });
    } else {
        popup.msg("Bạn chưa chọn khách hàng nào");
    }
};
sellercustomer.edit = function(id) {
    ajax({
        service: '/sellercustomer/get.json',
        data: {id: id},
        done: function(resp) {
            if (resp.success) {
                popup.open('popup-add', 'Sửa thông tin khách hàng', template('/user/tpl/detailsellercustomer.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function() {
                            var datas = {};
                            datas.id = $('#form-add input[name=id]').val();
                            datas.email = $('#form-add input[name=email]').val();
                            datas.address = $('#form-add input[name=address]').val();
                            datas.name = $('#form-add input[name=name]').val();
                            datas.phone = $('#form-add input[name=phone]').val();
                            ajax({
                                service: '/sellercustomer/edit.json',
                                data: datas,
                                contentType: 'json',
                                id: 'form-add',
                                type: 'POST',
                                done: function(rs) {
                                    if (rs.success) {
                                        popup.msg("Sửa khách hàng thành công");
                                        setTimeout(function() {
                                            window.location.reload();
                                        }, 1000);
                                    } else {
                                        $('#form-add input').parents('.form-group').removeClass('has-error');
                                        $('#form-add input').next('.help-block').remove();
                                        $.each(rs.data, function(key, value) {
                                            $('#form-add input[name=' + key + ']').parents('.form-group').removeClass('has-error');
                                            $('#form-add input[name=' + key + ']').next('.help-block').remove();
                                            $('#form-add input[name=' + key + ']').parents('.form-group').addClass('has-error');
                                            $('#form-add input[name=' + key + ']').after('<span class="help-block">' + rs.data[key] + '</span>');
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
                            popup.close('popup-add');
                        }
                    }
                ]);

            } else {
                popup.msg(resp.message);
            }
        }
    });
};
sellercustomer.add = function() {
    popup.open('popup-add', 'Thêm khách hàng', template('/user/tpl/detailsellercustomer.tpl'), [
        {
            title: 'Thêm mới',
            style: 'btn-primary',
            fn: function() {
                var datas = {};
                datas.email = $('#form-add input[name=email]').val();
                datas.address = $('#form-add input[name=address]').val();
                datas.name = $('#form-add input[name=name]').val();
                datas.phone = $('#form-add input[name=phone]').val();
                ajax({
                    service: '/sellercustomer/add.json',
                    data: datas,
                    contentType: 'json',
                    id: 'form-add',
                    type: 'POST',
                    done: function(rs) {
                        if (rs.success) {
                            popup.msg("Thêm khách hàng thành công");
                            setTimeout(function() {
                                window.location.reload();
                            }, 1000);
                        } else {
                            $('#form-add input').parents('.form-group').removeClass('has-error');
                            $('#form-add input').next('.help-block').remove();
                            $.each(rs.data, function(key, value) {
                                $('#form-add input[name=' + key + ']').parents('.form-group').removeClass('has-error');
                                $('#form-add input[name=' + key + ']').next('.help-block').remove();
                                $('#form-add input[name=' + key + ']').parents('.form-group').addClass('has-error');
                                $('#form-add input[name=' + key + ']').after('<span class="help-block">' + rs.data[key] + '</span>');
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
                popup.close('popup-add');
            }
        }
    ]);

};
sellercustomer.searchCharN = function(name) {
    var urlParams = sellercustomer.urlParam();
    if (name !== "") {
        urlParams.cname = name;
    } else {
        urlParams.cname = null;
    }
    var queryString = "";
    var i = 1;
    $.each(urlParams, function(key, val) {
        if (val != null) {
            if (i == 1)
                queryString += "?";
            else
                queryString += "&";
            queryString += key + "=" + val;
            i++;
        }
    });
    location.href = "/user/custormers.html" + queryString;
};
sellercustomer.search = function() {
    var urlParams = sellercustomer.urlParam();
    if ($("input[name=name]").val() !== "") {
        urlParams.name = $("input[name=name]").val();
    } else {
        urlParams.name = null;
    }
    if ($("input[name=emails]").val() !== "") {
        urlParams.email = $("input[name=emails]").val();
    } else {
        urlParams.email = null;
    }
    if ($("input[name=phone]").val() !== "") {
        urlParams.phone = $("input[name=phone]").val();
    } else {
        urlParams.phone = null;
    }
    if ($("select[name=option]").val() !== '' && $("select[name=option]").val() !== "0") {
        urlParams.option = $("select[name=option]").val();

    } else {
        urlParams.option = null;
    }
    var queryString = "";
    var i = 1;
    $.each(urlParams, function(key, val) {
        if (val != null) {
            if (i == 1)
                queryString += "?";
            else
                queryString += "&";
            queryString += key + "=" + val;
            i++;
        }
    });
    location.href = "/user/custormers.html" + queryString;
};
sellercustomer.urlParam = function() {
    var urlParams;
    var match,
            pl = /\+/g, // Regex for replacing addition symbol with a space
            search = /([^&=]+)=?([^&]*)/g,
            decode = function(s) {
                return decodeURIComponent(s.replace(pl, " "));
            },
            query = window.location.search.substring(1);
    urlParams = {};
    while (match = search.exec(query))
        urlParams[decode(match[1])] = decode(match[2]);
    return urlParams;
};

sellercustomer.uploadExcel = function() {
    ajaxUpload({
        service: '/sellercustomer/uploadexcel.json',
        id: 'fileBean',
        loading: false,
        done: function(resp) {
            if (resp.success) {
                popup.msg(resp.message, function() {
                    location.reload();
                });
            } else {
                popup.msg(resp.message);
                $("#fileData").val('');
            }
        }
    });
};