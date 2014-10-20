<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách danh mục trang chủ
        </a>
    </li>
</ul>
<div class="func-container"> 
    <table class="table table-striped table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center" style="vertical-align: middle">ID / Vị trí</th>
            <th class="text-center" style="vertical-align: middle">Danh mục</th>
            <th class="text-center" style="vertical-align: middle">Alias Banner</th>
            <th class="text-center" style="vertical-align: middle">Topics</th>
            <th class="text-center" style="vertical-align: middle">Thương hiệu</th>
            <th style="vertical-align: middle; width: 160px" class="btn-control text-center"><button class="btn btn-primary" onclick="categoryalias.add();"><span class="glyphicon glyphicon-plus"></span> Thêm mới</button></th>
        </tr>
        <c:forEach var="alias" items="${aliasData}">
            <tr>
                <td style="vertical-align: top">${alias.id}<br/>
                    <input type="text" value="${alias.position}" onchange="categoryalias.changePosition('${alias.id}', this.value)" class="form-control" placeholder="Vị trí sắp xếp" style="min-width: 30px; height: 30px;">
                </td>
                <td style="vertical-align: top">
                    <c:forEach items="${categories}" var="category">
                        <c:if test="${category.id == alias.categoryId}">
                            <p><strong>Danh mục:</strong> ${category.name}</p>
                        </c:if>
                    </c:forEach>
                    <p><strong>Title: </strong>${alias.title}</p>
                    <p><strong>Subtitle: </strong>${alias.subTitle}</p>
                    <strong>Trạng thái: </strong>
                    <c:if test="${alias.active}">
                        <label class="label label-success">Đang hoạt động</label>
                    </c:if>
                    <c:if test="${!alias.active}">
                        <label class="label label-danger">Ẩn</label>
                    </c:if>
                </td>    
                <td style="vertical-align: top">
                    <img src="${alias.image}" alt="${alias.bannerUrl}" width="100"/><br/>
                    <img src="${alias.image1}" alt="${alias.bannerUrl1}" width="100"/><br/>
                    <img src="${alias.image2}" alt="${alias.bannerUrl2}" width="100"/><br/>
                    <img src="${alias.image3}" alt="${alias.bannerUrl3}" width="100"/>
                </td>
                <td style="vertical-align: top">
                    <c:forEach items="${alias.topics}" var="topic">
                        <div class="clearfix">
                            <img src="${topic.image}" alt="${topic.title}" class="img-circle" style="float: left;margin: 5px;" width="50"/>    
                            <p class="cl-right">${topic.title}<br />
                                <a href="${topic.url}" title="${topic.url}" target="_blank">${fn:substring(topic.url, 0, 80)}</a>
                            </p>
                        </div>
                    </c:forEach>
                </td>
                <td style="vertical-align: top">
                    <c:forEach items="${alias.manufacturerIds}" var="munuId">
                        <c:forEach items="${mamufacturers}" var="manuf">
                            <c:if test="${munuId == manuf.id}">
                                <img src="${manuf.imageUrl}" alt="${manuf.name}"/><br/>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </td>
                <td>
                    <button class="btn btn-default" style="width: 160px; margin-bottom: 5px;" onclick="categoryalias.edit('${alias.id}');"><span class="glyphicon glyphicon-edit"></span> Sửa thông tin</button>
                    <button class="btn btn-default" style="width: 160px; margin-bottom: 5px;" onclick="categoryalias.editTopics('${alias.id}');"><span class="glyphicon glyphicon-list-alt"></span> Sửa topic</button>
                    <button class="btn btn-default" style="width: 160px;" onclick="categoryalias.editManufacturers('${alias.id}');"><span class="glyphicon glyphicon-leaf"></span> Sửa thương hiệu</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>