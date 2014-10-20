package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ShopHomeItem;

@Repository
public class ShopHomeItemRepository extends BaseRepository<ShopHomeItem> {

    public ShopHomeItemRepository() {
        super(ShopHomeItem.class);
    }

    public List<ShopHomeItem> list(String sellerId) {
        Criteria criteria = new Criteria("sellerId").is(sellerId);
        criteria.and("active").is(true);
        return getMongo().find(new Query(criteria), getEntityClass());
    }

    /**
     * Đếm tổng số lượng item theo danh mục
     *
     * @param categoryId
     * @return
     */
    public long countByCategory(String categoryId) {
        return count(new Query(new Criteria("categoryId").is(categoryId)));
    }

}
