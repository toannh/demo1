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
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ShopCategoryService;

/**
 *
 * @author TheHoa
 */
@Controller("serviceShopCategory")
@RequestMapping("/shopcategory")
public class ShopCategoryController extends BaseRest {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody ShopCategory category) throws Exception {
        return shopCategoryService.addCategory(category);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response getById(@RequestParam String id) {
        return new Response(true, "", shopCategoryService.get(id));
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ResponseBody
    public Response remove(@RequestParam String id) throws Exception {
        try {
            shopCategoryService.remove(id);
            return new Response(true, "Xóa thành công!");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Response edit(@RequestBody ShopCategory category) throws Exception {
        if (category.getParentId().equals("0")) {
            category.setParentId(null);
        }
        return shopCategoryService.edit(category);
    }

    /**
     * lấy danh mục shop theo shop
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getbyshop", method = RequestMethod.GET)
    @ResponseBody
    public Response getByShop() throws Exception {
        if (viewer.getUser() == null) {
            return new Response(false, "Đăng nhập lại để thực hiện");
        }
        return new Response(true, "ok", shopCategoryService.getByShop(viewer.getUser().getId()));
    }

    @RequestMapping(value = "/changeactive", method = RequestMethod.GET)
    @ResponseBody
    public Response changeActive(@RequestParam String id) throws Exception {
        try {
            shopCategoryService.changeActive(id);
            return new Response(true, "Thay đổi trạng thái hiển thị thành công!");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/changehome", method = RequestMethod.GET)
    @ResponseBody
    public Response changeHome(@RequestParam String id) throws Exception {
        try {
            shopCategoryService.changeHome(id);
            return new Response(true, "Thay đổi trạng thái nổi bật trang chủ thành công!");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }
    @RequestMapping(value = "/getchilds", method = RequestMethod.GET)
    @ResponseBody
    public Response getChilds(@RequestParam String id) {
        String userId = null;
        if(viewer.getUser() != null){
            userId = viewer.getUser().getId();
        }
        return new Response(true, "", shopCategoryService.getChilds(id,userId));
    }
}
