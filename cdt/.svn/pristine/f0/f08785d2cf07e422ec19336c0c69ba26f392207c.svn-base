package vn.chodientu.controller.user;

import com.google.gson.Gson;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.service.CashService;
import vn.chodientu.service.CityService;
import vn.chodientu.service.DistrictService;
import vn.chodientu.service.InvoiceService;

/**
 * @since May 20, 2014
 * @author PhuongDt
 */
@Controller
@RequestMapping("/user")
public class InvoiceController extends BaseUser {

    @Autowired
    private CashService cashService;
    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private Gson gson;
    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "/tao-hoa-don", method = RequestMethod.GET)
    public String list(ModelMap model, @RequestParam(value = "page", defaultValue = "1") int page, HttpSession session) {

        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/tao-hoa-don.html";
        }
        model.put("clientScript", "var citys = " + gson.toJson(cityService.list())
                + "; var districts = " + gson.toJson(districtService.list())
                + "; invoice.init();");
        model.put("orderIdInvoice", invoiceService.getIdCode());
        viewer.setInvoice(null);
        return "user.invoice";
    }

}
