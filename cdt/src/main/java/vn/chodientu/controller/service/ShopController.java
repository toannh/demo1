/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.NlClient;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.form.ShopForm;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ShopService;
import vn.chodientu.util.TextUtils;

/**
 *
 * @author Phuongdt
 */
@Controller("serviceShop")
@RequestMapping("/shop")
public class ShopController extends BaseRest {
    
    @Autowired
    private ShopService shopService;
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody ShopForm form) {
        return shopService.addShop(form);
    }
    
    @RequestMapping(value = "/changemap", method = RequestMethod.GET)
    @ResponseBody
    public Response changeMap(@RequestParam(value = "id") String id, @RequestParam double lat, @RequestParam double lng) {
        return shopService.changeLatAndLng(id, lat, lng);
    }
    
    @RequestMapping(value = "/updateinfo", method = RequestMethod.POST)
    @ResponseBody
    public Response update(@RequestParam String id, @RequestParam String type, @RequestParam String value) {
        return shopService.updateInfo(id, type, value);
    }
    
    @RequestMapping(value = "/getshopbyalias", method = RequestMethod.GET)
    @ResponseBody
    public Response getShopByAlias(@RequestParam String value) {
        if (viewer.getUser() == null) {
            return new Response(false, "Not allow");
        }
        Shop alias = shopService.getByAlias(TextUtils.createAlias(value));
        if (viewer.getUser().getId().equals(alias.getUserId())) {
            return new Response(true, "Của chính nó");
        }
        return new Response(false, "Của thằng khác");
    }
    
    @RequestMapping(value = "/nlIntegrate", method = RequestMethod.GET)
    @ResponseBody
    public Response nlIntegrate(@RequestParam(value = "type", defaultValue = "nl", required = false) String type) {
        User user = viewer.getUser();
        if (user == null) {
            return new Response(false, "Bạn phải đăng nhập!");
        }
        try {
            String return_url = baseUrl + "/user/open-shop-step3.html?itype=" + type;
            String urlNganluong = buildNganluongLinkNew("", user.getId(), "1", return_url);
            return new Response(true, "ok", urlNganluong);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(false, "Not Allow");
    }
    
    public String buildNganluongLinkNew(String email, String userId, String type, String returnUrl) {
        String url = "https://www.nganluong.vn/?portal=nganluong&page=merchant_login";
        String merchant_id = "15643";
        String merchant_password = "df19aa6d60";
        try {
            returnUrl = URLEncoder.encode(returnUrl, "UTF-8");
        } catch (Exception ex) {
            Logger.getLogger(NlClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        String checksum = DigestUtils.md5Hex(merchant_id + merchant_password + email + userId + type + returnUrl);
        return url + "&merchant_id=" + merchant_id + "&email=" + email + "&cdt_id=" + userId + "&type=" + type + "&return_url=" + returnUrl + "&checksum=" + checksum;
    }
    
}
