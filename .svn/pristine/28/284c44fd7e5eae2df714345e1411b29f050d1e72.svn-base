package vn.chodientu.repository;

import com.mongodb.DBObject;
import static java.lang.Long.parseLong;
import static java.lang.Long.parseLong;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ItemReview;

@Repository
public class ItemReviewRepository extends BaseRepository<ItemReview> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public ItemReviewRepository() {
        super(ItemReview.class);
    }

    /**
     * Kiểm tra người dùng đã comment cho sản phẩm đó chưa
     *
     * @param userId
     * @param itemId
     * @return
     */
    public boolean exitesReviewByUser(String userId, String itemId) {
        Criteria cri = new Criteria();
        cri.and("itemId").is(itemId);
        cri.and("userId").is(userId);
        return getMongo().count(new Query(cri), getEntityClass()) > 0;
    }

    public long totalCommentByItem(String itemId) {
        return getMongo().count(new Query(new Criteria("itemId").is(itemId).and("active").is(true)), getEntityClass());
    }

    public long totalRecommended(String itemId) {
        return getMongo().count(new Query(new Criteria("itemId").is(itemId).and("recommended").is(true).and("active").is(true)), getEntityClass());
    }

    public long totalPoint(String itemId, int point) {
        return getMongo().count(new Query(new Criteria("itemId").is(itemId).and("point").is(point).and("active").is(true)), getEntityClass());
    }

    public long sumPoint(String itemId) {
        Criteria cri = new Criteria();
        cri.and("itemId").is(itemId);
        cri.and("active").is(true);
        Aggregation aggregations = Aggregation.newAggregation(
                match(cri),
                group("null").sum("point").as("point"),
                project("point")
        );
        long point = 0;
        AggregationResults<DBObject> results = mongoTemplate.aggregate(aggregations, "itemReview", DBObject.class);
        List<DBObject> fieldList = results.getMappedResults();
        Map<String, Long> map = new HashMap<>();
        if (fieldList != null && !fieldList.isEmpty()) {
            for (DBObject db : fieldList) {
                point = parseLong(db.get("point").toString());
            }
        }
        return point;
    }

}
