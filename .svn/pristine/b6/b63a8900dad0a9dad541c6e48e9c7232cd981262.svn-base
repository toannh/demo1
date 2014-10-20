<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld" %>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a>
            <span class="tree-normal"></span>
            <a href="${baseUrl}/tim-kiem.html">Sản phẩm</a>
            <c:forEach items="${parentCategories}" var="pCate">
                <c:if test="${pCate.id == category.id}">
                    <span class="tree-before"></span>
                    <span typeof="v:Breadcrumb" >
                        <a rel="v:url" property="v:title"  class="last-item" href="${baseUrl}${url:modelBrowseUrl(modelSearch, pCate.name, '[{op:"mk",key:"cid",val:"'.concat(pCate.id).concat('"},{key:"order",val:"0"},{key:"page",val:"0"},{key:"models",op:"rm"}]'))}">${pCate.name}</a>
                    </span>
                </c:if>
                <c:if test="${pCate.id != category.id}">
                    <span class="tree-normal"></span>
                    <span typeof="v:Breadcrumb" >
                        <a rel="v:url" property="v:title" href="${baseUrl}${url:modelBrowseUrl(modelSearch, pCate.name, '[{op:"mk",key:"cid",val:"'.concat(pCate.id).concat('"},{key:"order",val:"0"},{key:"page",val:"0"},{key:"models",op:"rm"}]'))}">${pCate.name}</a>
                    </span>
                </c:if>
            </c:forEach>

            <span class="tree-after"></span>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <div class="bg-white">
            <div class="pd-detail model-detail">
                <div class="pd-title">
                    <div class="sf-row">
                        <div class="model-pushnew"><a class="btn btn-danger btn-lg" href="${baseUrl}/user/dang-ban.html?mid=${model.id}">Đăng bán sản phẩm này</a></div>
                        <a class="pd-brand" href="${baseUrl}${url:manufacturerUrl(model.manufacturerId)}">${model.manufacturerName}</a>
                    </div>
                    <div class="sf-row"><h1>${model.name}</h1></div>
                    <div class="sf-row">
                        <div class="star-outer">
                            <c:set var="score"><fmt:formatNumber value="${model.reviewScore}" maxFractionDigits="0"/></c:set>
                            <c:forEach begin="1" end="${score}">
                                <span class="icon-star"></span>
                            </c:forEach>
                            <c:forEach begin="1" end="${5 - score}">
                                <span></span>
                            </c:forEach>
                        </div>
                        <c:if test="${model.recommendedCount >0 || model.reviewCount>0}">
                            <span class="text-danger">${text:percentFormat(model.recommendedCount/(model.reviewCount >0 ?model.reviewCount:1))}%</span> khuyên nên mua; điểm đánh giá: ${text:numberFormat(model.reviewScore)} điểm / ${model.reviewCount} đánh giá 
                        </c:if>
                        <c:if test="${model.recommendedCount <= 0 && model.reviewCount <= 0}">
                            Chưa có đánh giá hay nhận xét nào cho model này
                        </c:if>
                    </div>
                </div><!-- pd-title -->
                <div class="pd-content">
                    <div class="pd-left">
                        <div class="image-view">
                            <c:if test="${model.images != null && fn:length(model.images)>0}">
                                <a href="${model.images[0]}" class ="cloud-zoom" id="zoom1" rel="adjustX: 10, adjustY:-4">
                                    <img src="${model.images[0]}" alt="${model.name}" />
                                </a>
                            </c:if>
                            <c:if test="${model.images == null || fn:length(model.images)<=0}">
                                <a href="${baseUrl}/market/images/no-image-product.png" class ="cloud-zoom" id="zoom1" rel="adjustX: 10, adjustY:-4">
                                    <img src="${baseUrl}/market/images/no-image-product.png" alt="${model.name}" />
                                </a>
                            </c:if>
                        </div>
                        <div class="image-desc"><span class="icon16-zoom"></span>Di chuột vào ảnh để xem hình to hơn</div>
                        <div class="pd-slider">
                            <ul class="imgdetail-slider jcarousel jcarousel-skin-tango">

                                <c:if test="${model.images != null && fn:length(model.images)>1}">
                                    <c:forEach items="${model.images}" var="img">
                                        <li>
                                            <div class="is-item">
                                                <a href="${img}" class="cloud-zoom-gallery" rel="useZoom: 'zoom1', smallImage: '${img}'">
                                                    <img class="zoom-tiny-image" src="${img}" alt = "${model.name}"/>
                                                </a>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </c:if>
                            </ul>
                        </div><!--pd-slider-->
                        <div class="pd-share">
                            <ul>
                                <li class="pd-share-fb">
                                    <div id="fb-root"></div>
                                    <div class="fb-share-button" data-href="${baseUrl}/model/${model.id}/${text:createAlias(model.name)}.html" data-type="button_count"></div>
                                </li>
                                <!--                                <li class="pd-like-cdt"><a class="btn-like-cdt" href="#">Quan tâm</a><span class="like-count">343</span></li>
                                                                <li><a href="#"><span class="icon30-email"></span></a></li>-->
                            </ul>
                        </div><!--pd-share-->
                    </div><!-- pd-left -->
                    <div class="pd-center">
                        <div class="model-info">
                            <c:if test="${model.properties != null && fn:length(model.properties) > 0 && cateProperties!=null && fn:length(cateProperties)>0}">
                                <c:forEach var="property" items="${model.properties}" end="5">
                                    <div class="sf-row">
                                        <p class="mi-bullet">
                                            <span class="icon-modelbullet"></span>
                                            <c:forEach var="cateProperty" items="${cateProperties}">
                                                <c:if test="${cateProperty.id==property.categoryPropertyId}">
                                                    ${cateProperty.name}
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${property.categoryPropertyValueIds != null && fn:length(property.categoryPropertyValueIds) > 0 && catePropertyValues!=null && fn:length(catePropertyValues)>0}">
                                                <c:forEach var="cateValue" items="${catePropertyValues}">
                                                    <c:if test="${cateValue.id==property.categoryPropertyValueIds[0]}">
                                                        : ${cateValue.name}
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${(property.categoryPropertyValueIds == null || fn:length(property.categoryPropertyValueIds) <= 0 ) && property.inputValue!=null && property.inputValue!=''}">
                                                : ${property.inputValue}
                                            </c:if>
                                        </p>
                                    </div>
                                </c:forEach>
                            </c:if>                        
                            <br />
                            <div class="sf-row"><a href="#tab2">Xem toàn bộ thông tin sản phẩm</a></div>
                            <div class="topreview">
                            </div>
                            <div class="sf-row">» Ý kiến của bạn về sản phẩm là gì? Hãy cùng chia sẻ <a href="#review"><b>tại đây</b></a></div>
                            <div class="sf-row">ChợĐiệnTử tìm thấy <b>${model.itemCount}</b> nơi bán sản phẩm này. 
                                <c:if test="${model.oldMinPrice>0 || model.newMinPrice>0}">Giá thấp nhất: 
                                    <c:set value="${model.newMinPrice>0?model.newMinPrice:0}" var="priceNewMin"></c:set>
                                    <c:set value="${model.oldMinPrice>0?model.oldMinPrice:0}" var="priceOldMin"></c:set>
                                    <c:if test="${priceOldMin>0}">
                                        <c:set value="${priceOldMin}" var="priceTK"></c:set>
                                    </c:if>
                                    <c:if test="${priceNewMin>0}">
                                        <c:set value="${priceNewMin}" var="priceTK"></c:set>
                                    </c:if>
                                    <c:if test="${model.oldMinPrice>0 && model.newMinPrice>0}">
                                        <c:set value="${priceNewMin>priceOldMin?priceOldMin:priceNewMin}" var="priceTK"></c:set>
                                    </c:if>
                                    <span class="text-danger"><b>${text:numberFormat(priceTK)} <sup>đ</sup></b></span>
                                </c:if>
                            </div>
                        </div>
                    </div><!-- pd-center -->
                    <div class="pd-sidebar">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h5>ChợĐiệnTử gợi ý người bán:</h5>
                                <p><span class="icon16-checkok"></span>Chấp nhận thanh toán online qua Ngân lượng</p>
                                <p><span class="icon16-checkok"></span>Giao hàng thu tiền tại nhà bởi Shipchung</p>
                            </div>
                            <ul class="list-group">
                                <c:if test="${model.itemCount>0 && itemHints != null && fn:length(itemHints)>0}">
                                    <c:forEach items="${itemHints}" var="item">
                                        <li class="list-group-item"><span class="text-danger"><b>${text:numberFormat(item.sellPrice)} <sup>đ</sup></b></span> từ 
                                            <c:if test="${item.shopName!=null && item.shopName!=''}"> shop <a href="${baseUrl}/${item.shopName}/">${item.shopName}</a></c:if>
                                            <c:if test="${item.shopName==null || item.shopName==''}"><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(item.sellerId).concat('"}]'))}">${item.sellerName}</a></c:if>
                                            <a class="btn btn-default btn-sm" href="${baserl}/san-pham/${item.id}/${text:createAlias(item.name)}.html"><span class="icon16-search"></span>Xem</a></li>
                                        </c:forEach>
                                    <li class="list-group-item"><p>Bạn có "${model.name}"? <a href="${baseUrl}/user/dang-ban.html?mid=${model.id}">Đăng bán ngay</a> và tích hợp Ngân Lượng, Ship Chung để được hiển thị tại đây</p></li>
                                    </c:if>
                                    <c:if test="${model.itemCount<=0 || itemHints == null || fn:length(itemHints)<=0}">tích hợp Ngân Lượng, Ship Chung để được hiển thị tại đây
                                    <li class="list-group-item">
                                        <br />
                                        <p>Bạn có "${model.name}"? <a href="${baseUrl}/user/dang-ban.html?mid=${model.id}">Đăng bán ngay</a> và tích hợp Ngân Lượng, Ship Chung để được hiển thị tại đây</p>
                                        <br />
                                        <div class="text-center"><a class="btn btn-danger btn-lg" href="${baseUrl}/user/dang-ban.html?mid=${model.id}">Đăng bán ngay</a></div>
                                        <br />
                                        <br />
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div><!-- pd-sidebar -->
                    <div class="clearfix"></div>
                </div><!-- pd-content -->
            </div><!-- pd-detail -->
            <div class="page-detail">
                <div class="boxblue" id="tab1">
                    <div class="boxblue-title full-tab">
                        <ul class="pull-left move-tabclick">               	
                            <li class="active"><a href="#tab1">So sánh giá</a></li>
                            <li><a href="#tab2">Chi tiết sản phẩm</a></li>
                            <li><a href="#tab3">Đánh giá/Nhận xét sản phẩm</a></li>
                        </ul>
                    </div><!--boxblue-title-->
                    <div class="boxblue-content model_item">

                    </div><!--boxblue-content-->
                    <div class="box-control">
                        <div class="small-page model_item_page">
                        </div>
                    </div>
                </div><!--boxblue-->

                <div class="boxblue" id="tab2">
                    <div class="boxblue-title full-tab">
                        <ul class="pull-left move-tabclick">               	
                            <li><a href="#tab1">So sánh giá</a></li>
                            <li class="active"><a href="#tab2">Chi tiết sản phẩm</a></li>
                            <li><a href="#tab3">Đánh giá/Nhận xét sản phẩm</a></li>
                        </ul>
                    </div><!--boxblue-title-->
                    <div class="boxblue-content">
                        <div class="detail-content">
                            <div class="tech-intro">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <td class="col1">Nhãn hiệu</td>
                                            <td class="col2">${model.manufacturerName}</td>
                                        </tr>
                                        <tr>
                                            <td class="col1">Model</td>
                                            <td class="col2"><a href="${baseUrl}/model/${model.id}/${text:createAlias(model.name)}.html">${model.name}</a></td>
                                        </tr>
                                    </tbody>
                                </table>
                                <table class="table">
                                    <tbody>
                                        <c:if test="${model.properties != null && fn:length(model.properties) > 0}">
                                            <c:forEach var="property" items="${model.properties}">
                                                <c:if test="${property.categoryPropertyValueIds != null && fn:length(property.categoryPropertyValueIds) > 0}">
                                                    <c:set var="values" value="" />
                                                    <c:forEach var="value" items="${property.categoryPropertyValueIds}">
                                                        <c:forEach var="cateValue" items="${catePropertyValues}">
                                                            <c:if test='${cateValue.categoryPropertyId==property.categoryPropertyId && cateValue.id == value && cateValue.name!=null && cateValue.name.trim()!=""}'>
                                                                <c:set var="values" >${values} <li>${cateValue.name}</li></c:set>                                    
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:forEach>
                                                    <c:if test="${values.trim() != ''}">
                                                    <tr>
                                                        <td class="col1">
                                                            <c:forEach var="cateProperty" items="${cateProperties}">
                                                                <c:if test="${cateProperty.id==property.categoryPropertyId}">
                                                                    ${cateProperty.name}
                                                                </c:if>
                                                            </c:forEach> 
                                                        </td>
                                                        <td class="col2">
                                                            <ul>
                                                                ${values}
                                                            </ul>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:if> 

                                            <c:if test="${(property.categoryPropertyValueIds == null || fn:length(property.categoryPropertyValueIds) <= 0 ) && property.inputValue!=null && property.inputValue!=''}">
                                                <tr>
                                                    <td class="col1">
                                                        <c:forEach var="cateProperty" items="${cateProperties}">
                                                            <c:if test="${cateProperty.id==property.categoryPropertyId}">
                                                                ${cateProperty.name}
                                                            </c:if>
                                                        </c:forEach> 
                                                    </td>
                                                    <td class="col2">
                                                        <ul>
                                                            ${property.inputValue}
                                                        </ul>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div><!--boxblue-content-->
                </div><!--boxblue-->
                <div class="boxblue" id="tab3">
                    <div class="boxblue-title full-tab">
                        <ul class="pull-left move-tabclick">               	
                            <li><a href="#tab1">So sánh giá</a></li>
                            <li><a href="#tab2">Chi tiết sản phẩm</a></li>
                            <li class="active"><a href="#tab3">Đánh giá/Nhận xét sản phẩm</a></li>
                        </ul>
                    </div><!--boxblue-title-->
                    <div class="boxblue-content">
                        <div class="review-outer">
                            <div class="review-top">
                                <label>Đánh giá</label>
                            </div><!--review-top-->
                            <div class="review-total">
                                <div class="col-sm-2">
                                    <c:if test="${model.recommendedCount > 0}">
                                        <h2>${text:percentFormat(model.recommendedCount/(model.reviewCount >0 ?model.reviewCount:1))}%</h2>
                                        <p>khuyên nên mua</p>
                                    </c:if>
                                    <c:if test="${model.recommendedCount <= 0}">
                                        <p>Chưa có đánh giá nào</p>
                                    </c:if>
                                </div>
                                <div class="col-sm-2">
                                    <div class="star-outer">
                                        <c:set var="score"><fmt:formatNumber value="${model.reviewScore}" maxFractionDigits="0"/></c:set>
                                        <c:forEach begin="1" end="${score}">
                                            <span class="icon-star"></span>
                                        </c:forEach>
                                        <c:forEach begin="1" end="${5 - score}">
                                            <span></span>
                                        </c:forEach>
                                    </div>
                                    <p>${text:numberFormat(model.reviewScore)} điểm / ${model.reviewCount} đánh giá </p>
                                </div>
                                <div class="col-sm-4">
                                    <div class="row">
                                        <label class="star-name">5 sao</label>
                                        <div class="star-bar"><span class="percentFiveStar" style="width:80%;" ></span></div>
                                        <label class="star-count fiveStar"></label>
                                    </div>
                                    <div class="row">
                                        <label class="star-name">4 sao</label>
                                        <div class="star-bar"><span class="percentFourStar" style="width:18%;"></span></div>
                                        <label class="star-count fourStar"></label>
                                    </div>
                                    <div class="row">
                                        <label class="star-name">3 sao</label>
                                        <div class="star-bar"><span class="percentThreeStar" style="width:2%;"></span></div>
                                        <label class="star-count threeStar"></label>
                                    </div>
                                    <div class="row">
                                        <label class="star-name">2 sao</label>
                                        <div class="star-bar"><span class="percentTwoStar" style="width:2%;"></span></div>
                                        <label class="star-count twoStar"></label>
                                    </div>
                                    <div class="row">
                                        <label class="star-name">1 sao</label>
                                        <div class="star-bar"><span class="percentOneStar" style="width:0%;"></span></div>
                                        <label class="star-count oneStar"></label>
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <a class="btn btn-primary btn-lg" href="#review">Viết đánh giá</a>
                                </div>
                            </div><!--review-total-->
                            <div class="graybox-title review_title">
                                <label class="lb-name">Đánh giá của người dùng <span class="text-danger review"></span></label>
                                <div class="lb-right"><a class="active order_time" style="cursor: pointer" onclick="model.loadModelReview(0, 2)">Mới nhất</a>&nbsp;|&nbsp;<a onclick="model.loadModelReview(0, 2, 1)" style="cursor: pointer" class="order_like">Hữu ích nhất</a></div>
                            </div><!--graybox-title-->
                            <div class="review-list">

                            </div><!--review-list-->
                            <div class="box-control">
                                <div class="small-page review">
                                </div>
                            </div><!--box-control-->
                            <c:if test="${review<=0}">
                                <div class="graybox-title" id="review">
                                    <label class="lb-name">Viết đánh giá, chia sẻ kinh nghiệm của bạn</label>
                                    <div class="lb-right">Với mỗi đánh giá bạn nhận được <span class="text-danger">200</span> xèng</div>
                                </div><!--graybox-title-->
                                <div class="form form-horizontal reviewBox">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Tiêu đề đánh giá:</label>
                                        <div class="col-sm-10">
                                            <input name="title" type="text" class="form-control" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Cho điểm:</label>
                                        <div class="col-sm-10 review">
                                            <div class="star-outer">
                                                <span for="1"></span>
                                                <span for="2"></span>
                                                <span for="3"></span>
                                                <span for="4"></span>
                                                <span for="5"></span>
                                            </div>
                                            <div class="radio">
                                                <label><input type="radio" name="rd-buy" checked value="1" /> Bạn nên mua</label>
                                            </div>
                                            <div class="radio">
                                                <label><input type="radio" name="rd-buy" value="0"/> Bạn không nên mua</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Kinh nghiệm dùng thực tế của tôi:</label>
                                        <div class="col-sm-10">
                                            <textarea name="content" rows="4" class="form-control" ></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-10 col-sm-offset-2">
                                            <input type="button" class="btn btn-primary" value="Gửi đánh giá" onclick="model.sendReview();" />
                                        </div>
                                    </div>
                                </div><!-- /form-horizontal -->
                            </c:if>
                        </div><!--review-outer-->
                    </div><!--boxblue-content-->
                </div><!--boxblue-->
                <div class="boxblue">
                    <div class="boxblue-title full-tab">
                        <ul class="pull-left">               	
                            <!--                            <li ><a href="#">Phụ kiện liên quan</a></li>-->
                            <li class="active" for="samemanuf" style="cursor: pointer"><a onclick="model.loadOtherItem(0, 'samemanuf')">Cùng hãng</a></li>
                            <li for="sameprice" style="cursor: pointer"><a onclick="model.loadOtherItem(0, 'sameprice')">Cùng mức giá</a></li>
                        </ul>
                        <div class="small-page others">

                        </div>
                    </div><!--boxblue-title-->
                    <div class="boxblue-content">
                        <div class="product-list product-other-list">

                        </div><!--product-list-->
                        <div class="clearfix"></div>
                    </div><!--boxblue-content-->
                </div><!--boxblue-->
            </div><!--page-detail-->
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
    <div class="internal-text">
<!--        <h1>Xem ${model.name} liên tục cập nhật, giá rẻ | Chợ ĐiệnTử eBay Việt Nam</h1>-->
        <h2>Xem ${model.name} giá rẻ, cập nhật liên tục tại chodientu.vn</h2>
    </div>
</div><!-- container -->

<div class="modal fade" id="ModalNormal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
                <div id="map-canvas"></div>
            </div><!-- end modal-body -->
        </div><!-- end modal-content -->
    </div><!-- end modal-dialog -->
</div><!-- end Modal -->
