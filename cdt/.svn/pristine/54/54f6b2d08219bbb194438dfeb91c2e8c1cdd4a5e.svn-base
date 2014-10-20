package vn.chodientu.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.CashHistory;
import vn.chodientu.entity.enu.CashTransactionType;

@Repository
public class CashHistoryRepository extends BaseRepository<CashHistory> {

    public CashHistoryRepository() {
        super(CashHistory.class);
    }

    /**
     * Đếm số lần theo time
     *
     * @param startTime
     * @param endTime
     * @param userId
     * @param type
     * @param fine
     * @return
     */
    public long totalCash(long startTime, long endTime, String userId, CashTransactionType type, int fine) {
        Criteria cri = new Criteria();
        cri.and("userId").is(userId);
        if (startTime > 0 && endTime > 0) {
            cri.and("createTime").gte(startTime).lte(endTime);
        }
        if (fine > 0) {
            cri.and("fine").is(fine == 1);
        }
        if (type != null) {
            cri.and("type").is(type.toString());
        }
        return getMongo().count(new Query(cri), getEntityClass());
    }

    public CashHistory find(CashTransactionType type, String userId, String objectId) {
        Criteria cri = new Criteria();
        cri.and("userId").is(userId);
        cri.and("type").is(type.toString());
        cri.and("objectId").is(objectId);
        return getMongo().findOne(new Query(cri), getEntityClass());
    }

}
