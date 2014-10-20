/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.ShopNewsCategory;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ShopNewsCategoryService;

/**
 *
 * @author TheHoa
 */
@Controller("serviceShopNewsCategory")
@RequestMapping("/shopnewscategory")
public class ShopNewsCategoryController extends BaseRest {

    @Autowired
    private ShopNewsCategoryService shopNewsCategoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody ShopNewsCategory category) throws Exception {
        return shopNewsCategoryService.addCategory(category);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response getById(@RequestParam String id) {
        return new Response(true, "", shopNewsCategoryService.get(id));
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response remove(@RequestParam String id) throws Exception {
        try {
            shopNewsCategoryService.remove(id);
            return new Response(true, "Xóa thành công!");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@RequestBody ShopNewsCategory category) throws Exception {
        if (category.getParentId().equals("0")) {
            category.setParentId(null);
        }
        return shopNewsCategoryService.edit(category);
    }

    @RequestMapping(value = "/changeactive", method = RequestMethod.GET)
    @ResponseBody
    public Response changeActive(@RequestParam String id) throws Exception {
        try {
            shopNewsCategoryService.changeActive(id);
            return new Response(true, "Thay đổi trạng thái hiển thị thành công!");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

}
