package vn.chodientu.controller.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.entity.api.Request;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemDetail;
import vn.chodientu.entity.db.SellerApi;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.Condition;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ItemSource;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.form.ItemForm;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.service.ApiHistoryService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;

@Controller("itemApiController")
@RequestMapping(value = "/api/item")
public class ItemController extends BaseApi {
    
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Gson gson;
    @Autowired
    private ApiHistoryService apiHistoryService;
    
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Response list(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            ItemSearch search = gson.fromJson(data.getParams(), ItemSearch.class);
            if (search == null) {
                return new Response(false, "Tham số chưa chính xác");
            }
            if (search.getPageSize() <= 0) {
                search.setPageSize(100);
            }
            search.setSellerId(user.getId());
            DataPage<Item> dataPage = itemService.search(search);
            for (Item it : dataPage.getData()) {
                List<String> img = new ArrayList<>();
                if (it != null && it.getImages() != null && !it.getImages().isEmpty()) {
                    for (String ig : it.getImages()) {
                        img.add(imageService.getUrl(ig).compress(100).getUrl());
                    }
                    it.setImages(img);
                }
            }
            response = new Response(true, "Danh sách sản phẩm", dataPage);
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }
    
    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    @ResponseBody
    public Response addItem(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            Item item = gson.fromJson(data.getParams(), Item.class);
            if (item == null) {
                return new Response(false, "Tham số chưa chính xác", "PARAM_EMPTY");
            }
            if (item.getSellerSku() == null) {
                return new Response(false, "Link gốc sản phẩm chưa được nhập", "SELLERSKU_EMPTY");
            }
            Item oldItem = itemService.findBySellerSku(item.getSellerSku());
            item.setSellerId(user.getId());
            itemService.add(item);
            response = new Response(true, "Đã tạo sản phẩm thành công trên chợ", item);
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }
    
    @RequestMapping(value = "/addimages", method = RequestMethod.POST)
    @ResponseBody
    public Response addImages(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            ItemForm itemform = gson.fromJson(data.getParams(), ItemForm.class);
            if (itemform == null) {
                return new Response(false, "Tham số chưa chính xác", "PARAM_EMPTY");
            }
            if (itemform.getSellerSku() == null || itemform.getSellerSku().equals("")) {
                return new Response(false, "Link gốc sản phẩm chưa được nhập", "SELLERSKU_EMPTY");
            }
            if (itemform.getImageUrl() == null || itemform.getImageUrl().equals("")) {
                return new Response(false, "Link ảnh cần đăng cho sản phẩm chưa được nhập", "IMAGEURL_EMPTY");
            }
            Item item = itemService.findBySellerSku(itemform.getSellerSku(), user.getId());
            if (item == null) {
                return new Response(false, "Không tìm thấy sản phẩm!");
            }
            List<String> images = item.getImages();
            if (images == null) {
                images = new ArrayList<>();
            }
            if (itemform.getImageUrl() != null && !itemform.getImageUrl().equals("")) {
                if (imageService.checkImage(itemform.getImageUrl(), null, 300, 300, false)) {
                    Response<String> resp = imageService.download(itemform.getImageUrl().trim(), ImageType.ITEM, item.getId());
                    if (resp == null || !resp.isSuccess()) {
                        return new Response(false, resp.getMessage());
                    }
                    images.add(resp.getData());
                } else {
                    return new Response(false, "Ảnh phải có kích thước tối thiểu 1 chiều là 300px");
                }
            } else if (itemform.getImage() != null && itemform.getImage().getSize() > 0) {
                if (imageService.checkImage(null, itemform.getImage(), 300, 300, false)) {
                    Response resp = imageService.upload(itemform.getImage(), ImageType.ITEM, item.getId());
                    if (resp == null || !resp.isSuccess()) {
                        return new Response(false, resp.getMessage());
                    }
                    images.add((String) resp.getData());
                } else {
                    return new Response(false, "Ảnh phải có kích thước tối thiểu 1 chiều là 300px");
                }
            }
            item.setImages(images);
            List<String> img = new ArrayList<>();
            if (item != null && item.getImages() != null && !item.getImages().isEmpty()) {
                for (String ig : item.getImages()) {
                    img.add(imageService.getUrl(ig).compress(100).getUrl());
                }
                item.setImages(img);
            }
            response = new Response(true, "Đã thêm ảnh cho sản phẩm thành công trên chợ", item);
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }
    
    @RequestMapping(value = "/delimage", method = RequestMethod.POST)
    @ResponseBody
    public Response delImage(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            HashMap<String, String> params = gson.fromJson(data.getParams(), new TypeToken<HashMap<String, String>>() {
            }.getType());
            if (params == null) {
                return new Response(false, "Tham số chưa chính xác", "PARAM_EMPTY");
            }
            String sellerSku = params.get("sellerSku");
            String imageUrl = params.get("imageUrl");
            if (sellerSku == null || sellerSku.equals("")) {
                return new Response(false, "Link gốc sản phẩm chưa được nhập", "SELLERSKU_EMPTY");
            }
            Item item = itemService.findBySellerSku(sellerSku, user.getId());
            if (item == null) {
                return new Response(false, "Sản phẩm không tồn trên hệ thống!");
            }
            if (imageUrl == null || imageUrl.equals("")) {
                return new Response(false, "Link ảnh cần xóa của sản phẩm chưa được nhập", "IMAGEURL_EMPTY");
            }
            imageService.deleteByUrl(ImageType.ITEM, item.getId(), imageUrl);
            response = new Response(true, "Xóa ảnh thành công");
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }
    
