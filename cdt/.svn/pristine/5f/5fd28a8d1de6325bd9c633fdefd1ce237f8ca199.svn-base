package vn.chodientu.controller.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.entity.api.Request;
import vn.chodientu.entity.db.SellerApi;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ApiHistoryService;
import vn.chodientu.service.CategoryService;

@Controller("categoryApiController")
@RequestMapping(value = "/api/cate")
public class CategoryController extends BaseApi {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private Gson gson;
    @Autowired
    private ApiHistoryService apiHistoryService;

    @RequestMapping(value = "/getbycategory", method = RequestMethod.POST)
    @ResponseBody
    public Response getByCategory(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            HashMap<String, String> params = gson.fromJson(data.getParams(), new TypeToken<HashMap<String, String>>() {
            }.getType());
            if (params == null) {
                return new Response(false, "Tham số chưa chính xác");
            }
            String cateId = params.get("cateId");
            if (cateId.equals("") || cateId.equals("0") || cateId == null) {
                cateId = null;
            }
            response = new Response(true, "Danh sách danh mục chợ", categoryService.getChilds(cateId));
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }

    @RequestMapping(value = "/getancestors", method = RequestMethod.POST)
    @ResponseBody
    public Response getAncestors(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            HashMap<String, String> params = gson.fromJson(data.getParams(), new TypeToken<HashMap<String, String>>() {
            }.getType());
            if (params == null) {
                return new Response(false, "Tham số chưa chính xác");
            }
            String cateId = params.get("cateId");
            if (cateId.equals("") || cateId.equals("0") || cateId == null) {
                cateId = null;
            }
            response = new Response(true, "Danh sách danh mục chợ", categoryService.getAncestors(cateId));
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }

    @RequestMapping(value = "/getancestorsbylevel", method = RequestMethod.POST)
    @ResponseBody
    public Response getAncestorsByLevelDisplay(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            HashMap<String, Integer> params = gson.fromJson(data.getParams(), new TypeToken<HashMap<String, String>>() {
            }.getType());
            if (params == null) {
                return new Response(false, "Tham số chưa chính xác");
            }
            int level = params.get("level");
            if (level < 0) {
                level = 0;
            }
            response = new Response(true, "Danh sách danh mục chợ", categoryService.getAncestorsByLevelDisplay(level));
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }

}
