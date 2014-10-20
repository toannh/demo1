package vn.chodientu.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import vn.chodientu.entity.enu.Condition;
import vn.chodientu.entity.enu.ItemSource;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.ModelSearch;
import vn.chodientu.entity.input.PropertySearch;

/**
 *
 * @author thanhvv
 */
public class UrlUtils {

    public static String item(String id, String name) {
        return "/san-pham/" + id + "/" + TextUtils.createAlias(name) + ".html";
    }

    public static String browse(String id, String name) {
        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setCategoryIds(new ArrayList<String>());
        itemSearch.getCategoryIds().add(id);
        return UrlUtils.browseUrl(itemSearch, name);
    }

    public static String manufacturerUrl(String id) {
        ItemSearch itemSearch = new ItemSearch();
        ArrayList<String> ids = new ArrayList<>();
        ids.add(id);
        itemSearch.setManufacturerIds(ids);
        return UrlUtils.browseUrl(itemSearch, null);
    }

    /**
     * built shop browse url
     *
     * @param itemSearch
     * @param alias
     * @return
     */
    public static String shopBrowseUrl(ItemSearch itemSearch, String alias) {
        List<String> params = new ArrayList<>();
        if (itemSearch.getKeyword() != null && !itemSearch.getKeyword().equals("")) {
            params.add("keyword=" + itemSearch.getKeyword().replaceAll("\\s+", "\\+"));
        }
        if (itemSearch.getPromotionId() != null && !itemSearch.getPromotionId().equals("")) {
            params.add("promotionId=" + itemSearch.getPromotionId());
        }

        if (itemSearch.getShopCategoryId() != null && !itemSearch.getShopCategoryId().trim().equals("")) {
            params.add("cid=" + itemSearch.getShopCategoryId());
        } else if (itemSearch.getCategoryIds() != null && itemSearch.getCategoryIds().size() > 0) {
            params.add("cid=" + itemSearch.getCategoryIds().get(0));
        }

        if (itemSearch.getOrderBy() > 0) {
            params.add("order=" + itemSearch.getOrderBy());
        }
        if (itemSearch.getPageIndex() > 0) {
            params.add("page=" + itemSearch.getPageIndex());
        }

        StringBuilder strParams = new StringBuilder();
        boolean first = true;
        for (String param : params) {
            if (first) {
                first = false;
                strParams.append("?");
            } else {
                strParams.append("&");
            }
            strParams.append(param);
        }
        return "/" + alias + "/browse.html" + strParams.toString();
    }

    public static String shopBrowseUrl(ItemSearch sourceItemSearch, String alias, String strChange) {
        ItemSearch itemSearch;
        try {
            itemSearch = (ItemSearch) BeanUtils.cloneBean(sourceItemSearch);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException ex) {
            itemSearch = new ItemSearch();
        }
        if (strChange != null && !strChange.equals("")) {
            List<Map<String, String>> changes = (List<Map<String, String>>) JsonUtils.decode(strChange, new TypeToken<List<Map<String, String>>>() {
            }.getType());

            for (Map<String, String> ch : changes) {
                String op = ch.get("op");
                String key = ch.get("key");
                String val = ch.get("val");
                if (key.equals("cid")) {
                    itemSearch.setShopCategoryId(val);
                }
                if (key.equals("keyword")) {
                    if (op.equals("mk")) {
                        itemSearch.setKeyword(val);
                    } else if (op.equals("rm")) {
                        itemSearch.setKeyword(null);
                    }
                }
                if (key.equals("promotionId")) {
                    if (op.equals("mk")) {
                        itemSearch.setPromotionId(val);
                    } else if (op.equals("rm")) {
                        itemSearch.setPromotionId(null);
                    }
                }
                if (key.equals("order")) {
                    try {
                        itemSearch.setOrderBy(Integer.parseInt(val));
                    } catch (NumberFormatException ex) {
                    }
                }
                if (key.equals("page")) {
                    try {
                        itemSearch.setPageIndex(Integer.parseInt(val));
                    } catch (NumberFormatException ex) {
                    }
                }
            }
        }
        return UrlUtils.shopBrowseUrl(itemSearch, alias);
    }

