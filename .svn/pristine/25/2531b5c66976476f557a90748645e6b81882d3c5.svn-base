package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.Model;
import vn.chodientu.repository.CategoryRepository;
import vn.chodientu.repository.ManufacturerRepository;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ManufacturerService;
import vn.chodientu.service.ModelService;

@Controller("editModel")
public class EditModelController extends BaseCp {

    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelService modelService;
    @Autowired
    private Gson gson;

    /**
     * Danh sách tất cả các model được yêu cầu sửa
     *
     * @param map
     * @param session
     * @param page
     * @return
     */
    @RequestMapping(value = "/cp/editmodel", method = RequestMethod.GET)
    public String editModel(ModelMap map) throws Exception {
        String id = "test";
        if (viewer != null && viewer.getAdministrator() != null) {
            id = viewer.getAdministrator().getId();
        }

        List<Model> data = modelService.getEditingModel(id);
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
        map.put("clientScript", "var category = " + gson.toJson(categorys) + ";var edit = true;editmodel.init();");
        return "cp.model.edit";
    }

}
