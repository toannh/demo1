package vn.chodientu.controller.service;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.VipItem;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.form.VipItemForm;
import vn.chodientu.entity.input.VipItemSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.VipItemService;

@Controller("serviceVipItem")
@RequestMapping("/vipitem")
public class VipItemController extends BaseRest {

    @Autowired
    private VipItemService vipItemService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody List<VipItemForm> vipItemForms) throws Exception {
        return vipItemService.add(vipItemForms);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Response search(@RequestParam(value = "categoryId", defaultValue = "") String categoryId,
            @RequestParam(value = "page", defaultValue = "0") int page) throws Exception {

        VipItemSearch vipSearch = new VipItemSearch();
        vipSearch.setCategoryId(categoryId);
        vipSearch.setActive(1);
        vipSearch.setPageIndex(page);
        vipSearch.setPageSize(4);
        DataPage<VipItem> search = vipItemService.search(vipSearch, true);
        List<Item> items = new ArrayList<>();
        List<String> itemIds = new ArrayList<>();
        for (VipItem vip : search.getData()) {
            if (!itemIds.contains(vip.getItemId())) {
                itemIds.add(vip.getItemId());
            }
        }
        items = itemService.list(itemIds);
        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).isActive() || !items.get(i).isApproved() || !items.get(i).isCompleted()) {
                items.remove(i);
                i--;
            }
        }
        for (Item item : items) {
            List<String> images = new ArrayList<>();
            if (item.getImages() != null && !item.getImages().isEmpty()) {
                for (String img : item.getImages()) {
                    ImageUrl url = imageService.getUrl(img);
                    if (url != null && url.getUrl() != null) {
                        images.add(url.getUrl());
                    }
                }
            }
            item.setImages(images);
        }

        List<String> sellerIds = new ArrayList<>();
        for (Item item : items) {
            sellerIds.add(item.getSellerId());
        }
        List<Shop> shops = shopService.getShops(sellerIds);

        Map<String, Object> map = new HashMap<>();
        map.put("items", items);
        map.put("shops", shops);
        map.put("vipPage", search);
        return new Response(true, "ok", map);
    }

}
