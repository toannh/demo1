/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.service.ShopService;

/**
 *
 * @author phugt
 */
public class ShopInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ShopService shopService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String baseUrl;
        switch (request.getRequestURI()) {
            case "/":
                baseUrl = request.getRequestURL().substring(0, request.getRequestURL().length() - 1);
                break;
            case "":
                baseUrl = request.getRequestURL().toString();
                break;
            default:
                baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
                break;
        }
        if (baseUrl.equals("http://chodientu.vn")) {
            return true;
        }
        Shop shop = shopService.getByUrl(baseUrl);
        String uri = request.getRequestURI();
        if (shop != null && request.getRequestURL().toString().startsWith(baseUrl)) {
            if (uri.equals("/")) {
                response.sendRedirect(baseUrl + "/" + shop.getAlias() + "/");
                return false;
            } else if (!uri.startsWith("/" + shop.getAlias() + "/")) {
                response.sendRedirect(request.getRequestURL().toString().replaceAll(baseUrl, "http://chodientu.vn"));
                return false;
            }
        }
        return true;
    }
}
