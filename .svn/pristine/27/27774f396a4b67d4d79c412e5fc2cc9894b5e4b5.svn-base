package vn.chodientu.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.EmailOutbox;

/**
 * @since May 16, 2014
 * @author Phu
 */
@Repository
public class EmailOutboxRepository extends BaseRepository<EmailOutbox> {

    public EmailOutboxRepository() {
        super(EmailOutbox.class);
    }

    public EmailOutbox getForSend() {
        return getMongo().findAndModify(new Query(new Criteria("sent").is(false)).with(new Sort(Sort.Direction.DESC, "time")), new Update().set("sent", true).set("sentTime", System.currentTimeMillis()), new FindAndModifyOptions().returnNew(true), getEntityClass());
    }
}