    @RequestMapping(value = "/addsubmit", method = RequestMethod.POST)
    @ResponseBody
    public Response addSubmit(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            ItemForm params = gson.fromJson(data.getParams(), ItemForm.class);
            if (params == null) {
                return new Response(false, "Tham số chưa chính xác");
            }
            if (params.getSellerSku() == null || params.getSellerSku().equals("")) {
                return new Response(false, "Link gốc sản phẩm chưa được nhập", "SELLERSKU_EMPTY");
            }
            Item item = itemService.findBySellerSku(params.getSellerSku(), user.getId());
            if (item == null) {
                return new Response(false, "Sản phẩm không tồn tại trên hệ thống");
            }
            if (params.getDetail() == null || params.getDetail().equals("")) {
                return new Response(false, "Detail sản phẩm không được để trống!");
            }
            item.setName(params.getName());
            item.setCategoryId(params.getCategoryId());
            item.setQuantity(params.getQuantity());
            item.setListingType(params.getListingType());
            if (item.getCondition() != null && !item.getCondition().equals("")) {
                item.setCondition(params.getCondition());
            } else {
                item.setCondition(Condition.NEW);
            }
            item.setShopCategoryId(params.getShopCategoryId());
            item.setSource(ItemSource.API);
            item.setModelId(params.getModelId());
            item.setManufacturerId(params.getManufacturerId());
            item.setStartPrice(params.getStartPrice());
            item.setSellPrice(params.getSellPrice());
            item.setStartTime(params.getStartTime());
            item.setEndTime(params.getEndTime());
            item.setWeight(params.getWeight());
            item.setBidStep(params.getBidStep());
            item.setShipmentType(params.getShipmentType());
            item.setShipmentPrice(params.getShipmentPrice());
            
            ItemDetail itemDetail = new ItemDetail();
            itemDetail.setItemId(item.getId());
            itemDetail.setDetail(params.getDetail());
            itemService.updateDetail(itemDetail);
            response = itemService.submitAdd(item, null);
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }
    
    @RequestMapping(value = "/getitem", method = RequestMethod.POST)
    @ResponseBody
    public Response getItem(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            HashMap<String, String> params = gson.fromJson(data.getParams(), new TypeToken<HashMap<String, String>>() {
            }.getType());
            if (params == null) {
                return new Response(false, "Tham số chưa chính xác");
            }
            String sellerSku = params.get("sellerSku");
            if (sellerSku == null || sellerSku.equals("")) {
                return new Response(false, "Link gốc sản phẩm chưa được nhập", "SELLERSKU_EMPTY");
            }
            Item item = itemService.findBySellerSku(sellerSku, user.getId());
            item.setImages(imageService.get(ImageType.ITEM, item.getId()));
            List<String> img = new ArrayList<>();
            if (item != null && item.getImages() != null && !item.getImages().isEmpty()) {
                for (String ig : item.getImages()) {
                    img.add(imageService.getUrl(ig).compress(100).getUrl());
                }
                item.setImages(img);
            }
            response = new Response(true, "Thông tin sản phẩm", item);
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }
    
