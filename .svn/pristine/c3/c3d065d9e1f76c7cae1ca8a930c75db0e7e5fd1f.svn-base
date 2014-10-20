package vn.chodientu.controller.cp;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Lading;
import vn.chodientu.entity.input.LadingSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.LadingService;

@Controller("cpLading")
@RequestMapping("/cp/lading")
public class LadingController extends BaseCp {

    @Autowired
    private LadingService ladingService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session,
            @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        LadingSearch ladingSearch = new LadingSearch();
        if (session.getAttribute("ladingSearch") != null && page != 0) {
            ladingSearch = (LadingSearch) session.getAttribute("ladingSearch");
        } else {
            session.setAttribute("ladingSearch", ladingSearch);
        }
        ladingSearch.setPageIndex(page - 1);
        ladingSearch.setPageSize(20);
        DataPage<Lading> dataPage = ladingService.search(ladingSearch);
        
        map.put("dataPage", dataPage);
        map.put("allPrice", ladingService.sumPrice(ladingSearch));
        map.put("ladingSearch", ladingSearch);
        map.put("clientScript", "lading.init();"); 
        return "cp.lading";

    }
    @RequestMapping(method = RequestMethod.POST)
    public String lists(ModelMap map, HttpSession session,@ModelAttribute LadingSearch ladingSearch) throws Exception {
        session.setAttribute("ladingSearch", ladingSearch);
        ladingSearch.setPageIndex(0);
        ladingSearch.setPageSize(20);
        DataPage<Lading> dataPage = ladingService.search(ladingSearch);
        map.put("dataPage", dataPage);
        map.put("ladingSearch", ladingSearch);
        map.put("allPrice", ladingService.sumPrice(ladingSearch));
        map.put("clientScript", "lading.init();");
        return "cp.lading";

    }

}
