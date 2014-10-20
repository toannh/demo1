<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<div class="homenews">
    <div class="homenews-col">
        <div class="homenews-title"><span class="icon20-user"></span>Câu chuyện thành công</div>
        <div class="homestory">
            <ul class="homestory-slider jcarousel jcarousel-skin-tango">
                <c:forEach items="${featuredNews}" var="featuredNewse">
                    <c:if test="${featuredNewse.type==1}">
                        <li>
                            <div class="grid">
                                <div class="img"><img src="${featuredNewse.image}" alt="${featuredNewse.name}" /></div>
                                <div class="g-content">
                                    <div class="g-row"><b>${featuredNewse.title}</b></div>
                                    <div class="g-row">
                                        ${featuredNewse.content}
                                    </div>
                                    <div class="g-right"><b>${featuredNewse.name}</b></div>
                                    <div class="g-right">${featuredNewse.nameCompany}</div>
                                    <div class="g-right">shop: <a href="${featuredNewse.url}" target="_blank" rel="nofollow">${featuredNewse.url}</a></div>
                                </div>
                            </div><!-- grid -->
                        </li>
                    </c:if>
                </c:forEach>

            </ul>
        </div><!-- homestory -->
        <div class="homenews-more"><a href="${baseUrl}/tin-tuc/goc-kinh-nghiem/258010553833.html">» Xem toàn bộ</a></div>
    </div><!-- homenews-col -->
    <div class="homenews-col">
        <div class="homenews-title"><span class="icon20-comment"></span>Nhận xét của khách hàng</div>
        <div class="homecomment">
            <ul class="homecomment-slider jcarousel jcarousel-skin-tango">
                <c:forEach items="${featuredNews}" var="featuredNewse">
                    <c:if test="${featuredNewse.type==2}">
                        <li>
                            <div class="grid">
                                <div class="img"><img src="${featuredNewse.image}" alt="${featuredNewse.name}" /></div>
                                <div class="g-content">
                                    <div class="g-row">
                                        ${featuredNewse.content}                            
                                    </div>
                                    <div class="g-right"><b>${featuredNewse.name}</b></div>
                                      <c:if test="${featuredNewse.nameShop!=null && featuredNewse.nameShop!=''}">
                                        <div class="g-right">Chủ shop: <a href="${featuredNewse.url}" target="_blank" rel="nofollow">${featuredNewse.nameShop}</a></div>
                                        </c:if>
                                </div>
                            </div><!-- grid -->
                        </li>
                    </c:if>
                </c:forEach>

            </ul>
        </div><!-- homecomment -->
        <div class="homenews-more"><a href="${baseUrl}/tin-tuc/y-kien-khach-hang/58295249532.html">» Xem toàn bộ</a></div>
    </div><!-- homenews-col -->
    <div class="homenews-col">
        <div class="homenews-title"><span class="icon20-news"></span>Tin tức/ Sự kiện</div>
        <ul class="homenews-ul">
            <c:forEach items="${newsHomeBoxs}" var="newsHomeBox">
                <li><a href="${baseUrl}${url:newsDetailUrl(newsHomeBox.id,newsHomeBox.title)}">${newsHomeBox.title}</a></li>
                </c:forEach>
        </ul>
        <div class="homenews-more text-right"><a href="${baseUrl}/tin-tuc.html" rel="nofollow">» Xem toàn bộ</a></div>
    </div><!-- homenews-col -->
</div><!-- homenews -->