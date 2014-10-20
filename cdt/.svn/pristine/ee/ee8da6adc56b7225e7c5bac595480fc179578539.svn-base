/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
cash = {};
cash.init = function () {
    layout.breadcrumb([
        ["Trang chủ", "/cp/index.html"],
        ["Quản trị xèng", "/cp/cash.html"],
        ["Danh sách tài khoản xèng"]
    ]);
    $('.timeselect').timeSelect();
    $('.cdt-tooltip').tooltip();
};
cash.paymentNL = function () {
    var obj = new Object();
    obj.amount = $("select[name=amount]").val();
    obj.spentQuantity = $("input[name=spentQuantity]").val();
    if (isNaN(obj.spentQuantity) || obj.spentQuantity <= 0) {
        obj.spentQuantity = 1;
    }
    ajax({
        service: '/cash/payment.json',
        data: obj,
        contentType: 'json',
        loading: false,
        type: 'post',
        done: function (resp) {
            if (resp.success) {
                document.location = resp.data;
            } else {
                popup.msg(resp.message);
            }
        }
    });
};

cash.changeAmount = function () {
    var amount = $("select[name=amount]").val();
    var spentQuantity = $("input[name=spentQuantity]").val();
    if (isNaN(spentQuantity) || spentQuantity <= 0) {
        spentQuantity = 1;
        $("input[name=spentQuantity]").val(1);
    }
    $('.totalAmount').html(cash.monnyFormat(amount * spentQuantity));
};

cash.monnyFormat = function (number, decimals, dec_point, thousands_sep) {
    number = (number + '').replace(/[^0-9+\-Ee.]/g, '');
    var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            sep = (typeof thousands_sep === 'undefined') ? '.' : thousands_sep,
            dec = (typeof dec_point === 'undefined') ? ',' : dec_point,
            s = '',
            toFixedFix = function (n, prec) {
                var k = Math.pow(10, prec);
                return '' + Math.round(n * k) / k;
            };
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
};


cash.exPortExcel = function () {
    var seftUrl = baseUrl + "/cp/cashtransaction/excel.html";
    urlParams = new Object();
    urlParams.id = $('input[name=id]').val();
    urlParams.startTime = ($('input[name=startTime]').val() == 0) ? null : $('input[name=startTime]').val();
    urlParams.endTime = ($('input[name=endTime]').val() == 0) ? null : $('input[name=endTime]').val();
    urlParams.userId = $('input[name=userId]').val();
    urlParams.email = $('input[name=email]').val();
    urlParams.transactionStatus = $('select[name=transactionStatus]').val();
    urlParams.type = $('select[name=type]').val();
    urlParams.status = $('select[name=status]').val();
    var queryString = "";
    var i = 1;
    $.each(urlParams, function (key, val) {
        if (val != null) {
            if (i == 1)
                queryString += "?";
            else
                queryString += "&";
            queryString += key + "=" + val;
            i++;
        }
    });
    location.href = seftUrl + queryString;
};
cash.exPortExcelBySeller = function () {
    var seftUrl = baseUrl + "/cp/cashtransaction/excelbyseller.html";
    urlParams = new Object();
    urlParams.id = $('input[name=id]').val();
    urlParams.startTime = ($('input[name=startTime]').val() == 0) ? null : $('input[name=startTime]').val();
    urlParams.endTime = ($('input[name=endTime]').val() == 0) ? null : $('input[name=endTime]').val();
    urlParams.userId = $('input[name=userId]').val();
    urlParams.email = $('input[name=email]').val();
    urlParams.type = $('select[name=type]').val();
    urlParams.transactionStatus = $('select[name=transactionStatus]').val();
    urlParams.status = $('select[name=status]').val();
    var queryString = "";
    var i = 1;
    $.each(urlParams, function (key, val) {
        if (val != null) {
            if (i == 1)
                queryString += "?";
            else
                queryString += "&";
            queryString += key + "=" + val;
            i++;
        }
    });
    location.href = seftUrl + queryString;
};
cash.addSupport = function (id) {
    popup.confirm("Bạn có chắc chắn muốn nhận chăm sóc giao dịch này?", function () {
        ajax({
            service: '/cpservice/cashtransaction/addsupport.json',
            data: {id: id},
            done: function (resp) {
                if (resp.success) {
                    popup.msg("Nhận chăm sóc thành công", function () {
                        var htmlSupport = '<button type="button" class="btn btn-default" onclick="cash.addNote(' + id + ')">'
                                + '<span class="glyphicon glyphicon-new-window"></span> Ghi chú'
                                + '</button>';
                        $('.cashtransaction' + id).html(htmlSupport);
                    });

                } else {
                    popup.msg(resp.message);
                }
            }
        });
    });
};
cash.addNote = function (id) {
    ajax({
        service: '/cpservice/cashtransaction/get.json',
        data: {id: id},
        done: function (resp) {
            if (resp.success) {
                popup.open('popup-add', 'Ghi chú chăm sóc', template('/cp/tpl/cashtransaction/note.tpl', resp), [
                    {
                        title: 'Lưu lại',
                        style: 'btn-primary',
                        fn: function () {
                            ajaxSubmit({
                                service: '/cpservice/cashtransaction/addnote.json',
                                id: 'add-note',
                                contentType: 'json',
                                done: function (rs) {
                                    if (rs.success) {
                                        popup.msg("Cập nhật ghi chú thành công", function () {
                                            popup.close('popup-add');
                                            var htmlSupport = '<button type="button" class="btn btn-default" onclick="cash.addNote(' + id + ')">'
                                                    + '<span class="glyphicon glyphicon-new-window"></span> Ghi chú'
                                                    + '</button><span class="badge badge-success cdt-tooltip" data-toggle="tooltip" data-placement="top" title="' + rs.data.note + '">!</span>';
                                            $('.cashtransaction' + id).html(htmlSupport);
                                            $('.cdt-tooltip').tooltip();
                                        });
                                    } else {
                                        popup.msg(resp.message);
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
            } else {
                popup.msg(resp.message);
            }
        }
    });
};
cash.updateNlTransactionId = function (id) {
    popup.open('popup-add', 'Cập nhật mã ngân lượng', template('/cp/tpl/cashtransaction/update_nl.tpl', {id: id}), [
        {
            title: 'Lưu lại',
            style: 'btn-primary',
            fn: function () {
                ajaxSubmit({
                    service: '/cpservice/cashtransaction/updatenlid.json',
                    id: 'add-note',
                    contentType: 'json',
                    done: function (rs) {
                        if (rs.success) {
                            popup.msg("Cập nhật mã ngân lượng thành công", function () {
                                popup.close('popup-add');
                                var htmlSupport = 'Nạp ngân lượng<p><a href="#">' + rs.data.nlTransactionId + '</a></p>';
                                $('.cashType' + id).html(htmlSupport);
                                $('.payment' + id).html('Đã thanh toán');
                                $('.newBalance' + id).html(rs.data.newBalance.toMoney(0, ',', '.'));
                                $('.cashColor' + id).removeClass('danger');
                                $('.cashColor' + id).addClass('success');
                            });
                        } else {
                            popup.msg(resp.message);
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