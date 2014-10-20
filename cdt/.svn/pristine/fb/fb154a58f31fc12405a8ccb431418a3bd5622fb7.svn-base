(function($) {
    $.fn.timeSelect = function() {
        $(this).each(function() {
            var id = Math.floor(Math.random() * 1000000000);
            $(this).attr('id', '_' + id);
            $(this).after('<input type="text" placeholder="' + $(this).attr('placeholder') + '" class="form-control" id="' + id + '" size="' + $(this).attr('size') + '"/>');
            $('#' + id).datetimepicker({
                dateFormat: 'dd/mm/yy',
                showOptions: {direction: "down"}
            });
            if ($(this).val() === '') {
                $(this).val(0);
            }
            if ($(this).val() > 0) {
                var dd = new Date(parseInt($(this).val()));
                $('#' + id).val(dd.getDate() + '/' + (dd.getMonth() + 1) + '/' + dd.getFullYear() + ' ' + dd.getHours() + ":" + dd.getMinutes());
            }
            var input = $(this);
            $('#' + id).change(function() {
                try {
                    var mm = $(this).val().split(' ');
                    var dd = mm[0].split('/');
                    var hh = mm[1].split(':');
                    var date = new Date();
                    date.setFullYear(dd[2]);
                    date.setMonth(dd[1] - 1);
                    date.setDate(dd[0]);
                    date.setHours(hh[0], hh[1], 0, 0);
                    input.val(date.getTime());
                } catch (err) {
                    input.val(0);
                }
            });

            $(this).parents('form').bind('reset', function() {
                input.val(0);
            });
        });
    };
    $.fn.timeSelectPicker = function() {
        $(this).each(function() {
            var id = Math.floor(Math.random() * 1000000000);
            $(this).attr('id', '_' + id);
            $(this).after('<input type="text" placeholder="' + $(this).attr('placeholder') + '" class="form-control" id="' + id + '" size="' + $(this).attr('size') + '"/>');
            $('#' + id).timepicker({
                showOptions: {direction: "down"}
            });


            if ($(this).val() === '') {
                $(this).val(0);
            }

            if ($(this).val() > 0) {
                var dd = new Date(parseInt($(this).val()));
                $('#' + id).val(dd.getHours() + ":" + dd.getMinutes());
            }
            var input = $(this);
            $('#' + id).change(function() {
                try {
                    var mm = $(this).val();
                    var hh = mm.split(':');

                    var date = new Date();
                    date.setHours(hh[0], hh[1], 0, 0);
                    var timeH =((hh[0] * 60 * 60 * 1000) + (hh[1] * 60 * 1000));
                    input.val(timeH);  
                } catch (err) {
                    input.val(0);
                }
            });
           
            $(this).parents('form').bind('reset', function() {
                input.val(0);
            });
        });
    };
})(jQuery);
