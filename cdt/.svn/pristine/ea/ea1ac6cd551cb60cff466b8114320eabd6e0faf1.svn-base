package vn.chodientu.controller.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.enu.Condition;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.ModelSearch;
import vn.chodientu.entity.input.PropertySearch;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.ModelService;

@Controller("serviceHistogram")
@RequestMapping("/histogram")
public class HistogramController extends BaseRest {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private Gson gson;

    @ResponseBody
    @RequestMapping(value = "/{action}", method = RequestMethod.GET)
    public Response itemHistogram(@PathVariable("action") String action,
            @RequestParam(value = "cid", required = false) String cid,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "manufacturers", required = false) String manufacturers,
            @RequestParam(value = "models", required = false) String models,
            @RequestParam(value = "cities", required = false) String cities,
            @RequestParam(value = "properties", required = false) String properties,
            @RequestParam(value = "sellerId", required = false) String sellerId,
            @RequestParam(value = "status", required = false, defaultValue = "1") int status,
            @RequestParam(value = "freeship", required = false) String freeShip,
            @RequestParam(value = "cod", required = false) String cod,
            @RequestParam(value = "onlinepayment", required = false) String onlinePayment,
            @RequestParam(value = "promotion", required = false) String promotion,
            @RequestParam(value = "type", required = false) ListingType type,
            @RequestParam(value = "condition", required = false) Condition condition,
            @RequestParam(value = "pricefrom", defaultValue = "0") int priceFrom,
            @RequestParam(value = "priceto", defaultValue = "0") int priceTo,
            @RequestParam(value = "leaf", defaultValue = "0") int leaf,
            @RequestParam(value = "catetype", defaultValue = "") String catetype,
            @RequestParam(value = "promotionId", defaultValue = "") String promotionId,
            @RequestParam(value = "ignoresearch", required = false) boolean ignoreSearch) {

        if (cid != null && cid.equals("")) {
            cid = null;
        }

        ItemSearch itemSearch = new ItemSearch();
        if (catetype.equals("shop")) {
            itemSearch.setShopCategoryId(cid);
        } else {
            itemSearch.setCategoryIds(new ArrayList<String>());
            if (cid != null) {
                itemSearch.getCategoryIds().add(cid);
            }
        }
        itemSearch.setKeyword(keyword);
        itemSearch.setManufacturerIds(gson.fromJson(manufacturers, ArrayList.class));
        itemSearch.setModelIds(gson.fromJson(models, ArrayList.class));
        itemSearch.setCityIds(gson.fromJson(cities, ArrayList.class));
        itemSearch.setProperties((List<PropertySearch>) gson.fromJson(properties, new TypeToken<List<PropertySearch>>() {
        }.getType()));

        if (freeShip != null) {
            itemSearch.setFreeShip(true);
        }
        if (cod != null) {
            itemSearch.setCod(true);
        }
        if (onlinePayment != null) {
            itemSearch.setOnlinePayment(true);
        }
        if (promotion != null) {
            itemSearch.setPromotion(true);
        }
        if (promotionId != null) {
            itemSearch.setPromotionId(promotionId);
        }
        if (sellerId != null) {
            itemSearch.setSellerId(sellerId);
        }
        if (condition != null && (condition == Condition.NEW || condition == Condition.OLD)) {
            itemSearch.setCondition(condition);
        }
        if (type != null && (type == ListingType.BUYNOW || type == ListingType.AUCTION)) {
            itemSearch.setListingType(type);
        }

        if (leaf > 0) {
            itemSearch.setLeaf(leaf);
        }
        itemSearch.setPriceFrom(priceFrom);
        itemSearch.setPriceTo(priceTo);
        itemSearch.setStatus(status);

        switch (action) {
            case "category":
                return new Response(true, "ok", itemService.getCategoryHistogram(itemSearch, ignoreSearch));
            case "shopcategory":
                return new Response(true, "ok", itemService.getShopCategoryHistogram(itemSearch, ignoreSearch));
            case "manufacturer":
                return new Response(true, "ok", itemService.getManufacturerHistogram(itemSearch));
            case "model":
                return new Response(true, "ok", itemService.getModelHistogram(itemSearch));
            case "item":
                return new Response(true, "ok", itemService.getItemHistogram(itemSearch));
            case "city":
                return new Response(true, "ok", itemService.getCityHistogram(itemSearch));
            case "property":
                return new Response(true, "ok", itemService.getPropertyHistogram(itemSearch));
            case "modelcount":
                return new Response(true, "ok", modelService.getModelCountByItem(itemSearch, ignoreSearch));
            default:
                return new Response(false);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/model/{action}", method = RequestMethod.GET)
    public Response modelHistogram(@PathVariable("action") String action,
            @RequestParam(value = "cid", required = false) String cid,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "manufacturers", required = false) String manufacturers,
            @RequestParam(value = "properties", required = false) String properties,
            @RequestParam(value = "ignoreSearch", required = false) boolean ignoreSearch,
            @RequestParam(value = "status", required = false, defaultValue = "1") int status) {

        if (cid != null && cid.equals("")) {
            cid = null;
        }

        ModelSearch modelSearch = new ModelSearch();
        modelSearch.setCategoryId(cid);
        modelSearch.setKeyword(keyword);
        modelSearch.setManufacturerIds(gson.fromJson(manufacturers, ArrayList.class));
        modelSearch.setProperties((List<PropertySearch>) gson.fromJson(properties, new TypeToken<List<PropertySearch>>() {
        }.getType()));

        modelSearch.setStatus(status);

        switch (action) {
            case "category":
                return new Response(true, "ok", modelService.getCategoryHistogram(modelSearch, ignoreSearch));
            case "manufacturer":
                return new Response(true, "ok", modelService.getManufacturerHistogram(modelSearch));
            case "property":
                return new Response(true, "ok", modelService.getPropertyHistogram(modelSearch));
            case "item":
                return new Response(true, "ok", itemService.getItemCountByModel(modelSearch, ignoreSearch));
            default:
                return new Response(false);
        }
    }

}
