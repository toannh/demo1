<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
                <a href="#" data-toggle="dropdown">Quản trị nội dung <span class="fa fa-sort-down"></span></a>
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
                        <li class="active"><a href="${baseUrl}/user/shop-news.html">Danh sách tin bài</a></li>
                        <li><a href="${baseUrl}/user/shop-add-news.html">Thêm bài mới</a></li>
                    </ul>                                        
                    <!-- Tab panes -->
                    <div class="tab-content ">
                        <div class="tab-pane active" id="config-list-news">
                            <div class="panel-config-shop">    
                                <form modelAttribute="newssearch" method="POST" role="form" style="margin-top: 20px;">
                                    <div class="box-step"> 
                                        <div class="title-config-shop">Tìm kiếm bài viết</div>
                                        <div class="config-shop-interface-item">                                             <div class="form-horizontal">       	
                                                <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">Từ khoá:</label>
                                                        <div class="col-sm-9">
                                                            <input type="text" name="title" class="form-control" value="${newssearch.title}" />
                                                        </div>
                                                    </div>
                                                </div>  
                                                <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">Danh mục:</label>
                                                        <div class="col-sm-9">
                                                            <select class="form-control" name="categoryId">
                                                                <option value="">Tất cả danh mục</option>
                                                                <c:forEach var="cat" items="${newscategory}">
                                                                    <c:if test="${cat.parentId == null}">
                                                                        <c:if test="${newssearch.categoryId == cat.id && newssearch.categoryId != null }">
                                                                            <option value="${cat.id}" selected="">${cat.name}</option>
                                                                        </c:if>
                                                                        <c:if test="${newssearch.categoryId != cat.id}">
                                                                            <option value="${cat.id}" >${cat.name}</option>
                                                                        </c:if>
                                                                        <c:forEach var="catlv2" items="${newscategory}">
                                                                            <c:if test="${catlv2.parentId == cat.id}"> 
                                                                                <c:if test="${newssearch.categoryId == catlv2.id && newssearch.categoryId != null }">
                                                                                    <option value="${catlv2.id}" selected> -- ${catlv2.name}</option>
                                                                                </c:if>
                                                                                <c:if test="${search.categoryId != catlv2.id}">
                                                                                    <option value="${catlv2.id}"> -- ${catlv2.name}</option>
                                                                                </c:if>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>                                                
                                                <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">Thời gian tạo từ:</label>
                                                        <div class="col-sm-4">
                                                            <div class="date-picker-block">
                                                                <input type="hidden" class="form-control timeselectStart" name="createTimeFrom" placeholder="Từ ngày"  >
                                                                <span class="glyphicon glyphicon-calendar"></span>     
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-5 reset-padding">
                                                            <div class="form-group">
                                                                <label class="col-sm-2 control-label">Tới</label>
                                                                <div class="col-sm-10">
                                                                    <div class="date-picker-block">
                                                                        <input type="hidden" class="form-control timeselectEnd" name="createTimeTo"  placeholder="Tới ngày" >
                                                                        <span class="glyphicon glyphicon-calendar"></span>     
                                                                    </div> 
                                                                </div>                                    
                                                            </div>                                                                   
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12">
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label display-view">&nbsp;</label>
                                                        <div class="col-sm-9">
                                                            <button class="btn btn-default" type="submit">Lọc</button> 
                                                        </div>                                    
                                                    </div> 
                                                </div>

                                            </div>
                                            <div class="clearfix"></div>  
                                        </div>
                                    </div>
                                </form>
                                <div class="box-step mgt-25">                             
                                    <div class="title-config-shop">Danh sách bài </div>
                                    <c:if test="${fn:length(newspage.data) <= 0}">
                                        <div class="cdt-message bg-danger text-center">Không tìm thấy bài tin nào!</div>
                                    </c:if>
                                    <c:if test="${fn:length(newspage.data) > 0}">
                                        <div class="table-responsive manager-content">
                                            <table class="table" width="100%">
                                                <tbody><tr class="warning">
                                                        <th width="5%" align="center" valign="top">
                                                <div class="text-center"><input type="checkbox" name="checkbox" onclick="shopnews.checkall(this, 'checkedItem')"></div>
                                                </th>
                                                <th width="35%"><button class="btn btn-danger btn-sm" onclick="shopnews.delAll();"><span class="glyphicon glyphicon-trash"></span> Xoá</button> &nbsp; Tiêu đề</th>
                                                <th width="20%" valign="middle"><div class="text-left">Danh mục</div></th>

                                                <th width="15%" valign="middle"><div class="text-left">Trạng thái</div></th>
                                                <th width="20%" valign="middle"><div class="text-center">Thao tác</div></th>
                                                </tr>

                                                <c:forEach var="news" items="${newspage.data}">
                                                    <c:forEach var="cate" items="${listCat}">
                                                        <c:if test="${news.categoryId == cate.id}" >
                                                            <tr>
                                                                <td valign="top" align="center"><div class="text-center"><input type="checkbox" name="${news.id}" class="checkedItem"></div></td>
                                                                <td>
                                                                    <div class="table-content">
                                                                        <div class="img-product-bill-small">
                                                                            <img src="${news.image != null?news.image:staticUrl.concat('/market/images/no-image-product.png')}">
                                                                        </div>
                                                                        ${news.title}
                                                                    </div>
                                                                </td>
                                                                <td><div class="text-left">${cate.name}</div></td>
                                                                <td>
                                                                    <c:if test="${news.active}">
                                                                        <span class="glyphicon glyphicon-ok visited" ></span> Hiển thị
                                                                    </c:if>
                                                                    <c:if test="${!news.active}">
                                                                        <span class="glyphicon glyphicon-ban-circle icon-hidden"></span> Không hiển thị
                                                                    </c:if>
                                                                </td>
                                                                <td valign="top" align="center">
                                                                    <div class="text-center">
                                                                        <button class="btn btn-default btn-sm" onclick="shopnews.edit('${news.id}');" >Sửa</button>
                                                                        <button class="btn btn-default btn-sm" onclick="shopnews.del('${news.id}');">Xoá</button>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </c:if>
                                </div>   <!--Logo shop--> 
                                <c:if test="${newspage.dataCount > 0}">
                                    <div class="page-ouner clearfix">
                                        <ul class="pagination pull-right">
                                            <c:if test="${newspage.pageIndex > 3}"><li><a href="${baseUrl}/user/shop-news.html?page=1" href="javascript:;"><<</a></li></c:if>
                                            <c:if test="${newspage.pageIndex > 2}"><li><a href="${baseUrl}/user/shop-news.html?page=${newspage.pageIndex}" ><</a></li></c:if>
                                            <c:if test="${newspage.pageIndex > 3}"><li><a>...</a></li></c:if>
                                            <c:if test="${newspage.pageIndex >= 3}"><li><a href="${baseUrl}/user/shop-news.html?page=${newspage.pageIndex - 2}">${newspage.pageIndex-2}</a></li></c:if>
                                            <c:if test="${newspage.pageIndex >= 2}"><li><a href="${baseUrl}/user/shop-news.html?page=${newspage.pageIndex - 1}" >${newspage.pageIndex-1}</a></li></c:if>
                                            <c:if test="${newspage.pageIndex >= 1}"><li><a href="${baseUrl}/user/shop-news.html?page=${newspage.pageIndex}" >${newspage.pageIndex}</a></li></c:if>
                                            <li class="active" ><a class="btn btn-primary">${newspage.pageIndex + 1}</a>
                                            <c:if test="${newspage.pageCount - newspage.pageIndex > 1}"><li><a href="${baseUrl}/user/shop-news.html?page=${newspage.pageIndex+2}" >${newspage.pageIndex+2}</a></li></c:if>
                                            <c:if test="${newspage.pageCount - newspage.pageIndex > 2}"><li><a href="${baseUrl}/user/shop-news.html?page=${newspage.pageIndex+3}" >${newspage.pageIndex+3}</a></li></c:if>
                                            <c:if test="${newspage.pageCount - newspage.pageIndex > 3}"><li><a >...</a></c:if>
                                            <c:if test="${newspage.pageCount - newspage.pageIndex > 2}"><li><a href="${baseUrl}/user/shop-news.html?page=${newspage.pageIndex+2}" >></a></li></c:if>
                                            <c:if test="${newspage.pageCount - newspage.pageIndex > 2}"><li><a href="${baseUrl}/user/shop-news.html?page=${newspage.pageCount}" >>></a></li></c:if>
                                            </ul>
                                        </div>
                                </c:if>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>       
                </div>                            
            </div>                     
        </div>   
    </div>
</div>