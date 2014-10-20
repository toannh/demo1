/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.SellerMarketingService;

/**
 *
 * @author PhucTd
 */
@Controller("cpSmsMarketingService")
@RequestMapping("/cpservice/smsmarketing")
public class SellerSmsMarketingController extends BaseRest {

    @Autowired
    private SellerMarketingService sellerMarketing;

    @RequestMapping(value = "/changeactivesms", method = RequestMethod.GET)
    @ResponseBody
    public Response changeActiveSms(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        if (viewer == null || viewer.getAdministrator() == null) {
            return new Response(false, "Bạn không phải người quản trị!");
        }
        return sellerMarketing.activeSmsMarketing(viewer.getAdministrator(), id);
    }

}
