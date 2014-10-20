package vn.chodientu.controller.user;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemProperty;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.ModelProperty;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.Condition;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CashService;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.ModelService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopCategoryService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

/**
 * @author thanhvv
 */
@Controller
@RequestMapping("/user")
public class ItemController extends BaseUser {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CashService cashService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private Gson gson;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @RequestMapping("/dang-ban")
    public String postItem(ModelMap map,
            @RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "mid", defaultValue = "") String mid,
            @RequestParam(value = "same", defaultValue = "0") int same) {
        if (viewer.getUser() == null) {
            if (id != null && !id.equals("")) {
                return "redirect:/user/signin.html?ref=" + baseUrl + "/user/dang-ban.html?id=" + id;
            }
            if (mid != null && !mid.equals("")) {
                return "redirect:/user/signin.html?ref=" + baseUrl + "/user/dang-ban.html?mid=" + mid;
            }

            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/dang-ban.html";
        }
        Shop shop = shopService.getShop(viewer.getUser().getId());
        User user = null;
        Seller seller = null;
        try {
            user = userService.get(viewer.getUser().getId());
            seller = sellerService.getById(viewer.getUser().getId());
        } catch (Exception ex) {
        }

        if (user == null || !user.isPhoneVerified() || user.getPhone() == null || user.getPhone().equals("")) {
            map.put("title", "Thông báo đăng bán");
            if (user == null) {
                map.put("message", "Tài khoản không tồn tại.");
            } else if (!user.isPhoneVerified()) {
                map.put("message", "Số điện thoại chưa xác minh, bạn cần xác minh bằng cách nhắn tin theo cú pháp <strong class=\"redfont\"></br>CDT XM " + viewer.getUser().getId() + "</strong> gửi <strong class=\"redfont\">8255</strong> (Phí 2.000đ/tin nhắn)</span>");
            } else if (user.getPhone() == null || user.getPhone().equals("")) {
                String url = baseUrl + "/user/profile.html";
                if (shop != null) {
                    url = baseUrl + "/user/cau-hinh-shop-step1.html";
                }
                map.put("message", "Bạn cần cập nhật số điện thoại để thực hiện thao tác này. Click vào <a href='" + url + "'>đây</a> để cập nhật.");
            }
            return "user.msg";
        }

        if (id.equals("")
                && ((shop == null && (user.getCityId() == null || user.getCityId().equals("") || user.getDistrictId() == null || user.getDistrictId().equals("")))
                || (shop != null && (shop.getCityId() == null || shop.getCityId().equals("") || shop.getDistrictId() == null || shop.getDistrictId().equals(""))))) {
            map.put("title", "Thông báo đăng bán");
            String url = baseUrl + "/user/profile.html";
            map.put("message", "Bạn cần cập nhật địa chỉ để thực hiện thao tác này. Click vào <a href='" + url + "'>đây</a> để cập nhật.");
            if (shop != null) {
                url = baseUrl + "/user/cau-hinh-shop-step1.html";
                map.put("message", "Bạn cần cập nhật địa chỉ shop để thực hiện thao tác này. Click vào <a href='" + url + "'>đây</a> để cập nhật.");
            }
            return "user.msg";
        }
        Item item = new Item();
        if (!id.equals("")) {
            try {
                item = itemService.get(id);
                if (!item.getSellerId().equals(viewer.getUser().getId())) {
                    map.put("title", "Thông báo đăng bán");
                    map.put("message", "Sản phẩm này thuộc người bán khác, về <a href='" + baseUrl + "/user/item.html'>quản trị sản phẩm</a> để quản trị sản phẩm.");
                    return "user.msg";
                }
                if (item.getListingType() == ListingType.AUCTION && item.getStartTime() < System.currentTimeMillis() && item.isCompleted()) {
                    map.put("title", "Thông báo đăng bán");
                    map.put("message", "Không được sửa sản phẩm đấu giá.");
                    return "user.msg";
                }
            } catch (Exception e) {
                map.put("title", "Thông báo đăng bán");
                map.put("message", e.getMessage() + ", về <a href='" + baseUrl + "/user/item.html'>quản trị sản phẩm</a> để quản trị sản phẩm.");
                return "user.msg";
            }
        }
        if (mid != null && !mid.equals("")) {
            try {
                Model model = modelService.getModel(mid);
                item.setModelId(mid);
                item.setCategoryId(model.getCategoryId());
                item.setManufacturerId(model.getManufacturerId());
                item.setSellerId(viewer.getUser().getId());
                item.setStartTime(System.currentTimeMillis());
                item.setEndTime(item.getStartTime() + 30 * 12 * 60 * 60 * 1000);

                Response add = itemService.add(item);
                if (add.isSuccess()) {
                    item = (Item) add.getData();
                    List<String> images = new ArrayList<String>();
                    if (model.getImages() != null && !model.getImages().isEmpty()) {
                        for (String mImg : model.getImages()) {
                            Response resp = imageService.download(imageService.getUrl(mImg.trim()).getUrl(), ImageType.ITEM, item.getId());
                            if (resp != null && resp.isSuccess()) {
                                images.add((String) resp.getData());
                            }
                        }
                    }
                    List<ModelProperty> properties = modelService.getProperties(mid);
                    List<ItemProperty> ips = new ArrayList<>();
                    ItemProperty itemProperty = null;
                    for (ModelProperty modelProperty : properties) {
                        itemProperty = new ItemProperty();
                        itemProperty.setCategoryPropertyId(modelProperty.getCategoryPropertyId());
                        itemProperty.setCategoryPropertyValueIds(modelProperty.getCategoryPropertyValueIds());
                        itemProperty.setInputValue(modelProperty.getInputValue());
                        itemProperty.setItemId(item.getId());
                        ips.add(itemProperty);
                    }
                    itemService.updateProperties(item.getId(), ips);
                    id = item.getId();

                }

            } catch (Exception ex) {
            }
        }
        map.put("id", id.trim());
        map.put("same", same);
        map.put("seller", seller);
        map.put("clientScript", "var itemId = '" + id.trim() + "'; var cates = " + gson.toJson(categoryService.getChilds(null)) + "; var seller = " + gson.toJson(seller) + ";additem.init();");
        if (!id.trim().equals("")) {
            return "user.edititem";
        } else {
            return "user.additem";
        }
    }

    @RequestMapping("/item")
    public String list(ModelMap map,
            @RequestParam(value = "tab", defaultValue = "all") String tab,
            @RequestParam(value = "categoryId", defaultValue = "0", required = false) String categoryId,
            @RequestParam(value = "shopCategoryId", defaultValue = "0", required = false) String shopCategoryId,
            @RequestParam(value = "condition", required = false) Condition condition,
            @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
            @RequestParam(value = "lastTime", defaultValue = "0", required = false) int lastTime,
            @RequestParam(value = "listingType", required = false) ListingType listingType,
            @RequestParam(value = "status", defaultValue = "0", required = false) int status,
            @RequestParam(value = "find", defaultValue = "0", required = false) String find,
            @RequestParam(value = "createTime", defaultValue = "0", required = false) long createTime,
            @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        if (viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/item.html";
        }
        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setPageIndex(page - 1);
        itemSearch.setPageSize(100);
        itemSearch.setSellerId(viewer.getUser().getId());

        if (!keyword.equals("")) {
            itemSearch.setKeyword(keyword);
        }
        if (!categoryId.equals("0")) {
            itemSearch.setCategoryIds(new ArrayList<String>());
            itemSearch.getCategoryIds().add(categoryId);
            itemSearch.setCategoryId(categoryId);
        }
        if (!shopCategoryId.equals("0")) {
            itemSearch.setShopCategoryId(shopCategoryId);
        }
        if (condition != null) {
            itemSearch.setCondition(condition);
        }
        if (listingType != null) {
            itemSearch.setListingType(listingType);
        }
        switch (tab) {
            case "all":
                itemSearch.setStatus(9);
                break;
            case "completed":
                itemSearch.setStatus(10);
                break;
            case "recyclebin":
                itemSearch.setStatus(5);
                break;
        }
        if (find != null && !find.equals("") && find.equals("selling")) {
            itemSearch.setStatus(1);
        }
        if (find != null && !find.equals("") && find.equals("unapproved")) {
            itemSearch.setStatus(13);
        }
        if (find != null && !find.equals("") && find.equals("outofstock")) {
            itemSearch.setStatus(11);
        }
        if (find != null && !find.equals("") && find.equals("outDate")) {
            itemSearch.setStatus(12);
        }
        if (find != null && !find.equals("") && find.equals("nobeginning")) {
            itemSearch.setStatus(8);
        }

        if (createTime > 0) {
            long time = 24 * 60 * 60 * 1000;
            itemSearch.setCreateTimeFrom(System.currentTimeMillis());
            itemSearch.setCreateTimeTo(createTime * time);
        }

        itemSearch.setOrderBy(3);
        DataPage<Item> dataPage = itemService.search(itemSearch);

        List<String> categoryIds = new ArrayList<>();
        for (Item item : dataPage.getData()) {
            if (item.getCategoryPath() != null && !item.getCategoryPath().isEmpty()) {
                for (String cId : item.getCategoryPath()) {
                    if (!categoryIds.contains(cId)) {
                        categoryIds.add(cId);
                    }

                }
            }
            List<String> get = imageService.get(ImageType.ITEM, item.getId());
            item.setImages(get);
            if (item.getImages() != null && !item.getImages().isEmpty()) {
                List<String> imgs = new ArrayList<>();
                for (String img : item.getImages()) {
                    imgs.add(imageService.getUrl(img).getUrl());
                }
                item.setImages(imgs);
            }
        }

        List<String> recentPostsCategory = itemService.recentPostsCategory(viewer.getUser().getId());
        List<Category> get = categoryService.get(recentPostsCategory);
        Shop shop = shopService.getShop(viewer.getUser().getId());
        map.put("shop", shop);
        map.put("shopCategory", shopCategoryService.getByShop(viewer.getUser().getId()));
        map.put("user", cashService.getCash(viewer.getUser().getId()));
        map.put("dataPage", dataPage);
        map.put("tab", tab);
        map.put("createTime", createTime);
        map.put("find", find);
        map.put("status", itemSearch.getStatus());
        map.put("search", itemSearch);
        map.put("category", get);
        map.put("clientScript", "var tab='" + tab + "';var categoryPath = " + gson.toJson(categoryService.getCategories(categoryIds)) + ";var itemList = " + gson.toJson(dataPage.getData()) + ";item.init('" + tab + "');");
        return "user.allitem";
    }

    @RequestMapping("/dang-ban-thanh-cong")
    public String postItemSuccess(ModelMap map, @RequestParam(value = "id", defaultValue = "") String id) {
        if (viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/item.html";
        }
        Seller byId = null;
        Item item = null;
        try {
            item = itemService.get(id);
            byId = sellerService.getById(viewer.getUser().getId());
        } catch (Exception ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        map.put("item", item);
        map.put("seller", byId);
        return "user.item.postsuccess";
    }

    @RequestMapping("/dang-ban-tuong-tu")
    public String postSameItem(ModelMap map, @RequestParam(value = "id", defaultValue = "") String id) {
        if (viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/dang-ban-tuong-tu.html?id=" + id;
        }

        User user = null;
        try {
            user = userService.get(viewer.getUser().getId());
        } catch (Exception ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Shop shop = shopService.getShop(viewer.getUser().getId());

        if (user == null || !user.isPhoneVerified() || user.getPhone() == null || user.getPhone().equals("")) {
            map.put("title", "Thông báo đăng bán");
            if (user == null) {
                map.put("message", "Tài khoản không tồn tại.");
            } else if (!user.isPhoneVerified()) {
                map.put("message", "Số điện thoại chưa xác minh, bạn cần xác minh bằng cách nhắn tin theo cú pháp <strong class=\"redfont\"></br>CDT XM " + viewer.getUser().getId() + "</strong> gửi <strong class=\"redfont\">8255</strong> (Phí 2.000đ/tin nhắn)</span>");
            } else if (user.getPhone() == null || user.getPhone().equals("")) {
                String url = baseUrl + "/user/profile.html";
                if (shop != null) {
                    url = baseUrl + "/user/cau-hinh-shop-step1.html";
                }
                map.put("message", "Bạn cần cập nhật số điện thoại để thực hiện thao tác này. Click vào <a href='" + url + "'>đây</a> để cập nhật.");
            }
            return "user.msg";
        }
        if (id.equals("")
                && ((shop == null && (user.getCityId() == null || user.getCityId().equals("") || user.getDistrictId() == null || user.getDistrictId().equals("")))
                || shop.getCityId() == null || shop.getCityId().equals("") || shop.getDistrictId() == null || shop.getDistrictId().equals(""))) {
            map.put("title", "Thông báo đăng bán");
            String url = baseUrl + "/user/profile.html";
            if (shop != null) {
                url = baseUrl + "/user/cau-hinh-shop-step1.html";
            }
            map.put("message", "Bạn cần cập nhật địa chỉ để thực hiện thao tác này. Click vào <a href='" + url + "'>đây</a> để cập nhật.");
            return "user.msg";
        }
        try {
            Item item = itemService.cloneItem(id, viewer.getUser().getId());
            if (item != null) {
                return "redirect:/user/dang-ban.html?id=" + item.getId() + "&same=1";
            }
        } catch (Exception e) {
            map.put("title", "Thông báo đăng bán");
            map.put("message", e.getMessage() + ", về <a href='" + baseUrl + "/user/item.html'>quản trị sản phẩm</a> để quản trị sản phẩm.");
            return "user.msg";
        }
        map.put("title", "Thông báo đăng bán");
        map.put("message", "Có lỗi không mong muốn xảy ra, vui lòng thử lại sau ít phút");
        return "user.msg";
    }

    @RequestMapping("/dang-ban-nhanh")
    public String postItemQuick(ModelMap map) {
        if (viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/dang-ban-nhanh.html";
        }
        Shop shop = shopService.getShop(viewer.getUser().getId());
        User user = null;
        try {
            user = userService.get(viewer.getUser().getId());
        } catch (Exception ex) {
        }
        if (user == null || !user.isPhoneVerified() || user.getPhone() == null || user.getPhone().equals("")) {
            map.put("title", "Thông báo đăng bán");
            if (user == null) {
                map.put("message", "Tài khoản không tồn tại.");
            } else if (!user.isPhoneVerified()) {
                map.put("message", "Số điện thoại chưa xác minh, bạn cần xác minh bằng cách nhắn tin theo cú pháp <strong class=\"redfont\"></br>CDT XM " + viewer.getUser().getId() + "</strong> gửi <strong class=\"redfont\">8255</strong> (Phí 2.000đ/tin nhắn)</span>");
            } else if (user.getPhone() == null || user.getPhone().equals("")) {
                String url = baseUrl + "/user/profile.html";
                if (shop != null) {
                    url = baseUrl + "/user/cau-hinh-shop-step1.html";
                }
                map.put("message", "Bạn cần cập nhật số điện thoại để thực hiện thao tác này. Click vào <a href='" + url + "'>đây</a> để cập nhật.");
            }
            return "user.msg";
        }
        if ((shop == null && (user.getCityId() == null || user.getCityId().equals("") || user.getDistrictId() == null || user.getDistrictId().equals("")))
                || (shop != null && (shop.getCityId() == null || shop.getCityId().equals("") || shop.getDistrictId() == null || shop.getDistrictId().equals("")))) {
            map.put("title", "Thông báo đăng bán");
            String url = baseUrl + "/user/profile.html";
            if (shop != null) {
                url = baseUrl + "/user/cau-hinh-shop-step1.html";
            }
            map.put("message", "Bạn cần cập nhật địa chỉ để thực hiện thao tác này. Click vào <a href='" + url + "'>đây</a> để cập nhật.");
            return "user.msg";
        }
        Seller seller = sellerService.getById(viewer.getUser().getId());
        if (!seller.isQuickSubmitItem()) {
            return "user.item.openquick";
        }
        List<ShopCategory> shopCate = shopCategoryService.getChilds(null, viewer.getUser().getId());

        map.put("shop", shop);
        map.put("user", user);
        map.put("shopCategoryCount", shopCate == null ? 0 : shopCate.size());
        map.put("clientScript", "var scates= " + gson.toJson(shopCate)
                + "; var cates = " + gson.toJson(categoryService.getChilds(null)) + "; additemquick.init();");
        return "user.item.quick";
    }

}
