package vn.chodientu.controller.cp;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.SmsInbox;
import vn.chodientu.entity.input.SmsInboxSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.SmsService;

@Controller("cpSmsInbox")
@RequestMapping("/cp/smsinbox")
public class SmsInboxController extends BaseCp {

    @Autowired
    private SmsService smsService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        SmsInboxSearch search = new SmsInboxSearch();
        if (session.getAttribute("search") != null && page != 0) {
            search = (SmsInboxSearch) session.getAttribute("search");
        } else {
            session.setAttribute("search", search);
        }
        search.setPageIndex(page - 1);
        search.setPageSize(50);
        DataPage<SmsInbox> pageSms = smsService.search(search);

        map.put("page", pageSms);
        map.put("search", search);
        map.put("clientScript", "smsInbox.init();");
        return "cp.smsinbox";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String list(ModelMap map,
            HttpSession session,
            @ModelAttribute SmsInboxSearch search) {

        session.setAttribute("search", search);
        search.setPageIndex(0);
        search.setPageSize(50);
        DataPage<SmsInbox> pageSms = smsService.search(search);

        map.put("page", pageSms);
        map.put("clientScript", "smsInbox.init();");
        map.put("search", search);
        return "cp.smsinbox";
    }

}
