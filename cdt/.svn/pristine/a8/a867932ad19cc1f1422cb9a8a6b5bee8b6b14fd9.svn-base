$(document).ready(function() {

    $.each($("h4 a"), function() {
        var url = $(this).attr("href");
        if (url.indexOf($("input[name=uri]").val()) > 0) {
            $(this).parent().parent().addClass("active");
            var par = this;
            setTimeout(function() {
                if ($(par).parent().parent().parent().parent().attr("id") != 'collapseOne1') {
                    $(par).parent().parent().parent().parent().removeClass("collapse in");
                    $(par).parent().parent().parent().parent().css({"height": "auto"});
                }
            }, 1000);

            return false;
        }
    });

    //tab 
    $(".tab-title a").click(function() {
        var parent = $(this).parent();
        var grand = $(this).parent().parent();
        $('a', $(parent)).removeClass("active");
        $(this).addClass("active");
        $('.tab-content', $(grand)).hide();
        var activeTab = $(this).attr("href");
        $(activeTab).fadeIn();
        return false;
    });

    // button top
    $('.go-top').click(function() {
        $("html, body").animate({scrollTop: $('body').offset().top}, 700);
    });
    $(function() {
        $(window).scroll(function() {
            if ($(this).scrollTop() > 500) {
                $('.go-top').fadeIn();
            } else {
                $('.go-top').fadeOut();
            }
        });
    });

    //menu top scroll
    $(window).scroll(function() {
        if ($(this).scrollTop() >= 46) {
            $('.navigator').addClass("navigator-fixed");
            $('body').css("padding-top", "46px");
        }
        if ($(this).scrollTop() < 46) {
            $('.navigator').removeClass("navigator-fixed");
            $('body').css("padding-top", "0");
        }
    });

    //homestory slider
    $('.homestory-slider').jcarousel({
        scroll: 1,
        auto: 0,
        animation: 500,
        wrap: 'circular',
    });

    //homecomment slider
    $('.homecomment-slider').jcarousel({
        scroll: 1,
        auto: 0,
        animation: 500,
        wrap: 'circular',
    });

    //homedeal slider
    $('.homedeal-slider').jcarousel({
        scroll: 1,
        auto: 0,
        animation: 500,
        wrap: 'circular',
    });

    //image detail slider
    $('.imgdetail-slider').jcarousel({
        scroll: 1,
        auto: 0,
        animation: 800,
        wrap: 'last'
    });

    //homedeal slider
    $('.pd-featured-slider').jcarousel({
        scroll: 1,
        auto: 5,
        animation: 500,
        wrap: 'circular',
    });

    //mobile menu
    $(function() {
        $('nav#menu').mmenu();
    });
    $(function() {
        $('nav#user').mmenu();
    });
    $(function() {
        $('nav#smart-filter').mmenu();
    });

    // function category menu click
	$('a', $('.category-list-icon ul')).click(function() {
		var id = $(this).attr('href');
		var navHeight = $(".navigator").height();
		var navCat = $(".category-list-icon").height();
		$("html, body").animate({scrollTop: $(id).offset().top - navHeight - navCat}, 700);
		var parent = $(this).parent();
		$('li', $('.category-list-icon ul')).removeClass("active");
		$(parent).addClass("active");
		return false;
	});

    //hover image product item
    $('.item-hover li').hover(function() {
        var x = $('img', $(this)).attr('src');
        var x = x.replace("&width=50&height=50", "&width=225&height=225");
        var parent = $(this).parent()
        var grand = $(this).parent().parent();
        $('li', $(parent)).removeClass("active");
        $(this).addClass("active");
        $('.item-img img', $(grand)).attr('src', x);
    });

    //click image product detail
    $('.pd-slider .is-item a').click(function() {
        var parent = $(this).parent();
        $('.is-item', $('.pd-slider')).removeClass("active");
        $(parent).addClass("active");
    });

    // function tab product detail click
    $('a', $('.move-tabclick li')).click(function() {
        var id = $(this).attr('href');
        var navHeight = $(".navigator").height();
        $("html, body").animate({scrollTop: $(id).offset().top - navHeight}, 700);
        return false;
    });

    //custom tip 
    $(".ctp-click .btn-ctp").click(function() {
        var parent = $(this).parent();
        $('.custom-tip-pop', $(parent)).show();
        return false;
    });
    $(".ctp-click .icon20-close").click(function() {
        var parent = $(this).parent().parent().parent();
        $('.custom-tip-pop', $(parent)).hide();
    });

    // call tooltip
    $('.cdt-tooltip').tooltip();

    //checkout click choose 
	$(".trans-item").click(function(){
		var parent = $(this).parent();	
		var grand = $(this).parent().parent();	
		if ($(parent).hasClass("active")) {
            $(parent).removeClass("active");
			$('.content-trans',$(parent)).slideUp();
			$('.trans-radio',$(this)).attr('checked', false);
        } else {
			$('li.active .content-trans',$(grand)).slideUp();
			$('.trans-radio',$(this)).attr('checked', true);
			$('li',$('.table-trans .ul-trans')).removeClass("active");
            $(parent).addClass("active");
			$('.content-trans',$(parent)).slideDown();
        }
		return false;
	});
	$(".table-trans .ul-bank a").click(function() {
        var parent = $(this).parent();
        var grand = $(this).parent().parent().parent().parent().parent();
        if ($(parent).hasClass("active")) {
            $(parent).removeClass("active");
            $(grand).removeClass("border-right-none");
            $('.logo-tick', $(this)).remove();
        } else {
            $('li', $('.table-trans .ul-bank')).removeClass("active");
            $(parent).addClass("active");
            $('.ul-trans li', $('.table-trans')).removeClass("border-right-none");
            $(grand).addClass("border-right-none");
            $('.logo-tick', $('.table-trans')).remove();
            $(this).append("<span class='logo-tick'></span>");
        }
        return false;
    });

    //openshop edit shopname
    $('.openshop-finish .grid .glyphicon-pencil').click(function() {
        var parent = $(this).parent();
        $('.openshop-textedit', $(parent)).hide();
        $('.glyphicon-pencil', $(parent)).hide();
        $('.text', $(parent)).show();
        $('.glyphicon-floppy-disk', $(parent)).show();
        $('.glyphicon-remove', $(parent)).show();
    });
    $('.openshop-finish .grid .glyphicon-remove').click(function() {
        var parent = $(this).parent();
        $('.glyphicon-pencil', $(parent)).show();
        $('.openshop-textedit', $(parent)).show();
        $('.text', $(parent)).hide();
        $('.glyphicon-floppy-disk', $(parent)).hide();
        $('.glyphicon-remove', $(parent)).hide();
    });
	
	//smart search click
	$(".smart-search-button").click(function(){		
		$('.smart-search-button').css("right","-45px");
		$('.smart-search').css("right","0");
		return false;
	});
	$(".smart-search .ss-title").click(function(){
		$('.smart-search').css("right","-267px");
		$('.smart-search-button').css("right","0");
		return false;
	});
	
	//smart history scroll show
	$(function () {
		$(window).scroll(function () {
			if ($(this).scrollTop() > ($('.pd-detail').height() + $('.pd-featured').height() + 180) & $(this).scrollTop() < ($('.pd-detail').height() + $('.pd-featured').height() + $('.page-detail').height() - $('.box-history').height())) {
				$('.smart-history').fadeIn();
			} else {
				$('.smart-history').fadeOut();
			}
		});
	});
	
	//smart history click
	$(".sh-history-button").click(function(){
		var parent = $(this).parent();
		var grand = $(this).parent().parent();
		$('.sh-history-button',$(grand)).css("left","0");
		$(this).css("left","-60px");
		$('.sh-view',$(grand)).css("left","-297px");
		$('.sh-view',$(parent)).css("left","0");
		return false;
	});
	$(".sh-title").click(function(){
		var parent = $(this).parent();
		var grand = $(this).parent().parent();
		$(parent).css("left","-297px");
		$('.sh-history-button',$(grand)).css("left","0");
		return false;
	});
	
	//smart support click
	$(".smart-support-button").click(function(){		
		$('.smart-support-button').css("right","-45px");
		$('.smart-support').css("right","0");
		return false;
	});
	$(".smart-support .ss-title").click(function(){
		$('.smart-support').css("right","-487px");
		$('.smart-support-button').css("right","0");
		return false;
	});
	
	//smart notification click
	$(".sn-button").click(function(){		
		$('.sn-button').css("bottom","-45px");
		$('.sn-view').slideDown();
		return false;
	});
	$(".sn-title").click(function(){
		$('.sn-view').slideUp();
		$('.sn-button').css("bottom","0");
		return false;
	});
	
    /***========Config pages user=============****/
    $(function() {
        $('#collapse-pr').collapse('hide')
    });
    $(function() {
        $('.collapse').collapse({toggle: true})
    });
    $('.tool-tip').tooltip();
    /***========Config pages user=============****/

    //param click submit
    $('.param-label', $('.submit-param')).click(function() {
        $('.param', $('.submit-param')).removeClass("active");
        var parent = $(this).parent();
        $(parent).addClass("active");
        $('.param-popup', $('.submit-param')).hide();
        $('.param-popup', $(parent)).show();
    });
    $('.button-gray', $('.submit-param')).click(function() {
        var parent = $(this).parent().parent();
        var grand = $(this).parent().parent().parent();
        $(parent).hide();
        $(grand).removeClass("active");
    });

    $('.btn-more-button').click(function() {
        var parent = $(this).parent();
        if ($('.shipping-block-statics').parent().hasClass("height-toggle")) {
            $('.shipping-block-statics').parent().removeClass("height-toggle");
        } else {
            $('.shipping-block-statics').parent().addClass("height-toggle");
            $('.shipping-block-statics').parent().fadeIn();
        }
        return true;
    });

    $(function() {
        //Scroll bar hotseller
        $('.col-sm-3.reset-padding .submit-choose-img-item').slimScroll({
            height: '120px',
        });
        $('.history-up').slimScroll({
            height: '120px',
        });

    });

	//check type video or flash tab1
    $("input[type=radio]").on('click', function() {
        if ($('#type-video-tab1').is(':checked')) {
            $("#box-type-video-tab1").slideDown("slow");
        } else {
            $("#box-type-video-tab1").slideUp("slow");
        }
        if ($('#type-flash-tab1').is(':checked')) {
            $("#box-type-flash-tab1").slideDown("slow");
        } else {
            $("#box-type-flash-tab1").slideUp("slow");
        }
    });
	
	//check type video or flash tab2
    $("input[type=radio]").on('click', function() {
        if ($('#type-video').is(':checked')) {
            $("#box-type-video").slideDown("slow");
        } else {
            $("#box-type-video").slideUp("slow");
        }
        if ($('#type-flash').is(':checked')) {
            $("#box-type-flash").slideDown("slow");
        } else {
            $("#box-type-flash").slideUp("slow");
        }
    });
});