    /**
     * built market browse url
     *
     * @param itemSearch
     * @param name
     * @return
     */
    public static String browseUrl(ItemSearch itemSearch, String name) {
        String url;
        boolean check = false;
        if (itemSearch.getCategoryIds() == null || itemSearch.getCategoryIds().isEmpty()) {
            check = true;
            url = "/tim-kiem.html";
        } else {
            url = "/mua-ban/" + itemSearch.getCategoryIds().get(0) + "/" + TextUtils.createAlias(name) + ".html";
        }

        String search = "";
        String keyword = itemSearch.getKeyword();
        int pageIndex = itemSearch.getPageIndex();
        if ((itemSearch.getModelIds() != null && !itemSearch.getModelIds().isEmpty())
                || (itemSearch.getManufacturerIds() != null && !itemSearch.getManufacturerIds().isEmpty())
                || (itemSearch.getCityIds() != null && !itemSearch.getCityIds().isEmpty())
                || itemSearch.getOrderBy() > 0 || (itemSearch.getProperties() != null && !itemSearch.getProperties().isEmpty())
                || (itemSearch.getCondition() == Condition.NEW || itemSearch.getCondition() == Condition.OLD)
                || (itemSearch.getListingType() == ListingType.AUCTION || itemSearch.getListingType() == ListingType.BUYNOW)
                || (itemSearch.getPriceFrom() > 0 || itemSearch.getPriceTo() > 0) || itemSearch.isCod()
                || itemSearch.isFreeShip() || itemSearch.isOnlinePayment() || itemSearch.isPromotion()
                || (itemSearch.getSellerId() != null && !itemSearch.getSellerId().equals(""))
                || (itemSearch.getPromotionId() != null && !itemSearch.getPromotionId().equals(""))) {
            itemSearch.setKeyword(null);
            itemSearch.setPageIndex(0);
            search = "filter=" + Base64.encodeBase64String(StringUtils.getBytesUtf8(new Gson().toJson(itemSearch)));
        }

        if (keyword != null && !keyword.equals("")) {
            if (check && search.equals("")) {
                return "/s/" + keyword.replaceAll("\\s+", "\\+").trim() + ".html" + (pageIndex <= 1 ? "" : "?page=" + pageIndex);
            } else {
                url += "?keyword=" + keyword.replaceAll("\\s+", "\\+").trim();
                url = pageIndex <= 1 ? url : url + "&page=" + pageIndex;
                return search.equals("") ? url : url +(search.equals("")?"":("&" + search)) ;
            }
        } else {
            return search.equals("")
                    ? (pageIndex <= 1 ? url : url + "?page=" + pageIndex)
                    : ((pageIndex <= 1 ? (url + "?") : (url + "?page=" + pageIndex + "&")) + search);
        }
    }

