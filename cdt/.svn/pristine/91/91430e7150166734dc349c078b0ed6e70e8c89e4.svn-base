package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.input.UserSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CityService;
import vn.chodientu.service.DistrictService;
import vn.chodientu.service.UserService;

@Controller("cpUser")
@RequestMapping("/cp/user")
public class UserController extends BaseCp {

    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;
    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map,
            HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {

        UserSearch userSearch = new UserSearch();
        if (session.getAttribute("userSearch") != null && page != 0) {
            userSearch = (UserSearch) session.getAttribute("userSearch");
        } else {
            session.setAttribute("userSearch", userSearch);
        }
        if (page > 0) {
            userSearch.setPageIndex(page - 1);
        } else {
            userSearch.setPageIndex(0);
        }
        userSearch.setPageSize(100);
        DataPage<User> dataPage = userService.search(userSearch);
        map.put("userSearch", userSearch);
        map.put("page", dataPage);
        List<City> citys = cityService.list();
        map.put("citys", citys);
        map.put("clientScript", "var citys = " + gson.toJson(citys) + ";var districts = " + gson.toJson(districtService.list()) + "; user.init();");
        return "cp.user";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(
            @ModelAttribute("userSearch") UserSearch userSearch,
            HttpSession session, ModelMap map) {
        userSearch.setPageSize(100);
        userSearch.setPageIndex(0);
        session.setAttribute("userSearch", userSearch);
        DataPage<User> dataPage = userService.search(userSearch);
        map.put("userSearch", userSearch);
        map.put("page", dataPage);
        List<City> citys = cityService.list();
        map.put("citys", citys);
        map.put("clientScript", "var citys = " + gson.toJson(citys) + ";var districts = " + gson.toJson(districtService.list()) + "; user.init();");
        return "cp.user";
    }

}
