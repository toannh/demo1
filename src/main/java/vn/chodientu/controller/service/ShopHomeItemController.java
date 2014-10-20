/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.ShopHomeItem;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ShopHomeItemService;

/**
 *
 * @author Phuc
 */
@Controller("serviceShopHomeItem")
@RequestMapping("/shophomeitem")
public class ShopHomeItemController extends BaseRest {

    @Autowired
    private ShopHomeItemService shopHomeItemService;
    @Autowired
    private Gson gson;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response addShopHomeItem(@RequestBody ShopHomeItem shopHomeItem) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        return shopHomeItemService.add(shopHomeItem, viewer.getUser());
    }

    @RequestMapping(value = "/changeactive", method = RequestMethod.GET)
    @ResponseBody
    public Response addShopHomeItem(@RequestParam String id) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        return shopHomeItemService.changeActive(id, viewer.getUser());
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    @ResponseBody
    public Response deleteShopHomeItem(@RequestParam String id) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        return shopHomeItemService.remove(id, viewer.getUser());
    }

    @RequestMapping(value = "/removes", method = RequestMethod.GET)
    @ResponseBody
    public Response removesShopHomeItem(@RequestParam("ids") String ids) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        try {
            List<String> itemIds = gson.fromJson(ids, new TypeToken<List<String>>() {
            }.getType());
            shopHomeItemService.remove(itemIds, viewer.getUser());
            return new Response(true, "Các box sản phẩm đã được xóa thành công !");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response updateShopHomeItem(@RequestBody ShopHomeItem shopHomeItem) {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        return shopHomeItemService.edit(shopHomeItem, viewer.getUser());
    }
}
