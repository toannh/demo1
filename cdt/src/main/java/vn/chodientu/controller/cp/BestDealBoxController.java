package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.chodientu.entity.db.BestDealBox;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.service.BestDealBoxService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;

@Controller("cpBestDealBox")
@RequestMapping("/cp/bestdealbox")
public class BestDealBoxController extends BaseCp {

    @Autowired
    private BestDealBoxService bestDealBoxService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private Gson gson;
    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map) throws Exception {
        List<BestDealBox> all = bestDealBoxService.getAll();
        List<String> ids = new ArrayList<>();
        if (all != null && all.size() > 0) {
            for (BestDealBox bestDealBox : all) {
                ids.add(bestDealBox.getItemId());
            }
        }
        List<Item> listItem = itemService.list(ids);
        for (Item item : listItem) {
            List<String> get = imageService.get(ImageType.ITEM, item.getId());
            List<String> listImg = new ArrayList<>();
            for (String img : get) {
                String url = imageService.getUrl(img).thumbnail(100, 100, "outbound").getUrl();
                listImg.add(url);
            }

            List<String> imgGet = imageService.get(ImageType.BEST_DEAL_BOX, item.getId());

            if (imgGet != null && imgGet.size() > 0) {
                String url = imageService.getUrl(imgGet.get(0)).thumbnail(100, 100, "outbound").getUrl();
                listImg = new ArrayList<>();
                listImg.add(url);
                item.setImages(listImg);
            } else {
                item.setImages(null);
            }

        }

        map.put("bestDealBox", all);
        map.put("items", listItem);
        map.put("clientScript", "bestdealbox.init();");
        return "cp.bestdealbox";
    }

}
