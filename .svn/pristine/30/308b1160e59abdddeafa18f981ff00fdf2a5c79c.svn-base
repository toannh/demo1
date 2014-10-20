package vn.chodientu.controller.cp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.chodientu.entity.db.FeaturedNews;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.FeaturedNewsService;

@Controller("featuredNews")
@RequestMapping("/cp/featurednews")
public class FeaturedNewsController extends BaseCp {

    @Autowired
    private FeaturedNewsService otherNewsService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<FeaturedNews> otherNewses = otherNewsService.getAll(false);
        if(otherNewses!=null && otherNewses.size()>0){
            for (FeaturedNews otherNews : otherNewses) {
                List<String> get = imageService.get(ImageType.FEATURED_NEWS, otherNews.getId());
                if(get!=null && get.size()>0){
                    otherNews.setImage(imageService.getUrl(get.get(0)).thumbnail(100, 100, "outbound").getUrl());
                }
                
            }
        }
        model.put("otherNewses", otherNewses);
        return "cp.featurednews";
    }

}
