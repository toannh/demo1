<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-list"></i>
            Danh sách marketing người bán
        </a>
    </li>
</ul>
<div class="func-container"> 
    <form:form modelAttribute="sellerEmailSearch" cssClass="form-vertical" method="POST" role="form" style="margin-top: 20px;">        
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="id" type="text" class="form-control" placeholder="Mã email marketing"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="fromName" type="text" class="form-control" placeholder="Tên người gửi"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="fromEmail" type="text" class="form-control" placeholder="Địa chỉ Email người gửi"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="sendTimeForm" type="hidden" class="form-control timeFrom" placeholder="Ngày yêu cầu gửi từ"/>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <form:input path="sendTimeTo" type="hidden" class="form-control timeTo" placeholder="Ngày yêu cầu gửi đến"/>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <form:select  path="run" class="form-control">
                    <form:option value="0">-- Trạng thái --</form:option>
                    <form:option value="1">Đang chạy</form:option>
                    <form:option value="2">Chưa chạy</form:option>
                </form:select>
            </div>
        </div>
        <div class="col-sm-4">  
            <div class="form-group">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> Lọc thông tin</button>
            </div>
        </div>
    </form:form>
    <div class="cms-line"></div>
    <div class="clearfix"></div>
    <div style="margin-top: 10px">
        <h5 class="pull-left" style="padding: 10px; width: 33%;" >
            Tìm thấy <strong>${page.dataCount} </strong> kết quả <strong>${page.pageCount}</strong> trang.
        </h5>            
        <div class="btn-toolbar pull-right" style="padding: 15px 0px;">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${page.pageIndex}"/>
                <jsp:param name="pageCount" value="${page.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
    <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
        <tr>
            <th class="text-center">Thông tin gửi</th>
            <th class="text-center">Danh sách email nhận</th>
            <th class="text-center">Tiêu đề</th>
            <th class="text-center" style="width: 150px;" >Ngày yêu cầu gửi</th>
            <th class="text-center">Đã chạy</th>
            <th class="text-center">Chạy xong</th>
            <th class="text-center" style="width: 200px;" > Chức năng </th>
        </tr>        
        <jsp:useBean id="date" class="java.util.Date"></jsp:useBean>
        <c:forEach var="item" items="${page.data}">  
            <tr class="${item.id} ${item.active?'success':'danger'}">
                <td class="text-left" style="vertical-align: middle" >
                    ${item.id}
                    <p>${item.fromName}</p>
                    <p>${item.fromEmail}</p>
                </td>
                <td class="text-left">
                    <p style="max-width: 500px; max-height: 100px; overflow: auto" >
                        <c:forEach items="${item.email}" var="email" varStatus="loop">
                            <c:if test="${loop.index==0}">${email}</c:if> 
                            <c:if test="${loop.index!=0}">,${email}</c:if> 
                        </c:forEach>
                    </p>
                </td>
                <td class="hide" for="content">${item.content}</td>
                <td class="hide" for="template">${item.template}</td>
                <td class="hide" for="sendTime">${item.sendTime}</td>
                <td class="hide" for="sellerId">${item.sellerId}</td>
                <td class="text-center" for="name" style="vertical-align: middle" >
                    <p style="max-width: 200px; overflow: auto" >
                        ${item.name}
                    </p>
                </td>
                <td class="text-center" style="vertical-align: middle" >
                    <jsp:setProperty name="date" property="time" value="${item.sendTime}" />
                    <fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy"  value="${date}"></fmt:formatDate>
                    </td>
                    <td class="text-center" style="vertical-align: middle" for="active" >
                    ${!item.run?'<i class="glyphicon glyphicon-unchecked"></i>':'<i class="glyphicon glyphicon-check"></i>'}
                </td>
                <td class="text-center" style="vertical-align: middle" for="active" >
                    ${!item.done?'<i class="glyphicon glyphicon-unchecked"></i>':'<i class="glyphicon glyphicon-check"></i>'}
                </td>
                <td class="text-center"  style="vertical-align: middle" >
                    <div class="btn-group">
                        <button title="Chi tiết" type="button" class="btn btn-info" onclick="sellerMarketing.previewEmail('${item.id}');" >
                            <span class="glyphicon glyphicon-export"></span>
                        </button>
                        <c:if test="${item.active==false}">
                            <button type="button" class="btn btn-success ${item.done?'disabled':''}"  onclick="sellerMarketing.activeEmail('${item.id}', 'active');" >
                                <c:if test="${item.done==true && item.run == true}">
                                    <span class="glyphicon glyphicon-refresh"></span> Gửi thử
                                </c:if>
                                <c:if test="${item.done!=true && item.run != true}">
                                    <span class="glyphicon glyphicon-refresh"></span> Duyệt
                                </c:if>
                                    
                            </button>
                        </c:if>
                        <c:if test="${item.active==true}">
                            <button type="button" class="btn btn-danger ${item.done?'disabled':''}"  onclick="sellerMarketing.activeEmail('${item.id}', 'inactive');" >
                                <span class="glyphicon glyphicon-refresh"></span> Khóa
                            </button>
                        </c:if>
                    </div>
                </td>
            </tr>      
        </c:forEach>
    </table>
    <div style="margin-top: 10px; margin-bottom:10px">
        <div class="btn-toolbar pull-right">
            <jsp:include page="/view/cp/widget/paging.jsp">
                <jsp:param name="pageIndex" value="${page.pageIndex}"/>
                <jsp:param name="pageCount" value="${page.pageCount}"/>
            </jsp:include>
        </div>
        <div class="clearfix"></div>
    </div>
</div>