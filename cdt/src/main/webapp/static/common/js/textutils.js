textUtils = {};

textUtils.createAlias = function(str) {
    if (str === null || str === '')
        return '';
    return textUtils.removeDiacritical(str).replace(/\W/g, "-").toLowerCase();
};

textUtils.createKeyword = function(str) {
    if (str === null || str === '')
        return '';
    return str.replace(/ /g, "+").toLowerCase();
};

textUtils.removeDiacritical = function(str) {
    str = str.replace(/(à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ)/g, "a");
    str = str.replace(/(è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ)/g, "e");
    str = str.replace(/(ì|í|ị|ỉ|ĩ)/g, "i");
    str = str.replace(/(ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ)/g, "o");
    str = str.replace(/(ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ)/g, "u");
    str = str.replace(/(ỳ|ý|ỵ|ỷ|ỹ)/g, "y");
    str = str.replace(/(đ)/g, "d");
    str = str.replace(/(À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ)/g, "A");
    str = str.replace(/(È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ)/g, "E");
    str = str.replace(/(Ì|Í|Ị|Ỉ|Ĩ)/g, "I");
    str = str.replace(/(Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ)/g, "O");
    str = str.replace(/(Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ)/g, "U");
    str = str.replace(/(Ỳ|Ý|Ỵ|Ỷ|Ỹ)/g, "Y");
    str = str.replace(/(Đ)/g, "D");
    return str;
};


Number.prototype.toMoney = function(decimals, decimal_sep, thousands_sep) {
    var n = this,
            c = isNaN(decimals) ? 2 : Math.abs(decimals), //if decimal is zero we must take it, it means user does not want to show any decimal
            d = decimal_sep || '.', //if no decimal separator is passed we use the dot as default decimal separator (we MUST use a decimal separator)

            t = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep, //if you don't want to use a thousands separator you can pass empty string as thousands_sep value

            sign = (n < 0) ? '-' : '',
            //extracting the absolute value of the integer part of the number and converting to string
            i = parseInt(n = Math.abs(n).toFixed(c)) + '',
            j = ((j = i.length) > 3) ? j % 3 : 0;
    return sign + (j ? i.substr(0, j) + t : '') + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : '');
};

textUtils.formatTime = function(time, format) {
    var months = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'];
    time = parseFloat(time);
    var a = new Date(time);
    var year = a.getFullYear();
    var month = months[a.getMonth()];
    var day = a.getDate();
    var hour = a.getHours();
    var minute = a.getMinutes();
    var second = a.getSeconds();
    var time = "";
    if (format === 'day') {
        time = day + "/" + month + "/" + year;
    } else if (format === 'hour') {
        time = hour + ":" + minute + " " + day + "/" + month + "/" + year;
    } else if (format === 'time') {
        time = day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second;
    } else if (format === 'hourfirst') {
        time = hour + ":" + minute + ":" + second + " " + day + "/" + month + "/" + year;
    }
    return time;
};

textUtils.redirect = function(_url, _time, _baseUrl) {
    _time = _time > 0 ? _time : 0;
    _baseUrl = _baseUrl != null ? _baseUrl : baseUrl;
    var t = _time / 1000;
    setInterval(function() {
        t = eval(t - 1);
        if (t >= 0)
            $('span.r-time').html('<div class="help-block">Hệ thống sẽ chuyển tự động chuyển trang trong vòng ' + eval(t) + ' giây</div>')
    }, 1000);

    setTimeout("location.href = '" + _baseUrl + _url + "';", _time);
};

textUtils.inputNumberFormat = function(cssClass) {
    $.each($('input.' + cssClass), function() {
        var num = this;
        $(this).on("keydown", function(e) {
            // Allow: backspace, delete, tab, escape, enter and .
            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 116]) !== -1 ||
                    // Allow: Ctrl+A
                            (e.keyCode == 65 && e.ctrlKey === true) ||
                            // Allow: home, end, left, right
                                    (e.keyCode >= 35 && e.keyCode <= 39)) {
                        // let it happen, don't do anything
                        return;
                    }
                    // Ensure that it is a number and stop the keypress
                    if ((e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode >= 96 && e.keyCode <= 105)) {
                        return;
                    } else {
                        e.preventDefault();
                    }
                });
        $(this).on("keyup", function(e) {
            var val = $(num).val().replace(/\./g, '');
            if (val != '' && !isNaN(val)) {
                $(num).val(parseFloat(val).toMoney(0, ',', '.'));
            } else {
                $(num).val(0);
            }
        });

    });
};

