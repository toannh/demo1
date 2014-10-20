package vn.chodientu.controller.user;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.SellerReview;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.PaymentStatus;
import vn.chodientu.entity.enu.ShipmentStatus;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.PropertySearch;
import vn.chodientu.entity.input.SellerReviewSearch;
import vn.chodientu.entity.input.UserAuctionSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.ItemHistogram;
import vn.chodientu.service.CashService;
import vn.chodientu.service.DistrictService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.OrderService;
import vn.chodientu.service.SellerReviewService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserAuctionService;
import vn.chodientu.service.UserService;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Controller
@RequestMapping("/user")
public class DashboardController extends BaseUser {

    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private CashService cashService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private SellerReviewService sellerReviewService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private Gson gson;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserAuctionService userAuctionService;

    @RequestMapping(value = "/{code}/ho-so-nguoi-ban.html", method = RequestMethod.GET)
    public String profile(ModelMap map, @PathVariable("code") String id) {
        User user;
        try {
            user = userService.get(id);
            Cash cash = cashService.getCash(id);
            map.put("cash", cash);
            map.put("buyerPaymentPaid", orderService.countByPaymentStatus(PaymentStatus.PAID, false, id));
            map.put("buyerPaymentNotPaid", orderService.countByPaymentStatus(PaymentStatus.NEW, false, id));
            map.put("buyerShipNEW", orderService.countByShipmentStatus(ShipmentStatus.NEW, false, id));
            map.put("sellerPaymentPaid", orderService.countByPaymentStatus(PaymentStatus.PAID, true, id));
            map.put("sellerPaymentNotPaid", orderService.countByPaymentStatus(PaymentStatus.NEW, true, id));
            map.put("sellerShipNEW", orderService.countByShipmentStatus(ShipmentStatus.NEW, true, id));
        } catch (Exception exception) {
            map.addAttribute("type", "fail");
            map.addAttribute("title", exception.getMessage());
            map.addAttribute("message", exception.getMessage() + ", về <a href='" + baseUrl + "'>trang chủ</a> để tham gia mua hàng trên ChợĐiệnTử ngay bây giờ!");
            return "user.msg";
        }
        SellerReviewSearch reviewSearch = new SellerReviewSearch();
        reviewSearch.setActive(1);
        reviewSearch.setOrderBy(1);
        reviewSearch.setPageIndex(0);
        reviewSearch.setPageSize(3);
        reviewSearch.setSellerId(id);
        DataPage<SellerReview> search = sellerReviewService.search(reviewSearch);
        List<SellerReview> reviews = search.getData();
        List<String> userIds = new ArrayList<>();
        if (reviews != null && !reviews.isEmpty()) {
            for (SellerReview sellerReview : reviews) {
                userIds.add(sellerReview.getUserId());
            }
        }
        List<User> userByIds = userService.getUserByIds(userIds);
        List<ItemHistogram> itemStatusHistogram = itemService.getItemStatusHistogram(id);

        Seller seller = sellerService.getById(id);
        if (seller != null) {
            map.put("seller", seller);
        }
        Shop shop = shopService.getShop(id);
        if (shop != null) {
            List<String> get = imageService.get(ImageType.SHOP_LOGO, shop.getUserId());
            if (get != null && !get.isEmpty()) {
                shop.setLogo(imageService.getUrl(get.get(0)).getUrl());
            }
            map.put("shop", shop);
        }
        map.put("itemStatusHistogram_all", itemStatusHistogram.get(4).getTotal() - itemStatusHistogram.get(4).getCount());
        map.put("itemStatusHistogram_selling", itemStatusHistogram.get(5).getCount());
        map.put("itemStatusHistogram_uncompleted", itemStatusHistogram.get(3).getCount());
        map.put("sellerReviewlist", search.getData());
        map.put("userByIds", userByIds);
        map.put("user", user);
        map.put("userReview", sellerReviewService.report(user.getId()));
        UserAuctionSearch auctionSearch = new UserAuctionSearch();
        auctionSearch.setUserId(id);
        Map<String, Long> countTab = userAuctionService.countTab(auctionSearch);
        map.put("countTab", countTab);
        map.put("clientScript", "sellerreview.init();");
        ItemSearch itSearch = new ItemSearch();
        itSearch.setManufacturerIds(new ArrayList<String>());
        itSearch.setModelIds(new ArrayList<String>());
        itSearch.setCityIds(new ArrayList<String>());
        itSearch.setProperties(new ArrayList<PropertySearch>());
        map.put("itemSearch", itSearch);
        if (viewer.getUser() != null && viewer.getUser().getId().equals(user.getId())) {
            return "user.dashboard";
        } else {
            return "user.productuserinfo";
        }

    }
}
