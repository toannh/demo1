package vn.chodientu.repository;

import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ShopContact;

/**
 * @since Jun 2, 2014
 * @author Phuongdt
 */
@Repository
public class ShopContactRepository extends BaseRepository<ShopContact> {

    public ShopContactRepository() {
        super(ShopContact.class);
    }
    
    public List<ShopContact> getShopContactByIdUser(String id){
        Criteria criteria = new Criteria();
        criteria.and("sellerId").is(id);
        return getMongo().find(new Query(criteria), getEntityClass());
    }
}
