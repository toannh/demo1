package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.UpScheduleHistory;

@Repository
public class UpScheduleHistoryRepository extends BaseRepository<UpScheduleHistory> {

    public UpScheduleHistoryRepository() {
        super(UpScheduleHistory.class);
    }

    /**
     *
     * @param upScheduleId
     * @return
     */
    public List<UpScheduleHistory> getByUpScheduleId(String upScheduleId) {
        return getMongo().find(new Query(new Criteria("upScheduleId").is(upScheduleId)), getEntityClass());
    }

    public List<UpScheduleHistory> list(Criteria cri, int pageSize, int offset) {
        Query query = new Query(cri);
        return getMongo().find(query.limit(pageSize).skip(offset).with(new Sort(Sort.Direction.DESC, "createTime")), getEntityClass());
    }

    /**
     * Tổng số lượng up free trong ngày
     *
     * @param startTime
     * @param endTime
     * @param sellerId
     * @return
     */
    public long totalUpFreeByDay(long startTime, long endTime, String sellerId) {
        Criteria cri = new Criteria();
        cri.and("free").is(true);
        cri.and("sellerId").is(sellerId);
        cri.and("createTime").gte(startTime).lte(endTime);
        return getMongo().count(new Query(cri), getEntityClass());
    }
}
