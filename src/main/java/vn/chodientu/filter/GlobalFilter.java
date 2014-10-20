/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.filter;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vn.chodientu.util.XSSRequestWrapper;

/**
 *
 * @author ThienPhu
 */
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getRequestURL().toString().startsWith("http://www")) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(req.getRequestURL().toString().replace("www.", ""));
            return;
        }
        String uri = req.getRequestURI();

        Pattern pt = Pattern.compile("/[a-z0-9-_]+", Pattern.CASE_INSENSITIVE);
        if (pt.matcher(uri).matches()) {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(uri + "/");
            return;
        }

        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
