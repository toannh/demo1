$(document).ready(function(){
	
	//tab 
	$(".tab-title a").click(function(){
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
	$('.top').click(function(){
		$("html, body").animate({scrollTop: $('body').offset().top}, 700);
	});
	
	//menu top scroll
	$(window).scroll(function(){
		var mixheight = 0;
		mixheight = $('.navbar-default').height() - 51;
		var windowwidth = $('body').width();
		if($(this).scrollTop() >= mixheight && windowwidth > 768){
			$('.navbar-default').addClass("navbar-fixed-top");
			$('body').css("padding-top","42px");
		}
		if($(this).scrollTop() == 0 && windowwidth > 768 ){
			$('.navbar-default').removeClass("navbar-fixed-top");
			$('body').css("padding-top","0");	
		}
	});
	
});
