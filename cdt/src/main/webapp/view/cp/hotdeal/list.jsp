<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul class="nav nav-tabs" role="tablist">
    <li <c:if test="${hcategory ==null}">class="active"</c:if>><a href="${baseUrl}/cp/hotdeal.html"><i class="fa fa-list"></i>Danh sách HotDeal</a></li>
            <c:if test="${hcategory !=null}"><li class="active"><a>${hcategory.name}</a></li></c:if>
</ul>
<div class="func-container">
    <div style="margin-top: 10px" >
        </div>
        <div style="margin-top: 10px"></div>
        <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px">
            <tr>
                <th style="vertical-align: middle; text-align: center; ">Mã danh mục</th>
                <th style="vertical-align: middle; text-align: center; ">Tên danh mục</th>
                <th>Thông tin</th>            
                <th style="vertical-align: middle; text-align: center; ">Vị trí</th>
                <th style="vertical-align: middle; text-align: center; ">Trạng thái</th>
                <c:if test="${hcategory ==null}">
                <th style="vertical-align: middle; text-align: center; ">Box đặc biệt</th>
                </c:if>
            <th style="text-align: center; width: 100px;">
                <c:if test="${hcategory ==null}">
                    <button onclick="hotdeal.add();" type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Thêm mới</button>
                </c:if>
            </th>
        </tr>     
        <c:forEach items="${hcategories}" var="hcate">
            <tr>
                <td class="text-center">${hcate.id}</td>
                <td class="text-center">
                    <a href="${baseUrl}/cp/hotdeal.html?hcate=${hcate.id}">${hcate.name}</a>
                    </td>
                    <td class="text-left">
                    <jsp:useBean id="date" class="java.util.Date" />
                    <jsp:setProperty name="date" property="time" value="${hcate.createTime}" />   
                    <p>Ngày tạo:
                        <b><fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></b><br/>
                        </p>
                        <p>Ngày bắt đầu: 
                        <jsp:setProperty name="date" property="time" value="${hcate.startTime}" />
                        <b><fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></b><br/>
                        </p>
                        <p>Ngày kết thúc: 
                        <jsp:setProperty name="date" property="time" value="${hcate.endTime}" />
                        <b><fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate></b><br/>
                    </td>
                    <td class="text-center" style="text-align: center;">
                        <input name="position" for="${hcate.id}" class="form-control" style="width:50px;" onchange="hotdeal.changePosition('${hcate.id}');"  value="${hcate.position}"/>
                </td>
                <td class="text-center">
                    <a href="javascript:void(0);" onclick="hotdeal.changeStatus('${hcate.id}');" editStatus="${hcate.id}">
                        <c:if test="${!hcate.active}">
                            <img src="${staticUrl}/cp/img/icon-disable.png" />
                        </c:if>
                        <c:if test="${hcate.active}">
                            <img src="${staticUrl}/cp/img/icon-enable.png" />
                        </c:if>
                    </a>

                </td>
                <c:if test="${hcategory ==null}">
                    <td class="text-center">
                        <a href="javascript:void(0);" onclick="hotdeal.changeSpecial('${hcate.id}');" editSpecial="${hcate.id}">
                            <c:if test="${!hcate.special}">
                                <img src="${staticUrl}/cp/img/icon-disable.png" />
                            </c:if>
                            <c:if test="${hcate.special}">
                                <img src="${staticUrl}/cp/img/icon-enable.png" />
                            </c:if>
                        </a>
                    </td>
                </c:if>
                <td style="vertical-align: middle; text-align: center">
                    <button style="width: 100%" onclick="hotdeal.editCate('${hcate.id}')" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span>Sửa</button>
                    <c:if test="${hcategory ==null}">
                        <button style="width: 100%; margin-top: 10px;" onclick="hotdeal.editItemHome('${hcate.id}')" type="button" class="btn btn-warning"><span class="glyphicon glyphicon-edit"></span>Sản phẩm đại diện</button>
                    </c:if>
                    <button style="width: 100%; margin-top: 10px;" onclick="hotdeal.editItem('${hcate.id}')" type="button" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span>Sửa sản phẩm</button>
                    <button style="width: 100%; margin-top: 10px;" onclick="hotdeal.removeCate('${hcate.id}')" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xoá</button>
                </td>
            </tr>   
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">

    </div>
</div>