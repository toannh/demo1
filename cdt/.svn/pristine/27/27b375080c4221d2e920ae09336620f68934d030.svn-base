package vn.chodientu.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import vn.chodientu.controller.BaseWeb;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.CategoryAlias;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.output.ItemHistogram;
import vn.chodientu.service.AdvBannerService;
import vn.chodientu.service.CashService;
import vn.chodientu.service.CategoryAliasService;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.ManufacturerService;
import vn.chodientu.service.ShopService;

/**
 * @since May 15, 2014
 * @author Phu
 */
public class BaseUser extends BaseWeb {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CashService cashService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private CategoryAliasService categoryAliasService;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private AdvBannerService advBannerService;

    @ModelAttribute
    public void addUserGlobalAttr(ModelMap map, HttpServletRequest request) {
        map.put("categories", categoryService.getByLevelDisplay(1));
        map.put("subCategories", categoryService.getByLevelDisplay(2));
        Cash cash = null;
        try {
            cash = cashService.getCash(viewer.getUser().getId());
            long statusCount = 0;
            List<ItemHistogram> itemHistograms = itemService.getItemStatusHistogram(viewer.getUser().getId());
            for (ItemHistogram itemHistogram : itemHistograms) {
                if (itemHistogram.getType().equals("outDate")) {
                    map.put("outDate", itemHistogram.getCount());
                    statusCount += itemHistogram.getCount();
                } else if (itemHistogram.getType().equals("outOfStock")) {
                    map.put("outOfStock", itemHistogram.getCount());
                    statusCount += itemHistogram.getCount();
                } else if (itemHistogram.getType().equals("unapproved")) {
                    map.put("unapproved", itemHistogram.getCount());
                    statusCount += itemHistogram.getCount();
                }
            }
            map.put("statusCount", statusCount);
        } catch (Exception e) {
            cash = new Cash();
        }
        map.put("cashUser", cash);
        try {
            map.put("ushop", shopService.getShop(viewer.getUser().getId()));
        } catch (Exception e) {
        }
        map.put("uri", request.getRequestURI());
        map.put("bannertop", advBannerService.getAdvBannerBackEndUser());
    }

    @ModelAttribute
    public void initAuth(ModelMap model) {
        List<CategoryAlias> aliasData = categoryAliasService.getAll(1);
        List<String> manufIds = new ArrayList<>();
        List<String> cateIds = new ArrayList<>();
        for (CategoryAlias categoryAlias : aliasData) {
            if (categoryAlias.getManufacturerIds() != null && !categoryAlias.getManufacturerIds().isEmpty()) {
                for (String munufId : categoryAlias.getManufacturerIds()) {
                    if (!manufIds.contains(munufId)) {
                        manufIds.add(munufId);
                    }
                }
            }
            if (!cateIds.contains(categoryAlias.getCategoryId())) {
                cateIds.add(categoryAlias.getCategoryId());
            }
        }
        List<Category> cates = categoryService.getCategories(cateIds);

        for (CategoryAlias categoryAlias : aliasData) {
            for (Category category : cates) {
                if (categoryAlias.getCategoryId().equals(category.getId())) {
                    categoryAlias.setCategoryName(category.getName());
                    break;
                }
            }
        }

        List<Category> childCates = categoryService.getChildsByIds(cateIds);
        List<Manufacturer> manufacturers = manufacturerService.get(manufIds);
        Map<String, List<String>> images = imageService.get(ImageType.MANUFACTURER, manufIds);
        for (Map.Entry<String, List<String>> entry : images.entrySet()) {
            String manufId = entry.getKey();
            List<String> manufImages = entry.getValue();
            for (Manufacturer manufacturer : manufacturers) {
                if (manufacturer.getId().equals(manufId) && manufImages != null && !manufImages.isEmpty()) {
                    manufacturer.setImageUrl(imageService.getUrl(manufImages.get(0)).getUrl());
                }
            }
        }
        model.put("alias", aliasData);
        model.put("childCates", childCates);
        model.put("mamufacturers", manufacturers);

        model.put("aliasActive", true);
    }
}
