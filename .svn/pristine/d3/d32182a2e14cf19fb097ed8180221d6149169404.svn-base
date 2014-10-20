package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Category;
import vn.chodientu.util.ListUtils;

@Repository
public class CategoryRepository extends BaseRepository<Category> {

    public CategoryRepository() {
        super(Category.class);
    }

    /**
     * Lấy danh sách category theo mảng id truyền vào
     *
     * @param ids
     * @return
     */
    public List<Category> get(List<String> ids) {
        Query query = new Query(new Criteria("id").in(ids));
        query.with(new Sort(new Sort.Order("level")));
        return ListUtils.orderByArray(getMongo().find(query, getEntityClass()), ids);
    }

    /**
     *
     * @param id
     * @return Danh sách các danh mục con
     */
    public List<Category> getChilds(String id) {
        Query query = new Query(new Criteria("parentId").is(id));
        query.with(new Sort(new Sort.Order("position")));
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param id
     * @return Danh sách các danh mục con
     */
    public List<Category> getActiveChilds(String id) {
        Query query = new Query(new Criteria("parentId").is(id).and("active").is(true));
        query.with(new Sort(new Sort.Order("position")));
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param ids
     * @return Danh sách các danh mục con
     */
    public List<Category> getChilds(List<String> ids) {
        Query query = new Query(new Criteria("parentId").in(ids));
        query.with(new Sort(new Sort.Order("position")));
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param id
     * @return Danh sách tất cả các danh mục con cháu
     */
    public List<Category> getDescendants(String id) {
        if (id.equals("")) {
            id = null;
        }
        Query query = new Query(new Criteria("path").is(id));
        query.with(new Sort(new Sort.Order("level")));
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param page
     * @return Danh sách danh mục theo phân trang
     */
    public List<Category> list(Pageable page) {
        return getMongo().find(new Query().with(page), getEntityClass());
    }

    /**
     *
     * @param level
     * @return
     */
    public List<Category> getByLevelDisplay(int level) {
        Query query = new Query(new Criteria("level").is(level).and("active").is(true));
        query.with(new Sort(new Sort.Order("position")));
        return getMongo().find(query, getEntityClass());
    }

    public List<Category> getAncestorsByLevelDisplay(int level) {
        Query query = new Query(new Criteria("level").lte(level).and("active").is(true));
        query.with(new Sort(new Sort.Order("position")));
        return getMongo().find(query, getEntityClass());
    }

    public List<Category> getByLeafDisplay() {
        Query query = new Query(new Criteria("leaf").is(true).and("active").is(true));
        query.with(new Sort(new Sort.Order("position")));
        return getMongo().find(query, getEntityClass());
    }

}
