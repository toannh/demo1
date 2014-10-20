<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-lg-9 col-md-9 col-sm-8 col-xs-12 right-content">
    <ol class="breadcrumb">
        <li><a href="#">Trang chủ</a></li>
        <li><a href="#">${alias}</a></li>
        <li>Bản đồ</li>
    </ol>
    <h2>Bản đồ</h2>

    <div class="box-module">
        <c:choose>
            <c:when test="${shop.lng == 0 && shop.lat==0}" >
                <div class="alert alert-warning alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <c:if test="${viewer.user.id != null && viewer.user.id == shop.userId && viewer.user.id  != ''}">
                        Bạn chưa tạo địa chỉ nào để hiển thị trên bản đồ, <a href="${baseUrl}/user/cau-hinh-shop-step1.html#configMap">click vào đây</a> để tạo
                    </c:if>
                    <c:if test="${viewer.user.id == null || viewer.user.id != shop.userId}">
                        Shop chưa tạo địa chỉ nào để hiển thị trên bản đồ.
                    </c:if>
                </div>    
            </c:when>
            <c:otherwise>
                <div style="width:100%;height:500px" id="map"></div>        
            </c:otherwise>
        </c:choose>
    </div>

</div>
