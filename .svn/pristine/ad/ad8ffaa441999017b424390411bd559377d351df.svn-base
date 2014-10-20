package vn.chodientu.controller.cp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.chodientu.entity.db.News;
import vn.chodientu.entity.db.NewsHomeBox;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.NewsHomeBoxService;
import vn.chodientu.service.NewsService;

@Controller("newshomebox")
@RequestMapping("/cp/newshomebox")
public class NewsHomeBoxController extends BaseCp {

    @Autowired
    private NewsHomeBoxService newsHomeBoxService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String list(ModelMap model) {

        NewsHomeBox newsHomeBox = newsHomeBoxService.getAll();
        List<News> listNews = new ArrayList<>();
        if (newsHomeBox != null) {
            List<String> itemIds = newsHomeBox.getItemIds();
            if (itemIds != null && itemIds.size() > 0) {
                List<News> list = newsService.list();
                if (list != null && list.size() > 0) {
                    for (News news : list) {
                        for (String str : itemIds) {
                            if (str.equals(news.getId())) {
                                List<String> get = imageService.get(ImageType.NEWS, news.getId());
                                if(get!=null && !get.isEmpty()){
                                    news.setImage(imageService.getUrl(get.get(0)).thumbnail(100, 100, "outbound").getUrl());
                                }
                                listNews.add(news);
                            }
                        }
                    }
                }
            }
        }
        model.put("newshomebox", listNews);
        model.put("clientScript", "newshomebox.init();");
        return "cp.newshomebox";
    }

}
