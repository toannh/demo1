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
import vn.chodientu.entity.db.Administrator;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.db.CpFunction;
import vn.chodientu.entity.input.AdministratorSearch;
import vn.chodientu.service.AdministratorService;
import vn.chodientu.service.CpFunctionService;

@Controller("Administrator")
@RequestMapping(value = "/cp/administrator")
public class AdministratorController extends BaseCp {

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private CpFunctionService cpFunctionService;
    @Autowired
    private Gson gson;

    /**
     *
     * @param map
     * @param session
     * @param page
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map,
            HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        AdministratorSearch adminSearch = new AdministratorSearch();

        if (session.getAttribute("adminSearch") != null && page != 0) {
            adminSearch = (AdministratorSearch) session.getAttribute("adminSearch");
        } else {
            session.setAttribute("adminSearch", adminSearch);
        }
        if (page > 0) {
            adminSearch.setPageIndex(page - 1);
        } else {
            adminSearch.setPageIndex(0);
        }
        adminSearch.setPageSize(100);

        DataPage<Administrator> administratorPage = administratorService.search(adminSearch);
        List<CpFunction> allFunctions = cpFunctionService.getFunctionsNotBeSkipped();
        map.put("adminSearch", adminSearch);
        map.put("administratorPage", administratorPage);
        map.put("clientScript", "functions = " + gson.toJson(allFunctions) + "; administrator.init(); ");

        return "cp.administrator";
    }

    /**
     *
     * @param adminSearch
     * @param session
     * @param map
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String search(
            @ModelAttribute("adminSearch") AdministratorSearch adminSearch,
            HttpSession session,
            ModelMap map) {
        adminSearch.setPageSize(100);
        adminSearch.setPageIndex(0);

        session.setAttribute("adminSearch", adminSearch);

        DataPage<Administrator> administratorPage = administratorService.search(adminSearch);
        List<CpFunction> allFunctions = cpFunctionService.getAll();
        map.put("adminSearch", adminSearch);
        map.put("administratorPage", administratorPage);
        map.put("clientScript", "functions = " + gson.toJson(allFunctions) + ";administrator.init();");

        return "cp.administrator";
    }

}
