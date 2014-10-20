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
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.input.SCHistorySearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.SCHistoryService;

@Controller("cpSCHistory")
@RequestMapping("/cp/schistory")
public class SCHistoryController extends BaseCp {

    @Autowired
    private SCHistoryService historyService;

    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map,
            HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {
        SCHistorySearch scHistorySearch = new SCHistorySearch();
        if (session.getAttribute("scHistorySearch") != null && page != 0) {
            scHistorySearch = (SCHistorySearch) session.getAttribute("scHistorySearch");
        } else {
            session.setAttribute("scHistorySearch", scHistorySearch);
        }
        if (page > 0) {
            scHistorySearch.setPageIndex(page - 1);
        } else {
            scHistorySearch.setPageIndex(0);
        }
        scHistorySearch.setPageSize(100);
        DataPage<Cash> dataPage = historyService.search(scHistorySearch);
        
        map.put("scHistoryData", dataPage);
        map.put("scHistorySearch", scHistorySearch);
        map.put("clientScript", "schistory.init();");
        return "cp.sc.history";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(ModelMap model,
            HttpSession session,
            @ModelAttribute SCHistorySearch scHistorySearch) {
        session.setAttribute("scHistorySearch", scHistorySearch);
        scHistorySearch.setPageIndex(0);
        scHistorySearch.setPageSize(100);
        DataPage<Cash> dataPage = historyService.search(scHistorySearch);

        model.put("scHistoryData", dataPage);
        model.put("scHistorySearch", scHistorySearch);
        model.put("clientScript", "schistory.init();");
        return "cp.sc.history";
    }

}
