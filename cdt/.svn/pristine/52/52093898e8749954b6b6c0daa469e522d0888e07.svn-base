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
    <h1 class="title-pages">Quản trị banner</h1>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-quan-tri-banner-shop-145921817681.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn quản trị banner Shop
        </a></div>
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
                    <p><strong>Quản trị banner</strong></p>
                    <ul class="nav nav-tabs">
                        <li><a href="${baseUrl}/user/shop-banner.html">Heart banner trang chủ</a></li>
                        <li class="active"><a href="${baseUrl}/user/shop-banner-ads.html">Banner quảng cáo</a></li>
                    </ul>                                        
                    <!-- Tab panes -->
                    <div class="tab-content ">
                        <div class="tab-pane active" id="config-heart-banner">
                            <div class="panel-config-shop">
                                <c:if test="${shopBannerRow==null}">
                                    <div class="box-step mgt-25"> 
                                        <div class="title-config-shop">Thêm banner (mặc định hiển thị ngay)</div>
                                        <form class="form-horizontal" id="form-add-banner" role="form" >
                                            <div class="config-shop-interface-item">
                                                <div class="form-horizontal">
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">Tên mô tả:</label>
                                                        <div class="col-sm-6">
                                                            <input type="text" class="form-control" name="title">
                                                        </div>

                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">Vị trí quảng cáo:</label>
                                                        <div class="col-sm-9">
                                                            <select class="form-control pull-left" name="type" style="width:40%;">
                                                                <option value="ADV_LEFT">Cột trái</option>
                                                                <!--<option value="ADV_RIGHT">Cột phải</option>-->
                                                            </select>
                                                            <label class="col-sm-4 control-label"><div class="text-left">(Kích cỡ: 200x200 px )</div></label>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">Định dạng:</label>
                                                        <div class="col-sm-8">
                                                            <div class="clearfix check-type-option" id="typeFormat">
                                                                <label class="radio-inline">
                                                                    <input type="radio" name="bannerType" value="type-image" id="type-flash-tab1"> Image
                                                                </label>
                                                                <label class="radio-inline">
                                                                    <input type="radio" name="bannerType" value="type-video" id="type-video-tab1"> Video
                                                                </label>
                                                            </div>                                                            </div>
                                                    </div>
                                                    <div class="content-expand" id="box-type-flash-tab1" style="display: none">  
                                                        <div class="form-group">
                                                            <label class="col-sm-3 control-label display-view">&nbsp;</label>
                                                            <div class="col-sm-9">
                                                                <div class="clearfix">
                                                                    <input type="file" id="lefile" name="image">
                                                                    <!--<span class="btn btn-default" onclick="$('#lefile').click();">Upload ảnh</span> &nbsp; Dung lượng file up tối đa < 0.5MB, các định dạng file hợp lệ gif, jpg, jpeg-->

                                                                </div>

                                                            </div>
                                                        </div>  
                                                        <div class="form-group">
                                                            <label class="col-sm-3 control-label">Đường dẫn:</label>
                                                            <div class="col-sm-6">
                                                                <input type="text" class="form-control" value="http://" name="url">
                                                                <div class="help-block">Banner dạng flash thì để trống thông tin ở ô này</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="content-expand" id="box-type-video-tab1" style="display: none">  
                                                        <div class="form-group">
                                                            <label class="col-sm-3 control-label display-view">Mã nhúng video</label>
                                                            <div class="col-sm-9">
                                                                <textarea class="form-control" rows="6" name="embedCode"></textarea>

                                                            </div>
                                                        </div>                                                              
                                                    </div>  
                                                    <div class="form-group mgt-25">
                                                        <label class="col-sm-3 control-label display-view">&nbsp;</label>
                                                        <div class="col-sm-9">
                                                            <button class="btn btn-lg btn-danger" onclick="return shopbanner.add();">Thêm banner</button> 
                                                            <a onclick="shopbanner.reload();" style="cursor: pointer" class="btn-remove"><span class="glyphicon glyphicon glyphicon-remove"></span> Huỷ</a> 
                                                        </div>                                    
                                                    </div>  

                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                                <c:if test="${shopBannerRow!=null}">
                                    <div class="box-step mgt-25"> 
                                        <div class="title-config-shop">Sửa banner (mặc định hiển thị ngay)</div>
                                        <form class="form-horizontal" id="form-edit-banner" role="form" >
                                            <input type="hidden" class="form-control" name="id" value="${shopBannerRow.id}">
                                            <div class="config-shop-interface-item">
                                                <div class="form-horizontal">
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">Tên mô tả:</label>
                                                        <div class="col-sm-6">
                                                            <input type="text" class="form-control" name="title" value="${shopBannerRow.title}">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">Vị trí quảng cáo:</label>
                                                        <div class="col-sm-9">
                                                            <select class="form-control pull-left" name="type" style="width:40%;">
                                                                <option <c:if test="${shopBannerRow.type=='ADV_LEFT'}">selected="true"</c:if> value="ADV_LEFT">Cột trái</option>
                                                                <option <c:if test="${shopBannerRow.type=='ADV_RIGHT'}">selected="true"</c:if> value="ADV_RIGHT">Cột phải</option>
                                                                </select>
                                                                <label class="col-sm-4 control-label"><div class="text-left">(Kích cỡ: 200x200 px )</div></label>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-sm-3 control-label">Định dạng:</label>
                                                            <div class="col-sm-8">
                                                                <div class="clearfix check-type-option" id="typeFormat">
                                                                    <label class="radio-inline">
                                                                        <input type="radio" <c:if test="${shopBannerRow.url!=null}"> checked="true"</c:if> name="bannerType" value="type-image" id="type-flash-tab1"> Image
                                                                    </label>
                                                                    <label class="radio-inline">
                                                                        <input type="radio" <c:if test="${shopBannerRow.url==null}"> checked="true"</c:if> name="bannerType" value="type-video" id="type-video-tab1"> Video
                                                                    </label>
                                                                </div>                                                            </div>
                                                        </div>
                                                        <div class="content-expand" id="box-type-flash-tab1" <c:if test="${shopBannerRow.url==null}">style="display: none"</c:if>>  

                                                            <div class="form-group">
                                                                <label class="col-sm-3 control-label display-view">&nbsp;</label>
                                                                <div class="col-sm-9">
                                                                    <div class="clearfix">
                                                                        <input type="file" id="lefiles" name="image">
                                                                        <!--<span class="btn btn-default" onclick="$('#lefiles').click();">Upload ảnh</span> &nbsp; Dung lượng file up tối đa < 1,5MB, các định dạng file hợp lệ gif, jpg, jpeg-->
                                                                    </div>
                                                                <c:if test="${shopBannerRow.image!=null}"><img src="${shopBannerRow.image}" width="100"/></c:if>
                                                                </div>
                                                            </div>  
                                                            <div class="form-group">
                                                                <label class="col-sm-3 control-label">Đường dẫn:</label>
                                                                <div class="col-sm-6">
                                                                    <input type="text" class="form-control" value="${shopBannerRow.url}" name="url">
                                                                <div class="help-block">Banner dạng flash thì để trống thông tin ở ô này</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="content-expand" id="box-type-video-tab1" <c:if test="${shopBannerRow.url!=null}">style="display: none"</c:if>>  
                                                            <div class="form-group">
                                                                <label class="col-sm-3 control-label display-view">Mã nhúng video</label>
                                                                <div class="col-sm-9">
                                                                    <textarea class="form-control" rows="6" name="embedCode">${shopBannerRow.embedCode}</textarea>

                                                            </div>
                                                        </div>                                                              
                                                    </div>  
                                                    <div class="form-group mgt-25">
                                                        <label class="col-sm-3 control-label">&nbsp;</label>
                                                        <div class="col-sm-9">
                                                            <button class="btn btn-lg btn-danger" onclick="return shopbanner.edit();">Lưu banner</button> 
                                                            <a href="#" class="btn-remove"><span class="glyphicon glyphicon glyphicon-remove"></span> Huỷ</a> 
                                                        </div>                                    
                                                    </div>  

                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                                <div class="box-step mgt-25">                             
                                    <div class="title-config-shop">Danh sách banner</div>
                                    <c:if test="${fn:length(shopBanners) <= 0}">
                                        <div class="cdt-message bg-danger text-center">Không tìm thấy banner nào!</div>
                                    </c:if>
                                    <c:if test="${fn:length(shopBanners) > 0}">
                                        <div class="table-responsive manager-content">
                                            <table class="table" width="100%">
                                                <tr class="warning">

                                                    <th width="25%"> Ảnh</th>
                                                    <th width="35%" valign="middle"><div class="text-left">Đường dẫn/Tên mô tả</div></th>
                                                <th width="15%" valign="middle"><div class="text-left">Trạng thái</div></th>
                                                <th width="20%" valign="middle"><div class="text-center">Thao tác</div></th>
                                                </tr>
                                                <c:forEach items="${shopBanners}" var="shopBanner">
                                                    <tr>
                                                        <td>
                                                            <div class="table-content-list">
                                                                <c:if test="${shopBanner.url!=null}">
                                                                    <img src="${shopBanner.image}" style="border: 1px #DDDDDD solid" />
                                                                </c:if>
                                                                <c:if test="${shopBanner.url==null}">
                                                                    <img title="Click vào để xem video" src="${staticUrl}/user/images/videodefault.png" style="border: 1px #DDDDDD solid" onclick="shopbanner.get('${shopBanner.id}');"/>
                                                                </c:if>
                                                            </div>
                                                        </td>
                                                        <td><div class="text-left">
                                                                <p class="text-left">${shopBanner.title}</p>
                                                                <c:if test="${shopBanner.url!=null}"><a href="${shopBanner.url}" title="${shopBanner.url}" target="_blank">Đường link dẫn</a> </c:if>

                                                                </div></td>
                                                            <td>
                                                            <c:if test="${shopBanner.active}">
                                                                <span editStatus="${shopBanner.id}"><span class="glyphicon glyphicon-ok visited"></span> Hiển thị</span>
                                                            </c:if>
                                                            <c:if test="${!shopBanner.active}">
                                                                <span editStatus="${shopBanner.id}"><span class="glyphicon glyphicon-ban-circle icon-hidden"></span> Không hiển thị</span> 
                                                            </c:if>
                                                        </td>
                                                        <td valign="top" align="center">
                                                            <div class="text-center">
                                                                <c:if test="${shopBanner.active}">
                                                                    <span editIcon="${shopBanner.id}"> 
                                                                        <button class="btn btn-default btn-sm" onclick="shopbanner.editStatus(${shopBanner.id})">Ẩn</button>
                                                                    </span>
                                                                </c:if>
                                                                <c:if test="${!shopBanner.active}">
                                                                    <span editIcon="${shopBanner.id}"> 
                                                                        <button class="btn btn-success btn-sm" onclick="shopbanner.editStatus(${shopBanner.id})">Hiện</button>
                                                                    </span>
                                                                </c:if>
                                                                <a href="${baseUrl}/user/shop-banner-ads.html?id=${shopBanner.id}"><button class="btn btn-info btn-sm">Sửa</button></a>
                                                                <button class="btn btn-danger btn-sm" onclick="shopbanner.del(${shopBanner.id})">Xoá</button>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </c:if>
                                </div>   <!--Logo shop--> 
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>  <!--Tabs content--->            
                </div>                            
            </div>                     
        </div>   
    </div>
</div>