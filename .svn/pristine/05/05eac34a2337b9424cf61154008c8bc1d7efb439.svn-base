package vn.chodientu.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.TopUpClient;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.TopUp;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.TopUpService;

/**
 *
 * @author TheHoa
 */
@Controller("serviceTopup")
@RequestMapping("/topup")
public class TopupController extends BaseRest {

    @Autowired
    private TopUpService topUpService;

    @RequestMapping(value = "/buycardtelco", method = RequestMethod.POST)
    @ResponseBody
    public Response buyCardTelco(@RequestParam TopUpClient.Amount amount, @RequestParam TopUpClient.Service service) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này");
        }
        try {
            TopUp buyCardTelco = topUpService.buyCardTelco(viewer.getUser().getId(), amount, service);
            return new Response(true, "Thành công!", buyCardTelco);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/buytelco", method = RequestMethod.POST)
    @ResponseBody
    public Response buyTelco(@RequestParam(value = "phone", defaultValue = "") String phone, @RequestParam TopUpClient.Amount amount) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này");
        }
        try {
            TopUp topupTelco = topUpService.topupTelco(phone, viewer.getUser().getId(), amount);
            return new Response(true, "Thành công!", topupTelco);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

}
