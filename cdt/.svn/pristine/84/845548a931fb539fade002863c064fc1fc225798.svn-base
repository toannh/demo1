<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<!DOCTYPE html>
<html>
    <head>
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${staticUrl}/lib/favicon.png" type="image/x-icon" />
        <jwr:style src="/css/cp.css" />
    </head>   
    <body>
        <div class="navbar navbar-default" role="navigation" data-rel="navigation" >
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#cms-navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${baseUrl}/cp/index.html"></a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="cms-navbar">
                    <ul class="nav navbar-nav navbar-right">
                        <c:forEach var="group" items="${cpFunctionGroups}">
                            <c:set var="ischeck" value="false" /> 
                            <c:forEach var="role" items="${roles}">
                                <c:forEach var="function" items="${cpFunctions}">
                                    <c:if test="${function.type == 'ACTION' && function.groupName == group.name && role.refUri == function.refUri}">
                                        <c:set var="checked" value="true" /> 
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                            <c:if test="${checked || !viewer.cpAuthRequired}">
                                <li class="dropdown" >
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;">${group.name} <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <c:forEach var="function" items="${cpFunctions}">
                                            <c:forEach var="role" items="${roles}">
                                                <c:if test="${function.type == 'ACTION' && function.groupName == group.name && role.functionUri == function.uri}">
                                                    <li>
                                                        <a href="${baseUrl}${function.uri}.html">${function.name}</a>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                        <c:if test="${!viewer.cpAuthRequired}">
                                            <c:forEach var="function" items="${cpFunctions}">
                                                <c:if test="${function.type == 'ACTION' && function.groupName == group.name}">
                                                    <li>
                                                        <a href="${baseUrl}${function.uri}.html">${function.name}</a>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </ul>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /container -->
        </div><!-- /navbar -->
        <div class="tree-view">
            <div class="container">
                <div class="admin-right">
                    <div class="user-label">

                        ${viewer.administrator.email}
                        <b class="caret"></b>
                    </div>
                    <ul>
                        <!--<li><a href="#">Log hoạt động<span class="glyphicon glyphicon-list-alt"></span></a></li>-->
                        <!--<li><a href="#">Tùy chọn<span class="glyphicon glyphicon-cog"></span></a></li>-->
                        <li><a href="${baseUrl}" target="_blank" >Trang chủ <span class="glyphicon glyphicon-home"></span></a></li>
                        <li class="ar-line"></li>
                        <li><a onclick="auth.googleSignout();">Đăng xuất <span class="glyphicon glyphicon-off"></span></a></li>
                    </ul>
                </div><!-- /admin-right -->
                <ol class="breadcrumb" data-rel="breadcrumb" ></ol>
            </div><!-- /container -->
        </div><!-- /tree-view -->
        <div class="container">
            <div class="cms-content">
                <tiles:insertAttribute name="content" />
            </div>
        </div>
        <div class="footer">
            <div class="container">
                © 2013 - 2014 <a href="http://peacesoft.net/" target="_blnak" >Peacesoft Solutions Corporation.</a> All rights reserved
            </div><!-- /container -->
        </div><!-- footer -->
        <div class="top"></div>
        <script>var CKEDITOR_BASEPATH = '${staticUrl}/lib/ckeditor/';</script>
        <jwr:script src="/js/cp.js"/> 
        <script>
            var baseUrl = '${baseUrl}';
            var staticUrl = '${staticUrl}';
            ${clientScript}
        </script>
        <script src="https://apis.google.com/js/client:plusone.js" type="text/javascript"></script>
    </body>
</html>