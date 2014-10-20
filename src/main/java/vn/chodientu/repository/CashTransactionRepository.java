package vn.chodientu.repository;

import com.mongodb.DBObject;
import static java.lang.Long.parseLong;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.output.CashSumary;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Repository
public class CashTransactionRepository extends BaseRepository<CashTransaction> {

    public CashTransactionRepository() {
        super(CashTransaction.class);
    }

    public List<CashTransaction> list(Criteria cri, int pageSize, int pageIndex) {
        return getMongo().find(new Query(cri).limit(pageSize).skip(pageSize * pageIndex).with(new Sort(Sort.Direction.DESC, "time")), getEntityClass());
    }

    /**
     * Tính tổng tiền của các giao dịch theo điều kiện
     *
     * @param cri
     * @return
     */
    public long userAmountSumary(Criteria cri) {
        AggregationOperation match = Aggregation.match(cri);
        AggregationOperation group = Aggregation.group("userId").sum("amount").as("value");
        Aggregation aggregation = newAggregation(match, group);
        AggregationResults<CashSumary> result = getMongo().aggregate(aggregation, "cashTransaction", CashSumary.class);
        CashSumary cashSumary = result.getUniqueMappedResult();

        return cashSumary == null ? 0 : cashSumary.getValue();
    }

    /**
     * Tính tổng tiền theo điều kiện search
     *
     * @param cri
     * @return
     */
    public Map<String, Long> sellerSumBlance(Criteria cri) {
        Aggregation aggregations = Aggregation.newAggregation(
                match(cri),
                project("userId").andExpression("amount * spentQuantity").as("amountX"),
                group("userId").sum("amountX").as("amountA")
        );
        AggregationResults<DBObject> results = mongoTemplate.aggregate(aggregations, "cashTransaction", DBObject.class);
        List<DBObject> fieldList = results.getMappedResults();
        Map<String, Long> map = new HashMap<>();
        if (fieldList != null && !fieldList.isEmpty()) {
            for (DBObject db : fieldList) {
                map.put(db.get("_id").toString(), parseLong(db.get("amountA").toString()));
            }
        }
        return map;
    }
    
    public long sumForReport(Criteria cri) {
        long total = 0;
        Aggregation aggregations = Aggregation.newAggregation(
                match(cri),
                project("nlStatus").andExpression("amount * spentQuantity").as("amountX"),
                group("nlStatus").sum("amountX").as("amountA")
        );
        AggregationResults<DBObject> results = mongoTemplate.aggregate(aggregations, "cashTransaction", DBObject.class);
        List<DBObject> fieldList = results.getMappedResults();
        if (fieldList != null && !fieldList.isEmpty()) {
            for (DBObject db : fieldList) {
               total += parseLong(db.get("amountA").toString());
            }
        }
       
        return total;
    }
}
