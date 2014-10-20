package vn.chodientu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.Promotion;
import vn.chodientu.entity.db.PromotionCategory;
import vn.chodientu.entity.db.PromotionItem;
import vn.chodientu.entity.db.Shop;
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.PromotionTarget;
import vn.chodientu.entity.enu.PromotionType;
import vn.chodientu.entity.input.PromotionSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.CategoryRepository;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.PromotionCategoryRepository;
import vn.chodientu.repository.PromotionItemRepository;
import vn.chodientu.repository.PromotionRepository;
import vn.chodientu.repository.ShopCategoryRepository;
import vn.chodientu.util.TextUtils;

/**
 *
 * @author thanhvv
 */
@Service
public class PromotionService {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PromotionService.class);

    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private PromotionCategoryRepository promotionCategoryRepository;
    @Autowired
    private PromotionItemRepository promotionItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ShopCategoryRepository shopCategoryRepository;
    @Autowired
    private SearchIndexService searchIndexService;
    @Autowired
    private Validator validator;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CashService cashService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private Viewer viewer;

    public Response sync(Promotion promotion) {
        Promotion pro = promotionRepository.find(promotion.getId());
        if (pro != null) {
            pro.setActive(promotion.isActive());
            promotionRepository.save(pro);
            return new Response(true);
        } else {
            return this.add(promotion);
        }
    }

    public Response add(Promotion promotion) {
        Map<String, String> errors = validator.validate(promotion);

        if (promotion.getId() != null) {
            if (promotionRepository.exists(promotion.getId())) {
                errors.put("id", "Chương trình khuyến mại đã tồn tại");
            }
        }
        String mess = checkPromotion(promotion, null);
        if (((promotion.getCategories() == null || promotion.getCategories().isEmpty())
                && (promotion.getItems() == null || promotion.getItems().isEmpty()))
                || (promotion.getItems() != null && promotion.getItems().size() < 4 && promotion.getType() == PromotionType.DISCOUND && promotion.getTarget() == PromotionTarget.ITEM)) {
            if (promotion.getItems() != null && promotion.getItems().size() < 4 && promotion.getType() == PromotionType.DISCOUND && promotion.getTarget() == PromotionTarget.ITEM) {
                errors.put("categories", "Phải có ít nhất 4 sản phẩm được giảm giá." + mess + ". Vui lòng xem lại");
            } else {
                errors.put("categories", "Phải có ít nhất 1 khuyến mại khả dụng." + mess + ". Vui lòng xem lại");
            }
        }
        if (errors.isEmpty()) {
            promotion.setPublished(false);
            promotion.setId(promotionRepository.genId());
            promotionRepository.save(promotion);
            if (promotion.getTarget() == PromotionTarget.CATEGORY || promotion.getTarget() == PromotionTarget.SHOP_CATEGORY) {
                if (promotion.getCategories() != null && !promotion.getCategories().isEmpty()) {
                    for (PromotionCategory cat : promotion.getCategories()) {
                        cat.setPromotionId(promotion.getId());
                        cat.setId(promotionCategoryRepository.genId());
                        promotionCategoryRepository.save(cat);
                    }
                }
            } else {
                if (promotion.getItems() != null && !promotion.getItems().isEmpty()) {
                    for (PromotionItem item : promotion.getItems()) {
                        item.setPromotionId(promotion.getId());
                        item.setId(promotionItemRepository.genId());
                        promotionItemRepository.save(item);
                    }
                }
            }
            if (mess.trim().equals("")) {
                mess = "Tạo khuyến mại thành công";
            }
            try {
                Shop shop = null;
                if (promotion.getTarget() == PromotionTarget.SHOP_CATEGORY) {
                    shop = shopService.getShop(promotion.getSellerId());
                }
                cashService.reward(CashTransactionType.SELLER_CREATE_PROMOTION, viewer.getUser().getId(), promotion.getId(), (promotion.getTarget() == PromotionTarget.SHOP_CATEGORY ? "/" + shop.getAlias() + "/browse.html?promotionId=" + promotion.getId() + "" : ""), null);
                return new Response(true, mess, promotion);
            } catch (Exception e) {
                return new Response(true, "SELLER_CREATE_PROMOTION_FAIL", promotion);
            }
        } else {
            return new Response(false, "failed", errors);
        }
    }

    private String checkPromotion(Promotion promotion, String promotionId) {

        List<Promotion> promotionSameTime = promotionRepository.promotionSameTime(promotion.getStartTime(), promotion.getEndTime(), promotion.getType(), promotion.getTarget(), promotion.getSellerId(), promotionId);
        if (promotionSameTime != null && !promotionSameTime.isEmpty()) {
            List<String> proIds = new ArrayList<>();
            for (Promotion promo : promotionSameTime) {
                proIds.add(promo.getId());
            }
            if (promotion.getTarget() == PromotionTarget.CATEGORY) {
                List<PromotionCategory> listPromotionCategories = promotionCategoryRepository.getList(proIds);
                List<PromotionCategory> existedCate = new ArrayList<>();
                List<String> cateIds = new ArrayList<>();
                List<String> proId = new ArrayList<>();

                for (PromotionCategory pc : promotion.getCategories()) {
                    for (PromotionCategory promotionCategory : listPromotionCategories) {
                        if (pc.getCategoryId() != null && !pc.getCategoryId().equals("")
                                && pc.getCategoryId().equals(promotionCategory.getCategoryId())) {
                            existedCate.add(pc);
                            cateIds.add(pc.getCategoryId());
                            if (!proId.contains(promotionCategory.getPromotionId())) {
                                proId.add(promotionCategory.getPromotionId());
                            }
                            break;
                        }
                    }
                }
                promotion.getCategories().removeAll(existedCate);
                if (!existedCate.isEmpty()) {
                    String mess = "Các danh mục: ";

                    List<Category> cates = categoryRepository.get(cateIds);
                    for (Category cate : cates) {
                        mess += cate.getName() + ", ";
                    }
                    mess += "không được áp dụng do đã tồn tại trong khuyến mại mã: ";
                    for (String p : proId) {
                        mess += p + ", ";
                    }
                    return mess + " được tạo trước đó";
                }
            } else if (promotion.getTarget() == PromotionTarget.SHOP_CATEGORY) {
                List<PromotionCategory> listPromotionCategories = promotionCategoryRepository.getList(proIds);
                List<PromotionCategory> existedCate = new ArrayList<>();
                List<String> cateIds = new ArrayList<>();
                List<String> proId = new ArrayList<>();

                for (PromotionCategory pc : promotion.getCategories()) {
                    for (PromotionCategory promotionCategory : listPromotionCategories) {
                        if (pc.getShopCategoryId() != null && !pc.getShopCategoryId().equals("")
                                && pc.getShopCategoryId().equals(promotionCategory.getShopCategoryId())) {
                            existedCate.add(pc);
                            cateIds.add(pc.getShopCategoryId());
                            if (!proId.contains(promotionCategory.getPromotionId())) {
                                proId.add(promotionCategory.getPromotionId());
                            }
                            break;
                        }
                    }
                }

                promotion.getCategories().removeAll(existedCate);
                if (!existedCate.isEmpty()) {
                    String mess = "Các danh mục: ";

                    List<ShopCategory> cates = shopCategoryRepository.get(cateIds);
                    for (ShopCategory cate : cates) {
                        mess += cate.getName() + ", ";
                    }
                    mess += "không được áp dụng do đã tồn tại trong khuyến mại mã: ";
                    for (String p : proId) {
                        mess += p + ", ";
                    }
                    return mess + " được tạo trước đó";
                }
            } else {
                List<PromotionItem> listPromotionItems = promotionItemRepository.getList(proIds);
                List<PromotionItem> existedItem = new ArrayList<>();
                List<String> proId = new ArrayList<>();

                for (PromotionItem pi : promotion.getItems()) {
                    for (PromotionItem promotionItem : listPromotionItems) {
                        if (pi.getItemId().equals(promotionItem.getItemId())) {
                            existedItem.add(pi);
                            if (!proId.contains(promotionItem.getPromotionId())) {
                                proId.add(promotionItem.getPromotionId());
                            }
                            break;
                        }
                    }
                }
                promotion.getItems().removeAll(existedItem);
                if (!existedItem.isEmpty()) {
                    String mess = "Sản phẩm : ";

                    for (PromotionItem promotionItem : existedItem) {
                        mess += promotionItem.getItemId() + ", ";

                    }
                    mess += "không được áp dụng do đã tồn tại trong khuyến mại mã: ";
                    for (String p : proId) {
                        mess += p + ", ";
                    }
                    return mess + " được tạo trước đó";
                }
            }
        }
        return "";
    }

    @Scheduled(fixedDelay = 60000)
    public void publicPromotion() {
        while (true) {
            Promotion promotion = promotionRepository.getForPublic();
            if (promotion == null) {
                break;
            }
            publicPromotion(promotion);
        }
    }

    @Scheduled(fixedDelay = 60000)
    public void unPublicPromotion() {
        while (true) {
            Promotion promotion = promotionRepository.getForUnPublic();
            if (promotion == null) {
                break;
            }
            unpublicPromotion(promotion);
        }
    }

    private void unpublicPromotion(Promotion promotion) {
        if (promotion.getTarget() == PromotionTarget.CATEGORY || promotion.getTarget() == PromotionTarget.SHOP_CATEGORY) {
            List<PromotionCategory> promotionCategories = promotionCategoryRepository.get(promotion.getId());
            if (promotionCategories != null && !promotionCategories.isEmpty()) {
                for (PromotionCategory promotionCategory : promotionCategories) {
                    for (Item item : itemRepository.getByCategory(promotionCategory.getShopCategoryId(), promotionCategory.getCategoryId(), promotion.getSellerId())) {
                        item.setDiscount(false);
                        item.setPromotionId(null);
                        item.setDiscountPrice(0);
                        item.setDiscountPercent(0);
                        item.setGift(false);
                        item.setGiftDetail(null);
                        itemRepository.save(item);
                        try {
                            searchIndexService.processIndexItem(item);
                        } catch (Exception ex) {
                        }
                    }
                }
            }
        } else {
            List<PromotionItem> promotionItems = promotionItemRepository.get(promotion.getId());
            if (promotionItems != null && !promotionItems.isEmpty()) {
                for (PromotionItem promotionItem : promotionItems) {
                    Item item = itemRepository.find(promotionItem.getItemId());
                    if (item != null) {
                        item.setDiscount(false);
                        item.setPromotionId(null);
                        item.setDiscountPrice(0);
                        item.setDiscountPercent(0);
                        item.setGift(false);
                        item.setGiftDetail(null);
                        if (promotion.isOldPromotion()) {
                            long oldPrice = promotionItem.getOldPrice();
                            promotionItem.setOldPrice(item.getSellPrice());
                            promotionItemRepository.save(promotionItem);
                            item.setSellPrice(oldPrice);
                        }
                        itemRepository.save(item);
                        try {
                            searchIndexService.processIndexItem(item);
                        } catch (Exception ex) {
                        }
                    }
                }
            }
        }
        promotion.setPublished(false);
        promotionRepository.save(promotion);
    }

    private void publicPromotion(Promotion promotion) {
        if (promotion.getTarget() == PromotionTarget.CATEGORY || promotion.getTarget() == PromotionTarget.SHOP_CATEGORY) {
            List<PromotionCategory> promotionCategories = promotionCategoryRepository.get(promotion.getId());
            for (PromotionCategory promotionCategory : promotionCategories) {
                List<Item> lItem = itemRepository.getByCategory(promotionCategory.getShopCategoryId(), promotionCategory.getCategoryId(), promotion.getSellerId());
                for (Item item : lItem) {
                    if (promotion.getType() == PromotionType.DISCOUND
                            && (item.getDiscountPrice() < promotionCategory.getDiscountPrice() || item.getDiscountPercent() < promotionCategory.getDiscountPercent())
                            && item.getSellPrice() > promotionCategory.getDiscountPrice()) {
                        item.setDiscount(true);
                        item.setDiscountPrice(promotionCategory.getDiscountPrice());
                        item.setDiscountPercent(promotionCategory.getDiscountPercent());
                        item.setPromotionId(promotion.getId());
                        itemRepository.save(item);
                    } else if (promotion.getType() == PromotionType.GIFT && !item.isGift()) {
                        item.setGift(true);
                        item.setGiftDetail(promotionCategory.getGift());
                        if (item.getPromotionId() == null || item.getPromotionId().equals("")) {
                            item.setPromotionId(promotion.getId());
                        }
                        itemRepository.save(item);
                    }
                    try {
                        searchIndexService.processIndexItem(item);
                    } catch (Exception ex) {
                    }
                }
            }
        } else {
            List<PromotionItem> promotionItems = promotionItemRepository.get(promotion.getId());
            for (PromotionItem promotionItem : promotionItems) {
                Item item = itemRepository.find(promotionItem.getItemId());
                if (item != null) {
                    if (promotion.getType() == PromotionType.DISCOUND
                            && (item.getDiscountPrice() < promotionItem.getDiscountPrice() || item.getDiscountPercent() < promotionItem.getDiscountPercent())
                            && item.getSellPrice() > promotionItem.getDiscountPrice()) {
                        item.setDiscount(true);
                        item.setDiscountPrice(promotionItem.getDiscountPrice());
                        item.setDiscountPercent(promotionItem.getDiscountPercent());
                        if (promotion.isOldPromotion()) {
                            long oldPrice = promotionItem.getOldPrice();
                            promotionItem.setOldPrice(item.getSellPrice());
                            promotionItemRepository.save(promotionItem);
                            item.setSellPrice(oldPrice);
                        }
                        item.setPromotionId(promotion.getId());
                        itemRepository.save(item);
                    } else if (promotion.getType() == PromotionType.GIFT && !item.isGift()) {
                        item.setGift(true);
                        item.setGiftDetail(promotionItem.getGift());
                        if (item.getPromotionId() == null || item.getPromotionId().equals("")) {
                            item.setPromotionId(promotion.getId());
                        }
                        itemRepository.save(item);
                    }

                    try {
                        searchIndexService.processIndexItem(item);
                    } catch (Exception ex) {
                    }
                }
            }
        }
        promotion.setPublished(true);
        promotionRepository.save(promotion);
    }

    public PromotionItem getPromotionItem(String promotionId, String itemId) {
        return promotionItemRepository.getPromotionItem(promotionId, itemId);
    }

    public PromotionCategory getPromotionCategory(String promotionId, String cateId) {
        return promotionCategoryRepository.getPromotionCategory(promotionId, cateId);
    }

    public Promotion getPromotion(String id) {
        return promotionRepository.find(id);
    }

    public List<PromotionCategory> getPromotionCategory(String promId) {
        List<PromotionCategory> pc = promotionCategoryRepository.get(promId);
        if (pc != null && !pc.isEmpty()) {
            for (PromotionCategory promotionCategory : pc) {
                if (promotionCategory.getCategoryId() != null && !promotionCategory.getCategoryId().equals("")) {
                    Category cate = categoryRepository.find(promotionCategory.getCategoryId());
                    promotionCategory.setCatePath(cate == null ? new ArrayList<String>() : cate.getPath());
                } else {
                    ShopCategory shopCate = shopCategoryRepository.find(promotionCategory.getShopCategoryId());
                    promotionCategory.setCatePath(shopCate == null ? new ArrayList<String>() : shopCate.getPath());
                }
            }
        }
        return pc;
    }

    /**
     * lấy danh sách khuyến mãi theo người bán
     *
     * @param sellerId
     * @param type
     * @param pageIndex
     * @return
     */
    public DataPage<Promotion> getBySeller(String sellerId, PromotionType type, int pageIndex) {
        DataPage<Promotion> page = new DataPage<>();
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        page.setPageSize(1);
        page.setDataCount(promotionRepository.getCountBySeller(sellerId, type));
        page.setData(promotionRepository.getBySeller(sellerId, type, pageIndex));
        page.setPageIndex(pageIndex);
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() > 0) {
            page.setPageCount(page.getPageCount() + 1);
        }
        return page;
    }

    /**
     * tìm kiếm sản phẩm trực tiếp mongo
     *
     * @param sellerId
     * @param type
     * @param target
     * @param pageIndex
     * @param pageSize
     * @return kết quả là danh sách sản phẩm đủ điều kiện theo trang
     */
    public DataPage<Promotion> search(String sellerId, PromotionType type, PromotionTarget target, int pageIndex, int pageSize) {
        Criteria criteria = new Criteria();
        criteria.and("sellerId").is(sellerId);
        if (type != null) {
            criteria.and("type").is(type);
        } else {
            criteria.and("active").is(false);
        }
        if (target != null) {
            criteria.and("target").is(target);
        }

        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "startTime"));
        query.skip(pageIndex * pageSize).limit(pageSize);
        DataPage<Promotion> page = new DataPage<>();
        page.setPageSize(pageSize);
        page.setPageIndex(pageIndex);
        if (page.getPageSize() <= 0) {
            page.setPageSize(1);
        }

        page.setDataCount(promotionRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }

        page.setData(promotionRepository.find(query));
        return page;
    }

    public DataPage<Promotion> search(PromotionSearch promotionSearch) {
        Criteria criteria = new Criteria();
        if (promotionSearch.getSellerId() != null && !promotionSearch.getSellerId().equals("")) {
            criteria.and("sellerId").is(promotionSearch.getSellerId());
        }
        if (promotionSearch.getType() != null) {
            criteria.and("type").is(promotionSearch.getType().toString());
        }
        if (promotionSearch.getStatus() > 0) {
            if (promotionSearch.getStatus() == 1) {
                criteria.and("active").is(true);
            }
            if (promotionSearch.getStatus() == 2) {
                criteria.and("active").is(false);
            }

        }
        if (promotionSearch.getTarget() != null) {
            criteria.and("target").is(promotionSearch.getTarget().toString());
        }
        if (promotionSearch.getStartTime() > 0 && promotionSearch.getEndTime() > 0) {
            criteria.and("startTime").gte(promotionSearch.getStartTime());
            criteria.and("endTime").lte(promotionSearch.getEndTime());
        }
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "startTime"));
        query.skip(promotionSearch.getPageIndex() * promotionSearch.getPageSize()).limit(promotionSearch.getPageSize());
        DataPage<Promotion> page = new DataPage<>();
        page.setPageSize(promotionSearch.getPageSize());
        page.setPageIndex(promotionSearch.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(1);
        }

        page.setDataCount(promotionRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }

        page.setData(promotionRepository.find(query));
        return page;
    }

    /**
     * Lấy danh sách những khuyến mại đã xóa
     *
     * @param sellerId
     * @param pageIndex
     * @return
     */
    public DataPage<Promotion> getDelBySeller(String sellerId, int pageIndex) {
        DataPage<Promotion> page = new DataPage<>();
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        page.setDataCount(promotionRepository.getCountDelBySeller(sellerId));
        page.setData(promotionRepository.getDelBySeller(sellerId, pageIndex));
        page.setPageIndex(pageIndex);
        page.setPageSize(10);
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() > 0) {
            page.setPageCount(page.getPageCount() + 1);
        }
        return page;
    }

    /**
     * Sửa khuyến mại
     *
     * @param promotion
     * @return
     */
    public Response update(Promotion promotion) {
        Map<String, String> errors = validator.validate(promotion);
        Promotion oldPromotion = promotionRepository.find(promotion.getId());
        if (promotion.getId() == null || oldPromotion == null) {
            errors.put("id", "Chương trình khuyến mại không tồn tại");
        }
        if (oldPromotion != null && !oldPromotion.getSellerId().equals(promotion.getSellerId())) {
            errors.put("seller", "Bạn không có quyền sửa khuyến mại này");
        }
        if (oldPromotion != null && oldPromotion.isOldPromotion()) {
            errors.put("categories", "Chương trình khuyến mại đã tạo ở phiên bản chodientu.vn cũ không được phép sửa, chỉ được phép dừng");
        }
        String mess = checkPromotion(promotion, promotion.getId());
        if (((promotion.getCategories() == null || promotion.getCategories().isEmpty())
                && (promotion.getItems() == null || promotion.getItems().isEmpty()))
                || (promotion.getItems() != null && promotion.getItems().size() < 4 && promotion.getType() == PromotionType.DISCOUND && promotion.getTarget() == PromotionTarget.ITEM)) {
            if (promotion.getItems() != null && promotion.getItems().size() < 4 && promotion.getType() == PromotionType.DISCOUND && promotion.getTarget() == PromotionTarget.ITEM) {
                errors.put("categories", "Phải có ít nhất 4 sản phẩm được giảm giá." + mess + ". Vui lòng xem lại");
            } else {
                errors.put("categories", "Phải có ít nhất 1 khuyến mại khả dụng." + mess + ". Vui lòng xem lại");
            }
        }
        if (errors.isEmpty()) {
            promotion.setPublished(false);
            promotion.setActive(true);
            promotionRepository.save(promotion);
            if (promotion.getTarget() == PromotionTarget.CATEGORY || promotion.getTarget() == PromotionTarget.SHOP_CATEGORY) {
                promotionCategoryRepository.remove(promotion.getId());
                if (promotion.getCategories() != null && !promotion.getCategories().isEmpty()) {
                    for (PromotionCategory cat : promotion.getCategories()) {
                        cat.setPromotionId(promotion.getId());
                        cat.setId(promotionCategoryRepository.genId());
                        promotionCategoryRepository.save(cat);
                    }
                }
            } else {
                promotionItemRepository.remove(promotion.getId());
                if (promotion.getItems() != null && !promotion.getItems().isEmpty()) {
                    for (PromotionItem item : promotion.getItems()) {
                        item.setPromotionId(promotion.getId());
                        item.setId(promotionItemRepository.genId());
                        promotionItemRepository.save(item);
                    }
                }
            }
            if (mess.trim().equals("")) {
                mess = "Sửa khuyến mại thành công";
            }
            return new Response(true, mess, promotion);
        } else {
            return new Response(false, "Sửa khuyến mại thất bại", errors);
        }
    }

    public Response stopPromotion(String promotionId, String sellerId) {
        Promotion promotion = promotionRepository.find(promotionId);
        if (promotion == null || !promotion.getSellerId().equals(sellerId)) {
            return new Response(false, "Khuyến mại không tồn tại hoặc bạn không có quyền thay đổi khuyến mại này");
        }
        if (promotion.getEndTime() < new Date().getTime() || !promotion.isActive()) {
            return new Response(false, "Khuyến mại này đã được dừng trước đó");
        }
        promotion.setActive(false);
        promotion.setEndTime(System.currentTimeMillis());
        promotionRepository.save(promotion);
        unpublicPromotion(promotion);
        return new Response(true, "Dừng khuyến mại thành công");
    }

    public long getCountBySeller(String sellerId, PromotionType type) {
        long promotionbyType = promotionRepository.getCountBySeller(sellerId, type);
        return promotionbyType;
    }

    public long getDeleteCount(String sellerId) {
        long promotion = promotionRepository.getCountDelBySeller(sellerId);
        return promotion;
    }

    public List<PromotionItem> getPromotionItem(String promId) {
        List<PromotionItem> pi = promotionItemRepository.get(promId);
        if (pi != null && !pi.isEmpty()) {
            for (int i = 0; i < pi.size(); i++) {
                PromotionItem promotionItem = pi.get(i);
                Item find = itemRepository.find(promotionItem.getItemId());
                if (find == null || find.getId() == null || !find.isActive() || !find.isApproved()) {
                    pi.remove(i);
                    i--;
                } else {
                    promotionItem.setCatePath(find.getCategoryPath());
                    promotionItem.setCategoryId(find.getCategoryId());
                }
            }
        }

        return pi;
    }

    /**
     * Lấy promotion đang chạy của người bán
     *
     * @param sellerId
     * @param type
     * @param published
     * @return
     */
    public List<Promotion> getPromotionBySellerIsRunning(String sellerId, PromotionType type, int published) {
        return promotionRepository.getPromotionBySellerRunning(sellerId, type, published);
    }

    public DataPage<PromotionItem> getPromotionItem(String promId, Pageable pageable) {
        List<PromotionItem> pi = promotionItemRepository.get(promId, pageable);
        if (pi != null && !pi.isEmpty()) {
            for (int i = 0; i < pi.size(); i++) {
                PromotionItem promotionItem = pi.get(i);
                Item find = itemRepository.find(promotionItem.getItemId());
                if (find == null || find.getId() == null || !find.isActive() || !find.isApproved()) {
                    pi.remove(i);
                    i--;
                } else {
                    promotionItem.setCatePath(find.getCategoryPath());
                    promotionItem.setCategoryId(find.getCategoryId());
                }
            }
        }
        DataPage<PromotionItem> dataPage = new DataPage<>();
        dataPage.setData(pi);
        dataPage.setPageIndex(pageable.getPageNumber());
        dataPage.setPageSize(pageable.getPageSize());
        dataPage.setDataCount(promotionItemRepository.count(promId));
        dataPage.setPageCount(dataPage.getDataCount() / dataPage.getPageSize());
        if (dataPage.getDataCount() % dataPage.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        return dataPage;
    }

}
