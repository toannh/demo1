<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-lg-9 col-md-9 col-sm-8 col-xs-12 right-content">
    <ol class="breadcrumb">
        <li><a href="#">Trang chủ</a></li>
        <li><a href="#">${alias}</a></li>
        <li>Hướng dẫn</li>
    </ol>
    <h2>Hướng dẫn</h2>

    <div class="box-module">
        ${shop.guide}
    </div>

</div>
