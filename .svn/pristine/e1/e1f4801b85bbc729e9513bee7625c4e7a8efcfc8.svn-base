package vn.chodientu.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Bid;

/**
 *
 * @author Phu
 */
@Repository
public class BidRepository extends BaseRepository<Bid> {

    public BidRepository() {
        super(Bid.class);
    }

    /**
     * Lấy bid cao nhất của 1 item
     *
     * @param itemId
     * @return
     */
    public Bid getHighest(String itemId) {
        return getMongo().findOne(new Query(new Criteria("itemId").is(itemId).and("auto").is(false)).with(new Sort(Sort.Direction.DESC, "bid")), getEntityClass());
    }

    /**
     * Lấy auto bid cao nhất của 1 item
     *
     * @param itemId
     * @return
     */
    public Bid getHighestAuto(String itemId) {
        return getMongo().findOne(new Query(new Criteria("itemId").is(itemId).and("auto").is(true)).with(new Sort(Sort.Direction.DESC, "bid")), getEntityClass());
    }

    /**
     * Đếm số bid của 1 sản phẩm
     *
     * @param itemId
     * @return
     */
    public int count(String itemId) {
        return (int) count(new Query(new Criteria("itemId").is(itemId).and("auto").is(false)));
    }

    /**
     * Lấy lịch sử đấu giá của 1 sản phẩm
     *
     * @param itemId
     * @return
     */
    public List<Bid> bidHistory(String itemId) {
        return find(new Query(new Criteria("itemId").is(itemId).and("auto").is(false)).with(new Sort(Sort.Direction.DESC, "time")));
    }

    /**
     * Lấy tất cả sản phẩm đấu giá của user
     *
     * @param userId
     * @return
     */
    public List<String> getDistincBidByUser(String userId) {
        DBCollection myColl = getMongo().getCollection("bid");
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("bider", userId);
        gtQuery.put("auto", false);
        
        List distinct = myColl.distinct("itemId", gtQuery);

        return distinct;
    }
}
