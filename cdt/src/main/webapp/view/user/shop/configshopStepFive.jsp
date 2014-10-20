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
    <div class="tabs-content-user">
        <div class="tabs-intro">
            <a href="${baseUrl}/tin-tuc/huong-dan-quan-tri-danh-muc-shop-713383508418.html" target="_blank">
                <span class="icon16-faq"></span>Hướng dẫn quản trị danh mục shop
            </a>
        </div>
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
                            <li onClick="shop.nextStep(1);">
                                <div class="step" title="Thông tin cơ bản">
                                    <span class="glyphicon glyphicon-ok"></span>
                                    <p><strong>Bước 1</strong></p>
                                    <span>Thông tin cơ bản</span>
                                </div>
                            </li>
                            <li onClick="shop.nextStep(2);">
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
                            <li onclick="shop.nextStep(5);" class="active step-success">
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

                    <div class="title-config-shop">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <input name="shopId" value="${shop.userId}" type="hidden" />
                                <label class="col-sm-2 control-label">Tên danh mục:</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="categoryName">
                                    <div class="help-block" style="color: red" for="name"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Cân nặng:</label>
                                <div class="col-sm-3">
                                    <div class="input-group">
                                        <input type="text" name="weight" class="form-control">
                                        <span class="input-group-addon">gram</span>
                                    </div>			
                                </div>
                                <div class="col-sm-4">
                                    <p class="form-control-static">(Cân nặng gợi ý theo danh mục)</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Cấp danh mục:<span class="clearfix">(Tối đa 3 cấp)</span></label>
                                <div class="col-sm-10">
                                    <div class="form-inline reset-form-group">
                                        <div class="form-group">
                                            <select class="form-control" name="parentId">
                                                 <option value="0"> Là danh mục cấp 1 </option>
                                                <c:forEach var="cat" items="${catShop}">
                                                    <c:if test="${cat.parentId == null}">
                                                        <option value="${cat.id}"> -- ${cat.name}</option>
                                                        <c:forEach var="catlv2" items="${catShop}">
                                                            <c:if test="${catlv2.parentId == cat.id}">
                                                                <option value="${catlv2.id}"> -- --${catlv2.name}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <div class="help-block" style="color: red" for="parentId"></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-2"><button type="button" class="btn btn-default" onclick="shop.addShopCategory();">Thêm</button>
                                            </div>
                                        </div>
                                    </div>                                
                                </div>
                            </div>
                        </div>
                    </div>
                    <p class="mgt-25"><strong>Danh mục hiện tại:</strong></p>
                    <c:if test="${catShop.size() <= 0 }">
                        <div class="cdt-message bg-danger text-center">Chưa có danh mục nào!</div>
                    </c:if>
                    <c:if test="${catShop.size() > 0 }">
                        <div class="list-table-content table-responsive mgt-25">
                            <table class="table table-bordered">
                                <tr class="warning">
                                    <th width="45%">Danh mục</th>
                                    <th width="14%">Trạng thái</th>
                                    <th width="15%"><div class="text-center">Nổi bật trang chủ</div></th>
                                <th><div class="text-center">Thao tác</div></th>
                                </tr>
                                <!--Begin for-->
                                <c:forEach var="category" items="${catShop}">
                                    <c:if test="${category.parentId == null}">
                                        <tr>
                                            <td><a><span class="glyphicon glyphicon-chevron-right icon-parent"></span> ${category.name}</a> [Id: ${category.id}]</td>
                                            <td>
                                                <c:if test="${category.active}">
                                                    <span class="glyphicon glyphicon-ok visited" ></span> Hiển thị
                                                </c:if>
                                                <c:if test="${!category.active}">
                                                    <span class="glyphicon glyphicon-ban-circle icon-hidden"></span> Không hiển thị
                                                </c:if>
                                            </td>
                                            <td><a  onclick="shop.changeHome('${category.id}');" class="btn btn-default btn-sm btn-block">${category.home?'Có':'Không'}</a></td>
                                            <td>
                                                <div class="text-center">
                                                    <a onclick="shop.changeActive('${category.id}')" class="btn btn-default btn-sm">${category.active?'Ẩn':'Hiển thị'}</a>
                                                    <a onclick="shop.editCategoryShop('${category.id}');" class="btn btn-default btn-sm">Sửa</a>
                                                    <a onclick="shop.removeCategoryShop('${category.id}')" class="btn btn-default btn-sm">Xoá</a>
                                                </div>
                                            </td>
                                        </tr>
                                        <c:forEach var="categorylv2" items="${catShop}">
                                            <c:if test="${categorylv2.parentId == category.id}">
                                                <tr>
                                                    <td class="sub-category-product"><a><span class="glyphicon glyphicon-share-alt"></span> ${categorylv2.name}</a> [Id: ${categorylv2.id}]</td>
                                                    <td>
                                                        <c:if test="${categorylv2.active}">
                                                            <span class="glyphicon glyphicon-ok visited" for="${category.id}"></span> Hiển thị
                                                        </c:if>
                                                        <c:if test="${!categorylv2.active}">
                                                            <span class="glyphicon glyphicon-ban-circle icon-hidden" for="${category.id}"></span> Không hiển thị
                                                        </c:if>
                                                    </td>
                                                    <td><a onclick="shop.changeHome('${categorylv2.id}');" class="btn btn-default btn-sm btn-block">${categorylv2.home?'Có':'Không'}</a></td>
                                                    <td>
                                                        <div class="text-center">
                                                            <a onclick="shop.changeActive('${categorylv2.id}')" class="btn btn-default btn-sm">${categorylv2.active?'Ẩn':'Hiển thị'}</a>
                                                            <a onclick="shop.editCategoryShop('${categorylv2.id}');" class="btn btn-default btn-sm">Sửa</a>
                                                            <a onclick="shop.removeCategoryShop('${categorylv2.id}')" class="btn btn-default btn-sm">Xoá</a>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:forEach var="categorylv3" items="${catShop}">
                                                    <c:if test="${categorylv3.parentId == categorylv2.id}">
                                                        <tr>
                                                            <td class="sub-sub-category-product"><a><span class="glyphicon glyphicon-share-alt"></span> ${categorylv3.name}</a> [Id: ${categorylv3.id}]</td>
                                                            <td>
                                                                <c:if test="${categorylv3.active}">
                                                                    <span class="glyphicon glyphicon-ok visited" for="${category.id}"></span> Hiển thị
                                                                </c:if>
                                                                <c:if test="${!categorylv3.active}">
                                                                    <span class="glyphicon glyphicon-ban-circle icon-hidden" for="${category.id}"></span> Không hiển thị
                                                                </c:if>
                                                            </td>
                                                            <td><a onclick="shop.changeHome('${categorylv3.id}');" class="btn btn-default btn-sm btn-block">${categorylv3.home?'Có':'Không'}</a></td>
                                                            <td>
                                                                <div class="text-center">
                                                                    <a onclick="shop.changeActive('${categorylv3.id}')" class="btn btn-default btn-sm">${categorylv3.active?'Ẩn':'Hiển thị'}</a>
                                                                    <a onclick="shop.editCategoryShop('${categorylv3.id}');" class="btn btn-default btn-sm">Sửa</a>
                                                                    <a onclick="shop.removeCategoryShop('${categorylv3.id}')" class="btn btn-default btn-sm">Xoá</a>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                        <tr>
                                            <td colspan="4">&nbsp;</td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                <!--End for-->
                            </table>
                        </div>
                    </c:if>      
                    <div class="box-step mgt-25">
                        <div class="form-horizontal">    
                            <div class="form-group text-center">
                                <button class="btn btn-primary btn-lg" onclick="shop.nextStep(6);">Qua bước 6</button> 
                            </div>      
                        </div>    
                    </div> <!--Thông tin đường đi và địa điểm-->                       
                </div>                            
            </div>                     
        </div>   
    </div>
</div>