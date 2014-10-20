package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.AdvBanner;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.input.AdvBannerSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.AdvBannerService;
import vn.chodientu.service.CategoryService;

@Controller("advbanner")
@RequestMapping("/cp/advbanner")
public class AdvBannerController extends BaseCp {

    @Autowired
    private AdvBannerService bannerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private Gson gson;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String list(ModelMap model, HttpSession session, @RequestParam(value = "page", defaultValue = "0") int page) {
        AdvBannerSearch bannerSearch = new AdvBannerSearch();

        if (session.getAttribute("bannerSearch") != null && page != 0) {
            bannerSearch = (AdvBannerSearch) session.getAttribute("bannerSearch");
        } else {
            session.setAttribute("bannerSearch", bannerSearch);
        }
        if (page > 0) {
            bannerSearch.setPageIndex(page - 1);
        } else {
            bannerSearch.setPageIndex(0);
        }
        bannerSearch.setPageSize(100);

        DataPage<AdvBanner> bannerPage = bannerService.search(bannerSearch);

        for (AdvBanner banner : bannerPage.getData()) {
            try {
                Category cate = categoryService.get(banner.getCategoryId());
                banner.setCategoryName(cate.getName());
            } catch (Exception ex) {
            }
        }
        model.put("bannerSearch", bannerSearch);
        model.put("bannerPage", bannerPage);
        
        model.put("clientScript", "cates = " + gson.toJson(categoryService.getChilds(null)) + "; advbanner.init();");
        return "cp.advbanner";
    }

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    public String search(ModelMap model,
            HttpSession session,
            @ModelAttribute AdvBannerSearch bannerSearch) {

        session.setAttribute("bannerSearch", bannerSearch);
        bannerSearch.setPageIndex(0);
        bannerSearch.setPageSize(100);
       DataPage<AdvBanner> bannerPage = bannerService.search(bannerSearch);

        for (AdvBanner banner : bannerPage.getData()) {
            try {
                Category cate = categoryService.get(banner.getCategoryId());
                banner.setCategoryName(cate.getName());
            } catch (Exception ex) {
            }
        }
        model.put("bannerSearch", bannerSearch);
        model.put("bannerPage", bannerPage);
        model.put("clientScript", "cates = " + gson.toJson(categoryService.getChilds(null)) + "; advbanner.init();");
        return "cp.advbanner";
    }
}
