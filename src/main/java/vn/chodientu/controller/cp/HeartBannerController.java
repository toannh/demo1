package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.HeartBanner;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.input.HeartBannerSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.HeartBannerService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.UserService;

@Controller("cpHeartBanner")
@RequestMapping("/cp/heartbanner")
public class HeartBannerController extends BaseCp {

    @Autowired
    private HeartBannerService heartBannerService;
    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;
    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map,
            HttpSession session,
            @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {
        HeartBannerSearch bannerSearch = new HeartBannerSearch();
        if (session.getAttribute("cpbannerSearch") != null && page != 0) {
            bannerSearch = (HeartBannerSearch) session.getAttribute("cpbannerSearch");
        } else {
            session.setAttribute("cpbannerSearch", bannerSearch);
        }
        if (page > 0) {
            bannerSearch.setPageIndex(page - 1);
        } else {
            bannerSearch.setPageIndex(0);
        }
        bannerSearch.setPageSize(100);
        DataPage<HeartBanner> dataPage = heartBannerService.search(bannerSearch);
        for (HeartBanner hb : dataPage.getData()) {
            List<String> get = imageService.get(ImageType.HEART_BANNER, hb.getId());
            if(get!=null && get.size()>0){
                hb.setImage(imageService.getUrl(get.get(0)).thumbnail(200, 81, "outbound").getUrl());
            } 
        }

        map.put("bannerSearchs", dataPage);
        map.put("cpbannerSearch", bannerSearch);
        map.put("clientScript","heartbanner.init();");
        return "cp.heartbanner";
    }

}
