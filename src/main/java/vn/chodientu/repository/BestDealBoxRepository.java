package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.BestDealBox;

/**
 * @since Jun 2, 2014
 * @author Phuongdt
 */
@Repository
public class BestDealBoxRepository extends BaseRepository<BestDealBox> {

    public BestDealBoxRepository() {
        super(BestDealBox.class);
    }

    public List<BestDealBox> getAll() {
        Criteria criteria = new Criteria();
        return getMongo().find(new Query(criteria), getEntityClass());
    }
    public BestDealBox getByItemId(String id) {
        Criteria criteria = new Criteria();
        criteria.and("itemId").is(id);
        return getMongo().findOne(new Query(criteria), getEntityClass());
    }
    public List<BestDealBox> list() {
        Criteria criteria = new Criteria();
        criteria.and("active").is(true);
        return getMongo().find(new Query(criteria), getEntityClass());
    }
}