    public static String browseUrl(ItemSearch sourceItemSearch, String name, String strChange) {
        ItemSearch itemSearch;
        try {
            itemSearch = (ItemSearch) BeanUtils.cloneBean(sourceItemSearch);
            itemSearch.setManufacturerIds(new ArrayList<>(sourceItemSearch.getManufacturerIds()));
            itemSearch.setModelIds(new ArrayList<>(sourceItemSearch.getModelIds()));
            itemSearch.setCityIds(new ArrayList<>(sourceItemSearch.getCityIds()));
            itemSearch.setProperties(new ArrayList<>(sourceItemSearch.getProperties()));
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException ex) {
            itemSearch = new ItemSearch();
        }
        if (strChange != null && !strChange.equals("")) {
            List<Map<String, String>> changes = (List<Map<String, String>>) JsonUtils.decode(strChange, new TypeToken<List<Map<String, String>>>() {
            }.getType());

            for (Map<String, String> ch : changes) {
                String op = ch.get("op");
                String key = ch.get("key");
                String val = ch.get("val");
                if (key.equals("cid")) {
                    if (op.equals("mk")) {
                        itemSearch.setCategoryIds(new ArrayList<String>());
                        itemSearch.getCategoryIds().add(val);
                    } else if (op.equals("rm")) {
                        itemSearch.setCategoryIds(null);
                        itemSearch.setCategoryId(null);
                    }
                }
                if (key.equals("keyword")) {
                    if (op.equals("mk")) {
                        itemSearch.setKeyword(val);
                    } else if (op.equals("rm")) {
                        itemSearch.setKeyword(null);
                    }
                }
                if (key.equals("sellerId")) {
                    if (op.equals("mk")) {
                        itemSearch.setSellerId(val);
                    } else if (op.equals("rm")) {
                        itemSearch.setSellerId(null);
                    }
                }
                if (key.equals("manufacturers")) {
                    if (op.equals("mk")) {
                        itemSearch.getManufacturerIds().remove(val);
                        itemSearch.getManufacturerIds().add(val);
                    } else if (op.equals("rm")) {
                        itemSearch.getManufacturerIds().remove(val);
                    } else if (op.equals("cl")) {
                        itemSearch.getManufacturerIds().clear();
                    }
                }
                if (key.equals("models")) {
                    if (op.equals("mk")) {
                        itemSearch.getModelIds().remove(val);
                        itemSearch.getModelIds().add(val);
                    } else if (op.equals("rm")) {
                        itemSearch.getModelIds().remove(val);
                    } else if (op.equals("cl")) {
                        itemSearch.getModelIds().clear();
                    }
                }
                if (key.equals("cities")) {
                    if (op.equals("mk")) {
                        itemSearch.getCityIds().remove(val);
                        itemSearch.getCityIds().add(val);
                    } else if (op.equals("rm")) {
                        itemSearch.getCityIds().remove(val);
                    } else if (op.equals("cl")) {
                        itemSearch.getCityIds().clear();
                    }
                }
                if (key.equals("properties")) {
                    PropertySearch ps = (PropertySearch) JsonUtils.decode(val, new TypeToken<PropertySearch>() {
                    }.getType());
                    if (op.equals("mk")) {
                        itemSearch.getProperties().remove(ps);
                        itemSearch.getProperties().add(ps);
                    } else if (op.equals("rm")) {
                        itemSearch.getProperties().remove(ps);
                    } else if (op.equals("cl")) {
                        itemSearch.getProperties().clear();
                    }
                }
                if (key.equals("freeship")) {
                    if (op.equals("mk")) {
                        itemSearch.setFreeShip(true);
                    } else if (op.equals("rm")) {
                        itemSearch.setFreeShip(false);
                    }
                }
                if (key.equals("cod")) {
                    if (op.equals("mk")) {
                        itemSearch.setCod(true);
                    } else if (op.equals("rm")) {
                        itemSearch.setCod(false);
                    }
                }
                if (key.equals("onlinepayment")) {
                    if (op.equals("mk")) {
                        itemSearch.setOnlinePayment(true);
                    } else if (op.equals("rm")) {
                        itemSearch.setOnlinePayment(false);
                    }
                }
                if (key.equals("promotion")) {
                    if (op.equals("mk")) {
                        itemSearch.setPromotion(true);
                    } else if (op.equals("rm")) {
                        itemSearch.setPromotion(false);
                    }
                }
                if (key.equals("promotionId")) {
                    if (op.equals("mk")) {
                        itemSearch.setPromotionId(val);
                    } else if (op.equals("rm")) {
                        itemSearch.setPromotionId(null);
                    }
                }
                if (key.equals("type")) {
                    try {
                        if (val.equals("BUYNOW")) {
                            itemSearch.setListingType(ListingType.BUYNOW);
                        } else if (val.equals("AUCTION")) {
                            itemSearch.setListingType(ListingType.AUCTION);
                        } else {
                            itemSearch.setListingType(null);
                        }
                    } catch (NumberFormatException ex) {
                    }
                }
                if (key.equals("condition")) {
                    try {
                        if (val.equals("NEW")) {
                            itemSearch.setCondition(Condition.NEW);
                        } else if (val.equals("OLD")) {
                            itemSearch.setCondition(Condition.OLD);
                        } else {
                            itemSearch.setCondition(null);
                        }
                    } catch (NumberFormatException ex) {
                    }
                }
                if (key.equals("pricefrom")) {
                    try {
                        itemSearch.setPriceFrom(Integer.parseInt(val));
                    } catch (NumberFormatException ex) {
                    }
                }
                if (key.equals("priceto")) {
                    try {
                        itemSearch.setPriceTo(Integer.parseInt(val));
                    } catch (NumberFormatException ex) {
                    }
                }
                if (key.equals("order")) {
                    try {
                        itemSearch.setOrderBy(Integer.parseInt(val));
                    } catch (NumberFormatException ex) {
                    }
                }
                if (key.equals("page")) {
                    try {
                        itemSearch.setPageIndex(Integer.parseInt(val));
                    } catch (NumberFormatException ex) {
                    }
                }
            }
        }
        return UrlUtils.browseUrl(itemSearch, name);
    }

