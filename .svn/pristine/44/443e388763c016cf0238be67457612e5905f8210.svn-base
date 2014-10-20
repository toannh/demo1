<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<div id="heartslider" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <c:forEach items="${heartBanners}" var="heartBanner" varStatus="stt">
            <li data-target="#heartslider" data-slide-to="${stt.index}" <c:if test="${stt.index==0}"> class="active"</c:if>><img src="${heartBanner.thumb}" alt="thumb" /><span>${heartBanner.name}</span></li>
                </c:forEach>

    </ol>
    <div class="carousel-inner">
        <c:forEach items="${heartBanners}" var="heartBanner" varStatus="stt">
            <div class="item <c:if test="${stt.index==0}">active</c:if>">
                <a href="${heartBanner.url}" target="_blank"><img src="${heartBanner.image}" alt="${heartBanner.name}"></a>
            </div><!-- item -->
        </c:forEach>


    </div><!-- carousel-inner -->
    <a class="left carousel-control" href="#heartslider" data-slide="prev">
        <span class="icon-prev"></span>
    </a>
    <a class="right carousel-control" href="#heartslider" data-slide="next">
        <span class="icon-next"></span>
    </a>
</div><!-- heartslider -->