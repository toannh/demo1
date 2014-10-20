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
import vn.chodientu.entity.db.TopUp;
import vn.chodientu.entity.input.TopUpSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.TopUpService;
import vn.chodientu.service.UserService;

@Controller("cpTopUp")
@RequestMapping("/cp/topup")
public class TopUpController extends BaseCp {

    @Autowired
    private TopUpService topUpService;
    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) {
        TopUpSearch topUpSearch = new TopUpSearch();
        if (session.getAttribute("topUpSearch") != null && page != 0) {
            topUpSearch = (TopUpSearch) session.getAttribute("topUpSearch");
        } else {
            session.setAttribute("topUpSearch", topUpSearch);
        }

        topUpSearch.setPageIndex(page - 1);
        topUpSearch.setPageSize(100);

        DataPage<TopUp> dataPage = topUpService.search(topUpSearch);

        List<String> listID = new ArrayList<>();
        for (TopUp tp : dataPage.getData()) {
            if (!listID.contains(tp.getUserId())) {
                listID.add(tp.getUserId());
            }
        }

        model.put("dataPage", dataPage);
        model.put("clientScript", "var userIds = " + gson.toJson(listID) + ";" + " topup.init();");
        model.put("topUpSearch", topUpSearch);
        return "cp.topup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(ModelMap model, HttpSession session, @ModelAttribute TopUpSearch topUpSearch) {
        session.setAttribute("topUpSearch", topUpSearch);
        topUpSearch.setPageIndex(0);
        topUpSearch.setPageSize(100);

        DataPage<TopUp> dataPage = topUpService.search(topUpSearch);

        List<String> listID = new ArrayList<>();
        for (TopUp tp : dataPage.getData()) {
            if (!listID.contains(tp.getUserId())) {
                listID.add(tp.getUserId());
            }
        }

        model.put("dataPage", dataPage);
        model.put("clientScript", "var userIds = " + gson.toJson(listID) + ";" + " topup.init();");
        model.put("topUpSearch", topUpSearch);
        return "cp.topup";
    }
}
