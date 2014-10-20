/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ManufacturerService;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.input.ManufacturerSearch;
import vn.chodientu.service.ImageService;

@Controller("Manufacturer")
@RequestMapping(value = "/cp/manufacturer")
public class ManufacturerController extends BaseCp {

    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Gson gson;

    /**
     *
     * @param map
     * @param session
     * @param page
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map,
            HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        ManufacturerSearch mnSearch = new ManufacturerSearch();
        if (mnSearch.getCategoryId() == null) {
            mnSearch.setCategoryId("");
        }
        if (session.getAttribute("mnSearch") != null && page != 0) {
            mnSearch = (ManufacturerSearch) session.getAttribute("mnSearch");
        } else {
            session.setAttribute("mnSearch", mnSearch);
        }
        if (page > 0) {
            mnSearch.setPageIndex(page - 1);
        } else {
            mnSearch.setPageIndex(0);
        }
        mnSearch.setPageSize(100);

        Map<String, Object> parentCategorys = new HashMap<>();
        getAncestors(mnSearch, parentCategorys);

        DataPage<Manufacturer> manufacturersPage = manufacturerService.search(mnSearch);

        List<String> ids = new ArrayList<>();

        for (Manufacturer manufacturer : manufacturersPage.getData()) {
            ids.add(manufacturer.getId());
        }

        Map<String, List<String>> images = imageService.get(ImageType.MANUFACTURER, ids);
        for (Manufacturer manufacturer : manufacturersPage.getData()) {
            if (images.get(manufacturer.getId()).size() > 0) {
                manufacturer.setImageUrl(imageService.getUrl(images.get(manufacturer.getId()).get(0)).thumbnail(200, 200, "inset").getUrl());
            }
        }

        map.put("mnSearch", mnSearch);
        map.put("manufacturersPage", manufacturersPage);
        map.put("countInElasticseach", manufacturerService.countElastic());
        map.put("realCount", manufacturerService.count());
        map.put("clientScript", "cate = " + gson.toJson(categoryService.getChilds(null)) + "; manufacturer.init({parentCategorys:" + gson.toJson(parentCategorys) + ", categoryId:'" + mnSearch.getCategoryId() + "'});");

        return "cp.manufacturer";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(
            @ModelAttribute("mnSearch") ManufacturerSearch mnSearch,
            HttpSession session,
            ModelMap map) {
        mnSearch.setPageSize(100);
        mnSearch.setPageIndex(0);

        session.setAttribute("mnSearch", mnSearch);

        Map<String, Object> parentCategorys = new HashMap<>();
        getAncestors(mnSearch, parentCategorys);

        DataPage<Manufacturer> manufacturersPage = manufacturerService.search(mnSearch);

        if (mnSearch.getCategoryId() == null) {
            mnSearch.setCategoryId("");
        }
        List<String> ids = new ArrayList<>();

        for (Manufacturer manufacturer : manufacturersPage.getData()) {
            ids.add(manufacturer.getId());
        }

        Map<String, List<String>> images = imageService.get(ImageType.MANUFACTURER, ids);
        for (Manufacturer manufacturer : manufacturersPage.getData()) {
            if (images.get(manufacturer.getId()).size() > 0) {
                manufacturer.setImageUrl(imageService.getUrl(images.get(manufacturer.getId()).get(0)).thumbnail(200, 200, "inset").getUrl());
            }
        }
        map.put("mnSearch", mnSearch);
        map.put("manufacturersPage", manufacturersPage);
        map.put("countInElasticseach", manufacturerService.countElastic());
        map.put("realCount", manufacturerService.count());
        map.put("clientScript", "cate = " + gson.toJson(categoryService.getChilds(null)) + "; manufacturer.init({parentCategorys:" + gson.toJson(parentCategorys) + ", categoryId:'" + mnSearch.getCategoryId() + "'});");
        return "cp.manufacturer";
    }

    public void getAncestors(ManufacturerSearch manufacturerSearch, Map<String, Object> parentCategorys) {
        List<Object> cats = new ArrayList<>();
        if (manufacturerSearch.getCategoryId() == null || manufacturerSearch.getCategoryId().trim().equals("")) {
            parentCategorys.put("cats", cats);
        } else {
            try {
                List<Category> ancestors = categoryService.getAncestors(manufacturerSearch.getCategoryId());
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
