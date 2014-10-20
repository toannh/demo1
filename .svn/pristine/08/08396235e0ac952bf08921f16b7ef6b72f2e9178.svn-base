<%-- 
    Document   : configShopStepTwo
    Created on : Jul 2, 2014, 3:30:53 PM
    Author     : PhucTd
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span><a href="${baseUrl}"> Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username != null?viewer.user.username:viewer.user.email}</a></li>
        <li class="active">Quản trị shop</li>
    </ol>
    <h1 class="title-pages">Quản trị shop</h1>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-chon-giao-dien-shop-115785286300.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn chọn giao diện Shop</a></div>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li class="dropdown active">
                <a href="javascript:;" data-toggle="dropdown">Cấu hình shop <span class="fa fa-sort-down"></span></a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step1.html">Thông tin cơ bản</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step2.html">Chọn giao diện</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step5.html">Soạn danh mục sản phẩm</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step6.html">Soạn danh mục tin tức</a></li>                                                                                                            
                </ul>
            </li>
            <li class="dropdown">
                <a href="javascript:;" data-toggle="dropdown">Quản trị nội dung<span class="fa fa-sort-down"></span></a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                    <li><a href="${baseUrl}/user/shop-news.html">Quản trị tin tức</a></li>
                    <li><a href="${baseUrl}/user/shop-banner.html">Quản trị banner</a></li>
                    <li><a href="${baseUrl}/user/shop-home-item.html">Quản trị SP nổi bật</a></li>
                </ul>
            </li>                   
        </ul>
        <div class="tabs-content-block">
            <div class="row config-shop-layout">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <p>Bạn hãy hoàn thiện đầy đủ các thông tin cấu hình Shop để có một hình ảnh chuyên nghiệp trong mắt khách hàng</p>
                    <div class="config-shop-step-layout">
                        <ul class="config-shop-step clearfix">
                            <li onClick="shop.nextStep(1)">
                                <div class="step" title="Thông tin cơ bản">
                                    <span class="glyphicon glyphicon-ok"></span>
                                    <p><strong>Bước 1</strong></p>
                                    <span>Thông tin cơ bản</span>
                                </div>
                            </li>
                            <li onClick="shop.nextStep(2)" class="active step-success">
                                <div class="step" title="Chọn giao diện">
                                    <span class="glyphicon glyphicon-ok"></span>
                                    <p><strong>Bước 2</strong></p>
                                    <span>Chọn giao diện</span>
                                </div>
                            </li>
                            <li onClick="shop.nextStep(3)">
                                <div class="step" title="Bố cục dàn trang">
                                    <span class="glyphicon glyphicon-ok"></span>
                                    <p><strong>Bước 3</strong></p>
                                    <span>Bố cục dàn trang</span>
                                </div>                                                
                            </li>
                            <li onClick="shop.nextStep(4)">
                                <div class="step" title="Soạn Menu Shop">
                                    <span class="glyphicon glyphicon-ok"></span>
                                    <p><strong>Bước 4</strong></p>
                                    <span>Soạn Menu Shop</span>
                                </div>     
                            </li>
                            <li onclick="shop.nextStep(5);">
                                <div class="step" style="min-width:155px;" title="Soạn danh mục sản phẩm">
                                    <span class="glyphicon glyphicon-ok"></span>
                                    <p><strong>Bước 5</strong></p>
                                    <span>Soạn DM sản phẩm</span>
                                </div>
                            </li>
                            <li onclick="shop.nextStep(6);">
                                <div class="step" title="Soạn danh mục tin tức">
                                    <span class="glyphicon glyphicon-ok"></span>
                                    <p><strong>Bước 6</strong></p>
                                    <span>Soạn DM tin tức</span>
                                </div>
                            </li>
                        </ul>
                    </div><!--Step config shop-->                                
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#config-shop-interface" data-toggle="tab">Cài đặt giao diện của bạn</a></li>
                    </ul>                                        
                    <!-- Tab panes -->
                    <div class="tab-content ">
                        <div class="tab-pane active" id="config-shop-interface">
                            <div class="panel-config-shop">
                                <p>Bạn hãy hoàn thiện các nội dung dưới đây để có một giao diện Shop đẹp mắt, hấp dẫn người mua</p>
                                <div class="box-step mgt-25"> 
                                </div>
                                <div class="box-step mgt-25">                             
                                    <div class="title-config-shop">Logo Shop </div>
                                    <div class="row">
                                        <p>Logo được hiển thị tại nhiều khu vực trên ChợĐiệnTử</p>
                                        <form method="post" enctype="multipart/form-data" id="logoUpload">
                                            <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12">
                                                <span class="btn btn-default fileinput-button">
                                                    <span>Upload ảnh</span>
                                                    <input id="fileupload" type="file" name="image" />
                                                </span>
                                                <p class="imgError">Cho phép các định dạng jpg, png, gif</p>
                                                <p class="mgt-25">Logo hiện tại</p>
                                                <div class="img-now-config-logo">
                                                    <div class="curren-logo-bg"></div>
                                                    <div class="curren-logo">
                                                        <c:if test="${shop.logo==null}">
                                                            <img src="${baseUrl}/static/user/images/data/AvatatShop-Default.png" />
                                                        </c:if>
                                                        <c:if test="${shop.logo!=''}">
                                                            <img src="${shop.logo}" height="130" />
                                                        </c:if> 
                                                    </div>
                                                    <input type="hidden" id="x1" name="x" value="0">
                                                    <input type="hidden" id="y1" name="y" value="0">
                                                    <input type="hidden" id="w" name="width" value="0">
                                                    <input type="hidden" id="h" name="height" value="0">
                                                </div>
                                            </div>
                                            <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12">       
                                                <div class="imgPlaceHolder"></div>
                                                <div class="text-center hide">
                                                    <p class="note">Kéo khung hình vuông giãn to, thu nhỏ và chọn góc ảnh <br> để có logo phù hợp nhất</p>
                                                    <button type="button" id="saveImage" class="btn btn-default" disabled="true" onclick="shop.saveShopLogo()">Lưu lại</button></div>	
                                            </div>
                                        </form>
                                    </div>
                                </div><!--Logo shop--> 
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div><!--Tài khoản giao dịch-->
                    <div class="box-step mgt-25">
                        <div class="form-horizontal">    
                            <div class="form-group text-center">
                                <a onclick="shop.nextStep(5)" class="btn btn-primary btn-lg">Qua bước 5</a> 
                            </div>      
                        </div>    
                    </div>             
                </div>                            
            </div>                     
        </div>   
    </div>
</div>
