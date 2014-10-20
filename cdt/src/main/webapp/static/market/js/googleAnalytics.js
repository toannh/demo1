var googleAnalytics = {};

/**
 * 
 * @param {type} _order
 * @param {type} _action purchase|order
 * @returns {undefined}
 */
googleAnalytics.add = function(_order, _action) {
//    ga('require', 'ec');
//    var items = _order.items;
//    $.each(items, function() {
//        var item = this;
//        ga('ec:addProduct', {
//            'id': item.itemId,
//            'name': item.itemName,
//            'category': item.categoryPath.toString(),
//            'price': item.itemPrice,
//            'quantity': item.quantity
//        });
////        ajax({
////            service: '/category/get.json',
////            data: {id: item.categoryPath[item.categoryPath.length - 1]},
////            loading: false,
////            success: function(resp) {
////                
////            }
////        });
//    });
//    ga('ec:setAction', _action, {
//        'id': _order.id,
//        'affiliation': 'NM ' + _order.buyerEmail + ' mua của NB' + _order.sellerId,
//        'revenue': _order.totalPrice,
//        'shipping': eval(_order.shipmentPrice - _order.cdtDiscountShipment),
//        'coupon': _order.couponId
//    });
//    ga('send', 'pageview');
    ga('require', 'ecommerce');
    ga('ecommerce:addTransaction', {
        'id': _order.id,
        'affiliation': 'NM ' + _order.buyerEmail + ' mua của NB' + _order.sellerId,
        'revenue': _order.totalPrice,
        'shipping': eval(_order.shipmentPrice - _order.cdtDiscountShipment),
    });
    var items = _order.items;
    $.each(items, function() {
        var item = this;
        ga('ecommerce:addItem', {
            'id': _order.id,
            'name': item.itemName,
            'sku': item.itemId,
            'category': item.categoryPath.toString(),
            'price': item.itemPrice,
            'quantity': item.quantity
        });
    });
    ga('ecommerce:send');
};

