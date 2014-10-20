package vn.chodientu.controller.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.CategoryProperty;
import vn.chodientu.entity.db.CategoryPropertyValue;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ItemService;

@Controller("serviceCategory")
@RequestMapping("/category")
public class CategoryController extends BaseRest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private Gson gson;

    @RequestMapping(value = "/getchilds", method = RequestMethod.GET)
    @ResponseBody
    public Response getChilds(ModelMap model, @RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        return new Response(true, "Danh sách danh mục", categoryService.getChilds(id));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(ModelMap model, @RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        return new Response(true, "Chi tiết danh mục", categoryService.get(id));
    }

    @RequestMapping(value = "/getproperties", method = RequestMethod.GET)
    @ResponseBody
    public Response getProperties(ModelMap model, @RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        List<CategoryProperty> properties = categoryService.getProperties(id);
        List<CategoryPropertyValue> propertyValues = categoryService.getPropertyValuesWithCategoryId(id);
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("properties", properties);
        hm.put("propertyValues", propertyValues);
        return new Response(true, "Danh sach thuộc tính", hm);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public Response index() throws Exception {
        return categoryService.index();
    }
    
    /**
     * Lấy toàn bộ danh mục cha
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getancestors", method = RequestMethod.GET)
    public Response getAncestors(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        List<Object> cats = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        cats.add(categoryService.getChilds(null));
        if (id == null || id.trim().equals("")) {
            result.put("cats", cats);
        } else {
            try {
                List<Category> ancestors = categoryService.getAncestors(id);

                for (Category cat : ancestors) {
                    cats.add(categoryService.getChilds(cat.getId()));
                }
                Category find = categoryService.get(id);
                result.put("cate", find);
                result.put("cats", cats);
                result.put("ancestors", ancestors);
            } catch (Exception ex) {
                return new Response(false, ex.getMessage());
            }
        }
        return new Response(true, "ok", result);

    }

}
