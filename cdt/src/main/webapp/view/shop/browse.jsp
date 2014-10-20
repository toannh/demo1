<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<div class="col-lg-9 col-md-9 col-sm-8 col-xs-12 right-content">
    <ol class="breadcrumb">
        <li><a href="${baseUrl}/${shop.alias}/">Trang chủ</a></li>
            <c:forEach items="${shopCategory.path}" var="path">
                <c:forEach items="${shopCategories}" var="cate">
                    <c:if test="${cate.id==path}"><li><a href="${baseUrl}/${shop.alias}/browse.html?cid=${path}">${cate.name}</a></li></c:if>
                </c:forEach>
            </c:forEach>
            <c:if test="${fn:length(shopCategories) == 0}">
            <li><a href="${baseUrl}/${shop.alias}/browse.html?cid=${shopCategory.id}">${shopCategory.name}</a></li>
            </c:if>
            <c:if test="${promotion!=null}">
            <li>Chương trình khuyến mãi</li>
            <li>${promotion.name}</li>
            </c:if>

    </ol>
    <c:if test="${promotion!=null}">
        <h2>
            <a href="javascript:;">${promotion.name}</a>
        </h2>
    </c:if>
    <c:if test="${shopCategory!=null}">
        <h2>
            <a href="${baseUrl}/${shop.alias}/browse.html?cid=${shopCategory.id}">${shopCategory.name}</a>
        </h2>
    </c:if>
    <div class="box-module category-list-box">
        <div class="title-box">
            <div class="clearfix">
                <div class="pull-left">Tìm thấy <strong>${itemPage.dataCount}</strong> kết quả</div>                        
                <div class="pull-right">
                    <select name="order" class="form-control" onChange="browse.changeOrder(this);">
                        <option value="0" <c:if test="${itemSearch.orderBy==0}">selected="true"</c:if>>Sắp xếp theo thời gian up</option>
                        <option value="1" <c:if test="${itemSearch.orderBy==1}">selected="true"</c:if>>Sắp xếp theo giá giảm dần</option>
                        <option value="2" <c:if test="${itemSearch.orderBy==2}">selected="true"</c:if>>Sắp xếp theo giá tăng dần</option>
                        <option value="3" <c:if test="${itemSearch.orderBy==3}">selected="true"</c:if>>Sắp xếp theo cập nhật mới nhất</option>                                                                                                                            
                        </select>
                    </div>
                </div>
                <hr />
            </div><!--Title box-->
            <div class="container-fluid">
                <div class="row">
                <c:forEach items="${itemPage.data}" var="item" >
                    <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
                        <div class="item-product">
                            <div class="item-product-img">
                                <c:if test="${item.listingType != 'AUCTION' && text:percentPrice(item.startPrice, item.sellPrice, item.discount, item.discountPrice, item.discountPercent) != '0'}">
                                    <div class="sticker-price">${text:percentPrice(item.startPrice, item.sellPrice, item.discount, item.discountPrice, item.discountPercent)}%</div>
                                </c:if>
                                <a href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">
                                    <img src="${(item.images != null && fn:length(item.images) >0)?item.images[0]:''}"  alt="Ảnh sản phẩm" title="${item.name}"/>
                                </a>
                                <div class="hover">
                                    <ul class="icons unstyled">										
                                        <li><a href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html" class="btn btn-warning btn-sm">Chi tiết</a></li>															
                                        <li><a class="btn btn-info btn-sm ${item.id}" data-toggle="modal" onclick="browse.quickView('${item.id}')" data-target="#popup-quick-view">Xem nhanh</a></li>
                                    </ul>
                                </div>
                            </div>
                            <h4><a href="${baseUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">${item.name}</a></h4>
                            <div class="item-product-price">
                                <c:if test="${item.listingType != 'AUCTION'}">
                                    <span class="pull-left product-item-price">${text:sellPrice(item.sellPrice, item.discount, item.discountPrice, item.discountPercent)} <sup>đ</sup></span>
                                </c:if>
                                <c:if test="${item.listingType == 'AUCTION'}">
                                    <span class="pull-left product-item-price">${text:numberFormat(item.highestBid > 0?item.highestBid:item.startPrice)} <sup>đ</sup></span>
                                </c:if>
                                <c:if test="${text:startPrice(item.startPrice, item.sellPrice, item.discount) != '0'}">
                                    <span class="pull-right product-item-price-old">${text:startPrice(item.startPrice, item.sellPrice, item.discount)} <sup>đ</sup></span>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>                                                                   
            </div>
        </div>
        <ul class="pagination pull-right">

            <c:if test="${itemPage.pageIndex > 3}"><li><a href="${baseUrl}${url:shopBrowseUrl(itemSearch, shop.alias, '[{key:"page",val:"1"}]')}">Đầu</a></li></c:if>
            <c:if test="${itemPage.pageIndex > 2}"><li><a href="${baseUrl}${url:shopBrowseUrl(itemSearch, shop.alias, '[{key:"page",val:"'.concat(itemSearch.pageIndex).concat('"}]'))}">&laquo;</a></li></c:if>
            <c:if test="${itemPage.pageIndex >= 2}"><li><a href="${baseUrl}${url:shopBrowseUrl(itemSearch, shop.alias, '[{key:"page",val:"'.concat(itemSearch.pageIndex-1).concat('"}]'))}">${itemPage.pageIndex-1}</a></li></c:if>
            <c:if test="${itemPage.pageIndex >= 1}"><li><a href="${baseUrl}${url:shopBrowseUrl(itemSearch, shop.alias, '[{key:"page",val:"'.concat(itemSearch.pageIndex).concat('"}]'))}">${itemPage.pageIndex}</a></li></c:if>
            <li class="active"><a>${itemPage.pageIndex + 1}</a></li>
            <c:if test="${itemPage.pageCount - itemPage.pageIndex >= 2}"><li><a href="${baseUrl}${url:shopBrowseUrl(itemSearch, shop.alias, '[{key:"page",val:"'.concat(itemSearch.pageIndex+2).concat('"}]'))}">${itemPage.pageIndex+2}</a></li></c:if>
            <c:if test="${itemPage.pageCount - itemPage.pageIndex > 3}"><li><a href="${baseUrl}${url:shopBrowseUrl(itemSearch, shop.alias, '[{key:"page",val:"'.concat(itemSearch.pageIndex+3).concat('"}]'))}">${itemPage.pageIndex+3}</a></li></c:if>
            <c:if test="${itemPage.pageCount - itemPage.pageIndex > 2}"><li><a href="${baseUrl}${url:shopBrowseUrl(itemSearch, shop.alias, '[{key:"page",val:"'.concat(itemSearch.pageIndex+2).concat('"}]'))}">&raquo;</a></li></c:if>
            <c:if test="${itemPage.pageCount - itemPage.pageIndex > 2}"><li><a href="${baseUrl}${url:shopBrowseUrl(itemSearch, shop.alias, '[{key:"page",val:"'.concat(itemPage.pageCount).concat('"}]'))}">Cuối</a></li></c:if>


            </ul>
        </div><!--Danh mục sản phẩm-->

    </div><!--Right content-->
    <div class="internal-text">
        <h1>${shopCategory==null?shop.title:shopCategory.name}</h1>
    <h2>${shopCategory==null?"Sản phẩm":shopCategory.name} giá rẻ, cập nhật từ ${shop.title} tại chodientu.vn</h2>
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