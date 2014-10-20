package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.HotdealCategory;

/**
 *
 * @author thunt
 */
@Repository
public class HotdealCategoryRepository extends BaseRepository<HotdealCategory> {

    public HotdealCategoryRepository() {
        super(HotdealCategory.class);
    }

    public List<HotdealCategory> getChilds(String parentId, int active) {
        Criteria cri = new Criteria("parentId").is(parentId);
        if (active > 0) {
            if (active == 1) {
                cri.and("active").is(true);
                cri.and("startTime").lte(System.currentTimeMillis());
                cri.and("endTime").gte(System.currentTimeMillis());
            } else {
                cri.and("active").is(false);
            }
        }
        return getMongo().find(new Query(cri).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }
    public long countChilds(String parentId, int active) {
        Criteria cri = new Criteria("parentId").is(parentId);
        if (active > 0) {
            if (active == 1) {
                cri.and("active").is(true);
                cri.and("startTime").lte(System.currentTimeMillis());
                cri.and("endTime").gte(System.currentTimeMillis());
            } else {
                cri.and("active").is(false);
            }
        }
        return getMongo().count(new Query(cri), getEntityClass());
    }

    public List<HotdealCategory> getAll(int active) {
        Criteria cri = new Criteria();
        if (active > 0) {
            if (active == 1) {
                cri.and("active").is(true);
                cri.and("startTime").lte(System.currentTimeMillis());
                cri.and("endTime").gte(System.currentTimeMillis());
            } else {
                cri.and("active").is(false);
            }
        }
        return getMongo().find(new Query(cri).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }
}
