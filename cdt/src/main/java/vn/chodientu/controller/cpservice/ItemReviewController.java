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
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemReviewService;
import vn.chodientu.service.UserService;

/**
 *
 * @author thunt
 */
@Controller("cpItemReviewService")
@RequestMapping(value = "/cpservice/itemreview")
public class ItemReviewController extends BaseRest {
    
    @Autowired
    private ItemReviewService itemReviewService;
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
    public Response changestatus(@RequestParam String itemId, @RequestParam String reviewId) throws Exception {
        ItemReview active = itemReviewService.changeActive(itemId, reviewId);
        return new Response(true, "Thay đổi trạng thái đánh giá thành công", active);
    }
    
    @RequestMapping(value = "/getitemreviewlike", method = RequestMethod.GET)
    @ResponseBody
    public Response getitemreviewlike(@RequestParam String reviewId) throws Exception {
        List<ItemReviewLike> listItemReviewLike = itemReviewService.listItemReviewLike(reviewId);
        List<String> userIds = new ArrayList<>();
        if (listItemReviewLike != null && !listItemReviewLike.isEmpty()) {
            for (ItemReviewLike itemReviewLike : listItemReviewLike) {
                userIds.add(itemReviewLike.getUserId());
            }
        }
        List<User> userByIds = userService.getUserByIds(userIds);
        if (userByIds != null && !userByIds.isEmpty()) {
            for (User user : userByIds) {
                for (ItemReviewLike itemReviewLike : listItemReviewLike) {
                    if (user.getId().equals(itemReviewLike.getUserId())) {
                        itemReviewLike.setUserName(user.getUsername());
                    }
                }
            }
        }
        return new Response(true, null, listItemReviewLike);
    }
    
}
