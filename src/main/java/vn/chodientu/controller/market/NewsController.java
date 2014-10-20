package vn.chodientu.controller.market;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.News;
import vn.chodientu.entity.db.NewsCategory;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.input.NewsSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.NewsCategoryService;
import vn.chodientu.service.NewsService;
import vn.chodientu.util.UrlUtils;

/**
 * @since Jun 30, 2014
 * @author PhucTd
 */
@Controller("newsController")
public class NewsController extends BaseMarket {

    @Autowired
    private NewsService newsService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private NewsCategoryService newsCategoryService;

    /**
     * Get all news
     *
     * @param page
     * @param modelMap
     * @return
     */
    @RequestMapping("/tin-tuc.html")
    public String list(@RequestParam(value = "page", defaultValue = "0") int page, ModelMap modelMap) {
        NewsSearch newsSearch = new NewsSearch();
        newsSearch.setPageSize(15);
        newsSearch.setActive(1);
        if (page > 0) {
            newsSearch.setPageIndex(page - 1);
        } else {
            newsSearch.setPageIndex(0);
        }
        DataPage<News> newsPage = newsService.search(newsSearch);
        // Get image 
        for (News news : newsPage.getData()) {
            List<String> getImg = null;
            getImg = imageService.get(ImageType.NEWS, news.getId());
            if (getImg != null && !getImg.isEmpty()) {
                news.setImage(imageService.getUrl(getImg.get(0)).thumbnail(100, 100, "outbound").getUrl());
            }
            news.setDetail(news.getDetail().replaceAll("\\<[^>]*>", ""));
        }
        // get news category list
        List<NewsCategory> listCate = newsCategoryService.getAllWithPositionSort();
        String pageS = "";
        if (page > 1) {
            pageS = "?page=" + page;
        } else {
            pageS = "";
        }
        modelMap.put("canonical", "/tin-tuc.html" + pageS);

        // set data
        modelMap.put("newsSearch", newsSearch);
        modelMap.put("newsPage", newsPage);
        modelMap.put("cateList", listCate);
        modelMap.put("title", "Xem thông báo và hướng dẫn mua bán | ChợĐiệnTử eBay Việt Nam");
        modelMap.put("description", "Cập nhật thông báo, quy định mới nhất và hướng dẫn mua sắm từ A-Z tại ChợĐiệnTử eBay Việt Nam - Thanh toán online, CoD, giao hàng toàn quốc, bảo vệ người mua");
        return "market.news.list";
    }

    /**
     *
     * @param id
     * @param page
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/tin-tuc/{name}/{id}.html")
    public String browse(@PathVariable("id") String id, @RequestParam(value = "page", defaultValue = "0") int page, ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) throws Exception {
        // get data
        NewsSearch newsSearch = new NewsSearch();
        newsSearch.setCategoryIds(id);
        newsSearch.setPageSize(15);
        newsSearch.setActive(1);
        if (page > 0) {
            newsSearch.setPageIndex(page - 1);
        } else {
            newsSearch.setPageIndex(0);
        }
        // get data using newsSearch object
        DataPage<News> newsPage = newsService.search(newsSearch);
        // Get image 
        for (News news : newsPage.getData()) {
            List<String> getImg = null;
            getImg = imageService.get(ImageType.NEWS, news.getId());
            if (getImg != null && !getImg.isEmpty()) {
                news.setImage(imageService.getUrl(getImg.get(0)).thumbnail(100, 100, "outbound").getUrl());
            }
            news.setDetail(news.getDetail().replaceAll("\\<[^>]*>", ""));
        }
        // get news category list
        List<NewsCategory> listCate = newsCategoryService.getAllWithPositionSort();
        // get news category detail
        NewsCategory newsCategory = newsCategoryService.getById(id);
        // error 404
        if (newsCategory == null) {
            return "index.404";
        }
        String uri = UrlUtils.newsCateUrl(newsCategory.getId(), newsCategory.getName());
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

        String pageT = "";
        if (page > 1) {
            pageT = "?page=" + page;
        } else {
            pageT = "";
        }
        modelMap.put("canonical", UrlUtils.newsCateUrl(newsCategory.getId(), newsCategory.getName()) + pageT);
        // set data
        modelMap.put("newsSearch", newsSearch);
        modelMap.put("newsPage", newsPage);
        modelMap.put("cateList", listCate);
        modelMap.put("newsCategory", newsCategory);
        modelMap.put("title", "Xem mục " + newsCategory.getName() + " | ChợĐiệnTử eBay Việt Nam");
        modelMap.put("description", "Cập nhật mục " + newsCategory.getName() + " tại ChợĐiệnTử eBay Việt Nam - Thanh toán online, CoD, giao hàng toàn quốc, bảo vệ người mua");

        return "market.news.browse";
    }

    /**
     *
     * @param id
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping("/tin-tuc/{name}-{id}.html")
    public String detail(@PathVariable("id") String id, ModelMap modelMap,HttpServletRequest request) throws Exception {
        // get news detail
        News news = newsService.getById(id);
        // 404 error
        if (news == null) {
            return "index.404";
        }
        String uri = UrlUtils.newsDetailUrl(news.getId(), news.getTitle());
        if (!request.getRequestURI().equals(uri)) {
            String q = request.getQueryString();
            if (q != null && !q.equals("")) {
                q = "?" + q;
            } else {
                q = "";
            }
            return "redirect:" + uri + q;
        }
        // get news category list
        List<NewsCategory> listCate = newsCategoryService.getAll();
        NewsCategory cate = new NewsCategory();
        for (NewsCategory newsCategory : listCate) {
            if (news.getCategoryId() != null && news.getCategoryId().equals(newsCategory.getId())) {
                cate = newsCategory;
                break;
            }
        }
        modelMap.put("canonical", UrlUtils.newsCateUrl(news.getId(), news.getTitle()));
        // set data
        NewsCategory newsCategory = newsCategoryService.getById(news.getCategoryId1());
        modelMap.put("newsCategory", newsCategory);
        modelMap.put("news", news);
        modelMap.put("cateList", listCate);
        modelMap.put("title", "Xem " + news.getTitle() + " | ChợĐiệnTử - Sản phẩm đa dạng, giá rẻ");
        modelMap.put("description", "Xem " + news.getTitle() + " tại mục " + cate.getName() + " tại ChợĐiệnTử eBay Việt Nam - Thanh toán online, CoD, giao hàng toàn quốc, bảo vệ người mua");
        modelMap.put("clientScript", "news.init();");
        return "market.news.detail";
    }
}
