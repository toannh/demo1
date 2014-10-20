<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib  prefix="url" uri="/WEB-INF/taglib/url.tld"%>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách từ khóa
        </a>
    </li>
</ul>
<div class="func-container">
    <form:form modelAttribute="keywordSearch" method="POST" role="form" style="margin-top: 20px;">
        <div class="col-sm-3">
            <div class="form-group">
                <form:input path="keyword" type="text" class="form-control" placeholder="Từ khóa"/>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <form:input path="url" type="text" class="form-control" placeholder="Link từ khóa"/>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="form-group">
                <form:select path="common" class="form-control">
                    <form:option value="0" label="Tất cả từ khóa"/>
                    <form:option value="1" label="Từ khóa phổ biến"/>
                    <form:option value="2" label="Từ khóa không phổ biến"/>
                </form:select>
            </div>
        </div>
        <div class="col-sm-3">
            <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc</button>
        </div>
    </form:form>
    <div class="cms-line"></div>
    <div class="clearfix"></div>
    <div style="margin-top: 10px" class="func-yellow">
        <h4>Thêm mới từ khóa</h4>
        <div class="col-sm-3">
            <div class="form-group">
                <input name="keyword" type="text" rel="check_keyword" class="form-control" onkeyup="footerkeyword.genUrl()" onkeypress="footerkeyword.genUrl()" onkeydown="footerkeyword.genUrl()" placeholder="Enter để thêm từ khóa"/>
            </div>
            <span class="text-danger" rel="check_keyword"></span>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <input name="url" type="text" rel="check_url" class="form-control" placeholder="Enter để thêm link"/>
            </div>
            <span class="text-danger" rel="check_url"></span>
        </div>
        <div class="col-sm-2">
            <div class="form-group checkbox">
                <label>
                    <input type="checkbox" checked="true" name="common"> Phổ biến
                </label>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
    <div class="clearfix"></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${dataPage.dataCount} </strong> kết quả <strong>${dataPage.pageCount}</strong> trang.
        </h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive tbl-heartbanner" style="margin-top: 10px" >
        <tr>
            <th style="vertical-align: middle; text-align: center; ">STT</th>
            <th style="vertical-align: middle; text-align: center; ">Từ khóa</th>            
            <th style="vertical-align: middle; text-align: center; ">Đường dẫn</th>
            <th style="vertical-align: middle; text-align: center; ">Vị trí</th>
            <th style="vertical-align: middle; text-align: center; ">Hiển thị</th>
            <th style="vertical-align: middle; text-align: center; ">Phổ biến</th>
            <th style="vertical-align: middle; text-align: center; "><a href="javascript:;" class="btn btn-success" onclick="footerkeyword.add()"><i class="glyphicon glyphicon-tag"></i> Thêm mới</a></th>
        </tr>
        <tbody data-rel="content">
            <c:forEach items="${dataPage.data}" var="footerkeyword" varStatus="stt">
                <tr rel="${footerkeyword.id}">
                    <td class="text-center">${stt.index+1}</td>
                    <td class="text-center">
                        <input name="keyword" id="keyword" type="text" value="${footerkeyword.keyword}" onchange="footerkeyword.changeKeyword('${footerkeyword.id}', this.value);" class="form-control" />
                    </td>
                    <td><input name="url" id="_url" type="text" value="${footerkeyword.url}" onchange="footerkeyword.changeUrl('${footerkeyword.id}', this.value)" class="form-control" /></td>
                    <td class="text-center">
                        <input name="position" id="_position" type="text" value="${footerkeyword.position}" onchange="footerkeyword.changePosition(${footerkeyword.id}, this.value);" class="form-control" style="width: 50px; height: 30px; text-align: center;">
                    </td>
                    <td class="text-center">
                        <a href="javascript:void(0);" onclick="footerkeyword.changeStatus('${footerkeyword.id}');" editStatus="${footerkeyword.id}">
                            <c:if test="${!footerkeyword.active}">
                                <img src="${staticUrl}/cp/img/icon-disable.png" />
                            </c:if>
                            <c:if test="${footerkeyword.active}">
                                <img src="${staticUrl}/cp/img/icon-enable.png" />
                            </c:if>
                        </a>
                    </td>
                    <td class="text-center">
                        <a href="javascript:void(0);" onclick="footerkeyword.changeCommon('${footerkeyword.id}');" editCommon="${footerkeyword.id}">
                            <c:if test="${!footerkeyword.common}">
                                <img src="${staticUrl}/cp/img/icon-disable.png" />
                            </c:if>
                            <c:if test="${footerkeyword.common}">
                                <img src="${staticUrl}/cp/img/icon-enable.png" />
                            </c:if>
                        </a>
                    </td>
                    <td style="vertical-align: middle;" class="text-center">
                        <div class="btn-group" id="615139734549">
                            <button id="reviews" onclick="footerkeyword.del('${footerkeyword.id}');" class="btn btn-danger" style="width: 80px;"><span class="glyphicon glyphicon-remove"></span>Xóa</button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>  
    </table>
    <div style="margin-top: 10px; margin-bottom:20px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${dataPage.pageIndex}"/>
                <jsp:param name="pageCount" value="${dataPage.pageCount}"/>
            </jsp:include>
        </div><div class="clearfix"></div>
    </div>
</div>