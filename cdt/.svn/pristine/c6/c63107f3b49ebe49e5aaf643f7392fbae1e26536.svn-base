package vn.chodientu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.elasticsearch.index.query.AndFilterBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilteredQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermFilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.CategoryPropertyRepository;
import vn.chodientu.repository.CategoryPropertyValueRepository;
import vn.chodientu.repository.CategoryRepository;
import vn.chodientu.repository.ItemPropertyRepository;
import vn.chodientu.repository.ItemRepository;
import vn.chodientu.repository.ModelPropertyRepository;
import vn.chodientu.repository.ModelRepository;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.CategoryProperty;
import vn.chodientu.entity.db.CategoryPropertyValue;
import vn.chodientu.entity.input.CategorySearch;
import vn.chodientu.repository.CategorySearchRepository;
import vn.chodientu.util.TextUtils;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryPropertyRepository categoryPropertyRepository;
    @Autowired
    private CategoryPropertyValueRepository categoryPropertyValueRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private ModelPropertyRepository modelPropertyRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemPropertyRepository itemPropertyRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private CategorySearchRepository categorySearchRepository;

    @Async
    public Response index() {
        long total = categoryRepository.count();
        int totalPage = (int) total / 100;
        if (total % 100 != 0) {
            totalPage++;
        }
        for (int i = 0; i < totalPage; i++) {
            processIndex(i);
        }
        return new Response(true);
    }

    @Async
    private void processIndex(int i) {
        List<Category> cates = categoryRepository.list(new PageRequest(i, 100));
        categorySearchRepository.index(cates);
    }

    /**
     * Xóa toàn bộ index
     *
     */
    public void unindex() {
        categorySearchRepository.preIndex();
    }

    /**
     * Lấy danh sách category Property theo của id
     *
     * @param ids
     * @return
     */
    public List<CategoryProperty> getPropertyById(String[] ids) {
        return categoryPropertyRepository.get(ids);
    }

    /**
     * Lấy danh sách category Property theo của id
     *
     * @param ids
     * @return
     */
    public List<CategoryPropertyValue> getPropertyValue(String[] ids) {
        return categoryPropertyValueRepository.get(ids);
    }

    /**
     * Lấy category theo id
     *
     * @param categoryId
     * @return
     * @throws Exception
     */
    public Category get(String categoryId) throws Exception {
        Category category = categoryRepository.find(categoryId);
        if (category == null) {
            throw new Exception("Danh mục không tồn tại");
        }
        return category;
    }

    /**
     * Lấy danh sách category theo mảng id truyền vào
     *
     * @param ids
     * @return
     * @throws Exception
     */
    public List<Category> get(List<String> ids) throws Exception {
        List<Category> category = categoryRepository.get(ids);
        if (category == null) {
            throw new Exception("Danh mục không tồn tại");
        }
        return category;
    }

    /**
     * Lấy path danh mục từ cấp gốc đến danh mục có id truyền vào
     *
     * @param categoryId
     * @return
     * @throws Exception
     */
    public List<Category> getAncestors(String categoryId) throws Exception {
        Category category = categoryRepository.find(categoryId);
        if (category == null) {
            throw new Exception("Danh mục không tồn tại");
        }
        return categoryRepository.get(category.getPath());
    }

    /**
     * Lấy các danh mục con trực tiếp
     *
     * @param categoryId
     * @return
     */
    public List<Category> getChilds(String categoryId) {
        return categoryRepository.getChilds(categoryId);
    }

    /**
     * Lấy các danh mục con trực tiếp
     *
     * @param categoryIds
     * @return
     */
    public List<Category> getChildsByIds(List<String> categoryIds) {
        return categoryRepository.getChilds(categoryIds);
    }

    /**
     * Lấy danh sách property của category
     *
     * @param categoryId
     * @return
     */
    public List<CategoryProperty> getProperties(String categoryId) {
        return categoryPropertyRepository.getByCategory(categoryId);
    }

    /**
     * Lấy danh sách value theo của property
     *
     * @param categoryPropertyId
     * @return
     */
    public List<CategoryPropertyValue> getPropertyValues(String categoryPropertyId) {
        return categoryPropertyValueRepository.getByProperty(categoryPropertyId);
    }

    /**
     * Lấy danh sách value của property categoryId
     *
     * @param categoryId
     * @return
     */
    public List<CategoryPropertyValue> getPropertyValuesWithCategoryId(String categoryId) {
        return categoryPropertyValueRepository.getByCategory(categoryId);
    }

    /**
     * Thêm category mới
     *
     * @param category
     * @return Kết quả kèm object category mới thêm hoặc báo lỗi
     * @throws Exception
     */
    public Response addCategory(Category category) throws Exception {
        category.setId(categoryRepository.genId());
        Map<String, String> error = validator.validate(category);
        Category parentCategory = null;

        if (category.getParentId() != null) {
            parentCategory = categoryRepository.find(category.getParentId());
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
            categoryRepository.save(category);
            if (parentCategory == null) {
                category.setPath(new ArrayList<String>());
                category.getPath().add(category.getId());
            } else {
                parentCategory.setLeaf(false);
                categoryRepository.save(parentCategory);
                category.setPath(parentCategory.getPath());
                category.getPath().add(category.getId());
            }
            categoryRepository.save(category);
            categorySearchRepository.index(category);
            return new Response(true, "Thêm mới danh mục thành công", category);
        } else {
            return new Response(false, "Thông tin không hợp lệ", error);
        }
    }

    /*
     * Sửa category
     * @param category category cần sửa
     * @return Kết quả kèm object category đã sửa hoặc báo lỗi
     */
    public Response edit(Category category) throws Exception {
        Map<String, String> error = validator.validate(category);

        Category oldCategory = categoryRepository.find(category.getId());
        if (oldCategory == null) {
            throw new Exception("Danh mục không tồn tại");
        } else {
            if (oldCategory.getParentId() == null ? category.getParentId() != null : !oldCategory.getParentId().equals(category.getParentId())) {
                if (category.getParentId() != null) {
                    Category parentCategory = categoryRepository.find(category.getParentId());
                    if (parentCategory == null) {
                        error.put("parentId", "Danh mục cha không tồn tại");
                    } else {
                        List<Category> descendantCategory = categoryRepository.getDescendants(category.getId());
                        boolean err = false;
                        for (Category c : descendantCategory) {
                            if (c.getId().equals(category.getParentId())) {
                                error.put("parentId", "Chuyển danh mục cha không hợp lệ");
                                err = true;
                                break;
                            }
                        }
                        if (!err) {
                            parentCategory.setLeaf(false);
                            categoryRepository.save(parentCategory);
                            categorySearchRepository.index(parentCategory);
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
        List<Category> childs = categoryRepository.getDescendants(category.getId());
        for (Category cate : childs) {
            cate.setActive(active);
            categoryRepository.save(cate);
            categorySearchRepository.index(cate);
        }

        if (error.isEmpty()) {
            categoryRepository.save(category);
            categorySearchRepository.index(category);
            if (oldCategory != null && oldCategory.getParentId() == null ? category.getParentId() != null : !oldCategory.getParentId().equals(category.getParentId())) {
                this.updateChilds(category);
            }
            return new Response(true, "Cập nhật danh mục thành công", category);
        } else {
            return new Response(false, "Thông tin không hợp lệ", error);
        }
    }

    private void updateChilds(Category category) {
        List<Category> childs = categoryRepository.getChilds(category.getId());
        for (Category c : childs) {
            c.setLevel(category.getLevel() + 1);
            c.setPath(new ArrayList<>(category.getPath()));
            c.getPath().add(c.getId());
            updateChilds(c);
            categoryRepository.save(c);
            categorySearchRepository.index(c);
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
        Category cat = categoryRepository.find(id);
        if (cat == null) {
            throw new Exception("Danh mục không tồn tại");
        }
        if (!categoryRepository.getChilds(id).isEmpty()) {
            throw new Exception("Không được xóa danh mục có chứa danh mục con");
        }
        if (modelRepository.countByCategory(id) > 0) {
            throw new Exception("Danh mục còn chứa model không được xóa");
        }
        if (itemRepository.countByCategory(id) > 0) {
            throw new Exception("Danh mục còn chứa sản phẩm không được xóa");
        }
        categoryPropertyRepository.deleteByCategory(id);
        categoryPropertyValueRepository.deleteByCategory(id);
        categoryRepository.delete(id);
        categorySearchRepository.delete(id);
        if (categoryRepository.getChilds(cat.getParentId()).size() <= 0) {
            Category parent = categoryRepository.find(cat.getParentId());
            parent.setLeaf(true);
            categoryRepository.save(parent);
            categorySearchRepository.index(parent);
        }
    }

    /**
     * Thêm thuộc tính cho danh mục
     *
     * @param property
     * @return
     * @throws Exception
     */
    public Response addProperty(CategoryProperty property) throws Exception {
        Map<String, String> errors = validator.validate(property);

        if (!categoryRepository.exists(property.getCategoryId())) {
            throw new Exception("Danh mục không tồn tại");
        }

        if (categoryPropertyRepository.exists(property.getName(), property.getCategoryId())) {
            errors.put("name", "Tên thuộc tính phải là duy nhất trong danh mục này");
        }

        if (errors.isEmpty()) {
            categoryPropertyRepository.save(property);
            return new Response(true, "Thêm thuộc tính thành công", property);
        } else {
            return new Response(false, "Thêm thuộc tính thất bại", errors);
        }
    }

    /**
     * Sửa thuộc tính danh mục
     *
     * @param property
     * @return
     * @throws Exception
     */
    public Response editProperty(CategoryProperty property) throws Exception {
        CategoryProperty oldProperty = categoryPropertyRepository.find(property.getId());
        if (oldProperty == null) {
            throw new Exception("Thuộc tính không tồn tại");
        }

        property.setCategoryId(oldProperty.getCategoryId());

        Map<String, String> errors = validator.validate(property);

        if (!property.getName().equals(oldProperty.getName()) && categoryPropertyRepository.exists(property.getName(), property.getCategoryId())) {
            errors.put("name", "Tên thuộc tính phải là duy nhất trong danh mục này");
        }
        if (errors.isEmpty()) {
            categoryPropertyRepository.save(property);
            return new Response(true, "Sửa thuộc tính thành công", property);
        } else {
            return new Response(false, "Sửa thuộc tính thất bại", errors);
        }
    }

    /**
     * Xóa thuộc tính, sẽ xóa toàn bộ giá trị của thuộc tính đó và xóa toàn bộ
     * thuộc tính tương ứng ở model, item
     *
     * @param propertyId
     * @throws Exception
     */
    public void removeProperty(String propertyId) throws Exception {
        if (!categoryPropertyRepository.exists(propertyId)) {
            throw new Exception("Thuộc tính không tồn tại");
        }
        categoryPropertyRepository.delete(propertyId);
        categoryPropertyValueRepository.deleteByProperty(propertyId);
        itemPropertyRepository.deleteWithCategoryProperty(propertyId);
        modelPropertyRepository.deleteWithCategoryProperty(propertyId);
    }

    /**
     * Thêm giá trị cho thuộc tính
     *
     * @param value
     * @return
     * @throws Exception
     */
    public Response addPropertyValue(CategoryPropertyValue value) throws Exception {
        Map<String, String> errors = validator.validate(value);
        if (!categoryRepository.exists(value.getCategoryId())) {
            throw new Exception("Danh mục không tồn tại");
        }
        if (!categoryPropertyRepository.exists(value.getCategoryPropertyId())) {
            throw new Exception("Thuộc tính không tồn tại");
        }
        if (categoryPropertyValueRepository.exists(value.getName(), value.getCategoryPropertyId())) {
            errors.put("name", "Giá trị phải là duy nhất trong thuộc tính này");
        }
        if (errors.isEmpty()) {
            categoryPropertyValueRepository.save(value);
            return new Response(true, "Thêm giá trị thuộc tính thành công", value);
        } else {
            return new Response(false, "Thêm giá trị thuộc tính thất bại", errors);
        }
    }

    /**
     * Sửa giá trị thuộc tính
     *
     * @param value
     * @return
     * @throws Exception
     */
    public Response editPropertyValue(CategoryPropertyValue value) throws Exception {
        CategoryPropertyValue oldValue = categoryPropertyValueRepository.find(value.getId());
        if (oldValue == null) {
            throw new Exception("Giá trị thuộc tính không tồn tại");
        }
        value.setCategoryId(oldValue.getCategoryId());
        value.setCategoryPropertyId(oldValue.getCategoryPropertyId());
        Map<String, String> errors = validator.validate(value);

        if (!value.getName().equals(value.getName()) && categoryPropertyValueRepository.exists(value.getName(), value.getCategoryPropertyId())) {
            errors.put("name", "Tên thuộc tính phải là duy nhất trong danh mục này");
        }

        if (errors.isEmpty()) {
            categoryPropertyValueRepository.save(value);
            return new Response(true, "Sửa giá trị thuộc tính thành công", value);
        } else {
            return new Response(false, "Sửa giá trị thuộc tính thất bại", errors);
        }
    }

    /**
     * Xóa giá trị thuộc tính
     *
     * @param valueId
     * @throws Exception
     */
    public void removePropertyValue(String valueId) throws Exception {
        CategoryPropertyValue value = categoryPropertyValueRepository.find(valueId);
        if (value == null) {
            throw new Exception("Giá trị thuộc tính không tồn tại");
        }
        categoryPropertyValueRepository.delete(valueId);
        itemPropertyRepository.deleteWithCategoryPropertyValue(value.getCategoryPropertyId(), valueId);
        modelPropertyRepository.deleteWithCategoryPropertyValue(value.getCategoryPropertyId(), valueId);
    }

    /**
     * Lấy danh sách danh mục theo list ids
     *
     * @param ids
     * @return
     */
    public List<Category> getCategories(List<String> ids) {
        return categoryRepository.get(ids);
    }

    /**
     * tìm kiếm fuzzyQuery cho trang đăng bán
     *
     * @param categorySearch
     * @return
     */
    public List<Category> seach(CategorySearch categorySearch) {
        QueryBuilder queryBuilder;

        List<FilterBuilder> filters = new ArrayList<>();
        if (categorySearch.getActive() > 0) {
            filters.add(new TermFilterBuilder("active", (categorySearch.getActive() == 1)));
        }
        if (categorySearch.getLeaf() > 0) {
            filters.add(new TermFilterBuilder("leaf", (categorySearch.getLeaf() == 1)));
        }

        if (categorySearch.getKeyword() != null && categorySearch.getKeyword().trim().length() > 1) {
            queryBuilder = new FilteredQueryBuilder(QueryBuilders.fuzzyLikeThisFieldQuery("keyword").likeText(TextUtils.removeDiacritical(categorySearch.getKeyword())), new AndFilterBuilder(filters.toArray(new FilterBuilder[0])));
        } else {
            if (filters.isEmpty()) {
                queryBuilder = new MatchAllQueryBuilder();
            } else {
                queryBuilder = new FilteredQueryBuilder(new MatchAllQueryBuilder(), new AndFilterBuilder(filters.toArray(new FilterBuilder[0])));
            }
        }

        List<String> ids = categorySearchRepository.search(queryBuilder);
        return categoryRepository.get(ids);
    }

    /**
     *
     * @param level
     * @return
     */
    public List<Category> getByLevelDisplay(int level) {
        return categoryRepository.getByLevelDisplay(level);
    }

    /**
     *
     * @param level
     * @return
     */
    public List<Category> getAncestorsByLevelDisplay(int level) {
        return categoryRepository.getAncestorsByLevelDisplay(level);
    }

    /**
     *
     * Lấy số lượng danh mục đã được index trong elasticsearch
     *
     * @return
     */
    public long countElastic() {
        return categorySearchRepository.count();
    }

    /**
     *
     * Lấy tổng số lượng danh mục
     *
     * @return
     */
    public long count() {
        return categoryRepository.count();
    }

    /**
     * Lấy danh sách property của list category
     *
     * @param categoryIds
     * @return
     */
    public List<CategoryProperty> getProperties(List<String> categoryIds) {
        return categoryPropertyRepository.getByCategories(categoryIds);
    }

    /**
     * Lấy danh sách value của list category
     *
     * @param categoryIds
     * @return
     */
    public List<CategoryPropertyValue> getPropertyValues(List<String> categoryIds) {
        return categoryPropertyValueRepository.getByCategories(categoryIds);
    }

    /**
     * Lấy tất cả danh mục không phân trang
     *
     * @return
     */
    public List<Category> listAll() {
        return categoryRepository.list(null);
    }

    /**
     * Lấy tất cả danh mục cấp lá của chợ
     *
     * @return
     */
    public List<Category> listByLeafDisplay() {
        return categoryRepository.getByLeafDisplay();
    }
}
