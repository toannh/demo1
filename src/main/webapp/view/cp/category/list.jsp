<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách danh mục
        </a>
    </li>
</ul>
<div class="func-container"> 
    <div style="margin-top: 10px">
        <ul class="nav nav-tabs nav-collapse">
            <li <c:if test="${category.id == null || category.id == ''}">class="active"</c:if> >
                <a href="${baseUrl}/cp/category.html">Danh mục gốc</a>
            </li>
            <c:forEach var="ancestor" items="${ancestors}">
                <li <c:if test="${category.id == ancestor.id}">class="active"</c:if>>
                    <a href="?id=${ancestor.id}">${ancestor.name}</a>
                </li>
            </c:forEach>
        </ul>
    </div>   
    <div class="col-sm-9 col-sm-offset-3" style="margin:15px -15px;">
        <button class="btn btn-primary" onclick="category.add('${category.id}');" style="padding: 8px 16px;"><span class="glyphicon glyphicon-plus"></span> Thêm mới danh mục</button>
        <button class="btn btn-primary" onclick="category.exportExcel();" style="padding: 8px 16px;"><span class="glyphicon glyphicon-plus"></span> Xuất excel danh mục có tên > 19 kí tự</button>
        <button class="btn btn-primary" onclick="category.exportExcelCategoryByLeafDisplay();" style="padding: 8px 16px;"><span class="glyphicon glyphicon-list"></span> Xuất excel tất cả danh mục lá</button>
    </div>
    <div style="margin-top: 80px;" >
        <h5 class ="alert alert-success" style="padding: 10px;">Có tổng số <span style="color: tomato; font-weight: bolder">${listCategories.size()}</span> kết quả.</h5>
        <div class="clearfix"></div>
    </div>
    <div style="margin-top: 10px;">
        <h5 class="pull-left alert alert-success" style="padding: 10px;" >
            <span style="color: tomato; font-weight: bolder">${text:numberFormat(countInElasticseach)} / ${text:numberFormat(realCategoryCount)}</span> danh mục đã được index.
            <button type="button"  class="btn btn-warning" onclick="category.index();"><span class="glyphicon glyphicon-plus"></span> Index </button>
            <button type="button"  class="btn btn-danger" onclick="category.unindex();"><span class="glyphicon glyphicon-remove"></span> Xóa Index </button>
        </h5>
        <div class="clearfix"></div>
    </div>            
    <table  class="table table-striped table-bordered table-responsive">
        <tbody>
            <tr>
                <th class="text-center">ID</th>
                <th class="text-center">Tên danh mục</th>
                <th class="text-center">Thứ tự</th>                             
                <th class="text-center">Cấp</th>                             
                <th class="text-center" >Trạng thái</th>
                <th class="text-center" >Thao tác</th>
            </tr>
            <c:forEach var="cates" items="${listCategories}">
                <tr>
                    <td class="text-center" style="vertical-align: middle" >${cates.id}</td>
                    <td class="text-center" style="vertical-align: middle" >
                        <c:if test="${!cates.leaf}">
                            <a href="?id=${cates.id}">${cates.name}</a>
                        </c:if>
                        <c:if test="${cates.leaf}">
                            ${cates.name}
                        </c:if>
                    </td>
                    <td class="text-center" style="vertical-align: middle" >${cates.position}</td>                    
                    <td class="text-center" style="vertical-align: middle" >${cates.level}</td>                    
                    <td class="text-center" style="vertical-align: middle" >
                        <c:if test="${cates.active}"><span class="label label-success">Đang hoạt động</span></c:if>
                        <c:if test="${!cates.active}"><span class="label label-danger">Tạm dừng</span></c:if>                    
                        <c:if test="${cates.leaf==true}"><span class="label label-info">Cấp lá</span></c:if> 
                        </td>
                        <td class="btn-control text-center" style="vertical-align: middle">
                            <div class="btn-group">
                                <button onclick="category.edit('${cates.id}');" class="btn btn-info"><span class="glyphicon glyphicon-edit"></span> Sửa </button>  
                            <button onclick="category.editProperty('${cates.id}');" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span> Thuộc tính</button>
                            <button onclick="category.manufacturer('${cates.id}');" class="btn btn-warning"><span class="glyphicon glyphicon-edit"></span> Map thương hiệu</button>
                            <button onclick="category.del('${cates.id}');" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Xóa</button>                        
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>


