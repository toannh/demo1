package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ShopCategory;
import vn.chodientu.entity.input.ItemSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.ShopCategoryRepository;
import vn.chodientu.repository.UserRepository;

/**
 * @since Jun 6, 2014
 * @author Phu
 */
@Service
public class ShopCategoryService {

    @Autowired
    private ShopCategoryRepository shopCategoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SearchIndexService searchIndexService;

    public void migrate(List<ShopCategory> shopCategorys) throws Exception {
        ShopCategory parentCategory = null;
        for (ShopCategory category : shopCategorys) {
            try {
                if (category.getParentId() != null) {
                    parentCategory = shopCategoryRepository.find(category.getParentId());
                    if (parentCategory == null) {
                        throw new Exception("Danh mục cha không tồn tại");
                    } else {
                        parentCategory.setLeaf(false);
                        shopCategoryRepository.save(parentCategory);
                        category.setLevel(parentCategory.getLevel() + 1);
                        category.setPath(parentCategory.getPath());
                        category.getPath().add(category.getId());
                    }
                } else {
                    category.setPath(new ArrayList<String>());
                    category.getPath().add(category.getId());
                    category.setLevel(1);
                }

                category.setLeaf(true);
                shopCategoryRepository.save(category);

            } catch (Exception ex) {
                Logger.getLogger(ShopCategoryService.class).error(ex);
            }

        }
    }

    /**
     * Lấy toàn bộ danh mục của một shop
     *
     * @param userId
     * @return
     */
    public List<ShopCategory> getByShop(String userId) {
        return shopCategoryRepository.getByShop(userId);
    }

    /**
     * Lấy toàn bộ danh mục được active của một shop
     *
     * @param userId
     * @return
     */
    public List<ShopCategory> getByShopIsActive(String userId) {
        return shopCategoryRepository.getByShopIsActive(userId);
    }

    /**
     * Thêm shop category mới
     *
     * @param category
     * @return Kết quả kèm object category mới thêm hoặc báo lỗi
     * @throws Exception
     */
    public Response addCategory(ShopCategory category) throws Exception {
        category.setId(shopCategoryRepository.genId());
        Map<String, String> error = validator.validate(category);
        ShopCategory parentCategory = null;

        if (!userRepository.exists(category.getUserId())) {
            error.put("userId", "Người bán không tồn tại");
        }

        if (category.getParentId() != null) {
            parentCategory = shopCategoryRepository.find(category.getParentId());
            if (parentCategory == null) {
                error.put("parentId", "Danh mục cha không tồn tại");
            } else {
                category.setLevel(parentCategory.getLevel() + 1);
            }
        } else {
            category.setLevel(1);
        }

        if (error.isEmpty()) {
            category.setLeaf(true);
            category.setActive(true);
            shopCategoryRepository.save(category);
            if (parentCategory == null) {
                category.setPath(new ArrayList<String>());
                category.getPath().add(category.getId());
            } else {
                parentCategory.setLeaf(false);
                shopCategoryRepository.save(parentCategory);
                category.setPath(parentCategory.getPath());
                category.getPath().add(category.getId());
            }
            shopCategoryRepository.save(category);
            return new Response(true, "Thêm mới danh mục shop thành công", category);
        } else {
            return new Response(false, "Thông tin không hợp lệ", error);
        }
    }

    /*
     * Sửa shop category
     * @param category category cần sửa
     * @return Kết quả kèm object category đã sửa hoặc báo lỗi
     */
    public Response edit(ShopCategory category) throws Exception {
        Map<String, String> error = validator.validate(category);

        if (!userRepository.exists(category.getUserId())) {
            error.put("userId", "Người bán không tồn tại");
        }

        ShopCategory oldCategory = shopCategoryRepository.find(category.getId());
        if (oldCategory == null) {
            throw new Exception("Danh mục không tồn tại");
        } else {
            if (oldCategory.getParentId() == null ? category.getParentId() != null : !oldCategory.getParentId().equals(category.getParentId())) {
                if (category.getParentId() != null) {
                    ShopCategory parentCategory = shopCategoryRepository.find(category.getParentId());
                    if (parentCategory == null) {
                        error.put("parentId", "Danh mục cha không tồn tại");
                    } else {
                        List<ShopCategory> descendantCategory = shopCategoryRepository.getDescendants(category.getId());
                        boolean err = false;
                        for (ShopCategory c : descendantCategory) {
                            if (c.getId().equals(category.getParentId())) {
                                error.put("parentId", "Chuyển danh mục cha không hợp lệ");
                                err = true;
                                break;
                            }
                        }
                        if (!err) {
                            parentCategory.setLeaf(false);
                            shopCategoryRepository.save(parentCategory);
                        }
                        category.setLevel(parentCategory.getLevel() + 1);
                        category.setPath(parentCategory.getPath());
                        category.getPath().add(category.getId());
                    }
                } else {
                    category.setLevel(1);
                    category.setPath(new ArrayList<String>());
                    category.getPath().add(category.getId());
                }
            } else {
                category.setLevel(oldCategory.getLevel());
                category.setPath(oldCategory.getPath());
            }

            category.setLeaf(oldCategory.isLeaf());
        }
        //Set đồng bộ trạng thái hoạt động dưới cấp 
        boolean active = category.isActive();
        int weight = category.getWeight();
        List<ShopCategory> childs = shopCategoryRepository.getDescendants(category.getId());
        for (ShopCategory cate : childs) {
            cate.setActive(active);
            cate.setWeight(weight);
            shopCategoryRepository.save(cate);
        }

        if (error.isEmpty()) {
            category.setUserId(oldCategory.getUserId());
            shopCategoryRepository.save(category);
            if (oldCategory != null && oldCategory.getParentId() == null ? category.getParentId() != null : !oldCategory.getParentId().equals(category.getParentId())) {
                this.updateChilds(category);
            }
            return new Response(true, "Cập nhật danh mục thành công", category);
        } else {
            return new Response(false, "Thông tin không hợp lệ", error);
        }
    }

