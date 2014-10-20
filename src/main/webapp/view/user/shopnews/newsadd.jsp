<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username != null?viewer.user.username:viewer.user.email}</a></li>
        <li class="active">Quản trị nội dung</li>
    </ol>
    <h1 class="title-pages">Quản trị tin tức</h1>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-quan-tri-tin-tuc-shop-724540569618.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn quản trị tin tức Shop</a></div>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li class="dropdown">
                <a href="#" data-toggle="dropdown">Cấu hình shop <span class="fa fa-sort-down"></span></a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step1.html">Thông tin cơ bản</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step2.html">Chọn giao diện</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step5.html">Soạn danh mục sản phẩm</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step6.html">Soạn danh mục tin tức</a></li>                                                                                                            
                </ul>
            </li>
            <li class="dropdown active">
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
                    <p><strong>Quản trị tin tức</strong></p>
                    <ul class="nav nav-tabs">
                        <li><a href="${baseUrl}/user/shop-news.html">Danh sách tin bài</a></li>
                        <li class="active"><a href="${baseUrl}/user/shop-add-news.html">Thêm bài mới</a></li>
                    </ul>                                        
                    <!-- Tab panes -->
                    <div class="tab-content ">
                        <div class="tab-pane" id="config-list-news">
                            <div class="clearfix"></div>
                        </div>
                        <form class="form-horizontal" id="form-add-news" role="form" method="POST">
                            <div class="tab-pane active" id="config-add-news">
                                <div class="panel-config-shop">    		                               	
                                    <div class="box-step mgt-25"> 
                                        <div class="config-shop-interface-item">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <input name="id" type="hidden" value="${news.id}" />
                                                    <label class="col-sm-2 control-label">Tên bài:</label>
                                                    <div class="col-sm-4">
                                                        <input type="text" class="form-control" name="title" value="${news.title}">
                                                    </div>
                                                    <label class="col-sm-2 control-label">Danh mục:</label>
                                                    <div class="col-sm-4">
                                                        <select class="form-control" name="categoryId">
                                                            <option value="">Chọn danh mục</option>
                                                            <c:forEach var="cat" items="${listcat}">
                                                                <c:if test="${cat.parentId == null}">
                                                                    <c:if test="${news.categoryId != null && news.categoryId == cat.id}">
                                                                        <option value="${cat.id}" selected="">${cat.name}</option>
                                                                    </c:if>
                                                                    <c:if test="${news.categoryId == null || news.categoryId != cat.id}">
                                                                        <option value="${cat.id}" >${cat.name}</option>
                                                                    </c:if>
                                                                    <c:forEach var="catlv2" items="${listcat}">
                                                                        <c:if test="${catlv2.parentId == cat.id}"> 
                                                                            <c:if test="${news.categoryId != null && news.categoryId == catlv2.id}">
                                                                                <option value="${catlv2.id}" selected=""> -- ${catlv2.name}</option>
                                                                            </c:if>
                                                                            <c:if test="${news.categoryId == null || news.categoryId != catlv2.id}">
                                                                                <option value="${catlv2.id}" > -- ${catlv2.name}</option>
                                                                            </c:if>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Nội dung bài viết:</label>
                                                    <div class="col-sm-10">
                                                        <textarea class="form-control" rows="5" name="detail">${news.detail}</textarea>                                                            </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Trạng thái hiển thị:</label>
                                                    <div class="col-sm-3">
                                                        <select name="active" class="form-control">
                                                            <option value="1" <c:if test="${news.active}">selected=""</c:if>>Hiển thị</option>
                                                                <option value="0">Không hiển thị</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label">Ảnh đại diện:</label>
                                                        <div class="col-sm-10">
                                                            <div class="help-block">
                                                                <span class="btn btn-default fileinput-button">
                                                                    <span>Upload ảnh</span>
                                                                    <input id="fileupload" type="file" name="image" />
                                                                </span>
                                                                <span class="help-block" id="valueImage"></span>
                                                            <c:if test="${news.image != null}">
                                                                <div><img src="${news.image}" /></div>
                                                                </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group mgt-25">
                                                    <label class="col-sm-2 control-label">&nbsp;</label>
                                                    <div class="col-sm-10">
                                                        <c:if test="${news != null}">
                                                            <button class="btn btn-lg btn-danger" onclick="shopnews.editNews();">Lưu</button> 
                                                        </c:if>
                                                        <c:if test="${news == null}">
                                                            <button class="btn btn-lg btn-danger" onclick="shopnews.addNews();">Lưu</button> 
                                                        </c:if>
                                                    </div>                                    
                                                </div>  
                                            </div>
                                        </div>
                                    </div>
                                </div>                                 	
                                <div class="clearfix"></div>
                            </div>
                        </form>
                    </div>        
                </div>                            
            </div>                     
        </div>   
    </div>
</div>