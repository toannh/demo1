(function () {

    function Popup() {
        this.open = function (id, title, content, cmd, type, backdrop) {
            if ($('#' + id).length > 0) {
                $('#' + id).remove();
            }
            if (type === '') {
                type = '';
            }

            $('body:first').append('<div class="modal fade" id="' + id + '">\
                <div class="modal-dialog ' + type + '">\
                    <div class="modal-content">\
                        <div class="modal-header">\
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>\
                            ' + (title === null || title === '' ? '' : '<h4 class="modal-title">' + title + '</h4>') + '\
                        </div>\
                        <div class="modal-body">' + content + '</div>\
                        <div class="modal-footer"></div>\
                    </div>\
                </div>\
            </div>');
            $('#' + id + ' .close').click(function () {
                popup.close(id);
            });


            if (cmd) {
                for (var i = 0; i < cmd.length; i++) {
                    $('#' + id + ' .modal-footer').append('<button type="button" class="btn ' + cmd[i].style + '" id="' + 'popup-cmd-' + id + '-' + i + '">' + cmd[i].title + '</button>');
                    $('#' + 'popup-cmd-' + id + '-' + i).click(cmd[i].fn);
                }
            }

            var option = {};
            //pop backdrop
            if (typeof backdrop !== 'undefined' && backdrop == true) {
                option.backdrop = 'static';
            }
            $('#' + id).modal(option);

            $('body').keydown(function (e) {
                if (e.keyCode === 27) {
                    popup.close(id);
                }
            });
        };

        this.close = function (id) {
            $('#' + id).modal('hide');
            $('#' + id).remove();
            var hasModal = false;
            $('.modal').each(function () {
                if ($(this).is(":visible")) {
                    hasModal = true;
                }
            });
            if (!hasModal) {
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
            }
        };


        this.msg = function (msg, fn) {
            this.open('popup-msg', 'Thông báo', '<div style="min-width: 300px">' + msg + '</div>', [{
                    title: "Đồng ý",
                    style: "btn-primary",
                    fn: function () {
                        if (fn) {
                            fn();
                        }
                        popup.close('popup-msg');
                    }
                }]);
        };

        this.confirm = function (msg, fn) {
            this.open('popup-confirm', 'Xác nhận', '<div class="container" style="min-width: 300px">' + msg + '</div>', [{
                    title: "Đồng ý",
                    style: "btn-primary",
                    fn: function () {
                        fn();
                        popup.close('popup-confirm');
                    }
                }, {
                    title: 'Từ chối',
                    fn: function () {
                        popup.close('popup-confirm');
                    }
                }]);
        };
    }

    function Loading() {
        this.show = function () {
            if ($('#loading').length === 0) {
                var html = '<div id="loading" class="modal fade" style=" z-index:1100">\
                <div class="modal-dialog">\
                    <div class="modal-content">\
                        <div class="modal-body">\
                            <div class="loading">\
                                <div class="loading-img"></div><p>Vui lòng chờ trong giây lát!</p></div>\
                            </div>\
                        </div>\
                    </div>\
                </div>'
                $('body:first').append(html);
            }
            $('#loading').modal();
        };
        this.hide = function () {
            popup.close("loading");
        };
    }

    this.loading = new Loading();
    this.popup = new Popup();

})();