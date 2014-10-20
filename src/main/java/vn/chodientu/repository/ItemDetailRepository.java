package vn.chodientu.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.ItemDetail;

/**
 * @since May 14, 2014
 * @author Phu
 */
@Repository
public class ItemDetailRepository extends BaseRepository<ItemDetail> {

    public ItemDetailRepository() {
        super(ItemDetail.class);
    }

    public void remove(String itemId) {
        getMongo().remove(new Query(new Criteria("itemId").is(itemId)), getEntityClass());
    }

    @Override
    public ItemDetail find(String id) {
        return getMongo().findOne(new Query(new Criteria("itemId").is(id)), getEntityClass());
    }
}
