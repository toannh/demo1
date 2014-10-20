package vn.chodientu.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class TopUpController extends BaseUser {

    @RequestMapping("/topup-by-cash.html")
    public String topUp(ModelMap map) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/topup-by-cash.html";
        }

        map.put("clientScript", "topup.init();");
        return "user.topup";
    }

}
