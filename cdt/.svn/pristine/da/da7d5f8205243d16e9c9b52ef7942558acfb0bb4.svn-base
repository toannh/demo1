package vn.chodientu.controller.user;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Coupon;
import vn.chodientu.entity.enu.PromotionType;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CouponService;
import vn.chodientu.service.PromotionService;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Controller
@RequestMapping("/user")
public class CouponController extends BaseUser {

    @Autowired
    private PromotionService promotionService;
    @Autowired
    private CouponService couponService;

    @RequestMapping("/coupon")
    public String coupon(ModelMap map,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "status", defaultValue = "0") int status) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/coupon.html";
        }
        DataPage<Coupon> data = couponService.search(viewer.getUser().getId(), keyword, status, page - 1, 50);
        map.put("keyword", keyword);
        map.put("status", status);
        map.put("dataPage", data);
        map.put("clientScript", "coupon.init();coupon.genCouponCode();");
        return "user.createcoupon";
    }

}
