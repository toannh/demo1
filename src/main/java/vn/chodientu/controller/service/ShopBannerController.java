/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.form.ShopBannerForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ShopBannerService;

/**
 *
 * @author Phuongdt
 */
@Controller("serviceShopBanner")
@RequestMapping("/shopbanner")
public class ShopBannerController extends BaseRest {

    @Autowired
    private ShopBannerService shopBannerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@ModelAttribute ShopBannerForm shopBannerForm) throws Exception {
        return shopBannerService.add(shopBannerForm);
    }

    @RequestMapping(value = "/changestatus", method = RequestMethod.GET)
    @ResponseBody
    public Response changestatus(@RequestParam String id) throws Exception {
        return shopBannerService.changeStatus(id);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response del(@RequestParam String id) throws Exception {
        return shopBannerService.del(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response del(@ModelAttribute ShopBannerForm shopBannerForm) throws Exception {
        return shopBannerService.edit(shopBannerForm);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam String id) throws Exception {
        return new Response(true, null, shopBannerService.getById(id));
    }

}
