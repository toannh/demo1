package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.CategoryAlias;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.service.CategoryAliasService;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ManufacturerService;

@Controller("CategoryAlias")
@RequestMapping(value = "/cp/categoryalias")
public class CategoryAliasController extends BaseCp {

    @Autowired
    private CategoryAliasService categoryAliasService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Gson gson;

    /**
     *
     * @param map
     * @return
     */
    @RequestMapping
    public String list(ModelMap map) {
        List<CategoryAlias> aliasData = categoryAliasService.getAll(0);
        List<String> manufIds = new ArrayList<>();
        for (CategoryAlias categoryAlias : aliasData) {
            if (categoryAlias.getManufacturerIds() != null && !categoryAlias.getManufacturerIds().isEmpty()) {
                for (String munufId : categoryAlias.getManufacturerIds()) {
                    if (!manufIds.contains(munufId)) {
                        manufIds.add(munufId);
                    }
                }
            }
        }
        List<Category> categories = categoryService.getChilds(null);
        List<Manufacturer> manufacturers = manufacturerService.get(manufIds);
        Map<String, List<String>> images = imageService.get(ImageType.MANUFACTURER, manufIds);
        for (Map.Entry<String, List<String>> entry : images.entrySet()) {
            String manufId = entry.getKey();
            List<String> manufImages = entry.getValue();
            for (Manufacturer manufacturer : manufacturers) {
                if (manufacturer.getId().equals(manufId) && manufImages != null && !manufImages.isEmpty()) {
                    manufacturer.setImageUrl(imageService.getUrl(manufImages.get(0)).thumbnail(180, 45, "outbound").getUrl());
                }
            }
        }
        map.put("aliasData", aliasData);
        map.put("categories", categories);
        map.put("mamufacturers", manufacturers);
        map.put("clientScript", "categoryalias.init(); var categories=" + gson.toJson(categories) + ";");
        return "cp.category.alias";
    }

}
