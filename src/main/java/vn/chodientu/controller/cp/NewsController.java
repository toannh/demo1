package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.News;
import vn.chodientu.entity.db.NewsCategory;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.input.NewsSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.NewsCategoryService;
import vn.chodientu.service.NewsService;

@Controller("news")
@RequestMapping("/cp/news")
public class NewsController extends BaseCp {

    @Autowired
    private NewsCategoryService newsCategoryService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Gson gson;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String list(ModelMap model, HttpSession session, @RequestParam(value = "page", defaultValue = "0") int page) {
        NewsSearch newsSearch = new NewsSearch();

        if (session.getAttribute("newsSearch") != null && page != 0) {
            newsSearch = (NewsSearch) session.getAttribute("newsSearch");
        } else {
            session.setAttribute("newsSearch", newsSearch);
        }
        newsSearch.setPageIndex(page - 1);
        newsSearch.setPageSize(300);

        DataPage<News> newsPage = newsService.search(newsSearch);
        for (News news : newsPage.getData()) {
            List<String> get = imageService.get(ImageType.NEWS, news.getId());
            if (get != null && get.size() > 0) {
                String url = imageService.getUrl(get.get(0)).thumbnail(100, 100, "outbound").getUrl();
                news.setImage(url);
            }

        }
        for (News nns : newsPage.getData()) {
            for (NewsCategory cate : newsCategoryService.getAll()) {
                if (cate.getId().equals(nns.getCategoryId())) {
                    nns.setCategoryId(cate.getName());
                }
            }
        }
        model.put("newsSearch", newsSearch);
        model.put("newsPage", newsPage);

        this.init(model);
        return "cp.news";
    }

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    public String search(ModelMap model,
            HttpSession session,
            @ModelAttribute NewsSearch newsSearch) {

        session.setAttribute("newsSearch", newsSearch);
        newsSearch.setPageIndex(0);
        newsSearch.setPageSize(300);
        DataPage<News> newsPage = newsService.search(newsSearch);
        for (News news : newsPage.getData()) {
            List<String> get = imageService.get(ImageType.NEWS, news.getId());
            if (get != null && get.size() > 0) {
                String url = imageService.getUrl(get.get(0)).thumbnail(100, 100, "outbound").getUrl();
                news.setImage(url);
            }
        }
        for (News nns : newsPage.getData()) {
            for (NewsCategory cate : newsCategoryService.getAll()) {
                if (cate.getId().equals(nns.getCategoryId())) {
                    nns.setCategoryId(cate.getName());
                }
            }
        }
        model.put("newsSearch", newsSearch);
        model.put("newsPage", newsPage);

        this.init(model);
        return "cp.news";
    }

    @ModelAttribute
    public void init(ModelMap model) {
        List<NewsCategory> newsCategories = newsCategoryService.getAll();
        model.put("clientScript", "newsCategories = " + gson.toJson(newsCategories) + "; news.init();");
        model.put("newsCategories", newsCategories);
    }
}
