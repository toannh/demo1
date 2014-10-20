<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<div class="col-lg-9 col-md-9 col-sm-8 col-xs-12 right-content">
    <c:if test="${fn:length(shopHeartBanner)>0}">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <c:forEach items="${shopHeartBanner}" var="shopbanner" varStatus="stt">
                    <li data-target="#carousel-example-generic" data-slide-to="${stt.index}" ${stt.index==0 ? 'class="active"':''}></li>
                    </c:forEach> 
            </ol>
            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <c:forEach items="${shopHeartBanner}" var="shopbanner" varStatus="stt">
                    <c:if test="${shopbanner.embedCode==null}">
                        <div class="item ${stt.index==0 ? 'active':''}">
                            <a href="${shopbanner.url}" target="_blank"><img src="${shopbanner.image}" alt="${shopbanner.title}"></a>
                        </div>
                    </c:if>
                    <c:if test="${shopbanner.embedCode!=null}">
                        <div class="item ${stt.index==0 ? 'active':''}">
                            ${shopbanner.embedCode}
                        </div>
                    </c:if>
                </c:forEach>  
            </div>
            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                <span class="icon-btn-left-slider"></span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                <span class="icon-btn-right-slider"></span>
            </a>
        </div><!--Slider home-->
    </c:if>
    <div class="box-module tab-product-home">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs">
            <li class="active"><a href="#home" data-toggle="tab">Sản phẩm mới</a></li>
            <li><a href="#profile" data-toggle="tab">Nhiều người xem</a></li>
        </ul>                    
        <!-- Tab panes -->
        <div class="tab-content">
            <div class="tab-pane active" id="home">                              
                <div class="container-fluid">
                    <div class="row">
                        <c:forEach items="${listItemsNew}" var="itemsNew" varStatus="stt">
                            <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                                <div class="item-product">
                                    <div class="item-product-img">
                                        <c:if test="${item.listingType != 'AUCTION' && text:percentPrice(itemsNew.startPrice, itemsNew.sellPrice, itemsNew.discount, itemsNew.discountPrice, itemsNew.discountPercent) != '0'}">
                                            <div class="sticker-price">${text:percentPrice(itemsNew.startPrice, itemsNew.sellPrice, itemsNew.discount, itemsNew.discountPrice, itemsNew.discountPercent)}%</div>
                                        </c:if>
                                        <a href="${basicUrl}/san-pham/${itemsNew.id}/${text:createAlias(itemsNew.name)}.html">
                                            <img src="${(itemsNew.images != null && fn:length(itemsNew.images) >0)?itemsNew.images[0]:staticUrl.concat('/market/images/no-image-product.png')}" alt="${itemsNew.name}" />
                                        </a>
                                        <div class="hover">
                                            <ul class="icons unstyled">										
                                                <li><a href="${basicUrl}/san-pham/${itemsNew.id}/${text:createAlias(itemsNew.name)}.html" class="btn btn-warning btn-sm">Chi tiết</a></li>															
                                                <li><a href="#" class="btn btn-info btn-sm" data-toggle="modal" data-target="#popup-quick-view" onclick="browse.quickView('${itemsNew.id}')">Xem nhanh</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <h4><a href="${basicUrl}/san-pham/${itemsNew.id}/${text:createAlias(itemsNew.name)}.html" title="${itemsNew.name}">${itemsNew.name}</a></h4>
                                    <div class="item-product-price">
                                        <c:if test="${itemsNew.listingType != 'AUCTION'}">
                                            <span class="pull-left product-item-price">${text:sellPrice(itemsNew.sellPrice, itemsNew.discount, itemsNew.discountPrice, itemsNew.discountPercent)} <sup>đ</sup></span>
                                            <c:if test="${text:startPrice(itemsNew.startPrice, itemsNew.sellPrice, itemsNew.discount) != '0'}">
                                                <span class="pull-right product-item-price-old">${text:startPrice(itemsNew.startPrice, itemsNew.sellPrice, itemsNew.discount)} <sup>đ</sup></span>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${itemsNew.listingType == 'AUCTION'}">
                                            <span class="pull-left product-item-price">${text:numberFormat(itemsNew.highestBid > 0?itemsNew.highestBid:itemsNew.startPrice)} <sup>đ</sup></span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach> 
                    </div>
                </div>                      
            </div><!--Tabs content1-->
            <div class="tab-pane" id="profile">
                <div class="container-fluid">
                    <div class="row">
                        <c:forEach items="${listItemsViewCounts}" var="itemsNew" varStatus="stt">
                            <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                                <div class="item-product">
                                    <div class="item-product-img">
                                        <c:if test="${item.listingType != 'AUCTION' && text:percentPrice(itemsNew.startPrice, itemsNew.sellPrice, itemsNew.discount, itemsNew.discountPrice, itemsNew.discountPercent) != '0'}">
                                            <div class="sticker-price">${text:percentPrice(itemsNew.startPrice, itemsNew.sellPrice, itemsNew.discount, itemsNew.discountPrice, itemsNew.discountPercent)}%</div>
                                        </c:if>
                                        <a href="${basicUrl}/san-pham/${itemsNew.id}/${text:createAlias(itemsNew.name)}.html">
                                            <img src="${(itemsNew.images != null && fn:length(itemsNew.images) >0)?itemsNew.images[0]:staticUrl.concat('/market/images/no-image-product.png')}" alt="${itemsNew.name}" /></a>
                                        <div class="hover">
                                            <ul class="icons unstyled">										
                                                <li><a href="${basicUrl}/san-pham/${itemsNew.id}/${text:createAlias(itemsNew.name)}.html" class="btn btn-warning btn-sm">Chi tiết</a></li>															
                                                <li><a href="#" class="btn btn-info btn-sm" data-toggle="modal" data-target="#popup-quick-view" onclick="browse.quickView('${itemsNew.id}')">Xem nhanh</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <h4><a href="${basicUrl}/san-pham/${itemsNew.id}/${text:createAlias(itemsNew.name)}.html">${itemsNew.name}</a></h4>
                                    <div class="item-product-price">
                                        <c:if test="${itemsNew.listingType != 'AUCTION'}">
                                            <span class="pull-left product-item-price">${text:sellPrice(itemsNew.sellPrice, itemsNew.discount, itemsNew.discountPrice, itemsNew.discountPercent)} <sup>đ</sup></span>
                                            <c:if test="${text:startPrice(itemsNew.startPrice, itemsNew.sellPrice, itemsNew.discount) != '0'}">
                                                <span class="pull-right product-item-price-old">${text:startPrice(itemsNew.startPrice, itemsNew.sellPrice, itemsNew.discount)} <sup>đ</sup></span>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${itemsNew.listingType == 'AUCTION'}">
                                            <span class="pull-left product-item-price">${text:numberFormat(itemsNew.highestBid > 0?itemsNew.highestBid:itemsNew.startPrice)} <sup>đ</sup></span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach> 
                    </div>
                </div>    
            </div><!--Tabs content2-->
        </div>
    </div><!--Tabs Danh mục sản phẩm-->  
    <c:forEach items="${listHomeItemsNB}" var="listHomeItems">
        <div class="box-module category-list-box">
            <div class="title-box">
                <h2>
                    <a><span class="fa ${listHomeItems.icon}"></span> ${listHomeItems.name}</a>
                    <span class="pull-right"><a href="#">Xem tất cả</a></span>
                </h2>                        
                <hr />
            </div><!--Title box-->
            <div class="container-fluid">
                <div class="row">
                    <c:forEach items="${listItemsNB}" var="itemsNew">
                        <c:forEach items="${listHomeItems.itemIds}" var="itemids">
                            <c:if test="${itemsNew.id==itemids}">
                                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                                    <div class="item-product">
                                        <div class="item-product-img">
                                            <c:if test="${item.listingType != 'AUCTION' && text:percentPrice(itemsNew.startPrice, itemsNew.sellPrice, itemsNew.discount, itemsNew.discountPrice, itemsNew.discountPercent) != '0'}">
                                                <div class="sticker-price">${text:percentPrice(itemsNew.startPrice, itemsNew.sellPrice, itemsNew.discount, itemsNew.discountPrice, itemsNew.discountPercent)}%</div>
                                            </c:if>
                                            <a href="${basicUrl}/san-pham/${itemsNew.id}/${text:createAlias(itemsNew.name)}.html">
                                                <img src="${(itemsNew.images != null && fn:length(itemsNew.images) >0)?itemsNew.images[0]:staticUrl.concat('/market/images/no-image-product.png')}" alt="${itemsNew.name}" /></a>
                                            <div class="hover">
                                                <ul class="icons unstyled">										
                                                    <li><a href="${basicUrl}/san-pham/${itemsNew.id}/${text:createAlias(itemsNew.name)}.html" class="btn btn-warning btn-sm">Chi tiết</a></li>															
                                                    <li><a href="#" class="btn btn-info btn-sm" data-toggle="modal" data-target="#popup-quick-view" onclick="browse.quickView('${itemsNew.id}')">Xem nhanh</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <h4><a href="${basicUrl}/san-pham/${itemsNew.id}/${text:createAlias(itemsNew.name)}.html">${itemsNew.name}</a></h4>
                                        <div class="item-product-price">
                                            <c:if test="${itemsNew.listingType != 'AUCTION'}">
                                                <span class="pull-left product-item-price">${text:sellPrice(itemsNew.sellPrice, itemsNew.discount, itemsNew.discountPrice, itemsNew.discountPercent)} <sup>đ</sup></span>
                                                <c:if test="${text:startPrice(itemsNew.startPrice, itemsNew.sellPrice, itemsNew.discount) != '0'}">
                                                    <span class="pull-right product-item-price-old">${text:startPrice(itemsNew.startPrice, itemsNew.sellPrice, itemsNew.discount)} <sup>đ</sup></span>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${itemsNew.listingType == 'AUCTION'}">
                                                <span class="pull-left product-item-price">${text:numberFormat(itemsNew.highestBid > 0?itemsNew.highestBid:itemsNew.startPrice)} <sup>đ</sup></span>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </div>
            </div>
        </div><!--Danh mục sản phẩm-->
    </c:forEach>
</div><!--Right content-->
<div class="internal-text">
    <h1>${shop.title} | Chợ ĐiệnTử eBay Việt Nam</h1>
    <h2>Sản phẩm cập nhật từ shop ${shop.title} tại chodientu.vn</h2>
</div>
<div class="modal fade" id="popup-quick-view" tabindex="-1" role="dialog" aria-labelledby="popup-quick-viewLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                </div>
            </div>
        </div>
    </div>
</div>