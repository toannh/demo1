<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAgrj58PbXr2YriiRDqbnL1RSqrCjdkglBijPNIIYrqkVvD1R4QxRl47Yh2D_0C1l5KXQJGrbkSDvXFA"
type="text/javascript"></script>


<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="#">Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username != null?viewer.user.username:viewer.user.email}</a></li>
        <li class="active">Quản trị shop</li>
    </ol>
    <h1 class="title-pages">Quản trị shop</h1>
    <div class="tabs-content-user">
        <div class="tabs-intro">
            <a href="${baseUrl}/tin-tuc/huong-dan-cau-hinh-thong-tin-co-ban-shop-113226526964.html" target="_blank">
                <span class="icon16-faq"></span>Hướng dẫn cấu hình shop
            </a>
        </div>
        <ul class="tab-title-content">
            <li class="dropdown active">
                <a href="#" data-toggle="dropdown">Cấu hình shop <span class="fa fa-sort-down"></span></a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step1.html">Thông tin cơ bản</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step2.html">Chọn giao diện</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step5.html">Soạn danh mục sản phẩm</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step5.html">Soạn danh mục tin tức</a></li>                                                                                                            
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
                            <li class="active step-success" onclick="shop.nextStep(1);" rel="step1">
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
                            <li  onclick="shop.nextStep(3);">
                                <div class="step" title="Bố cục dàn trang">
                                    <span class="glyphicon glyphicon-ok"></span>
                                    <p><strong>Bước 3</strong></p>
                                    <span>Bố cục dàn trang</span>
                                </div>                                                
                            </li>
                            <li  onclick="shop.nextStep(4);">
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
                    <div class="box-step mgt-25">
                        <div class="title-config-shop">Tài khoản giao dịch</div>
                        <div class="form-horizontal configshop-step">
                            <input name="slId" value="${viewer.user.id}" type="hidden" />
                            <input name="shopId" value="${shop.userId}" type="hidden" />
                            <c:if test="${viewer.user.username == null || viewer.user.username == '' || viewer.user.email == null ||viewer.user.email == '' || viewer.user.address == null ||viewer.user.address == '' || viewer.user.phone == null || viewer.user.phone == ''}">
                                <div class="form-group has-error">
                                    <div class="col-sm-12">
                                        <p class="help-block">Bạn chưa cập nhật đầy đủ thông tin. Vui lòng click <a target="_blank" href="${baseUrl}/user/profile.html">vào đây</a> để cập nhật.</p>
                                    </div>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label class="col-lg-2 col-md-3 col-sm-3 col-xs-3 control-label">Tài khoản:</label>
                                <div class="col-lg-10 col-md-9 col-sm-9 col-xs-9">
                                    <label class="control-label">${viewer.user.username}</label>                                   
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 col-md-3 col-sm-3 col-xs-3 control-label">Tên chủ tài khoản:</label>
                                <div class="col-lg-10 col-md-9 col-sm-9 col-xs-9">
                                    <label class="control-label">${viewer.user.name}</label>                                     
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 col-md-3 col-sm-3 col-xs-3 control-label">Địa chỉ:</label>
                                <div class="col-lg-10 col-md-9 col-sm-9 col-xs-9">
                                    <label class="control-label" for="address">${viewer.user.address}</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 col-md-3 col-sm-3 col-xs-3 control-label">Điện thoại:</label>
                                <div class="col-lg-10 col-md-9 col-sm-9 col-xs-9">
                                    <label class="control-label">${viewer.user.phone}</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 col-md-3 col-sm-3 col-xs-3 control-label">E-mail:</label>
                                <div class="col-lg-10 col-md-9 col-sm-9 col-xs-9"><label class="control-label">${viewer.user.email}</label></div>                                    
                            </div>                                                                      
                        </div> 
                    </div><!--Tài khoản giao dịch-->  
                    <div class="box-step mgt-25">                             
                        <div class="title-config-shop">Thông tin shop </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Tên shop: <span class="clr-red">*</span></label>
                                <div class="col-sm-8">
                                    <input type="text" name="title" class="form-control"  value="${shop.title}">	 			
                                    <div class="help-block clr-999" for="title">(Tên công ty hoặc địa chỉ cửa hàng)</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Địa chỉ truy cập (URL): <span class="clr-red">*</span></label>
                                <div class="col-sm-10" rel="checkAlias">
                                    <label class="control-label shopUrl" for="alias" style="color: #000">http://chodientu.vn/${shop.alias}/ <a><span class="glyphicon glyphicon-pencil" onclick="shop.quickEdit()" style="cursor: pointer;margin-left: 5px"></span></a></label>
                                    <span class="help-block" for="alias"></span>
                                    <div class="help-block clr-999" for="alias" style="color: #999">(Địa chỉ để truy cập vào shop tối đa 100 ký tự và chỉ được nhập các ký tự từ a-z, 0-9,_)</div>
                                </div>                                    
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">E-Mail:<span class="clr-red">*</span></label>
                                <div class="col-sm-8">
                                    <input type="text" name="email" class="form-control"  value="${shop.email}">
                                    <div class="help-block clr-999" for="email">(Địa chỉ Email liên hệ của Shop)</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Địa chỉ shop:<span class="clr-red">*</span></label>
                                <div class="col-sm-8">
                                    <input type="text" name="address" class="form-control"  value="${shop.address}">
                                    <div class="help-block clr-999" for="address">(Địa chỉ này có thể khác trong thông tin tài khoản)</div>
                                </div>
                            </div>
                            <div class="form-group" for="city">
                                <label class="col-sm-2 control-label">Tỉnh / Thành phố:<span class="clr-red">*</span></label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="cityId" onchange="shop.findDistrict(this)">
                                        <option value="0">Chọn tỉnh, thành phố</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group" for="district">
                                <label class="col-sm-2 control-label">Quận / huyện:<span class="clr-red">*</span></label>
                                <div class="col-sm-8">
                                    <select class="form-control" name="districtId">
                                        <option value="0">Chọn quận, huyện</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">Điện thoại:<span class="clr-red">*</span></label>
                                <div class="col-sm-8">
                                    <input type="text" name="phone" class="form-control"  value="${shop.phone}" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">&nbsp;</label>
                                <div class="col-sm-10">
                                    <button class="btn btn-danger" onclick="shop.updateInfo();" id="btnUpdate">Cập nhật</button> 
                                </div>
                            </div>  
                        </div>   
                    </div>   <!--Thông tin shop-->  
                    <div class="box-step mgt-25">
                        <div class="title-config-shop">Thông tin giới thiệu </div>
                        <div class="form-horizontal">
                            <div class="form-group">                                    
                                <div class="col-sm-12">
                                    <textarea class="form-control" rows="9" name="infoAbout">${shop.about}</textarea>
                                </div>
                            </div>                                                                     
                            <div class="form-group text-center">
                                <button class="btn btn-danger" onclick="shop.saveinfoShop('infoAbout');">Cập nhật</button> 
                            </div>  
                        </div>   
                    </div><!--Thông tin giới thiệu-->
                    <div class="box-step mgt-25">
                        <div class="title-config-shop">Thông tin hướng dẫn </div>
                        <div class="form-horizontal">
                            <div class="form-group">                                    
                                <div class="col-sm-12">
                                    <textarea class="form-control" rows="9"  name="infoGuide">${shop.guide}</textarea>
                                </div>
                            </div>                                                                     
                            <div class="form-group text-center">
                                <button class="btn btn-danger" onclick="shop.saveinfoShop('infoGuide');">Cập nhật</button> 
                            </div>  
                        </div>   
                    </div><!--Thông tin hướng dẫn-->
                    <div class="box-step mgt-25">
                        <div class="title-config-shop">Thông tin Footer  </div>
                        <div class="form-horizontal">
                            <div class="form-group">                                    
                                <div class="col-sm-12">
                                    <textarea class="form-control" rows="9"  name="infoFooter">${shop.footer}</textarea>
                                </div>
                            </div>  
                            <div class="form-group text-center">
                                <button class="btn btn-danger" onclick="shop.saveinfoShop('infoFooter');">Cập nhật</button> 
                            </div>  
                        </div>      
                    </div> <!--Thông tin footer-->
                    <div class="box-step mgt-25" id="configMap">
                        <div class="title-config-shop">Support Online</div>
                        <div class="form-horizontal">
                            <p>Tạo user hỗ trợ online trên shop!</p>
                            <div class="list-support-item table-responsive">
                                <table class="table table-bordered">
                                    <tr class="warning">
                                        <th scope="col">Title</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Phone</th>
                                        <th scope="col">Yahoo</th>
                                        <th scope="col">Skype</th>
                                        <th scope="col">Thao tác</th>       
                                    </tr>
                                    <!--Begin for-->
                                    <c:if test="${fn:length(contacts) > 0}">
                                        <c:forEach var="contact" items="${contacts}">
                                            <tr for="${contact.id}">
                                                <td>${contact.title}</td>
                                                <td>${contact.email}</td>
                                                <td>${contact.phone}</td>
                                                <td>${contact.yahoo}</td>
                                                <td>${contact.skype}</td>
                                                <td>
                                                    <div class="text-center">
                                                        <a onclick="shop.editUserContact('${contact.id}');" style="cursor: pointer">Sửa</a> |
                                                        <a onclick="shop.delUserContact('${contact.id}');" style="cursor: pointer">Xoá</a>
                                                    </div>
                                                </td>    
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    <tbody class="listContact">
                                    </tbody>
                                    <tr style="vertical-align: middle">
                                        <td colspan="6"><a onclick="shop.insertUserContact();" class="btn btn-default btn-sm">Thêm user</a></td>
                                    </tr>
                                    <!--End Begin for-->    
                                </table>
                            </div>
                        </div>    
                    </div> <!--Thông tin support-->
                    <div class="box-step mgt-25" >
                        <div class="title-config-shop">Bản đồ đường đi và địa điểm <a class="pull-right btn btn-default btn-sm" onclick="shop.changeMap();">Lưu tọa độ</a></div>
                        <input type="hidden" id="lat" for="lat"></input>
                        <input type="hidden" id="lng" for="lng"></input>
                        <div class="form-horizontal">
                            <p>Kéo sticker trên bản đồ để thay đổi tọa độ địa chỉ shop của bạn,sau đó click nút <b>Lưu tọa độ</b>. (Mặc định hệ thống sẽ tự động hiển thị địa chỉ Shop của bạn dựa vào địa chỉ Shop hiện tại)</p>    
                            <!-- google map -->
                            <div id="googleMap" style="height: 550px; border: 1px solid #6d6d6d"></div>
                            <!-- google map -->
                            <div class="form-group text-center mgt-25">
                                <button class="btn btn-primary btn-lg" onclick="shop.nextStep(2);">Qua bước 2</button> 
                            </div>      
                        </div>    
                    </div>                     
                </div>                            
            </div>                     
        </div>   
    </div>
</div>

