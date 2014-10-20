/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.HomeBanner;

/**
 *
 * @author toannh
 */
@Repository
public class HomeBannerRepository extends BaseRepository<HomeBanner>{

    public HomeBannerRepository() {
        super(HomeBanner.class);
    }
    
    public List<HomeBanner> getHomeBanner(){
        return getMongo().find(new Query(), getEntityClass());
    }
    public List<HomeBanner> getAll(){
        Criteria criteria = new Criteria();
        criteria.and("active").is(true);
        return getMongo().find(new Query(criteria).limit(4).with(new Sort(Sort.Direction.ASC, "position")), getEntityClass());
    }
    
}
