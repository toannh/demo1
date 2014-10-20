package vn.chodientu.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Email;

/**
 * @since May 16, 2014
 * @author Phu
 */
@Repository
public class EmailRepository extends BaseRepository<Email> {

    public EmailRepository() {
        super(Email.class);
    }

    /**
     * add contact
     *
     * @return
     */
    public Email addContact() {
        Criteria cri = new Criteria();
        cri.and("pushC").ne(true);
        return getMongo().findAndModify(new Query(cri).with(new Sort(Sort.Direction.ASC, "createTime")),
                new Update().set("pushC", true).set("updateTime", System.currentTimeMillis()), new FindAndModifyOptions().returnNew(true), getEntityClass());
    }

}
