package vn.chodientu.controller.cpservice.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

/**
 * @since May 10, 2014
 * @author Phu
 */
@Controller("cpGlobalCategoryService")
@RequestMapping("/cpservice/global/category")
public class CategoryController extends BaseRest {

    @Autowired
    private CategoryService categoryService;

    /**
     * Get thông tin danh mục
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Response get(@RequestParam("id") String id) {
        Category category = new Category();
        category.setId(id);
        Map<String, Object> result = new HashMap<>();
        try {
            Category cate = categoryService.get(category.getId());
            List<CategoryProperty> properties = categoryService.getProperties(id);

            result.put("properties", properties);
            return new Response(true, "ok", cate);
        } catch (Exception ex) {
            return new Response(false, "Danh mục không tồn tại");
        }
    }

    /**
     * Get thông tin thuộc tính
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getproperties", method = RequestMethod.GET)
    public Response getProperties(@RequestParam("id") String id) {
        Category category = new Category();
        category.setId(id);
        try {

            List<CategoryProperty> properties = categoryService.getProperties(id);
            return new Response(true, "ok", properties);
        } catch (Exception ex) {
            return new Response(false, "Danh mục không tồn tại");
        }
    }

    /**
     * Lấy danh mục theo id cha
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

    /**
     * Lấy danh mục cấp con theo id
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getchilds", method = RequestMethod.GET)
    public Response getChilds(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        if (!id.trim().equals("")) {
            return new Response(true, "ok", categoryService.getChilds(id));
        } else {
            return new Response(false, "Danh mục không tồn tại!");
        }

    }
    /**
     * Service lấy thông tin của giá trị thuộc tính
     *
     * @param Id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getpropertyvalue", method = RequestMethod.GET)
    public Response getPropertyValue(@RequestParam String id) {
        try {

            List<CategoryPropertyValue> propertyValues = categoryService.getPropertyValues(id);
            return new Response(true, "ok", propertyValues);
        } catch (Exception ex) {
            return new Response(false, "Giá trị thuộc tính không tồn tại");
        }
    }
}
