package vn.chodientu.controller.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ShopHomeItem;
import vn.chodientu.entity.input.ShopHomeItemSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.ShopHomeItemService;

/**
 * @author Phuc
 */
@Controller
@RequestMapping("/user")
public class ShopHomeItemController extends BaseUser {

    @Autowired
    private ShopHomeItemService shopHomeItemService;
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/shop-home-item", method = RequestMethod.GET)
    public String shopHomeItem(ModelMap model, @RequestParam(value = "page", defaultValue = "1") int page) {
        if (viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/shop-home-item.html";
        }
        ShopHomeItemSearch itemSearch = new ShopHomeItemSearch();
        itemSearch.setSellerId(viewer.getUser().getId());
        itemSearch.setPageIndex(page - 1);
        itemSearch.setPageSize(100);
        DataPage<ShopHomeItem> dataPage = shopHomeItemService.search(itemSearch);

        model.put("clientScript", "shophomeitem.init();");
        model.put("items", dataPage.getData());
        return "user.shophomeitem";
    }
    
    
    
    
}
