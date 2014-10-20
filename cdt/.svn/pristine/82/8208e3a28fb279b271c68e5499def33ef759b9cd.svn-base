package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.Model;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ManufacturerService;
import vn.chodientu.service.ModelService;

@Controller("reviewModel")
public class ReviewModelController extends BaseCp {

    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private Gson gson;

    /**
     * Duyệt model tạo
     *
     * @param map
     * @param task
     * @return
     */
    @RequestMapping(value = "/cp/reviewmodel", method = RequestMethod.GET)
    public String reviewCreateModel(ModelMap map, @RequestParam(required = false, value = "task") String task) throws Exception {
        String id = "test";
        if (viewer != null && viewer.getAdministrator() != null) {
            id = viewer.getAdministrator().getId();
        }
        List<Model> data;

        if (task != null && !task.trim().equals("") && task.equals("edit")) {
            data = modelService.getEditedModel(id);
        } else {
            data = modelService.getCreatedModel(id);
        }
        map.put("data", data);

        List<String> cateIds = new ArrayList<>();
        List<String> mfIds = new ArrayList<>();

        for (Model model : data) {
            if (!cateIds.contains(model.getCategoryId())) {
                cateIds.add(model.getCategoryId());
            }
            if (!mfIds.contains(model.getManufacturerId())) {
                mfIds.add(model.getManufacturerId());
            }
        }

        List<Category> cates = categoryService.get(cateIds);
        List<Manufacturer> mfs = manufacturerService.get(mfIds);

        for (Model model : data) {
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
        List<Category> categorys = categoryService.getChilds(null);
        map.put("clientScript", "var category = " + gson.toJson(categorys) + ";var edit = false;reviewmodel.init();");
        if (task != null && !task.trim().equals("") && task.equals("edit")) {
            return "cp.model.reviewedit";
        } else {
            return "cp.model.reviewcreate";
        }

    }

}