    /**
     * built market model browse url
     *
     * @param modelSearch
     * @param name
     * @return
     */
    public static String modelBrowseUrl(ModelSearch modelSearch, String name) {
        String url;

        if (modelSearch.getCategoryId() == null) {
            url = "/tim-kiem-model.html";
        } else {
            url = "/mua-ban/model/" + modelSearch.getCategoryId() + "/" + TextUtils.createAlias(name) + ".html";
        }
        String search = "";
        String keyword = modelSearch.getKeyword();
        int pageIndex = modelSearch.getPageIndex();
        if ((modelSearch.getManufacturerIds() != null && !modelSearch.getManufacturerIds().isEmpty())
                || modelSearch.getOrderBy() > 0 || (modelSearch.getProperties() != null && !modelSearch.getProperties().isEmpty())) {
            modelSearch.setKeyword(null);
            modelSearch.setPageIndex(0);
            search = "filter=" + Base64.encodeBase64String(StringUtils.getBytesUtf8(new Gson().toJson(modelSearch)));
        }

        if (keyword != null && !keyword.equals("")) {
            url += "?keyword=" + keyword.replaceAll("\\s+", "\\+");
            url = pageIndex <= 1 ? url : url + "&page=" + pageIndex;
            return search.equals("") ? url : url + "&" + search;
        } else {
            return search.equals("")
                    ? (pageIndex <= 1 ? url : url + "?page=" + pageIndex)
                    : ((pageIndex <= 1 ? (url + "?") : (url + "?page=" + pageIndex + "&")) + search);
        }
    }

    public static String modelBrowseUrl(ModelSearch sourceModelSearch, String name, String strChange) {
        ModelSearch modelSearch;
        try {
            modelSearch = (ModelSearch) BeanUtils.cloneBean(sourceModelSearch);
            modelSearch.setManufacturerIds(new ArrayList<>(sourceModelSearch.getManufacturerIds()));
            modelSearch.setProperties(new ArrayList<>(sourceModelSearch.getProperties()));
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException ex) {
            modelSearch = new ModelSearch();
        }
        if (strChange != null && !strChange.equals("")) {
            List<Map<String, String>> changes = (List<Map<String, String>>) JsonUtils.decode(strChange, new TypeToken<List<Map<String, String>>>() {
            }.getType());

            for (Map<String, String> ch : changes) {
                String op = ch.get("op");
                String key = ch.get("key");
                String val = ch.get("val");
                if (key.equals("cid")) {
                    modelSearch.setCategoryId(val);
                }
                if (key.equals("keyword")) {
                    if (op.equals("mk")) {
                        modelSearch.setKeyword(val);
                    } else if (op.equals("rm")) {
                        modelSearch.setKeyword(null);
                    }
                }
                if (key.equals("manufacturers")) {
                    if (op.equals("mk")) {
                        modelSearch.getManufacturerIds().remove(val);
                        modelSearch.getManufacturerIds().add(val);
                    } else if (op.equals("rm")) {
                        modelSearch.getManufacturerIds().remove(val);
                    } else if (op.equals("cl")) {
                        modelSearch.getManufacturerIds().clear();
                    }
                }
                if (key.equals("properties")) {
                    PropertySearch ps = (PropertySearch) JsonUtils.decode(val, new TypeToken<PropertySearch>() {
                    }.getType());
                    if (op.equals("mk")) {
                        modelSearch.getProperties().remove(ps);
                        modelSearch.getProperties().add(ps);
                    } else if (op.equals("rm")) {
                        modelSearch.getProperties().remove(ps);
                    } else if (op.equals("cl")) {
                        modelSearch.getProperties().clear();
                    }
                }

                if (key.equals("order")) {
                    try {
                        modelSearch.setOrderBy(Integer.parseInt(val));
                    } catch (NumberFormatException ex) {
                    }
                }
                if (key.equals("page")) {
                    try {
                        modelSearch.setPageIndex(Integer.parseInt(val));
                    } catch (NumberFormatException ex) {
                    }
                }
            }
        }
        return UrlUtils.modelBrowseUrl(modelSearch, name);
    }

