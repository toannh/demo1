package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.HotdealCategory;
import vn.chodientu.service.HotdealService;

@Controller("cpHotdeal")
@RequestMapping("/cp/hotdeal")
public class HotdealController extends BaseCp {

    @Autowired
    private HotdealService hotdealService;
    @Autowired
    private Gson gson;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, HttpSession session,
            @RequestParam(value = "hcate", required = false) String hcateId) throws Exception {
        List<HotdealCategory> hcategories = hotdealService.getCategories(hcateId, 0);
        HotdealCategory hcategory = hotdealService.getCategory(hcateId);
        
        map.put("hcategory", hcategory);
        map.put("hcategories", hcategories);
        
        String scriptClient = "";
        if (hcateId == null || hcateId.equals("")) {
            scriptClient+="hotdeal.init(); var hcateId='';var cates="+gson.toJson(hcategories);
        }else{
            scriptClient+="hotdeal.init(); var hcateId='"+hcateId+"';var cates="+gson.toJson(hotdealService.getCategories(null, 0));
        }
        map.put("clientScript", scriptClient);
        return "cp.hotdeal";
    }

}
