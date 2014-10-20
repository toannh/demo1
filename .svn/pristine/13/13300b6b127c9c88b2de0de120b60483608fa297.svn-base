<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container">
    <div class="dashboard">
        <div class="row">
            <c:forEach var="group" items="${cpFunctionGroups}">
                <c:set var="ischeck" value="false" /> 
                <c:forEach var="role" items="${roles}">
                    <c:forEach var="function" items="${cpFunctions}">
                        <c:if test="${function.type == 'ACTION' && function.groupName == group.name && role.functionUri == function.uri}">
                            <c:set var="ischeck" value="true" /> 
                        </c:if>
                    </c:forEach>
                </c:forEach>
                <c:if test="${ischeck == true}">
                    <div class="col-sm-3">
                        <div class="panel panel-default">
                            <div class="panel-heading">${group.name}</div>
                            <div class="panel-body">
                                <ul >
                                    <c:forEach var="function" items="${cpFunctions}">
                                        <c:forEach var="role" items="${roles}">
                                            <c:if test="${function.type == 'ACTION' && function.groupName == group.name && role.functionUri == function.uri}">
                                                <li>
                                                    <a href="${baseUrl}${function.uri}.html">${function.name}</a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>                   
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>