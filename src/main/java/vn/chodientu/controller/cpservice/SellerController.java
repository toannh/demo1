/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.SellerApi;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.SellerApiService;
import vn.chodientu.service.SellerService;

@Controller("cpSellerService")
@RequestMapping("/cpservice/seller")
public class SellerController extends BaseRest {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private SellerApiService sellerApiService;
    @Autowired
    private Gson gson;

    @RequestMapping(value = "/syncitem", method = RequestMethod.GET)
    @ResponseBody
    public Response syncItem(@RequestParam(value = "userId", defaultValue = "") String userId,
            @RequestParam boolean nlIntegrated,
            @RequestParam boolean scIntegrated) {
        try {
            sellerService.processUpdateOnlinePaymentItem(userId, nlIntegrated);
            sellerService.processUpdateCodPaymentItem(userId, scIntegrated);
            return new Response(true, "Đồng bộ thành công!");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

    }

    @RequestMapping(value = "/createapicode", method = RequestMethod.GET)
    @ResponseBody
    public Response syncItem(@RequestParam(value = "userId", defaultValue = "") String userId) {
        try {
            SellerApi sellerApi = sellerApiService.createApiCode(userId);
            return new Response(true, "Thêm code API vào người bán thành công", sellerApi);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

    }

    @RequestMapping(value = "/findbyuserids", method = RequestMethod.GET)
    @ResponseBody
    public Response listsellerapi(@RequestParam(value = "userIds", defaultValue = "") String userIds) {
        List<String> userIdss = gson.fromJson(userIds, new TypeToken<List<String>>() {
        }.getType());
        return new Response(true, null, sellerApiService.findSellerAPIByUserIds(userIdss));
    }
}
