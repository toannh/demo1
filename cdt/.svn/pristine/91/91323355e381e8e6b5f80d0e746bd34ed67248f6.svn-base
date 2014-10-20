<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách tỉnh thành
        </a>
    </li>
</ul>
<div class="func-container"> 
    <div class="clearfix"></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${city.size()} </strong> kết quả.
        </h5>             
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center" style="vertical-align: middle;" >Tỉnh / Thành phố</th>
            <th class="text-center" style="vertical-align: middle" >Thứ tự</th>
            <th class="text-center" style="vertical-align: middle" >Mã ship chung</th>
            <th class="text-center" style="vertical-align: middle" >
                <button onclick="city.add();" type="button" class="btn btn-success">
                    <span class="glyphicon glyphicon-plus"></span> Thêm mới</button>
            </th>
        </tr>        
        <c:forEach var="ct" items="${city}" varStatus="stt">            
            <tr>
                <td class="text-center" style="vertical-align: middle">${ct.name}</td>
                <td class="text-center" style="vertical-align: middle">
                    <p>${ct.position}</p>
                </td>
                <td class="text-center" style="vertical-align: middle;">${ct.scId}</td>
                <td class="text-center" style="vertical-align: middle">
                    <div class="btn-group">
                        <button type="button" class="btn btn-success" onclick="city.listDistrict('${ct.id}');" >
                            <span class="glyphicon glyphicon-list"></span> Quận / huyện</button>
                        <button type="button" class="btn btn-info" onclick="city.edit('${ct.id}');" >
                            <span class="glyphicon glyphicon-edit"></span> Sửa</button>
                        <button type="button" class="btn btn-danger" onclick="city.delCity('${ct.id}');" >
                            <span class="glyphicon glyphicon-trash"></span> Xóa</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${fn:length(city) == 0}">
            <tr>
                <td colspan="5" class="text-center text-danger">Không tìm thấy tỉnh / thành nào</td>
            </tr>   
        </c:if>
    </table>
</div>