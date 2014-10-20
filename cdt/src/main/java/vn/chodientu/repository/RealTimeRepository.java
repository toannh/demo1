package vn.chodientu.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.RealTime;

@Repository
public class RealTimeRepository extends BaseRepository<RealTime> {

    public RealTimeRepository() {
        super(RealTime.class);
    }

    public RealTime getForSend() {
        return getMongo().findAndModify(new Query(new Criteria("run").is(false)).with(new Sort(Sort.Direction.DESC, "time")), new Update().set("run", true).set("runTime", System.currentTimeMillis()), new FindAndModifyOptions().returnNew(true), getEntityClass());
    }

}
