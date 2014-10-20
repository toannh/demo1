package vn.chodientu.repository;

import com.mongodb.DBObject;
import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.OrderItem;

/**
 *
 * @author Phu
 */
@Repository
public class OrderItemRepository extends BaseRepository<OrderItem> {

    public OrderItemRepository() {
        super(OrderItem.class);
    }

    public List<OrderItem> getByOrderId(String orderId) {
        Criteria cri = new Criteria();
        cri.and("orderId").is(orderId);
        return getMongo().find(new Query(cri), getEntityClass());
    }
}
