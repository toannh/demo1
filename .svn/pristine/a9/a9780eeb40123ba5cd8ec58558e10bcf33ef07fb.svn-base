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
import vn.chodientu.entity.input.NewsCategorySearch;
import vn.chodientu.entity.db.NewsCategory;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.NewsCategoryService;

@Controller("newsCategory")
@RequestMapping("/cp/newscategory")
public class NewsCategoryController extends BaseCp {

    @Autowired
    private NewsCategoryService newsCategoryService;
    @Autowired
    private Gson gson;

    @RequestMapping("")
    public String list(ModelMap model, HttpSession session, @RequestParam(value = "page", defaultValue = "0") int page) {

        NewsCategorySearch catNewsSearch = new NewsCategorySearch();

        if (session.getAttribute("catNewsSearch") != null && page != 0) {
            catNewsSearch = (NewsCategorySearch) session.getAttribute("catNewsSearch");
            
        } else {
            session.setAttribute("catNewsSearch", catNewsSearch);
        }
         if (page > 0) {
            catNewsSearch.setPageIndex(page - 1);
        } else {
            catNewsSearch.setPageIndex(0);
        }
        catNewsSearch.setLevel(-1);
        catNewsSearch.setPageSize(200);

        DataPage<NewsCategory> categoryNewsPage = newsCategoryService.search(catNewsSearch);
        model.put("catNewsSearch", catNewsSearch);
        model.put("categoryNewsPage", categoryNewsPage);
        return "cp.newscategory";
    }
     @RequestMapping(value = {""}, method = RequestMethod.POST)
    public String search(ModelMap model,
            HttpSession session,
            @ModelAttribute NewsCategorySearch catNewsSearch) {

        session.setAttribute("catNewsSearch", catNewsSearch);
        catNewsSearch.setPageIndex(0);
        catNewsSearch.setPageSize(200);
        DataPage<NewsCategory> categoryNewsPage = newsCategoryService.search(catNewsSearch);
        model.put("catNewsSearch", catNewsSearch);
        model.put("categoryNewsPage", categoryNewsPage);
        return "cp.newscategory";
    }

    @ModelAttribute
    public void init(ModelMap map) {
        NewsCategorySearch critSearch = new NewsCategorySearch();
        critSearch.setLevel(0);
        critSearch.setPageIndex(0);
        critSearch.setPageSize(5);
        DataPage<NewsCategory> allNewsCate = newsCategoryService.search(critSearch);
        map.put("newsCate", allNewsCate.getData());
        map.put("clientScript", "listCate=" + gson.toJson(allNewsCate.getData()) + "; newscategory.init();");
    }
}
