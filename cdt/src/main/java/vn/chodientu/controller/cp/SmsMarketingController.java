package vn.chodientu.controller.cp;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.SellerSmsMarketing;
import vn.chodientu.entity.input.SellerMarketingSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.SellerMarketingService;

@Controller("cpSmsMarketing")
@RequestMapping("/cp/smsmarketing")
public class SmsMarketingController extends BaseCp {

    @Autowired
    private SellerMarketingService sellerMarketingService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        SellerMarketingSearch sellerSmsSearch = new SellerMarketingSearch();
        if (session.getAttribute("sellerSmsSearch") != null && page != 0) {
            sellerSmsSearch = (SellerMarketingSearch) session.getAttribute("sellerSmsSearch");
        } else {
            session.setAttribute("sellerSmsSearch", sellerSmsSearch);
        }
        sellerSmsSearch.setPageSize(50);
        sellerSmsSearch.setPageIndex(page - 1);
        DataPage<SellerSmsMarketing> dataPage = sellerMarketingService.searchSmsMarketing(sellerSmsSearch, null);
        map.put("page", dataPage);
        map.put("sellerSmsSearch", sellerSmsSearch);
        map.put("clientScript", "sellerMarketing.init();");
        return "cp.smsmarketing";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(@ModelAttribute("sellerSmsSearch") SellerMarketingSearch sellerSmsSearch,
            HttpSession session, ModelMap map) {
        sellerSmsSearch.setPageSize(50);
        sellerSmsSearch.setPageIndex(0);
        session.setAttribute("sellerSmsSearch", sellerSmsSearch);
        DataPage<SellerSmsMarketing> dataPage = sellerMarketingService.searchSmsMarketing(sellerSmsSearch, null);
        map.put("sellerSmsSearch", sellerSmsSearch);
        map.put("page", dataPage);
        map.put("clientScript", "sellerMarketing.init();");
        return "cp.smsmarketing";
    }

}
