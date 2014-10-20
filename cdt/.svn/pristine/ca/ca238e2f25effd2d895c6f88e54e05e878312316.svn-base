/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.data.CategoryManufacturerHome;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.FeaturedCategorySub;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.ModelSearch;
import vn.chodientu.entity.input.PropertySearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.FeaturedCategorySubService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.ManufacturerService;
import vn.chodientu.service.ModelService;

/**
 *
 * @author Phuongdt
 */
@Controller("serviceMarket")
@RequestMapping("/market")
public class MarketController extends BaseRest {

    @Autowired
    private FeaturedCategorySubService featuredCategorySubService;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelService modelService;

    @RequestMapping(value = "/gettab", method = RequestMethod.GET)
    @ResponseBody
    public Response getTemplate(@RequestParam String id) throws Exception {
        return featuredCategorySubService.getTabById(id);
    }

    @RequestMapping(value = "/getmodelbymanufac", method = RequestMethod.GET)
    @ResponseBody
    public Response getmodelbymanufac(@RequestParam String subId,@RequestParam String position) throws Exception {
        FeaturedCategorySub tabById = featuredCategorySubService.getModelByManufate(subId);
        tabById.setModels(null);
        if (tabById != null) { 
            String manufId = "";
            List<String> modelIds = new ArrayList<>();
            List<CategoryManufacturerHome> categoryManufacturerHomes = tabById.getCategoryManufacturerHomes();
            if (categoryManufacturerHomes != null && !categoryManufacturerHomes.isEmpty()) {
                for (CategoryManufacturerHome categoryManufacturerHome : categoryManufacturerHomes) {
                    if (position.equals(categoryManufacturerHome.getPosition())) {
                        modelIds.addAll(categoryManufacturerHome.getModelIds());
                        break;
                    }

                }
            }
            if (!modelIds.isEmpty() && !modelIds.get(0).equals("")) {
                List<Model> models = modelService.getModels(modelIds);
                if (models != null && models.size() > 0) {
                    for (Model m : models) {
                        List<String> get = imageService.get(ImageType.MODEL, m.getId());
                        List<String> image = new ArrayList<>();
                        if (get != null && get.size() > 0) {
                            for (String img : get) {
                                String url = imageService.getUrl(img).getUrl();
                                image.add(url);
                            }
                        }
                        m.setImages(image);
                    }
                    ModelSearch modelSearch = new ModelSearch();
                    modelSearch.setStatus(1);
                    modelSearch.setModelId(models.get(0).getId());
                    long itemByModelCount = itemService.getItemByModelCount(modelSearch);
                    models.get(0).setCountShop(itemByModelCount);

                    tabById.setModels(models);
                }
            }

        }
        return new Response(true, null, tabById);

    }

    @RequestMapping(value = "/getitem", method = RequestMethod.GET)
    @ResponseBody
    public Response getitem(@RequestParam String id) throws Exception {
        Item item = itemService.get(id);
        if (item != null) {
            List<String> get = imageService.get(ImageType.ITEM, item.getId());
            if (get != null && get.size() > 0) {
                List<String> image = new ArrayList<>();
                for (String img : get) {
                    image.add(imageService.getUrl(img).getUrl());
                }
                item.setImages(image);
            }
            if (item.getManufacturerId() != null && !item.getManufacturerId().equals("")) {
                Manufacturer manufacturer = manufacturerService.getManufacturer(item.getManufacturerId());
                if (manufacturer != null) {
                    item.setManufacturerName(manufacturer.getName());
                }
            }

        }
        Category get = categoryService.get(item.getCategoryId());
        item.setCategoryName(get.getName());
        //Lấy sản phẩm tương tự ,cùng khoảng giá nếu sản phẩm hết hạn đăng bán hoặc hết hàng
        ItemSearch searchSame = new ItemSearch();
        searchSame.setStatus(1);
        searchSame.setCategoryIds(new ArrayList<String>());
        searchSame.getCategoryIds().add(item.getCategoryId());
        searchSame.setPriceFrom((int) (item.getSellPrice() - (item.getSellPrice() / 10)));
        searchSame.setPriceTo((int) (item.getSellPrice() + (item.getSellPrice() / 10)));
        searchSame.setPageIndex(0);
        searchSame.setPageSize(5);
        searchSame.setModelIds(new ArrayList<String>());
        searchSame.setManufacturerIds(new ArrayList<String>());
        searchSame.setCityIds(new ArrayList<String>());
        searchSame.setProperties(new ArrayList<PropertySearch>());
        DataPage<Item> itemSamePrice = itemService.search(searchSame);
        item.setCountItemSamePrice(itemSamePrice.getDataCount());
        return new Response(true, null, item);
    }

}
