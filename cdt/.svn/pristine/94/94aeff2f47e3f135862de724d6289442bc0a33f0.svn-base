package vn.chodientu.controller.user;

import com.google.gson.Gson;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.component.ScClient;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.District;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.db.ShopContact;
import vn.chodientu.entity.db.ShopNewsCategory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.repository.SellerRepository;
import vn.chodientu.service.CashService;
import vn.chodientu.service.CityService;
import vn.chodientu.service.DistrictService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopCategoryService;
import vn.chodientu.service.ShopContactService;
import vn.chodientu.service.ShopNewsCategoryService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

@Controller
@RequestMapping("/user")
public class ShopController extends BaseUser {

    @Autowired
    private ShopService shopService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private ShopCategoryService categoryService;
    @Autowired
    private ShopContactService contactService;
    @Autowired
    ShopNewsCategoryService shopNewsCategoryService;
    @Autowired
    private Gson json;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CityService cityService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private ScClient scClient;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private CashService cashService;

    /**
     * Cấu hình shop bước 1
     *
     * @param map
     * @return
     */
    @RequestMapping("/cau-hinh-shop-step1.html")
    public String configshopStepOne(ModelMap map) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/cau-hinh-shop-step1.html";
        }
        Shop shop = shopService.getShop(viewer.getUser().getId());
        if (shop == null) {
            return "redirect:/user/open-shop-step1.html";
        }
        List<City> cities = cityService.list();
        List<District> districts = districtService.list();
        List<ShopContact> listShopcontact = contactService.getContactById(viewer.getUser().getId());
        User user = viewer.getUser();
        map.put("contacts", listShopcontact);
        map.put("shop", shop);
        map.put("clientScript", " var citys = " + json.toJson(cities) + "; var districts=" + json.toJson(districts) + " ;shop.initStepOne('" + shop.getCityId() + "', '" + shop.getDistrictId() + "');");
        return "user.configshop.stepone";
    }

    /**
     * Cấu hình shop bước 2
     *
     * @param map
     * @return
     */
    @RequestMapping("/cau-hinh-shop-step2.html")
    public String configshopStepTwo(ModelMap map) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/cau-hinh-shop-step2.html";
        }
        // get shop detail
        Shop shop = shopService.getShop(viewer.getUser().getId());
        if (shop == null) {
            return "redirect:/user/cau-hinh-shop-step1.html";
        } else {
            List<String> getImg = null;
            // shop id is user id too
            getImg = imageService.get(ImageType.SHOP_LOGO, viewer.getUser().getId());
            if (getImg != null && !getImg.isEmpty()) {
                shop.setLogo(imageService.getUrl(getImg.get(0)).getUrl());
            }
            map.put("shop", shop);
        }
        return "user.configshop.steptwo";
    }

    /**
     * Cấu hình shop bước 3
     *
     * @param map
     * @return
     */
    @RequestMapping("/cau-hinh-shop-step5.html")
    public String configshopStepThree(ModelMap map) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/cau-hinh-shop-step5.html";
        }
        Shop shop = shopService.getShop(viewer.getUser().getId());
        if (shop == null) {
            return "redirect:/user/cau-hinh-shop-step1.html";
        }
        List<ShopCategory> listCat = categoryService.getByShop(shop.getUserId());

        map.put("catShop", listCat);
        map.put("shop", shop);
        map.put("clientScript", "var listCat = " + json.toJson(listCat) + ";");
        return "user.configshop.stepfive";
    }

    /**
     * Cấu hình shop bước 4
     *
     * @param map
     * @return
     */
    @RequestMapping("/cau-hinh-shop-step6.html")
    public String configshopStepFour(ModelMap map) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/cau-hinh-shop-step6.html";
        }
        Shop shop = shopService.getShop(viewer.getUser().getId());
        if (shop == null) {
            return "redirect:/user/cau-hinh-shop-step1.html";
        }
        // get news category
        List<ShopNewsCategory> newsCategory = shopNewsCategoryService.getByShop(shop.getUserId());
        map.put("newsCategory", newsCategory);
        map.put("clientScript", "var listNewsCat = " + json.toJson(newsCategory) + ";");
        map.put("shop", shop);

        return "user.configshop.stepsix";
    }

    /**
     * Mở shop bước 1
     *
     * @param map
     * @return
     */
    @RequestMapping("/open-shop-step1")
    public String openShopStepOne(ModelMap map) {
        if (viewer != null && viewer.getUser() != null) {

        }
        return "user.openshopstep1";
    }

    /**
     * Mở shop bước 2
     *
     * @param map
     * @return
     */
    @RequestMapping("/open-shop-step2")
    public String openShopStepTwo(ModelMap map) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/open-shop-step1.html";
        }
        map.put("clientScript", "shop.initOpenStepTwo();");
        try {
            Shop shop = shopService.getShop(viewer.getUser().getId());
            if (shop != null) {
                return "redirect:/user/cau-hinh-shop-step1.html";
            }
            User user = (User) userService.getById(viewer.getUser().getId()).getData();
            if (user.getPhone() != null && !user.getPhone().equals("") && user.isPhoneVerified()) {
                map.put("clientScript", "textUtils.redirect('/user/open-shop-step3.html', 30000);");
            }

        } catch (Exception e) {
            map.put("type", "fail");
            map.put("title", "Thông báo mở shop");
            map.put("message", "Không tìm thấy tài khoản yêu cầu, <a href='" + baseUrl + "'>trang chủ</a> ngay bây giờ.");
            map.put("clientScript", "textUtils.redirect('/index.html', 10000);");
            return "user.msg";
        }

        map.put("user", viewer.getUser());
        return "user.openshopstep2";
    }

    /**
     * Mở shop bước 3
     *
     * @param merchant_id
     * @param email
     * @param cdt_id
     * @param type
     * @param itype
     * @param code
     * @param checksum
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/open-shop-step3")
    public String openShopStepThree(@RequestParam(value = "merchant_id", defaultValue = "", required = false) String merchant_id,
            @RequestParam(value = "email", defaultValue = "", required = false) String email,
            @RequestParam(value = "cdt_id", defaultValue = "", required = false) String cdt_id,
            @RequestParam(value = "type", defaultValue = "", required = false) String type,
            @RequestParam(value = "itype", defaultValue = "nl", required = false) String itype,
            @RequestParam(value = "code", defaultValue = "", required = false) String code,
            @RequestParam(value = "checksum", defaultValue = "-1", required = false) String checksum, ModelMap map) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/open-shop-step1.html";
        }
        map.put("clientScript", "shop.initOpenStepThree();");
        if (viewer.getUser().getPhone() == null || viewer.getUser().getPhone().equals("") || !viewer.getUser().isPhoneVerified()) {
            return "redirect:/user/open-shop-step2.html";
        }
        Shop shop = shopService.getShop(viewer.getUser().getId());
        if (shop != null) {
            map.put("type", "success");
            map.put("title", "Thông báo mở shop");
            map.put("message", "Bạn đã mở shop trên hệ thống, về <a href='" + baseUrl + "/user/cau-hinh-shop-step1.html'>cấu hình shop</a> ngay bây giờ.");
            map.put("clientScript", "textUtils.redirect('/user/cau-hinh-shop-step1.html', 30000);");
            return "user.msg";
        }
        String chcksumConfirm = DigestUtils.md5Hex(merchant_id + "df19aa6d60" + email + cdt_id + type);
        Seller seller = null;
        try {
            seller = sellerService.getById(viewer.getUser().getId());
            if (seller.getNlEmail() != null && !seller.getNlEmail().equals("")
                    && seller.getScEmail() != null && !seller.getScEmail().equals("")) {
                map.put("clientScript", "textUtils.redirect('/user/openshop-finish.html', 30000);");
            }
        } catch (Exception ex) {
            seller = sellerService.createSeller(viewer.getUser());
        }
        if (!code.equals("")) {
            try {
                scClient.signin(code, seller);
                sellerRepository.save(seller);
                sellerService.processUpdateCodPaymentItem(viewer.getUser().getId(), true);
                try {
                    cashService.reward(CashTransactionType.INTEGRATED_COD, seller.getUserId(), seller.getUserId(), "/user/" + viewer.getUser().getId() + "/ho-so-nguoi-ban.html", null);
                } catch (Exception e) {
                }
                return "redirect:/user/cau-hinh-tich-hop.html";
            } catch (Exception e) {
            }
        }
        if (checksum.equals(chcksumConfirm)) {
            if (itype.equals("nl")) {
                seller.setNlEmail(email);
                seller.setNlIntegrated(true);
                seller.setNlId(cdt_id);
                seller.setUserId(viewer.getUser().getId());
                sellerService.update(seller);
                sellerService.processUpdateOnlinePaymentItem(viewer.getUser().getId(), true);
                try {
                    cashService.reward(CashTransactionType.INTEGRATED_NL, seller.getUserId(), seller.getUserId(), "/user/" + viewer.getUser().getId() + "/ho-so-nguoi-ban.html", null);
                } catch (Exception e) {
                }
            } else {
                seller.setScEmail(email);
                seller.setScIntegrated(true);
                seller.setScId(cdt_id);
                seller.setUserId(viewer.getUser().getId());
                sellerService.update(seller);
                sellerService.processUpdateCodPaymentItem(viewer.getUser().getId(), true);
            }
            if (seller.getNlEmail() != null && !seller.getNlEmail().equals("")
                    && seller.getScEmail() != null && !seller.getScEmail().equals("")) {
                map.put("clientScript", "textUtils.redirect('/user/openshop-finish.html', 30000);");
            }
        }
        map.put("seller", seller);
        return "user.openshopstep3";
    }

    /**
     * Kết thúc mở shop
     *
     * @param map
     * @return
     */
    @RequestMapping("/openshop-finish")
    public String openShopFinish(ModelMap map) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/open-shop-step1.html";
        }
        if (viewer.getUser().getPhone() == null || viewer.getUser().getPhone().equals("") || !viewer.getUser().isPhoneVerified()) {
            return "redirect:/user/open-shop-step2.html";
        }
        Seller seller = null;
        try {
            seller = sellerService.getById(viewer.getUser().getId());
            if (seller.getNlEmail() == null || seller.getNlEmail().equals("")
                    || seller.getScEmail() == null || seller.getScEmail().equals("") || !seller.isNlIntegrated() || !seller.isScIntegrated()) {
                return "redirect:/user/open-shop-step3.html";
            }
        } catch (Exception ex) {
            seller = sellerService.createSeller(viewer.getUser());
            return "redirect:/user/open-shop-step3.html";
        }

        map.put("shop", shopService.createShop(viewer.getUser()));
        map.put("clientScript", "shop.FinishOpen();");
        return "user.openshopfinish";
    }

}
