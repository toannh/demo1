package vn.chodientu.repository;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.CategoryProperty;

@Repository
public class CategoryPropertyRepository extends BaseRepository<CategoryProperty> {

    public CategoryPropertyRepository() {
        super(CategoryProperty.class);
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
     * Kiểm tra tên thuộc tính đã tồn tại chưa
     *
     * @param name
     * @param categoryId
     * @return
     */
    public boolean exists(String name, String categoryId) {
        return count(new Query(new Criteria("categoryId").is(categoryId).and("name").is(name))) > 0;
    }

    /**
     * Lấy list property theo category id
     *
     * @param categoryId
     * @return
     */
    public List<CategoryProperty> getByCategory(String categoryId) { 
        return getMongo().find(new Query(new Criteria("categoryId").is(categoryId)).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }

    /**
     * Lấy list property theo danh sách category
     *
     * @param categoryIds
     * @return
     */
    public List<CategoryProperty> getByCategories(List<String> categoryIds) {
        return getMongo().find(new Query(new Criteria("categoryId").in(categoryIds)).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }

    /**
     * Lấy list property filter theo category id
     *
     * @param categoryId
     * @return
     */
    public List<CategoryProperty> getFilter(String categoryId) {
        return getMongo().find(new Query(new Criteria("categoryId").is(categoryId).and("filter").is(true)).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }

    /**
     * Tìm thuộc tính theo category và tên
     *
     * @param categoryId
     * @param name
     * @return
     */
    public CategoryProperty find(String categoryId, String name) {
        return getMongo().findOne(new Query(new Criteria("categoryId").is(categoryId).and("name").is(name)), getEntityClass());
    }

    /**
     * Lấy Thuộc tính của danh mục theo ids
     *
     * @param ids
     * @return
     */
    public List<CategoryProperty> get(String[] ids) {
        Query query = new Query(new Criteria("id").in((Object[]) ids));
        return getMongo().find(query, getEntityClass());
    }

}
