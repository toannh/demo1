package vn.chodientu.controller.cp;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.SellerHistory;
import vn.chodientu.entity.input.SellerHistorySearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.SellerHistoryService;

@Controller("cpSellerLog")
@RequestMapping("/cp/sellerlog")
public class SellerLogController extends BaseCp {

    @Autowired
    private SellerHistoryService sellerHistoryService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        SellerHistorySearch sellerHistorySearch = new SellerHistorySearch();
        if (session.getAttribute("sellerHistorySearch") != null && page != 0) {
            sellerHistorySearch = (SellerHistorySearch) session.getAttribute("sellerHistorySearch");
        } else {
            session.setAttribute("sellerHistorySearch", sellerHistorySearch);
        }
        DataPage<SellerHistory> datapage = null;
        if (sellerHistorySearch.getSellerId() != null && !sellerHistorySearch.getSellerId().equals("") && page != 0) {
            sellerHistorySearch.setPageIndex(page - 1);
            sellerHistorySearch.setPageSize(200);
            datapage = sellerHistoryService.search(sellerHistorySearch);
        }

        map.put("page", datapage);
        map.put("sellerHistorySearch", sellerHistorySearch);
        map.put("clientScript", "itemwarning.initSellerHistory();");
        return "cp.sellerlog";
    }

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    public String listSearch(ModelMap model,
            HttpSession session,
            @ModelAttribute SellerHistorySearch sellerHistorySearch) {
        session.setAttribute("sellerHistorySearch", sellerHistorySearch);
        sellerHistorySearch.setPageIndex(0);
        sellerHistorySearch.setPageSize(200);
        DataPage<SellerHistory> datapage = null;
        if (sellerHistorySearch.getSellerId() != null && !sellerHistorySearch.getSellerId().equals("")) {
            datapage = sellerHistoryService.search(sellerHistorySearch);
        }
        model.put("page", datapage);
        model.put("sellerHistorySearch", sellerHistorySearch);
        model.put("clientScript", "itemwarning.initSellerHistory();");
        return "cp.sellerlog";
    }

}
