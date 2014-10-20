<%-- 
    Document   : featureProduct
    Created on : Jul 10, 2014, 11:21:30 AM
    Author     : PhucTd
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span><a href="${baseUrl}"> Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">
                ${viewer.user.username==null?viewer.user.email:viewer.user.username}   
            </a></li>
        <li class="active">Quản trị nội dung</li>
    </ol>
    <h1 class="title-pages">Quản trị Sản phẩm nổi bật</h1>
    <div class="tabs-intro"><a href="${baseUrl}/tin-tuc/huong-dan-tao-va-quan-tri-san-pham-noi-bat-trang-chu-shop-731670463935.html" target="_blank"><span class="icon16-faq"></span>Hướng dẫn tạo và quản trị sản phẩm nổi bật trang chủ Shop
        </a></div>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li class="dropdown">
                <a href="javascript:;" data-toggle="dropdown">Cấu hình shop <span class="fa fa-sort-down"></span></a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step1.html">Thông tin cơ bản</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step2.html">Chọn giao diện</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step5.html">Soạn danh mục sản phẩm</a></li>
                    <li><a href="${baseUrl}/user/cau-hinh-shop-step6.html">Soạn danh mục tin tức</a></li>                                                                                                            
                </ul>
            </li>
            <li class="dropdown active">
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
                    <div class="box-step" id="addItem"> 
                        <div class="title-config-shop">Quản trị sản phẩm nổi bật tại trang chủ (mặc định hiển thị ngay)</div>
                        <div class="config-shop-interface-item">
                            <div class="form-horizontal form-reset-col">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Icon đại diện:</label>
                                    <div class="col-sm-7">
                                        <input type="text" readonly="readonly" class="form-control" name="icon" placeholder="Chọn icon">
                                    </div>
                                    <div class="col-sm-2 reset-padding"><button type="button" onclick="shophomeitem.showIconBox()" class="btn btn-default">Chèn icon</button></div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Tên box nổi bật:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" name="name" placeholder="Tên box nổi bật">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Danh sách ID sản phẩm:</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" name="itemIds" placeholder="Danh sách id sản phẩm " />
                                    </div>
                                    <div class="col-sm-2 reset-padding"><button type="button"  id="btnSelectItem" class="btn btn-default">Chọn sản phẩm</button></div>
                                </div>  
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Vị trí hiển thị:</label>
                                    <div class="col-sm-4">
                                        <input type="text" class="form-control" name="position" value="" placeholder="Vị trí hiển thị">                                                
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Trạng thái:</label>
                                    <div class="col-sm-4">
                                        <select class="form-control" name="active">
                                            <option value="true">Hiển thị</option>
                                            <option value="false">Không hiển thị</option>
                                        </select>                                                  
                                    </div>
                                </div>  
                                <div class="form-group">
                                    <label class="col-sm-2 control-label display-view">&nbsp;</label>
                                    <div class="col-sm-9">
                                        <button class="btn btn-lg btn-danger" onclick="shophomeitem.addShopHomeItem()">Thêm</button>                                                </div>                                    
                                </div>  
                            </div>
                        </div>
                    </div>
                    <c:if test="${fn:length(items) <= 0}">
                        <div class="cdt-message bg-danger text-center">Chưa có sản phẩm nào!</div>
                    </c:if>
                    <c:if test="${fn:length(items) > 0}">
                        <div class="box-step mgt-25">                             
                            <div class="title-config-shop">Danh sách box và sản phẩm nổi bật</div>
                            <div class="table-responsive list-box-product">
                                <table class="table" width="100%">
                                    <tr class="warning">
                                        <th width="5%" align="center" valign="top">
                                    <div class="text-center"><input type="checkbox" name="checkItem"></div>
                                    </th>
                                    <th width="25%">
                                        <button class="btn btn-danger btn-sm" onclick="shophomeitem.removes()"><span class="glyphicon glyphicon-trash"></span> Xoá</button> &nbsp; Tên box</th>
                                    <th width="35%" valign="middle"><div class="text-left">ID Sản phẩm</div></th>
                                    <th width="15%" valign="middle"><div class="text-left">Trạng thái</div></th>
                                    <th width="20%" valign="middle"><div class="text-center">Thao tác</div></th>
                                    </tr>
                                    <c:forEach items="${items}" var="item">
                                        <tr>
                                            <td valign="top" align="center"><div class="text-center"><input type="checkbox" for="checkallbox" value="${item.id}"></div></td>
                                            <td for="name"><div class="text-left">${item.name}</div></td>
                                            <td for="itemIds">
                                                <div class="text-left"><c:forEach items="${item.itemIds}" var="id" varStatus="loop"><c:if test="${loop.index!=0}">, ${id}</c:if><c:if test="${loop.index==0}">${id}</c:if></c:forEach></div>
                                                    </td>
                                                        <td for="icon" class="hidden">${item.icon}</td>
                                            <td for="position" class="hidden">${item.position}</td>
                                            <td class="${item.id}"><span class="glyphicon glyphicon-${item.active==true?'ok visited':'ban-circle icon-hidden'}"></span> ${item.active==true?'Hiển thị':'Không hiển thị'}</td>
                                            <td valign="top" align="center">
                                                <div class="text-center">
                                                    <button class="btn btn-default btn-sm" onclick="shophomeitem.edit('${item.id}')">Sửa</button>
                                                    <button class="btn btn-default btn-sm" onclick="shophomeitem.remove('${item.id}')">Xoá</button>
                                                    <button class="btn btn-default btn-sm btn-active" onclick="shophomeitem.changeActive('${item.id}')">${item.active==true?'Ẩn':'Hiện'}</button>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>  
                    </c:if>
                </div>                            
            </div>                     
        </div>   
    </div>
</div>