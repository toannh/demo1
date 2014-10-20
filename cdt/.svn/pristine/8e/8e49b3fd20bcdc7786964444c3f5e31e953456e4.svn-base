/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.AdvBanner;
import vn.chodientu.entity.enu.AdvBannerType;
import vn.chodientu.entity.form.AdvBannerForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.AdvBannerService;

/**
 *
 * @author Admin
 */
@Controller("cpAdvBanner")
@RequestMapping(value = "/cpservice/advbanner")
public class AdvBannerController extends BaseRest {

    @Autowired
    private AdvBannerService advBannerService;

    @RequestMapping(value = "/addbanner", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute AdvBannerForm advBannerForm) throws Exception {
        if (advBannerForm.getPosition() != null && advBannerForm.getPosition() == AdvBannerType.BACKEND_USER) {
            advBannerForm.setCategoryId(null);
        }
        return advBannerService.addAdvBanner(advBannerForm);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public Response delete(@RequestParam(value = "id", defaultValue = "") String id) {
        return advBannerService.delete(id);
    }

    @RequestMapping(value = "/changestatus", method = RequestMethod.GET)
    @ResponseBody
    public Response changeStatus(@RequestParam(value = "id", defaultValue = "") String id) {
        return advBannerService.changeStatus(id);
    }

    @RequestMapping(value = "/getbanner", method = RequestMethod.GET)
    @ResponseBody
    public Response getCategory(@RequestParam(value = "id", defaultValue = "") String id) {
        AdvBanner advBanner = advBannerService.getAdvBanner(id);
        return new Response(true, "ok", advBanner);
    }
}