    private void updateChilds(ShopCategory category) {
        List<ShopCategory> childs = shopCategoryRepository.getChilds(category.getId(), null);
        for (ShopCategory c : childs) {
            c.setLevel(category.getLevel() + 1);
            c.setPath(new ArrayList<>(category.getPath()));
            c.getPath().add(c.getId());
            updateChilds(c);
            shopCategoryRepository.save(c);
        }
    }

    /**
     * Xóa danh mục, không xóa được khi dm có dm con hoặc model, item, khi xóa
     * sẽ xóa cả property
     *
     * @param id
     * @throws Exception
     */
    public void remove(String id) throws Exception {
        ShopCategory cat = shopCategoryRepository.find(id);
        if (cat == null) {
            throw new Exception("Danh mục không tồn tại");
        }
        if (!shopCategoryRepository.getChilds(cat.getId(), null).isEmpty()) {
            throw new Exception("Không được xóa danh mục có chứa danh mục con");
        }
        ItemSearch itemSearch = new ItemSearch();
        itemSearch.setShopCategoryId(cat.getId());
        itemSearch.setPageSize(10000);
        DataPage<Item> search = itemService.search(itemSearch);
        if (search != null && search.getData().size() > 0) {
            for (Item item : search.getData()) {
                item.setShopCategoryId(null);
                itemRepository.save(item);
            }
            searchIndexService.processIndexPageItem(search.getData());
        }
        shopCategoryRepository.delete(id);
        if (shopCategoryRepository.getChilds(cat.getParentId(), null).size() <= 0) {
            ShopCategory parent = shopCategoryRepository.find(cat.getParentId());
            parent.setLeaf(true);
            shopCategoryRepository.save(parent);
        }
    }

    /**
     * Thay đổi trạng thái hiển thị của danh mục shop
     *
     * @param id
     * @throws Exception
     */
    public void changeActive(String id) throws Exception {
        ShopCategory cat = shopCategoryRepository.find(id);
        if (cat == null) {
            throw new Exception("Danh mục không tồn tại");
        }
        cat.setActive(!cat.isActive());
        shopCategoryRepository.save(cat);
        boolean active = cat.isActive();
        List<ShopCategory> childs = shopCategoryRepository.getDescendants(cat.getId());
        for (ShopCategory cate : childs) {
            cate.setActive(active);
            shopCategoryRepository.save(cate);
        }
    }

    /**
     * Thay đổi trạng thái hiển thị trang chủ cho danh mục shop
     *
     * @param id
     * @throws Exception
     */
    public void changeHome(String id) throws Exception {
        ShopCategory cat = shopCategoryRepository.find(id);
        if (cat == null) {
            throw new Exception("Danh mục không tồn tại");
        }
        cat.setHome(!cat.isHome());
        shopCategoryRepository.save(cat);
        boolean home = cat.isHome();
        List<ShopCategory> childs = shopCategoryRepository.getDescendants(cat.getId());
        for (ShopCategory cate : childs) {
            cate.setHome(home);
            shopCategoryRepository.save(cate);
        }
    }

    /**
     * Lấy 1 danh mục của một shop
     *
     * @param id
     * @return
     */
    public ShopCategory get(String id) {
        return shopCategoryRepository.find(id);
    }

    public List<ShopCategory> getChilds(String parentId, String userId) {
        return shopCategoryRepository.getChilds(parentId, userId);
    }
    
    
}
