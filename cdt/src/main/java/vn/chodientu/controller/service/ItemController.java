package vn.chodientu.controller.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vn.chodientu.component.ImageClient;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.controller.BaseRest;
import vn.chodientu.entity.db.AdministratorRole;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.Image;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemApprove;
import vn.chodientu.entity.db.ItemDetail;
import vn.chodientu.entity.db.ItemProperty;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.Message;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.ModelProperty;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ItemSource;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.form.ItemForm;
import vn.chodientu.entity.input.CategorySearch;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.ModelSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.service.AdministratorService;
import vn.chodientu.service.CashService;
import vn.chodientu.service.CategoryService;
import vn.chodientu.service.ImageService;
import vn.chodientu.service.ItemService;
import vn.chodientu.service.ManufacturerService;
import vn.chodientu.service.MessageService;
import vn.chodientu.service.ModelService;
import vn.chodientu.service.SearchIndexService;
import vn.chodientu.service.SellerService;
import vn.chodientu.service.ShopContactService;
import vn.chodientu.service.ShopService;
import vn.chodientu.service.UpScheduleService;
import vn.chodientu.service.UserService;
import vn.chodientu.util.TextUtils;

@Controller("serviceItem")
@RequestMapping("/item")
public class ItemController extends BaseRest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageClient imageClient;
    @Autowired
    private ManufacturerService manufacturerService;
    @Autowired
    private Gson gson;
    @Autowired
    private CashService cashService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private SearchIndexService searchIndexService;

    @Autowired
    private UserService userService;
    @Autowired
    private ShopContactService shopContactService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private UpScheduleService upScheduleService;

    @RequestMapping(value = "/getbykeyword", method = RequestMethod.POST)
    @ResponseBody
    public Response getByKeyword(ModelMap model, @RequestBody ItemSearch itemSearch) throws Exception {
        ModelSearch modelSearch = new ModelSearch();
        modelSearch.setKeyword(itemSearch.getKeyword());
        modelSearch.setPageIndex(0);
        modelSearch.setPageSize(15);
        modelSearch.setStatus(1);
        DataPage<Model> modelPage = modelService.search(modelSearch);
        List<Model> models = modelPage.getData();
        for (Model mdel : models) {
            List<String> images = new ArrayList<>();
            for (String img : mdel.getImages()) {
                images.add(imageService.getUrl(img).getUrl());
            }
            mdel.setImages(images);
        }

        CategorySearch categorySearch = new CategorySearch();
        categorySearch.setActive(1);
        categorySearch.setLeaf(1);
        categorySearch.setKeyword(itemSearch.getKeyword());
        List<Category> categoris = categoryService.seach(categorySearch);
        for (Category category : categoris) {
            category.setCategoris(categoryService.getCategories(category.getPath()));
        }
        List<Category> cates = new ArrayList<>();
        if (viewer != null && viewer.getUser() != null) {
            List<String> recentPostsCategory = itemService.recentPostsCategory(viewer.getUser().getId());
            cates = categoryService.get(recentPostsCategory).subList(0, recentPostsCategory.size() >= 20 ? 20 : recentPostsCategory.size());
            for (Category category : cates) {
                category.setCategoris(categoryService.getCategories(category.getPath()));
            }
        }

        HashMap<String, Object> hm = new HashMap<>();
        hm.put("models", models);
        hm.put("categories", categoris);
        hm.put("currentcategories", cates);
        return new Response(true, "Danh sách model và danh mục", hm);
    }

    /**
     * Lấy gợi ý model
     *
     * @param model
     * @param itemSearch
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getmodelsuggest", method = RequestMethod.POST)
    @ResponseBody
    public Response modelSuggest(ModelMap model, @RequestBody ItemSearch itemSearch) throws Exception {
        ModelSearch modelSearch = new ModelSearch();
        modelSearch.setKeyword(itemSearch.getKeyword());
        modelSearch.setPageIndex(0);
        modelSearch.setPageSize(15);
        modelSearch.setStatus(1);
        DataPage<Model> modelPage = modelService.search(modelSearch);
        List<Model> models = modelPage.getData();
        for (Model mdel : models) {
            List<String> images = new ArrayList<>();
            for (String img : mdel.getImages()) {
                images.add(imageService.getUrl(img).getUrl());
            }
            mdel.setImages(images);
        }

        return new Response(true, "ok", models);
    }

    /**
     * Lấy gợi ý danh mục
     *
     * @param model
     * @param itemSearch
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getcategorysuggest", method = RequestMethod.POST)
    @ResponseBody
    public Response categorySuggest(ModelMap model, @RequestBody ItemSearch itemSearch) throws Exception {
        CategorySearch categorySearch = new CategorySearch();
        categorySearch.setActive(1);
        categorySearch.setLeaf(1);
        categorySearch.setKeyword(itemSearch.getKeyword());
        List<Category> categoris = categoryService.seach(categorySearch);
        for (Category category : categoris) {
            category.setCategoris(categoryService.getCategories(category.getPath()));
        }

        return new Response(true, "ok", categoris);
    }

    /**
     * SubmitAdd hoàn thành thêm sản phẩm
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    @ResponseBody
    public Response submitItem(@RequestParam("id") String id) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Đăng nhập lại để lưu thông tin");
        }
        Item item = itemService.get(id);
        item.setSellerId(viewer.getUser().getId());
        item.setSource(ItemSource.SELLER);
        return itemService.submitAdd(item, null);
    }

    /**
     * Hoàn thành sửa sản phẩm
     *
     * @param id
     * @param same
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/submitedit", method = RequestMethod.GET)
    @ResponseBody
    public Response submitEdit(@RequestParam("id") String id, @RequestParam(value = "same", defaultValue = "0") int same) throws Exception {
        if (viewer == null || viewer.getUser() == null) {
            return new Response(false, "Đăng nhập lại để lưu thông tin");
        }
        Item item = itemService.get(id);
        item.setSellerId(viewer.getUser().getId());
        Response submitEdit = itemService.submitEdit(item, null);
        if (submitEdit.isSuccess() && same == 1) {
            return new Response(true, "Đăng bán sản phẩm tương tự thành công!", submitEdit.getData());
        }
        return submitEdit;
    }

    /**
     * Lưu thông tin sản phẩm
     *
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response add(@RequestBody ItemForm form) throws Exception {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        Item item = new Item();
        if (form.getId() != null && !form.getId().trim().equals("")) {
            item = itemService.get(form.getId());
        }
        item.setId(form.getId());
        item.setName(form.getName());
        item.setCategoryId(form.getCategoryId());
        item.setShopCategoryId(form.getShopCategoryId());
        item.setManufacturerId(form.getManufacturerId());
        item.setModelId(form.getModelId());
        item.setListingType(form.getListingType());
        item.setStartPrice(form.getStartPrice());
        item.setSellPrice(form.getSellPrice());
        item.setBidStep(form.getBidStep());
        item.setShipmentPrice(form.getShipmentPrice());
        item.setShipmentType(form.getShipmentType());
        item.setQuantity(form.getQuantity());
        item.setCondition(form.getCondition());
        item.setActive(true);
        item.setWeight(form.getWeight());
        item.setStartTime(form.getStartTime() == 0 ? System.currentTimeMillis() : form.getStartTime());
        if (item.getListingType() == ListingType.BUYNOW) {
            item.setEndTime(item.getStartTime() + form.getEndTime() * 24 * 60 * 60 * 1000);
        } else {
            item.setEndTime(form.getEndTime());
        }
        Seller seller = sellerService.getById(viewer.getUser().getId());
        item.setCod(seller.isScIntegrated());
        item.setOnlinePayment(seller.isNlIntegrated());

        item.setCityId(viewer.getUser().getCityId());
        item.setDistrictId(viewer.getUser().getDistrictId());
        item.setSellerName(viewer.getUser().getName());
        item.setSellerId(viewer.getUser().getId());
        if (item.getId() == null || item.getId().equals("")) {
            return itemService.add(item);
        }
        Response resp = itemService.edit(item, null);
        if (resp.isSuccess()) {
            if (form.getDetail() != null && !form.getDetail().equals("")) {
                ItemDetail itemDetail = new ItemDetail();
                itemDetail.setItemId(item.getId());
                itemDetail.setDetail(form.getDetail());
                itemService.updateDetail(itemDetail);
            }
            if (form.getPropertis() != null && !form.getPropertis().isEmpty()) {
                List<ItemProperty> ips = new ArrayList<>();
                ItemProperty itemProperty = null;
                for (ModelProperty modelProperty : form.getPropertis()) {
                    itemProperty = new ItemProperty();
                    itemProperty.setCategoryPropertyId(modelProperty.getCategoryPropertyId());
                    itemProperty.setCategoryPropertyValueIds(modelProperty.getCategoryPropertyValueIds());
                    itemProperty.setInputValue(modelProperty.getInputValue());
                    itemProperty.setItemId(item.getId());
                    ips.add(itemProperty);
                }
                itemService.updateProperties(item.getId(), ips);
            }
        }
        return resp;
    }

    /**
     * Thêm mới ảnh
     *
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addimage", method = RequestMethod.POST)
    @ResponseBody
    public Response addImage(@ModelAttribute ItemForm form) throws Exception {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        Item item = itemService.get(form.getId());
        if (item == null) {
            return new Response(false, "Sản phẩm chưa được tạo, bạn vui lòng thử lại sau vài giây");
        }
        if (!item.getSellerId().equals(viewer.getUser().getId())) {
            return new Response(false, "Sản phẩm này không phải của bạn");
        }
        List<String> images = item.getImages();
        if (images == null) {
            images = new ArrayList<>();
        }
        if (form.getImageUrl() != null && !form.getImageUrl().equals("")) {
            if (imageService.checkImage(form.getImageUrl(), null, 300, 300, false)) {
                Response<String> resp = imageService.download(form.getImageUrl().trim(), ImageType.ITEM, item.getId());
                if (resp == null || !resp.isSuccess()) {
                    return new Response(false, resp.getMessage());
                }
                images.add(resp.getData());
            } else {
                return new Response(false, "Ảnh phải có kích thước tối thiểu 1 chiều là 300px");
            }
        } else if (form.getImage() != null && form.getImage().getSize() > 0) {
            if (imageService.checkImage(null, form.getImage(), 300, 300, false)) {
                Response resp = imageService.upload(form.getImage(), ImageType.ITEM, item.getId());
                if (resp == null || !resp.isSuccess()) {
                    return new Response(false, resp.getMessage());
                }
                images.add((String) resp.getData());
            } else {
                return new Response(false, "Ảnh phải có kích thước tối thiểu 1 chiều là 300px");
            }
        }
        item.setImages(images);
        return new Response(true, "Chi tiết sản phẩm", item);
    }

    @RequestMapping(value = "/addimage", method = RequestMethod.GET)
    @ResponseBody
    public Response addImageUrl(@RequestParam("itemId") String itemId, @RequestParam("img") String img) throws Exception {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        Item item = itemService.get(itemId);
        if (item == null) {
            return new Response(false, "Sản phẩm chưa được tạo, bạn vui lòng thử lại sau vài giây");
        }
        if (!item.getSellerId().equals(viewer.getUser().getId())) {
            return new Response(false, "Sản phẩm này không phải của bạn");
        }
        List<String> images = item.getImages();
        if (images == null) {
            images = new ArrayList<>();
        }
        Response<String> resp = imageService.download(img.trim(), ImageType.ITEM, itemId);
        if (resp == null || !resp.isSuccess()) {
            return new Response(false, resp.getMessage());
        }
        images.add(resp.getData());
        return new Response(true, "Chi tiết sản phẩm", item);
    }

    /**
     * Xóa 1 ảnh
     *
     * @param id
     * @param imageName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delimage", method = RequestMethod.GET)
    @ResponseBody
    public Response delImage(@RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "name", defaultValue = "") String imageName) throws Exception {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn chưa đăng nhập");
        }
        Item item = itemService.get(id);
        if (item == null) {
            return new Response(false, "Sản phẩm chưa được tạo, bạn vui lòng thử lại sau vài giây");
        }
        if (!item.getSellerId().equals(viewer.getUser().getId())) {
            return new Response(false, "Sản phẩm này không phải của bạn");
        }
        if (imageName == null || imageName.trim().equals("")) {
            return new Response(false, "Phải chọn ảnh để xóa");
        }
        imageService.deleteById(ImageType.ITEM, item.getId(), imageName);
        try {
            return new Response(true, "Chi tiết sản phẩm", item);
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    @RequestMapping(value = "/countitem", method = RequestMethod.GET)
    @ResponseBody
    public Response countItem(@RequestParam(value = "tab", defaultValue = "all") String tab) {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn cần đăng nhập lại để thực hiện chức năng này");
        }
        return new Response(true, "ok", itemService.getItemStatusHistogram(viewer.getUser().getId()));
    }

    @RequestMapping(value = "/inactive", method = RequestMethod.GET)
    @ResponseBody
    public Response inActive(@RequestParam(value = "ids", defaultValue = "") String ids) {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn cần đăng nhập lại để thực hiện chức năng này");
        }
        try {
            List<String> itemIds = gson.fromJson(ids, new TypeToken<List<String>>() {
            }.getType());
            itemService.updateActive(itemIds, viewer.getUser().getId());
            return new Response(true, "Xóa sản phẩm thành công, đồng ý để thực hiện chức năng khác");
        } catch (Exception e) {
            return new Response(false, "Danh sách mã sản phẩm không chính xác");
        }
    }

    @RequestMapping(value = "/stopselling", method = RequestMethod.GET)
    @ResponseBody
    public Response stopSelling(@RequestParam(value = "ids", defaultValue = "") String ids) {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn cần đăng nhập lại để thực hiện chức năng này");
        }
        try {
            List<String> itemIds = gson.fromJson(ids, new TypeToken<List<String>>() {
            }.getType());
            itemService.stopSelling(itemIds, viewer.getUser().getId());
            return new Response(true, "Sản phẩm đã được ngừng bán, đồng ý để thực hiện chức năng khác");
        } catch (Exception e) {
            return new Response(false, "Danh sách mã sản phẩm không chính xác");
        }
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    @ResponseBody
    public Response remove(@RequestParam(value = "ids", defaultValue = "") String ids) {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn cần đăng nhập lại để thực hiện chức năng này");
        }
        try {
            List<String> itemIds = gson.fromJson(ids, new TypeToken<List<String>>() {
            }.getType());
            itemService.remove(itemIds, viewer.getUser().getId());
            return new Response(true, "Xóa sản phẩm thành công, đồng ý để thực hiện chức năng khác");
        } catch (Exception e) {
            return new Response(false, "Danh sách mã sản phẩm không chính xác");
        }
    }

    @RequestMapping(value = "/quickedit", method = RequestMethod.POST)
    @ResponseBody
    public Response quickEdit(@RequestBody ItemForm form) {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn cần đăng nhập lại để thực hiện chức năng này");
        }
        Item item = new Item();
        item.setId(form.getId());
        item.setName(form.getName());
        item.setQuantity(form.getQuantity());
        item.setWeight(form.getWeight());
        item.setSellPrice(form.getSellPrice());
        item.setStartPrice(form.getStartPrice());
        item.setSellerId(viewer.getUser().getId());
        try {
            item = itemService.quickEdit(item, null, form.getFieldUpdate());
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }

        return new Response(true, "Chi tiết sản phẩm", item);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Response get(@RequestParam(value = "id", defaultValue = "") String id) throws Exception {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn cần đăng nhập lại để thực hiện chức năng này");
        }
        try {
            HashMap<String, Object> itemInfo = new HashMap<>();
            Item item = itemService.get(id);
            ItemDetail detail = itemService.getDetail(id);
            if (detail == null) {
                detail = new ItemDetail();
            }
            itemInfo.put("item", item);
            itemInfo.put("itemDetail", detail);
            itemInfo.put("properties", itemService.getProperties(id));
            Manufacturer manufacturer = null;
            try {
                manufacturer = manufacturerService.getManufacturer(item.getManufacturerId());
            } catch (Exception e) {
            }
            itemInfo.put("manufacturer", manufacturer);
            return new Response(true, "Chi tiết sản phẩm", itemInfo);
        } catch (Exception e) {
            return new Response(true, e.getMessage());
        }
    }

    /**
     * lấy danh sách sản phẩm theo list category
     *
     * @thunt
     * @param cids
     * @param keyword
     * @param pageIndex
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getitembycateids", method = RequestMethod.GET)
    public Response getItemByCateIds(@RequestParam(value = "cids", defaultValue = "0") String cids,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int pageIndex) throws Exception {
        if (viewer.getUser() == null || viewer.getUser().getId() == null) {
            return new Response(false, "Phải đăng nhập");
        }
        List<String> cateIds = gson.fromJson(cids, ArrayList.class);
        List<String> listCate = new ArrayList<>();
        for (int i = 0; i < cateIds.size(); i++) {
            for (int j = i + 1; j < cateIds.size(); j++) {
                if (cateIds.get(i).equals(cateIds.get(j)) && j != i) {
                    listCate.add(cateIds.get(i));
                }
            }
        }
        for (int i = 0; i < listCate.size(); i++) {
            for (int j = 0; j < cateIds.size(); j++) {
                if (listCate.get(i).equals(cateIds.get(j))) {
                    cateIds.remove(j);
                    j--;
                }
            }
        }
        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setKeyword(keyword);
        itemSearch.setCategoryIds(cateIds);
        itemSearch.setListingType(ListingType.BUYNOW);
        itemSearch.setSellerId(viewer.getUser().getId());
        itemSearch.setPageIndex(pageIndex);
        itemSearch.setPageSize(5);
        itemSearch.setStatus(1);
        DataPage<Item> itemdata = itemService.search(itemSearch);
        List<String> image = null;
        for (Item item : itemdata.getData()) {
            image = new ArrayList<>();
            for (String img : item.getImages()) {
                image.add(imageService.getUrl(img).getUrl());
            }
            item.setImages(image);
        }
        return new Response(true, "ok", itemdata);
    }

    @ResponseBody
    @RequestMapping(value = "/copyimagebymodel", method = RequestMethod.GET)
    public Response copyImageByModel(@RequestParam(value = "id", defaultValue = "") String id,
            @RequestParam(value = "modelId", defaultValue = "") String modelId) {
        try {
            Item item = itemService.get(id);
            List<String> iImages = item.getImages();
            if (iImages == null) {
                iImages = new ArrayList<>();
            }
            if (modelId != null) {
                Model model = modelService.getModel(modelId);
                List<String> images = model.getImages();
                if (images == null) {
                    return new Response(false, "Model không có ảnh");
                }
                Response resp = null;
                for (String mImg : images) {
                    resp = imageService.download(imageService.getUrl(mImg.trim()).getUrl(), ImageType.ITEM, item.getId());
                    if (resp != null && resp.isSuccess()) {
                        iImages.add((String) resp.getData());
                    }
                }
                item.setImages(iImages);
            }
            return new Response(true, "Chi tiết sản phẩm", item);
        } catch (Exception e) {
            return new Response(false, "Không tìm thấy thông tin yêu cầu");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/genimages", method = RequestMethod.GET)
    public Response genImages(@RequestParam(value = "ids", defaultValue = "") String ids) throws Exception {
        HashMap<String, String> images = new HashMap<>();
        List<String> imgIds = gson.fromJson(ids, ArrayList.class);
        for (String imgId : imgIds) {
            images.put(imgId, imageService.getUrl(imgId).getUrl());
        }
        return new Response(true, "Danh sách link ảnh", images);
    }

    @ResponseBody
    @RequestMapping(value = "/getitems", method = RequestMethod.GET)
    public Response getItemByIds(@RequestParam(value = "ids") String listId) throws Exception {
        if (listId.equals("") && listId == null) {
            return new Response(false, "Lỗi", "Dữ liệu lỗi");
        }
        User checkUser = viewer.getUser();
        if (checkUser == null) {
            return new Response(false, "Lỗi", "Bạn cần đăng nhập lại để thực hiện thao tác này");
        }

        List<String> listItemIds = gson.fromJson(listId, new TypeToken<List<String>>() {
        }.getType());
        List<Item> checkList = itemService.list(listItemIds);
        List<String> cateIds = new ArrayList<>();
        for (Item item : checkList) {
            List<String> image = new ArrayList<>();
            for (String imgs : item.getImages()) {
                image.add(imageService.getUrl(imgs).thumbnail(100, 100, "outbound").getUrl());
            }
            item.setImages(image);
            cateIds.add(item.getCategoryPath().get(0));
        }
        if (cateIds != null && !cateIds.isEmpty()) {
            List<Category> categorys = categoryService.get(cateIds);
            for (Category category : categorys) {
                for (Item item : checkList) {
                    if (item.getCategoryPath().get(0).equals(category.getId())) {
                        item.setCategoryName(category.getName());
                    }

                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xeng", cashService.getCash(viewer.getUser().getId()).getBalance());
        map.put("items", checkList);
        return new Response(true, "Đang load item ra", map);

    }

    @ResponseBody
    @RequestMapping(value = "/getitem", method = RequestMethod.GET)
    public Response getItem(@RequestParam(value = "id") String id) throws Exception {
        if (id == null || id.trim().equals("")) {
            return new Response(false, "Sản phẩm không tồn tại");
        }
        Item item = itemService.get(id);
        if (item == null) {
            return new Response(false, "Sản phẩm không tồn tại");
        }
        List<String> image = new ArrayList<>();
        for (String imgs : item.getImages()) {
            image.add(imageService.getUrl(imgs).thumbnail(318, 400, "outbound").getUrl());
        }
        item.setImages(image);

        return new Response(true, "ok", item);
    }

    @ResponseBody
    @RequestMapping(value = "/downloadfromeditor", method = RequestMethod.GET)
    public Response processDownloadImageFromEditor(@RequestParam(value = "imgs") String jsonImgs, @RequestParam(value = "id") String itemId) {
        if (jsonImgs.equals("")) {
            return new Response(false, "FAILED", jsonImgs);
        } else {
            List<String> imgs = gson.fromJson(jsonImgs, new TypeToken<List<String>>() {
            }.getType());
            Map<String, String> returnImgs = new HashMap<>();
            Map<String, Object> dataReturn = new HashMap<>();
            Map<String, String> newImgs = new HashMap<>();
            for (String img : imgs) {
                if (imageService.checkImage(img, null, 300, 300, false) && !img.contains(imageClient.getServerUrls()[0])) {
                    Response<String> resp = imageService.download(img.trim(), ImageType.ITEM, itemId);
                    if (resp.isSuccess()) {
                        returnImgs.put(img, imageService.getUrl(resp.getData()).getUrl());
                        newImgs.put(resp.getData(), imageService.getUrl(resp.getData()).getUrl());
                    }
                }
            }
            dataReturn.put("images", newImgs);
            dataReturn.put("imagesMapping", returnImgs);
            return new Response(true, "OK", dataReturn);
        }
    }

    /**
     * set ảnh đại diện cho sản phẩm
     *
     * @param imageId
     * @param itemId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setdefaultimage", method = RequestMethod.GET)
    public Response setDefaultImage(@RequestParam(value = "imageId") String imageId, @RequestParam(value = "id") String itemId) {
        if (imageId != null && !imageId.equals("") && itemId != null && !itemId.equals("")) {
            imageService.resetFirstImage(imageId, itemId, ImageType.ITEM);
            Image firstImage = imageService.getFirstImage(itemId, ImageType.ITEM);
            if (firstImage != null) {
                return new Response(true, "", firstImage.getImageId());
            }
        }
        return new Response(false, "Chọn ảnh đại diện thất bại. Vui lòng thử lại");
    }

    /**
     * lấy ảnh đại diện cho sản phẩm
     *
     * @param itemId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getdefaultimage", method = RequestMethod.GET)
    public Response getDefaultImage(@RequestParam(value = "id") String itemId) {
        if (itemId != null && !itemId.equals("")) {
            Image firstImage = imageService.getFirstImage(itemId, ImageType.ITEM);
            if (firstImage != null) {
                return new Response(true, "", firstImage.getImageId());
            }
        }
        return new Response(false, "Sản phẩm không tồn tại hoặc chưa có ảnh");
    }

    /**
     * cắt ảnh đại diện
     *
     * @param itemId
     * @param x
     * @param y
     * @param width
     * @param height
     * @param imageId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cropdefaultimage", method = RequestMethod.GET)
    public Response cropDefaultImage(@RequestParam(value = "id") String itemId,
            @RequestParam(value = "x") int x,
            @RequestParam(value = "y") int y,
            @RequestParam(value = "width") int width,
            @RequestParam(value = "height") int height,
            @RequestParam(value = "imageId") String imageId) {
        if (itemId != null && !itemId.equals("") && imageId != null && !imageId.equals("")) {
            if (width >= 300 || height >= 300) {
                String crop = imageService.getUrl(imageId).crop(x, y, width, height).thumbnail(width, height, "outbound").getUrl();
                Response download = imageService.download(crop, ImageType.ITEM, itemId);
                if (download.isSuccess()) {
                    Image lastImage = imageService.getLastImage(itemId, ImageType.ITEM);
                    if (lastImage != null) {
                        imageService.resetFirstImage(lastImage.getImageId(), itemId, ImageType.ITEM);
                        imageService.deleteById(ImageType.ITEM, itemId, imageId);

                        return new Response(true, lastImage.getImageId(), imageService.getUrl(lastImage.getImageId()).getUrl());
                    }
                }
            } else {
                return new Response(false, "Ảnh cắt phải có kích thước tối thiểu 1 chiều là 300px");
            }
        }
        return new Response(false, "Cắt ảnh thất bại. Vui lòng thử lại");
    }

    /**
     * lấy sản phẩm theo điều kiện
     *
     * @param itemSearch
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getotheritem", method = RequestMethod.POST)
    public Response getOtherItems(@RequestBody ItemSearch itemSearch) throws Exception {

        itemSearch.setStatus(1);
        DataPage<Item> search = itemService.search(itemSearch);
        List<String> sellerIds = new ArrayList<>();

        if (search.getData() != null && !search.getData().isEmpty()) {
            for (Item item : search.getData()) {
                sellerIds.add(item.getSellerId());
                if (item.getImages() != null && !item.getImages().isEmpty()) {
                    List<String> image = new ArrayList<>();
                    for (String imgs : item.getImages()) {
                        image.add(imageService.getUrl(imgs).thumbnail(318, 400, "inset").getUrl());
                    }
                    item.setImages(image);
                }
            }
        }

        List<Shop> shops = shopService.getShops(sellerIds);

        Map<String, Object> map = new HashMap<>();
        map.put("itemPage", search);
        map.put("shops", shops);

        return new Response(true, "ok", map);
    }

    /**
     * Lấy item + thông tin support shop/seller
     *
     * @param itemSearch
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getmodelitems", method = RequestMethod.POST)
    public Response getModelItems(@RequestBody ItemSearch itemSearch) throws Exception {

        List<Shop> shops;
        List<String> shopsId = new ArrayList<>();

        DataPage<Item> itemPage = null;
        itemSearch.setPageIndex(itemSearch.getPageIndex());
        itemSearch.setPageSize(15);
        itemSearch.setStatus(1);
        if (itemSearch.getCityIds() == null) {
            itemSearch.setCityIds(new ArrayList<String>());
        }
        itemPage = itemService.search(itemSearch);
        for (Item item : itemPage.getData()) {
            if (item.getSellerId() != null && !"".equals(item.getSellerId()) && !shopsId.contains(item.getSellerId())) {
                shopsId.add(item.getSellerId());
            }
        }
        shops = shopService.getShops(shopsId);
        if (shops != null && !shops.isEmpty()) {
            for (Shop shop : shops) {
                shop.setShopContacts(shopContactService.getContactById(shop.getUserId()));
                if (shop.getLogo() != null && !shop.getLogo().equals("")) {
                    ImageUrl url = imageService.getUrl(shop.getLogo());
                    if (url != null && url.getUrl() != null) {
                        shop.setLogo(url.getUrl());
                    }
                }
            }
        }

        List<User> sellers = userService.getUserByIds(shopsId);
        if (sellers != null && !sellers.isEmpty()) {
            for (User user : sellers) {
                if (user.getAvatar() != null && !user.getAvatar().equals("")) {
                    ImageUrl url = imageService.getUrl(user.getAvatar());
                    if (url != null && url.getUrl() != null) {
                        user.setAvatar(url.getUrl());
                    }
                }
            }
        }
        for (Item item : itemPage.getData()) {
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
        Map<String, Object> map = new HashMap<>();
        map.put("itemPage", itemPage);
        map.put("shops", shops);
        map.put("sellers", sellers);
        map.put("itemSearch", itemSearch);

        return new Response(true, "ok", map);
    }

    @ResponseBody
    @RequestMapping(value = "/refreshitem", method = RequestMethod.GET)
    public Response refreshItem(@RequestParam(value = "ids", defaultValue = "") String ids) {
        try {
            List<String> itemIds = gson.fromJson(ids, new TypeToken<List<String>>() {
            }.getType());
            return itemService.refresh(itemIds);
        } catch (Exception e) {
            return new Response(false, "Không tồn tại sản phẩm");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Response seach(@ModelAttribute ItemSearch itemSearch) {
        itemSearch.setPageIndex(itemSearch.getPageIndex() > 0 ? itemSearch.getPageIndex() - 1 : 0);
        //itemSearch.setPageSize(5);
        itemSearch.setSellerId(viewer.getUser().getId());
        DataPage<Item> dataPage = itemService.search(itemSearch);
        List<Item> products = dataPage.getData();
        // get item image
        List<String> imgs = null;
        for (Item item : products) {
            imgs = new ArrayList<>();
            for (String img : item.getImages()) {
                try {
                    imgs.add(imageService.getUrl(img).thumbnail(100, 100, "inset").getUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            item.setImages(imgs);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("dataPage", dataPage);
        map.put("itemSearch", itemSearch);
        map.put("products", products);
        //map.put("clientScript", "shophomeitem.init();");
        return new Response(true, "Danh sách sản phẩm", map);
    }

    @ResponseBody
    @RequestMapping(value = "/getitembyids", method = RequestMethod.POST)
    public Response getItemByIds(@RequestBody List<String> ids, @RequestParam(value = "positionShow", defaultValue = "") String positionShow) {
        List<String> sellerIds = new ArrayList<>();
        List<String> imgs = null;
        List<Item> listItem = itemService.list(ids);
        for (Item item : listItem) {
            sellerIds.add(item.getSellerId());
            imgs = new ArrayList<>();
            for (String img : item.getImages()) {
                try {
                    imgs.add(imageService.getUrl(img).thumbnail(318, 400, "inset").getUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            item.setImages(imgs);
        }
        Map<String, Object> map = new HashMap<>();
        if (positionShow != null && !positionShow.equals("iconfloatLeft")) {
            DataPage<Item> page = new DataPage<>();
            page.setPageSize(4);
            page.setPageIndex(0);
            page.setData(listItem);
            map.put("shops", shopService.getShops(sellerIds));
            map.put("itemviewdPage", page);
        } else {
            map.put("itemviewdList", listItem);
        }
        return new Response(true, "OK", map);
    }

    @ResponseBody
    @RequestMapping(value = "/countitembyitemsearch", method = RequestMethod.POST)
    public Response countItemByitemSearch(@RequestBody ItemSearch itemSearch) throws Exception {
        itemSearch.setStatus(1);
        DataPage<Item> search = itemService.search(itemSearch);
        return new Response(true, "ok", search);
    }

    @ResponseBody
    @RequestMapping(value = "/sendquestion", method = RequestMethod.POST)
    public Response sendQuestion(@RequestBody Message message) throws Exception {
        try {
            String emailTo = "";
            User user = userService.get(message.getToUserId());
            if (user != null) {
                emailTo = user.getEmail();
            }
            return messageService.send(emailTo, message.getSubject(), message.getContent(), null, message.getItemId());
        } catch (Exception e) {
            return new Response(false, null, e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/closeadv", method = RequestMethod.GET)
    public Response closeAdv() {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        try {
            Seller seller = sellerService.closeAdv(viewer.getUser().getId());
            return new Response(true, "Tắt quảng cáo thành công", seller);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/disapproved", method = RequestMethod.GET)
    public Response disapproved(@RequestParam(value = "itemId", defaultValue = "") String itemId, @RequestParam(value = "message", defaultValue = "") String message) {
        boolean checkRole = false;
        try {
            List<AdministratorRole> roles = administratorService.getRoles(viewer.getAdministrator().getId());
            for (AdministratorRole administratorRole : roles) {
                if (administratorRole.getFunctionUri().equals("/cp/item")) {
                    checkRole = true;
                }
            }
            if (!checkRole) {
                return new Response(false, "Bạn không có quyền thực hiện thao tác này!");
            }
            itemService.disapproved(itemId, viewer.getAdministrator().getId(), message);
            return new Response(true, "Bỏ duyệt sản phẩm thành công!");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/approved", method = RequestMethod.GET)
    public Response approved(@RequestParam(value = "itemId", defaultValue = "") String itemId) {
        boolean checkRole = false;
        try {
            List<AdministratorRole> roles = administratorService.getRoles(viewer.getAdministrator().getId());
            for (AdministratorRole administratorRole : roles) {
                if (administratorRole.getFunctionUri().equals("/cp/item")) {
                    checkRole = true;
                }
            }
            if (!checkRole) {
                return new Response(false, "Bạn không có quyền thực hiện thao tác này!");
            }
            itemService.approved(itemId, viewer.getAdministrator().getId());
            return new Response(true, "Duyệt sản phẩm thành công!");
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getnoteapproveditem", method = RequestMethod.GET)
    public Response getnNoteApprovedItem(@RequestParam(value = "ids", defaultValue = "") String ids) {
        try {
            List<String> itemIds = gson.fromJson(ids, new TypeToken<List<String>>() {
            }.getType());
            List<ItemApprove> itemApproveByIds = itemService.getItemApproveByIds(itemIds);
            return new Response(true, "", itemApproveByIds);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/upfree", method = RequestMethod.GET)
    public Response upfree(@RequestParam(value = "id", defaultValue = "") String id) {
        if (viewer.getUser() == null) {
            return new Response(false, "Bạn phải đăng nhập để thực hiện chức năng này!");
        }
        long up = upScheduleService.upFreeDay(TextUtils.getTime(System.currentTimeMillis(), false),
                TextUtils.getTime(System.currentTimeMillis(), true), viewer.getUser().getId());
        try {
            Item item = itemService.get(id);
            if (!item.getSellerId().equals(viewer.getUser().getId())) {
                return new Response(false, "Sản phẩm không phải của bạn");
            }
            if (up > 5) {
                return new Response(false, "Bạn chỉ có 5 lần up tin miễn phí trong một ngày");
            }
            upScheduleService.upNow(item, true);
            return new Response(true, "Đưa tin bán lên đầu danh mục thành công", up + 1);
        } catch (Exception e) {
            return new Response(false, e.getMessage(), up);
        }
    }

}
