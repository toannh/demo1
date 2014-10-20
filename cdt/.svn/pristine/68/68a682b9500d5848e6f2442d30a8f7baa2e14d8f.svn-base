<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
             <i class="fa fa-list"></i>
            Danh sách banner
        </a>
    </li>
</ul>  
<div class="func-container">        
    <div class="cms-line"></div>
    <div class="clearfix"></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${bannerPage.dataCount} </strong> kết quả <strong>${bannerPage.pageCount}</strong> trang.
        </h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${bannerPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${bannerPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive">
        <tbody>
            <tr style="font-size: 12px;">
                <th class="text-center">Id</th>
                <th class="text-center" style="width: 200px;">Ảnh</th>
                <th class="text-center" >Vị trí</th>
                <th class="text-center">Danh mục</th>
                <th class="text-center">Trạng thái</th>
                <th class="text-center" style="width: 150px;"><button onclick="advbanner.add();" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> Thêm mới</button></th>                
            </tr>
            <c:forEach var="banner" items="${bannerPage.data}">
                <tr style="font-size: 12px;" for="${banner.id}">
                    <td>${banner.id}</td>                                   
                    <td>
                        <c:if test="${banner.banner!=null && banner.banner!=''}">
                            <img src="${banner.banner}" width="200" />
                        </c:if>
                    </td>                                       
                    <td>
                        <c:if test="${banner.position=='BROWSE_TOP'}">Top banner danh mục</c:if>
                        <c:if test="${banner.position=='BROWSE_CONTENT'}">Banner danh mục</c:if>
                        <c:if test="${banner.position=='BACKEND_USER'}">Banner backend user</c:if>
                        </td>                                       
                        <td>${banner.categoryName}</td> 
                    <td class="text-center">
                        <a href="javascript:void(0);" onclick="advbanner.changeStatus('${banner.id}');" editStatus="${banner.id}">
                            <c:if test="${!banner.active}">
                                <img src="${staticUrl}/cp/img/icon-disable.png" />
                            </c:if>
                            <c:if test="${banner.active}">
                                <img src="${staticUrl}/cp/img/icon-enable.png" />
                            </c:if>
                        </a>

                    </td>
                    <td style="vertical-align: middle; width: 140px" class="btn-control text-center">                        
                        <div class="form-group">
                            <button onclick="advbanner.edit('${banner.id}');" class="btn btn-success" style="width: 130px;"><span class="glyphicon glyphicon-edit"></span> Sửa</button>
                            <button onclick="advbanner.del('${banner.id}');" class="btn btn-danger" style="width: 130px;margin-top: 10px"><span class="glyphicon glyphicon-trash"></span> Xóa</button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${bannerPage.dataCount <= 0}">
                <tr>
                    <td class="text-danger text-center" colspan="7">Hiện tại chưa có banner nào</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <div style="margin-top: 10px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${bannerPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${bannerPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix" style="margin-bottom: 10px;"></div>
    </div>
</div>


