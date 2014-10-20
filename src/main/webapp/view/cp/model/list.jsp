
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="text" uri="http://chodientu.vn/text" %>

<ul class="nav nav-tabs" role="tablist">
    <li class="active"><a href="${baseUrl}/cp/model.html">Danh sách model</a></li>
    <li><a href="${baseUrl}/cp/addmodel.html">Danh sách model mới được tạo</a></li>
    <li><a href="${baseUrl}/cp/editmodel.html">Danh sách model yêu cầu sửa</a></li>
    <li><a href="${baseUrl}/cp/reviewmodel.html"><span class="glyphicon glyphicon-plus"></span>Duyệt model tạo</a></li>
    <li><a href="${baseUrl}/cp/reviewmodel.html?task=edit"><span class="glyphicon glyphicon-pencil"></span>Duyệt model sửa</a></li>
</ul>
<div class="func-container" >
    <form:form modelAttribute="modelSearch" cssClass="form-vertical" method="POST" role="form" style="margin-top: 20px;">
        <form:input path="categoryId" type="hidden"/>
        <div class="col-sm-4">
            <div class="form-group ">
                <form:input path="keyword" type="text" class="form-control" placeholder="Tên model"/>
            </div>
            <div class="form-group ">
                <form:select path="status" type="text" class="form-control">
                    <option value="0">-- Chọn trạng thái --</option>
                    <option value="1">Đang hoạt động"</option>
                    <option value="2">Đang khoá</option>
                    <option value="3">Đang chờ duyệt</option>
                </form:select>
            </div>
        </div>
        <div class="form-group col-md-4">
            <div class="form-group input-group">
                <form:input type="text" path="manufacturerIds" class="form-control" placeholder="Nhập mã thương hiệu" />
                <span class="input-group-btn">
                    <button class="btn btn-default" onclick="model.loadmf('manufacturerIds');"  type="button">Tìm</button>
                </span>
            </div>
            <div class="form-group ">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc model</button>
            </div>
        </div>
        <div class="form-group col-md-4" id="selectcategorys"></div>
    </form:form>
    <div class="cms-line"></div>
    <div class="clearfix"></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${text:numberFormat(dataPage.dataCount)} </strong> kết quả <strong>${text:numberFormat(dataPage.pageCount)}</strong> trang.
        </h5>
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center" style="vertical-align: middle">Mã</th>
            <th class="text-center" style="vertical-align: middle" >Model</th>
            <th class="text-center" style="vertical-align: middle" >Danh mục</th>
            <th class="text-center" style="vertical-align: middle" >Thương hiệu</th>
            <th class="text-center" style="vertical-align: middle;" >Hiển thị</th>
            <th class="text-center" style="width: 400px; vertical-align: middle;" >Chức năng</th>
        </tr>
        <c:forEach var="item" items="${dataPage.data}">
            <tr>
                <td class="text-center" style="vertical-align: middle">${item.id}</td>
                <td class="text-center" style="vertical-align: middle">${item.name}</td>
                <td class="text-center" style="vertical-align: middle">${item.categoryName}</td>
                <td class="text-center" style="vertical-align: middle">${item.manufacturerName}</td>
                <td class="text-center active_${item.id}" style="vertical-align: middle">
                    <i onclick="model.changeStatus('${item.id}');" class="glyphicon glyphicon-${(item.active)?'check':'unchecked'}"></i>
                </td>
                <td class="text-center" style="vertical-align: middle">
                    <div class="btn-group">
                        <button type="button" class="btn btn-warning" onclick="model.showImages('${item.id}');" >
                            <span class="glyphicon glyphicon-list"></span> Hình ảnh</button>
                        <button type="button" class="btn btn-info"  onclick="reviewmodel.showPropertiesEditFalse('${item.id}');"  >
                            <span class="glyphicon glyphicon-random"></span> Thuộc tính</button>
                            <c:if test="${item.approved == true}">
                            <button type="button" class="btn btn-danger" onclick="model.requestEdit('${item.id}');" >
                                <span class="glyphicon glyphicon-remove"></span> Yêu cầu sửa</button>
                            </c:if>
                    </div>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${dataPage.dataCount == 0}">
            <tr>
                <td colspan="6" class="text-center text-danger">Không tìm thấy model nào</td>
            </tr>
        </c:if>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>