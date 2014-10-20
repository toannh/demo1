package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.chodientu.entity.db.HotDealBox;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.service.HotDealBoxService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;

@Controller("cpHotDealBox")
@RequestMapping("/cp/hotdealbox")
public class HotDealBoxController extends BaseCp {

    @Autowired
    private HotDealBoxService hotDealBoxService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private Gson gson;
    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map) throws Exception {
        List<HotDealBox> all = hotDealBoxService.getAll();
        for (HotDealBox box : all) {
            List<String> imgGet = imageService.get(ImageType.HOT_DEAL_BOX, box.getItemId());
            if (imgGet != null && imgGet.size() > 0) {
                String url = imageService.getUrl(imgGet.get(0)).thumbnail(50, 50, "outbound").getUrl();
                box.setImage(url);
            }
        }
        map.put("Hotdealbox", all);
        map.put("clientScript", "hotdealbox.init();");
        return "cp.hotdealbox";
    }

}
