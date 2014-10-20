package vn.chodientu.controller.user;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.ShopBanner;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ShopBannerType;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopBannerService;
import vn.chodientu.service.ShopCategoryService;
import vn.chodientu.service.ShopContactService;
import vn.chodientu.service.ShopNewsCategoryService;
import vn.chodientu.service.UserService;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Controller
@RequestMapping("/user")
public class ShopBannerController extends BaseUser {

    @Autowired
    private ShopBannerService shopBannerService;
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

    /**
     * 
     *
     * @param map
     * @return
     */
    @RequestMapping("/shop-banner.html")
    public String shopbanner(ModelMap map,@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/shop-banner.html";
        }
        List<ShopBanner> shopBanners = shopBannerService.getAll(ShopBannerType.HEART,viewer.getUser().getId());
        List<String> ids = new ArrayList<>();
        if (shopBanners != null && shopBanners.size() > 0) {
            for (ShopBanner shopBanner : shopBanners) {
                ids.add(shopBanner.getId());
            }
        }
        if(ids!=null && ids.size()>0){
            Map<String, List<String>> image = imageService.get(ImageType.SHOP_BANNER, ids);
            for (Map.Entry<String, List<String>> entry : image.entrySet()) {
                String shopBannerId = entry.getKey();
                List<String> shopBannerImage = entry.getValue();
                for (ShopBanner banner : shopBanners) {
                    if(banner.getId().equals(shopBannerId) && shopBannerImage!=null && shopBannerImage.size()>0){
                        banner.setImage(imageService.getUrl(shopBannerImage.get(0)).thumbnail(298, 135, "outbound").getUrl());
                    }
                }
            }
            
        }
        if (!id.trim().equals("")) {
            ShopBanner shopBanner = shopBannerService.getById(id);
            List<String> get = imageService.get(ImageType.SHOP_BANNER, id);
            if(get!=null && get.size()>0){
                shopBanner.setImage(imageService.getUrl(get.get(0)).thumbnail(298, 135, "outbound").getUrl());
            }
            if(shopBanner!=null){
                map.put("shopBannerRow", shopBanner);
            }
        }

        map.put("shopBanners", shopBanners);
        return "user.shopbanner.heartbanner";
    }
    /**
     * 
     *
     * @param map
     * @return
     */
    @RequestMapping("/shop-banner-ads.html")
    public String shopbannerads(ModelMap map,@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/shop-banner-ads.html";
        }      
        List<ShopBanner> shopBanners = shopBannerService.getAll(ShopBannerType.ADV_RIGHT,viewer.getUser().getId());
        List<String> ids = new ArrayList<>();
        if (shopBanners != null && shopBanners.size() > 0) {
            for (ShopBanner shopBanner : shopBanners) {
                ids.add(shopBanner.getId());
            }
        }
        if(ids!=null && ids.size()>0){
            Map<String, List<String>> image = imageService.get(ImageType.SHOP_BANNER, ids);
            for (Map.Entry<String, List<String>> entry : image.entrySet()) {
                String shopBannerId = entry.getKey();
                List<String> shopBannerImage = entry.getValue();
                for (ShopBanner banner : shopBanners) {
                    if(banner.getId().equals(shopBannerId) && shopBannerImage!=null && shopBannerImage.size()>0){
                        banner.setImage(imageService.getUrl(shopBannerImage.get(0)).thumbnail(298, 135, "outbound").getUrl());
                    }
                }
            }
            
        }
        if (!id.trim().equals("")) {
            ShopBanner shopBanner = shopBannerService.getById(id);
            List<String> get = imageService.get(ImageType.SHOP_BANNER, id);
            if(get!=null && get.size()>0){
                shopBanner.setImage(imageService.getUrl(get.get(0)).thumbnail(298, 135, "outbound").getUrl());
            }
            if(shopBanner!=null){
                map.put("shopBannerRow", shopBanner);
            }
        }

        map.put("shopBanners", shopBanners);
       
        return "user.shopbanner.bannerads";
    }
       

}
