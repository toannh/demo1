<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="url" uri="http://chodientu.vn/url" %>

<div class="menu-click ${aliasActive?'active':''}">
    <span class="menu-title">Danh mục sản phẩm<span class="glyphicon glyphicon-chevron-down"></span></span>
    <div class="menu-popup">
        <ul class="menu-sub1">
            <c:forEach var="item" items="${alias}" varStatus="sttItem">
                <li>
                    <a href="${baseUrl}${url:browse(item.categoryId, item.categoryName)}">
                        <h4>${item.title}</h4>
                        <h5>${item.subTitle}</h5>
                    </a>
                    <div class="menu-expand">
                        <div class="me1">
                            <h3>${item.categoryName}</h3>
                            <ul>
                                <c:set var="i" value="1" />
                                <c:forEach var="childCate" items="${childCates}" varStatus="childIndex">
                                    <c:if test="${childCate.parentId == item.categoryId && i <= 10}">
                                        <c:set var="i" value="${i+1}" />
                                        <li>
                                            <a title="${childCate.name}" href="${baseUrl}${url:browse(childCate.id, childCate.name)}">${childCate.name}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${i > 10}">
                                    <li class="viewall"><a href="${baseUrl}${url:browse(item.categoryId, item.categoryName)}">» Xem toàn bộ</a></li>
                                </c:if>


                            </ul>
                            <div class="me-brand">
                                <div id="brand-slider${sttItem.index+1}" class="carousel slide" data-ride="carousel">
                                    <!-- Indicators -->
                                    <ol class="carousel-indicators">
                                        <c:set var="j" value="0" />
                                        <c:forEach var="mamufacturer" items="${item.manufacturers}" varStatus="stt">
                                            <c:if test="${stt.index%2==0}">
                                                <li data-target="#brand-slider${sttItem.index+1}" data-slide-to="${j}" <c:if test="${stt.first}"> class="active"</c:if>></li>
                                                    <c:set var="j" value="${j+1}" />   
                                                </c:if>
                                            </c:forEach>
                                    </ol>
                                    <div class="carousel-inner">
                                        <c:if test="${not empty item.manufacturers}">
                                            <c:set var="page" value="${fn:length(item.manufacturers)/2}" />
                                            <c:if test="${page%2!=0}">
                                                <c:set var="page" value="${page+1}" />
                                            </c:if>
                                            <c:forEach varStatus="stt" begin="0" end="${page}">
                                                <div class="item <c:if test="${stt.first}">active</c:if>">
                                                    <div class="me-logo"><a href="${baseUrl}${url:manufacturerUrl(item.manufacturers[stt.index*2].id)}"><img src="${item.manufacturers[stt.index*2].imageUrl}" alt="${item.manufacturers[stt.index*2].name}"></a></div>
                                                    <div class="me-logo"><a href="${baseUrl}${url:manufacturerUrl(item.manufacturers[stt.index*2 +1].id)}"><img src="${item.manufacturers[stt.index*2 +1].imageUrl}" alt="${item.manufacturers[stt.index*2+1].name}"></a></div>
                                                </div><!--item -->


                                            </c:forEach>
                                        </c:if>


                                    </div><!-- carousel-inner -->
                                </div><!-- heartslider -->
                            </div> <!-- /me-brand -->
                        </div><!-- /me1 -->
                        <div class="me2">
                            <c:forEach var="topic" items="${item.topics}">
                                <div class="grid">
                                    <a class="img" target="_blank" href="${topic.url}"><img src="${topic.image}" alt="${topic.title}" /></a>
                                    <div class="g-content">
                                        <div class="g-row">${topic.title}</div>
                                        <div class="g-row"><a target="_blank" href="${topic.url}">» Xem ngay</a></div>
                                    </div>
                                </div><!-- /grid -->
                            </c:forEach>
                        </div><!-- /me2 -->
                        <div class="me3">
                            <div class="me3-banner"><a href="${item.bannerUrl}" title="${item.title}" ><img src="${item.image}" alt="${item.title}" /></a></div>
                            <div class="me3-smallbanner">
                                <div class="ms-item"><a href="${item.bannerUrl1}" title="${item.title}" ><img src="${item.image1}" alt="${item.title}" /></a></div>
                                <div class="ms-item"><a href="${item.bannerUrl2}" title="${item.title}" ><img src="${item.image2}" alt="${item.title}" /></a></div>
                                <div class="ms-item"><a href="${item.bannerUrl3}" title="${item.title}" ><img src="${item.image3}" alt="${item.title}" /></a></div>
                            </div>
                        </div><!-- /me3 -->
                    </div><!--menu-expand-->
                </li>
            </c:forEach>
        </ul>
        <div class="menu-expand-click">
            <a href="${baseUrl}/danh-muc-san-pham.html">Tất cả danh mục</a>
        </div>
    </div><!--menu-popup-->
</div><!--menu-click-->