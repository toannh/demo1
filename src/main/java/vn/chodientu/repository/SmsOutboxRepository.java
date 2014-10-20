package vn.chodientu.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.SmsOutbox;

/**
 * @since May 17, 2014
 * @author Phu
 */
@Repository
public class SmsOutboxRepository extends BaseRepository<SmsOutbox> {

    public SmsOutboxRepository() {
        super(SmsOutbox.class);
    }
    
    public SmsOutbox getForSend() {
        return getMongo().findAndModify(new Query(new Criteria("sent").is(false)).with(new Sort(Sort.Direction.DESC, "time")), new Update().set("sent", true).set("sentTime", System.currentTimeMillis()), new FindAndModifyOptions().returnNew(true), getEntityClass());
    }
}
