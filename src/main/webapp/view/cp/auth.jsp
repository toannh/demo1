<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<!DOCTYPE html>
<html>
    <head>
        <title><tiles:insertAttribute name="title" /></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${staticUrl}/lib/favicon.png" type="image/x-icon" />
        <jwr:style src="/css/cp.css" />
    </head>   
    <body class="bg-login" >
        <div class="container">
            <div class="dashboard">
                <div class="box-login">
                    <div class="login-circle"><span class="icon-user"></span><span class="shadow2"></span></div>
                    <div class="shadow1"></div>
                    <div class="bl-title">ChoDienTu.Vn</div>
                    <div class="gmail-login">
                        <a style="cursor: pointer" onclick="auth.googleSingin();"><span class="icon-gmail"></span>Đăng nhập bằng Gmail</a>
                    </div>
                    <div class="shadow"></div>
                </div><!-- /box-login -->
            </div><!-- /dashboard -->
        </div>
        <jwr:script src="/js/cp.js"/> 
        <script>
            var baseUrl = '${baseUrl}';
            var staticUrl = '${staticUrl}';
            ${clientScript}
        </script>
        <script src="https://apis.google.com/js/client:plusone.js" type="text/javascript"></script>
    </body>
</html>
