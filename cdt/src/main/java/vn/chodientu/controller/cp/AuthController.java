package vn.chodientu.controller.cp;

import javax.servlet.http.HttpServletRequest;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Permission;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.controller.BaseWeb;
import vn.chodientu.entity.db.Administrator;
import vn.chodientu.service.AdministratorService;

@Controller("cpAuth")
@RequestMapping("/cp/auth")
public class AuthController extends BaseWeb {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private SocialAuthConfig socialAuthConfig;

    @RequestMapping("/signin")
    public String signin() throws Exception {
//        SocialAuthManager socialAuthManager = new SocialAuthManager();
//        socialAuthManager.setSocialAuthConfig(socialAuthConfig);
//        return "redirect:" + socialAuthManager.getAuthenticationUrl("googleplus", baseUrl + "/cp/auth/callback.html", Permission.AUTHENTICATE_ONLY);
        return "redirect:" + baseUrl + "/cp/auth/callback.html";
    }

    @RequestMapping("/signout")
    public String signout() {
        viewer.setAdministrator(null);
        return "redirect:/cp/auth/signin.html";
    }

    @RequestMapping("/callback")
    public String auth(HttpServletRequest request, @RequestParam(value = "error", required = false) String error) {
        if (error == null) {
            try {
                SocialAuthManager socialAuthManager = new SocialAuthManager();
                socialAuthManager.setSocialAuthConfig(socialAuthConfig);
                AuthProvider provider = socialAuthManager.connect(SocialAuthUtil.getRequestParametersMap(request));
                Administrator administrator = administratorService.auth(provider.getUserProfile().getEmail());
                if (administrator == null || !administrator.isActive()) {
                    return "cp.auth";
                }
                viewer.setAdministrator(administrator);
                for (String id : socialAuthManager.getConnectedProvidersIds()) {
                    socialAuthManager.disconnectProvider(id);
                }
//                return "redirect:/cp/index.html";
                return "cp.auth";
            } catch (Exception ex) {
                return "cp.auth";
            }
        } else {
            return "cp.auth";
        }
    }
}
