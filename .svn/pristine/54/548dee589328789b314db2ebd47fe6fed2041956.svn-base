package vn.chodientu.controller.service;

import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Coupon;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.CouponService;

@Controller("serviceCoupon")
@RequestMapping("/coupon")
public class CouponController extends BaseRest {

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody Coupon coupon) {
        return couponService.add(coupon);

    }

    /**
     * xu ly tao coupon code ngau nhien
     *
     * @return String code sinh ra
     */
    @RequestMapping(value = "/gencouponcode", method = RequestMethod.GET)
    @ResponseBody
    public Response genCouponCode() {
        String code = StringUtils.substring(UUID.randomUUID().toString(), 0, 6);
        return new Response(true, code);
    }
    /**
     * Xu ly update trang thai coupon cua user
     *
     * @param code
     * @param sellerId
     * @return response ket qua
     */
    @RequestMapping(value = "/updatecoupon", method = RequestMethod.GET)
    @ResponseBody
    public Response processUpdateCoupon(
            @RequestParam(value = "code") String code,
            @RequestParam(value = "sid") String sellerId) {
        return couponService.stopCouponByCode(code, sellerId);
    }
    
   

}
