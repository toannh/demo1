package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.FeaturedCategory;

/**
 * @since Jun 2, 2014
 * @author Phuongdt
 */
@Repository
public class FeaturedCategoryRepository extends BaseRepository<FeaturedCategory> {

    public FeaturedCategoryRepository() {
        super(FeaturedCategory.class);
    }

    public List<FeaturedCategory> list(boolean active) {
        Criteria criteria = new Criteria();
        if(active==true){
            criteria.and("active").is(active);
        }
        return getMongo().find(new Query(criteria).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }
    public FeaturedCategory getByCate(String id){
         return getMongo().findOne(new Query(new Criteria("categoryId").is(id)), getEntityClass());
    }
}
