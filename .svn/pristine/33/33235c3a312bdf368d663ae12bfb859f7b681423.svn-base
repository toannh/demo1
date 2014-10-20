package vn.chodientu.controller.cp;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.ItemWarning;
import vn.chodientu.entity.input.ItemWarningSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.ItemWarningService;

@Controller("cpItemWarning")
@RequestMapping("/cp/itemwarning")
public class ItemWarningController extends BaseCp {

    @Autowired
    private ItemWarningService itemWarningService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        ItemWarningSearch search = new ItemWarningSearch();
        if (session.getAttribute("search") != null && page != 0) {
            search = (ItemWarningSearch) session.getAttribute("search");
        } else {
            session.setAttribute("search", search);
        }
        search.setPageIndex(page - 1);
        search.setPageSize(50);
        DataPage<ItemWarning> dataPage = itemWarningService.search(search);
        map.put("dataPage", dataPage);
        map.put("search", search);
        map.put("clientScript", "itemwarning.initwarning();");
        return "cp.itemwarning";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(ModelMap map, HttpSession session, @ModelAttribute ItemWarningSearch search) {
        session.setAttribute("search", search);
        search.setPageIndex(0);
        search.setPageSize(50);
        DataPage<ItemWarning> dataPage = itemWarningService.search(search);
        map.put("dataPage", dataPage);

        map.put("dataPage", dataPage);
        map.put("search", search);
        map.put("clientScript", "itemwarning.initwarning();");
        return "cp.itemwarning";
    }

}
