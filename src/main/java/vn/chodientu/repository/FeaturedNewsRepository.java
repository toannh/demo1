package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.FeaturedNews;

/**
 * @since Jun 2, 2014
 * @author Phuongdt
 */
@Repository
public class FeaturedNewsRepository extends BaseRepository<FeaturedNews> {

    public FeaturedNewsRepository() {
        super(FeaturedNews.class);
    }

    public List<FeaturedNews> getAll(boolean active) {
        Criteria criteria = new Criteria();
        if (active == true) {
            criteria.and("active").is(true);
        }
        return getMongo().find(new Query(criteria).with(new Sort(Sort.Direction.DESC, "timeCreate")), getEntityClass());

    }

    public List<FeaturedNews> getByType(int type) {
        Criteria criteria = new Criteria();
        criteria.and("active").is(true);
        criteria.and("type").is(type);
        return getMongo().find(new Query(criteria).with(new Sort(Sort.Direction.DESC, "timeCreate")), getEntityClass());

    }

}
