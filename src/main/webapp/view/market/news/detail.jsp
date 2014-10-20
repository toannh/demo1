<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="url" uri="http://chodientu.vn/url" %>
<%@taglib prefix="text" uri="http://chodientu.vn/text" %>

<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a>
            <span class="tree-normal"></span>
            <a href="${baseUrl}/tin-tuc.html">Tin tức</a>
            <span class="tree-normal"></span>
            <a href="${url:newsCateUrl(news.categoryId,newsCategory.name)}">${newsCategory.name}</a>
            <span class="tree-normal"></span>
            <a href="javascript:void(0)">${news.title}</a>
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
                <div class="news-box">
                    <div class="news-title"><h1>${news.title}</h1></div>
                            <jsp:useBean id="date" class="java.util.Date"/>
                            <jsp:setProperty name="date" property="time" value="${news.createTime}"/>
                    <div class="news-time">
                        <span class="hide"><fmt:formatDate value="${date}" type="date" pattern="E" var="day"></fmt:formatDate></span>
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
                        </div>
                        <div class="main-content">
                        ${news.detail}
                    </div><!-- main-content -->
                </div><!-- news-box -->
            </div><!-- main -->
            <div class="clearfix"></div>
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
    <div class="internal-text">
        <!--<h1>${news.title}</h1>-->
        <h2>${news.title} tại chodientu.vn</h2>
    </div>
</div>
