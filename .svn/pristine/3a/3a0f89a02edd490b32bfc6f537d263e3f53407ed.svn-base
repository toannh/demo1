package vn.chodientu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.elasticsearch.index.query.AndFilterBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.NestedFilterBuilder;
import org.elasticsearch.index.query.OrFilterBuilder;
import org.elasticsearch.index.query.QueryFilterBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeFilterBuilder;
import org.elasticsearch.index.query.TermFilterBuilder;
import org.elasticsearch.index.query.TermsFilterBuilder;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.filter.FilterFacet;
import org.elasticsearch.search.facet.filter.FilterFacetBuilder;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.elasticsearch.search.facet.terms.TermsFacetBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.facet.request.NativeFacetRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.component.imboclient.Url.ImageUrl;
import vn.chodientu.entity.db.CashHistory;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.CategoryManufacturer;
import vn.chodientu.entity.db.CategoryProperty;
import vn.chodientu.entity.db.CategoryPropertyValue;
import vn.chodientu.entity.db.City;
import vn.chodientu.entity.db.Image;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemApprove;
import vn.chodientu.entity.db.ItemDetail;
import vn.chodientu.entity.db.ItemProperty;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.Seller;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.db.User;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.Condition;
import vn.chodientu.entity.enu.ImageType;
import vn.chodientu.entity.enu.ItemApproveStatus;
import vn.chodientu.entity.enu.ItemHistoryType;
import vn.chodientu.entity.enu.ItemSource;
import vn.chodientu.entity.enu.ItemUpdaterType;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.enu.PropertyOperator;
import vn.chodientu.entity.enu.SellerHistoryType;
import vn.chodientu.entity.enu.ShipmentType;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.input.ModelSearch;
import vn.chodientu.entity.input.PropertySearch;
import vn.chodientu.entity.output.CategoryHistogram;
import vn.chodientu.entity.output.CityHistogram;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.ItemHistogram;
import vn.chodientu.entity.output.ManufacturerHistogram;
import vn.chodientu.entity.output.ModelHistogram;
import vn.chodientu.entity.output.PropertyHistogram;
import vn.chodientu.entity.output.PropertyValueHistogram;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.search.FacetResult;
import vn.chodientu.repository.CashHistoryRepository;
import vn.chodientu.repository.CategoryManufacturerRepository;
import vn.chodientu.repository.CategoryPropertyRepository;
import vn.chodientu.repository.CategoryPropertyValueRepository;
import vn.chodientu.repository.CategoryRepository;
import vn.chodientu.repository.CityRepository;
import vn.chodientu.repository.ItemApproveRepository;
import vn.chodientu.repository.ItemDetailRepository;
import vn.chodientu.repository.ItemPropertyRepository;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.ItemSearchRepository;
import vn.chodientu.repository.ManufacturerRepository;
import vn.chodientu.repository.ModelRepository;
import vn.chodientu.repository.SellerRepository;
import vn.chodientu.repository.ShopCategoryRepository;
import vn.chodientu.repository.ShopRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;
import vn.chodientu.util.UrlUtils;

