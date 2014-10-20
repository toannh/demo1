/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
xengplus = {};
xengplus.plus = function(xeng) {
    if (typeof xeng !== 'undefined' && xeng > 0) {
        $("body").append('<div class="givexeng">\
    	<div class="givexeng-inner">\
        	<span class="givexeng-number">+' + xeng + '</span>\
        	<span class="givexeng-coin"></span>\
            <span class="givexeng-pig"></span>\
        </div>\
    </div><!--givexeng-->');

        $(".givexeng-number").show();
        $(".givexeng-coin").show();
        $(".givexeng-pig").show();
        $(".givexeng-number").delay(1000).animate({
            opacity: 0,
        }, 1000
                );
        $(".givexeng-coin").delay(1000).animate({
            opacity: 0,
            bottom: "-=64"
        }, 1000, function() {
            // Animation complete.
            $(".givexeng-pig").animate({
                opacity: 0,
            }, 1000
                    );
        });
    }
};

