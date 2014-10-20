package vn.chodientu.controller.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemFollow;
import vn.chodientu.entity.db.SellerFollow;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.PropertySearch;
import vn.chodientu.entity.input.SellerFollowSearch;
import vn.chodientu.entity.input.UserItemFollowSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemFollowService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.SellerFollowService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UserService;

/**
 * @author hoaot
 */
@Controller
@RequestMapping("/user")
public class UserItemFollowController extends BaseUser {

    @Autowired
    private ItemFollowService itemFollowService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private SellerFollowService sellerFollowService;
    @Autowired
    private UserService userService;

    @RequestMapping("/quan-tam-cua-toi")
    public String itemFollow(ModelMap map, @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        if (viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/quan-tam-cua-toi.html";
        }
        if (page > 0) {
            page = page - 1;
        }
        UserItemFollowSearch search = new UserItemFollowSearch();
        search.setPageSize(15);
        search.setPageIndex(page);
        if (!keyword.equals("")) {
            search.setKeyword(keyword);
        }

        search.setUserId(viewer.getUser().getId());
        DataPage<ItemFollow> pageItemFollow = itemFollowService.search(search);
        List<String> itemIds = new ArrayList<>();
        for (ItemFollow it : pageItemFollow.getData()) {
            itemIds.add(it.getItemId());
        }
        List<Item> listItem = itemService.list(itemIds);
        for (Item item : listItem) {
            if (item.getImages() != null && !item.getImages().isEmpty()) {
                List<String> images = new ArrayList<>();
                for (String img : item.getImages()) {
                    ImageUrl url = imageService.getUrl(img);
                    if (url != null) {
                        images.add(url.getUrl());
                    }
                }
                item.setImages(images);
            }
        }
        List<Shop> shops;
        List<String> shopsId = new ArrayList<>();
        for (Item item : listItem) {
            if (item.getSellerId() != null && !"".equals(item.getSellerId()) && !shopsId.contains(item.getSellerId())) {
                shopsId.add(item.getSellerId());
            }
        }
        shops = shopService.getShops(shopsId);

        map.put("listItem", listItem);
        map.put("shops", shops);
        map.put("keyword", keyword);
        map.put("clientScript", "itemfollow.init();");
        map.put("pageItemFollow", pageItemFollow);
        map.put("timeDateNow", System.currentTimeMillis());
        return "user.itemfollow";
    }

    @RequestMapping("/quan-tam-nguoi-ban")
    public String sellerFollow(ModelMap map, @RequestParam(value = "page", defaultValue = "0") int page) {
        if (viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/quan-tam-nguoi-ban.html";
        }
        if (page > 0) {
            page = page - 1;
        }
        SellerFollowSearch search = new SellerFollowSearch();
        search.setPageSize(15);
        search.setPageIndex(page);
        search.setUserId(viewer.getUser().getId());
        DataPage<SellerFollow> pageSellerFollow = sellerFollowService.search(search);
        List<String> sellerIds = new ArrayList<>();
        for (SellerFollow sl : pageSellerFollow.getData()) {
            sellerIds.add(sl.getSellerId());
        }
        List<User> listSeller = userService.getUserByIds(sellerIds);
        for (User u : listSeller) {
            List<String> images = null;
            images = imageService.get(ImageType.AVATAR, u.getId());
            if (images != null && !images.isEmpty()) {
                u.setAvatar(imageService.getUrl(images.get(0)).thumbnail(68, 77, "outbound").compress(100).getUrl());
            }
        }

        List<Shop> shops;
        List<String> shopsId = new ArrayList<>();
        for (User u : listSeller) {
            if (u.getId() != null && !"".equals(u.getId()) && !shopsId.contains(u.getId())) {
                shopsId.add(u.getId());
            }
        }
        shops = shopService.getShops(shopsId);

        ItemSearch itSearch = new ItemSearch();
        itSearch.setManufacturerIds(new ArrayList<String>());
        itSearch.setModelIds(new ArrayList<String>());
        itSearch.setCityIds(new ArrayList<String>());
        itSearch.setProperties(new ArrayList<PropertySearch>());
        map.put("itemSearch", itSearch);
        
        map.put("listSeller", listSeller);
        map.put("shops", shops);
        map.put("pageSellerFollow", pageSellerFollow);
        return "user.sellerfollow";
    }

}