/**
 * @since May 9, 2014
 * @author Phu
 */
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemPropertyRepository itemPropertyRepository;
    @Autowired
    private ItemDetailRepository itemDetailRepository;
    @Autowired
    private ItemApproveRepository itemApproveRepository;
    @Autowired
    private CashService cashService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryPropertyRepository categoryPropertyRepository;
    @Autowired
    private CategoryPropertyValueRepository categoryPropertyValueRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ItemSearchRepository itemSearchRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CategoryManufacturerRepository categoryManufacturerRepository;
    @Autowired
    private SearchIndexService searchIndexService;
    @Autowired
    private ShopCategoryRepository shopCategoryRepository;
    @Autowired
    private ItemHistoryService itemHistoryService;
    @Autowired
    private SellerHistoryService sellerHistoryService;
    @Autowired
    private CashHistoryRepository cashHistoryRepository;

    /**
     * Thêm mới tạm thời thông tin cơ bản của sản phẩm
     *
     * @param item
     * @return Kết quả kèm sản phẩm mới thêm hoặc báo lỗi
     * @throws Exception
     */
    public Response add(Item item) {
        Category category = null;
        ShopCategory shopCategory = null;
        if (item.getId() == null || item.getId().equals("")) {
            item.setId(itemRepository.genId());
            item.setCreateTime(System.currentTimeMillis());
        }
        item.setUpdateTime(System.currentTimeMillis());
        if (item.getCategoryId() != null && !item.getCategoryId().equals("")) {
            category = categoryRepository.find(item.getCategoryId());
        }
        if (item.getShopCategoryId() != null && !item.getShopCategoryId().equals("")) {
            shopCategory = shopCategoryRepository.find(item.getShopCategoryId());
        }
        if (category != null) {
            item.setCategoryPath(category.getPath());
        }
        if (shopCategory != null) {
            item.setShopCategoryPath(shopCategory.getPath());
        }
        item.setApproved(true);
        item.setActive(true);
        item.setCompleted(false);
        itemRepository.save(item);
        searchIndexService.processIndexItem(item);
        itemHistoryService.create(item, false, ItemHistoryType.CREATE);
        return new Response(true, "Thêm mới sản phẩm thành công", item);
    }

    /**
     * Sửa tạm thời thông tin cơ bản của sản phẩm
     *
     * @param item
     * @param administratorId
     * @return Kết quả kèm sản phẩm mới sửa hoặc báo lỗi
     * @throws Exception
     */
    public Response edit(Item item, String administratorId) throws Exception {
        Item oldItem = itemRepository.find(item.getId());
        if (oldItem == null) {
            throw new Exception("Sản phẩm không tồn tại");
        }
        if (administratorId == null && !oldItem.getSellerId().equals(item.getSellerId())) {
            throw new Exception("Bạn không có quyền sửa sản phẩm này");
        }

        Category category = null;
        ShopCategory shopCategory = null;
        if (item.getId() == null || item.getId().equals("")) {
            item.setId(itemRepository.genId());
            item.setCreateTime(System.currentTimeMillis());
        }
        if (item.getCategoryId() != null && !item.getCategoryId().equals("")) {
            category = categoryRepository.find(item.getCategoryId());
        }
        if (category != null) {
            item.setCategoryPath(category.getPath());
        }
        if (item.getShopCategoryId() != null && !item.getShopCategoryId().equals("")) {
            shopCategory = shopCategoryRepository.find(item.getShopCategoryId());
        }
        if (shopCategory != null) {
            item.setShopCategoryPath(shopCategory.getPath());
        }
        if (item.getImages() == null || item.getImages().isEmpty()) {
            item.setImages(oldItem.getImages());
        }
        item.setCompleted(oldItem.isCompleted());
        item.setApproved(oldItem.isApproved());
        item.setActive(oldItem.isActive());
        item.setUpdateTime(System.currentTimeMillis());
        itemRepository.save(item);
        searchIndexService.processIndexItem(item);
        itemHistoryService.create(item, false, ItemHistoryType.EDIT);

        if (item.getModelId() != null && !item.getModelId().equals("")) {
            Model model = modelRepository.find(item.getModelId());
            if (model != null) {
                this.updateModel(model);
            }
        }

        return new Response(true, "Sửa sản phẩm thành công", item);
    }

    /**
     * Chính thức tạo mới sản phẩm đăng bán
     *
     * @param item
     * @param administratorId - Người thêm mới sản phẩm
     * @return Kết quả kèm sản phẩm mới thêm hoặc báo lỗi
     * @throws Exception
     */
    public Response submitAdd(Item item, String administratorId) throws Exception {
        Item oldItem = itemRepository.find(item.getId());
        Map<String, String> error = validator.validate(item);
        long time = System.currentTimeMillis();
        Manufacturer manufacturer = null;
        Model model = null;
        Category category = null;
        ShopCategory shopCategory = null;
        User seller = null;
        Shop shop = null;
        validate(item, error);
        if (administratorId == null && !oldItem.getSellerId().equals(item.getSellerId())) {
            throw new Exception("Bạn không có quyền sửa sản phẩm này");
        }
        if (item.getCategoryId() == null || item.getCategoryId().equals("")) {
            error.put("categoryId", "Danh mục không được để trống");
        } else {
            category = categoryRepository.find(item.getCategoryId());
            if (category == null) {
                error.put("categoryId", "Danh mục không tồn tại");
            } else if (!category.isActive()) {
                error.put("categoryId", "Danh mục đang bị khóa");
            } else if (!category.isLeaf()) {
                error.put("categoryId", "Danh mục không phải là danh mục lá");
            }
        }
        if (shopCategoryRepository.count(item.getSellerId()) > 0) {
            if (item.getShopCategoryId() == null || item.getShopCategoryId().equals("")) {
                error.put("shopCategoryId", "Danh mục shop không được để trống");
            } else {
                shopCategory = shopCategoryRepository.find(item.getShopCategoryId());
                if (shopCategory == null) {
                    error.put("shopCategoryId", "Danh mục shop không tồn tại");
                } else if (!shopCategory.isActive()) {
                    error.put("shopCategoryId", "Danh mục shop đang bị khóa");
                } else if (!shopCategory.isLeaf()) {
                    error.put("shopCategoryId", "Danh mục shop không phải là danh mục lá");
                }
            }
        }
        if (item.getManufacturerId() != null && !item.getManufacturerId().equals("")) {
            manufacturer = manufacturerRepository.find(item.getManufacturerId());
            if (manufacturer == null) {
                error.put("manufacturerId", "Thương hiệu không tồn tại");
            }
            if (manufacturer != null && !manufacturer.isActive()) {
                error.put("manufacturerId", "Thương hiệu đang bị khóa");
            }
        }
        if (item.getModelId() != null && !item.getModelId().equals("")) {
            model = modelRepository.find(item.getModelId());
            if (model == null) {
                error.put("modelId", "Model không tồn tại");
            }
            if (model != null && !model.isActive()) {
                error.put("modelId", "Model đang bị khóa");
            }
        }
        if (item.getSellerId() != null && !item.getSellerId().trim().equals("")) {
            seller = userRepository.find(item.getSellerId());
            if (seller == null) {
                error.put("sellerId", "Người bán không tồn tại");
            } else {
                shop = shopRepository.findByUser(item.getSellerId());
            }
        }

        if (error.isEmpty()) {
            item.setSellerName(seller.getUsername());
            item.setCityId(seller.getCityId());
            item.setDistrictId(seller.getDistrictId());
            if (shop != null) {
                item.setShopName(shop.getAlias());
                item.setCityId(shop.getCityId());
                item.setDistrictId(shop.getDistrictId());
            }
            item.setCategoryPath(category.getPath());
            if (shopCategory != null) {
                item.setShopCategoryPath(shopCategory.getPath());
            } else {
                item.setShopCategoryPath(new ArrayList<String>());
            }
            ItemApprove approve = new ItemApprove();
            approve.setUpdaterType(ItemUpdaterType.ADMIN);
            item.setId(oldItem.getId());
            if (administratorId == null || administratorId.equals("")) {
                item.setActive(true);
                item.setApproved(true);
                item.setCompleted(true);
                approve.setUpdaterType(ItemUpdaterType.SELLER);
            }

            item.setStartTime(item.getStartTime() == 0 ? time : item.getStartTime());

            if (item.getEndTime() < item.getStartTime() || item.getEndTime() > item.getStartTime() + 60 * 24 * 60 * 60 * 1000l) {
                item.setEndTime(item.getStartTime() + 30 * 24 * 60 * 60 * 1000l);
            }
            if (oldItem.getUpTime() < time - 24 * 60 * 60 * 1000l) {
                item.setUpTime(time);
            }
            item.setUpdateTime(time);
            itemRepository.save(item);

            approve.setTime(time);
            approve.setStatus(ItemApproveStatus.CREATED);
            approve.setItemId(item.getId());
            approve.setUpdaterId(administratorId == null ? item.getSellerId() : administratorId);

            itemApproveRepository.save(approve);
            itemHistoryService.create(item, administratorId != null, ItemHistoryType.UPDATE);
            sellerHistoryService.create(SellerHistoryType.ITEM, item.getId(), true, 1, null);
            searchIndexService.processIndexItem(item);
            if (model != null) {
                this.updateModel(model);
            }
            try {
                if (administratorId == null) {
                    cashService.reward(CashTransactionType.SELLER_POST_ITEM, seller.getId(), item.getId(), UrlUtils.item(item.getId(), item.getName()), null);
                }
                return new Response(true, "Thêm mới sản phẩm thành công", item);
            } catch (Exception e) {
                return new Response(true, "POST_ITEM_FAIL", item);
            }

        }
        return new Response(false, "Thêm mới thất bại", error);
    }

    private void updateModel(Model model) {
        model.setNewMaxPrice(itemRepository.getNewMaxPrice(model.getId()));
        model.setNewMinPrice(itemRepository.getNewMinPrice(model.getId()));
        model.setOldMaxPrice(itemRepository.getOldMaxPrice(model.getId()));
        model.setOldMinPrice(itemRepository.getOldMinPrice(model.getId()));
        model.setItemCount(itemRepository.countItemByModel(model.getId()));

        modelRepository.save(model);
        searchIndexService.processIndexModel(model);
    }

    /**
     * Chính thức sửa sản phẩm đăng bán
     *
     * @param item
     * @param administratorId - Người sửa mới sản phẩm
     * @return Kết quả kèm sản phẩm mới thêm hoặc báo lỗi
     * @throws Exception
     */
    public Response submitEdit(Item item, String administratorId) throws Exception {
        Item oldItem = itemRepository.find(item.getId());
        if (oldItem == null) {
            throw new Exception("Sản phẩm không tồn tại");
        }
        if ((administratorId == null && !item.getSellerId().equals(item.getSellerId()))) {
            throw new Exception("Bạn không có quyền sửa sản phẩm này");
        }

        Manufacturer manufacturer = null;
        Model model = null;
        Category category = null;
        ShopCategory shopCategory = null;

        Map<String, String> error = validator.validate(item);

        long time = System.currentTimeMillis();
        User seller = null;
        Shop shop = null;
        if (oldItem == null) {
            throw new Exception("Sản phẩm không tồn tại");
        }
        validate(item, error);

        if (item.getCategoryId() == null || item.getCategoryId().equals("")) {
            error.put("categoryId", "Danh mục không được để trống");
        } else {
            category = categoryRepository.find(item.getCategoryId());
            if (category == null) {
                error.put("categoryId", "Danh mục không tồn tại");
            } else if (!category.isActive()) {
                error.put("categoryId", "Danh mục đang bị khóa");
            } else if (!category.isLeaf()) {
                error.put("categoryId", "Danh mục không phải là danh mục lá");
            }
        }

        if (shopCategoryRepository.count(item.getSellerId()) > 0) {
            if (item.getShopCategoryId() == null || item.getShopCategoryId().equals("")) {
                error.put("shopCategoryId", "Danh mục shop không được để trống");
            } else {
                shopCategory = shopCategoryRepository.find(item.getShopCategoryId());
                if (shopCategory == null) {
                    error.put("shopCategoryId", "Danh mục shop không tồn tại");
                } else if (!shopCategory.isActive()) {
                    error.put("shopCategoryId", "Danh mục shop đang bị khóa");
                } else if (!shopCategory.isLeaf()) {
                    error.put("shopCategoryId", "Danh mục shop không phải là danh mục lá");
                }
            }
        }

        if (item.getManufacturerId() != null && !item.getManufacturerId().equals("")) {
            manufacturer = manufacturerRepository.find(item.getManufacturerId());
            if (manufacturer == null) {
                error.put("manufacturerId", "Thương hiệu không tồn tại");
            }
            if (manufacturer != null && !manufacturer.isActive()) {
                error.put("manufacturerId", "Thương hiệu đang bị khóa");
            }
        }
        if (item.getModelId() != null && !item.getModelId().equals("")) {
            model = modelRepository.find(item.getModelId());
            if (model == null) {
                error.put("modelId", "Model không tồn tại");
            }
            if (model != null && !model.isActive()) {
                error.put("modelId", "Model đang bị khóa");
            }
        }
        if (item.getSellerId() != null && !item.getSellerId().trim().equals("")) {
            seller = userRepository.find(item.getSellerId());
            if (seller == null) {
                error.put("sellerId", "Người bán không tồn tại");
            } else {
                shop = shopRepository.findByUser(item.getSellerId());
            }
        }
        if (error.isEmpty()) {
            item.setSellerName(seller.getUsername());
            item.setCityId(seller.getCityId());
            item.setDistrictId(seller.getDistrictId());
            if (shop != null) {
                item.setShopName(shop.getAlias());
                item.setCityId(shop.getCityId());
                item.setDistrictId(shop.getDistrictId());
            }
            item.setCategoryPath(category.getPath());
            if (shopCategory != null) {
                item.setShopCategoryPath(shopCategory.getPath());
            } else {
                item.setShopCategoryPath(new ArrayList<String>());
            }
            ItemApprove lastApproved = itemApproveRepository.getLastAppoved(item.getId());
            ItemApprove approve = new ItemApprove();
            if (administratorId != null) {
                approve.setUpdaterType(ItemUpdaterType.ADMIN);
                approve.setUpdaterId(administratorId);
                item.setApproved(true);
            } else {
                approve.setUpdaterType(ItemUpdaterType.SELLER);
                approve.setUpdaterId(item.getSellerId());
                item.setStartTime(item.getStartTime() == 0 ? time : item.getStartTime());

                if (item.getEndTime() < item.getStartTime() || item.getEndTime() > item.getStartTime() + 60 * 24 * 60 * 60 * 1000l) {
                    item.setEndTime(item.getStartTime() + 30 * 24 * 60 * 60 * 1000l);
                }
//                if (oldItem.getUpTime() < time - 24 * 60 * 60 * 1000l) {
//                    item.setUpTime(time);
//                }
                item.setActive(oldItem.isActive());
                item.setApproved(oldItem.isApproved());
                if (!item.isCompleted()) {
                    item.setActive(true);
                    item.setApproved(true);
                    item.setCompleted(true);
                }

            }
            if (lastApproved != null) {
                approve.setLastUpdaterId(lastApproved.getUpdaterId());
            }

            item.setUpdateTime(time);
            itemRepository.save(item);
            searchIndexService.processIndexItem(item);
            if (model != null) {
                this.updateModel(model);
            }
            Model oldModel = modelRepository.find(oldItem.getModelId());
            if (oldModel != null) {
                this.updateModel(oldModel);
            }
            approve.setTime(time);
            approve.setStatus(ItemApproveStatus.EDITED);
            approve.setItemId(item.getId());
            itemApproveRepository.save(approve);
            itemHistoryService.create(item, administratorId != null, ItemHistoryType.UPDATE);
            sellerHistoryService.create(SellerHistoryType.ITEM, item.getId(), true, 2, null);
            return new Response(true, "Sửa sản phẩm thành công", item);
        }

        return new Response(false, "Sửa thất bại", error);
    }

    /**
     * vatidate điều kiện cơ bản đầu vào của sản phẩm
     *
     * @param item
     * @param error
     */
    private void validate(Item item, Map<String, String> error) {

        if (item.getListingType() == ListingType.AUCTION) {
            if (item.getBidStep() <= 0) {
                error.put("bidStep", "Bước giá phải lớn hơn 0");
            }
            if (item.getStartPrice() < 0) {
                error.put("bidStartPrice", "Giá gốc phải lớn hơn 0");
            }
            if (item.getSellPrice() > 0 && item.getSellPrice() <= item.getStartPrice()) {
                error.put("bidSellPrice", "Giá mua ngay phải lớn hơn giá khởi điểm");
            }
        } else {
            if (item.getSellPrice() <= 0) {
                error.put("sellPrice", "Giá bán phải lớn hơn 0");
            }
            if (item.getStartPrice() < 0) {
                error.put("startPrice", "Giá gốc phải lớn hơn 0");
            }
        }
        if (item.getShipmentType() == ShipmentType.BYWEIGHT && item.getWeight() <= 0) {
            error.put("weight", "Khối lượng > 0 với phí vận chuyển là linh hoạt");
        }
        if (item.getQuantity() <= 0) {
            error.put("quantity", "Số lượng sản phẩm phải lớn hơn 0");
        }
        Image firstImage = imageService.getFirstImage(item.getId(), ImageType.ITEM);
        if ((item.getImages() == null || item.getImages().isEmpty()) && firstImage == null) {
            error.put("images", "Sản phẩm phải có ảnh");
        }
        ItemDetail detail = itemDetailRepository.find(item.getId());
        if (detail == null || detail.getDetail() == null || detail.getDetail().trim().equals("")) {
            error.put("detail", "Chi tiết của sản phẩm phải được nhập");
        }
    }

    /**
     * Lấy toàn bộ thuộc tính của sản phẩm
     *
     * @param itemId
     * @return Kết quả là danh sách thuộc tính
     */
    public List<ItemProperty> getProperties(String itemId) {
        return itemPropertyRepository.getByItem(itemId);
    }

    /**
     * Cập nhật thuộc tính sản phẩm
     *
     * @param itemId
     * @param properties
     * @throws Exception
     */
    public void updateProperties(String itemId, List<ItemProperty> properties) throws Exception {
        Item item = itemRepository.find(itemId);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại");
        }

        itemPropertyRepository.deleteByItem(itemId);

        for (ItemProperty property : properties) {
            CategoryProperty catP = categoryPropertyRepository.find(property.getCategoryPropertyId());
            if (catP != null) {
                property.setCategoryPropertyId(catP.getId());
                List<String> ids = new ArrayList<>();
                for (String v : property.getCategoryPropertyValueIds()) {
                    CategoryPropertyValue catV = categoryPropertyValueRepository.find(v);
                    if (catV != null) {
                        ids.add(catV.getId());
                    }
                }
                property.setItemId(itemId);
                property.setCategoryPropertyValueIds(ids);
                itemPropertyRepository.save(property);
                searchIndexService.processIndexItem(item);
            }
        }

    }

    /**
     * Xóa sản phẩm xóa cả property
     *
     * @param id
     * @throws Exception
     */
    public void remove(String id) throws Exception {
        Item item = itemRepository.find(id);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại");
        }
        if (item.getImages() != null && item.getImages().isEmpty()) {
            for (String img : item.getImages()) {
                try {
                    imageService.deleteById(ImageType.ITEM, item.getId(), img);
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }

            }
        }
        sellerHistoryService.create(SellerHistoryType.ITEM, item.getId(), false, 3, null);
        itemPropertyRepository.deleteByItem(id);
        itemRepository.delete(id);
        itemDetailRepository.remove(id);
        itemSearchRepository.delete(id);
        if (item.getModelId() != null && !item.getModelId().equals("")) {
            Model model = modelRepository.find(item.getId());
            if (model != null) {
                this.updateModel(model);
            }
        }
        itemHistoryService.create(item, false, ItemHistoryType.DELETE);

    }

    /**
     * Xóa nhiều sản phẩm cùng người bán
     *
     * @param ids
     * @param sellerId
     * @throws Exception
     */
    public void remove(List<String> ids, String sellerId) throws Exception {
        List<Item> items = itemRepository.get(ids);
        if (items != null && !items.isEmpty()) {
            for (Item item : items) {
                if (item != null && item.getSellerId().equals(sellerId)) {
                    if (item.getImages() != null && item.getImages().isEmpty()) {
                        for (String img : item.getImages()) {
                            try {
                                imageService.deleteById(ImageType.ITEM, item.getId(), img);
                            } catch (Exception e) {
                                throw new Exception(e.getMessage());
                            }
                        }
                    }
                    itemPropertyRepository.deleteByItem(item.getId());
                    itemRepository.delete(item.getId());
                    itemDetailRepository.remove(item.getId());
                    itemSearchRepository.delete(item.getId());
                    if (item.getModelId() != null && !item.getModelId().equals("")) {
                        Model model = modelRepository.find(item.getId());
                        if (model != null) {
                            this.updateModel(model);
                        }
                    }
                    itemHistoryService.create(item, false, ItemHistoryType.DELETE);
                }
            }
        }
    }

    /**
     * Xóa nhiều sản phẩm cùng người bán
     *
     * @param ids
     * @param sellerId
     */
    public void updateActive(List<String> ids, String sellerId) {
        List<Item> items = itemRepository.get(ids);
        if (items != null && !items.isEmpty()) {
            for (Item item : items) {
                if (item != null && item.getSellerId().equals(sellerId)) {
                    item.setActive(!item.isActive());

                    itemRepository.save(item);
                    searchIndexService.processIndexItem(item);
                    Model model = modelRepository.find(item.getModelId());
                    if (model != null) {
                        this.updateModel(model);
                    }
                    itemHistoryService.create(item, false, ItemHistoryType.UPDATE);
                }
            }
        }
    }

    /**
     * Ngừng bán sản phẩm của người bán
     *
     * @param ids
     * @param sellerId
     */
    public void stopSelling(List<String> ids, String sellerId) {
        List<Item> items = itemRepository.get(ids);
        if (items != null && !items.isEmpty()) {
            for (Item item : items) {
                if (item != null && item.getSellerId().equals(sellerId)) {
                    item.setEndTime(System.currentTimeMillis());
                    itemRepository.save(item);
                    searchIndexService.processIndexItem(item);
                    if (item.getModelId() != null && !item.getModelId().equals("")) {
                        Model model = modelRepository.find(item.getId());
                        if (model != null) {
                            this.updateModel(model);
                        }
                    }
                    itemHistoryService.create(item, false, ItemHistoryType.UPDATE);
                }
            }
        }
    }

    /**
     * Bỏ duyệt sản phẩm
     *
     * @param itemId
     * @param administatorId id người bỏ duyệt
     * @param message
     * @throws java.lang.Exception
     */
    public void disapproved(String itemId, String administatorId, String message) throws Exception {
        Item item = itemRepository.find(itemId);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại");
        }
        item.setApproved(false);
        itemRepository.save(item);

        ItemApprove lastUpdate = itemApproveRepository.getLastAppoved(itemId);
        ItemApprove approve = new ItemApprove();
        approve.setItemId(itemId);
        if (lastUpdate != null) {
            approve.setLastUpdaterId(lastUpdate.getUpdaterId());
        }
        approve.setUpdaterId(administatorId);
        approve.setStatus(ItemApproveStatus.DISAPPROVED);
        approve.setUpdaterType(ItemUpdaterType.ADMIN);
        approve.setMessage(message);
        approve.setTime(System.currentTimeMillis());

        itemApproveRepository.save(approve);
        searchIndexService.processIndexItem(item);
        if (item.getModelId() != null && !item.getModelId().equals("")) {
            Model model = modelRepository.find(item.getId());
            if (model != null) {
                this.updateModel(model);
            }
        }
        CashHistory cashHistory = cashHistoryRepository.find(CashTransactionType.SELLER_POST_ITEM, item.getSellerId(), itemId);
        if (cashHistory != null) {
            cashHistory.setNote(cashHistory.getNote() + ", bị admin " + administatorId + " hạ vào lúc " + new Date().toString());
            cashHistory.setUpdateTime(System.currentTimeMillis());
            cashHistoryRepository.save(cashHistory);
        }
        itemHistoryService.create(item, true, ItemHistoryType.UPDATE);
    }

    /**
     * Duyệt sản phẩm
     *
     * @param itemId
     * @param administatorId id người duyệt
     * @param message
     * @throws java.lang.Exception
     */
    public void approved(String itemId, String administatorId) throws Exception {
        Item item = itemRepository.find(itemId);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại");
        }
        item.setApproved(true);
        itemRepository.save(item);
        if (item.getModelId() != null && !item.getModelId().equals("")) {
            Model model = modelRepository.find(item.getId());
            if (model != null) {
                this.updateModel(model);
            }
        }
        ItemApprove lastUpdate = itemApproveRepository.getLastAppoved(itemId);
        ItemApprove approve = new ItemApprove();
        approve.setItemId(itemId);
        if (lastUpdate != null) {
            approve.setLastUpdaterId(lastUpdate.getUpdaterId());
        }
        approve.setUpdaterId(administatorId);
        approve.setUpdaterType(ItemUpdaterType.ADMIN);
        approve.setTime(System.currentTimeMillis());

        itemApproveRepository.save(approve);
        searchIndexService.processIndexItem(item);
        if (item.getModelId() != null && !item.getModelId().equals("")) {
            Model model = modelRepository.find(item.getId());
            if (model != null) {
                this.updateModel(model);
            }
        }
        itemHistoryService.create(item, true, ItemHistoryType.UPDATE);
    }

    /**
     * Sửa detail sản phẩm
     *
     * @param itemDetail
     * @throws java.lang.Exception
     */
    public void updateDetail(ItemDetail itemDetail) throws Exception {
        if (!itemRepository.exists(itemDetail.getItemId())) {
            throw new Exception("Sản phẩm không tồn tại");
        }
        if (itemDetail.getDetail() == null || itemDetail.getDetail().trim().equals("")) {
            throw new Exception("Chi tiết sản phẩm không được để trống");
        }
        itemDetailRepository.save(itemDetail);
    }

    /**
     * Lấy detail sản phẩm
     *
     * @param itemId
     * @return
     * @throws java.lang.Exception
     */
    public ItemDetail getDetail(String itemId) throws Exception {
        if (!itemRepository.exists(itemId)) {
            throw new Exception("Sản phẩm không tồn tại");
        }
        return itemDetailRepository.find(itemId);
    }

    /**
     * Thay đổi trạng thái của sản phẩm
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Response changeStatus(String id) throws Exception {
        Item item = itemRepository.find(id);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại");
        }
        item.setActive(!item.isActive());
        itemRepository.save(item);
        searchIndexService.processIndexItem(item);
        if (item.getModelId() != null && !item.getModelId().equals("")) {
            Model model = modelRepository.find(item.getId());
            if (model != null) {
                this.updateModel(model);
            }
        }
        itemHistoryService.create(item, false, ItemHistoryType.UPDATE);
        return new Response(true, "Thông tin sản phẩm", item);
    }

    /**
     * Lấy chi tiết sản phẩm
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Item get(String id) throws Exception {
        Item item = itemRepository.find(id);
        if (item == null) {
            throw new Exception("Sản phẩm không tồn tại");
        }
        item.setImages(imageService.get(ImageType.ITEM, id));
        return item;
    }

    /**
     * Xóa toàn bộ index
     *
     */
    public void unIndex() {
        itemSearchRepository.preIndex();
    }

    /**
     * index tất cả item trong database
     *
     * @throws Exception
     */
    @Async
    public void index() throws Exception {
        long total = itemRepository.count();
        int totalPage = (int) total / 1000;
        if (total % 1000 != 0) {
            totalPage++;
        }
        for (int i = 0; i < totalPage; i++) {
            searchIndexService.processIndexPageItem(itemRepository.list(new PageRequest(i, 1000)));
        }
    }

    /**
     * Cập nhật thông tin seller sang item
     */
    @Async
    public void updateSellerInfo() {
        long total = itemRepository.count();
        int totalPage = (int) total / 1000;
        if (total % 1000 != 0) {
            totalPage++;
        }
        for (int i = 0; i < totalPage; i++) {
            List<Item> items = itemRepository.list(new PageRequest(i, 1000));
            List<String> userIds = new ArrayList<>();

            for (Item item : items) {
                if (item.getSellerId() != null) {
                    userIds.add(item.getSellerId());
                }
            }

            List<User> users = userRepository.get(userIds);
            List<Seller> sellers = sellerRepository.get(userIds);
            List<Shop> shops = shopRepository.get(userIds);

            for (Item item : items) {
                for (User user : users) {
                    if (item.getSellerId().equals(user.getId())) {
                        item.setSellerName(user.getUsername());
                        item.setCityId(user.getCityId());
                        item.setDistrictId(user.getDistrictId());
                    }
                }
                for (Seller seller : sellers) {
                    if (item.getSellerId().equals(seller.getUserId())) {
                        item.setOnlinePayment(seller.isNlIntegrated());
                        item.setCod(seller.isScIntegrated());
                        item.setShipmentType(seller.getShipmentType());
                        item.setShipmentPrice(seller.getShipmentPrice());
                    }
                }
                for (Shop shop : shops) {
                    if (item.getSellerId().equals(shop.getUserId())) {
                        item.setShopName(shop.getAlias());
                        item.setShopDescription(shop.getTitle());
                    }
                }

            }
            itemRepository.bulkSave(items);
        }
    }

    private NativeSearchQueryBuilder buildSearchCondition(ItemSearch itemSearch, boolean ignoreSearch) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        List<FilterBuilder> filters = new ArrayList<>();

        switch (itemSearch.getStatus()) {
            case 1:
                filters.add(new RangeFilterBuilder("startTime").lte(System.currentTimeMillis()));
                filters.add(new RangeFilterBuilder("endTime").gte(System.currentTimeMillis()));
                filters.add(new OrFilterBuilder(new FilterBuilder[]{
                    new RangeFilterBuilder("price").gt(0),
                    new TermFilterBuilder("listingType", ListingType.AUCTION.toString())
                }));
                filters.add(new TermFilterBuilder("active", true));
                filters.add(new TermFilterBuilder("completed", true));
                filters.add(new TermFilterBuilder("approved", true));
                filters.add(new RangeFilterBuilder("quantity").gt(0));
                break;
            case 2:
                filters.add(new RangeFilterBuilder("endTime").lt(System.currentTimeMillis()));
                break;
            case 3:
                filters.add(new RangeFilterBuilder("quantity").lte(0));
                break;
            case 4:
                filters.add(new TermFilterBuilder("approved", false));
                break;
            case 5:
                filters.add(new TermFilterBuilder("active", false));
                break;
            case 6:
                filters.add(new TermFilterBuilder("completed", false));
                break;
            case 7:
                filters.add(new TermFilterBuilder("completed", true));
                filters.add(new TermFilterBuilder("active", true));
                break;
            case 8:
                filters.add(new RangeFilterBuilder("startTime").gt(System.currentTimeMillis()));
                break;
            case 9:
                filters.add(new TermFilterBuilder("completed", true));
                filters.add(new TermFilterBuilder("active", true));
                filters.add(new TermFilterBuilder("approved", true));
                break;
            case 10:
                filters.add(new TermFilterBuilder("completed", false));
                filters.add(new TermFilterBuilder("active", true));
                filters.add(new TermFilterBuilder("approved", true));
                break;
            case 11:
                filters.add(new TermFilterBuilder("completed", true));
                filters.add(new TermFilterBuilder("active", true));
                filters.add(new TermFilterBuilder("approved", true));
                filters.add(new RangeFilterBuilder("startTime").lte(System.currentTimeMillis()));
                filters.add(new RangeFilterBuilder("endTime").gte(System.currentTimeMillis()));
                filters.add(new RangeFilterBuilder("quantity").lte(0));
                break;
            case 12:
                filters.add(new TermFilterBuilder("completed", true));
                filters.add(new TermFilterBuilder("active", true));
                filters.add(new TermFilterBuilder("approved", true));
                filters.add(new RangeFilterBuilder("endTime").lt(System.currentTimeMillis()));
                break;
            case 13:
                filters.add(new TermFilterBuilder("completed", true));
                filters.add(new TermFilterBuilder("active", true));
                filters.add(new TermFilterBuilder("approved", false));
                break;
        }

        if (itemSearch.getCreateTimeFrom() > 0 && itemSearch.getCreateTimeTo() > 0) {
            filters.add(new RangeFilterBuilder("createTime").gte(itemSearch.getCreateTimeFrom()).lte(itemSearch.getCreateTimeTo()));
        } else if (itemSearch.getCreateTimeFrom() > 0) {
            filters.add(new RangeFilterBuilder("createTime").gte(itemSearch.getCreateTimeFrom()));
        } else if (itemSearch.getCreateTimeTo() > 0) {
            filters.add(new RangeFilterBuilder("createTime").lte(itemSearch.getCreateTimeTo()));
        }

        if (itemSearch.getCategoryIds() != null && !itemSearch.getCategoryIds().isEmpty() && itemSearch.getCategoryIds().size() > 0) {
            filters.add(new TermsFilterBuilder("categoryPath", itemSearch.getCategoryIds()));
        }
        if (!ignoreSearch) {
            if (itemSearch.getId() != null && !itemSearch.getId().trim().equals("")) {
                filters.add(new TermFilterBuilder("id", itemSearch.getId().trim()));
            }

            if (itemSearch.getSellerId() != null && !itemSearch.getSellerId().trim().equals("")) {
                filters.add(new TermFilterBuilder("sellerId", itemSearch.getSellerId()));
            }
            if (itemSearch.getPromotionId() != null && !itemSearch.getPromotionId().trim().equals("")) {
                filters.add(new TermFilterBuilder("promotionId", itemSearch.getPromotionId()));
            }
            if (itemSearch.getSellerSku() != null && !itemSearch.getSellerSku().trim().equals("")) {
                filters.add(new TermFilterBuilder("sellerSku", DigestUtils.md5Hex(itemSearch.getSellerSku())));
            }
            if (itemSearch.getSource() != null) {
                filters.add(new TermFilterBuilder("source", itemSearch.getSource().toString()));
            }
            if (itemSearch.getShopCategoryId() != null && !itemSearch.getShopCategoryId().trim().equals("")) {
                filters.add(new TermFilterBuilder("shopCategoryPath", itemSearch.getShopCategoryId()));
            }
            if (itemSearch.getManufacturerIds() != null && !itemSearch.getManufacturerIds().isEmpty()) {
                filters.add(new TermsFilterBuilder("manufacturerId", itemSearch.getManufacturerIds()));
            }
            if (itemSearch.getModelIds() != null && !itemSearch.getModelIds().isEmpty()) {
                filters.add(new TermsFilterBuilder("modelId", itemSearch.getModelIds()));
            }
            if (itemSearch.getCityIds() != null && !itemSearch.getCityIds().isEmpty()) {
                filters.add(new TermsFilterBuilder("cityId", itemSearch.getCityIds()));
            }

            if (itemSearch.getListingType() != null) {
                filters.add(new TermFilterBuilder("listingType", itemSearch.getListingType().toString()));
            }
            if (itemSearch.getCondition() != null) {
                filters.add(new TermFilterBuilder("condition", itemSearch.getCondition().toString()));
            }
            if (itemSearch.isFreeShip()) {
                filters.add(new TermFilterBuilder("freeShip", true));
            }
            if (itemSearch.isOnlinePayment()) {
                filters.add(new TermFilterBuilder("onlinePayment", true));
            }
            if (itemSearch.isPromotion()) {
                filters.add(new TermFilterBuilder("discount", true));
            }
            if (itemSearch.isCod()) {
                filters.add(new TermFilterBuilder("cod", true));
            }
            if (itemSearch.getPriceFrom() > 0 && itemSearch.getPriceTo() > 0) {
                filters.add(new RangeFilterBuilder("price").gte(itemSearch.getPriceFrom()).lte(itemSearch.getPriceTo()));
            } else if (itemSearch.getPriceFrom() > 0) {
                filters.add(new RangeFilterBuilder("price").gte(itemSearch.getPriceFrom()));
            } else if (itemSearch.getPriceTo() > 0) {
                filters.add(new RangeFilterBuilder("price").lte(itemSearch.getPriceTo()));
            }

            List<FilterBuilder> proFilters = new ArrayList<>();
            if (itemSearch.getProperties() != null) {
                for (PropertySearch pro : itemSearch.getProperties()) {
                    FilterBuilder[] prosFilter = new FilterBuilder[2];
                    prosFilter[0] = new TermFilterBuilder("properties.name", pro.getName());
                    if (pro.getOperator() == PropertyOperator.GTE) {
                        prosFilter[1] = new RangeFilterBuilder("value").gte(pro.getValue());
                    } else if (pro.getOperator() == PropertyOperator.LTE) {
                        prosFilter[1] = new RangeFilterBuilder("value").lte(pro.getValue());
                    } else {
                        prosFilter[1] = new TermFilterBuilder("value", pro.getValue());
                    }
                    proFilters.add(new NestedFilterBuilder("properties", new AndFilterBuilder(prosFilter)));
                }
                filters.add(new AndFilterBuilder(proFilters.toArray(new FilterBuilder[0])));
            }
        }
        if (itemSearch.getKeyword() != null && itemSearch.getKeyword().trim().length() > 1) {
            queryBuilder.withQuery(new QueryStringQueryBuilder(TextUtils.removeDiacritical(itemSearch.getKeyword())).defaultOperator(QueryStringQueryBuilder.Operator.AND));
        } else {
            queryBuilder.withQuery(new MatchAllQueryBuilder());
        }
        if (!filters.isEmpty()) {
            queryBuilder.withFilter(new AndFilterBuilder(filters.toArray(new FilterBuilder[0])));
        }

        return queryBuilder;
    }

    @Cacheable(value = "buffercache", key = "'itemsearch-'.concat(#itemSearch.getKey())")
    public DataPage<Item> search(ItemSearch itemSearch) {
        NativeSearchQueryBuilder builder = buildSearchCondition(itemSearch, false);
        switch (itemSearch.getOrderBy()) {
            case 1:
                builder.withSort(new FieldSortBuilder("price").order(SortOrder.DESC));
                break;
            case 2:
                builder.withSort(new FieldSortBuilder("price").order(SortOrder.ASC));
                break;
            case 3:
                builder.withSort(new FieldSortBuilder("updateTime").order(SortOrder.DESC));
                break;
            case 4:
                builder.withSort(new FieldSortBuilder("viewCount").order(SortOrder.DESC));
                break;
            case 5:
                builder.withSort(new FieldSortBuilder("startTime").order(SortOrder.DESC));
                break;
            default:
                builder.withSort(new FieldSortBuilder("upTime").order(SortOrder.DESC));
                break;
        }

        builder.withFields("id");
        builder.withPageable(new PageRequest(itemSearch.getPageIndex(), itemSearch.getPageSize()));
        DataPage<Item> dataPage = new DataPage<>();
        try {
            FacetedPage<vn.chodientu.entity.search.ItemSearch> page = itemSearchRepository.search(builder.build());

            dataPage.setDataCount(page.getTotalElements());
            dataPage.setPageCount(page.getTotalPages());
            dataPage.setPageSize(page.getSize());
            dataPage.setPageIndex(page.getNumber());

            List<String> ids = new ArrayList<>();

            for (vn.chodientu.entity.search.ItemSearch is : page.getContent()) {
                ids.add(is.getId());
            }

            dataPage.setData(itemRepository.get(ids));

            Map<String, List<String>> images = imageService.get(ImageType.ITEM, ids);
            for (Map.Entry<String, List<String>> entry : images.entrySet()) {
                String modelId = entry.getKey();
                List<String> imgs = entry.getValue();
                for (Item item : dataPage.getData()) {
                    if (item.getId().equals(modelId)) {
                        item.setImages(imgs);
                        break;
                    }
                }
            }

        } catch (Exception ex) {
            dataPage.setData(new ArrayList<Item>());
        }
        return dataPage;

    }

    @Cacheable(value = "datacache", key = "'itemh-'.concat(#itemSearch.getKey())")
    public List<ItemHistogram> getItemHistogram(ItemSearch itemSearch) {
        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(itemSearch, false);

        queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("conditionNew").filter(new TermFilterBuilder("condition", Condition.NEW.toString())), true));
        queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("freeShip").filter(new TermFilterBuilder("freeShip", true)), true));
        queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("listingtypeBuynow").filter(new TermFilterBuilder("listingType", ListingType.BUYNOW.toString())), true));
        queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("cod").filter(new TermFilterBuilder("cod", true)), true));
        queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("onlinePayment").filter(new TermFilterBuilder("onlinePayment", true)), true));
        queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("promotion").filter(new OrFilterBuilder(new FilterBuilder[]{
            new TermFilterBuilder("discount", true),
            new TermFilterBuilder("gift", true)
        })), true));

        FacetResult result = itemSearchRepository.getFacets(queryBuilder.build());

        List<ItemHistogram> itemHistogram = new ArrayList<>();

        FilterFacet facet;

        facet = result.getFacet(FilterFacet.class, "conditionNew");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("conditionNew");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }
        facet = result.getFacet(FilterFacet.class, "listingtypeBuynow");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("listingtypeBuynow");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }
        facet = result.getFacet(FilterFacet.class, "freeShip");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("freeShip");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }
        facet = result.getFacet(FilterFacet.class, "cod");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("cod");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }
        facet = result.getFacet(FilterFacet.class, "onlinePayment");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("onlinePayment");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }
        facet = result.getFacet(FilterFacet.class, "promotion");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("promotion");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }

        return itemHistogram;
    }

    public List<ItemHistogram> getItemStatusHistogram(String sellerId) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        if (sellerId != null && !sellerId.equals("")) {
            queryBuilder.withFilter(new TermFilterBuilder("sellerId", sellerId));
        }
