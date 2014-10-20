package vn.chodientu.controller.cp;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.ItemHistory;
import vn.chodientu.entity.input.ItemHistorySearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.ItemHistoryService;

@Controller("cpItemHistory")
@RequestMapping("/cp/itemhistory")
public class ItemHistoryController extends BaseCp {

    @Autowired
    private ItemHistoryService historyService;

    @RequestMapping
    public String list(ModelMap map, HttpSession session,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "itemId", defaultValue = "") String itemId) {
        ItemHistorySearch search = new ItemHistorySearch();
        DataPage<ItemHistory> datapage = null;
        if (itemId != null && !itemId.equals("")) {
            search.setItemId(itemId);
            search.setPageIndex(0);
            search.setPageSize(100);
            datapage = historyService.search(search);
        }

        map.put("page", datapage);
        map.put("search", search);
        map.put("clientScript", "itemwarning.inithistory();");
        return "cp.itemhistory";
    }

}
