<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="nav nav-tabs" role="tablist">
    <li class="active">
        <a>
            <i class="fa fa-tags"></i>
            Định nghĩa quyền
        </a>
    </li>
</ul>
<div class="func-container" > 
    <c:if test="${fn:length(groups) > 0}">
        <c:forEach var="group" items="${groups}">
            <h3>Nhóm quyền: ${group.name} (<a class="btn-link" href="javascript:;" onclick="func.editgroup('${group.name}')">Sửa</a> | <a class="btn-link" href="javascript:;" onclick="func.removegroup('${group.name}')">Xóa</a>)</h3>
            <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
                <tr>
                    <th>Chức năng</th>
                    <th>Hành động</th>
                    <th style="width: 150px">&nbsp;</th>
                </tr>
                <c:forEach var="function" items="${functions}">
                    <c:if test="${function.type == 'ACTION' && function.groupName == group.name}">
                        <tr>
                            <td>
                                <p>${function.name} <span class="text-muted">(${function.uri})</span></p>
                            </td>
                            <td>
                                <c:forEach var="func" items="${functions}">
                                    <c:if test="${func.type == 'SERVICE' && func.refUri == function.refUri && function.groupName == group.name}">
                                        <p>${func.name} <span class="text-muted">(${func.uri})</span></p>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td style="text-align: center">
                                <div class="btn-group">
                                    <button class="btn btn-primary" onclick="func.edit('${function.uri}')">Sửa</button>
                                    <button class="btn btn-danger" onclick="func.remove('${function.uri}')">Xóa</button>
                                </div>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </c:forEach>
    </c:if>

    <c:if test="${fn:length(unmappedFunctions) > 0}">
        <h3>Chức năng chưa được map</h3>
        <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
            <tr>
                <th>Chức năng</th>
                <th>Hành động</th>
                <th style="width: 150px">&nbsp;</th>
            </tr>
            <c:forEach var="function" items="${unmappedFunctions}">
                <c:if test="${function.type == 'ACTION'}">
                    <tr>
                        <td>
                            <p>${function.uri}</p>
                        </td>
                        <td>
                            <c:forEach var="func" items="${unmappedFunctions}">
                                <c:if test="${func.type == 'SERVICE' && func.refUri == function.refUri}">
                                    <p>${func.uri}</p>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td style="text-align: center">
                            <button class="btn btn-primary" onclick="func.map('${function.uri}')">Map chức năng</button>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            <c:if test="${fn:length(invalidFunctions) > 0}">     
                <tr>
                    <td>
                        <p>Hành động không có chức năng</p>
                    </td>
                    <td colspan="2">
                        <c:forEach var="func" items="${invalidFunctions}">
                            <p>${func.uri}</p>
                        </c:forEach>
                    </td>
                </tr>
            </c:if>
        </table>
    </c:if>
    <c:if test="${fn:length(deletedFunctions) > 0}">
        <h3>Chức năng đã xóa</h3>
        <table class="table table-striped table-bordered table-responsive" style="margin-top: 10px">
            <tr>
                <th>Hành động</th>
                <th style="width: 150px">&nbsp;</th>
            </tr>
            <c:forEach var="function" items="${deletedFunctions}">
                <tr>
                    <td>
                        <p>${function.uri}</p>
                    </td>
                    <td style="text-align: center">
                        <button class="btn btn-danger" onclick="func.remove('${function.uri}')">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
