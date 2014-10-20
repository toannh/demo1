<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<header>
    <input type="hidden" name="sellerId" value="${shop.userId}" />
    <div class="container">
        <div class="row">
            <div class="col-lg-7 col-md-8 col-sm-8 col-xs-12">
                <a class="logo pull-left" href="${baseUrl}/${shop.alias}"><img style="width: 121px;" src="${shop.logo}" alt="${shop.title}" /></a>
                <div class="content-header">
                    <h3><a href="${baseUrl}/${shop.alias}">${shop.title}</a> 
                        <c:if test="${shop.description!=null && shop.description!=''}"><div class="fa-quote-content">${shop.description}</div></c:if>
                        </h3>
                    <c:if test="${shop.phone != null && shop.phone != ''}">
                        <div class="block">
                            <span class="fa fa-phone"></span> ${shop.phone}
                        </div>
                    </c:if>
                    <c:if test="${(shop.phone == null || shop.phone == '') && user.data.phone != null &&  user.data.phone != ''}">
                        <div class="block">
                            <span class="fa fa-phone"></span> ${user.data.phone}
                        </div>
                    </c:if>
                    <c:if test="${shop.address != null && shop.address != ''}">
                        <div class="block">
                            <span class="fa fa-home"></span> : ${shop.address}
                        </div>
                    </c:if>
                    <div class="block">
                        <jsp:useBean id="date" class="java.util.Date" />
                        <jsp:setProperty name="date" property="time" value="${shop.createTime}" /> 
                        <span class="tool-tip pull-left" data-toggle="tooltip" data-placement="top" title="" data-original-title="Ngày tạo shop trên hệ thống ChoDienTu.vn">
                            <span class="fa fa-calendar"></span> : 
                            <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                            </span>
                        <c:if test="${shopReview.totalPoint > 0}">
                            <div class="more-tooltip">
                                <div class="more-tooltip-content">
                                    <span class="fa fa-star"></span> Điểm uy tín: ${shopReview.totalPoint} &nbsp;&nbsp;&nbsp; <span class="fa fa-trophy"></span> <fmt:formatNumber value="${shopReview.good * 100 / (shopReview.good + shopReview.normal + shopReview.bad)}" pattern="0" />% người đánh giá tốt <b class="caret"></b>
                                    <hr>

                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">Tốt</label>
                                            <div class="col-sm-8">
                                                <div class="progress">
                                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: ${shopReview.good * 100/shopReview.totalPoint}%;">
                                                    </div>
                                                </div> ${shopReview.good}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">Trung bình</label>
                                            <div class="col-sm-8">
                                                <div class="progress">
                                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: ${shopReview.normal * 100/shopReview.totalPoint}%;">
                                                    </div>
                                                </div> ${shopReview.normal}
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">Không hài lòng</label>
                                            <div class="col-sm-8">
                                                <div class="progress">
                                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: ${shopReview.bad * 100/shopReview.totalPoint}%;">
                                                    </div>
                                                </div> ${shopReview.bad}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col-lg-5 col-md-4 col-sm-4 col-xs-12">
                <div class="pull-right search-form" >  
                    <div class="block">
                        <ul>
                            <li>
                                <a onclick="index.followSeller('${shop.userId}');" class="btn-like-cdt" href="javascript:;">Quan tâm</a>
                                <span class="like-count">${sellerFollowCount}</span>
                            </li>
                            <li>
                                <iframe src="//www.facebook.com/plugins/like.php?href=${baseUrl}/${shop.alias}/&amp;width&amp;layout=standard&amp;action=like&amp;show_faces=false&amp;share=true&amp;height=35&amp;appId=238325043032573" scrolling="no" frameborder="0" style="border:none; overflow:hidden; height:35px;" allowTransparency="true"></iframe>
                            </li>
                        </ul>

                    </div>
                    <div class="clearfix">
                        <div class="input-group" id="searchShop">
                            <input type="text" class="form-control" id="search" placeholder="Từ khoá tìm kiếm...">
                            <span class="input-group-btn">
                                <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                            </span><!-- /input-group -->
                        </div>
                    </div>
                </div>
            </div>        
        </div><!--container-->
    </div>    
</header><!--header-->