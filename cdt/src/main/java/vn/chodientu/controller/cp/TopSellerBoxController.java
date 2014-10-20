package vn.chodientu.controller.cp;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.beanutils.BeanComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vn.chodientu.entity.db.FeaturedCategorySub;
import vn.chodientu.entity.db.TopSellerBox;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.TopSellerBoxItemForm;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.TopSellerBoxService;

@Controller("cpTopSellerBox")
@RequestMapping("/cp/topsellerbox")
public class TopSellerBoxController extends BaseCp {

    @Autowired
    private TopSellerBoxService topSellerBoxService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private Gson gson;
    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap map, @RequestParam(value = "id", defaultValue = "") String id) throws Exception {

        if (!id.trim().equals("")) {
            TopSellerBox bySellerId = topSellerBoxService.getBySellerId(id);
            if (bySellerId != null) {
                List<TopSellerBoxItemForm> topSellerBoxItemForms = bySellerId.getTopSellerBoxItemForms();

                if (topSellerBoxItemForms != null && !topSellerBoxItemForms.isEmpty()) {
                    BeanComparator fieldComparator = new BeanComparator("position");
                    Collections.sort(topSellerBoxItemForms, fieldComparator);
                    for (TopSellerBoxItemForm topSellerBoxItemForm : topSellerBoxItemForms) {
                        List<String> get = imageService.get(ImageType.TOP_SELLER_BOX, topSellerBoxItemForm.getItemId());
                        if (get != null && get.size() > 0) {
                            topSellerBoxItemForm.setImage(imageService.getUrl(get.get(0)).getUrl());
                        } else {
                            topSellerBoxItemForm.setImage(null);
                        }
                    }
                }
                map.put("topSellerItemBoxs", topSellerBoxItemForms);
            }
            map.put("clientScript", "topsellerbox.initItem();");
            return "cp.topsellerboxitem";
        } else {
            List<TopSellerBox> list = topSellerBoxService.getAll();
            for (TopSellerBox topSellerBox : list) {
                List<String> get = imageService.get(ImageType.TOP_SELLER_BOX, topSellerBox.getId());
                if (get != null && get.size() > 0) {
                    topSellerBox.setImage(imageService.getUrl(get.get(0)).thumbnail(50, 50, "outbound").getUrl());
                }
                topSellerBox.setCountItem(itemService.countBySeller(topSellerBox.getSellerId()));
            }
            map.put("topsellerboxs", list);
            map.put("clientScript", "topsellerbox.init();");
            return "cp.topsellerbox";
        }
    }

}
