package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.input.NewsCategorySearch;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.NewsCategory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.input.CashSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CashService;
import vn.chodientu.service.UserService;

@Controller("cpCash")
@RequestMapping("/cp/cash")
public class CashController extends BaseCp {

    @Autowired
    private CashService cashService;
    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map,
            HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {
        CashSearch cashSearch = new CashSearch();
        if (session.getAttribute("cpCashSearch") != null && page != 0) {
            cashSearch = (CashSearch) session.getAttribute("cpCashSearch");
        } else {
            session.setAttribute("cpCashSearch", cashSearch);
        }
        if (page > 0) {
            cashSearch.setPageIndex(page - 1);
        } else {
            cashSearch.setPageIndex(0);
        }
        cashSearch.setPageSize(100);
        DataPage<Cash> dataPage = cashService.searchCash(cashSearch);
        List<String> userId = new ArrayList<>();
        for (Cash sh : dataPage.getData()) {
            userId.add(sh.getUserId());
        }
        List<User> userByIds = userService.getUserByIds(userId);

        map.put("cashSearchs", dataPage);
        map.put("users", userByIds);
        map.put("clientScript", "cash.init();");
        map.put("cpCashSearch", cashSearch);
        return "cp.cash";
    }

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    public String search(ModelMap model,
            HttpSession session,
            @ModelAttribute CashSearch cashSearch) {
        session.setAttribute("cpCashSearch", cashSearch);
        cashSearch.setPageIndex(0);
        cashSearch.setPageSize(100);
        DataPage<Cash> dataPage = cashService.searchCash(cashSearch);
        List<String> userId = new ArrayList<>();
        for (Cash sh : dataPage.getData()) {
            userId.add(sh.getUserId());
        }
        List<User> userByIds = userService.getUserByIds(userId);
        model.put("cashSearchs", dataPage);
        model.put("users", userByIds);
        model.put("clientScript", "cash.init();");
        model.put("cpCashSearch", cashSearch);
        return "cp.cash";
    }

}
