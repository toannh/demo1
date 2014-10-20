package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.elasticsearch.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.ItemCrawl;
import vn.chodientu.entity.input.ItemCrawlSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.ItemCrawlService;

import vn.chodientu.service.UserService;

@Controller("cpItemCrawl")
@RequestMapping("/cp/itemcrawl")
public class ItemCrawlController extends BaseCp {

    @Autowired
    private ItemCrawlService crawlService;
    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model, HttpSession session, @RequestParam(value = "page", defaultValue = "1") int page) {
        ItemCrawlSearch crawlSearch = new ItemCrawlSearch();
        if (session.getAttribute("crawlSearch") != null && page != 0) {
            crawlSearch = (ItemCrawlSearch) session.getAttribute("crawlSearch");
        } else {
            session.setAttribute("crawlSearch", crawlSearch);
        }

        crawlSearch.setPageIndex(page - 1);
        crawlSearch.setPageSize(100);

        DataPage<ItemCrawl> dataPage = crawlService.search(crawlSearch);
        List<String> listItem = new ArrayList<String>();
        for (ItemCrawl item : dataPage.getData()) {
            if (!listItem.contains(item.getItemId())) {
                listItem.add(item.getItemId());
            }
        }

        model.put("dataPage", dataPage);
        model.put("clientScript", "itemcrawl.init();");
        model.put("crawlSearch", crawlSearch);
        return "cp.itemcrawl";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(ModelMap model, HttpSession session, @ModelAttribute ItemCrawlSearch crawlSearch) {
        session.setAttribute("topUpSearch", crawlSearch);
        crawlSearch.setPageIndex(0);
        crawlSearch.setPageSize(100);

        DataPage<ItemCrawl> dataPage = crawlService.search(crawlSearch);
        List<String> listItem = new ArrayList<String>();
        for (ItemCrawl item : dataPage.getData()) {
            if (!listItem.contains(item.getItemId())) {
                listItem.add(item.getItemId());
            }
        }
        model.put("dataPage", dataPage);
        model.put("clientScript", "itemcrawl.init();");
        model.put("crawlSearch", crawlSearch);
        return "cp.itemcrawl";
    }
}
