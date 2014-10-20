package vn.chodientu.controller.user;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.UserAuction;
import vn.chodientu.entity.enu.Condition;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.UserAuctionSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.AuctionService;
import vn.chodientu.service.CashService;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserAuctionService;

/**
 * @author thunt
 */
@Controller
@RequestMapping("/user")
public class UserAuctionController extends BaseUser {

    @Autowired
    private UserAuctionService userAuctionService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ShopService shopService;

    @RequestMapping("/theo-doi-dau-gia")
    public String postItem(ModelMap map,
            @RequestParam(value = "tab", defaultValue = "all") String tab,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/theo-doi-dau-gia.html";
        }
        if(page > 0){
            page = page - 1;
        }
        UserAuctionSearch auctionSearch = new UserAuctionSearch();
        auctionSearch.setPageSize(15);
        auctionSearch.setPageIndex(page);
        auctionSearch.setTab(tab);
        if(keyword.matches("/^[0-9]+$/")){
            auctionSearch.setId(keyword);
        }else{
            auctionSearch.setKeyword(keyword);
        }
        auctionSearch.setUserId(viewer.getUser().getId());
        DataPage<UserAuction> search = userAuctionService.search(auctionSearch);
        List<String> itemIds = new ArrayList<>();
        for (UserAuction item : search.getData()) {
            itemIds.add(item.getItemId());
        }
        List<Item> listItems = itemService.list(itemIds);
        for (Item item : listItems) {
            if(item.getImages() != null && !item.getImages().isEmpty()){
                List<String> images = new ArrayList<>();
                for (String img : item.getImages()) {
                    ImageUrl url = imageService.getUrl(img);
                    if(url != null){
                        images.add(url.getUrl());
                    }
                }
                item.setImages(images);
            }
        }
        
        List<Shop> shops;
        List<String> shopsId = new ArrayList<>();
        for (Item item : listItems) {
            if (item.getSellerId() != null && !"".equals(item.getSellerId()) && !shopsId.contains(item.getSellerId())) {
                shopsId.add(item.getSellerId());
            }
        }
        shops = shopService.getShops(shopsId);
        Map<String, Long> countTab = userAuctionService.countTab(auctionSearch);
        
        map.put("userAuctionItemPage", search);
        map.put("items", listItems);
        map.put("shops", shops);
        map.put("countTab", countTab);
        map.put("auctionSearch", auctionSearch);
        map.put("clientScript", "auction.init();");
        return "user.follow.auction";
    }

}
