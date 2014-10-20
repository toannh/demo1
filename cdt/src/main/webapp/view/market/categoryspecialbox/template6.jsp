<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@ taglib prefix="url" uri="http://chodientu.vn/url" %>
<c:forEach items="${featuredcategory.categorySubs}" var="categorySubItem">
    <div class="box box-brown">
        <div class="box-title">
            <label class="lb-name">${featuredcategory.categoryName}</label>
            <div class="pull-right">
                <ul class="tiny-ul ${featuredcategory.id}">
                    <c:forEach items="${featuredcategory.categorySubHome}" var="cate">
                        <li><a href="${baseUrl}${url:browse(cate.id, cate.name)}">${cate.name}</a></li>
                        </c:forEach>

                </ul>
            </div>
        </div><!-- box-title -->
        <div class="box-content" id="${featuredcategory.id}">
            <div class="box-product bp-template6">
                <div class="bp-brand6">
                    <c:forEach items="${categorySubItem.categoryManufacturerHomes}" var="logo">
                        <div class="bb-item"><a href="${baseUrl}${url:manufacturerUrl(logo.manufacturerId)}"><img src="${logo.image}" alt="logo"></a></div>
                            </c:forEach>
                </div>
                <c:forEach items="${categorySubItem.categoryItemHomes}" var="categoryItemHome">
                    <c:forEach items="${featuredcategory.items}" var="item">
                        <c:if test="${categoryItemHome.position==1}">
                            <c:if test="${item.id==categoryItemHome.itemId}">
                                <div class="home-item bp1">
                                    <div class="hoi-inner">
                                        <c:if test="${item.listingType=='AUCTION'}">
                                            <span class="hoi-bid"></span>
                                        </c:if>
                                        <c:if test="${item.listingType == 'BUYNOW' && (text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))>0}">
                                            <span class="hoi-sale">-${text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent)}%</span>
                                        </c:if> 
                                        <a class="hoi-thumblink" href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html"><img src="${categoryItemHome.image}" alt="product"></a>
                                        <div class="hoi-title">
                                            <div class="hoi-row"><a href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">${categoryItemHome.title}</a></div>
                                            <div class="hoi-row"><span class="hoi-price">${text:sellPrice(item.sellPrice,item.discount,item.discountPrice,item.discountPercent)} <sup class="u-price">đ</sup></span>
                                                <c:if test="${(text:discountPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))!='0'}">
                                                    <span class="hoi-oldprice">${text:startPrice(item.startPrice,item.sellPrice,item.discount)} <sup class="u-price">đ</sup></span>
                                                </c:if>    
                                            </div>
                                        </div>
                                        <div class="hoi-view">
                                            <a class="hoi-btn" href="javascript:;" onclick="market.quickview('${item.id}')">Xem nhanh</a>
                                            <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                        </div>
                                    </div>
                                </div><!-- home-item -->
                            </c:if>
                        </c:if>
                        <c:if test="${categoryItemHome.position==2}">
                            <c:if test="${item.id==categoryItemHome.itemId}">
                                <div class="home-item bp2">
                                    <div class="hoi-inner">
                                        <c:if test="${item.listingType=='AUCTION'}">
                                            <span class="hoi-bid"></span>
                                        </c:if>
                                        <c:if test="${item.listingType == 'BUYNOW' && (text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))>0}">
                                            <span class="hoi-sale">-${text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent)}%</span>
                                        </c:if> 
                                        <a class="hoi-thumblink" href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html"><img src="${categoryItemHome.image}" alt="product"></a>
                                        <div class="hoi-title">
                                            <div class="hoi-row"><a href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">${categoryItemHome.title}</a></div>
                                            <div class="hoi-row"><span class="hoi-price">${text:sellPrice(item.sellPrice,item.discount,item.discountPrice,item.discountPercent)} <sup class="u-price">đ</sup></span>
                                                <c:if test="${(text:discountPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))!='0'}">
                                                    <span class="hoi-oldprice">${text:startPrice(item.startPrice,item.sellPrice,item.discount)} <sup class="u-price">đ</sup></span>
                                                </c:if>    
                                            </div>
                                        </div>
                                        <div class="hoi-view">
                                            <a class="hoi-btn" href="javascript:;" onclick="market.quickview('${item.id}')">Xem nhanh</a>
                                            <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                        </div>
                                    </div>
                                </div><!-- home-item -->
                            </c:if>
                        </c:if>
                        <c:if test="${categoryItemHome.position==3}">
                            <c:if test="${item.id==categoryItemHome.itemId}">
                                <div class="home-item bp3">
                                    <div class="hoi-inner">
                                        <c:if test="${item.listingType=='AUCTION'}">
                                            <span class="hoi-bid"></span>
                                        </c:if>
                                        <c:if test="${item.listingType == 'BUYNOW' && (text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))>0}">
                                            <span class="hoi-sale">-${text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent)}%</span>
                                        </c:if> 
                                        <a class="hoi-thumblink" href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html"><img src="${categoryItemHome.image}" alt="product"></a>
                                        <div class="hoi-title">
                                            <div class="hoi-row"><a href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">${categoryItemHome.title}</a></div>
                                            <div class="hoi-row"><span class="hoi-price">${text:sellPrice(item.sellPrice,item.discount,item.discountPrice,item.discountPercent)} <sup class="u-price">đ</sup></span>
                                                <c:if test="${(text:discountPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))!='0'}">
                                                    <span class="hoi-oldprice">${text:startPrice(item.startPrice,item.sellPrice,item.discount)} <sup class="u-price">đ</sup></span>
                                                </c:if>    
                                            </div>
                                        </div>
                                        <div class="hoi-view">
                                            <a class="hoi-btn" href="javascript:;" onclick="market.quickview('${item.id}')">Xem nhanh</a>
                                            <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                        </div>
                                    </div>
                                </div><!-- home-item -->
                            </c:if>
                        </c:if>
                        <c:if test="${categoryItemHome.position==4}">
                            <c:if test="${item.id==categoryItemHome.itemId}">
                                <div class="home-item bp4">
                                    <div class="hoi-inner">
                                        <c:if test="${item.listingType=='AUCTION'}">
                                            <span class="hoi-bid"></span>
                                        </c:if>
                                        <c:if test="${item.listingType == 'BUYNOW' && (text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))>0}">
                                            <span class="hoi-sale">-${text:percentPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent)}%</span>
                                        </c:if> 
                                        <a class="hoi-thumblink" href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html"><img src="${categoryItemHome.image}" alt="product"></a>
                                        <div class="hoi-title">
                                            <div class="hoi-row"><a href="${basicUrl}/san-pham/${item.id}/${text:createAlias(item.name)}.html">${categoryItemHome.title}</a></div>
                                            <div class="hoi-row"><span class="hoi-price">${text:sellPrice(item.sellPrice,item.discount,item.discountPrice,item.discountPercent)} <sup class="u-price">đ</sup></span>
                                                <c:if test="${(text:discountPrice(item.startPrice,item.sellPrice,item.discount,item.discountPrice,item.discountPercent))!='0'}">
                                                    <span class="hoi-oldprice">${text:startPrice(item.startPrice,item.sellPrice,item.discount)} <sup class="u-price">đ</sup></span>
                                                </c:if>    
                                            </div>
                                        </div>
                                        <div class="hoi-view">
                                            <a class="hoi-btn" href="javascript:;" onclick="market.quickview('${item.id}')">Xem nhanh</a>
                                            <a class="hoi-btn" href="#"><span class="icon24-star"></span></a>
                                        </div>
                                    </div>
                                </div><!-- home-item -->
                            </c:if>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </div><!-- box-product -->
        </div><!-- box-content -->
    </div>
</c:forEach>