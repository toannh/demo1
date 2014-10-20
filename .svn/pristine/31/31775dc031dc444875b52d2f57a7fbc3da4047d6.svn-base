(function() {

    this.ajax = function(params) {
        params.loading = (typeof params.loading !== 'undefined' && !params.loading) ? false : true;
        if (params.contentType === 'json') {
            params.contentType = 'application/json; charset=utf-8';
            params.data = JSON.stringify(params.data);
        }

        params = $.extend({
            url: baseUrl + params.service,
            dataType: 'json',
            success: function(result) {
                if (params.loading)
                    loading.hide();
                params.done(result);
            },
            error: function() {
                if (params.loading)
                    loading.hide();
                popup.msg('Có lỗi xảy ra trong quá trình truyền dữ liệu!');
            }
        }, params);
        if (params.loading)
            loading.show();
        setTimeout(function() {
            $.ajax(params);
        }, 300);
    };

    this.ajaxSubmit = function(params) {
        for (var obj in CKEDITOR.instances) {
            CKEDITOR.instances[obj].updateElement();
        }
        var data = {};
        $('#' + params.id).find('input, select, textarea').each(function() {
            if ($(this).attr('type') === 'checkbox') {
                if ($(this).is(':checked')) {
                    data[$(this).attr('name')] = true;
                } else {
                    data[$(this).attr('name')] = false;
                }
            } else if ($(this).attr('type') === 'radio') {
                if ($(this).is(':checked')) {
                    data[$(this).attr('name')] = $(this).val();
                }
            }
            else {
                if ($(this).val() !== '' && typeof $(this).attr('name') != 'undefined') {
                    if (isNaN($(this).val())) {
                        data[$(this).attr('name')] = $(this).val();
                    } else {
                        data[$(this).attr('name')] = parseFloat($(this).val());
                    }
                }
            }
        });
        para = $.extend({
            success: function(result) {
                loading.hide();
                if (result.success) {
                    params.done(result);
                }
                else {
                    if (result.data) {
                        $('#' + params.id + ' input,#' + params.id + ' select,#' + params.id + ' textarea').each(function() {
                            $(this).parents('.form-group').removeClass('has-error');
                            $(this).next('.help-block').remove();
                            if ($(this).attr('name') && result.data[$(this).attr('name')]) {
                                $(this).parents('.form-group').addClass('has-error');
                                $(this).after('<span class="help-block">' + result.data[$(this).attr('name')] + '</span>');
                            }
                        });
                    }
                    if (result.message) {
                        popup.msg(result.message);
                    }
                }
            },
            service: params.service,
            type: 'post',
            data: data
        }, params);
        ajax(para);
    };

    this.ajaxUpload = function(params) {
        for (var obj in CKEDITOR.instances) {
            CKEDITOR.instances[obj].updateElement();
        }
        params.loading = (typeof params.loading !== 'undefined' && !params.loading) ? false : true;
        if (params.loading) {
            loading.show();
        }
        var action = baseUrl + params.service;
        if ($('#upload-iframe-submit').length > 0) {
            $('#upload-iframe-submit').remove();
        }
        $('body').append('<iframe id="upload-iframe-submit" name="upload-iframe-submit" style="display:none" />');
        $('#' + params.id).attr('target', 'upload-iframe-submit');
        $('#' + params.id).attr('action', action);
        $('#' + params.id).attr('method', 'post');
        $('#' + params.id).attr('enctype', 'multipart/form-data');
        $('#' + params.id).submit();
        $('#upload-iframe-submit').load(function() {
            try {
                var result = $('#upload-iframe-submit').contents().find('body');
                result = $.parseJSON(result.text());
                if (!result.success && result.message === '-signin') {
                    viewer = null;
                    document.location = '#auth';
                }
                setTimeout(function() {
                    if (params.loading) {
                        loading.hide();
                    }
                    if (result.success) {
                        params.done(result);
                    } else {
                        if (result.data) {
                            $('#' + params.id + ' input,#' + params.id + ' select,#' + params.id + ' textarea').each(function() {
                                $(this).parents('.form-group').removeClass('has-error');
                                $(this).next('.help-block').remove();
                                if ($(this).attr('name') && result.data[$(this).attr('name')]) {
                                    $(this).parents('.form-group').addClass('has-error');
                                    $(this).after('<span class="help-block">' + result.data[$(this).attr('name')] + '</span>');
                                }
                            });
                        }
                        if (result.message) {
                            popup.msg(result.message);
                        }
                    }
                }, 300);

            }
            catch (err) {
                if (params.loading) {
                    loading.hide();
                }
            }
        });
    };

    this.editor = function(id, options) {
        options = $.extend({autoGrow_maxHeight: 400}, options);
        setTimeout(function() {
            CKEDITOR.replace(id, options);
            CKEDITOR.on('instanceReady', function() {
            });
        }, 500);
    };
})();