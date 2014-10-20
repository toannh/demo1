
<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="keywords" content="${keywords}" />
        <meta name="description" content="${description}" />
        <link rel="shortcut icon" href="${staticUrl}/lib/favicon.png" />
        <title>${title} | ChợĐiệnTử eBay Việt Nam</title>
        <meta property="og:title" content="${title}" />
        <meta property="og:site_name" content="ChợĐiệnTử eBay Việt Nam"/>
        <meta property="og:url" content="${baseUrl}${requestScope['javax.servlet.forward.request_uri']}"/>
        <meta property="og:image" content="${ogImage}"/>
        <meta property="og:description"  content="${description}" />
        <link rel="canonical" href="http://chodientu.vn${canonical}" />
        <jwr:style src="/css/shop.css" />
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,700,600italic,700italic,800,800italic&subset=latin,vietnamese' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <div class="container-global">
            <tiles:insertAttribute name="navbar"/>
            <tiles:insertAttribute name="header"/>
            <tiles:insertAttribute name="menu"/>

            <div class="container">
                <div class="row">
                    <tiles:insertAttribute name="content"/>
                    <tiles:insertAttribute name="sidebar"/>
                </div>
            </div>
            <tiles:insertAttribute name="footer"/>
        </div>
        <a title="Lên đầu trang" id="gotop" href="#"><span class="fa fa-chevron-up"></span></a> 

        <jwr:script src="/js/shop.js"/> 
        <script>(function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.0";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>
        <script type="text/javascript" src="https://apis.google.com/js/plusone.js"></script>

        <script type="text/javascript">
            /* <![CDATA[ */
            var google_conversion_id = 965672748;
            var google_custom_params = window.google_tag_params;
            var google_remarketing_only = true;
            /* ]]> */
        </script>
        <script type="text/javascript" src="//www.googleadservices.com/pagead/conversion.js"></script>
        <script>(function () {
                var _fbq = window._fbq || (window._fbq = []);
                if (!_fbq.loaded) {
                    var fbds = document.createElement('script');
                    fbds.async = true;
                    fbds.src = '//connect.facebook.net/en_US/fbds.js';
                    var s = document.getElementsByTagName('script')[0];
                    s.parentNode.insertBefore(fbds, s);
                    _fbq.loaded = true;
                }
                _fbq.push(['addPixelId', '252708024928683']);
            })();
            window._fbq = window._fbq || [];
            window._fbq.push(['track', 'PixelInitialized', {}]);
        </script>

        <script>(function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.0";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));
        </script>

        <script type="text/javascript">
            var __lc = {};
            __lc.license = 1915652;

            (function () {
                var lc = document.createElement('script');
                lc.type = 'text/javascript';
                lc.async = true;
                lc.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'cdn.livechatinc.com/tracking.js';
                var s = document.getElementsByTagName('script')[0];
                s.parentNode.insertBefore(lc, s);
            })();
        </script>
        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

            ga('create', 'UA-50752946-1', 'auto');
            ga('require', 'displayfeatures');
            ga('send', 'pageview');
        </script>
        <script>
            var baseUrl = '${baseUrl}';
            var staticUrl = '${staticUrl}';
            ${clientScript}
        </script>

        <c:if test="${viewer.user != null}">
            <script src="//js.pusher.com/2.2/pusher.min.js" type="text/javascript"></script>
            <script type="text/javascript">
            realTime.init('${viewer.user.id}');
            </script>
        </c:if>
    </body>
</html>
