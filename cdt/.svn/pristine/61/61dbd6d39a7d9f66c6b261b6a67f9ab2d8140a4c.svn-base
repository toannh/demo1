package vn.chodientu.controller.cp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("cpIndex")
@RequestMapping("/cp")
public class IndexController extends BaseCp {

    @RequestMapping("/index")
    public String index(ModelMap map) {
        if (viewer.isCpAuthRequired() && viewer.getAdministrator() == null) {
            return "redirect:/cp/auth/signin.html";
        }
        map.put("clientScript", "layout.homeinit();");
        return "cp.index";
    }
}
