<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld" %>

<div class="container">
    <div class="tree-main">
        <div class="menu-click">
            <jsp:include page="/view/market/widget/alias.jsp" />
        </div><!--menu-click-->
        <div class="tree-view">
            <a class="home-button" href="${baseUrl}"></a><span class="tree-before"></span><a class="last-item" href="${baseUrl}/lich-su-tim-kiem-cua-ban.html">Lịch sử tìm kiếm của bạn</a><span class="tree-after"></span>
        </div><!-- /tree-view -->
    </div><!-- /tree-main -->
    <div class="bground">
        <div class="bg-white">
            <div class="sidebar">
                <div class="pbox search-winget">
                    <div class="pbox-title"><label class="lb-name">Sản phẩm vừa xem</label></div>
                    <div class="pbox-content">
                        <ul class="search-near">
                            <li class="active">
                                <div class="search-count" data-rel="aaaaaaaaaaaaa">

                                </div><!--search-count-->
                            </li>
                        </ul>
                    </div><!--pbox-content-->
                </div><!--pbox-->
            </div><!-- sidebar -->
            <div class="main">
                <div class="mbox">
                    <div class="mbox-title full-tab">
                        <ul>
                            <li><a href="${baseUrl}/lich-su-tim-kiem.html">Lịch sử tìm kiếm</a></li>
                            <li><a href="${baseUrl}/tim-kiem-pho-bien.html">Từ khóa phổ biến</a></li>
                                <c:if test="${viewer.user != null}">
                                <li  class="active"><a href="${baseUrl}/lich-su-tim-kiem-cua-ban.html">Lịch sử tìm kiếm của bạn</a></li>
                                </c:if>
                        </ul>
                    </div><!-- mbox-title -->
                    <div class="mbox-content">
                        <!--                        <div class="box-control">
                                                    <label>Hiện <b>1-20</b> trong <b>11.730</b> từ khoá theo:</label>
                                                    <ul>
                                                        <li class="active"><a href="#">100</a></li>
                                                        <li><a href="#">200</a></li>
                                                        <li><a href="#">500</a></li>
                                                        <li><a href="#">Tất cả</a></li>
                                                    </ul>
                                                </div>-->
                        <div class="form-horizontal form search-form">
                            <div class="form-group">
                                <label class="control-label col-sm-2">Từ khoá:</label>
                                <div class="col-sm-7">
                                    <input name="keyword" type="text" class="form-control">	
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2">Từ ngày:</label>
                                <div class="col-sm-3">
                                    <input name="createTimeFrom" type="hidden" class="form-control timeSelect" placeholder="0">	
                                </div>
                                <label class="control-label col-sm-1">Tới ngày:</label>
                                <div class="col-sm-3">
                                    <input name="createTimeTo" type="hidden" class="form-control timeSelect" placeholder="0">	
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-7 col-sm-offset-2"><button type="submit" class="btn btn-primary" onclick="tagsearchhistory.loadTagSearchByUser();">Xem</button></div>
                            </div>
                        </div><!-- form-horizontal form -->
                        <div class="search-result" data-rel="counttagSearch"></div>
                        <div class="search-content" data-rel="tagSearch" >
                        </div><!--search-content-->
                        <div class="box-bottom">
                            <ul class="pagination"  data-rel="pageSearchHistory">

                            </ul>
                        </div><!--box-bottom-->
                    </div><!-- mbox-content -->
                </div><!-- mbox -->
            </div><!-- main -->
            <div class="clearfix"></div>
        </div><!-- bg-white -->
        <div class="clearfix"></div>
    </div><!-- bground -->
    <div class="internal-text">
        <h1>Mua bán, đấu giá sản phẩm thời trang, trang sức, công nghệ, điện tử... | ChợĐiệnTử  - đa dạng, giá rẻ.</h1>
        <h2>Lịch sử tìm kiêm sản phẩm tại chodientu.vn</h2>
    </div><!-- /internal-text -->
</div>