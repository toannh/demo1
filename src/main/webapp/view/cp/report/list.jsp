<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<div class="container">
    <div class="cms-content">
        <h1 class="func-title"><i class="fa fa-clipboard"></i>Báo cáo thống kê</h1>
        <div class="panel panel-default panel-tabs">
            <div class="panel-heading">
                <ul class="nav nav-tabs" role="tablist" data-rel="nav" >
                    <li data-hash="user"><a href="${baseUrl}/cp/report.html#user" >Người dùng</a></li>
                    <li data-hash="shop"><a href="${baseUrl}/cp/report.html#shop">Shop</a></li>
                    <li data-hash="order"><a href="${baseUrl}/cp/report.html#order">Đơn hàng</a></li>
                    <li data-hash="gmv"><a href="${baseUrl}/cp/report.html#gmv">GMV</a></li>
                    <li data-hash="item"><a href="${baseUrl}/cp/report.html#item">Listing</a></li>
                    <li data-hash="lading"><a href="${baseUrl}/cp/report.html#lading">Vận đơn</a></li>
                    <li data-hash="cash"><a href="${baseUrl}/cp/report.html#cash">Xèng</a></li>
                </ul>
            </div>
            <div class="func-container">
                <div class="func-yellow">
                    <div class="form row">
                        <div class="col-sm-4 padding-right-5">
                        </div><!-- /col -->
                        <div class="col-sm-3 padding-all-5">
                            <div class="input-group">
                                <input type="hidden" data-rel="startTime" value="${search.startTime}" class="form-control" placeholder="Xem từ ngày" />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div><!-- /col -->
                        <div class="col-sm-3 padding-all-5">
                            <div class="input-group">
                                <input type="hidden" data-rel="endTime" value="${search.endTime}" class="form-control" placeholder="Tới ngày" />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div><!-- /col -->
                        <div class="col-sm-2 padding-left-5">
                            <div class="btn-full">
                                <button onclick="report.draw();" type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span>Xem</button>
                            </div>
                        </div><!-- /col -->
                    </div><!-- /form -->
                </div><!-- /func-yellow -->
                <div class="report-chart" style="text-align:center;" id="chart">
                    <div class="lading"></div>
                </div><!-- /report-chart -->
                <div class="report-content row" data-rel="data" ></div><!-- /report-content -->
            </div><!-- /func-container -->
        </div><!-- /panel -->
    </div><!-- /cms-content -->
</div><!-- /container -->