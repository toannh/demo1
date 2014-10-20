package vn.chodientu.repository;

import com.mongodb.DBObject;
import static java.lang.Long.parseLong;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Lading;

@Repository
public class LadingRepository extends BaseRepository<Lading> {

    public LadingRepository() {
        super(Lading.class);
    }

    public Lading get(String orderId) {
        return getMongo().findOne(new Query(new Criteria("orderId").is(orderId)), getEntityClass());
    }

    /**
     * Tính tổng giá trị vận chuyển theo điều kiện search
     *
     * @param cri
     * @return
     */
    public Map<String, Long> sumCodPrice(Criteria cri) {
        Aggregation aggregations = Aggregation.newAggregation(
                match(cri),
                group("null").sum("codPrice").as("codPrice").sum("shipmentPrice").as("shipmentPrice"), project("codPrice", "shipmentPrice")
        );
        AggregationResults<DBObject> results = mongoTemplate.aggregate(aggregations, "lading", DBObject.class);
        List<DBObject> fieldList = results.getMappedResults();
        Map<String, Long> map = new HashMap<>();
        if (fieldList != null && !fieldList.isEmpty()) {
            for (DBObject db : fieldList) {
                map.put("Tổng phí vận chuyển", parseLong(db.get("shipmentPrice").toString()));
                map.put("Tổng tiền thu hộ", parseLong(db.get("codPrice").toString()));
            }
        }
        return map;
    }

}
