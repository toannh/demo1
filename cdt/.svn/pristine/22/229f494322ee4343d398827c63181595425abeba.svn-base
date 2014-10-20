<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="url" uri="http://chodientu.vn/url" %>
<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a>
            <span class="tree-normal"></span>
            <a href="${baseUrl}/tin-tuc.html">Tin tức</a>
            <span class="tree-normal"></span>
            <a href="javascript:void(0)">${newsCategory.name}</a>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <div class="bg-white bg-inner">
            <div class="sidebar">
                <c:forEach items="${cateList}" var="cate">
                    <c:if test="${cate.parentId=='0'}">
                        <div class="mbox">
                            <div class="mbox-title">
                                <label class="lb-name">${cate.name}</label>
                            </div><!-- mbox-title -->
                            <div class="mbox-content">
                                <div class="menusidebar">
                                    <ul id="cateList">
                                        <c:forEach items="${cateList}" var="childCate">
                                            <c:if test="${childCate.parentId==cate.id}">
                                                <li>
                                                    <a href="${baseUrl}${url:newsCateUrl(childCate.id,childCate.name)}">${childCate.name}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </div><!-- menusidebar -->
                            </div><!-- mbox-content -->
                        </div><!-- mbox -->
                    </c:if>
                </c:forEach>
                <div class="mbox">
                    <div class="mbox-title">
                        <label class="lb-name">Liên hệ trực tiếp</label>
                    </div><!-- mbox-title -->
                    <div class="mbox-content">
                        <div class="box-hotline">
                            <p class="bh-left"><span class="icon16-telgray"></span>Hotline: 1900585888</p>
                            <p class="bh-left"><span class="icon16-telgray"></span>HN: 04. 3632 0985 (107)</p>
                            <p class="bh-left"><span class="icon16-telgray"></span>TP.HCM: 08- 3811-4757 (107)</p>
                            <p class="bh-left"><span class="icon16-telgray"></span>Email: support@chodientu.vn</p>
                            <p><a href="ymsgr:sendim?hotro_hn7"><img src="http://opi.yahoo.com/online?u=hotro_hn7&amp;m=g&amp;t=5"></a><a href="skype:hotro_phn7?chat"><span class="icon16-skype"></span></a>&nbsp;&nbsp;Chăm sóc khách hàng</p>
                            <p><a href="ymsgr:sendim?chodientu_vn"><img src="http://opi.yahoo.com/online?u=chodientu_vn&amp;m=g&amp;t=5"></a><a href="skype:chodientu_vn?chat"><span class="icon16-skype"></span></a>&nbsp;&nbsp;Hỗ trợ chung</p>
                        </div>
                    </div><!-- mbox-content -->
                </div><!-- mbox -->
            </div><!-- sidebar -->
            <div class="main">
                <div class="big-title">
                    <label class="lb-name">${newsCategory.name}</label>
                </div><!-- big-title -->
                <div class="news-list">
                    <c:forEach items="${newsPage.data}" var="item">
                        <div class="grid">
                            <a class="img" href="${baseUrl}${url:newsDetailUrl(item.id,item.title)}">
                                <img src="${item.image}" alt="${item.title}"/>
                            </a>
                            <div class="g-content">
                                <div class="g-row">
                                    <a class="g-title" href="${baseUrl}${url:newsDetailUrl(item.id,item.title)}">${item.title}</a>
                                </div>
                                <div class="g-row">
                                    <span>
                                        <jsp:useBean id="date" class="java.util.Date"/>
                                        <jsp:setProperty name="date" property="time" value="${item.createTime}"/>
                                        <span class="hide">
                                            <fmt:formatDate value="${date}" type="date" pattern="E" var="day"></fmt:formatDate></span>
                                            <c:choose>
                                                <c:when test="${day=='Mon'}">
                                                Thứ hai,
                                            </c:when>
                                            <c:when test="${day=='Tue'}">
                                                Thứ ba,
                                            </c:when>
                                            <c:when test="${day=='Wed'}">
                                                Thứ tư,
                                            </c:when>
                                            <c:when test="${day=='Thu'}">
                                                Thứ năm,
                                            </c:when>
                                            <c:when test="${day=='Fri'}">
                                                Thứ sáu,
                                            </c:when>
                                            <c:when test="${day=='Sat'}">
                                                Thứ bảy,
                                            </c:when>
                                            <c:when test="${day=='Sat'}">
                                                Chủ nhật,
                                            </c:when>
                                        </c:choose>
                                        <fmt:formatDate value="${date}" type="date" pattern="dd/MM/yyyy"></fmt:formatDate> -
                                        <fmt:formatDate value="${date}" type="date" pattern="HH:mm"></fmt:formatDate>
                                        </span>
                                    </div>
                                    <div class="g-row">
                                        <!-- Generate short description -->
                                    <c:set var="originalDetail" value="${fn:substring(fn:trim(item.detail), 0, 180)}" />
                                    ${originalDetail} ...
                                </div>
                                <div class="g-row"><a href="${baseUrl}${url:newsDetailUrl(item.id,item.title)}">&gt; Xem chi tiết</a></div>
                            </div>
                        </div><!-- grid -->
                    </c:forEach>
                </div><!-- news-list -->
                <div class="page-ouner clearfix">
                    <ul class="pagination pull-right">
                        <c:if test="${newsPage.pageCount > 1}">
                            <!-- Generate first link -->
                            <c:if test="${newsSearch.pageIndex!=0}">
                                <li><a href="${baseUrl}${url:newsCateUrl(newsCategory.id,newsCategory.name)}">«</a></li>
                                </c:if>
                            <!--/ End first link -->
                            <!-- Number of page to display -->
                            <c:set var="displayLink" value="3"></c:set>
                                <!-- Set begin link and end link -->
                            <c:if test="${newsSearch.pageIndex==0}">
                                <c:set value="1" var="beginLink"></c:set>
                                <c:set value="${newsPage.pageCount}" var="endLink"></c:set>
                            </c:if>
                            <c:if test="${newsSearch.pageIndex!=0}">
                                <c:set value="${newsSearch.pageIndex}" var="beginLink"></c:set>
                                <c:set value="${newsSearch.pageIndex+2}" var="endLink"></c:set>
                            </c:if>
                            <c:if test="${(newsSearch.pageIndex+1)==newsPage.pageCount}">
                                <c:if test="${transactions.pageIndex==1}">
                                    <c:set value="${newsPage.pageCount-displayLink+2}" var="beginLink"></c:set>
                                </c:if>
                                <c:if test="${transactions.pageIndex!=1}">
                                    <c:set value="${newsPage.pageCount-displayLink+1}" var="beginLink"></c:set>
                                </c:if>
                                <c:set value="${newsPage.pageCount}" var="endLink"></c:set>
                            </c:if>
                            <!--/ End set begin and end link -->
                            <!-- Generate link to other page -->
                            <c:forEach begin="${beginLink}" end="${endLink}" step="1" var="p">
                                <li class="${(newsSearch.pageIndex+1)==p ?'active':''}"><a href="${baseUrl}${url:newsCateUrl(newsCategory.id,newsCategory.name)}?page=${p}">${p}</a></li>
                                </c:forEach>
                            <!--/ End generate link -->
                            <!-- Generate last link -->
                            <c:if test="${(newsSearch.pageIndex+1)!=newsPage.pageCount}">
                                <li><a href="${baseUrl}${url:newsCateUrl(newsCategory.id,newsCategory.name)}?page=${newsPage.pageCount}">»</a></li>
                                </c:if>
                            </c:if>
                    </ul>
                </div><!-- page-ouner -->
            </div><!-- main -->
            <div class="clearfix"></div>
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
    <div class="internal-text">
        <h1>${newsCategory.name}</h1>
        <h2>${newsCategory.name} cập nhật tại chodientu.vn</h2>
    </div>
</div>