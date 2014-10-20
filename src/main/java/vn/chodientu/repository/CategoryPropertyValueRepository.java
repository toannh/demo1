package vn.chodientu.repository;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.CategoryPropertyValue;
import vn.chodientu.entity.db.NewsCategory;

@Repository
public class CategoryPropertyValueRepository extends BaseRepository<CategoryPropertyValue> {

    public CategoryPropertyValueRepository() {
        super(CategoryPropertyValue.class);
    }

    public boolean exists(String name, String categoryPropertyId) {
        return count(new Query(new Criteria("categoryPropertyId").is(categoryPropertyId).and("name").is(name))) > 0;
    }

    /**
     * Lấy tất cả CategoryPropertyValue theo ids
     *
     * @param ids
     * @return
     */
    public List<CategoryPropertyValue> get(String[] ids) {
        Query query = new Query(new Criteria("id").in((Object[]) ids));
        return getMongo().find(query, getEntityClass());
    }

    /**
     * Xóa tất cả thuộc tính của danh mục
     *
     * @param categoryId
     */
    public void deleteByCategory(String categoryId) {
        delete(new Query(new Criteria("categoryId").is(categoryId)));
    }

    /**
     * Xóa tất cả giá trị thuộc tính khi xóa thuộc tính danh mục
     *
     * @param categoryPropertyId
     */
    public void deleteByProperty(String categoryPropertyId) {
        delete(new Query(new Criteria("categoryPropertyId").is(categoryPropertyId)));
    }

    /**
     * Lấy list value theo property id
     *
     * @param categoryPropertyId
     * @return
     */
    public List<CategoryPropertyValue> getByProperty(String categoryPropertyId) {
        return getMongo().find(new Query(new Criteria("categoryPropertyId").is(categoryPropertyId)).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }

    /**
     * Lấy list value theo categoryId
     *
     * @param categoryId
     * @return
     */
    public List<CategoryPropertyValue> getByCategory(String categoryId) {
        return getMongo().find(new Query(new Criteria("categoryId").is(categoryId)).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }

    /**
     * Lấy list value theo danh sách categoryId
     *
     * @param categoryIds
     * @return
     */
    public List<CategoryPropertyValue> getByCategories(List<String> categoryIds) {
        return getMongo().find(new Query(new Criteria("categoryId").in(categoryIds)).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }

    /**
     * Lấy list value filter theo categoryId
     *
     * @param categoryId
     * @return
     */
    public List<CategoryPropertyValue> getFilter(String categoryId) {
        return getMongo().find(new Query(new Criteria("categoryId").is(categoryId).and("filter").is(true)).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }

    /**
     * Tìm giá trị thuộc tính theo category, property và tên
     *
     * @param categoryId
     * @param categoryPropertyId
     * @param name
     * @return
     */
    public CategoryPropertyValue find(String categoryId, String categoryPropertyId, String name) {
        return getMongo().findOne(new Query(new Criteria("categoryId").is(categoryId).and("categoryPropertyId").is(categoryPropertyId).and("name").is(name)), getEntityClass());
    }

}
