<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username != null?viewer.user.username:viewer.user.email}</a></li>
        <li class="active">Quản trị shop</li>
    </ol>
    <h1 class="title-pages">Quản trị shop</h1>
    <div class="tabs-intro">
        <a href="${baseUrl}/tin-tuc/huong-dan-quan-tri-danh-muc-tin-tuc-shop-193069992340.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn quản trị danh mục tin tức SHOP
        </a>
    </div>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li class="dropdown active">
                <a href="#" data-toggle="dropdown">Cấu hình shop <span class="fa fa-sort-down"></span></a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step1.html">Thông tin cơ bản</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step2.html">Chọn giao diện</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step5.html">Soạn danh mục sản phẩm</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step6.html">Soạn danh mục tin tức</a></li>                                                                                                            
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" data-toggle="dropdown">Quản trị nội dung<span class="fa fa-sort-down"></span></a>
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
                            <li onclick="shop.nextStep(1);">
                                <div class="step" title="Thông tin cơ bản">
                                    <span class="glyphicon glyphicon-ok"></span>
                                    <p><strong>Bước 1</strong></p>
                                    <span>Thông tin cơ bản</span>
                                </div>
                            </li>
                            <li onclick="shop.nextStep(2);">
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
                            <li onclick="shop.nextStep(6);" class="active step-success">
                                <div class="step" title="Soạn danh mục tin tức">
                                    <span class="glyphicon glyphicon-ok"></span>
                                    <p><strong>Bước 6</strong></p>
                                    <span>Soạn DM tin tức</span>
                                </div>
                            </li>
                        </ul>
                    </div><!--Step config shop-->                                
                    <div class="title-config-shop">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <input name="shopId" value="${shop.userId}" type="hidden" />
                                <label class="col-sm-2 control-label">Tên danh mục:</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control"  name="shopNewsCategoryName" placeholder="Ví dụ: Thông báo của shop; câu hỏi thường gặp"/>	 
                                    <div class="help-block" style="color: red" for="name"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Cấp danh mục:<span class="clearfix">(Tối đa 2 cấp)</span></label>
                                <div class="col-sm-10">
                                    <div class="form-inline reset-form-group">
                                        <div class="form-group">
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="parentNewsCate" value="0" checked/>Danh mục cấp 1
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="radio-inline">
                                                <label>
                                                    <input type="radio" name="parentNewsCate" value="1"/>Danh mục cấp con của
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <select class="form-control" name="parentId">
                                                <c:forEach items="${newsCategory}" var="cate">
                                                    <c:if test="${cate.parentId == null }">
                                                        <option value="${cate.id}">${cate.name}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <div class="help-block" style="color: red" for="parentId"></div>
                                        </div>
                                        <div class="form-group">
                                            <button type="button" class="btn btn-default" onclick="shop.addShopNewsCategory()">Thêm</button>
                                        </div>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                    <p class="mgt-25"><strong>Danh mục hiện tại:</strong></p>
                    <c:if test="${newsCategory.size() <= 0}">
                        <div class="cdt-message bg-danger text-center">Chưa có danh mục nào!</div>
                    </c:if>
                    <c:if test="${newsCategory.size() > 0}">
                        <div class="list-table-content table-responsive mgt-25">
                            <table class="table table-bordered">
                                <tbody>
                                    <tr class="warning">
                                        <th width="57%">Danh mục</th>
                                        <th width="15%">Trạng thái</th>
                                        <th><div class="text-center">Thao tác</div></th>
                                </tr>
                                <!--Begin for-->
                                <c:forEach var="newsCat" items="${newsCategory}">
                                    <c:if test="${newsCat.parentId == null || newsCat.parentId ==''}">
                                        <tr>
                                            <td><a><span class="glyphicon glyphicon-chevron-right icon-parent"></span> ${newsCat.name}</a> [Id: ${newsCat.id}]</td>
                                            <td>
                                                <c:if test="${newsCat.active}">
                                                    <span class="glyphicon glyphicon-ok visited"></span> Hiển thị
                                                </c:if>
                                                <c:if test="${!newsCat.active}">
                                                    <span class="glyphicon glyphicon-ban-circle icon-hidden"></span> Không hiển thị
                                                </c:if>
                                            </td>
                                            <td>
                                                <div class="text-center">
                                                    <a onclick="shop.changeActiveNewsCategory('${newsCat.id}');" class="btn btn-default btn-sm">${newsCat.active?'Ẩn':'Hiển thị'}</a>
                                                    <a onclick="shop.editNewsCategory('${newsCat.id}');" class="btn btn-default btn-sm">Sửa</a>
                                                    <a onclick="shop.removeNewsCategory('${newsCat.id}');" class="btn btn-default btn-sm">Xoá</a>
                                                </div>
                                            </td>
                                        </tr>
                                        <c:forEach var="newsCatlv2" items="${newsCategory}">
                                            <c:if test="${newsCatlv2.parentId == newsCat.id}">
                                                <tr>
                                                    <td class="sub-category-product"><a href="#"><span class="glyphicon glyphicon-share-alt"></span> ${newsCatlv2.name}</a> [Id: ${newsCatlv2.id}]</td>
                                                    <td>
                                                        <c:if test="${newsCatlv2.active}">
                                                            <span class="glyphicon glyphicon-ok visited"></span> Hiển thị
                                                        </c:if>
                                                        <c:if test="${!newsCatlv2.active}">
                                                            <span class="glyphicon glyphicon-ban-circle icon-hidden"></span> Không hiển thị
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <div class="text-center">
                                                            <a onclick="shop.changeActiveNewsCategory('${newsCatlv2.id}');" class="btn btn-default btn-sm">${newsCatlv2.active?'Ẩn':'Hiển thị'}</a>
                                                            <a onclick="shop.editNewsCategory('${newsCatlv2.id}');" class="btn btn-default btn-sm">Sửa</a>
                                                            <a onclick="shop.removeNewsCategory('${newsCatlv2.id}');" class="btn btn-default btn-sm">Xoá</a>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                        <tr>
                                            <td colspan="4">&nbsp;</td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>                            
            </div>                     
        </div>   
    </div>
</div>