package vn.chodientu.controller.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemReview;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ItemWarningType;
import vn.chodientu.entity.input.ItemReviewSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemFollowService;
import vn.chodientu.service.ItemReviewService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.ItemWarningService;
import vn.chodientu.service.UserService;

@Controller("serviceReviewItem")
@RequestMapping("/itemreview")
public class ItemReviewController extends BaseRest {

    @Autowired
    private ItemReviewService itemlReviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ItemFollowService itemFollowService;
    @Autowired
    private ItemWarningService itemWarningService;

    /**
     * Comment đánh giá về một sản phẩm
     *
     * @param itemReview
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/commentitem", method = RequestMethod.POST)
    @ResponseBody
    public Response commentItem(@RequestBody ItemReview itemReview) throws Exception {
        return itemlReviewService.add(itemReview, request);
    }

    /**
     * Like comment đánh giá
     *
     * @param commentId
     * @param itemId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/likecommnet", method = RequestMethod.GET)
    @ResponseBody
    public Response likeCommentItem(@RequestParam String commentId, @RequestParam String itemId) throws Exception {
        ItemReview like = itemlReviewService.like(commentId, itemId, request);
        return new Response(true, "OK", like);
    }

    /**
     * Lấy danh sách bình luận đánh giá của sản phẩm
     *
     * @param itemReviewSearch
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getitemreview", method = RequestMethod.POST)
    @ResponseBody
    public Response getItemReview(@RequestBody ItemReviewSearch search) throws Exception {
        List<String> userIds = new ArrayList<>();
        DataPage<ItemReview> reviewItems = itemlReviewService.search(search);
        if (reviewItems.getData() != null && !reviewItems.getData().isEmpty()) {
            for (ItemReview review : reviewItems.getData()) {
                if (review.getUserId() != null && !userIds.contains(review.getUserId())) {
                    userIds.add(review.getUserId());
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        List<User> userByIds = userService.getUserByIds(userIds);
        if (userByIds != null && !userByIds.isEmpty()) {
            for (User user : userByIds) {
                List<String> getImg = null;
                getImg = imageService.get(ImageType.AVATAR, user.getId());
                if (getImg != null && !getImg.isEmpty()) {
                    user.setAvatar(imageService.getUrl(getImg.get(0)).thumbnail(70, 65, "inset").getUrl());
                }
            }
        }
        map.put("reviewers", userByIds);
        map.put("itemReviews", reviewItems);
        return new Response(true, "Chi tiết item", map);
    }

    /**
     * Kiểm tra xem người dùng đã đánh giá sản phẩm này hay chưa?
     *
     * @param idItem
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkcommentbyuser", method = RequestMethod.GET)
    @ResponseBody
    public Response checkCommentByUser(@RequestParam String idItem) throws Exception {
        return new Response(itemlReviewService.exitsComment(idItem));
    }

    @RequestMapping(value = "/loadinforeviewitem", method = RequestMethod.GET)
    @ResponseBody
    public Response loadInfoReviewItem(@RequestParam String itemId) throws Exception {
        HashMap<String, Long> info = itemlReviewService.info(itemId);
        return new Response(true, "Info review item", info);
    }

    @RequestMapping(value = "/interestitem", method = RequestMethod.GET)
    @ResponseBody
    public Response interestItem(@RequestParam(value = "itemId", defaultValue = "") String itemId) throws Exception {
        return new Response(true, "OK", itemFollowService.action(itemId, request));
    }

    @RequestMapping(value = "/warningoutofstock", method = RequestMethod.GET)
    @ResponseBody
    public Response warningOutOfStock(@RequestParam ItemWarningType warningtype, @RequestParam(value = "itemId", defaultValue = "") String itemId) throws Exception {
        itemWarningService.warning(warningtype, itemId, request);
        return new Response(true, "OK");
    }

    @RequestMapping(value = "/itemreviewsearchs", method = RequestMethod.GET)
    @ResponseBody
    public Response itemreviewsearchs(@ModelAttribute ItemReviewSearch reviewSearch) throws Exception {
        reviewSearch.setPageIndex(reviewSearch.getPageIndex() > 0 ? reviewSearch.getPageIndex() - 1 : 0);
        List<String> itemIds = new ArrayList<>();
        if (reviewSearch.isReviewOther()) {
            List<Item> items = itemService.getBSeller(reviewSearch.getUserId());
            if (items != null && !items.isEmpty()) {
                for (Item item : items) {
                    itemIds.add(item.getId());
                }
            }
            reviewSearch.setUserId(null);
        }
        DataPage<ItemReview> search = itemlReviewService.search(reviewSearch);
        List<String> itemids = new ArrayList<>();
        for (ItemReview review : search.getData()) {
            itemids.add(review.getItemId());
        }
        List<Item> items = itemService.list(itemids);
        Map<String, Object> map = new HashMap<>();
        map.put("reviewPage", search);
        map.put("items", items);
        return new Response(true, null, map);
    }

}
