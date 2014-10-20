package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.LandingCategory;

/**
 *
 * @author thunt
 */
@Repository
public class LandingCategoryRepository extends BaseRepository<LandingCategory> {

    public LandingCategoryRepository() {
        super(LandingCategory.class);
    }

    public List<LandingCategory> getByLanding(String landingId) {
        Criteria cri = new Criteria("landingId").is(landingId);
        return getMongo().find(new Query(cri).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }
    public void deleteByLanding(String landingId) {
        Criteria cri = new Criteria("landingId").is(landingId);
        getMongo().remove(new Query(cri), getEntityClass());
    }
}