    public static String browseToModel(ItemSearch itemSearch, String name) {
        ModelSearch modelSearch = new ModelSearch();
        if (itemSearch.getCategoryIds() != null && !itemSearch.getCategoryIds().isEmpty()) {
            modelSearch.setCategoryId(itemSearch.getCategoryIds().get(0));
        }
        modelSearch.setKeyword(itemSearch.getKeyword());
        modelSearch.setManufacturerIds(itemSearch.getManufacturerIds());
        modelSearch.setProperties(itemSearch.getProperties());
        return UrlUtils.modelBrowseUrl(modelSearch, name);
    }

    public static String browseToItem(ModelSearch modelSearch, String name) {
        ItemSearch itemSearch = new ItemSearch();
        if (modelSearch.getCategoryId() != null && !modelSearch.getCategoryId().trim().equals("")) {
            itemSearch.setCategoryIds(new ArrayList<String>());
            itemSearch.getCategoryIds().add(modelSearch.getCategoryId());
        }
        itemSearch.setKeyword(modelSearch.getKeyword());
        itemSearch.setManufacturerIds(modelSearch.getManufacturerIds());
        itemSearch.setProperties(modelSearch.getProperties());
        return UrlUtils.browseUrl(itemSearch, name);
    }

    public static String modelDetailUrl(ItemSearch sourceItemSearch, String name, String strChange) {
        ItemSearch itemSearch;
        try {
            itemSearch = (ItemSearch) BeanUtils.cloneBean(sourceItemSearch);
            itemSearch.setModelIds(new ArrayList<>(sourceItemSearch.getModelIds()));
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException ex) {
            itemSearch = new ItemSearch();
        }
        if (strChange != null && !strChange.equals("")) {
            List<Map<String, String>> changes = (List<Map<String, String>>) JsonUtils.decode(strChange, new TypeToken<List<Map<String, String>>>() {
            }.getType());

            for (Map<String, String> ch : changes) {
                String op = ch.get("op");
                String key = ch.get("key");
                String val = ch.get("val");

                if (key.equals("cities")) {
                    if (op.equals("mk")) {
                        itemSearch.getCityIds().remove(val);
                        itemSearch.getCityIds().add(val);
                    } else if (op.equals("rm")) {
                        itemSearch.getCityIds().remove(val);
                    } else if (op.equals("cl")) {
                        itemSearch.getCityIds().clear();
                    }
                }
                if (key.equals("type")) {
                    try {
                        if (val.equals("BUYNOW")) {
                            itemSearch.setListingType(ListingType.BUYNOW);
                        } else if (val.equals("AUCTION")) {
                            itemSearch.setListingType(ListingType.AUCTION);
                        } else {
                            itemSearch.setListingType(null);
                        }
                    } catch (NumberFormatException ex) {
                    }
                }
                if (key.equals("cod")) {
                    if (op.equals("mk")) {
                        itemSearch.setCod(true);
                    } else if (op.equals("rm")) {
                        itemSearch.setCod(false);
                    }
                }
                if (key.equals("manuf")) {
                    if (op.equals("mk")) {
                        itemSearch.setManufacturerIds(new ArrayList<String>());
                    }
                }
                if (key.equals("promotion")) {
                    if (op.equals("mk")) {
                        itemSearch.setPromotion(true);
                    } else if (op.equals("rm")) {
                        itemSearch.setPromotion(false);
                    }
                }

                if (key.equals("condition")) {
                    try {
                        if (val.equals("NEW")) {
                            itemSearch.setCondition(Condition.NEW);
                        } else if (val.equals("OLD")) {
                            itemSearch.setCondition(Condition.OLD);
                        } else {
                            itemSearch.setListingType(null);
                        }
                    } catch (NumberFormatException ex) {
                    }
                }
                if (key.equals("order")) {
                    try {
                        itemSearch.setOrderBy(Integer.parseInt(val));
                    } catch (NumberFormatException ex) {
                    }
                }
                if (key.equals("page")) {
                    try {
                        itemSearch.setPageIndex(Integer.parseInt(val));
                    } catch (NumberFormatException ex) {
                    }
                }
            }
        }
        String url;
        if (itemSearch.getModelIds() != null && itemSearch.getModelIds().size() > 0) {
            url = "/model/" + itemSearch.getModelIds().get(0) + "/" + TextUtils.createAlias(name) + ".html";
        } else {
            url = "/tim-kiem-model.html";
        }
        String search = "";
        String keyword = itemSearch.getKeyword();
        int pageIndex = itemSearch.getPageIndex();
        if ((itemSearch.getManufacturerIds() != null && !itemSearch.getManufacturerIds().isEmpty())
                || (itemSearch.getCityIds() != null && !itemSearch.getCityIds().isEmpty())
                || itemSearch.getOrderBy() > 0 || (itemSearch.getProperties() != null && !itemSearch.getProperties().isEmpty())
                || (itemSearch.getCondition() == Condition.NEW || itemSearch.getCondition() == Condition.OLD)
                || (itemSearch.getListingType() == ListingType.AUCTION || itemSearch.getListingType() == ListingType.BUYNOW)
                || (itemSearch.getPriceFrom() > 0 || itemSearch.getPriceTo() > 0) || itemSearch.isCod()
                || itemSearch.isFreeShip() || itemSearch.isOnlinePayment() || itemSearch.isPromotion()) {
            itemSearch.setKeyword(null);
            search = "?filter=" + Base64.encodeBase64String(StringUtils.getBytesUtf8(new Gson().toJson(itemSearch)));
        }
        if (keyword != null && !keyword.equals("")) {
            url += "?keyword=" + keyword.replaceAll("\\s+", "\\+");
            url = pageIndex <= 1 ? url : url + "&page=" + pageIndex;
            return search.equals("") ? url : url + "&" + search;
        } else {
            return search.equals("")
                    ? (pageIndex <= 1 ? url : url + "?page=" + pageIndex)
                    : ((pageIndex <= 1 ? (url + "?") : (url + "?page=" + pageIndex + "&")) + search);
        }
    }

