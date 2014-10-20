package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.ShopNewsCategory;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ShopNewsCategoryRepository;
import vn.chodientu.repository.UserRepository;

/**
 * @since Jun 6, 2014
 * @author Phu
 */
@Service
public class ShopNewsCategoryService {

    @Autowired
    private ShopNewsCategoryRepository shopNewsCategoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;

    @Async
    public void migrate(List<ShopNewsCategory> shopNewsCategorys) throws Exception {
        for (ShopNewsCategory newsCategory : shopNewsCategorys) {
            if (userRepository.exists(newsCategory.getUserId())) {
                ShopNewsCategory parentCategory = null;
                if (newsCategory.getParentId() != null) {
                    parentCategory = shopNewsCategoryRepository.find(newsCategory.getId());
                    if (parentCategory == null) {
                        throw new Exception("Danh mục cha không tồn tại");
                    } else {
                        newsCategory.setLevel(parentCategory.getLevel() + 1);
                        shopNewsCategoryRepository.save(parentCategory);
                        newsCategory.setPath(parentCategory.getPath());
                        newsCategory.getPath().add(newsCategory.getId());
                    }
                } else {
                    newsCategory.setLevel(1);
                    newsCategory.setPath(new ArrayList<String>());
                    newsCategory.getPath().add(newsCategory.getId());
                }
                shopNewsCategoryRepository.save(newsCategory);
            }
        }

    }

    /**
     * Lấy tất cả danh mục tin tức của shop
     *
     * @param userId
     * @return
     */
    public List<ShopNewsCategory> getByShop(String userId) {
        return shopNewsCategoryRepository.getByShop(userId);
    }

    /**
     * Lấy tất cả danh mục tin tức được active của shop
     *
     * @param userId
     * @return
     */
    public List<ShopNewsCategory> getByShopisActive(String userId) {
        return shopNewsCategoryRepository.getByShopisActive(userId);
    }

    /**
     * Lấy 1 danh mục tin tức
     *
     * @param id
     * @return
     */
    public ShopNewsCategory get(String id) {
        return shopNewsCategoryRepository.find(id);
    }

    /**
     * Thêm shop category mới
     *
     * @param category
     * @return Kết quả kèm object category mới thêm hoặc báo lỗi
     * @throws Exception
     */
    public Response addCategory(ShopNewsCategory category) throws Exception {
        category.setId(shopNewsCategoryRepository.genId());
        Map<String, String> error = validator.validate(category);
        ShopNewsCategory parentCategory = null;

        if (!userRepository.exists(category.getUserId())) {
            error.put("userId", "Người bán không tồn tại");
        }

        if (category.getParentId() != null) {
            parentCategory = shopNewsCategoryRepository.find(category.getParentId());
            if (parentCategory == null) {
                error.put("parentId", "Danh mục cha không tồn tại");
            } else {
                category.setLevel(parentCategory.getLevel() + 1);
            }
        } else {
            category.setLevel(1);
        }

        if (error.isEmpty()) {
            category.setActive(true);
            shopNewsCategoryRepository.save(category);
            if (parentCategory == null) {
                category.setPath(new ArrayList<String>());
                category.getPath().add(category.getId());
            } else {
                shopNewsCategoryRepository.save(parentCategory);
                category.setPath(parentCategory.getPath());
                category.getPath().add(category.getId());
            }
            shopNewsCategoryRepository.save(category);
            return new Response(true, "Thêm mới danh mục tin tức thành công", category);
        } else {
            return new Response(false, "Thông tin không hợp lệ", error);
        }
    }

    /*
     * Sửa shop category
     * @param category category cần sửa
     * @return Kết quả kèm object category đã sửa hoặc báo lỗi
     */
    public Response edit(ShopNewsCategory category) throws Exception {
        Map<String, String> error = validator.validate(category);

        if (!userRepository.exists(category.getUserId())) {
            error.put("userId", "Người bán không tồn tại");
        }

        ShopNewsCategory oldCategory = shopNewsCategoryRepository.find(category.getId());
        if (oldCategory == null) {
            throw new Exception("Danh mục không tồn tại");
        } else {
            if (oldCategory.getParentId() == null ? category.getParentId() != null : !oldCategory.getParentId().equals(category.getParentId())) {
                if (category.getParentId() != null) {
                    ShopNewsCategory parentCategory = shopNewsCategoryRepository.find(category.getParentId());
                    if (parentCategory == null) {
                        error.put("parentId", "Danh mục cha không tồn tại");
                    } else {
                        List<ShopNewsCategory> descendantCategory = shopNewsCategoryRepository.getDescendants(category.getId());
                        boolean err = false;
                        for (ShopNewsCategory c : descendantCategory) {
                            if (c.getId().equals(category.getParentId())) {
                                error.put("parentId", "Chuyển danh mục cha không hợp lệ");
                                err = true;
                                break;
                            }
                        }
                        if (!err) {
                            shopNewsCategoryRepository.save(parentCategory);
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

        }
        //Set đồng bộ trạng thái hoạt động dưới cấp 
        boolean active = category.isActive();
        List<ShopNewsCategory> childs = shopNewsCategoryRepository.getDescendants(category.getId());
        for (ShopNewsCategory cate : childs) {
            cate.setActive(active);
            shopNewsCategoryRepository.save(cate);
        }

        if (error.isEmpty()) {
            category.setUserId(oldCategory.getUserId());
            shopNewsCategoryRepository.save(category);
            if (oldCategory != null && oldCategory.getParentId() == null ? category.getParentId() != null : !oldCategory.getParentId().equals(category.getParentId())) {
                this.updateChilds(category);
            }
            return new Response(true, "Cập nhật danh mục thành công", category);
        } else {
            return new Response(false, "Thông tin không hợp lệ", error);
        }
    }

    private void updateChilds(ShopNewsCategory category) {
        List<ShopNewsCategory> childs = shopNewsCategoryRepository.getChilds(category.getId());
        for (ShopNewsCategory c : childs) {
            c.setLevel(category.getLevel() + 1);
            c.setPath(new ArrayList<>(category.getPath()));
            c.getPath().add(c.getId());
            updateChilds(c);
            shopNewsCategoryRepository.save(c);
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
        ShopNewsCategory cat = shopNewsCategoryRepository.find(id);
        if (cat == null) {
            throw new Exception("Danh mục không tồn tại");
        }
        if (!shopNewsCategoryRepository.getChilds(cat.getId()).isEmpty()) {
            throw new Exception("Không được xóa danh mục có chứa danh mục con");
        }
        shopNewsCategoryRepository.delete(id);
    }

    /**
     * Thay đổi trạng thái hiển thị của danh mục tin tức
     *
     * @param id
     * @throws Exception
     */
    public void changeActive(String id) throws Exception {
        ShopNewsCategory cat = shopNewsCategoryRepository.find(id);
        if (cat == null) {
            throw new Exception("Danh mục không tồn tại");
        }
        cat.setActive(!cat.isActive());
        shopNewsCategoryRepository.save(cat);
        boolean active = cat.isActive();
        List<ShopNewsCategory> childs = shopNewsCategoryRepository.getDescendants(cat.getId());
        for (ShopNewsCategory cate : childs) {
            cate.setActive(active);
            shopNewsCategoryRepository.save(cate);
        }
    }

    /**
     * Lấy theo danh sách id
     *
     * @param ids
     * @return
     */
    public List<ShopNewsCategory> getByIds(List<String> ids) {
        return shopNewsCategoryRepository.find(new Query(new Criteria("id").in(ids)));
    }
}
