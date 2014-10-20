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
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.ManufacturerService;
import vn.chodientu.service.ModelService;
import vn.chodientu.service.CityService;
import vn.chodientu.service.ImageService;

/**
 *
 * @author Admin
 */
@Controller("item")
@RequestMapping(value = {"/cp"})
public class ItemController extends BaseCp {

    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private CityService cityService;
    @Autowired
    private Gson gson;
    @Autowired
    private ImageService imageService;

    /**
     * Tìm kiếm tất cả sản phẩm
     *
     * @param itemSearch
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping(value = {"/item"}, method = RequestMethod.POST)
    public String search(@ModelAttribute ItemSearch itemSearch,
            HttpSession session,
            ModelMap modelMap) {
        Map<String, Object> parentCategorys = new HashMap<>();
        List<String> cateIds = new ArrayList<>();
        List<String> manufIds = new ArrayList<>();
        List<String> modelIds = new ArrayList<>();
        

        itemSearch.setPageIndex(0);
        itemSearch.setPageSize(40);
        if (itemSearch.getCategoryId() != null && !itemSearch.getCategoryId().equals("")) {
            itemSearch.setCategoryIds(new ArrayList<String>());
            itemSearch.getCategoryIds().add(itemSearch.getCategoryId());
        }
        getAncestors(itemSearch, parentCategorys);
        session.setAttribute("itemSearch", itemSearch);

        DataPage<Item> itemPage = itemService.search(itemSearch);
        for (Item item : itemPage.getData()) {
            if (item.getCategoryId() != null && !item.getCategoryId().equals("") && !cateIds.contains(item.getCategoryId())) {
                cateIds.add(item.getCategoryId());
            }
            if (item.getManufacturerId() != null && !item.getManufacturerId().equals("") && !manufIds.contains(item.getManufacturerId())) {
                manufIds.add(item.getManufacturerId());
            }
            if (item.getModelId() != null && !item.getModelId().equals("") && !modelIds.contains(item.getModelId())) {
                modelIds.add(item.getModelId());
            }

        }
        for (Item item : itemPage.getData()) {
            List<String> images = new ArrayList<>();
            if (item != null && item.getImages() != null && !item.getImages().isEmpty()) {
                for (String img : item.getImages()) {
                    images.add(imageService.getUrl(img).thumbnail(200, 200, "outbound").getUrl());
                }
                item.setImages(images);
            }
        }

        if (itemSearch.getCategoryIds() == null) {
            itemSearch.setCategoryIds(new ArrayList<String>());
        }
        List<City> cities = cityService.list();
        modelMap.put("itemSearch", itemSearch);
        modelMap.put("itemPage", itemPage);
        modelMap.put("cities", cities);
        modelMap.put("itemCates", categoryService.getCategories(cateIds));
        modelMap.put("itemManuf", manufacturerService.getManufacturers(manufIds));
        modelMap.put("itemModels", modelService.getModels(modelIds));
        modelMap.put("realItemCount", itemService.countItem());
        modelMap.put("countItemInElastic", itemService.countElastic());
        modelMap.put("clientScript", "category = " + gson.toJson(categoryService.getChilds(null)) + ",cities=" + gson.toJson(cities) + ";item.init({parentCategorys:" + gson.toJson(parentCategorys) + ", categoryId:'" + (itemSearch.getCategoryIds().isEmpty() ? "" : itemSearch.getCategoryIds().get(0)) + "'});");
        return "cp.item";
    }

    /**
     * Danh sách tất cả sản phẩm
     *
     * @param modelMap
     * @param session
     * @param page
     * @return
     */
    @RequestMapping(value = {"/item"}, method = RequestMethod.GET)
    public String list(
            ModelMap modelMap,
            HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) {

        ItemSearch itemSearch = new ItemSearch();
        List<String> cateIds = new ArrayList<>();
        List<String> manufIds = new ArrayList<>();
        List<String> modelIds = new ArrayList<>();
        if (itemSearch.getCategoryIds() == null) {
            itemSearch.setCategoryIds(new ArrayList<String>());
        }
        if (session.getAttribute("itemSearch") != null && page != 0) {
            itemSearch = (ItemSearch) session.getAttribute("itemSearch");
        } else {
            session.setAttribute("itemSearch", itemSearch);
        }
        Map<String, Object> parentCategorys = new HashMap<>();
        getAncestors(itemSearch, parentCategorys);

        if (page > 0) {
            itemSearch.setPageIndex(page - 1);
        } else {
            itemSearch.setPageIndex(0);
        }
        itemSearch.setPageSize(40);

        DataPage<Item> itemPage = itemService.search(itemSearch);
        for (Item item : itemPage.getData()) {
            if (item.getCategoryId() != null && !item.getCategoryId().equals("") && !cateIds.contains(item.getCategoryId())) {
                cateIds.add(item.getCategoryId());
            }
            if (item.getManufacturerId() != null && !item.getManufacturerId().equals("") && !manufIds.contains(item.getManufacturerId())) {
                manufIds.add(item.getManufacturerId());
            }
            if (item.getModelId() != null && !item.getModelId().equals("") && !modelIds.contains(item.getModelId())) {
                modelIds.add(item.getModelId());
            }
        }

        for (Item item : itemPage.getData()) {
            List<String> images = new ArrayList<>();
            if (item != null && item.getImages() != null && !item.getImages().isEmpty()) {
                for (String img : item.getImages()) {
                    images.add(imageService.getUrl(img).thumbnail(200, 200, "outbound").getUrl());
                }
                item.setImages(images);
            }
        }
        List<City> cities = cityService.list();
        modelMap.put("itemSearch", itemSearch);
        modelMap.put("itemPage", itemPage);
        modelMap.put("cities", cities);
        modelMap.put("itemCates", categoryService.getCategories(cateIds));
        modelMap.put("itemManuf", manufacturerService.getManufacturers(manufIds));
        modelMap.put("itemModels", modelService.getModels(modelIds));
        modelMap.put("realItemCount", itemService.countItem());
        modelMap.put("countItemInElastic", itemService.countElastic());
        modelMap.put("clientScript", "category = " + gson.toJson(categoryService.getChilds(null)) + ",cities=" + gson.toJson(cities) + ";item.init({parentCategorys:" + gson.toJson(parentCategorys) + ", categoryId:'" + (itemSearch.getCategoryIds().isEmpty() ? "" : itemSearch.getCategoryIds().get(0)) + "'});");
        return "cp.item";
    }

    public void getAncestors(ItemSearch itemSearch, Map<String, Object> parentCategorys) {
        List<Object> cats = new ArrayList<>();
        if (itemSearch.getCategoryIds() == null || itemSearch.getCategoryIds().isEmpty()) {
            parentCategorys.put("cats", cats);
        } else {
            try {
                cats.add(categoryService.getChilds(null));
                List<Category> ancestors = categoryService.getAncestors(itemSearch.getCategoryIds().get(0));
                for (Category cat : ancestors) {
                    cats.add(categoryService.getChilds(cat.getId()));
                }
                parentCategorys.put("cats", cats);
                parentCategorys.put("ancestors", ancestors);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
