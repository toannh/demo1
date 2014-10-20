<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="text" uri="/WEB-INF/taglib/text.tld"%>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>

<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  <a href="${baseUrl}">Trang chủ</a></li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username==null?viewer.user.email:viewer.user.username}</a></li>
        <li class="active">Quan tâm người bán</li>
    </ol>
    <h1 class="title-pages">Quan tâm của tôi</h1>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li><a href="${baseUrl}/user/quan-tam-cua-toi.html">Sản phẩm</a></li>
            <li  class="active"><a href="${baseUrl}/user/quan-tam-nguoi-ban.html">Người bán</a></li>
            <li class="right-title pull-right">
                <div class="num-tin">
                    ${pageSellerFollow.pageIndex + 1}/${pageSellerFollow.pageCount} 
                    <c:if test="${pageSellerFollow.pageIndex + 1 > 1}">
                        <button onclick="order.nextPage(${pageSellerFollow.pageIndex}, false);" type="button" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </button> 
                    </c:if>
                    <c:if test="${pageSellerFollow.pageIndex + 1 < pageSellerFollow.pageCount}">
                        <button onclick="order.nextPage('${pageSellerFollow.pageIndex + 2}', false);" type="button" class="btn btn-default btn-sm">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </button>
                    </c:if>
                </div>
            </li>  
        </ul>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="list-table-content table-responsive">
                        <c:if test="${pageSellerFollow.dataCount <= 0}">
                            <div class="cdt-message bg-danger text-center">Bạn chưa quan tâm người bán nào!</div>
                        </c:if>
                        <c:if test="${pageSellerFollow.dataCount > 0}">
                            <table class="table" width="100%">
                                <tbody>
                                    <tr class="warning">
                                        <th width="5%" align="center" valign="top"><div class="text-center"><input type="checkbox" name="checkbox" onclick="itemfollow.checkall(this, 'checkedItem')" ></div></th>
                                <th width="70%"><button class="btn btn-danger btn-sm" onclick="itemfollow.delSellerFollow();"><span class="glyphicon glyphicon-trash"></span> Xoá</button> &nbsp; Người bán</th>
                                <th width="25%" valign="middle"><div class="text-center">Thao tác</div></th>
                                </tr>
                                <c:forEach var="sellerFollow" items="${pageSellerFollow.data}">
                                    <c:forEach items="${listSeller}" var="seller">
                                        <c:if test="${seller.id == sellerFollow.sellerId}">
                                            <c:set var="hasShop" value="false" /> 
                                            <c:forEach items="${shops}" var="shop">
                                                <c:if test="${seller.id==shop.userId}">
                                                    <c:set var="hasShop" value="true" />
                                                    <c:set var="shopAlias" value="${shop.alias}" />
                                                    <c:set var="shopTitle" value="${shop.title}" />
                                                </c:if>
                                            </c:forEach>
                                            <tr>
                                                <td valign="top" align="center"><div class="text-center"><input type="checkbox" name="${seller.id}" class="checkedItem" value="${seller.id}" ></div></td>
                                                <td>
                                                    <div class="table-content sales-item-detail">
                                                        <c:if test="${seller.avatar != null && seller.avatar != ''}">
                                                            <span class="img-list-tinvip"><img src="${seller.avatar}" class="img-responsive"></span>
                                                            </c:if>
                                                            <c:if test="${seller.avatar == null || seller.avatar == ''}">
                                                            <span class="img-list-tinvip"><img src="${baseUrl}/static/market/images/no-avatar.png" class="img-responsive"></span>
                                                            </c:if>
                                                        <p><a href="${baseUrl}/user/${sellerFollow.sellerId}/ho-so-nguoi-ban.html" target="_blank">${(sellerFollow.sellerName != '' && sellerFollow.sellerName != null )?sellerFollow.sellerName:seller.username}</a></p>
                                                            <c:if test="${hasShop}">
                                                            <p class="mgt-15">Shop: <a href="${baseUrl}/${shopAlias}" target="_blank">${shopAlias}</a></p>
                                                            <p class="category-name"><span class="fa fa-file-text-o"></span> <a href="${baseUrl}/${shopAlias}" target="_blank">${shopTitle}</a></p>
                                                            </c:if>
                                                    </div>
                                                </td>
                                                <td valign="top" align="center">
                                                    <div class="text-center btn-control">
                                                        <p><a href="${baseUrl}${url:browseUrl(itemSearch, '', '[{key:"sellerId",op:"mk",val:"'.concat(sellerFollow.sellerId).concat('"}]'))}" target="_blank">Xem sản phẩm của người bán</a></p>
                                                        <p><a onclick="itemfollow.noteSellerFollow();" style="cursor: pointer">Ghi chú</a></p>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                                </tbody></table>
                            </c:if>
                    </div>
                    <hr>
                    <div class="page-ouner clearfix">
                        <span class="pull-left"><strong>Có: ${pageSellerFollow.dataCount} kết quả</strong></span>
                        <ul class="pagination pull-right">
                            <c:if test="${pageSellerFollow.pageIndex > 2}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageSellerFollow.pageIndex});" >«</a></li></c:if>
                            <c:if test="${pageSellerFollow.pageIndex >= 3}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageSellerFollow.pageIndex-2});">${pageSellerFollow.pageIndex-2}</a></li></c:if>
                            <c:if test="${pageSellerFollow.pageIndex >= 2}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageSellerFollow.pageIndex-1});" >${pageSellerFollow.pageIndex-1}</a></li></c:if>
                            <c:if test="${pageSellerFollow.pageIndex >= 1}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageSellerFollow.pageIndex});">${pageSellerFollow.pageIndex}</a></li></c:if>
                            <li class="active" ><a class="btn btn-primary">${pageSellerFollow.pageIndex + 1}</a>
                            <c:if test="${pageSellerFollow.pageCount - pageSellerFollow.pageIndex > 1}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageSellerFollow.pageIndex+2});" >${pageSellerFollow.pageIndex+2}</a></li></c:if>
                            <c:if test="${pageSellerFollow.pageCount - pageSellerFollow.pageIndex > 2}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageSellerFollow.pageIndex+3});">${pageSellerFollow.pageIndex+3}</a></li></c:if>
                            <c:if test="${pageSellerFollow.pageCount - pageSellerFollow.pageIndex > 2}"><li><a href="javascript:;" onclick="itemfollow.changPage(${pageSellerFollow.pageIndex+2});" >»</a></li></c:if>
                        </ul>
                    </div>
                </div>                            
            </div>                     
        </div>   
    </div>
</div>