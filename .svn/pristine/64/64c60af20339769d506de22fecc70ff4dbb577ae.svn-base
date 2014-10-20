<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
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
        <jwr:style src="/css/market.css" />
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script type="text/javascript">
            var googletag = googletag || {};
            googletag.cmd = googletag.cmd || [];
            (function () {
                var gads = document.createElement("script");
                gads.async = true;
                gads.type = "text/javascript";
                var useSSL = "https:" == document.location.protocol;
                gads.src = (useSSL ? "https:" : "http:") + "//www.googletagservices.com/tag/js/gpt.js";
                var node = document.getElementsByTagName("script")[0];
                node.parentNode.insertBefore(gads, node);
            })();
            googletag.cmd.push(function () {
                googletag.pubads().setTargeting("page", "${adPage}");
            });

            googletag.cmd.push(function () {
                googletag.defineSlot('/21504722/200x200', [200, 200], 'div-gpt-ad-1383805719385-0').addService(googletag.pubads());
                googletag.pubads().enableSingleRequest();
                googletag.enableServices();
            });
            googletag.cmd.push(function () {
                googletag.defineSlot('/21504722/970x90', [970, 90], 'div-gpt-ad-1409797103321-0').addService(googletag.pubads());
                googletag.pubads().enableSingleRequest();
                googletag.enableServices();
            });
            googletag.cmd.push(function () {
                googletag.defineSlot('/21504722/990x90', [990, 90], 'div-gpt-ad-1409797788286-0').addService(googletag.pubads());
                googletag.pubads().enableSingleRequest();
                googletag.enableServices();
            });
        </script>
    </head>
    <body>
        <div id="fb-root"></div>
        <tiles:insertAttribute name="navigator"/>
        <tiles:insertAttribute name="header"/>
        <tiles:insertAttribute name="content"/>
        <tiles:insertAttribute name="footer"/>
        <div class="loading-fast"></div>

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
        <jwr:script src="/js/market.js"/> 

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
            var baseUrl = '${baseUrl}';
            var staticUrl = '${staticUrl}';
            ${clientScript};
        </script>
        
        <c:if test="${viewer.user != null}">
            <script src="//js.pusher.com/2.2/pusher.min.js" type="text/javascript"></script>
            <script type="text/javascript">
            realTime.init('${viewer.user.id}');
            </script>
        </c:if>

    </body>
</html>
