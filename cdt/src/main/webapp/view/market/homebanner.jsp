<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<div class="center-banner">
    <c:forEach items="${homeBanners}" var="homeBanner" varStatus="stt">
        <div class="cb-item"><a href="${homeBanner.url}" target="_blank"><img src="${homeBanner.image}" alt="${homeBanner.name}" /></a></div>
    </c:forEach>
</div><!--center-banner-->