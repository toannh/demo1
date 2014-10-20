package vn.chodientu.controller.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.user.BaseUser;
import vn.chodientu.entity.db.SellerReview;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.SellerReviewService;

/**
 * @author Phuc
 */
@Controller("serviceSellerReview")
@RequestMapping("/sellerreview")
public class SellerReviewController extends BaseUser {

    @Autowired
    private SellerReviewService sellerReviewService;
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    @ResponseBody
    public Response review(@RequestBody SellerReview review, HttpServletRequest request) {
        try {
            SellerReview rv = sellerReviewService.review(review.getOrderId(), review.getContent(), review.getPoint(), request);
            return new Response(true, "Đánh giá thành công", rv);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/loadinforeviewseller", method = RequestMethod.GET)
    @ResponseBody
    public Response loadInfoReviewSeller(@RequestParam String sellerId) throws Exception {
        HashMap<String, Long> info = sellerReviewService.report(sellerId);
        return new Response(true, "Info review item", info);
    }

    @RequestMapping(value = "/getbyorderids", method = RequestMethod.POST)
    @ResponseBody
    public Response getbyorderids(@RequestBody List<String> ids, @RequestParam String userId, @RequestParam boolean seller) throws Exception {
        List<SellerReview> byOrderIds = sellerReviewService.getByOrderIds(ids, userId, seller);
        return new Response(true, null, byOrderIds);
    }

}
