package vn.chodientu.controller.market;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.HotdealCategory;
import vn.chodientu.entity.db.HotdealItem;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.PropertySearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.HotdealService;
import vn.chodientu.util.TextUtils;
import vn.chodientu.util.UrlUtils;

/**
 * @since Jul 14, 2014
 * @author Account
 */
@Controller("hotdealController")
public class HotdealController extends BaseMarket {

    @Autowired
    private HotdealService hotdealService;
    @Autowired
    private Gson json;

    @RequestMapping({"/hotdeal"})
    public String hotdeal(HttpServletResponse response, ModelMap model) {
        List<HotdealCategory> categories = hotdealService.getAll(1);
        List<HotdealCategory> categoriesParent = new ArrayList<>();
        List<HotdealCategory> categoriesChilds = new ArrayList<>();
        HotdealCategory cateSpecial = null;
        List<HotdealItem> hotdealItems = hotdealService.getHomeByCategory(null);
        for (HotdealCategory cate : categories) {
            if (cate.isSpecial()) {
                cateSpecial = cate;
            } else {
                if (cate.getParentId() != null && !cate.getParentId().equals("")) {
                    categoriesChilds.add(cate);
                } else {
                    categoriesParent.add(cate);
                }
            }
        }
        categories.remove(cateSpecial);
        ItemSearch searchSame = new ItemSearch();
        searchSame.setStatus(1);
        searchSame.setCategoryIds(new ArrayList<String>());
        searchSame.getCategoryIds().add(null);
        searchSame.setPriceFrom(0);
        searchSame.setPriceTo(0);
        searchSame.setPageIndex(0);
        searchSame.setPageSize(5);
        searchSame.setModelIds(new ArrayList<String>());
        searchSame.setManufacturerIds(new ArrayList<String>());
        searchSame.setCityIds(new ArrayList<String>());
        searchSame.setProperties(new ArrayList<PropertySearch>());
        model.put("clientScript", "market.init({itemSearch:" + json.toJson(searchSame) + "});");
        model.put("categoriesSpecial", cateSpecial);
        model.put("categories", categoriesParent);
        model.put("categoriesChilds", categoriesChilds);
        model.put("hotdealItems", hotdealItems);
        model.put("canonical", "/hotdeal.html");
        return "market.hotdeal";
    }

    @RequestMapping({"/hotdeal/{cid}/{name}.html"})
    public String browse(@PathVariable("cid") String cid, @RequestParam(value = "page", defaultValue = "0") int page,
            HttpServletResponse response, ModelMap model, HttpServletRequest request) {

        List<HotdealCategory> categories = hotdealService.getAll(1);
        List<HotdealCategory> categoriesParent = new ArrayList<>();
        List<HotdealCategory> categoriesChilds = new ArrayList<>();
        HotdealCategory cateSpecial = null;
        HotdealCategory currentCate = null;
        if (page > 0) {
            page = page - 1;
        } else {
            page = 0;
        }

        DataPage<HotdealItem> hotdealItems = hotdealService.getByCategory(cid, new PageRequest(page, 40));
        for (HotdealCategory cate : categories) {
            if (cate.isSpecial()) {
                cateSpecial = cate;
            } else {
                if (cate.getParentId() != null && !cate.getParentId().equals("")) {
                    categoriesChilds.add(cate);
                } else {
                    categoriesParent.add(cate);
                }
            }
            if (cate.getId().equals(cid)) {
                currentCate = cate;
            }
        }
        categories.remove(cateSpecial);
        ItemSearch searchSame = new ItemSearch();
        searchSame.setStatus(1);
        searchSame.setCategoryIds(new ArrayList<String>());
        searchSame.getCategoryIds().add(null);
        searchSame.setPriceFrom(0);
        searchSame.setPriceTo(0);
        searchSame.setPageIndex(0);
        searchSame.setPageSize(5);
        searchSame.setModelIds(new ArrayList<String>());
        searchSame.setManufacturerIds(new ArrayList<String>());
        searchSame.setCityIds(new ArrayList<String>());
        searchSame.setProperties(new ArrayList<PropertySearch>());
        model.put("clientScript", "market.init({itemSearch:" + json.toJson(searchSame) + "});");
        model.put("currentCate", currentCate);
        model.put("categories", categoriesParent);
        model.put("categoriesChilds", categoriesChilds);
        model.put("hotdealItemsPage", hotdealItems);
        HotdealCategory hotdealCategory = hotdealService.getCategory(cid);
        String uri = "/hotdeal/" + cid + "/" + TextUtils.createAlias(hotdealCategory.getName()) + ".html";
        if (!request.getRequestURI().equals(uri)) {
            String q = request.getQueryString();
            if (q != null && !q.equals("")) {
                q = "?" + q;
            } else {
                q = "";
            }
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            return "redirect:" + uri + q;
        }
        model.put("canonical", baseUrl + "/hotdeal/" + cid + "/" + TextUtils.createAlias(hotdealCategory.getName()) + ".html");
        return "market.hotdeal.category";
    }
}
