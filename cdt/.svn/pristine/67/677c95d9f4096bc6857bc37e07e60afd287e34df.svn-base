package vn.chodientu.controller.shop;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpServerErrorException;
import vn.chodientu.controller.BaseWeb;
import vn.chodientu.entity.db.Promotion;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.db.ShopContact;
import vn.chodientu.entity.db.ShopNewsCategory;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.PropertySearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.PromotionService;
import vn.chodientu.service.SellerFollowService;
import vn.chodientu.service.SellerReviewService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopCategoryService;
import vn.chodientu.service.ShopContactService;
import vn.chodientu.service.ShopNewsCategoryService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;
import vn.chodientu.util.UrlUtils;

/**
 * @since Jun 2, 2014
 * @author Phu
 */
public class BaseShop extends BaseWeb {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private ShopContactService shopContactService;
    @Autowired
    private ShopNewsCategoryService shopNewsCategoryService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Gson gson;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private SellerReviewService sellerReviewService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private UserService userService;
    @Autowired
    private SellerFollowService sellerFollowService;

    @ModelAttribute
    public void addShopAttr(ModelMap model, @PathVariable String alias,
            HttpServletResponse response) {
        List categories = categoryService.getChilds(null);
        model.put("rootCategories", categories);
    }

    public void initMap(String alias, ModelMap model, HttpServletResponse response) {
        Shop shop = shopService.getByAlias(alias);
        if (shop == null) {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "Shop không tồn tại");
        }

        Seller seller = sellerService.getById(shop.getUserId());
        if (!seller.isNlIntegrated() || !seller.isScIntegrated()) {
            if (viewer.getUser() == null || !viewer.getUser().getId().equals(shop.getUserId())) {
                ItemSearch itemSearch = new ItemSearch();
                itemSearch.setCategoryIds(new ArrayList<String>());
                itemSearch.setManufacturerIds(new ArrayList<String>());
                itemSearch.setModelIds(new ArrayList<String>());
                itemSearch.setProperties(new ArrayList<PropertySearch>());
                itemSearch.setSellerId(shop.getUserId());
                itemSearch.setCityIds(new ArrayList<String>());
                try {
                    response.sendRedirect(UrlUtils.browseUrl(itemSearch, ""));
                } catch (Exception e) {
                }
                return;
            }
            throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED,
                    "Bạn chưa cấu hình tích hợp Nganluong hoặc shipchung cho shop, vui lòng vào trang <a href='http://chodientu.vn/user/cau-hinh-tich-hop.html' >cấu hình tích hợp</a> để cấu hình");
        }

        shopService.viewCount(alias);
        List<String> images = imageService.get(ImageType.SHOP_LOGO, shop.getUserId());
        if (images.isEmpty()) {
            shop.setLogo(staticUrl + "/shop/img/data/logo.png");
        } else {
            shop.setLogo(imageService.getUrl(images.get(0)).maxSize(200, 100).getUrl());
        }

        List<ShopCategory> shopCategories = shopCategoryService.getByShopIsActive(shop.getUserId());
        List<ShopNewsCategory> shopNewsCategories = shopNewsCategoryService.getByShopisActive(shop.getUserId());
        List<ShopContact> shopContacts = shopContactService.getContactById(shop.getUserId());

        List<Promotion> listPromotion = promotionService.getPromotionBySellerIsRunning(shop.getUserId(), null, 1);
        model.put("listPromotion", listPromotion);

        ItemSearch shopItemSearch = new ItemSearch();
        shopItemSearch.setCategoryIds(new ArrayList<String>());
        shopItemSearch.setManufacturerIds(new ArrayList<String>());
        shopItemSearch.setModelIds(new ArrayList<String>());
        shopItemSearch.setProperties(new ArrayList<PropertySearch>());
        shopItemSearch.setCityIds(new ArrayList<String>());
        shopItemSearch.setSellerId(shop.getUserId());
        model.put("shopItemSearch", shopItemSearch);

        model.put("shopReview", sellerReviewService.report(shop.getUserId()));
        model.put("shopNewsCategories", shopNewsCategories);
        model.put("title", "Xem sản phẩm liên tục cập nhật từ shop " + shop.getTitle());
        model.put("description", "Xem sản phẩm từ shop " + shop.getTitle() + " tại chodientu.vn - Giá rẻ, nhiều khuyến mại, thanh toán online, CoD, giao hàng toàn quốc, bảo vệ người mua");
        model.put("keywords", "chodientu, quần áo, thời trang, trang sức, phụ kiện, điện tử, xe hơi, đồ thể thao, điện thoại, máy tính, mobile, laptop máy ảnh kỹ thuật số, đồ mẹ bé, ebay, mua bán, đấu giá");
        model.put("canonical", "/" + shop.getAlias() + "/");
        model.put("shop", shop);
        model.put("user", userService.getById(shop.getUserId()));
        model.put("shopContact", shopContacts);
        model.put("shopCategories", shopCategories);
        model.put("sellerFollowCount", sellerFollowService.countByItem(shop.getUserId()));

        String clientScript = "";
        clientScript += "shopCategories=" + gson.toJson(shopCategories) + ";";
        clientScript += "shop=" + gson.toJson(shop) + ";";
        clientScript += "shopNewsCategories=" + gson.toJson(shopNewsCategories) + ";";
        model.put("clientScript", clientScript);

//        return model;
    }

}
