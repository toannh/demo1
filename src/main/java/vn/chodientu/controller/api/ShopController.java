package vn.chodientu.controller.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.entity.api.Request;
import vn.chodientu.entity.db.SellerApi;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ApiHistoryService;
import vn.chodientu.service.ShopCategoryService;
import vn.chodientu.service.ShopService;

@Controller("shopApiController")
@RequestMapping(value = "/api/shop")
public class ShopController extends BaseApi {

    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private Gson gson;
    @Autowired
    private ApiHistoryService apiHistoryService;
    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Response list(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            Shop shop = shopService.getShop(user.getId());
            if (shop == null) {
                response = new Response(false, "Shop không tồn tại!");
            }
            List<ShopCategory> byShop = shopCategoryService.getByShop(shop.getUserId());
            response = new Response(true, "Danh sách danh mục shop", byShop);
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }

    @RequestMapping(value = "/createandupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response createAndUpdate(@RequestBody Request data, ModelMap model) {
        Response response = null;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            ShopCategory params = gson.fromJson(data.getParams(), ShopCategory.class);
            Shop shop = shopService.getShop(user.getId());
            if (shop == null) {
                response = new Response(false, "Shop không tồn tại!");
            }
            if (params == null) {
                return new Response(false, "Tham số chưa chính xác");
            }
            if (shopCategoryService.get(params.getId()) == null) {
                response = shopCategoryService.addCategory(params);
            } else {
                response = shopCategoryService.edit(params);
            }
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }
}
