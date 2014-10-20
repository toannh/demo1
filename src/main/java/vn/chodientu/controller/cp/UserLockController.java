package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.UserLock;
import vn.chodientu.entity.input.UserLockSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.UserLockService;
import vn.chodientu.service.UserService;

@Controller("cpUserLock")
@RequestMapping("/cp/userlock")
public class UserLockController extends BaseCp {

    @Autowired
    private UserLockService userLockService;
    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) {
        UserLockSearch userLockSearch = new UserLockSearch();
        if (session.getAttribute("userLockSearch") != null && page != 0) {
            userLockSearch = (UserLockSearch) session.getAttribute("userLockSearch");
        } else {
            session.setAttribute("userLockSearch", userLockSearch);
        }

        userLockSearch.setPageIndex(page - 1);
        userLockSearch.setPageSize(100);

        DataPage<UserLock> dataPage = userLockService.search(userLockSearch);

        model.put("dataPage", dataPage);
        model.put("clientScript", "userlock.init();");
        model.put("userLockSearch", userLockSearch);
        return "cp.userlock";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(ModelMap model, HttpSession session, @ModelAttribute UserLockSearch userLockSearch) {
        session.setAttribute("userLockSearch", userLockSearch);
        userLockSearch.setPageIndex(0);
        userLockSearch.setPageSize(100);

        DataPage<UserLock> dataPage = userLockService.search(userLockSearch);
        model.put("dataPage", dataPage);
        model.put("clientScript", "userlock.init();");
        model.put("userLockSearch", userLockSearch);
        return "cp.userlock";
    }
}
