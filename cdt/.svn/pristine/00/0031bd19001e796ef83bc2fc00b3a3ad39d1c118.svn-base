package vn.chodientu.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.entity.db.Cash;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.db.VipItem;
import vn.chodientu.entity.input.VipItemSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.CashService;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.VipItemService;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Controller
@RequestMapping("/user")
public class VipItemController extends BaseUser {

    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CashService cashService;
    @Autowired
    private VipItemService vipItemService;

    @RequestMapping(value = {"/vipitem"}, method = RequestMethod.GET)
    public String posting(ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session,
            @RequestParam(value = "postId", defaultValue = "") String postId,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        User user = viewer.getUser();
        if (user == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/vipitem.html";
        }
        VipItemSearch vipItemSearch = new VipItemSearch();
        vipItemSearch.setPageIndex(page - 1);
        vipItemSearch.setPageSize(20);
        vipItemSearch.setUserId(viewer.getUser().getId());
        DataPage<VipItem> search = vipItemService.search(vipItemSearch, false);
        List<String> itemIds = new ArrayList<>();
        List<String> cateIds = new ArrayList<>();
        for (VipItem vipItem : search.getData()) {
            itemIds.add(vipItem.getItemId());
            for (String cateId : vipItem.getCategoryPath()) {
                if (!cateIds.contains(cateId)) {
                    cateIds.add(cateId);
                }
            }

        }
        List<Category> categorys = new ArrayList<>();
        try {
            categorys = categoryService.get(cateIds);
        } catch (Exception e) {
        }

        List<Item> items = itemService.list(itemIds);
        for (Item item : items) {
            try {
                if (item.getImages() != null && !item.getImages().isEmpty()) {
                    ImageUrl url = imageService.getUrl(item.getImages().get(0));
                    item.setImages(new ArrayList<String>());
                    item.getImages().add(url.thumbnail(100, 100, "outbound").getUrl());
                }
            } catch (Exception e) {
            }
        }
        model.put("items", items);
        model.put("categorys", categorys);
        model.put("dataPage", search);
        model.put("vipItemSearch", vipItemSearch);
        Cash cash = new Cash();
        try {
            cash = cashService.getCash(viewer.getUser().getId());
        } catch (Exception e) {
        }
        model.put("user", cash);

        return "user.vipitem";
    }
}
