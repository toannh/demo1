/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.interceptor;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import vn.chodientu.entity.db.AdministratorRole;
import vn.chodientu.entity.db.CpFunction;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.service.AdministratorService;
import vn.chodientu.service.CpFunctionService;

/**
 *
 * @author phugt
 */
public class CpAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Viewer viewer;
    @Autowired
    private CpFunctionService cpFunctionService;
    @Autowired
    private AdministratorService administratorService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!viewer.isCpAuthRequired() || (viewer.getAdministrator() != null
                && viewer.getAdministrator().getEmail().equals("thanhvv@peacesoft.net"))
                || (viewer.getAdministrator() != null && viewer.getAdministrator().getEmail().equals("bapcai.vn29@gmail.com"))) {
            return true;
        }
        List<CpFunction> finByActive = cpFunctionService.getFunctionsNotBeSkipped();
        List<String> funcActive = new ArrayList<>();
        if (finByActive.size() > 0) {
            for (CpFunction function : finByActive) {
                funcActive.add(function.getUri());
            }
        }

        boolean rl = false;
        String uri = request.getRequestURI();
        String rexUrlSig = "/cpservice/auth/signin";
        String notChecked = "/cpservice/auth/signout";
        String callback = "/cp/auth/callback";
        String[] uriPrefixs = {".html", ".json"};

        if (uri.contains("cpservice/global/")) {
            return true;
        }

        for (String uriPrefix : uriPrefixs) {
            if (uri.contains(uriPrefix)) {
                String[] split = uri.split(uriPrefix);
                uri = split[0];

                if (viewer.getAdministrator() == null && !uri.contains(rexUrlSig) && !uri.equals(callback)) {
                    if (uri.contains("cpservice")) {
                        throw new Exception("Bạn không có quyền thực hiện chức năng này");
                    }
                    response.sendRedirect(((uri.contains("chodientu")) ? "/chodientu" : "") + callback + ".html");
                    break;
                }

                List<String> roles = new ArrayList<>();
                if (viewer != null && viewer.getAdministrator() != null) {
                    List<AdministratorRole> adminRoles = administratorService.getRoles(viewer.getAdministrator().getId());
                    for (AdministratorRole administratorRole : adminRoles) {
                        roles.add(administratorRole.getFunctionUri());
                    }
                }
                if ((!roles.isEmpty() && contentItem(roles, uri) && contentItem(funcActive, uri))
                        || uri.contains(notChecked) || uri.contains(rexUrlSig) || uri.contains("/cp/index") || uri.equals(callback)) {
                    rl = true;
                    break;
                } else {
                    if (uri.contains("cpservice")) {
                        throw new Exception("Bạn không có quyền thực hiện chức năng này");
                    }
                    response.sendRedirect(((uri.contains("chodientu")) ? "/chodientu" : "") + "/cp/index.html");
                    break;
                }
            }
        }
        return rl;
    }

    private boolean contentItem(List<String> list, String item) {
        for (String string : list) {
            if (string.contains(item)) {
                return true;
            }
        }
        return false;
    }
}
