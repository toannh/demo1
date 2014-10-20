package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.NewsCategory;

/**
 * @since May 15, 2014
 * @author Phuongdt
 */
@Repository
public class NewsCategoryRepository extends BaseRepository<NewsCategory> {

    public NewsCategoryRepository() {
        super(NewsCategory.class);
    }

    public List<NewsCategory> list(Criteria cri, int pageSize, int offset) {
        Query query = new Query(cri);
        return getMongo().find(query.limit(pageSize).skip(offset).with(new Sort(Sort.Direction.DESC, "createTime")), getEntityClass());
    }

    /**
     *
     * @param id
     * @return Danh sách các danh mục con
     */
    public List<NewsCategory> getChilds(String id) {
        Query query = new Query(new Criteria("parentId").is(id));
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC,"position")));
        return getMongo().find(query, getEntityClass());
    }

    public List<NewsCategory> getAll() {
        return getMongo().find(new Query(new Criteria()).with(new Sort(new Sort.Order(Sort.Direction.ASC, "order"))), getEntityClass());
    }
    /**
     * Get all with position sort
     */
    public List<NewsCategory> getAllWithPositionSort() {
        return getMongo().find(new Query(new Criteria()).with(new Sort(new Sort.Order(Sort.Direction.ASC, "position"))), getEntityClass());
    }
    /**
     *
     * @param id
     * @return
     */
    public NewsCategory get(String id) {
        Query query = new Query(new Criteria("id").is(id));
        return getMongo().findOne(query, getEntityClass());
    }

    /**
     *
     * @param ids
     * @return
     */
    public List<NewsCategory> get(String[] ids) {
        Query query = new Query(new Criteria("id").in((Object[]) ids));
        query.fields().exclude("properties");
        query.with(new Sort(new Sort.Order("level")));
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param id
     * @return Danh sách tất cả các danh mục con cháu
     */
    public List<NewsCategory> getDescendants(String id) {
        if (id.equals("")) {
            id = null;
        }
        Query query = new Query(new Criteria("path").is(id));
        query.with(new Sort(new Sort.Order("level")));
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param id
     * @return Danh sách các danh mục con
     */
    public List<NewsCategory> getFullChilds(String id) {
        Query query = new Query(new Criteria("parentId").is(id));
        return getMongo().find(query, getEntityClass());
    }
}
