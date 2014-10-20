package vn.chodientu.controller.shop;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopNews;
import vn.chodientu.entity.db.ShopNewsCategory;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ShopNewsCategoryService;
import vn.chodientu.service.ShopNewsService;

/**
 * @since Jun 2, 2014
 * @author Phu
 */
@Controller("shopNews")
public class NewsController extends BaseShop {

    @Autowired
    private ShopNewsCategoryService shopNewsCategoryService;
    @Autowired
    private ShopNewsService shopNewsService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Gson gson;

    @RequestMapping(value = "/{alias}/news-allcategory")
    public String allNewsCategory(@PathVariable String alias, ModelMap model,
            HttpServletResponse response) {

        initMap(alias, model, response);
        Shop shop = (Shop) model.get("shop");
        List<ShopNewsCategory> shopNewsCategories = shopNewsCategoryService.getByShop(shop.getUserId());
        Map<String, List<ShopNews>> shopNews = new HashMap<>();

        if (shopNewsCategories != null && !shopNewsCategories.isEmpty()) {
            for (ShopNewsCategory shopNewsCategory : shopNewsCategories) {
                DataPage<ShopNews> byNewsCategory = shopNewsService.getByNewsCategory(shopNewsCategory.getId(), new PageRequest(0, 6), null, 1);
                if (byNewsCategory != null && byNewsCategory.getData() != null && !byNewsCategory.getData().isEmpty()) {
                    for (ShopNews news : byNewsCategory.getData()) {
                        if (news.getDetail() != null && !news.getDetail().trim().equals("")) {
                            news.setDetail(news.getDetail().replaceAll("\\<.*?\\>", "").concat("..."));
                            if (news.getDetail().length() > 200) {
                                news.setDetail(news.getDetail().substring(0, 199));
                            }
                        }
                        if (news.getImage() != null && !news.getImage().equals("")) {
                            news.setImage(imageService.getUrl(news.getImage()).getUrl());
                        }
                    }
                }
                shopNews.put(shopNewsCategory.getId(), byNewsCategory.getData());
            }
        }

        model.put("shopNews", shopNews);
        model.put("shopNewsCategories", shopNewsCategories);
        model.put("title", "Tin tức shop " + shop.getTitle());
        model.put("description", "Xem Tin tức shop từ shop " + shop.getTitle() + " tại chodientu.vn - Giá rẻ, nhiều khuyến mại, thanh toán online, CoD, giao hàng toàn quốc, bảo vệ người mua");
        model.put("canonical", "/" + shop.getAlias() + "/news-allcategory.html");
        return "shop.news.allcategory";
    }

    @RequestMapping(value = "/{alias}/news-category/{nid}/{name}")
    public String newsCategory(@PathVariable String alias, ModelMap model,
            @PathVariable String nid, @RequestParam(value = "page", defaultValue = "1") int page,
            HttpServletResponse response) {
        initMap(alias, model, response);
        Shop shop = (Shop) model.get("shop");
        ShopNewsCategory newsCategory = shopNewsCategoryService.get(nid);

        if (page > 0) {
            page = page - 1;
        } else {
            page = 0;
        }
        DataPage<ShopNews> byNewsCategory = shopNewsService.getByNewsCategory(nid, new PageRequest(page, 10), null, 1);
        if (byNewsCategory != null && byNewsCategory.getData() != null && !byNewsCategory.getData().isEmpty()) {
            for (ShopNews news : byNewsCategory.getData()) {
                if (news.getDetail() != null && !news.getDetail().trim().equals("")) {
                    news.setDetail(news.getDetail().replaceAll("\\<.*?\\>", "").concat("..."));
                    if (news.getDetail().length() > 200) {
                        news.setDetail(news.getDetail().substring(0, 199));
                    }
                }
                if (news.getImage() != null && !news.getImage().equals("")) {
                    news.setImage(imageService.getUrl(news.getImage()).getUrl());
                }
            }
        }

        model.put("title", "Tin tức shop " + newsCategory.getName());
        model.put("description", "Xem Tin tức shop từ shop " + shop.getTitle() + " tại chodientu.vn - Giá rẻ, nhiều khuyến mại, thanh toán online, CoD, giao hàng toàn quốc, bảo vệ người mua");
        model.put("shopNewsData", byNewsCategory);
        model.put("newsCategory", newsCategory);
        return "shop.news.category";
    }

    @RequestMapping(value = "/{alias}/news/{id}/{name}")
    public String newsDetail(@PathVariable String alias, ModelMap model, @PathVariable String id,
            HttpServletResponse response) {
        initMap(alias, model, response);
        Shop shop = (Shop) model.get("shop");
        ShopNews news = shopNewsService.get(id);
        DataPage<ShopNews> others = new DataPage<>();
        if (news != null) {
            others = shopNewsService.getByNewsCategory(news.getCategoryId(), new PageRequest(0, 5), id, 1);
        }

        model.put("title", "Tin tức shop " + news.getTitle());
        model.put("description", "Xem Tin tức shop từ shop " + shop.getTitle() + " tại chodientu.vn - Giá rẻ, nhiều khuyến mại, thanh toán online, CoD, giao hàng toàn quốc, bảo vệ người mua");
        model.put("news", news);
        model.put("otherNews", others);
        return "shop.news.detail";
    }
}
