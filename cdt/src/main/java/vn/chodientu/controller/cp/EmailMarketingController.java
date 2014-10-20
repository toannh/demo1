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
import vn.chodientu.entity.db.SellerEmailMarketing;
import vn.chodientu.entity.input.SellerMarketingSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.SellerMarketingService;

@Controller("cpEmailMarketing")
@RequestMapping("/cp/emailmarketing")
public class EmailMarketingController extends BaseCp {

    @Autowired
    private SellerMarketingService sellerMarketingService;
    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        SellerMarketingSearch sellerEmailSearch = new SellerMarketingSearch();
        if (session.getAttribute("sellerEmailSearch") != null && page != 0) {
            sellerEmailSearch = (SellerMarketingSearch) session.getAttribute("sellerEmailSearch");
        } else {
            session.setAttribute("sellerEmailSearch", sellerEmailSearch);
        }
        sellerEmailSearch.setPageSize(50);
        sellerEmailSearch.setPageIndex(page - 1);
        DataPage<SellerEmailMarketing> dataPage = sellerMarketingService.searchEmailMarketing(sellerEmailSearch, null);
        map.put("page", dataPage);
        map.put("sellerEmailSearch", sellerEmailSearch);
        map.put("clientScript", "sellerMarketing.init();");
        return "cp.emailmarketing";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(@ModelAttribute("sellerEmailSearch") SellerMarketingSearch sellerEmailSearch,
            HttpSession session, ModelMap map) {
        sellerEmailSearch.setPageSize(50);
        sellerEmailSearch.setPageIndex(0);
        session.setAttribute("sellerEmailSearch", sellerEmailSearch);
        DataPage<SellerEmailMarketing> dataPage = sellerMarketingService.searchEmailMarketing(sellerEmailSearch, null);
        map.put("sellerEmailSearch", sellerEmailSearch);
        map.put("page", dataPage);
        map.put("clientScript", "sellerMarketing.init();");
        return "cp.emailmarketing";
    }

}