    /**
     * @param id
     * @param name
     * @return
     */
    public static String newsCateUrl(String id, String name) {
        return "/tin-tuc/" + TextUtils.createAlias(name) + "/" + id + ".html";
    }

    /**
     * @param id
     * @param name
     * @return
     */
    public static String newsDetailUrl(String id, String name) {
        return "/tin-tuc/" + TextUtils.createAlias(name) + "-" + id + ".html";
    }

    /**
     * Chi tiết đơn hàng
     *
     * @param id
     * @return
     */
    public static String order(String id) {
        return "";
    }

    /**
     * Cập nhật đơn hàng
     *
     * @param id
     * @return
     */
    public static String orderEdit(String id) {
        return "";
    }

    /**
     * tạo vận đơn COD
     *
     * @param id
     * @return
     */
    public static String ladingCod(String id) {
        return "";
    }

    /**
     * Tạo vận đơn vận chuyển
     *
     * @param id
     * @return
     */
    public static String ladingPayment(String id) {
        return "";
    }

    /**
     * Danh sách đơn hàng người mua
     *
     * @return
     */
    public static String orderBuyer() {
        return "/user/don-hang-cua-toi.html";
    }

    /**
     * Danh sách đơn hàng người bán
     *
     * @return
     */
    public static String orderSeller() {
        return "/user/hoa-don-ban-hang.html";
    }
}
