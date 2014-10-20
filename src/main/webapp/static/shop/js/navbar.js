// JavaScript Document
$(document).ready(function() {
    /*Main Menu */


    /*Mobile menu*/
    $(function() {
        $('nav#menu-category-mobile').mmenu();
        $('nav#menu-category-news-mobile').mmenu();


    });
    /*End Mobile menu*/

    if ($('#menu').length <= 0) {
        $('body').append(template('/shop/tpl/navbarmenu.tpl'));
        $('nav#menu').mmenu();
    }
});