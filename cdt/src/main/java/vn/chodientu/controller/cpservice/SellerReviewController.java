/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.controller.cpservice;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.ItemReview;
import vn.chodientu.entity.db.ItemReviewLike;
import vn.chodientu.entity.db.SellerReview;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemReviewService;
import vn.chodientu.service.SellerReviewService;
import vn.chodientu.service.UserService;

/**
 *
 * @author thunt
 */
@Controller("cpSellerReviewService")
@RequestMapping(value = "/cpservice/sellerreview")
public class SellerReviewController extends BaseRest {
    
    @Autowired
    private SellerReviewService sellerReviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Gson gson;

    /**
     * Sửa trạng thái đánh giá
     *
     * @param itemId
     * @param reviewId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/changestatus", method = RequestMethod.GET)
    @ResponseBody
    public Response changestatus(@RequestParam String id) throws Exception {
        SellerReview active = sellerReviewService.changeActive(id);
        return new Response(true, "Thay đổi trạng thái đánh giá thành công", active);
    }
    
}
