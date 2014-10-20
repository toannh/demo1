var realTime = {};
realTime.init = function (userId) {
    var pusher = new Pusher('d656a076aec85c984bff');
    var channel = pusher.subscribe(userId);
    channel.bind('MESSAGE', function (data) {
        realTime.drawl(JSON.parse(data));
    });
};
realTime.drawl = function (_data) {
    var id = new Date().getTime();
    if ($("body").find("div.realtime-notification").length === 0) {
        $("body").append('<div class="realtime-notification" data-rel="realtime" ></div>');
    }
    $("div[data-rel=realtime]").append('<div class="realtime-item" data-time="' + id + '" >\
        	<span class="glyphicon glyphicon-remove-circle"></span>' +
            (typeof _data.data !== 'undefined' && typeof _data.data.photo !== 'undefined' ? '<img class="realtime-img" src="' + _data.data.photo + '" alt="img">' : '') + '\
                <p>' + _data.message + '</p>' +
            (typeof _data.data !== 'undefined' && typeof _data.data.link !== 'undefined' ? ('<p><a href="' + baseUrl + _data.data.link + '">» ' + _data.data.text + '</a></p>') : '') + '</div>');
    $("div[data-time=" + id + "] span.glyphicon-remove-circle").click(function () {
        realTime.close(id);
    });
    setTimeout(function () {
        if ($("div[data-time=" + id + "]").length > 0) {
            realTime.close(id);
        }
    }, 10000);
};
realTime.close = function (_id) {
    $("div[data-time=" + _id + "]").hide("slow");
    $("div[data-time=" + _id + "]").remove();
};