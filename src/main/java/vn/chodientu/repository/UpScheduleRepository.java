package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.UpSchedule;

@Repository
public class UpScheduleRepository extends BaseRepository<UpSchedule> {

    public UpScheduleRepository() {
        super(UpSchedule.class);
    }

    public List<UpSchedule> list(Criteria cri, int pageSize, int offset) {
        Query query = new Query(cri);
        return getMongo().find(query.limit(pageSize).skip(offset).with(new Sort(Sort.Direction.DESC, "createTime")), getEntityClass());
    }

    public UpSchedule getByThread(int day) {
        Criteria cri = new Criteria();
        cri.and("done").is(false);
        cri.and("upDay").is(day);
        cri.and("lock").is(false);
        cri.and("lastTime").lt(System.currentTimeMillis() - 60 * 1000);
        return getMongo().findAndModify(new Query(cri).with(new Sort(Sort.Direction.ASC, "lastTime")), new Update().update("lock", true).update("lastTime", System.currentTimeMillis()), new FindAndModifyOptions().returnNew(true), getEntityClass());
    }

}