textUtils.urlParam = function() {
    var urlParams;
    var match,
            pl = /\+/g, // Regex for replacing addition symbol with a space
            search = /([^&=]+)=?([^&]*)/g,
            decode = function(s) {
                return decodeURIComponent(s.replace(pl, " "));
            },
            query = window.location.search.substring(1);
    urlParams = {};
    while (match = search.exec(query))
        urlParams[decode(match[1])] = decode(match[2]);
    return urlParams;
};

textUtils.percentFormat = function(startPrice, sellPrice, discount, discountPrice, discountPercent) {
    var percent = 0;
    if (!discount && startPrice > sellPrice) {
        percent = (startPrice - sellPrice) / startPrice;
    } else {
        if (discountPercent > 0) {
            discountPrice = sellPrice * ((100 - discountPercent) / 100);
        } else {
            discountPrice = sellPrice - discountPrice;
        }
        if (startPrice <= sellPrice) {
            startPrice = sellPrice;
        }
        percent = (startPrice - discountPrice) / startPrice;
    }
    percent *= 100;
    percent = Math.ceil(percent);
    return percent.toMoney(0, ',', '.');
};

textUtils.startPrice = function(startPrice, sellPrice, discount) {
    if (!discount && startPrice <= sellPrice) {
        return "0";
    }
    if (discount && startPrice <= sellPrice) {
        startPrice = sellPrice;
    }
    if (startPrice > 0) {
        return startPrice.toMoney(0, ',', '.');
    }
    return "";
};

textUtils.sellPrice = function(sellPrice, discount, discountPrice, discountPercent) {
    if (discount) {
        if (discountPercent > 0) {
            sellPrice = eval(sellPrice) * (100 - eval(discountPercent)) / 100;
        } else {
            sellPrice = eval(sellPrice) - eval(discountPrice);
        }
    }
    return sellPrice.toMoney(0, ',', '.');
};
textUtils.discountPrice = function(startPrice, sellPrice, discount, discountPrice, discountPercent) {
    if (discount && startPrice <= sellPrice) {
        startPrice = sellPrice;
    }
    if (discount) {
        if (discountPercent > 0) {
            sellPrice = eval(sellPrice) * (100 - eval(discountPercent)) / 100;
        } else {
            sellPrice = eval(sellPrice) - eval(discountPrice);
        }
    }
    var price = (eval(startPrice) - eval(sellPrice));
    price = (price > 0 ? price : 0);
    return price.toMoney(0, ',', '.');
};

/**
 * get cookie by name
 * @param {type} name
 * @returns {unresolved}
 */
textUtils.getCookie = function(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length === 2)
        return parts.pop().split(";").shift();
    else
        return "";
};

textUtils.setCookie = function(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires + "; path=/";
};

textUtils.removeSpecialCharacter = function(str) {
    str = str.replace(/[\:!&^/\*|+-~()?{}[]];\.#@$<>/g, "");
    return str.toLowerCase();
};

textUtils.isMobile = function() {
    ///<summary>Detecting whether the browser is a mobile browser or desktop browser</summary>
    ///<returns>A boolean value indicating whether the browser is a mobile browser or not</returns>

    if (sessionStorage.desktop) // desktop storage 
        return false;
    else if (localStorage.mobile) // mobile storage
        return true;

    // alternative
    var mobile = ['iphone', 'ipad', 'android', 'blackberry', 'nokia', 'opera mini', 'windows mobile', 'windows phone', 'iemobile'];
    for (var i in mobile)
        if (navigator.userAgent.toLowerCase().indexOf(mobile[i].toLowerCase()) > 0)
            return true;

    // nothing found.. assume desktop
    return false;
};
