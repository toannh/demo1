package vn.chodientu.controller.cp;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.Landing;
import vn.chodientu.entity.db.LandingCategory;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.service.LandingService;

@Controller("cpLanding")
@RequestMapping("/cp/landing")
public class LandingController extends BaseCp {

    @Autowired
    private LandingService landingService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session,
            @RequestParam(value = "landingid", required = false) String landingId,
            @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {
        if (page > 0) {
            page = page - 1;
        }
        if (landingId == null || landingId.equals("")) {
            DataPage<Landing> landingPage = landingService.getAll(new PageRequest(page, 30));
            map.put("listLanding", landingPage);
            map.put("clientScript", "landing.init();"); 
            return "cp.landing.list";
        } else {
            List<LandingCategory> categories = landingService.getCategories(landingId);
            map.put("listCategories", categories);
            map.put("landing", landingService.getLanding(landingId));
            map.put("clientScript", "landing.init();"); 
            return "cp.landing.category";
        }

    }

}
