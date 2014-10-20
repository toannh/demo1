<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<div class="col-lg-9 col-md-9 col-sm-8 col-xs-12 right-content">
    <ol class="breadcrumb">
        <li><a href="${baseUrl}/${shop.alias}">Trang chủ</a></li>
        <li><a href="${baseUrl}/${shop.alias}/news-allcategory.html">Tin tức</a></li>
            <c:if test="${news!=null}">
                <c:forEach items="${news.categoryPath}" var="path">
                    <c:forEach items="${shopNewsCategories}" var="cate">
                        <c:if test="${cate.id==path}"><li><a href="${baseUrl}/${shop.alias}/news-category/${cate.id}/${text:createAlias(cate.name)}.html">${cate.name}</a></li></c:if>
                    </c:forEach>
                </c:forEach>
            </c:if>
    </ol>
    <h2>${news.title}</h2>
    <div class="date-news small">
        <jsp:useBean id="date" class="java.util.Date" />
        <jsp:setProperty name="date" property="time" value="${news.updateTime}" />        
        <span class="fa fa-calendar"></span> Cập nhật: <fmt:formatDate type="date" pattern="dd/MM/yyyy"  value="${date}"></fmt:formatDate>
        </div>
        <div class="box-module news-detail">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    ${news.detail}
                    <div class="clearfix" style="margin-bottom:25px;">
                        <!-- AddThis Button BEGIN -->
                        <div class="addthis_toolbox addthis_default_style addthis_32x32_style pull-right">
                            <a class="addthis_button_preferred_1"></a>
                            <a class="addthis_button_preferred_2"></a>
                            <a class="addthis_button_preferred_3"></a>
                            <a class="addthis_counter addthis_bubble_style"></a>
                        </div>
                        <script type="text/javascript">var addthis_config = {"data_track_addressbar": true};</script>
                        <script type="text/javascript" src="//s7.addthis.com/js/300/addthis_widget.js#pubid=ra-5333cdbb3a37fde8"></script>
                        <!-- AddThis Button END -->
                    </div>

                </div>
                <c:if test="${otherNews != null && fn:length(otherNews.data)>0}">
                    <hr style="width:100%;"/>
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <h3>Tin khác</h3>
                        <ul class="news-list-more">
                            <c:forEach items="${otherNews.data}" var="other">
                                <li><a href="${baseUrl}/${shop.alias}/news/${other.id}/${text:createAlias(other.title)}.html">${other.title}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </c:if>
            </div>
        </div>                    
    </div><!--Tin tức chi tiết-->
</div><!--Right content-->
