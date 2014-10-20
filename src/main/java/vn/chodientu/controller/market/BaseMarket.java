package vn.chodientu.controller.market;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import vn.chodientu.controller.BaseWeb;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.CategoryAlias;
import vn.chodientu.entity.db.Footerkeyword;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.output.ItemHistogram;
import vn.chodientu.service.CashService;
import vn.chodientu.service.CategoryAliasService;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.FooterKeywordService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.ManufacturerService;
import vn.chodientu.service.ShopService; 

/**
 * @since Jun 10, 2014
 * @author Phu
 */
public class BaseMarket extends BaseWeb {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CashService cashService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryAliasService categoryAliasService;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private FooterKeywordService footerKeywordService;

    @ModelAttribute
    public void addMarketGlobalAttr(ModelMap map) throws Exception {
        map.put("title", "Mua bán, đấu giá sản phẩm thời trang, trang sức, công nghệ, điện tử...");
        map.put("description", "Mua và bán sản phẩm thời trang, trang sức, mỹ phẩm, làm đẹp, điện thoại, máy tính, công nghệ, điện tử, linh phụ kiện v.v.. mọi thứ tại ChợĐiệnTử eBay Việt Nam - Thanh toán online, CoD, giao hàng toàn quốc, bảo vệ người mua.");

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
        map.put("cash", cash);
        List<CategoryAlias> aliasData = categoryAliasService.getAll(1);
        List<String> manufIds = new ArrayList<>();
        List<String> cateIds = new ArrayList<>();

        for (CategoryAlias categoryAlias : aliasData) {
            List<Manufacturer> mf = new ArrayList<>();
            if (categoryAlias.getManufacturerIds() != null && !categoryAlias.getManufacturerIds().isEmpty()) {
                for (String munufId : categoryAlias.getManufacturerIds()) {
                    Manufacturer manufacturer = manufacturerService.getManufacturer(munufId);
                    List<String> get = imageService.get(ImageType.MANUFACTURER, manufacturer.getId());
                    if (get != null && !get.isEmpty()) {
                        manufacturer.setImageUrl(imageService.getUrl(get.get(0)).compress(100).getUrl());
                    }
                    mf.add(manufacturer);
                    if (!manufIds.contains(munufId)) {
                        manufIds.add(munufId);
                    }
                }
            }
            categoryAlias.setManufacturers(mf);
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
        map.put("alias", aliasData);
        map.put("childCates", childCates);
        //Lấy xu hướng tìm kiếm

        try {
            List<Footerkeyword> footerkeywords = footerKeywordService.list();
            map.put("footerkeywords", footerkeywords);
            map.put("marketshop", shopService.getShop(viewer.getUser().getId()));
        } catch (Exception e) {
        }
    }
}
