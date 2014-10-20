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
import vn.chodientu.entity.db.EmailOutbox;
import vn.chodientu.entity.input.EmailOutboxSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.EmailService;

@Controller("cpEmailOutbox")
@RequestMapping("/cp/emailoutbox")
public class EmailOutboxController extends BaseCp {

    @Autowired
    private EmailService emailOutboxService;
    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map,
            HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {

        EmailOutboxSearch emailOutboxSearch = new EmailOutboxSearch();
        if (session.getAttribute("userSearch") != null && page != 0) {
            emailOutboxSearch = (EmailOutboxSearch) session.getAttribute("emailOutboxSearch");
        } else {
            session.setAttribute("emailOutboxSearch", emailOutboxSearch);
        }
        if (page > 0) {
            emailOutboxSearch.setPageIndex(page - 1);
        } else {
            emailOutboxSearch.setPageIndex(0);
        }
        emailOutboxSearch.setPageSize(100);
        DataPage<EmailOutbox> dataPage = emailOutboxService.search(emailOutboxSearch);
        map.put("emailOutboxSearch", emailOutboxSearch);
        map.put("page", dataPage);
        map.put("clientScript", "emailOutbox.init();");
        return "cp.emailOutbox";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(
            @ModelAttribute("emailOutboxSearch") EmailOutboxSearch emailOutboxSearch,
            HttpSession session, ModelMap map) {
        emailOutboxSearch.setPageSize(100);
        emailOutboxSearch.setPageIndex(0);
        session.setAttribute("emailOutboxSearch", emailOutboxSearch);
        DataPage<EmailOutbox> dataPage = emailOutboxService.search(emailOutboxSearch);
        map.put("emailOutboxSearch", emailOutboxSearch);
        map.put("page", dataPage);
        map.put("clientScript", "emailOutbox.init();");
        return "cp.emailOutbox";
    }

}
