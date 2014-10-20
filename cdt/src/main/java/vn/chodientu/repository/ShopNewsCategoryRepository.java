package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ShopNewsCategory;

/**
 * @since Jun 2, 2014
 * @author Phu
 */
@Repository
public class ShopNewsCategoryRepository extends BaseRepository<ShopNewsCategory> {

    public ShopNewsCategoryRepository() {
        super(ShopNewsCategory.class);
    }

    /**
     *
     * @param userId
     * @return Danh sách các danh mục tin tức của shop
     */
    public List<ShopNewsCategory> getByShop(String userId) {
        Query query = new Query(new Criteria("userId").is(userId));
        query.with(new Sort(new Sort.Order("position")));
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param userId
     * @return Danh sách các danh mục tin tức được active của shop
     */
    public List<ShopNewsCategory> getByShopisActive(String userId) {
        Query query = new Query(new Criteria("userId").is(userId).and("active").is(true));
        query.with(new Sort(new Sort.Order("position")));
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param id
     * @return Danh sách các danh mục con
     */
    public List<ShopNewsCategory> getChilds(String id) {
        Query query = new Query(new Criteria("parentId").is(id));
        query.with(new Sort(new Sort.Order("position")));
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param id
     * @return Danh sách tất cả các danh mục con cháu
     */
    public List<ShopNewsCategory> getDescendants(String id) {
        if (id.equals("")) {
            id = null;
        }
        Query query = new Query(new Criteria("path").is(id));
        query.with(new Sort(new Sort.Order("level")));
        return getMongo().find(query, getEntityClass());
    }
}
