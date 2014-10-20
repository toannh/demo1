package vn.chodientu.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.report.ReportUser;

/**
 * @since May 20, 2014
 * @author PhuongDT
 */
@Repository
public class ReportUserRepository extends BaseRepository<ReportUser> {

    public ReportUserRepository() {
        super(ReportUser.class);
    }

    public ReportUser find(long time) {
        return getMongo().findOne(new Query(new Criteria("time").is(time)), getEntityClass());
    }
    
}
