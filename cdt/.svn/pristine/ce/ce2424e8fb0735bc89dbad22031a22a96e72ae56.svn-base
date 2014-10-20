package vn.chodientu.controller.cp;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.SellerReview;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.input.SellerReviewSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemReviewService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.SellerReviewService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

@Controller("cpSellerReview")
@RequestMapping("/cp/sellerreview")
public class SellerReviewController extends BaseCp {

    @Autowired
    private SellerReviewService sellerReviewService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) throws Exception {
        SellerReviewSearch reviewSearch = new SellerReviewSearch();
        reviewSearch.setPageIndex(page - 1);
        reviewSearch.setPageSize(20);
        DataPage<SellerReview> search = sellerReviewService.search(reviewSearch);
        List<String> userIds = new ArrayList<>();
        for (SellerReview sellerReview : search.getData()) {
            if (!userIds.contains(sellerReview.getUserId())) {
                userIds.add(sellerReview.getUserId());
            }
            if (!userIds.contains(sellerReview.getSellerId())) {
                userIds.add(sellerReview.getSellerId());
            }
        }
        List<User> users = userService.getUserByIds(userIds);
        for (User user : users) {
            for (SellerReview sellerReview : search.getData()) {
                if (sellerReview.getSellerId() != null && sellerReview.getSellerId().equals(user.getId())) {
                    sellerReview.setUserNameSeller(user.getName());
                }
                if (sellerReview.getSellerId() != null &&  sellerReview.getUserId().equals(user.getId())) {
                    sellerReview.setUserNameBuyer(user.getName());
                }
            }
        }
        map.put("dataPage", search);
        map.put("clientScript", "sellerreview.init();");
        return "cp.sellerreview";
    }

}
