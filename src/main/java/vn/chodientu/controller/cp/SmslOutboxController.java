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
import vn.chodientu.entity.db.SmsOutbox;
import vn.chodientu.entity.input.SmsOutboxSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.SmsService;

@Controller("cpSmsOutbox")
@RequestMapping("/cp/smsoutbox")
public class SmslOutboxController extends BaseCp {

    @Autowired
    private SmsService smsService;
    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map,
            HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {

        SmsOutboxSearch smsOutboxSearch = new SmsOutboxSearch();
        if (session.getAttribute("smsOutboxSearch") != null && page != 0) {
            smsOutboxSearch = (SmsOutboxSearch) session.getAttribute("smsOutboxSearch");
        } else {
            session.setAttribute("smsOutboxSearch", smsOutboxSearch);
        }
        if (page > 0) {
            smsOutboxSearch.setPageIndex(page - 1);
        } else {
            smsOutboxSearch.setPageIndex(0);
        }
        smsOutboxSearch.setPageSize(100);
        DataPage<SmsOutbox> dataPage = smsService.search(smsOutboxSearch);
        map.put("smsOutboxSearch", smsOutboxSearch);
        map.put("page", dataPage);
        map.put("clientScript", "smsOutbox.init();");
        return "cp.smsOutbox";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(
            @ModelAttribute("smsOutboxSearch") SmsOutboxSearch smsOutboxSearch,
            HttpSession session, ModelMap map) {
        smsOutboxSearch.setPageSize(100);
        smsOutboxSearch.setPageIndex(0);
        session.setAttribute("smsOutboxSearch", smsOutboxSearch);
        DataPage<SmsOutbox> dataPage = smsService.search(smsOutboxSearch);
        map.put("smsOutboxSearch", smsOutboxSearch);
        map.put("page", dataPage);
        map.put("clientScript", "smsOutbox.init();");
        return "cp.smsOutbox";
    }

}
