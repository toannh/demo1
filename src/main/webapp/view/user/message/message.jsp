<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="text" uri="/WEB-INF/taglib/text.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12 slidebar-right">
    <ol class="breadcrumb">
        <li><span class="glyphicon glyphicon-home"></span>  
            <a href="${baseUrl}"> Trang chủ</a>
        </li>
        <li><a href="${baseUrl}/user/profile.html">${viewer.user.username}</a></li>
        <li class="active">Tài khoản</li>
    </ol>
    <h1 class="title-pages">Thông báo & tin nhắn</h1>
    <div class="tabs-content-user">
        <ul class="tab-title-content">
            <li class="${(tab=='inbox'||tab=='unread' || tab =='') ?'active':''}"><a href="${baseUrl}/user/quan-ly-thu.html?tab=inbox">Hòm thư <span id="inboxCount"></span></a></li>
            <li class="${tab=='sent'?'active':''}"><a href="${baseUrl}/user/quan-ly-thu.html?tab=sent">Thư đã gửi <span id="outboxCount"></span></a></li>
            <li><a href="${baseUrl}/user/soan-thu.html">Soạn thư</a></li>                            
        </ul>
        <div class="tabs-content-block">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <c:if test="${tab!='sent'}">
                        <div class="sub-tab-content">
                            <a href="${baseUrl}/user/quan-ly-thu.html?tab=inbox" class="${tab=='inbox'?'active':''}">Toàn bộ</a>  |  <a class="${tab=='unread'?'active':''}" href="${baseUrl}/user/quan-ly-thu.html?tab=unread">Chưa đọc</a>
                        </div>    
                    </c:if>    
                    <jsp:useBean id="date" class="java.util.Date" />
                    <div class="mesenger-table table-responsive">  
                        <c:if test="${fn:length(messages.data)>0}"> 
                            <table class="table" width="100%">
                                <tr class="warning">
                                    <th width="5%" align="center" valign="top">
                                <div class="text-center"><input type="checkbox" name="checkall"></div>
                                </th>
                                <th width="" colspan="2"><button class="btn btn-danger btn-sm" onclick="message.deleteMess()"><span class="glyphicon glyphicon-trash"></span> Xoá</button> <button type="button" class="btn btn-default btn-sm" onclick="message.makeNoRead();">Đánh dấu chưa đọc</button></th>
                                <th width="15%" valign="middle">&nbsp;</th>
                                </tr>


                                <c:forEach items="${messages.data}" var="mess">
                                    <tr class="${!mess.read?'no-read':''} ${mess.id}">
                                        <td valign="top" align="center"><div class="text-center"><input type="checkbox" for="checkall" value="${mess.id}"></div></td>
                                        <td>
                                            <div class="text-left" onclick="message.repMessage('${mess.id}');">${mess.fromName}</div>
                                        </td>
                                        <td><div class="text-left" onclick="message.repMessage('${mess.id}');">Re: ${mess.subject}</div></td>
                                        <jsp:setProperty name="date" property="time" value="${mess.createTime}" /> 
                                        <td><div class="text-center" onclick="message.repMessage('${mess.id}');"><fmt:formatDate type="date" pattern="HH:mm dd/MM/yyyy" value="${date}"></fmt:formatDate></div></td>

                                        </tr>
                                </c:forEach>

                            </table>
                        </c:if>
                        <c:if test="${fn:length(messages.data)<=0}">
                            <div class="cdt-message bg-danger text-center">Không tìm thấy bản ghi nào!</div>
                        </c:if>
                        
                    </div>
                    <hr>
                    <div class="page-ouner clearfix">
                        <span class="pull-left go-pages">
                            <label class="control-label pull-left">Tới trang: </label>
                            <input type="text" class="form-control pull-left" id="nextPage" value="${messages.pageIndex+1}">
                            <a href="javascript:;" onclick="message.search('', $('#nextPage').val());" class="btn btn-default pull-left">
                                <span class="glyphicon glyphicon-log-in"></span>
                            </a>
                        </span>
                        <ul class="pagination pull-right">
                            <c:if test="${messages.pageIndex > 3}"><li><a href="javascript:message.search('${find != ''?find:''}', 1);"  style="cursor: pointer" ><<</a></li></c:if>
                            <c:if test="${messages.pageIndex > 2}"><li><a href="javascript:message.search('${find != ''?find:''}', ${messages.pageIndex});" style="cursor: pointer"  ><</a></li></c:if>
                            <c:if test="${messages.pageIndex > 3}"><li><a href="javascript:;" style="cursor: pointer">...</a></li></c:if>
                            <c:if test="${messages.pageIndex >= 3}"><li><a href="javascript:message.search('${find != ''?find:''}', ${messages.pageIndex - 2});" style="cursor: pointer" >${messages.pageIndex-2}</a></li></c:if>
                            <c:if test="${messages.pageIndex >= 2}"><li><a href="javascript:message.search('${find != ''?find:''}', ${messages.pageIndex -1});" style="cursor: pointer" >${messages.pageIndex-1}</a></li></c:if>
                            <c:if test="${messages.pageIndex >= 1}"><li><a href="javascript:message.search('${find != ''?find:''}', 1);" onclick="" style="cursor: pointer" >${messages.pageIndex}</a></li></c:if>
                            <li class="active" ><a class="btn btn-primary" >${messages.pageIndex + 1}</a>
                            <c:if test="${messages.pageCount - messages.pageIndex > 1}"><li><a href="javascript:message.search('${find != ''?find:''}', ${messages.pageIndex + 2});"  style="cursor: pointer" >${messages.pageIndex+2}</a></li></c:if>
                            <c:if test="${messages.pageCount - messages.pageIndex > 2}"><li><a href="javascript:message.search('${find != ''?find:''}', ${messages.pageIndex +3});"  style="cursor: pointer"  >${messages.pageIndex+3}</a></li></c:if>
                            <c:if test="${messages.pageCount - messages.pageIndex > 3}"><li><a href="javascript:;" style="cursor: pointer">...</a></c:if>
                            <c:if test="${messages.pageCount - messages.pageIndex > 2}"><li><a href="javascript:message.search('${find != ''?find:''}', ${messages.pageIndex +2});"  style="cursor: pointer" >></a></li></c:if>
                            <c:if test="${messages.pageCount - messages.pageIndex > 2}"><li><a href="javascript:message.search('${find != ''?find:''}', ${messages.pageCount});"  style="cursor: pointer" >>></a></li></c:if>
                        </ul>
                    </div>
                    <div class="mgt-25" id="messRep">
                    </div>
                </div>                            
            </div>                     
        </div>   
    </div>
</div>