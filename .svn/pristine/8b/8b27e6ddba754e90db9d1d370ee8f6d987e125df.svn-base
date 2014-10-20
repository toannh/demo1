package vn.chodientu.repository;

import com.mongodb.DBObject;
import static java.lang.Long.parseLong;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Cash;

/**
 * @since May 20, 2014
 * @author Phu
 */
@Repository
public class CashRepository extends BaseRepository<Cash> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public CashRepository() {
        super(Cash.class);
    }

    /**
     * Lấy số dư tài khoản, nếu chưa có tài khoản sẽ tạo mới 1 tài khoản có số
     * dư = 0
     *
     * @param userId
     * @return
     */
    public Cash getCash(String userId) {
        Cash cash = getMongo().findOne(new Query(new Criteria("userId").is(userId)), getEntityClass());
        if (cash == null) {
            cash = new Cash();
            cash.setUserId(userId);
            cash.setBalance(0);
            getMongo().save(cash);
        }
        return cash;
    }

    /**
     * Cộng xèng vào tài khoản người dùng
     *
     * @param userId
     * @param money
     * @return
     */
    public Cash topupPaymentDone(String userId, long money) {
        return getMongo().findAndModify(new Query(new Criteria("userId").is(userId)),
                new Update().inc("balance", money),
                new FindAndModifyOptions().returnNew(true), getEntityClass());
    }

    /**
     * List xeng
     *
     * @param cri
     * @param pageSize
     * @param offset
     * @return
     */
    public List<Cash> list(Criteria cri, int pageSize, int offset) {
        Query query = new Query(cri);
        return getMongo().find(query.limit(pageSize).skip(offset), getEntityClass());
    }

    public List<Cash> listAll() {
        return getMongo().find(new Query(new Criteria()).with(new Sort(new Sort.Order(Sort.Direction.ASC, "order"))), getEntityClass());
    }

    /**
     * Tổng số xèng trên hệ thống
     *
     * @return
     */
    public long sumCash() {
        Aggregation aggregations = Aggregation.newAggregation(
                group("null").sum("balance").as("balance"),
                project("balance")
        );
        long cash = 0;
        AggregationResults<DBObject> results = mongoTemplate.aggregate(aggregations, "cash", DBObject.class);
        List<DBObject> fieldList = results.getMappedResults();
        Map<String, Long> map = new HashMap<>();
        if (fieldList != null && !fieldList.isEmpty()) {
            for (DBObject db : fieldList) {
                cash = parseLong(db.get("finalPrice").toString());
            }
        }
        return cash;
    }
}
