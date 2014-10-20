<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="http://chodientu.vn/url" %>
<jsp:useBean id="date" class="java.util.Date" />
<div class="container">
    <div class="tree-main">
        <jsp:include page="/view/market/widget/alias.jsp" />
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a>
            <span class="tree-normal"></span>
            <a href="${baseUrl}/user/${user.id}/ho-so-nguoi-ban.html">Hồ sơ người bán</a>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <div class="bg-white">
            <div class="page-userinfo">
                <div class="pu-title">Hồ sơ người bán</div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="userinfo-cv grid">
                            <c:if test="${shop.logo!=null && shop.logo != ''}"><div class="img"><a href="${shop.alias}" target="_blank"><img src="${shop.logo}" alt="avatar"></a></div>
                                <div class="g-content">
                                    <div class="g-row">Shop: <a href="${baseUrl}/${shop.alias}/" target="_blank"><b>${shop.title}</b></a></div>
                                    <div class="g-row"><p class="p-checkred"><span class="icon16-calendar"></span>Tham gia ngày: <jsp:setProperty name="date" property="time" value="${shop.createTime}" /> <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate>, ${shop.address}</p></div>
                                    </div>
                                    <div class="g-row"><p class="p-checkred"><span class="icon16-telgray"></span>${shop.phone}</p></div>
                                    </c:if>
                           <c:if test="${shop == null}">
                               
                                    <p>Thành viên: <strong>${user.name}</strong></p>
                                                <jsp:setProperty name="date" property="time" value="${user.joinTime}" /> 
                                    <p class="mgt-15"><span class="fa fa-calendar"></span> Tham gia ngày: <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate></p>
                                    <c:if test="${user.address!=null && user.address!=''}"><p><span class="fa fa-map-marker"></span> ${user.address}</p></c:if>
                                    <c:if test="${user.email!=null && user.email!=''}"><p><span class="fa fa-envelope"></span> ${user.email} </p></c:if>
                                    <c:if test="${user.phone!=null && user.phone!=''}"><p><span class="fa fa-phone"></span> ${user.phone}</p></c:if>
                                    <c:if test="${user.yahoo!=null && user.yahoo!=''}"><p><span class="fa fa-comments"></span> ${user.yahoo}</p></c:if>
                                    <c:if test="${user.skype!=null && user.skype!=''}"><p><span class="fa fa-skype"></span> ${user.skype}</p></c:if>
                                        
                            </c:if>
                            <div class="g-row"><p class="p-checkred"><label class="star-outer"><span class="icon-star"></span></label>Điểm uy tín: <b class="text-danger">${userReview.totalPoint}</b></p></div>
                            <div class="g-row"><p class="p-checkred"><span class="icon16-like-green"></span><b class="text-danger"><c:if test="${(userReview.good * 100/userReview.totalPoint)=='NaN'}">0</c:if><c:if test="${(userReview.good * 100/userReview.totalPoint)!='NaN'}"><fmt:formatNumber value="${userReview.good*100 / userReview.totalPoint}" pattern="0" /></c:if>%</b> người mua đánh giá tốt</p></div>
                            <div class="g-row"><p class="p-checkred"><span class="icon16-shopin"></span><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(shop.userId).concat('"}]'))}">Xem sản phẩm của người bán</a></p></div>
                        </div><!-- userinfo-cv -->
                    </div><!-- col-md-4 -->
                    <div class="col-md-4">
                        <div class="userinfo-review">
                            <div class="ur-title">Chi tiết điểm uy tín</div>
                            <div class="row">
                                <b class="text-danger"><c:if test="${(userReview.good * 100/userReview.totalPoint)=='NaN'}">0</c:if><c:if test="${(userReview.good * 100/userReview.totalPoint)!='NaN'}"><fmt:formatNumber value="${userReview.good * 100 / (userReview.good + userReview.normal + userReview.bad)}" pattern="0" /></c:if>%</b> người mua đánh giá tốt
                                </div>
                                <div class="row">
                                    <label class="star-name">Tốt</label>
                                        <div class="star-bar"><span style="width:${userReview.good * 100/userReview.totalPoint}%;"></span></div>
                                <label class="star-count"><c:if test="${(userReview.good * 100/userReview.totalPoint)=='NaN'}">0</c:if><c:if test="${(userReview.good * 100/userReview.totalPoint)!='NaN'}">${userReview.good}</c:if></label>
                                </div>
                                <div class="row">
                                    <label class="star-name">Trung bình</label>
                                        <div class="star-bar"><span style="width:${userReview.normal * 100/userReview.totalPoint}%;"></span></div>
                                <label class="star-count"><c:if test="${(userReview.normal * 100/userReview.totalPoint)=='NaN'}">0</c:if><c:if test="${(userReview.normal * 100/userReview.totalPoint)!='NaN'}">${userReview.normal}</c:if></label>
                                </div>
                                <div class="row">
                                    <label class="star-name">Không hài lòng</label>
                                        <div class="star-bar"><span style="width:${userReview.bad * 100/userReview.totalPoint}%;"></span></div>
                                <label class="star-count"><c:if test="${(userReview.bad * 100/userReview.totalPoint)=='NaN'}">0</c:if><c:if test="${(userReview.bad * 100/userReview.totalPoint)!='NaN'}">${userReview.bad}</c:if></label>
                                </div>
                            </div><!-- userinfo-review -->
                        </div><!-- col-md-4 -->
                        <div class="col-md-4">
                            <div class="userinfo-review">
                                <div class="ur-title">Đánh giá uy tín gần đây</div>

                            <c:forEach items="${sellerReviewlist}" var="reviewlist">
                                <c:set var="flag" value="true" />
                                <c:forEach items="${userByIds}" var="users">
                                    <c:if test="${users.id==reviewlist.userId && flag}">
                                        <c:set var="flag" value="false" />
                                        <p><span class="glyphicon glyphicon-ok-circle"></span>
                                            <c:set var="originalDetail" value="${fn:substring(fn:trim(reviewlist.content), 0, 40)}" />
                                            ${originalDetail} ...
                                        </p>
                                        <jsp:setProperty name="date" property="time" value="${reviewlist.createTime}" /> 

                                        <p class="text-right"><a href="${baseUrl}/user/${users.id}/ho-so-nguoi-ban.html" target="_blank">${users.name}</a>, <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate></p>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                            <c:if test="${fn:length(sellerReviewlist)<=0}">
                                <div style="color: red" class="text-center">Chưa có đánh giá nào</div>
                            </c:if>

                        </div><!-- userinfo-review -->
                    </div><!-- col-md-4 -->
                </div><!-- row -->
                <div class="boxblue">
                    <div class="boxblue-title full-tab">
                        <ul class="pull-left">               	
                            <li class="active reviewTrue"><a href="javascript:;" onclick="sellerreview.changeTabReview(true, '${user.id}')">Đánh giá thành viên khác</a></li>
                            <li class="reviewFalse"><a href="javascript:;" onclick="sellerreview.changeTabReview(false, '${user.id}')">Thành viên khác đánh giá</a></li>
                        </ul>
                    </div><!-- boxblue-title -->
                    <input type="hidden" name="userIdReview" value="${user.id}">
                    <input type="hidden" name="sellerId" value="">
                    <div class="boxblue-content" id="showitemOther">
                    </div><!-- boxblue-content -->

                </div><!-- boxblue -->
            </div><!-- page-userinfo -->
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
</div>