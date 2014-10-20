package vn.chodientu.controller.user;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.Promotion;
import vn.chodientu.entity.db.PromotionItem;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.enu.PromotionTarget;
import vn.chodientu.entity.enu.PromotionType;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.CouponService;
import vn.chodientu.service.PromotionService;
import vn.chodientu.service.ShopCategoryService;
import vn.chodientu.service.ShopService;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Controller
@RequestMapping("/user")
public class PromotionController extends BaseUser {

    @Autowired
    private PromotionService promotionService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private Gson gson;
    @Autowired
    private CouponService couponService;

    @RequestMapping("/categorypromotion")
    public String createCategoryPromotion(ModelMap map,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "same", defaultValue = "0") int same,
            @RequestParam(value = "id", defaultValue = "") String id) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/categorypromotion.html";
        }
        map.put("clientScript", " var listTemp = new Array(); promotion.init({type:''});");

        try {
            if (!id.equals("")) {
                Promotion promotion = promotionService.getPromotion(id);
                promotion.setCategories(promotionService.getPromotionCategory(id));
                if (same == 1) {
                    promotion.setId(null);
                } else if (promotion.getStartTime() < System.currentTimeMillis()) {
                    promotion.setId(null);
                    promotionService.stopPromotion(id, viewer.getUser().getId());
                }

                map.put("promotion", promotion);
                map.put("clientScript", " var listTemp = new Array(); promotion.edit({promotion:" + gson.toJson(promotion) + "});");
            }
        } catch (Exception e) {
        }

        DataPage<Promotion> data = promotionService.search(viewer.getUser().getId(), PromotionType.DISCOUND, PromotionTarget.CATEGORY, page - 1, 50);
        List<Category> categorys = categoryService.getChilds(null);
        map.put("category", categorys);
        map.put("dataPage", data);
        map.put("module", "promotion");
        return "user.createcategorypromotion";
    }

    @RequestMapping("/shopcategorypromotion")
    public String createShopCategoryPromotion(ModelMap map,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "same", defaultValue = "0") int same,
            @RequestParam(value = "id", defaultValue = "") String id) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/shopcategorypromotion.html";
        }
        Shop shop = shopService.getShop(viewer.getUser().getId());
        if (shop == null) {
            map.put("title", "Thông báo sửa khuyến mại cho danh mục shop");
            map.put("message", "Bạn phải khởi tạo shop trước khi sử dụng chức năng này!");
            return "user.msg";
        }
        map.put("clientScript", " var listTemp = new Array(); promotion.init({type:'shop'});");

        try {
            if (!id.equals("")) {
                Promotion promotion = promotionService.getPromotion(id);
                promotion.setCategories(promotionService.getPromotionCategory(id));
                if (same == 1) {
                    promotion.setId(null);
                } else if (promotion.getStartTime() < System.currentTimeMillis()) {
                    promotion.setId(null);
                    promotionService.stopPromotion(id, viewer.getUser().getId());
                }

                map.put("promotion", promotion);
                map.put("clientScript", " var listTemp = new Array(); promotion.edit({promotion:" + gson.toJson(promotion) + "});");
            }
        } catch (Exception e) {
        }

        DataPage<Promotion> data = promotionService.search(viewer.getUser().getId(), PromotionType.DISCOUND, PromotionTarget.SHOP_CATEGORY, page - 1, 50);
        List<ShopCategory> categorys = shopCategoryService.getChilds(null, viewer.getUser().getId());
        map.put("category", categorys);
        map.put("dataPage", data);
        map.put("module", "promotion");
        return "user.createshopcategorypromotion";
    }

    @RequestMapping("/itempromotion")
    public String createItemPromotion(ModelMap map,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "same", defaultValue = "0") int same,
            @RequestParam(value = "id", defaultValue = "") String id) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/itempromotion.html";
        }

        map.put("clientScript", " var listTemp = new Array();promotion.init({type:''});");
        if (!id.equals("")) {
            try {
                Promotion promotion = promotionService.getPromotion(id);
                List<PromotionItem> promotionItems = promotionService.getPromotionItem(id);
                promotion.setItems(promotionItems);
                if (same == 1) {
                    promotion.setId(null);
                } else if (promotion.getStartTime() < System.currentTimeMillis()) {
                    promotion.setId(null);
                    promotionService.stopPromotion(id, viewer.getUser().getId());
                }
                map.put("promotion", promotion);
                map.put("clientScript", " var listTemp = new Array(); promotion.edit({promotion:" + gson.toJson(promotion) + "});");
            } catch (Exception e) {
            }
        }
        List<Category> categorys = categoryService.getChilds(null);
        DataPage<Promotion> search = promotionService.search(viewer.getUser().getId(), PromotionType.DISCOUND, PromotionTarget.ITEM, page - 1, 50);
        map.put("dataPage", search);
        map.put("category", categorys);
        map.put("module", "promotion");
        return "user.createitempromotion";
    }

    @RequestMapping("/giftpromotion.html")
    public String createPromotionGifts(ModelMap map,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "same", defaultValue = "0") int same,
            @RequestParam(value = "id", defaultValue = "") String id) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/giftpromotion.html";
        }
        map.put("clientScript", "  var listTemp = new Array(); promotion.init({type:''});");
        if (!id.equals("")) {
            try {
                Promotion promotion = promotionService.getPromotion(id);
                List<PromotionItem> promotionItem = promotionService.getPromotionItem(id);
                promotion.setItems(promotionItem);
                if (same == 1) {
                    promotion.setId(null);
                } else if (promotion.getStartTime() < System.currentTimeMillis()) {
                    promotion.setId(null);
                    promotionService.stopPromotion(id, viewer.getUser().getId());
                }
                map.put("promotion", promotion);
                map.put("clientScript", " var listTemp = new Array(); promotion.edit({promotion:" + gson.toJson(promotion) + "});");
            } catch (Exception e) {
            }
        }
        List<Category> categorys = categoryService.getChilds(null);
        map.put("category", categorys);
        DataPage<Promotion> search = promotionService.search(viewer.getUser().getId(), PromotionType.GIFT, null, page - 1, 50);
        map.put("dataPage", search);
        return "user.giftpromotion";
    }
}
