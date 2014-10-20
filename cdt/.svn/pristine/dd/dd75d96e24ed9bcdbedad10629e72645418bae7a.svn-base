<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld" %>
<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a>
            <span class="tree-before"></span>
            <a class="last-item" href="${baseUrl}/tim-kiem-model.html?keywork=${modelSearch.keyword}">Tìm kiếm model</a>
            <span class="tree-after"></span>

            <c:if test="${fn:length(modelSearch.manufacturerIds) > 0}">
                <c:forEach items="${modelSearch.manufacturerIds}" var="manId">
                    <div style="display:none" for="manufacturer" val="${manId}" class="filter-item"></div>
                </c:forEach>
            </c:if>
            <c:if test="${fn:length(modelSearch.properties) > 0}">
                <c:forEach items="${modelSearch.properties}" var="property">
                    <div for="property" name="${property.name}" value="${property.value}" operator="${property.operator}" class="filter-item">
                        <span class="filter-lb">${property.name}: ${property.value}</span>
                        <span class="filter-remove"></span>
                    </div>
                </c:forEach>
            </c:if>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <c:if test="${topBanner != null}">
        <div class="topbanner">
            <a href="${topBanner.link}"><img src="${topBanner.banner}" alt="${topBanner.title}" /></a>
        </div>
    </c:if>
    <div class="bground">
        <div class="bg-white">
            <div class="sidebar">
                <div class="big-label">
                    <div class="bl-inner">
                        <h1>Danh mục
                        </h1>
                    </div>
                </div>
                <div class="menuleft">
                    <ul>
                        <c:forEach items="${listCategories}" var="cate">
                            <li for="${cate.id}" class="<c:if test="${!cate.leaf}" >sub-menu</c:if><c:if test="${category.id == cate.id}" > active</c:if>">
                                <a href="${baseUrl}${url:modelBrowseUrl(modelSearch, cate.name, '[{op:"mk",key:"cid",val:"'.concat(cate.id).concat('"},{key:"order",val:"0"},{key:"page",val:"0"}]'))}">${cate.name}<span></span></a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="pbox" for="manufacturer">
                </div><!--pbox-->
                <div class="pbox more-filter" for="property"></div><!--pbox-->
                <c:if test="${category.metaDescription != null && category.metaDescription!=''}"><div class="panel-intro">${category.metaDescription}</div></c:if><!--pannel-intro-->
            </div><!-- sidebar -->
            <div class="main">
                <div class="mbox">
                    <div class="mbox-title full-tab">
                        <ul>
                            <li><a href="${baseUrl}${url:browseToItem(modelSearch, category.name)}" class="itemCount">Sản phẩm</a></li>
                            <li class="active"><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{op:"rm",key:"page"},{op:"rm",key:"order"}]')}">Model (${text:numberFormat(modelPage.dataCount)})</a></li>
                        </ul>
                    </div><!-- mbox-title -->

                    <div class="mbox-content">
                        <div class="box-control">
                            <a class="btn btn-danger" href="#">So sánh</a>
                            <label>Sắp xếp theo:</label>
                            <ul>
                                <li for="order_0"><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"order",val:"0"}]')}">Mới nhất</a></li>
                                <li for="order_1"><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"order",val:"1"}]')}">Rẻ nhất</a></li>
                            </ul>
                            <ul class="pagination">
                                    <c:if test="${modelPage.pageIndex > 3}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"1"}]')}" >&laquo;</a></li></c:if>
                                    <c:if test="${modelPage.pageIndex > 2}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageIndex).concat('"}]'))}" >&lt;</a></li></c:if>
                                    <c:if test="${modelPage.pageIndex >= 2}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageIndex-1).concat('"}]'))}" >${modelPage.pageIndex-1}</a></li></c:if>
                                    <c:if test="${modelPage.pageIndex >= 1}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageIndex).concat('"}]'))}" >${modelPage.pageIndex}</a></li></c:if>
                                    <li class="active"><a>${modelPage.pageIndex + 1}</a></li>
                                    <c:if test="${modelPage.pageCount - modelPage.pageIndex >= 2}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageIndex+2).concat('"}]'))}">${modelPage.pageIndex+2}</a></li></c:if>
                                    <c:if test="${modelPage.pageCount - modelPage.pageIndex > 3}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageIndex+3).concat('"}]'))}">${modelPage.pageIndex+3}</a></li></c:if>
                                    <c:if test="${modelPage.pageCount - modelPage.pageIndex > 2}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageIndex+2).concat('"}]'))}">&gt;</a></li></c:if>
                                    <c:if test="${modelPage.pageCount - modelPage.pageIndex > 2}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageCount).concat('"}]'))}">&raquo;</a></li></c:if>
                                    </ul>
                            <div class="mobile-filter">
                                <a href="#smart-filter">Lọc sản phẩm</a>
                            </div>
                        </div><!--box-control-->
                        <div class="product-list">
                            <c:forEach items="${modelPage.data}" var="model"> 
                                <div class="model-item">
                                    <input type="checkbox" value="" name="" />
                                    <div class="item-img"><a href="${baseUrl}/model/${model.id}/${text:createAlias(model.name)}.html"><img src="${(model.images != null && fn:length(model.images) >0)?model.images[0]:staticUrl.concat('/market/images/no-image-product.png')}"  alt="${model.name}" title="${model.name}"/></a></div>
                                    <div class="item-text">
                                        <a class="item-link" href="${baseUrl}/model/${model.id}/${text:createAlias(model.name)}.html"><span>Model:&nbsp;</span>${model.name}</a>
                                        <c:if test="${model.properties != null && fn:length(model.properties) > 0 && categoryProperties!=null && fn:length(categoryProperties)>0}">
                                            <c:forEach var="property" items="${model.properties}" end="3">
                                                <div class="item-row">
                                                    <span class="icon-modelbullet"></span>
                                                    <c:forEach var="cateProperty" items="${categoryProperties}">
                                                        <c:if test="${cateProperty.id==property.categoryPropertyId}">
                                                            ${cateProperty.name}
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${property.categoryPropertyValueIds != null && fn:length(property.categoryPropertyValueIds) > 0 && categoryPropertyValues!=null && fn:length(categoryPropertyValues)>0}">
                                                        <c:forEach var="cateValue" items="${categoryPropertyValues}">
                                                            <c:if test="${cateValue.id==property.categoryPropertyValueIds[0]}">
                                                                : ${cateValue.name}
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${(property.categoryPropertyValueIds == null || fn:length(property.categoryPropertyValueIds) <= 0 ) && property.inputValue!=null && property.inputValue!=''}">
                                                        : ${property.inputValue}
                                                    </c:if>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <div class="item-row">Có <b>${model.itemCount}</b> tin bán</div>
                                        <c:if test="${model.newMinPrice > 0}">
                                            <div class="item-row">Hàng mới từ:&nbsp;<span class="item-price">${text:numberFormat(model.newMinPrice)} VNĐ</span></div>
                                        </c:if>
                                        <c:if test="${model.oldMinPrice > 0}">
                                            <div class="item-row">Hàng cũ từ:&nbsp;<span class="item-price">${text:numberFormat(model.oldMinPrice)} VNĐ</span></div>
                                        </c:if>
                                    </div>
                                </div><!--model-item-->
                            </c:forEach>
                            <div class="clearfix"></div>
                        </div><!-- product-list -->
                        <div class="box-bottom">
                            <ul class="pagination">
                                    <c:if test="${modelPage.pageIndex > 3}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"1"}]')}" >&laquo;</a></li></c:if>
                                    <c:if test="${modelPage.pageIndex > 2}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageIndex).concat('"}]'))}" >&lt;</a></li></c:if>
                                    <c:if test="${modelPage.pageIndex >= 2}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageIndex-1).concat('"}]'))}" >${modelPage.pageIndex-1}</a></li></c:if>
                                    <c:if test="${modelPage.pageIndex >= 1}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageIndex).concat('"}]'))}" >${modelPage.pageIndex}</a></li></c:if>
                                    <li class="active"><a>${modelPage.pageIndex + 1}</a></li>
                                    <c:if test="${modelPage.pageCount - modelPage.pageIndex >= 2}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageIndex+2).concat('"}]'))}">${modelPage.pageIndex+2}</a></li></c:if>
                                    <c:if test="${modelPage.pageCount - modelPage.pageIndex > 3}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageIndex+3).concat('"}]'))}">${modelPage.pageIndex+3}</a></li></c:if>
                                    <c:if test="${modelPage.pageCount - modelPage.pageIndex > 2}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageIndex+2).concat('"}]'))}">&gt;</a></li></c:if>
                                    <c:if test="${modelPage.pageCount - modelPage.pageIndex > 2}"><li><a href="${baseUrl}${url:modelBrowseUrl(modelSearch, category.name, '[{key:"page",val:"'.concat(modelPage.pageCount).concat('"}]'))}">&raquo;</a></li></c:if>
                                    </ul>
                        </div><!--box-bottom-->
                        <%--<c:if test="${fn:length(munufData.data) > 0}">
                            <div class="boxlogo-title"><img class="brand-title" src="${staticUrl}/market/images/brand-title.png" alt="Thương hiệu nổi tiếng" /></div>
                            <div class="boxlogo-content">
                                <c:forEach items="${munufData.data}" var="logo">
                                    <c:if test="${logo.imageUrl != null && logo.imageUrl != ''}">
                                        <div class="logoitem"><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{op: "mk",key:"manufacturers",val:"'.concat(logo.id).concat('"}]'))}"><img src="${logo.imageUrl}" alt="${logo.name}" /></a></div>
                                            </c:if>
                                            <c:if test="${logo.imageUrl == null || logo.imageUrl == ''}">
                                        <div class="logoitem"><a href="${baseUrl}${url:browseUrl(itemSearch, category.name, '[{op: "mk",key:"manufacturers",val:"'.concat(logo.id).concat('"}]'))}">${logo.name}</a></div>
                                        </c:if>
                                    </c:forEach>
                            </div><!--boxlogo-content-->
                            <c:if test="${munufData.pageCount > 1}">
                                <div class="box-bottom">
                                    <div class="small-page" for="manuf">
                                        <a class="small-button next" onclick="browse.loadManufactuterByPage(1);" ></a>
                                    </div>
                                </div><!--box-bottom-->
                            </c:if>
                        </c:if> --%> 
                    </div><!-- mbox-content -->
                </div><!-- mbox -->
            </div><!-- main -->
            <div class="clearfix"></div>
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
    <div class="internal-text">
        <h1>Xem catalog hàng tại ${category!=null?category.name:""} liên tục cập nhật, giá rẻ </h1>
        <h2>${category!=null?category.name:""} giá rẻ, cập nhật liên tục tại chodientu.vn</h2>
    </div>
