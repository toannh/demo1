package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.TopSellerBox;
import vn.chodientu.entity.form.TopSellerBoxItemForm;

/**
 * @since Jun 2, 2014
 * @author Phuongdt
 */
@Repository
public class TopSellerBoxRepository extends BaseRepository<TopSellerBox> {

    public TopSellerBoxRepository() {
        super(TopSellerBox.class);
    }

    public List<TopSellerBox> getAll() {
        Query query = new Query(new Criteria());
        return getMongo().find(query, getEntityClass());
    }

    public List<TopSellerBox> list() {
        Criteria criteria = new Criteria();
        criteria.and("active").is(true);
        Query query = new Query(criteria).with(new Sort(Sort.Direction.ASC, "position"));
        return getMongo().find(query, getEntityClass());
    }

    public TopSellerBox getBySellerId(String sellerId) {
        Criteria criteria = new Criteria();
        criteria.and("sellerId").is(sellerId);
        Query query = new Query(criteria);
        return getMongo().findOne(query, getEntityClass());
    }

    public boolean exitesItemBySeller(String sellerId, String itemId) {
        Criteria criteria = new Criteria();
        criteria.and("sellerId").is(sellerId);
        criteria.and("_id").is(itemId);
        Query query = new Query(criteria);
        return getMongo().count(query, getEntityClass()) > 0;
    }

}
