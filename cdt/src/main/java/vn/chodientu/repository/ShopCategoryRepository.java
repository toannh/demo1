package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ShopCategory;

/**
 * @since Jun 2, 2014
 * @author Phu
 */
@Repository
public class ShopCategoryRepository extends BaseRepository<ShopCategory> {
    
    public ShopCategoryRepository() {
        super(ShopCategory.class);
    }

    /**
     *
     * @param userId
     * @return Danh sách các danh mục của một shop
     */
    public List<ShopCategory> getByShop(String userId) {
        Query query = new Query(new Criteria("userId").is(userId));
        query.with(new Sort(new Sort.Order("position")));
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param userId
     * @return Danh sách các danh mục được active của một shop
     */
    public List<ShopCategory> getByShopIsActive(String userId) {
        Query query = new Query(new Criteria("userId").is(userId).and("active").is(true));
        query.with(new Sort(new Sort.Order("position")));
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param id
     * @param user
     * @return Danh sách các danh mục con
     */
    public List<ShopCategory> getChilds(String id, String user) {
        Criteria cri = new Criteria("parentId").is(id);
        if(user!= null && !user.equals("")){
            cri.and("userId").is(user);
        }
        return getMongo().find(new Query(cri).with(new Sort(new Sort.Order("position"))), getEntityClass());
    }

    /**
     *
     * @param id
     * @return Danh sách tất cả các danh mục con cháu
     */
    public List<ShopCategory> getDescendants(String id) {
        if (id.equals("")) {
            id = null;
        }
        Query query = new Query(new Criteria("path").is(id));
        query.with(new Sort(new Sort.Order("level")));
        return getMongo().find(query, getEntityClass());
    }

    /**
     *
     * @param shopId
     * @return Đếm danh mục active của shop
     */
    public long count(String shopId) {
        return getMongo().count(new Query(new Criteria("userId").is(shopId).and("active").is(true)), getEntityClass());
    }

    public List<ShopCategory> get(List<String> cateIds) {
        return getMongo().find(new Query(new Criteria("id").in(cateIds)), getEntityClass());
    }
    
}