    @RequestMapping(value = "/editsubmit", method = RequestMethod.POST)
    @ResponseBody
    public Response editSubmit(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            ItemForm params = gson.fromJson(data.getParams(), ItemForm.class);
            if (params == null) {
                return new Response(false, "Tham số chưa chính xác");
            }
            if (params.getSellerSku() == null || params.getSellerSku().equals("")) {
                return new Response(false, "Link gốc sản phẩm chưa được nhập", "SELLERSKU_EMPTY");
            }
            Item item = itemService.findBySellerSku(params.getSellerSku(), user.getId());
            if (item == null) {
                return new Response(false, "Sản phẩm không tồn tại!");
            }
            if (item.getListingType() == ListingType.AUCTION) {
                return new Response(false, "Sản phẩm đấu giá không thể sửa!");
            }
            item.setName(params.getName());
            item.setCategoryId(params.getCategoryId());
            item.setQuantity(params.getQuantity());
            item.setListingType(params.getListingType());
            item.setCondition(params.getCondition());
            item.setShopCategoryId(params.getShopCategoryId());
            item.setModelId(params.getModelId());
            item.setManufacturerId(params.getManufacturerId());
            item.setStartPrice(params.getStartPrice());
            item.setSellPrice(params.getSellPrice());
            item.setStartTime(params.getStartTime());
            item.setEndTime(params.getEndTime());
            item.setWeight(params.getWeight());
            item.setBidStep(params.getBidStep());
            item.setShipmentType(params.getShipmentType());
            item.setShipmentPrice(params.getShipmentPrice());
            if (params.getDetail() != null && !params.getDetail().equals("")) {
                ItemDetail itemDetail = new ItemDetail();
                itemDetail.setItemId(item.getId());
                itemDetail.setDetail(params.getDetail());
                itemService.updateDetail(itemDetail);
            }
            
            response = itemService.submitEdit(item, null);
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Response delete(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            HashMap<String, String> params = gson.fromJson(data.getParams(), new TypeToken<HashMap<String, String>>() {
            }.getType());
            if (params == null) {
                return new Response(false, "Tham số chưa chính xác");
            }
            String sellerSku = params.get("sellerSku");
            if (sellerSku == null || sellerSku.equals("")) {
                return new Response(false, "Link gốc sản phẩm chưa được nhập", "SELLERSKU_EMPTY");
            }
            Item item = itemService.findBySellerSku(sellerSku, user.getId());
            if (item == null) {
                return new Response(false, "Không tìm thấy sản phẩm nào!");
            }
            List<String> itemIds = new ArrayList<>();
            itemIds.add(item.getId());
            itemService.updateActive(itemIds, user.getId());
            response = new Response(true, "Xóa sản phẩm thành công,sản phẩm đã được đưa vào thùng rác!");
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public Response search(@RequestBody ItemSearch itemSearch) {
        itemSearch.setStatus(1);
        if (itemSearch.getPageSize() == 0) {
            itemSearch.setPageSize(60);
        }
        DataPage<Item> itemPage = itemService.search(itemSearch);
        List<Item> list = new ArrayList<>();
        for (Item item : itemPage.getData()) {
            List<String> images = new ArrayList<>();
            if (item.getImages() != null && !item.getImages().isEmpty()) {
                for (String img : item.getImages()) {
                    ImageUrl url = imageService.getUrl(img).thumbnail(350, 350, "inset").compress(100);
                    if (url != null && url.getUrl() != null) {
                        images.add(url.getUrl());
                    }
                }
            }
            item.setImages(images);
        }
        return new Response(true, "Danh sách sản phẩm", itemPage);
    }
    
    @RequestMapping(value = "/postitem", method = RequestMethod.POST)
    @ResponseBody
    public Response postItem(@RequestBody Request data, ModelMap model) {
        Response response;
        User user = null;
        try {
            validate(data, model);
            user = (User) model.get("user");
            SellerApi sellerApi = (SellerApi) model.get("sellerApi");
            Item params = gson.fromJson(data.getParams(), Item.class);
            if (params == null) {
                return new Response(false, "Tham số chưa chính xác");
            }
            Item item = itemService.findBySellerSku(params.getSellerSku(), user.getId());
            if (item != null) {
                if (item.getListingType() == ListingType.AUCTION) {
                    return new Response(false, "Sản phẩm đấu giá không thể sửa!");
                }
                item.setQuantity(params.getQuantity());
                item.setStartPrice(params.getStartPrice());
                item.setSellPrice(params.getSellPrice());
                item.setUpdateTime(System.currentTimeMillis());
                item.setStartTime(params.getStartPrice());
                item.setEndTime(item.getEndTime() + 30 * 12 * 60 * 60 * 1000);
                item.setCategoryId(params.getCategoryId());
                item.setShopCategoryId(params.getShopCategoryId());
                if (item.getEndTime() < System.currentTimeMillis()) {
                    item.setEndTime(System.currentTimeMillis() + 30 * 12 * 60 * 60 * 1000);
                }
                
                if (item.getDetail() != null && !item.getDetail().equals("")) {
                    ItemDetail itemDetail = new ItemDetail();
                    itemDetail.setItemId(item.getId());
                    itemDetail.setDetail(params.getDetail());
                    itemService.updateDetail(itemDetail);
                }
                
                response = itemService.submitEdit(item, null);
            } else {
                params.setSellerId(user.getId());
                item = (Item) itemService.add(params).getData();
                try {
                    List<String> images = item.getImages();
                    if (images != null && images.size() > 0) {
                        for (String image : images) {
                            if (image != null && !image.equals("")) {
                                if (imageService.checkImage(image, null, 300, 300, false)) {
                                    Response<String> resp = imageService.download(image.trim(), ImageType.ITEM, item.getId());
                                    if (resp == null || !resp.isSuccess()) {
                                        return new Response(false, resp.getMessage());
                                    }
                                    images.add(resp.getData());
                                } else {
                                    return new Response(false, "Ảnh phải có kích thước tối thiểu 1 chiều là 300px");
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
                if (item.getDetail() != null && !item.getDetail().equals("")) {
                    ItemDetail itemDetail = new ItemDetail();
                    itemDetail.setItemId(item.getId());
                    itemDetail.setDetail(params.getDetail());
                    itemService.updateDetail(itemDetail);
                }
                response = itemService.submitAdd(params, null);
            }
            
        } catch (Exception e) {
            response = new Response(false, e.getMessage());
        }
        apiHistoryService.create(data, user, response);
        return response;
    }
    
}
