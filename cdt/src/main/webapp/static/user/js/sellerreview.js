sellerreview = {};
sellerreview.init = function() {
    sellerreview.loadOther(0);
    $('input[name=sellerId]').val('');
};
sellerreview.changeTabReview = function(fag, userId) {
    if (fag) {
        $('.reviewFalse').removeClass('active');
        $('.reviewTrue').addClass('active');
        $('input[name=sellerId]').val('');
        $('input[name=userIdReview]').val(userId);
        sellerreview.loadOther(0);
    } else {
        $('.reviewFalse').addClass('active');
        $('.reviewTrue').removeClass('active');
        $('input[name=userIdReview]').val('');
        $('input[name=sellerId]').val(userId);
        sellerreview.loadOther(0);
    }
};
sellerreview.loadOther = function(page) {
    var reviewSearch = {};
    reviewSearch.pageIndex = page;
    reviewSearch.pageSize = 10;
    if ($('input[name=sellerId]').val() === '') {
        reviewSearch.userId = $('input[name=userIdReview]').val();
    } else {
        reviewSearch.sellerId = $('input[name=sellerId]').val();
    }
    ajax({
        service: '/itemreview/itemreviewsearchs.json',
        data: reviewSearch,
        loading: false,
        type: 'get',
        done: function(resp) {
            if (resp.success) {
                var data = resp.data['reviewPage'];
                var items = resp.data['items'];
                if (data.data.length > 0) {
                    var htmlReview = '';
                    htmlReview += '<div class="box-control">' +
                            '<label>Hiện <b>' + eval(data.pageIndex + 1) + '-' + data.pageCount + '</b> trong <b>' + data.dataCount + '</b> đánh giá</label>' +
                            '<ul class="pagination pull-right" id="pagination">' +
                            '</ul>' +
                            '</div><!-- box-control -->' +
                            '<div class="pu-review-title">' +
                            '<div class="row">' +
                            '<div class="col-sm-6">Nhận xét</div>' +
                            '<div class="col-sm-2">&nbsp;</div>' +
                            '<div class="col-sm-2">Người nhận xét</div>' +
                            '<div class="col-sm-2">Thời gian</div>' +
                            '</div><!-- row -->' +
                            '</div><!-- pu-review-title -->';
                    $('#showitemOther').html(htmlReview);
                    for (var i = 0; i < data.data.length; i++) {
                        $('#showitemOther').append(template('/user/tpl/listitemreview.tpl', {data: data.data[i], items: items}));
                    }
                    var dataPage = resp.data['reviewPage'];

                    // Phân trang sản phẩm
                    $("#pagination").html("");
                    if (dataPage.pageCount > 1) {
                        var display = 3;
                        var begin = 0;
                        var end = 0;
                        // alert(dataPage.pageIndex);
                        if (dataPage.pageIndex != 0) {
                            $("#pagination").append('<li><a href="javascript:;" onclick="sellerreview.loadOther(1)">«</a></li>');
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
                            var link = '<li class="' + active + '"><a href="javascript:;" onclick="sellerreview.loadOther(' + j + ')">' + j + '</a></li>';
                            $("#pagination").append(link);
                        }
                        if (dataPage.pageIndex + 1 != end) {
                            $("#pagination").append('<li><a href="javascript:;" onclick="sellerreview.loadOther(' + dataPage.pageCount + ')">»</a></li>');
                        }
                    }
                } else {
                    $('#showitemOther').html('<div class="cdt-message bg-danger text-center">Không tìm thấy đánh giá!</div>');
                    $('.others').html('');
                }
            }
        }
    });
};