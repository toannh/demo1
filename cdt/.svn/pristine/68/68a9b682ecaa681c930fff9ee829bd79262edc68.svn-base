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
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopService;

@Controller("cpShopService")
@RequestMapping("/cpservice/shop")
public class ShopController extends BaseRest {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response getShopById(@RequestParam(value = "userId", defaultValue = "") String userId) {
        try {
            Shop shop = shopService.getShop(userId);
            return new Response(true, "", shop);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

    }

    @RequestMapping(value = "/adddomain", method = RequestMethod.GET)
    @ResponseBody
    public Response addDomain(@RequestParam(value = "id", defaultValue = "") String id, @RequestParam(value = "domain", defaultValue = "") String domain) {
        try {
            Shop addDomain = shopService.addDomain(id, domain);
            return new Response(true, "Thêm domain cho shop thành công!", addDomain);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

    }

    @RequestMapping(value = "/addsupport", method = RequestMethod.GET)
    @ResponseBody
    public Response addsupport(@RequestParam(value = "userId") String userId) {
        try {
            Shop addSupporter = shopService.addSupporter(userId, viewer.getAdministrator().getEmail());
            return new Response(true, null, addSupporter);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/addnote", method = RequestMethod.GET)
    @ResponseBody
    public Response addNote(@RequestParam(value = "id") String id, @RequestParam(value = "note", defaultValue = "") String note) {
        try {
            Shop addNote = shopService.addNote(id, note);
            return new Response(true, "Cập nhật ghi chú thành công", addNote);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

}
