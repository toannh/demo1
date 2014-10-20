<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<jwr:style src="/css/cpcategory.css" />
<style>
    .home-item .hoi-view .hoi-btn {
        border: 1px solid #1b212b;
        border-radius: 2px;
        text-transform: none;
    }
</style>
<%  String url = request.getParameter("id");%>
 <ul class="nav nav-tabs" role="tablist">
   <li class="active">
       <a href="${baseUrl}/cp/featuredcategory.html">Featured categories</a>
   </li>
   <li><a href="${baseUrl}/cp/featuredcategory.html?cate=true">Danh sách danh mục</a></li>
</ul>
<div class="func-container">   
    <button onclick="featuredcategory.add();" type="button" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Thêm mới danh mục</button>
    <c:if test="${cateSubTrue==1}">
        <button onclick="featuredcategory.addsub('${param.id}');" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-plus"></span> Thêm mới danh mục cấp con</button>
    </c:if>
    <div style="margin-top: 10px">
        <div class="alert alert-info">
            <c:forEach items="${listFC}" var="fc" varStatus="stt">
                        <c:if test="${fc.categoryId == param.id}">
                            <a href="${baseUrl}/cp/featuredcategory.html?id=${fc.categoryId}"><button type="button" class="btn btn-sm btn-primary">${fc.categoryName}</button></a>

                        </c:if>
                        <c:if test="${fc.categoryId != param.id}">
                            <a href="${baseUrl}/cp/featuredcategory.html?id=${fc.categoryId}"><button type="button" class="btn btn-sm btn-default">${fc.categoryName}</button></a>

                        </c:if>
                        <c:if test="${!sst.isLast()}">|</c:if>
               
     
            </c:forEach>
        </div>
        <c:if test="${not empty categorySubs}">
            <div class="alert alert-danger col-sm-12">
                <div class="col-sm-4">
                    <select class="form-control" name="template">
                        <option value="${template}">${template} - Layout đang chọn</option>
                    </select>
                </div>
                <div class="col-sm-4">
                    <select class="form-control" name="subId"> <option value="0">Chọn Tab</option>
                        <c:forEach items="${categorySubs}" var="fcsub">
                            <option value="${fcsub.categorySubId}">${fcsub.categorySubName}</option>
                        </c:forEach>
                    </select> 
                </div>
                <div class="col-sm-4">
                    <button style="width: 100%" onclick="featuredcategory.saveTemplate('${param.id}');" type="button" class="btn btn-default"><span class="glyphicon glyphicon-list"></span> Chọn</button>
                </div>

            </div>
        </c:if>
    </div>


    <br/><br/>
    <div style="margin-top: 10px; margin-bottom:10px">
        <br/><br/>
        <div id="content_box">

        </div>
    </div>
</div>