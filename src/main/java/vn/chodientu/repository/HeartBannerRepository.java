package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.HeartBanner;

/**
 * @since Jun 2, 2014
 * @author Phuongdt
 */
@Repository
public class HeartBannerRepository extends BaseRepository<HeartBanner> {

    public HeartBannerRepository() {
        super(HeartBanner.class);
    }
    public List<HeartBanner> list(Criteria cri, int pageSize, int pageIndex) {
        return getMongo().find(new Query(cri).limit(pageSize).skip(pageSize * pageIndex).with(new Sort(Sort.Direction.DESC, "id")), getEntityClass());
    }
    public List<HeartBanner> getAll() {
        Criteria criteria = new Criteria();
        criteria.and("active").is(true);
        return getMongo().find(new Query(criteria).limit(4).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }
}
