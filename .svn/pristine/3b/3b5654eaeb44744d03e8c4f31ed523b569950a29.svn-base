<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<c:forEach items="${featuredCategorys}" var="featuredcategory">
    <c:set var="featuredcategory" value="${featuredcategory}" scope="request" />
    <c:if test="${featuredcategory.template=='template1'}">
        <jsp:include page="/view/market/categoryspecialbox/template1.jsp"></jsp:include>
    </c:if>

    <c:if test="${featuredcategory.template=='template2'}">
        <jsp:include page="/view/market/categoryspecialbox/template2.jsp"></jsp:include>
    </c:if>
    <c:if test="${featuredcategory.template=='template3'}">
        <jsp:include page="/view/market/categoryspecialbox/template3.jsp"></jsp:include>
    </c:if>
    <c:if test="${featuredcategory.template=='template4'}">
        <jsp:include page="/view/market/categoryspecialbox/template4.jsp"></jsp:include>
    </c:if>

    <c:if test="${featuredcategory.template=='template5'}">
        <div class="twobox ipad-hidden">
            <jsp:include page="/view/market/categoryspecialbox/template5.jsp"></jsp:include>
        </c:if>
        <c:if test="${featuredcategory.template=='template6'}">
            <jsp:include page="/view/market/categoryspecialbox/template6.jsp"></jsp:include>
            </div>
    </c:if>

</c:forEach>
