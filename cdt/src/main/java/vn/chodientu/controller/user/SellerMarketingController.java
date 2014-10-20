package vn.chodientu.controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.SellerEmailMarketing;
import vn.chodientu.entity.db.SellerSmsMarketing;
import vn.chodientu.entity.input.SellerMarketingSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.SellerMarketingService;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Controller("sellerMarketing")
@RequestMapping("/user")
public class SellerMarketingController extends BaseUser {

    @Autowired
    private SellerMarketingService sellerMarketingService;

    @RequestMapping(value = "/email-marketing", method = RequestMethod.GET)
    public String emailMarketing(ModelMap map, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/email-marketing.html";
        }
        SellerMarketingSearch sellerMarketingSearch = new SellerMarketingSearch();
        sellerMarketingSearch.setPageIndex(page - 1);
        sellerMarketingSearch.setPageSize(15);
        DataPage<SellerEmailMarketing> dataPage = sellerMarketingService.searchEmailMarketing(sellerMarketingSearch, viewer.getUser());
        List<SellerEmailMarketing> emails = dataPage.getData();
        map.put("dataPage", dataPage);
        map.put("emails", emails);
        map.put("sellerSearch", sellerMarketingSearch);
        map.put("clientScript", "email.initEmail();");
        return "user.emailmarketing";
    }

    @RequestMapping(value = "/create-email-marketing", method = RequestMethod.GET)
    public String createEmailMarketing(ModelMap map, @RequestParam(value = "edit", defaultValue = "") String id, HttpServletRequest request) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/create-email-marketing.html";
        }
        if (request.getParameter("edit") != null) {
            SellerEmailMarketing email = sellerMarketingService.getEmail(id);
            map.put("email", email);
        }
        map.put("clientScript", "email.initEmail();");
        return "user.createemailmarketing";
    }

    @RequestMapping(value = "/sms-marketing", method = RequestMethod.GET)
    public String smsMarketing(ModelMap map, @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/sms-marketing.html";
        }

        SellerMarketingSearch smsMarketing = new SellerMarketingSearch();
        if (page > 0) {
            smsMarketing.setPageIndex(page - 1);
        } else {
            smsMarketing.setPageIndex(0);
        }
        smsMarketing.setPageSize(10);
        DataPage<SellerSmsMarketing> pageSms = sellerMarketingService.searchSmsMarketing(smsMarketing, viewer.getUser());

        map.put("pageSms", pageSms);
        return "user.smsmarketing";
    }

    @RequestMapping(value = "/create-sms-marketing", method = RequestMethod.GET)
    public String createSmsMarketing(ModelMap map, @RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/create-sms-marketing.html";
        }

        if (id != null && !id.equals("")) {
            SellerSmsMarketing sms = sellerMarketingService.getSms(id);
            map.put("sms", sms);
        }
        map.put("clientScript", "email.initSms(); var smsMarketing=1;");
        return "user.createsmsmarketing";
    }
}
