package vn.chodientu.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.report.ReportLading;

/**
 * @since May 20, 2014
 * @author PhuongDT
 */
@Repository
public class ReportLadingRepository extends BaseRepository<ReportLading> {

    public ReportLadingRepository() {
        super(ReportLading.class);
    }

    public ReportLading find(long time) {
        return getMongo().findOne(new Query(new Criteria("time").is(time)), getEntityClass());
    }

}
