<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="category-list-icon" id="category-home">
        <ul>
            <li class="cl-home active">
                <a href="#category-home">
                    <span class="icon42-home-white"></span>
                </a>
            </li>
            <c:forEach items="${categories}" var="item" varStatus="stt" >
                <li class="${stt.index <= 0?'active':''}" >
                    <a href="#${item.id}" title="${item.name}" >
                        <span class="${item.icon != null?item.icon:'icon42-fashion'}-white"></span>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div><!-- /category-list-icon -->
    <div class="category-box">
        <c:forEach items="${categories}" var="item" varStatus="stt" >
            <div class="category-level" id="${item.id}" for="cateRoot" >
                <a href="${baseUrl}${url:browse(item.id, item.name)}"><span class="${item.icon != null?item.icon:'icon42-fashion'}-blue"></span>
                    ${item.name}
                </a>
            </div> 
            <c:forEach items="${item.categoris}" var="sub" varStatus="index" >
                <c:if test="${!sub.leaf}">
                    <div class="category-list"  for="child" child="${item.id}" cateId="${sub.id}" rootId="${item.id}" >
                        <a class="category-label" href="${baseUrl}${url:browse(sub.id, sub.name)}">${sub.name}</a>
                        <ul class="category-ul" >
                            <c:set var="weight" value="1" />
                            <c:forEach items="${sub.categoris}" var="child" >
                                <li style="display: ${weight > 6?'none':'block'}" >
                                    <a href="${baseUrl}${url:browse(child.id, child.name)}">${child.name}</a>
                                </li>
                                <c:set var="weight" value="${weight+1}" />
                            </c:forEach>
                        </ul>
                        <a class="cb-more" style="display: ${weight <= 6?'none':'block'};cursor: pointer" >
                            Xem thêm
                            <span class="icon-category-more"></span>
                        </a>
                    </div><!--category-list-->
                </c:if>
            </c:forEach>
            <div class="category-list cateLv2" for="child" child="${item.id}" >
                <ul class="category-ul" >
                    <c:set var="weight" value="1" />
                    <c:forEach items="${item.categoris}" var="sub" varStatus="index" >
                        <c:if test="${sub.leaf}">
                            <li style="display: ${weight > 6?'none':'block'}" >
                                <a style="color: #000; font-weight: bold;" href="${baseUrl}${url:browse(sub.id, sub.name)}">${sub.name}</a>
                            </li>
                            <c:set var="weight" value="${weight+1}" />
                        </c:if>
                    </c:forEach>
                </ul>
                <a class="cb-more" style="display: ${weight <= 6?'none':'block'} ; cursor: pointer" >
                    Xem thêm
                    <span class="icon-category-more"></span>
                </a>
            </div>
            <div class="category-line"></div>
        </c:forEach>
    </div><!--category-box-->
</div><!-- container -->