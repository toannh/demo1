var searchboxPlaceHolder = "Từ khoá tìm kiếm...";
$(document).ready(function() {
    $("#searchShop #search").val(searchboxPlaceHolder);

    $("#searchShop #search").focusin(function() {
        if ($(this).val() === searchboxPlaceHolder) {
            $(this).val("");
        }
    });
    $("#searchShop #search").focusout(function() {
        if ($(this).val() === "") {
            $(this).val(searchboxPlaceHolder);
        }
    });

    $('#searchShop button').click(function() {
        dosearch();
    });

    $("#searchShop #search").on('keyup', null, function(event) {
        if (event.keyCode === 13) {
            dosearch();
        }
    });

    var dosearch = function() {
        var key = $('#searchShop #search').val();
        if (key === '' || key === 'Từ khoá tìm kiếm...') {
            return;
        }
        document.location = baseUrl + "/" + shop.alias + "/browse.html?keyword=" + key;
    };
});