</div><!-- container -->
<div class="modal fade" id="ModalFilter" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            </div>
            <div class="modal-body">
                <div class="filter-choose">
                    <c:if test="${fn:length(modelSearch.manufacturerIds) > 0}">
                        <c:forEach items="${modelSearch.manufacturerIds}" var="manId">
                            <div style="display:none" for="manufacturer" val="${manId}" class="filter-item"></div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${fn:length(modelSearch.properties) > 0}">
                        <c:forEach items="${modelSearch.properties}" var="property">
                            <div for="property" name="${property.name}" value="${property.value}" operator="${property.operator}" class="filter-item">
                                <span class="filter-lb">${property.name}: ${property.value}</span>
                                <span class="filter-remove"></span>
                            </div>
                        </c:forEach>
                    </c:if>

                </div><!-- filter-choose -->
                <div class="filter-other">
                    <div class="filter-left">
                        <ul>
                            <li for="manufacturer"><a>Thương hiệu</a></li>
                        </ul>
                    </div><!--filter-left-->
                    <div class="filter-right">
                        <div class="filter-title"></div>
                        <div for="manufacturer" name="Thương hiệu" class="box-check"></div>
                    </div><!--filter-right-->
                </div><!--filter-other-->
            </div><!-- end modal-body -->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Huỷ bỏ</button>
            </div>
        </div><!-- end modal-content -->
    </div><!-- end modal-dialog -->
</div><!-- end Modal -->