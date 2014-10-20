package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.data.CategoryItemHome;
import vn.chodientu.entity.db.FeaturedCategorySub;

/**
 * @since Jun 2, 2014
 * @author Phuongdt
 */
@Repository
public class FeaturedCategorySubRepository extends BaseRepository<FeaturedCategorySub> {

    public FeaturedCategorySubRepository() {
        super(FeaturedCategorySub.class);
    }

    public List<FeaturedCategorySub> list(boolean active) {
        Criteria criteria = new Criteria();
        if(active==true){
            criteria.and("active").is(active);
        }
        return getMongo().find(new Query(criteria).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }
    public List<FeaturedCategorySub> getByCategoryId(String id) {
        return getMongo().find(new Query(new Criteria("featuredCategororyId").is(id)).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }
    public List<FeaturedCategorySub> getByCategoryIdActive(String id) {
        Criteria criteria = new Criteria();
        criteria.and("featuredCategororyId").is(id);
        criteria.and("active").is(true);
        return getMongo().find(new Query(criteria).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }
    public FeaturedCategorySub getCategorySubId(String id) {
        return getMongo().findOne(new Query(new Criteria("categorySubId").is(id)), getEntityClass());
    }

    
}
