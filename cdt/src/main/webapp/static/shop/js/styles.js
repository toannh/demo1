// JavaScript Document
$(document).ready(function() {
    for (var i = 0; i < shopCategories.length; i++) {
        var cat = shopCategories[i];
        if (cat.level === 1) {
            $('#menu-category-product ul').append('<li class="menu-item-cat" for="' + cat.id + '"><a href="' + baseUrl + '/' + shop.alias + '/browse.html?cid=' + cat.id + '">' + cat.name + '</a></li>');
            $('#menu-category-mobile ul').append('<li for="' + cat.id + '"><a href="' + baseUrl + '/' + shop.alias + '/browse.html?cid=' + cat.id + '">' + cat.name + '</a></li>');
        }
    }
    if (shopCategories === null || shopCategories.length === 0) {
        ajax({
            service: '/histogram/category.json?leaf=1&sellerId=' + $("input[name=sellerId]").val(),
            loading: false,
            done: function(resp) {
                if (resp.success) {
                    $.each(resp.data, function() {
                        $('#menu-category-product ul').append('<li for="' + this.id + '"><a href="' + baseUrl + '/' + shop.alias + '/browse.html?cid=' + this.id + '">' + this.name + '</a></li>');
                        $('#menu-category-mobile ul').append('<li for="' + this.id + '"><a href="' + baseUrl + '/' + shop.alias + '/browse.html?cid=' + this.id + '">' + this.name + '</a></li>');
                    });
                }
            }
        });
    }

    for (var lv = 2; lv < 6; lv++) {
        for (var i = 0; i < shopCategories.length; i++) {
            var cat = shopCategories[i];
            if (cat.level === lv) {
                if ($('#menu-category-product li[for=' + cat.parentId + '] ul').length <= 0) {
                    $('#menu-category-product li[for=' + cat.parentId + ']').append('<ul></ul>');
                }
                $('#menu-category-product li[for=' + cat.parentId + '] ul').append('<li for="' + cat.id + '"><a href="' + baseUrl + '/' + shop.alias + '/browse.html?cid=' + cat.id + '">' + cat.name + '</a></li>');
                if ($('#menu-category-mobile li[for=' + cat.parentId + '] ul').length <= 0) {
                    $('#menu-category-mobile li[for=' + cat.parentId + ']').append('<ul></ul>');
                }
                $('#menu-category-mobile li[for=' + cat.parentId + '] ul').append('<li for="' + cat.id + '"><a href="' + baseUrl + '/' + shop.alias + '/browse.html?cid=' + cat.id + '">' + cat.name + '</a></li>');
            }
        }
    }
    for (var i = 0; i < shopNewsCategories.length; i++) {
        var cat = shopNewsCategories[i];
        if (cat.level === 1) {
            $('#menu-catnews ul').append('<li class="menu-item-cat" for="' + cat.id + '"><a href="' + baseUrl + '/' + shop.alias + '/news-category/' + cat.id + '/' + textUtils.createAlias(cat.name) + '.html">' + cat.name + '</a></li>');
            $('#menu-category-news-mobile ul').append('<li for="' + cat.id + '"><a href="' + baseUrl + '/' + shop.alias + '/news-category/' + cat.id + '/' + textUtils.createAlias(cat.name) + '.html">' + cat.name + '</a></li>');
        }
    }

    for (var lv = 2; lv < 6; lv++) {
        for (var i = 0; i < shopNewsCategories.length; i++) {
            var cat = shopNewsCategories[i];
            if (cat.level === lv) {
                if ($('#menu-catnews li[for=' + cat.parentId + '] ul').length <= 0) {
                    $('#menu-catnews li[for=' + cat.parentId + ']').append('<ul></ul>');
                }
                $('#menu-catnews li[for=' + cat.parentId + '] ul').append('<li for="' + cat.id + '"><a href="' + baseUrl + '/' + shop.alias + '/news-category/' + cat.id + '/' + textUtils.createAlias(cat.name) + '.html">' + cat.name + '</a></li>');
                if ($('#menu-category-news-mobile li[for=' + cat.parentId + '] ul').length <= 0) {
                    $('#menu-category-news-mobile li[for=' + cat.parentId + ']').append('<ul></ul>');
                }
                $('#menu-category-news-mobile li[for=' + cat.parentId + '] ul').append('<li for="' + cat.id + '"><a href="' + baseUrl + '/' + '/news-category/' + cat.id + '/' + textUtils.createAlias(cat.name) + '.html">' + cat.name + '</a></li>');
            }
        }
    }

    /*Main Menu */
    $(function() {
        ddsmoothmenu.init({
            mainmenuid: "menu-category-product", //Menu DIV id
            orientation: 'v', //Horizontal or vertical menu: Set to "h" or "v"
            classname: 'ddsmoothmenu-v', //class added to menu's outer DIV
            //customtheme: ["#804000", "#482400"],
            contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
        });
        ddsmoothmenu.init({
            mainmenuid: "menu-catnews",
            orientation: 'v', //Horizontal or vertical menu: Set to "h" or "v"
            classname: 'ddsmoothmenu-v', //class added to menu's outer DIV
            //customtheme: ["#804000", "#482400"],
            contentsource: "markup", //"markup" or ["container_id", "path_to_menu_file"]
        });
    });

    /*End Main Menu */

    /*Mobile menu*/
    $(function() {
        $('nav#menu-category-mobile').mmenu();
        $('nav#menu-category-news-mobile').mmenu();
        $('nav#menu').mmenu();
        $('nav#user').mmenu();
        $('nav#user-nologin').mmenu();

    });
    /*End Mobile menu*/

    $('.tool-tip').tooltip();

    $(window).scroll(function() {
        if ($(this).scrollTop() > 150) {
            $('#gotop').fadeIn(500);
        } else {
            $('#gotop').fadeOut(500);
        }
    });
    $('#gotop').click(function() {
        $('html, body').animate({scrollTop: 0}, '200');
    });

});