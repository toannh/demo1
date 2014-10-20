<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container">
    <h4>Quản trị tin tức nổi bật</h4>
</div>
<div class="container" style="margin-bottom: -1px;" >
    <div style="margin-top: 10px" >
        <ul class="nav nav-tabs nav-collapse">
            <li class="active"><a>Danh sách tin tức</a></li>
        </ul>
    </div>
</div>
<div class="container" style="border: 1px solid #ccc; padding-top: 10px" >
    <button onclick="featurednews.add();" type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>Thêm tin tức</button>

    <div style="margin-top: 10px">

    </div>

    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
        <tr>
            <th style="vertical-align: middle; text-align: center; ">Mã tin</th>
            <th style="vertical-align: middle; text-align: center; ">Mô tả</th>            
            <th style="vertical-align: middle; text-align: center; ">Ảnh hiển thị</th>
            <th style="text-align: center; width: 100px;">Thao tác</th>
        </tr>   
        <c:forEach items="${otherNewses}" var="otherNewse">
            <tr>
                <td style="vertical-align: middle; text-align: center;">${otherNewse.id}</td>
                <td style="vertical-align: middle; text-align: left;">
                    <c:if test="${not empty otherNewse.title}"><p><b>Tiêu đề:</b> ${otherNewse.title}</p></c:if> 
                    <p><b>Họ tên:</b> ${otherNewse.name}</p>
                    <p><b>Tên công ty:</b> ${otherNewse.nameCompany}</p>
                    <p><b>Thời gian tạo:</b> <span class="timestam">${otherNewse.timeCreate}</span></p>
                    <p><b>Tên công ty:</b> ${otherNewse.nameCompany}</p>
                    <p><b>URL:</b> <a href="${otherNewse.url}" target="_blank">${otherNewse.url}</a></p>
                    <p>
                        <b>Kiểu tin: </b> 
                        <c:if test="${otherNewse.type==1}">Câu chuyện thành công</c:if>
                        <c:if test="${otherNewse.type==2}">Nhận xét của khách hàng</c:if>
                    
                    </p>
                    <p>
                        <b>Trạng thái: </b> 
                        <c:if test="${otherNewse.active==true}">Đang hiển thị</c:if>
                        <c:if test="${otherNewse.active!=true}">Đang ẩn</c:if>
                    
                    </p>
                </td>            
                <td style="vertical-align: middle; text-align: center;"><img src="${otherNewse.image}" width="100"/></td>
                <td style="text-align: center; width: 100px;">
                    <button onclick="featurednews.edit('${otherNewse.id}')" class="btn btn-primary form-control"><span class="glyphicon glyphicon-edit"></span> Sửa</button>         
                    <button style="width: 100%; margin-top: 10px;" onclick="featurednews.del('${otherNewse.id}')" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xoá</button>

                </td>
            </tr>    
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">

    </div>
</div>