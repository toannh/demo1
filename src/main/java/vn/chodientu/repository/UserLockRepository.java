package vn.chodientu.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.UserLock;

@Repository
public class UserLockRepository extends BaseRepository<UserLock> {

    public UserLockRepository() {
        super(UserLock.class);
    }

    public UserLock getProcess() {
        return getMongo().findAndModify(new Query(new Criteria("run").is(false).and("done").is(false).and("endTime").lte(System.currentTimeMillis())),
                new Update().set("run", true).set("done", true).set("updateTime", System.currentTimeMillis()), new FindAndModifyOptions().returnNew(true), getEntityClass());

    }

    public UserLock getLockIsRunByUserId(String id) {
        return getMongo().findOne(new Query(new Criteria("run").is(true).and("userId").is(id)).with(new Sort(new Sort.Order(Sort.Direction.DESC, "endTime"))), getEntityClass());
    }

}
