package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.input.ModelSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ManufacturerService;
import vn.chodientu.service.ModelService;


@Controller("model")
public class ModelController extends BaseCp {

    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private Gson gson;

    /**
     * Danh sách tất cả các model
     *
     * @param map
     * @param session
     * @param page
     * @return
     */
    @RequestMapping(value = "/cp/model", method = RequestMethod.GET)
    public String list(ModelMap map,
            HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {
        ModelSearch search = new ModelSearch();
        if (session.getAttribute("modelSearch") != null && page != 0) {
            search = (ModelSearch) session.getAttribute("modelSearch");
        } else {
            session.setAttribute("modelSearch", search);
        }
        if (page > 0) {
            search.setPageIndex(page - 1);
        } else {
            search.setPageIndex(0);
        }
        search.setPageSize(100);
        DataPage<Model> dataPage = modelService.search(search);
        List<Category> categorys = categoryService.getChilds(null);
        List<String> cateIds = new ArrayList<>();
        List<String> mfIds = new ArrayList<>();

        for (Model model : dataPage.getData()) {
            if (!cateIds.contains(model.getCategoryId())) {
                cateIds.add(model.getCategoryId());
            }
            if (!mfIds.contains(model.getManufacturerId())) {
                mfIds.add(model.getManufacturerId());
            }
        }

        List<Category> cates = categoryService.getCategories(cateIds);
        List<Manufacturer> mfs = manufacturerService.getManufacturers(mfIds);

        for (Model model : dataPage.getData()) {
            for (Manufacturer manufacturer : mfs) {
                if (manufacturer.getId().equals(model.getManufacturerId())) {
                    model.setManufacturerName(manufacturer.getName());
                    break;
                }
            }
            for (Category category : cates) {
                if (category.getId().equals(model.getCategoryId())) {
                    model.setCategoryName(category.getName());
                    break;
                }
            }
        }
        Map<String, Object> parentCategorys = new HashMap<>();
        getAncestors(search.getCategoryId(), parentCategorys);
        map.put("dataPage", dataPage);
        map.put("modelSearch", search);
        map.put("countInElasticseach", modelService.countElastic());
        map.put("realCount", modelService.count());

        map.put("clientScript", "var category = " + gson.toJson(categorys) + " ;var edit = false; model.init({parentCategorys:" + gson.toJson(parentCategorys) + ",categoryId:''});");

        return "cp.model";
    }

    @RequestMapping(value = "/cp/model", method = RequestMethod.POST)
    public String search(ModelMap map,
            HttpSession session, @ModelAttribute("modelSearch") ModelSearch search) throws Exception {
        search.setPageSize(100);
        search.setPageIndex(0);

        session.setAttribute("modelSearch", search);

        DataPage<Model> dataPage = modelService.search(search);
        List<Category> categorys = categoryService.getChilds(null);
        List<String> cateIds = new ArrayList<>();
        List<String> mfIds = new ArrayList<>();

        for (Model model : dataPage.getData()) {
            if (!cateIds.contains(model.getCategoryId())) {
                cateIds.add(model.getCategoryId());
            }
            if (!mfIds.contains(model.getManufacturerId())) {
                mfIds.add(model.getManufacturerId());
            }
        }

        List<Category> cates = categoryService.getCategories(cateIds);
        List<Manufacturer> mfs = manufacturerService.getManufacturers(mfIds);

        for (Model model : dataPage.getData()) {
            for (Manufacturer manufacturer : mfs) {
                if (manufacturer.getId().equals(model.getManufacturerId())) {
                    model.setManufacturerName(manufacturer.getName());
                    break;
                }
            }
            for (Category category : cates) {
                if (category.getId().equals(model.getCategoryId())) {
                    model.setCategoryName(category.getName());
                    break;
                }
            }
        }
        Map<String, Object> parentCategorys = new HashMap<>();
        getAncestors(search.getCategoryId(), parentCategorys);        
        map.put("dataPage", dataPage);
        map.put("modelSearch", search);
        map.put("countInElasticseach", modelService.countElastic());
        map.put("realCount", modelService.count());

        map.put("clientScript", "var category = " + gson.toJson(categorys) + ";var edit = false; model.init({parentCategorys:" + gson.toJson(parentCategorys) + ", categoryId:'" + search.getCategoryId() + "'});");

        return "cp.model";
    }

    

    public void getAncestors(String categoryId, Map<String, Object> parentCategorys) {
        List<Object> cats = new ArrayList<>();
        if (categoryId == null || categoryId.trim().equals("") || categoryId.trim().equals("0")) {
            parentCategorys.put("cats", cats);
        } else {
            try {
                List<Category> ancestors = categoryService.getAncestors(categoryId);
                for (Category cat : ancestors) {
                    cats.add(categoryService.getChilds(cat.getId()));
                }
                parentCategorys.put("cats", cats);
                parentCategorys.put("ancestors", ancestors);
            } catch (Exception ex) {
            }
        }
    }

}