//        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("unapproved", new TermFilterBuilder("approved", false)), true));
//        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("outDate", new RangeFilterBuilder("endTime").lt(System.currentTimeMillis())), true));
//        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("outOfStock", new RangeFilterBuilder("quantity").lte(0)), true));
//        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("uncompleted", new TermFilterBuilder("completed", false)), true));

        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("unapproved", new AndFilterBuilder(new FilterBuilder[]{
            new TermFilterBuilder("active", true),
            new TermFilterBuilder("completed", true),
            new TermFilterBuilder("approved", false),})), true));
        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("uncompleted", new AndFilterBuilder(new FilterBuilder[]{
            new TermFilterBuilder("active", true),
            new TermFilterBuilder("completed", false),
            new TermFilterBuilder("approved", true),})), true));
        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("outOfStock", new AndFilterBuilder(new FilterBuilder[]{
            new RangeFilterBuilder("quantity").lte(0),
            new RangeFilterBuilder("startTime").lte(System.currentTimeMillis()),
            new RangeFilterBuilder("endTime").gte(System.currentTimeMillis()),
            new TermFilterBuilder("active", true),
            new TermFilterBuilder("completed", true),
            new TermFilterBuilder("approved", true),})), true));
        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("outDate", new AndFilterBuilder(new FilterBuilder[]{
            new RangeFilterBuilder("endTime").lt(System.currentTimeMillis()),
            new TermFilterBuilder("active", true),
            new TermFilterBuilder("completed", true),
            new TermFilterBuilder("approved", true),})), true));
        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("all", new AndFilterBuilder(new FilterBuilder[]{
            new TermFilterBuilder("active", true),
            new TermFilterBuilder("completed", true),
            new TermFilterBuilder("approved", true),})), true));

        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("recycle", new TermFilterBuilder("active", false)), true));

        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("selling", new AndFilterBuilder(new FilterBuilder[]{
            new RangeFilterBuilder("startTime").lte(System.currentTimeMillis()),
            new RangeFilterBuilder("endTime").gte(System.currentTimeMillis()),
            new TermFilterBuilder("active", true),
            new TermFilterBuilder("completed", true),
            new TermFilterBuilder("approved", true),
            new RangeFilterBuilder("quantity").gt(0)
        })), true));

        FacetResult result = itemSearchRepository.getFacets(queryBuilder.build());
        List<ItemHistogram> itemHistogram = new ArrayList<>();

        FilterFacet facet;

        facet = result.getFacet(FilterFacet.class, "unapproved");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("unapproved");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }
        facet = result.getFacet(FilterFacet.class, "outDate");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("outDate");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }
        facet = result.getFacet(FilterFacet.class, "outOfStock");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("outOfStock");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }
        facet = result.getFacet(FilterFacet.class, "uncompleted");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("uncompleted");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }
        facet = result.getFacet(FilterFacet.class, "recycle");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("recycle");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }
        facet = result.getFacet(FilterFacet.class, "selling");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("selling");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }
        facet = result.getFacet(FilterFacet.class, "all");
        if (facet != null) {
            ItemHistogram histogram = new ItemHistogram();
            histogram.setType("all");
            histogram.setCount(facet.getCount());
            histogram.setTotal(result.getTotalElements());
            itemHistogram.add(histogram);
        }

        return itemHistogram;
    }

    @Cacheable(value = "datacache", key = "'modelh-'.concat(#itemSearch.getKey())")
    public List<ModelHistogram> getModelHistogram(ItemSearch itemSearch) {
        itemSearch.setModelIds(null);

        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(itemSearch, false);
        queryBuilder.withFacet(new NativeFacetRequest(new TermsFacetBuilder("model").field("modelId").size(100), true));

        FacetResult result = itemSearchRepository.getFacets(queryBuilder.build());

        List<ModelHistogram> modelHistogram = new ArrayList<>();
        List<String> modelIds = new ArrayList<>();
        for (TermsFacet.Entry entry : ((TermsFacet) result.getFacet(TermsFacet.class, "model")).getEntries()) {
            ModelHistogram his = new ModelHistogram();
            his.setId(entry.getTerm().string());
            his.setCount(entry.getCount());
            modelIds.add(entry.getTerm().string());
            modelHistogram.add(his);
        }
        for (Model model : modelRepository.get(modelIds)) {
            for (ModelHistogram mh : modelHistogram) {
                if (mh.getId().equals(model.getId())) {
                    mh.setName(model.getName());
                }
            }
        }

        return modelHistogram;
    }

    @Cacheable(value = "datacache", key = "'cityh-'.concat(#itemSearch.getKey())")
    public List<CityHistogram> getCityHistogram(ItemSearch itemSearch) {
        itemSearch.setCityIds(null);
        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(itemSearch, false);

        queryBuilder.withFacet(new NativeFacetRequest(new TermsFacetBuilder("city").field("cityId").size(100), true));

        FacetResult result = itemSearchRepository.getFacets(queryBuilder.build());

        List<CityHistogram> cityHistogram = new ArrayList<>();
        List<String> locationIds = new ArrayList<>();
        for (TermsFacet.Entry entry : (result.getFacet(TermsFacet.class, "city")).getEntries()) {
            CityHistogram his = new CityHistogram();
            if (entry.getTerm().string() != null && !entry.getTerm().string().equals("") && !entry.getTerm().string().equals("0")) {
                his.setId(entry.getTerm().string());
                his.setCount(entry.getCount());
                locationIds.add(entry.getTerm().string());
                cityHistogram.add(his);
            }
        }
        for (City lo : cityRepository.get(locationIds)) {
            for (CityHistogram lh : cityHistogram) {
                if (lh.getId().equals(lo.getId())) {
                    lh.setName(lo.getName());
                }
            }
        }

        return cityHistogram;
    }

    @Cacheable(value = "datacache", key = "'categoryh-'.concat(#itemSearch.getKey()).concat(#ignoreSearch)")
    public List<CategoryHistogram> getCategoryHistogram(ItemSearch itemSearch, boolean ignoreSearch) {
        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(itemSearch, ignoreSearch);

        String cateId = null;
        if (itemSearch.getCategoryIds() != null && !itemSearch.getCategoryIds().isEmpty()) {
            cateId = itemSearch.getCategoryIds().get(0);
        }
        List<Category> childCats;
        if (itemSearch.getLeaf() > 0) {
            try {
                List<String> pCategory = recentPostsCategory(itemSearch.getSellerId());
                childCats = categoryRepository.get(pCategory);
            } catch (Exception e) {
                childCats = new ArrayList<>();
            }
        } else {
            childCats = categoryRepository.getActiveChilds(cateId);
        }
        for (Category cat : childCats) {
            queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("category-" + cat.getId()).filter(new TermFilterBuilder("categoryPath", cat.getId())), true));
        }

        FacetResult result = itemSearchRepository.getFacets(queryBuilder.build());

        List<CategoryHistogram> categoryHistogram = new ArrayList<>();
        for (Category cat : childCats) {
            FilterFacet facet = result.getFacet(FilterFacet.class, "category-" + cat.getId());
            if (facet != null) {
                CategoryHistogram ch = new CategoryHistogram();
                ch.setId(cat.getId());
                ch.setName(cat.getName());
                ch.setCount((int) facet.getCount());
                categoryHistogram.add(ch);
            }
        }

        return categoryHistogram;
    }

    @Cacheable(value = "datacache", key = "'manufacturerh-'.concat(#itemSearch.getKey())")
    public List<ManufacturerHistogram> getManufacturerHistogram(ItemSearch itemSearch) {
        itemSearch.setManufacturerIds(null);
        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(itemSearch, false);

        List<ManufacturerHistogram> manufacturerHistogram = new ArrayList<>();

        if (itemSearch.getCategoryIds() == null) {
            return manufacturerHistogram;
        }

        String cateId = null;
        if (itemSearch.getCategoryIds() != null && !itemSearch.getCategoryIds().isEmpty()) {
            cateId = itemSearch.getCategoryIds().get(0);
        }

        List<CategoryManufacturer> catManus = categoryManufacturerRepository.search(new Criteria("categoryId").is(cateId).and("filter").is(true), 0, 300);
        for (CategoryManufacturer cm : catManus) {
            queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("manufacturer-" + cm.getManufacturerId()).filter(new TermFilterBuilder("manufacturerId", cm.getManufacturerId())), true));
        }

        FacetResult result = itemSearchRepository.getFacets(queryBuilder.build());

        List<String> manuIds = new ArrayList<>();
        for (CategoryManufacturer cm : catManus) {
            manuIds.add(cm.getManufacturerId());
        }
        List<Manufacturer> manus = manufacturerRepository.get(manuIds);
        for (Manufacturer man : manus) {
            FilterFacet facet = result.getFacet(FilterFacet.class, "manufacturer-" + man.getId());
            if (facet != null) {
                ManufacturerHistogram mh = new ManufacturerHistogram();
                mh.setId(man.getId());
                mh.setName(man.getName());
                mh.setCount((int) facet.getCount());
                manufacturerHistogram.add(mh);
            }
        }

        return manufacturerHistogram;

    }

    @Cacheable(value = "datacache", key = "'propertyh-'.concat(#itemSearch.getKey())")
    public List<PropertyHistogram> getPropertyHistogram(ItemSearch itemSearch) {
        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(itemSearch, false);

        String cateId = null;
        if (itemSearch.getCategoryIds() != null && !itemSearch.getCategoryIds().isEmpty()) {
            cateId = itemSearch.getCategoryIds().get(0);
        }
        List<CategoryProperty> properties = categoryPropertyRepository.getFilter(cateId);
        List<CategoryPropertyValue> propertyValues = categoryPropertyValueRepository.getByCategory(cateId);
        if (properties != null) {
            for (CategoryProperty p : properties) {
                for (CategoryPropertyValue v : propertyValues) {
                    if (p.getId().equals(v.getCategoryPropertyId())) {
                        FilterBuilder[] prosFilter = new FilterBuilder[2];
                        prosFilter[0] = new TermFilterBuilder("properties.name", p.getName());

                        if (p.getOperator() == PropertyOperator.GTE) {
                            prosFilter[1] = new RangeFilterBuilder("value").gte(v.getValue());
                        } else if (p.getOperator() == PropertyOperator.LTE) {
                            prosFilter[1] = new RangeFilterBuilder("value").lte(v.getValue());
                        } else {
                            prosFilter[1] = new TermFilterBuilder("value", v.getValue());
                        }
                        queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("properties-" + p.getId() + "-" + v.getId()).filter(new NestedFilterBuilder("properties", new AndFilterBuilder(prosFilter))), true));
                    }
                }
            }
        }

        FacetResult result = itemSearchRepository.getFacets(queryBuilder.build());

        List<PropertyHistogram> propertyHistogram = new ArrayList<>();
        if (properties != null) {
            for (CategoryProperty p : properties) {
                PropertyHistogram ph = new PropertyHistogram();
                ph.setName(p.getName());
                ph.setType(p.getType());
                ph.setCount(0);
                ph.setOperator(p.getOperator());
                ph.setValues(new ArrayList<PropertyValueHistogram>());
                for (CategoryPropertyValue v : propertyValues) {
                    FilterFacet facet = result.getFacet(FilterFacet.class, "properties-" + p.getId() + "-" + v.getId());
                    if (facet != null) {
                        PropertyValueHistogram pvh = new PropertyValueHistogram();
                        pvh.setName(v.getName());
                        pvh.setValue(v.getValue());
                        pvh.setCount((int) facet.getCount());
                        ph.setCount(ph.getCount() + pvh.getCount());
                        ph.getValues().add(pvh);
                    }
                }
                propertyHistogram.add(ph);
            }
        }

        return propertyHistogram;
    }

    /**
     * Đếm sản phẩm
     *
     * @return
     */
    public double countItem() {
        return itemRepository.count();
    }

    public Item quickEdit(Item item, String administratorId, String fieldUpdate) throws Exception {
        Item oldItem = itemRepository.find(item.getId());
        if (oldItem == null) {
            throw new Exception("Sản phẩm không tồn tại");
        }
        if (administratorId == null && !oldItem.getSellerId().equals(item.getSellerId())) {
            throw new Exception("Bạn không có quyền sửa sản phẩm này");
        }
        if (fieldUpdate != null && fieldUpdate.trim().equalsIgnoreCase("startPrice")) {
            if (item.getStartPrice() < 0) {
                throw new Exception("Giá gốc phải lớn hơn 0");
            } else {
                oldItem.setStartPrice(item.getStartPrice());
            }
        }
        if (fieldUpdate != null && fieldUpdate.trim().equalsIgnoreCase("name")) {
            if (item.getName() == null || item.getName().trim().equals("") || item.getName().length() > 180) {
                throw new Exception("Tên phải có ít nhất 1 ký tự và nhiều nhất 180 ký tự");
            } else {
                oldItem.setName(item.getName());
            }
        }
        if (fieldUpdate != null && fieldUpdate.trim().equalsIgnoreCase("sellPrice")) {
            if (item.getSellPrice() <= 0) {
                throw new Exception("Giá bán phải lớn hơn 0");
            } else {
                oldItem.setSellPrice(item.getSellPrice());
            }
        }
        if (fieldUpdate != null && fieldUpdate.trim().equalsIgnoreCase("quantity")) {
            if (item.getQuantity() < 0) {
                throw new Exception("Số lượng sản phẩm phải lớn hơn hoặc bằng 0");
            } else {
                oldItem.setQuantity(item.getQuantity());
            }
        }
        if (fieldUpdate != null && fieldUpdate.trim().equalsIgnoreCase("weight")) {
            if (item.getWeight() <= 0) {
                throw new Exception("Khối lượng sản phẩm phải lớn hơn 0");
            } else {
                oldItem.setWeight(item.getWeight());
            }
        }

        itemRepository.save(oldItem);
        if (fieldUpdate != null && fieldUpdate.trim().equalsIgnoreCase("sellPrice") && item.getModelId() != null && !item.getModelId().equals("")) {
            Model model = modelRepository.find(item.getId());
            if (model != null) {
                this.updateModel(model);
            }
        }
        itemHistoryService.create(item, false, ItemHistoryType.UPDATE);
        searchIndexService.processIndexItem(oldItem);
        return oldItem;
    }

    /**
     * đếm số lượng items đã được index
     *
     * @return
     */
    public long countElastic() {
        return itemSearchRepository.count();
    }

    /**
     * đếm số lượng items đã được theo người bán
     *
     * @param id
     * @return
     */
    public long countBySeller(String id) {
        return itemRepository.countBySeller(id);
    }

    /**
     * Lấy sản phẩm của người bán
     *
     * @param page
     * @param sellerId
     * @return
     */
    public List<Item> getBySeller(Pageable page, String sellerId) {
        return itemRepository.getBySeller(page, sellerId);
    }

    /**
     * Lấy danh sách sản phẩm theo ids
     *
     * @param ids
     * @return
     * @throws Exception
     */
    public List<Item> list(List<String> ids) {
        List<Item> list = itemRepository.get(ids);
        for (Item item : list) {
            item.setImages(imageService.get(ImageType.ITEM, item.getId()));
        }
        return list;
    }

    void updateSellerCityId(String sellerId) throws Exception {
        User seller = userRepository.find(sellerId);
        if (seller == null) {
            throw new Exception("Người bán chưa tồn tại");
        }
        itemRepository.updateCityId(sellerId, seller.getCityId());
        indexSellerItem(sellerId);
    }

    /**
     * Index lại toàn bộ sản phẩm của 1 người bán
     *
     * @param sellerId
     * @throws Exception
     */
    @Async
    public void indexSellerItem(String sellerId) {
        long total = itemRepository.countBySeller(sellerId);
        int totalPage = (int) total / 1000;
        if (total % 1000 != 0) {
            totalPage++;
        }
        for (int i = 0; i < totalPage; i++) {
            searchIndexService.processIndexPageItem(itemRepository.getBySeller(new PageRequest(i, 1000), sellerId));
        }
    }

    /**
     * List Item theo SellerId
     *
     * @param id
     * @return
     */
    public List<Item> getBSeller(String id) {
        return itemRepository.getBSeller(id);
    }

    /**
     * List Item theo SellerId ra trang chủ shop
     *
     * @param id
     * @return
     */
    public List<Item> getBSellerLimit(String id) {
        return itemRepository.getBSellerLimit(id);
    }

    /**
     * List Item theo ModelId
     *
     * @param id
     * @return
     */
    public List<Item> getBModel(String id) {
        return itemRepository.getBModel(id);
    }

    /**
     * Đếm sản phẩm trong trang model browse
     *
     * @param modelSearch
     * @param ignoreSearch
     * @return
     */
    @Cacheable(value = "datacache", key = "'itemcount-'.concat(#modelSearch.getKey()).concat(#ignoreSearch)")
    public long getItemCountByModel(ModelSearch modelSearch, boolean ignoreSearch) {

        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setStatus(modelSearch.getStatus());
        itemSearch.setKeyword(modelSearch.getKeyword());
        itemSearch.setManufacturerIds(modelSearch.getManufacturerIds());
        List<String> cids = new ArrayList<>();
        if (modelSearch.getCategoryId() != null && !modelSearch.getCategoryId().equals("")) {
            cids.add(modelSearch.getCategoryId());
        }
        itemSearch.setCategoryIds(cids);

        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(itemSearch, ignoreSearch);

        queryBuilder.withFacet(new NativeFacetRequest(FacetBuilders.filterFacet("itemCount", new QueryFilterBuilder(new MatchAllQueryBuilder())), true));

        FacetResult result = itemSearchRepository.getFacets(queryBuilder.build());

        FilterFacet facet;

        facet = result.getFacet(FilterFacet.class, "itemCount");
        if (facet != null) {
            return facet.getCount();
        }
        return 0;
    }

    /**
     * Làm mới
     *
     * @param ids
     * @return
     */
    public Response refresh(List<String> ids) {
        try {
            List<Item> items = itemRepository.get(ids);
            long time = System.currentTimeMillis();
            time = time + Long.parseLong("2592000000");
            for (Item item : items) {
                item.setEndTime(time);
                if (item.getModelId() != null && !item.getModelId().equals("")) {
                    Model model = modelRepository.find(item.getId());
                    if (model != null) {
                        this.updateModel(model);
                    }
                }
                itemHistoryService.create(item, false, ItemHistoryType.UPDATE);
                itemRepository.save(item);
            }
            searchIndexService.processIndexPageItem(items);
            return new Response(true, "Sản phẩm đã được gia hạn thành công");
        } catch (Exception ex) {
            return new Response(false, ex.getMessage());
        }
    }

    public Item cloneItem(String id, String sellerId) throws Exception {
        Item oldItem = get(id);
        ItemDetail detail = getDetail(id);
        List<ItemProperty> properties = getProperties(id);

        oldItem.setId(itemRepository.genId());
        oldItem.setCompleted(false);
        if (!oldItem.getSellerId().equals(sellerId)) {
            oldItem.setSellerId(sellerId);
            oldItem.setSellerName("");
            oldItem.setShopName("");
            oldItem.setShopCategoryPath(new ArrayList<String>());
            oldItem.setShopCategoryId(null);
            oldItem.setShopDescription(null);
            oldItem.setOnlinePayment(false);
            oldItem.setCod(false);
            oldItem.setShipmentPrice(0);
            oldItem.setShipmentType(ShipmentType.AGREEMENT);
        }
        oldItem.setApproved(false);
        oldItem.setActive(false);
        oldItem.setStartTime(System.currentTimeMillis());
        oldItem.setEndTime(oldItem.getStartTime() + 30 * 12 * 60 * 60 * 1000);
        oldItem.setStartPrice(0);
        oldItem.setSellPrice(0);
        oldItem.setBidStep(0);
        oldItem.setHighestBid(0);
        oldItem.setHighestBider(null);
        oldItem.setBidCount(0);
        oldItem.setViewCount(0);
        oldItem.setViewTime(0);
        oldItem.setPromotionId(null);
        oldItem.setDiscount(false);
        oldItem.setGift(false);
        oldItem.setGiftDetail("");
        oldItem.setDiscountPercent(0);
        oldItem.setDiscountPrice(0);
        oldItem.setCreateTime(System.currentTimeMillis());
        oldItem.setUpdateTime(System.currentTimeMillis());
        oldItem.setUpTime(0);
        oldItem.setSource(ItemSource.SELLER);
        oldItem.setSellerSku(null);
        itemHistoryService.create(oldItem, false, ItemHistoryType.CREATE);
        itemRepository.save(oldItem);

        detail.setItemId(oldItem.getId());
        itemDetailRepository.save(detail);

        for (ItemProperty itemProperty : properties) {
            itemProperty.setItemId(oldItem.getId());
            itemPropertyRepository.save(itemProperty);
        }
        for (String img : oldItem.getImages()) {
            ImageUrl url = imageService.getUrl(img);
            if (url != null) {
                imageService.download(url.getUrl(), ImageType.ITEM, oldItem.getId());
            }
        }
        searchIndexService.processIndexItem(oldItem);

        return oldItem;
    }

    /**
     * Lấy danh mục đã đăng của người bán
     *
     * @param sellerId
     * @return
     * @throws Exception
     */
    public List<String> recentPostsCategory(String sellerId) throws Exception {
        return itemRepository.getDistincCateByUser(sellerId);
    }

    /**
     * Set view count
     *
     * @param item
     */
    public void viewcount(Item item) {
        item.setViewCount(item.getViewCount() + 1);
        itemRepository.save(item);
        searchIndexService.processIndexItem(item);
    }

    @Cacheable(value = "datacache", key = "'shopcategoryh-'.concat(#itemSearch.getKey()).concat(#ignoreSearch)")
    public List<CategoryHistogram> getShopCategoryHistogram(ItemSearch itemSearch, boolean ignoreSearch) {
        NativeSearchQueryBuilder queryBuilder = buildSearchCondition(itemSearch, ignoreSearch);

        String shopcateId = null;
        if (itemSearch.getShopCategoryId() != null && !itemSearch.getShopCategoryId().equals("")) {
            shopcateId = itemSearch.getShopCategoryId();
        }
        List<ShopCategory> childCats = shopCategoryRepository.getChilds(shopcateId, itemSearch.getSellerId());

        for (ShopCategory cat : childCats) {
            queryBuilder.withFacet(new NativeFacetRequest(new FilterFacetBuilder("shopcategory-" + cat.getId()).filter(new TermFilterBuilder("shopCategoryPath", cat.getId())), true));
        }

        FacetResult result = itemSearchRepository.getFacets(queryBuilder.build());

        List<CategoryHistogram> categoryHistogram = new ArrayList<>();
        for (ShopCategory cat : childCats) {
            FilterFacet facet = result.getFacet(FilterFacet.class, "shopcategory-" + cat.getId());
            if (facet != null) {
                CategoryHistogram ch = new CategoryHistogram();
                ch.setId(cat.getId());
                ch.setName(cat.getName());
                ch.setCount((int) facet.getCount());
                categoryHistogram.add(ch);
            }
        }

        return categoryHistogram;
    }

    public long getItemByModelCount(ModelSearch modelSearch) {
        Criteria criteria = new Criteria();
        if (modelSearch.getModelId() != null && !modelSearch.getModelId().equals("")) {
            criteria.and("modelId").is(modelSearch.getModelId());
        }
        criteria.and("completed").is(true);
        criteria.and("active").is(true);
        criteria.and("approved").is(true);
        criteria.and("sellPrice").gt(0);
        criteria.and("quantity").gt(0);
        long timeNow = new Date().getTime();
        criteria.and("startTime").lte(timeNow);
        criteria.and("endTime").gte(timeNow);
        return itemRepository.countSellerByModel(criteria);
    }

    /**
     * Lấy danh sách sp không được duyệt theo ids
     *
     * @param ids
     * @return
     */
    public List<ItemApprove> getItemApproveByIds(List<String> ids) {
        return itemApproveRepository.getNoteApproveByIds(ids);
    }

    public Item findBySellerSku(String sku) {
        return itemRepository.getBySellerSku(sku);
    }

    public Item findBySellerSku(String sku, String sellerId) {
        return itemRepository.getBySellerSku(sku, sellerId);
    }
}
