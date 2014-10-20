package vn.chodientu.controller.user;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.ShopNews;
import vn.chodientu.entity.db.ShopNewsCategory;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.input.ShopNewsSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ShopNewsCategoryService;
import vn.chodientu.service.ShopNewsService;

/**
 * @since May 20, 2014
 * @author TheHoa
 */
@Controller
@RequestMapping("/user")
public class ShopNewsController extends BaseUser {

    @Autowired
    private ShopNewsService shopNewsService;
    @Autowired
    private ShopNewsCategoryService shopNewsCategoryService;
    @Autowired
    private Gson json;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/shop-news.html", method = RequestMethod.GET)
    public String seachShopNews(ModelMap map, @RequestParam(value = "page", defaultValue = "0") int page, HttpSession session) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/shop-news.html";
        }
        ShopNewsSearch newssearch = new ShopNewsSearch();
        if (session.getAttribute("newssearch") != null && page != 0) {
            newssearch = (ShopNewsSearch) session.getAttribute("newssearch");
        } else {
            session.setAttribute("newssearch", newssearch);
        }
        newssearch.setUserId(viewer.getUser().getId());
        if (page > 0) {
            newssearch.setPageIndex(page - 1);
        } else {
            newssearch.setPageIndex(0);
        }
        newssearch.setPageSize(20);
        DataPage<ShopNews> pageShopNews = shopNewsService.search(newssearch);
        List<String> ids = new ArrayList<>();
        List<String> catIds = new ArrayList<>();
        if (pageShopNews.getData() != null && pageShopNews.getData().size() > 0) {
            for (ShopNews shopnews : pageShopNews.getData()) {
                ids.add(shopnews.getId());
                catIds.add(shopnews.getCategoryId());
            }
        }
        if (ids != null && ids.size() > 0) {
            Map<String, List<String>> image = imageService.get(ImageType.SHOP_NEWS, ids);
            for (Map.Entry<String, List<String>> entry : image.entrySet()) {
                String shopNewsId = entry.getKey();
                List<String> shopNewsImage = entry.getValue();
                for (ShopNews shopnews : pageShopNews.getData()) {
                    if (shopnews.getId().equals(shopNewsId) && shopNewsImage != null && shopNewsImage.size() > 0) {
                        shopnews.setImage(imageService.getUrl(shopNewsImage.get(0)).thumbnail(50, 50, "outbound").getUrl());
                    }
                }
            }

        }

        List<ShopNewsCategory> newscategorybyShop = shopNewsCategoryService.getByShop(viewer.getUser().getId());
        List<ShopNewsCategory> listCat = shopNewsCategoryService.getByIds(catIds);
        map.put("listCat", listCat);
        map.put("newssearch", newssearch);
        map.put("newspage", pageShopNews);
        map.put("newscategory", newscategorybyShop);
        map.put("clientScript", "shopnews.init();");
        return "user.shoplistnews";
    }

    @RequestMapping(value = "/shop-news.html", method = RequestMethod.POST)
    public String seachShopNews(ModelMap map, @ModelAttribute ShopNewsSearch newssearch, HttpSession session) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/shop-news.html";
        }
        newssearch.setUserId(viewer.getUser().getId());
        newssearch.setPageIndex(0);
        newssearch.setPageSize(20);
        session.setAttribute("newssearch", newssearch);
        DataPage<ShopNews> pageShopNews = shopNewsService.search(newssearch);
        List<String> ids = new ArrayList<>();
        List<String> catIds = new ArrayList<>();
        if (pageShopNews.getData() != null && pageShopNews.getData().size() > 0) {
            for (ShopNews shopnews : pageShopNews.getData()) {
                ids.add(shopnews.getId());
                catIds.add(shopnews.getCategoryId());
            }
        }
        if (ids != null && ids.size() > 0) {
            Map<String, List<String>> image = imageService.get(ImageType.SHOP_NEWS, ids);
            for (Map.Entry<String, List<String>> entry : image.entrySet()) {
                String shopNewsId = entry.getKey();
                List<String> shopNewsImage = entry.getValue();
                for (ShopNews shopnews : pageShopNews.getData()) {
                    if (shopnews.getId().equals(shopNewsId) && shopNewsImage != null && shopNewsImage.size() > 0) {
                        shopnews.setImage(imageService.getUrl(shopNewsImage.get(0)).thumbnail(50, 50, "outbound").getUrl());
                    }
                }
            }

        }

        List<ShopNewsCategory> newscategorybyShop = shopNewsCategoryService.getByShop(viewer.getUser().getId());
        List<ShopNewsCategory> listCat = shopNewsCategoryService.getByIds(catIds);
        map.put("listCat", listCat);
        map.put("newssearch", newssearch);
        map.put("newspage", pageShopNews);
        map.put("newscategory", newscategorybyShop);
        map.put("clientScript", "shopnews.init();");
        return "user.shoplistnews";
    }

    @RequestMapping(value = "/shop-add-news.html", method = RequestMethod.GET)
    public String addShopNews(ModelMap map, @RequestParam(value = "id", defaultValue = "") String id) {
        if (viewer == null || viewer.getUser() == null) {
            return "redirect:/user/signin.html?ref=" + baseUrl + "/user/shop-add-news.html";
        }

        List<ShopNewsCategory> newscategorybyShop = shopNewsCategoryService.getByShop(viewer.getUser().getId());
        if (id != null && !id.trim().equals("")) {
            ShopNews news = shopNewsService.get(id);
            List<String> get = imageService.get(ImageType.SHOP_NEWS, id);
            if (get != null && get.size() > 0) {
                news.setImage(imageService.getUrl(get.get(0)).thumbnail(100, 100, "outbound").getUrl());
            }
            map.put("news", news);
        }
        map.put("clientScript", "shopnews.initAddnews();");
        map.put("listcat", newscategorybyShop);
        return "user.shopnewsadd";
    }

}
