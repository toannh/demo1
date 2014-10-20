package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.HotDealBox;

/**
 * @since Jun 2, 2014
 * @author Phuongdt
 */
@Repository
public class HotDealBoxRepository extends BaseRepository<HotDealBox> {

    public HotDealBoxRepository() {
        super(HotDealBox.class);
    }
    public List<HotDealBox> getAll(){
        Criteria criteria = new Criteria();
        return getMongo().find(new Query(criteria), getEntityClass());
        
    }
    public List<HotDealBox> list(){
        Criteria criteria = new Criteria();
        criteria.and("active").is(true);
        return getMongo().find(new Query(criteria), getEntityClass());
        
    }
    public HotDealBox getByItemId(String id){
        Criteria criteria = new Criteria();
        criteria.and("itemId").is(id);
        return getMongo().findOne(new Query(criteria), getEntityClass());
        
    }
    
    
    
    
}
