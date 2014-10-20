package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemReview;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.input.ItemReviewSearch;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemReviewService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

@Controller("cpItemReview")
@RequestMapping("/cp/itemreview")
public class ItemReviewController extends BaseCp {
    
    @Autowired
    private ItemReviewService itemReviewService;
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
        ItemReviewSearch reviewSearch = new ItemReviewSearch();
        if (session.getAttribute("reviewSearch") != null && page != 0) {
            reviewSearch = (ItemReviewSearch) session.getAttribute("reviewSearch");
        } else {
            session.setAttribute("reviewSearch", reviewSearch);
        }
        reviewSearch.setPageIndex(page - 1);
        reviewSearch.setPageSize(20);
        DataPage<ItemReview> dataPage = itemReviewService.search(reviewSearch);
        List<ItemReview> itemReviews = dataPage.getData();
        List<String> itemIds = new ArrayList<>();
        List<String> userIds = new ArrayList<>();
        List<String> sellerIds = new ArrayList<>();
        if (itemReviews != null && !itemReviews.isEmpty()) {
            for (ItemReview itemReview : itemReviews) {
                itemIds.add(itemReview.getItemId());
                userIds.add(itemReview.getUserId());
            }
        }
        List<Item> items = itemService.list(itemIds);
        Map<String, List<String>> get = imageService.get(ImageType.ITEM, itemIds);
        if (get != null && !get.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : get.entrySet()) {
                String key = entry.getKey();
                List<String> images = entry.getValue();
                for (Item item : items) {
                    sellerIds.add(item.getSellerId());
                    if (item.getId().equals(key) && images != null && !images.isEmpty()) {
                        item.setImages(new ArrayList<String>());
                        item.getImages().add(imageService.getUrl(images.get(0)).getUrl());
                    }
                }
            }
        }
        
        List<User> userByIds = userService.getUserByIds(userIds);
         if(userByIds!=null && !userByIds.isEmpty()){
            for (User user : userByIds) {
                for (ItemReview itemReview : itemReviews) {
                    if (itemReview.getUserId().equals(user.getId())) {
                        itemReview.setUser(user);
                    }
                }
            }
        }
        if (items != null && !items.isEmpty()) {
            for (Item item : items) {
                for (ItemReview itemReview : itemReviews) {
                    if (itemReview.getItemId().equals(item.getId())) {
                        itemReview.setItem(item);
                    }
                }
            }
        }
        map.put("dataPage", dataPage);
        map.put("clientScript", "itemreview.init();");
        return "cp.itemreview";
    }
    
}
